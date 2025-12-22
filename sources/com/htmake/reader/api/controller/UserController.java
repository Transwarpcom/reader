package com.htmake.reader.api.controller;

import ch.qos.logback.classic.ClassicConstants;
import ch.qos.logback.core.CoreConstants;
import com.htmake.reader.entity.License;
import com.htmake.reader.entity.User;
import com.htmake.reader.utils.ExtKt;
import io.vertx.ext.web.RoutingContext;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: UserController.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\f\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\rJ\u0019\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\rJ\u0019\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\rJO\u0010\u0015\u001a\u00020\u000f2<\u0010\u0016\u001a8\b\u0001\u0012\u0004\u0012\u00020\u0018\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u0017¢\u0006\u0002\b H\u0086@ø\u0001��¢\u0006\u0002\u0010!J\u0019\u0010\"\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\rJ\u0019\u0010#\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\rJ\u0010\u0010$\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0019\u0010%\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\rJ\u0019\u0010&\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\rJ\u0019\u0010'\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\rJ\u0019\u0010(\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\rJ\u0019\u0010)\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\rJ\u0019\u0010*\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\rJ\u0019\u0010+\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\rR\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"Lcom/htmake/reader/api/controller/UserController;", "Lcom/htmake/reader/api/controller/BaseController;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "userMaxCount", "", "getUserMaxCount", "()I", "addUser", "Lcom/htmake/reader/api/ReturnData;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lio/vertx/ext/web/RoutingContext;", "(Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearInactiveUsers", "", "day", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteFile", "deleteUsers", "downloadBackupFile", "forEachUser", "handler", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Lcom/htmake/reader/entity/User;", "Lkotlin/ParameterName;", "name", ClassicConstants.USER_MDC_KEY, "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserConfig", "getUserInfo", "getUserLimit", "getUserList", "login", "logout", "resetPassword", "saveUserConfig", "updateUser", "uploadFile", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController.class */
public final class UserController extends BaseController {
    private final int userMaxCount;

