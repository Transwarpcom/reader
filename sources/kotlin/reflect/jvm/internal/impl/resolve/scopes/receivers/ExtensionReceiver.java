package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/ExtensionReceiver.class */
public class ExtensionReceiver extends AbstractReceiverValue implements ImplicitReceiver {
    private final CallableDescriptor descriptor;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 3:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 2:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 3:
            default:
                i2 = 3;
                break;
            case 2:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            default:
                objArr[0] = "callableDescriptor";
                break;
            case 1:
                objArr[0] = "receiverType";
                break;
            case 2:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/ExtensionReceiver";
                break;
            case 3:
                objArr[0] = "newType";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 3:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/ExtensionReceiver";
                break;
            case 2:
                objArr[1] = "getDeclarationDescriptor";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            default:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 2:
                break;
            case 3:
                objArr[2] = "replaceType";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 3:
            default:
                throw new IllegalArgumentException(str2);
            case 2:
                throw new IllegalStateException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExtensionReceiver(@NotNull CallableDescriptor callableDescriptor, @NotNull KotlinType receiverType, @Nullable ReceiverValue original) {
        super(receiverType, original);
        if (callableDescriptor == null) {
            $$$reportNull$$$0(0);
        }
        if (receiverType == null) {
            $$$reportNull$$$0(1);
        }
        this.descriptor = callableDescriptor;
    }

    public String toString() {
        return getType() + ": Ext {" + this.descriptor + "}";
    }
}
