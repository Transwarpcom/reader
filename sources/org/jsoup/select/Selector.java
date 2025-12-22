package org.jsoup.select;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import javax.annotation.Nullable;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Element;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/Selector.class */
public class Selector {
    private Selector() {
    }

    public static Elements select(String query, Element root) {
        Validate.notEmpty(query);
        return select(QueryParser.parse(query), root);
    }

    public static Elements select(Evaluator evaluator, Element root) {
        Validate.notNull(evaluator);
        Validate.notNull(root);
        return Collector.collect(evaluator, root);
    }

    public static Elements select(String query, Iterable<Element> roots) {
        Validate.notEmpty(query);
        Validate.notNull(roots);
        Evaluator evaluator = QueryParser.parse(query);
        Elements elements = new Elements();
        IdentityHashMap<Element, Boolean> seenElements = new IdentityHashMap<>();
        for (Element root : roots) {
            Elements found = select(evaluator, root);
            Iterator<Element> it = found.iterator();
            while (it.hasNext()) {
                Element el = it.next();
                if (seenElements.put(el, Boolean.TRUE) == null) {
                    elements.add(el);
                }
            }
        }
        return elements;
    }

    static Elements filterOut(Collection<Element> elements, Collection<Element> outs) {
        Elements output = new Elements();
        for (Element el : elements) {
            boolean found = false;
            Iterator<Element> it = outs.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Element out = it.next();
                if (el.equals(out)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                output.add(el);
            }
        }
        return output;
    }

    @Nullable
    public static Element selectFirst(String cssQuery, Element root) {
        Validate.notEmpty(cssQuery);
        return Collector.findFirst(QueryParser.parse(cssQuery), root);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/Selector$SelectorParseException.class */
    public static class SelectorParseException extends IllegalStateException {
        public SelectorParseException(String msg, Object... params) {
            super(String.format(msg, params));
        }
    }
}
