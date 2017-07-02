package com.peterae86.doc.module.psi;

/**
 * Created by test on 2017/7/2.
 */
public class DocTag {
    private String tagType;
    private String text;

    public DocTag(String name, String text) {
        this.tagType = name;
        this.text = text;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
