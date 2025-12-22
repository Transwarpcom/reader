package org.antlr.v4.runtime.atn;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.misc.Pair;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/ATNDeserializer.class */
public class ATNDeserializer {
    public static final int SERIALIZED_VERSION = 3;
    private static final UUID BASE_SERIALIZED_UUID = UUID.fromString("33761B2D-78BB-4A43-8B0B-4F5BEE8AACF3");
    private static final UUID ADDED_PRECEDENCE_TRANSITIONS = UUID.fromString("1DA0C57D-6C06-438A-9B27-10BCB3CE0F61");
    private static final UUID ADDED_LEXER_ACTIONS = UUID.fromString("AADB8D7E-AEEF-4415-AD2B-8204D6CF042E");
    private static final UUID ADDED_UNICODE_SMP = UUID.fromString("59627784-3BE5-417A-B9EB-8131A7286089");
    private static final List<UUID> SUPPORTED_UUIDS = new ArrayList();
    public static final UUID SERIALIZED_UUID;
    private final ATNDeserializationOptions deserializationOptions;

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/ATNDeserializer$UnicodeDeserializer.class */
    interface UnicodeDeserializer {
        int readUnicode(char[] cArr, int i);

        int size();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/ATNDeserializer$UnicodeDeserializingMode.class */
    enum UnicodeDeserializingMode {
        UNICODE_BMP,
        UNICODE_SMP
    }

    static {
        SUPPORTED_UUIDS.add(BASE_SERIALIZED_UUID);
        SUPPORTED_UUIDS.add(ADDED_PRECEDENCE_TRANSITIONS);
        SUPPORTED_UUIDS.add(ADDED_LEXER_ACTIONS);
        SUPPORTED_UUIDS.add(ADDED_UNICODE_SMP);
        SERIALIZED_UUID = ADDED_UNICODE_SMP;
    }

    static UnicodeDeserializer getUnicodeDeserializer(UnicodeDeserializingMode mode) {
        if (mode == UnicodeDeserializingMode.UNICODE_BMP) {
            return new UnicodeDeserializer() { // from class: org.antlr.v4.runtime.atn.ATNDeserializer.1
                @Override // org.antlr.v4.runtime.atn.ATNDeserializer.UnicodeDeserializer
                public int readUnicode(char[] data, int p) {
                    return ATNDeserializer.toInt(data[p]);
                }

                @Override // org.antlr.v4.runtime.atn.ATNDeserializer.UnicodeDeserializer
                public int size() {
                    return 1;
                }
            };
        }
        return new UnicodeDeserializer() { // from class: org.antlr.v4.runtime.atn.ATNDeserializer.2
            @Override // org.antlr.v4.runtime.atn.ATNDeserializer.UnicodeDeserializer
            public int readUnicode(char[] data, int p) {
                return ATNDeserializer.toInt32(data, p);
            }

            @Override // org.antlr.v4.runtime.atn.ATNDeserializer.UnicodeDeserializer
            public int size() {
                return 2;
            }
        };
    }

    public ATNDeserializer() {
        this(ATNDeserializationOptions.getDefaultOptions());
    }

    public ATNDeserializer(ATNDeserializationOptions deserializationOptions) {
        this.deserializationOptions = deserializationOptions == null ? ATNDeserializationOptions.getDefaultOptions() : deserializationOptions;
    }

    protected static boolean isFeatureSupported(UUID feature, UUID actualUuid) {
        int featureIndex = SUPPORTED_UUIDS.indexOf(feature);
        return featureIndex >= 0 && SUPPORTED_UUIDS.indexOf(actualUuid) >= featureIndex;
    }

