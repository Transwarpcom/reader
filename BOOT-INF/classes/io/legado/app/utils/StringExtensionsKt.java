package io.legado.app.utils;

import io.legado.app.constant.AppPattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 2,
   xi = 48,
   d1 = {"\u0000&\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002\u001a\f\u0010\u0004\u001a\u00020\u0002*\u0004\u0018\u00010\u0002\u001a\f\u0010\u0005\u001a\u00020\u0006*\u0004\u0018\u00010\u0002\u001a\f\u0010\u0007\u001a\u00020\u0006*\u0004\u0018\u00010\u0002\u001a\f\u0010\b\u001a\u00020\u0006*\u0004\u0018\u00010\u0002\u001a\f\u0010\t\u001a\u00020\u0006*\u0004\u0018\u00010\u0002\u001a\f\u0010\n\u001a\u00020\u0006*\u0004\u0018\u00010\u0002\u001a\u0016\u0010\u000b\u001a\u00020\u0006*\u0004\u0018\u00010\u00022\b\b\u0002\u0010\f\u001a\u00020\u0006\u001a\f\u0010\r\u001a\u00020\u0006*\u0004\u0018\u00010\u0002\u001a\u000e\u0010\u000e\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u0002\u001a)\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010*\u00020\u00022\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0010\"\u00020\u0002¢\u0006\u0002\u0010\u0012\u001a'\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0001¢\u0006\u0002\u0010\u0016\u001a\u0012\u0010\u0017\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0002\u001a\u0015\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010*\u00020\u0002¢\u0006\u0002\u0010\u001a¨\u0006\u001b"},
   d2 = {"cnCompare", "", "", "other", "htmlFormat", "isAbsUrl", "", "isDataUrl", "isJson", "isJsonArray", "isJsonObject", "isTrue", "nullIsTrue", "isXml", "safeTrim", "splitNotBlank", "", "delimiter", "(Ljava/lang/String;[Ljava/lang/String;)[Ljava/lang/String;", "regex", "Lkotlin/text/Regex;", "limit", "(Ljava/lang/String;Lkotlin/text/Regex;I)[Ljava/lang/String;", "startWithIgnoreCase", "start", "toStringArray", "(Ljava/lang/String;)[Ljava/lang/String;", "reader-pro"}
)
public final class StringExtensionsKt {
   @Nullable
   public static final String safeTrim(@Nullable String $this$safeTrim) {
      CharSequence var1 = (CharSequence)$this$safeTrim;
      boolean var2 = false;
      boolean var3 = false;
      String var10000;
      if (var1 == null || StringsKt.isBlank(var1)) {
         var10000 = null;
      } else {
         var2 = false;
         if ($this$safeTrim == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
         }

         var10000 = StringsKt.trim((CharSequence)$this$safeTrim).toString();
      }

      return var10000;
   }

   public static final boolean isAbsUrl(@Nullable String $this$isAbsUrl) {
      CharSequence var1 = (CharSequence)$this$isAbsUrl;
      boolean var2 = false;
      boolean var3 = false;
      return var1 == null || StringsKt.isBlank(var1) ? false : StringsKt.startsWith($this$isAbsUrl, "http://", true) || StringsKt.startsWith($this$isAbsUrl, "https://", true);
   }

   public static final boolean isDataUrl(@Nullable String $this$isDataUrl) {
      boolean var10000;
      if ($this$isDataUrl == null) {
         var10000 = false;
      } else {
         boolean var4 = false;
         boolean var5 = false;
         int var7 = false;
         boolean var2 = AppPattern.INSTANCE.getDataUriRegex().matches((CharSequence)$this$isDataUrl);
         var10000 = var2;
      }

      return var10000;
   }

   public static final boolean isJson(@Nullable String $this$isJson) {
      boolean var10000;
      if ($this$isJson == null) {
         var10000 = false;
      } else {
         boolean var4 = false;
         boolean var5 = false;
         int var7 = false;
         boolean var9 = false;
         String str = StringsKt.trim((CharSequence)$this$isJson).toString();
         boolean var2 = StringsKt.startsWith$default(str, "{", false, 2, (Object)null) && StringsKt.endsWith$default(str, "}", false, 2, (Object)null) ? true : StringsKt.startsWith$default(str, "[", false, 2, (Object)null) && StringsKt.endsWith$default(str, "]", false, 2, (Object)null);
         var10000 = var2;
      }

      return var10000;
   }

   public static final boolean isJsonObject(@Nullable String $this$isJsonObject) {
      boolean var10000;
      if ($this$isJsonObject == null) {
         var10000 = false;
      } else {
         boolean var4 = false;
         boolean var5 = false;
         int var7 = false;
         boolean var9 = false;
         String str = StringsKt.trim((CharSequence)$this$isJsonObject).toString();
         boolean var2 = StringsKt.startsWith$default(str, "{", false, 2, (Object)null) && StringsKt.endsWith$default(str, "}", false, 2, (Object)null);
         var10000 = var2;
      }

      return var10000;
   }

