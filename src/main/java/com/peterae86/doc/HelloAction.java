package com.peterae86.doc;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.actions.BaseCodeInsightAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassImpl;
import com.intellij.psi.impl.source.PsiClassReferenceType;
import com.intellij.psi.javadoc.PsiDocComment;
import com.peterae86.doc.module.api.ApiParam;
import com.peterae86.doc.module.psi.JavaDoc;
import com.peterae86.doc.util.PsiUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
                try {
                    PsiClassImpl psiClass = PsiUtil.getByType(psiFile.getChildren(), PsiClassImpl.class);

                    PsiDocComment docComment = psiClass.getDocComment();
                    PsiField[] allFields = psiClass.getAllFields();
                    PsiAnnotation[] annotations = psiClass.getModifierList().getAnnotations();
                    PsiMethod[] methods = psiClass.getMethods();
                    for (PsiMethod method : methods) {
                        PsiParameterList parameterList = method.getParameterList();
                        for (PsiParameter psiParameter : parameterList.getParameters()) {
                            psiParameter.getType();
                            praseParam(psiParameter.getType());
                        }

                    }
                    JavaDoc doc = PsiUtil.getDoc(docComment);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
    }

    private static Map<String, String> javaTypeMap = new HashMap<>();

    static {
        javaTypeMap.put(CommonClassNames.JAVA_LANG_BOOLEAN, "boolean");
        javaTypeMap.put(CommonClassNames.JAVA_LANG_BYTE, "byte");
        javaTypeMap.put(CommonClassNames.JAVA_LANG_SHORT, "short");
        javaTypeMap.put(CommonClassNames.JAVA_LANG_INTEGER, "int");
        javaTypeMap.put(CommonClassNames.JAVA_LANG_LONG, "long");
        javaTypeMap.put(CommonClassNames.JAVA_LANG_FLOAT, "float");
        javaTypeMap.put(CommonClassNames.JAVA_LANG_DOUBLE, "double");
        javaTypeMap.put(CommonClassNames.JAVA_LANG_CHARACTER, "char");
        javaTypeMap.put(CommonClassNames.JAVA_LANG_STRING, "string");
    }

    private ApiParam praseParam(PsiType psiType) {
        ApiParam apiParam = new ApiParam();
        if (psiType instanceof PsiPrimitiveType) {
            apiParam.setType(psiType.getCanonicalText());
            return apiParam;
        }
        if (psiType instanceof PsiArrayType) {
            psiType = psiType.getDeepComponentType();
            ApiParam childType = praseParam(psiType);
            String type = "array[" + childType.getType() + "]";
            if (Objects.equals(childType.getType(), "object")) {
                apiParam.setChildren(childType.getChildren());
            }
            apiParam.setType(type);
        }
        if (psiType instanceof PsiClassReferenceType) {
            if (((PsiClassReferenceType) psiType).hasParameters()) {
                psiType = ((PsiClassReferenceType) psiType).getParameters()[0];
                ApiParam childType = praseParam(psiType);
                String type = "array[" + childType.getType() + "]";
                if (Objects.equals(childType.getType(), "object")) {
                    apiParam.setChildren(childType.getChildren());
                }
                apiParam.setType(type);
                return apiParam;
            }
            if (psiType.getSuperTypes()[0].getCanonicalText().startsWith("java.lang.Enum")){
                apiParam.setType("string");
                return apiParam;
            }
            if (javaTypeMap.containsKey(psiType.getCanonicalText())) {
                apiParam.setType(javaTypeMap.get(psiType.getCanonicalText()));
                return apiParam;
            }
            PsiField[] fields = ((PsiClassReferenceType) psiType).resolve().getFields();
            apiParam.setType("object");
            for (PsiField field : fields) {
                JavaDoc javaDoc = PsiUtil.getDoc(field.getDocComment());
                ApiParam childType = praseParam(field.getType());
                childType.setName(field.getName());
                childType.setDesc(javaDoc.getDesc());
                apiParam.getChildren().add(childType);
            }
            return apiParam;
        }
        return new ApiParam();
    }

}
