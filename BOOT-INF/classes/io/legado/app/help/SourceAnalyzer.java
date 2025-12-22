package io.legado.app.help;

import com.google.gson.Gson;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ParseContext;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.ReadContext;
import io.legado.app.data.entities.BookSource;
import io.legado.app.data.entities.rule.BookInfoRule;
import io.legado.app.data.entities.rule.ContentRule;
import io.legado.app.data.entities.rule.ExploreRule;
import io.legado.app.data.entities.rule.SearchRule;
import io.legado.app.data.entities.rule.TocRule;
import io.legado.app.exception.NoStackTraceException;
import io.legado.app.model.Debug;
import io.legado.app.model.DebugLog;
import io.legado.app.utils.GsonExtensionsKt;
import io.legado.app.utils.JsonExtensionsKt;
import io.legado.app.utils.StringExtensionsKt;
import java.io.InputStream;
import java.lang.reflect.Type;
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
import kotlin.Result.Companion;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001cB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\f\u0010\rJ*\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000f0\b2\u0006\u0010\u0010\u001a\u00020\u0011ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0012\u0010\u0013J*\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000f0\b2\u0006\u0010\n\u001a\u00020\u000bø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0012\u0010\rJ\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000bH\u0002J\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u000bH\u0002J\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000bH\u0002J\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u000b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000bH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"},
   d2 = {"Lio/legado/app/help/SourceAnalyzer;", "", "()V", "headerPattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "jsPattern", "jsonToBookSource", "Lkotlin/Result;", "Lio/legado/app/data/entities/BookSource;", "json", "", "jsonToBookSource-IoAF18A", "(Ljava/lang/String;)Ljava/lang/Object;", "jsonToBookSources", "", "inputStream", "Ljava/io/InputStream;", "jsonToBookSources-IoAF18A", "(Ljava/io/InputStream;)Ljava/lang/Object;", "toNewRule", "oldRule", "toNewUrl", "oldUrl", "toNewUrls", "oldUrls", "uaToHeader", "ua", "BookSourceAny", "reader-pro"}
)
public final class SourceAnalyzer {
   @NotNull
   public static final SourceAnalyzer INSTANCE = new SourceAnalyzer();
   private static final Pattern headerPattern = Pattern.compile("@Header:\\{.+?\\}", 2);
   private static final Pattern jsPattern = Pattern.compile("\\{\\{.+?\\}\\}", 2);

   private SourceAnalyzer() {
   }

   @NotNull
   public final Object jsonToBookSources_IoAF18A/* $FF was: jsonToBookSources-IoAF18A*/(@NotNull String json) {
      Intrinsics.checkNotNullParameter(json, "json");
      boolean var2 = false;

      Object var3;
      try {
         Companion var16 = Result.Companion;
         int var4 = false;
         boolean var17 = false;
         List bookSources = (List)(new ArrayList());
         if (StringExtensionsKt.isJsonArray(json)) {
            Object var21 = JsonExtensionsKt.getJsonPath().parse(json).read("$", new Predicate[0]);
            Intrinsics.checkNotNullExpressionValue(var21, "jsonPath.parse(json).read(\"$\")");
            List items = (List)var21;
            Iterator var22 = items.iterator();

            while(var22.hasNext()) {
               Map item = (Map)var22.next();
               DocumentContext jsonItem = JsonExtensionsKt.getJsonPath().parse(item);
               SourceAnalyzer var10000 = INSTANCE;
               String var25 = jsonItem.jsonString();
               Intrinsics.checkNotNullExpressionValue(var25, "jsonItem.jsonString()");
               Object var26 = var10000.jsonToBookSource-IoAF18A(var25);
               boolean var11 = false;
               ResultKt.throwOnFailure(var26);
               var11 = false;
               boolean var12 = false;
               BookSource it = (BookSource)var26;
               int var14 = false;
               bookSources.add(it);
            }
         } else {
            if (!StringExtensionsKt.isJsonObject(json)) {
               throw new NoStackTraceException("格式不对");
            }

            Object var18 = INSTANCE.jsonToBookSource-IoAF18A(json);
            boolean var7 = false;
            ResultKt.throwOnFailure(var18);
            var7 = false;
            boolean var8 = false;
            BookSource it = (BookSource)var18;
            int var10 = false;
            bookSources.add(it);
         }

         var17 = false;
         var3 = Result.constructor-impl(bookSources);
      } catch (Throwable var15) {
         Companion var5 = Result.Companion;
         boolean var6 = false;
         var3 = Result.constructor-impl(ResultKt.createFailure(var15));
      }

      return var3;
   }

   @NotNull
   public final Object jsonToBookSources_IoAF18A/* $FF was: jsonToBookSources-IoAF18A*/(@NotNull InputStream inputStream) {
      Intrinsics.checkNotNullParameter(inputStream, "inputStream");
      boolean var2 = false;

      Object var3;
      try {
         Companion var24 = Result.Companion;
         int var4 = false;
         boolean var25 = false;
         List bookSources = (List)(new ArrayList());
         var25 = false;

         Object var7;
         boolean var8;
         boolean var10;
         boolean var14;
         SourceAnalyzer var10000;
         boolean var33;
         try {
            Companion var28 = Result.Companion;
            var8 = false;
            Object var31 = JsonExtensionsKt.getJsonPath().parse(inputStream).read("$", new Predicate[0]);
            Intrinsics.checkNotNullExpressionValue(var31, "jsonPath.parse(inputStream).read(\"$\")");
            List items = (List)var31;
            Iterator var32 = items.iterator();

            while(var32.hasNext()) {
               Map item = (Map)var32.next();
               DocumentContext jsonItem = JsonExtensionsKt.getJsonPath().parse(item);
               var10000 = INSTANCE;
               String var13 = jsonItem.jsonString();
               Intrinsics.checkNotNullExpressionValue(var13, "jsonItem.jsonString()");
               Object var36 = var10000.jsonToBookSource-IoAF18A(var13);
               var14 = false;
               ResultKt.throwOnFailure(var36);
               var14 = false;
               boolean var15 = false;
               BookSource it = (BookSource)var36;
               int var17 = false;
               bookSources.add(it);
            }

            Unit var30 = Unit.INSTANCE;
            var33 = false;
            var7 = Result.constructor-impl(var30);
         } catch (Throwable var22) {
            Companion var9 = Result.Companion;
            var10 = false;
            var7 = Result.constructor-impl(ResultKt.createFailure(var22));
         }

         Object var26 = var7;
         boolean var29 = false;
         var8 = false;
         if (Result.exceptionOrNull-impl(var26) != null) {
            var33 = false;
            var10 = false;
            boolean var35 = false;
            var14 = false;
            Object var37 = JsonExtensionsKt.getJsonPath().parse(inputStream).read("$", new Predicate[0]);
            Intrinsics.checkNotNullExpressionValue(var37, "jsonPath.parse(inputStream).read(\"$\")");
            Map item = (Map)var37;
            DocumentContext jsonItem = JsonExtensionsKt.getJsonPath().parse(item);
            var10000 = INSTANCE;
            String var40 = jsonItem.jsonString();
            Intrinsics.checkNotNullExpressionValue(var40, "jsonItem.jsonString()");
            Object var41 = var10000.jsonToBookSource-IoAF18A(var40);
            boolean var18 = false;
            ResultKt.throwOnFailure(var41);
            var18 = false;
            boolean var19 = false;
            BookSource it = (BookSource)var41;
            int var21 = false;
            bookSources.add(it);
         }

         var25 = false;
         var3 = Result.constructor-impl(bookSources);
      } catch (Throwable var23) {
         Companion var5 = Result.Companion;
         boolean var6 = false;
         var3 = Result.constructor-impl(ResultKt.createFailure(var23));
      }

      return var3;
   }

