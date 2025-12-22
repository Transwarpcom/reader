package org.springframework.beans.factory.parsing;

import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.springframework.beans.factory.parsing.ParseState;
import org.springframework.util.StringUtils;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/parsing/QualifierEntry.class */
public class QualifierEntry implements ParseState.Entry {
    private String typeName;

    public QualifierEntry(String typeName) {
        if (!StringUtils.hasText(typeName)) {
            throw new IllegalArgumentException("Invalid qualifier type '" + typeName + "'.");
        }
        this.typeName = typeName;
    }

    public String toString() {
        return "Qualifier '" + this.typeName + OperatorName.SHOW_TEXT_LINE;
    }
}
