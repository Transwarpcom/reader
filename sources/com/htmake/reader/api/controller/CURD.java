package com.htmake.reader.api.controller;

import ch.qos.logback.core.CoreConstants;
import com.google.gson.JsonSyntaxException;
import com.htmake.reader.api.ReturnData;
import com.htmake.reader.db.DB;
import com.htmake.reader.utils.ExtKt;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import java.lang.reflect.Array;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: CURD.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��T\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018��*\u0004\b��\u0010\u00012\u00020\u0002J%\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00028��2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028��0\u0007H\u0016¢\u0006\u0002\u0010\bJ%\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00028��2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028��0\u0007H\u0016¢\u0006\u0002\u0010\bJ%\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00028��2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028��0\u0007H\u0016¢\u0006\u0002\u0010\bJ\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH¦@ø\u0001��¢\u0006\u0002\u0010\u000fJ\u001d\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028��H&¢\u0006\u0002\u0010\u0014J\u0015\u0010\u0015\u001a\u00028��2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¢\u0006\u0002\u0010\u0016J\u001b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028��0\u00182\u0006\u0010\u0011\u001a\u00020\u0019H\u0016¢\u0006\u0002\u0010\u001aJ\u0019\u0010\u001b\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0096@ø\u0001��¢\u0006\u0002\u0010\u000fJ\u0019\u0010\u001c\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0096@ø\u0001��¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028��0\u001eH&J\b\u0010\u001f\u001a\u00020\u0019H&J\u0010\u0010 \u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u000eH&J\u0019\u0010!\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0096@ø\u0001��¢\u0006\u0002\u0010\u000fJ%\u0010\"\u001a\u00020#2\u0006\u0010\u0011\u001a\u00028��2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010$\u001a\u00020%H\u0016¢\u0006\u0002\u0010&J\u0018\u0010'\u001a\u00020%2\u0006\u0010\u0011\u001a\u00020%2\u0006\u0010(\u001a\u00020\u0019H\u0016J\u0019\u0010)\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0096@ø\u0001��¢\u0006\u0002\u0010\u000fJ\u0019\u0010*\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0096@ø\u0001��¢\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, d2 = {"Lcom/htmake/reader/api/controller/CURD;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "beforeAdd", "Lcom/htmake/reader/api/ReturnData;", "val1", "db", "Lcom/htmake/reader/db/DB;", "(Ljava/lang/Object;Lcom/htmake/reader/db/DB;)Lcom/htmake/reader/api/ReturnData;", "beforeDelete", "beforeSave", "checkUserAuth", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Lio/vertx/ext/web/RoutingContext;", "(Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checker", "var1", "Lio/vertx/core/json/JsonObject;", "var2", "(Lio/vertx/core/json/JsonObject;Ljava/lang/Object;)Z", "convertToEntity", "(Lio/vertx/core/json/JsonObject;)Ljava/lang/Object;", "convertToEntityList", "", "", "(Ljava/lang/String;)[Ljava/lang/Object;", "delete", "deleteMulti", "getEntityClass", "Ljava/lang/Class;", "getTableName", "getUserNS", BeanDefinitionParserDelegate.LIST_ELEMENT, "onCheckEnd", "", "var3", "Lio/vertx/core/json/JsonArray;", "(Ljava/lang/Object;ZLio/vertx/core/json/JsonArray;)V", "onList", "userNameSpace", "save", "saveMulti", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/CURD.class */
public interface CURD<T> {

    /* compiled from: CURD.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "CURD.kt", l = {121}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "delete", c = "com.htmake.reader.api.controller.CURD$DefaultImpls")
    /* renamed from: com.htmake.reader.api.controller.CURD$delete$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/CURD$delete$1.class */
    static final class AnonymousClass1<T> extends ContinuationImpl {
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
            return DefaultImpls.delete(null, null, this);
        }
    }

    /* compiled from: CURD.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "CURD.kt", l = {143}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "deleteMulti", c = "com.htmake.reader.api.controller.CURD$DefaultImpls")
    /* renamed from: com.htmake.reader.api.controller.CURD$deleteMulti$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/CURD$deleteMulti$1.class */
    static final class C01551<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01551(Continuation<? super C01551> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return DefaultImpls.deleteMulti(null, null, this);
        }
    }

    /* compiled from: CURD.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "CURD.kt", l = {57}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = BeanDefinitionParserDelegate.LIST_ELEMENT, c = "com.htmake.reader.api.controller.CURD$DefaultImpls")
    /* renamed from: com.htmake.reader.api.controller.CURD$list$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/CURD$list$1.class */
    static final class C01571<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01571(Continuation<? super C01571> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return DefaultImpls.list(null, null, this);
        }
    }

    /* compiled from: CURD.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "CURD.kt", l = {68}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "save", c = "com.htmake.reader.api.controller.CURD$DefaultImpls")
    /* renamed from: com.htmake.reader.api.controller.CURD$save$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/CURD$save$1.class */
    static final class C01581<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01581(Continuation<? super C01581> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return DefaultImpls.save(null, null, this);
        }
    }

    /* compiled from: CURD.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "CURD.kt", l = {92}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "saveMulti", c = "com.htmake.reader.api.controller.CURD$DefaultImpls")
    /* renamed from: com.htmake.reader.api.controller.CURD$saveMulti$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/CURD$saveMulti$1.class */
    static final class C01601<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01601(Continuation<? super C01601> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return DefaultImpls.saveMulti(null, null, this);
        }
    }

    @NotNull
    String getTableName();

    T convertToEntity(@NotNull JsonObject var1);

    @NotNull
    T[] convertToEntityList(@NotNull String var1);

    @NotNull
    JsonArray onList(@NotNull JsonArray var1, @NotNull String userNameSpace);

    boolean checker(@NotNull JsonObject var1, T var2);

    void onCheckEnd(T var1, boolean var2, @NotNull JsonArray var3);

    @Nullable
    ReturnData beforeSave(T val1, @NotNull DB<T> db);

    @Nullable
    ReturnData beforeAdd(T val1, @NotNull DB<T> db);

    @Nullable
    ReturnData beforeDelete(T val1, @NotNull DB<T> db);

    @Nullable
    Object checkUserAuth(@NotNull RoutingContext context, @NotNull Continuation<? super Boolean> $completion);

    @NotNull
    String getUserNS(@NotNull RoutingContext context);

    @NotNull
    Class<T> getEntityClass();

    @Nullable
    Object list(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion);

    @Nullable
    Object save(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion);

    @Nullable
    Object saveMulti(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion);

    @Nullable
    Object delete(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion);

    @Nullable
    Object deleteMulti(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion);

    /* compiled from: CURD.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/CURD$DefaultImpls.class */
    public static final class DefaultImpls {
        public static <T> T convertToEntity(@NotNull CURD<T> curd, @NotNull JsonObject var1) {
            Intrinsics.checkNotNullParameter(curd, "this");
            Intrinsics.checkNotNullParameter(var1, "var1");
            return (T) var1.mapTo(curd.getEntityClass());
        }

        @NotNull
        public static <T> T[] convertToEntityList(@NotNull CURD<T> curd, @NotNull String var1) throws JsonSyntaxException {
            Intrinsics.checkNotNullParameter(curd, "this");
            Intrinsics.checkNotNullParameter(var1, "var1");
            Object objFromJson = ExtKt.getGson().fromJson(var1, (Class<Object>) Array.newInstance((Class<?>) curd.getEntityClass(), 0).getClass());
            Intrinsics.checkNotNullExpressionValue(objFromJson, "gson.fromJson(var1, clazz)");
            return (T[]) ((Object[]) objFromJson);
        }

        @NotNull
        public static <T> JsonArray onList(@NotNull CURD<T> curd, @NotNull JsonArray var1, @NotNull String userNameSpace) {
            Intrinsics.checkNotNullParameter(curd, "this");
            Intrinsics.checkNotNullParameter(var1, "var1");
            Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
            return var1;
        }

        public static <T> void onCheckEnd(@NotNull CURD<T> curd, T var1, boolean var2, @NotNull JsonArray var3) {
            Intrinsics.checkNotNullParameter(curd, "this");
            Intrinsics.checkNotNullParameter(var3, "var3");
        }

        @Nullable
        public static <T> ReturnData beforeSave(@NotNull CURD<T> curd, T val1, @NotNull DB<T> db) {
            Intrinsics.checkNotNullParameter(curd, "this");
            Intrinsics.checkNotNullParameter(db, "db");
            return null;
        }

        @Nullable
        public static <T> ReturnData beforeAdd(@NotNull CURD<T> curd, T val1, @NotNull DB<T> db) {
            Intrinsics.checkNotNullParameter(curd, "this");
            Intrinsics.checkNotNullParameter(db, "db");
            return null;
        }

        @Nullable
        public static <T> ReturnData beforeDelete(@NotNull CURD<T> curd, T val1, @NotNull DB<T> db) {
            Intrinsics.checkNotNullParameter(curd, "this");
            Intrinsics.checkNotNullParameter(db, "db");
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static <T> java.lang.Object list(@org.jetbrains.annotations.NotNull com.htmake.reader.api.controller.CURD<T> r7, @org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 278
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.CURD.DefaultImpls.list(com.htmake.reader.api.controller.CURD, io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static <T> java.lang.Object save(@org.jetbrains.annotations.NotNull com.htmake.reader.api.controller.CURD<T> r7, @org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 323
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.CURD.DefaultImpls.save(com.htmake.reader.api.controller.CURD, io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static <T> java.lang.Object saveMulti(@org.jetbrains.annotations.NotNull com.htmake.reader.api.controller.CURD<T> r7, @org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 382
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.CURD.DefaultImpls.saveMulti(com.htmake.reader.api.controller.CURD, io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static <T> java.lang.Object delete(@org.jetbrains.annotations.NotNull com.htmake.reader.api.controller.CURD<T> r7, @org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 312
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.CURD.DefaultImpls.delete(com.htmake.reader.api.controller.CURD, io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static <T> java.lang.Object deleteMulti(@org.jetbrains.annotations.NotNull com.htmake.reader.api.controller.CURD<T> r7, @org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 395
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.CURD.DefaultImpls.deleteMulti(com.htmake.reader.api.controller.CURD, io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* compiled from: CURD.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    /* renamed from: com.htmake.reader.api.controller.CURD$save$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/CURD$save$2.class */
    /* synthetic */ class C01592 extends FunctionReferenceImpl implements Function3<T, Boolean, JsonArray, Unit> {
        C01592(CURD<T> curd) {
            super(3, curd, CURD.class, "onCheckEnd", "onCheckEnd(Ljava/lang/Object;ZLio/vertx/core/json/JsonArray;)V", 0);
        }

        public final void invoke(T p0, boolean p1, @NotNull JsonArray p2) {
            Intrinsics.checkNotNullParameter(p2, "p2");
            ((CURD) this.receiver).onCheckEnd(p0, p1, p2);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(Object p1, Boolean bool, JsonArray jsonArray) {
            invoke((C01592) p1, bool.booleanValue(), jsonArray);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: CURD.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    /* renamed from: com.htmake.reader.api.controller.CURD$saveMulti$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/CURD$saveMulti$2.class */
    /* synthetic */ class C01612 extends FunctionReferenceImpl implements Function3<T, Boolean, JsonArray, Unit> {
        C01612(CURD<T> curd) {
            super(3, curd, CURD.class, "onCheckEnd", "onCheckEnd(Ljava/lang/Object;ZLio/vertx/core/json/JsonArray;)V", 0);
        }

        public final void invoke(T p0, boolean p1, @NotNull JsonArray p2) {
            Intrinsics.checkNotNullParameter(p2, "p2");
            ((CURD) this.receiver).onCheckEnd(p0, p1, p2);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(Object p1, Boolean bool, JsonArray jsonArray) {
            invoke((C01612) p1, bool.booleanValue(), jsonArray);
            return Unit.INSTANCE;
        }
    }
}
