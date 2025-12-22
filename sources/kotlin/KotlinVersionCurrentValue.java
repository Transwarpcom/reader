package kotlin;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinVersion.kt */
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\bÂ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lkotlin/KotlinVersionCurrentValue;", "", "()V", BeanUtil.PREFIX_GETTER_GET, "Lkotlin/KotlinVersion;", "kotlin-stdlib"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/KotlinVersionCurrentValue.class */
final class KotlinVersionCurrentValue {

    @NotNull
    public static final KotlinVersionCurrentValue INSTANCE = new KotlinVersionCurrentValue();

    private KotlinVersionCurrentValue() {
    }

    @JvmStatic
    @NotNull
    public static final KotlinVersion get() {
        return new KotlinVersion(1, 5, 21);
    }
}
