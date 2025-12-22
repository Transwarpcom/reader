package org.seimicrawler.xpath.antlr;

import java.util.List;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.fontbox.afm.AFMParser;
import org.mozilla.classfile.ByteCode;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser.class */
public class XpathParser extends Parser {
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache;
    public static final int T__0 = 1;
    public static final int T__1 = 2;
    public static final int T__2 = 3;
    public static final int T__3 = 4;
    public static final int NodeType = 5;
    public static final int Number = 6;
    public static final int AxisName = 7;
    public static final int PATHSEP = 8;
    public static final int ABRPATH = 9;
    public static final int LPAR = 10;
    public static final int RPAR = 11;
    public static final int LBRAC = 12;
    public static final int RBRAC = 13;
    public static final int MINUS = 14;
    public static final int PLUS = 15;
    public static final int DOT = 16;
    public static final int MUL = 17;
    public static final int DIVISION = 18;
    public static final int MODULO = 19;
    public static final int DOTDOT = 20;
    public static final int AT = 21;
    public static final int COMMA = 22;
    public static final int PIPE = 23;
    public static final int LESS = 24;
    public static final int MORE_ = 25;
    public static final int LE = 26;
    public static final int GE = 27;
    public static final int EQUALITY = 28;
    public static final int INEQUALITY = 29;
    public static final int START_WITH = 30;
    public static final int END_WITH = 31;
    public static final int CONTAIN_WITH = 32;
    public static final int REGEXP_WITH = 33;
    public static final int REGEXP_NOT_WITH = 34;
    public static final int COLON = 35;
    public static final int CC = 36;
    public static final int APOS = 37;
    public static final int QUOT = 38;
    public static final int Literal = 39;
    public static final int Whitespace = 40;
    public static final int NCName = 41;
    public static final int RULE_main = 0;
    public static final int RULE_locationPath = 1;
    public static final int RULE_absoluteLocationPathNoroot = 2;
    public static final int RULE_relativeLocationPath = 3;
    public static final int RULE_step = 4;
    public static final int RULE_axisSpecifier = 5;
    public static final int RULE_nodeTest = 6;
    public static final int RULE_predicate = 7;
    public static final int RULE_abbreviatedStep = 8;
    public static final int RULE_expr = 9;
    public static final int RULE_primaryExpr = 10;
    public static final int RULE_functionCall = 11;
    public static final int RULE_unionExprNoRoot = 12;
    public static final int RULE_pathExprNoRoot = 13;
    public static final int RULE_filterExpr = 14;
    public static final int RULE_orExpr = 15;
    public static final int RULE_andExpr = 16;
    public static final int RULE_equalityExpr = 17;
    public static final int RULE_relationalExpr = 18;
    public static final int RULE_additiveExpr = 19;
    public static final int RULE_multiplicativeExpr = 20;
    public static final int RULE_unaryExprNoRoot = 21;
    public static final int RULE_qName = 22;
    public static final int RULE_functionName = 23;
    public static final int RULE_variableReference = 24;
    public static final int RULE_nameTest = 25;
    public static final int RULE_nCName = 26;
    public static final String[] ruleNames;
    private static final String[] _LITERAL_NAMES;
    private static final String[] _SYMBOLIC_NAMES;
    public static final Vocabulary VOCABULARY;

    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN = "\u0003悋Ꜫ脳맭䅼㯧瞆奤\u0003+å\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004\f\t\f\u0004\r\t\r\u0004\u000e\t\u000e\u0004\u000f\t\u000f\u0004\u0010\t\u0010\u0004\u0011\t\u0011\u0004\u0012\t\u0012\u0004\u0013\t\u0013\u0004\u0014\t\u0014\u0004\u0015\t\u0015\u0004\u0016\t\u0016\u0004\u0017\t\u0017\u0004\u0018\t\u0018\u0004\u0019\t\u0019\u0004\u001a\t\u001a\u0004\u001b\t\u001b\u0004\u001c\t\u001c\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0005\u0003=\n\u0003\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0005\u0003\u0005\u0003\u0005\u0007\u0005E\n\u0005\f\u0005\u000e\u0005H\u000b\u0005\u0003\u0006\u0003\u0006\u0003\u0006\u0007\u0006M\n\u0006\f\u0006\u000e\u0006P\u000b\u0006\u0003\u0006\u0005\u0006S\n\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0005\u0007X\n\u0007\u0005\u0007Z\n\u0007\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0005\bd\n\b\u0003\t\u0003\t\u0003\t\u0003\t\u0003\n\u0003\n\u0003\u000b\u0003\u000b\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0005\fv\n\f\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0007\r}\n\r\f\r\u000e\r\u0080\u000b\r\u0005\r\u0082\n\r\u0003\r\u0003\r\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000e\u0089\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000e\u008e\n\u000e\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0005\u000f\u0094\n\u000f\u0005\u000f\u0096\n\u000f\u0003\u0010\u0003\u0010\u0007\u0010\u009a\n\u0010\f\u0010\u000e\u0010\u009d\u000b\u0010\u0003\u0011\u0003\u0011\u0003\u0011\u0007\u0011¢\n\u0011\f\u0011\u000e\u0011¥\u000b\u0011\u0003\u0012\u0003\u0012\u0003\u0012\u0007\u0012ª\n\u0012\f\u0012\u000e\u0012\u00ad\u000b\u0012\u0003\u0013\u0003\u0013\u0003\u0013\u0007\u0013²\n\u0013\f\u0013\u000e\u0013µ\u000b\u0013\u0003\u0014\u0003\u0014\u0003\u0014\u0007\u0014º\n\u0014\f\u0014\u000e\u0014½\u000b\u0014\u0003\u0015\u0003\u0015\u0003\u0015\u0007\u0015Â\n\u0015\f\u0015\u000e\u0015Å\u000b\u0015\u0003\u0016\u0003\u0016\u0003\u0016\u0005\u0016Ê\n\u0016\u0003\u0017\u0005\u0017Í\n\u0017\u0003\u0017\u0003\u0017\u0003\u0018\u0003\u0018\u0003\u0018\u0005\u0018Ô\n\u0018\u0003\u0019\u0003\u0019\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001b\u0003\u001b\u0003\u001b\u0003\u001b\u0003\u001b\u0003\u001b\u0005\u001bá\n\u001b\u0003\u001c\u0003\u001c\u0003\u001c\u0002\u0002\u001d\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.0246\u0002\t\u0003\u0002\n\u000b\u0004\u0002\u0012\u0012\u0016\u0016\u0003\u0002\u001e\u001f\u0005\u0002\u001a\u001b\u001d\u001d $\u0003\u0002\u0010\u0011\u0003\u0002\u0013\u0015\u0004\u0002\t\t++\u0002æ\u00028\u0003\u0002\u0002\u0002\u0004<\u0003\u0002\u0002\u0002\u0006>\u0003\u0002\u0002\u0002\bA\u0003\u0002\u0002\u0002\nR\u0003\u0002\u0002\u0002\fY\u0003\u0002\u0002\u0002\u000ec\u0003\u0002\u0002\u0002\u0010e\u0003\u0002\u0002\u0002\u0012i\u0003\u0002\u0002\u0002\u0014k\u0003\u0002\u0002\u0002\u0016u\u0003\u0002\u0002\u0002\u0018w\u0003\u0002\u0002\u0002\u001a\u008d\u0003\u0002\u0002\u0002\u001c\u0095\u0003\u0002\u0002\u0002\u001e\u0097\u0003\u0002\u0002\u0002 \u009e\u0003\u0002\u0002\u0002\"¦\u0003\u0002\u0002\u0002$®\u0003\u0002\u0002\u0002&¶\u0003\u0002\u0002\u0002(¾\u0003\u0002\u0002\u0002*Æ\u0003\u0002\u0002\u0002,Ì\u0003\u0002\u0002\u0002.Ð\u0003\u0002\u0002\u00020Õ\u0003\u0002\u0002\u00022×\u0003\u0002\u0002\u00024à\u0003\u0002\u0002\u00026â\u0003\u0002\u0002\u000289\u0005\u0014\u000b\u00029\u0003\u0003\u0002\u0002\u0002:=\u0005\b\u0005\u0002;=\u0005\u0006\u0004\u0002<:\u0003\u0002\u0002\u0002<;\u0003\u0002\u0002\u0002=\u0005\u0003\u0002\u0002\u0002>?\t\u0002\u0002\u0002?@\u0005\b\u0005\u0002@\u0007\u0003\u0002\u0002\u0002AF\u0005\n\u0006\u0002BC\t\u0002\u0002\u0002CE\u0005\n\u0006\u0002DB\u0003\u0002\u0002\u0002EH\u0003\u0002\u0002\u0002FD\u0003\u0002\u0002\u0002FG\u0003\u0002\u0002\u0002G\t\u0003\u0002\u0002\u0002HF\u0003\u0002\u0002\u0002IJ\u0005\f\u0007\u0002JN\u0005\u000e\b\u0002KM\u0005\u0010\t\u0002LK\u0003\u0002\u0002\u0002MP\u0003\u0002\u0002\u0002NL\u0003\u0002\u0002\u0002NO\u0003\u0002\u0002\u0002OS\u0003\u0002\u0002\u0002PN\u0003\u0002\u0002\u0002QS\u0005\u0012\n\u0002RI\u0003\u0002\u0002\u0002RQ\u0003\u0002\u0002\u0002S\u000b\u0003\u0002\u0002\u0002TU\u0007\t\u0002\u0002UZ\u0007&\u0002\u0002VX\u0007\u0017\u0002\u0002WV\u0003\u0002\u0002\u0002WX\u0003\u0002\u0002\u0002XZ\u0003\u0002\u0002\u0002YT\u0003\u0002\u0002\u0002YW\u0003\u0002\u0002\u0002Z\r\u0003\u0002\u0002\u0002[d\u00054\u001b\u0002\\]\u0007\u0007\u0002\u0002]^\u0007\f\u0002\u0002^d\u0007\r\u0002\u0002_`\u0007\u0003\u0002\u0002`a\u0007\f\u0002\u0002ab\u0007)\u0002\u0002bd\u0007\r\u0002\u0002c[\u0003\u0002\u0002\u0002c\\\u0003\u0002\u0002\u0002c_\u0003\u0002\u0002\u0002d\u000f\u0003\u0002\u0002\u0002ef\u0007\u000e\u0002\u0002fg\u0005\u0014\u000b\u0002gh\u0007\u000f\u0002\u0002h\u0011\u0003\u0002\u0002\u0002ij\t\u0003\u0002\u0002j\u0013\u0003\u0002\u0002\u0002kl\u0005 \u0011\u0002l\u0015\u0003\u0002\u0002\u0002mv\u00052\u001a\u0002no\u0007\f\u0002\u0002op\u0005\u0014\u000b\u0002pq\u0007\r\u0002\u0002qv\u0003\u0002\u0002\u0002rv\u0007)\u0002\u0002sv\u0007\b\u0002\u0002tv\u0005\u0018\r\u0002um\u0003\u0002\u0002\u0002un\u0003\u0002\u0002\u0002ur\u0003\u0002\u0002\u0002us\u0003\u0002\u0002\u0002ut\u0003\u0002\u0002\u0002v\u0017\u0003\u0002\u0002\u0002wx\u00050\u0019\u0002x\u0081\u0007\f\u0002\u0002y~\u0005\u0014\u000b\u0002z{\u0007\u0018\u0002\u0002{}\u0005\u0014\u000b\u0002|z\u0003\u0002\u0002\u0002}\u0080\u0003\u0002\u0002\u0002~|\u0003\u0002\u0002\u0002~\u007f\u0003\u0002\u0002\u0002\u007f\u0082\u0003\u0002\u0002\u0002\u0080~\u0003\u0002\u0002\u0002\u0081y\u0003\u0002\u0002\u0002\u0081\u0082\u0003\u0002\u0002\u0002\u0082\u0083\u0003\u0002\u0002\u0002\u0083\u0084\u0007\r\u0002\u0002\u0084\u0019\u0003\u0002\u0002\u0002\u0085\u0088\u0005\u001c\u000f\u0002\u0086\u0087\u0007\u0019\u0002\u0002\u0087\u0089\u0005\u001a\u000e\u0002\u0088\u0086\u0003\u0002\u0002\u0002\u0088\u0089\u0003\u0002\u0002\u0002\u0089\u008e\u0003\u0002\u0002\u0002\u008a\u008b\u0007\n\u0002\u0002\u008b\u008c\u0007\u0019\u0002\u0002\u008c\u008e\u0005\u001a\u000e\u0002\u008d\u0085\u0003\u0002\u0002\u0002\u008d\u008a\u0003\u0002\u0002\u0002\u008e\u001b\u0003\u0002\u0002\u0002\u008f\u0096\u0005\u0004\u0003\u0002\u0090\u0093\u0005\u001e\u0010\u0002\u0091\u0092\t\u0002\u0002\u0002\u0092\u0094\u0005\b\u0005\u0002\u0093\u0091\u0003\u0002\u0002\u0002\u0093\u0094\u0003\u0002\u0002\u0002\u0094\u0096\u0003\u0002\u0002\u0002\u0095\u008f\u0003\u0002\u0002\u0002\u0095\u0090\u0003\u0002\u0002\u0002\u0096\u001d\u0003\u0002\u0002\u0002\u0097\u009b\u0005\u0016\f\u0002\u0098\u009a\u0005\u0010\t\u0002\u0099\u0098\u0003\u0002\u0002\u0002\u009a\u009d\u0003\u0002\u0002\u0002\u009b\u0099\u0003\u0002\u0002\u0002\u009b\u009c\u0003\u0002\u0002\u0002\u009c\u001f\u0003\u0002\u0002\u0002\u009d\u009b\u0003\u0002\u0002\u0002\u009e£\u0005\"\u0012\u0002\u009f \u0007\u0004\u0002\u0002 ¢\u0005\"\u0012\u0002¡\u009f\u0003\u0002\u0002\u0002¢¥\u0003\u0002\u0002\u0002£¡\u0003\u0002\u0002\u0002£¤\u0003\u0002\u0002\u0002¤!\u0003\u0002\u0002\u0002¥£\u0003\u0002\u0002\u0002¦«\u0005$\u0013\u0002§¨\u0007\u0005\u0002\u0002¨ª\u0005$\u0013\u0002©§\u0003\u0002\u0002\u0002ª\u00ad\u0003\u0002\u0002\u0002«©\u0003\u0002\u0002\u0002«¬\u0003\u0002\u0002\u0002¬#\u0003\u0002\u0002\u0002\u00ad«\u0003\u0002\u0002\u0002®³\u0005&\u0014\u0002¯°\t\u0004\u0002\u0002°²\u0005&\u0014\u0002±¯\u0003\u0002\u0002\u0002²µ\u0003\u0002\u0002\u0002³±\u0003\u0002\u0002\u0002³´\u0003\u0002\u0002\u0002´%\u0003\u0002\u0002\u0002µ³\u0003\u0002\u0002\u0002¶»\u0005(\u0015\u0002·¸\t\u0005\u0002\u0002¸º\u0005(\u0015\u0002¹·\u0003\u0002\u0002\u0002º½\u0003\u0002\u0002\u0002»¹\u0003\u0002\u0002\u0002»¼\u0003\u0002\u0002\u0002¼'\u0003\u0002\u0002\u0002½»\u0003\u0002\u0002\u0002¾Ã\u0005*\u0016\u0002¿À\t\u0006\u0002\u0002ÀÂ\u0005*\u0016\u0002Á¿\u0003\u0002\u0002\u0002ÂÅ\u0003\u0002\u0002\u0002ÃÁ\u0003\u0002\u0002\u0002ÃÄ\u0003\u0002\u0002\u0002Ä)\u0003\u0002\u0002\u0002ÅÃ\u0003\u0002\u0002\u0002ÆÉ\u0005,\u0017\u0002ÇÈ\t\u0007\u0002\u0002ÈÊ\u0005*\u0016\u0002ÉÇ\u0003\u0002\u0002\u0002ÉÊ\u0003\u0002\u0002\u0002Ê+\u0003\u0002\u0002\u0002ËÍ\u0007\u0010\u0002\u0002ÌË\u0003\u0002\u0002\u0002ÌÍ\u0003\u0002\u0002\u0002ÍÎ\u0003\u0002\u0002\u0002ÎÏ\u0005\u001a\u000e\u0002Ï-\u0003\u0002\u0002\u0002ÐÓ\u00056\u001c\u0002ÑÒ\u0007%\u0002\u0002ÒÔ\u00056\u001c\u0002ÓÑ\u0003\u0002\u0002\u0002ÓÔ\u0003\u0002\u0002\u0002Ô/\u0003\u0002\u0002\u0002ÕÖ\u0005.\u0018\u0002Ö1\u0003\u0002\u0002\u0002×Ø\u0007\u0006\u0002\u0002ØÙ\u0005.\u0018\u0002Ù3\u0003\u0002\u0002\u0002Úá\u0007\u0013\u0002\u0002ÛÜ\u00056\u001c\u0002ÜÝ\u0007%\u0002\u0002ÝÞ\u0007\u0013\u0002\u0002Þá\u0003\u0002\u0002\u0002ßá\u0005.\u0018\u0002àÚ\u0003\u0002\u0002\u0002àÛ\u0003\u0002\u0002\u0002àß\u0003\u0002\u0002\u0002á5\u0003\u0002\u0002\u0002âã\t\b\u0002\u0002ã7\u0003\u0002\u0002\u0002\u001a<FNRWYcu~\u0081\u0088\u008d\u0093\u0095\u009b£«³»ÃÉÌÓà";
    public static final ATN _ATN;

