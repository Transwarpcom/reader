package org.springframework.aop.config;

import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.springframework.beans.factory.parsing.ParseState;

/* loaded from: reader.jar:BOOT-INF/lib/spring-aop-5.1.8.RELEASE.jar:org/springframework/aop/config/AdvisorEntry.class */
public class AdvisorEntry implements ParseState.Entry {
    private final String name;

    public AdvisorEntry(String name) {
        this.name = name;
    }

    public String toString() {
        return "Advisor '" + this.name + OperatorName.SHOW_TEXT_LINE;
    }
}
