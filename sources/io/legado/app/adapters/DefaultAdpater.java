package io.legado.app.adapters;

import io.legado.app.help.http.StrResponse;
import io.legado.app.model.DebugLog;
import java.io.File;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.ag2s.epublib.epub.NCXDocumentV3;
import org.apache.fontbox.ttf.PostScriptTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DefaultAdapter.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u001f\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0007\"\u00020\u0004¢\u0006\u0002\u0010\bJ\u0091\u0001\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0017\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0096@ø\u0001��¢\u0006\u0002\u0010\u001aJ!\u0010\u001b\u001a\u00020\u00042\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0007\"\u00020\u0004H\u0016¢\u0006\u0002\u0010\bJ\u0010\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0004H\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"Lio/legado/app/adapters/DefaultAdpater;", "Lio/legado/app/adapters/ReaderAdapterInterface;", "()V", "getCacheDir", "", "getRelativePath", "subDirFiles", "", "([Ljava/lang/String;)Ljava/lang/String;", "getStrResponseByRemoteWebview", "Lio/legado/app/help/http/StrResponse;", "url", "html", "encode", "tag", "headerMap", "", "sourceRegex", "javaScript", "proxy", PostScriptTable.TAG, "", NCXDocumentV3.XHTMLTgs.body, "userNameSpace", "debugLog", "Lio/legado/app/model/DebugLog;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Lio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWorkDir", "subPath", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/adapters/DefaultAdpater.class */
public final class DefaultAdpater implements ReaderAdapterInterface {
    /* JADX WARN: Removed duplicated region for block: B:7:0x0060  */
    @Override // io.legado.app.adapters.ReaderAdapterInterface
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getWorkDir(@org.jetbrains.annotations.NotNull java.lang.String r7) {
        /*
            r6 = this;
            r0 = r7
            java.lang.String r1 = "subPath"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            java.lang.String r0 = ""
            r8 = r0
            java.lang.String r0 = "os.name"
            java.lang.String r0 = java.lang.System.getProperty(r0)
            r9 = r0
            java.lang.String r0 = "user.dir"
            java.lang.String r0 = java.lang.System.getProperty(r0)
            r10 = r0
            r0 = r9
            r11 = r0
            r0 = r11
            java.lang.String r1 = "osName"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r0 = r11
            java.lang.String r1 = "Mac OS"
            r2 = 1
            boolean r0 = kotlin.text.StringsKt.startsWith(r0, r1, r2)
            if (r0 == 0) goto L60
            r0 = r10
            r11 = r0
            r0 = r11
            java.lang.String r1 = "currentDir"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r0 = r11
            java.lang.String r1 = "/Users/"
            r2 = 0
            r3 = 2
            r4 = 0
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r1, r2, r3, r4)
            if (r0 != 0) goto L60
            java.lang.String r0 = "user.home"
            java.lang.String r0 = java.lang.System.getProperty(r0)
            r1 = 1
            java.lang.String[] r1 = new java.lang.String[r1]
            r11 = r1
            r1 = r11
            r2 = 0
            java.lang.String r3 = ".reader"
            r1[r2] = r3
            r1 = r11
            java.nio.file.Path r0 = java.nio.file.Paths.get(r0, r1)
            java.lang.String r0 = r0.toString()
            r8 = r0
            goto L6e
        L60:
            r0 = r10
            r11 = r0
            r0 = r11
            java.lang.String r1 = "currentDir"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r0 = r11
            r8 = r0
        L6e:
            r0 = r8
            r1 = 1
            java.lang.String[] r1 = new java.lang.String[r1]
            r12 = r1
            r1 = r12
            r2 = 0
            r3 = r7
            r1[r2] = r3
            r1 = r12
            java.nio.file.Path r0 = java.nio.file.Paths.get(r0, r1)
            r11 = r0
            r0 = r11
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.adapters.DefaultAdpater.getWorkDir(java.lang.String):java.lang.String");
    }

    @Override // io.legado.app.adapters.ReaderAdapterInterface
    @NotNull
    public String getWorkDir(@NotNull String... subDirFiles) {
        Intrinsics.checkNotNullParameter(subDirFiles, "subDirFiles");
        return getWorkDir(getRelativePath((String[]) Arrays.copyOf(subDirFiles, subDirFiles.length)));
    }

    @NotNull
    public final String getRelativePath(@NotNull String... subDirFiles) {
        Intrinsics.checkNotNullParameter(subDirFiles, "subDirFiles");
        StringBuilder path = new StringBuilder("");
        for (String str : subDirFiles) {
            if (str.length() > 0) {
                path.append(File.separator).append(str);
            }
        }
        String it = path.toString();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        if (StringsKt.startsWith$default(it, "/", false, 2, (Object) null)) {
            String strSubstring = it.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            return strSubstring;
        }
        return it;
    }

    @Override // io.legado.app.adapters.ReaderAdapterInterface
    @NotNull
    public String getCacheDir() {
        return getWorkDir("storage", "cache");
    }

    @Override // io.legado.app.adapters.ReaderAdapterInterface
    @Nullable
    public Object getStrResponseByRemoteWebview(@Nullable String url, @Nullable String html, @Nullable String encode, @Nullable String tag, @Nullable Map<String, String> headerMap, @Nullable String sourceRegex, @Nullable String javaScript, @Nullable String proxy, boolean post, @Nullable String body, @NotNull String userNameSpace, @Nullable DebugLog debugLog, @NotNull Continuation<? super StrResponse> $completion) throws Exception {
        throw new Exception("不支持webview");
    }
}
