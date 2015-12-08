package com.cniao5.app36kr.entity;

import java.util.Arrays;

/**
 * 当前类注释:
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class ActivityExtraItemBean {
    private String extra_serial_id;
    private String question;
    private String option_type;
    private String description;
    private String placeholder;
    private String is_required;
    private String validation;
    private String min_length;
    private String max_length;
    private String[] option_array;

    public ActivityExtraItemBean() {
    }

    public ActivityExtraItemBean(String extra_serial_id, String question, String option_type, String description, String placeholder, String is_required, String validation, String min_length, String max_length, String[] option_array) {
        this.extra_serial_id = extra_serial_id;
        this.question = question;
        this.option_type = option_type;
        this.description = description;
        this.placeholder = placeholder;
        this.is_required = is_required;
        this.validation = validation;
        this.min_length = min_length;
        this.max_length = max_length;
        this.option_array = option_array;
    }

    public String getExtra_serial_id() {
        return extra_serial_id;
    }

    public void setExtra_serial_id(String extra_serial_id) {
        this.extra_serial_id = extra_serial_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption_type() {
        return option_type;
    }

    public void setOption_type(String option_type) {
        this.option_type = option_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getIs_required() {
        return is_required;
    }

    public void setIs_required(String is_required) {
        this.is_required = is_required;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public String getMin_length() {
        return min_length;
    }

    public void setMin_length(String min_length) {
        this.min_length = min_length;
    }

    public String getMax_length() {
        return max_length;
    }

    public void setMax_length(String max_length) {
        this.max_length = max_length;
    }

    public String[] getOption_array() {
        return option_array;
    }

    public void setOption_array(String[] option_array) {
        this.option_array = option_array;
    }

    @Override
    public String toString() {
        return "ActivityExtraItemBean{" +
                "extra_serial_id='" + extra_serial_id + '\'' +
                ", question='" + question + '\'' +
                ", option_type='" + option_type + '\'' +
                ", description='" + description + '\'' +
                ", placeholder='" + placeholder + '\'' +
                ", is_required='" + is_required + '\'' +
                ", validation='" + validation + '\'' +
                ", min_length='" + min_length + '\'' +
                ", max_length='" + max_length + '\'' +
                ", option_array=" + Arrays.toString(option_array) +
                '}';
    }
}
