package bal.mediator;

import io.ballerina.runtime.api.Environment;
import io.ballerina.runtime.api.PredefinedTypes;
import io.ballerina.runtime.api.Runtime;
import io.ballerina.runtime.api.async.Callback;
import io.ballerina.runtime.api.values.BError;
import io.ballerina.runtime.api.values.BObject;
import io.ballerina.runtime.internal.BalRuntime;
import io.ballerina.runtime.internal.scheduling.Scheduler;
import io.ballerina.runtime.internal.scheduling.Strand;
import io.ballerina.runtime.internal.values.BmpStringValue;
import org.apache.synapse.MessageContext;
import io.ballerina.runtime.api.Future;
import javax.xml.namespace.QName;

import static bal.mediator.Constants.CONTEXT;
import static bal.mediator.Constants.FIRST_ARGUMENT;
import static bal.mediator.Constants.FUNCTION_NAME;
import static bal.mediator.Constants.RESULT;
import static bal.mediator.Constants.SECOND_ARGUMENT;

public class Call {
    public static void call(Environment env, BObject object) {
        Strand strand = Scheduler.getStrand();
        Scheduler scheduler = strand.scheduler;
        Runtime balRuntime = new BalRuntime(scheduler);
        MessageContext context = (MessageContext) strand.getProperty(CONTEXT);

        String firstArgument = context.getProperty(FIRST_ARGUMENT).toString();
        String secondArgument = context.getProperty(SECOND_ARGUMENT).toString();

        Object[] argumentsList = buildArguments(firstArgument, secondArgument);

        final Future balFuture = env.markAsync();
        Callback returnCallback = new Callback() {
            @Override
            public void notifySuccess(Object result) {
                // Note: Here set the result for the payload.
                context.setProperty(RESULT, result.toString());
                balFuture.complete(result);
            }

            @Override
            public void notifyFailure(BError result) {
                balFuture.complete(result);
            }
        };

        balRuntime.invokeMethodAsyncSequentially(object, context.getProperty(FUNCTION_NAME).toString(), null,
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
