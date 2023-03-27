package com.sc.login.mapper;

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
}
