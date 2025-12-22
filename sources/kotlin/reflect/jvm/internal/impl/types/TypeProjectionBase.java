package kotlin.reflect.jvm.internal.impl.types;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/TypeProjectionBase.class */
public abstract class TypeProjectionBase implements TypeProjection {
    public String toString() {
        if (isStarProjection()) {
            return "*";
        }
        if (getProjectionKind() == Variance.INVARIANT) {
            return getType().toString();
        }
        return getProjectionKind() + " " + getType();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeProjection)) {
            return false;
        }
        TypeProjection that = (TypeProjection) o;
        return isStarProjection() == that.isStarProjection() && getProjectionKind() == that.getProjectionKind() && getType().equals(that.getType());
    }

    public int hashCode() {
        int result;
        int result2 = getProjectionKind().hashCode();
        if (TypeUtils.noExpectedType(getType())) {
            result = (31 * result2) + 19;
        } else {
            result = (31 * result2) + (isStarProjection() ? 17 : getType().hashCode());
        }
        return result;
    }
}
