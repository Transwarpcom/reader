package org.antlr.v4.runtime;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/VocabularyImpl.class */
public class VocabularyImpl implements Vocabulary {
    private static final String[] EMPTY_NAMES = new String[0];
    public static final VocabularyImpl EMPTY_VOCABULARY = new VocabularyImpl(EMPTY_NAMES, EMPTY_NAMES, EMPTY_NAMES);
    private final String[] literalNames;
    private final String[] symbolicNames;
    private final String[] displayNames;
    private final int maxTokenType;

    public VocabularyImpl(String[] literalNames, String[] symbolicNames) {
        this(literalNames, symbolicNames, null);
    }

    public VocabularyImpl(String[] literalNames, String[] symbolicNames, String[] displayNames) {
        this.literalNames = literalNames != null ? literalNames : EMPTY_NAMES;
        this.symbolicNames = symbolicNames != null ? symbolicNames : EMPTY_NAMES;
        this.displayNames = displayNames != null ? displayNames : EMPTY_NAMES;
        this.maxTokenType = Math.max(this.displayNames.length, Math.max(this.literalNames.length, this.symbolicNames.length)) - 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.antlr.v4.runtime.Vocabulary fromTokenNames(java.lang.String[] r6) {
        /*
            r0 = r6
            if (r0 == 0) goto L9
            r0 = r6
            int r0 = r0.length
            if (r0 != 0) goto Ld
        L9:
            org.antlr.v4.runtime.VocabularyImpl r0 = org.antlr.v4.runtime.VocabularyImpl.EMPTY_VOCABULARY
            return r0
        Ld:
            r0 = r6
            r1 = r6
            int r1 = r1.length
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r1)
            java.lang.String[] r0 = (java.lang.String[]) r0
            r7 = r0
            r0 = r6
            r1 = r6
            int r1 = r1.length
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r1)
            java.lang.String[] r0 = (java.lang.String[]) r0
            r8 = r0
            r0 = 0
            r9 = r0
        L23:
            r0 = r9
            r1 = r6
            int r1 = r1.length
            if (r0 >= r1) goto L71
            r0 = r6
            r1 = r9
            r0 = r0[r1]
            r10 = r0
            r0 = r10
            if (r0 != 0) goto L36
            goto L6b
        L36:
            r0 = r10
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L63
            r0 = r10
            r1 = 0
            char r0 = r0.charAt(r1)
            r11 = r0
            r0 = r11
            r1 = 39
            if (r0 != r1) goto L54
            r0 = r8
            r1 = r9
            r2 = 0
            r0[r1] = r2
            goto L6b
        L54:
            r0 = r11
            boolean r0 = java.lang.Character.isUpperCase(r0)
            if (r0 == 0) goto L63
            r0 = r7
            r1 = r9
            r2 = 0
            r0[r1] = r2
            goto L6b
        L63:
            r0 = r7
            r1 = r9
            r2 = 0
            r0[r1] = r2
            r0 = r8
            r1 = r9
            r2 = 0
            r0[r1] = r2
        L6b:
            int r9 = r9 + 1
            goto L23
        L71:
            org.antlr.v4.runtime.VocabularyImpl r0 = new org.antlr.v4.runtime.VocabularyImpl
            r1 = r0
            r2 = r7
            r3 = r8
            r4 = r6
            r1.<init>(r2, r3, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.antlr.v4.runtime.VocabularyImpl.fromTokenNames(java.lang.String[]):org.antlr.v4.runtime.Vocabulary");
    }

    @Override // org.antlr.v4.runtime.Vocabulary
    public int getMaxTokenType() {
        return this.maxTokenType;
    }

    @Override // org.antlr.v4.runtime.Vocabulary
    public String getLiteralName(int tokenType) {
        if (tokenType >= 0 && tokenType < this.literalNames.length) {
            return this.literalNames[tokenType];
        }
        return null;
    }

    @Override // org.antlr.v4.runtime.Vocabulary
    public String getSymbolicName(int tokenType) {
        if (tokenType >= 0 && tokenType < this.symbolicNames.length) {
            return this.symbolicNames[tokenType];
        }
        if (tokenType == -1) {
            return "EOF";
        }
        return null;
    }

    @Override // org.antlr.v4.runtime.Vocabulary
    public String getDisplayName(int tokenType) {
        String displayName;
        if (tokenType >= 0 && tokenType < this.displayNames.length && (displayName = this.displayNames[tokenType]) != null) {
            return displayName;
        }
        String literalName = getLiteralName(tokenType);
        if (literalName != null) {
            return literalName;
        }
        String symbolicName = getSymbolicName(tokenType);
        if (symbolicName != null) {
            return symbolicName;
        }
        return Integer.toString(tokenType);
    }
}
