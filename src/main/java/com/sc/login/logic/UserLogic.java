package com.sc.login.logic;

import com.sc.login.mapper.UserMapper;
import com.sc.utils.TypeEnum;
import com.sc.utils.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLogic {

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过用户名查询用户信息
     */
    public UserEntity getUserInfo(String userName){
        UserEntity entity = new UserEntity();
        entity.setUserName(userName);
        entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
        UserEntity user = userMapper.getUserInfo(entity);
        return user;
    }
}
