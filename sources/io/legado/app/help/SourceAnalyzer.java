package io.legado.app.help;

import cn.hutool.core.text.StrPool;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.Predicate;
import io.legado.app.data.entities.BookSource;
import io.legado.app.exception.NoStackTraceException;
import io.legado.app.utils.GsonExtensionsKt;
import io.legado.app.utils.JsonExtensionsKt;
import io.legado.app.utils.StringExtensionsKt;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import me.ag2s.epublib.epub.NCXDocumentV3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import org.slf4j.Marker;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: SourceAnalyzer.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n��\n\u0002\u0018\u0002\n\u0002\b\f\bÆ\u0002\u0018��2\u00020\u0001:\u0001\u001cB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bø\u0001��ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\f\u0010\rJ*\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000f0\b2\u0006\u0010\u0010\u001a\u00020\u0011ø\u0001��ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0012\u0010\u0013J*\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000f0\b2\u0006\u0010\n\u001a\u00020\u000bø\u0001��ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0012\u0010\rJ\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000bH\u0002J\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u000bH\u0002J\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000bH\u0002J\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u000b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000bH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n��\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"Lio/legado/app/help/SourceAnalyzer;", "", "()V", "headerPattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "jsPattern", "jsonToBookSource", "Lkotlin/Result;", "Lio/legado/app/data/entities/BookSource;", "json", "", "jsonToBookSource-IoAF18A", "(Ljava/lang/String;)Ljava/lang/Object;", "jsonToBookSources", "", "inputStream", "Ljava/io/InputStream;", "jsonToBookSources-IoAF18A", "(Ljava/io/InputStream;)Ljava/lang/Object;", "toNewRule", "oldRule", "toNewUrl", "oldUrl", "toNewUrls", "oldUrls", "uaToHeader", "ua", "BookSourceAny", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/help/SourceAnalyzer.class */
public final class SourceAnalyzer {

    @NotNull
    public static final SourceAnalyzer INSTANCE = new SourceAnalyzer();
    private static final Pattern headerPattern = Pattern.compile("@Header:\\{.+?\\}", 2);
    private static final Pattern jsPattern = Pattern.compile("\\{\\{.+?\\}\\}", 2);

    private SourceAnalyzer() {
    }

    @NotNull
    /* renamed from: jsonToBookSources-IoAF18A, reason: not valid java name */
    public final Object m1109jsonToBookSourcesIoAF18A(@NotNull String json) {
        Object objM2105constructorimpl;
        Intrinsics.checkNotNullParameter(json, "json");
        try {
            Result.Companion companion = Result.Companion;
            List bookSources = new ArrayList();
            if (StringExtensionsKt.isJsonArray(json)) {
                Object obj = JsonExtensionsKt.getJsonPath().parse(json).read(PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX, new Predicate[0]);
                Intrinsics.checkNotNullExpressionValue(obj, "jsonPath.parse(json).read(\"$\")");
                List<Map> items = (List) obj;
                for (Map item : items) {
                    DocumentContext jsonItem = JsonExtensionsKt.getJsonPath().parse(item);
                    SourceAnalyzer sourceAnalyzer = INSTANCE;
                    String strJsonString = jsonItem.jsonString();
                    Intrinsics.checkNotNullExpressionValue(strJsonString, "jsonItem.jsonString()");
                    Object objM1111jsonToBookSourceIoAF18A = sourceAnalyzer.m1111jsonToBookSourceIoAF18A(strJsonString);
                    ResultKt.throwOnFailure(objM1111jsonToBookSourceIoAF18A);
                    BookSource it = (BookSource) objM1111jsonToBookSourceIoAF18A;
                    bookSources.add(it);
                }
            } else if (StringExtensionsKt.isJsonObject(json)) {
                Object objM1111jsonToBookSourceIoAF18A2 = INSTANCE.m1111jsonToBookSourceIoAF18A(json);
                ResultKt.throwOnFailure(objM1111jsonToBookSourceIoAF18A2);
                BookSource it2 = (BookSource) objM1111jsonToBookSourceIoAF18A2;
                bookSources.add(it2);
            } else {
                throw new NoStackTraceException("格式不对");
            }
            objM2105constructorimpl = Result.m2105constructorimpl(bookSources);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
        }
        return objM2105constructorimpl;
    }

