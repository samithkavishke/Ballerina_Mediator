//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package bal.mediator;

import org.OmelementBXmlConversion.BXmlConverter;
import io.ballerina.runtime.api.values.BXml;
import io.ballerina.runtime.api.Environment;
import io.ballerina.runtime.api.Future;
import io.ballerina.runtime.api.PredefinedTypes;
import io.ballerina.runtime.api.async.Callback;
import io.ballerina.runtime.api.async.StrandMetadata;
import io.ballerina.runtime.api.values.BError;
import io.ballerina.runtime.api.values.BObject;
import io.ballerina.runtime.internal.BalRuntime;
import io.ballerina.runtime.internal.scheduling.Scheduler;
import io.ballerina.runtime.internal.scheduling.Strand;
import io.ballerina.runtime.internal.values.BmpStringValue;
import org.apache.synapse.core.axis2.Axis2MessageContext;

import java.util.Map;

public class Call {
	public Call() {
	}

	public static void call(Environment env, BObject object) {
		final Strand strand = Scheduler.getStrand();
		Scheduler scheduler = strand.scheduler;
		BalRuntime balRuntime = new BalRuntime(scheduler);
		Axis2MessageContext messageContext = (Axis2MessageContext) strand.getProperty("messageContext");
		BXml payload =  (BXml)strand.getProperty("payload");;
		Object firstArgument = strand.getProperty("firstArgument").toString();
		Object secondArgument = strand.getProperty("secondArgument").toString();
		String thirdArgument = strand.getProperty("thirdArgument").toString();
		String fourthArgument = strand.getProperty("fourthArgument").toString();
		String fifthArgument = strand.getProperty("fifthArgument").toString();
		String sixthArgument = strand.getProperty("sixthArgument").toString();
		String functionName = strand.getProperty("functionName").toString();

		Object[] argumentsList = buildArguments(payload,firstArgument, secondArgument);
		final Future balFuture = env.markAsync();
		Callback returnCallback = new Callback() {
			public void notifySuccess(Object result) {
				strand.setProperty("result", result.toString());
				balFuture.complete(result);
			}

			public void notifyFailure(BError result) {
				strand.setProperty("result", result.toString());
				balFuture.complete(result);
			}
		};
		balRuntime.invokeMethodAsyncSequentially(object, functionName, (String)null, (StrandMetadata)null, returnCallback, (Map)null, PredefinedTypes.TYPE_ANY, argumentsList);
	}

	private static Object[] buildArguments(BXml payload,Object... arguments) {
		Object[] args = new Object[arguments.length * 2+2];
		args[0] = payload;
		args[1] = true;
		for (int i = 0; i < arguments.length; i++) {
			args[i * 2+2] = new BmpStringValue(arguments[i].toString()) ;
			args[i * 2 + 3] = true;
		}

		return args;
	}
}
