package org.example.model;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.example.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.example.Utils.readXml;
import static org.example.Utils.writeXml;

public class Component extends ModelElement {

    private String name = "";
    private String description = "";

    private ArrayList<Param> params = new ArrayList<>();

    public Component(String name){
        this.name = name;
    }
    public Component(String name,  ArrayList<Param> params) {
        this.name = name;
        this.params = params;
    }

    public ArrayList<Param> getParams() {
        return params;
    }

    public void setParam(Param param) {
        this.params.add(param);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return "component";
    }

//    public void generateTemplateXml(){
//        try {
//            Handlebars handlebar = new Handlebars();
//            String inputFileName = String.format("%s_template.xml", this.getType());
//            String xmlContent = readXml(inputFileName);
//            Template template = handlebar.compileInline(xmlContent);
//            String output = template.apply(this);
//
//            String outputFileName = String.format("%s/%s_template.xml", this.getName());
//            writeXml(outputFileName, output);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void generateInstanceXml(){
        File root = new File("connector");
        if (!root.exists()) {
            root.mkdir();
        }
        File file = new File("connector/"+this.getName());
        if (!file.exists()) {
            file.mkdir();
        }
        Utils.generateXml(this.getType(), "connector/"+this.getName()+"/"+this.getName(),this);
    }

    public void generateTemplateXml(){
        File file = new File("connector/"+this.getName());
        if (!file.exists()) {
            file.mkdir();
        }
        Utils.generateXml(this.getType()+"_template", "connector/"+this.getName()+"/"+this.getName() + "_template",this);
    }
}
