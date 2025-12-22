package org.springframework.beans.propertyeditors;

import java.beans.PropertyEditorSupport;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/propertyeditors/CharArrayPropertyEditor.class */
public class CharArrayPropertyEditor extends PropertyEditorSupport {
    public void setAsText(@Nullable String text) {
        setValue(text != null ? text.toCharArray() : null);
    }

    public String getAsText() {
        char[] value = (char[]) getValue();
        return value != null ? new String(value) : "";
    }
}
