package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/CommonMatcher.class */
abstract class CommonMatcher {
    public abstract boolean matches();

    public abstract boolean find();

    public abstract boolean find(int i);

    public abstract String replaceAll(String str);

    public abstract int end();

    public abstract int start();

    CommonMatcher() {
    }
}
