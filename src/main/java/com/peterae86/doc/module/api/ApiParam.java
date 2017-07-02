package com.peterae86.doc.module.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 2017/6/27.
 */
public class ApiParam {
    private String name;
    private String desc;
    private boolean required;
    private String defaultValue;
    private String type;


    private List<ApiParam> children=new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<ApiParam> getChildren() {
        return children;
    }

    public void setChildren(List<ApiParam> children) {
        this.children = children;
    }
}
