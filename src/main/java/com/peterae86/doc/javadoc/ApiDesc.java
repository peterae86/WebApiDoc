package com.peterae86.doc.javadoc;

import java.util.List;

/**
 * Created by test on 2017/6/27.
 */
public class ApiDesc {
    private String name;
    private String desc;
    private String url;
    private String method;
    private String type;
    private String contentType;

    private List<ApiParam> params;

    private ApiParam response;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public List<ApiParam> getParams() {
        return params;
    }

    public void setParams(List<ApiParam> params) {
        this.params = params;
    }

    public ApiParam getResponse() {
        return response;
    }

    public void setResponse(ApiParam response) {
        this.response = response;
    }
}
