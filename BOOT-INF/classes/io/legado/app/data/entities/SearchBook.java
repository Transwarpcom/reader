package io.legado.app.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import io.legado.app.utils.GsonExtensionsKt;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Result.Companion;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties({"variableMap", "infoHtml", "tocHtml", "origins", "kindList"})
@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B§\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0004\u0012\b\b\u0002\u0010\n\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0014\u001a\u00020\b¢\u0006\u0002\u0010\u0015J\u000e\u0010N\u001a\u00020O2\u0006\u0010\u0005\u001a\u00020\u0004J\u0011\u0010P\u001a\u00020\b2\u0006\u0010Q\u001a\u00020\u0000H\u0096\u0002J\t\u0010R\u001a\u00020\u0004HÆ\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010U\u001a\u00020\u0004HÆ\u0003J\t\u0010V\u001a\u00020\u0012HÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010X\u001a\u00020\bHÆ\u0003J\t\u0010Y\u001a\u00020\u0004HÆ\u0003J\t\u0010Z\u001a\u00020\u0004HÆ\u0003J\t\u0010[\u001a\u00020\bHÆ\u0003J\t\u0010\\\u001a\u00020\u0004HÆ\u0003J\t\u0010]\u001a\u00020\u0004HÆ\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0004HÆ\u0003J«\u0001\u0010a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0014\u001a\u00020\bHÆ\u0001J\u0013\u0010b\u001a\u00020c2\b\u0010Q\u001a\u0004\u0018\u00010dH\u0096\u0002J\b\u0010e\u001a\u00020\u0004H\u0016J\b\u0010f\u001a\u00020\bH\u0016J\u001a\u0010g\u001a\u00020O2\u0006\u0010h\u001a\u00020\u00042\b\u0010i\u001a\u0004\u0018\u00010\u0004H\u0016J\u000e\u0010j\u001a\u00020O2\u0006\u0010k\u001a\u00020\u0004J\u0006\u0010l\u001a\u00020mJ\t\u0010n\u001a\u00020\u0004HÖ\u0001R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0003\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0018\"\u0004\b\u001c\u0010\u001aR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0018\"\u0004\b\u001e\u0010\u001aR\u001c\u0010\u001f\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0018\"\u0004\b!\u0010\u001aR\u001c\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0018\"\u0004\b#\u0010\u001aR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0018\"\u0004\b%\u0010\u001aR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0018\"\u0004\b'\u0010\u001aR\u001a\u0010\t\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0018\"\u0004\b)\u0010\u001aR\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0018\"\u0004\b+\u0010\u001aR\u001a\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0018\"\u0004\b-\u0010\u001aR\u001a\u0010\u0014\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101RF\u00105\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u000103j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`42\u001a\u00102\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u000103j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`4@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001c\u0010<\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0018\"\u0004\b>\u0010\u001aR\u001a\u0010\u0010\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u0018\"\u0004\b@\u0010\u001aR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010/\"\u0004\bB\u00101R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0018\"\u0004\bD\u0010\u001aR7\u0010E\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040Fj\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`G8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bJ\u0010K\u001a\u0004\bH\u0010IR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u0018\"\u0004\bM\u0010\u001a¨\u0006o"},
   d2 = {"Lio/legado/app/data/entities/SearchBook;", "Lio/legado/app/data/entities/BaseBook;", "", "bookUrl", "", "origin", "originName", "type", "", "name", "author", "kind", "coverUrl", "intro", "wordCount", "latestChapterTitle", "tocUrl", "time", "", "variable", "originOrder", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;I)V", "_userNameSpace", "getAuthor", "()Ljava/lang/String;", "setAuthor", "(Ljava/lang/String;)V", "getBookUrl", "setBookUrl", "getCoverUrl", "setCoverUrl", "infoHtml", "getInfoHtml", "setInfoHtml", "getIntro", "setIntro", "getKind", "setKind", "getLatestChapterTitle", "setLatestChapterTitle", "getName", "setName", "getOrigin", "setOrigin", "getOriginName", "setOriginName", "getOriginOrder", "()I", "setOriginOrder", "(I)V", "<set-?>", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "origins", "getOrigins", "()Ljava/util/LinkedHashSet;", "getTime", "()J", "setTime", "(J)V", "tocHtml", "getTocHtml", "setTocHtml", "getTocUrl", "setTocUrl", "getType", "setType", "getVariable", "setVariable", "variableMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getVariableMap", "()Ljava/util/HashMap;", "variableMap$delegate", "Lkotlin/Lazy;", "getWordCount", "setWordCount", "addOrigin", "", "compareTo", "other", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "", "getUserNameSpace", "hashCode", "putVariable", "key", "value", "setUserNameSpace", "nameSpace", "toBook", "Lio/legado/app/data/entities/Book;", "toString", "reader-pro"}
)
public final class SearchBook implements BaseBook, Comparable<SearchBook> {
   @NotNull
   private String bookUrl;
   @NotNull
   private String origin;
   @NotNull
   private String originName;
   private int type;
   @NotNull
   private String name;
   @NotNull
   private String author;
   @Nullable
   private String kind;
   @Nullable
   private String coverUrl;
   @Nullable
   private String intro;
   @Nullable
   private String wordCount;
   @Nullable
   private String latestChapterTitle;
   @NotNull
   private String tocUrl;
   private long time;
   @Nullable
   private String variable;
   private int originOrder;
   @Nullable
   private String infoHtml;
   @Nullable
   private String tocHtml;
   @NotNull
   private final transient Lazy variableMap$delegate;
   @NotNull
   private transient String _userNameSpace;
   @Nullable
   private LinkedHashSet<String> origins;

