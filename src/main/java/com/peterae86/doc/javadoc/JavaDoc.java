package com.peterae86.doc.javadoc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by test on 2017/6/25.
 */
public class JavaDoc {
    private String desc;
    private Map<String,String> tags=new HashMap<>();

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
    public void addTag(String tagName,String tagValue) {
        this.tags.put(tagName,tagValue);
    }

}
