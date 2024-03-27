// import ballerina/io;
import samith/mediator as _;

// public function main() {
//     Param param1 = {name: "param1", description: "This is param1"};
//     Param param2 = {name: "param2", description: "This is param2"};
//     Component component1 = {name: "component1", description: "This is component1", params: [param1, param2]};
//     Component component2 = {name: "component2", description: "This is component2", params: [param1, param2]};
//     Connector connector1 = {name: "connector1", description: "This is connector1", iconPath: "iconPath1", packageName: "packageName1", components: [component1, component2]};
//     Connector connector2 = {name: "connector2", description: "This is connector2", iconPath: "iconPath2", packageName: "packageName2", components: [component1, component2]};
//     Integration integration = {connectors: [connector1, connector2]};
//     // io:println(integration);
//     io:println("This is the plugin user module");
// }


type F record {
	string a;
	string b;
};

class Foo{
	function X(string a, string b){
		
	}

	function Y(string a, string b){

	}
}

// type Integration record {
// 	Connector[] connectors;
// };

// type Connector record {
// 	string name;
// 	string description;
// 	string iconPath;
// 	string packageName;
// 	Component[] components;
// };

// type Component record {
// 	string name;
// 	string description?;
// 	Param[] params;
// };

// type Param record {
// 	string name;
// 	string description;
// };
