package org.springframework.beans.factory.support;

import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/support/GenericBeanDefinition.class */
public class GenericBeanDefinition extends AbstractBeanDefinition {

    @Nullable
    private String parentName;

    public GenericBeanDefinition() {
    }

    public GenericBeanDefinition(BeanDefinition original) {
        super(original);
    }

    @Override // org.springframework.beans.factory.config.BeanDefinition
    public void setParentName(@Nullable String parentName) {
        this.parentName = parentName;
    }

    @Override // org.springframework.beans.factory.config.BeanDefinition
    @Nullable
    public String getParentName() {
        return this.parentName;
    }

    @Override // org.springframework.beans.factory.support.AbstractBeanDefinition
    public AbstractBeanDefinition cloneBeanDefinition() {
        return new GenericBeanDefinition(this);
    }

    @Override // org.springframework.beans.factory.support.AbstractBeanDefinition, org.springframework.core.AttributeAccessorSupport
    public boolean equals(Object other) {
        return this == other || ((other instanceof GenericBeanDefinition) && super.equals(other));
    }

    @Override // org.springframework.beans.factory.support.AbstractBeanDefinition
    public String toString() {
        StringBuilder sb = new StringBuilder("Generic bean");
        if (this.parentName != null) {
            sb.append(" with parent '").append(this.parentName).append(OperatorName.SHOW_TEXT_LINE);
        }
        sb.append(": ").append(super.toString());
        return sb.toString();
    }
}
