package com.sc.caselist.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sc.utils.entity.ImageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImageMapper {

    /**
     * loadByKey
     */
    ImageEntity loadByKey(@Param("imageId") String imageId);

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
    List<ImageEntity> findByWhere(@Param("image") ImageEntity entity);

    /**
     * 删除关联附件信息
     */
    int deleteImageInfoById(@Param("resourceId") String resourceId);
}
