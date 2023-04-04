package com.sc.utils.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class LesionEntity {

    private String lesionId;

    private String caseId;

    private String lesionName;

    private String lesionLocation;

    private String lesionInfo;

    private String lesionType;

    private String creatorId;

    private String creatorName;

    private String isDel;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public String getLesionId() {
        return lesionId;
    }

    public void setLesionId(String lesionId) {
        this.lesionId = lesionId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getLesionName() {
        return lesionName;
    }

    public void setLesionName(String lesionName) {
        this.lesionName = lesionName;
    }

    public String getLesionLocation() {
        return lesionLocation;
    }

    public void setLesionLocation(String lesionLocation) {
        this.lesionLocation = lesionLocation;
    }

    public String getLesionInfo() {
        return lesionInfo;
    }

    public void setLesionInfo(String lesionInfo) {
        this.lesionInfo = lesionInfo;
    }

    public String getLesionType() {
        return lesionType;
    }

    public void setLesionType(String lesionType) {
        this.lesionType = lesionType;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
