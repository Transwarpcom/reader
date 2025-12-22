package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: typeSignatureMapping.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/JvmDescriptorTypeWriter.class */
public class JvmDescriptorTypeWriter<T> {

    @NotNull
    private final JvmTypeFactory<T> jvmTypeFactory;
    private int jvmCurrentTypeArrayLevel;

    @Nullable
    private T jvmCurrentType;

    public void writeArrayType() {
        if (this.jvmCurrentType == null) {
            this.jvmCurrentTypeArrayLevel++;
            int i = this.jvmCurrentTypeArrayLevel;
        }
    }

    public void writeArrayEnd() {
    }

    public void writeClass(@NotNull T objectType) {
        Intrinsics.checkNotNullParameter(objectType, "objectType");
        writeJvmTypeAsIs(objectType);
    }

    protected final void writeJvmTypeAsIs(@NotNull T type) {
        T tCreateFromString;
        Intrinsics.checkNotNullParameter(type, "type");
        if (this.jvmCurrentType == null) {
            if (this.jvmCurrentTypeArrayLevel > 0) {
                tCreateFromString = this.jvmTypeFactory.createFromString(Intrinsics.stringPlus(StringsKt.repeat("[", this.jvmCurrentTypeArrayLevel), this.jvmTypeFactory.toString(type)));
            } else {
                tCreateFromString = type;
            }
            this.jvmCurrentType = tCreateFromString;
        }
    }

    public void writeTypeVariable(@NotNull Name name, @NotNull T type) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(type, "type");
        writeJvmTypeAsIs(type);
    }
}
