package com.sc.login.mapper;

import com.sc.utils.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserEntity> findUser();
}
