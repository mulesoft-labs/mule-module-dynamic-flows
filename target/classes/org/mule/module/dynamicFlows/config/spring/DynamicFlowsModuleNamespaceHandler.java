
package org.mule.module.dynamicFlows.config.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * Registers bean definitions parsers for handling elements in <code>http://www.mulesoft.org/schema/mule/dynamicflows</code>.
 * 
 */
public class DynamicFlowsModuleNamespaceHandler
    extends NamespaceHandlerSupport
{


    /**
     * Invoked by the {@link DefaultBeanDefinitionDocumentReader} after construction but before any custom elements are parsed. 
     * @see NamespaceHandlerSupport#registerBeanDefinitionParser(String, BeanDefinitionParser)
     * 
     */
    public void init() {
        registerBeanDefinitionParser("config", new DynamicFlowsModuleConfigDefinitionParser());
        registerBeanDefinitionParser("add", new AddDefinitionParser());
        registerBeanDefinitionParser("remove", new RemoveDefinitionParser());
        registerBeanDefinitionParser("run", new RunDefinitionParser());
    }

}
