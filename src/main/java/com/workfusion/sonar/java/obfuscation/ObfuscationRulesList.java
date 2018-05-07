package com.workfusion.sonar.java.obfuscation;

import com.google.common.collect.ImmutableList;
import java.util.List;

import com.workfusion.sonar.java.obfuscation.checks.PathParamObfuscationRule;
import com.workfusion.sonar.java.obfuscation.checks.RequestParamObfuscationRule;
import org.sonar.plugins.java.api.JavaCheck;

public final class ObfuscationRulesList {

    private ObfuscationRulesList() {
    }

    public static List<Class> getChecks() {
        return ImmutableList.<Class>builder().addAll(getJavaChecks()).addAll(getJavaTestChecks()).build();
    }

    public static List<Class<? extends JavaCheck>> getJavaChecks() {
        return ImmutableList.<Class<? extends JavaCheck>>builder()
                .add(RequestParamObfuscationRule.class)
                .add(PathParamObfuscationRule.class)
                .build();
    }

    public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
        return ImmutableList.<Class<? extends JavaCheck>>builder()
                .build();
    }
}