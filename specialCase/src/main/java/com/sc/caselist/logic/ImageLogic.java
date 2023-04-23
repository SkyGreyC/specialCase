package com.sc.caselist.logic;

import com.sc.caselist.mapper.ImageMapper;
import com.sc.caselist.service.vo.CaseVO;
import com.sc.caselist.service.vo.ImageVO;
import com.sc.caselist.service.vo.LesionVO;
import com.sc.login.service.vo.UserVO;
import com.sc.utils.CommonUtils;
import com.sc.utils.MapToBean;
import com.sc.utils.TypeEnum;
import com.sc.utils.entity.CaseEntity;
import com.sc.utils.entity.HomeEntity;
import com.sc.utils.entity.ImageEntity;
import com.sc.utils.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ImageLogic {

    @Autowired
    private ImageMapper imageMapper;

    /**
     * loadByKey
     */
    public ImageEntity loadByKey(String imageId){
        return imageMapper.loadByKey(imageId);
    }

    /**
     * 查询图片信息
     */
    public List<ImageEntity> findImageByResourceId(String resourceId){
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setResourceId(resourceId);
        imageEntity.setIsDel(TypeEnum.IS_DEL.NO.toString());
        return imageMapper.findByWhere(imageEntity);
    }

    /**
     * 保存关联关系
     */
    @Transactional
    public int saveImageInfo(List<ImageVO> imageVOs, UserEntity user, String caseId){
        //先删除之前的关联附件信息
        if(CommonUtils.isNotNull(caseId)){
            this.deleteImageInfoById(caseId);
        }
        int res=0;
        List<ImageEntity> imageEntities = MapToBean.toList(imageVOs,ImageEntity.class);
        for(ImageEntity entity:imageEntities){
            if(CommonUtils.isNull(entity.getResourceId())){
                entity.setResourceId(caseId);
                entity.setCreatorId(user.getUserId());
                entity.setCreatorName(user.getUserName());
                entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
                res+=imageMapper.insertImage(entity);
            } else {
                entity.setCreatorId(user.getUserId());
                entity.setCreatorName(user.getUserName());
                entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
                res+=imageMapper.updateByKey(entity);
            }
        }
        return res;
    }

    /**
     * 删除关联附件信息
     */
    private int deleteImageInfoById(String resourceId){
        return imageMapper.deleteImageInfoById(resourceId);
    }
}