    /* compiled from: UserController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "UserController.kt", l = {251}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "addUser", c = "com.htmake.reader.api.controller.UserController")
    /* renamed from: com.htmake.reader.api.controller.UserController$addUser$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController$addUser$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
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
            return UserController.this.addUser(null, this);
        }
    }

    /* compiled from: UserController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "UserController.kt", l = {412, 423, 424}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "clearInactiveUsers", c = "com.htmake.reader.api.controller.UserController")
    /* renamed from: com.htmake.reader.api.controller.UserController$clearInactiveUsers$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController$clearInactiveUsers$1.class */
    static final class C01801 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01801(Continuation<? super C01801> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return UserController.this.clearInactiveUsers((RoutingContext) null, this);
        }
    }

    /* compiled from: UserController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "UserController.kt", l = {612}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "deleteFile", c = "com.htmake.reader.api.controller.UserController")
    /* renamed from: com.htmake.reader.api.controller.UserController$deleteFile$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController$deleteFile$1.class */
    static final class C01811 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01811(Continuation<? super C01811> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return UserController.this.deleteFile(null, this);
        }
    }

    /* compiled from: UserController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "UserController.kt", l = {372}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "deleteUsers", c = "com.htmake.reader.api.controller.UserController")
    /* renamed from: com.htmake.reader.api.controller.UserController$deleteUsers$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController$deleteUsers$1.class */
    static final class C01821 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01821(Continuation<? super C01821> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return UserController.this.deleteUsers(null, this);
        }
    }

    /* compiled from: UserController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "UserController.kt", l = {638, 645, 648}, i = {0, 0, 0, 1, 1}, s = {"L$0", "L$1", "L$2", "L$2", "L$3"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData", "bookController", "userNameSpace"}, m = "downloadBackupFile", c = "com.htmake.reader.api.controller.UserController")
    /* renamed from: com.htmake.reader.api.controller.UserController$downloadBackupFile$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController$downloadBackupFile$1.class */
    static final class C01831 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        /* synthetic */ Object result;
        int label;

        C01831(Continuation<? super C01831> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return UserController.this.downloadBackupFile(null, this);
        }
    }

    /* compiled from: UserController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "UserController.kt", l = {674}, i = {}, s = {}, n = {}, m = "forEachUser", c = "com.htmake.reader.api.controller.UserController")
    /* renamed from: com.htmake.reader.api.controller.UserController$forEachUser$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController$forEachUser$1.class */
    static final class C01841 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        /* synthetic */ Object result;
        int label;

        C01841(Continuation<? super C01841> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return UserController.this.forEachUser(null, this);
        }
    }

    /* compiled from: UserController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "UserController.kt", l = {562}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "getUserConfig", c = "com.htmake.reader.api.controller.UserController")
    /* renamed from: com.htmake.reader.api.controller.UserController$getUserConfig$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController$getUserConfig$1.class */
    static final class C01851 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01851(Continuation<? super C01851> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return UserController.this.getUserConfig(null, this);
        }
    }

    /* compiled from: UserController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "UserController.kt", l = {507}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "getUserInfo", c = "com.htmake.reader.api.controller.UserController")
    /* renamed from: com.htmake.reader.api.controller.UserController$getUserInfo$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController$getUserInfo$1.class */
    static final class C01861 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01861(Continuation<? super C01861> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return UserController.this.getUserInfo(null, this);
        }
    }

    /* compiled from: UserController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "UserController.kt", l = {228}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "getUserList", c = "com.htmake.reader.api.controller.UserController")
    /* renamed from: com.htmake.reader.api.controller.UserController$getUserList$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController$getUserList$1.class */
    static final class C01871 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01871(Continuation<? super C01871> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return UserController.this.getUserList(null, this);
        }
    }

    /* compiled from: UserController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "UserController.kt", l = {154, 170}, i = {}, s = {}, n = {}, m = "login", c = "com.htmake.reader.api.controller.UserController")
    /* renamed from: com.htmake.reader.api.controller.UserController$login$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController$login$1.class */
    static final class C01881 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C01881(Continuation<? super C01881> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return UserController.this.login(null, this);
        }
    }

    /* compiled from: UserController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "UserController.kt", l = {177, 195}, i = {0, 0, 0, 1}, s = {"L$0", "L$1", "L$2", "L$4"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData", "userMap"}, m = "logout", c = "com.htmake.reader.api.controller.UserController")
    /* renamed from: com.htmake.reader.api.controller.UserController$logout$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController$logout$1.class */
    static final class C01891 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        /* synthetic */ Object result;
        int label;

        C01891(Continuation<? super C01891> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return UserController.this.logout(null, this);
        }
    }

    /* compiled from: UserController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "UserController.kt", l = {326}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "resetPassword", c = "com.htmake.reader.api.controller.UserController")
    /* renamed from: com.htmake.reader.api.controller.UserController$resetPassword$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController$resetPassword$1.class */
    static final class C01901 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01901(Continuation<? super C01901> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return UserController.this.resetPassword(null, this);
        }
    }

    /* compiled from: UserController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "UserController.kt", l = {546}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "saveUserConfig", c = "com.htmake.reader.api.controller.UserController")
    /* renamed from: com.htmake.reader.api.controller.UserController$saveUserConfig$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController$saveUserConfig$1.class */
    static final class C01911 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01911(Continuation<? super C01911> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return UserController.this.saveUserConfig(null, this);
        }
    }

    /* compiled from: UserController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "UserController.kt", l = {447}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "updateUser", c = "com.htmake.reader.api.controller.UserController")
    /* renamed from: com.htmake.reader.api.controller.UserController$updateUser$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController$updateUser$1.class */
    static final class C01921 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01921(Continuation<? super C01921> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return UserController.this.updateUser(null, this);
        }
    }

    /* compiled from: UserController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "UserController.kt", l = {575}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "uploadFile", c = "com.htmake.reader.api.controller.UserController")
    /* renamed from: com.htmake.reader.api.controller.UserController$uploadFile$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController$uploadFile$1.class */
    static final class C01931 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01931(Continuation<? super C01931> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return UserController.this.uploadFile(null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserController(@NotNull CoroutineContext coroutineContext) {
        super(coroutineContext);
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        this.userMaxCount = 15;
    }

    public final int getUserMaxCount() {
        return this.userMaxCount;
    }

    private final int getUserLimit(RoutingContext context) {
        License license = ExtKt.getInstalledLicense$default(false, 1, null);
        String strHost = context.request().host();
        Intrinsics.checkNotNullExpressionValue(strHost, "context.request().host()");
        if (license.validHost(strHost)) {
            return Math.min(Math.max(getAppConfig().getUserLimit(), 1), license.getUserMaxLimit());
        }
        return Math.min(Math.max(getAppConfig().getUserLimit(), 1), this.userMaxCount);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object login(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r21, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1068
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.UserController.login(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x022f A[Catch: all -> 0x033b, TryCatch #0 {all -> 0x033b, blocks: (B:39:0x0195, B:46:0x020a, B:48:0x022f, B:50:0x023b, B:51:0x0245, B:52:0x0246, B:55:0x025d, B:56:0x0266, B:57:0x0267, B:59:0x027a, B:62:0x0290, B:64:0x02b1, B:66:0x02bd, B:67:0x02d4, B:69:0x02e8, B:70:0x02f5, B:45:0x0202), top: B:80:0x0043 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x025d A[Catch: all -> 0x033b, TryCatch #0 {all -> 0x033b, blocks: (B:39:0x0195, B:46:0x020a, B:48:0x022f, B:50:0x023b, B:51:0x0245, B:52:0x0246, B:55:0x025d, B:56:0x0266, B:57:0x0267, B:59:0x027a, B:62:0x0290, B:64:0x02b1, B:66:0x02bd, B:67:0x02d4, B:69:0x02e8, B:70:0x02f5, B:45:0x0202), top: B:80:0x0043 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0267 A[Catch: all -> 0x033b, TryCatch #0 {all -> 0x033b, blocks: (B:39:0x0195, B:46:0x020a, B:48:0x022f, B:50:0x023b, B:51:0x0245, B:52:0x0246, B:55:0x025d, B:56:0x0266, B:57:0x0267, B:59:0x027a, B:62:0x0290, B:64:0x02b1, B:66:0x02bd, B:67:0x02d4, B:69:0x02e8, B:70:0x02f5, B:45:0x0202), top: B:80:0x0043 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object logout(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 870
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.UserController.logout(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getUserList(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 473
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.UserController.getUserList(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object addUser(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r21, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1139
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.UserController.addUser(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object resetPassword(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 727
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.UserController.resetPassword(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object deleteUsers(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 658
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.UserController.deleteUsers(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object clearInactiveUsers(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 434
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.UserController.clearInactiveUsers(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: UserController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", ClassicConstants.USER_MDC_KEY, "Lcom/htmake/reader/entity/User;"})
    @DebugMetadata(f = "UserController.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.UserController$clearInactiveUsers$3")
    /* renamed from: com.htmake.reader.api.controller.UserController$clearInactiveUsers$3, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/UserController$clearInactiveUsers$3.class */
    static final class AnonymousClass3 extends SuspendLambda implements Function3<CoroutineScope, User, Continuation<? super Boolean>, Object> {
        int label;
        /* synthetic */ Object L$0;
        final /* synthetic */ long $expireTime;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(long $expireTime, Continuation<? super AnonymousClass3> $completion) {
            super(3, $completion);
            this.$expireTime = $expireTime;
        }

        @Override // kotlin.jvm.functions.Function3
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @NotNull User p2, @Nullable Continuation<? super Boolean> p3) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$expireTime, p3);
            anonymousClass3.L$0 = p2;
            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            boolean z;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    User user = (User) this.L$0;
                    if (user.getLast_login_at() < this.$expireTime) {
                        UserControllerKt.logger.info("delete user: {}", user);
                        File userHome = new File(ExtKt.getWorkDir("storage", "data", user.getUsername()));
                        UserControllerKt.logger.info("delete userHome: {}", userHome);
                        if (userHome.exists()) {
                            ExtKt.deleteRecursively(userHome);
                        }
                        z = true;
                    } else {
                        z = false;
                    }
                    return Boxing.boxBoolean(z);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Nullable
    public final Object clearInactiveUsers(int day, @NotNull Continuation<? super Unit> $completion) throws Throwable {
        long expireTime = System.currentTimeMillis() - ((day * 86400) * 1000);
        Object objForEachUser = forEachUser(new AnonymousClass3(expireTime, null), $completion);
        return objForEachUser == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objForEachUser : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object updateUser(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 837
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.UserController.updateUser(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getUserInfo(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 613
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.UserController.getUserInfo(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveUserConfig(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.UserController.saveUserConfig(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getUserConfig(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.UserController.getUserConfig(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object uploadFile(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 689
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.UserController.uploadFile(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object deleteFile(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 445
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.UserController.deleteFile(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object downloadBackupFile(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 552
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.UserController.downloadBackupFile(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Type inference failed for: r1v22, types: [T, java.util.Map] */
    /* JADX WARN: Type inference failed for: r1v31, types: [T, java.util.Map] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x0278 -> B:20:0x0105). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x027b -> B:20:0x0105). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object forEachUser(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function3<? super kotlinx.coroutines.CoroutineScope, ? super com.htmake.reader.entity.User, ? super kotlin.coroutines.Continuation<? super java.lang.Boolean>, ? extends java.lang.Object> r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 725
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.UserController.forEachUser(kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
