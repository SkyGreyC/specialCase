package com.sc.home.service.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sc.caselist.service.vo.ImageVO;

import java.util.Date;
import java.util.List;

public class HomeVO {

    private String homeId;

    private String homeTitle;

    private String homeInfo;

    private String homeType;

    private String isDel;

    private String createTime;

    private List<ImageVO> imageVOs;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<ImageVO> getImageVOs() {
        return imageVOs;
    }

    public void setImageVOs(List<ImageVO> imageVOs) {
        this.imageVOs = imageVOs;
    }
}
