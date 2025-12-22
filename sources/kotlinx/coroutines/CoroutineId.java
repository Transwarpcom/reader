package kotlinx.coroutines;

import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: CoroutineContext.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0080\b\u0018�� \u00182\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0018B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\t\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\n\u001a\u00020��2\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0002H\u0016J\b\u0010\u0016\u001a\u00020\u0002H\u0016J\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/CoroutineId;", "Lkotlinx/coroutines/ThreadContextElement;", "", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "id", "", "(J)V", "getId", "()J", "component1", "copy", "equals", "", "other", "", IdentityNamingStrategy.HASH_CODE_KEY, "", "restoreThreadContext", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "oldState", "toString", "updateThreadContext", PDAnnotationText.NAME_KEY, "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/CoroutineId.class */
public final class CoroutineId extends AbstractCoroutineContextElement implements ThreadContextElement<String> {

    @NotNull
    public static final Key Key = new Key(null);
    private final long id;

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final CoroutineId copy(long id) {
        return new CoroutineId(id);
    }

    public static /* synthetic */ CoroutineId copy$default(CoroutineId coroutineId, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = coroutineId.id;
        }
        return coroutineId.copy(j);
    }

    public int hashCode() {
        return Long.hashCode(this.id);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof CoroutineId) && this.id == ((CoroutineId) other).id;
    }

    public final long getId() {
        return this.id;
    }

    /* compiled from: CoroutineContext.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/CoroutineId$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/CoroutineId;", "()V", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/CoroutineId$Key.class */
    public static final class Key implements CoroutineContext.Key<CoroutineId> {
        public /* synthetic */ Key(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Key() {
        }
    }

    public CoroutineId(long id) {
        super(Key);
        this.id = id;
    }

    @NotNull
    public String toString() {
        return "CoroutineId(" + this.id + ')';
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlinx.coroutines.ThreadContextElement
    @NotNull
    /* renamed from: updateThreadContext */
    public String updateThreadContext2(@NotNull CoroutineContext context) {
        String str;
        CoroutineName coroutineName = (CoroutineName) context.get(CoroutineName.Key);
        if (coroutineName == null) {
            str = "coroutine";
        } else {
            String name = coroutineName.getName();
            str = name == null ? "coroutine" : name;
        }
        String coroutineName2 = str;
        Thread currentThread = Thread.currentThread();
        String oldName = currentThread.getName();
        int lastIndex = StringsKt.lastIndexOf$default((CharSequence) oldName, " @", 0, false, 6, (Object) null);
        if (lastIndex < 0) {
            lastIndex = oldName.length();
        }
        StringBuilder $this$updateThreadContext_u24lambda_u2d0 = new StringBuilder(lastIndex + coroutineName2.length() + 10);
        int i = lastIndex;
        if (oldName == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = oldName.substring(0, i);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        $this$updateThreadContext_u24lambda_u2d0.append(strSubstring);
        $this$updateThreadContext_u24lambda_u2d0.append(" @");
        $this$updateThreadContext_u24lambda_u2d0.append(coroutineName2);
        $this$updateThreadContext_u24lambda_u2d0.append('#');
        $this$updateThreadContext_u24lambda_u2d0.append(getId());
        Unit unit = Unit.INSTANCE;
        String string = $this$updateThreadContext_u24lambda_u2d0.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        currentThread.setName(string);
        return oldName;
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    public void restoreThreadContext(@NotNull CoroutineContext context, @NotNull String oldState) {
        Thread.currentThread().setName(oldState);
    }
}
