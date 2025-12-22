package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/PatternCompiler.class */
interface PatternCompiler {
    CommonPattern compile(String str);

    boolean isPcreLike();
}
