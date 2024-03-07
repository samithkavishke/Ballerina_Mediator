import ballerina/jballerina.java;

function init() returns error? {
    DataMapper bar = new();
    call(bar);
}

xmlns "http://ws.apache.org/ns/synapse" as ns;

class DataMapper {
    function transform_1(string value) returns string {
        // Please update the logic
        xml|error fromString = xml:fromString(value);
        if (fromString is xml) {
            // return  fromString.toString();
            return (fromString/<ns:book>[0]).toString();
        } else {
            return "fromString is error";
        }
    }

    

    function transform_5(xml value,string value1,string value2) returns xml {
        // Please update the logic
        // xml value3 = xml `<ns:book>${value1}</ns:book>`;
        // xml value4 = xml `<ns:book>${value2}</ns:book>`;
        // return value +<ns:book>value1</ns:book> ;
        xml value3 = xml `<ns:book>${value}</ns:book>` ;
        return value3;
    }

    function transform_2(string value1, string value2) returns string {
        // Please update the logic
        return value1 + " " + value2;
    }

    function transform_3(string value1, string value2, string value3) returns string {
        // Please update the logic
        return value1 + " " + value2 + " " + value3;
    }

    function transform_4(xml value, string value1) returns string {
        // Please update the logic
        return value.toString() + " " + value1;
    }
}

// Please do not change the below program

public function main() {
}

function call(DataMapper bar) = @java:Method {
    'class: "bal.mediator.Call"
} external;