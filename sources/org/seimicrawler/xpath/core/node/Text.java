package org.seimicrawler.xpath.core.node;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import me.ag2s.epublib.epub.NCXDocumentV2;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.seimicrawler.xpath.core.Constants;
import org.seimicrawler.xpath.core.NodeTest;
import org.seimicrawler.xpath.core.Scope;
import org.seimicrawler.xpath.core.XValue;
import org.seimicrawler.xpath.util.CommonUtil;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/node/Text.class */
public class Text implements NodeTest {
    @Override // org.seimicrawler.xpath.core.NodeTest
    public String name() {
        return NCXDocumentV2.NCXTags.text;
    }

    @Override // org.seimicrawler.xpath.core.NodeTest
    public XValue call(Scope scope) {
        Elements context = scope.context();
        final Elements res = new Elements();
        if (context != null && context.size() > 0) {
            if (scope.isRecursion()) {
                Iterator<Element> it = context.iterator();
                while (it.hasNext()) {
                    Element e = it.next();
                    final Map<String, Integer> indexMap = new HashMap<>();
                    NodeTraversor.traverse(new NodeVisitor() { // from class: org.seimicrawler.xpath.core.node.Text.1
                        @Override // org.jsoup.select.NodeVisitor
                        public void head(org.jsoup.nodes.Node node, int depth) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
                            Integer index;
                            if (node instanceof TextNode) {
                                TextNode textNode = (TextNode) node;
                                String key = depth + "_" + textNode.parent().hashCode();
                                Integer index2 = (Integer) indexMap.get(key);
                                if (index2 == null) {
                                    index = 1;
                                    indexMap.put(key, 1);
                                } else {
                                    index = Integer.valueOf(index2.intValue() + 1);
                                    indexMap.put(key, index);
                                }
                                Element data = new Element(Constants.DEF_TEXT_TAG_NAME);
                                data.text(textNode.getWholeText());
                                try {
                                    Method parent = org.jsoup.nodes.Node.class.getDeclaredMethod("setParentNode", org.jsoup.nodes.Node.class);
                                    parent.setAccessible(true);
                                    parent.invoke(data, textNode.parent());
                                } catch (Exception e2) {
                                }
                                CommonUtil.setSameTagIndexInSiblings(data, index.intValue());
                                res.add(data);
                            }
                        }

                        @Override // org.jsoup.select.NodeVisitor
                        public void tail(org.jsoup.nodes.Node node, int depth) {
                        }
                    }, e);
                }
            } else {
                Iterator<Element> it2 = context.iterator();
                while (it2.hasNext()) {
                    Element e2 = it2.next();
                    if ("script".equals(e2.nodeName())) {
                        Element data = new Element(Constants.DEF_TEXT_TAG_NAME);
                        data.text(e2.data());
                        CommonUtil.setSameTagIndexInSiblings(data, 1);
                        res.add(data);
                    } else {
                        List<TextNode> textNodes = e2.textNodes();
                        for (int i = 0; i < textNodes.size(); i++) {
                            TextNode textNode = textNodes.get(i);
                            Element data2 = new Element(Constants.DEF_TEXT_TAG_NAME);
                            data2.text(textNode.getWholeText());
                            CommonUtil.setSameTagIndexInSiblings(data2, i + 1);
                            res.add(data2);
                        }
                    }
                }
            }
        }
        return XValue.create(res);
    }
}
