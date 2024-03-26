package org.example;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Utils {

    public static String readXml(String fileName) throws IOException {
        InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(fileName);
        assert inputStream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder xmlContent = new StringBuilder();
        int character;
        while ((character = reader.read()) != -1) {
            xmlContent.append((char) character);
        }
        reader.close();
        return xmlContent.toString();
    }

    public static void writeXml(String fileName, String content) throws IOException {
        FileWriter myWriter = new FileWriter(fileName);
        myWriter.write(content);
        myWriter.close();
    }

    public static void generateXml(String inputName, String outputName){
        try {
            Handlebars handlebar = new Handlebars();
            String inputFileName = String.format("%s.xml", inputName);
            String xmlContent = readXml(inputFileName);
            Template template = handlebar.compileInline(xmlContent);
            String output = template.apply(inputName);

            String outputFileName = String.format("%s.xml", outputName);
            writeXml(outputFileName, output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
