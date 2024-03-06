public type MessageContext object {

    isolated function get() returns handle;

    isolated function set(handle messageContextHandle);

    isolated function getEnvelope() returns xml;

    isolated function getLocalEntry(string key) returns any;

    isolated function getMessageID() returns string;

    isolated function getProperty(string key) returns any;

    isolated function getProperties() returns map<any>;
    
    isolated function getPropertyKeySet() returns string[];

    isolated function getSoapAction() returns string;

    isolated function getWSAAction() returns string;

    isolated function getWSAMessageID() returns string;

    isolated function isDoingGET() returns boolean;

    isolated function isDoingMTOM() returns boolean;

    isolated function isDoingPOX() returns boolean;

    isolated function isDoingSWA() returns boolean;

    isolated function isFaultResponse() returns boolean;

    isolated function isPaused() returns boolean;

    isolated function isResponse() returns boolean;

    isolated function isServerSide() returns boolean;

    isolated function isSOAP11() returns boolean;

    isolated function setWSAAction(string action);

    isolated function setWSAMessageID(string id);

    isolated function setDoingGET(boolean b) ;

    isolated function setDoingMTOM(boolean b);

    isolated function setDoingPOX(boolean b);

    isolated function setDoingSWA(boolean b);

    isolated function setEnvelope(xml envelope) returns error?;

    isolated function setFaultResponse(boolean b);

    isolated function setMessageID(string id);

    isolated function setPaused(boolean b);

    isolated function setProperty(string key, any value);

    isolated function setResponse(boolean b);

    isolated function setServerSide(boolean b);

    isolated function setContextEntries(map<any> entries);

    isolated function setSoapAction(string action);

    isolated function toString() returns string;

};

