package com.peterae86.doc.util;

import com.google.common.collect.Lists;
import com.intellij.psi.CommonClassNames;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiType;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocTag;
import com.intellij.psi.util.PsiTypesUtil;
import com.peterae86.doc.module.psi.DocTag;
import com.peterae86.doc.module.psi.JavaDoc;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by test on 2017/6/25.
 */
public class PsiUtil {

    public static <T> List<T> filterByType(PsiElement[] elements, final Class<T> clazz) {
        return Lists.newArrayList(elements).stream().filter(
                psiElement -> (psiElement.getClass().isAssignableFrom(clazz))
        ).map(new Function<PsiElement, T>() {
            @Nullable
            @Override
            public T apply(@Nullable PsiElement psiElement) {
                return (T) psiElement;
            }
        }).collect(Collectors.toList());
    }

    public static <T> T getByType(PsiElement[] elements, final Class<T> clazz) {
        for (PsiElement element : elements) {
            if (element.getClass().isAssignableFrom(clazz)) {
                return (T) element;
            }
        }
        return null;
    }

    public static JavaDoc getDoc(PsiDocComment psiDocComment) {
        JavaDoc javaDoc = new JavaDoc();
        if (psiDocComment == null) {
            return javaDoc;
        }
        String content = joinElementsText(psiDocComment.getDescriptionElements());
        javaDoc.setDesc(content);
        PsiDocTag[] tags = psiDocComment.getTags();
        List<DocTag> docTags = new ArrayList<>();
        for (PsiDocTag tag : tags) {
            docTags.add(new DocTag(tag.getName(), joinElementsText(tag.getDataElements())));
        }
        javaDoc.setTags(docTags);
        return javaDoc;
    }

    private static String joinElementsText(PsiElement[] elements) {
        StringBuilder res = new StringBuilder();
        for (PsiElement descriptionElement : elements) {
            res.append(descriptionElement.getText());
        }
        return StringUtils.strip(res.toString());
    }

}