    public ATN deserialize(char[] data) {
        ATNState endState;
        char[] data2 = (char[]) data.clone();
        for (int i = 1; i < data2.length; i++) {
            data2[i] = (char) (data2[i] - 2);
        }
        int p = 0 + 1;
        int version = toInt(data2[0]);
        if (version != SERIALIZED_VERSION) {
            String reason = String.format(Locale.getDefault(), "Could not deserialize ATN with version %d (expected %d).", Integer.valueOf(version), Integer.valueOf(SERIALIZED_VERSION));
            throw new UnsupportedOperationException(new InvalidClassException(ATN.class.getName(), reason));
        }
        UUID uuid = toUUID(data2, p);
        int p2 = p + 8;
        if (!SUPPORTED_UUIDS.contains(uuid)) {
            String reason2 = String.format(Locale.getDefault(), "Could not deserialize ATN with UUID %s (expected %s or a legacy UUID).", uuid, SERIALIZED_UUID);
            throw new UnsupportedOperationException(new InvalidClassException(ATN.class.getName(), reason2));
        }
        boolean supportsPrecedencePredicates = isFeatureSupported(ADDED_PRECEDENCE_TRANSITIONS, uuid);
        boolean supportsLexerActions = isFeatureSupported(ADDED_LEXER_ACTIONS, uuid);
        int p3 = p2 + 1;
        ATNType grammarType = ATNType.values()[toInt(data2[p2])];
        int p4 = p3 + 1;
        int maxTokenType = toInt(data2[p3]);
        ATN atn = new ATN(grammarType, maxTokenType);
        List<Pair<LoopEndState, Integer>> loopBackStateNumbers = new ArrayList<>();
        List<Pair<BlockStartState, Integer>> endStateNumbers = new ArrayList<>();
        int p5 = p4 + 1;
        int nstates = toInt(data2[p4]);
        for (int i2 = 0; i2 < nstates; i2++) {
            int i3 = p5;
            p5++;
            int stype = toInt(data2[i3]);
            if (stype == 0) {
                atn.addState(null);
            } else {
                p5++;
                int ruleIndex = toInt(data2[p5]);
                if (ruleIndex == 65535) {
                    ruleIndex = -1;
                }
                ATNState s = stateFactory(stype, ruleIndex);
                if (stype == 12) {
                    p5++;
                    int loopBackStateNumber = toInt(data2[p5]);
                    loopBackStateNumbers.add(new Pair<>((LoopEndState) s, Integer.valueOf(loopBackStateNumber)));
                } else if (s instanceof BlockStartState) {
                    p5++;
                    int endStateNumber = toInt(data2[p5]);
                    endStateNumbers.add(new Pair<>((BlockStartState) s, Integer.valueOf(endStateNumber)));
                }
                atn.addState(s);
            }
        }
        for (Pair<LoopEndState, Integer> pair : loopBackStateNumbers) {
            pair.a.loopBackState = atn.states.get(pair.b.intValue());
        }
        for (Pair<BlockStartState, Integer> pair2 : endStateNumbers) {
            pair2.a.endState = (BlockEndState) atn.states.get(pair2.b.intValue());
        }
        int i4 = p5;
        int p6 = p5 + 1;
        int numNonGreedyStates = toInt(data2[i4]);
        for (int i5 = 0; i5 < numNonGreedyStates; i5++) {
            int i6 = p6;
            p6++;
            int stateNumber = toInt(data2[i6]);
            ((DecisionState) atn.states.get(stateNumber)).nonGreedy = true;
        }
        if (supportsPrecedencePredicates) {
            int i7 = p6;
            p6++;
            int numPrecedenceStates = toInt(data2[i7]);
            for (int i8 = 0; i8 < numPrecedenceStates; i8++) {
                int i9 = p6;
                p6++;
                int stateNumber2 = toInt(data2[i9]);
                ((RuleStartState) atn.states.get(stateNumber2)).isLeftRecursiveRule = true;
            }
        }
        int i10 = p6;
        int p7 = p6 + 1;
        int nrules = toInt(data2[i10]);
        if (atn.grammarType == ATNType.LEXER) {
            atn.ruleToTokenType = new int[nrules];
        }
        atn.ruleToStartState = new RuleStartState[nrules];
        for (int i11 = 0; i11 < nrules; i11++) {
            int i12 = p7;
            p7++;
            int s2 = toInt(data2[i12]);
            RuleStartState startState = (RuleStartState) atn.states.get(s2);
            atn.ruleToStartState[i11] = startState;
            if (atn.grammarType == ATNType.LEXER) {
                p7++;
                int tokenType = toInt(data2[p7]);
                if (tokenType == 65535) {
                    tokenType = -1;
                }
                atn.ruleToTokenType[i11] = tokenType;
                if (!isFeatureSupported(ADDED_LEXER_ACTIONS, uuid)) {
                    p7++;
                    toInt(data2[p7]);
                }
            }
        }
        atn.ruleToStopState = new RuleStopState[nrules];
        for (ATNState state : atn.states) {
            if (state instanceof RuleStopState) {
                RuleStopState stopState = (RuleStopState) state;
                atn.ruleToStopState[state.ruleIndex] = stopState;
                atn.ruleToStartState[state.ruleIndex].stopState = stopState;
            }
        }
        int i13 = p7;
        int p8 = p7 + 1;
        int nmodes = toInt(data2[i13]);
        for (int i14 = 0; i14 < nmodes; i14++) {
            int i15 = p8;
            p8++;
            int s3 = toInt(data2[i15]);
            atn.modeToStartState.add((TokensStartState) atn.states.get(s3));
        }
        List<IntervalSet> sets = new ArrayList<>();
        int p9 = deserializeSets(data2, p8, sets, getUnicodeDeserializer(UnicodeDeserializingMode.UNICODE_BMP));
        if (isFeatureSupported(ADDED_UNICODE_SMP, uuid)) {
            p9 = deserializeSets(data2, p9, sets, getUnicodeDeserializer(UnicodeDeserializingMode.UNICODE_SMP));
        }
        int i16 = p9;
        int p10 = p9 + 1;
        int nedges = toInt(data2[i16]);
        for (int i17 = 0; i17 < nedges; i17++) {
            int src = toInt(data2[p10]);
            int trg = toInt(data2[p10 + 1]);
            int ttype = toInt(data2[p10 + 2]);
            int arg1 = toInt(data2[p10 + 3]);
            int arg2 = toInt(data2[p10 + 4]);
            int arg3 = toInt(data2[p10 + 5]);
            Transition trans = edgeFactory(atn, ttype, src, trg, arg1, arg2, arg3, sets);
            ATNState srcState = atn.states.get(src);
            srcState.addTransition(trans);
            p10 += 6;
        }
        for (ATNState state2 : atn.states) {
            for (int i18 = 0; i18 < state2.getNumberOfTransitions(); i18++) {
                Transition t = state2.transition(i18);
                if (t instanceof RuleTransition) {
                    RuleTransition ruleTransition = (RuleTransition) t;
                    int outermostPrecedenceReturn = -1;
                    if (atn.ruleToStartState[ruleTransition.target.ruleIndex].isLeftRecursiveRule && ruleTransition.precedence == 0) {
                        outermostPrecedenceReturn = ruleTransition.target.ruleIndex;
                    }
                    EpsilonTransition returnTransition = new EpsilonTransition(ruleTransition.followState, outermostPrecedenceReturn);
                    atn.ruleToStopState[ruleTransition.target.ruleIndex].addTransition(returnTransition);
                }
            }
        }
        for (ATNState state3 : atn.states) {
            if (state3 instanceof BlockStartState) {
                if (((BlockStartState) state3).endState == null) {
                    throw new IllegalStateException();
                }
                if (((BlockStartState) state3).endState.startState != null) {
                    throw new IllegalStateException();
                }
                ((BlockStartState) state3).endState.startState = (BlockStartState) state3;
            }
            if (state3 instanceof PlusLoopbackState) {
                PlusLoopbackState loopbackState = (PlusLoopbackState) state3;
                for (int i19 = 0; i19 < loopbackState.getNumberOfTransitions(); i19++) {
                    ATNState target = loopbackState.transition(i19).target;
                    if (target instanceof PlusBlockStartState) {
                        ((PlusBlockStartState) target).loopBackState = loopbackState;
                    }
                }
            } else if (state3 instanceof StarLoopbackState) {
                StarLoopbackState loopbackState2 = (StarLoopbackState) state3;
                for (int i20 = 0; i20 < loopbackState2.getNumberOfTransitions(); i20++) {
                    ATNState target2 = loopbackState2.transition(i20).target;
                    if (target2 instanceof StarLoopEntryState) {
                        ((StarLoopEntryState) target2).loopBackState = loopbackState2;
                    }
                }
            }
        }
        int i21 = p10;
        int p11 = p10 + 1;
        int ndecisions = toInt(data2[i21]);
        for (int i22 = 1; i22 <= ndecisions; i22++) {
            int i23 = p11;
            p11++;
            int s4 = toInt(data2[i23]);
            DecisionState decState = (DecisionState) atn.states.get(s4);
            atn.decisionToState.add(decState);
            decState.decision = i22 - 1;
        }
        if (atn.grammarType == ATNType.LEXER) {
            if (supportsLexerActions) {
                int i24 = p11;
                int p12 = p11 + 1;
                atn.lexerActions = new LexerAction[toInt(data2[i24])];
                for (int i25 = 0; i25 < atn.lexerActions.length; i25++) {
                    int i26 = p12;
                    int p13 = p12 + 1;
                    LexerActionType actionType = LexerActionType.values()[toInt(data2[i26])];
                    int p14 = p13 + 1;
                    int data1 = toInt(data2[p13]);
                    if (data1 == 65535) {
                        data1 = -1;
                    }
                    p12 = p14 + 1;
                    int data22 = toInt(data2[p14]);
                    if (data22 == 65535) {
                        data22 = -1;
                    }
                    LexerAction lexerAction = lexerActionFactory(actionType, data1, data22);
                    atn.lexerActions[i25] = lexerAction;
                }
            } else {
                List<LexerAction> legacyLexerActions = new ArrayList<>();
                for (ATNState state4 : atn.states) {
                    for (int i27 = 0; i27 < state4.getNumberOfTransitions(); i27++) {
                        Transition transition = state4.transition(i27);
                        if (transition instanceof ActionTransition) {
                            int ruleIndex2 = ((ActionTransition) transition).ruleIndex;
                            int actionIndex = ((ActionTransition) transition).actionIndex;
                            LexerCustomAction lexerAction2 = new LexerCustomAction(ruleIndex2, actionIndex);
                            state4.setTransition(i27, new ActionTransition(transition.target, ruleIndex2, legacyLexerActions.size(), false));
                            legacyLexerActions.add(lexerAction2);
                        }
                    }
                }
                atn.lexerActions = (LexerAction[]) legacyLexerActions.toArray(new LexerAction[legacyLexerActions.size()]);
            }
        }
        markPrecedenceDecisions(atn);
        if (this.deserializationOptions.isVerifyATN()) {
            verifyATN(atn);
        }
        if (this.deserializationOptions.isGenerateRuleBypassTransitions() && atn.grammarType == ATNType.PARSER) {
            atn.ruleToTokenType = new int[atn.ruleToStartState.length];
            for (int i28 = 0; i28 < atn.ruleToStartState.length; i28++) {
                atn.ruleToTokenType[i28] = atn.maxTokenType + i28 + 1;
            }
            for (int i29 = 0; i29 < atn.ruleToStartState.length; i29++) {
                BasicBlockStartState bypassStart = new BasicBlockStartState();
                bypassStart.ruleIndex = i29;
                atn.addState(bypassStart);
                BlockEndState bypassStop = new BlockEndState();
                bypassStop.ruleIndex = i29;
                atn.addState(bypassStop);
                bypassStart.endState = bypassStop;
                atn.defineDecisionState(bypassStart);
                bypassStop.startState = bypassStart;
                Transition excludeTransition = null;
                if (atn.ruleToStartState[i29].isLeftRecursiveRule) {
                    endState = null;
                    Iterator i$ = atn.states.iterator();
                    while (true) {
                        if (!i$.hasNext()) {
                            break;
                        }
                        ATNState state5 = i$.next();
                        if (state5.ruleIndex == i29 && (state5 instanceof StarLoopEntryState)) {
                            ATNState maybeLoopEndState = state5.transition(state5.getNumberOfTransitions() - 1).target;
                            if ((maybeLoopEndState instanceof LoopEndState) && maybeLoopEndState.epsilonOnlyTransitions && (maybeLoopEndState.transition(0).target instanceof RuleStopState)) {
                                endState = state5;
                                break;
                            }
                        }
                    }
                    if (endState == null) {
                        throw new UnsupportedOperationException("Couldn't identify final state of the precedence rule prefix section.");
                    }
                    excludeTransition = ((StarLoopEntryState) endState).loopBackState.transition(0);
                } else {
                    endState = atn.ruleToStopState[i29];
                }
                Iterator i$2 = atn.states.iterator();
                while (i$2.hasNext()) {
                    for (Transition transition2 : i$2.next().transitions) {
                        if (transition2 != excludeTransition && transition2.target == endState) {
                            transition2.target = bypassStop;
                        }
                    }
                }
                while (atn.ruleToStartState[i29].getNumberOfTransitions() > 0) {
                    bypassStart.addTransition(atn.ruleToStartState[i29].removeTransition(atn.ruleToStartState[i29].getNumberOfTransitions() - 1));
                }
                atn.ruleToStartState[i29].addTransition(new EpsilonTransition(bypassStart));
                bypassStop.addTransition(new EpsilonTransition(endState));
                ATNState matchState = new BasicState();
                atn.addState(matchState);
                matchState.addTransition(new AtomTransition(bypassStop, atn.ruleToTokenType[i29]));
                bypassStart.addTransition(new EpsilonTransition(matchState));
            }
            if (this.deserializationOptions.isVerifyATN()) {
                verifyATN(atn);
            }
        }
        return atn;
    }

