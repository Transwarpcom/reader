package io.legado.app.model.analyzeRule;

import cn.hutool.core.text.StrPool;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.ag2s.epublib.epub.NCXDocumentV2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.JXNode;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: AnalyzeByJSoup.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000e\u0018�� \u00182\u00020\u0001:\u0003\u0018\u0019\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH��¢\u0006\u0002\b\nJ\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u0002J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\tH\u0002J\u0018\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\tH\u0002J\u0017\u0010\u0012\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\tH��¢\u0006\u0002\b\u0013J\u0015\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH��¢\u0006\u0002\b\u0015J\u001b\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u0011\u001a\u00020\tH��¢\u0006\u0002\b\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u001b"}, d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeByJSoup;", "", "doc", "(Ljava/lang/Object;)V", "element", "Lorg/jsoup/nodes/Element;", "getElements", "Lorg/jsoup/select/Elements;", "rule", "", "getElements$reader_pro", "temp", "getResultLast", "", "elements", "lastRule", "getResultList", "ruleStr", "getString", "getString$reader_pro", "getString0", "getString0$reader_pro", "getStringList", "getStringList$reader_pro", "Companion", "ElementsSingle", "SourceRule", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeByJSoup.class */
public final class AnalyzeByJSoup {

    @NotNull
    private Element element;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final String[] validKeys = {"class", "id", "tag", NCXDocumentV2.NCXTags.text, "children"};

