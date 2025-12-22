package org.seimicrawler.xpath.util;

import java.util.Iterator;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.core.Constants;
import org.seimicrawler.xpath.core.Scope;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/util/CommonUtil.class */
public class CommonUtil {
    public static int getElIndexInSameTags(Element e, Scope scope) {
        Elements chs = e.parent().children();
        int index = 1;
        Iterator<Element> it = chs.iterator();
        while (it.hasNext()) {
            Element cur = it.next();
            if (e.tagName().equals(cur.tagName()) && scope.context().contains(cur)) {
                if (e.equals(cur)) {
                    break;
                }
                index++;
            }
        }
        return index;
    }

    public static int sameTagElNums(Element e, Scope scope) {
        Elements context = new Elements();
        Elements els = e.parent().getElementsByTag(e.tagName());
        Iterator<Element> it = els.iterator();
        while (it.hasNext()) {
            Element el = it.next();
            if (scope.context().contains(el)) {
                context.add(el);
            }
        }
        return context.size();
    }

    public static int getIndexInContext(Scope scope, Element el) {
        for (int i = 0; i < scope.context().size(); i++) {
            Element tmp = scope.context().get(i);
            if (Objects.equals(tmp, el)) {
                return i + 1;
            }
        }
        return Integer.MIN_VALUE;
    }

    public static Elements followingSibling(Element el) {
        Elements rs = new Elements();
        Element elementNextElementSibling = el.nextElementSibling();
        while (true) {
            Element tmp = elementNextElementSibling;
            if (tmp == null) {
                break;
            }
            rs.add(tmp);
            elementNextElementSibling = tmp.nextElementSibling();
        }
        if (rs.size() > 0) {
            return rs;
        }
        return null;
    }

    public static Elements precedingSibling(Element el) {
        Elements rs = new Elements();
        Element elementPreviousElementSibling = el.previousElementSibling();
        while (true) {
            Element tmp = elementPreviousElementSibling;
            if (tmp == null) {
                break;
            }
            rs.add(tmp);
            elementPreviousElementSibling = tmp.previousElementSibling();
        }
        if (rs.size() > 0) {
            return rs;
        }
        return null;
    }

    public static void setSameTagIndexInSiblings(Element ori, int index) {
        if (ori == null) {
            return;
        }
        ori.attr(Constants.EL_SAME_TAG_INDEX_KEY, String.valueOf(index));
    }

    public static int getJxSameTagIndexInSiblings(Element ori) {
        String val = ori.attr(Constants.EL_SAME_TAG_INDEX_KEY);
        if (StringUtils.isBlank(val)) {
            return -1;
        }
        return Integer.parseInt(val);
    }
}
