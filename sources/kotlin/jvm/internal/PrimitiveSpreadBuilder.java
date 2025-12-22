package kotlin.jvm.internal;

import kotlin.Metadata;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;

/* compiled from: PrimitiveSpreadBuilders.kt */
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��$\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\b&\u0018��*\b\b��\u0010\u0001*\u00020\u00022\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028��¢\u0006\u0002\u0010\u0012J\b\u0010\u0003\u001a\u00020\u0004H\u0004J\u001d\u0010\u0013\u001a\u00028��2\u0006\u0010\u0014\u001a\u00028��2\u0006\u0010\u0015\u001a\u00028��H\u0004¢\u0006\u0002\u0010\u0016J\u0011\u0010\u0017\u001a\u00020\u0004*\u00028��H$¢\u0006\u0002\u0010\u0018R\u001a\u0010\u0006\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0005R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u001e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018��0\u000bX\u0082\u0004¢\u0006\n\n\u0002\u0010\u000e\u0012\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "size", "", "(I)V", "position", "getPosition", "()I", "setPosition", "spreads", "", "getSpreads$annotations", "()V", "[Ljava/lang/Object;", "addSpread", "", "spreadArgument", "(Ljava/lang/Object;)V", "toArray", "values", CacheOperationExpressionEvaluator.RESULT_VARIABLE, "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getSize", "(Ljava/lang/Object;)I", "kotlin-stdlib"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/jvm/internal/PrimitiveSpreadBuilder.class */
public abstract class PrimitiveSpreadBuilder<T> {
    private int position;
    private final T[] spreads;
    private final int size;

    protected abstract int getSize(@NotNull T t);

    private static /* synthetic */ void getSpreads$annotations() {
    }

    public PrimitiveSpreadBuilder(int i) {
        this.size = i;
        this.spreads = (T[]) new Object[this.size];
    }

    protected final int getPosition() {
        return this.position;
    }

    protected final void setPosition(int i) {
        this.position = i;
    }

    public final void addSpread(@NotNull T spreadArgument) {
        Intrinsics.checkNotNullParameter(spreadArgument, "spreadArgument");
        T[] tArr = this.spreads;
        int i = this.position;
        this.position = i + 1;
        tArr[i] = spreadArgument;
    }

    protected final int size() {
        int totalLength = 0;
        int i = 0;
        int i2 = this.size - 1;
        if (0 <= i2) {
            while (true) {
                int i3 = totalLength;
                T t = this.spreads[i];
                totalLength = i3 + (t != null ? getSize(t) : 1);
                if (i == i2) {
                    break;
                }
                i++;
            }
        }
        return totalLength;
    }

    @NotNull
    protected final T toArray(@NotNull T values, @NotNull T result) {
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(result, "result");
        int dstIndex = 0;
        int copyValuesFrom = 0;
        int i = 0;
        int i2 = this.size - 1;
        if (0 <= i2) {
            while (true) {
                T t = this.spreads[i];
                if (t != null) {
                    if (copyValuesFrom < i) {
                        System.arraycopy(values, copyValuesFrom, result, dstIndex, i - copyValuesFrom);
                        dstIndex += i - copyValuesFrom;
                    }
                    int spreadSize = getSize(t);
                    System.arraycopy(t, 0, result, dstIndex, spreadSize);
                    dstIndex += spreadSize;
                    copyValuesFrom = i + 1;
                }
                if (i == i2) {
                    break;
                }
                i++;
            }
        }
        if (copyValuesFrom < this.size) {
            System.arraycopy(values, copyValuesFrom, result, dstIndex, this.size - copyValuesFrom);
        }
        return result;
    }
}
