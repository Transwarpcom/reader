package org.springframework.boot.context;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.LiveBeansView;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/ContextIdApplicationContextInitializer.class */
public class ContextIdApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {
    private int order = 2147483637;

    public void setOrder(int order) {
        this.order = order;
    }

    @Override // org.springframework.core.Ordered
    public int getOrder() {
        return this.order;
    }

    @Override // org.springframework.context.ApplicationContextInitializer
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ContextId contextId = getContextId(applicationContext);
        applicationContext.setId(contextId.getId());
        applicationContext.getBeanFactory().registerSingleton(ContextId.class.getName(), contextId);
    }

    private ContextId getContextId(ConfigurableApplicationContext applicationContext) {
        ApplicationContext parent = applicationContext.getParent();
        if (parent != null && parent.containsBean(ContextId.class.getName())) {
            return ((ContextId) parent.getBean(ContextId.class)).createChildId();
        }
        return new ContextId(getApplicationId(applicationContext.getEnvironment()));
    }

    private String getApplicationId(ConfigurableEnvironment environment) {
        String name = environment.getProperty("spring.application.name");
        return StringUtils.hasText(name) ? name : LiveBeansView.MBEAN_APPLICATION_KEY;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/ContextIdApplicationContextInitializer$ContextId.class */
    class ContextId {
        private final AtomicLong children = new AtomicLong(0);
        private final String id;

        ContextId(String id) {
            this.id = id;
        }

        ContextId createChildId() {
            return ContextIdApplicationContextInitializer.this.new ContextId(this.id + "-" + this.children.incrementAndGet());
        }

        String getId() {
            return this.id;
        }
    }
}
