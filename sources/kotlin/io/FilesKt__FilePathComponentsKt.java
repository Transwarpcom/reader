package kotlin.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: FilePathComponents.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 1, d1 = {"��$\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\u001a\u0011\u0010\u000b\u001a\u00020\f*\u00020\bH\u0002¢\u0006\u0002\b\r\u001a\u001c\u0010\u000e\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH��\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0002H��\"\u0015\u0010��\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b��\u0010\u0003\"\u0018\u0010\u0004\u001a\u00020\u0002*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u0018\u0010\u0007\u001a\u00020\b*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"isRooted", "", "Ljava/io/File;", "(Ljava/io/File;)Z", "root", "getRoot", "(Ljava/io/File;)Ljava/io/File;", "rootName", "", "getRootName", "(Ljava/io/File;)Ljava/lang/String;", "getRootLength", "", "getRootLength$FilesKt__FilePathComponentsKt", "subPath", "beginIndex", "endIndex", "toComponents", "Lkotlin/io/FilePathComponents;", "kotlin-stdlib"}, xs = "kotlin/io/FilesKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/io/FilesKt__FilePathComponentsKt.class */
class FilesKt__FilePathComponentsKt {
    private static final int getRootLength$FilesKt__FilePathComponentsKt(String $this$getRootLength) {
        int first;
        int first2 = StringsKt.indexOf$default((CharSequence) $this$getRootLength, File.separatorChar, 0, false, 4, (Object) null);
        if (first2 == 0) {
            if ($this$getRootLength.length() > 1 && $this$getRootLength.charAt(1) == File.separatorChar && (first = StringsKt.indexOf$default((CharSequence) $this$getRootLength, File.separatorChar, 2, false, 4, (Object) null)) >= 0) {
                int first3 = StringsKt.indexOf$default((CharSequence) $this$getRootLength, File.separatorChar, first + 1, false, 4, (Object) null);
                if (first3 >= 0) {
                    return first3 + 1;
                }
                return $this$getRootLength.length();
            }
            return 1;
        }
        if (first2 > 0 && $this$getRootLength.charAt(first2 - 1) == ':') {
            return first2 + 1;
        }
        if (first2 == -1 && StringsKt.endsWith$default((CharSequence) $this$getRootLength, ':', false, 2, (Object) null)) {
            return $this$getRootLength.length();
        }
        return 0;
    }

    @NotNull
    public static final String getRootName(@NotNull File rootName) {
        Intrinsics.checkNotNullParameter(rootName, "$this$rootName");
        String path = rootName.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "path");
        String path2 = rootName.getPath();
        Intrinsics.checkNotNullExpressionValue(path2, "path");
        int rootLength$FilesKt__FilePathComponentsKt = getRootLength$FilesKt__FilePathComponentsKt(path2);
        if (path == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = path.substring(0, rootLength$FilesKt__FilePathComponentsKt);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @NotNull
    public static final File getRoot(@NotNull File root) {
        Intrinsics.checkNotNullParameter(root, "$this$root");
        return new File(FilesKt.getRootName(root));
    }

    public static final boolean isRooted(@NotNull File isRooted) {
        Intrinsics.checkNotNullParameter(isRooted, "$this$isRooted");
        String path = isRooted.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "path");
        return getRootLength$FilesKt__FilePathComponentsKt(path) > 0;
    }

    @NotNull
    public static final FilePathComponents toComponents(@NotNull File toComponents) {
        List listEmptyList;
        Intrinsics.checkNotNullParameter(toComponents, "$this$toComponents");
        String path = toComponents.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "path");
        int rootLength = getRootLength$FilesKt__FilePathComponentsKt(path);
        String rootName = path.substring(0, rootLength);
        Intrinsics.checkNotNullExpressionValue(rootName, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        String subPath = path.substring(rootLength);
        Intrinsics.checkNotNullExpressionValue(subPath, "(this as java.lang.String).substring(startIndex)");
        if (subPath.length() == 0) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            Iterable $this$map$iv = StringsKt.split$default((CharSequence) subPath, new char[]{File.separatorChar}, false, 0, 6, (Object) null);
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                String p1 = (String) item$iv$iv;
                destination$iv$iv.add(new File(p1));
            }
            listEmptyList = (List) destination$iv$iv;
        }
        List list = listEmptyList;
        return new FilePathComponents(new File(rootName), list);
    }

    @NotNull
    public static final File subPath(@NotNull File subPath, int beginIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(subPath, "$this$subPath");
        return FilesKt.toComponents(subPath).subPath(beginIndex, endIndex);
    }
}
