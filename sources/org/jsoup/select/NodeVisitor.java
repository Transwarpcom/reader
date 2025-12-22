package org.jsoup.select;

import org.jsoup.nodes.Node;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/NodeVisitor.class */
public interface NodeVisitor {
    void head(Node node, int i);

    void tail(Node node, int i);
}
