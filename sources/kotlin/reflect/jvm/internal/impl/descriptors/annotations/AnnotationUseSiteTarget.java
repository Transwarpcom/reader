package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: AnnotationUseSiteTarget.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationUseSiteTarget.class */
public enum AnnotationUseSiteTarget {
    FIELD(null, 1, null),
    FILE(null, 1, null),
    PROPERTY(null, 1, null),
    PROPERTY_GETTER(BeanUtil.PREFIX_GETTER_GET),
    PROPERTY_SETTER("set"),
    RECEIVER(null, 1, null),
    CONSTRUCTOR_PARAMETER("param"),
    SETTER_PARAMETER("setparam"),
    PROPERTY_DELEGATE_FIELD("delegate");


    @NotNull
    private final String renderName;

    AnnotationUseSiteTarget(String renderName) {
        this.renderName = renderName == null ? CapitalizeDecapitalizeKt.toLowerCaseAsciiOnly(name()) : renderName;
    }

    /* synthetic */ AnnotationUseSiteTarget(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str);
    }

    @NotNull
    public final String getRenderName() {
        return this.renderName;
    }
}
