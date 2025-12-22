package org.antlr.v4.runtime.atn;

import java.io.InvalidClassException;
import java.lang.Character;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.misc.IntegerList;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.misc.Utils;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/ATNSerializer.class */
public class ATNSerializer {
    public ATN atn;
    private List<String> tokenNames;
    static final /* synthetic */ boolean $assertionsDisabled;

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/ATNSerializer$CodePointSerializer.class */
    private interface CodePointSerializer {
        void serializeCodePoint(IntegerList integerList, int i);
    }

    static {
        $assertionsDisabled = !ATNSerializer.class.desiredAssertionStatus();
    }

    public ATNSerializer(ATN atn) {
        if (!$assertionsDisabled && atn.grammarType == null) {
            throw new AssertionError();
        }
        this.atn = atn;
    }

    public ATNSerializer(ATN atn, List<String> tokenNames) {
        if (!$assertionsDisabled && atn.grammarType == null) {
            throw new AssertionError();
        }
        this.atn = atn;
        this.tokenNames = tokenNames;
    }

    public IntegerList serialize() {
        IntegerList data = new IntegerList();
        data.add(ATNDeserializer.SERIALIZED_VERSION);
        serializeUUID(data, ATNDeserializer.SERIALIZED_UUID);
        data.add(this.atn.grammarType.ordinal());
        data.add(this.atn.maxTokenType);
        int nedges = 0;
        Map<IntervalSet, Boolean> sets = new LinkedHashMap<>();
        IntegerList nonGreedyStates = new IntegerList();
        IntegerList precedenceStates = new IntegerList();
        data.add(this.atn.states.size());
        for (ATNState s : this.atn.states) {
            if (s == null) {
                data.add(0);
            } else {
                int stateType = s.getStateType();
                if ((s instanceof DecisionState) && ((DecisionState) s).nonGreedy) {
                    nonGreedyStates.add(s.stateNumber);
                }
                if ((s instanceof RuleStartState) && ((RuleStartState) s).isLeftRecursiveRule) {
                    precedenceStates.add(s.stateNumber);
                }
                data.add(stateType);
                if (s.ruleIndex == -1) {
                    data.add(65535);
                } else {
                    data.add(s.ruleIndex);
                }
                if (s.getStateType() == 12) {
                    data.add(((LoopEndState) s).loopBackState.stateNumber);
                } else if (s instanceof BlockStartState) {
                    data.add(((BlockStartState) s).endState.stateNumber);
                }
                if (s.getStateType() != 7) {
                    nedges += s.getNumberOfTransitions();
                }
                for (int i = 0; i < s.getNumberOfTransitions(); i++) {
                    Transition t = s.transition(i);
                    int edgeType = Transition.serializationTypes.get(t.getClass()).intValue();
                    if (edgeType == 7 || edgeType == 8) {
                        SetTransition st = (SetTransition) t;
                        sets.put(st.set, true);
                    }
                }
            }
        }
        data.add(nonGreedyStates.size());
        for (int i2 = 0; i2 < nonGreedyStates.size(); i2++) {
            data.add(nonGreedyStates.get(i2));
        }
        data.add(precedenceStates.size());
        for (int i3 = 0; i3 < precedenceStates.size(); i3++) {
            data.add(precedenceStates.get(i3));
        }
        int nrules = this.atn.ruleToStartState.length;
        data.add(nrules);
        for (int r = 0; r < nrules; r++) {
            ATNState ruleStartState = this.atn.ruleToStartState[r];
            data.add(ruleStartState.stateNumber);
            if (this.atn.grammarType == ATNType.LEXER) {
                if (this.atn.ruleToTokenType[r] == -1) {
                    data.add(65535);
                } else {
                    data.add(this.atn.ruleToTokenType[r]);
                }
            }
        }
        int nmodes = this.atn.modeToStartState.size();
        data.add(nmodes);
        if (nmodes > 0) {
            for (TokensStartState modeStartState : this.atn.modeToStartState) {
                data.add(modeStartState.stateNumber);
            }
        }
        List<IntervalSet> bmpSets = new ArrayList<>();
        List<IntervalSet> smpSets = new ArrayList<>();
        for (IntervalSet set : sets.keySet()) {
            if (set.getMaxElement() <= 65535) {
                bmpSets.add(set);
            } else {
                smpSets.add(set);
            }
        }
        serializeSets(data, bmpSets, new CodePointSerializer() { // from class: org.antlr.v4.runtime.atn.ATNSerializer.1
            @Override // org.antlr.v4.runtime.atn.ATNSerializer.CodePointSerializer
            public void serializeCodePoint(IntegerList data2, int cp) {
                data2.add(cp);
            }
        });
        serializeSets(data, smpSets, new CodePointSerializer() { // from class: org.antlr.v4.runtime.atn.ATNSerializer.2
            @Override // org.antlr.v4.runtime.atn.ATNSerializer.CodePointSerializer
            public void serializeCodePoint(IntegerList data2, int cp) {
                ATNSerializer.this.serializeInt(data2, cp);
            }
        });
        Map<IntervalSet, Integer> setIndices = new HashMap<>();
        int setIndex = 0;
        for (IntervalSet bmpSet : bmpSets) {
            int i4 = setIndex;
            setIndex++;
            setIndices.put(bmpSet, Integer.valueOf(i4));
        }
        for (IntervalSet smpSet : smpSets) {
            int i5 = setIndex;
            setIndex++;
            setIndices.put(smpSet, Integer.valueOf(i5));
        }
        data.add(nedges);
        for (ATNState s2 : this.atn.states) {
            if (s2 != null && s2.getStateType() != 7) {
                for (int i6 = 0; i6 < s2.getNumberOfTransitions(); i6++) {
                    Transition t2 = s2.transition(i6);
                    if (this.atn.states.get(t2.target.stateNumber) == null) {
                        throw new IllegalStateException("Cannot serialize a transition to a removed state.");
                    }
                    int src = s2.stateNumber;
                    int trg = t2.target.stateNumber;
                    int edgeType2 = Transition.serializationTypes.get(t2.getClass()).intValue();
                    int arg1 = 0;
                    int arg2 = 0;
                    int arg3 = 0;
                    switch (edgeType2) {
                        case 2:
                            arg1 = ((RangeTransition) t2).from;
                            arg2 = ((RangeTransition) t2).to;
                            if (arg1 == -1) {
                                arg1 = 0;
                                arg3 = 1;
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            trg = ((RuleTransition) t2).followState.stateNumber;
                            arg1 = ((RuleTransition) t2).target.stateNumber;
                            arg2 = ((RuleTransition) t2).ruleIndex;
                            arg3 = ((RuleTransition) t2).precedence;
                            break;
                        case 4:
                            PredicateTransition pt = (PredicateTransition) t2;
                            arg1 = pt.ruleIndex;
                            arg2 = pt.predIndex;
                            arg3 = pt.isCtxDependent ? 1 : 0;
                            break;
                        case 5:
                            arg1 = ((AtomTransition) t2).label;
                            if (arg1 == -1) {
                                arg1 = 0;
                                arg3 = 1;
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            ActionTransition at = (ActionTransition) t2;
                            arg1 = at.ruleIndex;
                            arg2 = at.actionIndex;
                            if (arg2 == -1) {
                                arg2 = 65535;
                            }
                            arg3 = at.isCtxDependent ? 1 : 0;
                            break;
                        case 7:
                            arg1 = setIndices.get(((SetTransition) t2).set).intValue();
                            break;
                        case 8:
                            arg1 = setIndices.get(((SetTransition) t2).set).intValue();
                            break;
                        case 10:
                            PrecedencePredicateTransition ppt = (PrecedencePredicateTransition) t2;
                            arg1 = ppt.precedence;
                            break;
                    }
                    data.add(src);
                    data.add(trg);
                    data.add(edgeType2);
                    data.add(arg1);
                    data.add(arg2);
                    data.add(arg3);
                }
            }
        }
        int ndecisions = this.atn.decisionToState.size();
        data.add(ndecisions);
        for (DecisionState decStartState : this.atn.decisionToState) {
            data.add(decStartState.stateNumber);
        }
        if (this.atn.grammarType == ATNType.LEXER) {
            data.add(this.atn.lexerActions.length);
            LexerAction[] arr$ = this.atn.lexerActions;
            for (LexerAction action : arr$) {
                data.add(action.getActionType().ordinal());
                switch (action.getActionType()) {
                    case CHANNEL:
                        int channel = ((LexerChannelAction) action).getChannel();
                        data.add(channel != -1 ? channel : 65535);
                        data.add(0);
                        break;
                    case CUSTOM:
                        int ruleIndex = ((LexerCustomAction) action).getRuleIndex();
                        int actionIndex = ((LexerCustomAction) action).getActionIndex();
                        data.add(ruleIndex != -1 ? ruleIndex : 65535);
                        data.add(actionIndex != -1 ? actionIndex : 65535);
                        break;
                    case MODE:
                        int mode = ((LexerModeAction) action).getMode();
                        data.add(mode != -1 ? mode : 65535);
                        data.add(0);
                        break;
                    case MORE:
                        data.add(0);
                        data.add(0);
                        break;
                    case POP_MODE:
                        data.add(0);
                        data.add(0);
                        break;
                    case PUSH_MODE:
                        int mode2 = ((LexerPushModeAction) action).getMode();
                        data.add(mode2 != -1 ? mode2 : 65535);
                        data.add(0);
                        break;
                    case SKIP:
                        data.add(0);
                        data.add(0);
                        break;
                    case TYPE:
                        int type = ((LexerTypeAction) action).getType();
                        data.add(type != -1 ? type : 65535);
                        data.add(0);
                        break;
                    default:
                        String message = String.format(Locale.getDefault(), "The specified lexer action type %s is not valid.", action.getActionType());
                        throw new IllegalArgumentException(message);
                }
            }
        }
        for (int i7 = 1; i7 < data.size(); i7++) {
            if (data.get(i7) < 0 || data.get(i7) > 65535) {
                throw new UnsupportedOperationException("Serialized ATN data element " + data.get(i7) + " element " + i7 + " out of range 0..65535");
            }
            int value = (data.get(i7) + 2) & 65535;
            data.set(i7, value);
        }
        return data;
    }

    private static void serializeSets(IntegerList data, Collection<IntervalSet> sets, CodePointSerializer codePointSerializer) {
        int nSets = sets.size();
        data.add(nSets);
        for (IntervalSet set : sets) {
            boolean containsEof = set.contains(-1);
            if (containsEof && set.getIntervals().get(0).b == -1) {
                data.add(set.getIntervals().size() - 1);
            } else {
                data.add(set.getIntervals().size());
            }
            data.add(containsEof ? 1 : 0);
            for (Interval I : set.getIntervals()) {
                if (I.a == -1) {
                    if (I.b != -1) {
                        codePointSerializer.serializeCodePoint(data, 0);
                    }
                } else {
                    codePointSerializer.serializeCodePoint(data, I.a);
                }
                codePointSerializer.serializeCodePoint(data, I.b);
            }
        }
    }

    public String decode(char[] data) {
        char[] data2 = (char[]) data.clone();
        for (int i = 1; i < data2.length; i++) {
            data2[i] = (char) (data2[i] - 2);
        }
        StringBuilder buf = new StringBuilder();
        int p = 0 + 1;
        int version = ATNDeserializer.toInt(data2[0]);
        if (version != ATNDeserializer.SERIALIZED_VERSION) {
            String reason = String.format("Could not deserialize ATN with version %d (expected %d).", Integer.valueOf(version), Integer.valueOf(ATNDeserializer.SERIALIZED_VERSION));
            throw new UnsupportedOperationException(new InvalidClassException(ATN.class.getName(), reason));
        }
        UUID uuid = ATNDeserializer.toUUID(data2, p);
        int p2 = p + 8;
        if (!uuid.equals(ATNDeserializer.SERIALIZED_UUID)) {
            String reason2 = String.format(Locale.getDefault(), "Could not deserialize ATN with UUID %s (expected %s).", uuid, ATNDeserializer.SERIALIZED_UUID);
            throw new UnsupportedOperationException(new InvalidClassException(ATN.class.getName(), reason2));
        }
        int p3 = p2 + 1;
        int p4 = p3 + 1;
        int maxType = ATNDeserializer.toInt(data2[p3]);
        buf.append("max type ").append(maxType).append("\n");
        int p5 = p4 + 1;
        int nstates = ATNDeserializer.toInt(data2[p4]);
        for (int i2 = 0; i2 < nstates; i2++) {
            int i3 = p5;
            p5++;
            int stype = ATNDeserializer.toInt(data2[i3]);
            if (stype != 0) {
                p5++;
                int ruleIndex = ATNDeserializer.toInt(data2[p5]);
                if (ruleIndex == 65535) {
                    ruleIndex = -1;
                }
                String arg = "";
                if (stype == 12) {
                    p5++;
                    int loopBackStateNumber = ATNDeserializer.toInt(data2[p5]);
                    arg = " " + loopBackStateNumber;
                } else if (stype == 4 || stype == 5 || stype == 3) {
                    p5++;
                    int endStateNumber = ATNDeserializer.toInt(data2[p5]);
                    arg = " " + endStateNumber;
                }
                buf.append(i2).append(":").append(ATNState.serializationNames.get(stype)).append(" ").append(ruleIndex).append(arg).append("\n");
            }
        }
        int i4 = p5;
        int p6 = p5 + 1;
        int numNonGreedyStates = ATNDeserializer.toInt(data2[i4]);
        for (int i5 = 0; i5 < numNonGreedyStates; i5++) {
            int i6 = p6;
            p6++;
            ATNDeserializer.toInt(data2[i6]);
        }
        int i7 = p6;
        int p7 = p6 + 1;
        int numPrecedenceStates = ATNDeserializer.toInt(data2[i7]);
        for (int i8 = 0; i8 < numPrecedenceStates; i8++) {
            int i9 = p7;
            p7++;
            ATNDeserializer.toInt(data2[i9]);
        }
        int i10 = p7;
        int p8 = p7 + 1;
        int nrules = ATNDeserializer.toInt(data2[i10]);
        for (int i11 = 0; i11 < nrules; i11++) {
            int i12 = p8;
            p8++;
            int s = ATNDeserializer.toInt(data2[i12]);
            if (this.atn.grammarType == ATNType.LEXER) {
                p8++;
                int arg1 = ATNDeserializer.toInt(data2[p8]);
                buf.append("rule ").append(i11).append(":").append(s).append(" ").append(arg1).append('\n');
            } else {
                buf.append("rule ").append(i11).append(":").append(s).append('\n');
            }
        }
        int i13 = p8;
        int p9 = p8 + 1;
        int nmodes = ATNDeserializer.toInt(data2[i13]);
        for (int i14 = 0; i14 < nmodes; i14++) {
            int i15 = p9;
            p9++;
            int s2 = ATNDeserializer.toInt(data2[i15]);
            buf.append("mode ").append(i14).append(":").append(s2).append('\n');
        }
        int numBMPSets = ATNDeserializer.toInt(data2[p9]);
        int p10 = appendSets(buf, data2, p9 + 1, numBMPSets, 0, ATNDeserializer.getUnicodeDeserializer(ATNDeserializer.UnicodeDeserializingMode.UNICODE_BMP));
        int p11 = p10 + 1;
        int numSMPSets = ATNDeserializer.toInt(data2[p10]);
        int p12 = appendSets(buf, data2, p11, numSMPSets, numBMPSets, ATNDeserializer.getUnicodeDeserializer(ATNDeserializer.UnicodeDeserializingMode.UNICODE_SMP));
        int p13 = p12 + 1;
        int nedges = ATNDeserializer.toInt(data2[p12]);
        for (int i16 = 0; i16 < nedges; i16++) {
            int src = ATNDeserializer.toInt(data2[p13]);
            int trg = ATNDeserializer.toInt(data2[p13 + 1]);
            int ttype = ATNDeserializer.toInt(data2[p13 + 2]);
            int arg12 = ATNDeserializer.toInt(data2[p13 + 3]);
            int arg2 = ATNDeserializer.toInt(data2[p13 + 4]);
            int arg3 = ATNDeserializer.toInt(data2[p13 + 5]);
            buf.append(src).append("->").append(trg).append(" ").append(Transition.serializationNames.get(ttype)).append(" ").append(arg12).append(",").append(arg2).append(",").append(arg3).append("\n");
            p13 += 6;
        }
        int i17 = p13;
        int p14 = p13 + 1;
        int ndecisions = ATNDeserializer.toInt(data2[i17]);
        for (int i18 = 0; i18 < ndecisions; i18++) {
            int i19 = p14;
            p14++;
            int s3 = ATNDeserializer.toInt(data2[i19]);
            buf.append(i18).append(":").append(s3).append("\n");
        }
        if (this.atn.grammarType == ATNType.LEXER) {
            int i20 = p14;
            int p15 = p14 + 1;
            int lexerActionCount = ATNDeserializer.toInt(data2[i20]);
            for (int i21 = 0; i21 < lexerActionCount; i21++) {
                int i22 = p15;
                int p16 = p15 + 1;
                LexerActionType lexerActionType = LexerActionType.values()[ATNDeserializer.toInt(data2[i22])];
                int p17 = p16 + 1;
                ATNDeserializer.toInt(data2[p16]);
                p15 = p17 + 1;
                ATNDeserializer.toInt(data2[p17]);
            }
        }
        return buf.toString();
    }

    private int appendSets(StringBuilder buf, char[] data, int p, int nsets, int setIndexOffset, ATNDeserializer.UnicodeDeserializer unicodeDeserializer) {
        for (int i = 0; i < nsets; i++) {
            int i2 = p;
            int p2 = p + 1;
            int nintervals = ATNDeserializer.toInt(data[i2]);
            buf.append(i + setIndexOffset).append(":");
            p = p2 + 1;
            boolean containsEof = data[p2] != 0;
            if (containsEof) {
                buf.append(getTokenName(-1));
            }
            for (int j = 0; j < nintervals; j++) {
                if (containsEof || j > 0) {
                    buf.append(", ");
                }
                int a = unicodeDeserializer.readUnicode(data, p);
                int p3 = p + unicodeDeserializer.size();
                int b = unicodeDeserializer.readUnicode(data, p3);
                p = p3 + unicodeDeserializer.size();
                buf.append(getTokenName(a)).append("..").append(getTokenName(b));
            }
            buf.append("\n");
        }
        return p;
    }

    public String getTokenName(int t) {
        if (t == -1) {
            return "EOF";
        }
        if (this.atn.grammarType == ATNType.LEXER && t >= 0 && t <= 65535) {
            switch (t) {
                case 8:
                    return "'\\b'";
                case 9:
                    return "'\\t'";
                case 10:
                    return "'\\n'";
                case 12:
                    return "'\\f'";
                case 13:
                    return "'\\r'";
                case 39:
                    return "'\\''";
                case 92:
                    return "'\\\\'";
                default:
                    if (Character.UnicodeBlock.of((char) t) == Character.UnicodeBlock.BASIC_LATIN && !Character.isISOControl((char) t)) {
                        return '\'' + Character.toString((char) t) + '\'';
                    }
                    String hex = Integer.toHexString(t | 65536).toUpperCase().substring(1, 5);
                    String unicodeStr = "'\\u" + hex + OperatorName.SHOW_TEXT_LINE;
                    return unicodeStr;
            }
        }
        if (this.tokenNames != null && t >= 0 && t < this.tokenNames.size()) {
            return this.tokenNames.get(t);
        }
        return String.valueOf(t);
    }

    public static String getSerializedAsString(ATN atn) {
        return new String(getSerializedAsChars(atn));
    }

    public static IntegerList getSerialized(ATN atn) {
        return new ATNSerializer(atn).serialize();
    }

    public static char[] getSerializedAsChars(ATN atn) {
        return Utils.toCharArray(getSerialized(atn));
    }

    public static String getDecoded(ATN atn, List<String> tokenNames) {
        IntegerList serialized = getSerialized(atn);
        char[] data = Utils.toCharArray(serialized);
        return new ATNSerializer(atn, tokenNames).decode(data);
    }

    private void serializeUUID(IntegerList data, UUID uuid) {
        serializeLong(data, uuid.getLeastSignificantBits());
        serializeLong(data, uuid.getMostSignificantBits());
    }

    private void serializeLong(IntegerList data, long value) {
        serializeInt(data, (int) value);
        serializeInt(data, (int) (value >> 32));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void serializeInt(IntegerList data, int value) {
        data.add((char) value);
        data.add((char) (value >> 16));
    }
}
