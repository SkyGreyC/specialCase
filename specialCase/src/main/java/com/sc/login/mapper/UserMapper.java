package com.sc.login.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sc.utils.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 通过用户名查询用户信息
     */
    UserEntity getUserInfo(@Param("user") UserEntity entity);

    /**
     * 通过userId获取用户
     */
    UserEntity findUserByUserId(@Param("user") UserEntity entity);

    /**
     * 修改用户
     */
    int updateByKey(@Param("user") UserEntity entity);

    /**
     * 删除用户
     */
    int deleteByUserId(@Param("user") UserEntity entity);

    /**
     * 新增用户
     */
    int insertUser(@Param("user") UserEntity entity);

    /**
     * 查询用户列表
     */
    List<UserEntity> findByWhere(IPage<?> page, @Param("user") UserEntity entity);
}
