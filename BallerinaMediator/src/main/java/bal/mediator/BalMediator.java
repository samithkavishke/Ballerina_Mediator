//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package bal.mediator;

import org.OmelementBXmlConversion.OMElementConverter;
//import org.OmelementBXmlConversion.OMElementConverter;
import org.apache.axiom.om.*;
import io.ballerina.runtime.api.values.BXml;
import io.ballerina.runtime.internal.BalRuntime;
import io.ballerina.runtime.internal.scheduling.Scheduler;
import java.util.HashMap;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class BalMediator extends AbstractMediator {
	static Scheduler scheduler = new Scheduler(false);
	private String firstArgument = "";
	private String secondArgument = "";
	private String thirdArgument = "";
	private String fourthArgument = "";
	private String fifthArgument = "";
	private String sixthArgument = "";
	private String functionName = "transform_";

	public BalMediator() {
	}

	public boolean mediate(final MessageContext context) {
		System.out.println("Hello from Ballerina Mediator");
		HashMap<String, Object> properties = new HashMap<String, Object>() {
			{
				this.put("payload", BalMediator.this.getPayload(context));
				this.put("firstArgument", BalMediator.this.getFirstArgument());
				this.put("secondArgument", BalMediator.this.getSecondArgument());
				this.put("thirdArgument", BalMediator.this.getThirdArgument());
				this.put("fourthArgument", BalMediator.this.getFourthArgument());
				this.put("fifthArgument", BalMediator.this.getFifthArgument());
				this.put("sixthArgument", BalMediator.this.getSixthArgument());
				this.put("functionName", BalMediator.this.getFunctionName());
			}
		};

		BalRuntime.balStart(scheduler, properties, "wso2", "datamapper", "0");
		context.setProperty("result", properties.get("result"));
		return true;
	}

	public void setFirstArgument(String value) {
		this.firstArgument = value;
	}

	public String getFirstArgument() {
		return this.firstArgument;
	}

	public void setSecondArgument(String value) {
		this.secondArgument = value;
	}

	public String getSecondArgument() {
		return this.secondArgument;
	}

	public void setThirdArgument(String value) {
		this.thirdArgument = value;
	}

	public String getThirdArgument() {
		return this.thirdArgument;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public BXml getPayload(MessageContext context) {
		return OMElementConverter.toBXml(context.getEnvelope().getBody().getFirstElement());
	}

	public String getFourthArgument() {
		return this.fourthArgument;
	}

	public void setFourthArgument(String fourthArgument) {
		this.fourthArgument = fourthArgument;
	}

	public String getFifthArgument() {
		return this.fifthArgument;
	}

	public void setFifthArgument(String fifthArgument) {
		this.fifthArgument = fifthArgument;
	}

	public String getSixthArgument() {
		return this.sixthArgument;
	}

	public void setSixthArgument(String sixthArgument) {
		this.sixthArgument = sixthArgument;
	}
}
