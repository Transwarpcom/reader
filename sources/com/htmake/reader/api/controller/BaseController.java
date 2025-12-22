package com.htmake.reader.api.controller;

import ch.qos.logback.classic.ClassicConstants;
import ch.qos.logback.core.CoreConstants;
import com.google.gson.reflect.TypeToken;
import com.htmake.reader.config.AppConfig;
import com.htmake.reader.entity.User;
import com.htmake.reader.utils.ExtKt;
import com.htmake.reader.utils.SpringContextUtils;
import io.legado.app.utils.FileUtils;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.FormLoginHandler;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.core.env.Environment;

/* compiled from: BaseController.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010��\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u001a\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e0\u001c2\u0006\u0010\u001f\u001a\u00020\u001eJ\u0018\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u001d2\b\b\u0002\u0010\"\u001a\u00020\u001dJ\u0010\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020\u001dJ\u001c\u0010&\u001a\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e\u0018\u00010'2\u0006\u0010%\u001a\u00020\u001dJ\u000e\u0010(\u001a\u00020\u001d2\u0006\u0010\u0017\u001a\u00020\u0018J)\u0010)\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0017\u001a\u00020\u001e2\u0012\u0010*\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001d0+\"\u00020\u001d¢\u0006\u0002\u0010,J\u000e\u0010-\u001a\u00020\u001d2\u0006\u0010\u0017\u001a\u00020\u001eJX\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u00102\u0006\u00101\u001a\u00020\u00102\u0006\u00102\u001a\u00020\u00102-\u00103\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e05\u0012\u0006\u0012\u0004\u0018\u00010\u001e04¢\u0006\u0002\b6H\u0086@ø\u0001��¢\u0006\u0002\u00107J\u0082\u0001\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u00102\u0006\u00101\u001a\u00020\u00102\u0006\u00102\u001a\u00020\u00102-\u00103\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e05\u0012\u0006\u0012\u0004\u0018\u00010\u001e04¢\u0006\u0002\b62(\u00108\u001a$\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u001e0:j\b\u0012\u0004\u0012\u00020\u001e`;\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001609H\u0086@ø\u0001��¢\u0006\u0002\u0010<J7\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e0'2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010>\u001a\u00020$2\b\b\u0002\u0010?\u001a\u00020\u0016H\u0086@ø\u0001��¢\u0006\u0002\u0010@J\u001e\u0010A\u001a\u00020/2\u0006\u0010\u0017\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\u001d2\u0006\u0010B\u001a\u00020\u001eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n��R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006C"}, d2 = {"Lcom/htmake/reader/api/controller/BaseController;", "Lkotlinx/coroutines/CoroutineScope;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "appConfig", "Lcom/htmake/reader/config/AppConfig;", "getAppConfig", "()Lcom/htmake/reader/config/AppConfig;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "env", "Lorg/springframework/core/env/Environment;", "getEnv", "()Lorg/springframework/core/env/Environment;", "loginExpireDays", "", "userMutex", "Lkotlinx/coroutines/sync/Mutex;", "getUserMutex", "()Lkotlinx/coroutines/sync/Mutex;", "checkAuth", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Lio/vertx/ext/web/RoutingContext;", "(Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkManagerAuth", "formatUser", "", "", "", "userInfo", "getFileExt", "url", "defaultExt", "getUserInfoClass", "Lcom/htmake/reader/entity/User;", FormLoginHandler.DEFAULT_USERNAME_PARAM, "getUserInfoMap", "", "getUserNameSpace", "getUserStorage", "path", "", "(Ljava/lang/Object;[Ljava/lang/String;)Ljava/lang/String;", "getUserWebdavHome", "limitConcurrent", "", "concurrentCount", "startIndex", "endIndex", "handler", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(IIILkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "needContinue", "Lkotlin/Function2;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "(IIILkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveUserSession", ClassicConstants.USER_MDC_KEY, "regenerateToken", "(Lio/vertx/ext/web/RoutingContext;Lcom/htmake/reader/entity/User;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveUserStorage", "value", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BaseController.class */
public class BaseController implements CoroutineScope {

    @NotNull
    private final CoroutineContext coroutineContext;
    private int loginExpireDays;

    @NotNull
    private final AppConfig appConfig;

    @NotNull
    private final Environment env;

    @NotNull
    private final Mutex userMutex;

