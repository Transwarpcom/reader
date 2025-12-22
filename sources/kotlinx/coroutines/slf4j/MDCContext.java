package kotlinx.coroutines.slf4j;

import ch.qos.logback.core.CoreConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.ThreadContextElement;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.MDC;

/* compiled from: MDCContext.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018�� \u00112\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\u0002`\u00040\u00012\u00020\u0005:\u0001\u0011B!\u0012\u001a\b\u0002\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\u0002`\u0004¢\u0006\u0002\u0010\u0007J*\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\u0002`\u0004H\u0016J\"\u0010\u000f\u001a\u00020\u000b2\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\u0002`\u0004H\u0002J\"\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\u0002`\u00042\u0006\u0010\f\u001a\u00020\rH\u0016R#\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002j\u0002`\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/slf4j/MDCContext;", "Lkotlinx/coroutines/ThreadContextElement;", "", "", "Lkotlinx/coroutines/slf4j/MDCContextMap;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "contextMap", "(Ljava/util/Map;)V", "getContextMap", "()Ljava/util/Map;", "restoreThreadContext", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "oldState", "setCurrent", "updateThreadContext", PDAnnotationText.NAME_KEY, "kotlinx-coroutines-slf4j"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-slf4j-1.5.2.jar:kotlinx/coroutines/slf4j/MDCContext.class */
public final class MDCContext extends AbstractCoroutineContextElement implements ThreadContextElement<Map<String, ? extends String>> {

    @NotNull
    public static final Key Key = new Key(null);

    @Nullable
    private final Map<String, String> contextMap;

    public MDCContext() {
        this(null, 1, null);
    }

    public /* synthetic */ MDCContext(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? MDC.getCopyOfContextMap() : map);
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    public /* bridge */ /* synthetic */ void restoreThreadContext(CoroutineContext context, Map<String, ? extends String> map) {
        restoreThreadContext2(context, (Map<String, String>) map);
    }

    @Nullable
    public final Map<String, String> getContextMap() {
        return this.contextMap;
    }

    public MDCContext(@Nullable Map<String, String> map) {
        super(Key);
        this.contextMap = map;
    }

    /* compiled from: MDCContext.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/slf4j/MDCContext$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/slf4j/MDCContext;", "()V", "kotlinx-coroutines-slf4j"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-slf4j-1.5.2.jar:kotlinx/coroutines/slf4j/MDCContext$Key.class */
    public static final class Key implements CoroutineContext.Key<MDCContext> {
        public /* synthetic */ Key(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Key() {
        }
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    @Nullable
    /* renamed from: updateThreadContext, reason: merged with bridge method [inline-methods] */
    public Map<String, ? extends String> updateThreadContext2(@NotNull CoroutineContext context) {
        Map oldState = MDC.getCopyOfContextMap();
        setCurrent(this.contextMap);
        return oldState;
    }

    /* renamed from: restoreThreadContext, reason: avoid collision after fix types in other method */
    public void restoreThreadContext2(@NotNull CoroutineContext context, @Nullable Map<String, String> map) {
        setCurrent(map);
    }

    private final void setCurrent(Map<String, String> map) {
        if (map == null) {
            MDC.clear();
        } else {
            MDC.setContextMap(map);
        }
    }
}
