package org.springframework.boot;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.ClassUtils;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/EnvironmentConverter.class */
final class EnvironmentConverter {
    private static final String CONFIGURABLE_WEB_ENVIRONMENT_CLASS = "org.springframework.web.context.ConfigurableWebEnvironment";
    private static final Set<String> SERVLET_ENVIRONMENT_SOURCE_NAMES;
    private final ClassLoader classLoader;

    static {
        Set<String> names = new HashSet<>();
        names.add("servletContextInitParams");
        names.add("servletConfigInitParams");
        names.add("jndiProperties");
        SERVLET_ENVIRONMENT_SOURCE_NAMES = Collections.unmodifiableSet(names);
    }

    EnvironmentConverter(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    StandardEnvironment convertEnvironmentIfNecessary(ConfigurableEnvironment environment, Class<? extends StandardEnvironment> type) {
        if (type.equals(environment.getClass())) {
            return (StandardEnvironment) environment;
        }
        return convertEnvironment(environment, type);
    }

    private StandardEnvironment convertEnvironment(ConfigurableEnvironment environment, Class<? extends StandardEnvironment> type) {
        StandardEnvironment result = createEnvironment(type);
        result.setActiveProfiles(environment.getActiveProfiles());
        result.setConversionService(environment.getConversionService());
        copyPropertySources(environment, result);
        return result;
    }

    private StandardEnvironment createEnvironment(Class<? extends StandardEnvironment> type) {
        try {
            return type.newInstance();
        } catch (Exception e) {
            return new StandardEnvironment();
        }
    }

    private void copyPropertySources(ConfigurableEnvironment source, StandardEnvironment target) {
        removePropertySources(target.getPropertySources(), isServletEnvironment(target.getClass(), this.classLoader));
        Iterator<PropertySource<?>> it = source.getPropertySources().iterator();
        while (it.hasNext()) {
            PropertySource<?> propertySource = it.next();
            if (!SERVLET_ENVIRONMENT_SOURCE_NAMES.contains(propertySource.getName())) {
                target.getPropertySources().addLast(propertySource);
            }
        }
    }

    private boolean isServletEnvironment(Class<?> conversionType, ClassLoader classLoader) {
        try {
            Class<?> webEnvironmentClass = ClassUtils.forName(CONFIGURABLE_WEB_ENVIRONMENT_CLASS, classLoader);
            return webEnvironmentClass.isAssignableFrom(conversionType);
        } catch (Throwable th) {
            return false;
        }
    }

    private void removePropertySources(MutablePropertySources propertySources, boolean isServletEnvironment) {
        Set<String> names = new HashSet<>();
        Iterator<PropertySource<?>> it = propertySources.iterator();
        while (it.hasNext()) {
            PropertySource<?> propertySource = it.next();
            names.add(propertySource.getName());
        }
        for (String name : names) {
            if (!isServletEnvironment || !SERVLET_ENVIRONMENT_SOURCE_NAMES.contains(name)) {
                propertySources.remove(name);
            }
        }
    }
}
