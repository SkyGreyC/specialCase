package com.sc.home.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sc.utils.entity.HomeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HomeMapper {

    /**
     * 查询首页信息
     */
    HomeEntity getHomeInfo(@Param("home") HomeEntity entity);

    /**
     * 修改首页信息
     */
    int updateByKey(@Param("home") HomeEntity entity);

    /**
     * 新增首页信息
     */
    int insertHome(@Param("home") HomeEntity entity);

    /**
     * 查询首页信息
     */
    List<HomeEntity> findByWhere(IPage<?> page, @Param("home") HomeEntity entity);
}
