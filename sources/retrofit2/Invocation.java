package retrofit2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/Invocation.class */
public final class Invocation {
    private final Method method;
    private final List<?> arguments;

    public static Invocation of(Method method, List<?> arguments) {
        Utils.checkNotNull(method, "method == null");
        Utils.checkNotNull(arguments, "arguments == null");
        return new Invocation(method, new ArrayList(arguments));
    }

    Invocation(Method method, List<?> arguments) {
        this.method = method;
        this.arguments = Collections.unmodifiableList(arguments);
    }

    public Method method() {
        return this.method;
    }

    public List<?> arguments() {
        return this.arguments;
    }

    public String toString() {
        return String.format("%s.%s() %s", this.method.getDeclaringClass().getName(), this.method.getName(), this.arguments);
    }
}
