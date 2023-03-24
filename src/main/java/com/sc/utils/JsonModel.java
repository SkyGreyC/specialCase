package com.sc.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

public class JsonModel {

    private static final String SUCCESS="success";
    private static final String ERROR="error";

    //状态码
    private static final String CODE_SUCCESS="000";
    private static final String CODE_NULL_RECORD="001";
    private static final String CODE_NULL_PARAM="002";
    private static final String CODE_SYS_ERROR="003";

    //提示信息
    private static final String MSG_SUCCESS="执行成功";
    private static final String MSG_NULL_RECORD="没有记录";
    private static final String MSG_NULL_PARAM="参数有误";
    private static final String MSG_SYS_ERROR="系统错误";

    private String status;
    private String message;
    private Object data;
    private String code;
    private String errMessage;
    private String detailErrMessage;
    private Page<?> page;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getDetailErrMessage() {
        return detailErrMessage;
    }

    public void setDetailErrMessage(String detailErrMessage) {
        this.detailErrMessage = detailErrMessage;
    }

    public Page<?> getPage() {
        return page;
    }

    public void setPage(Page<?> page) {
        this.page = page;
    }

    /*
     * 设置status替换成字符串
     * */
    public void setStatusValue(Boolean status){
        if(status){
            this.status = SUCCESS;
        }else{
            this.status = ERROR;
        }
    }

    /*
     * 执行成功设置返回
     * */
    public JsonModel msgSuccess(String msg,Object resultData){
        this.setStatusValue(true);
        this.setCode(CODE_SUCCESS);
        if(CommonUtils.isNull(msg)){
            this.setMessage(MSG_SUCCESS);
        }else {
            this.setMessage(msg);
        }
        //设置返回数据
        if(null != resultData){
            this.setData(resultData);
        }
        return this;
    }

    /*
     * 执行成功设置返回
     * */
    public JsonModel msgSuccess(String msg){
        this.setStatusValue(true);
        this.setCode(CODE_SUCCESS);
        if(CommonUtils.isNull(msg)){
            this.setMessage(MSG_SUCCESS);
        }else {
            this.setMessage(msg);
        }
        return this;
    }

    /*
     * 执行成功设置返回(包含分页)
     * */
    public <T> JsonModel msgSuccess(String msg, List<T> resultList, Page page){
        this.setStatusValue(true);
        this.setCode(CODE_SUCCESS);
        if(CommonUtils.isNull(msg)){
            this.setMessage(MSG_SUCCESS);
        }else {
            this.setMessage(msg);
        }
        //设置返回数据
        this.setData(resultList);
        if(null != page){
            this.setPage(page);
        }
        return this;
    }

    /*
     * 空数据
     * */
    public JsonModel msgNullRecord(String msg){
        this.setStatusValue(true);
        this.setCode(CODE_NULL_RECORD);
        if(CommonUtils.isNull(msg)){
            this.setMessage(MSG_NULL_RECORD);
        }else {
            this.setMessage(msg);
        }
        return this;
    }

    /*
     * 空数据List
     * */
    public JsonModel msgNullRecordList(String msg){
        this.setStatusValue(true);
        this.setCode(CODE_NULL_RECORD);
        if(CommonUtils.isNull(msg)){
            this.setMessage(MSG_NULL_RECORD);
        }else {
            this.setMessage(msg);
        }
        this.setData(Collections.emptyList());
        return this;
    }

    /*
     * 系统错误
     * */
    public JsonModel msgError(Exception e){
        this.setStatusValue(false);
        this.setCode(CODE_SYS_ERROR);
        this.setErrMessage(e.getMessage());
        return this;
    }

    /*
     * 系统错误
     * */
    public JsonModel msgError(String errMsg){
        this.setStatusValue(false);
        this.setCode(CODE_SYS_ERROR);
        this.setErrMessage(errMsg);
        return this;
    }
}
