package com.sc.home.service;


import com.sc.home.logic.HomeLogic;
import com.sc.home.service.vo.HomeVO;
import com.sc.login.service.vo.UserVO;
import com.sc.utils.CommonUtils;
import com.sc.utils.JsonModel;
import com.sc.utils.MapToBean;
import com.sc.utils.entity.HomeEntity;
import com.sc.utils.entity.UserEntity;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/home")
public class HomeService {

    @Autowired
    private HomeLogic homeLogic;

    /*
     * 新增或修改首页信息
     * */
    @RequestMapping(value = "/saveHomeInfo",method = RequestMethod.POST)
    public JsonModel saveHomeInfo(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            HomeVO homeVO = MapToBean.getObject(map,"homeVO",HomeVO.class);
            if(CommonUtils.isAnyNull(homeVO.getHomeTitle(),homeVO.getHomeInfo())){
                model.msgError("保存首页信息失败，有内容为空");
                return model;
            }
            homeLogic.saveHomeInfo(homeVO);
            model.msgSuccess("保存首页信息成功");
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }

    /*
     * 修改首页信息
     * */
    @RequestMapping(value = "/updateHomeInfo",method = RequestMethod.POST)
    public JsonModel updateHomeInfo(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            HomeVO homeVO = MapToBean.getObject(map,"homeVO",HomeVO.class);
            if(CommonUtils.isNull(homeVO.getHomeId())){
                model.msgError("修改首页信息失败，id为空");
                return model;
            }
            homeLogic.updateHomeInfo(MapToBean.toBean(homeVO, HomeEntity.class));
            model.msgSuccess("修改首页信息成功");
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }

    /*
     * 删除首页信息
     * */
    @RequestMapping(value = "/deleteHomeInfo",method = RequestMethod.POST)
    public JsonModel deleteHomeInfo(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            String homeId = MapUtils.getString(map,"homeId");
            if(CommonUtils.isNull(homeId)){
                model.msgError("删除首页信息失败，id为空");
                return model;
            }
            homeLogic.deleteHomeInfo(homeId);
            model.msgSuccess("删除首页信息成功");
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }

    /*
     * 查询首页信息
     * */
    @RequestMapping(value = "/findHomeInfo",method = RequestMethod.POST)
    public JsonModel findHomeInfo(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            HomeVO homeVO = MapToBean.getObject(map,"homeVO",HomeVO.class);
//            if(CommonUtils.isNull(homeVO.getHomeType())){
//                model.msgError("查询首页信息失败，类型为空");
//                return model;
//            }
            List<HomeVO> data = homeLogic.findHomeInfo(MapToBean.toBean(homeVO, HomeEntity.class));
            model.msgSuccess("查询首页信息成功",MapToBean.toList(data, HomeVO.class));
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }

    /*
     * 查询首页信息详情
     * */
    @RequestMapping(value = "/findHomeDetail",method = RequestMethod.POST)
    public JsonModel findHomeDetail(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            String homeId = MapUtils.getString(map,"homeId");
            if(CommonUtils.isNull(homeId)){
                model.msgError("查询首页信息详情失败，homeId");
                return model;
            }
            HomeVO data = homeLogic.findHomeDetail(homeId);
            model.msgSuccess("查询首页信息详情成功",data);
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }


}
