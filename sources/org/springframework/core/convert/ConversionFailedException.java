package org.springframework.core.convert;

import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/convert/ConversionFailedException.class */
public class ConversionFailedException extends ConversionException {

    @Nullable
    private final TypeDescriptor sourceType;
    private final TypeDescriptor targetType;

    @Nullable
    private final Object value;

    public ConversionFailedException(@Nullable TypeDescriptor sourceType, TypeDescriptor targetType, @Nullable Object value, Throwable cause) {
        super("Failed to convert from type [" + sourceType + "] to type [" + targetType + "] for value '" + ObjectUtils.nullSafeToString(value) + OperatorName.SHOW_TEXT_LINE, cause);
        this.sourceType = sourceType;
        this.targetType = targetType;
        this.value = value;
    }

    @Nullable
    public TypeDescriptor getSourceType() {
        return this.sourceType;
    }

    public TypeDescriptor getTargetType() {
        return this.targetType;
    }

    @Nullable
    public Object getValue() {
        return this.value;
    }
}
