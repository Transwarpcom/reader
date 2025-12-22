package io.legado.app.model.analyzeRule;

import io.legado.app.utils.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u001d\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nJ\u0018\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\r\u001a\u00020\tJ\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\u000fJ\u0010\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"},
   d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeByXPath;", "", "doc", "(Ljava/lang/Object;)V", "jxNode", "getElements", "", "Lorg/seimicrawler/xpath/JXNode;", "xPath", "", "getElements$reader_pro", "getResult", "getString", "rule", "getStringList", "getStringList$reader_pro", "parse", "strToJXDocument", "Lorg/seimicrawler/xpath/JXDocument;", "html", "reader-pro"}
)
public final class AnalyzeByXPath {
   @NotNull
   private Object jxNode;

   public AnalyzeByXPath(@NotNull Object doc) {
      Intrinsics.checkNotNullParameter(doc, "doc");
      super();
      this.jxNode = this.parse(doc);
   }

   private final Object parse(Object doc) {
      Object var10000;
      if (doc instanceof JXNode) {
         var10000 = ((JXNode)doc).isElement() ? doc : this.strToJXDocument(doc.toString());
      } else {
         JXDocument var3;
         if (doc instanceof Document) {
            var3 = JXDocument.create((Document)doc);
            Intrinsics.checkNotNullExpressionValue(var3, "create(doc)");
            var10000 = var3;
         } else if (doc instanceof Element) {
            Element[] var4 = new Element[]{(Element)doc};
            var3 = JXDocument.create(new Elements(var4));
            Intrinsics.checkNotNullExpressionValue(var3, "create(Elements(doc))");
            var10000 = var3;
         } else if (doc instanceof Elements) {
            var3 = JXDocument.create((Elements)doc);
            Intrinsics.checkNotNullExpressionValue(var3, "create(doc)");
            var10000 = var3;
         } else {
            var10000 = this.strToJXDocument(doc.toString());
         }
      }

      return var10000;
   }

   private final JXDocument strToJXDocument(String html) {
      String html1 = html;
      if (StringsKt.endsWith$default(html, "</td>", false, 2, (Object)null)) {
         html1 = "<tr>" + html + "</tr>";
      }

      if (StringsKt.endsWith$default(html1, "</tr>", false, 2, (Object)null) || StringsKt.endsWith$default(html1, "</tbody>", false, 2, (Object)null)) {
         html1 = "<table>" + html1 + "</table>";
      }

      JXDocument var3 = JXDocument.create(html1);
      Intrinsics.checkNotNullExpressionValue(var3, "create(html1)");
      return var3;
   }

   private final List<JXNode> getResult(String xPath) {
      Object node = this.jxNode;
      return node instanceof JXNode ? ((JXNode)node).sel(xPath) : ((JXDocument)node).selN(xPath);
   }

