package org.example.model;

import org.example.Utils;

import java.util.ArrayList;

public class Connector extends ModelElement {

    private String name = "";

    private String description = "helps to connect with external systems";

    private String iconPath = "/path/helper/icon.png";

    private String packageName = "samith.connector";

    private ArrayList<Component> components= new ArrayList<>();

    public Connector(String name){
        this.name = name;
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

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void setComponent(Component component) {
        this.components.add(component);
    }

    public String getType() {
        return "connector";
    }

    public void generateInstanceXml(){
        Utils.generateXml(this.getType(), this.getName());
    }
}
