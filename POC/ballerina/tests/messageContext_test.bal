import ballerina/test;

@test:Config {}
function envelopeTest() {
    MessageContext mc = new MessageContextImpl();
    xml inputXml = xml `<soapenv:Envelope xmlns:soapenv="http://www.w3.org/2003/05/soap-envelope"><soapenv:Header/><soapenv:Body><m0:getQuote xmlns:m0="http://services.samples"><m0:request><m0:symbol>IBM</m0:symbol></m0:request></m0:getQuote></soapenv:Body></soapenv:Envelope>`;

    error? Error = mc.setEnvelope(inputXml);

    test:assertEquals(Error, null, "Error setting envelope");
    test:assertEquals(mc.getEnvelope(),inputXml, "Envelope mismatch");       
}


@test:Config {}
function entryTest() {
    MessageContext mc = new MessageContextImpl();
    json inputJson = {"key1":1, "key2":"value2", "key3":3.0, "key4":true};
     mc.setContextEntries({"key1":1, "key2":"value2", "key3":3.0, "key4":true, "key5":xml `<key5>value5</key5>`,"key6":inputJson });

    test:assertEquals(mc.getLocalEntry("key1"),1, "Entry mismatch");
    test:assertEquals(mc.getLocalEntry("key2"),"value2", "Entry mismatch");
    test:assertEquals(mc.getLocalEntry("key3"),3.0, "Entry mismatch");
    test:assertEquals(mc.getLocalEntry("key4"),true, "Entry mismatch");
    test:assertEquals(mc.getLocalEntry("key5"),xml `<key5>value5</key5>`, "Entry mismatch");
    test:assertEquals(mc.getLocalEntry("key6"),inputJson, "Entry mismatch");
}

@test:Config {}
function propertyTest() {
    MessageContext mc = new MessageContextImpl();
    json inputJson = {"key1":1, "key2":"value2", "key3":3.0, "key4":true};
    //  mc.setP({"key1":1, "key2":"value2", "key3":3.0, "key4":true, "key5":xml `<key5>value5</key5>`,"key6":inputJson });
    mc.setProperty("key1",1);
    mc.setProperty("key2","value2");
    mc.setProperty("key3",3.0);
    mc.setProperty("key4",true);
    mc.setProperty("key5",xml `<key5>value5</key5>`);
    mc.setProperty("key6",inputJson);


    test:assertEquals(mc.getProperty("key1"),1, "Entry mismatch");
    test:assertEquals(mc.getProperty("key2"),"value2", "Entry mismatch");
    test:assertEquals(mc.getProperty("key3"),3.0, "Entry mismatch");
    test:assertEquals(mc.getProperty("key4"),true, "Entry mismatch");
    test:assertEquals(mc.getProperty("key5"),xml `<key5>value5</key5>`, "Entry mismatch");
    test:assertEquals(mc.getProperty("key6"),inputJson, "Entry mismatch");
}