   @NotNull
   public final Object jsonToBookSource_IoAF18A/* $FF was: jsonToBookSource-IoAF18A*/(@NotNull String json) {
      Intrinsics.checkNotNullParameter(json, "json");
      BookSource source = new BookSource((String)null, (String)null, (String)null, 0, (String)null, 0, false, false, (Boolean)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, 0L, 0L, 0, (String)null, (ExploreRule)null, (String)null, (SearchRule)null, (BookInfoRule)null, (TocRule)null, (ContentRule)null, 67108863, (DefaultConstructorMarker)null);
      Gson $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
      boolean $i$f$fromJsonObject = false;
      String json$iv = StringsKt.trim((CharSequence)json).toString();
      $i$f$fromJsonObject = false;
      boolean var7 = false;

      Object var8;
      boolean $i$f$fromJsonObject;
      boolean var11;
      Object var10000;
      boolean $i$f$genericType;
      try {
         Companion var43 = Result.Companion;
         $i$f$fromJsonObject = false;
         $i$f$genericType = false;
         Type var50 = (new SourceAnalyzer$jsonToBookSource-IoAF18A$$inlined$fromJsonObject$1()).getType();
         Intrinsics.checkNotNullExpressionValue(var50, "object : TypeToken<T>() {}.type");
         var10000 = $this$fromJsonObject$iv.fromJson(json$iv, var50);
         if (!(var10000 instanceof SourceAnalyzer.BookSourceAny)) {
            var10000 = null;
         }

         SourceAnalyzer.BookSourceAny var45 = (SourceAnalyzer.BookSourceAny)var10000;
         $i$f$genericType = false;
         var8 = Result.constructor-impl(var45);
      } catch (Throwable var34) {
         Companion var10 = Result.Companion;
         var11 = false;
         var8 = Result.constructor-impl(ResultKt.createFailure(var34));
      }

      boolean var37 = false;
      $i$f$fromJsonObject = false;
      Throwable var70 = Result.exceptionOrNull-impl(var8);
      boolean var12;
      Unit var71;
      if (var70 != null) {
         Throwable var40 = var70;
         var7 = false;
         var8 = false;
         $i$f$genericType = false;
         var12 = false;
         DebugLog.DefaultImpls.log$default((DebugLog)Debug.INSTANCE, "转化书源出错", var40.getLocalizedMessage(), false, 4, (Object)null);
         var71 = Unit.INSTANCE;
      }

      var37 = false;
      SourceAnalyzer.BookSourceAny sourceAny = (SourceAnalyzer.BookSourceAny)(Result.isFailure-impl(var8) ? null : var8);
      boolean var36 = false;

      Object var38;
      boolean var46;
      try {
         Companion var39 = Result.Companion;
         $i$f$fromJsonObject = false;
         boolean $i$f$genericType;
         if ((sourceAny == null ? null : sourceAny.getRuleToc()) != null) {
            source.setBookSourceUrl(sourceAny.getBookSourceUrl());
            source.setBookSourceName(sourceAny.getBookSourceName());
            source.setBookSourceGroup(sourceAny.getBookSourceGroup());
            source.setBookSourceType(sourceAny.getBookSourceType());
            source.setBookUrlPattern(sourceAny.getBookUrlPattern());
            source.setCustomOrder(sourceAny.getCustomOrder());
            source.setEnabled(sourceAny.getEnabled());
            source.setEnabledExplore(sourceAny.getEnabledExplore());
            source.setEnabledCookieJar(sourceAny.getEnabledCookieJar());
            source.setConcurrentRate(sourceAny.getConcurrentRate());
            source.setHeader(sourceAny.getHeader());
            Object var42 = sourceAny.getLoginUrl();
            String var75;
            if (var42 == null) {
               var75 = null;
            } else if (var42 instanceof String) {
               var75 = String.valueOf(sourceAny.getLoginUrl());
            } else {
               DocumentContext var48 = JsonPath.parse(sourceAny.getLoginUrl());
               Intrinsics.checkNotNullExpressionValue(var48, "parse(sourceAny.loginUrl)");
               var75 = JsonExtensionsKt.readString((ReadContext)var48, "url");
            }

            source.setLoginUrl(var75);
            source.setLoginCheckJs(sourceAny.getLoginCheckJs());
            source.setBookSourceComment(sourceAny.getBookSourceComment());
            source.setLastUpdateTime(sourceAny.getLastUpdateTime());
            source.setRespondTime(sourceAny.getRespondTime());
            source.setWeight(sourceAny.getWeight());
            source.setExploreUrl(sourceAny.getExploreUrl());
            Gson $this$fromJsonObject$iv;
            String json$iv;
            Object var51;
            Companion var53;
            boolean var58;
            ExploreRule var59;
            Type var60;
            Companion var63;
            BookSource var74;
            ExploreRule var76;
            if (sourceAny.getRuleExplore() instanceof String) {
               $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
               json$iv = String.valueOf(sourceAny.getRuleExplore());
               $i$f$fromJsonObject = false;
               $i$f$genericType = false;

               try {
                  var53 = Result.Companion;
                  var12 = false;
                  $i$f$genericType = false;
                  var60 = (new SourceAnalyzer$jsonToBookSource_IoAF18A$lambda-10$$inlined$fromJsonObject$1()).getType();
                  Intrinsics.checkNotNullExpressionValue(var60, "object : TypeToken<T>() {}.type");
                  var10000 = $this$fromJsonObject$iv.fromJson(json$iv, var60);
                  if (!(var10000 instanceof ExploreRule)) {
                     var10000 = null;
                  }

                  var59 = (ExploreRule)var10000;
                  $i$f$genericType = false;
                  var51 = Result.constructor-impl(var59);
               } catch (Throwable var33) {
                  var63 = Result.Companion;
                  var58 = false;
                  var51 = Result.constructor-impl(ResultKt.createFailure(var33));
               }

               var74 = source;
               var46 = false;
               var76 = (ExploreRule)(Result.isFailure-impl(var51) ? null : var51);
            } else {
               $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
               json$iv = GsonExtensionsKt.getGSON().toJson(sourceAny.getRuleExplore());
               $i$f$fromJsonObject = false;
               $i$f$genericType = false;

               try {
                  var53 = Result.Companion;
                  var12 = false;
                  $i$f$genericType = false;
                  var60 = (new SourceAnalyzer$jsonToBookSource_IoAF18A$lambda-10$$inlined$fromJsonObject$2()).getType();
                  Intrinsics.checkNotNullExpressionValue(var60, "object : TypeToken<T>() {}.type");
                  var10000 = $this$fromJsonObject$iv.fromJson(json$iv, var60);
                  if (!(var10000 instanceof ExploreRule)) {
                     var10000 = null;
                  }

                  var59 = (ExploreRule)var10000;
                  $i$f$genericType = false;
                  var51 = Result.constructor-impl(var59);
               } catch (Throwable var32) {
                  var63 = Result.Companion;
                  var58 = false;
                  var51 = Result.constructor-impl(ResultKt.createFailure(var32));
               }

               var74 = source;
               var46 = false;
               var76 = (ExploreRule)(Result.isFailure-impl(var51) ? null : var51);
            }

            var74.setRuleExplore(var76);
            source.setSearchUrl(sourceAny.getSearchUrl());
            SearchRule var62;
            SearchRule var77;
            if (sourceAny.getRuleSearch() instanceof String) {
               $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
               json$iv = String.valueOf(sourceAny.getRuleSearch());
               $i$f$fromJsonObject = false;
               $i$f$genericType = false;

               try {
                  var53 = Result.Companion;
                  var12 = false;
                  $i$f$genericType = false;
                  var60 = (new SourceAnalyzer$jsonToBookSource_IoAF18A$lambda-10$$inlined$fromJsonObject$3()).getType();
                  Intrinsics.checkNotNullExpressionValue(var60, "object : TypeToken<T>() {}.type");
                  var10000 = $this$fromJsonObject$iv.fromJson(json$iv, var60);
                  if (!(var10000 instanceof SearchRule)) {
                     var10000 = null;
                  }

                  var62 = (SearchRule)var10000;
                  $i$f$genericType = false;
                  var51 = Result.constructor-impl(var62);
               } catch (Throwable var31) {
                  var63 = Result.Companion;
                  var58 = false;
                  var51 = Result.constructor-impl(ResultKt.createFailure(var31));
               }

               var74 = source;
               var46 = false;
               var77 = (SearchRule)(Result.isFailure-impl(var51) ? null : var51);
            } else {
               $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
               json$iv = GsonExtensionsKt.getGSON().toJson(sourceAny.getRuleSearch());
               $i$f$fromJsonObject = false;
               $i$f$genericType = false;

               try {
                  var53 = Result.Companion;
                  var12 = false;
                  $i$f$genericType = false;
                  var60 = (new SourceAnalyzer$jsonToBookSource_IoAF18A$lambda-10$$inlined$fromJsonObject$4()).getType();
                  Intrinsics.checkNotNullExpressionValue(var60, "object : TypeToken<T>() {}.type");
                  var10000 = $this$fromJsonObject$iv.fromJson(json$iv, var60);
                  if (!(var10000 instanceof SearchRule)) {
                     var10000 = null;
                  }

                  var62 = (SearchRule)var10000;
                  $i$f$genericType = false;
                  var51 = Result.constructor-impl(var62);
               } catch (Throwable var30) {
                  var63 = Result.Companion;
                  var58 = false;
                  var51 = Result.constructor-impl(ResultKt.createFailure(var30));
               }

               var74 = source;
               var46 = false;
               var77 = (SearchRule)(Result.isFailure-impl(var51) ? null : var51);
            }

            var74.setRuleSearch(var77);
            BookInfoRule var65;
            BookInfoRule var78;
            if (sourceAny.getRuleBookInfo() instanceof String) {
               $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
               json$iv = String.valueOf(sourceAny.getRuleBookInfo());
               $i$f$fromJsonObject = false;
               $i$f$genericType = false;

               try {
                  var53 = Result.Companion;
                  var12 = false;
                  $i$f$genericType = false;
                  var60 = (new SourceAnalyzer$jsonToBookSource_IoAF18A$lambda-10$$inlined$fromJsonObject$5()).getType();
                  Intrinsics.checkNotNullExpressionValue(var60, "object : TypeToken<T>() {}.type");
                  var10000 = $this$fromJsonObject$iv.fromJson(json$iv, var60);
                  if (!(var10000 instanceof BookInfoRule)) {
                     var10000 = null;
                  }

                  var65 = (BookInfoRule)var10000;
                  $i$f$genericType = false;
                  var51 = Result.constructor-impl(var65);
               } catch (Throwable var29) {
                  var63 = Result.Companion;
                  var58 = false;
                  var51 = Result.constructor-impl(ResultKt.createFailure(var29));
               }

               var74 = source;
               var46 = false;
               var78 = (BookInfoRule)(Result.isFailure-impl(var51) ? null : var51);
            } else {
               $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
               json$iv = GsonExtensionsKt.getGSON().toJson(sourceAny.getRuleBookInfo());
               $i$f$fromJsonObject = false;
               $i$f$genericType = false;

               try {
                  var53 = Result.Companion;
                  var12 = false;
                  $i$f$genericType = false;
                  var60 = (new SourceAnalyzer$jsonToBookSource_IoAF18A$lambda-10$$inlined$fromJsonObject$6()).getType();
                  Intrinsics.checkNotNullExpressionValue(var60, "object : TypeToken<T>() {}.type");
                  var10000 = $this$fromJsonObject$iv.fromJson(json$iv, var60);
                  if (!(var10000 instanceof BookInfoRule)) {
                     var10000 = null;
                  }

                  var65 = (BookInfoRule)var10000;
                  $i$f$genericType = false;
                  var51 = Result.constructor-impl(var65);
               } catch (Throwable var28) {
                  var63 = Result.Companion;
                  var58 = false;
                  var51 = Result.constructor-impl(ResultKt.createFailure(var28));
               }

               var74 = source;
               var46 = false;
               var78 = (BookInfoRule)(Result.isFailure-impl(var51) ? null : var51);
            }

            var74.setRuleBookInfo(var78);
            TocRule var68;
            TocRule var79;
            if (sourceAny.getRuleToc() instanceof String) {
               $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
               json$iv = String.valueOf(sourceAny.getRuleToc());
               $i$f$fromJsonObject = false;
               $i$f$genericType = false;

               try {
                  var53 = Result.Companion;
                  var12 = false;
                  $i$f$genericType = false;
                  var60 = (new SourceAnalyzer$jsonToBookSource_IoAF18A$lambda-10$$inlined$fromJsonObject$7()).getType();
                  Intrinsics.checkNotNullExpressionValue(var60, "object : TypeToken<T>() {}.type");
                  var10000 = $this$fromJsonObject$iv.fromJson(json$iv, var60);
                  if (!(var10000 instanceof TocRule)) {
                     var10000 = null;
                  }

                  var68 = (TocRule)var10000;
                  $i$f$genericType = false;
                  var51 = Result.constructor-impl(var68);
               } catch (Throwable var27) {
                  var63 = Result.Companion;
                  var58 = false;
                  var51 = Result.constructor-impl(ResultKt.createFailure(var27));
               }

               var74 = source;
               var46 = false;
               var79 = (TocRule)(Result.isFailure-impl(var51) ? null : var51);
            } else {
               $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
               json$iv = GsonExtensionsKt.getGSON().toJson(sourceAny.getRuleToc());
               $i$f$fromJsonObject = false;
               $i$f$genericType = false;

               try {
                  var53 = Result.Companion;
                  var12 = false;
                  $i$f$genericType = false;
                  var60 = (new SourceAnalyzer$jsonToBookSource_IoAF18A$lambda-10$$inlined$fromJsonObject$8()).getType();
                  Intrinsics.checkNotNullExpressionValue(var60, "object : TypeToken<T>() {}.type");
                  var10000 = $this$fromJsonObject$iv.fromJson(json$iv, var60);
                  if (!(var10000 instanceof TocRule)) {
                     var10000 = null;
                  }

                  var68 = (TocRule)var10000;
                  $i$f$genericType = false;
                  var51 = Result.constructor-impl(var68);
               } catch (Throwable var26) {
                  var63 = Result.Companion;
                  var58 = false;
                  var51 = Result.constructor-impl(ResultKt.createFailure(var26));
               }

               var74 = source;
               var46 = false;
               var79 = (TocRule)(Result.isFailure-impl(var51) ? null : var51);
            }

            var74.setRuleToc(var79);
            ContentRule var69;
            ContentRule var80;
            if (sourceAny.getRuleContent() instanceof String) {
               $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
               json$iv = String.valueOf(sourceAny.getRuleContent());
               $i$f$fromJsonObject = false;
               $i$f$genericType = false;

               try {
                  var53 = Result.Companion;
                  var12 = false;
                  $i$f$genericType = false;
                  var60 = (new SourceAnalyzer$jsonToBookSource_IoAF18A$lambda-10$$inlined$fromJsonObject$9()).getType();
                  Intrinsics.checkNotNullExpressionValue(var60, "object : TypeToken<T>() {}.type");
                  var10000 = $this$fromJsonObject$iv.fromJson(json$iv, var60);
                  if (!(var10000 instanceof ContentRule)) {
                     var10000 = null;
                  }

                  var69 = (ContentRule)var10000;
                  $i$f$genericType = false;
                  var51 = Result.constructor-impl(var69);
               } catch (Throwable var25) {
                  var63 = Result.Companion;
                  var58 = false;
                  var51 = Result.constructor-impl(ResultKt.createFailure(var25));
               }

               var74 = source;
               var46 = false;
               var80 = (ContentRule)(Result.isFailure-impl(var51) ? null : var51);
            } else {
               $this$fromJsonObject$iv = GsonExtensionsKt.getGSON();
               json$iv = GsonExtensionsKt.getGSON().toJson(sourceAny.getRuleContent());
               $i$f$fromJsonObject = false;
               $i$f$genericType = false;

               try {
                  var53 = Result.Companion;
                  var12 = false;
                  $i$f$genericType = false;
                  var60 = (new SourceAnalyzer$jsonToBookSource_IoAF18A$lambda-10$$inlined$fromJsonObject$10()).getType();
                  Intrinsics.checkNotNullExpressionValue(var60, "object : TypeToken<T>() {}.type");
                  var10000 = $this$fromJsonObject$iv.fromJson(json$iv, var60);
                  if (!(var10000 instanceof ContentRule)) {
                     var10000 = null;
                  }

                  var69 = (ContentRule)var10000;
                  $i$f$genericType = false;
                  var51 = Result.constructor-impl(var69);
               } catch (Throwable var24) {
                  var63 = Result.Companion;
                  var58 = false;
                  var51 = Result.constructor-impl(ResultKt.createFailure(var24));
               }

               var74 = source;
               var46 = false;
               var80 = (ContentRule)(Result.isFailure-impl(var51) ? null : var51);
            }

            var74.setRuleContent(var80);
         } else {
            var46 = false;
            $i$f$fromJsonObject = false;
            var11 = false;
            ParseContext var72 = JsonExtensionsKt.getJsonPath();
            $i$f$genericType = false;
            DocumentContext jsonItem = var72.parse(StringsKt.trim((CharSequence)json).toString());
            Intrinsics.checkNotNullExpressionValue(jsonItem, "jsonItem");
            String sourceType = JsonExtensionsKt.readString((ReadContext)jsonItem, "bookSourceUrl");
            if (sourceType == null) {
               throw new NoStackTraceException("格式不对");
            }

            byte var10001;
            label337: {
               source.setBookSourceUrl(sourceType);
               sourceType = JsonExtensionsKt.readString((ReadContext)jsonItem, "bookSourceName");
               source.setBookSourceName(sourceType == null ? "" : sourceType);
               source.setBookSourceGroup(JsonExtensionsKt.readString((ReadContext)jsonItem, "bookSourceGroup"));
               sourceType = JsonExtensionsKt.readString((ReadContext)jsonItem, "bookSourceComment");
               source.setBookSourceComment(sourceType == null ? "" : sourceType);
               source.setBookUrlPattern(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleBookUrlPattern"));
               Integer var54 = JsonExtensionsKt.readInt((ReadContext)jsonItem, "serialNumber");
               source.setCustomOrder(var54 == null ? 0 : var54);
               source.setHeader(INSTANCE.uaToHeader(JsonExtensionsKt.readString((ReadContext)jsonItem, "httpUserAgent")));
               source.setSearchUrl(INSTANCE.toNewUrl(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleSearchUrl")));
               source.setExploreUrl(INSTANCE.toNewUrls(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleFindUrl")));
               sourceType = JsonExtensionsKt.readString((ReadContext)jsonItem, "bookSourceType");
               if (sourceType != null) {
                  switch(sourceType.hashCode()) {
                  case 49:
                     if (sourceType.equals("1")) {
                        var10001 = 1;
                        break label337;
                     }
                     break;
                  case 50:
                     if (sourceType.equals("2")) {
                        var10001 = 2;
                        break label337;
                     }
                     break;
                  case 51:
                     if (sourceType.equals("3")) {
                        var10001 = 3;
                        break label337;
                     }
                     break;
                  case 2157948:
                     if (sourceType.equals("FILE")) {
                        var10001 = 3;
                        break label337;
                     }
                     break;
                  case 3143036:
                     if (sourceType.equals("file")) {
                        var10001 = 3;
                        break label337;
                     }
                     break;
                  case 62628790:
                     if (sourceType.equals("AUDIO")) {
                        var10001 = 1;
                        break label337;
                     }
                     break;
                  case 69775675:
                     if (sourceType.equals("IMAGE")) {
                        var10001 = 2;
                        break label337;
                     }
                     break;
                  case 93166550:
                     if (sourceType.equals("audio")) {
                        var10001 = 1;
                        break label337;
                     }
                     break;
                  case 100313435:
                     if (sourceType.equals("image")) {
                        var10001 = 2;
                        break label337;
                     }
                  }
               }

               var10001 = 0;
            }

            source.setBookSourceType(var10001);
            Boolean var55 = JsonExtensionsKt.readBool((ReadContext)jsonItem, "enable");
            source.setEnabled(var55 == null ? true : var55);
            CharSequence var56 = (CharSequence)source.getExploreUrl();
            boolean var15 = false;
            boolean var16 = false;
            if (var56 == null || StringsKt.isBlank(var56)) {
               source.setEnabledExplore(false);
            }

            String content = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleSearchList"));
            String var61 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleSearchName"));
            String var64 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleSearchAuthor"));
            String var17 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleSearchIntroduce"));
            String var18 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleSearchKind"));
            String var19 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleSearchNoteUrl"));
            String var20 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleSearchCoverUrl"));
            String var21 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleSearchLastChapter"));
            source.setRuleSearch(new SearchRule(content, var61, var64, var17, var18, var21, (String)null, var19, var20, (String)null, 576, (DefaultConstructorMarker)null));
            content = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleFindList"));
            var61 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleFindName"));
            var64 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleFindAuthor"));
            var17 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleFindIntroduce"));
            var18 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleFindKind"));
            var19 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleFindNoteUrl"));
            var20 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleFindCoverUrl"));
            var21 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleFindLastChapter"));
            source.setRuleExplore(new ExploreRule(content, var61, var64, var17, var18, var21, (String)null, var19, var20, (String)null, 576, (DefaultConstructorMarker)null));
            content = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleBookInfoInit"));
            var61 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleBookName"));
            var64 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleBookAuthor"));
            var17 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleIntroduce"));
            var18 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleBookKind"));
            var19 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleCoverUrl"));
            var20 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleBookLastChapter"));
            var21 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleChapterUrl"));
            source.setRuleBookInfo(new BookInfoRule(content, var61, var64, var17, var18, var20, (String)null, var19, var21, (String)null, (String)null, 1600, (DefaultConstructorMarker)null));
            source.setRuleToc(new TocRule((String)null, INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleChapterList")), INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleChapterName")), INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleContentUrl")), (String)null, (String)null, (String)null, INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleChapterUrlNext")), 113, (DefaultConstructorMarker)null));
            var61 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleBookContent"));
            content = var61 == null ? "" : var61;
            if (StringsKt.startsWith$default(content, "$", false, 2, (Object)null) && !StringsKt.startsWith$default(content, "$.", false, 2, (Object)null)) {
               byte var66 = 1;
               boolean var67 = false;
               String var73 = content.substring(var66);
               Intrinsics.checkNotNullExpressionValue(var73, "(this as java.lang.String).substring(startIndex)");
               content = var73;
            }

            var61 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleBookContentReplace"));
            var64 = INSTANCE.toNewRule(JsonExtensionsKt.readString((ReadContext)jsonItem, "ruleContentUrlNext"));
            source.setRuleContent(new ContentRule(content, var64, (String)null, (String)null, var61, (String)null, 44, (DefaultConstructorMarker)null));
            var71 = Unit.INSTANCE;
         }

         var7 = false;
         var38 = Result.constructor-impl(source);
      } catch (Throwable var35) {
         Companion var41 = Result.Companion;
         var46 = false;
         var38 = Result.constructor-impl(ResultKt.createFailure(var35));
      }

      return var38;
   }

   private final String toNewRule(String oldRule) {
      CharSequence var2 = (CharSequence)oldRule;
      boolean reverse = false;
      boolean allinone = false;
      if (var2 == null || StringsKt.isBlank(var2)) {
         return null;
      } else {
         String newRule = oldRule;
         reverse = false;
         allinone = false;
         byte var6;
         boolean var7;
         String var10000;
         if (StringsKt.startsWith$default(oldRule, "-", false, 2, (Object)null)) {
            reverse = true;
            var6 = 1;
            var7 = false;
            if (oldRule == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            var10000 = oldRule.substring(var6);
            Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
            newRule = var10000;
         }

         if (StringsKt.startsWith$default(newRule, "+", false, 2, (Object)null)) {
            allinone = true;
            var6 = 1;
            var7 = false;
            if (newRule == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            var10000 = newRule.substring(var6);
            Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
            newRule = var10000;
         }

         if (!StringsKt.startsWith(newRule, "@CSS:", true) && !StringsKt.startsWith(newRule, "@XPath:", true) && !StringsKt.startsWith$default(newRule, "//", false, 2, (Object)null) && !StringsKt.startsWith$default(newRule, "##", false, 2, (Object)null) && !StringsKt.startsWith$default(newRule, ":", false, 2, (Object)null) && !StringsKt.contains((CharSequence)newRule, (CharSequence)"@js:", true) && !StringsKt.contains((CharSequence)newRule, (CharSequence)"<js>", true)) {
            if (StringsKt.contains$default((CharSequence)newRule, (CharSequence)"#", false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)newRule, (CharSequence)"##", false, 2, (Object)null)) {
               newRule = StringsKt.replace$default(oldRule, "#", "##", false, 4, (Object)null);
            }

            if (StringsKt.contains$default((CharSequence)newRule, (CharSequence)"|", false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)newRule, (CharSequence)"||", false, 2, (Object)null)) {
               if (StringsKt.contains$default((CharSequence)newRule, (CharSequence)"##", false, 2, (Object)null)) {
                  CharSequence var13 = (CharSequence)newRule;
                  String[] var10 = new String[]{"##"};
                  List list = StringsKt.split$default(var13, var10, false, 0, 6, (Object)null);
                  if (StringsKt.contains$default((CharSequence)list.get(0), (CharSequence)"|", false, 2, (Object)null)) {
                     newRule = StringsKt.replace$default((String)list.get(0), "|", "||", false, 4, (Object)null);
                     int var11 = 1;
                     int var12 = list.size();
                     if (var11 < var12) {
                        do {
                           int i = var11++;
                           newRule = newRule + "##" + (String)list.get(i);
                        } while(var11 < var12);
                     }
                  }
               } else {
                  newRule = StringsKt.replace$default(newRule, "|", "||", false, 4, (Object)null);
               }
            }

            if (StringsKt.contains$default((CharSequence)newRule, (CharSequence)"&", false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)newRule, (CharSequence)"&&", false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)newRule, (CharSequence)"http", false, 2, (Object)null) && !StringsKt.startsWith$default(newRule, "/", false, 2, (Object)null)) {
               newRule = StringsKt.replace$default(newRule, "&", "&&", false, 4, (Object)null);
            }
         }

         if (allinone) {
            newRule = Intrinsics.stringPlus("+", newRule);
         }

         if (reverse) {
            newRule = Intrinsics.stringPlus("-", newRule);
         }

         return newRule;
      }
   }

   private final String toNewUrls(String oldUrls) {
      CharSequence var2 = (CharSequence)oldUrls;
      boolean var3 = false;
      boolean $i$f$map = false;
      if (var2 == null || StringsKt.isBlank(var2)) {
         return null;
      } else if (!StringsKt.startsWith$default(oldUrls, "@js:", false, 2, (Object)null) && !StringsKt.startsWith$default(oldUrls, "<js>", false, 2, (Object)null)) {
         if (!StringsKt.contains$default((CharSequence)oldUrls, (CharSequence)"\n", false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)oldUrls, (CharSequence)"&&", false, 2, (Object)null)) {
            return this.toNewUrl(oldUrls);
         } else {
            CharSequence var20 = (CharSequence)oldUrls;
            String var22 = "(&&|\r?\n)+";
            boolean var5 = false;
            Regex var23 = new Regex(var22);
            byte var24 = 0;
            boolean var6 = false;
            List urls = var23.split(var20, var24);
            Iterable $this$map$iv = (Iterable)urls;
            $i$f$map = false;
            Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
            int $i$f$mapTo = false;
            Iterator var8 = $this$map$iv.iterator();

            while(var8.hasNext()) {
               Object item$iv$iv = var8.next();
               String it = (String)item$iv$iv;
               int var11 = false;
               String var12 = INSTANCE.toNewUrl(it);
               String var10000;
               if (var12 == null) {
                  var10000 = null;
               } else {
                  CharSequence var13 = (CharSequence)var12;
                  String var14 = "\n\\s*";
                  boolean var15 = false;
                  Regex var26 = new Regex(var14);
                  String var27 = "";
                  boolean var16 = false;
                  var10000 = var26.replace(var13, var27);
               }

               String var18 = var10000;
               destination$iv$iv.add(var18);
            }

            return CollectionsKt.joinToString$default((Iterable)((List)destination$iv$iv), (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
         }
      } else {
         return oldUrls;
      }
   }

   private final String toNewUrl(String oldUrl) {
      CharSequence var2 = (CharSequence)oldUrl;
      boolean var3 = false;
      boolean var4 = false;
      if (var2 == null || StringsKt.isBlank(var2)) {
         return null;
      } else {
         String url = oldUrl;
         if (StringsKt.startsWith(oldUrl, "<js>", true)) {
            url = StringsKt.replace$default(StringsKt.replace$default(oldUrl, "=searchKey", "={{key}}", false, 4, (Object)null), "=searchPage", "={{page}}", false, 4, (Object)null);
            return url;
         } else {
            HashMap map = new HashMap();
            Matcher mather = headerPattern.matcher((CharSequence)oldUrl);
            Map var6;
            String var7;
            String var8;
            boolean var10;
            boolean var23;
            if (mather.find()) {
               String header = mather.group();
               Intrinsics.checkNotNullExpressionValue(header, "header");
               url = StringsKt.replace$default(oldUrl, header, "", false, 4, (Object)null);
               var6 = (Map)map;
               var7 = "headers";
               byte var9 = 8;
               var10 = false;
               String var10000 = header.substring(var9);
               Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
               var8 = var10000;
               var23 = false;
               var6.put(var7, var8);
            }

            CharSequence var31 = (CharSequence)url;
            String[] var15 = new String[]{"|"};
            List urlList = StringsKt.split$default(var31, var15, false, 0, 6, (Object)null);
            url = (String)urlList.get(0);
            if (urlList.size() > 1) {
               var6 = (Map)map;
               var7 = "charset";
               var31 = (CharSequence)urlList.get(1);
               String[] var19 = new String[]{"="};
               Object var20 = StringsKt.split$default(var31, var19, false, 0, 6, (Object)null).get(1);
               var23 = false;
               var6.put(var7, var20);
            }

            mather = jsPattern.matcher((CharSequence)url);
            boolean var17 = false;

            ArrayList jsList;
            for(jsList = new ArrayList(); mather.find(); url = StringsKt.replace$default(url, (String)CollectionsKt.last((List)jsList), Intrinsics.stringPlus("$", jsList.size() - 1), false, 4, (Object)null)) {
               jsList.add(mather.group());
            }

            url = StringsKt.replace$default(StringsKt.replace$default(url, "{", "<", false, 4, (Object)null), "}", ">", false, 4, (Object)null);
            url = StringsKt.replace$default(url, "searchKey", "{{key}}", false, 4, (Object)null);
            CharSequence var18 = (CharSequence)url;
            var8 = "<searchPage([-+]1)>";
            var23 = false;
            Regex var24 = new Regex(var8);
            String var26 = "{{page$1}}";
            var10 = false;
            var18 = (CharSequence)var24.replace(var18, var26);
            var8 = "searchPage([-+]1)";
            var23 = false;
            var24 = new Regex(var8);
            var26 = "{{page$1}}";
            var10 = false;
            url = StringsKt.replace$default(var24.replace(var18, var26), "searchPage", "{{page}}", false, 4, (Object)null);
            Iterator var21 = jsList.iterator();

            String item;
            int index;
            for(int var27 = 0; var21.hasNext(); url = StringsKt.replace$default(url, Intrinsics.stringPlus("$", index), StringsKt.replace$default(StringsKt.replace$default(item, "searchKey", "key", false, 4, (Object)null), "searchPage", "page", false, 4, (Object)null), false, 4, (Object)null)) {
               index = var27++;
               item = (String)var21.next();
            }

            var31 = (CharSequence)url;
            String[] var22 = new String[]{"@"};
            urlList = StringsKt.split$default(var31, var22, false, 0, 6, (Object)null);
            url = (String)urlList.get(0);
            if (urlList.size() > 1) {
               Map var25 = (Map)map;
               var8 = "method";
               var26 = "POST";
               var10 = false;
               var25.put(var8, var26);
               var25 = (Map)map;
               var8 = "body";
               Object var30 = urlList.get(1);
               var10 = false;
               var25.put(var8, var30);
            }

            if (map.size() > 0) {
               url = url + ',' + GsonExtensionsKt.getGSON().toJson(map);
            }

            return url;
         }
      }
   }

   private final String uaToHeader(String ua) {
      CharSequence var2 = (CharSequence)ua;
      boolean var3 = false;
      boolean var4 = false;
      if (var2 == null || var2.length() == 0) {
         return null;
      } else {
         Map map = MapsKt.mapOf(new Pair("User-Agent", ua));
         return GsonExtensionsKt.getGSON().toJson(map);
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\be\b\u0086\b\u0018\u00002\u00020\u0001B\u009d\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u001fJ\t\u0010\\\u001a\u00020\u0003HÆ\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010c\u001a\u00020\u0015HÆ\u0003J\t\u0010d\u001a\u00020\u0015HÆ\u0003J\t\u0010e\u001a\u00020\u0007HÆ\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010i\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\t\u0010n\u001a\u00020\u0003HÆ\u0003J\t\u0010o\u001a\u00020\u0007HÆ\u0003J\u000b\u0010p\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010q\u001a\u00020\u0007HÆ\u0003J\t\u0010r\u001a\u00020\u000bHÆ\u0003J\t\u0010s\u001a\u00020\u000bHÆ\u0003J\t\u0010t\u001a\u00020\u000bHÆ\u0003J¡\u0002\u0010u\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00072\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÆ\u0001J\u0013\u0010v\u001a\u00020\u000b2\b\u0010w\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010x\u001a\u00020\u0007HÖ\u0001J\t\u0010y\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010!\"\u0004\b%\u0010#R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010!\"\u0004\b'\u0010#R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010!\"\u0004\b-\u0010#R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010!\"\u0004\b/\u0010#R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010!\"\u0004\b1\u0010#R\u001a\u0010\t\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010)\"\u0004\b3\u0010+R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001a\u0010\r\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00105\"\u0004\b9\u00107R\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u00105\"\u0004\b;\u00107R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010!\"\u0004\b=\u0010#R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010!\"\u0004\b?\u0010#R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010!\"\u0004\bE\u0010#R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010G\"\u0004\bK\u0010IR\u001a\u0010\u0016\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010A\"\u0004\bM\u0010CR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010G\"\u0004\bO\u0010IR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010G\"\u0004\bQ\u0010IR\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010G\"\u0004\bS\u0010IR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010G\"\u0004\bU\u0010IR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010G\"\u0004\bW\u0010IR\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010!\"\u0004\bY\u0010#R\u001a\u0010\u0017\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010)\"\u0004\b[\u0010+¨\u0006z"},
      d2 = {"Lio/legado/app/help/SourceAnalyzer$BookSourceAny;", "", "bookSourceName", "", "bookSourceGroup", "bookSourceUrl", "bookSourceType", "", "bookUrlPattern", "customOrder", "enabled", "", "enabledExplore", "enabledCookieJar", "concurrentRate", "header", "loginUrl", "loginUi", "loginCheckJs", "bookSourceComment", "lastUpdateTime", "", "respondTime", "weight", "exploreUrl", "ruleExplore", "searchUrl", "ruleSearch", "ruleBookInfo", "ruleToc", "ruleContent", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;IZZZLjava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;JJILjava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "getBookSourceComment", "()Ljava/lang/String;", "setBookSourceComment", "(Ljava/lang/String;)V", "getBookSourceGroup", "setBookSourceGroup", "getBookSourceName", "setBookSourceName", "getBookSourceType", "()I", "setBookSourceType", "(I)V", "getBookSourceUrl", "setBookSourceUrl", "getBookUrlPattern", "setBookUrlPattern", "getConcurrentRate", "setConcurrentRate", "getCustomOrder", "setCustomOrder", "getEnabled", "()Z", "setEnabled", "(Z)V", "getEnabledCookieJar", "setEnabledCookieJar", "getEnabledExplore", "setEnabledExplore", "getExploreUrl", "setExploreUrl", "getHeader", "setHeader", "getLastUpdateTime", "()J", "setLastUpdateTime", "(J)V", "getLoginCheckJs", "setLoginCheckJs", "getLoginUi", "()Ljava/lang/Object;", "setLoginUi", "(Ljava/lang/Object;)V", "getLoginUrl", "setLoginUrl", "getRespondTime", "setRespondTime", "getRuleBookInfo", "setRuleBookInfo", "getRuleContent", "setRuleContent", "getRuleExplore", "setRuleExplore", "getRuleSearch", "setRuleSearch", "getRuleToc", "setRuleToc", "getSearchUrl", "setSearchUrl", "getWeight", "setWeight", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "reader-pro"}
   )
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

      public BookSourceAny(@NotNull String bookSourceName, @Nullable String bookSourceGroup, @NotNull String bookSourceUrl, int bookSourceType, @Nullable String bookUrlPattern, int customOrder, boolean enabled, boolean enabledExplore, boolean enabledCookieJar, @Nullable String concurrentRate, @Nullable String header, @Nullable Object loginUrl, @Nullable Object loginUi, @Nullable String loginCheckJs, @Nullable String bookSourceComment, long lastUpdateTime, long respondTime, int weight, @Nullable String exploreUrl, @Nullable Object ruleExplore, @Nullable String searchUrl, @Nullable Object ruleSearch, @Nullable Object ruleBookInfo, @Nullable Object ruleToc, @Nullable Object ruleContent) {
         Intrinsics.checkNotNullParameter(bookSourceName, "bookSourceName");
         Intrinsics.checkNotNullParameter(bookSourceUrl, "bookSourceUrl");
         super();
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

      // $FF: synthetic method
      public BookSourceAny(String var1, String var2, String var3, int var4, String var5, int var6, boolean var7, boolean var8, boolean var9, String var10, String var11, Object var12, Object var13, String var14, String var15, long var16, long var18, int var20, String var21, Object var22, String var23, Object var24, Object var25, Object var26, Object var27, int var28, DefaultConstructorMarker var29) {
         if ((var28 & 1) != 0) {
            var1 = "";
         }

         if ((var28 & 2) != 0) {
            var2 = null;
         }

         if ((var28 & 4) != 0) {
            var3 = "";
         }

         if ((var28 & 8) != 0) {
            var4 = 0;
         }

         if ((var28 & 16) != 0) {
            var5 = null;
         }

         if ((var28 & 32) != 0) {
            var6 = 0;
         }

         if ((var28 & 64) != 0) {
            var7 = true;
         }

         if ((var28 & 128) != 0) {
            var8 = true;
         }

         if ((var28 & 256) != 0) {
            var9 = false;
         }

         if ((var28 & 512) != 0) {
            var10 = null;
         }

         if ((var28 & 1024) != 0) {
            var11 = null;
         }

         if ((var28 & 2048) != 0) {
            var12 = null;
         }

         if ((var28 & 4096) != 0) {
            var13 = null;
         }

         if ((var28 & 8192) != 0) {
            var14 = null;
         }

         if ((var28 & 16384) != 0) {
            var15 = "";
         }

         if ((var28 & '耀') != 0) {
            var16 = 0L;
         }

         if ((var28 & 65536) != 0) {
            var18 = 180000L;
         }

         if ((var28 & 131072) != 0) {
            var20 = 0;
         }

         if ((var28 & 262144) != 0) {
            var21 = null;
         }

         if ((var28 & 524288) != 0) {
            var22 = null;
         }

         if ((var28 & 1048576) != 0) {
            var23 = null;
         }

         if ((var28 & 2097152) != 0) {
            var24 = null;
         }

         if ((var28 & 4194304) != 0) {
            var25 = null;
         }

         if ((var28 & 8388608) != 0) {
            var26 = null;
         }

         if ((var28 & 16777216) != 0) {
            var27 = null;
         }

         this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var18, var20, var21, var22, var23, var24, var25, var26, var27);
      }

      @NotNull
      public final String getBookSourceName() {
         return this.bookSourceName;
      }

      public final void setBookSourceName(@NotNull String <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.bookSourceName = var1;
      }

      @Nullable
      public final String getBookSourceGroup() {
         return this.bookSourceGroup;
      }

      public final void setBookSourceGroup(@Nullable String <set-?>) {
         this.bookSourceGroup = var1;
      }

      @NotNull
      public final String getBookSourceUrl() {
         return this.bookSourceUrl;
      }

      public final void setBookSourceUrl(@NotNull String <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.bookSourceUrl = var1;
      }

      public final int getBookSourceType() {
         return this.bookSourceType;
      }

      public final void setBookSourceType(int <set-?>) {
         this.bookSourceType = var1;
      }

      @Nullable
      public final String getBookUrlPattern() {
         return this.bookUrlPattern;
      }

      public final void setBookUrlPattern(@Nullable String <set-?>) {
         this.bookUrlPattern = var1;
      }

      public final int getCustomOrder() {
         return this.customOrder;
      }

      public final void setCustomOrder(int <set-?>) {
         this.customOrder = var1;
      }

      public final boolean getEnabled() {
         return this.enabled;
      }

      public final void setEnabled(boolean <set-?>) {
         this.enabled = var1;
      }

      public final boolean getEnabledExplore() {
         return this.enabledExplore;
      }

      public final void setEnabledExplore(boolean <set-?>) {
         this.enabledExplore = var1;
      }

      public final boolean getEnabledCookieJar() {
         return this.enabledCookieJar;
      }

      public final void setEnabledCookieJar(boolean <set-?>) {
         this.enabledCookieJar = var1;
      }

      @Nullable
      public final String getConcurrentRate() {
         return this.concurrentRate;
      }

      public final void setConcurrentRate(@Nullable String <set-?>) {
         this.concurrentRate = var1;
      }

      @Nullable
      public final String getHeader() {
         return this.header;
      }

      public final void setHeader(@Nullable String <set-?>) {
         this.header = var1;
      }

      @Nullable
      public final Object getLoginUrl() {
         return this.loginUrl;
      }

      public final void setLoginUrl(@Nullable Object <set-?>) {
         this.loginUrl = var1;
      }

      @Nullable
      public final Object getLoginUi() {
         return this.loginUi;
      }

      public final void setLoginUi(@Nullable Object <set-?>) {
         this.loginUi = var1;
      }

      @Nullable
      public final String getLoginCheckJs() {
         return this.loginCheckJs;
      }

      public final void setLoginCheckJs(@Nullable String <set-?>) {
         this.loginCheckJs = var1;
      }

      @Nullable
      public final String getBookSourceComment() {
         return this.bookSourceComment;
      }

      public final void setBookSourceComment(@Nullable String <set-?>) {
         this.bookSourceComment = var1;
      }

      public final long getLastUpdateTime() {
         return this.lastUpdateTime;
      }

      public final void setLastUpdateTime(long <set-?>) {
         this.lastUpdateTime = var1;
      }

      public final long getRespondTime() {
         return this.respondTime;
      }

      public final void setRespondTime(long <set-?>) {
         this.respondTime = var1;
      }

      public final int getWeight() {
         return this.weight;
      }

      public final void setWeight(int <set-?>) {
         this.weight = var1;
      }

      @Nullable
      public final String getExploreUrl() {
         return this.exploreUrl;
      }

      public final void setExploreUrl(@Nullable String <set-?>) {
         this.exploreUrl = var1;
      }

      @Nullable
      public final Object getRuleExplore() {
         return this.ruleExplore;
      }

      public final void setRuleExplore(@Nullable Object <set-?>) {
         this.ruleExplore = var1;
      }

      @Nullable
      public final String getSearchUrl() {
         return this.searchUrl;
      }

      public final void setSearchUrl(@Nullable String <set-?>) {
         this.searchUrl = var1;
      }

      @Nullable
      public final Object getRuleSearch() {
         return this.ruleSearch;
      }

      public final void setRuleSearch(@Nullable Object <set-?>) {
         this.ruleSearch = var1;
      }

      @Nullable
      public final Object getRuleBookInfo() {
         return this.ruleBookInfo;
      }

      public final void setRuleBookInfo(@Nullable Object <set-?>) {
         this.ruleBookInfo = var1;
      }

      @Nullable
      public final Object getRuleToc() {
         return this.ruleToc;
      }

      public final void setRuleToc(@Nullable Object <set-?>) {
         this.ruleToc = var1;
      }

      @Nullable
      public final Object getRuleContent() {
         return this.ruleContent;
      }

      public final void setRuleContent(@Nullable Object <set-?>) {
         this.ruleContent = var1;
      }

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
      public final SourceAnalyzer.BookSourceAny copy(@NotNull String bookSourceName, @Nullable String bookSourceGroup, @NotNull String bookSourceUrl, int bookSourceType, @Nullable String bookUrlPattern, int customOrder, boolean enabled, boolean enabledExplore, boolean enabledCookieJar, @Nullable String concurrentRate, @Nullable String header, @Nullable Object loginUrl, @Nullable Object loginUi, @Nullable String loginCheckJs, @Nullable String bookSourceComment, long lastUpdateTime, long respondTime, int weight, @Nullable String exploreUrl, @Nullable Object ruleExplore, @Nullable String searchUrl, @Nullable Object ruleSearch, @Nullable Object ruleBookInfo, @Nullable Object ruleToc, @Nullable Object ruleContent) {
         Intrinsics.checkNotNullParameter(bookSourceName, "bookSourceName");
         Intrinsics.checkNotNullParameter(bookSourceUrl, "bookSourceUrl");
         return new SourceAnalyzer.BookSourceAny(bookSourceName, bookSourceGroup, bookSourceUrl, bookSourceType, bookUrlPattern, customOrder, enabled, enabledExplore, enabledCookieJar, concurrentRate, header, loginUrl, loginUi, loginCheckJs, bookSourceComment, lastUpdateTime, respondTime, weight, exploreUrl, ruleExplore, searchUrl, ruleSearch, ruleBookInfo, ruleToc, ruleContent);
      }

      // $FF: synthetic method
      public static SourceAnalyzer.BookSourceAny copy$default(SourceAnalyzer.BookSourceAny var0, String var1, String var2, String var3, int var4, String var5, int var6, boolean var7, boolean var8, boolean var9, String var10, String var11, Object var12, Object var13, String var14, String var15, long var16, long var18, int var20, String var21, Object var22, String var23, Object var24, Object var25, Object var26, Object var27, int var28, Object var29) {
         if ((var28 & 1) != 0) {
            var1 = var0.bookSourceName;
         }

         if ((var28 & 2) != 0) {
            var2 = var0.bookSourceGroup;
         }

         if ((var28 & 4) != 0) {
            var3 = var0.bookSourceUrl;
         }

         if ((var28 & 8) != 0) {
            var4 = var0.bookSourceType;
         }

         if ((var28 & 16) != 0) {
            var5 = var0.bookUrlPattern;
         }

         if ((var28 & 32) != 0) {
            var6 = var0.customOrder;
         }

         if ((var28 & 64) != 0) {
            var7 = var0.enabled;
         }

         if ((var28 & 128) != 0) {
            var8 = var0.enabledExplore;
         }

         if ((var28 & 256) != 0) {
            var9 = var0.enabledCookieJar;
         }

         if ((var28 & 512) != 0) {
            var10 = var0.concurrentRate;
         }

         if ((var28 & 1024) != 0) {
            var11 = var0.header;
         }

         if ((var28 & 2048) != 0) {
            var12 = var0.loginUrl;
         }

         if ((var28 & 4096) != 0) {
            var13 = var0.loginUi;
         }

         if ((var28 & 8192) != 0) {
            var14 = var0.loginCheckJs;
         }

         if ((var28 & 16384) != 0) {
            var15 = var0.bookSourceComment;
         }

         if ((var28 & '耀') != 0) {
            var16 = var0.lastUpdateTime;
         }

         if ((var28 & 65536) != 0) {
            var18 = var0.respondTime;
         }

         if ((var28 & 131072) != 0) {
            var20 = var0.weight;
         }

         if ((var28 & 262144) != 0) {
            var21 = var0.exploreUrl;
         }

         if ((var28 & 524288) != 0) {
            var22 = var0.ruleExplore;
         }

         if ((var28 & 1048576) != 0) {
            var23 = var0.searchUrl;
         }

         if ((var28 & 2097152) != 0) {
            var24 = var0.ruleSearch;
         }

         if ((var28 & 4194304) != 0) {
            var25 = var0.ruleBookInfo;
         }

         if ((var28 & 8388608) != 0) {
            var26 = var0.ruleToc;
         }

         if ((var28 & 16777216) != 0) {
            var27 = var0.ruleContent;
         }

         return var0.copy(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var18, var20, var21, var22, var23, var24, var25, var26, var27);
      }

      @NotNull
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         var1.append("BookSourceAny(bookSourceName=").append(this.bookSourceName).append(", bookSourceGroup=").append(this.bookSourceGroup).append(", bookSourceUrl=").append(this.bookSourceUrl).append(", bookSourceType=").append(this.bookSourceType).append(", bookUrlPattern=").append(this.bookUrlPattern).append(", customOrder=").append(this.customOrder).append(", enabled=").append(this.enabled).append(", enabledExplore=").append(this.enabledExplore).append(", enabledCookieJar=").append(this.enabledCookieJar).append(", concurrentRate=").append(this.concurrentRate).append(", header=").append(this.header).append(", loginUrl=");
         var1.append(this.loginUrl).append(", loginUi=").append(this.loginUi).append(", loginCheckJs=").append(this.loginCheckJs).append(", bookSourceComment=").append(this.bookSourceComment).append(", lastUpdateTime=").append(this.lastUpdateTime).append(", respondTime=").append(this.respondTime).append(", weight=").append(this.weight).append(", exploreUrl=").append(this.exploreUrl).append(", ruleExplore=").append(this.ruleExplore).append(", searchUrl=").append(this.searchUrl).append(", ruleSearch=").append(this.ruleSearch).append(", ruleBookInfo=").append(this.ruleBookInfo);
         var1.append(", ruleToc=").append(this.ruleToc).append(", ruleContent=").append(this.ruleContent).append(')');
         return var1.toString();
      }

      public int hashCode() {
         int result = this.bookSourceName.hashCode();
         result = result * 31 + (this.bookSourceGroup == null ? 0 : this.bookSourceGroup.hashCode());
         result = result * 31 + this.bookSourceUrl.hashCode();
         result = result * 31 + Integer.hashCode(this.bookSourceType);
         result = result * 31 + (this.bookUrlPattern == null ? 0 : this.bookUrlPattern.hashCode());
         result = result * 31 + Integer.hashCode(this.customOrder);
         int var10000 = result * 31;
         byte var10001 = this.enabled;
         if (var10001 != 0) {
            var10001 = 1;
         }

         result = var10000 + var10001;
         var10000 = result * 31;
         var10001 = this.enabledExplore;
         if (var10001 != 0) {
            var10001 = 1;
         }

         result = var10000 + var10001;
         var10000 = result * 31;
         var10001 = this.enabledCookieJar;
         if (var10001 != 0) {
            var10001 = 1;
         }

         result = var10000 + var10001;
         result = result * 31 + (this.concurrentRate == null ? 0 : this.concurrentRate.hashCode());
         result = result * 31 + (this.header == null ? 0 : this.header.hashCode());
         result = result * 31 + (this.loginUrl == null ? 0 : this.loginUrl.hashCode());
         result = result * 31 + (this.loginUi == null ? 0 : this.loginUi.hashCode());
         result = result * 31 + (this.loginCheckJs == null ? 0 : this.loginCheckJs.hashCode());
         result = result * 31 + (this.bookSourceComment == null ? 0 : this.bookSourceComment.hashCode());
         result = result * 31 + Long.hashCode(this.lastUpdateTime);
         result = result * 31 + Long.hashCode(this.respondTime);
         result = result * 31 + Integer.hashCode(this.weight);
         result = result * 31 + (this.exploreUrl == null ? 0 : this.exploreUrl.hashCode());
         result = result * 31 + (this.ruleExplore == null ? 0 : this.ruleExplore.hashCode());
         result = result * 31 + (this.searchUrl == null ? 0 : this.searchUrl.hashCode());
         result = result * 31 + (this.ruleSearch == null ? 0 : this.ruleSearch.hashCode());
         result = result * 31 + (this.ruleBookInfo == null ? 0 : this.ruleBookInfo.hashCode());
         result = result * 31 + (this.ruleToc == null ? 0 : this.ruleToc.hashCode());
         result = result * 31 + (this.ruleContent == null ? 0 : this.ruleContent.hashCode());
         return result;
      }

      public boolean equals(@Nullable Object other) {
         if (this == other) {
            return true;
         } else if (!(other instanceof SourceAnalyzer.BookSourceAny)) {
            return false;
         } else {
            SourceAnalyzer.BookSourceAny var2 = (SourceAnalyzer.BookSourceAny)other;
            if (!Intrinsics.areEqual(this.bookSourceName, var2.bookSourceName)) {
               return false;
            } else if (!Intrinsics.areEqual(this.bookSourceGroup, var2.bookSourceGroup)) {
               return false;
            } else if (!Intrinsics.areEqual(this.bookSourceUrl, var2.bookSourceUrl)) {
               return false;
            } else if (this.bookSourceType != var2.bookSourceType) {
               return false;
            } else if (!Intrinsics.areEqual(this.bookUrlPattern, var2.bookUrlPattern)) {
               return false;
            } else if (this.customOrder != var2.customOrder) {
               return false;
            } else if (this.enabled != var2.enabled) {
               return false;
            } else if (this.enabledExplore != var2.enabledExplore) {
               return false;
            } else if (this.enabledCookieJar != var2.enabledCookieJar) {
               return false;
            } else if (!Intrinsics.areEqual(this.concurrentRate, var2.concurrentRate)) {
               return false;
            } else if (!Intrinsics.areEqual(this.header, var2.header)) {
               return false;
            } else if (!Intrinsics.areEqual(this.loginUrl, var2.loginUrl)) {
               return false;
            } else if (!Intrinsics.areEqual(this.loginUi, var2.loginUi)) {
               return false;
            } else if (!Intrinsics.areEqual(this.loginCheckJs, var2.loginCheckJs)) {
               return false;
            } else if (!Intrinsics.areEqual(this.bookSourceComment, var2.bookSourceComment)) {
               return false;
            } else if (this.lastUpdateTime != var2.lastUpdateTime) {
               return false;
            } else if (this.respondTime != var2.respondTime) {
               return false;
            } else if (this.weight != var2.weight) {
               return false;
            } else if (!Intrinsics.areEqual(this.exploreUrl, var2.exploreUrl)) {
               return false;
            } else if (!Intrinsics.areEqual(this.ruleExplore, var2.ruleExplore)) {
               return false;
            } else if (!Intrinsics.areEqual(this.searchUrl, var2.searchUrl)) {
               return false;
            } else if (!Intrinsics.areEqual(this.ruleSearch, var2.ruleSearch)) {
               return false;
            } else if (!Intrinsics.areEqual(this.ruleBookInfo, var2.ruleBookInfo)) {
               return false;
            } else if (!Intrinsics.areEqual(this.ruleToc, var2.ruleToc)) {
               return false;
            } else {
               return Intrinsics.areEqual(this.ruleContent, var2.ruleContent);
            }
         }
      }

      public BookSourceAny() {
         this((String)null, (String)null, (String)null, 0, (String)null, 0, false, false, false, (String)null, (String)null, (Object)null, (Object)null, (String)null, (String)null, 0L, 0L, 0, (String)null, (Object)null, (String)null, (Object)null, (Object)null, (Object)null, (Object)null, 33554431, (DefaultConstructorMarker)null);
      }
   }
}
