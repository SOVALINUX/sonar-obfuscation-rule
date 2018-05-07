package com.workfusion.sonar.java.obfuscation;

import org.sonar.api.Plugin;

/**
 * Created by Sergey Nekhviadovich on 5/7/2018.
 */
public class ObfuscationRulePlugin implements Plugin {

    @Override
    public void define(Context context) {
        // server extensions -> objects are instantiated during server startup
        context.addExtension(ObfuscationRulesDefinition.class);

        // batch extensions -> objects are instantiated during code analysis
        context.addExtension(ObfuscationRulesCheckRegistry.class);
    }
}