    private int deserializeSets(char[] data, int p, List<IntervalSet> sets, UnicodeDeserializer unicodeDeserializer) {
        int p2 = p + 1;
        int nsets = toInt(data[p]);
        for (int i = 0; i < nsets; i++) {
            int nintervals = toInt(data[p2]);
            int p3 = p2 + 1;
            IntervalSet set = new IntervalSet(new int[0]);
            sets.add(set);
            p2 = p3 + 1;
            boolean containsEof = toInt(data[p3]) != 0;
            if (containsEof) {
                set.add(-1);
            }
            for (int j = 0; j < nintervals; j++) {
                int a = unicodeDeserializer.readUnicode(data, p2);
                int p4 = p2 + unicodeDeserializer.size();
                int b = unicodeDeserializer.readUnicode(data, p4);
                p2 = p4 + unicodeDeserializer.size();
                set.add(a, b);
            }
        }
        return p2;
    }

    protected void markPrecedenceDecisions(ATN atn) {
        for (ATNState state : atn.states) {
            if ((state instanceof StarLoopEntryState) && atn.ruleToStartState[state.ruleIndex].isLeftRecursiveRule) {
                ATNState maybeLoopEndState = state.transition(state.getNumberOfTransitions() - 1).target;
                if ((maybeLoopEndState instanceof LoopEndState) && maybeLoopEndState.epsilonOnlyTransitions && (maybeLoopEndState.transition(0).target instanceof RuleStopState)) {
                    ((StarLoopEntryState) state).isPrecedenceDecision = true;
                }
            }
        }
    }

