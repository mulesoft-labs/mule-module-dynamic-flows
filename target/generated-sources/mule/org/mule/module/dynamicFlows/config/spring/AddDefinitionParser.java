
package org.mule.module.dynamicFlows.config.spring;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.mule.config.spring.MuleHierarchicalBeanDefinitionParserDelegate;
import org.mule.config.spring.util.SpringXMLUtils;
import org.mule.module.dynamicFlows.config.AddMessageProcessor;
import org.mule.util.TemplateParser;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

public class AddDefinitionParser
    implements BeanDefinitionParser
{

    /**
     * Mule Pattern Info
     * 
     */
    private TemplateParser.PatternInfo patternInfo;

    public AddDefinitionParser() {
        patternInfo = TemplateParser.createMuleStyleParser().getStyle();
    }

    public BeanDefinition parse(Element element, ParserContext parserContent) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(AddMessageProcessor.class.getName());
        String configRef = element.getAttribute("config-ref");
        if ((configRef!= null)&&(!StringUtils.isBlank(configRef))) {
            builder.addPropertyValue("moduleObject", configRef);
        }
        if ((element.getAttribute("contextName")!= null)&&(!StringUtils.isBlank(element.getAttribute("contextName")))) {
            builder.addPropertyValue("contextName", element.getAttribute("contextName"));
        }
        Element configsListElement = null;
        configsListElement = DomUtils.getChildElementByTagName(element, "configs");
        List<Element> configsListChilds = null;
        if (configsListElement!= null) {
            String configsRef = configsListElement.getAttribute("ref");
            if ((configsRef!= null)&&(!StringUtils.isBlank(configsRef))) {
                if ((!configsRef.startsWith(patternInfo.getPrefix()))&&(!configsRef.endsWith(patternInfo.getSuffix()))) {
                    builder.addPropertyValue("configs", new RuntimeBeanReference(configsRef));
                } else {
                    builder.addPropertyValue("configs", configsRef);
                }
            } else {
                ManagedList configs = new ManagedList();
                configsListChilds = DomUtils.getChildElementsByTagName(configsListElement, "config");
                if (configsListChilds!= null) {
                    for (Element configsChild: configsListChilds) {
                        String valueRef = configsChild.getAttribute("value-ref");
                        if ((valueRef!= null)&&(!StringUtils.isBlank(valueRef))) {
                            configs.add(new RuntimeBeanReference(valueRef));
                        } else {
                            configs.add(configsChild.getTextContent());
                        }
                    }
                }
                builder.addPropertyValue("configs", configs);
            }
        }
        BeanDefinition definition = builder.getBeanDefinition();
        definition.setAttribute(MuleHierarchicalBeanDefinitionParserDelegate.MULE_NO_RECURSE, Boolean.TRUE);
        MutablePropertyValues propertyValues = parserContent.getContainingBeanDefinition().getPropertyValues();
        if (parserContent.getContainingBeanDefinition().getBeanClassName().equals("org.mule.config.spring.factories.PollingMessageSourceFactoryBean")) {
            propertyValues.addPropertyValue("messageProcessor", definition);
        } else {
            PropertyValue messageProcessors = propertyValues.getPropertyValue("messageProcessors");
            if ((messageProcessors == null)||(messageProcessors.getValue() == null)) {
                propertyValues.addPropertyValue("messageProcessors", new ManagedList());
            }
            List listMessageProcessors = ((List) propertyValues.getPropertyValue("messageProcessors").getValue());
            listMessageProcessors.add(definition);
        }
        return definition;
    }

    protected String getAttributeValue(Element element, String attributeName) {
        if (!StringUtils.isEmpty(element.getAttribute(attributeName))) {
            return element.getAttribute(attributeName);
        }
        return null;
    }

    private String generateChildBeanName(Element element) {
        String id = SpringXMLUtils.getNameOrId(element);
        if (StringUtils.isBlank(id)) {
            String parentId = SpringXMLUtils.getNameOrId(((Element) element.getParentNode()));
            return ((("."+ parentId)+":")+ element.getLocalName());
        } else {
            return id;
        }
    }

}
