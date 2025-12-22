package io.legado.app.model.localBook;

import cn.hutool.core.text.StrPool;
import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.utils.FileUtils;
import io.legado.app.utils.MD5Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.ag2s.epublib.epub.NCXDocumentV2;
import me.ag2s.umdlib.domain.UmdBook;
import me.ag2s.umdlib.domain.UmdChapters;
import me.ag2s.umdlib.domain.UmdCover;
import me.ag2s.umdlib.domain.UmdHeader;
import me.ag2s.umdlib.umd.UmdReader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: UmdFile.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��<\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018�� \u00192\u00020\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0002J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0010H\u0002J\n\u0010\u0015\u001a\u0004\u0018\u00010\tH\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8BX\u0082\u000e¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u001a"}, d2 = {"Lio/legado/app/model/localBook/UmdFile;", "", "book", "Lio/legado/app/data/entities/Book;", "(Lio/legado/app/data/entities/Book;)V", "getBook", "()Lio/legado/app/data/entities/Book;", "setBook", "umdBook", "Lme/ag2s/umdlib/domain/UmdBook;", "getUmdBook", "()Lme/ag2s/umdlib/domain/UmdBook;", "getChapterList", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/BookChapter;", "getContent", "", NCXDocumentV2.NCXAttributeValues.chapter, "getImage", "Ljava/io/InputStream;", "href", "readUmd", "upBookInfo", "", "updateCover", "Companion", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/localBook/UmdFile.class */
public final class UmdFile {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private Book book;

    @Nullable
    private UmdBook umdBook;

    @Nullable
    private static UmdFile uFile;

