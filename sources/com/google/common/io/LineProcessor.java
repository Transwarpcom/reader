package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;

@Beta
@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/io/LineProcessor.class */
public interface LineProcessor<T> {
    @CanIgnoreReturnValue
    boolean processLine(String str) throws IOException;

    T getResult();
}
