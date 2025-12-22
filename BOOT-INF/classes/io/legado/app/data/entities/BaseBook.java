package io.legado.app.data.entities;

import io.legado.app.model.analyzeRule.RuleDataInterface;
import io.legado.app.utils.StringExtensionsKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010 \n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\u001bH\u0016R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\u0005\"\u0004\b\r\u0010\u0007R\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0005\"\u0004\b\u0010\u0010\u0007R\u0018\u0010\u0011\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0005\"\u0004\b\u0013\u0010\u0007R\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0005\"\u0004\b\u0016\u0010\u0007R\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0005\"\u0004\b\u0019\u0010\u0007¨\u0006\u001c"},
   d2 = {"Lio/legado/app/data/entities/BaseBook;", "Lio/legado/app/model/analyzeRule/RuleDataInterface;", "author", "", "getAuthor", "()Ljava/lang/String;", "setAuthor", "(Ljava/lang/String;)V", "bookUrl", "getBookUrl", "setBookUrl", "infoHtml", "getInfoHtml", "setInfoHtml", "kind", "getKind", "setKind", "name", "getName", "setName", "tocHtml", "getTocHtml", "setTocHtml", "wordCount", "getWordCount", "setWordCount", "getKindList", "", "reader-pro"}
)
public interface BaseBook extends RuleDataInterface {
   @NotNull
   String getName();

   void setName(@NotNull String <set-?>);

   @NotNull
   String getAuthor();

   void setAuthor(@NotNull String <set-?>);

   @NotNull
   String getBookUrl();

   void setBookUrl(@NotNull String <set-?>);

   @Nullable
   String getKind();

   void setKind(@Nullable String <set-?>);

   @Nullable
   String getWordCount();

   void setWordCount(@Nullable String <set-?>);

   @Nullable
   String getInfoHtml();

   void setInfoHtml(@Nullable String <set-?>);

   @Nullable
   String getTocHtml();

   void setTocHtml(@Nullable String <set-?>);

   @NotNull
   List<String> getKindList();

   @Metadata(
      mv = {1, 5, 1},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      @NotNull
      public static List<String> getKindList(@NotNull BaseBook this) {
         Intrinsics.checkNotNullParameter(var0, "this");
         boolean var2 = false;
         ArrayList kindList = new ArrayList();
         String var10 = var0.getWordCount();
         boolean var4;
         boolean var5;
         boolean var7;
         if (var10 != null) {
            var4 = false;
            var5 = false;
            var7 = false;
            CharSequence var8 = (CharSequence)var10;
            boolean var9 = false;
            if (!StringsKt.isBlank(var8)) {
               kindList.add(var10);
            }
         }

         var10 = var0.getKind();
         if (var10 != null) {
            var4 = false;
            var5 = false;
            var7 = false;
            String[] var11 = new String[]{",", "\n"};
            String[] kinds = StringExtensionsKt.splitNotBlank(var10, var11);
            CollectionsKt.addAll((Collection)kindList, kinds);
         }

         return (List)kindList;
      }

      @Nullable
      public static String getVariable(@NotNull BaseBook this, @NotNull String key) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(key, "key");
         return RuleDataInterface.DefaultImpls.getVariable((RuleDataInterface)var0, key);
      }
   }
}
