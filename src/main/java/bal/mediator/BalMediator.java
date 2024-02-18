package bal.mediator;

import io.ballerina.runtime.internal.scheduling.Scheduler;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import java.util.HashMap;
import java.util.Map;

import static bal.mediator.Constants.CONTEXT;
import static bal.mediator.Constants.FIRST_ARGUMENT;
import static bal.mediator.Constants.FUNCTION_NAME;
import static bal.mediator.Constants.MODULE_NAME;
import static bal.mediator.Constants.ORG_NAME;
import static bal.mediator.Constants.SECOND_ARGUMENT;
import static bal.mediator.Constants.VERSION;
import static io.ballerina.runtime.internal.BalRuntime.balStart;

public class BalMediator extends AbstractMediator {
	static Scheduler scheduler = new Scheduler(false);
	private String firstArgument ="";
	private String secondArgument = "";
	private String functionName;

	public boolean mediate(MessageContext context) {
		context.setProperty(FIRST_ARGUMENT, getFirstArgument());
		context.setProperty(SECOND_ARGUMENT, getSecondArgument());
		context.setProperty(FUNCTION_NAME, getFunctionName());
		balStart(scheduler, new HashMap<>(Map.of(CONTEXT, context)), ORG_NAME, MODULE_NAME, VERSION);
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
}