   @Nullable
   public final List<JXNode> getElements$reader_pro(@NotNull String xPath) {
      Intrinsics.checkNotNullParameter(xPath, "xPath");
      CharSequence var2 = (CharSequence)xPath;
      boolean var3 = false;
      if (var2.length() == 0) {
         return null;
      } else {
         ArrayList jxNodes = new ArrayList();
         RuleAnalyzer ruleAnalyzes = new RuleAnalyzer(xPath, false, 2, (DefaultConstructorMarker)null);
         String[] var5 = new String[]{"&&", "||", "%%"};
         ArrayList rules = ruleAnalyzes.splitRule(var5);
         if (rules.size() == 1) {
            Object var14 = rules.get(0);
            Intrinsics.checkNotNullExpressionValue(var14, "rules[0]");
            return this.getResult((String)var14);
         } else {
            ArrayList results = new ArrayList();
            Iterator var6 = rules.iterator();

            while(var6.hasNext()) {
               String rl = (String)var6.next();
               Intrinsics.checkNotNullExpressionValue(rl, "rl");
               List temp = this.getElements$reader_pro(rl);
               if (temp != null) {
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
            }

            if (results.size() > 0) {
               if (Intrinsics.areEqual("%%", ruleAnalyzes.getElementsType())) {
                  int var15 = 0;
                  int var16 = ((List)results.get(0)).size() + -1;
                  if (var15 <= var16) {
                     do {
                        int i = var15++;
                        Iterator var19 = results.iterator();

                        while(var19.hasNext()) {
                           List temp = (List)var19.next();
                           if (i < temp.size()) {
                              jxNodes.add(temp.get(i));
                           }
                        }
                     } while(var15 <= var16);
                  }
               } else {
                  var6 = results.iterator();

                  while(var6.hasNext()) {
                     List temp = (List)var6.next();
                     jxNodes.addAll((Collection)temp);
                  }
               }
            }

            return (List)jxNodes;
         }
      }
   }

   @NotNull
   public final List<String> getStringList$reader_pro(@NotNull String xPath) {
      Intrinsics.checkNotNullParameter(xPath, "xPath");
      ArrayList result = new ArrayList();
      RuleAnalyzer ruleAnalyzes = new RuleAnalyzer(xPath, false, 2, (DefaultConstructorMarker)null);
      String[] var5 = new String[]{"&&", "||", "%%"};
      ArrayList rules = ruleAnalyzes.splitRule(var5);
      Collection destination$iv$iv;
      boolean $i$f$mapTo;
      if (rules.size() == 1) {
         List var18 = this.getResult(xPath);
         if (var18 != null) {
            Iterable $this$map$iv = (Iterable)var18;
            int $i$f$map = false;
            destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
            $i$f$mapTo = false;
            Iterator var11 = $this$map$iv.iterator();

            while(var11.hasNext()) {
               Object item$iv$iv = var11.next();
               JXNode it = (JXNode)item$iv$iv;
               int var14 = false;
               Boolean var16 = result.add(it.asString());
               destination$iv$iv.add(var16);
            }

            List var10000 = (List)destination$iv$iv;
         }

         return (List)result;
      } else {
         ArrayList results = new ArrayList();
         Iterator var6 = rules.iterator();

         while(var6.hasNext()) {
            String rl = (String)var6.next();
            Intrinsics.checkNotNullExpressionValue(rl, "rl");
            List temp = this.getStringList$reader_pro(rl);
            destination$iv$iv = (Collection)temp;
            $i$f$mapTo = false;
            if (!destination$iv$iv.isEmpty()) {
               results.add(temp);
               destination$iv$iv = (Collection)temp;
               $i$f$mapTo = false;
               if (!destination$iv$iv.isEmpty() && Intrinsics.areEqual(ruleAnalyzes.getElementsType(), "||")) {
                  break;
               }
            }
         }

         if (results.size() > 0) {
            if (Intrinsics.areEqual("%%", ruleAnalyzes.getElementsType())) {
               int var19 = 0;
               int var21 = ((List)results.get(0)).size() + -1;
               if (var19 <= var21) {
                  do {
                     int i = var19++;
                     Iterator var25 = results.iterator();

                     while(var25.hasNext()) {
                        List temp = (List)var25.next();
                        if (i < temp.size()) {
                           result.add(temp.get(i));
                        }
                     }
                  } while(var19 <= var21);
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

   @Nullable
   public final String getString(@NotNull String rule) {
      Intrinsics.checkNotNullParameter(rule, "rule");
      RuleAnalyzer ruleAnalyzes = new RuleAnalyzer(rule, false, 2, (DefaultConstructorMarker)null);
      String[] var4 = new String[]{"&&", "||"};
      ArrayList rules = ruleAnalyzes.splitRule(var4);
      boolean var9;
      if (rules.size() == 1) {
         List var12 = this.getResult(rule);
         if (var12 == null) {
            return null;
         } else {
            boolean var14 = false;
            boolean var15 = false;
            var9 = false;
            return TextUtils.join((CharSequence)"\n", (Iterable)var12);
         }
      } else {
         boolean var5 = false;
         ArrayList textList = new ArrayList();
         Iterator var13 = rules.iterator();

         while(var13.hasNext()) {
            String rl = (String)var13.next();
            Intrinsics.checkNotNullExpressionValue(rl, "rl");
            String temp = this.getString(rl);
            CharSequence var8 = (CharSequence)temp;
            var9 = false;
            boolean var10 = false;
            if (var8 != null && var8.length() != 0) {
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
