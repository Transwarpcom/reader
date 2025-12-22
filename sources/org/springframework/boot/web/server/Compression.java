package org.springframework.boot.web.server;

import org.springframework.util.MimeTypeUtils;
import org.springframework.util.unit.DataSize;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/server/Compression.class */
public class Compression {
    private boolean enabled = false;
    private String[] mimeTypes = {"text/html", MimeTypeUtils.TEXT_XML_VALUE, "text/plain", "text/css", "text/javascript", "application/javascript", "application/json", MimeTypeUtils.APPLICATION_XML_VALUE};
    private String[] excludedUserAgents = null;
    private DataSize minResponseSize = DataSize.ofKilobytes(2);

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String[] getMimeTypes() {
        return this.mimeTypes;
    }

    public void setMimeTypes(String[] mimeTypes) {
        this.mimeTypes = mimeTypes;
    }

    public String[] getExcludedUserAgents() {
        return this.excludedUserAgents;
    }

    public void setExcludedUserAgents(String[] excludedUserAgents) {
        this.excludedUserAgents = excludedUserAgents;
    }

    public DataSize getMinResponseSize() {
        return this.minResponseSize;
    }

    public void setMinResponseSize(DataSize minSize) {
        this.minResponseSize = minSize;
    }
}
