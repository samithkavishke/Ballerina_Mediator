package org.example;

import io.ballerina.projects.plugins.CompilerPlugin;
import io.ballerina.projects.plugins.CompilerPluginContext;

public class BalMediatorCompilerPlugin extends CompilerPlugin {

    @Override
    public void init(CompilerPluginContext compilerPluginContext) {
        compilerPluginContext.addCodeAnalyzer(new BalMediatorCodeAnalyzer());
//        compilerPluginContext.addCodeGenerator(new BalMediatorCodeGenerator());
    }
}
