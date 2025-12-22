package org.jsoup.select;

import javax.annotation.Nullable;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/Collector.class */
public class Collector {
    private Collector() {
    }

    public static Elements collect(Evaluator eval, Element root) {
        Elements elements = new Elements();
        NodeTraversor.traverse(new Accumulator(root, elements, eval), root);
        return elements;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/Collector$Accumulator.class */
    private static class Accumulator implements NodeVisitor {
        private final Element root;
        private final Elements elements;
        private final Evaluator eval;

        Accumulator(Element root, Elements elements, Evaluator eval) {
            this.root = root;
            this.elements = elements;
            this.eval = eval;
        }

        @Override // org.jsoup.select.NodeVisitor
        public void head(Node node, int depth) {
            if (node instanceof Element) {
                Element el = (Element) node;
                if (this.eval.matches(this.root, el)) {
                    this.elements.add(el);
                }
            }
        }

        @Override // org.jsoup.select.NodeVisitor
        public void tail(Node node, int depth) {
        }
    }

    @Nullable
    public static Element findFirst(Evaluator eval, Element root) {
        FirstFinder finder = new FirstFinder(root, eval);
        NodeTraversor.filter(finder, root);
        return finder.match;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/Collector$FirstFinder.class */
    private static class FirstFinder implements NodeFilter {
        private final Element root;

        @Nullable
        private Element match = null;
        private final Evaluator eval;

        FirstFinder(Element root, Evaluator eval) {
            this.root = root;
            this.eval = eval;
        }

        @Override // org.jsoup.select.NodeFilter
        public NodeFilter.FilterResult head(Node node, int depth) {
            if (node instanceof Element) {
                Element el = (Element) node;
                if (this.eval.matches(this.root, el)) {
                    this.match = el;
                    return NodeFilter.FilterResult.STOP;
                }
            }
            return NodeFilter.FilterResult.CONTINUE;
        }

        @Override // org.jsoup.select.NodeFilter
        public NodeFilter.FilterResult tail(Node node, int depth) {
            return NodeFilter.FilterResult.CONTINUE;
        }
    }
}