    /* compiled from: BaseController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BaseController.kt", l = {117}, i = {}, s = {}, n = {}, m = "checkAuth", c = "com.htmake.reader.api.controller.BaseController")
    /* renamed from: com.htmake.reader.api.controller.BaseController$checkAuth$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BaseController$checkAuth$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int I$0;
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
            return BaseController.this.checkAuth(null, this);
        }
    }

    /* compiled from: BaseController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BaseController.kt", l = {289, 320}, i = {0}, s = {"L$4"}, n = {"resultList"}, m = "limitConcurrent", c = "com.htmake.reader.api.controller.BaseController")
    /* renamed from: com.htmake.reader.api.controller.BaseController$limitConcurrent$3, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BaseController$limitConcurrent$3.class */
    static final class AnonymousClass3 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        long J$0;
        /* synthetic */ Object result;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BaseController.this.limitConcurrent(0, 0, 0, null, null, this);
        }
    }

    /* compiled from: BaseController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BaseController.kt", l = {33}, i = {0, 0, 0, 0}, s = {"L$0", "L$1", "L$2", "Z$0"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, ClassicConstants.USER_MDC_KEY, "regenerateToken"}, m = "saveUserSession", c = "com.htmake.reader.api.controller.BaseController")
    /* renamed from: com.htmake.reader.api.controller.BaseController$saveUserSession$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BaseController$saveUserSession$1.class */
    static final class C00601 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        /* synthetic */ Object result;
        int label;

        C00601(Continuation<? super C00601> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BaseController.this.saveUserSession(null, null, false, this);
        }
    }

    public BaseController(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        this.coroutineContext = coroutineContext;
        this.loginExpireDays = 7;
        Object bean = SpringContextUtils.getBean("appConfig", AppConfig.class);
        Intrinsics.checkNotNullExpressionValue(bean, "getBean(\"appConfig\", AppConfig::class.java)");
        this.appConfig = (AppConfig) bean;
        Object bean2 = SpringContextUtils.getBean((Class<Object>) Environment.class);
        Intrinsics.checkNotNullExpressionValue(bean2, "getBean(Environment::class.java)");
        this.env = (Environment) bean2;
        this.userMutex = MutexKt.Mutex$default(false, 1, null);
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    @NotNull
    public final AppConfig getAppConfig() {
        return this.appConfig;
    }

    @NotNull
    public final Environment getEnv() {
        return this.env;
    }

    @NotNull
    public final Mutex getUserMutex() {
        return this.userMutex;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveUserSession(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r9, @org.jetbrains.annotations.NotNull com.htmake.reader.entity.User r10, boolean r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.Map<java.lang.String, ? extends java.lang.Object>> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 608
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BaseController.saveUserSession(io.vertx.ext.web.RoutingContext, com.htmake.reader.entity.User, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object saveUserSession$default(BaseController baseController, RoutingContext routingContext, User user, boolean z, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: saveUserSession");
        }
        if ((i & 4) != 0) {
            z = true;
        }
        return baseController.saveUserSession(routingContext, user, z, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0362  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object checkAuth(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 887
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BaseController.checkAuth(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final boolean checkManagerAuth(@NotNull RoutingContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!this.appConfig.getSecure()) {
            return true;
        }
        if (this.appConfig.getSecureKey().length() == 0) {
            return true;
        }
        List<String> listQueryParam = context.queryParam("secureKey");
        Intrinsics.checkNotNullExpressionValue(listQueryParam, "context.queryParam(\"secureKey\")");
        String str = (String) CollectionsKt.firstOrNull((List) listQueryParam);
        String secureKey = str == null ? "" : str;
        if (Intrinsics.areEqual(secureKey, this.appConfig.getSecureKey())) {
            List<String> listQueryParam2 = context.queryParam("userNS");
            Intrinsics.checkNotNullExpressionValue(listQueryParam2, "context.queryParam(\"userNS\")");
            String userNS = (String) CollectionsKt.firstOrNull((List) listQueryParam2);
            String str2 = userNS;
            if (!(str2 == null || str2.length() == 0)) {
                context.put("userNameSpace", userNS);
                return true;
            }
            context.remove("userNameSpace");
            return true;
        }
        return false;
    }

    @NotNull
    public final String getUserNameSpace(@NotNull RoutingContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!this.appConfig.getSecure()) {
            return "default";
        }
        checkManagerAuth(context);
        String userNS = (String) context.get("userNameSpace");
        String str = userNS;
        if (!(str == null || str.length() == 0)) {
            return userNS;
        }
        String username = (String) context.get(FormLoginHandler.DEFAULT_USERNAME_PARAM);
        if (username != null) {
            return username;
        }
        return "default";
    }

    @Nullable
    public final String getUserStorage(@NotNull Object context, @NotNull String... path) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(path, "path");
        String userNameSpace = "";
        if (context instanceof RoutingContext) {
            userNameSpace = getUserNameSpace((RoutingContext) context);
        } else if (context instanceof String) {
            userNameSpace = (String) context;
        }
        if (userNameSpace.length() == 0) {
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add("data");
            spreadBuilder.addSpread(path);
            return ExtKt.getStorage$default((String[]) spreadBuilder.toArray(new String[spreadBuilder.size()]), null, 2, null);
        }
        SpreadBuilder spreadBuilder2 = new SpreadBuilder(3);
        spreadBuilder2.add("data");
        spreadBuilder2.add(userNameSpace);
        spreadBuilder2.addSpread(path);
        return ExtKt.getStorage$default((String[]) spreadBuilder2.toArray(new String[spreadBuilder2.size()]), null, 2, null);
    }

    public final void saveUserStorage(@NotNull Object context, @NotNull String path, @NotNull Object value) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(value, "value");
        String userNameSpace = "";
        if (context instanceof RoutingContext) {
            userNameSpace = getUserNameSpace((RoutingContext) context);
        } else if (context instanceof String) {
            userNameSpace = (String) context;
        }
        if (userNameSpace.length() == 0) {
            ExtKt.saveStorage$default(new String[]{"data", path}, value, false, null, 12, null);
        } else {
            ExtKt.saveStorage$default(new String[]{"data", userNameSpace, path}, value, false, null, 12, null);
        }
    }

    @Nullable
    public final User getUserInfoClass(@NotNull String username) {
        String json;
        User user;
        Intrinsics.checkNotNullParameter(username, "username");
        Object userInfoMap = getUserInfoMap(username);
        if (userInfoMap == null) {
            user = null;
        } else {
            if (!(userInfoMap instanceof String)) {
                json = ExtKt.getGson().toJson(userInfoMap);
            } else {
                json = (String) userInfoMap;
            }
            String json$iv$iv = json;
            user = (User) ExtKt.getGson().fromJson(json$iv$iv, new TypeToken<User>() { // from class: com.htmake.reader.api.controller.BaseController$getUserInfoClass$$inlined$toDataClass$1
            }.getType());
        }
        User user2 = user;
        return user2;
    }

    @Nullable
    public final Map<String, Object> getUserInfoMap(@NotNull String username) {
        Intrinsics.checkNotNullParameter(username, "username");
        if (username.length() == 0) {
            return null;
        }
        Map userMap = new LinkedHashMap();
        JsonObject userMapJson = ExtKt.asJsonObject(ExtKt.getStorage$default(new String[]{"data", "users"}, null, 2, null));
        if (userMapJson != null) {
            Map<String, Object> map = userMapJson.getMap();
            if (map == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.collections.Map<kotlin.String, kotlin.Any>>");
            }
            userMap = TypeIntrinsics.asMutableMap(map);
        }
        Map map2 = userMap;
        if (map2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
        }
        return (Map) map2.getOrDefault(username, null);
    }

    @NotNull
    public final Map<String, Object> formatUser(@NotNull Object userInfo) {
        Intrinsics.checkNotNullParameter(userInfo, "userInfo");
        User user = null;
        if (userInfo instanceof User) {
            user = (User) userInfo;
        } else {
            Object obj = userInfo instanceof Map ? (Map) userInfo : null;
            if (obj != null) {
                String json$iv$iv = obj instanceof String ? (String) obj : ExtKt.getGson().toJson(obj);
                user = (User) ExtKt.getGson().fromJson(json$iv$iv, new TypeToken<User>() { // from class: com.htmake.reader.api.controller.BaseController$formatUser$$inlined$toDataClass$1
                }.getType());
            }
        }
        return user == null ? new LinkedHashMap() : MapsKt.mutableMapOf(TuplesKt.to(FormLoginHandler.DEFAULT_USERNAME_PARAM, user.getUsername()), TuplesKt.to("lastLoginAt", Long.valueOf(user.getLast_login_at())), TuplesKt.to("accessToken", user.getUsername() + ':' + user.getToken()), TuplesKt.to("enableWebdav", Boolean.valueOf(user.getEnable_webdav())), TuplesKt.to("enableLocalStore", Boolean.valueOf(user.getEnable_local_store())), TuplesKt.to("enableBookSource", Boolean.valueOf(user.getEnable_book_source())), TuplesKt.to("enableRssSource", Boolean.valueOf(user.getEnable_rss_source())), TuplesKt.to("bookSourceLimit", Integer.valueOf(user.getBook_source_limit())), TuplesKt.to("bookLimit", Integer.valueOf(user.getBook_limit())), TuplesKt.to("createdAt", Long.valueOf(user.getCreated_at())));
    }

    @NotNull
    public final String getUserWebdavHome(@NotNull Object context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String prefix = ExtKt.getWorkDir("storage", "data");
        String userNameSpace = "";
        if (context instanceof RoutingContext) {
            userNameSpace = getUserNameSpace((RoutingContext) context);
        } else if (context instanceof String) {
            userNameSpace = (String) context;
        }
        if (userNameSpace.length() > 0) {
            prefix = prefix + ((Object) File.separator) + userNameSpace;
        }
        String prefix2 = prefix + ((Object) File.separator) + "webdav";
        File file = new File(prefix2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return prefix2;
    }

    public static /* synthetic */ String getFileExt$default(BaseController baseController, String str, String str2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFileExt");
        }
        if ((i & 2) != 0) {
            str2 = "";
        }
        return baseController.getFileExt(str, str2);
    }

    @NotNull
    public final String getFileExt(@NotNull String url, @NotNull String defaultExt) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(defaultExt, "defaultExt");
        return FileUtils.INSTANCE.getFileExtetion(url, defaultExt);
    }

    @Nullable
    public final Object limitConcurrent(int concurrentCount, int startIndex, int endIndex, @NotNull Function3<? super CoroutineScope, ? super Integer, ? super Continuation<Object>, ? extends Object> handler, @NotNull Continuation<? super Unit> $completion) throws Throwable {
        Object objLimitConcurrent = limitConcurrent(concurrentCount, startIndex, endIndex, handler, new Function2<ArrayList<Object>, Integer, Boolean>() { // from class: com.htmake.reader.api.controller.BaseController.limitConcurrent.2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Boolean invoke(ArrayList<Object> arrayList, Integer num) {
                return Boolean.valueOf(invoke(arrayList, num.intValue()));
            }

            public final boolean invoke(@NotNull ArrayList<Object> noName_0, int $noName_1) {
                Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                return true;
            }
        }, $completion);
        return objLimitConcurrent == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objLimitConcurrent : Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x02d3, code lost:
    
        if (0 < r28) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x02d6, code lost:
    
        r0 = r27;
        r27 = r27 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x02dd, code lost:
    
        r33 = r26;
        r36.L$0 = r15;
        r36.L$1 = r24;
        r36.L$2 = r26;
        r36.L$3 = r33;
        r36.L$4 = null;
        r36.I$0 = r18;
        r36.I$1 = r27;
        r36.I$2 = r28;
        r36.label = 2;
        r0 = ((kotlinx.coroutines.Deferred) r24.get(r0)).await(r36);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0333, code lost:
    
        if (r0 != r0) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0338, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0391, code lost:
    
        if (r27 >= r28) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0394, code lost:
    
        r24.clear();
        r15.invoke(r26, kotlin.coroutines.jvm.internal.Boxing.boxInt(r18));
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00e3, code lost:
    
        r24.clear();
        r24.addAll(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0082, code lost:
    
        r17 = r17 + 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x01d8 A[LOOP:1: B:30:0x01d8->B:82:?, LOOP_START, PHI: r19 r28
  0x01d8: PHI (r19v2 'resultCount' int) = (r19v1 'resultCount' int), (r19v3 'resultCount' int) binds: [B:29:0x01d5, B:82:?] A[DONT_GENERATE, DONT_INLINE]
  0x01d8: PHI (r28v7 int) = (r28v6 int), (r28v8 int) binds: [B:29:0x01d5, B:82:?] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x0391 -> B:49:0x02d6). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object limitConcurrent(int r11, int r12, int r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function3<? super kotlinx.coroutines.CoroutineScope, ? super java.lang.Integer, ? super kotlin.coroutines.Continuation<java.lang.Object>, ? extends java.lang.Object> r14, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.util.ArrayList<java.lang.Object>, ? super java.lang.Integer, java.lang.Boolean> r15, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r16) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 994
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BaseController.limitConcurrent(int, int, int, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: BaseController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "BaseController.kt", l = {276}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.BaseController$limitConcurrent$4")
    /* renamed from: com.htmake.reader.api.controller.BaseController$limitConcurrent$4, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BaseController$limitConcurrent$4.class */
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Object>, Object> {
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ Function3<CoroutineScope, Integer, Continuation<Object>, Object> $handler;
        final /* synthetic */ int $i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass4(Function3<? super CoroutineScope, ? super Integer, ? super Continuation<Object>, ? extends Object> $handler, int $i, Continuation<? super AnonymousClass4> $completion) {
            super(2, $completion);
            this.$handler = $handler;
            this.$i = $i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.$handler, this.$i, $completion);
            anonymousClass4.L$0 = value;
            return anonymousClass4;
        }

        @Nullable
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope p1, @Nullable Continuation<Object> p2) {
            return ((AnonymousClass4) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Object> continuation) {
            return invoke2(coroutineScope, (Continuation<Object>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$async = (CoroutineScope) this.L$0;
                    Function3<CoroutineScope, Integer, Continuation<Object>, Object> function3 = this.$handler;
                    Integer numBoxInt = Boxing.boxInt(this.$i);
                    this.label = 1;
                    Object objInvoke = function3.invoke($this$async, numBoxInt, this);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return objInvoke;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
