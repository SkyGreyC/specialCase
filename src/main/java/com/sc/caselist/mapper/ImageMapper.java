package com.sc.caselist.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sc.utils.entity.ImageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImageMapper {

    /**
     * 修改
     */
    int updateByKey(@Param("image") ImageEntity entity);

    /**
     * 新增信息
     */
    int insertImage(@Param("image") ImageEntity entity);

    /**
     * 查询信息
     */
    List<ImageEntity> findByWhere(IPage<?> page, @Param("image") ImageEntity entity);
}