   public static final boolean isJsonArray(@Nullable String $this$isJsonArray) {
      boolean var10000;
      if ($this$isJsonArray == null) {
         var10000 = false;
      } else {
         boolean var4 = false;
         boolean var5 = false;
         int var7 = false;
         boolean var9 = false;
         String str = StringsKt.trim((CharSequence)$this$isJsonArray).toString();
         boolean var2 = StringsKt.startsWith$default(str, "[", false, 2, (Object)null) && StringsKt.endsWith$default(str, "]", false, 2, (Object)null);
         var10000 = var2;
      }

      return var10000;
   }

   public static final boolean isXml(@Nullable String $this$isXml) {
      boolean var10000;
      if ($this$isXml == null) {
         var10000 = false;
      } else {
         boolean var4 = false;
         boolean var5 = false;
         int var7 = false;
         boolean var9 = false;
         String str = StringsKt.trim((CharSequence)$this$isXml).toString();
         boolean var2 = StringsKt.startsWith$default(str, "<", false, 2, (Object)null) && StringsKt.endsWith$default(str, ">", false, 2, (Object)null);
         var10000 = var2;
      }

      return var10000;
   }

   public static final boolean isTrue(@Nullable String $this$isTrue, boolean nullIsTrue) {
      CharSequence var2 = (CharSequence)$this$isTrue;
      boolean var3 = false;
      boolean var4 = false;
      if (var2 != null && !StringsKt.isBlank(var2) && !Intrinsics.areEqual($this$isTrue, "null")) {
         var2 = (CharSequence)$this$isTrue;
         String var5 = "\\s*(?i)(false|no|not|0)\\s*";
         var4 = false;
         Regex var6 = new Regex(var5);
         var4 = false;
         return !var6.matches(var2);
      } else {
         return nullIsTrue;
      }
   }

   // $FF: synthetic method
   public static boolean isTrue$default(String var0, boolean var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = false;
      }

