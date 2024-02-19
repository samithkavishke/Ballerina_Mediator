package bal.mediator;

import io.ballerina.runtime.internal.scheduling.Scheduler;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import java.util.HashMap;

import static bal.mediator.Constants.FIRST_ARGUMENT;
import static bal.mediator.Constants.FUNCTION_NAME;
import static bal.mediator.Constants.MODULE_NAME;
import static bal.mediator.Constants.ORG_NAME;
import static bal.mediator.Constants.PAYLOAD;
import static bal.mediator.Constants.RESULT;
import static bal.mediator.Constants.SECOND_ARGUMENT;
import static bal.mediator.Constants.VERSION;
import static io.ballerina.runtime.internal.BalRuntime.balStart;

public class BalMediator extends AbstractMediator {
	static Scheduler scheduler = new Scheduler(false);
	private String firstArgument ="";
	private String secondArgument = "";
	private String functionName;

	public boolean mediate(MessageContext context) {
		HashMap<String, Object> properties = new HashMap<>() {{
			put(PAYLOAD, getPayload(context));
			put(FIRST_ARGUMENT, getFirstArgument());
			put(SECOND_ARGUMENT, getSecondArgument());
			put(FUNCTION_NAME, getFunctionName());
		}};

		balStart(scheduler, properties, ORG_NAME, MODULE_NAME, VERSION);
		context.setProperty(RESULT, properties.get(RESULT));
		return true;
	}

	public void setFirstArgument(String value) {
		firstArgument = value;
	}

	public String getFirstArgument() {
		return firstArgument;
	}

	public void setSecondArgument(String value) {
		secondArgument = value;
	}

	public String getSecondArgument() {
		return secondArgument;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getPayload(MessageContext context) {
		return context.getEnvelope().getBody().getFirstElement().toString();
	}
}
