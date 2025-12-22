package io.vertx.ext.web.common;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-common-3.8.5.jar:io/vertx/ext/web/common/WebEnvironment.class */
public interface WebEnvironment {
    public static final String SYSTEM_PROPERTY_NAME = "vertxweb.environment";
    public static final String ENV_VARIABLE_NAME = "VERTXWEB_ENVIRONMENT";

    static boolean development() {
        String mode = mode();
        return "dev".equalsIgnoreCase(mode) || "Development".equalsIgnoreCase(mode);
    }

    static String mode() {
        return System.getProperty(SYSTEM_PROPERTY_NAME, System.getenv(ENV_VARIABLE_NAME));
    }
}
