package cn.hutool.core.lang;

import cn.hutool.core.util.TypeUtil;
import java.lang.reflect.Type;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/TypeReference.class */
public abstract class TypeReference<T> implements Type {
    private final Type type = TypeUtil.getTypeArgument(getClass());

    public Type getType() {
        return this.type;
    }

    public String toString() {
        return this.type.toString();
    }
}
