package cn.hutool.core.lang.intern;

import cn.hutool.core.lang.SimpleCache;
import java.lang.invoke.SerializedLambda;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/intern/WeakInterner.class */
public class WeakInterner<T> implements Interner<T> {
    private final SimpleCache<T, T> cache = new SimpleCache<>();

    private static /* synthetic */ Object $deserializeLambda$(SerializedLambda lambda) {
        switch (lambda.getImplMethodName()) {
            case "lambda$intern$cf951c4b$1":
                if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("cn/hutool/core/lang/func/Func0") && lambda.getFunctionalInterfaceMethodName().equals("call") && lambda.getFunctionalInterfaceMethodSignature().equals("()Ljava/lang/Object;") && lambda.getImplClass().equals("cn/hutool/core/lang/intern/WeakInterner") && lambda.getImplMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;")) {
                    Object capturedArg = lambda.getCapturedArg(0);
                    return () -> {
                        return capturedArg;
                    };
                }
                break;
        }
        throw new IllegalArgumentException("Invalid lambda deserialization");
    }

    @Override // cn.hutool.core.lang.intern.Interner
    public T intern(T sample) {
        if (null == sample) {
            return null;
        }
        return this.cache.get(sample, () -> {
            return sample;
        });
    }
}