   public SearchBook(@NotNull String bookUrl, @NotNull String origin, @NotNull String originName, int type, @NotNull String name, @NotNull String author, @Nullable String kind, @Nullable String coverUrl, @Nullable String intro, @Nullable String wordCount, @Nullable String latestChapterTitle, @NotNull String tocUrl, long time, @Nullable String variable, int originOrder) {
      Intrinsics.checkNotNullParameter(bookUrl, "bookUrl");
      Intrinsics.checkNotNullParameter(origin, "origin");
      Intrinsics.checkNotNullParameter(originName, "originName");
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(author, "author");
      Intrinsics.checkNotNullParameter(tocUrl, "tocUrl");
      super();
      this.bookUrl = bookUrl;
      this.origin = origin;
      this.originName = originName;
      this.type = type;
      this.name = name;
      this.author = author;
      this.kind = kind;
      this.coverUrl = coverUrl;
      this.intro = intro;
      this.wordCount = wordCount;
      this.latestChapterTitle = latestChapterTitle;
      this.tocUrl = tocUrl;
      this.time = time;
      this.variable = variable;
      this.originOrder = originOrder;
      this.variableMap$delegate = LazyKt.lazy((Function0)(new Function0<HashMap<String, String>>() {
         @NotNull
         public final HashMap<String, String> invoke() {
            Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
            String json$iv = SearchBook.this.getVariable();
            int $i$f$fromJsonObject = false;
            boolean var5 = false;

            Object var6;
            try {
               Companion var13 = Result.Companion;
               int var7 = false;
               int $i$f$genericType = false;
               Type var16 = (new SearchBook$variableMap$2$invoke$$inlined$fromJsonObject$1()).getType();
               Intrinsics.checkNotNullExpressionValue(var16, "object : TypeToken<T>() {}.type");
               Object var10000 = $this$fromJsonObject$iv.fromJson(json$iv, var16);
               if (!(var10000 instanceof HashMap)) {
                  var10000 = null;
               }

               HashMap var14 = (HashMap)var10000;
               $i$f$genericType = false;
               var6 = Result.constructor-impl(var14);
            } catch (Throwable var10) {
               Companion var8 = Result.Companion;
               boolean var9 = false;
               var6 = Result.constructor-impl(ResultKt.createFailure(var10));
            }

            boolean var12 = false;
            HashMap var1 = (HashMap)(Result.isFailure-impl(var6) ? null : var6);
            HashMap var17;
            if (var1 == null) {
               boolean var11 = false;
               var17 = new HashMap();
            } else {
               var17 = var1;
            }

            return var17;
         }
      }));
      this._userNameSpace = "";
   }

