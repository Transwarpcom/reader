package org.springframework.scripting;

import java.util.Map;
import org.springframework.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/scripting/ScriptEvaluator.class */
public interface ScriptEvaluator {
    @Nullable
    Object evaluate(ScriptSource scriptSource) throws ScriptCompilationException;

    @Nullable
    Object evaluate(ScriptSource scriptSource, @Nullable Map<String, Object> map) throws ScriptCompilationException;
}
