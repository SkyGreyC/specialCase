package com.sc.utils.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class MarkEntity {

    private String markId;

    private String userId;

    private String caseId;

    private String isDel;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public String getMarkId() {
        return markId;
    }

    public void setMarkId(String markId) {
        this.markId = markId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