    /* compiled from: AnalyzeByJSoup.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeByJSoup$Companion;", "", "()V", "validKeys", "", "", "getValidKeys", "()[Ljava/lang/String;", "[Ljava/lang/String;", "parse", "Lorg/jsoup/nodes/Element;", "doc", "reader-pro"})
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeByJSoup$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final String[] getValidKeys() {
            return AnalyzeByJSoup.validKeys;
        }

        @NotNull
        public final Element parse(@NotNull Object doc) {
            Intrinsics.checkNotNullParameter(doc, "doc");
            if (doc instanceof Element) {
                return (Element) doc;
            }
            if (doc instanceof JXNode) {
                Element elementAsElement = ((JXNode) doc).isElement() ? ((JXNode) doc).asElement() : Jsoup.parse(doc.toString());
                Intrinsics.checkNotNullExpressionValue(elementAsElement, "if (doc.isElement) doc.asElement() else Jsoup.parse(doc.toString())");
                return elementAsElement;
            }
            Document document = Jsoup.parse(doc.toString());
            Intrinsics.checkNotNullExpressionValue(document, "parse(doc.toString())");
            return document;
        }
    }

    public AnalyzeByJSoup(@NotNull Object doc) {
        Intrinsics.checkNotNullParameter(doc, "doc");
        this.element = Companion.parse(doc);
    }

    @NotNull
    public final Elements getElements$reader_pro(@NotNull String rule) {
        Intrinsics.checkNotNullParameter(rule, "rule");
        return getElements(this.element, rule);
    }

    @Nullable
    public final String getString$reader_pro(@NotNull String ruleStr) {
        Intrinsics.checkNotNullParameter(ruleStr, "ruleStr");
        if (ruleStr.length() == 0) {
            return null;
        }
        List it = getStringList$reader_pro(ruleStr);
        List list = !it.isEmpty() ? it : null;
        if (list == null) {
            return null;
        }
        return CollectionsKt.joinToString$default(list, "\n", null, null, 0, null, null, 62, null);
    }

    @NotNull
    public final String getString0$reader_pro(@NotNull String ruleStr) {
        Intrinsics.checkNotNullParameter(ruleStr, "ruleStr");
        List it = getStringList$reader_pro(ruleStr);
        return it.isEmpty() ? "" : it.get(0);
    }

    @NotNull
    public final List<String> getStringList$reader_pro(@NotNull String ruleStr) {
        List resultList;
        Intrinsics.checkNotNullParameter(ruleStr, "ruleStr");
        ArrayList textS = new ArrayList();
        if (ruleStr.length() == 0) {
            return textS;
        }
        SourceRule sourceRule = new SourceRule(this, ruleStr);
        if (sourceRule.getElementsRule().length() == 0) {
            String strData = this.element.data();
            textS.add(strData == null ? "" : strData);
        } else {
            RuleAnalyzer ruleAnalyzes = new RuleAnalyzer(sourceRule.getElementsRule(), false, 2, null);
            ArrayList ruleStrS = ruleAnalyzes.splitRule("&&", "||", "%%");
            ArrayList results = new ArrayList();
            Iterator<String> it = ruleStrS.iterator();
            while (it.hasNext()) {
                String ruleStrX = it.next();
                if (sourceRule.isCss()) {
                    Intrinsics.checkNotNullExpressionValue(ruleStrX, "ruleStrX");
                    int lastIndex = StringsKt.lastIndexOf$default((CharSequence) ruleStrX, '@', 0, false, 6, (Object) null);
                    Element element = this.element;
                    String strSubstring = ruleStrX.substring(0, lastIndex);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    Elements elementsSelect = element.select(strSubstring);
                    Intrinsics.checkNotNullExpressionValue(elementsSelect, "element.select(ruleStrX.substring(0, lastIndex))");
                    String strSubstring2 = ruleStrX.substring(lastIndex + 1);
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.String).substring(startIndex)");
                    resultList = getResultLast(elementsSelect, strSubstring2);
                } else {
                    Intrinsics.checkNotNullExpressionValue(ruleStrX, "ruleStrX");
                    resultList = getResultList(ruleStrX);
                }
                List temp = resultList;
                List list = temp;
                if (!(list == null || list.isEmpty())) {
                    results.add(temp);
                    if (Intrinsics.areEqual(ruleAnalyzes.getElementsType(), "||")) {
                        break;
                    }
                }
            }
            if (results.size() > 0) {
                if (Intrinsics.areEqual("%%", ruleAnalyzes.getElementsType())) {
                    int i = 0;
                    int size = ((List) results.get(0)).size() - 1;
                    if (0 <= size) {
                        do {
                            int i2 = i;
                            i++;
                            Iterator it2 = results.iterator();
                            while (it2.hasNext()) {
                                List temp2 = (List) it2.next();
                                if (i2 < temp2.size()) {
                                    textS.add(temp2.get(i2));
                                }
                            }
                        } while (i <= size);
                    }
                } else {
                    Iterator it3 = results.iterator();
                    while (it3.hasNext()) {
                        textS.addAll((List) it3.next());
                    }
                }
            }
        }
        return textS;
    }

    private final Elements getElements(Element temp, String rule) {
        Elements elementsSingle;
        if (temp != null) {
            if (!(rule.length() == 0)) {
                Elements elements = new Elements();
                SourceRule sourceRule = new SourceRule(this, rule);
                RuleAnalyzer ruleAnalyzes = new RuleAnalyzer(sourceRule.getElementsRule(), false, 2, null);
                ArrayList ruleStrS = ruleAnalyzes.splitRule("&&", "||", "%%");
                ArrayList elementsList = new ArrayList();
                if (sourceRule.isCss()) {
                    Iterator<String> it = ruleStrS.iterator();
                    while (it.hasNext()) {
                        Elements tempS = temp.select(it.next());
                        elementsList.add(tempS);
                        if (tempS.size() > 0 && Intrinsics.areEqual(ruleAnalyzes.getElementsType(), "||")) {
                            break;
                        }
                    }
                } else {
                    Iterator<String> it2 = ruleStrS.iterator();
                    while (it2.hasNext()) {
                        String ruleStr = it2.next();
                        Intrinsics.checkNotNullExpressionValue(ruleStr, "ruleStr");
                        RuleAnalyzer rsRule = new RuleAnalyzer(ruleStr, false, 2, null);
                        rsRule.trim();
                        ArrayList rs = rsRule.splitRule(StrPool.AT);
                        if (rs.size() > 1) {
                            Elements el = new Elements();
                            el.add(temp);
                            Iterator<String> it3 = rs.iterator();
                            while (it3.hasNext()) {
                                String rl = it3.next();
                                Elements es = new Elements();
                                Iterator<Element> it4 = el.iterator();
                                while (it4.hasNext()) {
                                    Element et = it4.next();
                                    Intrinsics.checkNotNullExpressionValue(rl, "rl");
                                    es.addAll(getElements(et, rl));
                                }
                                el.clear();
                                el.addAll(es);
                            }
                            elementsSingle = el;
                        } else {
                            elementsSingle = new ElementsSingle((char) 0, null, null, null, 15, null).getElementsSingle(temp, ruleStr);
                        }
                        Elements el2 = elementsSingle;
                        elementsList.add(el2);
                        if (el2.size() > 0 && Intrinsics.areEqual(ruleAnalyzes.getElementsType(), "||")) {
                            break;
                        }
                    }
                }
                if (elementsList.size() > 0) {
                    if (Intrinsics.areEqual("%%", ruleAnalyzes.getElementsType())) {
                        int i = 0;
                        int size = ((Elements) elementsList.get(0)).size();
                        if (0 < size) {
                            do {
                                int i2 = i;
                                i++;
                                Iterator it5 = elementsList.iterator();
                                while (it5.hasNext()) {
                                    Elements es2 = (Elements) it5.next();
                                    if (i2 < es2.size()) {
                                        elements.add(es2.get(i2));
                                    }
                                }
                            } while (i < size);
                        }
                    } else {
                        Iterator it6 = elementsList.iterator();
                        while (it6.hasNext()) {
                            elements.addAll((Elements) it6.next());
                        }
                    }
                }
                return elements;
            }
        }
        return new Elements();
    }

    private final List<String> getResultList(String ruleStr) {
        if (ruleStr.length() == 0) {
            return null;
        }
        Elements elements = new Elements();
        elements.add(this.element);
        RuleAnalyzer rule = new RuleAnalyzer(ruleStr, false, 2, null);
        rule.trim();
        ArrayList rules = rule.splitRule(StrPool.AT);
        int last = rules.size() - 1;
        int i = 0;
        if (0 < last) {
            do {
                int i2 = i;
                i++;
                Elements es = new Elements();
                Iterator<Element> it = elements.iterator();
                while (it.hasNext()) {
                    Element elt = it.next();
                    ElementsSingle elementsSingle = new ElementsSingle((char) 0, null, null, null, 15, null);
                    Intrinsics.checkNotNullExpressionValue(elt, "elt");
                    String str = rules.get(i2);
                    Intrinsics.checkNotNullExpressionValue(str, "rules[i]");
                    es.addAll(elementsSingle.getElementsSingle(elt, str));
                }
                elements.clear();
                elements = es;
            } while (i < last);
        }
        if (elements.isEmpty()) {
            return null;
        }
        String str2 = rules.get(last);
        Intrinsics.checkNotNullExpressionValue(str2, "rules[last]");
        return getResultLast(elements, str2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01e6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0113 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x02d1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.util.List<java.lang.String> getResultLast(org.jsoup.select.Elements r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 803
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.analyzeRule.AnalyzeByJSoup.getResultLast(org.jsoup.select.Elements, java.lang.String):java.util.List");
    }

    /* compiled from: AnalyzeByJSoup.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��@\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\f\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018��2\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007HÆ\u0003J=\u0010\u001a\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0005H\u0002J\u0016\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010 \u001a\u00020\u0005J\t\u0010%\u001a\u00020\bHÖ\u0001J\t\u0010&\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006'"}, d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeByJSoup$ElementsSingle;", "", "split", "", "beforeRule", "", "indexDefault", "", "", "indexes", "(CLjava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getBeforeRule", "()Ljava/lang/String;", "setBeforeRule", "(Ljava/lang/String;)V", "getIndexDefault", "()Ljava/util/List;", "getIndexes", "getSplit", "()C", "setSplit", "(C)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "findIndexSet", "", "rule", "getElementsSingle", "Lorg/jsoup/select/Elements;", "temp", "Lorg/jsoup/nodes/Element;", IdentityNamingStrategy.HASH_CODE_KEY, "toString", "reader-pro"})
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeByJSoup$ElementsSingle.class */
    public static final class ElementsSingle {
        private char split;

