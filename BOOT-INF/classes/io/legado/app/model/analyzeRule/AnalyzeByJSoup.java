package io.legado.app.model.analyzeRule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.jsoup.select.Evaluator.Id;
import org.seimicrawler.xpath.JXNode;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000e\u0018\u0000 \u00182\u00020\u0001:\u0003\u0018\u0019\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nJ\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u0002J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\tH\u0002J\u0018\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\tH\u0002J\u0017\u0010\u0012\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\tH\u0000¢\u0006\u0002\b\u0013J\u0015\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH\u0000¢\u0006\u0002\b\u0015J\u001b\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u0011\u001a\u00020\tH\u0000¢\u0006\u0002\b\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"},
   d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeByJSoup;", "", "doc", "(Ljava/lang/Object;)V", "element", "Lorg/jsoup/nodes/Element;", "getElements", "Lorg/jsoup/select/Elements;", "rule", "", "getElements$reader_pro", "temp", "getResultLast", "", "elements", "lastRule", "getResultList", "ruleStr", "getString", "getString$reader_pro", "getString0", "getString0$reader_pro", "getStringList", "getStringList$reader_pro", "Companion", "ElementsSingle", "SourceRule", "reader-pro"}
)
public final class AnalyzeByJSoup {
   @NotNull
   public static final AnalyzeByJSoup.Companion Companion = new AnalyzeByJSoup.Companion((DefaultConstructorMarker)null);
   @NotNull
   private Element element;
   @NotNull
   private static final String[] validKeys;

   public AnalyzeByJSoup(@NotNull Object doc) {
      Intrinsics.checkNotNullParameter(doc, "doc");
      super();
      this.element = Companion.parse(doc);
   }

   @NotNull
   public final Elements getElements$reader_pro(@NotNull String rule) {
      Intrinsics.checkNotNullParameter(rule, "rule");
      return this.getElements(this.element, rule);
   }

