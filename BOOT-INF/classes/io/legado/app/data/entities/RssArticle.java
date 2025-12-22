package io.legado.app.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import io.legado.app.model.analyzeRule.RuleDataInterface;
import io.legado.app.utils.GsonExtensionsKt;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Result.Companion;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties({"variableMap", "_userNameSpace", "userNameSpace"})
@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B}\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0010J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u000eHÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0007HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0081\u0001\u0010@\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010A\u001a\u00020\u000e2\b\u0010B\u001a\u0004\u0018\u00010CH\u0096\u0002J\b\u0010D\u001a\u00020\u0003H\u0016J\b\u0010E\u001a\u00020FH\u0016J\u001a\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\u00032\b\u0010J\u001a\u0004\u0018\u00010\u0003H\u0016J\u000e\u0010K\u001a\u00020H2\u0006\u0010L\u001a\u00020\u0003J\t\u0010M\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0011\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0013\"\u0004\b#\u0010\u0015R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0013\"\u0004\b)\u0010\u0015R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0013\"\u0004\b+\u0010\u0015R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0013\"\u0004\b-\u0010\u0015R7\u0010.\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030/j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`08VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b1\u00102¨\u0006N"},
   d2 = {"Lio/legado/app/data/entities/RssArticle;", "Lio/legado/app/model/analyzeRule/RuleDataInterface;", "origin", "", "sort", "title", "order", "", "link", "pubDate", "description", "content", "image", "read", "", "variable", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "_userNameSpace", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "getDescription", "setDescription", "getImage", "setImage", "getLink", "setLink", "getOrder", "()J", "setOrder", "(J)V", "getOrigin", "setOrigin", "getPubDate", "setPubDate", "getRead", "()Z", "setRead", "(Z)V", "getSort", "setSort", "getTitle", "setTitle", "getVariable", "setVariable", "variableMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getVariableMap", "()Ljava/util/HashMap;", "variableMap$delegate", "Lkotlin/Lazy;", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "getUserNameSpace", "hashCode", "", "putVariable", "", "key", "value", "setUserNameSpace", "nameSpace", "toString", "reader-pro"}
)
public final class RssArticle implements RuleDataInterface {
   @NotNull
   private String origin;
   @NotNull
   private String sort;
   @NotNull
   private String title;
   private long order;
   @NotNull
   private String link;
   @Nullable
   private String pubDate;
   @Nullable
   private String description;
   @Nullable
   private String content;
   @Nullable
   private String image;
   private boolean read;
   @Nullable
   private String variable;
   @NotNull
   private final transient Lazy variableMap$delegate;
   @NotNull
   private transient String _userNameSpace;

