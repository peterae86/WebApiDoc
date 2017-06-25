package com.peterae86.doc;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.actions.BaseCodeInsightAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassImpl;
import com.intellij.psi.javadoc.PsiDocComment;
import com.peterae86.doc.javadoc.JavaDoc;
import com.peterae86.doc.util.PsiUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by test on 2017/6/25.
 */
public class HelloAction extends BaseCodeInsightAction {
    @NotNull
    @Override
    protected CodeInsightActionHandler getHandler() {
        return new CodeInsightActionHandler() {
            @Override
            public void invoke(@NotNull Project project, @NotNull Editor editor, @NotNull PsiFile psiFile) {
                PsiClassImpl psiClass = PsiUtil.getByType(psiFile.getChildren(), PsiClassImpl.class);

                PsiDocComment docComment = psiClass.getDocComment();
                PsiField[] allFields = psiClass.getAllFields();
                PsiAnnotation[] annotations = psiClass.getModifierList().getAnnotations();
                for (PsiField field : allFields) {
                    String name = field.getName();
                    String desc = PsiUtil.getDoc(field.getDocComment()).getDesc();
                    System.out.println();
                    PsiClass resolve = ((PsiClassType) field.getType()).resolve();
                }
                PsiMethod[] methods = psiClass.getMethods();
                for (PsiMethod method : methods) {
                    PsiParameterList parameterList = method.getParameterList();
                    for (PsiParameter psiParameter : parameterList.getParameters()) {
                        psiParameter.getType();
                    }

                }




                JavaDoc doc = PsiUtil.getDoc(docComment);
            }
        };
    }
}
