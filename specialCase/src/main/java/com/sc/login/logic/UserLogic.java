package com.sc.login.logic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sc.login.mapper.UserMapper;
import com.sc.login.service.vo.UserVO;
import com.sc.utils.CommonUtils;
import com.sc.utils.TypeEnum;
import com.sc.utils.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 重置用户密码
     */
    @Transactional
    public int resetPassword(String userId,String password){
        UserEntity entity = new UserEntity();
        entity.setUserId(userId);
        entity.setPassword(password);
        return userMapper.updateByKey(entity);
    }

    /**
     * 注册用户
     */
    @Transactional
    public int registerUser(UserEntity entity){
        entity.setUserId(CommonUtils.getUUID());
        entity.setNickName(entity.getUserName());
        entity.setUserType(TypeEnum.USER_TYPE.NORMAL.toString());
        entity.setAdminType(TypeEnum.ADMIN_TYPE.NO.toString());
        entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
        return userMapper.insertUser(entity);
    }

    /**
     * 获取用户列表
     */
    public List<UserEntity> findUserList(Page<UserVO> page){
        UserEntity entity = new UserEntity();
        entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
        return userMapper.findByWhere(page,entity);
    }

    /**
     * 修改用户权限
     */
    @Transactional
    public int updateUserType(UserEntity entity){
        return userMapper.updateByKey(entity);
    }

    /**
     * 删除用户
     */
    @Transactional
    public int deleteUser(UserEntity entity){
        entity.setIsDel(TypeEnum.IS_DEL.NO.toString());
        return userMapper.updateByKey(entity);
    }

    /**
     * 修改个人昵称
     */
    @Transactional
    public int updateNickName(UserEntity entity){
        return userMapper.updateByKey(entity);
    }
}