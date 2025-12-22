package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;

@Beta
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/reflect/TypeToInstanceMap.class */
public interface TypeToInstanceMap<B> extends Map<TypeToken<? extends B>, B> {
    <T extends B> T getInstance(Class<T> cls);

    <T extends B> T getInstance(TypeToken<T> typeToken);

    @CanIgnoreReturnValue
    <T extends B> T putInstance(Class<T> cls, T t);

    @CanIgnoreReturnValue
    <T extends B> T putInstance(TypeToken<T> typeToken, T t);
}
