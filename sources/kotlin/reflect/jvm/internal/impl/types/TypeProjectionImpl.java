package kotlin.reflect.jvm.internal.impl.types;

import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/TypeProjectionImpl.class */
public class TypeProjectionImpl extends TypeProjectionBase {
    private final Variance projection;
    private final KotlinType type;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 6:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 4:
            case 5:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 6:
            default:
                i2 = 3;
                break;
            case 4:
            case 5:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            default:
                objArr[0] = "projection";
                break;
            case 1:
            case 2:
            case 3:
                objArr[0] = "type";
                break;
            case 4:
            case 5:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/TypeProjectionImpl";
                break;
            case 6:
                objArr[0] = "kotlinTypeRefiner";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 6:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/types/TypeProjectionImpl";
                break;
            case 4:
                objArr[1] = "getProjectionKind";
                break;
            case 5:
                objArr[1] = "getType";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            default:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 3:
                objArr[2] = "replaceType";
                break;
            case 4:
            case 5:
                break;
            case 6:
                objArr[2] = "refine";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 6:
            default:
                throw new IllegalArgumentException(str2);
            case 4:
            case 5:
                throw new IllegalStateException(str2);
        }
    }

    public TypeProjectionImpl(@NotNull Variance projection, @NotNull KotlinType type) {
        if (projection == null) {
            $$$reportNull$$$0(0);
        }
        if (type == null) {
            $$$reportNull$$$0(1);
        }
        this.projection = projection;
        this.type = type;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TypeProjectionImpl(@NotNull KotlinType type) {
        this(Variance.INVARIANT, type);
        if (type == null) {
            $$$reportNull$$$0(2);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    @NotNull
    public Variance getProjectionKind() {
        Variance variance = this.projection;
        if (variance == null) {
            $$$reportNull$$$0(4);
        }
        return variance;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    @NotNull
    public KotlinType getType() {
        KotlinType kotlinType = this.type;
        if (kotlinType == null) {
            $$$reportNull$$$0(5);
        }
        return kotlinType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    public boolean isStarProjection() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    @NotNull
    public TypeProjection refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        if (kotlinTypeRefiner == null) {
            $$$reportNull$$$0(6);
        }
        return new TypeProjectionImpl(this.projection, kotlinTypeRefiner.refineType(this.type));
    }
}
