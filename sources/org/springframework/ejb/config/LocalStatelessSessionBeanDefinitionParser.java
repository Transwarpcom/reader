package org.springframework.ejb.config;

import org.w3c.dom.Element;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/ejb/config/LocalStatelessSessionBeanDefinitionParser.class */
class LocalStatelessSessionBeanDefinitionParser extends AbstractJndiLocatingBeanDefinitionParser {
    LocalStatelessSessionBeanDefinitionParser() {
    }

    @Override // org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser
    protected String getBeanClassName(Element element) {
        return "org.springframework.ejb.access.LocalStatelessSessionProxyFactoryBean";
    }
}
