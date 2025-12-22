package kotlin.reflect.full;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KParameter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KCallables.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��0\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\u001a9\u0010\u000f\u001a\u0002H\u0010\"\u0004\b��\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u00022\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00130\u0012\"\u0004\u0018\u00010\u0013H\u0087@ø\u0001��¢\u0006\u0002\u0010\u0014\u001a7\u0010\u0015\u001a\u0002H\u0010\"\u0004\b��\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u00022\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0016H\u0087@ø\u0001��¢\u0006\u0002\u0010\u0017\u001a\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0007\"$\u0010��\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"$\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\u0006\"(\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\f\u0010\u0004\u001a\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"extensionReceiverParameter", "Lkotlin/reflect/KParameter;", "Lkotlin/reflect/KCallable;", "getExtensionReceiverParameter$annotations", "(Lkotlin/reflect/KCallable;)V", "getExtensionReceiverParameter", "(Lkotlin/reflect/KCallable;)Lkotlin/reflect/KParameter;", "instanceParameter", "getInstanceParameter$annotations", "getInstanceParameter", "valueParameters", "", "getValueParameters$annotations", "getValueParameters", "(Lkotlin/reflect/KCallable;)Ljava/util/List;", "callSuspend", "R", "args", "", "", "(Lkotlin/reflect/KCallable;[Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callSuspendBy", "", "(Lkotlin/reflect/KCallable;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findParameterByName", "name", "", "kotlin-reflection"})
@JvmName(name = "KCallables")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/full/KCallables.class */
public final class KCallables {

    /* compiled from: KCallables.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, d1 = {"��\u0018\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n��\n\u0002\u0018\u0002\u0010��\u001a\u0004\u0018\u00010\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0016\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0005\"\u0004\u0018\u00010\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0087@"}, d2 = {"callSuspend", "", "R", "Lkotlin/reflect/KCallable;", "args", "", "continuation", "Lkotlin/coroutines/Continuation;"})
    @DebugMetadata(f = "KCallables.kt", l = {55}, i = {}, s = {}, n = {}, m = "callSuspend", c = "kotlin.reflect.full.KCallables")
    /* renamed from: kotlin.reflect.full.KCallables$callSuspend$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/full/KCallables$callSuspend$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        /* synthetic */ Object result;
        int label;
        Object L$0;
        Object L$1;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return KCallables.callSuspend(null, null, this);
        }

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }
    }

    /* compiled from: KCallables.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, d1 = {"��\u001c\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u0004\u0018\u00010\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0087@"}, d2 = {"callSuspendBy", "", "R", "Lkotlin/reflect/KCallable;", "args", "", "Lkotlin/reflect/KParameter;", "continuation", "Lkotlin/coroutines/Continuation;"})
    @DebugMetadata(f = "KCallables.kt", l = {73}, i = {}, s = {}, n = {}, m = "callSuspendBy", c = "kotlin.reflect.full.KCallables")
    /* renamed from: kotlin.reflect.full.KCallables$callSuspendBy$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/full/KCallables$callSuspendBy$1.class */
    static final class C14991 extends ContinuationImpl {
        /* synthetic */ Object result;
        int label;
        Object L$0;
        Object L$1;
        Object L$2;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return KCallables.callSuspendBy(null, null, this);
        }

        C14991(Continuation continuation) {
            super(continuation);
        }
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getInstanceParameter$annotations(KCallable kCallable) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getExtensionReceiverParameter$annotations(KCallable kCallable) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getValueParameters$annotations(KCallable kCallable) {
    }

    @Nullable
    public static final KParameter getInstanceParameter(@NotNull KCallable<?> instanceParameter) {
        Object obj;
        Intrinsics.checkNotNullParameter(instanceParameter, "$this$instanceParameter");
        Iterable $this$singleOrNull$iv = instanceParameter.getParameters();
        Object single$iv = null;
        boolean found$iv = false;
        Iterator it = $this$singleOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object element$iv = it.next();
                KParameter it2 = (KParameter) element$iv;
                if (it2.getKind() == KParameter.Kind.INSTANCE) {
                    if (found$iv) {
                        obj = null;
                        break;
                    }
                    single$iv = element$iv;
                    found$iv = true;
                }
            } else {
                obj = !found$iv ? null : single$iv;
            }
        }
        return (KParameter) obj;
    }

    @Nullable
    public static final KParameter getExtensionReceiverParameter(@NotNull KCallable<?> extensionReceiverParameter) {
        Object obj;
        Intrinsics.checkNotNullParameter(extensionReceiverParameter, "$this$extensionReceiverParameter");
        Iterable $this$singleOrNull$iv = extensionReceiverParameter.getParameters();
        Object single$iv = null;
        boolean found$iv = false;
        Iterator it = $this$singleOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object element$iv = it.next();
                KParameter it2 = (KParameter) element$iv;
                if (it2.getKind() == KParameter.Kind.EXTENSION_RECEIVER) {
                    if (found$iv) {
                        obj = null;
                        break;
                    }
                    single$iv = element$iv;
                    found$iv = true;
                }
            } else {
                obj = !found$iv ? null : single$iv;
            }
        }
        return (KParameter) obj;
    }

    @NotNull
    public static final List<KParameter> getValueParameters(@NotNull KCallable<?> valueParameters) {
        Intrinsics.checkNotNullParameter(valueParameters, "$this$valueParameters");
        Iterable $this$filter$iv = valueParameters.getParameters();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            KParameter it = (KParameter) element$iv$iv;
            if (it.getKind() == KParameter.Kind.VALUE) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final KParameter findParameterByName(@NotNull KCallable<?> findParameterByName, @NotNull String name) {
        Object obj;
        Intrinsics.checkNotNullParameter(findParameterByName, "$this$findParameterByName");
        Intrinsics.checkNotNullParameter(name, "name");
        Iterable $this$singleOrNull$iv = findParameterByName.getParameters();
        Object single$iv = null;
        boolean found$iv = false;
        Iterator it = $this$singleOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object element$iv = it.next();
                KParameter it2 = (KParameter) element$iv;
                if (Intrinsics.areEqual(it2.getName(), name)) {
                    if (found$iv) {
                        obj = null;
                        break;
                    }
                    single$iv = element$iv;
                    found$iv = true;
                }
            } else {
                obj = !found$iv ? null : single$iv;
            }
        }
        return (KParameter) obj;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @kotlin.SinceKotlin(version = "1.3")
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <R> java.lang.Object callSuspend(@org.jetbrains.annotations.NotNull kotlin.reflect.KCallable<? extends R> r5, @org.jetbrains.annotations.NotNull java.lang.Object[] r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super R> r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.full.KCallables.callSuspend(kotlin.reflect.KCallable, java.lang.Object[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Type inference failed for: r1v6, types: [T, kotlin.reflect.jvm.internal.KCallableImpl] */
    @kotlin.SinceKotlin(version = "1.3")
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <R> java.lang.Object callSuspendBy(@org.jetbrains.annotations.NotNull kotlin.reflect.KCallable<? extends R> r6, @org.jetbrains.annotations.NotNull java.util.Map<kotlin.reflect.KParameter, ? extends java.lang.Object> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super R> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 369
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.full.KCallables.callSuspendBy(kotlin.reflect.KCallable, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
