package org.antlr.v4.runtime.atn;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.antlr.v4.runtime.misc.AbstractEqualityComparator;
import org.antlr.v4.runtime.misc.Array2DHashSet;
import org.antlr.v4.runtime.misc.DoubleKeyMap;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/ATNConfigSet.class */
public class ATNConfigSet implements Set<ATNConfig> {
    protected boolean readonly;
    public AbstractConfigHashSet configLookup;
    public final ArrayList<ATNConfig> configs;
    public int uniqueAlt;
    protected BitSet conflictingAlts;
    public boolean hasSemanticContext;
    public boolean dipsIntoOuterContext;
    public final boolean fullCtx;
    private int cachedHashCode;

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/ATNConfigSet$ConfigHashSet.class */
    public static class ConfigHashSet extends AbstractConfigHashSet {
        public ConfigHashSet() {
            super(ConfigEqualityComparator.INSTANCE);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/ATNConfigSet$ConfigEqualityComparator.class */
    public static final class ConfigEqualityComparator extends AbstractEqualityComparator<ATNConfig> {
        public static final ConfigEqualityComparator INSTANCE = new ConfigEqualityComparator();

        private ConfigEqualityComparator() {
        }

        @Override // org.antlr.v4.runtime.misc.EqualityComparator
        public int hashCode(ATNConfig o) {
            int hashCode = (31 * 7) + o.state.stateNumber;
            return (31 * ((31 * hashCode) + o.alt)) + o.semanticContext.hashCode();
        }

        @Override // org.antlr.v4.runtime.misc.EqualityComparator
        public boolean equals(ATNConfig a, ATNConfig b) {
            if (a == b) {
                return true;
            }
            return a != null && b != null && a.state.stateNumber == b.state.stateNumber && a.alt == b.alt && a.semanticContext.equals(b.semanticContext);
        }
    }

    public ATNConfigSet(boolean fullCtx) {
        this.readonly = false;
        this.configs = new ArrayList<>(7);
        this.cachedHashCode = -1;
        this.configLookup = new ConfigHashSet();
        this.fullCtx = fullCtx;
    }

    public ATNConfigSet() {
        this(true);
    }

    public ATNConfigSet(ATNConfigSet old) {
        this(old.fullCtx);
        addAll(old);
        this.uniqueAlt = old.uniqueAlt;
        this.conflictingAlts = old.conflictingAlts;
        this.hasSemanticContext = old.hasSemanticContext;
        this.dipsIntoOuterContext = old.dipsIntoOuterContext;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(ATNConfig config) {
        return add(config, null);
    }

    public boolean add(ATNConfig config, DoubleKeyMap<PredictionContext, PredictionContext, PredictionContext> mergeCache) {
        if (this.readonly) {
            throw new IllegalStateException("This set is readonly");
        }
        if (config.semanticContext != SemanticContext.NONE) {
            this.hasSemanticContext = true;
        }
        if (config.getOuterContextDepth() > 0) {
            this.dipsIntoOuterContext = true;
        }
        ATNConfig existing = this.configLookup.getOrAdd(config);
        if (existing == config) {
            this.cachedHashCode = -1;
            this.configs.add(config);
            return true;
        }
        boolean rootIsWildcard = !this.fullCtx;
        PredictionContext merged = PredictionContext.merge(existing.context, config.context, rootIsWildcard, mergeCache);
        existing.reachesIntoOuterContext = Math.max(existing.reachesIntoOuterContext, config.reachesIntoOuterContext);
        if (config.isPrecedenceFilterSuppressed()) {
            existing.setPrecedenceFilterSuppressed(true);
        }
        existing.context = merged;
        return true;
    }

    public List<ATNConfig> elements() {
        return this.configs;
    }

    public Set<ATNState> getStates() {
        Set<ATNState> states = new HashSet<>();
        Iterator i$ = this.configs.iterator();
        while (i$.hasNext()) {
            ATNConfig c = i$.next();
            states.add(c.state);
        }
        return states;
    }

    public BitSet getAlts() {
        BitSet alts = new BitSet();
        Iterator i$ = this.configs.iterator();
        while (i$.hasNext()) {
            ATNConfig config = i$.next();
            alts.set(config.alt);
        }
        return alts;
    }

    public List<SemanticContext> getPredicates() {
        List<SemanticContext> preds = new ArrayList<>();
        Iterator i$ = this.configs.iterator();
        while (i$.hasNext()) {
            ATNConfig c = i$.next();
            if (c.semanticContext != SemanticContext.NONE) {
                preds.add(c.semanticContext);
            }
        }
        return preds;
    }

    public ATNConfig get(int i) {
        return this.configs.get(i);
    }

    public void optimizeConfigs(ATNSimulator interpreter) {
        if (this.readonly) {
            throw new IllegalStateException("This set is readonly");
        }
        if (this.configLookup.isEmpty()) {
            return;
        }
        Iterator i$ = this.configs.iterator();
        while (i$.hasNext()) {
            ATNConfig config = i$.next();
            config.context = interpreter.getCachedContext(config.context);
        }
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends ATNConfig> coll) {
        for (ATNConfig c : coll) {
            add(c);
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ATNConfigSet)) {
            return false;
        }
        ATNConfigSet other = (ATNConfigSet) o;
        boolean same = this.configs != null && this.configs.equals(other.configs) && this.fullCtx == other.fullCtx && this.uniqueAlt == other.uniqueAlt && this.conflictingAlts == other.conflictingAlts && this.hasSemanticContext == other.hasSemanticContext && this.dipsIntoOuterContext == other.dipsIntoOuterContext;
        return same;
    }

    @Override // java.util.Set, java.util.Collection
    public int hashCode() {
        if (isReadonly()) {
            if (this.cachedHashCode == -1) {
                this.cachedHashCode = this.configs.hashCode();
            }
            return this.cachedHashCode;
        }
        return this.configs.hashCode();
    }

    @Override // java.util.Set, java.util.Collection
    public int size() {
        return this.configs.size();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.configs.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object o) {
        if (this.configLookup == null) {
            throw new UnsupportedOperationException("This method is not implemented for readonly sets.");
        }
        return this.configLookup.contains(o);
    }

    public boolean containsFast(ATNConfig obj) {
        if (this.configLookup == null) {
            throw new UnsupportedOperationException("This method is not implemented for readonly sets.");
        }
        return this.configLookup.containsFast(obj);
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<ATNConfig> iterator() {
        return this.configs.iterator();
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        if (this.readonly) {
            throw new IllegalStateException("This set is readonly");
        }
        this.configs.clear();
        this.cachedHashCode = -1;
        this.configLookup.clear();
    }

    public boolean isReadonly() {
        return this.readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
        this.configLookup = null;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(elements().toString());
        if (this.hasSemanticContext) {
            buf.append(",hasSemanticContext=").append(this.hasSemanticContext);
        }
        if (this.uniqueAlt != 0) {
            buf.append(",uniqueAlt=").append(this.uniqueAlt);
        }
        if (this.conflictingAlts != null) {
            buf.append(",conflictingAlts=").append(this.conflictingAlts);
        }
        if (this.dipsIntoOuterContext) {
            buf.append(",dipsIntoOuterContext");
        }
        return buf.toString();
    }

    @Override // java.util.Set, java.util.Collection
    public ATNConfig[] toArray() {
        return this.configLookup.toArray();
    }

    @Override // java.util.Set, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.configLookup.toArray(tArr);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/ATNConfigSet$AbstractConfigHashSet.class */
    public static abstract class AbstractConfigHashSet extends Array2DHashSet<ATNConfig> {
        public AbstractConfigHashSet(AbstractEqualityComparator<? super ATNConfig> comparator) {
            this(comparator, 16, 2);
        }

        public AbstractConfigHashSet(AbstractEqualityComparator<? super ATNConfig> comparator, int initialCapacity, int initialBucketCapacity) {
            super(comparator, initialCapacity, initialBucketCapacity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.antlr.v4.runtime.misc.Array2DHashSet
        public final ATNConfig asElementType(Object o) {
            if (!(o instanceof ATNConfig)) {
                return null;
            }
            return (ATNConfig) o;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Type inference failed for: r0v1, types: [org.antlr.v4.runtime.atn.ATNConfig[], org.antlr.v4.runtime.atn.ATNConfig[][]] */
        @Override // org.antlr.v4.runtime.misc.Array2DHashSet
        public final ATNConfig[][] createBuckets(int capacity) {
            return new ATNConfig[capacity];
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.antlr.v4.runtime.misc.Array2DHashSet
        public final ATNConfig[] createBucket(int capacity) {
            return new ATNConfig[capacity];
        }
    }
}
