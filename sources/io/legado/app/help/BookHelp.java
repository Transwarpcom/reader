package io.legado.app.help;

import cn.hutool.core.img.ImgUtil;
import io.legado.app.constant.AppPattern;
import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.data.entities.BookSource;
import io.legado.app.model.localBook.LocalBook;
import io.legado.app.utils.FileExtensionsKt;
import io.legado.app.utils.FileUtils;
import io.legado.app.utils.MD5Utils;
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import me.ag2s.epublib.epub.NCXDocumentV2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BookHelp.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004J\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u0017\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0004J\u000e\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004J9\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010 J+\u0010!\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\"J9\u0010#\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010 J\u001e\u0010$\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n��R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0082\u0004¢\u0006\u0002\n��\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, d2 = {"Lio/legado/app/help/BookHelp;", "", "()V", "cacheImageFolderName", "", "downloadImages", "Ljava/util/concurrent/CopyOnWriteArraySet;", "delContent", "", "book", "Lio/legado/app/data/entities/Book;", "bookChapter", "Lio/legado/app/data/entities/BookChapter;", "formatAuthor", "author", "formatBookAuthor", "formatBookName", "name", "formatFolderName", "folderName", "getBookCacheDir", "Ljava/io/File;", "getContent", "getImage", NCXDocumentV2.NCXAttributes.src, "getImageSuffix", "saveContent", "scope", "Lkotlinx/coroutines/CoroutineScope;", "bookSource", "Lio/legado/app/data/entities/BookSource;", "content", "(Lkotlinx/coroutines/CoroutineScope;Lio/legado/app/data/entities/BookSource;Lio/legado/app/data/entities/Book;Lio/legado/app/data/entities/BookChapter;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveImage", "(Lio/legado/app/data/entities/BookSource;Lio/legado/app/data/entities/Book;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveImages", "saveText", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/help/BookHelp.class */
public final class BookHelp {

    @NotNull
    private static final String cacheImageFolderName = "images";

    @NotNull
    public static final BookHelp INSTANCE = new BookHelp();

    @NotNull
    private static final CopyOnWriteArraySet<String> downloadImages = new CopyOnWriteArraySet<>();

    /* compiled from: BookHelp.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookHelp.kt", l = {152, 160}, i = {0, 0, 0, 0}, s = {"L$0", "L$1", "L$2", "L$3"}, n = {"this", "bookSource", "book", NCXDocumentV2.NCXAttributes.src}, m = "saveImage", c = "io.legado.app.help.BookHelp")
    /* renamed from: io.legado.app.help.BookHelp$saveImage$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/help/BookHelp$saveImage$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
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
            return BookHelp.this.saveImage(null, null, null, this);
        }
    }

    /* compiled from: BookHelp.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookHelp.kt", l = {146}, i = {}, s = {}, n = {}, m = "saveImages", c = "io.legado.app.help.BookHelp")
    /* renamed from: io.legado.app.help.BookHelp$saveImages$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/help/BookHelp$saveImages$1.class */
    static final class C01951 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C01951(Continuation<? super C01951> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookHelp.this.saveImages(null, null, null, null, null, this);
        }
    }

    private BookHelp() {
    }

    private final String formatFolderName(String folderName) {
        return new Regex("[\\\\/:*?\"<>|.]").replace(folderName, "");
    }

    @NotNull
    public final String formatAuthor(@Nullable String author) {
        if (author == null) {
            return "";
        }
        String strReplace = new Regex("作\\s*者[\\s:：]*").replace(author, "");
        if (strReplace == null) {
            return "";
        }
        CharSequence $this$trim$iv = new Regex("\\s+").replace(strReplace, " ");
        if ($this$trim$iv == null) {
            return "";
        }
        CharSequence $this$trim$iv$iv = $this$trim$iv;
        int startIndex$iv$iv = 0;
        int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
        boolean startFound$iv$iv = false;
        while (startIndex$iv$iv <= endIndex$iv$iv) {
            int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
            char it = $this$trim$iv$iv.charAt(index$iv$iv);
            boolean match$iv$iv = Intrinsics.compare((int) it, 32) <= 0;
            if (startFound$iv$iv) {
                if (!match$iv$iv) {
                    break;
                }
                endIndex$iv$iv--;
            } else if (match$iv$iv) {
                startIndex$iv$iv++;
            } else {
                startFound$iv$iv = true;
            }
        }
        String string = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
        return string == null ? "" : string;
    }

    @NotNull
    public final String formatBookName(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        CharSequence $this$trim$iv = AppPattern.INSTANCE.getNameRegex().replace(name, "");
        CharSequence $this$trim$iv$iv = $this$trim$iv;
        int startIndex$iv$iv = 0;
        int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
        boolean startFound$iv$iv = false;
        while (startIndex$iv$iv <= endIndex$iv$iv) {
            int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
            char it = $this$trim$iv$iv.charAt(index$iv$iv);
            boolean match$iv$iv = Intrinsics.compare((int) it, 32) <= 0;
            if (!startFound$iv$iv) {
                if (!match$iv$iv) {
                    startFound$iv$iv = true;
                } else {
                    startIndex$iv$iv++;
                }
            } else {
                if (!match$iv$iv) {
                    break;
                }
                endIndex$iv$iv--;
            }
        }
        return $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
    }

    @NotNull
    public final String formatBookAuthor(@NotNull String author) {
        Intrinsics.checkNotNullParameter(author, "author");
        CharSequence $this$trim$iv = AppPattern.INSTANCE.getAuthorRegex().replace(author, "");
        CharSequence $this$trim$iv$iv = $this$trim$iv;
        int startIndex$iv$iv = 0;
        int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
        boolean startFound$iv$iv = false;
        while (startIndex$iv$iv <= endIndex$iv$iv) {
            int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
            char it = $this$trim$iv$iv.charAt(index$iv$iv);
            boolean match$iv$iv = Intrinsics.compare((int) it, 32) <= 0;
            if (!startFound$iv$iv) {
                if (!match$iv$iv) {
                    startFound$iv$iv = true;
                } else {
                    startIndex$iv$iv++;
                }
            } else {
                if (!match$iv$iv) {
                    break;
                }
                endIndex$iv$iv--;
            }
        }
        return $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
    }

    @NotNull
    public final File getBookCacheDir(@NotNull Book book) throws Exception {
        Intrinsics.checkNotNullParameter(book, "book");
        String md5Encode = MD5Utils.INSTANCE.md5Encode(book.getBookUrl()).toString();
        String bookDir = book.getBookDir();
        if (bookDir.length() == 0) {
            throw new Exception("bookDir不能为空");
        }
        File localCacheDir = FileExtensionsKt.getFile(new File(bookDir), md5Encode);
        if (!localCacheDir.exists()) {
            localCacheDir.mkdirs();
        }
        return localCacheDir;
    }

    @Nullable
    public final String getContent(@NotNull Book book, @NotNull BookChapter bookChapter) {
        Intrinsics.checkNotNullParameter(book, "book");
        Intrinsics.checkNotNullParameter(bookChapter, "bookChapter");
        File bookCacheDir = getBookCacheDir(book);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Integer.valueOf(bookChapter.getIndex())};
        String str = String.format("%d.txt", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        File file = FileExtensionsKt.getFile(bookCacheDir, str);
        if (file.exists()) {
            return FilesKt.readText$default(file, null, 1, null);
        }
        if (book.isLocalBook()) {
            String content = LocalBook.INSTANCE.getContent(book, bookChapter);
            if (content != null && book.isEpub()) {
                saveText(book, bookChapter, content);
            }
            return content;
        }
        return null;
    }

    public final void delContent(@NotNull Book book, @NotNull BookChapter bookChapter) throws Exception {
        Intrinsics.checkNotNullParameter(book, "book");
        Intrinsics.checkNotNullParameter(bookChapter, "bookChapter");
        FileUtils fileUtils = FileUtils.INSTANCE;
        File bookCacheDir = getBookCacheDir(book);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Integer.valueOf(bookChapter.getIndex())};
        String str = String.format("%d.txt", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        fileUtils.createFileIfNotExist(bookCacheDir, str).delete();
    }

    @Nullable
    public final Object saveContent(@NotNull CoroutineScope scope, @NotNull BookSource bookSource, @NotNull Book book, @NotNull BookChapter bookChapter, @NotNull String content, @NotNull Continuation<? super Unit> $completion) throws Exception {
        saveText(book, bookChapter, content);
        Object objSaveImages = saveImages(scope, bookSource, book, bookChapter, content, $completion);
        return objSaveImages == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSaveImages : Unit.INSTANCE;
    }

    public final void saveText(@NotNull Book book, @NotNull BookChapter bookChapter, @NotNull String content) throws Exception {
        Intrinsics.checkNotNullParameter(book, "book");
        Intrinsics.checkNotNullParameter(bookChapter, "bookChapter");
        Intrinsics.checkNotNullParameter(content, "content");
        FileUtils fileUtils = FileUtils.INSTANCE;
        File bookCacheDir = getBookCacheDir(book);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Integer.valueOf(bookChapter.getIndex())};
        String str = String.format("%d.txt", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        FilesKt.writeText$default(fileUtils.createFileIfNotExist(bookCacheDir, str), content, null, 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x002b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveImages(@org.jetbrains.annotations.NotNull kotlinx.coroutines.CoroutineScope r11, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.BookSource r12, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.Book r13, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.BookChapter r14, @org.jetbrains.annotations.NotNull java.lang.String r15, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r16) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 423
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.help.BookHelp.saveImages(kotlinx.coroutines.CoroutineScope, io.legado.app.data.entities.BookSource, io.legado.app.data.entities.Book, io.legado.app.data.entities.BookChapter, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveImage(@org.jetbrains.annotations.Nullable io.legado.app.data.entities.BookSource r17, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.Book r18, @org.jetbrains.annotations.NotNull java.lang.String r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.help.BookHelp.saveImage(io.legado.app.data.entities.BookSource, io.legado.app.data.entities.Book, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final File getImage(@NotNull Book book, @NotNull String src) {
        Intrinsics.checkNotNullParameter(book, "book");
        Intrinsics.checkNotNullParameter(src, "src");
        return FileExtensionsKt.getFile(getBookCacheDir(book), cacheImageFolderName, MD5Utils.INSTANCE.md5Encode16(src) + '.' + getImageSuffix(src));
    }

    @NotNull
    public final String getImageSuffix(@NotNull String src) {
        Intrinsics.checkNotNullParameter(src, "src");
        String suffix = StringsKt.substringBefore$default(StringsKt.substringAfterLast$default(src, ".", (String) null, 2, (Object) null), ",", (String) null, 2, (Object) null);
        Regex fileSuffixRegex = new Regex("^[a-z0-9]+$", RegexOption.IGNORE_CASE);
        if (suffix.length() > 5 || !fileSuffixRegex.matches(suffix)) {
            suffix = ImgUtil.IMAGE_TYPE_JPG;
        }
        return suffix;
    }
}
