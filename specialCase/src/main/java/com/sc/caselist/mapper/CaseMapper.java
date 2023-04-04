package com.sc.caselist.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sc.utils.entity.CaseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CaseMapper {

    /**
     * 修改
     */
    int updateByKey(@Param("case") CaseEntity entity);

    /**
     * 新增信息
     */
    int insertCase(@Param("case") CaseEntity entity);

    /**
     * 查询信息
     */
    List<CaseEntity> findByWhere(IPage<?> page, @Param("case") CaseEntity entity);

    /**
     * loadByKey
     */
    CaseEntity loadByKey( @Param("caseId") String caseId);
}
