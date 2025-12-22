package kotlinx.coroutines.internal;

import kotlin.Metadata;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mozilla.javascript.NativeSymbol;

/* compiled from: Symbol.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\t\b��\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\u001e\u0010\b\u001a\u0002H\t\"\u0004\b��\u0010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0086\b¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/internal/Symbol;", "", NativeSymbol.TYPE_NAME, "", "(Ljava/lang/String;)V", "getSymbol", "()Ljava/lang/String;", "toString", "unbox", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "value", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/Symbol.class */
public final class Symbol {

    @NotNull
    private final String symbol;

    public Symbol(@NotNull String symbol) {
        this.symbol = symbol;
    }

    @NotNull
    public final String getSymbol() {
        return this.symbol;
    }

    @NotNull
    public String toString() {
        return '<' + this.symbol + '>';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> T unbox(@Nullable Object obj) {
        if (obj == this) {
            return null;
        }
        return obj;
    }
}
