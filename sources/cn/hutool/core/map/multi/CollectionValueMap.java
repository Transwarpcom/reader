package cn.hutool.core.map.multi;

import cn.hutool.core.lang.func.Func0;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/map/multi/CollectionValueMap.class */
public class CollectionValueMap<K, V> extends AbsCollValueMap<K, V, Collection<V>> {
    private static final long serialVersionUID = 9012989578038102983L;
    private final Func0<Collection<V>> collectionCreateFunc;

    private static /* synthetic */ Object $deserializeLambda$(SerializedLambda lambda) {
        switch (lambda.getImplMethodName()) {
            case "<init>":
                if (lambda.getImplMethodKind() == 8 && lambda.getFunctionalInterfaceClass().equals("cn/hutool/core/lang/func/Func0") && lambda.getFunctionalInterfaceMethodName().equals("call") && lambda.getFunctionalInterfaceMethodSignature().equals("()Ljava/lang/Object;") && lambda.getImplClass().equals("java/util/ArrayList") && lambda.getImplMethodSignature().equals("()V")) {
                    return ArrayList::new;
                }
                if (lambda.getImplMethodKind() == 8 && lambda.getFunctionalInterfaceClass().equals("cn/hutool/core/lang/func/Func0") && lambda.getFunctionalInterfaceMethodName().equals("call") && lambda.getFunctionalInterfaceMethodSignature().equals("()Ljava/lang/Object;") && lambda.getImplClass().equals("java/util/ArrayList") && lambda.getImplMethodSignature().equals("()V")) {
                    return ArrayList::new;
                }
                break;
        }
        throw new IllegalArgumentException("Invalid lambda deserialization");
    }

    public CollectionValueMap() {
        this(16);
    }

    public CollectionValueMap(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public CollectionValueMap(Map<? extends K, ? extends Collection<V>> m) {
        this(0.75f, m);
    }

    public CollectionValueMap(float loadFactor, Map<? extends K, ? extends Collection<V>> m) {
        this(loadFactor, m, ArrayList::new);
    }

    public CollectionValueMap(int initialCapacity, float loadFactor) {
        this(initialCapacity, loadFactor, ArrayList::new);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CollectionValueMap(float loadFactor, Map<? extends K, ? extends Collection<V>> map, Func0<Collection<V>> collectionCreateFunc) {
        this(map.size(), loadFactor, collectionCreateFunc);
        putAll(map);
    }

    public CollectionValueMap(int initialCapacity, float loadFactor, Func0<Collection<V>> collectionCreateFunc) {
        super(new HashMap(initialCapacity, loadFactor));
        this.collectionCreateFunc = collectionCreateFunc;
    }

    @Override // cn.hutool.core.map.multi.AbsCollValueMap
    protected Collection<V> createCollection() {
        return this.collectionCreateFunc.callWithRuntimeException();
    }
}
