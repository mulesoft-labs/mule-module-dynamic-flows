package util;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SomeResourceTransformer extends AbstractMessageTransformer
{

	@Override
	public Object transformMessage(MuleMessage arg0, String arg1) throws TransformerException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("contextName", "testContext");
		map.put("configs", getContextConfiguration());
		return map;
	}

	private List<String> getContextConfiguration() {
		ArrayList<String> configFiles = new ArrayList<String>();
		configFiles.add(muleConfig1);
		configFiles.add(muleConfig2);
		
		return configFiles;
	}

    private static String muleConfig1 = "<mule xmlns=\"http://www.mulesoft.org/schema/mule/core\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "      xmlns:vm=\"http://www.mulesoft.org/schema/mule/vm\"\n" +
            "      xsi:schemaLocation=\"\n" +
            "      http://www.mulesoft.org/schema/mule/vm http://www.mulesoft.org/schema/mule/vm/current/mule-vm.xsd\n" +
            "        http://www.mulesoft.org/schema/mule/core http://www.mulesoft.org/schema/mule/core/current/mule.xsd\">\n" +
            "\n" +
            "    <flow name=\"echo1\">\n" +
            "            <vm:inbound-endpoint path=\"echo1\" exchange-pattern=\"request-response\"/>\n" +
            "            \t<logger level=\"ERROR\" />\n" +
            "            </flow>\n" +
            "</mule>";

    private static String muleConfig2 = "<mule xmlns=\"http://www.mulesoft.org/schema/mule/core\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "      xmlns:vm=\"http://www.mulesoft.org/schema/mule/vm\"\n" +
            "      xsi:schemaLocation=\"\n" +
            "      http://www.mulesoft.org/schema/mule/vm http://www.mulesoft.org/schema/mule/vm/current/mule-vm.xsd\n" +
            "        http://www.mulesoft.org/schema/mule/core http://www.mulesoft.org/schema/mule/core/current/mule.xsd\">\n" +
            "\n" +
            "    <flow name=\"echo2\">\n" +
            "            <vm:inbound-endpoint path=\"echo2\" exchange-pattern=\"request-response\"/>\n" +
            "            \t<logger level=\"ERROR\" />\n" +
            "            </flow>\n" +
            "</mule>";
	
}
