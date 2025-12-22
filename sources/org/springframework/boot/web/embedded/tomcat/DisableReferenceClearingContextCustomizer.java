package org.springframework.boot.web.embedded.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.core.StandardContext;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/embedded/tomcat/DisableReferenceClearingContextCustomizer.class */
class DisableReferenceClearingContextCustomizer implements TomcatContextCustomizer {
    DisableReferenceClearingContextCustomizer() {
    }

    @Override // org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer
    public void customize(Context context) {
        if (!(context instanceof StandardContext)) {
            return;
        }
        StandardContext standardContext = (StandardContext) context;
        try {
            standardContext.setClearReferencesObjectStreamClassCaches(false);
            standardContext.setClearReferencesRmiTargets(false);
            standardContext.setClearReferencesThreadLocals(false);
        } catch (NoSuchMethodError e) {
        }
    }
}
