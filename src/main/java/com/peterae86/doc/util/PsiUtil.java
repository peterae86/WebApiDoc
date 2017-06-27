package com.peterae86.doc.util;

import com.google.common.collect.Lists;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.javadoc.PsiDocTokenImpl;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocTag;
import com.peterae86.doc.javadoc.JavaDoc;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
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
        if(psiDocComment==null){
            return null;
        }
        PsiDocTokenImpl descriptionElements = getByType(psiDocComment.getDescriptionElements(), PsiDocTokenImpl.class);
        javaDoc.setDesc(descriptionElements.getText());
        PsiDocTag[] tags = psiDocComment.getTags();
        for (PsiDocTag tag : tags) {
            javaDoc.addTag(tag.getName(), tag.getText());
        }
        return javaDoc;
    }
}
