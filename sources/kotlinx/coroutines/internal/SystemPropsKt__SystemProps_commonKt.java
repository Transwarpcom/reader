package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SystemProps.common.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 48, d1 = {"��\u001c\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n��\u001a\u0018\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H��\u001a,\u0010��\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005H��\u001a,\u0010��\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\b2\b\b\u0002\u0010\u0006\u001a\u00020\b2\b\b\u0002\u0010\u0007\u001a\u00020\bH��¨\u0006\t"}, d2 = {"systemProp", "", "propertyName", "", "defaultValue", "", "minValue", "maxValue", "", "kotlinx-coroutines-core"}, xs = "kotlinx/coroutines/internal/SystemPropsKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/SystemPropsKt__SystemProps_commonKt.class */
public final /* synthetic */ class SystemPropsKt__SystemProps_commonKt {
    public static final boolean systemProp(@NotNull String propertyName, boolean defaultValue) {
        String strSystemProp = SystemPropsKt.systemProp(propertyName);
        return strSystemProp == null ? defaultValue : Boolean.parseBoolean(strSystemProp);
    }

    public static /* synthetic */ int systemProp$default(String str, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 1;
        }
        if ((i4 & 8) != 0) {
            i3 = Integer.MAX_VALUE;
        }
        return SystemPropsKt.systemProp(str, i, i2, i3);
    }

    public static final int systemProp(@NotNull String propertyName, int defaultValue, int minValue, int maxValue) {
        return (int) SystemPropsKt.systemProp(propertyName, defaultValue, minValue, maxValue);
    }

    public static /* synthetic */ long systemProp$default(String str, long j, long j2, long j3, int i, Object obj) {
        if ((i & 4) != 0) {
            j2 = 1;
        }
        if ((i & 8) != 0) {
            j3 = Long.MAX_VALUE;
        }
        return SystemPropsKt.systemProp(str, j, j2, j3);
    }

    public static final long systemProp(@NotNull String propertyName, long defaultValue, long minValue, long maxValue) {
        String value = SystemPropsKt.systemProp(propertyName);
        if (value == null) {
            return defaultValue;
        }
        Long longOrNull = StringsKt.toLongOrNull(value);
        if (longOrNull != null) {
            long parsed = longOrNull.longValue();
            boolean z = minValue <= parsed && parsed <= maxValue;
            if (!z) {
                throw new IllegalStateException(("System property '" + propertyName + "' should be in range " + minValue + ".." + maxValue + ", but is '" + parsed + '\'').toString());
            }
            return parsed;
        }
        throw new IllegalStateException(("System property '" + propertyName + "' has unrecognized value '" + value + '\'').toString());
    }
}