    static {
        RuntimeMetaData.checkVersion(RuntimeMetaData.VERSION, RuntimeMetaData.VERSION);
        _sharedContextCache = new PredictionContextCache();
        ruleNames = makeRuleNames();
        _LITERAL_NAMES = makeLiteralNames();
        _SYMBOLIC_NAMES = makeSymbolicNames();
        VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }
            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
        _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i2 = 0; i2 < _ATN.getNumberOfDecisions(); i2++) {
            _decisionToDFA[i2] = new DFA(_ATN.getDecisionState(i2), i2);
        }
    }

    private static String[] makeRuleNames() {
        return new String[]{"main", "locationPath", "absoluteLocationPathNoroot", "relativeLocationPath", "step", "axisSpecifier", "nodeTest", "predicate", "abbreviatedStep", "expr", "primaryExpr", "functionCall", "unionExprNoRoot", "pathExprNoRoot", "filterExpr", "orExpr", "andExpr", "equalityExpr", "relationalExpr", "additiveExpr", "multiplicativeExpr", "unaryExprNoRoot", "qName", "functionName", "variableReference", "nameTest", "nCName"};
    }

    private static String[] makeLiteralNames() {
        return new String[]{null, "'processing-instruction'", "'or'", "'and'", "'$'", null, null, null, "'/'", "'//'", "'('", "')'", "'['", "']'", "'-'", "'+'", "'.'", "'*'", "'`div`'", "'`mod`'", "'..'", "'@'", "','", "'|'", "'<'", "'>'", "'<='", "'>='", "'='", "'!='", "'^='", "'$='", "'*='", "'~='", "'!~'", "':'", "'::'", "'''", "'\"'"};
    }

    private static String[] makeSymbolicNames() {
        return new String[]{null, null, null, null, null, "NodeType", "Number", "AxisName", "PATHSEP", "ABRPATH", "LPAR", "RPAR", "LBRAC", "RBRAC", "MINUS", "PLUS", "DOT", "MUL", "DIVISION", "MODULO", "DOTDOT", "AT", "COMMA", "PIPE", "LESS", "MORE_", "LE", "GE", "EQUALITY", "INEQUALITY", "START_WITH", "END_WITH", "CONTAIN_WITH", "REGEXP_WITH", "REGEXP_NOT_WITH", "COLON", AFMParser.CC, "APOS", "QUOT", "Literal", "Whitespace", "NCName"};
    }

    @Override // org.antlr.v4.runtime.Recognizer
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override // org.antlr.v4.runtime.Recognizer
    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    @Override // org.antlr.v4.runtime.Recognizer
    public String getGrammarFileName() {
        return "Xpath.g4";
    }

    @Override // org.antlr.v4.runtime.Recognizer
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override // org.antlr.v4.runtime.Recognizer
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override // org.antlr.v4.runtime.Recognizer
    public ATN getATN() {
        return _ATN;
    }

    public XpathParser(TokenStream input) {
        super(input);
        this._interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$MainContext.class */
    public static class MainContext extends ParserRuleContext {
        public ExprContext expr() {
            return (ExprContext) getRuleContext(ExprContext.class, 0);
        }

        public MainContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 0;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterMain(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitMain(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitMain(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final MainContext main() throws RecognitionException {
        MainContext _localctx = new MainContext(this._ctx, getState());
        enterRule(_localctx, 0, 0);
        try {
            enterOuterAlt(_localctx, 1);
            setState(54);
            expr();
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$LocationPathContext.class */
    public static class LocationPathContext extends ParserRuleContext {
        public RelativeLocationPathContext relativeLocationPath() {
            return (RelativeLocationPathContext) getRuleContext(RelativeLocationPathContext.class, 0);
        }

        public AbsoluteLocationPathNorootContext absoluteLocationPathNoroot() {
            return (AbsoluteLocationPathNorootContext) getRuleContext(AbsoluteLocationPathNorootContext.class, 0);
        }

        public LocationPathContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 1;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterLocationPath(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitLocationPath(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitLocationPath(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final LocationPathContext locationPath() throws RecognitionException {
        LocationPathContext _localctx = new LocationPathContext(this._ctx, getState());
        enterRule(_localctx, 2, 1);
        try {
            setState(58);
            this._errHandler.sync(this);
            switch (this._input.LA(1)) {
                case 1:
                case 5:
                case 7:
                case 16:
                case 17:
                case 20:
                case 21:
                case 41:
                    enterOuterAlt(_localctx, 1);
                    setState(56);
                    relativeLocationPath();
                    break;
                case 8:
                case 9:
                    enterOuterAlt(_localctx, 2);
                    setState(57);
                    absoluteLocationPathNoroot();
                    break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$AbsoluteLocationPathNorootContext.class */
    public static class AbsoluteLocationPathNorootContext extends ParserRuleContext {
        public Token op;

        public RelativeLocationPathContext relativeLocationPath() {
            return (RelativeLocationPathContext) getRuleContext(RelativeLocationPathContext.class, 0);
        }

        public TerminalNode PATHSEP() {
            return getToken(8, 0);
        }

        public TerminalNode ABRPATH() {
            return getToken(9, 0);
        }

        public AbsoluteLocationPathNorootContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 2;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterAbsoluteLocationPathNoroot(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitAbsoluteLocationPathNoroot(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitAbsoluteLocationPathNoroot(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final AbsoluteLocationPathNorootContext absoluteLocationPathNoroot() throws RecognitionException {
        AbsoluteLocationPathNorootContext _localctx = new AbsoluteLocationPathNorootContext(this._ctx, getState());
        enterRule(_localctx, 4, 2);
        try {
            try {
                enterOuterAlt(_localctx, 1);
                setState(60);
                _localctx.op = this._input.LT(1);
                int _la = this._input.LA(1);
                if (_la != 8 && _la != 9) {
                    _localctx.op = this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }
                    this._errHandler.reportMatch(this);
                    consume();
                }
                setState(61);
                relativeLocationPath();
                exitRule();
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } catch (Throwable th) {
            exitRule();
            throw th;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$RelativeLocationPathContext.class */
    public static class RelativeLocationPathContext extends ParserRuleContext {
        public Token op;

        public List<StepContext> step() {
            return getRuleContexts(StepContext.class);
        }

        public StepContext step(int i) {
            return (StepContext) getRuleContext(StepContext.class, i);
        }

        public List<TerminalNode> PATHSEP() {
            return getTokens(8);
        }

        public TerminalNode PATHSEP(int i) {
            return getToken(8, i);
        }

        public List<TerminalNode> ABRPATH() {
            return getTokens(9);
        }

        public TerminalNode ABRPATH(int i) {
            return getToken(9, i);
        }

        public RelativeLocationPathContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 3;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterRelativeLocationPath(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitRelativeLocationPath(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitRelativeLocationPath(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final RelativeLocationPathContext relativeLocationPath() throws RecognitionException {
        RelativeLocationPathContext _localctx = new RelativeLocationPathContext(this._ctx, getState());
        enterRule(_localctx, 6, 3);
        try {
            try {
                enterOuterAlt(_localctx, 1);
                setState(63);
                step();
                setState(68);
                this._errHandler.sync(this);
                int _la = this._input.LA(1);
                while (true) {
                    if (_la != 8 && _la != 9) {
                        break;
                    }
                    setState(64);
                    _localctx.op = this._input.LT(1);
                    int _la2 = this._input.LA(1);
                    if (_la2 != 8 && _la2 != 9) {
                        _localctx.op = this._errHandler.recoverInline(this);
                    } else {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }
                        this._errHandler.reportMatch(this);
                        consume();
                    }
                    setState(65);
                    step();
                    setState(70);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                }
                exitRule();
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } catch (Throwable th) {
            exitRule();
            throw th;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$StepContext.class */
    public static class StepContext extends ParserRuleContext {
        public AxisSpecifierContext axisSpecifier() {
            return (AxisSpecifierContext) getRuleContext(AxisSpecifierContext.class, 0);
        }

        public NodeTestContext nodeTest() {
            return (NodeTestContext) getRuleContext(NodeTestContext.class, 0);
        }

        public List<PredicateContext> predicate() {
            return getRuleContexts(PredicateContext.class);
        }

        public PredicateContext predicate(int i) {
            return (PredicateContext) getRuleContext(PredicateContext.class, i);
        }

        public AbbreviatedStepContext abbreviatedStep() {
            return (AbbreviatedStepContext) getRuleContext(AbbreviatedStepContext.class, 0);
        }

        public StepContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 4;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterStep(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitStep(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitStep(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final StepContext step() throws RecognitionException {
        StepContext _localctx = new StepContext(this._ctx, getState());
        enterRule(_localctx, 8, 4);
        try {
            try {
                setState(80);
                this._errHandler.sync(this);
                switch (this._input.LA(1)) {
                    case 1:
                    case 5:
                    case 7:
                    case 17:
                    case 21:
                    case 41:
                        enterOuterAlt(_localctx, 1);
                        setState(71);
                        axisSpecifier();
                        setState(72);
                        nodeTest();
                        setState(76);
                        this._errHandler.sync(this);
                        int _la = this._input.LA(1);
                        while (_la == 12) {
                            setState(73);
                            predicate();
                            setState(78);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);
                        }
                        break;
                    case 16:
                    case 20:
                        enterOuterAlt(_localctx, 2);
                        setState(79);
                        abbreviatedStep();
                        break;
                    default:
                        throw new NoViableAltException(this);
                }
                exitRule();
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } catch (Throwable th) {
            exitRule();
            throw th;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$AxisSpecifierContext.class */
    public static class AxisSpecifierContext extends ParserRuleContext {
        public TerminalNode AxisName() {
            return getToken(7, 0);
        }

        public TerminalNode CC() {
            return getToken(36, 0);
        }

        public TerminalNode AT() {
            return getToken(21, 0);
        }

        public AxisSpecifierContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 5;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterAxisSpecifier(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitAxisSpecifier(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitAxisSpecifier(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final AxisSpecifierContext axisSpecifier() throws RecognitionException {
        AxisSpecifierContext _localctx = new AxisSpecifierContext(this._ctx, getState());
        enterRule(_localctx, 10, 5);
        try {
            try {
                setState(87);
                this._errHandler.sync(this);
                switch (((ParserATNSimulator) getInterpreter()).adaptivePredict(this._input, 5, this._ctx)) {
                    case 1:
                        enterOuterAlt(_localctx, 1);
                        setState(82);
                        match(7);
                        setState(83);
                        match(36);
                        break;
                    case 2:
                        enterOuterAlt(_localctx, 2);
                        setState(85);
                        this._errHandler.sync(this);
                        int _la = this._input.LA(1);
                        if (_la == 21) {
                            setState(84);
                            match(21);
                            break;
                        }
                        break;
                }
                exitRule();
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } catch (Throwable th) {
            exitRule();
            throw th;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$NodeTestContext.class */
    public static class NodeTestContext extends ParserRuleContext {
        public NameTestContext nameTest() {
            return (NameTestContext) getRuleContext(NameTestContext.class, 0);
        }

        public TerminalNode NodeType() {
            return getToken(5, 0);
        }

        public TerminalNode LPAR() {
            return getToken(10, 0);
        }

        public TerminalNode RPAR() {
            return getToken(11, 0);
        }

        public TerminalNode Literal() {
            return getToken(39, 0);
        }

        public NodeTestContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 6;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterNodeTest(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitNodeTest(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitNodeTest(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final NodeTestContext nodeTest() throws RecognitionException {
        NodeTestContext _localctx = new NodeTestContext(this._ctx, getState());
        enterRule(_localctx, 12, 6);
        try {
            setState(97);
            this._errHandler.sync(this);
            switch (this._input.LA(1)) {
                case 1:
                    enterOuterAlt(_localctx, 3);
                    setState(93);
                    match(1);
                    setState(94);
                    match(10);
                    setState(95);
                    match(39);
                    setState(96);
                    match(11);
                    break;
                case 5:
                    enterOuterAlt(_localctx, 2);
                    setState(90);
                    match(5);
                    setState(91);
                    match(10);
                    setState(92);
                    match(11);
                    break;
                case 7:
                case 17:
                case 41:
                    enterOuterAlt(_localctx, 1);
                    setState(89);
                    nameTest();
                    break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$PredicateContext.class */
    public static class PredicateContext extends ParserRuleContext {
        public TerminalNode LBRAC() {
            return getToken(12, 0);
        }

        public ExprContext expr() {
            return (ExprContext) getRuleContext(ExprContext.class, 0);
        }

        public TerminalNode RBRAC() {
            return getToken(13, 0);
        }

        public PredicateContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 7;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterPredicate(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitPredicate(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitPredicate(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final PredicateContext predicate() throws RecognitionException {
        PredicateContext _localctx = new PredicateContext(this._ctx, getState());
        enterRule(_localctx, 14, 7);
        try {
            enterOuterAlt(_localctx, 1);
            setState(99);
            match(12);
            setState(100);
            expr();
            setState(101);
            match(13);
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$AbbreviatedStepContext.class */
    public static class AbbreviatedStepContext extends ParserRuleContext {
        public TerminalNode DOT() {
            return getToken(16, 0);
        }

        public TerminalNode DOTDOT() {
            return getToken(20, 0);
        }

        public AbbreviatedStepContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 8;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterAbbreviatedStep(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitAbbreviatedStep(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitAbbreviatedStep(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final AbbreviatedStepContext abbreviatedStep() throws RecognitionException {
        AbbreviatedStepContext _localctx = new AbbreviatedStepContext(this._ctx, getState());
        enterRule(_localctx, 16, 8);
        try {
            try {
                enterOuterAlt(_localctx, 1);
                setState(103);
                int _la = this._input.LA(1);
                if (_la != 16 && _la != 20) {
                    this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }
                    this._errHandler.reportMatch(this);
                    consume();
                }
                exitRule();
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } catch (Throwable th) {
            exitRule();
            throw th;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$ExprContext.class */
    public static class ExprContext extends ParserRuleContext {
        public OrExprContext orExpr() {
            return (OrExprContext) getRuleContext(OrExprContext.class, 0);
        }

        public ExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 9;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitExpr(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final ExprContext expr() throws RecognitionException {
        ExprContext _localctx = new ExprContext(this._ctx, getState());
        enterRule(_localctx, 18, 9);
        try {
            enterOuterAlt(_localctx, 1);
            setState(105);
            orExpr();
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$PrimaryExprContext.class */
    public static class PrimaryExprContext extends ParserRuleContext {
        public VariableReferenceContext variableReference() {
            return (VariableReferenceContext) getRuleContext(VariableReferenceContext.class, 0);
        }

        public TerminalNode LPAR() {
            return getToken(10, 0);
        }

        public ExprContext expr() {
            return (ExprContext) getRuleContext(ExprContext.class, 0);
        }

        public TerminalNode RPAR() {
            return getToken(11, 0);
        }

        public TerminalNode Literal() {
            return getToken(39, 0);
        }

        public TerminalNode Number() {
            return getToken(6, 0);
        }

        public FunctionCallContext functionCall() {
            return (FunctionCallContext) getRuleContext(FunctionCallContext.class, 0);
        }

        public PrimaryExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 10;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterPrimaryExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitPrimaryExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitPrimaryExpr(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final PrimaryExprContext primaryExpr() throws RecognitionException {
        PrimaryExprContext _localctx = new PrimaryExprContext(this._ctx, getState());
        enterRule(_localctx, 20, 10);
        try {
            setState(115);
            this._errHandler.sync(this);
            switch (this._input.LA(1)) {
                case 4:
                    enterOuterAlt(_localctx, 1);
                    setState(107);
                    variableReference();
                    break;
                case 6:
                    enterOuterAlt(_localctx, 4);
                    setState(113);
                    match(6);
                    break;
                case 7:
                case 41:
                    enterOuterAlt(_localctx, 5);
                    setState(114);
                    functionCall();
                    break;
                case 10:
                    enterOuterAlt(_localctx, 2);
                    setState(108);
                    match(10);
                    setState(109);
                    expr();
                    setState(110);
                    match(11);
                    break;
                case 39:
                    enterOuterAlt(_localctx, 3);
                    setState(112);
                    match(39);
                    break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$FunctionCallContext.class */
    public static class FunctionCallContext extends ParserRuleContext {
        public FunctionNameContext functionName() {
            return (FunctionNameContext) getRuleContext(FunctionNameContext.class, 0);
        }

        public TerminalNode LPAR() {
            return getToken(10, 0);
        }

        public TerminalNode RPAR() {
            return getToken(11, 0);
        }

        public List<ExprContext> expr() {
            return getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return (ExprContext) getRuleContext(ExprContext.class, i);
        }

        public List<TerminalNode> COMMA() {
            return getTokens(22);
        }

        public TerminalNode COMMA(int i) {
            return getToken(22, i);
        }

        public FunctionCallContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 11;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterFunctionCall(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitFunctionCall(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitFunctionCall(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final FunctionCallContext functionCall() throws RecognitionException {
        FunctionCallContext _localctx = new FunctionCallContext(this._ctx, getState());
        enterRule(_localctx, 22, 11);
        try {
            try {
                enterOuterAlt(_localctx, 1);
                setState(117);
                functionName();
                setState(118);
                match(10);
                setState(127);
                this._errHandler.sync(this);
                int _la = this._input.LA(1);
                if ((_la & (-64)) == 0 && ((1 << _la) & 2748782430194L) != 0) {
                    setState(119);
                    expr();
                    setState(124);
                    this._errHandler.sync(this);
                    int _la2 = this._input.LA(1);
                    while (_la2 == 22) {
                        setState(120);
                        match(22);
                        setState(121);
                        expr();
                        setState(126);
                        this._errHandler.sync(this);
                        _la2 = this._input.LA(1);
                    }
                }
                setState(129);
                match(11);
                exitRule();
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } catch (Throwable th) {
            exitRule();
            throw th;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$UnionExprNoRootContext.class */
    public static class UnionExprNoRootContext extends ParserRuleContext {
        public Token op;

        public PathExprNoRootContext pathExprNoRoot() {
            return (PathExprNoRootContext) getRuleContext(PathExprNoRootContext.class, 0);
        }

        public UnionExprNoRootContext unionExprNoRoot() {
            return (UnionExprNoRootContext) getRuleContext(UnionExprNoRootContext.class, 0);
        }

        public TerminalNode PIPE() {
            return getToken(23, 0);
        }

        public TerminalNode PATHSEP() {
            return getToken(8, 0);
        }

        public UnionExprNoRootContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 12;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterUnionExprNoRoot(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitUnionExprNoRoot(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitUnionExprNoRoot(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final UnionExprNoRootContext unionExprNoRoot() throws RecognitionException {
        UnionExprNoRootContext _localctx = new UnionExprNoRootContext(this._ctx, getState());
        enterRule(_localctx, 24, 12);
        try {
            try {
                setState(139);
                this._errHandler.sync(this);
                switch (((ParserATNSimulator) getInterpreter()).adaptivePredict(this._input, 11, this._ctx)) {
                    case 1:
                        enterOuterAlt(_localctx, 1);
                        setState(131);
                        pathExprNoRoot();
                        setState(134);
                        this._errHandler.sync(this);
                        int _la = this._input.LA(1);
                        if (_la == 23) {
                            setState(132);
                            _localctx.op = match(23);
                            setState(133);
                            unionExprNoRoot();
                            break;
                        }
                        break;
                    case 2:
                        enterOuterAlt(_localctx, 2);
                        setState(136);
                        match(8);
                        setState(137);
                        match(23);
                        setState(138);
                        unionExprNoRoot();
                        break;
                }
                exitRule();
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } catch (Throwable th) {
            exitRule();
            throw th;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$PathExprNoRootContext.class */
    public static class PathExprNoRootContext extends ParserRuleContext {
        public Token op;

        public LocationPathContext locationPath() {
            return (LocationPathContext) getRuleContext(LocationPathContext.class, 0);
        }

        public FilterExprContext filterExpr() {
            return (FilterExprContext) getRuleContext(FilterExprContext.class, 0);
        }

        public RelativeLocationPathContext relativeLocationPath() {
            return (RelativeLocationPathContext) getRuleContext(RelativeLocationPathContext.class, 0);
        }

        public TerminalNode PATHSEP() {
            return getToken(8, 0);
        }

        public TerminalNode ABRPATH() {
            return getToken(9, 0);
        }

        public PathExprNoRootContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 13;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterPathExprNoRoot(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitPathExprNoRoot(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitPathExprNoRoot(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final PathExprNoRootContext pathExprNoRoot() throws RecognitionException {
        PathExprNoRootContext _localctx = new PathExprNoRootContext(this._ctx, getState());
        enterRule(_localctx, 26, 13);
        try {
            try {
                setState(147);
                this._errHandler.sync(this);
                switch (((ParserATNSimulator) getInterpreter()).adaptivePredict(this._input, 13, this._ctx)) {
                    case 1:
                        enterOuterAlt(_localctx, 1);
                        setState(141);
                        locationPath();
                        break;
                    case 2:
                        enterOuterAlt(_localctx, 2);
                        setState(142);
                        filterExpr();
                        setState(145);
                        this._errHandler.sync(this);
                        int _la = this._input.LA(1);
                        if (_la == 8 || _la == 9) {
                            setState(143);
                            _localctx.op = this._input.LT(1);
                            int _la2 = this._input.LA(1);
                            if (_la2 != 8 && _la2 != 9) {
                                _localctx.op = this._errHandler.recoverInline(this);
                            } else {
                                if (this._input.LA(1) == -1) {
                                    this.matchedEOF = true;
                                }
                                this._errHandler.reportMatch(this);
                                consume();
                            }
                            setState(144);
                            relativeLocationPath();
                            break;
                        }
                        break;
                }
                exitRule();
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } catch (Throwable th) {
            exitRule();
            throw th;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$FilterExprContext.class */
    public static class FilterExprContext extends ParserRuleContext {
        public PrimaryExprContext primaryExpr() {
            return (PrimaryExprContext) getRuleContext(PrimaryExprContext.class, 0);
        }

        public List<PredicateContext> predicate() {
            return getRuleContexts(PredicateContext.class);
        }

        public PredicateContext predicate(int i) {
            return (PredicateContext) getRuleContext(PredicateContext.class, i);
        }

        public FilterExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 14;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterFilterExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitFilterExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitFilterExpr(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final FilterExprContext filterExpr() throws RecognitionException {
        FilterExprContext _localctx = new FilterExprContext(this._ctx, getState());
        enterRule(_localctx, 28, 14);
        try {
            try {
                enterOuterAlt(_localctx, 1);
                setState(149);
                primaryExpr();
                setState(153);
                this._errHandler.sync(this);
                int _la = this._input.LA(1);
                while (_la == 12) {
                    setState(150);
                    predicate();
                    setState(155);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                }
                exitRule();
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } catch (Throwable th) {
            exitRule();
            throw th;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$OrExprContext.class */
    public static class OrExprContext extends ParserRuleContext {
        public List<AndExprContext> andExpr() {
            return getRuleContexts(AndExprContext.class);
        }

        public AndExprContext andExpr(int i) {
            return (AndExprContext) getRuleContext(AndExprContext.class, i);
        }

        public OrExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 15;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterOrExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitOrExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitOrExpr(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final OrExprContext orExpr() throws RecognitionException {
        OrExprContext _localctx = new OrExprContext(this._ctx, getState());
        enterRule(_localctx, 30, 15);
        try {
            try {
                enterOuterAlt(_localctx, 1);
                setState(156);
                andExpr();
                setState(161);
                this._errHandler.sync(this);
                int _la = this._input.LA(1);
                while (_la == 2) {
                    setState(157);
                    match(2);
                    setState(158);
                    andExpr();
                    setState(163);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                }
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } finally {
            exitRule();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$AndExprContext.class */
    public static class AndExprContext extends ParserRuleContext {
        public List<EqualityExprContext> equalityExpr() {
            return getRuleContexts(EqualityExprContext.class);
        }

        public EqualityExprContext equalityExpr(int i) {
            return (EqualityExprContext) getRuleContext(EqualityExprContext.class, i);
        }

        public AndExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 16;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterAndExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitAndExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitAndExpr(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final AndExprContext andExpr() throws RecognitionException {
        AndExprContext _localctx = new AndExprContext(this._ctx, getState());
        enterRule(_localctx, 32, 16);
        try {
            try {
                enterOuterAlt(_localctx, 1);
                setState(164);
                equalityExpr();
                setState(169);
                this._errHandler.sync(this);
                int _la = this._input.LA(1);
                while (_la == 3) {
                    setState(165);
                    match(3);
                    setState(166);
                    equalityExpr();
                    setState(171);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                }
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } finally {
            exitRule();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$EqualityExprContext.class */
    public static class EqualityExprContext extends ParserRuleContext {
        public Token op;

        public List<RelationalExprContext> relationalExpr() {
            return getRuleContexts(RelationalExprContext.class);
        }

        public RelationalExprContext relationalExpr(int i) {
            return (RelationalExprContext) getRuleContext(RelationalExprContext.class, i);
        }

        public List<TerminalNode> EQUALITY() {
            return getTokens(28);
        }

        public TerminalNode EQUALITY(int i) {
            return getToken(28, i);
        }

        public List<TerminalNode> INEQUALITY() {
            return getTokens(29);
        }

        public TerminalNode INEQUALITY(int i) {
            return getToken(29, i);
        }

        public EqualityExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 17;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterEqualityExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitEqualityExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitEqualityExpr(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final EqualityExprContext equalityExpr() throws RecognitionException {
        EqualityExprContext _localctx = new EqualityExprContext(this._ctx, getState());
        enterRule(_localctx, 34, 17);
        try {
            try {
                enterOuterAlt(_localctx, 1);
                setState(172);
                relationalExpr();
                setState(177);
                this._errHandler.sync(this);
                int _la = this._input.LA(1);
                while (true) {
                    if (_la != 28 && _la != 29) {
                        break;
                    }
                    setState(173);
                    _localctx.op = this._input.LT(1);
                    int _la2 = this._input.LA(1);
                    if (_la2 != 28 && _la2 != 29) {
                        _localctx.op = this._errHandler.recoverInline(this);
                    } else {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }
                        this._errHandler.reportMatch(this);
                        consume();
                    }
                    setState(174);
                    relationalExpr();
                    setState(179);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                }
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } finally {
            exitRule();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$RelationalExprContext.class */
    public static class RelationalExprContext extends ParserRuleContext {
        public Token op;

        public List<AdditiveExprContext> additiveExpr() {
            return getRuleContexts(AdditiveExprContext.class);
        }

        public AdditiveExprContext additiveExpr(int i) {
            return (AdditiveExprContext) getRuleContext(AdditiveExprContext.class, i);
        }

        public List<TerminalNode> LESS() {
            return getTokens(24);
        }

        public TerminalNode LESS(int i) {
            return getToken(24, i);
        }

        public List<TerminalNode> MORE_() {
            return getTokens(25);
        }

        public TerminalNode MORE_(int i) {
            return getToken(25, i);
        }

        public List<TerminalNode> GE() {
            return getTokens(27);
        }

        public TerminalNode GE(int i) {
            return getToken(27, i);
        }

        public List<TerminalNode> START_WITH() {
            return getTokens(30);
        }

        public TerminalNode START_WITH(int i) {
            return getToken(30, i);
        }

        public List<TerminalNode> END_WITH() {
            return getTokens(31);
        }

        public TerminalNode END_WITH(int i) {
            return getToken(31, i);
        }

        public List<TerminalNode> CONTAIN_WITH() {
            return getTokens(32);
        }

        public TerminalNode CONTAIN_WITH(int i) {
            return getToken(32, i);
        }

        public List<TerminalNode> REGEXP_WITH() {
            return getTokens(33);
        }

        public TerminalNode REGEXP_WITH(int i) {
            return getToken(33, i);
        }

        public List<TerminalNode> REGEXP_NOT_WITH() {
            return getTokens(34);
        }

        public TerminalNode REGEXP_NOT_WITH(int i) {
            return getToken(34, i);
        }

        public RelationalExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 18;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterRelationalExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitRelationalExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitRelationalExpr(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final RelationalExprContext relationalExpr() throws RecognitionException {
        RelationalExprContext _localctx = new RelationalExprContext(this._ctx, getState());
        enterRule(_localctx, 36, 18);
        try {
            try {
                enterOuterAlt(_localctx, 1);
                setState(180);
                additiveExpr();
                setState(185);
                this._errHandler.sync(this);
                int _la = this._input.LA(1);
                while ((_la & (-64)) == 0 && ((1 << _la) & 33470545920L) != 0) {
                    setState(181);
                    _localctx.op = this._input.LT(1);
                    int _la2 = this._input.LA(1);
                    if ((_la2 & (-64)) != 0 || ((1 << _la2) & 33470545920L) == 0) {
                        _localctx.op = this._errHandler.recoverInline(this);
                    } else {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }
                        this._errHandler.reportMatch(this);
                        consume();
                    }
                    setState(182);
                    additiveExpr();
                    setState(187);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                }
                exitRule();
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } catch (Throwable th) {
            exitRule();
            throw th;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$AdditiveExprContext.class */
    public static class AdditiveExprContext extends ParserRuleContext {
        public Token op;

        public List<MultiplicativeExprContext> multiplicativeExpr() {
            return getRuleContexts(MultiplicativeExprContext.class);
        }

        public MultiplicativeExprContext multiplicativeExpr(int i) {
            return (MultiplicativeExprContext) getRuleContext(MultiplicativeExprContext.class, i);
        }

        public List<TerminalNode> PLUS() {
            return getTokens(15);
        }

        public TerminalNode PLUS(int i) {
            return getToken(15, i);
        }

        public List<TerminalNode> MINUS() {
            return getTokens(14);
        }

        public TerminalNode MINUS(int i) {
            return getToken(14, i);
        }

        public AdditiveExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 19;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterAdditiveExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitAdditiveExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitAdditiveExpr(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final AdditiveExprContext additiveExpr() throws RecognitionException {
        AdditiveExprContext _localctx = new AdditiveExprContext(this._ctx, getState());
        enterRule(_localctx, 38, 19);
        try {
            try {
                enterOuterAlt(_localctx, 1);
                setState(188);
                multiplicativeExpr();
                setState(193);
                this._errHandler.sync(this);
                int _la = this._input.LA(1);
                while (true) {
                    if (_la != 14 && _la != 15) {
                        break;
                    }
                    setState(189);
                    _localctx.op = this._input.LT(1);
                    int _la2 = this._input.LA(1);
                    if (_la2 != 14 && _la2 != 15) {
                        _localctx.op = this._errHandler.recoverInline(this);
                    } else {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }
                        this._errHandler.reportMatch(this);
                        consume();
                    }
                    setState(190);
                    multiplicativeExpr();
                    setState(195);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                }
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } finally {
            exitRule();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$MultiplicativeExprContext.class */
    public static class MultiplicativeExprContext extends ParserRuleContext {
        public Token op;

        public UnaryExprNoRootContext unaryExprNoRoot() {
            return (UnaryExprNoRootContext) getRuleContext(UnaryExprNoRootContext.class, 0);
        }

        public MultiplicativeExprContext multiplicativeExpr() {
            return (MultiplicativeExprContext) getRuleContext(MultiplicativeExprContext.class, 0);
        }

        public TerminalNode MUL() {
            return getToken(17, 0);
        }

        public TerminalNode DIVISION() {
            return getToken(18, 0);
        }

        public TerminalNode MODULO() {
            return getToken(19, 0);
        }

        public MultiplicativeExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 20;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterMultiplicativeExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitMultiplicativeExpr(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitMultiplicativeExpr(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final MultiplicativeExprContext multiplicativeExpr() throws RecognitionException {
        MultiplicativeExprContext _localctx = new MultiplicativeExprContext(this._ctx, getState());
        enterRule(_localctx, 40, 20);
        try {
            try {
                enterOuterAlt(_localctx, 1);
                setState(196);
                unaryExprNoRoot();
                setState(199);
                this._errHandler.sync(this);
                int _la = this._input.LA(1);
                if ((_la & (-64)) == 0 && ((1 << _la) & 917504) != 0) {
                    setState(197);
                    _localctx.op = this._input.LT(1);
                    int _la2 = this._input.LA(1);
                    if ((_la2 & (-64)) != 0 || ((1 << _la2) & 917504) == 0) {
                        _localctx.op = this._errHandler.recoverInline(this);
                    } else {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }
                        this._errHandler.reportMatch(this);
                        consume();
                    }
                    setState(198);
                    multiplicativeExpr();
                }
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } finally {
            exitRule();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$UnaryExprNoRootContext.class */
    public static class UnaryExprNoRootContext extends ParserRuleContext {
        public Token sign;

        public UnionExprNoRootContext unionExprNoRoot() {
            return (UnionExprNoRootContext) getRuleContext(UnionExprNoRootContext.class, 0);
        }

        public TerminalNode MINUS() {
            return getToken(14, 0);
        }

        public UnaryExprNoRootContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 21;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterUnaryExprNoRoot(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitUnaryExprNoRoot(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitUnaryExprNoRoot(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final UnaryExprNoRootContext unaryExprNoRoot() throws RecognitionException {
        UnaryExprNoRootContext _localctx = new UnaryExprNoRootContext(this._ctx, getState());
        enterRule(_localctx, 42, 21);
        try {
            try {
                enterOuterAlt(_localctx, 1);
                setState(ByteCode.BREAKPOINT);
                this._errHandler.sync(this);
                int _la = this._input.LA(1);
                if (_la == 14) {
                    setState(ByteCode.JSR_W);
                    _localctx.sign = match(14);
                }
                setState(204);
                unionExprNoRoot();
                exitRule();
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } catch (Throwable th) {
            exitRule();
            throw th;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$QNameContext.class */
    public static class QNameContext extends ParserRuleContext {
        public List<NCNameContext> nCName() {
            return getRuleContexts(NCNameContext.class);
        }

        public NCNameContext nCName(int i) {
            return (NCNameContext) getRuleContext(NCNameContext.class, i);
        }

        public TerminalNode COLON() {
            return getToken(35, 0);
        }

        public QNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 22;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterQName(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitQName(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitQName(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final QNameContext qName() throws RecognitionException {
        QNameContext _localctx = new QNameContext(this._ctx, getState());
        enterRule(_localctx, 44, 22);
        try {
            try {
                enterOuterAlt(_localctx, 1);
                setState(206);
                nCName();
                setState(209);
                this._errHandler.sync(this);
                int _la = this._input.LA(1);
                if (_la == 35) {
                    setState(207);
                    match(35);
                    setState(208);
                    nCName();
                }
                exitRule();
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } catch (Throwable th) {
            exitRule();
            throw th;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$FunctionNameContext.class */
    public static class FunctionNameContext extends ParserRuleContext {
        public QNameContext qName() {
            return (QNameContext) getRuleContext(QNameContext.class, 0);
        }

        public FunctionNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 23;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterFunctionName(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitFunctionName(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitFunctionName(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final FunctionNameContext functionName() throws RecognitionException {
        FunctionNameContext _localctx = new FunctionNameContext(this._ctx, getState());
        enterRule(_localctx, 46, 23);
        try {
            enterOuterAlt(_localctx, 1);
            setState(211);
            qName();
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$VariableReferenceContext.class */
    public static class VariableReferenceContext extends ParserRuleContext {
        public QNameContext qName() {
            return (QNameContext) getRuleContext(QNameContext.class, 0);
        }

        public VariableReferenceContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 24;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterVariableReference(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitVariableReference(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitVariableReference(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final VariableReferenceContext variableReference() throws RecognitionException {
        VariableReferenceContext _localctx = new VariableReferenceContext(this._ctx, getState());
        enterRule(_localctx, 48, 24);
        try {
            enterOuterAlt(_localctx, 1);
            setState(213);
            match(4);
            setState(214);
            qName();
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$NameTestContext.class */
    public static class NameTestContext extends ParserRuleContext {
        public TerminalNode MUL() {
            return getToken(17, 0);
        }

        public NCNameContext nCName() {
            return (NCNameContext) getRuleContext(NCNameContext.class, 0);
        }

        public TerminalNode COLON() {
            return getToken(35, 0);
        }

        public QNameContext qName() {
            return (QNameContext) getRuleContext(QNameContext.class, 0);
        }

        public NameTestContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 25;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterNameTest(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitNameTest(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitNameTest(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final NameTestContext nameTest() throws RecognitionException {
        NameTestContext _localctx = new NameTestContext(this._ctx, getState());
        enterRule(_localctx, 50, 25);
        try {
            setState(222);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator) getInterpreter()).adaptivePredict(this._input, 23, this._ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                    setState(216);
                    match(17);
                    break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                    setState(217);
                    nCName();
                    setState(218);
                    match(35);
                    setState(219);
                    match(17);
                    break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                    setState(221);
                    qName();
                    break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError(this, re);
            this._errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/antlr/XpathParser$NCNameContext.class */
    public static class NCNameContext extends ParserRuleContext {
        public TerminalNode NCName() {
            return getToken(41, 0);
        }

        public TerminalNode AxisName() {
            return getToken(7, 0);
        }

        public NCNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override // org.antlr.v4.runtime.RuleContext
        public int getRuleIndex() {
            return 26;
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).enterNCName(this);
            }
        }

        @Override // org.antlr.v4.runtime.ParserRuleContext
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof XpathListener) {
                ((XpathListener) listener).exitNCName(this);
            }
        }

        @Override // org.antlr.v4.runtime.RuleContext, org.antlr.v4.runtime.tree.ParseTree
        public <T> T accept(ParseTreeVisitor<? extends T> parseTreeVisitor) {
            return parseTreeVisitor instanceof XpathVisitor ? (T) ((XpathVisitor) parseTreeVisitor).visitNCName(this) : parseTreeVisitor.visitChildren(this);
        }
    }

    public final NCNameContext nCName() throws RecognitionException {
        NCNameContext _localctx = new NCNameContext(this._ctx, getState());
        enterRule(_localctx, 52, 26);
        try {
            try {
                enterOuterAlt(_localctx, 1);
                setState(224);
                int _la = this._input.LA(1);
                if (_la != 7 && _la != 41) {
                    this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }
                    this._errHandler.reportMatch(this);
                    consume();
                }
                exitRule();
            } catch (RecognitionException re) {
                _localctx.exception = re;
                this._errHandler.reportError(this, re);
                this._errHandler.recover(this, re);
                exitRule();
            }
            return _localctx;
        } catch (Throwable th) {
            exitRule();
            throw th;
        }
    }
}
