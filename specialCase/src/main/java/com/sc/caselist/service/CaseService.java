package com.sc.caselist.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sc.caselist.logic.CaseLogic;
import com.sc.caselist.service.vo.CaseVO;
import com.sc.home.service.vo.HomeVO;
import com.sc.login.service.vo.UserVO;
import com.sc.utils.CommonUtils;
import com.sc.utils.JsonModel;
import com.sc.utils.MapToBean;
import com.sc.utils.entity.CaseEntity;
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
@RequestMapping(value = "/case")
public class CaseService {

    @Autowired
    private CaseLogic caseLogic;

    /*
     * 新增或修改单个病例
     * */
    @RequestMapping(value = "/saveCase",method = RequestMethod.POST)
    public JsonModel saveCase(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            CaseVO caseVO = MapToBean.getObject(map,"caseVO",CaseVO.class);
            String userId = MapUtils.getString(map,"userId");
            if(CommonUtils.isAnyNull(caseVO.getCaseTitle(),caseVO.getDiagnosis())){
                model.msgError("保存单个病例失败，有内容为空");
                return model;
            }
            String data = caseLogic.saveCase(caseVO,userId);
            model.msgSuccess("保存单个病例成功",data);
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }

    /*
     * 修改单个病例
     * */
    @RequestMapping(value = "/updateCase",method = RequestMethod.POST)
    public JsonModel updateCase(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            CaseVO caseVO = MapToBean.getObject(map,"caseVO",CaseVO.class);
            UserVO userVO = MapToBean.getObject(map,"userVO",UserVO.class);
            if(CommonUtils.isAnyNull(caseVO.getCaseTitle(),caseVO.getDiagnosis())){
                model.msgError("修改单个病例失败，有内容为空");
                return model;
            }
            caseLogic.updateCase(caseVO,userVO);
            model.msgSuccess("修改单个病例成功",caseVO.getCaseId());
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }

    /*
     * 删除单个病例
     * */
    @RequestMapping(value = "/deleteCase",method = RequestMethod.POST)
    public JsonModel deleteCase(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            String caseId = MapUtils.getString(map,"caseId");
            if(CommonUtils.isNull(caseId)){
                model.msgError("删除单个病例失败，id为空");
                return model;
            }
            caseLogic.deleteCase(caseId);
            model.msgSuccess("删除单个病例成功");
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }

    /*
     * 获取病例列表
     * */
    @RequestMapping(value = "/findCaseList",method = RequestMethod.POST)
    public JsonModel findCaseList(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            Page<CaseVO> page = MapToBean.getPage(map,"page",CaseVO.class);
            String userId = MapUtils.getString(map,"userId");
            List<CaseVO> data = caseLogic.findCaseList(page,userId);
            model.msgSuccess("获取病例列表成功",data,page);
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }

    /*
     * 获取收藏病例列表
     * */
    @RequestMapping(value = "/findMarkList",method = RequestMethod.POST)
    public JsonModel findMarkList(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            Page<CaseVO> page = MapToBean.getPage(map,"page",CaseVO.class);
            String userId = MapUtils.getString(map,"userId");
            List<CaseVO> data = caseLogic.findMarkList(page,userId);
            model.msgSuccess("获取病例列表成功",data,page);
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }

    /*
     * 获取病例详情
     * */
    @RequestMapping(value = "/findCaseDetail",method = RequestMethod.POST)
    public JsonModel findCaseDetail(@RequestBody Map<String, Object> map){
        try{
            JsonModel model = new JsonModel();
            String caseId = MapUtils.getString(map,"caseId");
            String userId = MapUtils.getString(map,"userId");
            CaseVO data = caseLogic.findCaseDetail(caseId,userId);
            model.msgSuccess("获取病例详情成功",data);
            return model;
        }catch(Exception e){
            return new JsonModel().msgError(e.getMessage());
        }
    }
}
