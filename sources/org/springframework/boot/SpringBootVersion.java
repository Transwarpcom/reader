package org.springframework.boot;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/SpringBootVersion.class */
public final class SpringBootVersion {
    private SpringBootVersion() {
    }

    public static String getVersion() {
        Package pkg = SpringBootVersion.class.getPackage();
        if (pkg != null) {
            return pkg.getImplementationVersion();
        }
        return null;
    }
}
