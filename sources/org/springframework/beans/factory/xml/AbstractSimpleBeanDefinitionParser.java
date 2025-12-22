package org.springframework.beans.factory.xml;

import me.ag2s.epublib.epub.NCXDocumentV3;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.core.Conventions;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/xml/AbstractSimpleBeanDefinitionParser.class */
public abstract class AbstractSimpleBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    @Override // org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        NamedNodeMap attributes = element.getAttributes();
        for (int x = 0; x < attributes.getLength(); x++) {
            Attr attribute = (Attr) attributes.item(x);
            if (isEligibleAttribute(attribute, parserContext)) {
                String propertyName = extractPropertyName(attribute.getLocalName());
                Assert.state(StringUtils.hasText(propertyName), "Illegal property name returned from 'extractPropertyName(String)': cannot be null or empty.");
                builder.addPropertyValue(propertyName, attribute.getValue());
            }
        }
        postProcess(builder, element);
    }

    protected boolean isEligibleAttribute(Attr attribute, ParserContext parserContext) {
        String fullName = attribute.getName();
        return (fullName.equals(NCXDocumentV3.XHTMLAttributes.xmlns) || fullName.startsWith("xmlns:") || !isEligibleAttribute(parserContext.getDelegate().getLocalName(attribute))) ? false : true;
    }

    protected boolean isEligibleAttribute(String attributeName) {
        return !"id".equals(attributeName);
    }

    protected String extractPropertyName(String attributeName) {
        return Conventions.attributeNameToPropertyName(attributeName);
    }

    protected void postProcess(BeanDefinitionBuilder beanDefinition, Element element) {
    }
}
