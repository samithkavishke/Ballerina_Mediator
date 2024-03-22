package org.example;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.ballerina.compiler.api.SemanticModel;
import io.ballerina.compiler.api.symbols.ClassSymbol;
import io.ballerina.compiler.api.symbols.MethodSymbol;
import io.ballerina.compiler.internal.parser.tree.STSimpleNameReferenceNode;
import io.ballerina.compiler.internal.parser.tree.STSpecificFieldNode;
import io.ballerina.compiler.syntax.tree.*;
import io.ballerina.projects.ModuleId;
import io.ballerina.projects.Project;
import io.ballerina.projects.plugins.AnalysisTask;
import io.ballerina.projects.plugins.CodeAnalysisContext;
import io.ballerina.projects.plugins.SyntaxNodeAnalysisContext;
import org.example.RecordTypes;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LocalVarAnalysisTask implements AnalysisTask<SyntaxNodeAnalysisContext> {

    private static final String THIS_PKG_ORG = "samith";
    private static final String THIS_PKG_NAME = "mediator";

    private static CodeAnalysisContext codeAnalysisContext;
    public LocalVarAnalysisTask(CodeAnalysisContext codeAnalysisContext) {
        this.codeAnalysisContext = codeAnalysisContext;
    }


    @Override
    public void perform(SyntaxNodeAnalysisContext syntaxNodeAnalysisContext) {

        System.out.println("hello from LocalVarAnalysisTask 12");
        VariableDeclarationNode variableDeclarationNode = (VariableDeclarationNode) syntaxNodeAnalysisContext.node();

        String variableName = variableDeclarationNode.typedBindingPattern().typeDescriptor().internalNode().firstToken().text();
        switch (variableName){
            case (RecordTypes.PARAM) :
                try {
                    paramAnalysis(variableDeclarationNode,syntaxNodeAnalysisContext);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case (RecordTypes.CONNECTOR) :
                connectorAnalysis(variableDeclarationNode);
                break;
            case (RecordTypes.COMPONENT) :
                componentAnalysis(variableDeclarationNode);
                break;
            case (RecordTypes.INTEGRATION) :
                integrationAnalysis(variableDeclarationNode);
                break;
            default:
                break;
        }
    }

    private void paramAnalysis(VariableDeclarationNode variableDeclarationNode, SyntaxNodeAnalysisContext context) throws IOException {
        String variableName = variableDeclarationNode.typedBindingPattern().bindingPattern().internalNode().firstToken().text();
        SpecificFieldNode paramNode = (SpecificFieldNode)variableDeclarationNode.initializer().get().children().get(1);
        STSpecificFieldNode node1 = (STSpecificFieldNode) paramNode.internalNode();

        String name = node1.firstToken().text();
        String value = node1.lastToken().text();
//        System.out.println("Param : " + name + " : " + value);

        SpecificFieldNode valueNode = (SpecificFieldNode) variableDeclarationNode.initializer().get().children().get(3);
        STSpecificFieldNode valueInternalNode = (STSpecificFieldNode) valueNode.internalNode();
        String valueName = valueInternalNode.firstToken().text();
        String valueValue = valueInternalNode.lastToken().text();
//
//        System.out.println("Param : "+ name + " : " + value+" " + valueName + " : " + valueValue);
//
//        Handlebars handlebars = new Handlebars();
//        Template template = handlebars.compileInline("Hi {{name}}!");
//        Map<String, String> parameterMap = new HashMap<>();
//        parameterMap.put("name", "Baeldung");
//
//        String templateString = template.apply(parameterMap);

        generateXml();
    }

    private void connectorAnalysis(VariableDeclarationNode variableDeclarationNode){
        System.out.println("Connector");
    }

    private void componentAnalysis(VariableDeclarationNode variableDeclarationNode){
        System.out.println("Component");
    }

    private void integrationAnalysis(VariableDeclarationNode variableDeclarationNode){
        System.out.println("Integration");
    }

    private void generateXml() throws IOException {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("myfile.xml");
        assert inputStream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder xmlContent = new StringBuilder();
        int character;
        while ((character = reader.read()) != -1) {
            xmlContent.append((char) character);
        }
        reader.close();
        System.out.println(xmlContent.toString());

        Handlebars handlebars = new Handlebars();
        Template template = handlebars.compileInline(xmlContent.toString());
//        Template textTemplate = handlebars.compileInline(IOUtils.toString(txtInputStream, "UTF-8"));
        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("name", "Baeldung");

        String templateString = template.apply(parameterMap);
        System.out.println(templateString);

        String filePath = "output.xml";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(templateString);
            System.out.println("XML data has been written to the file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

