package com.htmake.reader.api.controller;

import ch.qos.logback.core.CoreConstants;
import cn.hutool.core.date.DatePattern;
import com.google.common.net.HttpHeaders;
import com.google.gson.reflect.TypeToken;
import com.htmake.reader.entity.User;
import com.htmake.reader.utils.ExtKt;
import com.htmake.reader.utils.VertExtKt;
import io.legado.app.utils.EncoderUtils;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.FormLoginHandler;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.slf4j.MDCContext;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mozilla.classfile.ByteCode;

/* compiled from: WebdavController.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\u0018��2\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u001c\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u00060\tj\u0002`\n\u0012\u0004\u0012\u00020\u000b0\u0007¢\u0006\u0002\u0010\fJ\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\bJ\u0019\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010J\u0019\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010J\u0019\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010J\u0019\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010J\u0019\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010J\u0019\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010J\u0019\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010J\u0019\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010J\u0019\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lcom/htmake/reader/api/controller/WebdavController;", "Lcom/htmake/reader/api/controller/BaseController;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "router", "Lio/vertx/ext/web/Router;", "onHandlerError", "Lkotlin/Function2;", "Lio/vertx/ext/web/RoutingContext;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "", "(Lkotlin/coroutines/CoroutineContext;Lio/vertx/ext/web/Router;Lkotlin/jvm/functions/Function2;)V", "backupToWebdav", "Lcom/htmake/reader/api/ReturnData;", CoreConstants.CONTEXT_SCOPE_VALUE, "(Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkAuthorization", "", "webdavCopy", "webdavDelete", "webdavDownload", "webdavList", "webdavLock", "webdavMkdir", "webdavMove", "webdavUnLock", "webdavUpload", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/WebdavController.class */
public final class WebdavController extends BaseController {

