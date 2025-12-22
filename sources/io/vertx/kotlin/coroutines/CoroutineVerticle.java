package io.vertx.kotlin.coroutines;

import ch.qos.logback.core.CoreConstants;
import io.legado.app.constant.Action;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CoroutineVerticle.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018��2\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010!\u001a\u00020 H\u0016J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020 2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0011\u0010%\u001a\u00020#H\u0094@ø\u0001��¢\u0006\u0002\u0010&J\u0018\u0010%\u001a\u00020#2\u000e\u0010'\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010(H\u0016J\u0011\u0010*\u001a\u00020#H\u0094@ø\u0001��¢\u0006\u0002\u0010&J\u0018\u0010*\u001a\u00020#2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010(H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\n\u001a\u00020\u000bX\u0084.¢\u0006\u000e\n��\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u00118VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\t\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\t\u001a\u0004\b\u0017\u0010\u0018R!\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00160\u001b8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\t\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n��\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"Lio/vertx/kotlin/coroutines/CoroutineVerticle;", "Lio/vertx/core/Verticle;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "config", "Lio/vertx/core/json/JsonObject;", "getConfig", "()Lio/vertx/core/json/JsonObject;", "config$delegate", "Lkotlin/Lazy;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lio/vertx/core/Context;", "getContext", "()Lio/vertx/core/Context;", "setContext", "(Lio/vertx/core/Context;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "coroutineContext$delegate", "deploymentID", "", "getDeploymentID", "()Ljava/lang/String;", "deploymentID$delegate", "processArgs", "", "getProcessArgs", "()Ljava/util/List;", "processArgs$delegate", "vertxInstance", "Lio/vertx/core/Vertx;", "getVertx", Action.init, "", "vertx", "start", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startFuture", "Lio/vertx/core/Future;", "Ljava/lang/Void;", Action.stop, "stopFuture", "vertx-lang-kotlin-coroutines"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-coroutines-3.8.5.jar:io/vertx/kotlin/coroutines/CoroutineVerticle.class */
public abstract class CoroutineVerticle implements Verticle, CoroutineScope {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CoroutineVerticle.class), "coroutineContext", "getCoroutineContext()Lkotlin/coroutines/CoroutineContext;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CoroutineVerticle.class), "deploymentID", "getDeploymentID()Ljava/lang/String;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CoroutineVerticle.class), "config", "getConfig()Lio/vertx/core/json/JsonObject;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CoroutineVerticle.class), "processArgs", "getProcessArgs()Ljava/util/List;"))};
    private Vertx vertxInstance;

    @NotNull
    protected Context context;

    @NotNull
    private final Lazy coroutineContext$delegate = LazyKt.lazy(new Function0<CoroutineDispatcher>() { // from class: io.vertx.kotlin.coroutines.CoroutineVerticle$coroutineContext$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final CoroutineDispatcher invoke() {
            return VertxCoroutineKt.dispatcher(this.this$0.getContext());
        }
    });

    @NotNull
    private final Lazy deploymentID$delegate = LazyKt.lazy(new Function0<String>() { // from class: io.vertx.kotlin.coroutines.CoroutineVerticle$deploymentID$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return this.this$0.getContext().deploymentID();
        }
    });

    @NotNull
    private final Lazy config$delegate = LazyKt.lazy(new Function0<JsonObject>() { // from class: io.vertx.kotlin.coroutines.CoroutineVerticle$config$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final JsonObject invoke() {
            return this.this$0.getContext().config();
        }
    });

    @NotNull
    private final Lazy processArgs$delegate = LazyKt.lazy(new Function0<List<String>>() { // from class: io.vertx.kotlin.coroutines.CoroutineVerticle$processArgs$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final List<String> invoke() {
            return this.this$0.getContext().processArgs();
        }
    });

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        Lazy lazy = this.coroutineContext$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (CoroutineContext) lazy.getValue();
    }

    @NotNull
    protected final String getDeploymentID() {
        Lazy lazy = this.deploymentID$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (String) lazy.getValue();
    }

    @NotNull
    protected final JsonObject getConfig() {
        Lazy lazy = this.config$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (JsonObject) lazy.getValue();
    }

    @NotNull
    protected final List<String> getProcessArgs() {
        Lazy lazy = this.processArgs$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (List) lazy.getValue();
    }

    @Nullable
    protected Object start(@NotNull Continuation<? super Unit> continuation) {
        return start$suspendImpl(this, continuation);
    }

    @Nullable
    protected Object stop(@NotNull Continuation<? super Unit> continuation) {
        return stop$suspendImpl(this, continuation);
    }

    @NotNull
    protected final Context getContext() {
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException(CoreConstants.CONTEXT_SCOPE_VALUE);
        }
        return context;
    }

    protected final void setContext(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    @Override // io.vertx.core.Verticle
    public void init(@NotNull Vertx vertx, @NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(vertx, "vertx");
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.vertxInstance = vertx;
        this.context = context;
    }

    @Override // io.vertx.core.Verticle
    @NotNull
    public Vertx getVertx() {
        Vertx vertx = this.vertxInstance;
        if (vertx == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vertxInstance");
        }
        return vertx;
    }

    /* compiled from: CoroutineVerticle.kt */
    @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001��¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"})
    @DebugMetadata(f = "CoroutineVerticle.kt", l = {53}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "io.vertx.kotlin.coroutines.CoroutineVerticle$start$1")
    /* renamed from: io.vertx.kotlin.coroutines.CoroutineVerticle$start$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-coroutines-3.8.5.jar:io/vertx/kotlin/coroutines/CoroutineVerticle$start$1.class */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private CoroutineScope p$;
        int label;
        final /* synthetic */ Future $startFuture;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Future future, Continuation continuation) {
            super(2, continuation);
            this.$startFuture = future;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = CoroutineVerticle.this.new AnonymousClass1(this.$startFuture, completion);
            anonymousClass1.p$ = (CoroutineScope) value;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(result);
                        CoroutineScope coroutineScope = this.p$;
                        CoroutineVerticle coroutineVerticle = CoroutineVerticle.this;
                        this.label = 1;
                        if (coroutineVerticle.start(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure(result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Future future = this.$startFuture;
                if (future != null) {
                    future.complete();
                }
            } catch (Throwable t) {
                Future future2 = this.$startFuture;
                if (future2 != null) {
                    future2.fail(t);
                }
            }
            return Unit.INSTANCE;
        }
    }

    @Override // io.vertx.core.Verticle
    public void start(@Nullable Future<Void> future) {
        BuildersKt__Builders_commonKt.launch$default(this, null, null, new AnonymousClass1(future, null), 3, null);
    }

    /* compiled from: CoroutineVerticle.kt */
    @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001��¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"})
    @DebugMetadata(f = "CoroutineVerticle.kt", l = {64}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "io.vertx.kotlin.coroutines.CoroutineVerticle$stop$1")
    /* renamed from: io.vertx.kotlin.coroutines.CoroutineVerticle$stop$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-coroutines-3.8.5.jar:io/vertx/kotlin/coroutines/CoroutineVerticle$stop$1.class */
    static final class C03931 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private CoroutineScope p$;
        int label;
        final /* synthetic */ Future $stopFuture;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03931(Future future, Continuation continuation) {
            super(2, continuation);
            this.$stopFuture = future;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03931 c03931 = CoroutineVerticle.this.new C03931(this.$stopFuture, completion);
            c03931.p$ = (CoroutineScope) value;
            return c03931;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03931) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(result);
                        CoroutineScope coroutineScope = this.p$;
                        CoroutineVerticle coroutineVerticle = CoroutineVerticle.this;
                        this.label = 1;
                        if (coroutineVerticle.stop(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure(result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Future future = this.$stopFuture;
                if (future != null) {
                    future.complete();
                }
            } catch (Throwable t) {
                Future future2 = this.$stopFuture;
                if (future2 != null) {
                    future2.fail(t);
                }
            }
            return Unit.INSTANCE;
        }
    }

    @Override // io.vertx.core.Verticle
    public void stop(@Nullable Future<Void> future) {
        BuildersKt__Builders_commonKt.launch$default(this, null, null, new C03931(future, null), 3, null);
    }

    static /* synthetic */ Object start$suspendImpl(CoroutineVerticle coroutineVerticle, Continuation $completion) {
        return Unit.INSTANCE;
    }

    static /* synthetic */ Object stop$suspendImpl(CoroutineVerticle coroutineVerticle, Continuation $completion) {
        return Unit.INSTANCE;
    }
}
