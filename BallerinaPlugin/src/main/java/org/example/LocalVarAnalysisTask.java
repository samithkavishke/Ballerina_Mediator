package org.example;

import io.ballerina.compiler.syntax.tree.*;
import io.ballerina.projects.plugins.AnalysisTask;
import io.ballerina.projects.plugins.SyntaxNodeAnalysisContext;
import org.example.RecordTypes;
public class LocalVarAnalysisTask implements AnalysisTask<SyntaxNodeAnalysisContext> {

    private static final String THIS_PKG_ORG = "samith";
    private static final String THIS_PKG_NAME = "mediator";
    @Override
    public void perform(SyntaxNodeAnalysisContext syntaxNodeAnalysisContext) {
        System.out.println("hello from LocalVarAnalysisTask");
        VariableDeclarationNode variableDeclarationNode = (VariableDeclarationNode) syntaxNodeAnalysisContext.node();

        String variableName = variableDeclarationNode.typedBindingPattern().typeDescriptor().toString();
        switch (variableName){
            case (RecordTypes.PARAM) :
                System.out.println("Param");
                break;
            case (RecordTypes.CONNECTOR) :
                System.out.println("Connector");
                break;
            case (RecordTypes.COMPONENT) :
                System.out.println("Component");
                break;
            case (RecordTypes.INTEGRATION) :
                System.out.println("Integration");
                break;
            default:
                break;
        }
    }
}
