package org.example.model;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import java.io.*;

import static org.example.Utils.readXml;
import static org.example.Utils.writeXml;

public abstract class ModelElement {

    String getType(){
        return null;
    };

    String getName(){
        return null;
    };


}
