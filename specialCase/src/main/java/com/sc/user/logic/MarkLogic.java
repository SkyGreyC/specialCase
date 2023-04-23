package com.sc.user.logic;

import com.sc.user.mapper.MarkMapper;
import com.sc.utils.CommonUtils;
import com.sc.utils.TypeEnum;
import com.sc.utils.entity.HomeEntity;
import com.sc.utils.entity.MarkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class MarkLogic {

    @Autowired
    private MarkMapper markMapper;

    /**
     * 保存收藏信息
     */
    @Transactional
    public int saveMarkInfo(String caseId, String userId){
        MarkEntity entity = markMapper.findByUserIdAndCaseId(caseId,userId);
        MarkEntity markEntity = new MarkEntity();
        markEntity.setCaseId(caseId);
        markEntity.setUserId(userId);
        if(entity==null){
            markEntity.setMarkId(CommonUtils.getUUID());
            markEntity.setIsDel(TypeEnum.IS_DEL.NO.toString());
            markMapper.insertMark(markEntity);
        }else {
            markEntity.setMarkId(entity.getMarkId());
            String isDel = entity.getIsDel().equals(TypeEnum.IS_DEL.NO.toString())?
                    TypeEnum.IS_DEL.YES.toString():TypeEnum.IS_DEL.NO.toString();
            markEntity.setIsDel(isDel);
            markMapper.updateByKey(markEntity);
        }
        return 1;
    }
}
