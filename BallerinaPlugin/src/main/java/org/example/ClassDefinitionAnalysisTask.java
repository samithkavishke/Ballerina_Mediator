package org.example;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.ballerina.compiler.api.SemanticModel;
import io.ballerina.compiler.api.symbols.ClassSymbol;
import io.ballerina.compiler.api.symbols.MethodSymbol;
import io.ballerina.compiler.syntax.tree.ClassDefinitionNode;
import io.ballerina.projects.plugins.AnalysisTask;
import io.ballerina.projects.plugins.SyntaxNodeAnalysisContext;
import org.example.model.Component;
import org.example.model.Connector;
import org.example.model.Param;

import java.util.Map;


public class ClassDefinitionAnalysisTask implements AnalysisTask<SyntaxNodeAnalysisContext> {
    @Override
    public void perform(SyntaxNodeAnalysisContext context) {

        ClassDefinitionNode node = (ClassDefinitionNode) context.node();
        Connector connector = new Connector(node.className().text());

        SemanticModel semanticModel = context.compilation().getSemanticModel(context.moduleId());
        Map<String, MethodSymbol> methods = ((ClassSymbol) semanticModel.symbol(node).get()).methods();


        for (Map.Entry<String, MethodSymbol> entry : methods.entrySet()) {
            String key = entry.getKey();
            MethodSymbol value = entry.getValue();
            Component component = new Component(value.getName().get());

            for (int i = 0; i < value.typeDescriptor().params().get().size(); i++) {
                Param param = new Param(value.typeDescriptor().params().get().get(i).getName().get());
                component.setParam(param);
            }
            connector.setComponent(component);
            component.generateInstanceXml();
            component.generateTemplateXml();
        }
        connector.generateInstanceXml();
    }
}
