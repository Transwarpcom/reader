package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/AbstractReceiverValue.class */
public abstract class AbstractReceiverValue implements ReceiverValue {
    protected final KotlinType receiverType;
    private final ReceiverValue original;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 1:
            case 2:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            default:
                i2 = 3;
                break;
            case 1:
            case 2:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            default:
                objArr[0] = "receiverType";
                break;
            case 1:
            case 2:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/AbstractReceiverValue";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/AbstractReceiverValue";
                break;
            case 1:
                objArr[1] = "getType";
                break;
            case 2:
                objArr[1] = "getOriginal";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 1:
            case 2:
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            default:
                throw new IllegalArgumentException(str2);
            case 1:
            case 2:
                throw new IllegalStateException(str2);
        }
    }

    public AbstractReceiverValue(@NotNull KotlinType receiverType, @Nullable ReceiverValue original) {
        if (receiverType == null) {
            $$$reportNull$$$0(0);
        }
        this.receiverType = receiverType;
        this.original = original != null ? original : this;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue
    @NotNull
    public KotlinType getType() {
        KotlinType kotlinType = this.receiverType;
        if (kotlinType == null) {
            $$$reportNull$$$0(1);
        }
        return kotlinType;
    }
}
