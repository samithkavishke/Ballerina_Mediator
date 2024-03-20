package org.example;

import io.ballerina.compiler.syntax.tree.SyntaxKind;
import io.ballerina.projects.plugins.CodeAnalysisContext;
import io.ballerina.projects.plugins.CodeAnalyzer;

public class BalMediatorCodeAnalyzer extends CodeAnalyzer {

    @Override
    public void init(CodeAnalysisContext codeAnalysisContext) {
//        codeAnalysisContext.addSyntaxNodeAnalysisTask(
//                new TypeDefinitionAnalysisTask(),  SyntaxKind.TYPE_DEFINITION);
        codeAnalysisContext.addSyntaxNodeAnalysisTask(
               new LocalVarAnalysisTask(),  SyntaxKind.LOCAL_VAR_DECL
        );
//        codeAnalysisContext.addSyntaxNodeAnalysisTask(
//
//        );
//        codeAnalysisContext.addSyntaxNodeAnalysisTask(
//                new BalMediatorAnalysisTask(),  SyntaxKind.FUNCTION_DEFINITION);
    }
}
