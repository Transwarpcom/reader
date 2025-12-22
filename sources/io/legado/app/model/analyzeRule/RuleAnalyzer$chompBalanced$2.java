package io.legado.app.model.analyzeRule;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: RuleAnalyzer.kt */
@Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/RuleAnalyzer$chompBalanced$2.class */
/* synthetic */ class RuleAnalyzer$chompBalanced$2 extends FunctionReferenceImpl implements Function2<Character, Character, Boolean> {
    RuleAnalyzer$chompBalanced$2(RuleAnalyzer ruleAnalyzer) {
        super(2, ruleAnalyzer, RuleAnalyzer.class, "chompRuleBalanced", "chompRuleBalanced(CC)Z", 0);
    }

    public final boolean invoke(char p0, char p1) {
        return ((RuleAnalyzer) this.receiver).chompRuleBalanced(p0, p1);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Boolean invoke(Character ch2, Character ch3) {
        return Boolean.valueOf(invoke(ch2.charValue(), ch3.charValue()));
    }
}
