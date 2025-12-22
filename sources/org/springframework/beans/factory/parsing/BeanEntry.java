package org.springframework.beans.factory.parsing;

import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.springframework.beans.factory.parsing.ParseState;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/parsing/BeanEntry.class */
public class BeanEntry implements ParseState.Entry {
    private String beanDefinitionName;

    public BeanEntry(String beanDefinitionName) {
        this.beanDefinitionName = beanDefinitionName;
    }

    public String toString() {
        return "Bean '" + this.beanDefinitionName + OperatorName.SHOW_TEXT_LINE;
    }
}