    protected void verifyATN(ATN atn) {
        for (ATNState state : atn.states) {
            if (state != null) {
                checkCondition(state.onlyHasEpsilonTransitions() || state.getNumberOfTransitions() <= 1);
                if (state instanceof PlusBlockStartState) {
                    checkCondition(((PlusBlockStartState) state).loopBackState != null);
                }
                if (state instanceof StarLoopEntryState) {
                    StarLoopEntryState starLoopEntryState = (StarLoopEntryState) state;
                    checkCondition(starLoopEntryState.loopBackState != null);
                    checkCondition(starLoopEntryState.getNumberOfTransitions() == 2);
                    if (starLoopEntryState.transition(0).target instanceof StarBlockStartState) {
                        checkCondition(starLoopEntryState.transition(1).target instanceof LoopEndState);
                        checkCondition(!starLoopEntryState.nonGreedy);
                    } else if (starLoopEntryState.transition(0).target instanceof LoopEndState) {
                        checkCondition(starLoopEntryState.transition(1).target instanceof StarBlockStartState);
                        checkCondition(starLoopEntryState.nonGreedy);
                    } else {
                        throw new IllegalStateException();
                    }
                }
                if (state instanceof StarLoopbackState) {
                    checkCondition(state.getNumberOfTransitions() == 1);
                    checkCondition(state.transition(0).target instanceof StarLoopEntryState);
                }
                if (state instanceof LoopEndState) {
                    checkCondition(((LoopEndState) state).loopBackState != null);
                }
                if (state instanceof RuleStartState) {
                    checkCondition(((RuleStartState) state).stopState != null);
                }
                if (state instanceof BlockStartState) {
                    checkCondition(((BlockStartState) state).endState != null);
                }
                if (state instanceof BlockEndState) {
                    checkCondition(((BlockEndState) state).startState != null);
                }
                if (state instanceof DecisionState) {
                    DecisionState decisionState = (DecisionState) state;
                    checkCondition(decisionState.getNumberOfTransitions() <= 1 || decisionState.decision >= 0);
                } else {
                    checkCondition(state.getNumberOfTransitions() <= 1 || (state instanceof RuleStopState));
                }
            }
        }
    }

