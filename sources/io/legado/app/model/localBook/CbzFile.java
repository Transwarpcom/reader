package io.legado.app.model.localBook;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.text.StrPool;
import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.utils.FileUtils;
import io.legado.app.utils.MD5Utils;
import io.legado.app.utils.XmlUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.ag2s.epublib.epub.NCXDocumentV2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CbzFile.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��@\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018�� \u001f2\u00020\u0001:\u0001\u001fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0019\u001a\u00020\u0017H\u0002J$\u0010\u001a\u001a\u001e\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR(\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006 "}, d2 = {"Lio/legado/app/model/localBook/CbzFile;", "", "book", "Lio/legado/app/data/entities/Book;", "(Lio/legado/app/data/entities/Book;)V", "getBook", "()Lio/legado/app/data/entities/Book;", "setBook", "cover", "Ljava/io/InputStream;", "getCover", "()Ljava/io/InputStream;", "setCover", "(Ljava/io/InputStream;)V", "info", "", "", "getInfo", "()Ljava/util/Map;", "setInfo", "(Ljava/util/Map;)V", "getChapterList", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/BookChapter;", "getContent", NCXDocumentV2.NCXAttributeValues.chapter, "parseBookInfo", "Lkotlin/Pair;", "upBookInfo", "", "updateCover", "Companion", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/localBook/CbzFile.class */
public final class CbzFile {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private Book book;

    @Nullable
    private Map<String, Object> info;

    @Nullable
    private InputStream cover;

    @Nullable
    private static CbzFile cFile;

    public CbzFile(@NotNull Book book) {
        Intrinsics.checkNotNullParameter(book, "book");
        this.book = book;
    }

    @NotNull
    public final Book getBook() {
        return this.book;
    }

    public final void setBook(@NotNull Book book) {
        Intrinsics.checkNotNullParameter(book, "<set-?>");
        this.book = book;
    }

    @Nullable
    public final Map<String, Object> getInfo() {
        return this.info;
    }

    public final void setInfo(@Nullable Map<String, Object> map) {
        this.info = map;
    }

    @Nullable
    public final InputStream getCover() {
        return this.cover;
    }

    public final void setCover(@Nullable InputStream inputStream) {
        this.cover = inputStream;
    }

    /* compiled from: CbzFile.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000b\n��\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0006\u001a\u00020\u0007J\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\nJ\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u0011R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0012"}, d2 = {"Lio/legado/app/model/localBook/CbzFile$Companion;", "", "()V", "cFile", "Lio/legado/app/model/localBook/CbzFile;", "getCbzFile", "book", "Lio/legado/app/data/entities/Book;", "getChapterList", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/BookChapter;", "getContent", "", NCXDocumentV2.NCXAttributeValues.chapter, "upBookInfo", "", "onlyCover", "", "reader-pro"})
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/localBook/CbzFile$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        private final synchronized CbzFile getCbzFile(Book book) {
            if (CbzFile.cFile != null) {
                CbzFile cbzFile = CbzFile.cFile;
                if (Intrinsics.areEqual(cbzFile == null ? null : cbzFile.getBook().getBookUrl(), book.getBookUrl())) {
                    CbzFile cbzFile2 = CbzFile.cFile;
                    if (cbzFile2 != null) {
                        cbzFile2.setBook(book);
                    }
                    CbzFile cbzFile3 = CbzFile.cFile;
                    Intrinsics.checkNotNull(cbzFile3);
                    return cbzFile3;
                }
            }
            CbzFile.cFile = new CbzFile(book);
            CbzFile cbzFile4 = CbzFile.cFile;
            Intrinsics.checkNotNull(cbzFile4);
            return cbzFile4;
        }

        @NotNull
        public final synchronized ArrayList<BookChapter> getChapterList(@NotNull Book book) {
            Intrinsics.checkNotNullParameter(book, "book");
            return getCbzFile(book).getChapterList();
        }

        @Nullable
        public final synchronized String getContent(@NotNull Book book, @NotNull BookChapter chapter) {
            Intrinsics.checkNotNullParameter(book, "book");
            Intrinsics.checkNotNullParameter(chapter, "chapter");
            return getCbzFile(book).getContent(chapter);
        }

        public static /* synthetic */ void upBookInfo$default(Companion companion, Book book, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            companion.upBookInfo(book, z);
        }

        public final synchronized void upBookInfo(@NotNull Book book, boolean onlyCover) {
            Intrinsics.checkNotNullParameter(book, "book");
            if (onlyCover) {
                getCbzFile(book).updateCover();
            } else {
                getCbzFile(book).upBookInfo();
            }
        }
    }

    private final Pair<Map<String, Object>, InputStream> parseBookInfo() throws IOException {
        if (this.cover != null || this.info != null) {
            return new Pair<>(this.info, this.cover);
        }
        ZipFile zf = new ZipFile(this.book.getLocalFile());
        Enumeration entries = zf.entries();
        List imageExt = CollectionsKt.listOf((Object[]) new String[]{ImgUtil.IMAGE_TYPE_JPG, ImgUtil.IMAGE_TYPE_JPEG, ImgUtil.IMAGE_TYPE_GIF, ImgUtil.IMAGE_TYPE_PNG, ImgUtil.IMAGE_TYPE_BMP, "webp", "svg"});
        while (entries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = entries.nextElement();
            if (zipEntryNextElement != null) {
                ZipEntry zipEntry = zipEntryNextElement;
                if (!zipEntry.isDirectory()) {
                    String name = zipEntry.getName();
                    if (name.equals("ComicInfo.xml")) {
                        InputStream inputStream = zf.getInputStream(zipEntry);
                        XmlUtils xmlUtils = XmlUtils.INSTANCE;
                        Intrinsics.checkNotNullExpressionValue(inputStream, "inputStream");
                        this.info = xmlUtils.xml2map(inputStream);
                    } else if (this.cover == null) {
                        FileUtils fileUtils = FileUtils.INSTANCE;
                        Intrinsics.checkNotNullExpressionValue(name, "name");
                        String fileExtetion$default = FileUtils.getFileExtetion$default(fileUtils, name, null, 2, null);
                        if (fileExtetion$default == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }
                        String ext = fileExtetion$default.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(ext, "(this as java.lang.Strin….toLowerCase(Locale.ROOT)");
                        if (imageExt.contains(ext)) {
                            this.cover = zf.getInputStream(zipEntry);
                        }
                    }
                }
                if (this.cover != null && this.info != null) {
                    break;
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type java.util.zip.ZipEntry");
            }
        }
        return new Pair<>(this.info, this.cover);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void upBookInfo() throws IOException {
        Pair<Map<String, Object>, InputStream> bookInfo = parseBookInfo();
        if (bookInfo.getFirst() != null) {
            Map<String, Object> first = bookInfo.getFirst();
            if (first == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>");
            }
            Map map = (Map) first.get("ComicInfo");
            Map map2 = map == null ? null : map;
            Book book = this.book;
            Object obj = map2 == null ? null : map2.get("Title");
            book.setName((String) (obj == null ? this.book.getName() : obj));
            Book book2 = this.book;
            Object obj2 = map2 == null ? null : map2.get("Writer");
            book2.setAuthor((String) (obj2 == null ? this.book.getAuthor() : obj2));
        }
        updateCover();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateCover() throws IOException {
        String coverFile = Intrinsics.stringPlus(MD5Utils.INSTANCE.md5Encode16(this.book.getBookUrl()), ".jpg");
        String relativeCoverUrl = Paths.get("assets", this.book.getUserNameSpace(), "covers", coverFile).toString();
        this.book.setCoverUrl(Intrinsics.stringPlus("/", StringsKt.replace$default(relativeCoverUrl, StrPool.BACKSLASH, "/", false, 4, (Object) null)));
        String coverUrl = Paths.get(this.book.workRoot(), "storage", relativeCoverUrl).toString();
        if (!new File(coverUrl).exists()) {
            Pair result = parseBookInfo();
            if (result.getSecond() != null) {
                InputStream second = result.getSecond();
                if (second == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.io.InputStream");
                }
                InputStream coverStream = second;
                FileUtils.INSTANCE.writeInputStream(coverUrl, coverStream);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getContent(BookChapter chapter) {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ArrayList<BookChapter> getChapterList() {
        ArrayList chapterList = new ArrayList();
        ZipFile zf = new ZipFile(this.book.getLocalFile());
        Enumeration entries = zf.entries();
        ArrayList imageFileList = new ArrayList();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = entries.nextElement();
            if (zipEntryNextElement == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.util.zip.ZipEntry");
            }
            ZipEntry zipEntry = zipEntryNextElement;
            if (!zipEntry.isDirectory()) {
                String name = zipEntry.getName();
                Intrinsics.checkNotNullExpressionValue(name, "name");
                if (!StringsKt.endsWith$default(name, ".xml", false, 2, (Object) null)) {
                    imageFileList.add(name);
                }
            }
        }
        CollectionsKt.sort(imageFileList);
        int i = 0;
        int size = imageFileList.size();
        if (0 < size) {
            do {
                int i2 = i;
                i++;
                Object obj = imageFileList.get(i2);
                Intrinsics.checkNotNullExpressionValue(obj, "imageFileList.get(i)");
                String name2 = (String) obj;
                BookChapter chapter = new BookChapter(null, null, false, null, null, 0, null, null, null, null, null, null, null, 8191, null);
                chapter.setTitle(name2);
                chapter.setIndex(i2);
                chapter.setBookUrl(this.book.getBookUrl());
                chapter.setUrl(name2);
                chapterList.add(chapter);
            } while (i < size);
        }
        Book book = this.book;
        BookChapter bookChapter = (BookChapter) CollectionsKt.lastOrNull((List) chapterList);
        book.setLatestChapterTitle(bookChapter == null ? null : bookChapter.getTitle());
        this.book.setTotalChapterNum(chapterList.size());
        return chapterList;
    }
}
