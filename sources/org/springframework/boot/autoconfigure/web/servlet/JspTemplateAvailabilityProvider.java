package org.springframework.boot.autoconfigure.web.servlet;

import java.io.File;
import java.security.AccessControlException;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/web/servlet/JspTemplateAvailabilityProvider.class */
public class JspTemplateAvailabilityProvider implements TemplateAvailabilityProvider {
    @Override // org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider
    public boolean isTemplateAvailable(String view, Environment environment, ClassLoader classLoader, ResourceLoader resourceLoader) {
        if (ClassUtils.isPresent("org.apache.jasper.compiler.JspConfig", classLoader)) {
            String resourceName = getResourceName(view, environment);
            if (resourceLoader.getResource(resourceName).exists()) {
                return true;
            }
            try {
                return new File("src/main/webapp", resourceName).exists();
            } catch (AccessControlException e) {
                return false;
            }
        }
        return false;
    }

    private String getResourceName(String view, Environment environment) {
        String prefix = environment.getProperty("spring.mvc.view.prefix", "");
        String suffix = environment.getProperty("spring.mvc.view.suffix", "");
        return prefix + view + suffix;
    }
}
