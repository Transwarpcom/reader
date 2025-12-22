package io.legado.app.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import io.legado.app.model.analyzeRule.AnalyzeUrl;
import io.legado.app.model.analyzeRule.RuleDataInterface;
import io.legado.app.utils.GsonExtensionsKt;
import io.legado.app.utils.MD5Utils;
import io.legado.app.utils.NetworkUtils;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Result.Companion;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties({"variableMap", "_userNameSpace", "userNameSpace"})
@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b)\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B\u0095\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0013J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\u0010\u0010?\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010\u001cJ\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010C\u001a\u00020\u0003HÆ\u0003J\t\u0010D\u001a\u00020\u0006HÆ\u0003J\t\u0010E\u001a\u00020\u0003HÆ\u0003J\t\u0010F\u001a\u00020\u0003HÆ\u0003J\t\u0010G\u001a\u00020\nHÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010J\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010\u001cJ\u009e\u0001\u0010K\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010LJ\u0013\u0010M\u001a\u00020\u00062\b\u0010N\u001a\u0004\u0018\u00010OH\u0096\u0002J\u0006\u0010P\u001a\u00020\u0003J\u0006\u0010Q\u001a\u00020\u0003J\b\u0010R\u001a\u00020\u0003H\u0016J\b\u0010S\u001a\u00020\nH\u0016J\u001a\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020\u00032\b\u0010W\u001a\u0004\u0018\u00010\u0003H\u0016J\u000e\u0010X\u001a\u00020U2\u0006\u0010Y\u001a\u00020\u0003J\t\u0010Z\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0014\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0016\"\u0004\b!\u0010\u0018R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010&\"\u0004\b'\u0010(R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0016\"\u0004\b*\u0010\u0018R\u001e\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b+\u0010\u001c\"\u0004\b,\u0010\u001eR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0016\"\u0004\b.\u0010\u0018R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0016\"\u0004\b0\u0010\u0018R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0016\"\u0004\b2\u0010\u0018R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0016\"\u0004\b4\u0010\u0018R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0016\"\u0004\b6\u0010\u0018R7\u00107\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000308j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`98VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b<\u0010=\u001a\u0004\b:\u0010;¨\u0006["},
   d2 = {"Lio/legado/app/data/entities/BookChapter;", "Lio/legado/app/model/analyzeRule/RuleDataInterface;", "url", "", "title", "isVolume", "", "baseUrl", "bookUrl", "index", "", "resourceUrl", "tag", "start", "", "end", "startFragmentId", "endFragmentId", "variable", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "_userNameSpace", "getBaseUrl", "()Ljava/lang/String;", "setBaseUrl", "(Ljava/lang/String;)V", "getBookUrl", "setBookUrl", "getEnd", "()Ljava/lang/Long;", "setEnd", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getEndFragmentId", "setEndFragmentId", "getIndex", "()I", "setIndex", "(I)V", "()Z", "setVolume", "(Z)V", "getResourceUrl", "setResourceUrl", "getStart", "setStart", "getStartFragmentId", "setStartFragmentId", "getTag", "setTag", "getTitle", "setTitle", "getUrl", "setUrl", "getVariable", "setVariable", "variableMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getVariableMap", "()Ljava/util/HashMap;", "variableMap$delegate", "Lkotlin/Lazy;", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lio/legado/app/data/entities/BookChapter;", "equals", "other", "", "getAbsoluteURL", "getFileName", "getUserNameSpace", "hashCode", "putVariable", "", "key", "value", "setUserNameSpace", "nameSpace", "toString", "reader-pro"}
)
public final class BookChapter implements RuleDataInterface {
   @NotNull
   private String url;
   @NotNull
   private String title;
   private boolean isVolume;
   @NotNull
   private String baseUrl;
   @NotNull
   private String bookUrl;
   private int index;
   @Nullable
   private String resourceUrl;
   @Nullable
   private String tag;
   @Nullable
   private Long start;
   @Nullable
   private Long end;
   @Nullable
   private String startFragmentId;
   @Nullable
   private String endFragmentId;
   @Nullable
   private String variable;
   @NotNull
   private final transient Lazy variableMap$delegate;
   @NotNull
   private transient String _userNameSpace;

