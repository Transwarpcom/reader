package io.netty.util.internal.svm;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.RecomputeFieldValue;
import com.oracle.svm.core.annotate.TargetClass;

@TargetClass(className = "io.netty.util.internal.PlatformDependent")
/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/svm/PlatformDependentSubstitution.class */
final class PlatformDependentSubstitution {

    @RecomputeFieldValue(kind = RecomputeFieldValue.Kind.ArrayBaseOffset, declClass = byte[].class)
    @Alias
    private static long BYTE_ARRAY_BASE_OFFSET;

    private PlatformDependentSubstitution() {
    }
}
