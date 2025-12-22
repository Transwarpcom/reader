package org.springframework.expression;

import java.util.List;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-expression-5.1.8.RELEASE.jar:org/springframework/expression/ConstructorResolver.class */
public interface ConstructorResolver {
    @Nullable
    ConstructorExecutor resolve(EvaluationContext evaluationContext, String str, List<TypeDescriptor> list) throws AccessException;
}
