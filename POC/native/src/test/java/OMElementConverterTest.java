import io.ballerina.runtime.api.values.BXml;
import org.apache.axiom.om.*;
import org.OmelementBXmlConversion.BXmlConverter;
import org.OmelementBXmlConversion.OMElementConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class OMElementConverterTest {

    @Test
    public void testOMElementToBXmlWithText() {
        OMFactory factory = OMAbstractFactory.getOMFactory();
        OMNamespace namespace = factory.createOMNamespace("http://example.com", "ns");
        OMElement rootElement = factory.createOMElement("root", namespace);
        OMElement childElement1 = factory.createOMElement("child1", namespace);
        childElement1.setText("Hello");
        OMElement childElement2 = factory.createOMElement("child2", namespace);
        childElement2.setText("World");
        rootElement.addChild(childElement1);
        rootElement.addChild(childElement2);
        BXml bXml = OMElementConverter.toBXml(rootElement);

        Assertions.assertEquals(bXml.children().size(), 2);
        Assertions.assertEquals(bXml.children().getItem(0).children().getItem(0).toString(), "Hello");
        Assertions.assertEquals(bXml.children().getItem(1).children().getItem(0).toString(), "World");
    }

    @Test
    public void testOMElementToBXmlWithComment() {


        OMFactory factory = OMAbstractFactory.getOMFactory();
        OMNamespace namespace = factory.createOMNamespace("http://example.com", "ns");
        OMElement rootElement = factory.createOMElement("root", namespace);
        OMComment comment = factory.createOMComment(rootElement, "This is a comment");
        rootElement.addChild(comment);
        BXml bXml = OMElementConverter.toBXml(rootElement);

        Assertions.assertEquals(bXml.children().size(), 1);
        Assertions.assertEquals(bXml.children().getItem(0).toString(), "<!--This is a comment-->");
    }

    @Test
    public void testOMElementToBXmlWithPI() {
        OMFactory factory = OMAbstractFactory.getOMFactory();
        OMNamespace namespace = factory.createOMNamespace("http://example.com", "ns");
        OMElement rootElement = factory.createOMElement("root", namespace);
        OMProcessingInstruction pi = factory.createOMProcessingInstruction(rootElement, "target", "data");
        rootElement.addChild(pi);
        BXml bXml = OMElementConverter.toBXml(rootElement);

        Assertions.assertEquals(bXml.children().size(), 1);
        Assertions.assertEquals(bXml.children().getItem(0).toString(), "<?target data?>");
    }

    @Test
    public void testOMElementWithCData() {

        OMFactory factory = OMAbstractFactory.getOMFactory();
        OMNamespace namespace = factory.createOMNamespace("http://example.com", "ns");
        OMElement rootElement = factory.createOMElement("root", namespace);
        OMText cdata = factory.createOMText(rootElement, "This is a CDATA", OMNode.CDATA_SECTION_NODE);
        rootElement.addChild(cdata);
        BXml bXml = OMElementConverter.toBXml(rootElement);

        Assertions.assertEquals(bXml.children().size(), 1);
        Assertions.assertEquals(bXml.children().getItem(0).toString(), "This is a CDATA");
    }

    @Test
    public void testOMElementWithMoreLevels(){
        try {
            File xmlFile = new File("src/test/resources/testOMElementWithMoreLevels.xml");
            FileInputStream fileInputStream = new FileInputStream(xmlFile);
            OMXMLParserWrapper builder = OMXMLBuilderFactory.createOMBuilder(fileInputStream);
            OMElement rootElement = builder.getDocumentElement();

            Assertions.assertEquals(OMElementConverter.toBXml(rootElement).toString(), rootElement.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBXmlWithMoreCData(){


        try {
            File xmlFile = new File("src/test/resources/testBxmlWithMoreCData.xml");
            FileInputStream fileInputStream = new FileInputStream(xmlFile);
            OMXMLParserWrapper builder = OMXMLBuilderFactory.createOMBuilder(fileInputStream);
            OMElement rootElement = builder.getDocumentElement();

            File textFile = new File("src/test/resources/testBxmlWithMoreCData.txt");
            FileInputStream textInputStream = new FileInputStream(textFile);
            byte[] data = new byte[(int) textFile.length()];
            textInputStream.read(data);
            textInputStream.close();
            String text = new String(data, "UTF-8");

            Assertions.assertEquals(OMElementConverter.toBXml(rootElement).toString(), text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testBXmlWithAll(){
        try {
            File xmlFile = new File("src/test/resources/testBxmlWithAll.xml");
            FileInputStream fileInputStream = new FileInputStream(xmlFile);
            OMXMLParserWrapper builder = OMXMLBuilderFactory.createOMBuilder(fileInputStream);
            OMElement rootElement = builder.getDocumentElement();

            File textFile = new File("src/test/resources/testBXmlWithAll.txt");
            FileInputStream textInputStream = new FileInputStream(textFile);
            byte[] data = new byte[(int) textFile.length()];
            textInputStream.read(data);
            textInputStream.close();
            String text = new String(data, "UTF-8");
            BXml bXml = OMElementConverter.toBXml(rootElement);

            Assertions.assertEquals(bXml.toString(), text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testOMElementToBXmlWithAttributes(){
        OMFactory factory = OMAbstractFactory.getOMFactory();
        OMNamespace namespace = factory.createOMNamespace("http://ballerina.com/ballerinaio", "sam");
        OMElement rootElement = factory.createOMElement("child1", namespace);
        OMNamespace namespace1 = factory.createOMNamespace("https://hure.com", "mas");
        OMAttribute attribute1 = factory.createOMAttribute("arg1", namespace, "val1");
        OMAttribute attribute2 = factory.createOMAttribute("arg2", namespace1, "val2");
        OMAttribute attribute3 = factory.createOMAttribute("arg3", namespace1, "val3");

        rootElement.addAttribute(attribute1);
        rootElement.addAttribute(attribute2);
        rootElement.addAttribute(attribute3);
        BXml bXml = OMElementConverter.toBXml(rootElement);
        OMElement omElement = BXmlConverter.toOMElement(bXml);

        // Mixed Test Case
        Assertions.assertEquals(bXml.toString(), OMElementConverter.toBXml(omElement).toString());
        Assertions.assertEquals(BXmlConverter.toOMElement(bXml).toString(), rootElement.toString());
    }

    @Test
    public void testDefaultNamespace(){
        try {
            File xmlFile = new File("src/test/resources/default_namespaced_invoice.xml");
            FileInputStream fileInputStream = new FileInputStream(xmlFile);
            OMXMLParserWrapper builder = OMXMLBuilderFactory.createOMBuilder(fileInputStream);
            OMElement rootElement = builder.getDocumentElement();


            BXml bXml = OMElementConverter.toBXml(rootElement);
            Assertions.assertEquals(rootElement.toString(), bXml.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testnamespacedInvoice(){
        try {
            File xmlFile = new File("src/test/resources/namespaced_invoice.xml");
            FileInputStream fileInputStream = new FileInputStream(xmlFile);
            OMXMLParserWrapper builder = OMXMLBuilderFactory.createOMBuilder(fileInputStream);
            OMElement rootElement = builder.getDocumentElement();

            BXml bXml = OMElementConverter.toBXml(rootElement);
            Assertions.assertEquals(rootElement.toString(), bXml.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}