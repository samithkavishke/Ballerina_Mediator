import ballerina/jballerina.java;

function init() returns error? {
    DataMapper bar = new();
    call(bar);
}

class DataMapper {
    function transform_1(string value) returns string {
        // Please update the logic
        return value;
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
