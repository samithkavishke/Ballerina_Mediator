package org.example;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.example.model.ModelElement;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    public static void generateXml(String inputName, String outputName, ModelElement element){
        try {
            Handlebars handlebar = new Handlebars();
            String inputFileName = String.format("%s.xml", inputName);
            String xmlContent = readXml(inputFileName);
            Template template = handlebar.compileInline(xmlContent);
            String output = template.apply(element);

            String outputFileName = String.format("%s.xml", outputName);
            writeXml(outputFileName, output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void zipFolder(String sourceDirPath, String zipFilePath) throws IOException {
        Path sourceDir = Paths.get(sourceDirPath);
        try (ZipOutputStream outputStream = new ZipOutputStream(Files.newOutputStream(Paths.get(zipFilePath)))) {
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path targetFile = sourceDir.relativize(file);
                    outputStream.putNextEntry(new ZipEntry(targetFile.toString()));

                    Files.copy(file, outputStream);
                    outputStream.closeEntry();
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    public static void deleteDirectory(String dirPath) throws IOException {
        Path directory = Paths.get(dirPath);
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
