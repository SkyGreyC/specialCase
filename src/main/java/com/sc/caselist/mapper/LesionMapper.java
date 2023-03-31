package com.sc.caselist.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sc.utils.entity.LesionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LesionMapper {

    /**
     * 修改
     */
    int updateByKey(@Param("lesion") LesionEntity entity);

    /**
     * 新增信息
     */
    int insertLesion(@Param("lesion") LesionEntity entity);

    /**
     * 查询信息
     */
    List<LesionEntity> findByWhere(IPage<?> page, @Param("lesion") LesionEntity entity);
}