      return isTrue(var0, var1);
   }

   @NotNull
   public static final String htmlFormat(@Nullable String $this$htmlFormat) {
      CharSequence var1 = (CharSequence)$this$htmlFormat;
      boolean var2 = false;
      boolean var3 = false;
      String var10000;
      if (var1 == null || StringsKt.isBlank(var1)) {
         var10000 = "";
      } else {
         var1 = (CharSequence)$this$htmlFormat;
         String var5 = "(?i)<(br[\\s/]*|/*p\\b.*?|/*div\\b.*?)>";
         var3 = false;
         Regex var6 = new Regex(var5);
         String var7 = "\n";
         boolean var4 = false;
         var1 = (CharSequence)var6.replace(var1, var7);
         var5 = "<[script>]*.*?>|&nbsp;";
         var3 = false;
         var6 = new Regex(var5);
         var7 = "";
         var4 = false;
         var1 = (CharSequence)var6.replace(var1, var7);
         var5 = "\\s*\\n+\\s*";
         var3 = false;
         var6 = new Regex(var5);
         var7 = "\n　　";
         var4 = false;
         var1 = (CharSequence)var6.replace(var1, var7);
         var5 = "^[\\n\\s]+";
         var3 = false;
         var6 = new Regex(var5);
         var7 = "　　";
         var4 = false;
         var1 = (CharSequence)var6.replace(var1, var7);
         var5 = "[\\n\\s]+$";
         var3 = false;
         var6 = new Regex(var5);
         var7 = "";
         var4 = false;
         var10000 = var6.replace(var1, var7);
      }

      return var10000;
   }

   @NotNull
   public static final String[] splitNotBlank(@NotNull String $this$splitNotBlank, @NotNull String... delimiter) {
      Intrinsics.checkNotNullParameter($this$splitNotBlank, "<this>");
      Intrinsics.checkNotNullParameter(delimiter, "delimiter");
      boolean var3 = false;
      boolean var4 = false;
      int var6 = false;
      Iterable $this$filterNot$iv = (Iterable)StringsKt.split$default((CharSequence)$this$splitNotBlank, (String[])Arrays.copyOf(delimiter, delimiter.length), false, 0, 6, (Object)null);
      int $i$f$toTypedArray = false;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$filterNot$iv, 10)));
      int $i$f$filterNotTo = false;
      Iterator var12 = $this$filterNot$iv.iterator();

      Object element$iv$iv;
      String it;
      boolean var16;
      while(var12.hasNext()) {
         element$iv$iv = var12.next();
         it = (String)element$iv$iv;
         var16 = false;
         boolean var18 = false;
         if (it == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
         }

         String var19 = StringsKt.trim((CharSequence)it).toString();
         destination$iv$iv.add(var19);
      }

      $this$filterNot$iv = (Iterable)((List)destination$iv$iv);
      $i$f$toTypedArray = false;
      destination$iv$iv = (Collection)(new ArrayList());
      $i$f$filterNotTo = false;
      var12 = $this$filterNot$iv.iterator();

      while(var12.hasNext()) {
         element$iv$iv = var12.next();
         it = (String)element$iv$iv;
         var16 = false;
         if (!StringsKt.isBlank((CharSequence)it)) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      Collection $this$toTypedArray$iv = (Collection)((List)destination$iv$iv);
      $i$f$toTypedArray = false;
      Object[] var10000 = $this$toTypedArray$iv.toArray(new String[0]);
      if (var10000 == null) {
         throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
      } else {
         return (String[])var10000;
      }
   }

   @NotNull
   public static final String[] splitNotBlank(@NotNull String $this$splitNotBlank, @NotNull Regex regex, int limit) {
      Intrinsics.checkNotNullParameter($this$splitNotBlank, "<this>");
      Intrinsics.checkNotNullParameter(regex, "regex");
      boolean var4 = false;
      boolean var5 = false;
      int var7 = false;
      CharSequence var8 = (CharSequence)$this$splitNotBlank;
      boolean $i$f$toTypedArray = false;
      Iterable $this$filterNot$iv = (Iterable)regex.split(var8, limit);
      $i$f$toTypedArray = false;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$filterNot$iv, 10)));
      int $i$f$filterNotTo = false;
      Iterator var13 = $this$filterNot$iv.iterator();

      Object element$iv$iv;
      String it;
      boolean var17;
      while(var13.hasNext()) {
         element$iv$iv = var13.next();
         it = (String)element$iv$iv;
         var17 = false;
         boolean var19 = false;
         if (it == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
         }

         String var20 = StringsKt.trim((CharSequence)it).toString();
         destination$iv$iv.add(var20);
      }

      $this$filterNot$iv = (Iterable)((List)destination$iv$iv);
      $i$f$toTypedArray = false;
      destination$iv$iv = (Collection)(new ArrayList());
      $i$f$filterNotTo = false;
      var13 = $this$filterNot$iv.iterator();

      while(var13.hasNext()) {
         element$iv$iv = var13.next();
         it = (String)element$iv$iv;
         var17 = false;
         if (!StringsKt.isBlank((CharSequence)it)) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      Collection $this$toTypedArray$iv = (Collection)((List)destination$iv$iv);
      $i$f$toTypedArray = false;
      Object[] var10000 = $this$toTypedArray$iv.toArray(new String[0]);
      if (var10000 == null) {
         throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
      } else {
         return (String[])var10000;
      }
   }

   // $FF: synthetic method
   public static String[] splitNotBlank$default(String var0, Regex var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      return splitNotBlank(var0, var1, var2);
   }

   public static final boolean startWithIgnoreCase(@NotNull String $this$startWithIgnoreCase, @NotNull String start) {
      Intrinsics.checkNotNullParameter($this$startWithIgnoreCase, "<this>");
      Intrinsics.checkNotNullParameter(start, "start");
      return StringsKt.isBlank((CharSequence)$this$startWithIgnoreCase) ? false : StringsKt.startsWith($this$startWithIgnoreCase, start, true);
   }

   public static final int cnCompare(@NotNull String $this$cnCompare, @NotNull String other) {
      Intrinsics.checkNotNullParameter($this$cnCompare, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      return $this$cnCompare.compareTo(other);
   }

   @NotNull
   public static final String[] toStringArray(@NotNull String $this$toStringArray) {
      Intrinsics.checkNotNullParameter($this$toStringArray, "<this>");
      int codePointIndex = 0;

      String[] var2;
      String[] var4;
      try {
         int var11 = 0;
         byte var13 = 0;
         int start = $this$toStringArray.length();
         boolean var7 = false;
         int var3 = $this$toStringArray.codePointCount(var13, start);

         for(var4 = new String[var3]; var11 < var3; ++var11) {
            start = codePointIndex;
            byte var8 = 1;
            boolean var9 = false;
            codePointIndex = $this$toStringArray.offsetByCodePoints(codePointIndex, var8);
            boolean var14 = false;
            String var10002 = $this$toStringArray.substring(start, codePointIndex);
            Intrinsics.checkNotNullExpressionValue(var10002, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            var4[var11] = var10002;
         }

         var2 = var4;
      } catch (Exception var10) {
         CharSequence var10000 = (CharSequence)$this$toStringArray;
         var4 = new String[]{""};
         Collection $this$toTypedArray$iv = (Collection)StringsKt.split$default(var10000, var4, false, 0, 6, (Object)null);
         int $i$f$toTypedArray = false;
         Object[] var15 = $this$toTypedArray$iv.toArray(new String[0]);
         if (var15 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
         }

         var2 = (String[])var15;
      }

      return var2;
   }
}
