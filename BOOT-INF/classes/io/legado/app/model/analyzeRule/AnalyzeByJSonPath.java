package io.legado.app.model.analyzeRule;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.ReadContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u001d\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nJ\u0015\u0010\u000b\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\fJ\u0010\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\tJ\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000f2\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"},
   d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeByJSonPath;", "", "json", "(Ljava/lang/Object;)V", "ctx", "Lcom/jayway/jsonpath/ReadContext;", "getList", "Ljava/util/ArrayList;", "rule", "", "getList$reader_pro", "getObject", "getObject$reader_pro", "getString", "getStringList", "", "getStringList$reader_pro", "Companion", "reader-pro"}
)
public final class AnalyzeByJSonPath {
   @NotNull
   public static final AnalyzeByJSonPath.Companion Companion = new AnalyzeByJSonPath.Companion((DefaultConstructorMarker)null);
   @NotNull
   private ReadContext ctx;

   public AnalyzeByJSonPath(@NotNull Object json) {
      Intrinsics.checkNotNullParameter(json, "json");
      super();
      this.ctx = Companion.parse(json);
   }

   @Nullable
   public final String getString(@NotNull String rule) {
      Intrinsics.checkNotNullParameter(rule, "rule");
      CharSequence result = (CharSequence)rule;
      boolean var3 = false;
      if (result.length() == 0) {
         return null;
      } else {
         result = null;
         RuleAnalyzer ruleAnalyzes = new RuleAnalyzer(rule, true);
         String[] var5 = new String[]{"&&", "||"};
         ArrayList rules = ruleAnalyzes.splitRule(var5);
         boolean var6;
         if (rules.size() == 1) {
            ruleAnalyzes.reSetPos();
            String result = RuleAnalyzer.innerRule$default(ruleAnalyzes, "{$.", 0, 0, (Function1)(new Function1<String, String>() {
               @Nullable
               public final String invoke(@NotNull String it) {
                  Intrinsics.checkNotNullParameter(it, "it");
                  return AnalyzeByJSonPath.this.getString(it);
               }
            }), 6, (Object)null);
            CharSequence var16 = (CharSequence)result;
            var6 = false;
            if (var16.length() == 0) {
               try {
                  Object ob = this.ctx.read(rule, new Predicate[0]);
                  result = ob instanceof List ? CollectionsKt.joinToString$default((Iterable)ob, (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null) : ob.toString();
               } catch (Exception var12) {
                  var12.printStackTrace();
               }
            }

            return result;
         } else {
            var6 = false;
            ArrayList textList = new ArrayList();
            Iterator var18 = rules.iterator();

            while(var18.hasNext()) {
               String rl = (String)var18.next();
               Intrinsics.checkNotNullExpressionValue(rl, "rl");
               String temp = this.getString(rl);
               CharSequence var9 = (CharSequence)temp;
               boolean var10 = false;
               boolean var11 = false;
               if (var9 != null && var9.length() != 0) {
                  textList.add(temp);
                  if (Intrinsics.areEqual(ruleAnalyzes.getElementsType(), "||")) {
                     break;
                  }
               }
            }

            return CollectionsKt.joinToString$default((Iterable)textList, (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
         }
      }
   }

   @NotNull
   public final List<String> getStringList$reader_pro(@NotNull String rule) {
      Intrinsics.checkNotNullParameter(rule, "rule");
      ArrayList result = new ArrayList();
      CharSequence var3 = (CharSequence)rule;
      boolean var4 = false;
      if (var3.length() == 0) {
         return (List)result;
      } else {
         RuleAnalyzer ruleAnalyzes = new RuleAnalyzer(rule, true);
         String[] var5 = new String[]{"&&", "||", "%%"};
         ArrayList rules = ruleAnalyzes.splitRule(var5);
         if (rules.size() == 1) {
            ruleAnalyzes.reSetPos();
            String st = RuleAnalyzer.innerRule$default(ruleAnalyzes, "{$.", 0, 0, (Function1)(new Function1<String, String>() {
               @Nullable
               public final String invoke(@NotNull String it) {
                  Intrinsics.checkNotNullParameter(it, "it");
                  return AnalyzeByJSonPath.this.getString(it);
               }
            }), 6, (Object)null);
            CharSequence var17 = (CharSequence)st;
            boolean var21 = false;
            if (var17.length() == 0) {
               try {
                  Object obj = this.ctx.read(rule, new Predicate[0]);
                  if (obj instanceof List) {
                     Iterator var22 = ((List)obj).iterator();

                     while(var22.hasNext()) {
                        Object o = var22.next();
                        result.add(String.valueOf(o));
                     }
                  } else {
                     result.add(obj.toString());
                  }
               } catch (Exception var11) {
                  var11.printStackTrace();
               }
            } else {
               result.add(st);
            }

            return (List)result;
         } else {
            ArrayList results = new ArrayList();
            Iterator var6 = rules.iterator();

            while(var6.hasNext()) {
               String rl = (String)var6.next();
               Intrinsics.checkNotNullExpressionValue(rl, "rl");
               List temp = this.getStringList$reader_pro(rl);
               Collection var9 = (Collection)temp;
               boolean var10 = false;
               if (!var9.isEmpty()) {
                  results.add(temp);
                  var9 = (Collection)temp;
                  var10 = false;
                  if (!var9.isEmpty() && Intrinsics.areEqual(ruleAnalyzes.getElementsType(), "||")) {
                     break;
                  }
               }
            }

            if (results.size() > 0) {
               if (Intrinsics.areEqual("%%", ruleAnalyzes.getElementsType())) {
                  int var16 = 0;
                  int var18 = ((List)results.get(0)).size() + -1;
                  if (var16 <= var18) {
                     do {
                        int i = var16++;
                        Iterator var25 = results.iterator();

                        while(var25.hasNext()) {
                           List temp = (List)var25.next();
                           if (i < temp.size()) {
                              result.add(temp.get(i));
                           }
                        }
                     } while(var16 <= var18);
                  }
               } else {
                  var6 = results.iterator();

                  while(var6.hasNext()) {
                     List temp = (List)var6.next();
                     result.addAll((Collection)temp);
                  }
               }
            }

            return (List)result;
         }
      }
   }

   @NotNull
   public final Object getObject$reader_pro(@NotNull String rule) {
      Intrinsics.checkNotNullParameter(rule, "rule");
      Object var2 = this.ctx.read(rule, new Predicate[0]);
      Intrinsics.checkNotNullExpressionValue(var2, "ctx.read(rule)");
      return var2;
   }

   @Nullable
   public final ArrayList<Object> getList$reader_pro(@NotNull String rule) {
      Intrinsics.checkNotNullParameter(rule, "rule");
      ArrayList result = new ArrayList();
      CharSequence var3 = (CharSequence)rule;
      boolean var4 = false;
      if (var3.length() == 0) {
         return result;
      } else {
         RuleAnalyzer ruleAnalyzes = new RuleAnalyzer(rule, true);
         String[] var5 = new String[]{"&&", "||", "%%"};
         ArrayList rules = ruleAnalyzes.splitRule(var5);
         if (rules.size() == 1) {
            ReadContext var20 = this.ctx;
            boolean var6 = false;
            boolean var7 = false;
            ReadContext it = var20;
            boolean var9 = false;

            try {
               return (ArrayList)it.read((String)rules.get(0), new Predicate[0]);
            } catch (Exception var17) {
               var17.printStackTrace();
            }
         } else {
            ArrayList results = new ArrayList();
            Iterator var22 = rules.iterator();

            while(var22.hasNext()) {
               String rl = (String)var22.next();
               Intrinsics.checkNotNullExpressionValue(rl, "rl");
               ArrayList temp = this.getList$reader_pro(rl);
               if (temp != null) {
                  Collection var29 = (Collection)temp;
                  boolean var10 = false;
                  if (!var29.isEmpty()) {
                     results.add(temp);
                     var29 = (Collection)temp;
                     var10 = false;
                     if (!var29.isEmpty() && Intrinsics.areEqual(ruleAnalyzes.getElementsType(), "||")) {
                        break;
                     }
                  }
               }
            }

            if (results.size() > 0) {
               if (Intrinsics.areEqual("%%", ruleAnalyzes.getElementsType())) {
                  int var23 = 0;
                  int var25 = ((ArrayList)results.get(0)).size();
                  if (var23 < var25) {
                     do {
                        int i = var23++;
                        Iterator var30 = results.iterator();

                        while(var30.hasNext()) {
                           ArrayList temp = (ArrayList)var30.next();
                           if (i < temp.size()) {
                              Object var11 = temp.get(i);
                              if (var11 != null) {
                                 boolean var13 = false;
                                 boolean var14 = false;
                                 int var16 = false;
                                 result.add(var11);
                              }
                           }
                        }
                     } while(var23 < var25);
                  }
               } else {
                  var22 = results.iterator();

                  while(var22.hasNext()) {
                     ArrayList temp = (ArrayList)var22.next();
                     result.addAll((Collection)temp);
                  }
               }
            }
         }

         return result;
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001¨\u0006\u0006"},
      d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeByJSonPath$Companion;", "", "()V", "parse", "Lcom/jayway/jsonpath/ReadContext;", "json", "reader-pro"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final ReadContext parse(@NotNull Object json) {
         Intrinsics.checkNotNullParameter(json, "json");
         ReadContext var10000;
         if (json instanceof ReadContext) {
            var10000 = (ReadContext)json;
         } else {
            DocumentContext var3;
            if (json instanceof String) {
               var3 = JsonPath.parse((String)json);
               Intrinsics.checkNotNullExpressionValue(var3, "parse(json)");
               var10000 = (ReadContext)var3;
            } else {
               var3 = JsonPath.parse(json);
               Intrinsics.checkNotNullExpressionValue(var3, "parse(json)");
               var10000 = (ReadContext)var3;
            }
         }

         return var10000;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
