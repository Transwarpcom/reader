package org.springframework.aop.config;

import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.springframework.beans.factory.parsing.ParseState;
import org.springframework.util.StringUtils;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/config/AspectEntry.class */
public class AspectEntry implements ParseState.Entry {
    private final String id;
    private final String ref;

    public AspectEntry(String id, String ref) {
        this.id = id;
        this.ref = ref;
    }

    public String toString() {
        return "Aspect: " + (StringUtils.hasLength(this.id) ? "id='" + this.id + OperatorName.SHOW_TEXT_LINE : "ref='" + this.ref + OperatorName.SHOW_TEXT_LINE);
    }
}
