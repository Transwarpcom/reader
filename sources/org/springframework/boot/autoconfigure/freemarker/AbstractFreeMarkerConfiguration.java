package org.springframework.boot.autoconfigure.freemarker;

import java.util.Properties;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/freemarker/AbstractFreeMarkerConfiguration.class */
abstract class AbstractFreeMarkerConfiguration {
    private final FreeMarkerProperties properties;

    protected AbstractFreeMarkerConfiguration(FreeMarkerProperties properties) {
        this.properties = properties;
    }

    protected final FreeMarkerProperties getProperties() {
        return this.properties;
    }

    protected void applyProperties(FreeMarkerConfigurationFactory factory) {
        factory.setTemplateLoaderPaths(this.properties.getTemplateLoaderPath());
        factory.setPreferFileSystemAccess(this.properties.isPreferFileSystemAccess());
        factory.setDefaultEncoding(this.properties.getCharsetName());
        Properties settings = new Properties();
        settings.putAll(this.properties.getSettings());
        factory.setFreemarkerSettings(settings);
    }
}
