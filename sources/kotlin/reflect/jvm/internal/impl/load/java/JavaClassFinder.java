package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.Arrays;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaClassFinder.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/JavaClassFinder.class */
public interface JavaClassFinder {
    @Nullable
    JavaClass findClass(@NotNull Request request);

    @Nullable
    JavaPackage findPackage(@NotNull FqName fqName);

    @Nullable
    Set<String> knownClassNamesInPackage(@NotNull FqName fqName);

    /* compiled from: JavaClassFinder.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/JavaClassFinder$Request.class */
    public static final class Request {

        @NotNull
        private final ClassId classId;

        @Nullable
        private final byte[] previouslyFoundClassFileContent;

        @Nullable
        private final JavaClass outerClass;

        @NotNull
        public String toString() {
            return "Request(classId=" + this.classId + ", previouslyFoundClassFileContent=" + Arrays.toString(this.previouslyFoundClassFileContent) + ", outerClass=" + this.outerClass + ')';
        }

        public int hashCode() {
            int result = this.classId.hashCode();
            return (((result * 31) + (this.previouslyFoundClassFileContent == null ? 0 : Arrays.hashCode(this.previouslyFoundClassFileContent))) * 31) + (this.outerClass == null ? 0 : this.outerClass.hashCode());
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Request)) {
                return false;
            }
            Request request = (Request) other;
            return Intrinsics.areEqual(this.classId, request.classId) && Intrinsics.areEqual(this.previouslyFoundClassFileContent, request.previouslyFoundClassFileContent) && Intrinsics.areEqual(this.outerClass, request.outerClass);
        }

        public Request(@NotNull ClassId classId, @Nullable byte[] previouslyFoundClassFileContent, @Nullable JavaClass outerClass) {
            Intrinsics.checkNotNullParameter(classId, "classId");
            this.classId = classId;
            this.previouslyFoundClassFileContent = previouslyFoundClassFileContent;
            this.outerClass = outerClass;
        }

        public /* synthetic */ Request(ClassId classId, byte[] bArr, JavaClass javaClass, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(classId, (i & 2) != 0 ? null : bArr, (i & 4) != 0 ? null : javaClass);
        }

        @NotNull
        public final ClassId getClassId() {
            return this.classId;
        }
    }
}