   @Nullable
   public final String getString$reader_pro(@NotNull String ruleStr) {
      Intrinsics.checkNotNullParameter(ruleStr, "ruleStr");
      CharSequence var2 = (CharSequence)ruleStr;
      boolean var3 = false;
      String var10000;
      if (var2.length() == 0) {
         var10000 = null;
      } else {
         List var11 = this.getStringList$reader_pro(ruleStr);
         boolean var4 = false;
         boolean var5 = false;
         int var7 = false;
         Collection var8 = (Collection)var11;
         boolean var9 = false;
         List var10 = !var8.isEmpty() ? var11 : null;
         var10000 = var10 == null ? null : CollectionsKt.joinToString$default((Iterable)var10, (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
      }

      return var10000;
   }

   @NotNull
   public final String getString0$reader_pro(@NotNull String ruleStr) {
      Intrinsics.checkNotNullParameter(ruleStr, "ruleStr");
      List var2 = this.getStringList$reader_pro(ruleStr);
      boolean var3 = false;
      boolean var4 = false;
      int var6 = false;
      return var2.isEmpty() ? "" : (String)var2.get(0);
   }

   @NotNull
   public final List<String> getStringList$reader_pro(@NotNull String ruleStr) {
      Intrinsics.checkNotNullParameter(ruleStr, "ruleStr");
      ArrayList textS = new ArrayList();
      CharSequence var3 = (CharSequence)ruleStr;
      boolean var4 = false;
      if (var3.length() == 0) {
         return (List)textS;
      } else {
         AnalyzeByJSoup.SourceRule sourceRule = new AnalyzeByJSoup.SourceRule(ruleStr);
         CharSequence var16 = (CharSequence)sourceRule.getElementsRule();
         boolean var5 = false;
         if (var16.length() == 0) {
            String var17 = this.element.data();
            textS.add(var17 == null ? "" : var17);
         } else {
            RuleAnalyzer ruleAnalyzes = new RuleAnalyzer(sourceRule.getElementsRule(), false, 2, (DefaultConstructorMarker)null);
            String[] var6 = new String[]{"&&", "||", "%%"};
            ArrayList ruleStrS = ruleAnalyzes.splitRule(var6);
            ArrayList results = new ArrayList();
            Iterator var7 = ruleStrS.iterator();

            while(var7.hasNext()) {
               String ruleStrX = (String)var7.next();
               List var10000;
               if (sourceRule.isCss()) {
                  Intrinsics.checkNotNullExpressionValue(ruleStrX, "ruleStrX");
                  int lastIndex = StringsKt.lastIndexOf$default((CharSequence)ruleStrX, '@', 0, false, 6, (Object)null);
                  Element var10001 = this.element;
                  byte var13 = 0;
                  boolean var14 = false;
                  String var10002 = ruleStrX.substring(var13, lastIndex);
                  Intrinsics.checkNotNullExpressionValue(var10002, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                  Elements var11 = var10001.select(var10002);
                  Intrinsics.checkNotNullExpressionValue(var11, "element.select(ruleStrX.substring(0, lastIndex))");
                  int var12 = lastIndex + 1;
                  boolean var30 = false;
                  var10002 = ruleStrX.substring(var12);
                  Intrinsics.checkNotNullExpressionValue(var10002, "(this as java.lang.String).substring(startIndex)");
                  var10000 = this.getResultLast(var11, var10002);
               } else {
                  Intrinsics.checkNotNullExpressionValue(ruleStrX, "ruleStrX");
                  var10000 = this.getResultList(ruleStrX);
               }

               List temp = var10000;
               Collection var25 = (Collection)temp;
               boolean var27 = false;
               boolean var29 = false;
               if (var25 != null && !var25.isEmpty()) {
                  results.add(temp);
                  if (Intrinsics.areEqual(ruleAnalyzes.getElementsType(), "||")) {
                     break;
                  }
               }
            }

            if (results.size() > 0) {
               if (Intrinsics.areEqual("%%", ruleAnalyzes.getElementsType())) {
                  int var21 = 0;
                  int var22 = ((List)results.get(0)).size() + -1;
                  if (var21 <= var22) {
                     do {
                        int i = var21++;
                        Iterator var26 = results.iterator();

                        while(var26.hasNext()) {
                           List temp = (List)var26.next();
                           if (i < temp.size()) {
                              textS.add(temp.get(i));
                           }
                        }
                     } while(var21 <= var22);
                  }
               } else {
                  var7 = results.iterator();

                  while(var7.hasNext()) {
                     List temp = (List)var7.next();
                     textS.addAll((Collection)temp);
                  }
               }
            }
         }

         return (List)textS;
      }
   }

   private final Elements getElements(Element temp, String rule) {
      if (temp != null) {
         CharSequence var3 = (CharSequence)rule;
         boolean var4 = false;
         if (var3.length() != 0) {
            Elements elements = new Elements();
            AnalyzeByJSoup.SourceRule sourceRule = new AnalyzeByJSoup.SourceRule(rule);
            RuleAnalyzer ruleAnalyzes = new RuleAnalyzer(sourceRule.getElementsRule(), false, 2, (DefaultConstructorMarker)null);
            String[] var7 = new String[]{"&&", "||", "%%"};
            ArrayList ruleStrS = ruleAnalyzes.splitRule(var7);
            ArrayList elementsList = new ArrayList();
            Iterator var8;
            String ruleStr;
            Elements es;
            if (sourceRule.isCss()) {
               var8 = ruleStrS.iterator();

               while(var8.hasNext()) {
                  ruleStr = (String)var8.next();
                  Elements tempS = temp.select(ruleStr);
                  elementsList.add(tempS);
                  if (tempS.size() > 0 && Intrinsics.areEqual(ruleAnalyzes.getElementsType(), "||")) {
                     break;
                  }
               }
            } else {
               var8 = ruleStrS.iterator();

               while(var8.hasNext()) {
                  ruleStr = (String)var8.next();
                  Intrinsics.checkNotNullExpressionValue(ruleStr, "ruleStr");
                  RuleAnalyzer rsRule = new RuleAnalyzer(ruleStr, false, 2, (DefaultConstructorMarker)null);
                  rsRule.trim();
                  String[] var12 = new String[]{"@"};
                  ArrayList rs = rsRule.splitRule(var12);
                  Elements var10000;
                  if (rs.size() <= 1) {
                     var10000 = (new AnalyzeByJSoup.ElementsSingle('\u0000', (String)null, (List)null, (List)null, 15, (DefaultConstructorMarker)null)).getElementsSingle(temp, ruleStr);
                  } else {
                     Elements el = new Elements();
                     el.add(temp);
                     Iterator var14 = rs.iterator();

                     while(true) {
                        if (!var14.hasNext()) {
                           var10000 = el;
                           break;
                        }

                        String rl = (String)var14.next();
                        Elements es = new Elements();
                        Iterator var17 = el.iterator();

                        while(var17.hasNext()) {
                           Element et = (Element)var17.next();
                           Intrinsics.checkNotNullExpressionValue(rl, "rl");
                           es.addAll((Collection)this.getElements(et, rl));
                        }

                        el.clear();
                        el.addAll((Collection)es);
                     }
                  }

                  es = var10000;
                  elementsList.add(es);
                  if (es.size() > 0 && Intrinsics.areEqual(ruleAnalyzes.getElementsType(), "||")) {
                     break;
                  }
               }
            }

            if (elementsList.size() > 0) {
               if (Intrinsics.areEqual("%%", ruleAnalyzes.getElementsType())) {
                  int var22 = 0;
                  int var23 = ((Elements)elementsList.get(0)).size();
                  if (var22 < var23) {
                     do {
                        int i = var22++;
                        Iterator var27 = elementsList.iterator();

                        while(var27.hasNext()) {
                           es = (Elements)var27.next();
                           if (i < es.size()) {
                              elements.add(es.get(i));
                           }
                        }
                     } while(var22 < var23);
                  }
               } else {
                  var8 = elementsList.iterator();

                  while(var8.hasNext()) {
                     Elements es = (Elements)var8.next();
                     elements.addAll((Collection)es);
                  }
               }
            }

            return elements;
         }
      }

      return new Elements();
   }

   private final List<String> getResultList(String ruleStr) {
      CharSequence var2 = (CharSequence)ruleStr;
      boolean var3 = false;
      if (var2.length() == 0) {
         return null;
      } else {
         Elements elements = new Elements();
         elements.add(this.element);
         RuleAnalyzer rule = new RuleAnalyzer(ruleStr, false, 2, (DefaultConstructorMarker)null);
         rule.trim();
         String[] var5 = new String[]{"@"};
         ArrayList rules = rule.splitRule(var5);
         int last = rules.size() - 1;
         int var6 = 0;
         if (var6 < last) {
            do {
               int i = var6++;
               Elements es = new Elements();
               Iterator var9 = elements.iterator();

               while(var9.hasNext()) {
                  Element elt = (Element)var9.next();
                  AnalyzeByJSoup.ElementsSingle var10001 = new AnalyzeByJSoup.ElementsSingle('\u0000', (String)null, (List)null, (List)null, 15, (DefaultConstructorMarker)null);
                  Intrinsics.checkNotNullExpressionValue(elt, "elt");
                  Object var11 = rules.get(i);
                  Intrinsics.checkNotNullExpressionValue(var11, "rules[i]");
                  es.addAll((Collection)var10001.getElementsSingle(elt, (String)var11));
               }

               elements.clear();
               elements = es;
            } while(var6 < last);
         }

         List var10000;
         if (elements.isEmpty()) {
            var10000 = null;
         } else {
            Object var15 = rules.get(last);
            Intrinsics.checkNotNullExpressionValue(var15, "rules[last]");
            var10000 = this.getResultLast(elements, (String)var15);
         }

         return var10000;
      }
   }

   private final List<String> getResultLast(Elements elements, String lastRule) {
      ArrayList textS = new ArrayList();
      Iterator var5;
      Element element;
      String text;
      CharSequence var8;
      boolean var9;
      switch(lastRule.hashCode()) {
      case -1055246893:
         if (lastRule.equals("ownText")) {
            var5 = elements.iterator();

            while(var5.hasNext()) {
               element = (Element)var5.next();
               text = element.ownText();
               Intrinsics.checkNotNullExpressionValue(text, "text");
               var8 = (CharSequence)text;
               var9 = false;
               if (var8.length() > 0) {
                  textS.add(text);
               }
            }

            return (List)textS;
         }
         break;
      case -1053421180:
         if (lastRule.equals("textNodes")) {
            var5 = elements.iterator();

            while(var5.hasNext()) {
               element = (Element)var5.next();
               boolean var26 = false;
               ArrayList tn = new ArrayList();
               List contentEs = element.textNodes();
               Iterator var28 = contentEs.iterator();

               while(var28.hasNext()) {
                  TextNode item = (TextNode)var28.next();
                  String var12 = item.text();
                  Intrinsics.checkNotNullExpressionValue(var12, "item.text()");
                  int $i$f$trim = false;
                  CharSequence $this$trim$iv$iv = (CharSequence)var12;
                  int $i$f$trim = false;
                  int startIndex$iv$iv = 0;
                  int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
                  boolean startFound$iv$iv = false;

                  while(startIndex$iv$iv <= endIndex$iv$iv) {
                     int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
                     char it = $this$trim$iv$iv.charAt(index$iv$iv);
                     int var21 = false;
                     boolean match$iv$iv = Intrinsics.compare(it, 32) <= 0;
                     if (!startFound$iv$iv) {
                        if (!match$iv$iv) {
                           startFound$iv$iv = true;
                        } else {
                           ++startIndex$iv$iv;
                        }
                     } else {
                        if (!match$iv$iv) {
                           break;
                        }

                        --endIndex$iv$iv;
                     }
                  }

                  String text = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
                  CharSequence var31 = (CharSequence)text;
                  $i$f$trim = false;
                  if (var31.length() > 0) {
                     tn.add(text);
                  }
               }

               Collection var29 = (Collection)tn;
               boolean var30 = false;
               if (!var29.isEmpty()) {
                  textS.add(CollectionsKt.joinToString$default((Iterable)tn, (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null));
               }
            }

            return (List)textS;
         }
         break;
      case 96673:
         if (lastRule.equals("all")) {
            textS.add(elements.outerHtml());
            return (List)textS;
         }
         break;
      case 3213227:
         if (lastRule.equals("html")) {
            elements.select("script").remove();
            elements.select("style").remove();
            String html = elements.outerHtml();
            Intrinsics.checkNotNullExpressionValue(html, "html");
            CharSequence var23 = (CharSequence)html;
            boolean var24 = false;
            if (var23.length() > 0) {
               textS.add(html);
            }

            return (List)textS;
         }
         break;
      case 3556653:
         if (lastRule.equals("text")) {
            var5 = elements.iterator();

            while(var5.hasNext()) {
               element = (Element)var5.next();
               text = element.text();
               Intrinsics.checkNotNullExpressionValue(text, "text");
               var8 = (CharSequence)text;
               var9 = false;
               if (var8.length() > 0) {
                  textS.add(text);
               }
            }

            return (List)textS;
         }
      }

      var5 = elements.iterator();

      while(var5.hasNext()) {
         element = (Element)var5.next();
         text = element.attr(lastRule);
         Intrinsics.checkNotNullExpressionValue(text, "url");
         if (!StringsKt.isBlank((CharSequence)text) && !textS.contains(text)) {
            textS.add(text);
         }
      }

      return (List)textS;
   }

   static {
      String[] var0 = new String[]{"class", "id", "tag", "text", "children"};
      validKeys = var0;
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"},
      d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeByJSoup$Companion;", "", "()V", "validKeys", "", "", "getValidKeys", "()[Ljava/lang/String;", "[Ljava/lang/String;", "parse", "Lorg/jsoup/nodes/Element;", "doc", "reader-pro"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final String[] getValidKeys() {
         return AnalyzeByJSoup.validKeys;
      }

      @NotNull
      public final Element parse(@NotNull Object doc) {
         Intrinsics.checkNotNullParameter(doc, "doc");
         Element var10000;
         if (doc instanceof Element) {
            var10000 = (Element)doc;
         } else if (doc instanceof JXNode) {
            Element var3 = ((JXNode)doc).isElement() ? ((JXNode)doc).asElement() : (Element)Jsoup.parse(doc.toString());
            Intrinsics.checkNotNullExpressionValue(var3, "if (doc.isElement) doc.asElement() else Jsoup.parse(doc.toString())");
            var10000 = var3;
         } else {
            Document var4 = Jsoup.parse(doc.toString());
            Intrinsics.checkNotNullExpressionValue(var4, "parse(doc.toString())");
            var10000 = (Element)var4;
         }

         return var10000;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007HÆ\u0003J=\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0005H\u0002J\u0016\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010 \u001a\u00020\u0005J\t\u0010%\u001a\u00020\bHÖ\u0001J\t\u0010&\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006'"},
      d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeByJSoup$ElementsSingle;", "", "split", "", "beforeRule", "", "indexDefault", "", "", "indexes", "(CLjava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getBeforeRule", "()Ljava/lang/String;", "setBeforeRule", "(Ljava/lang/String;)V", "getIndexDefault", "()Ljava/util/List;", "getIndexes", "getSplit", "()C", "setSplit", "(C)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "findIndexSet", "", "rule", "getElementsSingle", "Lorg/jsoup/select/Elements;", "temp", "Lorg/jsoup/nodes/Element;", "hashCode", "toString", "reader-pro"}
   )
   public static final class ElementsSingle {
      private char split;
      @NotNull
      private String beforeRule;
      @NotNull
      private final List<Integer> indexDefault;
      @NotNull
      private final List<Object> indexes;

      public ElementsSingle(char split, @NotNull String beforeRule, @NotNull List<Integer> indexDefault, @NotNull List<Object> indexes) {
         Intrinsics.checkNotNullParameter(beforeRule, "beforeRule");
         Intrinsics.checkNotNullParameter(indexDefault, "indexDefault");
         Intrinsics.checkNotNullParameter(indexes, "indexes");
         super();
         this.split = split;
         this.beforeRule = beforeRule;
         this.indexDefault = indexDefault;
         this.indexes = indexes;
      }

      // $FF: synthetic method
      public ElementsSingle(char var1, String var2, List var3, List var4, int var5, DefaultConstructorMarker var6) {
         if ((var5 & 1) != 0) {
            var1 = '.';
         }

         if ((var5 & 2) != 0) {
            var2 = "";
         }

         boolean var7;
         if ((var5 & 4) != 0) {
            var7 = false;
            var3 = (List)(new ArrayList());
         }

         if ((var5 & 8) != 0) {
            var7 = false;
            var4 = (List)(new ArrayList());
         }

         this(var1, var2, var3, var4);
      }

      public final char getSplit() {
         return this.split;
      }

      public final void setSplit(char <set-?>) {
         this.split = var1;
      }

      @NotNull
      public final String getBeforeRule() {
         return this.beforeRule;
      }

      public final void setBeforeRule(@NotNull String <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.beforeRule = var1;
      }

      @NotNull
      public final List<Integer> getIndexDefault() {
         return this.indexDefault;
      }

      @NotNull
      public final List<Object> getIndexes() {
         return this.indexes;
      }

      @NotNull
      public final Elements getElementsSingle(@NotNull Element temp, @NotNull String rule) {
         Intrinsics.checkNotNullParameter(temp, "temp");
         Intrinsics.checkNotNullParameter(rule, "rule");
         this.findIndexSet(rule);
         CharSequence var4 = (CharSequence)this.beforeRule;
         boolean var5 = false;
         Elements var10000;
         if (var4.length() == 0) {
            var10000 = temp.children();
         } else {
            label180: {
               CharSequence var32 = (CharSequence)this.beforeRule;
               String[] var18 = new String[]{"."};
               List rules = StringsKt.split$default(var32, var18, false, 0, 6, (Object)null);
               String var19 = (String)rules.get(0);
               switch(var19.hashCode()) {
               case 3355:
                  if (var19.equals("id")) {
                     var10000 = Collector.collect((Evaluator)(new Id((String)rules.get(1))), temp);
                     break label180;
                  }
                  break;
               case 114586:
                  if (var19.equals("tag")) {
                     var10000 = temp.getElementsByTag((String)rules.get(1));
                     break label180;
                  }
                  break;
               case 3556653:
                  if (var19.equals("text")) {
                     var10000 = temp.getElementsContainingOwnText((String)rules.get(1));
                     break label180;
                  }
                  break;
               case 94742904:
                  if (var19.equals("class")) {
                     var10000 = temp.getElementsByClass((String)rules.get(1));
                     break label180;
                  }
                  break;
               case 1659526655:
                  if (var19.equals("children")) {
                     var10000 = temp.children();
                     break label180;
                  }
               }

               var10000 = temp.select(this.beforeRule);
            }
         }

         Elements elements = var10000;
         int len = elements.size();
         Integer var7 = this.indexDefault.size() - 1;
         boolean var8 = false;
         boolean var9 = false;
         int it = ((Number)var7).intValue();
         int var11 = false;
         Integer var6 = it != -1 ? var7 : null;
         int lastIndexes = var6 == null ? this.indexes.size() - 1 : var6;
         boolean var22 = false;
         Set indexSet = (Set)(new LinkedHashSet());
         int var23;
         int pcInt;
         int pcInt;
         if (this.indexes.isEmpty()) {
            var23 = lastIndexes;
            if (0 <= lastIndexes) {
               do {
                  pcInt = var23--;
                  pcInt = ((Number)this.indexDefault.get(pcInt)).intValue();
                  if (0 <= pcInt ? pcInt < len : false) {
                     indexSet.add(pcInt);
                  } else if (pcInt < 0 && len >= -pcInt) {
                     indexSet.add(pcInt + len);
                  }
               } while(0 <= var23);
            }
         } else {
            var23 = lastIndexes;
            if (0 <= lastIndexes) {
               do {
                  pcInt = var23--;
                  if (this.indexes.get(pcInt) instanceof Triple) {
                     Triple var29 = (Triple)this.indexes.get(pcInt);
                     Integer startX = (Integer)var29.component1();
                     Integer endX = (Integer)var29.component2();
                     int stepX = ((Number)var29.component3()).intValue();
                     int start = startX == null ? 0 : (startX >= 0 ? (startX < len ? startX : len - 1) : (-startX <= len ? len + startX : 0));
                     int end = endX == null ? len - 1 : (endX >= 0 ? (endX < len ? endX : len - 1) : (-endX <= len ? len + endX : 0));
                     if (start != end && stepX < len) {
                        int step = stepX > 0 ? stepX : (-stepX < len ? stepX + len : 1);
                        CollectionsKt.addAll((Collection)indexSet, (Iterable)(end > start ? RangesKt.step((IntProgression)(new IntRange(start, end)), step) : RangesKt.step(RangesKt.downTo(start, end), step)));
                     } else {
                        indexSet.add(start);
                     }
                  } else {
                     pcInt = (Integer)this.indexes.get(pcInt);
                     if (0 <= pcInt ? pcInt < len : false) {
                        indexSet.add(pcInt);
                     } else if (pcInt < 0 && len >= -pcInt) {
                        indexSet.add(pcInt + len);
                     }
                  }
               } while(0 <= var23);
            }
         }

         if (this.split == '!') {
            Iterator var25 = indexSet.iterator();

            while(var25.hasNext()) {
               pcInt = ((Number)var25.next()).intValue();
               elements.set(pcInt, (Object)null);
            }

            elements.removeAll((Collection)CollectionsKt.listOf((Object)null));
         } else if (this.split == '.') {
            Elements es = new Elements();
            Iterator var28 = indexSet.iterator();

            while(var28.hasNext()) {
               pcInt = ((Number)var28.next()).intValue();
               es.add(elements.get(pcInt));
            }

            elements = es;
         }

         Intrinsics.checkNotNullExpressionValue(elements, "elements");
         return elements;
      }

      private final void findIndexSet(String rule) {
         int $i$f$trim = false;
         CharSequence $this$trim$iv$iv = (CharSequence)rule;
         int $i$f$trim = false;
         int startIndex$iv$iv = 0;
         int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
         boolean startFound$iv$iv = false;

         boolean var12;
         boolean match$iv$iv;
         while(startIndex$iv$iv <= endIndex$iv$iv) {
            int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
            char it = $this$trim$iv$iv.charAt(index$iv$iv);
            var12 = false;
            match$iv$iv = Intrinsics.compare(it, 32) <= 0;
            if (!startFound$iv$iv) {
               if (!match$iv$iv) {
                  startFound$iv$iv = true;
               } else {
                  ++startIndex$iv$iv;
               }
            } else {
               if (!match$iv$iv) {
                  break;
               }

               --endIndex$iv$iv;
            }
         }

         String rus = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
         int len = rus.length();
         Integer curInt = null;
         boolean curMinus = false;
         boolean var17 = false;
         List curList = (List)(new ArrayList());
         String l = "";
         boolean head = StringsKt.last((CharSequence)rus) == ']';
         int var20;
         char rl;
         String var27;
         if (head) {
            len += -1;

            label148:
            while(true) {
               while(true) {
                  do {
                     var20 = len;
                     len += -1;
                     if (var20 < 0) {
                        break label148;
                     }

                     rl = rus.charAt(len);
                  } while(rl == ' ');

                  if ('0' <= rl ? rl <= '9' : false) {
                     match$iv$iv = false;
                     l = rl + l;
                  } else if (rl == '-') {
                     curMinus = true;
                  } else {
                     CharSequence var22 = (CharSequence)l;
                     match$iv$iv = false;
                     Integer var26;
                     if (var22.length() == 0) {
                        var26 = null;
                     } else if (curMinus) {
                        match$iv$iv = false;
                        var26 = -Integer.parseInt(l);
                     } else {
                        match$iv$iv = false;
                        var26 = Integer.parseInt(l);
                     }

                     curInt = var26;
                     if (rl == ':') {
                        curList.add(curInt);
                     } else {
                        if (curList.isEmpty()) {
                           if (curInt == null) {
                              break label148;
                           }

                           this.indexes.add(curInt);
                        } else {
                           this.indexes.add(new Triple(curInt, CollectionsKt.last(curList), curList.size() == 2 ? (Integer)CollectionsKt.first(curList) : 1));
                           curList.clear();
                        }

                        if (rl == '!') {
                           this.split = '!';

                           do {
                              --len;
                              rl = rus.charAt(len);
                           } while(len > 0 && rl == ' ');
                        }

                        if (rl == '[') {
                           byte var25 = 0;
                           boolean var13 = false;
                           if (rus == null) {
                              throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                           }

                           var27 = rus.substring(var25, len);
                           Intrinsics.checkNotNullExpressionValue(var27, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                           this.beforeRule = var27;
                           return;
                        }

                        if (rl != ',') {
                           break label148;
                        }
                     }

                     l = "";
                     curMinus = false;
                  }
               }
            }
         } else {
            while(true) {
               var20 = len;
               len += -1;
               if (var20 < 0) {
                  break;
               }

               rl = rus.charAt(len);
               if (rl != ' ') {
                  if ('0' <= rl ? rl <= '9' : false) {
                     match$iv$iv = false;
                     l = rl + l;
                  } else if (rl == '-') {
                     curMinus = true;
                  } else {
                     if (rl != '!' && rl != '.' && rl != ':') {
                        break;
                     }

                     List var10000 = this.indexDefault;
                     int var10001;
                     if (curMinus) {
                        match$iv$iv = false;
                        var10001 = -Integer.parseInt(l);
                     } else {
                        match$iv$iv = false;
                        var10001 = Integer.parseInt(l);
                     }

                     var10000.add(var10001);
                     if (rl != ':') {
                        this.split = rl;
                        byte var24 = 0;
                        var12 = false;
                        if (rus == null) {
                           throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }

                        var27 = rus.substring(var24, len);
                        Intrinsics.checkNotNullExpressionValue(var27, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        this.beforeRule = var27;
                        return;
                     }

                     l = "";
                     curMinus = false;
                  }
               }
            }
         }

         this.split = ' ';
         this.beforeRule = rus;
      }

      public final char component1() {
         return this.split;
      }

      @NotNull
      public final String component2() {
         return this.beforeRule;
      }

      @NotNull
      public final List<Integer> component3() {
         return this.indexDefault;
      }

      @NotNull
      public final List<Object> component4() {
         return this.indexes;
      }

      @NotNull
      public final AnalyzeByJSoup.ElementsSingle copy(char split, @NotNull String beforeRule, @NotNull List<Integer> indexDefault, @NotNull List<Object> indexes) {
         Intrinsics.checkNotNullParameter(beforeRule, "beforeRule");
         Intrinsics.checkNotNullParameter(indexDefault, "indexDefault");
         Intrinsics.checkNotNullParameter(indexes, "indexes");
         return new AnalyzeByJSoup.ElementsSingle(split, beforeRule, indexDefault, indexes);
      }

      // $FF: synthetic method
      public static AnalyzeByJSoup.ElementsSingle copy$default(AnalyzeByJSoup.ElementsSingle var0, char var1, String var2, List var3, List var4, int var5, Object var6) {
         if ((var5 & 1) != 0) {
            var1 = var0.split;
         }

         if ((var5 & 2) != 0) {
            var2 = var0.beforeRule;
         }

         if ((var5 & 4) != 0) {
            var3 = var0.indexDefault;
         }

         if ((var5 & 8) != 0) {
            var4 = var0.indexes;
         }

         return var0.copy(var1, var2, var3, var4);
      }

      @NotNull
      public String toString() {
         return "ElementsSingle(split=" + this.split + ", beforeRule=" + this.beforeRule + ", indexDefault=" + this.indexDefault + ", indexes=" + this.indexes + ')';
      }

      public int hashCode() {
         int result = Character.hashCode(this.split);
         result = result * 31 + this.beforeRule.hashCode();
         result = result * 31 + this.indexDefault.hashCode();
         result = result * 31 + this.indexes.hashCode();
         return result;
      }

      public boolean equals(@Nullable Object other) {
         if (this == other) {
            return true;
         } else if (!(other instanceof AnalyzeByJSoup.ElementsSingle)) {
            return false;
         } else {
            AnalyzeByJSoup.ElementsSingle var2 = (AnalyzeByJSoup.ElementsSingle)other;
            if (this.split != var2.split) {
               return false;
            } else if (!Intrinsics.areEqual(this.beforeRule, var2.beforeRule)) {
               return false;
            } else if (!Intrinsics.areEqual(this.indexDefault, var2.indexDefault)) {
               return false;
            } else {
               return Intrinsics.areEqual(this.indexes, var2.indexes);
            }
         }
      }

      public ElementsSingle() {
         this('\u0000', (String)null, (List)null, (List)null, 15, (DefaultConstructorMarker)null);
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"},
      d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeByJSoup$SourceRule;", "", "ruleStr", "", "(Lio/legado/app/model/analyzeRule/AnalyzeByJSoup;Ljava/lang/String;)V", "elementsRule", "getElementsRule", "()Ljava/lang/String;", "setElementsRule", "(Ljava/lang/String;)V", "isCss", "", "()Z", "setCss", "(Z)V", "reader-pro"}
   )
   public final class SourceRule {
      private boolean isCss;
      @NotNull
      private String elementsRule;

      public SourceRule(@NotNull String ruleStr) {
         Intrinsics.checkNotNullParameter(AnalyzeByJSoup.this, "this$0");
         Intrinsics.checkNotNullParameter(ruleStr, "ruleStr");
         super();
         AnalyzeByJSoup.SourceRule var10000 = this;
         String var10001;
         if (StringsKt.startsWith(ruleStr, "@CSS:", true)) {
            this.isCss = true;
            byte var4 = 5;
            boolean var5 = false;
            var10001 = ruleStr.substring(var4);
            Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).substring(startIndex)");
            String $this$trim$iv = var10001;
            int $i$f$trim = false;
            CharSequence $this$trim$iv$iv = (CharSequence)$this$trim$iv;
            int $i$f$trimx = false;
            int startIndex$iv$iv = 0;
            int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
            boolean startFound$iv$iv = false;

            while(startIndex$iv$iv <= endIndex$iv$iv) {
               int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
               char it = $this$trim$iv$iv.charAt(index$iv$iv);
               int var12 = false;
               boolean match$iv$iv = Intrinsics.compare(it, 32) <= 0;
               if (!startFound$iv$iv) {
                  if (!match$iv$iv) {
                     startFound$iv$iv = true;
                  } else {
                     ++startIndex$iv$iv;
                  }
               } else {
                  if (!match$iv$iv) {
                     break;
                  }

                  --endIndex$iv$iv;
               }
            }

            String var14 = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
            var10000 = this;
            var10001 = var14;
         } else {
            var10001 = ruleStr;
         }

         var10000.elementsRule = var10001;
      }

      public final boolean isCss() {
         return this.isCss;
      }

      public final void setCss(boolean <set-?>) {
         this.isCss = var1;
      }

      @NotNull
      public final String getElementsRule() {
         return this.elementsRule;
      }

      public final void setElementsRule(@NotNull String <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.elementsRule = var1;
      }
   }
}
