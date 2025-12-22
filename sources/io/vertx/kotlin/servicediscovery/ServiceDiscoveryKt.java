package io.vertx.kotlin.servicediscovery;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.spi.ServiceExporter;
import io.vertx.servicediscovery.spi.ServiceImporter;
import java.util.List;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ServiceDiscovery.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��F\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a+\u0010��\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a3\u0010��\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a\u001f\u0010��\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a/\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\f*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a7\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\f*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a#\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\f*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\u001d\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u001a%\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0015\u001a%\u0010\u0016\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0019\u001a\u001d\u0010\u001a\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001cH\u0086@ø\u0001��¢\u0006\u0002\u0010\u001d\u001a\u001d\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"getRecordAwait", "Lio/vertx/servicediscovery/Record;", "Lio/vertx/servicediscovery/ServiceDiscovery;", "filter", "Lkotlin/Function1;", "", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "includeOutOfService", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lkotlin/jvm/functions/Function1;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/vertx/core/json/JsonObject;", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecordsAwait", "", "publishAwait", "record", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lio/vertx/servicediscovery/Record;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerServiceExporterAwait", "", "exporter", "Lio/vertx/servicediscovery/spi/ServiceExporter;", "configuration", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lio/vertx/servicediscovery/spi/ServiceExporter;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerServiceImporterAwait", "importer", "Lio/vertx/servicediscovery/spi/ServiceImporter;", "(Lio/vertx/servicediscovery/ServiceDiscovery;Lio/vertx/servicediscovery/spi/ServiceImporter;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unpublishAwait", "id", "", "(Lio/vertx/servicediscovery/ServiceDiscovery;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/ServiceDiscoveryKt.class */
public final class ServiceDiscoveryKt {
    @Nullable
    public static final Object registerServiceImporterAwait(@NotNull final ServiceDiscovery $this$registerServiceImporterAwait, @NotNull final ServiceImporter importer, @NotNull final JsonObject configuration, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt.registerServiceImporterAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$registerServiceImporterAwait.registerServiceImporter(importer, configuration, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt.registerServiceImporterAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object registerServiceExporterAwait(@NotNull final ServiceDiscovery $this$registerServiceExporterAwait, @NotNull final ServiceExporter exporter, @NotNull final JsonObject configuration, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt.registerServiceExporterAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$registerServiceExporterAwait.registerServiceExporter(exporter, configuration, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt.registerServiceExporterAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object publishAwait(@NotNull final ServiceDiscovery $this$publishAwait, @NotNull final Record record, @NotNull Continuation<? super Record> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Record>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt.publishAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Record>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Record>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$publishAwait.publish(record, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object unpublishAwait(@NotNull final ServiceDiscovery $this$unpublishAwait, @NotNull final String id, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt.unpublishAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$unpublishAwait.unpublish(id, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt.unpublishAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object getRecordAwait(@NotNull final ServiceDiscovery $this$getRecordAwait, @NotNull final JsonObject filter, @NotNull Continuation<? super Record> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Record>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt.getRecordAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Record>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Record>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getRecordAwait.getRecord(filter, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getRecordAwait(@NotNull final ServiceDiscovery $this$getRecordAwait, @NotNull final Function1<? super Record, Boolean> function1, @NotNull Continuation<? super Record> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Record>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt.getRecordAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Record>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: ServiceDiscovery.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0018\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010��\u001a\u00020\u00012<\u0010\u0002\u001a8\u0012\u0006\u0012\u0004\u0018\u00010\u0004 \b*\u001b\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "p1", "Lio/vertx/core/AsyncResult;", "Lio/vertx/servicediscovery/Record;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"})
            /* renamed from: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt$getRecordAwait$4$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/ServiceDiscoveryKt$getRecordAwait$4$1.class */
            static final /* synthetic */ class AnonymousClass1 extends FunctionReference implements Function1<AsyncResult<Record>, Unit> {
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AsyncResult<Record> asyncResult) {
                    invoke2(asyncResult);
                    return Unit.INSTANCE;
                }

                @Override // kotlin.jvm.internal.CallableReference
                public final KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinClass(Handler.class);
                }

                @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
                public final String getName() {
                    return "handle";
                }

                @Override // kotlin.jvm.internal.CallableReference
                public final String getSignature() {
                    return "handle(Ljava/lang/Object;)V";
                }

                AnonymousClass1(Handler handler) {
                    super(1, handler);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AsyncResult<Record> asyncResult) {
                    ((Handler) this.receiver).handle(asyncResult);
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Record>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                ServiceDiscovery serviceDiscovery = $this$getRecordAwait;
                Function1 function12 = function1;
                Object serviceDiscoveryKt$sam$java_util_function_Function$0 = function12;
                if (function12 != null) {
                    serviceDiscoveryKt$sam$java_util_function_Function$0 = new ServiceDiscoveryKt$sam$java_util_function_Function$0(function12);
                }
                serviceDiscovery.getRecord((Function) serviceDiscoveryKt$sam$java_util_function_Function$0, new ServiceDiscoveryKt$sam$io_vertx_core_Handler$0(new AnonymousClass1(it)));
            }
        }, continuation);
    }

    @Nullable
    public static final Object getRecordAwait(@NotNull final ServiceDiscovery $this$getRecordAwait, @NotNull final Function1<? super Record, Boolean> function1, final boolean includeOutOfService, @NotNull Continuation<? super Record> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Record>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt.getRecordAwait.6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Record>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: ServiceDiscovery.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0018\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010��\u001a\u00020\u00012<\u0010\u0002\u001a8\u0012\u0006\u0012\u0004\u0018\u00010\u0004 \b*\u001b\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "p1", "Lio/vertx/core/AsyncResult;", "Lio/vertx/servicediscovery/Record;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"})
            /* renamed from: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt$getRecordAwait$6$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/ServiceDiscoveryKt$getRecordAwait$6$1.class */
            static final /* synthetic */ class AnonymousClass1 extends FunctionReference implements Function1<AsyncResult<Record>, Unit> {
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AsyncResult<Record> asyncResult) {
                    invoke2(asyncResult);
                    return Unit.INSTANCE;
                }

                @Override // kotlin.jvm.internal.CallableReference
                public final KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinClass(Handler.class);
                }

                @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
                public final String getName() {
                    return "handle";
                }

                @Override // kotlin.jvm.internal.CallableReference
                public final String getSignature() {
                    return "handle(Ljava/lang/Object;)V";
                }

                AnonymousClass1(Handler handler) {
                    super(1, handler);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AsyncResult<Record> asyncResult) {
                    ((Handler) this.receiver).handle(asyncResult);
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Record>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                ServiceDiscovery serviceDiscovery = $this$getRecordAwait;
                Function1 function12 = function1;
                Object serviceDiscoveryKt$sam$java_util_function_Function$0 = function12;
                if (function12 != null) {
                    serviceDiscoveryKt$sam$java_util_function_Function$0 = new ServiceDiscoveryKt$sam$java_util_function_Function$0(function12);
                }
                serviceDiscovery.getRecord((Function) serviceDiscoveryKt$sam$java_util_function_Function$0, includeOutOfService, new ServiceDiscoveryKt$sam$io_vertx_core_Handler$0(new AnonymousClass1(it)));
            }
        }, continuation);
    }