    protected void checkCondition(boolean condition) {
        checkCondition(condition, null);
    }

    protected void checkCondition(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }

    protected static int toInt(char c) {
        return c;
    }

    protected static int toInt32(char[] data, int offset) {
        return data[offset] | (data[offset + 1] << 16);
    }

    protected static long toLong(char[] data, int offset) {
        long lowOrder = toInt32(data, offset) & 4294967295L;
        return lowOrder | (toInt32(data, offset + 2) << 32);
    }

    protected static UUID toUUID(char[] data, int offset) {
        long leastSigBits = toLong(data, offset);
        long mostSigBits = toLong(data, offset + 4);
        return new UUID(mostSigBits, leastSigBits);
    }

    protected Transition edgeFactory(ATN atn, int type, int src, int trg, int arg1, int arg2, int arg3, List<IntervalSet> sets) {
        ATNState target = atn.states.get(trg);
        switch (type) {
            case 1:
                return new EpsilonTransition(target);
            case 2:
                if (arg3 != 0) {
                    return new RangeTransition(target, -1, arg2);
                }
                return new RangeTransition(target, arg1, arg2);
            case 3:
                RuleTransition rt = new RuleTransition((RuleStartState) atn.states.get(arg1), arg2, arg3, target);
                return rt;
            case 4:
                PredicateTransition pt = new PredicateTransition(target, arg1, arg2, arg3 != 0);
                return pt;
            case 5:
                if (arg3 != 0) {
                    return new AtomTransition(target, -1);
                }
                return new AtomTransition(target, arg1);
            case 6:
                ActionTransition a = new ActionTransition(target, arg1, arg2, arg3 != 0);
                return a;
            case 7:
                return new SetTransition(target, sets.get(arg1));
            case 8:
                return new NotSetTransition(target, sets.get(arg1));
            case 9:
                return new WildcardTransition(target);
            case 10:
                return new PrecedencePredicateTransition(target, arg1);
            default:
                throw new IllegalArgumentException("The specified transition type is not valid.");
        }
    }

