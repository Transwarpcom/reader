package retrofit2;

import cn.hutool.core.text.StrPool;
import java.lang.annotation.Annotation;

/* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/SkipCallbackExecutorImpl.class */
final class SkipCallbackExecutorImpl implements SkipCallbackExecutor {
    private static final SkipCallbackExecutor INSTANCE = new SkipCallbackExecutorImpl();

    SkipCallbackExecutorImpl() {
    }

    static Annotation[] ensurePresent(Annotation[] annotations) {
        if (Utils.isAnnotationPresent(annotations, SkipCallbackExecutor.class)) {
            return annotations;
        }
        Annotation[] newAnnotations = new Annotation[annotations.length + 1];
        newAnnotations[0] = INSTANCE;
        System.arraycopy(annotations, 0, newAnnotations, 1, annotations.length);
        return newAnnotations;
    }

    @Override // java.lang.annotation.Annotation
    public Class<? extends Annotation> annotationType() {
        return SkipCallbackExecutor.class;
    }

    @Override // java.lang.annotation.Annotation
    public boolean equals(Object obj) {
        return obj instanceof SkipCallbackExecutor;
    }

    @Override // java.lang.annotation.Annotation
    public int hashCode() {
        return 0;
    }

    @Override // java.lang.annotation.Annotation
    public String toString() {
        return StrPool.AT + SkipCallbackExecutor.class.getName() + "()";
    }
}
