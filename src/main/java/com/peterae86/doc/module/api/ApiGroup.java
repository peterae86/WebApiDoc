package com.peterae86.doc.module.api;

import java.util.List;

/**
 * Created by test on 2017/6/27.
 */
public class ApiGroup {
    private String name;
    private String desc;
    private List<ApiDoc> api;

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

    public List<ApiDoc> getApi() {
        return api;
    }

    public void setApi(List<ApiDoc> api) {
        this.api = api;
    }
}