   // $FF: synthetic method
   public SearchBook(String var1, String var2, String var3, int var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, long var13, String var15, int var16, int var17, DefaultConstructorMarker var18) {
      if ((var17 & 1) != 0) {
         var1 = "";
      }

      if ((var17 & 2) != 0) {
         var2 = "";
      }

      if ((var17 & 4) != 0) {
         var3 = "";
      }

      if ((var17 & 8) != 0) {
         var4 = 0;
      }

      if ((var17 & 16) != 0) {
         var5 = "";
      }

      if ((var17 & 32) != 0) {
         var6 = "";
      }

      if ((var17 & 64) != 0) {
         var7 = null;
      }

      if ((var17 & 128) != 0) {
         var8 = null;
      }

      if ((var17 & 256) != 0) {
         var9 = null;
      }

      if ((var17 & 512) != 0) {
         var10 = null;
      }

      if ((var17 & 1024) != 0) {
         var11 = null;
      }

      if ((var17 & 2048) != 0) {
         var12 = "";
      }

      if ((var17 & 4096) != 0) {
         var13 = 0L;
      }

      if ((var17 & 8192) != 0) {
         var15 = null;
      }

      if ((var17 & 16384) != 0) {
         var16 = 0;
      }

      this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var15, var16);
   }

   @NotNull
   public String getBookUrl() {
      return this.bookUrl;
   }

   public void setBookUrl(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.bookUrl = var1;
   }

   @NotNull
   public final String getOrigin() {
      return this.origin;
   }

   public final void setOrigin(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.origin = var1;
   }

   @NotNull
   public final String getOriginName() {
      return this.originName;
   }

   public final void setOriginName(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.originName = var1;
   }

   public final int getType() {
      return this.type;
   }

   public final void setType(int <set-?>) {
      this.type = var1;
   }

   @NotNull
   public String getName() {
      return this.name;
   }

   public void setName(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.name = var1;
   }

   @NotNull
   public String getAuthor() {
      return this.author;
   }

   public void setAuthor(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.author = var1;
   }

   @Nullable
   public String getKind() {
      return this.kind;
   }

   public void setKind(@Nullable String <set-?>) {
      this.kind = var1;
   }

   @Nullable
   public final String getCoverUrl() {
      return this.coverUrl;
   }

   public final void setCoverUrl(@Nullable String <set-?>) {
      this.coverUrl = var1;
   }

   @Nullable
   public final String getIntro() {
      return this.intro;
   }

   public final void setIntro(@Nullable String <set-?>) {
      this.intro = var1;
   }

   @Nullable
   public String getWordCount() {
      return this.wordCount;
   }

   public void setWordCount(@Nullable String <set-?>) {
      this.wordCount = var1;
   }

   @Nullable
   public final String getLatestChapterTitle() {
      return this.latestChapterTitle;
   }

   public final void setLatestChapterTitle(@Nullable String <set-?>) {
      this.latestChapterTitle = var1;
   }

   @NotNull
   public final String getTocUrl() {
      return this.tocUrl;
   }

   public final void setTocUrl(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.tocUrl = var1;
   }

   public final long getTime() {
      return this.time;
   }

   public final void setTime(long <set-?>) {
      this.time = var1;
   }

   @Nullable
   public final String getVariable() {
      return this.variable;
   }

   public final void setVariable(@Nullable String <set-?>) {
      this.variable = var1;
   }

   public final int getOriginOrder() {
      return this.originOrder;
   }

   public final void setOriginOrder(int <set-?>) {
      this.originOrder = var1;
   }

   @Nullable
   public String getInfoHtml() {
      return this.infoHtml;
   }

   public void setInfoHtml(@Nullable String <set-?>) {
      this.infoHtml = var1;
   }

   @Nullable
   public String getTocHtml() {
      return this.tocHtml;
   }

   public void setTocHtml(@Nullable String <set-?>) {
      this.tocHtml = var1;
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof SearchBook && Intrinsics.areEqual(((SearchBook)other).getBookUrl(), this.getBookUrl());
   }

   public int hashCode() {
      return this.getBookUrl().hashCode();
   }

   public int compareTo(@NotNull SearchBook other) {
      Intrinsics.checkNotNullParameter(other, "other");
      return other.originOrder - this.originOrder;
   }

   @NotNull
   public HashMap<String, String> getVariableMap() {
      Lazy var1 = this.variableMap$delegate;
      boolean var3 = false;
      return (HashMap)var1.getValue();
   }

   public void putVariable(@NotNull String key, @Nullable String value) {
      Intrinsics.checkNotNullParameter(key, "key");
      if (value != null) {
         Map var3 = (Map)this.getVariableMap();
         boolean var4 = false;
         var3.put(key, value);
      } else {
         this.getVariableMap().remove(key);
      }

      this.variable = GsonExtensionsKt.getGSON().toJson(this.getVariableMap());
   }

   public final void setUserNameSpace(@NotNull String nameSpace) {
      Intrinsics.checkNotNullParameter(nameSpace, "nameSpace");
      this._userNameSpace = nameSpace;
   }

   @NotNull
   public String getUserNameSpace() {
      return this._userNameSpace;
   }

   @Nullable
   public final LinkedHashSet<String> getOrigins() {
      return this.origins;
   }

   public final void addOrigin(@NotNull String origin) {
      Intrinsics.checkNotNullParameter(origin, "origin");
      if (this.origins == null) {
         String[] var2 = new String[]{this.origin};
         this.origins = SetsKt.linkedSetOf(var2);
      }

      LinkedHashSet var3 = this.origins;
      if (var3 != null) {
         var3.add(origin);
      }

   }

   @NotNull
   public final Book toBook() {
      String var1 = this.getName();
      String var2 = this.getAuthor();
      String var3 = this.getKind();
      String var4 = this.getBookUrl();
      String var5 = this.origin;
      String var6 = this.originName;
      int var7 = this.type;
      String var8 = this.getWordCount();
      String var9 = this.latestChapterTitle;
      String var10 = this.coverUrl;
      String var11 = this.intro;
      String var12 = this.tocUrl;
      String var13 = this.variable;
      Book var14 = new Book(var4, var12, var5, var6, var1, var2, var3, (String)null, var10, (String)null, var11, (String)null, (String)null, var7, 0L, var9, 0L, 0L, 0, 0, (String)null, 0, 0, 0L, var8, false, 0, 0, false, var13, (Book.ReadConfig)null, false, (String)null, -553690496, 1, (DefaultConstructorMarker)null);
      boolean var15 = false;
      boolean var16 = false;
      int var17 = false;
      var14.setInfoHtml(this.getInfoHtml());
      var14.setTocUrl(this.getTocUrl());
      var14.setUserNameSpace(this.getUserNameSpace());
      return var14;
   }

   @NotNull
   public List<String> getKindList() {
      return BaseBook.DefaultImpls.getKindList(this);
   }

   @Nullable
   public String getVariable(@NotNull String key) {
      return BaseBook.DefaultImpls.getVariable((BaseBook)this, key);
   }

   @NotNull
   public final String component1() {
      return this.getBookUrl();
   }

   @NotNull
   public final String component2() {
      return this.origin;
   }

   @NotNull
   public final String component3() {
      return this.originName;
   }

   public final int component4() {
      return this.type;
   }

   @NotNull
   public final String component5() {
      return this.getName();
   }

   @NotNull
   public final String component6() {
      return this.getAuthor();
   }

   @Nullable
   public final String component7() {
      return this.getKind();
   }

   @Nullable
   public final String component8() {
      return this.coverUrl;
   }

   @Nullable
   public final String component9() {
      return this.intro;
   }

   @Nullable
   public final String component10() {
      return this.getWordCount();
   }

   @Nullable
   public final String component11() {
      return this.latestChapterTitle;
   }

   @NotNull
   public final String component12() {
      return this.tocUrl;
   }

   public final long component13() {
      return this.time;
   }

   @Nullable
   public final String component14() {
      return this.variable;
   }

   public final int component15() {
      return this.originOrder;
   }

   @NotNull
   public final SearchBook copy(@NotNull String bookUrl, @NotNull String origin, @NotNull String originName, int type, @NotNull String name, @NotNull String author, @Nullable String kind, @Nullable String coverUrl, @Nullable String intro, @Nullable String wordCount, @Nullable String latestChapterTitle, @NotNull String tocUrl, long time, @Nullable String variable, int originOrder) {
      Intrinsics.checkNotNullParameter(bookUrl, "bookUrl");
      Intrinsics.checkNotNullParameter(origin, "origin");
      Intrinsics.checkNotNullParameter(originName, "originName");
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(author, "author");
      Intrinsics.checkNotNullParameter(tocUrl, "tocUrl");
      return new SearchBook(bookUrl, origin, originName, type, name, author, kind, coverUrl, intro, wordCount, latestChapterTitle, tocUrl, time, variable, originOrder);
   }

   // $FF: synthetic method
   public static SearchBook copy$default(SearchBook var0, String var1, String var2, String var3, int var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, long var13, String var15, int var16, int var17, Object var18) {
      if ((var17 & 1) != 0) {
         var1 = var0.getBookUrl();
      }

      if ((var17 & 2) != 0) {
         var2 = var0.origin;
      }

      if ((var17 & 4) != 0) {
         var3 = var0.originName;
      }

      if ((var17 & 8) != 0) {
         var4 = var0.type;
      }

      if ((var17 & 16) != 0) {
         var5 = var0.getName();
      }

      if ((var17 & 32) != 0) {
         var6 = var0.getAuthor();
      }

      if ((var17 & 64) != 0) {
         var7 = var0.getKind();
      }

      if ((var17 & 128) != 0) {
         var8 = var0.coverUrl;
      }

      if ((var17 & 256) != 0) {
         var9 = var0.intro;
      }

      if ((var17 & 512) != 0) {
         var10 = var0.getWordCount();
      }

      if ((var17 & 1024) != 0) {
         var11 = var0.latestChapterTitle;
      }

      if ((var17 & 2048) != 0) {
         var12 = var0.tocUrl;
      }

      if ((var17 & 4096) != 0) {
         var13 = var0.time;
      }

      if ((var17 & 8192) != 0) {
         var15 = var0.variable;
      }

      if ((var17 & 16384) != 0) {
         var16 = var0.originOrder;
      }

      return var0.copy(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var15, var16);
   }

   @NotNull
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("SearchBook(bookUrl=").append(this.getBookUrl()).append(", origin=").append(this.origin).append(", originName=").append(this.originName).append(", type=").append(this.type).append(", name=").append(this.getName()).append(", author=").append(this.getAuthor()).append(", kind=").append(this.getKind()).append(", coverUrl=").append(this.coverUrl).append(", intro=").append(this.intro).append(", wordCount=").append(this.getWordCount()).append(", latestChapterTitle=").append(this.latestChapterTitle).append(", tocUrl=");
      var1.append(this.tocUrl).append(", time=").append(this.time).append(", variable=").append(this.variable).append(", originOrder=").append(this.originOrder).append(')');
      return var1.toString();
   }

   public SearchBook() {
      this((String)null, (String)null, (String)null, 0, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, 0L, (String)null, 0, 32767, (DefaultConstructorMarker)null);
   }
}
