package org.springframework.boot.context.properties.source;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/source/PropertyMapping.class */
class PropertyMapping {
    private final String propertySourceName;
    private final ConfigurationPropertyName configurationPropertyName;

    PropertyMapping(String propertySourceName, ConfigurationPropertyName configurationPropertyName) {
        this.propertySourceName = propertySourceName;
        this.configurationPropertyName = configurationPropertyName;
    }

    public String getPropertySourceName() {
        return this.propertySourceName;
    }

    public ConfigurationPropertyName getConfigurationPropertyName() {
        return this.configurationPropertyName;
    }

    public boolean isApplicable(ConfigurationPropertyName name) {
        return this.configurationPropertyName.equals(name);
    }
}