   public BookChapter(@NotNull String url, @NotNull String title, boolean isVolume, @NotNull String baseUrl, @NotNull String bookUrl, int index, @Nullable String resourceUrl, @Nullable String tag, @Nullable Long start, @Nullable Long end, @Nullable String startFragmentId, @Nullable String endFragmentId, @Nullable String variable) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(title, "title");
      Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
      Intrinsics.checkNotNullParameter(bookUrl, "bookUrl");
      super();
      this.url = url;
      this.title = title;
      this.isVolume = isVolume;
      this.baseUrl = baseUrl;
      this.bookUrl = bookUrl;
      this.index = index;
      this.resourceUrl = resourceUrl;
      this.tag = tag;
      this.start = start;
      this.end = end;
      this.startFragmentId = startFragmentId;
      this.endFragmentId = endFragmentId;
      this.variable = variable;
      this.variableMap$delegate = LazyKt.lazy((Function0)(new Function0<HashMap<String, String>>() {
         @NotNull
         public final HashMap<String, String> invoke() {
            Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
            String json$iv = BookChapter.this.getVariable();
            int $i$f$fromJsonObject = false;
            boolean var5 = false;

            Object var6;
            try {
               Companion var13 = Result.Companion;
               int var7 = false;
               int $i$f$genericType = false;
               Type var16 = (new BookChapter$variableMap$2$invoke$$inlined$fromJsonObject$1()).getType();
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
   public BookChapter(String var1, String var2, boolean var3, String var4, String var5, int var6, String var7, String var8, Long var9, Long var10, String var11, String var12, String var13, int var14, DefaultConstructorMarker var15) {
      if ((var14 & 1) != 0) {
         var1 = "";
      }

      if ((var14 & 2) != 0) {
         var2 = "";
      }

      if ((var14 & 4) != 0) {
         var3 = false;
      }

      if ((var14 & 8) != 0) {
         var4 = "";
      }

      if ((var14 & 16) != 0) {
         var5 = "";
      }

      if ((var14 & 32) != 0) {
         var6 = 0;
      }

      if ((var14 & 64) != 0) {
         var7 = null;
      }

      if ((var14 & 128) != 0) {
         var8 = null;
      }

      if ((var14 & 256) != 0) {
         var9 = null;
      }

      if ((var14 & 512) != 0) {
         var10 = null;
      }

      if ((var14 & 1024) != 0) {
         var11 = null;
      }

      if ((var14 & 2048) != 0) {
         var12 = null;
      }

      if ((var14 & 4096) != 0) {
         var13 = null;
      }

      this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
   }

   @NotNull
   public final String getUrl() {
      return this.url;
   }

   public final void setUrl(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.url = var1;
   }

   @NotNull
   public final String getTitle() {
      return this.title;
   }

   public final void setTitle(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.title = var1;
   }

   public final boolean isVolume() {
      return this.isVolume;
   }

   public final void setVolume(boolean <set-?>) {
      this.isVolume = var1;
   }

   @NotNull
   public final String getBaseUrl() {
      return this.baseUrl;
   }

   public final void setBaseUrl(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.baseUrl = var1;
   }

   @NotNull
   public final String getBookUrl() {
      return this.bookUrl;
   }

   public final void setBookUrl(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.bookUrl = var1;
   }

   public final int getIndex() {
      return this.index;
   }

   public final void setIndex(int <set-?>) {
      this.index = var1;
   }

   @Nullable
   public final String getResourceUrl() {
      return this.resourceUrl;
   }

   public final void setResourceUrl(@Nullable String <set-?>) {
      this.resourceUrl = var1;
   }

   @Nullable
   public final String getTag() {
      return this.tag;
   }

   public final void setTag(@Nullable String <set-?>) {
      this.tag = var1;
   }

   @Nullable
   public final Long getStart() {
      return this.start;
   }

   public final void setStart(@Nullable Long <set-?>) {
      this.start = var1;
   }

   @Nullable
   public final Long getEnd() {
      return this.end;
   }

   public final void setEnd(@Nullable Long <set-?>) {
      this.end = var1;
   }

   @Nullable
   public final String getStartFragmentId() {
      return this.startFragmentId;
   }

   public final void setStartFragmentId(@Nullable String <set-?>) {
      this.startFragmentId = var1;
   }

   @Nullable
   public final String getEndFragmentId() {
      return this.endFragmentId;
   }

   public final void setEndFragmentId(@Nullable String <set-?>) {
      this.endFragmentId = var1;
   }

   @Nullable
   public final String getVariable() {
      return this.variable;
   }

   public final void setVariable(@Nullable String <set-?>) {
      this.variable = var1;
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

   public int hashCode() {
      return this.url.hashCode();
   }

   public boolean equals(@Nullable Object other) {
      return other instanceof BookChapter ? Intrinsics.areEqual(((BookChapter)other).url, this.url) : false;
   }

   @NotNull
   public final String getAbsoluteURL() {
      Matcher urlMatcher = AnalyzeUrl.Companion.getParamPattern().matcher((CharSequence)this.url);
      String var10000;
      String urlAbsoluteBefore;
      int var5;
      boolean var6;
      if (urlMatcher.find()) {
         urlAbsoluteBefore = this.url;
         byte var4 = 0;
         var5 = urlMatcher.start();
         var6 = false;
         if (urlAbsoluteBefore == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
         }

         var10000 = urlAbsoluteBefore.substring(var4, var5);
         Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
      } else {
         var10000 = this.url;
      }

      String urlBefore = var10000;
      urlAbsoluteBefore = NetworkUtils.INSTANCE.getAbsoluteURL(this.baseUrl, urlBefore);
      if (urlBefore.length() == this.url.length()) {
         var10000 = urlAbsoluteBefore;
      } else {
         StringBuilder var8 = (new StringBuilder()).append(urlAbsoluteBefore).append(',');
         String var7 = this.url;
         var5 = urlMatcher.end();
         var6 = false;
         if (var7 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
         }

         String var10001 = var7.substring(var5);
         Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).substring(startIndex)");
         var10000 = var8.append(var10001).toString();
      }

      return var10000;
   }

   @NotNull
   public final String getFileName() {
      StringCompanionObject var1 = StringCompanionObject.INSTANCE;
      String var2 = "%05d-%s.nb";
      Object[] var3 = new Object[]{this.index, MD5Utils.INSTANCE.md5Encode16(this.title)};
      boolean var4 = false;
      String var10000 = String.format(var2, Arrays.copyOf(var3, var3.length));
      Intrinsics.checkNotNullExpressionValue(var10000, "java.lang.String.format(format, *args)");
      return var10000;
   }

   @Nullable
   public String getVariable(@NotNull String key) {
      return RuleDataInterface.DefaultImpls.getVariable(this, key);
   }

   @NotNull
   public final String component1() {
      return this.url;
   }

   @NotNull
   public final String component2() {
      return this.title;
   }

   public final boolean component3() {
      return this.isVolume;
   }

   @NotNull
   public final String component4() {
      return this.baseUrl;
   }

   @NotNull
   public final String component5() {
      return this.bookUrl;
   }

   public final int component6() {
      return this.index;
   }

   @Nullable
   public final String component7() {
      return this.resourceUrl;
   }

   @Nullable
   public final String component8() {
      return this.tag;
   }

   @Nullable
   public final Long component9() {
      return this.start;
   }

   @Nullable
   public final Long component10() {
      return this.end;
   }

   @Nullable
   public final String component11() {
      return this.startFragmentId;
   }

   @Nullable
   public final String component12() {
      return this.endFragmentId;
   }

   @Nullable
   public final String component13() {
      return this.variable;
   }

   @NotNull
   public final BookChapter copy(@NotNull String url, @NotNull String title, boolean isVolume, @NotNull String baseUrl, @NotNull String bookUrl, int index, @Nullable String resourceUrl, @Nullable String tag, @Nullable Long start, @Nullable Long end, @Nullable String startFragmentId, @Nullable String endFragmentId, @Nullable String variable) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(title, "title");
      Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
      Intrinsics.checkNotNullParameter(bookUrl, "bookUrl");
      return new BookChapter(url, title, isVolume, baseUrl, bookUrl, index, resourceUrl, tag, start, end, startFragmentId, endFragmentId, variable);
   }

   // $FF: synthetic method
   public static BookChapter copy$default(BookChapter var0, String var1, String var2, boolean var3, String var4, String var5, int var6, String var7, String var8, Long var9, Long var10, String var11, String var12, String var13, int var14, Object var15) {
      if ((var14 & 1) != 0) {
         var1 = var0.url;
      }

      if ((var14 & 2) != 0) {
         var2 = var0.title;
      }

      if ((var14 & 4) != 0) {
         var3 = var0.isVolume;
      }

      if ((var14 & 8) != 0) {
         var4 = var0.baseUrl;
      }

      if ((var14 & 16) != 0) {
         var5 = var0.bookUrl;
      }

      if ((var14 & 32) != 0) {
         var6 = var0.index;
      }

      if ((var14 & 64) != 0) {
         var7 = var0.resourceUrl;
      }

      if ((var14 & 128) != 0) {
         var8 = var0.tag;
      }

      if ((var14 & 256) != 0) {
         var9 = var0.start;
      }

      if ((var14 & 512) != 0) {
         var10 = var0.end;
      }

      if ((var14 & 1024) != 0) {
         var11 = var0.startFragmentId;
      }

      if ((var14 & 2048) != 0) {
         var12 = var0.endFragmentId;
      }

      if ((var14 & 4096) != 0) {
         var13 = var0.variable;
      }

      return var0.copy(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
   }

   @NotNull
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("BookChapter(url=").append(this.url).append(", title=").append(this.title).append(", isVolume=").append(this.isVolume).append(", baseUrl=").append(this.baseUrl).append(", bookUrl=").append(this.bookUrl).append(", index=").append(this.index).append(", resourceUrl=").append(this.resourceUrl).append(", tag=").append(this.tag).append(", start=").append(this.start).append(", end=").append(this.end).append(", startFragmentId=").append(this.startFragmentId).append(", endFragmentId=");
      var1.append(this.endFragmentId).append(", variable=").append(this.variable).append(')');
      return var1.toString();
   }

   public BookChapter() {
      this((String)null, (String)null, false, (String)null, (String)null, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8191, (DefaultConstructorMarker)null);
   }
}
