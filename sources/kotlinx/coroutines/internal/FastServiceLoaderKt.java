package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;

/* compiled from: FastServiceLoader.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\"\u0014\u0010��\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"ANDROID_DETECTED", "", "getANDROID_DETECTED", "()Z", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/FastServiceLoaderKt.class */
public final class FastServiceLoaderKt {
    private static final boolean ANDROID_DETECTED;

    public static final boolean getANDROID_DETECTED() {
        return ANDROID_DETECTED;
    }

    static {
        Object objM2105constructorimpl;
        try {
            Result.Companion companion = Result.Companion;
            objM2105constructorimpl = Result.m2105constructorimpl(Class.forName("android.os.Build"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
        }
        ANDROID_DETECTED = Result.m2100isSuccessimpl(objM2105constructorimpl);
    }
}
