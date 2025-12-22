package org.springframework.boot.web.servlet;

import javax.servlet.ServletException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/servlet/DelegatingFilterProxyRegistrationBean.class */
public class DelegatingFilterProxyRegistrationBean extends AbstractFilterRegistrationBean<DelegatingFilterProxy> implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private final String targetBeanName;

    public DelegatingFilterProxyRegistrationBean(String targetBeanName, ServletRegistrationBean<?>... servletRegistrationBeans) {
        super(servletRegistrationBeans);
        Assert.hasLength(targetBeanName, "TargetBeanName must not be null or empty");
        this.targetBeanName = targetBeanName;
        setName(targetBeanName);
    }

    @Override // org.springframework.context.ApplicationContextAware
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected String getTargetBeanName() {
        return this.targetBeanName;
    }

    @Override // org.springframework.boot.web.servlet.AbstractFilterRegistrationBean
    /* renamed from: getFilter, reason: merged with bridge method [inline-methods] */
    public DelegatingFilterProxy mo5642getFilter() {
        return new DelegatingFilterProxy(this.targetBeanName, getWebApplicationContext()) { // from class: org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean.1
            protected void initFilterBean() throws ServletException {
            }
        };
    }

    private WebApplicationContext getWebApplicationContext() {
        Assert.notNull(this.applicationContext, "ApplicationContext be injected");
        Assert.isInstanceOf(WebApplicationContext.class, this.applicationContext);
        return this.applicationContext;
    }
}
