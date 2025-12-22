package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import org.jetbrains.annotations.NotNull;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/ErrorReporter.class */
public interface ErrorReporter {
    public static final ErrorReporter DO_NOTHING = new ErrorReporter() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter.1
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 0:
                case 2:
                default:
                    objArr[0] = "descriptor";
                    break;
                case 1:
                    objArr[0] = "unresolvedSuperClasses";
                    break;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/serialization/deserialization/ErrorReporter$1";
            switch (i) {
                case 0:
                case 1:
                default:
                    objArr[2] = "reportIncompleteHierarchy";
                    break;
                case 2:
                    objArr[2] = "reportCannotInferVisibility";
                    break;
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter
        public void reportIncompleteHierarchy(@NotNull ClassDescriptor descriptor, @NotNull List<String> unresolvedSuperClasses) {
            if (descriptor == null) {
                $$$reportNull$$$0(0);
            }
            if (unresolvedSuperClasses == null) {
                $$$reportNull$$$0(1);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter
        public void reportCannotInferVisibility(@NotNull CallableMemberDescriptor descriptor) {
            if (descriptor == null) {
                $$$reportNull$$$0(2);
            }
        }
    };

    void reportIncompleteHierarchy(@NotNull ClassDescriptor classDescriptor, @NotNull List<String> list);

    void reportCannotInferVisibility(@NotNull CallableMemberDescriptor callableMemberDescriptor);
}
