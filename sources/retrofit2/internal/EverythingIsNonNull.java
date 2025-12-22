package retrofit2.internal;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;

@TypeQualifierDefault({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Nonnull
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/internal/EverythingIsNonNull.class */
public @interface EverythingIsNonNull {
}
