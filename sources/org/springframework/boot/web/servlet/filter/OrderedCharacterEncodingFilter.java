package org.springframework.boot.web.servlet.filter;

import org.springframework.web.filter.CharacterEncodingFilter;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/servlet/filter/OrderedCharacterEncodingFilter.class */
public class OrderedCharacterEncodingFilter extends CharacterEncodingFilter implements OrderedFilter {
    private int order = Integer.MIN_VALUE;

    @Override // org.springframework.core.Ordered
    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
