package io.legado.app.utils;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00042\u0006\u0010\t\u001a\u00020\u0001¨\u0006\n"},
   d2 = {"Lio/legado/app/utils/XmlUtils;", "", "()V", "parseNode", "", "", "list", "Lorg/w3c/dom/NodeList;", "xml2map", "source", "reader-pro"}
)
public final class XmlUtils {
   @NotNull
   public static final XmlUtils INSTANCE = new XmlUtils();

   private XmlUtils() {
   }

   @NotNull
   public final Map<String, Object> xml2map(@NotNull Object source) {
      Intrinsics.checkNotNullParameter(source, "source");
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      boolean var4 = false;
      Map doc = (Map)(new LinkedHashMap());

      try {
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document document;
         NodeList var6;
         if (source instanceof String) {
            document = builder.parse((String)source);
            var6 = document.getChildNodes();
            Intrinsics.checkNotNullExpressionValue(var6, "document.getChildNodes()");
            return this.parseNode(var6);
         } else if (source instanceof InputStream) {
            document = builder.parse((InputStream)source);
            var6 = document.getChildNodes();
            Intrinsics.checkNotNullExpressionValue(var6, "document.getChildNodes()");
            return this.parseNode(var6);
         } else if (source instanceof InputSource) {
            document = builder.parse((InputSource)source);
            var6 = document.getChildNodes();
            Intrinsics.checkNotNullExpressionValue(var6, "document.getChildNodes()");
            return this.parseNode(var6);
         } else {
            return doc;
         }
      } catch (Exception var7) {
         var7.printStackTrace();
         return doc;
      }
   }

   @NotNull
   public final Map<String, Object> parseNode(@NotNull NodeList list) {
      Intrinsics.checkNotNullParameter(list, "list");
      boolean var3 = false;
      Map doc = (Map)(new LinkedHashMap());
      int var9 = 0;
      int var4 = list.getLength();
      if (var9 < var4) {
         do {
            int i = var9++;
            Node node = list.item(i);
            if (node.getNodeType() == 1) {
               NodeList childNodes = node.getChildNodes();
               String var8;
               if (childNodes.getLength() == 1 && node.getFirstChild().getNodeType() == 3) {
                  var8 = node.getNodeName();
                  Intrinsics.checkNotNullExpressionValue(var8, "node.getNodeName()");
                  String var10001 = var8;
                  var8 = node.getFirstChild().getNodeValue();
                  Intrinsics.checkNotNullExpressionValue(var8, "node.getFirstChild().getNodeValue()");
                  doc.put(var10001, var8);
               } else if (childNodes.getLength() > 1) {
                  var8 = node.getNodeName();
                  Intrinsics.checkNotNullExpressionValue(var8, "node.getNodeName()");
                  Intrinsics.checkNotNullExpressionValue(childNodes, "childNodes");
                  doc.put(var8, this.parseNode(childNodes));
               }
            }
         } while(var9 < var4);
      }

      return doc;
   }
}
