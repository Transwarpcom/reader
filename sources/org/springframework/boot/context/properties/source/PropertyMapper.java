package org.springframework.boot.context.properties.source;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/source/PropertyMapper.class */
interface PropertyMapper {
    public static final PropertyMapping[] NO_MAPPINGS = new PropertyMapping[0];

    PropertyMapping[] map(ConfigurationPropertyName configurationPropertyName);

    PropertyMapping[] map(String propertySourceName);
}
