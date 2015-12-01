package com.cniao5.app36kr.entity;

/**
 * 当前类注释:左侧功能菜单 没想item实体类
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class LeftItemMenu {
    private int leftIcon;
    private String title;
    private int arrowIcon;
    public LeftItemMenu() {
    }
    public LeftItemMenu(int leftIcon, int arrowIcon, String title) {
        this.leftIcon = leftIcon;
        this.arrowIcon = arrowIcon;
        this.title = title;
    }
    public int getLeftIcon() {
        return leftIcon;
    }
    public void setLeftIcon(int leftIcon) {
        this.leftIcon = leftIcon;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getArrowIcon() {
        return arrowIcon;
    }
    public void setArrowIcon(int arrowIcon) {
        this.arrowIcon = arrowIcon;
    }
    @Override
    public String toString() {
        return "LeftItemMenu{" +
                "leftIcon=" + leftIcon +
                ", title='" + title + '\'' +
                ", arrowIcon=" + arrowIcon +
                '}';
    }
}
