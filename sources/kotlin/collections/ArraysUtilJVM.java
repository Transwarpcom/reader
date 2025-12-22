package kotlin.collections;

import java.util.Arrays;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/ArraysUtilJVM.class */
class ArraysUtilJVM {
    ArraysUtilJVM() {
    }

    static <T> List<T> asList(T[] array) {
        return Arrays.asList(array);
    }
}
