package io.legado.app.model.analyzeRule;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.ReadContext;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AnalyzeByJSonPath.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\u0018�� \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u001d\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH��¢\u0006\u0002\b\nJ\u0015\u0010\u000b\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH��¢\u0006\u0002\b\fJ\u0010\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\tJ\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000f2\u0006\u0010\b\u001a\u00020\tH��¢\u0006\u0002\b\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0012"}, d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeByJSonPath;", "", "json", "(Ljava/lang/Object;)V", "ctx", "Lcom/jayway/jsonpath/ReadContext;", "getList", "Ljava/util/ArrayList;", "rule", "", "getList$reader_pro", "getObject", "getObject$reader_pro", "getString", "getStringList", "", "getStringList$reader_pro", "Companion", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeByJSonPath.class */
public final class AnalyzeByJSonPath {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private ReadContext ctx;

    public AnalyzeByJSonPath(@NotNull Object json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.ctx = Companion.parse(json);
    }

    /* compiled from: AnalyzeByJSonPath.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001¨\u0006\u0006"}, d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeByJSonPath$Companion;", "", "()V", "parse", "Lcom/jayway/jsonpath/ReadContext;", "json", "reader-pro"})
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeByJSonPath$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final ReadContext parse(@NotNull Object json) {
            Intrinsics.checkNotNullParameter(json, "json");
            if (json instanceof ReadContext) {
                return (ReadContext) json;
            }
            if (json instanceof String) {
                DocumentContext documentContext = JsonPath.parse((String) json);
                Intrinsics.checkNotNullExpressionValue(documentContext, "parse(json)");
                return documentContext;
            }
            DocumentContext documentContext2 = JsonPath.parse(json);
            Intrinsics.checkNotNullExpressionValue(documentContext2, "parse(json)");
            return documentContext2;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:16:0x0085
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getString(@org.jetbrains.annotations.NotNull java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.analyzeRule.AnalyzeByJSonPath.getString(java.lang.String):java.lang.String");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:16:0x0097
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getStringList$reader_pro(@org.jetbrains.annotations.NotNull java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 557
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.analyzeRule.AnalyzeByJSonPath.getStringList$reader_pro(java.lang.String):java.util.List");
    }

    @NotNull
    public final Object getObject$reader_pro(@NotNull String rule) {
        Intrinsics.checkNotNullParameter(rule, "rule");
        Object obj = this.ctx.read(rule, new Predicate[0]);
        Intrinsics.checkNotNullExpressionValue(obj, "ctx.read(rule)");
        return obj;
    }

    @Nullable
    public final ArrayList<Object> getList$reader_pro(@NotNull String rule) {
        Object it;
        Intrinsics.checkNotNullParameter(rule, "rule");
        ArrayList result = new ArrayList();
        if (rule.length() == 0) {
            return result;
        }
        RuleAnalyzer ruleAnalyzes = new RuleAnalyzer(rule, true);
        ArrayList rules = ruleAnalyzes.splitRule("&&", "||", "%%");
        if (rules.size() == 1) {
            try {
                return (ArrayList) this.ctx.read(rules.get(0), new Predicate[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ArrayList results = new ArrayList();
            Iterator<String> it2 = rules.iterator();
            while (it2.hasNext()) {
                String rl = it2.next();
                Intrinsics.checkNotNullExpressionValue(rl, "rl");
                ArrayList temp = getList$reader_pro(rl);
                if (temp != null) {
                    if (!temp.isEmpty()) {
                        results.add(temp);
                        if ((!temp.isEmpty()) && Intrinsics.areEqual(ruleAnalyzes.getElementsType(), "||")) {
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            }
            if (results.size() > 0) {
                if (Intrinsics.areEqual("%%", ruleAnalyzes.getElementsType())) {
                    int i = 0;
                    int size = ((ArrayList) results.get(0)).size();
                    if (0 < size) {
                        do {
                            int i2 = i;
                            i++;
                            Iterator it3 = results.iterator();
                            while (it3.hasNext()) {
                                ArrayList temp2 = (ArrayList) it3.next();
                                if (i2 < temp2.size() && (it = temp2.get(i2)) != null) {
                                    result.add(it);
                                }
                            }
                        } while (i < size);
                    }
                } else {
                    Iterator it4 = results.iterator();
                    while (it4.hasNext()) {
                        result.addAll((ArrayList) it4.next());
                    }
                }
            }
        }
        return result;
    }
}
