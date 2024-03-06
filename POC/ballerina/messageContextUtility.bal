import ballerina/jballerina.java; 

// isolated function initalizeMessageContext() returns handle = @java:Constructor {
//     'class: "org.MessageContext.BMessageContext"} external;

isolated function createMessageContextUtil() returns handle = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "createMessageContextUtil"
} external;

isolated function setEnvelopeUtil(handle messageContext,xml envelope) returns error? = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "setEnvelopeUtil"
} external;

isolated function getEnvelopeUtil(handle messageContext) returns xml = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getEnvelopeUtil"
} external;

isolated function getOMElementUtil() returns handle = @java:Method {
    'class: "org.OmelementBXmlConversion.OMElementConverter",
    name: "toBXml"
} external;

isolated function setContextEntriesUtil(handle messageContext,map<any> entries)  = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "setContextEntriesUtil"
} external;

isolated function getLocalEntryUtil(handle messageContext,string key) returns any = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getLocalEntryUtil"
} external; 

isolated function getMessageIDUtil(handle messageContext) returns string = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getMessageIDUtil"
} external;

isolated function setMessageIDUtil(handle messageContext,string messageID) = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "setMessageIDUtil"
} external;

isolated function setPropertyUtil(handle messageContext,string key,any value) = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "setPropertyUtil"
} external;

isolated function getPropertiesUtil(handle messageContext) returns map<any> = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getPropertiesUtil"
} external;

isolated function getPropertyUtil(handle messageContext,string key) returns any = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getPropertyUtil"
} external;

isolated function getSoapActionUtil(handle messageContext) returns string = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getSOAPActionUtil"
} external;

isolated function setSoapActionUtil(handle messageContext,string soapAction) = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "setSOAPActionUtil"
} external;

isolated function getWSAActionUtil(handle messageContext) returns string = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getWSAActionUtil"
} external;

isolated function setWSAActionUtil(handle messageContext,string wsaAction) = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "setWSAActionUtil"
} external;

isolated function getWSAMessageIDUtil(handle messageContext) returns string = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getWSAMessageIDUtil"
} external;

isolated function setWSAMessageIDUtil(handle messageContext,string wsaMessageID) = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "setWSAMessageIDUtil"
} external;

isolated function toStringUtil(handle messageContext) returns string = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "toStringUtil"
} external;

/////////////////////////////////////////  BOOLEAN VALUES  /////////////////////////////////////////

isolated function setDoingGETUtil(handle messageContext,boolean isDoingGET) = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "setDoingGETUtil"
} external;

isolated function setDoingMTOMUtil(handle messageContext,boolean isDoingMTOM) = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "setDoingMTOMUtil"
} external;

isolated function setDoingPOXUtil(handle messageContext,boolean isDoingPOX) = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "setDoingPOXUtil"
} external;

isolated function setDoingSWAUtil(handle messageContext,boolean isDoingREST) = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "setDoingSWAUtil"
} external;

isolated function setFaultResponseUtil(handle messageContext,boolean isFaultResponse) = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "setFaultResponseUtil"
} external;

isolated function setResponseUtil(handle messageContext,boolean isResponse) = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "setResponseUtil"
} external;

isolated function setServerSideUtil(handle messageContext,boolean isServerSide) = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "setServerSideUtil"
} external;

isolated function setPausedUtil(handle messageContext,boolean isPaused) = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "setPausedUtil"
} external;

isolated function getDoingGETUtil(handle messageContext) returns boolean = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getDoingGETUtil"
} external;

isolated function getDoingMTOMUtil(handle messageContext) returns boolean = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getDoingMTOMUtil"
} external;

isolated function getDoingPOXUtil(handle messageContext) returns boolean = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getDoingPOXUtil"
} external;

isolated function getDoingSWAUtil(handle messageContext) returns boolean = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getDoingSWAUtil"
} external;

isolated function getFaultResponseUtil(handle messageContext) returns boolean = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getFaultResponseUtil"
} external;

isolated function getResponseUtil(handle messageContext) returns boolean = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getResponseUtil"
} external;

isolated function getServerSideUtil(handle messageContext) returns boolean = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getServerSideUtil"
} external;

isolated function getPausedUtil(handle messageContext) returns boolean = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getPausedUtil"
} external;

isolated function getSOAP11Util(handle messageContext) returns boolean = @java:Method {
    'class: "org.MessageContext.BMessageContext",
    name: "getSOAP11Util"
} external;
// ////////////////////////////////////////////////////////////////////////////////////////////////////

