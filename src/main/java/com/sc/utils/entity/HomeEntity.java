package com.sc.utils.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class HomeEntity {
    private String homeId;

    private String homeTitle;

    private String homeInfo;

    private String homeType;

    private String isDel;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public String getHomeId() {
        return homeId;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId;
    }

    public String getHomeTitle() {
        return homeTitle;
    }

    public void setHomeTitle(String homeTitle) {
        this.homeTitle = homeTitle;
    }

    public String getHomeInfo() {
        return homeInfo;
    }

    public void setHomeInfo(String homeInfo) {
        this.homeInfo = homeInfo;
    }

    public String getHomeType() {
        return homeType;
    }

    public void setHomeType(String homeType) {
        this.homeType = homeType;
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
