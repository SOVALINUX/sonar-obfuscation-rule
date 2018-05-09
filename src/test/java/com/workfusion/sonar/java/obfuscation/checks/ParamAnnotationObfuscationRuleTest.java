package com.workfusion.sonar.java.obfuscation.checks;

/**
 * Created by Sergey Nekhviadovich on 5/9/2018.
 */
import org.junit.Rule;
import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;
import org.sonar.squidbridge.checks.CheckMessagesVerifierRule;

public class ParamAnnotationObfuscationRuleTest {

    @Rule
    public CheckMessagesVerifierRule checkMessagesVerifier = new CheckMessagesVerifierRule();

    @Test
    public void detected() {

        ParamAnnotationObfuscationRule check = new ParamAnnotationObfuscationRule();

        // Verifies that the check will raise the adequate issues with the expected message.
        // In the test file, lines which should raise an issue have been commented out
        // by using the following syntax: "// Noncompliant {{EXPECTED_MESSAGE}}"
        JavaCheckVerifier.verify("src/test/files/ParamAnnotationsViolationClass.java", check);
    }
}