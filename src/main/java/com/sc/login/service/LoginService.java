package com.sc.login.service;

import com.sc.login.mapper.UserMapper;
import com.sc.login.service.vo.UserVO;
import com.sc.utils.MapToBean;
import com.sc.utils.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/login")
public class LoginService {
    @Autowired
    private UserMapper userMapper;

    /*
     * List 里的对象是Map对象，而Map对象是
     * 由一个String类型的键和Object类型的值组成
     * */
    @RequestMapping(value = "/getUsers",method = RequestMethod.GET)
    public String getUsers(){
        List<UserEntity> list=userMapper.findUser();
        UserEntity a = list.get(0);
        UserVO b = MapToBean.toBean(a,UserVO.class);
        return b.getCreateTime();
    }
}