   public RssArticle(@NotNull String origin, @NotNull String sort, @NotNull String title, long order, @NotNull String link, @Nullable String pubDate, @Nullable String description, @Nullable String content, @Nullable String image, boolean read, @Nullable String variable) {
      Intrinsics.checkNotNullParameter(origin, "origin");
      Intrinsics.checkNotNullParameter(sort, "sort");
      Intrinsics.checkNotNullParameter(title, "title");
      Intrinsics.checkNotNullParameter(link, "link");
      super();
      this.origin = origin;
      this.sort = sort;
      this.title = title;
      this.order = order;
      this.link = link;
      this.pubDate = pubDate;
      this.description = description;
      this.content = content;
      this.image = image;
      this.read = read;
      this.variable = variable;
      this.variableMap$delegate = LazyKt.lazy((Function0)(new Function0<HashMap<String, String>>() {
         @NotNull
         public final HashMap<String, String> invoke() {
            Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
            String json$iv = RssArticle.this.getVariable();
            int $i$f$fromJsonObject = false;
            boolean var5 = false;

            Object var6;
            try {
               Companion var13 = Result.Companion;
               int var7 = false;
               int $i$f$genericType = false;
               Type var16 = (new RssArticle$variableMap$2$invoke$$inlined$fromJsonObject$1()).getType();
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
   public RssArticle(String var1, String var2, String var3, long var4, String var6, String var7, String var8, String var9, String var10, boolean var11, String var12, int var13, DefaultConstructorMarker var14) {
      if ((var13 & 1) != 0) {
         var1 = "";
      }

      if ((var13 & 2) != 0) {
         var2 = "";
      }

      if ((var13 & 4) != 0) {
         var3 = "";
      }

      if ((var13 & 8) != 0) {
         var4 = 0L;
      }

      if ((var13 & 16) != 0) {
         var6 = "";
      }

      if ((var13 & 32) != 0) {
         var7 = null;
      }

      if ((var13 & 64) != 0) {
         var8 = null;
      }

      if ((var13 & 128) != 0) {
         var9 = null;
      }

      if ((var13 & 256) != 0) {
         var10 = null;
      }

      if ((var13 & 512) != 0) {
         var11 = false;
      }

      if ((var13 & 1024) != 0) {
         var12 = null;
      }

      this(var1, var2, var3, var4, var6, var7, var8, var9, var10, var11, var12);
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
   public final String getSort() {
      return this.sort;
   }

   public final void setSort(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.sort = var1;
   }

   @NotNull
   public final String getTitle() {
      return this.title;
   }

   public final void setTitle(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.title = var1;
   }

   public final long getOrder() {
      return this.order;
   }

   public final void setOrder(long <set-?>) {
      this.order = var1;
   }

   @NotNull
   public final String getLink() {
      return this.link;
   }

   public final void setLink(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.link = var1;
   }

   @Nullable
   public final String getPubDate() {
      return this.pubDate;
   }

   public final void setPubDate(@Nullable String <set-?>) {
      this.pubDate = var1;
   }

   @Nullable
   public final String getDescription() {
      return this.description;
   }

   public final void setDescription(@Nullable String <set-?>) {
      this.description = var1;
   }

   @Nullable
   public final String getContent() {
      return this.content;
   }

   public final void setContent(@Nullable String <set-?>) {
      this.content = var1;
   }

   @Nullable
   public final String getImage() {
      return this.image;
   }

   public final void setImage(@Nullable String <set-?>) {
      this.image = var1;
   }

   public final boolean getRead() {
      return this.read;
   }

   public final void setRead(boolean <set-?>) {
      this.read = var1;
   }

   @Nullable
   public final String getVariable() {
      return this.variable;
   }

   public final void setVariable(@Nullable String <set-?>) {
      this.variable = var1;
   }

   public int hashCode() {
      return this.link.hashCode();
   }

   public boolean equals(@Nullable Object other) {
      if (other == null) {
         return false;
      } else {
         return other instanceof RssArticle ? Intrinsics.areEqual(this.origin, ((RssArticle)other).origin) && Intrinsics.areEqual(this.link, ((RssArticle)other).link) : false;
      }
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
   public String getVariable(@NotNull String key) {
      return RuleDataInterface.DefaultImpls.getVariable(this, key);
   }

   @NotNull
   public final String component1() {
      return this.origin;
   }

   @NotNull
   public final String component2() {
      return this.sort;
   }

   @NotNull
   public final String component3() {
      return this.title;
   }

   public final long component4() {
      return this.order;
   }

   @NotNull
   public final String component5() {
      return this.link;
   }

   @Nullable
   public final String component6() {
      return this.pubDate;
   }

   @Nullable
   public final String component7() {
      return this.description;
   }

   @Nullable
   public final String component8() {
      return this.content;
   }

   @Nullable
   public final String component9() {
      return this.image;
   }

   public final boolean component10() {
      return this.read;
   }

   @Nullable
   public final String component11() {
      return this.variable;
   }

   @NotNull
   public final RssArticle copy(@NotNull String origin, @NotNull String sort, @NotNull String title, long order, @NotNull String link, @Nullable String pubDate, @Nullable String description, @Nullable String content, @Nullable String image, boolean read, @Nullable String variable) {
      Intrinsics.checkNotNullParameter(origin, "origin");
      Intrinsics.checkNotNullParameter(sort, "sort");
      Intrinsics.checkNotNullParameter(title, "title");
      Intrinsics.checkNotNullParameter(link, "link");
      return new RssArticle(origin, sort, title, order, link, pubDate, description, content, image, read, variable);
   }

   // $FF: synthetic method
   public static RssArticle copy$default(RssArticle var0, String var1, String var2, String var3, long var4, String var6, String var7, String var8, String var9, String var10, boolean var11, String var12, int var13, Object var14) {
      if ((var13 & 1) != 0) {
         var1 = var0.origin;
      }

      if ((var13 & 2) != 0) {
         var2 = var0.sort;
      }

      if ((var13 & 4) != 0) {
         var3 = var0.title;
      }

      if ((var13 & 8) != 0) {
         var4 = var0.order;
      }

      if ((var13 & 16) != 0) {
         var6 = var0.link;
      }

      if ((var13 & 32) != 0) {
         var7 = var0.pubDate;
      }

      if ((var13 & 64) != 0) {
         var8 = var0.description;
      }

      if ((var13 & 128) != 0) {
         var9 = var0.content;
      }

      if ((var13 & 256) != 0) {
         var10 = var0.image;
      }

      if ((var13 & 512) != 0) {
         var11 = var0.read;
      }

      if ((var13 & 1024) != 0) {
         var12 = var0.variable;
      }

      return var0.copy(var1, var2, var3, var4, var6, var7, var8, var9, var10, var11, var12);
   }

   @NotNull
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("RssArticle(origin=").append(this.origin).append(", sort=").append(this.sort).append(", title=").append(this.title).append(", order=").append(this.order).append(", link=").append(this.link).append(", pubDate=").append(this.pubDate).append(", description=").append(this.description).append(", content=").append(this.content).append(", image=").append(this.image).append(", read=").append(this.read).append(", variable=").append(this.variable).append(')');
      return var1.toString();
   }

   public RssArticle() {
      this((String)null, (String)null, (String)null, 0L, (String)null, (String)null, (String)null, (String)null, (String)null, false, (String)null, 2047, (DefaultConstructorMarker)null);
   }
}
