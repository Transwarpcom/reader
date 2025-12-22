package org.springframework.boot.web.servlet.filter;

import org.springframework.web.filter.HiddenHttpMethodFilter;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/servlet/filter/OrderedHiddenHttpMethodFilter.class */
public class OrderedHiddenHttpMethodFilter extends HiddenHttpMethodFilter implements OrderedFilter {
    public static final int DEFAULT_ORDER = -10000;
    private int order = -10000;

    @Override // org.springframework.core.Ordered
    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
