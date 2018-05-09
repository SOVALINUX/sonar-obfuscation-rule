package com.workfusion.sonar.java.obfuscation.checks;

import java.util.function.Predicate;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.Arguments;
import org.sonar.plugins.java.api.tree.AssignmentExpressionTree;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.MethodTree;

/**
 * Created by Sergey Nekhviadovich on 5/8/2018.
 */
@Rule(key = "ParamAnnotationObfuscation")
public class ParamAnnotationObfuscationRule extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;

    @Override
    public void scanFile(JavaFileScannerContext javaFileScannerContext) {
        this.context = javaFileScannerContext;
        scan(context.getTree());
    }

    @Override
    public void visitMethod(MethodTree tree) {
        tree.parameters().stream().forEach((parameter) -> {
            Predicate<AnnotationTree> pathParamAnnotation = a -> a.annotationType().toString().equals("PathParam");
            Predicate<AnnotationTree> requestParamAnnotation = a -> a.annotationType().toString().equals("RequestParam");
            Predicate<AnnotationTree> queryParamAnnotation = a -> a.annotationType().toString().equals("QueryParam");
            Predicate<AnnotationTree> annotationParam = pathParamAnnotation.or(requestParamAnnotation).or(queryParamAnnotation);
            parameter.modifiers().annotations().stream().filter(annotationParam).forEach((annotation) -> {
                final String annotationType = annotation.annotationType().toString();
                Predicate<ExpressionTree> hasExplicitValue = arg -> ((arg instanceof AssignmentExpressionTree) && (
                        ((AssignmentExpressionTree) arg).variable().toString().equals("value")));
                Predicate<ExpressionTree> hasImplicitValue = arg -> arg.symbolType().toString().equals("String");
                Arguments arguments = annotation.arguments();
                if (arguments.isEmpty() || !arguments.stream().anyMatch(hasImplicitValue.or(hasExplicitValue))) {
                    context.reportIssue(this, tree, annotationType + " annotation should have explicitly set value");
                }
            });
        });
        super.visitMethod(tree);
    }
}
