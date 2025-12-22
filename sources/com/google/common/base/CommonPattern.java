package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/CommonPattern.class */
abstract class CommonPattern {
    public abstract CommonMatcher matcher(CharSequence charSequence);

    public abstract String pattern();

    public abstract int flags();

    public abstract String toString();

    CommonPattern() {
    }

    public static CommonPattern compile(String pattern) {
        return Platform.compilePattern(pattern);
    }

    public static boolean isPcreLike() {
        return Platform.patternCompilerIsPcreLike();
    }
}
