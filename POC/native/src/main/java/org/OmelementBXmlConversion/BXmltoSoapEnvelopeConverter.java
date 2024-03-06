package org.OmelementBXmlConversion;

import io.ballerina.runtime.api.types.XmlNodeType;
import io.ballerina.runtime.api.values.BMap;
import io.ballerina.runtime.api.values.BString;
import io.ballerina.runtime.api.values.BXml;
import io.ballerina.runtime.api.values.BXmlItem;
import org.apache.axiom.om.*;
import org.apache.axiom.soap.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;


// NOTE: This class is not in use at the moment
public class BXmltoSoapEnvelopeConverter {

    private static final OMFactory factory = OMAbstractFactory.getOMFactory();
    private static SOAPFactory soapFactory ;

    public static SOAPEnvelope toSOAPEnvelope(BXml bXml) throws UnsupportedOperationException{

        BXmlItem xmlItem = (BXmlItem) bXml;

        OMNamespace namespace = factory.createOMNamespace(xmlItem.getQName().getNamespaceURI(), xmlItem.getQName().getPrefix());
        BMap<BString, BString> bMap = xmlItem.getAttributesMap();

        setSoapFactory(namespace.getNamespaceURI());
        SOAPEnvelope soapEnvelope = soapFactory.createSOAPEnvelope(namespace);

        for (Map.Entry<BString, BString> attribute : bMap.entrySet()) {
            if (!attribute.getKey().getValue().startsWith(BXmlItem.XMLNS_NS_URI_PREFIX)) {
                //if this is a namespace
                Pair<String, String> pair = BXmlConverter.extractNamespace(attribute.getKey().getValue());
                OMAttribute omattribute = factory.createOMAttribute(pair.getRight(), namespace, attribute.getValue().getValue());
                soapEnvelope.addAttribute(omattribute);
            }
        }

        //NOTE: SOAP Envelope can only have the following children: Header, Body which are OMElements
        for (int i = 0; i < xmlItem.children().size(); i++) {
            BXml child = xmlItem.children().getItem(i);
            if (child.getNodeType() == XmlNodeType.ELEMENT) {
                BXmlItem childXmlItem = (BXmlItem) child;
                if (childXmlItem.getQName().getLocalPart().equals("Header")) {
                    BXmlConverter.addChildrenElements(soapEnvelope.getOrCreateHeader(), child);

                } else if (childXmlItem.getQName().getLocalPart().equals("Body")) {
                    SOAPBody soapBody;
                    if (soapEnvelope.getBody() == null) {
                        soapBody = soapFactory.createSOAPBody(soapEnvelope);
                    } else {
                        soapBody = soapEnvelope.getBody();
                    }
                    addElementsToBody(soapBody,childXmlItem);
                }
            }
        }
        return soapEnvelope;
    }

    // NOTE: Adding elements to Body has special case because of SOAPFault
    private static void addElementsToBody(SOAPBody soapBody, BXmlItem bXmlItem) {
        for (int i = 0; i < bXmlItem.children().size(); i++) {
            BXml child = bXmlItem.children().getItem(i);
            if (child.getNodeType() == XmlNodeType.ELEMENT) {
                BXmlItem childXmlItem = (BXmlItem) child;
                if (childXmlItem.getQName().getLocalPart().equals("Fault")) {
                    SOAPFault soapFault = soapFactory.createSOAPFault();
                    if(soapFactory.getSOAPVersion().getEnvelopeURI().equals(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI)){
                        addElementsToSOAPFault12(soapFault,childXmlItem);
                    } else {
                        addElementsToSOAPFault11(soapFault,childXmlItem);
                    }
                    soapBody.addFault(soapFault);
                } else {
                    BXmlConverter.addChildrenElements(soapBody, child);
                }
            }
        }
    }

    //NOTE: There are different types of SOAP elements in SOAPFaults1.1 and 1.2
    private static void addElementsToSOAPFault12(SOAPFault soapFault,BXmlItem bXmlItem){
        for (int i = 0; i < bXmlItem.children().size(); i++) {
            BXml child = bXmlItem.children().getItem(i);
            if (child.getNodeType() == XmlNodeType.ELEMENT) {
                BXmlItem childXmlItem = (BXmlItem) child;

                switch (childXmlItem.getQName().getLocalPart()) {
                    case "Code":
                        SOAPFaultCode code = soapFactory.createSOAPFaultCode(soapFault);
                        BXmlConverter.addChildrenElements(code, child);
                        soapFault.setCode(code);
                        break;
                    case "Reason":
                        SOAPFaultReason reason = soapFactory.createSOAPFaultReason(soapFault);
                        BXmlConverter.addChildrenElements(reason, child);
                        soapFault.setReason(reason);
                        break;
                    case "Node":
                        SOAPFaultNode node = soapFactory.createSOAPFaultNode(soapFault);
                        BXmlConverter.addChildrenElements(node, child);
                        soapFault.setNode(node);
                        break;
                    case "Role":
                        SOAPFaultRole role = soapFactory.createSOAPFaultRole(soapFault);
                        BXmlConverter.addChildrenElements(role, child);
                        soapFault.setRole(role);
                        break;
                    case "Detail":
                        SOAPFaultDetail detail = soapFactory.createSOAPFaultDetail(soapFault);
                        BXmlConverter.addChildrenElements(detail, child);
                        soapFault.setDetail(detail);
                        break;
                    default:
                        BXmlConverter.addChildrenElements(soapFault, child);
                }
            }
        }
    }

    private static void addElementsToSOAPFault11(SOAPFault soapFault,BXmlItem bXmlItem){
        for (int i = 0; i < bXmlItem.children().size(); i++) {
            BXml child = bXmlItem.children().getItem(i);
            if (child.getNodeType() == XmlNodeType.ELEMENT) {
                BXmlItem childXmlItem = (BXmlItem) child;

                switch (childXmlItem.getQName().getLocalPart()) {
                    case "faultcode":
                        SOAPFaultCode code = soapFactory.createSOAPFaultCode(soapFault);
                        BXmlConverter.addChildrenElements(code, child);
                        soapFault.setCode(code);
                        break;
                    case "faultstring":
                        SOAPFaultReason reason = soapFactory.createSOAPFaultReason(soapFault);
                        BXmlConverter.addChildrenElements(reason, child);
                        soapFault.setReason(reason);
                        break;
                    case "faultactor":
                        SOAPFaultRole role = soapFactory.createSOAPFaultRole(soapFault);
                        BXmlConverter.addChildrenElements(role, child);
                        soapFault.setRole(role);
                        break;
                    case "detail":
                        SOAPFaultDetail detail = soapFactory.createSOAPFaultDetail(soapFault);
                        BXmlConverter.addChildrenElements(detail, child);
                        soapFault.setDetail(detail);
                        break;
                    default:
                        BXmlConverter.addChildrenElements(soapFault, child);
                }
            }
        }
    }
    private static void setSoapFactory(String uri) throws UnsupportedOperationException {
        if( uri.equals(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI)){
            soapFactory = OMAbstractFactory.getSOAP12Factory();
        } else if (uri.equals(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI)) {
            soapFactory = OMAbstractFactory.getSOAP11Factory();
        } else{
            throw new UnsupportedOperationException("SOAP version could not be identified.");
        }
    }
}


