package com.sc.user.service;

import com.sc.home.service.vo.HomeVO;
import com.sc.user.logic.MarkLogic;
import com.sc.utils.CommonUtils;
import com.sc.utils.JsonModel;
import com.sc.utils.MapToBean;
import com.sc.utils.entity.HomeEntity;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/mark")
public class MarkService {

    @Autowired
    private MarkLogic markLogic;

    /*
     * 保存收藏信息
     * */
    @RequestMapping(value = "/saveMarkInfo",method = RequestMethod.POST)
    public JsonModel saveMarkInfo(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            String caseId = MapUtils.getString(map,"caseId");
            String userId = MapUtils.getString(map,"userId");
            if(CommonUtils.isAnyNull(caseId,userId)){
                model.msgError("保存收藏信息失败，有内容为空");
                return model;
            }
            markLogic.saveMarkInfo(caseId,userId);
            model.msgSuccess("保存收藏信息成功");
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }
}
