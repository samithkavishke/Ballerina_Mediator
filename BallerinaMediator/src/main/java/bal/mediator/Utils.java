package bal.mediator;

import java.util.Optional;

import org.apache.synapse.MessageContext;
import org.wso2.carbon.connector.core.util.ConnectorUtils;
import org.wso2.carbon.module.core.SimpleMessageContext;
import org.apache.commons.lang.StringUtils;
public class Utils {
    public static String getStringParam(MessageContext mc, String parameterKey) {
        String parameter = (String) ConnectorUtils.lookupTemplateParamater(mc, parameterKey);
//        return StringUtils.isNotBlank(parameter) ? Optional.of(parameter) : Optional.empty();
        return parameter;
    }
}
