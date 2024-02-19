package bal.mediator;

import io.ballerina.runtime.api.Environment;
import io.ballerina.runtime.api.Future;
import io.ballerina.runtime.api.PredefinedTypes;
import io.ballerina.runtime.api.async.Callback;
import io.ballerina.runtime.api.values.BError;
import io.ballerina.runtime.api.values.BObject;
import io.ballerina.runtime.internal.BalRuntime;
import io.ballerina.runtime.internal.scheduling.Scheduler;
import io.ballerina.runtime.internal.scheduling.Strand;
import io.ballerina.runtime.internal.values.BmpStringValue;

public class Call {
	public static void call(Environment env, BObject object) {
		final Strand strand = Scheduler.getStrand();
		Scheduler scheduler = strand.scheduler;
		BalRuntime balRuntime = new BalRuntime(scheduler);

		final String payload = (String) strand.getProperty("payload");
        String firstArgument = strand.getProperty("firstArgument").toString();
		String secondArgument = strand.getProperty("secondArgument").toString();
		String functionName = strand.getProperty("functionName").toString();

		Object[] argumentsList = buildArguments(payload, secondArgument);

		final Future balFuture = env.markAsync();
		Callback returnCallback = new Callback() {
			@Override
			public void notifySuccess(Object result) {
				// Note: Here set the result for the payload.
				strand.setProperty("result", result.toString());
				balFuture.complete(result);
			}

			@Override
			public void notifyFailure(BError result) {
                strand.setProperty("result", result.toString());
				balFuture.complete(result);
			}
		};

		balRuntime.invokeMethodAsyncSequentially(object, functionName, null,
				null, returnCallback, null,
				PredefinedTypes.TYPE_ANY, argumentsList);
	}

	private static Object[] buildArguments(String... arguments) {
		// Note: This function constructs the arguments array for the Ballerina function invocation.
		// Depending on the function signature on the Ballerina side, you may need to adjust the number
		// of arguments and their types accordingly.
		Object[] args = new Object[arguments.length * 2];
		for (int i = 0; i < arguments.length; i++) {
			args[i * 2] = new BmpStringValue(arguments[i]);
			args[i * 2 + 1] = true;
		}
		return args;
	}
}
