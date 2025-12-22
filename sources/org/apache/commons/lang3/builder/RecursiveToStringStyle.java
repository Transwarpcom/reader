package org.apache.commons.lang3.builder;

import java.util.Collection;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/builder/RecursiveToStringStyle.class */
public class RecursiveToStringStyle extends ToStringStyle {
    private static final long serialVersionUID = 1;

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer buffer, String fieldName, Object value) {
        if (!ClassUtils.isPrimitiveWrapper(value.getClass()) && !String.class.equals(value.getClass()) && accept(value.getClass())) {
            buffer.append(ReflectionToStringBuilder.toString(value, this));
        } else {
            super.appendDetail(buffer, fieldName, value);
        }
    }

    @Override // org.apache.commons.lang3.builder.ToStringStyle
    protected void appendDetail(StringBuffer buffer, String fieldName, Collection<?> coll) {
        appendClassName(buffer, coll);
        appendIdentityHashCode(buffer, coll);
        appendDetail(buffer, fieldName, coll.toArray());
    }

    protected boolean accept(Class<?> clazz) {
        return true;
    }
}
