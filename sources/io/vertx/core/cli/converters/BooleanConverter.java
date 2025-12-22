package io.vertx.core.cli.converters;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/converters/BooleanConverter.class */
public final class BooleanConverter implements Converter<Boolean> {
    public static final BooleanConverter INSTANCE = new BooleanConverter();
    private static final List<String> TRUE = Arrays.asList("true", CustomBooleanEditor.VALUE_YES, "on", CustomBooleanEditor.VALUE_1);

    private BooleanConverter() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.cli.converters.Converter
    public Boolean fromString(String value) {
        return Boolean.valueOf(value != null && TRUE.contains(value.toLowerCase()));
    }
}
