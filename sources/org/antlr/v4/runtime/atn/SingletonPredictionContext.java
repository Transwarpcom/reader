package org.antlr.v4.runtime.atn;

import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/SingletonPredictionContext.class */
public class SingletonPredictionContext extends PredictionContext {
    public final PredictionContext parent;
    public final int returnState;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !SingletonPredictionContext.class.desiredAssertionStatus();
    }

    SingletonPredictionContext(PredictionContext parent, int returnState) {
        super(parent != null ? calculateHashCode(parent, returnState) : calculateEmptyHashCode());
        if (!$assertionsDisabled && returnState == -1) {
            throw new AssertionError();
        }
        this.parent = parent;
        this.returnState = returnState;
    }

    public static SingletonPredictionContext create(PredictionContext parent, int returnState) {
        if (returnState == Integer.MAX_VALUE && parent == null) {
            return EMPTY;
        }
        return new SingletonPredictionContext(parent, returnState);
    }

    @Override // org.antlr.v4.runtime.atn.PredictionContext
    public int size() {
        return 1;
    }

    @Override // org.antlr.v4.runtime.atn.PredictionContext
    public PredictionContext getParent(int index) {
        if ($assertionsDisabled || index == 0) {
            return this.parent;
        }
        throw new AssertionError();
    }

    @Override // org.antlr.v4.runtime.atn.PredictionContext
    public int getReturnState(int index) {
        if ($assertionsDisabled || index == 0) {
            return this.returnState;
        }
        throw new AssertionError();
    }

    @Override // org.antlr.v4.runtime.atn.PredictionContext
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SingletonPredictionContext) || hashCode() != o.hashCode()) {
            return false;
        }
        SingletonPredictionContext s = (SingletonPredictionContext) o;
        return this.returnState == s.returnState && this.parent != null && this.parent.equals(s.parent);
    }

    public String toString() {
        String up = this.parent != null ? this.parent.toString() : "";
        if (up.length() == 0) {
            if (this.returnState == Integer.MAX_VALUE) {
                return PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX;
            }
            return String.valueOf(this.returnState);
        }
        return String.valueOf(this.returnState) + " " + up;
    }
}
