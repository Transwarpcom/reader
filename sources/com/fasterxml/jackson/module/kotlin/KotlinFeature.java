package com.fasterxml.jackson.module.kotlin;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinFeature.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0001\u0018�� \u000e2\b\u0012\u0004\u0012\u00020��0\u0001:\u0001\u000eB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinFeature;", "", "enabledByDefault", "", "(Ljava/lang/String;IZ)V", "bitSet", "Ljava/util/BitSet;", "getBitSet$jackson_module_kotlin", "()Ljava/util/BitSet;", "NullToEmptyCollection", "NullToEmptyMap", "NullIsSameAsDefault", "SingletonSupport", "StrictNullChecks", "Companion", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinFeature.class */
public enum KotlinFeature {
    NullToEmptyCollection(false),
    NullToEmptyMap(false),
    NullIsSameAsDefault(false),
    SingletonSupport(false),
    StrictNullChecks(false);


    @NotNull
    public static final Companion Companion = new Companion(null);
    private final boolean enabledByDefault;

    @NotNull
    private final BitSet bitSet = ExtensionsKt.toBitSet((int) Math.pow(2.0d, ordinal()));

    KotlinFeature(boolean enabledByDefault) {
        this.enabledByDefault = enabledByDefault;
    }

    @NotNull
    public final BitSet getBitSet$jackson_module_kotlin() {
        return this.bitSet;
    }

    /* compiled from: KotlinFeature.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinFeature$Companion;", "", "()V", "defaults", "Ljava/util/BitSet;", "getDefaults$jackson_module_kotlin", "()Ljava/util/BitSet;", "jackson-module-kotlin"})
    /* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinFeature$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final BitSet getDefaults$jackson_module_kotlin() {
            BitSet $this$_get_defaults__u24lambda_u2d2 = ExtensionsKt.toBitSet(0);
            KotlinFeature[] kotlinFeatureArrValues = KotlinFeature.values();
            Collection destination$iv$iv = new ArrayList();
            for (KotlinFeature kotlinFeature : kotlinFeatureArrValues) {
                if (kotlinFeature.enabledByDefault) {
                    destination$iv$iv.add(kotlinFeature);
                }
            }
            Iterable $this$forEach$iv = (List) destination$iv$iv;
            for (Object element$iv : $this$forEach$iv) {
                KotlinFeature it = (KotlinFeature) element$iv;
                $this$_get_defaults__u24lambda_u2d2.or(it.getBitSet$jackson_module_kotlin());
            }
            return $this$_get_defaults__u24lambda_u2d2;
        }
    }
}
