package org.jsoup.internal;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;

@TypeQualifierDefault({ElementType.FIELD})
@Nonnull
@Documented
@Retention(RetentionPolicy.CLASS)
/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/internal/FieldsAreNonnullByDefault.class */
public @interface FieldsAreNonnullByDefault {
}