    @NotNull
    /* renamed from: jsonToBookSources-IoAF18A, reason: not valid java name */
    public final Object m1110jsonToBookSourcesIoAF18A(@NotNull InputStream inputStream) {
        Object objM2105constructorimpl;
        Object objM2105constructorimpl2;
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        try {
            Result.Companion companion = Result.Companion;
            List bookSources = new ArrayList();
            try {
                Result.Companion companion2 = Result.Companion;
                Object obj = JsonExtensionsKt.getJsonPath().parse(inputStream).read(PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX, new Predicate[0]);
                Intrinsics.checkNotNullExpressionValue(obj, "jsonPath.parse(inputStream).read(\"$\")");
                List<Map> items = (List) obj;
                for (Map item : items) {
                    DocumentContext jsonItem = JsonExtensionsKt.getJsonPath().parse(item);
                    SourceAnalyzer sourceAnalyzer = INSTANCE;
                    String strJsonString = jsonItem.jsonString();
                    Intrinsics.checkNotNullExpressionValue(strJsonString, "jsonItem.jsonString()");
                    Object objM1111jsonToBookSourceIoAF18A = sourceAnalyzer.m1111jsonToBookSourceIoAF18A(strJsonString);
                    ResultKt.throwOnFailure(objM1111jsonToBookSourceIoAF18A);
                    BookSource it = (BookSource) objM1111jsonToBookSourceIoAF18A;
                    bookSources.add(it);
                }
                objM2105constructorimpl2 = Result.m2105constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion3 = Result.Companion;
                objM2105constructorimpl2 = Result.m2105constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m2103exceptionOrNullimpl(objM2105constructorimpl2) != null) {
                Object obj2 = JsonExtensionsKt.getJsonPath().parse(inputStream).read(PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX, new Predicate[0]);
                Intrinsics.checkNotNullExpressionValue(obj2, "jsonPath.parse(inputStream).read(\"$\")");
                Map item2 = (Map) obj2;
                DocumentContext jsonItem2 = JsonExtensionsKt.getJsonPath().parse(item2);
                SourceAnalyzer sourceAnalyzer2 = INSTANCE;
                String strJsonString2 = jsonItem2.jsonString();
                Intrinsics.checkNotNullExpressionValue(strJsonString2, "jsonItem.jsonString()");
                Object objM1111jsonToBookSourceIoAF18A2 = sourceAnalyzer2.m1111jsonToBookSourceIoAF18A(strJsonString2);
                ResultKt.throwOnFailure(objM1111jsonToBookSourceIoAF18A2);
                BookSource it2 = (BookSource) objM1111jsonToBookSourceIoAF18A2;
                bookSources.add(it2);
            }
            objM2105constructorimpl = Result.m2105constructorimpl(bookSources);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.Companion;
            objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th2));
        }
        return objM2105constructorimpl;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0342  */
    @org.jetbrains.annotations.NotNull
    /* renamed from: jsonToBookSource-IoAF18A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m1111jsonToBookSourceIoAF18A(@org.jetbrains.annotations.NotNull java.lang.String r34) {
        /*
            Method dump skipped, instructions count: 3469
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.help.SourceAnalyzer.m1111jsonToBookSourceIoAF18A(java.lang.String):java.lang.Object");
    }

    /* compiled from: SourceAnalyzer.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\be\b\u0086\b\u0018��2\u00020\u0001B\u009d\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u001fJ\t\u0010\\\u001a\u00020\u0003HÆ\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010c\u001a\u00020\u0015HÆ\u0003J\t\u0010d\u001a\u00020\u0015HÆ\u0003J\t\u0010e\u001a\u00020\u0007HÆ\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010i\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\t\u0010n\u001a\u00020\u0003HÆ\u0003J\t\u0010o\u001a\u00020\u0007HÆ\u0003J\u000b\u0010p\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010q\u001a\u00020\u0007HÆ\u0003J\t\u0010r\u001a\u00020\u000bHÆ\u0003J\t\u0010s\u001a\u00020\u000bHÆ\u0003J\t\u0010t\u001a\u00020\u000bHÆ\u0003J¡\u0002\u0010u\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00072\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÆ\u0001J\u0013\u0010v\u001a\u00020\u000b2\b\u0010w\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010x\u001a\u00020\u0007HÖ\u0001J\t\u0010y\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b$\u0010!\"\u0004\b%\u0010#R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b&\u0010!\"\u0004\b'\u0010#R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b,\u0010!\"\u0004\b-\u0010#R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b.\u0010!\"\u0004\b/\u0010#R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b0\u0010!\"\u0004\b1\u0010#R\u001a\u0010\t\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b2\u0010)\"\u0004\b3\u0010+R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001a\u0010\r\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b8\u00105\"\u0004\b9\u00107R\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b:\u00105\"\u0004\b;\u00107R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b<\u0010!\"\u0004\b=\u0010#R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b>\u0010!\"\u0004\b?\u0010#R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bD\u0010!\"\u0004\bE\u0010#R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bJ\u0010G\"\u0004\bK\u0010IR\u001a\u0010\u0016\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bL\u0010A\"\u0004\bM\u0010CR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bN\u0010G\"\u0004\bO\u0010IR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bP\u0010G\"\u0004\bQ\u0010IR\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bR\u0010G\"\u0004\bS\u0010IR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bT\u0010G\"\u0004\bU\u0010IR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bV\u0010G\"\u0004\bW\u0010IR\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bX\u0010!\"\u0004\bY\u0010#R\u001a\u0010\u0017\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bZ\u0010)\"\u0004\b[\u0010+¨\u0006z"}, d2 = {"Lio/legado/app/help/SourceAnalyzer$BookSourceAny;", "", "bookSourceName", "", "bookSourceGroup", "bookSourceUrl", "bookSourceType", "", "bookUrlPattern", "customOrder", "enabled", "", "enabledExplore", "enabledCookieJar", "concurrentRate", "header", "loginUrl", "loginUi", "loginCheckJs", "bookSourceComment", "lastUpdateTime", "", "respondTime", "weight", "exploreUrl", "ruleExplore", "searchUrl", "ruleSearch", "ruleBookInfo", "ruleToc", "ruleContent", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;IZZZLjava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;JJILjava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "getBookSourceComment", "()Ljava/lang/String;", "setBookSourceComment", "(Ljava/lang/String;)V", "getBookSourceGroup", "setBookSourceGroup", "getBookSourceName", "setBookSourceName", "getBookSourceType", "()I", "setBookSourceType", "(I)V", "getBookSourceUrl", "setBookSourceUrl", "getBookUrlPattern", "setBookUrlPattern", "getConcurrentRate", "setConcurrentRate", "getCustomOrder", "setCustomOrder", "getEnabled", "()Z", "setEnabled", "(Z)V", "getEnabledCookieJar", "setEnabledCookieJar", "getEnabledExplore", "setEnabledExplore", "getExploreUrl", "setExploreUrl", "getHeader", "setHeader", "getLastUpdateTime", "()J", "setLastUpdateTime", "(J)V", "getLoginCheckJs", "setLoginCheckJs", "getLoginUi", "()Ljava/lang/Object;", "setLoginUi", "(Ljava/lang/Object;)V", "getLoginUrl", "setLoginUrl", "getRespondTime", "setRespondTime", "getRuleBookInfo", "setRuleBookInfo", "getRuleContent", "setRuleContent", "getRuleExplore", "setRuleExplore", "getRuleSearch", "setRuleSearch", "getRuleToc", "setRuleToc", "getSearchUrl", "setSearchUrl", "getWeight", "setWeight", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", IdentityNamingStrategy.HASH_CODE_KEY, "toString", "reader-pro"})
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/help/SourceAnalyzer$BookSourceAny.class */
    public static final class BookSourceAny {

        @NotNull
        private String bookSourceName;

        @Nullable
        private String bookSourceGroup;

        @NotNull
        private String bookSourceUrl;
        private int bookSourceType;

        @Nullable
        private String bookUrlPattern;
        private int customOrder;
        private boolean enabled;
        private boolean enabledExplore;
        private boolean enabledCookieJar;

        @Nullable
        private String concurrentRate;

        @Nullable
        private String header;

        @Nullable
        private Object loginUrl;

        @Nullable
        private Object loginUi;

        @Nullable
        private String loginCheckJs;

        @Nullable
        private String bookSourceComment;
        private long lastUpdateTime;
        private long respondTime;
        private int weight;

        @Nullable
        private String exploreUrl;

        @Nullable
        private Object ruleExplore;

        @Nullable
        private String searchUrl;

        @Nullable
        private Object ruleSearch;

        @Nullable
        private Object ruleBookInfo;

        @Nullable
        private Object ruleToc;

        @Nullable
        private Object ruleContent;

        @NotNull
        public final String component1() {
            return this.bookSourceName;
        }

        @Nullable
        public final String component2() {
            return this.bookSourceGroup;
        }

        @NotNull
        public final String component3() {
            return this.bookSourceUrl;
        }

        public final int component4() {
            return this.bookSourceType;
        }

        @Nullable
        public final String component5() {
            return this.bookUrlPattern;
        }

        public final int component6() {
            return this.customOrder;
        }

        public final boolean component7() {
            return this.enabled;
        }

        public final boolean component8() {
            return this.enabledExplore;
        }

        public final boolean component9() {
            return this.enabledCookieJar;
        }

        @Nullable
        public final String component10() {
            return this.concurrentRate;
        }

        @Nullable
        public final String component11() {
            return this.header;
        }

        @Nullable
        public final Object component12() {
            return this.loginUrl;
        }

        @Nullable
        public final Object component13() {
            return this.loginUi;
        }

        @Nullable
        public final String component14() {
            return this.loginCheckJs;
        }

        @Nullable
        public final String component15() {
            return this.bookSourceComment;
        }

        public final long component16() {
            return this.lastUpdateTime;
        }

        public final long component17() {
            return this.respondTime;
        }

        public final int component18() {
            return this.weight;
        }

        @Nullable
        public final String component19() {
            return this.exploreUrl;
        }

        @Nullable
        public final Object component20() {
            return this.ruleExplore;
        }

        @Nullable
        public final String component21() {
            return this.searchUrl;
        }

        @Nullable
        public final Object component22() {
            return this.ruleSearch;
        }

        @Nullable
        public final Object component23() {
            return this.ruleBookInfo;
        }

        @Nullable
        public final Object component24() {
            return this.ruleToc;
        }

        @Nullable
        public final Object component25() {
            return this.ruleContent;
        }

        @NotNull
        public final BookSourceAny copy(@NotNull String bookSourceName, @Nullable String bookSourceGroup, @NotNull String bookSourceUrl, int bookSourceType, @Nullable String bookUrlPattern, int customOrder, boolean enabled, boolean enabledExplore, boolean enabledCookieJar, @Nullable String concurrentRate, @Nullable String header, @Nullable Object loginUrl, @Nullable Object loginUi, @Nullable String loginCheckJs, @Nullable String bookSourceComment, long lastUpdateTime, long respondTime, int weight, @Nullable String exploreUrl, @Nullable Object ruleExplore, @Nullable String searchUrl, @Nullable Object ruleSearch, @Nullable Object ruleBookInfo, @Nullable Object ruleToc, @Nullable Object ruleContent) {
            Intrinsics.checkNotNullParameter(bookSourceName, "bookSourceName");
            Intrinsics.checkNotNullParameter(bookSourceUrl, "bookSourceUrl");
            return new BookSourceAny(bookSourceName, bookSourceGroup, bookSourceUrl, bookSourceType, bookUrlPattern, customOrder, enabled, enabledExplore, enabledCookieJar, concurrentRate, header, loginUrl, loginUi, loginCheckJs, bookSourceComment, lastUpdateTime, respondTime, weight, exploreUrl, ruleExplore, searchUrl, ruleSearch, ruleBookInfo, ruleToc, ruleContent);
        }

        public static /* synthetic */ BookSourceAny copy$default(BookSourceAny bookSourceAny, String str, String str2, String str3, int i, String str4, int i2, boolean z, boolean z2, boolean z3, String str5, String str6, Object obj, Object obj2, String str7, String str8, long j, long j2, int i3, String str9, Object obj3, String str10, Object obj4, Object obj5, Object obj6, Object obj7, int i4, Object obj8) {
            if ((i4 & 1) != 0) {
                str = bookSourceAny.bookSourceName;
            }
            if ((i4 & 2) != 0) {
                str2 = bookSourceAny.bookSourceGroup;
            }
            if ((i4 & 4) != 0) {
                str3 = bookSourceAny.bookSourceUrl;
            }
            if ((i4 & 8) != 0) {
                i = bookSourceAny.bookSourceType;
            }
            if ((i4 & 16) != 0) {
                str4 = bookSourceAny.bookUrlPattern;
            }
            if ((i4 & 32) != 0) {
                i2 = bookSourceAny.customOrder;
            }
            if ((i4 & 64) != 0) {
                z = bookSourceAny.enabled;
            }
            if ((i4 & 128) != 0) {
                z2 = bookSourceAny.enabledExplore;
            }
            if ((i4 & 256) != 0) {
                z3 = bookSourceAny.enabledCookieJar;
            }
            if ((i4 & 512) != 0) {
                str5 = bookSourceAny.concurrentRate;
            }
            if ((i4 & 1024) != 0) {
                str6 = bookSourceAny.header;
            }
            if ((i4 & 2048) != 0) {
                obj = bookSourceAny.loginUrl;
            }
            if ((i4 & 4096) != 0) {
                obj2 = bookSourceAny.loginUi;
            }
            if ((i4 & 8192) != 0) {
                str7 = bookSourceAny.loginCheckJs;
            }
            if ((i4 & 16384) != 0) {
                str8 = bookSourceAny.bookSourceComment;
            }
            if ((i4 & 32768) != 0) {
                j = bookSourceAny.lastUpdateTime;
            }
            if ((i4 & 65536) != 0) {
                j2 = bookSourceAny.respondTime;
            }
            if ((i4 & 131072) != 0) {
                i3 = bookSourceAny.weight;
            }
            if ((i4 & 262144) != 0) {
                str9 = bookSourceAny.exploreUrl;
            }
            if ((i4 & Opcodes.ASM8) != 0) {
                obj3 = bookSourceAny.ruleExplore;
            }
            if ((i4 & 1048576) != 0) {
                str10 = bookSourceAny.searchUrl;
            }
            if ((i4 & 2097152) != 0) {
                obj4 = bookSourceAny.ruleSearch;
            }
            if ((i4 & 4194304) != 0) {
                obj5 = bookSourceAny.ruleBookInfo;
            }
            if ((i4 & 8388608) != 0) {
                obj6 = bookSourceAny.ruleToc;
            }
            if ((i4 & 16777216) != 0) {
                obj7 = bookSourceAny.ruleContent;
            }
            return bookSourceAny.copy(str, str2, str3, i, str4, i2, z, z2, z3, str5, str6, obj, obj2, str7, str8, j, j2, i3, str9, obj3, str10, obj4, obj5, obj6, obj7);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("BookSourceAny(bookSourceName=").append(this.bookSourceName).append(", bookSourceGroup=").append((Object) this.bookSourceGroup).append(", bookSourceUrl=").append(this.bookSourceUrl).append(", bookSourceType=").append(this.bookSourceType).append(", bookUrlPattern=").append((Object) this.bookUrlPattern).append(", customOrder=").append(this.customOrder).append(", enabled=").append(this.enabled).append(", enabledExplore=").append(this.enabledExplore).append(", enabledCookieJar=").append(this.enabledCookieJar).append(", concurrentRate=").append((Object) this.concurrentRate).append(", header=").append((Object) this.header).append(", loginUrl=");
            sb.append(this.loginUrl).append(", loginUi=").append(this.loginUi).append(", loginCheckJs=").append((Object) this.loginCheckJs).append(", bookSourceComment=").append((Object) this.bookSourceComment).append(", lastUpdateTime=").append(this.lastUpdateTime).append(", respondTime=").append(this.respondTime).append(", weight=").append(this.weight).append(", exploreUrl=").append((Object) this.exploreUrl).append(", ruleExplore=").append(this.ruleExplore).append(", searchUrl=").append((Object) this.searchUrl).append(", ruleSearch=").append(this.ruleSearch).append(", ruleBookInfo=").append(this.ruleBookInfo);
            sb.append(", ruleToc=").append(this.ruleToc).append(", ruleContent=").append(this.ruleContent).append(')');
            return sb.toString();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int iHashCode = ((((((((((this.bookSourceName.hashCode() * 31) + (this.bookSourceGroup == null ? 0 : this.bookSourceGroup.hashCode())) * 31) + this.bookSourceUrl.hashCode()) * 31) + Integer.hashCode(this.bookSourceType)) * 31) + (this.bookUrlPattern == null ? 0 : this.bookUrlPattern.hashCode())) * 31) + Integer.hashCode(this.customOrder)) * 31;
            boolean z = this.enabled;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (iHashCode + i) * 31;
            boolean z2 = this.enabledExplore;
            int i3 = z2;
            if (z2 != 0) {
                i3 = 1;
            }
            int i4 = (i2 + i3) * 31;
            boolean z3 = this.enabledCookieJar;
            int i5 = z3;
            if (z3 != 0) {
                i5 = 1;
            }
            return ((((((((((((((((((((((((((((((((i4 + i5) * 31) + (this.concurrentRate == null ? 0 : this.concurrentRate.hashCode())) * 31) + (this.header == null ? 0 : this.header.hashCode())) * 31) + (this.loginUrl == null ? 0 : this.loginUrl.hashCode())) * 31) + (this.loginUi == null ? 0 : this.loginUi.hashCode())) * 31) + (this.loginCheckJs == null ? 0 : this.loginCheckJs.hashCode())) * 31) + (this.bookSourceComment == null ? 0 : this.bookSourceComment.hashCode())) * 31) + Long.hashCode(this.lastUpdateTime)) * 31) + Long.hashCode(this.respondTime)) * 31) + Integer.hashCode(this.weight)) * 31) + (this.exploreUrl == null ? 0 : this.exploreUrl.hashCode())) * 31) + (this.ruleExplore == null ? 0 : this.ruleExplore.hashCode())) * 31) + (this.searchUrl == null ? 0 : this.searchUrl.hashCode())) * 31) + (this.ruleSearch == null ? 0 : this.ruleSearch.hashCode())) * 31) + (this.ruleBookInfo == null ? 0 : this.ruleBookInfo.hashCode())) * 31) + (this.ruleToc == null ? 0 : this.ruleToc.hashCode())) * 31) + (this.ruleContent == null ? 0 : this.ruleContent.hashCode());
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BookSourceAny)) {
                return false;
            }
            BookSourceAny bookSourceAny = (BookSourceAny) other;
            return Intrinsics.areEqual(this.bookSourceName, bookSourceAny.bookSourceName) && Intrinsics.areEqual(this.bookSourceGroup, bookSourceAny.bookSourceGroup) && Intrinsics.areEqual(this.bookSourceUrl, bookSourceAny.bookSourceUrl) && this.bookSourceType == bookSourceAny.bookSourceType && Intrinsics.areEqual(this.bookUrlPattern, bookSourceAny.bookUrlPattern) && this.customOrder == bookSourceAny.customOrder && this.enabled == bookSourceAny.enabled && this.enabledExplore == bookSourceAny.enabledExplore && this.enabledCookieJar == bookSourceAny.enabledCookieJar && Intrinsics.areEqual(this.concurrentRate, bookSourceAny.concurrentRate) && Intrinsics.areEqual(this.header, bookSourceAny.header) && Intrinsics.areEqual(this.loginUrl, bookSourceAny.loginUrl) && Intrinsics.areEqual(this.loginUi, bookSourceAny.loginUi) && Intrinsics.areEqual(this.loginCheckJs, bookSourceAny.loginCheckJs) && Intrinsics.areEqual(this.bookSourceComment, bookSourceAny.bookSourceComment) && this.lastUpdateTime == bookSourceAny.lastUpdateTime && this.respondTime == bookSourceAny.respondTime && this.weight == bookSourceAny.weight && Intrinsics.areEqual(this.exploreUrl, bookSourceAny.exploreUrl) && Intrinsics.areEqual(this.ruleExplore, bookSourceAny.ruleExplore) && Intrinsics.areEqual(this.searchUrl, bookSourceAny.searchUrl) && Intrinsics.areEqual(this.ruleSearch, bookSourceAny.ruleSearch) && Intrinsics.areEqual(this.ruleBookInfo, bookSourceAny.ruleBookInfo) && Intrinsics.areEqual(this.ruleToc, bookSourceAny.ruleToc) && Intrinsics.areEqual(this.ruleContent, bookSourceAny.ruleContent);
        }

        public BookSourceAny() {
            this(null, null, null, 0, null, 0, false, false, false, null, null, null, null, null, null, 0L, 0L, 0, null, null, null, null, null, null, null, 33554431, null);
        }

        public BookSourceAny(@NotNull String bookSourceName, @Nullable String bookSourceGroup, @NotNull String bookSourceUrl, int bookSourceType, @Nullable String bookUrlPattern, int customOrder, boolean enabled, boolean enabledExplore, boolean enabledCookieJar, @Nullable String concurrentRate, @Nullable String header, @Nullable Object loginUrl, @Nullable Object loginUi, @Nullable String loginCheckJs, @Nullable String bookSourceComment, long lastUpdateTime, long respondTime, int weight, @Nullable String exploreUrl, @Nullable Object ruleExplore, @Nullable String searchUrl, @Nullable Object ruleSearch, @Nullable Object ruleBookInfo, @Nullable Object ruleToc, @Nullable Object ruleContent) {
            Intrinsics.checkNotNullParameter(bookSourceName, "bookSourceName");
            Intrinsics.checkNotNullParameter(bookSourceUrl, "bookSourceUrl");
            this.bookSourceName = bookSourceName;
            this.bookSourceGroup = bookSourceGroup;
            this.bookSourceUrl = bookSourceUrl;
            this.bookSourceType = bookSourceType;
            this.bookUrlPattern = bookUrlPattern;
            this.customOrder = customOrder;
            this.enabled = enabled;
            this.enabledExplore = enabledExplore;
            this.enabledCookieJar = enabledCookieJar;
            this.concurrentRate = concurrentRate;
            this.header = header;
            this.loginUrl = loginUrl;
            this.loginUi = loginUi;
            this.loginCheckJs = loginCheckJs;
            this.bookSourceComment = bookSourceComment;
            this.lastUpdateTime = lastUpdateTime;
            this.respondTime = respondTime;
            this.weight = weight;
            this.exploreUrl = exploreUrl;
            this.ruleExplore = ruleExplore;
            this.searchUrl = searchUrl;
            this.ruleSearch = ruleSearch;
            this.ruleBookInfo = ruleBookInfo;
            this.ruleToc = ruleToc;
            this.ruleContent = ruleContent;
        }

        public /* synthetic */ BookSourceAny(String str, String str2, String str3, int i, String str4, int i2, boolean z, boolean z2, boolean z3, String str5, String str6, Object obj, Object obj2, String str7, String str8, long j, long j2, int i3, String str9, Object obj3, String str10, Object obj4, Object obj5, Object obj6, Object obj7, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this((i4 & 1) != 0 ? "" : str, (i4 & 2) != 0 ? null : str2, (i4 & 4) != 0 ? "" : str3, (i4 & 8) != 0 ? 0 : i, (i4 & 16) != 0 ? null : str4, (i4 & 32) != 0 ? 0 : i2, (i4 & 64) != 0 ? true : z, (i4 & 128) != 0 ? true : z2, (i4 & 256) != 0 ? false : z3, (i4 & 512) != 0 ? null : str5, (i4 & 1024) != 0 ? null : str6, (i4 & 2048) != 0 ? null : obj, (i4 & 4096) != 0 ? null : obj2, (i4 & 8192) != 0 ? null : str7, (i4 & 16384) != 0 ? "" : str8, (i4 & 32768) != 0 ? 0L : j, (i4 & 65536) != 0 ? 180000L : j2, (i4 & 131072) != 0 ? 0 : i3, (i4 & 262144) != 0 ? null : str9, (i4 & Opcodes.ASM8) != 0 ? null : obj3, (i4 & 1048576) != 0 ? null : str10, (i4 & 2097152) != 0 ? null : obj4, (i4 & 4194304) != 0 ? null : obj5, (i4 & 8388608) != 0 ? null : obj6, (i4 & 16777216) != 0 ? null : obj7);
        }

        @NotNull
        public final String getBookSourceName() {
            return this.bookSourceName;
        }

        public final void setBookSourceName(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.bookSourceName = str;
        }

        @Nullable
        public final String getBookSourceGroup() {
            return this.bookSourceGroup;
        }

        public final void setBookSourceGroup(@Nullable String str) {
            this.bookSourceGroup = str;
        }

        @NotNull
        public final String getBookSourceUrl() {
            return this.bookSourceUrl;
        }

        public final void setBookSourceUrl(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.bookSourceUrl = str;
        }

        public final int getBookSourceType() {
            return this.bookSourceType;
        }

        public final void setBookSourceType(int i) {
            this.bookSourceType = i;
        }

        @Nullable
        public final String getBookUrlPattern() {
            return this.bookUrlPattern;
        }

        public final void setBookUrlPattern(@Nullable String str) {
            this.bookUrlPattern = str;
        }

        public final int getCustomOrder() {
            return this.customOrder;
        }

        public final void setCustomOrder(int i) {
            this.customOrder = i;
        }

        public final boolean getEnabled() {
            return this.enabled;
        }

        public final void setEnabled(boolean z) {
            this.enabled = z;
        }

        public final boolean getEnabledExplore() {
            return this.enabledExplore;
        }

        public final void setEnabledExplore(boolean z) {
            this.enabledExplore = z;
        }

        public final boolean getEnabledCookieJar() {
            return this.enabledCookieJar;
        }

        public final void setEnabledCookieJar(boolean z) {
            this.enabledCookieJar = z;
        }

        @Nullable
        public final String getConcurrentRate() {
            return this.concurrentRate;
        }

        public final void setConcurrentRate(@Nullable String str) {
            this.concurrentRate = str;
        }

        @Nullable
        public final String getHeader() {
            return this.header;
        }

        public final void setHeader(@Nullable String str) {
            this.header = str;
        }

        @Nullable
        public final Object getLoginUrl() {
            return this.loginUrl;
        }

        public final void setLoginUrl(@Nullable Object obj) {
            this.loginUrl = obj;
        }

        @Nullable
        public final Object getLoginUi() {
            return this.loginUi;
        }

        public final void setLoginUi(@Nullable Object obj) {
            this.loginUi = obj;
        }

        @Nullable
        public final String getLoginCheckJs() {
            return this.loginCheckJs;
        }

        public final void setLoginCheckJs(@Nullable String str) {
            this.loginCheckJs = str;
        }

        @Nullable
        public final String getBookSourceComment() {
            return this.bookSourceComment;
        }

        public final void setBookSourceComment(@Nullable String str) {
            this.bookSourceComment = str;
        }

        public final long getLastUpdateTime() {
            return this.lastUpdateTime;
        }

        public final void setLastUpdateTime(long j) {
            this.lastUpdateTime = j;
        }

        public final long getRespondTime() {
            return this.respondTime;
        }

        public final void setRespondTime(long j) {
            this.respondTime = j;
        }

        public final int getWeight() {
            return this.weight;
        }

        public final void setWeight(int i) {
            this.weight = i;
        }

        @Nullable
        public final String getExploreUrl() {
            return this.exploreUrl;
        }

        public final void setExploreUrl(@Nullable String str) {
            this.exploreUrl = str;
        }

        @Nullable
        public final Object getRuleExplore() {
            return this.ruleExplore;
        }

        public final void setRuleExplore(@Nullable Object obj) {
            this.ruleExplore = obj;
        }

        @Nullable
        public final String getSearchUrl() {
            return this.searchUrl;
        }

        public final void setSearchUrl(@Nullable String str) {
            this.searchUrl = str;
        }

        @Nullable
        public final Object getRuleSearch() {
            return this.ruleSearch;
        }

        public final void setRuleSearch(@Nullable Object obj) {
            this.ruleSearch = obj;
        }

        @Nullable
        public final Object getRuleBookInfo() {
            return this.ruleBookInfo;
        }

        public final void setRuleBookInfo(@Nullable Object obj) {
            this.ruleBookInfo = obj;
        }

        @Nullable
        public final Object getRuleToc() {
            return this.ruleToc;
        }

        public final void setRuleToc(@Nullable Object obj) {
            this.ruleToc = obj;
        }

        @Nullable
        public final Object getRuleContent() {
            return this.ruleContent;
        }

        public final void setRuleContent(@Nullable Object obj) {
            this.ruleContent = obj;
        }
    }

    private final String toNewRule(String oldRule) {
        String str = oldRule;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        String newRule = oldRule;
        boolean reverse = false;
        boolean allinone = false;
        if (StringsKt.startsWith$default(oldRule, "-", false, 2, (Object) null)) {
            reverse = true;
            if (oldRule == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = oldRule.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            newRule = strSubstring;
        }
        if (StringsKt.startsWith$default(newRule, Marker.ANY_NON_NULL_MARKER, false, 2, (Object) null)) {
            allinone = true;
            String str2 = newRule;
            if (str2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring2 = str2.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.String).substring(startIndex)");
            newRule = strSubstring2;
        }
        if (!StringsKt.startsWith(newRule, "@CSS:", true) && !StringsKt.startsWith(newRule, "@XPath:", true) && !StringsKt.startsWith$default(newRule, "//", false, 2, (Object) null) && !StringsKt.startsWith$default(newRule, "##", false, 2, (Object) null) && !StringsKt.startsWith$default(newRule, ":", false, 2, (Object) null) && !StringsKt.contains((CharSequence) newRule, (CharSequence) "@js:", true) && !StringsKt.contains((CharSequence) newRule, (CharSequence) "<js>", true)) {
            if (StringsKt.contains$default((CharSequence) newRule, (CharSequence) "#", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) newRule, (CharSequence) "##", false, 2, (Object) null)) {
                newRule = StringsKt.replace$default(oldRule, "#", "##", false, 4, (Object) null);
            }
            if (StringsKt.contains$default((CharSequence) newRule, (CharSequence) "|", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) newRule, (CharSequence) "||", false, 2, (Object) null)) {
                if (StringsKt.contains$default((CharSequence) newRule, (CharSequence) "##", false, 2, (Object) null)) {
                    List list = StringsKt.split$default((CharSequence) newRule, new String[]{"##"}, false, 0, 6, (Object) null);
                    if (StringsKt.contains$default((CharSequence) list.get(0), (CharSequence) "|", false, 2, (Object) null)) {
                        newRule = StringsKt.replace$default((String) list.get(0), "|", "||", false, 4, (Object) null);
                        int i = 1;
                        int size = list.size();
                        if (1 < size) {
                            do {
                                int i2 = i;
                                i++;
                                newRule = ((Object) newRule) + "##" + ((String) list.get(i2));
                            } while (i < size);
                        }
                    }
                } else {
                    newRule = StringsKt.replace$default(newRule, "|", "||", false, 4, (Object) null);
                }
            }
            if (StringsKt.contains$default((CharSequence) newRule, (CharSequence) BeanFactory.FACTORY_BEAN_PREFIX, false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) newRule, (CharSequence) "&&", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) newRule, (CharSequence) "http", false, 2, (Object) null) && !StringsKt.startsWith$default(newRule, "/", false, 2, (Object) null)) {
                newRule = StringsKt.replace$default(newRule, BeanFactory.FACTORY_BEAN_PREFIX, "&&", false, 4, (Object) null);
            }
        }
        if (allinone) {
            newRule = Intrinsics.stringPlus(Marker.ANY_NON_NULL_MARKER, newRule);
        }
        if (reverse) {
            newRule = Intrinsics.stringPlus("-", newRule);
        }
        return newRule;
    }

    private final String toNewUrls(String oldUrls) {
        String str = oldUrls;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        if (StringsKt.startsWith$default(oldUrls, "@js:", false, 2, (Object) null) || StringsKt.startsWith$default(oldUrls, "<js>", false, 2, (Object) null)) {
            return oldUrls;
        }
        if (!StringsKt.contains$default((CharSequence) oldUrls, (CharSequence) "\n", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) oldUrls, (CharSequence) "&&", false, 2, (Object) null)) {
            return toNewUrl(oldUrls);
        }
        Iterable urls = new Regex("(&&|\r?\n)+").split(oldUrls, 0);
        Iterable $this$map$iv = urls;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            String newUrl = INSTANCE.toNewUrl(it);
            destination$iv$iv.add(newUrl == null ? null : new Regex("\n\\s*").replace(newUrl, ""));
        }
        return CollectionsKt.joinToString$default((List) destination$iv$iv, "\n", null, null, 0, null, null, 62, null);
    }

    private final String toNewUrl(String oldUrl) {
        String str = oldUrl;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        String url = oldUrl;
        if (StringsKt.startsWith(oldUrl, "<js>", true)) {
            return StringsKt.replace$default(StringsKt.replace$default(url, "=searchKey", "={{key}}", false, 4, (Object) null), "=searchPage", "={{page}}", false, 4, (Object) null);
        }
        HashMap map = new HashMap();
        Matcher mather = headerPattern.matcher(url);
        if (mather.find()) {
            String header = mather.group();
            Intrinsics.checkNotNullExpressionValue(header, "header");
            url = StringsKt.replace$default(url, header, "", false, 4, (Object) null);
            HashMap map2 = map;
            String strSubstring = header.substring(8);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            map2.put("headers", strSubstring);
        }
        List urlList = StringsKt.split$default((CharSequence) url, new String[]{"|"}, false, 0, 6, (Object) null);
        String url2 = (String) urlList.get(0);
        if (urlList.size() > 1) {
            map.put("charset", StringsKt.split$default((CharSequence) urlList.get(1), new String[]{"="}, false, 0, 6, (Object) null).get(1));
        }
        Matcher mather2 = jsPattern.matcher(url2);
        ArrayList jsList = new ArrayList();
        while (mather2.find()) {
            jsList.add(mather2.group());
            url2 = StringsKt.replace$default(url2, (String) CollectionsKt.last((List) jsList), Intrinsics.stringPlus(PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX, Integer.valueOf(jsList.size() - 1)), false, 4, (Object) null);
        }
        String url3 = StringsKt.replace$default(new Regex("searchPage([-+]1)").replace(new Regex("<searchPage([-+]1)>").replace(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(url2, StrPool.DELIM_START, "<", false, 4, (Object) null), "}", ">", false, 4, (Object) null), "searchKey", "{{key}}", false, 4, (Object) null), "{{page$1}}"), "{{page$1}}"), "searchPage", "{{page}}", false, 4, (Object) null);
        Iterator it = jsList.iterator();
        int i = 0;
        while (it.hasNext()) {
            int index = i;
            i++;
            String item = (String) it.next();
            url3 = StringsKt.replace$default(url3, Intrinsics.stringPlus(PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX, Integer.valueOf(index)), StringsKt.replace$default(StringsKt.replace$default(item, "searchKey", "key", false, 4, (Object) null), "searchPage", "page", false, 4, (Object) null), false, 4, (Object) null);
        }
        List urlList2 = StringsKt.split$default((CharSequence) url3, new String[]{StrPool.AT}, false, 0, 6, (Object) null);
        String url4 = (String) urlList2.get(0);
        if (urlList2.size() > 1) {
            map.put("method", "POST");
            map.put(NCXDocumentV3.XHTMLTgs.body, urlList2.get(1));
        }
        if (map.size() > 0) {
            url4 = url4 + ',' + ((Object) GsonExtensionsKt.getGSON().toJson(map));
        }
        return url4;
    }

    private final String uaToHeader(String ua) {
        String str = ua;
        if (str == null || str.length() == 0) {
            return null;
        }
        Map map = MapsKt.mapOf(new Pair("User-Agent", ua));
        return GsonExtensionsKt.getGSON().toJson(map);
    }
}