    protected ATNState stateFactory(int type, int ruleIndex) {
        ATNState s;
        switch (type) {
            case 0:
                return null;
            case 1:
                s = new BasicState();
                break;
            case 2:
                s = new RuleStartState();
                break;
            case 3:
                s = new BasicBlockStartState();
                break;
            case 4:
                s = new PlusBlockStartState();
                break;
            case 5:
                s = new StarBlockStartState();
                break;
            case 6:
                s = new TokensStartState();
                break;
            case 7:
                s = new RuleStopState();
                break;
            case 8:
                s = new BlockEndState();
                break;
            case 9:
                s = new StarLoopbackState();
                break;
            case 10:
                s = new StarLoopEntryState();
                break;
            case 11:
                s = new PlusLoopbackState();
                break;
            case 12:
                s = new LoopEndState();
                break;
            default:
                String message = String.format(Locale.getDefault(), "The specified state type %d is not valid.", Integer.valueOf(type));
                throw new IllegalArgumentException(message);
        }
        s.ruleIndex = ruleIndex;
        return s;
    }

    protected LexerAction lexerActionFactory(LexerActionType type, int data1, int data2) {
        switch (type) {
            case CHANNEL:
                return new LexerChannelAction(data1);
            case CUSTOM:
                return new LexerCustomAction(data1, data2);
            case MODE:
                return new LexerModeAction(data1);
            case MORE:
                return LexerMoreAction.INSTANCE;
            case POP_MODE:
                return LexerPopModeAction.INSTANCE;
            case PUSH_MODE:
                return new LexerPushModeAction(data1);
            case SKIP:
                return LexerSkipAction.INSTANCE;
            case TYPE:
                return new LexerTypeAction(data1);
            default:
                String message = String.format(Locale.getDefault(), "The specified lexer action type %d is not valid.", type);
                throw new IllegalArgumentException(message);
        }
    }
}