    @Nullable
    public static final Object getRecordsAwait(@NotNull final ServiceDiscovery $this$getRecordsAwait, @NotNull final JsonObject filter, @NotNull Continuation<? super List<? extends Record>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends Record>>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt.getRecordsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends Record>>> handler) {
                invoke2((Handler<AsyncResult<List<Record>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<Record>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getRecordsAwait.getRecords(filter, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getRecordsAwait(@NotNull final ServiceDiscovery $this$getRecordsAwait, @NotNull final Function1<? super Record, Boolean> function1, @NotNull Continuation<? super List<? extends Record>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends Record>>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt.getRecordsAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends Record>>> handler) {
                invoke2((Handler<AsyncResult<List<Record>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: ServiceDiscovery.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u001c\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010��\u001a\u00020\u00012D\u0010\u0002\u001a@\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004 \t*\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b0\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "", "p1", "Lio/vertx/core/AsyncResult;", "", "Lio/vertx/servicediscovery/Record;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"})
            /* renamed from: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt$getRecordsAwait$4$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/ServiceDiscoveryKt$getRecordsAwait$4$1.class */
            static final /* synthetic */ class AnonymousClass1 extends FunctionReference implements Function1<AsyncResult<List<? extends Record>>, Unit> {
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AsyncResult<List<? extends Record>> asyncResult) {
                    invoke2((AsyncResult<List<Record>>) asyncResult);
                    return Unit.INSTANCE;
                }

                @Override // kotlin.jvm.internal.CallableReference
                public final KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinClass(Handler.class);
                }

                @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
                public final String getName() {
                    return "handle";
                }

                @Override // kotlin.jvm.internal.CallableReference
                public final String getSignature() {
                    return "handle(Ljava/lang/Object;)V";
                }

                AnonymousClass1(Handler handler) {
                    super(1, handler);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AsyncResult<List<Record>> asyncResult) {
                    ((Handler) this.receiver).handle(asyncResult);
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<Record>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                ServiceDiscovery serviceDiscovery = $this$getRecordsAwait;
                Function1 function12 = function1;
                Object serviceDiscoveryKt$sam$java_util_function_Function$0 = function12;
                if (function12 != null) {
                    serviceDiscoveryKt$sam$java_util_function_Function$0 = new ServiceDiscoveryKt$sam$java_util_function_Function$0(function12);
                }
                serviceDiscovery.getRecords((Function) serviceDiscoveryKt$sam$java_util_function_Function$0, new ServiceDiscoveryKt$sam$io_vertx_core_Handler$0(new AnonymousClass1(it)));
            }
        }, continuation);
    }

    @Nullable
    public static final Object getRecordsAwait(@NotNull final ServiceDiscovery $this$getRecordsAwait, @NotNull final Function1<? super Record, Boolean> function1, final boolean includeOutOfService, @NotNull Continuation<? super List<? extends Record>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends Record>>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt.getRecordsAwait.6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends Record>>> handler) {
                invoke2((Handler<AsyncResult<List<Record>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* compiled from: ServiceDiscovery.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u001c\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010��\u001a\u00020\u00012D\u0010\u0002\u001a@\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004 \t*\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b0\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "", "p1", "Lio/vertx/core/AsyncResult;", "", "Lio/vertx/servicediscovery/Record;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "invoke"})
            /* renamed from: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt$getRecordsAwait$6$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/ServiceDiscoveryKt$getRecordsAwait$6$1.class */
            static final /* synthetic */ class AnonymousClass1 extends FunctionReference implements Function1<AsyncResult<List<? extends Record>>, Unit> {
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AsyncResult<List<? extends Record>> asyncResult) {
                    invoke2((AsyncResult<List<Record>>) asyncResult);
                    return Unit.INSTANCE;
                }

                @Override // kotlin.jvm.internal.CallableReference
                public final KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinClass(Handler.class);
                }

                @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
                public final String getName() {
                    return "handle";
                }

                @Override // kotlin.jvm.internal.CallableReference
                public final String getSignature() {
                    return "handle(Ljava/lang/Object;)V";
                }

                AnonymousClass1(Handler handler) {
                    super(1, handler);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AsyncResult<List<Record>> asyncResult) {
                    ((Handler) this.receiver).handle(asyncResult);
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<Record>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                ServiceDiscovery serviceDiscovery = $this$getRecordsAwait;
                Function1 function12 = function1;
                Object serviceDiscoveryKt$sam$java_util_function_Function$0 = function12;
                if (function12 != null) {
                    serviceDiscoveryKt$sam$java_util_function_Function$0 = new ServiceDiscoveryKt$sam$java_util_function_Function$0(function12);
                }
                serviceDiscovery.getRecords((Function) serviceDiscoveryKt$sam$java_util_function_Function$0, includeOutOfService, new ServiceDiscoveryKt$sam$io_vertx_core_Handler$0(new AnonymousClass1(it)));
            }
        }, continuation);
    }

    @Nullable
    public static final Object updateAwait(@NotNull final ServiceDiscovery $this$updateAwait, @NotNull final Record record, @NotNull Continuation<? super Record> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Record>>, Unit>() { // from class: io.vertx.kotlin.servicediscovery.ServiceDiscoveryKt.updateAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Record>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Record>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$updateAwait.update(record, it);
            }
        }, continuation);
    }
}
