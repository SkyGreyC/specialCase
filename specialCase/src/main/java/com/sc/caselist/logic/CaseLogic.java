package com.sc.caselist.logic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sc.caselist.mapper.CaseMapper;
import com.sc.caselist.mapper.LesionMapper;
import com.sc.caselist.service.vo.CaseVO;
import com.sc.caselist.service.vo.LesionVO;
import com.sc.login.service.vo.UserVO;
import com.sc.utils.CommonUtils;
import com.sc.utils.MapToBean;
import com.sc.utils.TypeEnum;
import com.sc.utils.entity.CaseEntity;
import com.sc.utils.entity.LesionEntity;
import com.sc.utils.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CaseLogic {

    @Autowired
    private LesionLogic lesionLogic;

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
        CaseEntity entity = MapToBean.toBean(caseVO, CaseEntity.class);
        String caseId = CommonUtils.getUUID();
        entity.setCaseId(caseId);
        entity.setCreatorId(userVO.getUserId());
        entity.setCreatorName(userVO.getUserName());
        entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
        caseMapper.insertCase(entity);

        lesionLogic.insertLesions(lesionVOs,userVO,caseId);
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

        return lesionLogic.updateLesions(lesionVOs,userVO,entity.getCaseId());
    }

    /**
     * 删除单个病例
     */
    @Transactional
    public int deleteCase(CaseVO caseVO, UserVO userVO){
        CaseEntity entity = MapToBean.toBean(caseVO, CaseEntity.class);
        entity.setCreatorId(userVO.getUserId());
        entity.setCreatorName(userVO.getUserName());
        entity.setUpdateTime(CommonUtils.getNow());
        entity.setIsDel(TypeEnum.IS_DEL.YES.toString());
        return caseMapper.updateByKey(entity);
    }

    /**
     * 获取病例列表
     */
    public List<CaseEntity> findCaseList(Page<CaseVO> page){
        CaseEntity entity = new CaseEntity();
        entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
        return caseMapper.findByWhere(page,entity);
    }

    /**
     * 获取病例详情
     */
    public CaseVO findCaseDetail(String caseId){
        CaseEntity entity = caseMapper.loadByKey(caseId);
        LesionEntity lesionEntity=new LesionEntity();
        lesionEntity.setCaseId(caseId);
        lesionEntity.setIsDel(TypeEnum.IS_DEL.NO.toString());
        List<LesionEntity> lesionEntities = lesionMapper.findByWhere(lesionEntity);
        List<LesionVO> lesionVOs = MapToBean.toList(lesionEntities,LesionVO.class);
        CaseVO caseVO = MapToBean.toBean(entity,CaseVO.class);
        caseVO.setLesionVOs(lesionVOs);
        return caseVO;
    }
}
