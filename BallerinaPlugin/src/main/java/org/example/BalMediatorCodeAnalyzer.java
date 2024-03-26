package org.example;

import io.ballerina.compiler.syntax.tree.SyntaxKind;
import io.ballerina.projects.plugins.CodeAnalysisContext;
import io.ballerina.projects.plugins.CodeAnalyzer;

public class BalMediatorCodeAnalyzer extends CodeAnalyzer {

    @Override
    public void init(CodeAnalysisContext codeAnalysisContext) {


        codeAnalysisContext.addSyntaxNodeAnalysisTask(
            new ClassDefinitionAnalysisTask(), SyntaxKind.CLASS_DEFINITION);
    }
}
