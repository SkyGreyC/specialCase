package com.sc.home.logic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sc.home.mapper.HomeMapper;
import com.sc.login.service.vo.UserVO;
import com.sc.utils.CommonUtils;
import com.sc.utils.TypeEnum;
import com.sc.utils.entity.HomeEntity;
import com.sc.utils.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class HomeLogic {

    @Autowired
    private HomeMapper homeMapper;

    /**
     * 新增首页信息
     */
    @Transactional
    public int insertHomeInfo(HomeEntity entity){
        entity.setHomeId(CommonUtils.getUUID());
        entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
        return homeMapper.insertHome(entity);
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
    public int deleteHomeInfo(HomeEntity entity){
        entity.setIsDel(TypeEnum.IS_DEL.YES.toString());
        return homeMapper.updateByKey(entity);
    }

    /**
     * 查询首页信息
     */
    public List<HomeEntity> findHomeInfo(HomeEntity entity){
        entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
        return homeMapper.findByWhere(entity);
    }

    /**
     * 查询首页信息详情
     */
    public HomeEntity findHomeDetail(String homeId){
        return homeMapper.loadByKey(homeId);
    }

}
