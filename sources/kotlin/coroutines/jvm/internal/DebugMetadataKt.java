package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DebugMetadata.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��0\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0002\u001a\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\bH\u0002\u001a\f\u0010\t\u001a\u00020\u0001*\u00020\bH\u0002\u001a\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b*\u00020\bH\u0001¢\u0006\u0002\u0010\r\u001a\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\bH\u0001¢\u0006\u0002\b\u0010\"\u000e\u0010��\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��¨\u0006\u0011"}, d2 = {"COROUTINES_DEBUG_METADATA_VERSION", "", "checkDebugMetadataVersion", "", "expected", "actual", "getDebugMetadataAnnotation", "Lkotlin/coroutines/jvm/internal/DebugMetadata;", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "getLabel", "getSpilledVariableFieldMapping", "", "", "(Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;)[Ljava/lang/String;", "getStackTraceElementImpl", "Ljava/lang/StackTraceElement;", "getStackTraceElement", "kotlin-stdlib"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/coroutines/jvm/internal/DebugMetadataKt.class */
public final class DebugMetadataKt {
    private static final int COROUTINES_DEBUG_METADATA_VERSION = 1;

    @SinceKotlin(version = "1.3")
    @JvmName(name = "getStackTraceElement")
    @Nullable
    public static final StackTraceElement getStackTraceElement(@NotNull BaseContinuationImpl getStackTraceElementImpl) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(getStackTraceElementImpl, "$this$getStackTraceElementImpl");
        DebugMetadata debugMetadata = getDebugMetadataAnnotation(getStackTraceElementImpl);
        if (debugMetadata == null) {
            return null;
        }
        checkDebugMetadataVersion(1, debugMetadata.v());
        int label = getLabel(getStackTraceElementImpl);
        int lineNumber = label < 0 ? -1 : debugMetadata.l()[label];
        String moduleName = ModuleNameRetriever.INSTANCE.getModuleName(getStackTraceElementImpl);
        String moduleAndClass = moduleName == null ? debugMetadata.c() : moduleName + '/' + debugMetadata.c();
        return new StackTraceElement(moduleAndClass, debugMetadata.m(), debugMetadata.f(), lineNumber);
    }

    private static final DebugMetadata getDebugMetadataAnnotation(BaseContinuationImpl $this$getDebugMetadataAnnotation) {
        return (DebugMetadata) $this$getDebugMetadataAnnotation.getClass().getAnnotation(DebugMetadata.class);
    }

    private static final int getLabel(BaseContinuationImpl $this$getLabel) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        int iIntValue;
        try {
            Field field = $this$getLabel.getClass().getDeclaredField("label");
            Intrinsics.checkNotNullExpressionValue(field, "field");
            field.setAccessible(true);
            Object obj = field.get($this$getLabel);
            if (!(obj instanceof Integer)) {
                obj = null;
            }
            Integer num = (Integer) obj;
            iIntValue = (num != null ? num.intValue() : 0) - 1;
        } catch (Exception e) {
            iIntValue = -1;
        }
        return iIntValue;
    }

    private static final void checkDebugMetadataVersion(int expected, int actual) {
        if (actual > expected) {
            throw new IllegalStateException(("Debug metadata version mismatch. Expected: " + expected + ", got " + actual + ". Please update the Kotlin standard library.").toString());
        }
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "getSpilledVariableFieldMapping")
    @Nullable
    public static final String[] getSpilledVariableFieldMapping(@NotNull BaseContinuationImpl getSpilledVariableFieldMapping) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(getSpilledVariableFieldMapping, "$this$getSpilledVariableFieldMapping");
        DebugMetadata debugMetadata = getDebugMetadataAnnotation(getSpilledVariableFieldMapping);
        if (debugMetadata == null) {
            return null;
        }
        checkDebugMetadataVersion(1, debugMetadata.v());
        ArrayList res = new ArrayList();
        int label = getLabel(getSpilledVariableFieldMapping);
        int[] iArrI = debugMetadata.i();
        int length = iArrI.length;
        for (int i = 0; i < length; i++) {
            int labelOfIndex = iArrI[i];
            if (labelOfIndex == label) {
                res.add(debugMetadata.s()[i]);
                res.add(debugMetadata.n()[i]);
            }
        }
        ArrayList $this$toTypedArray$iv = res;
        Object[] array = $this$toTypedArray$iv.toArray(new String[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return (String[]) array;
    }
}
