package com.sc.login.service;

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
}
