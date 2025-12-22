package io.legado.app.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

@Metadata(
   mv = {1, 5, 1},
   k = 2,
   xi = 48,
   d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0012\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002\u001a\u0015\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r*\u00020\u000f¢\u0006\u0002\u0010\u0010¨\u0006\u0011"},
   d2 = {"appendNormalisedText", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "textNode", "Lorg/jsoup/nodes/TextNode;", "lastCharIsWhitespace", "", "preserveWhitespace", "node", "Lorg/jsoup/nodes/Node;", "textArray", "", "", "Lorg/jsoup/nodes/Element;", "(Lorg/jsoup/nodes/Element;)[Ljava/lang/String;", "reader-pro"}
)
public final class JsoupExtensionsKt {
   @NotNull
   public static final String[] textArray(@NotNull Element $this$textArray) {
      Intrinsics.checkNotNullParameter($this$textArray, "<this>");
      final StringBuilder sb = StringUtil.borrowBuilder();
      NodeTraversor.traverse((NodeVisitor)(new NodeVisitor() {
         public void head(@NotNull Node node, int depth) {
            Intrinsics.checkNotNullParameter(node, "node");
            StringBuilder var3;
            if (node instanceof TextNode) {
               var3 = sb;
               Intrinsics.checkNotNullExpressionValue(var3, "sb");
               JsoupExtensionsKt.appendNormalisedText(var3, (TextNode)node);
            } else if (node instanceof Element) {
               var3 = sb;
               Intrinsics.checkNotNullExpressionValue(var3, "sb");
               CharSequence var5 = (CharSequence)var3;
               boolean var4 = false;
               if (var5.length() > 0 && (((Element)node).isBlock() || Intrinsics.areEqual(((Element)node).tag().getName(), "br"))) {
                  var3 = sb;
                  Intrinsics.checkNotNullExpressionValue(var3, "sb");
                  if (!JsoupExtensionsKt.lastCharIsWhitespace(var3)) {
                     sb.append("\n");
                  }
               }
            }

         }

         public void tail(@NotNull Node node, int depth) {
            Intrinsics.checkNotNullParameter(node, "node");
            if (node instanceof Element && ((Element)node).isBlock() && node.nextSibling() instanceof TextNode) {
               StringBuilder var3 = sb;
               Intrinsics.checkNotNullExpressionValue(var3, "sb");
               if (!JsoupExtensionsKt.lastCharIsWhitespace(var3)) {
                  sb.append("\n");
               }
            }

         }
      }), (Node)$this$textArray);
      String var3 = StringUtil.releaseBuilder(sb);
      Intrinsics.checkNotNullExpressionValue(var3, "releaseBuilder(sb)");
      int $i$f$trim = false;
      CharSequence $this$trim$iv$iv = (CharSequence)var3;
      int $i$f$trim = false;
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

      String text = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
      String[] var13 = new String[]{"\n"};
      return StringExtensionsKt.splitNotBlank(text, var13);
   }

   private static final void appendNormalisedText(StringBuilder sb, TextNode textNode) {
      String text = textNode.getWholeText();
      if (!preserveWhitespace(textNode.parentNode()) && !(textNode instanceof CDataNode)) {
         StringUtil.appendNormalisedWhitespace(sb, text, lastCharIsWhitespace(sb));
      } else {
         sb.append(text);
      }

   }

   private static final boolean preserveWhitespace(Node node) {
      if (node instanceof Element) {
         Element el = (Element)node;
         int i = 0;

         do {
            if (el.tag().preserveWhitespace()) {
               return true;
            }

            el = el.parent();
            ++i;
         } while(i < 6 && el != null);
      }

      return false;
   }

   private static final boolean lastCharIsWhitespace(StringBuilder sb) {
      CharSequence var1 = (CharSequence)sb;
      boolean var2 = false;
      return var1.length() > 0 && sb.charAt(sb.length() - 1) == ' ';
   }
}
