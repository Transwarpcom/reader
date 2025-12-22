package com.htmake.reader.utils;

import io.legado.app.model.DebugLog;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.NCXDocumentV3;
import org.apache.fontbox.ttf.PostScriptTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RemoteWebview.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��<\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J©\u0001\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u00042\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0086@ø\u0001��¢\u0006\u0002\u0010\u001aJ\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lcom/htmake/reader/utils/RemoteWebview;", "", "()V", "remoteWebviewApi", "", "getRemoteWebviewApi", "()Ljava/lang/String;", "setRemoteWebviewApi", "(Ljava/lang/String;)V", "getStrResponse", "Lio/legado/app/help/http/StrResponse;", "url", "html", "encode", "tag", "headerMap", "", "sourceRegex", "javaScript", "proxy", PostScriptTable.TAG, "", NCXDocumentV3.XHTMLTgs.body, "userNameSpace", "debugLog", "Lio/legado/app/model/DebugLog;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Lio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setRemoteApi", "", "remoteApi", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/utils/RemoteWebview.class */
public final class RemoteWebview {

    @NotNull
    public static final RemoteWebview INSTANCE = new RemoteWebview();

    @NotNull
    private static String remoteWebviewApi = "";

    /* compiled from: RemoteWebview.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "RemoteWebview.kt", l = {47}, i = {}, s = {}, n = {}, m = "getStrResponse", c = "com.htmake.reader.utils.RemoteWebview")
    /* renamed from: com.htmake.reader.utils.RemoteWebview$getStrResponse$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/utils/RemoteWebview$getStrResponse$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
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
            return RemoteWebview.this.getStrResponse(null, null, null, null, null, null, null, null, false, null, null, null, this);
        }
    }

    private RemoteWebview() {
    }

    @NotNull
    public final String getRemoteWebviewApi() {
        return remoteWebviewApi;
    }

    public final void setRemoteWebviewApi(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        remoteWebviewApi = str;
    }

    public final void setRemoteApi(@NotNull String remoteApi) {
        Intrinsics.checkNotNullParameter(remoteApi, "remoteApi");
        remoteWebviewApi = remoteApi;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /* JADX WARN: Type inference failed for: r1v23, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v27, types: [T, java.lang.String] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getStrResponse(@org.jetbrains.annotations.Nullable java.lang.String r8, @org.jetbrains.annotations.Nullable java.lang.String r9, @org.jetbrains.annotations.Nullable java.lang.String r10, @org.jetbrains.annotations.Nullable java.lang.String r11, @org.jetbrains.annotations.Nullable java.util.Map<java.lang.String, java.lang.String> r12, @org.jetbrains.annotations.Nullable java.lang.String r13, @org.jetbrains.annotations.Nullable java.lang.String r14, @org.jetbrains.annotations.Nullable java.lang.String r15, boolean r16, @org.jetbrains.annotations.Nullable java.lang.String r17, @org.jetbrains.annotations.NotNull java.lang.String r18, @org.jetbrains.annotations.Nullable io.legado.app.model.DebugLog r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super io.legado.app.help.http.StrResponse> r20) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 587
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.utils.RemoteWebview.getStrResponse(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Map, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String, java.lang.String, io.legado.app.model.DebugLog, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object getStrResponse$default(RemoteWebview remoteWebview, String str, String str2, String str3, String str4, Map map, String str5, String str6, String str7, boolean z, String str8, String str9, DebugLog debugLog, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            str3 = null;
        }
        if ((i & 8) != 0) {
            str4 = null;
        }
        if ((i & 16) != 0) {
            map = null;
        }
        if ((i & 32) != 0) {
            str5 = null;
        }
        if ((i & 64) != 0) {
            str6 = null;
        }
        if ((i & 128) != 0) {
            str7 = null;
        }
        if ((i & 256) != 0) {
            z = false;
        }
        if ((i & 512) != 0) {
            str8 = null;
        }
        if ((i & 1024) != 0) {
            str9 = "";
        }
        if ((i & 2048) != 0) {
            debugLog = null;
        }
        return remoteWebview.getStrResponse(str, str2, str3, str4, map, str5, str6, str7, z, str8, str9, debugLog, continuation);
    }
}
