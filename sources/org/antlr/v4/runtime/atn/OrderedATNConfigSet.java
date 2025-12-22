package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.misc.ObjectEqualityComparator;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/OrderedATNConfigSet.class */
public class OrderedATNConfigSet extends ATNConfigSet {
    public OrderedATNConfigSet() {
        this.configLookup = new LexerConfigHashSet();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/OrderedATNConfigSet$LexerConfigHashSet.class */
    public static class LexerConfigHashSet extends ATNConfigSet.AbstractConfigHashSet {
        public LexerConfigHashSet() {
            super(ObjectEqualityComparator.INSTANCE);
        }
    }
}
