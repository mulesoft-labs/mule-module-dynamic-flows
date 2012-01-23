package org.mule;

import org.junit.Test;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.config.ConfigurationBuilder;
import org.mule.api.config.ConfigurationException;
import org.mule.api.context.MuleContextBuilder;
import org.mule.api.context.MuleContextFactory;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.config.spring.SpringXmlConfigurationBuilder;
import org.mule.construct.Flow;
import org.mule.context.DefaultMuleContextBuilder;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.session.DefaultMuleSession;
import org.mule.tck.junit4.FunctionalTestCase;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SalesforceConnectorTest extends FunctionalTestCase {

	@Override
	protected String getConfigResources() {
		return "mule-parent.xml,mule-config-test.xml";
	}

    @Test
	public void testSalesforceGetCampaigns() throws Exception
	{
        Flow flow = (Flow) muleContext.getRegistry().lookupFlowConstruct("dispatchFlow");
        MuleMessage message = new DefaultMuleMessage("hola", new HashMap<String, Object>(), muleContext);
        MuleEvent event = new DefaultMuleEvent(message, MessageExchangePattern.REQUEST_RESPONSE, new DefaultMuleSession(flow, muleContext));
        MuleMessage responseMessage = flow.process(event).getMessage();
        
        assertEquals("donkey", responseMessage.getPayloadAsString());
        

	}

    private MuleContext createParent(ApplicationContext applicationContext) throws ConfigurationException, InitialisationException {
        MuleContextFactory muleContextFactory = new DefaultMuleContextFactory();
        List<ConfigurationBuilder> builders = new ArrayList<ConfigurationBuilder>();
        SpringXmlConfigurationBuilder springXmlConfigurationBuilder = new SpringXmlConfigurationBuilder(new String[]{"/Users/fernandofederico/Desktop/source/campaign-management/src/main/resources/super.xml"});
        springXmlConfigurationBuilder.setParentContext(applicationContext);
        builders.add(springXmlConfigurationBuilder);
        MuleContextBuilder contextBuilder = new DefaultMuleContextBuilder();
        return muleContextFactory.createMuleContext(builders, contextBuilder);
    }


}
