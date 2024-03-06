package org.MessageContext;

import io.ballerina.runtime.api.utils.StringUtils;
import io.ballerina.runtime.api.values.BMap;
import io.ballerina.runtime.api.values.BString;
import io.ballerina.runtime.api.values.BValue;
import io.ballerina.runtime.api.values.BXml;
import io.ballerina.runtime.internal.values.MapValueImpl;
import io.ballerina.runtime.internal.values.XmlItem;
import org.OmelementBXmlConversion.BXmltoSoapEnvelopeConverter;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.engine.AxisConfiguration;
import org.apache.synapse.config.SynapseConfiguration;
import org.apache.synapse.core.SynapseEnvironment;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.core.axis2.Axis2SynapseEnvironment;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

import static org.OmelementBXmlConversion.OMElementConverter.toBXml;



public class BMessageContext  {
    public static Axis2MessageContext createMessageContextUtil() {
        MessageContext axisMsgCtx = new org.apache.axis2.context.MessageContext();
        SynapseConfiguration synCfg = new SynapseConfiguration();
        SynapseEnvironment synEnv = new Axis2SynapseEnvironment(new ConfigurationContext(new AxisConfiguration()), synCfg);
        return new Axis2MessageContext(axisMsgCtx, synCfg, synEnv);
    }

    public static void setEnvelopeUtil(Axis2MessageContext messageContext,BXml bXml) throws AxisFault, UnsupportedOperationException{

        SOAPEnvelope soapEnvelope = BXmltoSoapEnvelopeConverter.toSOAPEnvelope(bXml);
        messageContext.setEnvelope(soapEnvelope);
    }

    public static BXml getEnvelopeUtil(Axis2MessageContext messageContext) {
        if (messageContext.getEnvelope() == null) {
            return new XmlItem(new QName(""));
        }
        return toBXml(messageContext.getEnvelope());
    }

    public static <T> T getLocalEntryUtil(Axis2MessageContext messageContext, BString key) {
        switch (messageContext.getContextEntries().get(key.getValue()).getClass().getSimpleName()) {
            case "Boolean":
                return (T)(Boolean) messageContext.getContextEntries().get((key.getValue()));
            case "Integer":
                return (T)(Long) messageContext.getContextEntries().get((key.getValue()));
            case "Long":
                return (T)(Long) messageContext.getContextEntries().get(key.getValue());
            case "Double":
                return (T)(Double) messageContext.getContextEntries().get((key.getValue()));
            case "Float":
                return (T)(Double) messageContext.getContextEntries().get((key.getValue()));
        }
        return (T)(BValue) messageContext.getContextEntries().get((key.getValue()));
    }

    public static void setContextEntriesUtil(Axis2MessageContext messageContext, BMap<BString, BValue> entries) {
        // initialize a map
        Map<String, Object> entryMap = new HashMap<>();
        for (BString key : entries.getKeys()) {
            entryMap.put(key.getValue(), entries.get(key));
        }
        messageContext.setContextEntries(entryMap);
    }

    public static BString getMessageIDUtil(Axis2MessageContext messageContext) {
        return StringUtils.fromString(messageContext.getMessageID());
    }

    public static void setMessageIDUtil(Axis2MessageContext messageContext, BString messageID) {
        messageContext.setMessageID(messageID.getValue());
    }

    public static <T> void setPropertyUtil(Axis2MessageContext messageContext, BString key, T value) {
        messageContext.setProperty(key.getValue(), value);
    }

    public static <T> T getPropertyUtil(Axis2MessageContext messageContext, BString key) {
        switch (messageContext.getProperties().get(key.getValue()).getClass().getSimpleName()) {
            case "Boolean":
                return (T)(Boolean) messageContext.getProperties().get((key.getValue()));
            case "Integer":
                return (T)(Long) messageContext.getProperties().get((key.getValue()));
            case "Long":
                return (T)(Long) messageContext.getProperties().get(key.getValue());
            case "Double":
                return (T)(Double) messageContext.getProperties().get((key.getValue()));
            case "Float":
                return (T)(Double) messageContext.getProperties().get((key.getValue()));
        }
        return (T)(BValue) messageContext.getProperties().get((key.getValue()));
    }


