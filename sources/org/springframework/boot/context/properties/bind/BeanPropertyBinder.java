package org.springframework.boot.context.properties.bind;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/bind/BeanPropertyBinder.class */
interface BeanPropertyBinder {
    Object bindProperty(String propertyName, Bindable<?> target);
}
