package org.antlr.v4.runtime.atn;

import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/EmptyPredictionContext.class */
public class EmptyPredictionContext extends SingletonPredictionContext {
    public EmptyPredictionContext() {
        super(null, Integer.MAX_VALUE);
    }

    @Override // org.antlr.v4.runtime.atn.PredictionContext
    public boolean isEmpty() {
        return true;
    }

    @Override // org.antlr.v4.runtime.atn.SingletonPredictionContext, org.antlr.v4.runtime.atn.PredictionContext
    public int size() {
        return 1;
    }

    @Override // org.antlr.v4.runtime.atn.SingletonPredictionContext, org.antlr.v4.runtime.atn.PredictionContext
    public PredictionContext getParent(int index) {
        return null;
    }

    @Override // org.antlr.v4.runtime.atn.SingletonPredictionContext, org.antlr.v4.runtime.atn.PredictionContext
    public int getReturnState(int index) {
        return this.returnState;
    }

    @Override // org.antlr.v4.runtime.atn.SingletonPredictionContext, org.antlr.v4.runtime.atn.PredictionContext
    public boolean equals(Object o) {
        return this == o;
    }

    @Override // org.antlr.v4.runtime.atn.SingletonPredictionContext
    public String toString() {
        return PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX;
    }
}
