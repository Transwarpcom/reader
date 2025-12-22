package org.springframework.boot.context.properties.bind;

import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/bind/BeanBinder.class */
interface BeanBinder {
    <T> T bind(ConfigurationPropertyName name, Bindable<T> target, Binder.Context context, BeanPropertyBinder propertyBinder);
}