    /* compiled from: UmdFile.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000b\n��\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tJ\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0007J\u0018\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000bJ\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0015"}, d2 = {"Lio/legado/app/model/localBook/UmdFile$Companion;", "", "()V", "uFile", "Lio/legado/app/model/localBook/UmdFile;", "getChapterList", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/BookChapter;", "book", "Lio/legado/app/data/entities/Book;", "getContent", "", NCXDocumentV2.NCXAttributeValues.chapter, "getImage", "Ljava/io/InputStream;", "href", "getUFile", "upBookInfo", "", "onlyCover", "", "reader-pro"})
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/localBook/UmdFile$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        private final synchronized UmdFile getUFile(Book book) {
            if (UmdFile.uFile != null) {
                UmdFile umdFile = UmdFile.uFile;
                if (Intrinsics.areEqual(umdFile == null ? null : umdFile.getBook().getBookUrl(), book.getBookUrl())) {
                    UmdFile umdFile2 = UmdFile.uFile;
                    if (umdFile2 != null) {
                        umdFile2.setBook(book);
                    }
                    UmdFile umdFile3 = UmdFile.uFile;
                    Intrinsics.checkNotNull(umdFile3);
                    return umdFile3;
                }
            }
            UmdFile.uFile = new UmdFile(book);
            UmdFile umdFile4 = UmdFile.uFile;
            Intrinsics.checkNotNull(umdFile4);
            return umdFile4;
        }

        @NotNull
        public final synchronized ArrayList<BookChapter> getChapterList(@NotNull Book book) {
            Intrinsics.checkNotNullParameter(book, "book");
            return getUFile(book).getChapterList();
        }

        @Nullable
        public final synchronized String getContent(@NotNull Book book, @NotNull BookChapter chapter) {
            Intrinsics.checkNotNullParameter(book, "book");
            Intrinsics.checkNotNullParameter(chapter, "chapter");
            return getUFile(book).getContent(chapter);
        }

        @Nullable
        public final synchronized InputStream getImage(@NotNull Book book, @NotNull String href) {
            Intrinsics.checkNotNullParameter(book, "book");
            Intrinsics.checkNotNullParameter(href, "href");
            return getUFile(book).getImage(href);
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
                getUFile(book).updateCover();
            } else {
                getUFile(book).upBookInfo();
            }
        }
    }

    public UmdFile(@NotNull Book book) {
        Intrinsics.checkNotNullParameter(book, "book");
        this.book = book;
        try {
            if (getUmdBook() == null) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    public final Book getBook() {
        return this.book;
    }

    public final void setBook(@NotNull Book book) {
        Intrinsics.checkNotNullParameter(book, "<set-?>");
        this.book = book;
    }

    private final UmdBook getUmdBook() {
        if (this.umdBook != null) {
            return this.umdBook;
        }
        this.umdBook = readUmd();
        return this.umdBook;
    }

    private final UmdBook readUmd() throws SecurityException, FileNotFoundException {
        InputStream input = LocalBook.INSTANCE.getBookInputStream(this.book);
        return new UmdReader().read(input);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void upBookInfo() throws IOException {
        if (getUmdBook() == null) {
            Companion companion = Companion;
            uFile = null;
            this.book.setIntro("书籍导入异常");
            return;
        }
        UmdBook umdBook = getUmdBook();
        Intrinsics.checkNotNull(umdBook);
        UmdHeader hd = umdBook.getHeader();
        Book book = this.book;
        String title = hd.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "hd.title");
        book.setName(title);
        Book book2 = this.book;
        String author = hd.getAuthor();
        Intrinsics.checkNotNullExpressionValue(author, "hd.author");
        book2.setAuthor(author);
        this.book.setKind(hd.getBookType());
        updateCover();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateCover() throws IOException {
        UmdBook umdBook;
        UmdCover cover;
        byte[] it;
        if (getUmdBook() == null) {
            Companion companion = Companion;
            uFile = null;
            return;
        }
        String coverFile = Intrinsics.stringPlus(MD5Utils.INSTANCE.md5Encode16(this.book.getBookUrl()), ".jpg");
        String relativeCoverUrl = Paths.get("assets", this.book.getUserNameSpace(), "covers", coverFile).toString();
        this.book.setCoverUrl(Intrinsics.stringPlus("/", StringsKt.replace$default(relativeCoverUrl, StrPool.BACKSLASH, "/", false, 4, (Object) null)));
        String coverUrl = Paths.get(this.book.workRoot(), "storage", relativeCoverUrl).toString();
        if (!new File(coverUrl).exists() && (umdBook = getUmdBook()) != null && (cover = umdBook.getCover()) != null && (it = cover.getCoverData()) != null) {
            FileUtils.INSTANCE.writeBytes(coverUrl, it);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getContent(BookChapter chapter) {
        UmdChapters chapters;
        UmdBook umdBook = getUmdBook();
        if (umdBook == null || (chapters = umdBook.getChapters()) == null) {
            return null;
        }
        return chapters.getContentString(chapter.getIndex());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ArrayList<BookChapter> getChapterList() {
        UmdChapters chapters;
        Iterable titles;
        ArrayList chapterList = new ArrayList();
        UmdBook umdBook = getUmdBook();
        if (umdBook != null && (chapters = umdBook.getChapters()) != null && (titles = chapters.getTitles()) != null) {
            Iterable $this$forEachIndexed$iv = titles;
            int index$iv = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                int index = index$iv;
                index$iv++;
                if (index < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                UmdBook umdBook2 = getUmdBook();
                Intrinsics.checkNotNull(umdBook2);
                String title = umdBook2.getChapters().getTitle(index);
                BookChapter chapter = new BookChapter(null, null, false, null, null, 0, null, null, null, null, null, null, null, 8191, null);
                Intrinsics.checkNotNullExpressionValue(title, "title");
                chapter.setTitle(title);
                chapter.setIndex(index);
                chapter.setBookUrl(getBook().getBookUrl());
                chapter.setUrl(String.valueOf(index));
                System.out.println(Intrinsics.stringPlus("UMD", chapter.getUrl()));
                chapterList.add(chapter);
            }
        }
        Book book = this.book;
        BookChapter bookChapter = (BookChapter) CollectionsKt.lastOrNull((List) chapterList);
        book.setLatestChapterTitle(bookChapter == null ? null : bookChapter.getTitle());
        this.book.setTotalChapterNum(chapterList.size());
        return chapterList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final InputStream getImage(String href) {
        return null;
    }
}
