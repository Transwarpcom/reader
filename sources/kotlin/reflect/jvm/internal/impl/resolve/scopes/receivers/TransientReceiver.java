package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/TransientReceiver.class */
public class TransientReceiver extends AbstractReceiverValue {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        switch (i) {
            case 0:
            case 1:
            default:
                objArr[0] = "type";
                break;
            case 2:
                objArr[0] = "newType";
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/TransientReceiver";
        switch (i) {
            case 0:
            case 1:
            default:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 2:
                objArr[2] = "replaceType";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TransientReceiver(@NotNull KotlinType type) {
        this(type, null);
        if (type == null) {
            $$$reportNull$$$0(0);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private TransientReceiver(@NotNull KotlinType type, @Nullable ReceiverValue original) {
        super(type, original);
        if (type == null) {
            $$$reportNull$$$0(1);
        }
    }

    public String toString() {
        return "{Transient} : " + getType();
    }
}
