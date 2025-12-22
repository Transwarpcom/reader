package org.antlr.v4.runtime.atn;

import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.antlr.v4.runtime.misc.AbstractEqualityComparator;
import org.antlr.v4.runtime.misc.FlexibleHashMap;
import org.antlr.v4.runtime.misc.MurmurHash;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/PredictionMode.class */
public enum PredictionMode {
    SLL,
    LL,
    LL_EXACT_AMBIG_DETECTION;

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/PredictionMode$AltAndContextMap.class */
    static class AltAndContextMap extends FlexibleHashMap<ATNConfig, BitSet> {
        public AltAndContextMap() {
            super(AltAndContextConfigEqualityComparator.INSTANCE);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/PredictionMode$AltAndContextConfigEqualityComparator.class */
    private static final class AltAndContextConfigEqualityComparator extends AbstractEqualityComparator<ATNConfig> {
        public static final AltAndContextConfigEqualityComparator INSTANCE = new AltAndContextConfigEqualityComparator();

        private AltAndContextConfigEqualityComparator() {
        }

        @Override // org.antlr.v4.runtime.misc.EqualityComparator
        public int hashCode(ATNConfig o) {
            int hashCode = MurmurHash.initialize(7);
            return MurmurHash.finish(MurmurHash.update(MurmurHash.update(hashCode, o.state.stateNumber), o.context), 2);
        }

        @Override // org.antlr.v4.runtime.misc.EqualityComparator
        public boolean equals(ATNConfig a, ATNConfig b) {
            if (a == b) {
                return true;
            }
            return a != null && b != null && a.state.stateNumber == b.state.stateNumber && a.context.equals(b.context);
        }
    }

    public static boolean hasSLLConflictTerminatingPrediction(PredictionMode mode, ATNConfigSet configs) {
        if (allConfigsInRuleStopStates(configs)) {
            return true;
        }
        if (mode == SLL && configs.hasSemanticContext) {
            ATNConfigSet dup = new ATNConfigSet();
            Iterator i$ = configs.iterator();
            while (i$.hasNext()) {
                ATNConfig c = i$.next();
                dup.add(new ATNConfig(c, SemanticContext.NONE));
            }
            configs = dup;
        }
        Collection<BitSet> altsets = getConflictingAltSubsets(configs);
        boolean heuristic = hasConflictingAltSet(altsets) && !hasStateAssociatedWithOneAlt(configs);
        return heuristic;
    }

    public static boolean hasConfigInRuleStopState(ATNConfigSet configs) {
        Iterator i$ = configs.iterator();
        while (i$.hasNext()) {
            ATNConfig c = i$.next();
            if (c.state instanceof RuleStopState) {
                return true;
            }
        }
        return false;
    }

    public static boolean allConfigsInRuleStopStates(ATNConfigSet configs) {
        Iterator i$ = configs.iterator();
        while (i$.hasNext()) {
            ATNConfig config = i$.next();
            if (!(config.state instanceof RuleStopState)) {
                return false;
            }
        }
        return true;
    }

    public static int resolvesToJustOneViableAlt(Collection<BitSet> altsets) {
        return getSingleViableAlt(altsets);
    }

    public static boolean allSubsetsConflict(Collection<BitSet> altsets) {
        return !hasNonConflictingAltSet(altsets);
    }

    public static boolean hasNonConflictingAltSet(Collection<BitSet> altsets) {
        for (BitSet alts : altsets) {
            if (alts.cardinality() == 1) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasConflictingAltSet(Collection<BitSet> altsets) {
        for (BitSet alts : altsets) {
            if (alts.cardinality() > 1) {
                return true;
            }
        }
        return false;
    }

    public static boolean allSubsetsEqual(Collection<BitSet> altsets) {
        Iterator<BitSet> it = altsets.iterator();
        BitSet first = it.next();
        while (it.hasNext()) {
            BitSet next = it.next();
            if (!next.equals(first)) {
                return false;
            }
        }
        return true;
    }

    public static int getUniqueAlt(Collection<BitSet> altsets) {
        BitSet all = getAlts(altsets);
        if (all.cardinality() == 1) {
            return all.nextSetBit(0);
        }
        return 0;
    }

    public static BitSet getAlts(Collection<BitSet> altsets) {
        BitSet all = new BitSet();
        for (BitSet alts : altsets) {
            all.or(alts);
        }
        return all;
    }

    public static BitSet getAlts(ATNConfigSet configs) {
        BitSet alts = new BitSet();
        Iterator i$ = configs.iterator();
        while (i$.hasNext()) {
            ATNConfig config = i$.next();
            alts.set(config.alt);
        }
        return alts;
    }

    public static Collection<BitSet> getConflictingAltSubsets(ATNConfigSet configs) {
        AltAndContextMap configToAlts = new AltAndContextMap();
        Iterator i$ = configs.iterator();
        while (i$.hasNext()) {
            ATNConfig c = i$.next();
            BitSet alts = (BitSet) configToAlts.get(c);
            if (alts == null) {
                alts = new BitSet();
                configToAlts.put(c, alts);
            }
            alts.set(c.alt);
        }
        return configToAlts.values();
    }

    public static Map<ATNState, BitSet> getStateToAltMap(ATNConfigSet configs) {
        Map<ATNState, BitSet> m = new HashMap<>();
        Iterator i$ = configs.iterator();
        while (i$.hasNext()) {
            ATNConfig c = i$.next();
            BitSet alts = m.get(c.state);
            if (alts == null) {
                alts = new BitSet();
                m.put(c.state, alts);
            }
            alts.set(c.alt);
        }
        return m;
    }

    public static boolean hasStateAssociatedWithOneAlt(ATNConfigSet configs) {
        Map<ATNState, BitSet> x = getStateToAltMap(configs);
        for (BitSet alts : x.values()) {
            if (alts.cardinality() == 1) {
                return true;
            }
        }
        return false;
    }

    public static int getSingleViableAlt(Collection<BitSet> altsets) {
        BitSet viableAlts = new BitSet();
        for (BitSet alts : altsets) {
            int minAlt = alts.nextSetBit(0);
            viableAlts.set(minAlt);
            if (viableAlts.cardinality() > 1) {
                return 0;
            }
        }
        return viableAlts.nextSetBit(0);
    }
}
