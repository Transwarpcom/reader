package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReflectKotlinClass.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/components/ReflectKotlinClass.class */
public final class ReflectKotlinClass implements KotlinJvmBinaryClass {

    @NotNull
    public static final Factory Factory = new Factory(null);

    @NotNull
    private final Class<?> klass;

    @NotNull
    private final KotlinClassHeader classHeader;

    public /* synthetic */ ReflectKotlinClass(Class klass, KotlinClassHeader classHeader, DefaultConstructorMarker $constructor_marker) {
        this(klass, classHeader);
    }

    private ReflectKotlinClass(Class<?> cls, KotlinClassHeader classHeader) {
        this.klass = cls;
        this.classHeader = classHeader;
    }

    @NotNull
    public final Class<?> getKlass() {
        return this.klass;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass
    @NotNull
    public KotlinClassHeader getClassHeader() {
        return this.classHeader;
    }

    /* compiled from: ReflectKotlinClass.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/components/ReflectKotlinClass$Factory.class */
    public static final class Factory {
        public /* synthetic */ Factory(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Factory() {
        }

        @Nullable
        public final ReflectKotlinClass create(@NotNull Class<?> klass) {
            Intrinsics.checkNotNullParameter(klass, "klass");
            ReadKotlinClassHeaderAnnotationVisitor headerReader = new ReadKotlinClassHeaderAnnotationVisitor();
            ReflectClassStructure.INSTANCE.loadClassAnnotations(klass, headerReader);
            KotlinClassHeader kotlinClassHeaderCreateHeader = headerReader.createHeader();
            if (kotlinClassHeaderCreateHeader == null) {
                return null;
            }
            return new ReflectKotlinClass(klass, kotlinClassHeaderCreateHeader, null);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass
    @NotNull
    public String getLocation() {
        String name = this.klass.getName();
        Intrinsics.checkNotNullExpressionValue(name, "klass.name");
        return Intrinsics.stringPlus(StringsKt.replace$default(name, '.', '/', false, 4, (Object) null), ".class");
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass
    @NotNull
    public ClassId getClassId() {
        return ReflectClassUtilKt.getClassId(this.klass);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass
    public void loadClassAnnotations(@NotNull KotlinJvmBinaryClass.AnnotationVisitor visitor, @Nullable byte[] cachedContents) {
        Intrinsics.checkNotNullParameter(visitor, "visitor");
        ReflectClassStructure.INSTANCE.loadClassAnnotations(this.klass, visitor);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass
    public void visitMembers(@NotNull KotlinJvmBinaryClass.MemberVisitor visitor, @Nullable byte[] cachedContents) {
        Intrinsics.checkNotNullParameter(visitor, "visitor");
        ReflectClassStructure.INSTANCE.visitMembers(this.klass, visitor);
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof ReflectKotlinClass) && Intrinsics.areEqual(this.klass, ((ReflectKotlinClass) other).klass);
    }

    public int hashCode() {
        return this.klass.hashCode();
    }

    @NotNull
    public String toString() {
        return getClass().getName() + ": " + this.klass;
    }
}
