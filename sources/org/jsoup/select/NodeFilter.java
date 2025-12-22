package org.jsoup.select;

import org.jsoup.nodes.Node;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/NodeFilter.class */
public interface NodeFilter {

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/NodeFilter$FilterResult.class */
    public enum FilterResult {
        CONTINUE,
        SKIP_CHILDREN,
        SKIP_ENTIRELY,
        REMOVE,
        STOP
    }

    FilterResult head(Node node, int i);

    FilterResult tail(Node node, int i);
}