        @NotNull
        private String beforeRule;

        @NotNull
        private final List<Integer> indexDefault;

        @NotNull
        private final List<Object> indexes;

        public final char component1() {
            return this.split;
        }

        @NotNull
        public final String component2() {
            return this.beforeRule;
        }

        @NotNull
        public final List<Integer> component3() {
            return this.indexDefault;
        }

        @NotNull
        public final List<Object> component4() {
            return this.indexes;
        }

        @NotNull
        public final ElementsSingle copy(char split, @NotNull String beforeRule, @NotNull List<Integer> indexDefault, @NotNull List<Object> indexes) {
            Intrinsics.checkNotNullParameter(beforeRule, "beforeRule");
            Intrinsics.checkNotNullParameter(indexDefault, "indexDefault");
            Intrinsics.checkNotNullParameter(indexes, "indexes");
            return new ElementsSingle(split, beforeRule, indexDefault, indexes);
        }

        public static /* synthetic */ ElementsSingle copy$default(ElementsSingle elementsSingle, char c, String str, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                c = elementsSingle.split;
            }
            if ((i & 2) != 0) {
                str = elementsSingle.beforeRule;
            }
            if ((i & 4) != 0) {
                list = elementsSingle.indexDefault;
            }
            if ((i & 8) != 0) {
                list2 = elementsSingle.indexes;
            }
            return elementsSingle.copy(c, str, list, list2);
        }