    /* compiled from: WebdavController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "WebdavController.kt", l = {518, 533, 537}, i = {0, 0, 0, 1, 1}, s = {"L$0", "L$1", "L$2", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData", "bookController", "userNameSpace"}, m = "backupToWebdav", c = "com.htmake.reader.api.controller.WebdavController")
    /* renamed from: com.htmake.reader.api.controller.WebdavController$backupToWebdav$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/WebdavController$backupToWebdav$1.class */
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
            return WebdavController.this.backupToWebdav(null, this);
        }
    }

    /* compiled from: WebdavController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "WebdavController.kt", l = {357}, i = {}, s = {}, n = {}, m = "webdavUpload", c = "com.htmake.reader.api.controller.WebdavController")
    /* renamed from: com.htmake.reader.api.controller.WebdavController$webdavUpload$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/WebdavController$webdavUpload$1.class */
    static final class C01941 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C01941(Continuation<? super C01941> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return WebdavController.this.webdavUpload(null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebdavController(@NotNull CoroutineContext coroutineContext, @NotNull Router router, @NotNull Function2<? super RoutingContext, ? super Exception, Unit> onHandlerError) {
        super(coroutineContext);
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        Intrinsics.checkNotNullParameter(router, "router");
        Intrinsics.checkNotNullParameter(onHandlerError, "onHandlerError");
        Route route = router.route("/reader3/webdav*");
        Intrinsics.checkNotNullExpressionValue(route, "router.route(\"/reader3/webdav*\")");
        VertExtKt.globalHandler(route, (v2) -> {
            m867_init_$lambda1(r1, r2, v2);
        });
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: _init_$lambda-1, reason: not valid java name */
    private static final void m867_init_$lambda1(WebdavController this$0, Function2 onHandlerError, RoutingContext it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(onHandlerError, "$onHandlerError");
        it.addHeadersEndHandler((v2) -> {
            m866lambda1$lambda0(r1, r2, v2);
        });
        String rawMethod = it.request().rawMethod();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        if (!this$0.checkAuthorization(it)) {
            if (rawMethod.equals("PROPFIND") || rawMethod.equals("MKCOL") || rawMethod.equals("PUT") || rawMethod.equals("GET") || rawMethod.equals("DELETE") || rawMethod.equals("MOVE") || rawMethod.equals("COPY") || rawMethod.equals("LOCK") || rawMethod.equals("UNLOCK")) {
                it.response().setStatusCode(401).end();
                return;
            } else if (rawMethod.equals("OPTIONS")) {
                String authorization = it.request().getHeader("Authorization");
                if (authorization != null) {
                    it.response().setStatusCode(401).end();
                    return;
                }
            }
        }
        if (rawMethod != null) {
            switch (rawMethod.hashCode()) {
                case -1787112636:
                    if (rawMethod.equals("UNLOCK")) {
                        BuildersKt__Builders_commonKt.launch$default(this$0, new MDCContext(null, 1, null).plus(Dispatchers.getIO()), null, new WebdavController$1$10(this$0, it, onHandlerError, null), 2, null);
                        return;
                    }
                    break;
                case -531492226:
                    if (rawMethod.equals("OPTIONS")) {
                        it.response().setStatusCode(200).end();
                        return;
                    }
                    break;
                case -210493540:
                    if (rawMethod.equals("PROPFIND")) {
                        BuildersKt__Builders_commonKt.launch$default(this$0, new MDCContext(null, 1, null).plus(Dispatchers.getIO()), null, new WebdavController$1$2(this$0, it, onHandlerError, null), 2, null);
                        return;
                    }
                    break;
                case 70454:
                    if (rawMethod.equals("GET")) {
                        BuildersKt__Builders_commonKt.launch$default(this$0, new MDCContext(null, 1, null).plus(Dispatchers.getIO()), null, new WebdavController$1$5(this$0, it, onHandlerError, null), 2, null);
                        return;
                    }
                    break;
                case 79599:
                    if (rawMethod.equals("PUT")) {
                        BuildersKt__Builders_commonKt.launch$default(this$0, new MDCContext(null, 1, null).plus(Dispatchers.getIO()), null, new WebdavController$1$4(this$0, it, onHandlerError, null), 2, null);
                        return;
                    }
                    break;
                case 2074485:
                    if (rawMethod.equals("COPY")) {
                        BuildersKt__Builders_commonKt.launch$default(this$0, new MDCContext(null, 1, null).plus(Dispatchers.getIO()), null, new WebdavController$1$8(this$0, it, onHandlerError, null), 2, null);
                        return;
                    }
                    break;
                case 2342187:
                    if (rawMethod.equals("LOCK")) {
                        BuildersKt__Builders_commonKt.launch$default(this$0, new MDCContext(null, 1, null).plus(Dispatchers.getIO()), null, new WebdavController$1$9(this$0, it, onHandlerError, null), 2, null);
                        return;
                    }
                    break;
                case 2372561:
                    if (rawMethod.equals("MOVE")) {
                        BuildersKt__Builders_commonKt.launch$default(this$0, new MDCContext(null, 1, null).plus(Dispatchers.getIO()), null, new WebdavController$1$7(this$0, it, onHandlerError, null), 2, null);
                        return;
                    }
                    break;
                case 73412354:
                    if (rawMethod.equals("MKCOL")) {
                        BuildersKt__Builders_commonKt.launch$default(this$0, new MDCContext(null, 1, null).plus(Dispatchers.getIO()), null, new WebdavController$1$3(this$0, it, onHandlerError, null), 2, null);
                        return;
                    }
                    break;
                case 2012838315:
                    if (rawMethod.equals("DELETE")) {
                        BuildersKt__Builders_commonKt.launch$default(this$0, new MDCContext(null, 1, null).plus(Dispatchers.getIO()), null, new WebdavController$1$6(this$0, it, onHandlerError, null), 2, null);
                        return;
                    }
                    break;
            }
        }
        it.response().setStatusCode(405).end();
    }

    /* renamed from: lambda-1$lambda-0, reason: not valid java name */
    private static final void m866lambda1$lambda0(RoutingContext $it, WebdavController this$0, Void $noName_0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HttpServerResponse res = $it.response();
        res.putHeader("DAV", "1,2");
        res.putHeader("Access-Control-Allow-Origin", "*");
        res.putHeader("Access-Control-Allow-Credentials", "true");
        res.putHeader("Access-Control-Expose-Headers", "DAV, content-length, Allow");
        res.putHeader("MS-Author-Via", "DAV");
        res.putHeader("Allow", "OPTIONS,DELETE,GET,PUT,PROPFIND,MKCOL,MOVE,COPY,LOCK,UNLOCK");
        if (this$0.getAppConfig().getSecure()) {
            res.putHeader("WWW-Authenticate", "Basic realm=\"Default realm\"");
        }
    }

    public final boolean checkAuthorization(@NotNull RoutingContext context) {
        String json;
        Intrinsics.checkNotNullParameter(context, "context");
        if (!getAppConfig().getSecure()) {
            return true;
        }
        String authorization = context.request().getHeader("Authorization");
        WebdavControllerKt.logger.info("authorization: {}", authorization);
        if (authorization != null) {
            if (authorization.length() == 0) {
                return false;
            }
            List auth = StringsKt.split$default((CharSequence) EncoderUtils.base64Decode$default(EncoderUtils.INSTANCE, StringsKt.replace(authorization, "Basic ", "", true), 0, 2, null), new String[]{":"}, false, 2, 2, (Object) null);
            if (auth.size() < 2) {
                return false;
            }
            Object username = (String) auth.get(0);
            String password = (String) auth.get(1);
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
            Object obj = (Map) map2.getOrDefault(username, null);
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof String)) {
                json = ExtKt.getGson().toJson(obj);
            } else {
                json = (String) obj;
            }
            String json$iv$iv = json;
            User userInfo = (User) ExtKt.getGson().fromJson(json$iv$iv, new TypeToken<User>() { // from class: com.htmake.reader.api.controller.WebdavController$checkAuthorization$$inlined$toDataClass$1
            }.getType());
            if (userInfo == null) {
                return false;
            }
            String passwordEncrypted = ExtKt.genEncryptedPassword(password, userInfo.getSalt());
            if (!Intrinsics.areEqual(passwordEncrypted, userInfo.getPassword())) {
                WebdavControllerKt.logger.info("user: {} password error", userInfo.getUsername());
                return false;
            }
            if (!userInfo.getEnable_webdav()) {
                WebdavControllerKt.logger.info("user: {} enable_webdav: false", userInfo.getUsername());
                return false;
            }
            context.put(FormLoginHandler.DEFAULT_USERNAME_PARAM, userInfo.getUsername());
            return true;
        }
        return false;
    }

    @Nullable
    public final Object webdavList(@NotNull RoutingContext context, @NotNull Continuation<? super Unit> $completion) throws UnsupportedEncodingException {
        String home = getUserWebdavHome(context);
        String strPath = context.request().path();
        Intrinsics.checkNotNullExpressionValue(strPath, "context.request().path()");
        String path = URLDecoder.decode(StringsKt.replace(strPath, "/reader3/webdav/", "/", true), "UTF-8");
        Intrinsics.checkNotNullExpressionValue(path, "decode(path, \"UTF-8\")");
        File file = new File(Intrinsics.stringPlus(home, path));
        if (!file.exists()) {
            context.response().setStatusCode(404).end();
            return Unit.INSTANCE;
        }
        final Ref.ObjectRef dirResponse = new Ref.ObjectRef();
        dirResponse.element = "<D:response>\n                <D:href>%s</D:href>\n                <D:propstat>\n                    <D:status>HTTP/1.1 200 OK</D:status>\n                    <D:prop>\n                        <D:getlastmodified>%s</D:getlastmodified>\n                        <D:creationdate>%s</D:creationdate>\n                        <D:resourcetype>\n                            <D:collection />\n                        </D:resourcetype>\n                        <D:displayname>%s</D:displayname>\n                    </D:prop>\n                </D:propstat>\n            </D:response>\n        ";
        final Ref.ObjectRef fileResponse = new Ref.ObjectRef();
        fileResponse.element = "<D:response>\n                <D:href>%s</D:href>\n                <D:propstat>\n                    <D:status>HTTP/1.1 200 OK</D:status>\n                    <D:prop>\n                        <D:getlastmodified>%s</D:getlastmodified>\n                        <D:creationdate>%s</D:creationdate>\n                        <D:resourcetype />\n                        <D:displayname>%s</D:displayname>\n                        <D:getcontentlength>%s</D:getcontentlength>\n                        <D:getcontenttype>%s</D:getcontenttype>\n                    </D:prop>\n                </D:propstat>\n            </D:response>\n        ";
        String fileUrl = context.request().absoluteURI();
        Object formatter = new Function3<File, String, Boolean, String>() { // from class: com.htmake.reader.api.controller.WebdavController$webdavList$formatter$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ String invoke(File file2, String str, Boolean bool) {
                return invoke(file2, str, bool.booleanValue());
            }

            @NotNull
            public final String invoke(@NotNull File f, @NotNull String url, boolean showName) {
                Intrinsics.checkNotNullParameter(f, "f");
                Intrinsics.checkNotNullParameter(url, "url");
                String name = showName ? f.getName() : "";
                String modifiedDate = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN).format(Long.valueOf(f.lastModified()));
                if (f.isFile()) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str = fileResponse.element;
                    Object[] objArr = {url, modifiedDate, modifiedDate, name, Long.valueOf(f.length()), ""};
                    String str2 = String.format(str, Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
                    return str2;
                }
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Object[] objArr2 = {url, modifiedDate, modifiedDate, name};
                String str3 = String.format(dirResponse.element, Arrays.copyOf(objArr2, objArr2.length));
                Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(format, *args)");
                return str3;
            }
        };
        if (file.isFile()) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(fileUrl, "fileUrl");
            Object[] objArr = {((Function3) formatter).invoke(file, fileUrl, Boxing.boxBoolean(true))};
            String str = String.format("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n            <D:multistatus xmlns:D=\"DAV:\">\n                %s\n            </D:multistatus>\n        ", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
            context.response().setStatusCode(207).end(str);
            return Unit.INSTANCE;
        }
        if (file.isDirectory()) {
            Intrinsics.checkNotNullExpressionValue(fileUrl, "fileUrl");
            String fileUrl2 = StringsKt.endsWith$default(fileUrl, "/", false, 2, (Object) null) ? fileUrl : Intrinsics.stringPlus(fileUrl, "/");
            Intrinsics.checkNotNullExpressionValue(fileUrl2, "fileUrl");
            Object response = ((Function3) formatter).invoke(file, fileUrl2, Boxing.boxBoolean(false));
            Object[] objArrListFiles = file.listFiles();
            Intrinsics.checkNotNullExpressionValue(objArrListFiles, "file.listFiles()");
            Object[] $this$forEach$iv = objArrListFiles;
            for (Object element$iv : $this$forEach$iv) {
                File it = (File) element$iv;
                String fileName = URLEncoder.encode(it.getName(), "UTF-8");
                Intrinsics.checkNotNullExpressionValue(it, "it");
                response = Intrinsics.stringPlus((String) response, ((Function3) formatter).invoke(it, Intrinsics.stringPlus(fileUrl2, fileName), Boxing.boxBoolean(true)));
            }
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Object[] objArr2 = {response};
            String str2 = String.format("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n            <D:multistatus xmlns:D=\"DAV:\">\n                %s\n            </D:multistatus>\n        ", Arrays.copyOf(objArr2, objArr2.length));
            Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
            context.response().setStatusCode(207).end(str2);
            return Unit.INSTANCE;
        }
        context.response().setStatusCode(404).end();
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object webdavMkdir(@NotNull RoutingContext context, @NotNull Continuation<? super Unit> $completion) throws UnsupportedEncodingException {
        String home = getUserWebdavHome(context);
        String strPath = context.request().path();
        Intrinsics.checkNotNullExpressionValue(strPath, "context.request().path()");
        String path = URLDecoder.decode(StringsKt.replace(strPath, "/reader3/webdav/", "/", true), "UTF-8");
        Intrinsics.checkNotNullExpressionValue(path, "decode(path, \"UTF-8\")");
        File file = new File(Intrinsics.stringPlus(home, path));
        if (file.exists()) {
            context.response().setStatusCode(ByteCode.JSR_W).end();
            return Unit.INSTANCE;
        }
        try {
            file.mkdirs();
            context.response().setStatusCode(ByteCode.JSR_W).end();
        } catch (Exception e) {
            context.response().setStatusCode(500).end();
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object webdavUpload(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 475
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.WebdavController.webdavUpload(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object webdavDownload(@NotNull RoutingContext context, @NotNull Continuation<? super Unit> $completion) throws UnsupportedEncodingException {
        String home = getUserWebdavHome(context);
        String strPath = context.request().path();
        Intrinsics.checkNotNullExpressionValue(strPath, "context.request().path()");
        String path = URLDecoder.decode(StringsKt.replace(strPath, "/reader3/webdav/", "/", true), "UTF-8");
        Intrinsics.checkNotNullExpressionValue(path, "decode(path, \"UTF-8\")");
        File file = new File(Intrinsics.stringPlus(home, path));
        if (!file.exists()) {
            context.response().setStatusCode(404).end();
            return Unit.INSTANCE;
        }
        if (file.isDirectory()) {
            context.response().setStatusCode(405).end();
            return Unit.INSTANCE;
        }
        HttpServerResponse httpServerResponseSendFile = context.response().putHeader("Cache-Control", "86400").putHeader(HttpHeaders.CONTENT_DISPOSITION, Intrinsics.stringPlus("attachment; filename=", URLEncoder.encode(file.getName(), "UTF-8"))).sendFile(file.toString());
        return httpServerResponseSendFile == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? httpServerResponseSendFile : Unit.INSTANCE;
    }

    @Nullable
    public final Object webdavDelete(@NotNull RoutingContext context, @NotNull Continuation<? super Unit> $completion) throws UnsupportedEncodingException {
        String home = getUserWebdavHome(context);
        String strPath = context.request().path();
        Intrinsics.checkNotNullExpressionValue(strPath, "context.request().path()");
        String path = URLDecoder.decode(StringsKt.replace(strPath, "/reader3/webdav/", "/", true), "UTF-8");
        Intrinsics.checkNotNullExpressionValue(path, "decode(path, \"UTF-8\")");
        File file = new File(Intrinsics.stringPlus(home, path));
        if (!file.exists()) {
            context.response().setStatusCode(404).end();
            return Unit.INSTANCE;
        }
        ExtKt.deleteRecursively(file);
        context.response().setStatusCode(200).end();
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object webdavMove(@NotNull RoutingContext context, @NotNull Continuation<? super Unit> $completion) throws UnsupportedEncodingException {
        String home = getUserWebdavHome(context);
        String strPath = context.request().path();
        Intrinsics.checkNotNullExpressionValue(strPath, "context.request().path()");
        String path = URLDecoder.decode(StringsKt.replace(strPath, "/reader3/webdav/", "/", true), "UTF-8");
        Intrinsics.checkNotNullExpressionValue(path, "decode(path, \"UTF-8\")");
        File file = new File(Intrinsics.stringPlus(home, path));
        if (!file.exists()) {
            context.response().setStatusCode(412).end();
            return Unit.INSTANCE;
        }
        String destination = context.request().getHeader("Destination");
        if (destination == null) {
            context.response().setStatusCode(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL).end();
            return Unit.INSTANCE;
        }
        URL destinationUrl = new URL(destination);
        String path2 = destinationUrl.getPath();
        String destination2 = path2 == null ? null : StringsKt.replace(path2, "/reader3/webdav/", "/", true);
        if (destination2 == null) {
            context.response().setStatusCode(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL).end();
            return Unit.INSTANCE;
        }
        String overwrite = context.request().getHeader("Overwrite");
        File destinationFile = new File(Intrinsics.stringPlus(home, URLDecoder.decode(destination2, "UTF-8")));
        if (destinationFile.exists()) {
            if (overwrite != null) {
                if (!(overwrite.length() == 0)) {
                    ExtKt.deleteRecursively(destinationFile);
                }
            }
            context.response().setStatusCode(412).end();
            return Unit.INSTANCE;
        }
        file.renameTo(destinationFile);
        context.response().setStatusCode(ByteCode.JSR_W).end();
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object webdavCopy(@NotNull RoutingContext context, @NotNull Continuation<? super Unit> $completion) throws UnsupportedEncodingException {
        String home = getUserWebdavHome(context);
        String strPath = context.request().path();
        Intrinsics.checkNotNullExpressionValue(strPath, "context.request().path()");
        String path = URLDecoder.decode(StringsKt.replace(strPath, "/reader3/webdav/", "/", true), "UTF-8");
        Intrinsics.checkNotNullExpressionValue(path, "decode(path, \"UTF-8\")");
        File file = new File(Intrinsics.stringPlus(home, path));
        if (!file.exists()) {
            context.response().setStatusCode(412).end();
            return Unit.INSTANCE;
        }
        String destination = context.request().getHeader("Destination");
        if (destination == null) {
            context.response().setStatusCode(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL).end();
            return Unit.INSTANCE;
        }
        URL destinationUrl = new URL(destination);
        String path2 = destinationUrl.getPath();
        String destination2 = path2 == null ? null : StringsKt.replace(path2, "/reader3/webdav/", "/", true);
        if (destination2 == null) {
            context.response().setStatusCode(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL).end();
            return Unit.INSTANCE;
        }
        String overwrite = context.request().getHeader("Overwrite");
        File destinationFile = new File(Intrinsics.stringPlus(home, URLDecoder.decode(destination2, "UTF-8")));
        if (destinationFile.exists()) {
            if (overwrite != null) {
                if (!(overwrite.length() == 0)) {
                    ExtKt.deleteRecursively(destinationFile);
                }
            }
            context.response().setStatusCode(412).end();
            return Unit.INSTANCE;
        }
        FilesKt.copyRecursively$default(file, destinationFile, false, null, 6, null);
        context.response().setStatusCode(ByteCode.JSR_W).end();
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object webdavLock(@NotNull RoutingContext context, @NotNull Continuation<? super Unit> $completion) {
        UUID uuidRandomUUID = UUID.randomUUID();
        Intrinsics.checkNotNullExpressionValue(uuidRandomUUID, "randomUUID()");
        String lockToken = Intrinsics.stringPlus("urn:uuid:", uuidRandomUUID);
        String timeout = context.request().getHeader("Timeout");
        if (timeout == null) {
            timeout = "Second-3600";
        }
        String fileUrl = context.request().absoluteURI();
        HttpServerResponse statusCode = context.response().putHeader("Lock-Token", lockToken).setStatusCode(200);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {lockToken, fileUrl, timeout};
        String str = String.format("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n        <D:prop xmlns:D=\"DAV:\">\n            <D:lockdiscovery>\n                <D:activelock>\n                    <D:locktype>\n                        <write />\n                    </D:locktype>\n                    <D:lockscope>\n                        <exclusive />\n                    </D:lockscope>\n                    <D:locktoken>\n                        <D:href>%s</D:href>\n                    </D:locktoken>\n                    <D:lockroot>\n                        <D:href>%s</D:href>\n                    </D:lockroot>\n                    <D:depth>infinity</D:depth>\n                    <D:owner>\n                        <a:href xmlns:a=\"DAV:\">http://www.apple.com/webdav_fs/</a:href>\n                    </D:owner>\n                    <D:timeout>%s</D:timeout>\n                </D:activelock>\n            </D:lockdiscovery>\n        </D:prop>\n        ", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        statusCode.end(str);
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object webdavUnLock(@NotNull RoutingContext context, @NotNull Continuation<? super Unit> $completion) {
        String lockToken = context.request().getHeader("Lock-Token");
        if (lockToken == null) {
            context.response().setStatusCode(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL).end();
            return Unit.INSTANCE;
        }
        context.response().putHeader("Lock-Token", lockToken).setStatusCode(204).end();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object backupToWebdav(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 478
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.WebdavController.backupToWebdav(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
