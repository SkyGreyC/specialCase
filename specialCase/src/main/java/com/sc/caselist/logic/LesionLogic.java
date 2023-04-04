package com.sc.caselist.logic;

import com.sc.caselist.mapper.LesionMapper;
import com.sc.caselist.service.vo.CaseVO;
import com.sc.caselist.service.vo.LesionVO;
import com.sc.home.mapper.HomeMapper;
import com.sc.login.service.vo.UserVO;
import com.sc.utils.CommonUtils;
import com.sc.utils.MapToBean;
import com.sc.utils.TypeEnum;
import com.sc.utils.entity.CaseEntity;
import com.sc.utils.entity.LesionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class LesionLogic {

    @Autowired
    private LesionMapper lesionMapper;

    /**
     * 新增多个病灶
     */
    @Transactional
    public int insertLesions(List<LesionVO> lesions, UserVO userVO,String caseId){
        int res=0;
        List<LesionEntity> entities = MapToBean.toList(lesions,LesionEntity.class);
        for(LesionEntity entity:entities){
            entity.setLesionId(CommonUtils.getUUID());
            entity.setCaseId(caseId);
            entity.setCreatorId(userVO.getUserId());
            entity.setCreatorName(userVO.getUserName());
            entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
            res+=lesionMapper.insertLesion(entity);
        }
        return res;
    }

    /**
     * 修改多个病灶
     */
    @Transactional
    public int updateLesions(List<LesionVO> lesions, UserVO userVO,String caseId){
        int res=0;
        List<LesionEntity> entities = MapToBean.toList(lesions,LesionEntity.class);
        for(LesionEntity entity:entities){
            if(CommonUtils.isNull(entity.getLesionId())){
                entity.setLesionId(CommonUtils.getUUID());
                entity.setCaseId(caseId);
                entity.setCreatorId(userVO.getUserId());
                entity.setCreatorName(userVO.getUserName());
                entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
                res+=lesionMapper.insertLesion(entity);
            }else{
                entity.setCreatorId(userVO.getUserId());
                entity.setCreatorName(userVO.getUserName());
                entity.setUpdateTime(CommonUtils.getNow());
                res+=lesionMapper.updateByKey(entity);
            }
        }
        return res;
    }
}
