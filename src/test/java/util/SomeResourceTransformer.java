package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class SomeResourceTransformer  extends AbstractMessageTransformer
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
	
	private static String muleConfig1 = "<mule xmlns=\"http://www.mulesoft.org/schema/mule/core\"\n" + 
			"      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" + 
			"      xmlns:spring=\"http://www.springframework.org/schema/beans\"\n" + 
			"      xmlns:dynamicflows=\"http://www.mulesoft.org/schema/mule/dynamicflows\"\n" + 
			"      xsi:schemaLocation=\"\n" + 
			"        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd\n" + 
			"        http://www.mulesoft.org/schema/mule/core http://www.mulesoft.org/schema/mule/core/current/mule.xsd\n" + 
			"        http://www.mulesoft.org/schema/mule/dynamicflows http://www.mulesoft.org/schema/mule/dynamicflows/1.0/mule-dynamicflows.xsd\">\n" + 
			"\n" + 
			"\n" + 
			"    \n" + 
			"    <flow name=\"echo1\">\n" + 
			"    	<echo-component />\n" + 
			"    </flow>\n" + 
			"\n" + 
			"</mule>";
	
	private static String muleConfig2 = "<mule xmlns=\"http://www.mulesoft.org/schema/mule/core\"\n" + 
			"      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" + 
			"      xmlns:spring=\"http://www.springframework.org/schema/beans\"\n" + 
			"      xmlns:dynamicflows=\"http://www.mulesoft.org/schema/mule/dynamicflows\"\n" + 
			"      xsi:schemaLocation=\"\n" + 
			"        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd\n" + 
			"        http://www.mulesoft.org/schema/mule/core http://www.mulesoft.org/schema/mule/core/current/mule.xsd\n" + 
			"        http://www.mulesoft.org/schema/mule/dynamicflows http://www.mulesoft.org/schema/mule/dynamicflows/1.0/mule-dynamicflows.xsd\">\n" + 
			"\n" + 
			"\n" + 
			"    \n" + 
			"    <flow name=\"echo2\">\n" + 
			"    	<echo-component />\n" + 
			"    </flow>\n" + 
			"\n" + 
			"</mule>";
	
}
