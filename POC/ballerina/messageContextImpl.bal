class MessageContextImpl {
    *MessageContext;

    private xml envelope = xml ``;
    private handle messageContextHandle;

    isolated function init() {
        self.messageContextHandle = createMessageContextUtil();
    }
    
    isolated function get() returns handle {
        return self.messageContextHandle;
    }

    isolated function set(handle messageContextHandle) {
        self.messageContextHandle = messageContextHandle;
    }

    isolated function getEnvelope() returns xml {        
        return getEnvelopeUtil(self.messageContextHandle);
    }

    isolated function setEnvelope(xml envelope) returns error? {
        error? Error = setEnvelopeUtil(self.messageContextHandle, envelope);
        return Error;
    }

    isolated function setContextEntries(map<any> entries)  {
        setContextEntriesUtil(self.messageContextHandle, entries);
    }
    // bxml 
    isolated function getLocalEntry(string key) returns any {
        return getLocalEntryUtil(self.messageContextHandle, key);
    }

    isolated function setMessageID(string id) {
        return setMessageIDUtil(self.messageContextHandle, id);
    }

    isolated function getMessageID() returns string {
        return getMessageIDUtil(self.messageContextHandle);
    }

    isolated function setProperty(string key, any value) {
        setPropertyUtil(self.messageContextHandle, key, value);
    }

    isolated function getProperties() returns map<any> {
        return getPropertiesUtil(self.messageContextHandle);
    }

    isolated function getProperty(string key) returns any {
        return getPropertyUtil(self.messageContextHandle, key);
    }

    isolated function getPropertyKeySet() returns string[] {
        return self.getProperties().keys();
    }

    isolated function setSoapAction(string action) {
        return setSoapActionUtil(self.messageContextHandle,action);
    }
    isolated function getSoapAction() returns string {
        return getSoapActionUtil(self.messageContextHandle);
    }

    isolated function setWSAAction(string action) {
        return setWSAActionUtil(self.messageContextHandle, action);
    }

    isolated function getWSAAction() returns string {
        return getWSAActionUtil(self.messageContextHandle);
    }

    isolated function setWSAMessageID(string id) {
        return setWSAMessageIDUtil(self.messageContextHandle, id);
    }
    isolated function getWSAMessageID() returns string {
        return getWSAMessageIDUtil(self.messageContextHandle);
    }

    isolated function isDoingGET() returns boolean {
        return getDoingGETUtil(self.messageContextHandle);
    }

    isolated function isDoingMTOM() returns boolean {
        return getDoingMTOMUtil(self.messageContextHandle);
    }

    isolated function isDoingPOX() returns boolean {
        return getDoingPOXUtil(self.messageContextHandle);
    }

    isolated function isDoingSWA() returns boolean {
        return getDoingSWAUtil(self.messageContextHandle);
    }

    isolated function isFaultResponse() returns boolean {
        return getFaultResponseUtil(self.messageContextHandle);
    }

    isolated function isPaused() returns boolean {
        return getPausedUtil(self.messageContextHandle);
    }

    isolated function isResponse() returns boolean {
        return getResponseUtil(self.messageContextHandle);
    }

    isolated function isSOAP11() returns boolean {
        return getSOAP11Util(self.messageContextHandle);
    }

    isolated function isServerSide() returns boolean {
        return getServerSideUtil(self.messageContextHandle);
    }

    isolated function setDoingGET(boolean b) {
        setDoingGETUtil(self.messageContextHandle, b);
    }

    isolated function setDoingMTOM(boolean b)  {
        setDoingMTOMUtil(self.messageContextHandle, b);
    }

    isolated function setDoingPOX(boolean b)  {
        setDoingPOXUtil(self.messageContextHandle, b);
    }

    isolated function setDoingSWA(boolean b)  {
        setDoingSWAUtil(self.messageContextHandle, b);
    }

    isolated function setFaultResponse(boolean b)  {
        setFaultResponseUtil(self.messageContextHandle, b);
    }

    isolated function setPaused(boolean b)  {
        setPausedUtil(self.messageContextHandle, b);
    }

    isolated function setResponse(boolean b)  {
        setResponseUtil(self.messageContextHandle, b);
    }

    isolated function setServerSide(boolean b)  {
        setServerSideUtil(self.messageContextHandle, b);
    }

    isolated function toString() returns string {
        return toStringUtil(self.messageContextHandle);
    }

}