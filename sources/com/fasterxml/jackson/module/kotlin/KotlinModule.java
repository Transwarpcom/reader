package com.fasterxml.jackson.module.kotlin;

import ch.qos.logback.core.CoreConstants;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.BitSet;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.CharRange;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.springframework.cglib.core.Constants;

/* compiled from: KotlinModule.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018�� \"2\u00020\u0001:\u0002!\"B\u001f\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007B'\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tB\u000f\b\u0012\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fBC\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016R\u0018\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u0012X\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n��\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u001c\u0010\u0015¨\u0006#"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinModule;", "Lcom/fasterxml/jackson/databind/module/SimpleModule;", "reflectionCacheSize", "", "nullToEmptyCollection", "", "nullToEmptyMap", "(IZZ)V", "nullIsSameAsDefault", "(IZZZ)V", "builder", "Lcom/fasterxml/jackson/module/kotlin/KotlinModule$Builder;", "(Lcom/fasterxml/jackson/module/kotlin/KotlinModule$Builder;)V", "singletonSupport", "Lcom/fasterxml/jackson/module/kotlin/SingletonSupport;", "strictNullChecks", "(IZZZLcom/fasterxml/jackson/module/kotlin/SingletonSupport;Z)V", "ignoredClassesForImplyingJsonCreator", "", "Lkotlin/reflect/KClass;", "getNullIsSameAsDefault", "()Z", "getNullToEmptyCollection", "getNullToEmptyMap", "getReflectionCacheSize", "()I", "getSingletonSupport", "()Lcom/fasterxml/jackson/module/kotlin/SingletonSupport;", "getStrictNullChecks", "setupModule", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Lcom/fasterxml/jackson/databind/Module$SetupContext;", "Builder", "Companion", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinModule.class */
public final class KotlinModule extends SimpleModule {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private final int reflectionCacheSize;
    private final boolean nullToEmptyCollection;
    private final boolean nullToEmptyMap;
    private final boolean nullIsSameAsDefault;

    @NotNull
    private final SingletonSupport singletonSupport;
    private final boolean strictNullChecks;

    @NotNull
    private final Set<KClass<?>> ignoredClassesForImplyingJsonCreator;
    public static final long serialVersionUID = 1;

    /* compiled from: KotlinModule.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    /* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinModule$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SingletonSupport.values().length];
            iArr[SingletonSupport.DISABLED.ordinal()] = 1;
            iArr[SingletonSupport.CANONICALIZE.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Deprecated(message = "Use KotlinModule.Builder instead of named constructor parameters.", replaceWith = @ReplaceWith(expression = "KotlinModule.Builder()\n            .withReflectionCacheSize(reflectionCacheSize)\n            .configure(KotlinFeature.NullToEmptyCollection, nullToEmptyCollection)\n            .configure(KotlinFeature.NullToEmptyMap, nullToEmptyMap)\n            .configure(KotlinFeature.NullIsSameAsDefault, nullIsSameAsDefault)\n            .configure(KotlinFeature.SingletonSupport, singletonSupport)\n            .configure(KotlinFeature.StrictNullChecks, strictNullChecks)\n            .build()", imports = {"com.fasterxml.jackson.module.kotlin.KotlinFeature"}), level = DeprecationLevel.WARNING)
    public KotlinModule() {
        this(0, false, false, false, null, false, 63, null);
    }

    public /* synthetic */ KotlinModule(Builder builder, DefaultConstructorMarker $constructor_marker) {
        this(builder);
    }

