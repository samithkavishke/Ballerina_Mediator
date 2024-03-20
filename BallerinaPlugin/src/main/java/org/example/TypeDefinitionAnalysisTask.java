package org.example;

import io.ballerina.compiler.syntax.tree.*;
import io.ballerina.projects.plugins.AnalysisTask;
import io.ballerina.projects.plugins.SyntaxNodeAnalysisContext;

public class TypeDefinitionAnalysisTask implements AnalysisTask<SyntaxNodeAnalysisContext> {
    private static final String THIS_PKG_ORG = "samith";
    private static final String THIS_PKG_NAME = "mediator";

    @Override
    public void perform(SyntaxNodeAnalysisContext context) {
//        Optional<ImportOrgNameNode> importOrgNameOpt = ((ImportDeclarationNode)context.node()).orgName();
//        String importModuleName = ((ImportDeclarationNode)context.node()).moduleName().get(0).text();
//
//        String packageOrgName = context.currentPackage().packageOrg().value();
//        String packageName = context.currentPackage().packageName().value();
//
//        String importOrgName = importOrgNameOpt.map(node -> node.orgName().text()).orElse(packageOrgName);
//
//        // Do not warn if package org starts with "ballerina"
//        boolean isBallerinaImport = importOrgName.startsWith("ballerina");

        // ....

        TypeDefinitionNode typeDefinitionNode = (TypeDefinitionNode)context.node();
        String typeName = typeDefinitionNode.typeName().toString();
//        MemberTypeDescriptorNode memberTypeDescriptorNode = (MemberTypeDescriptorNode)context.node();

    }
}