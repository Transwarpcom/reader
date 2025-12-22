package org.springframework.beans.factory.xml;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.lang.Nullable;
import org.w3c.dom.Element;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/xml/BeanDefinitionParser.class */
public interface BeanDefinitionParser {
    @Nullable
    BeanDefinition parse(Element element, ParserContext parserContext);
}
