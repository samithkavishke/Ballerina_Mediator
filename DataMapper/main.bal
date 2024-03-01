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

    function transform_2(string value1, string value2) returns string {
        // Please update the logic
        return value1 + " " + value2;
    }

    function transform_3(string value1, string value2, string value3) returns string {
        // Please update the logic
        return value1 + " " + value2 + " " + value3;
    }
}

// Please do not change the below program

public function main() {
}

function call(DataMapper bar) = @java:Method {
    'class: "bal.mediator.Call"
} external;