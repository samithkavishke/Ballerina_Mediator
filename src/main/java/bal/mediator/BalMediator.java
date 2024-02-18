package bal.mediator;

import io.ballerina.runtime.internal.scheduling.Scheduler;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import java.util.HashMap;
import java.util.Map;

import static bal.mediator.Constants.CONTEXT;
import static bal.mediator.Constants.MODULE_NAME;
import static bal.mediator.Constants.ORG_NAME;
import static bal.mediator.Constants.VERSION;
import static io.ballerina.runtime.internal.BalRuntime.balStart;

public class BalMediator extends AbstractMediator {
	public boolean mediate(MessageContext context) {
		final Scheduler scheduler = new Scheduler(false);
		balStart(scheduler, new HashMap<>(Map.of(CONTEXT, context)), ORG_NAME, MODULE_NAME, VERSION);
		return true;
	}
}
