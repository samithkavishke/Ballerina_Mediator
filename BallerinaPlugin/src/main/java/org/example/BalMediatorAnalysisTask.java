package org.example;

import io.ballerina.compiler.syntax.tree.FunctionDefinitionNode;
import io.ballerina.compiler.syntax.tree.ImportDeclarationNode;
import io.ballerina.compiler.syntax.tree.ImportOrgNameNode;
import io.ballerina.compiler.syntax.tree.MemberTypeDescriptorNode;
import io.ballerina.projects.plugins.AnalysisTask;
import io.ballerina.projects.plugins.SyntaxNodeAnalysisContext;

import java.util.Optional;

public class BalMediatorAnalysisTask implements AnalysisTask<SyntaxNodeAnalysisContext> {
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


        MemberTypeDescriptorNode memberTypeDescriptorNode = (MemberTypeDescriptorNode)context.node();

    }
}