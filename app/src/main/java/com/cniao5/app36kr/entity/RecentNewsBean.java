package com.cniao5.app36kr.entity;

import java.util.List;

/**
 * 当前类注释:近期活动数据信息实体类
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class RecentNewsBean {
    private String createTime;
    private String updateTime;
    private String headImageUrl;
    private String status;
    private String link;
    private String categoryId;
    private List<ActivityBriefBean> activityBriefArray;
    private String applyEndTime;
    private String tagId;
    private String applyBeginTime;
    private String proposerRole;
    private String listImageUrl;
    private String city;
    private String activityId;
    private String title;
    private String activityBeginTime;
    private String activityEndTime;
    private List<ActivityExtraItemBean>activityExtraItemArray;
    private String description;

    public RecentNewsBean() {
    }

    public RecentNewsBean(String createTime, String updateTime, String headImageUrl, String status, String link, String categoryId, List<ActivityBriefBean> activityBriefArray, String applyEndTime, String tagId, String applyBeginTime, String proposerRole, String listImageUrl, String city, String activityId, String title, String activityBeginTime, String activityEndTime, List<ActivityExtraItemBean> activityExtraItemArray, String description) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.headImageUrl = headImageUrl;
        this.status = status;
        this.link = link;
        this.categoryId = categoryId;
        this.activityBriefArray = activityBriefArray;
        this.applyEndTime = applyEndTime;
        this.tagId = tagId;
        this.applyBeginTime = applyBeginTime;
        this.proposerRole = proposerRole;
        this.listImageUrl = listImageUrl;
        this.city = city;
        this.activityId = activityId;
        this.title = title;
        this.activityBeginTime = activityBeginTime;
        this.activityEndTime = activityEndTime;
        this.activityExtraItemArray = activityExtraItemArray;
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<ActivityBriefBean> getActivityBriefArray() {
        return activityBriefArray;
    }

    public void setActivityBriefArray(List<ActivityBriefBean> activityBriefArray) {
        this.activityBriefArray = activityBriefArray;
    }

    public String getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(String applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getApplyBeginTime() {
        return applyBeginTime;
    }

    public void setApplyBeginTime(String applyBeginTime) {
        this.applyBeginTime = applyBeginTime;
    }

    public String getProposerRole() {
        return proposerRole;
    }

    public void setProposerRole(String proposerRole) {
        this.proposerRole = proposerRole;
    }

    public String getListImageUrl() {
        return listImageUrl;
    }

    public void setListImageUrl(String listImageUrl) {
        this.listImageUrl = listImageUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivityBeginTime() {
        return activityBeginTime;
    }

    public void setActivityBeginTime(String activityBeginTime) {
        this.activityBeginTime = activityBeginTime;
    }

    public String getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(String activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public List<ActivityExtraItemBean> getActivityExtraItemArray() {
        return activityExtraItemArray;
    }

    public void setActivityExtraItemArray(List<ActivityExtraItemBean> activityExtraItemArray) {
        this.activityExtraItemArray = activityExtraItemArray;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RecentNewsBean{" +
                "createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", headImageUrl='" + headImageUrl + '\'' +
                ", status='" + status + '\'' +
                ", link='" + link + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", activityBriefArray=" + activityBriefArray +
                ", applyEndTime='" + applyEndTime + '\'' +
                ", tagId='" + tagId + '\'' +
                ", applyBeginTime='" + applyBeginTime + '\'' +
                ", proposerRole='" + proposerRole + '\'' +
                ", listImageUrl='" + listImageUrl + '\'' +
                ", city='" + city + '\'' +
                ", activityId='" + activityId + '\'' +
                ", title='" + title + '\'' +
                ", activityBeginTime='" + activityBeginTime + '\'' +
                ", activityEndTime='" + activityEndTime + '\'' +
                ", activityExtraItemArray=" + activityExtraItemArray +
                ", description='" + description + '\'' +
                '}';
    }
}