    public /* synthetic */ KotlinModule(int i, boolean z, boolean z2, boolean z3, SingletonSupport singletonSupport, boolean z4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 512 : i, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? false : z2, (i2 & 8) != 0 ? false : z3, (i2 & 16) != 0 ? SingletonSupport.DISABLED : singletonSupport, (i2 & 32) != 0 ? false : z4);
    }

    public final int getReflectionCacheSize() {
        return this.reflectionCacheSize;
    }

    public final boolean getNullToEmptyCollection() {
        return this.nullToEmptyCollection;
    }

    public final boolean getNullToEmptyMap() {
        return this.nullToEmptyMap;
    }

    public final boolean getNullIsSameAsDefault() {
        return this.nullIsSameAsDefault;
    }

    @NotNull
    public final SingletonSupport getSingletonSupport() {
        return this.singletonSupport;
    }

    public final boolean getStrictNullChecks() {
        return this.strictNullChecks;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "Use KotlinModule.Builder instead of named constructor parameters.", replaceWith = @ReplaceWith(expression = "KotlinModule.Builder()\n            .withReflectionCacheSize(reflectionCacheSize)\n            .configure(KotlinFeature.NullToEmptyCollection, nullToEmptyCollection)\n            .configure(KotlinFeature.NullToEmptyMap, nullToEmptyMap)\n            .configure(KotlinFeature.NullIsSameAsDefault, nullIsSameAsDefault)\n            .configure(KotlinFeature.SingletonSupport, singletonSupport)\n            .configure(KotlinFeature.StrictNullChecks, strictNullChecks)\n            .build()", imports = {"com.fasterxml.jackson.module.kotlin.KotlinFeature"}), level = DeprecationLevel.WARNING)
    public KotlinModule(int reflectionCacheSize, boolean nullToEmptyCollection, boolean nullToEmptyMap, boolean nullIsSameAsDefault, @NotNull SingletonSupport singletonSupport, boolean strictNullChecks) {
        super(KotlinModule.class.getName(), PackageVersion.VERSION);
        Intrinsics.checkNotNullParameter(singletonSupport, "singletonSupport");
        this.reflectionCacheSize = reflectionCacheSize;
        this.nullToEmptyCollection = nullToEmptyCollection;
        this.nullToEmptyMap = nullToEmptyMap;
        this.nullIsSameAsDefault = nullIsSameAsDefault;
        this.singletonSupport = singletonSupport;
        this.strictNullChecks = strictNullChecks;
        this.ignoredClassesForImplyingJsonCreator = SetsKt.emptySet();
    }

    @Deprecated(message = "For ABI compatibility", level = DeprecationLevel.HIDDEN)
    public /* synthetic */ KotlinModule(int reflectionCacheSize, boolean nullToEmptyCollection, boolean nullToEmptyMap) {
        this(new Builder().withReflectionCacheSize(reflectionCacheSize).configure(KotlinFeature.NullToEmptyCollection, nullToEmptyCollection).configure(KotlinFeature.NullToEmptyMap, nullToEmptyMap).disable(KotlinFeature.NullIsSameAsDefault));
    }

    @Deprecated(message = "For ABI compatibility", level = DeprecationLevel.HIDDEN)
    public /* synthetic */ KotlinModule(int reflectionCacheSize, boolean nullToEmptyCollection, boolean nullToEmptyMap, boolean nullIsSameAsDefault) {
        this(new Builder().withReflectionCacheSize(reflectionCacheSize).configure(KotlinFeature.NullToEmptyCollection, nullToEmptyCollection).configure(KotlinFeature.NullToEmptyMap, nullToEmptyMap).configure(KotlinFeature.NullIsSameAsDefault, nullIsSameAsDefault));
    }

    private KotlinModule(Builder builder) {
        this(builder.getReflectionCacheSize(), builder.isEnabled(KotlinFeature.NullToEmptyCollection), builder.isEnabled(KotlinFeature.NullToEmptyMap), builder.isEnabled(KotlinFeature.NullIsSameAsDefault), builder.isEnabled(KotlinFeature.SingletonSupport) ? SingletonSupport.CANONICALIZE : SingletonSupport.DISABLED, builder.isEnabled(KotlinFeature.StrictNullChecks));
    }

    /* compiled from: KotlinModule.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\t\n��\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��¨\u0006\u0005"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinModule$Companion;", "", "()V", Constants.SUID_FIELD_NAME, "", "jackson-module-kotlin"})
    /* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinModule$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // com.fasterxml.jackson.databind.module.SimpleModule, com.fasterxml.jackson.databind.Module
    public void setupModule(@NotNull Module.SetupContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.setupModule(context);
        if (!context.isEnabled(MapperFeature.USE_ANNOTATIONS)) {
            throw new IllegalStateException("The Jackson Kotlin module requires USE_ANNOTATIONS to be true or it cannot function");
        }
        ReflectionCache cache = new ReflectionCache(this.reflectionCacheSize);
        context.addValueInstantiators(new KotlinInstantiators(cache, this.nullToEmptyCollection, this.nullToEmptyMap, this.nullIsSameAsDefault, this.strictNullChecks));
        switch (WhenMappings.$EnumSwitchMapping$0[this.singletonSupport.ordinal()]) {
            case 2:
                context.addBeanDeserializerModifier(KotlinBeanDeserializerModifier.INSTANCE);
                break;
        }
        context.insertAnnotationIntrospector(new KotlinAnnotationIntrospector(context, cache, this.nullToEmptyCollection, this.nullToEmptyMap, this.nullIsSameAsDefault));
        context.appendAnnotationIntrospector(new KotlinNamesAnnotationIntrospector(this, cache, this.ignoredClassesForImplyingJsonCreator));
        context.addDeserializers(new KotlinDeserializers());
        context.addKeyDeserializers(KotlinKeyDeserializers.INSTANCE);
        context.addSerializers(new KotlinSerializers());
        context.addKeySerializers(new KotlinKeySerializers());
        context.setMixInAnnotations(IntRange.class, ClosedRangeMixin.class);
        context.setMixInAnnotations(CharRange.class, ClosedRangeMixin.class);
        context.setMixInAnnotations(LongRange.class, ClosedRangeMixin.class);
        context.setMixInAnnotations(ClosedRange.class, ClosedRangeMixin.class);
    }

    /* compiled from: KotlinModule.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020��2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020��2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0012\u001a\u00020��2\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u0013\u001a\u00020\u0010H\u0007J\b\u0010\u0014\u001a\u00020\u0010H\u0007J\b\u0010\u0015\u001a\u00020\u0010H\u0007J\b\u0010\u0016\u001a\u00020\u0017H\u0007J\b\u0010\u0018\u001a\u00020\u0010H\u0007J\u000e\u0010\u0019\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u001a\u001a\u00020��2\u0006\u0010\u001a\u001a\u00020\u0010H\u0007J\u0010\u0010\u001b\u001a\u00020��2\u0006\u0010\u001b\u001a\u00020\u0010H\u0007J\u0010\u0010\u001c\u001a\u00020��2\u0006\u0010\u001c\u001a\u00020\u0010H\u0007J\u0010\u0010\u0007\u001a\u00020��2\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\u0010\u0010\u001d\u001a\u00020��2\u0006\u0010\u001d\u001a\u00020\u0017H\u0007J\u0010\u0010\u001e\u001a\u00020��2\u0006\u0010\u001e\u001a\u00020\u0010H\u0007J\u000e\u0010\u001f\u001a\u00020��2\u0006\u0010\u0007\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\b\u0010\t¨\u0006 "}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinModule$Builder;", "", "()V", "bitSet", "Ljava/util/BitSet;", "<set-?>", "", "reflectionCacheSize", "getReflectionCacheSize", "()I", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/fasterxml/jackson/module/kotlin/KotlinModule;", "configure", "feature", "Lcom/fasterxml/jackson/module/kotlin/KotlinFeature;", "enabled", "", "disable", "enable", "getNullIsSameAsDefault", "getNullToEmptyCollection", "getNullToEmptyMap", "getSingletonSupport", "Lcom/fasterxml/jackson/module/kotlin/SingletonSupport;", "getStrictNullChecks", "isEnabled", "nullIsSameAsDefault", "nullToEmptyCollection", "nullToEmptyMap", "singletonSupport", "strictNullChecks", "withReflectionCacheSize", "jackson-module-kotlin"})
    /* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinModule$Builder.class */
    public static final class Builder {
        private int reflectionCacheSize = 512;

        @NotNull
        private final BitSet bitSet = KotlinFeature.Companion.getDefaults$jackson_module_kotlin();

        /* compiled from: KotlinModule.kt */
        @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
        /* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinModule$Builder$WhenMappings.class */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[SingletonSupport.values().length];
                iArr[SingletonSupport.CANONICALIZE.ordinal()] = 1;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public final int getReflectionCacheSize() {
            return this.reflectionCacheSize;
        }

        @NotNull
        public final Builder withReflectionCacheSize(int reflectionCacheSize) {
            Builder $this$withReflectionCacheSize_u24lambda_u2d0 = this;
            $this$withReflectionCacheSize_u24lambda_u2d0.reflectionCacheSize = reflectionCacheSize;
            return this;
        }

        @NotNull
        public final Builder enable(@NotNull KotlinFeature feature) {
            Intrinsics.checkNotNullParameter(feature, "feature");
            Builder $this$enable_u24lambda_u2d1 = this;
            $this$enable_u24lambda_u2d1.bitSet.or(feature.getBitSet$jackson_module_kotlin());
            return this;
        }

        @NotNull
        public final Builder disable(@NotNull KotlinFeature feature) {
            Intrinsics.checkNotNullParameter(feature, "feature");
            Builder $this$disable_u24lambda_u2d2 = this;
            $this$disable_u24lambda_u2d2.bitSet.andNot(feature.getBitSet$jackson_module_kotlin());
            return this;
        }

        @NotNull
        public final Builder configure(@NotNull KotlinFeature feature, boolean enabled) {
            Intrinsics.checkNotNullParameter(feature, "feature");
            return enabled ? enable(feature) : disable(feature);
        }

        public final boolean isEnabled(@NotNull KotlinFeature feature) {
            Intrinsics.checkNotNullParameter(feature, "feature");
            return this.bitSet.intersects(feature.getBitSet$jackson_module_kotlin());
        }

        @Deprecated(message = "Deprecated, use withReflectionCacheSize(reflectionCacheSize) instead.", replaceWith = @ReplaceWith(expression = "withReflectionCacheSize(reflectionCacheSize)", imports = {}))
        @NotNull
        public final Builder reflectionCacheSize(int reflectionCacheSize) {
            return withReflectionCacheSize(reflectionCacheSize);
        }

        @Deprecated(message = "Deprecated, use isEnabled(NullToEmptyCollection) instead.", replaceWith = @ReplaceWith(expression = "isEnabled(KotlinFeature.NullToEmptyCollection)", imports = {"com.fasterxml.jackson.module.kotlin.KotlinFeature"}))
        public final boolean getNullToEmptyCollection() {
            return isEnabled(KotlinFeature.NullToEmptyCollection);
        }

        @Deprecated(message = "Deprecated, use configure(NullToEmptyCollection, enabled) instead.", replaceWith = @ReplaceWith(expression = "configure(KotlinFeature.NullToEmptyCollection, nullToEmptyCollection)", imports = {"com.fasterxml.jackson.module.kotlin.KotlinFeature"}))
        @NotNull
        public final Builder nullToEmptyCollection(boolean nullToEmptyCollection) {
            return configure(KotlinFeature.NullToEmptyCollection, nullToEmptyCollection);
        }

        @Deprecated(message = "Deprecated, use isEnabled(NullToEmptyMap) instead.", replaceWith = @ReplaceWith(expression = "isEnabled(KotlinFeature.NullToEmptyMap)", imports = {"com.fasterxml.jackson.module.kotlin.KotlinFeature"}))
        public final boolean getNullToEmptyMap() {
            return isEnabled(KotlinFeature.NullToEmptyMap);
        }

        @Deprecated(message = "Deprecated, use configure(NullToEmptyMap, enabled) instead.", replaceWith = @ReplaceWith(expression = "configure(KotlinFeature.NullToEmptyMap, nullToEmptyMap)", imports = {"com.fasterxml.jackson.module.kotlin.KotlinFeature"}))
        @NotNull
        public final Builder nullToEmptyMap(boolean nullToEmptyMap) {
            return configure(KotlinFeature.NullToEmptyMap, nullToEmptyMap);
        }

        @Deprecated(message = "Deprecated, use isEnabled(NullIsSameAsDefault) instead.", replaceWith = @ReplaceWith(expression = "isEnabled(KotlinFeature.NullIsSameAsDefault)", imports = {"com.fasterxml.jackson.module.kotlin.KotlinFeature"}))
        public final boolean getNullIsSameAsDefault() {
            return isEnabled(KotlinFeature.NullIsSameAsDefault);
        }

        @Deprecated(message = "Deprecated, use configure(NullIsSameAsDefault, enabled) instead.", replaceWith = @ReplaceWith(expression = "configure(KotlinFeature.NullIsSameAsDefault, nullIsSameAsDefault)", imports = {"com.fasterxml.jackson.module.kotlin.KotlinFeature"}))
        @NotNull
        public final Builder nullIsSameAsDefault(boolean nullIsSameAsDefault) {
            return configure(KotlinFeature.NullIsSameAsDefault, nullIsSameAsDefault);
        }

        @Deprecated(message = "Deprecated, use isEnabled(SingletonSupport) instead.", replaceWith = @ReplaceWith(expression = "isEnabled(KotlinFeature.SingletonSupport)", imports = {"com.fasterxml.jackson.module.kotlin.KotlinFeature"}))
        @NotNull
        public final SingletonSupport getSingletonSupport() {
            return isEnabled(KotlinFeature.SingletonSupport) ? SingletonSupport.CANONICALIZE : SingletonSupport.DISABLED;
        }

        @Deprecated(message = "Deprecated, use configure(SingletonSupport, enabled) instead.", replaceWith = @ReplaceWith(expression = "configure(KotlinFeature.SingletonSupport, singletonSupport)", imports = {"com.fasterxml.jackson.module.kotlin.KotlinFeature"}))
        @NotNull
        public final Builder singletonSupport(@NotNull SingletonSupport singletonSupport) {
            Intrinsics.checkNotNullParameter(singletonSupport, "singletonSupport");
            return WhenMappings.$EnumSwitchMapping$0[singletonSupport.ordinal()] == 1 ? enable(KotlinFeature.SingletonSupport) : disable(KotlinFeature.SingletonSupport);
        }

        @Deprecated(message = "Deprecated, use isEnabled(StrictNullChecks) instead.", replaceWith = @ReplaceWith(expression = "isEnabled(KotlinFeature.StrictNullChecks)", imports = {"com.fasterxml.jackson.module.kotlin.KotlinFeature"}))
        public final boolean getStrictNullChecks() {
            return isEnabled(KotlinFeature.StrictNullChecks);
        }

        @Deprecated(message = "Deprecated, use configure(StrictNullChecks, enabled) instead.", replaceWith = @ReplaceWith(expression = "configure(KotlinFeature.StrictNullChecks, strictNullChecks)", imports = {"com.fasterxml.jackson.module.kotlin.KotlinFeature"}))
        @NotNull
        public final Builder strictNullChecks(boolean strictNullChecks) {
            return configure(KotlinFeature.StrictNullChecks, strictNullChecks);
        }

        @NotNull
        public final KotlinModule build() {
            return new KotlinModule(this, null);
        }
    }
}
