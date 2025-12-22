package kotlinx.coroutines;

import java.lang.Throwable;
import kotlin.Metadata;
import kotlinx.coroutines.CopyableThrowable;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.Nullable;

/* compiled from: Debug.common.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0003\n\u0002\u0010��\n\u0002\b\u0003\bg\u0018��*\u0012\b��\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010��2\u00020\u0003J\u000f\u0010\u0004\u001a\u0004\u0018\u00018��H&¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/CopyableThrowable;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "", "createCopy", "()Ljava/lang/Throwable;", "kotlinx-coroutines-core"})
@ExperimentalCoroutinesApi
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/CopyableThrowable.class */
public interface CopyableThrowable<T extends Throwable & CopyableThrowable<T>> {
    @Nullable
    T createCopy();
}
