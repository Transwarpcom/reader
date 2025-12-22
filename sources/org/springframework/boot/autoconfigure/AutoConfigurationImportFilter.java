package org.springframework.boot.autoconfigure;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/AutoConfigurationImportFilter.class */
public interface AutoConfigurationImportFilter {
    boolean[] match(String[] autoConfigurationClasses, AutoConfigurationMetadata autoConfigurationMetadata);
}
