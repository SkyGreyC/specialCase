package com.sc.user.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sc.utils.entity.MarkEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MarkMapper {

    /**
     * 修改
     */
    int updateByKey(@Param("mark") MarkEntity entity);

    /**
     * 新增信息
     */
    int insertMark(@Param("mark") MarkEntity entity);

    /**
     * 查询信息
     */
    List<MarkEntity> findByWhere(IPage<?> page, @Param("mark") MarkEntity entity);

    /**
     * 查询信息
     */
    MarkEntity findByUserIdAndCaseId(@Param("caseId") String caseId,@Param("userId") String userId);
}
