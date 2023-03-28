package com.sc.login.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sc.login.logic.UserLogic;
import com.sc.login.service.vo.UserVO;
import com.sc.utils.CommonUtils;
import com.sc.utils.JsonModel;
import com.sc.utils.MapToBean;
import com.sc.utils.entity.UserEntity;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/login")
public class LoginService {
    @Autowired
    private UserLogic userLogic;

    /*
     * 账号密码登录
     * */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JsonModel login(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            String userName = MapUtils.getString(map,"userName");
            String password = MapUtils.getString(map,"password");
            if(CommonUtils.isNull(userName)){
                model.msgError("登录失败，用户名为空");
                return model;
            }
            UserEntity userEntity = userLogic.getUserInfo(userName);
            if(CommonUtils.isNull(userEntity)){
                model.msgError("登录失败，用户不存在");
                return model;
            }
            if(!password.equals(userEntity.getPassword())){
                model.msgError("登录失败，密码错误");
                return model;
            }
            Map<String, Object> loginInfo = new HashMap<>();
            UserVO userInfo = MapToBean.toBean(userEntity,UserVO.class);
            loginInfo.put("userInfo",userInfo);
            model.msgSuccess("登录成功",loginInfo);
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }

    /*
     * 重置用户密码
     * */
    @RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
    public JsonModel resetPassword(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            String userId = MapUtils.getString(map,"userId");
            String password = MapUtils.getString(map,"password");
            if(CommonUtils.isNull(password)){
                model.msgError("重置失败，密码为空");
                return model;
            }
            userLogic.resetPassword(userId,password);
            model.msgSuccess("重置用户密码成功");
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }

    /*
     * 注册用户
     * */
    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    public JsonModel registerUser(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            UserVO userVO = MapToBean.getObject(map,"userVO",UserVO.class);
            if(CommonUtils.isAnyNull(userVO.getUserName(),userVO.getPassword())){
                model.msgError("注册失败，用户名或密码为空");
                return model;
            }
            userLogic.registerUser(MapToBean.toBean(userVO,UserEntity.class));
            model.msgSuccess("注册用户成功");
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }

    /*
     * 获取用户列表
     * */
    @RequestMapping(value = "/findUserList",method = RequestMethod.POST)
    public JsonModel findUserList(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            Page<UserVO> page = MapToBean.getPage(map,"page",UserVO.class);
            List<UserEntity> data = userLogic.findUserList(page);
            model.msgSuccess("获取用户列表成功",MapToBean.toList(data,UserVO.class),page);
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }
}
