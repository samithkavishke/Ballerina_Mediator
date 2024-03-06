import ballerina/io;


public function main() {
    MessageContext messageContext = new MessageContextImpl();
//     xml envelope = messageContext.getEnvelope();
//     xml env = messageContext.getEnvelope();
//     handle envelopeHandle = messageContext.get();
//     // handle oMElementUtil = getOMElementUtil();
//     error? hello = messageContext.setEnvelope(xml `<soap:Envelope
//             xmlns:soap="http://www.w3.org/2003/05/soap-envelope/"
// soap:encodingStyle="http://www.w3.org/2003/05/soap-encoding">
//   ...
//   Message information goes here
//   ...
// </soap:Envelope>`);
//     io:println(messageContext.getEnvelope());
//     map<string> contextEntries = {
//         "key1": "abc",
//         "key2": "def"
//     };


    
//     messageContext.setContextEntries(contextEntries);

//     any a = messageContext.getLocalEntry("key1");
//     io:println(a);

//     messageContext.setMessageID("1234");
//     io:println(messageContext.getMessageID());

//     messageContext.setProperty("key1", "value1");
//     messageContext.setProperty("key2", "value2");

//     map<any> properties = messageContext.getProperties();
//     io:println(properties);

//     io:println(messageContext.getProperty("key1"));

//     io:println(messageContext.getPropertyKeySet());

//     messageContext.setSoapAction("http://www.w3.org/2003/05/soap-envelope");
//     io:println(messageContext.getSoapAction());

//     messageContext.setWSAMessageID("1234");
//     io:println(messageContext.getWSAMessageID());

//     messageContext.setWSAAction("http://www.w3.org/2003/05/action");
//     io:println(messageContext.getWSAAction());
//     io:println("###############################################################");
//     io:println(messageContext.toString());



//     messageContext.setDoingGET(true);
//     io:println(messageContext.isDoingGET());


    MessageContext mc = new MessageContextImpl();
    // // xml inputXml = xml `<soapenv:Envelope xmlns:soapenv="http://www.w3.org/2003/05/soap-envelope"><soapenv:Header/><soapenv:Body><m0:getQuote xmlns:m0="http://services.samples"><m0:request><m0:symbol>IBM</m0:symbol></m0:request></m0:getQuote></soapenv:Body></soapenv:Envelope>`;

    // // xml inputXml11 = xml `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Header/><soapenv:Body><soapenv:Fault><faultcode/><faultstring>Missing required parameter</faultstring></soapenv:Fault></soapenv:Body></soapenv:Envelope>`;
    // xml inputXml12 = xml `<soapenv:Envelope xmlns:soapenv="http://www.w3.org/2003/05/soap-envelope"><soapenv:Header/><soapenv:Body><soapenv:Fault><soapenv:Code/><soapenv:Reason>Missing required parameter</soapenv:Reason></soapenv:Fault></soapenv:Body></soapenv:Envelope>`;

    // error? Error = mc.setEnvelope(inputXml12);
    // if(Error != null) {
    //     io:println("Error occurred while setting the envelope");
    // }

    // map<any> properties = {"key1": 1, "key2": "value2", "key3":2.3};

    // mc.setContextEntries(properties);
    // // io:print(1 is any);

    // io:println(mc.getLocalEntry("key3"));
    // // io:println(properties);
    // io:print(mc.isSOAP11());

    // map<int> map1 = {"a": 2, "b": 4};

    mc.setProperty("key1", 3);
    // CreateRecordFromMap(map1);
    any a = mc.getProperty("key1");
    io:println(a);

    // MessageContext mc = new MessageContextImpl();
    // json inputJson = {"key1":1, "key2":"value2", "key3":3.0, "key4":true};
    // //  mc.setP({"key1":1, "key2":"value2", "key3":3.0, "key4":true, "key5":xml `<key5>value5</key5>`,"key6":inputJson });
    // mc.setProperty("key1",3);
    // io:println(mc.getLocalEntry("key1"));
}