        @NotNull
        public String toString() {
            return "ElementsSingle(split=" + this.split + ", beforeRule=" + this.beforeRule + ", indexDefault=" + this.indexDefault + ", indexes=" + this.indexes + ')';
        }

        public int hashCode() {
            int result = Character.hashCode(this.split);
            return (((((result * 31) + this.beforeRule.hashCode()) * 31) + this.indexDefault.hashCode()) * 31) + this.indexes.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ElementsSingle)) {
                return false;
            }
            ElementsSingle elementsSingle = (ElementsSingle) other;
            return this.split == elementsSingle.split && Intrinsics.areEqual(this.beforeRule, elementsSingle.beforeRule) && Intrinsics.areEqual(this.indexDefault, elementsSingle.indexDefault) && Intrinsics.areEqual(this.indexes, elementsSingle.indexes);
        }

        public ElementsSingle() {
            this((char) 0, null, null, null, 15, null);
        }

        public ElementsSingle(char split, @NotNull String beforeRule, @NotNull List<Integer> indexDefault, @NotNull List<Object> indexes) {
            Intrinsics.checkNotNullParameter(beforeRule, "beforeRule");
            Intrinsics.checkNotNullParameter(indexDefault, "indexDefault");
            Intrinsics.checkNotNullParameter(indexes, "indexes");
            this.split = split;
            this.beforeRule = beforeRule;
            this.indexDefault = indexDefault;
            this.indexes = indexes;
        }

        public /* synthetic */ ElementsSingle(char c, String str, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? '.' : c, (i & 2) != 0 ? "" : str, (i & 4) != 0 ? new ArrayList() : list, (i & 8) != 0 ? new ArrayList() : list2);
        }

        public final char getSplit() {
            return this.split;
        }

        public final void setSplit(char c) {
            this.split = c;
        }

        @NotNull
        public final String getBeforeRule() {
            return this.beforeRule;
        }

        public final void setBeforeRule(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.beforeRule = str;
        }

        @NotNull
        public final List<Integer> getIndexDefault() {
            return this.indexDefault;
        }

        @NotNull
        public final List<Object> getIndexes() {
            return this.indexes;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
        java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
         */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0132  */
        @org.jetbrains.annotations.NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final org.jsoup.select.Elements getElementsSingle(@org.jetbrains.annotations.NotNull org.jsoup.nodes.Element r8, @org.jetbrains.annotations.NotNull java.lang.String r9) {
            /*
                Method dump skipped, instructions count: 1128
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.legado.app.model.analyzeRule.AnalyzeByJSoup.ElementsSingle.getElementsSingle(org.jsoup.nodes.Element, java.lang.String):org.jsoup.select.Elements");
        }

        private final void findIndexSet(String rule) {
            Integer numValueOf;
            String $this$trim$iv$iv = rule;
            int startIndex$iv$iv = 0;
            int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
            boolean startFound$iv$iv = false;
            while (startIndex$iv$iv <= endIndex$iv$iv) {
                int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
                char it = $this$trim$iv$iv.charAt(index$iv$iv);
                boolean match$iv$iv = Intrinsics.compare((int) it, 32) <= 0;
                if (!startFound$iv$iv) {
                    if (!match$iv$iv) {
                        startFound$iv$iv = true;
                    } else {
                        startIndex$iv$iv++;
                    }
                } else if (!match$iv$iv) {
                    break;
                } else {
                    endIndex$iv$iv--;
                }
            }
            String rus = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
            int len = rus.length();
            boolean curMinus = false;
            List curList = new ArrayList();
            String l = "";
            boolean head = StringsKt.last(rus) == ']';
            if (head) {
                int len2 = len - 1;
                while (true) {
                    int i = len2;
                    len2 = i - 1;
                    if (i < 0) {
                        break;
                    }
                    char rl = rus.charAt(len2);
                    if (rl != ' ') {
                        boolean z = '0' <= rl && rl <= '9';
                        if (z) {
                            l = String.valueOf(rl) + l;
                        } else if (rl == '-') {
                            curMinus = true;
                        } else {
                            if (l.length() == 0) {
                                numValueOf = null;
                            } else {
                                numValueOf = curMinus ? Integer.valueOf(-Integer.parseInt(l)) : Integer.valueOf(Integer.parseInt(l));
                            }
                            Integer curInt = numValueOf;
                            if (rl == ':') {
                                curList.add(curInt);
                            } else {
                                if (curList.isEmpty()) {
                                    if (curInt == null) {
                                        break;
                                    } else {
                                        this.indexes.add(curInt);
                                    }
                                } else {
                                    this.indexes.add(new Triple(curInt, CollectionsKt.last(curList), curList.size() == 2 ? (Integer) CollectionsKt.first(curList) : 1));
                                    curList.clear();
                                }
                                if (rl == '!') {
                                    this.split = '!';
                                    do {
                                        len2--;
                                        rl = rus.charAt(len2);
                                        if (len2 <= 0) {
                                            break;
                                        }
                                    } while (rl == ' ');
                                }
                                if (rl == '[') {
                                    if (rus == null) {
                                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                                    }
                                    String strSubstring = rus.substring(0, len2);
                                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                    this.beforeRule = strSubstring;
                                    return;
                                }
                                if (rl != ',') {
                                    break;
                                }
                            }
                            l = "";
                            curMinus = false;
                        }
                    }
                }
            } else {
                while (true) {
                    int i2 = len;
                    len = i2 - 1;
                    if (i2 < 0) {
                        break;
                    }
                    char rl2 = rus.charAt(len);
                    if (rl2 != ' ') {
                        boolean z2 = '0' <= rl2 && rl2 <= '9';
                        if (z2) {
                            l = String.valueOf(rl2) + l;
                        } else if (rl2 != '-') {
                            if (rl2 != '!' && rl2 != '.' && rl2 != ':') {
                                break;
                            }
                            this.indexDefault.add(Integer.valueOf(curMinus ? -Integer.parseInt(l) : Integer.parseInt(l)));
                            if (rl2 != ':') {
                                this.split = rl2;
                                if (rus == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                                }
                                String strSubstring2 = rus.substring(0, len);
                                Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                this.beforeRule = strSubstring2;
                                return;
                            }
                            l = "";
                            curMinus = false;
                        } else {
                            curMinus = true;
                        }
                    }
                }
            }
            this.split = ' ';
            this.beforeRule = rus;
        }
    }

    /* compiled from: AnalyzeByJSoup.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0080\u0004\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\n\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/legado/app/model/analyzeRule/AnalyzeByJSoup$SourceRule;", "", "ruleStr", "", "(Lio/legado/app/model/analyzeRule/AnalyzeByJSoup;Ljava/lang/String;)V", "elementsRule", "getElementsRule", "()Ljava/lang/String;", "setElementsRule", "(Ljava/lang/String;)V", "isCss", "", "()Z", "setCss", "(Z)V", "reader-pro"})
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/model/analyzeRule/AnalyzeByJSoup$SourceRule.class */
    public final class SourceRule {
        private boolean isCss;

        @NotNull
        private String elementsRule;
        final /* synthetic */ AnalyzeByJSoup this$0;

        public SourceRule(@NotNull AnalyzeByJSoup this$0, String ruleStr) {
            String str;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(ruleStr, "ruleStr");
            this.this$0 = this$0;
            SourceRule sourceRule = this;
            if (StringsKt.startsWith(ruleStr, "@CSS:", true)) {
                this.isCss = true;
                CharSequence $this$trim$iv = ruleStr.substring(5);
                Intrinsics.checkNotNullExpressionValue($this$trim$iv, "(this as java.lang.String).substring(startIndex)");
                CharSequence $this$trim$iv$iv = $this$trim$iv;
                int startIndex$iv$iv = 0;
                int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
                boolean startFound$iv$iv = false;
                while (startIndex$iv$iv <= endIndex$iv$iv) {
                    int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
                    char it = $this$trim$iv$iv.charAt(index$iv$iv);
                    boolean match$iv$iv = Intrinsics.compare((int) it, 32) <= 0;
                    if (!startFound$iv$iv) {
                        if (!match$iv$iv) {
                            startFound$iv$iv = true;
                        } else {
                            startIndex$iv$iv++;
                        }
                    } else if (!match$iv$iv) {
                        break;
                    } else {
                        endIndex$iv$iv--;
                    }
                }
                String string = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
                sourceRule = sourceRule;
                str = string;
            } else {
                str = ruleStr;
            }
            sourceRule.elementsRule = str;
        }

        public final boolean isCss() {
            return this.isCss;
        }

        public final void setCss(boolean z) {
            this.isCss = z;
        }

        @NotNull
        public final String getElementsRule() {
            return this.elementsRule;
        }

        public final void setElementsRule(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.elementsRule = str;
        }
    }
}
