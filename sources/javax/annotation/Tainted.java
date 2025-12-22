package javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifierNickname;
import javax.annotation.meta.When;

@TypeQualifierNickname
@Documented
@Untainted(when = When.MAYBE)
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: reader.jar:BOOT-INF/lib/jsr305-3.0.2.jar:javax/annotation/Tainted.class */
public @interface Tainted {
}
