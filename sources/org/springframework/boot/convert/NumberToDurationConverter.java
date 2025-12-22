package org.springframework.boot.convert;

import java.time.Duration;
import java.util.Collections;
import java.util.Set;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/convert/NumberToDurationConverter.class */
final class NumberToDurationConverter implements GenericConverter {
    private final StringToDurationConverter delegate = new StringToDurationConverter();

    NumberToDurationConverter() {
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(Number.class, Duration.class));
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        return this.delegate.convert(source != null ? source.toString() : null, TypeDescriptor.valueOf(String.class), targetType);
    }
}