    public static BMap<BString, BValue> getPropertiesUtil(Axis2MessageContext messageContext) {
        Map<String, Object> propertyMap = messageContext.getProperties();
        BMap<BString, BValue> properties = new MapValueImpl<>();
        for (Map.Entry<String, Object> entry : propertyMap.entrySet()) {
            properties.put(StringUtils.fromString(entry.getKey()), (BValue) entry.getValue());
        }
        return properties;
    }

    public static BString getSOAPActionUtil(Axis2MessageContext messageContext) {
        return StringUtils.fromString(messageContext.getSoapAction());
    }

    public static void setSOAPActionUtil(Axis2MessageContext messageContext, BString soapAction) {
        messageContext.setSoapAction(soapAction.getValue());
    }

    public static BString getWSAActionUtil(Axis2MessageContext messageContext) {
        return StringUtils.fromString(messageContext.getWSAAction());
    }
    public static void setWSAActionUtil(Axis2MessageContext messageContext, BString wsaAction) {
        messageContext.setWSAAction(wsaAction.getValue());
    }

    public static BString getWSAMessageIDUtil(Axis2MessageContext messageContext) {
        return StringUtils.fromString(messageContext.getWSAMessageID());
    }

    public static void setWSAMessageIDUtil(Axis2MessageContext messageContext, BString wsaMessageID) {
        messageContext.setWSAMessageID(wsaMessageID.getValue());
    }

    public static BString toStringUtil(Axis2MessageContext messageContext) {
        return StringUtils.fromString(messageContext.toString());
    }

    /////////////////////////////////////////////////////////////////////////

    public static void setDoingGETUtil(Axis2MessageContext messageContext, Boolean doingGET) {
        messageContext.setDoingGET(doingGET);
    }

    public static Boolean getDoingGETUtil(Axis2MessageContext messageContext) {
        return messageContext.isDoingGET();
    }

    public static void setDoingMTOMUtil(Axis2MessageContext messageContext, Boolean doingMTOM) {
        messageContext.setDoingMTOM(doingMTOM);
    }

    public static Boolean getDoingMTOMUtil(Axis2MessageContext messageContext) {
        return messageContext.isDoingMTOM();
    }

    public static void setDoingPOXUtil(Axis2MessageContext messageContext, Boolean doingPOX) {
        messageContext.setDoingPOX(doingPOX);
    }

    public static Boolean getDoingPOXUtil(Axis2MessageContext messageContext) {
        return messageContext.isDoingPOX();
    }

    public static void setDoingSWAUtil(Axis2MessageContext messageContext, Boolean doingSWA) {
        messageContext.setDoingSWA(doingSWA);
    }

    public static Boolean getDoingSWAUtil(Axis2MessageContext messageContext) {
        return messageContext.isDoingSWA();
    }

    public static void setServerSideUtil(Axis2MessageContext messageContext, Boolean serverSide) {
        messageContext.setServerSide(serverSide);
    }

    public static Boolean getServerSideUtil(Axis2MessageContext messageContext) {
        return messageContext.isServerSide();
    }

    public static void setFaultResponseUtil(Axis2MessageContext messageContext, Boolean faultResponse) {
        messageContext.setFaultResponse(faultResponse);
    }

    public static Boolean getFaultResponseUtil(Axis2MessageContext messageContext) {
        return messageContext.isFaultResponse();
    }

    public static void setResponseUtil(Axis2MessageContext messageContext, Boolean response) {
        messageContext.setResponse(response);
    }

    public static Boolean getResponseUtil(Axis2MessageContext messageContext) {
        return messageContext.isResponse();
    }

    public static void setPausedUtil(Axis2MessageContext messageContext, Boolean paused) {
        messageContext.setPaused(paused);
    }

    public static Boolean getPausedUtil(Axis2MessageContext messageContext) {
        return messageContext.isPaused();
    }

    public static Boolean getSOAP11Util(Axis2MessageContext messageContext) {
        return messageContext.isSOAP11();
    }
}
