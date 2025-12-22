package org.springframework.boot.admin;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/admin/SpringApplicationAdminMXBean.class */
public interface SpringApplicationAdminMXBean {
    boolean isReady();

    boolean isEmbeddedWebApplication();

    String getProperty(String key);

    void shutdown();
}
