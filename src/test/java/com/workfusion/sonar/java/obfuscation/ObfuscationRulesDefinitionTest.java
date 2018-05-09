package com.workfusion.sonar.java.obfuscation;

import org.junit.Test;
import org.sonar.api.rules.RuleType;
import org.sonar.api.server.debt.DebtRemediationFunction.Type;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinition.Param;
import org.sonar.api.server.rule.RulesDefinition.Repository;
import org.sonar.api.server.rule.RulesDefinition.Rule;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by Sergey Nekhviadovich on 5/9/2018.
 */
public class ObfuscationRulesDefinitionTest {

    @Test
    public void test() {
        ObfuscationRulesDefinition rulesDefinition = new ObfuscationRulesDefinition();
        RulesDefinition.Context context = new RulesDefinition.Context();
        rulesDefinition.define(context);
        RulesDefinition.Repository repository = context.repository(ObfuscationRulesDefinition.REPOSITORY_KEY);

        assertThat(repository.name()).isEqualTo("WorkFusion Custom Repository");
        assertThat(repository.language()).isEqualTo("java");
        assertThat(repository.rules()).hasSize(ObfuscationRulesList.getChecks().size());

        assertRuleProperties(repository);
        assertAllRuleParametersHaveDescription(repository);
    }

    private void assertRuleProperties(Repository repository) {
        Rule rule = repository.rule("ParamAnnotationObfuscation");
        assertThat(rule).isNotNull();
        assertThat(rule.name()).isEqualTo("Parameter annotations are not ready for obfuscation");
        assertThat(rule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(rule.type()).isEqualTo(RuleType.BUG);
    }

    private void assertAllRuleParametersHaveDescription(Repository repository) {
        for (Rule rule : repository.rules()) {
            for (Param param : rule.params()) {
                assertThat(param.description()).as("description for " + param.key()).isNotEmpty();
            }
        }
    }

}