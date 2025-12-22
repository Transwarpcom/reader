package com.jayway.jsonpath;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/EvaluationListener.class */
public interface EvaluationListener {

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/EvaluationListener$EvaluationContinuation.class */
    public enum EvaluationContinuation {
        CONTINUE,
        ABORT
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/EvaluationListener$FoundResult.class */
    public interface FoundResult {
        int index();

        String path();

        Object result();
    }

    EvaluationContinuation resultFound(FoundResult foundResult);
}
