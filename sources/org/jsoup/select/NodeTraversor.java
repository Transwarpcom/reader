package org.jsoup.select;

import java.util.Iterator;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/NodeTraversor.class */
public class NodeTraversor {
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !NodeTraversor.class.desiredAssertionStatus();
    }

    public static void traverse(NodeVisitor visitor, Node root) {
        Node node = root;
        int depth = 0;
        while (node != null) {
            Node parent = node.parentNode();
            visitor.head(node, depth);
            if (parent != null && !node.hasParent()) {
                node = parent.childNode(node.siblingIndex());
            }
            if (node.childNodeSize() > 0) {
                node = node.childNode(0);
                depth++;
            } else {
                while (true) {
                    if (!$assertionsDisabled && node == null) {
                        throw new AssertionError();
                    }
                    if (node.nextSibling() != null || depth <= 0) {
                        break;
                    }
                    visitor.tail(node, depth);
                    node = node.parentNode();
                    depth--;
                }
                visitor.tail(node, depth);
                if (node != root) {
                    node = node.nextSibling();
                } else {
                    return;
                }
            }
        }
    }

    public static void traverse(NodeVisitor visitor, Elements elements) {
        Validate.notNull(visitor);
        Validate.notNull(elements);
        Iterator<Element> it = elements.iterator();
        while (it.hasNext()) {
            Element el = it.next();
            traverse(visitor, el);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a2, code lost:
    
        if (r8 == org.jsoup.select.NodeFilter.FilterResult.CONTINUE) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00aa, code lost:
    
        if (r8 != org.jsoup.select.NodeFilter.FilterResult.SKIP_CHILDREN) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00ad, code lost:
    
        r8 = r4.tail(r6, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00bc, code lost:
    
        if (r8 != org.jsoup.select.NodeFilter.FilterResult.STOP) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00c1, code lost:
    
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00c4, code lost:
    
        if (r6 != r5) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00c9, code lost:
    
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00ca, code lost:
    
        r0 = r6;
        r6 = r6.nextSibling();
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00d7, code lost:
    
        if (r8 != org.jsoup.select.NodeFilter.FilterResult.REMOVE) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00da, code lost:
    
        r0.remove();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.jsoup.select.NodeFilter.FilterResult filter(org.jsoup.select.NodeFilter r4, org.jsoup.nodes.Node r5) {
        /*
            Method dump skipped, instructions count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.select.NodeTraversor.filter(org.jsoup.select.NodeFilter, org.jsoup.nodes.Node):org.jsoup.select.NodeFilter$FilterResult");
    }

    public static void filter(NodeFilter filter, Elements elements) {
        Validate.notNull(filter);
        Validate.notNull(elements);
        Iterator<Element> it = elements.iterator();
        while (it.hasNext()) {
            Element el = it.next();
            if (filter(filter, el) == NodeFilter.FilterResult.STOP) {
                return;
            }
        }
    }
}
