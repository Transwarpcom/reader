package org.springframework.boot.web.servlet.filter;

import javax.servlet.Filter;
import org.springframework.core.Ordered;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/servlet/filter/OrderedFilter.class */
public interface OrderedFilter extends Filter, Ordered {
    public static final int REQUEST_WRAPPER_FILTER_MAX_ORDER = 0;
}
