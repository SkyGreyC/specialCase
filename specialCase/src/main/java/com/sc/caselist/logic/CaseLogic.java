package com.sc.caselist.logic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sc.caselist.mapper.CaseMapper;
import com.sc.caselist.mapper.LesionMapper;
import com.sc.caselist.service.vo.CaseVO;
import com.sc.caselist.service.vo.ImageVO;
import com.sc.caselist.service.vo.LesionVO;
import com.sc.login.logic.UserLogic;
import com.sc.login.service.vo.UserVO;
import com.sc.user.logic.MarkLogic;
import com.sc.user.mapper.MarkMapper;
import com.sc.utils.CommonUtils;
import com.sc.utils.MapToBean;
import com.sc.utils.TypeEnum;
import com.sc.utils.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class CaseLogic {

    @Autowired
    private LesionLogic lesionLogic;

    @Autowired
    private ImageLogic imageLogic;

    @Autowired
    private UserLogic userLogic;

    @Autowired
    private MarkMapper markMapper;

    @Autowired
    private CaseMapper caseMapper;

    @Autowired
    private LesionMapper lesionMapper;


    /**
     * 新增单个病例
     */
    @Transactional
    public String insertCase(CaseVO caseVO, UserVO userVO){
        List<LesionVO> lesionVOs=caseVO.getLesionVOs();
//        List<ImageVO> imageVOs=caseVO.getImageVOs();
        CaseEntity entity = MapToBean.toBean(caseVO, CaseEntity.class);
        String caseId = CommonUtils.getUUID();
        entity.setCaseId(caseId);
        entity.setCreatorId(userVO.getUserId());
        entity.setCreatorName(userVO.getUserName());
        entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
        caseMapper.insertCase(entity);

//        lesionLogic.insertLesions(lesionVOs,userVO,caseId);
//        imageLogic.saveImageInfo(imageVOs,userVO);
        return caseId;
    }

    /**
     * 新增或修改单个病例
     */
    @Transactional
    public String saveCase(CaseVO caseVO, String userId){
        UserEntity user = userLogic.findUserByUserId(userId);
        List<LesionVO> lesionVOs=caseVO.getLesionVOs();
        ImageVO caseImageVO=caseVO.getCaseImageVO();
        ImageVO labelImageVO=caseVO.getLabelImageVO();
        List<ImageVO> imageVOs = new ArrayList<>();
        imageVOs.add(caseImageVO);
        imageVOs.add(labelImageVO);
        String caseId = caseVO.getCaseId();
        if(CommonUtils.isNull(caseId)){
            caseId = CommonUtils.getUUID();
            CaseEntity entity = MapToBean.toBean(caseVO, CaseEntity.class);
            entity.setCaseId(caseId);
            entity.setCreatorId(user.getUserId());
            entity.setCreatorName(user.getUserName());
            entity.setIsSample(TypeEnum.IS_SAMPLE.NO.toString());
            entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
            caseMapper.insertCase(entity);
            lesionLogic.insertLesions(lesionVOs,user,caseId);
            imageLogic.saveImageInfo(imageVOs,user,caseId);
        }else {
            CaseEntity entity = MapToBean.toBean(caseVO, CaseEntity.class);
            entity.setCreatorId(user.getUserId());
            entity.setCreatorName(user.getUserName());
            caseMapper.updateByKey(entity);
            lesionLogic.updateLesions(lesionVOs,user,caseId);
            imageLogic.saveImageInfo(imageVOs,user,caseId);
        }
        return caseId;
    }

    /**
     * 修改单个病例
     */
    @Transactional
    public int updateCase(CaseVO caseVO, UserVO userVO){
        List<LesionVO> lesionVOs=caseVO.getLesionVOs();
        CaseEntity entity = MapToBean.toBean(caseVO, CaseEntity.class);
        entity.setCreatorId(userVO.getUserId());
        entity.setCreatorName(userVO.getUserName());
        entity.setUpdateTime(CommonUtils.getNow());
        caseMapper.updateByKey(entity);

//        return lesionLogic.updateLesions(lesionVOs,userVO,entity.getCaseId());
        return 0;
    }


    /**
     * 删除单个病例
     */
    @Transactional
    public int deleteCase(String caseId){
        CaseEntity entity = new CaseEntity();
        entity.setCaseId(caseId);
        entity.setUpdateTime(CommonUtils.getNow());
        entity.setIsDel(TypeEnum.IS_DEL.YES.toString());
        return caseMapper.updateByKey(entity);
    }

    /**
     * 获取病例列表
     */
    public List<CaseVO> findCaseList(Page<CaseVO> page,String userId){
        CaseEntity entity = new CaseEntity();
        entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
        List<CaseEntity> caseEntities = caseMapper.findByWhere(page,entity);
        List<CaseVO> caseVOs = MapToBean.toList(caseEntities,CaseVO.class);
        for(CaseVO caseVO: caseVOs){
            MarkEntity markEntity = markMapper.findByUserIdAndCaseId(caseVO.getCaseId(),userId);
            if(markEntity!=null){
                caseVO.setIsMarked(TypeEnum.IS_MARKED.YES.toString());
            }else{
                caseVO.setIsMarked(TypeEnum.IS_MARKED.NO.toString());
            }
        }
        return caseVOs;
    }

    /**
     * 获取病例详情
     */
    public CaseVO findCaseDetail(String caseId,String userId){
        CaseEntity entity = caseMapper.loadByKey(caseId);
        LesionEntity lesionEntity=new LesionEntity();
        lesionEntity.setCaseId(caseId);
        lesionEntity.setIsDel(TypeEnum.IS_DEL.NO.toString());
        List<LesionEntity> lesionEntities = lesionMapper.findByWhere(lesionEntity);
        List<LesionVO> lesionVOs = MapToBean.toList(lesionEntities,LesionVO.class);
        CaseVO caseVO = MapToBean.toBean(entity,CaseVO.class);
        caseVO.setLesionVOs(lesionVOs);
        List<ImageEntity> imageEntities = imageLogic.findImageByResourceId(caseId);
        List<ImageVO> imageVOs = MapToBean.toList(imageEntities,ImageVO.class);
        for(ImageVO imageVO:imageVOs){
            if(imageVO.getResourceType().equals(TypeEnum.RESOURCE_TYPE.CASE.toString())){
                caseVO.setCaseImageVO(imageVO);
            }else if(imageVO.getResourceType().equals(TypeEnum.RESOURCE_TYPE.LESION.toString())){
                caseVO.setLabelImage(imageVO);
            }
        }
        MarkEntity markEntity = markMapper.findByUserIdAndCaseId(caseVO.getCaseId(),userId);
        if(markEntity!=null){
            caseVO.setIsMarked(markEntity.getIsDel());
        }else{
            caseVO.setIsMarked(TypeEnum.IS_MARKED.NO.toString());
        }
        return caseVO;
    }
}
