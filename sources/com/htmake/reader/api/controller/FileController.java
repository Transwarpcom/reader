package com.htmake.reader.api.controller;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.util.beans.BeanUtil;
import io.vertx.ext.web.RoutingContext;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: FileController.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\t\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J/\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\fJ\u0019\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lcom/htmake/reader/api/controller/FileController;", "Lcom/htmake/reader/api/controller/BaseController;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "checkAccess", "Lcom/htmake/reader/api/ReturnData;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lio/vertx/ext/web/RoutingContext;", "isSave", "", "isDelete", "(Lio/vertx/ext/web/RoutingContext;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "(Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteMulti", "download", "", BeanUtil.PREFIX_GETTER_GET, "importPreview", BeanDefinitionParserDelegate.LIST_ELEMENT, "mkdir", "parse", "restore", "save", "upload", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/FileController.class */
public final class FileController extends BaseController {

    /* compiled from: FileController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "FileController.kt", l = {26}, i = {0, 0, 0, 0, 0}, s = {"L$0", "L$1", "L$2", "Z$0", "Z$1"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData", "isSave", "isDelete"}, m = "checkAccess", c = "com.htmake.reader.api.controller.FileController")
    /* renamed from: com.htmake.reader.api.controller.FileController$checkAccess$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/FileController$checkAccess$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        boolean Z$1;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FileController.this.checkAccess(null, false, false, this);
        }
    }

    /* compiled from: FileController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "FileController.kt", l = {311}, i = {0}, s = {"L$0"}, n = {CoreConstants.CONTEXT_SCOPE_VALUE}, m = "delete", c = "com.htmake.reader.api.controller.FileController")
    /* renamed from: com.htmake.reader.api.controller.FileController$delete$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/FileController$delete$1.class */
    static final class C01631 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C01631(Continuation<? super C01631> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FileController.this.delete(null, this);
        }
    }

    /* compiled from: FileController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "FileController.kt", l = {342}, i = {0}, s = {"L$0"}, n = {CoreConstants.CONTEXT_SCOPE_VALUE}, m = "deleteMulti", c = "com.htmake.reader.api.controller.FileController")
    /* renamed from: com.htmake.reader.api.controller.FileController$deleteMulti$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/FileController$deleteMulti$1.class */
    static final class C01641 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C01641(Continuation<? super C01641> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FileController.this.deleteMulti(null, this);
        }
    }

    /* compiled from: FileController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "FileController.kt", l = {188}, i = {0}, s = {"L$0"}, n = {CoreConstants.CONTEXT_SCOPE_VALUE}, m = "download", c = "com.htmake.reader.api.controller.FileController")
    /* renamed from: com.htmake.reader.api.controller.FileController$download$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/FileController$download$1.class */
    static final class C01651 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C01651(Continuation<? super C01651> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FileController.this.download(null, this);
        }
    }

    /* compiled from: FileController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "FileController.kt", l = {229}, i = {0}, s = {"L$0"}, n = {CoreConstants.CONTEXT_SCOPE_VALUE}, m = BeanUtil.PREFIX_GETTER_GET, c = "com.htmake.reader.api.controller.FileController")
    /* renamed from: com.htmake.reader.api.controller.FileController$get$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/FileController$get$1.class */
    static final class C01661 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C01661(Continuation<? super C01661> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FileController.this.get(null, this);
        }
    }

    /* compiled from: FileController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "FileController.kt", l = {366}, i = {0, 0}, s = {"L$0", "L$1"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE}, m = "importPreview", c = "com.htmake.reader.api.controller.FileController")
    /* renamed from: com.htmake.reader.api.controller.FileController$importPreview$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/FileController$importPreview$1.class */
    static final class C01671 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        C01671(Continuation<? super C01671> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FileController.this.importPreview(null, this);
        }
    }

    /* compiled from: FileController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "FileController.kt", l = {91}, i = {0}, s = {"L$0"}, n = {CoreConstants.CONTEXT_SCOPE_VALUE}, m = BeanDefinitionParserDelegate.LIST_ELEMENT, c = "com.htmake.reader.api.controller.FileController")
    /* renamed from: com.htmake.reader.api.controller.FileController$list$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/FileController$list$1.class */
    static final class C01681 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C01681(Continuation<? super C01681> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FileController.this.list(null, this);
        }
    }

    /* compiled from: FileController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "FileController.kt", l = {282}, i = {0}, s = {"L$0"}, n = {CoreConstants.CONTEXT_SCOPE_VALUE}, m = "mkdir", c = "com.htmake.reader.api.controller.FileController")
    /* renamed from: com.htmake.reader.api.controller.FileController$mkdir$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/FileController$mkdir$1.class */
    static final class C01691 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C01691(Continuation<? super C01691> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FileController.this.mkdir(null, this);
        }
    }

    /* compiled from: FileController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "FileController.kt", l = {457}, i = {0, 0}, s = {"L$0", "L$1"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE}, m = "parse", c = "com.htmake.reader.api.controller.FileController")
    /* renamed from: com.htmake.reader.api.controller.FileController$parse$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/FileController$parse$1.class */
    static final class C01701 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        C01701(Continuation<? super C01701> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FileController.this.parse(null, this);
        }
    }

    /* compiled from: FileController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "FileController.kt", l = {420, 450}, i = {0, 0}, s = {"L$0", "L$1"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE}, m = "restore", c = "com.htmake.reader.api.controller.FileController")
    /* renamed from: com.htmake.reader.api.controller.FileController$restore$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/FileController$restore$1.class */
    static final class C01711 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        C01711(Continuation<? super C01711> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FileController.this.restore(null, this);
        }
    }

    /* compiled from: FileController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "FileController.kt", l = {259}, i = {0}, s = {"L$0"}, n = {CoreConstants.CONTEXT_SCOPE_VALUE}, m = "save", c = "com.htmake.reader.api.controller.FileController")
    /* renamed from: com.htmake.reader.api.controller.FileController$save$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/FileController$save$1.class */
    static final class C01721 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C01721(Continuation<? super C01721> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FileController.this.save(null, this);
        }
    }

    /* compiled from: FileController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "FileController.kt", l = {143}, i = {}, s = {}, n = {}, m = "upload", c = "com.htmake.reader.api.controller.FileController")
    /* renamed from: com.htmake.reader.api.controller.FileController$upload$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/FileController$upload$1.class */
    static final class C01731 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        C01731(Continuation<? super C01731> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FileController.this.upload(null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileController(@NotNull CoroutineContext coroutineContext) {
        super(coroutineContext);
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object checkAccess(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, boolean r9, boolean r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 848
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.FileController.checkAccess(io.vertx.ext.web.RoutingContext, boolean, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object checkAccess$default(FileController fileController, RoutingContext routingContext, boolean z, boolean z2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return fileController.checkAccess(routingContext, z, z2, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object list(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 669
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.FileController.list(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object upload(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 733
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.FileController.upload(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object download(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 593
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.FileController.download(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object get(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 403
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.FileController.get(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object save(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 354
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.FileController.save(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object mkdir(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 443
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.FileController.mkdir(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object delete(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.FileController.delete(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object deleteMulti(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 367
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.FileController.deleteMulti(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object importPreview(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r12) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 811
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.FileController.importPreview(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object restore(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 543
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.FileController.restore(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0420 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x03e7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0371  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object parse(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1203
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.FileController.parse(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
