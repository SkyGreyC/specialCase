package com.sc.home.logic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sc.caselist.logic.ImageLogic;
import com.sc.caselist.service.vo.ImageVO;
import com.sc.home.mapper.HomeMapper;
import com.sc.home.service.vo.HomeVO;
import com.sc.login.service.vo.UserVO;
import com.sc.utils.CommonUtils;
import com.sc.utils.MapToBean;
import com.sc.utils.TypeEnum;
import com.sc.utils.entity.HomeEntity;
import com.sc.utils.entity.ImageEntity;
import com.sc.utils.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class HomeLogic {

    @Autowired
    private HomeMapper homeMapper;

    @Autowired
    private ImageLogic imageLogic;

    /**
     * 新增首页信息
     */
    @Transactional
    public int saveHomeInfo(HomeVO homeVO){
        HomeEntity entity = MapToBean.toBean(homeVO, HomeEntity.class);
        List<ImageVO> imageVOs=homeVO.getImageVOs();
        String homeId = entity.getHomeId();
        if(CommonUtils.isNull(homeId)){
            homeId = CommonUtils.getUUID();
            entity.setHomeId(homeId);
            entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
            imageLogic.saveImageInfo(imageVOs,new UserEntity(),homeId);
            return homeMapper.insertHome(entity);
        }else {
            imageLogic.saveImageInfo(imageVOs,new UserEntity(),homeId);
            return homeMapper.updateByKey(entity);
        }
    }

    /**
     * 修改首页信息
     */
    @Transactional
    public int updateHomeInfo(HomeEntity entity){
        return homeMapper.updateByKey(entity);
    }

    /**
     * 删除首页信息
     */
    @Transactional
    public int deleteHomeInfo(String homeId){
        HomeEntity entity = new HomeEntity();
        entity.setHomeId(homeId);
        entity.setIsDel(TypeEnum.IS_DEL.YES.toString());
        return homeMapper.updateByKey(entity);
    }

    /**
     * 查询首页信息
     */
    public List<HomeVO> findHomeInfo(HomeEntity entity){
        entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
        List<HomeEntity> entities = homeMapper.findByWhere(entity);
        List<HomeVO> homeVOs = MapToBean.toList(entities,HomeVO.class);
        for(HomeVO homeVO:homeVOs){
            List<ImageEntity> imageEntities = imageLogic.findImageByResourceId(homeVO.getHomeId());
            List<ImageVO> imageVOs = MapToBean.toList(imageEntities,ImageVO.class);
            homeVO.setImageVOs(imageVOs);
        }
        return homeVOs;
    }

    /**
     * 查询首页信息详情
     */
    public HomeVO findHomeDetail(String homeId){
        HomeEntity homeEntity = homeMapper.loadByKey(homeId);
        HomeVO homeVO = MapToBean.toBean(homeEntity,HomeVO.class);
        List<ImageEntity> imageEntities = imageLogic.findImageByResourceId(homeVO.getHomeId());
        List<ImageVO> imageVOs = MapToBean.toList(imageEntities,ImageVO.class);
        homeVO.setImageVOs(imageVOs);
        return homeVO;
    }

}
