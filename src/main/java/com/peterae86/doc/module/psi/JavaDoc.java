package com.peterae86.doc.module.psi;

import java.util.List;

/**
 * Created by test on 2017/6/25.
 */
public class JavaDoc {
    private String desc;
    private List<DocTag> tags;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public List<DocTag> getTags() {
        return tags;
    }

    public void setTags(List<DocTag> tags) {
        this.tags = tags;
    }
}
