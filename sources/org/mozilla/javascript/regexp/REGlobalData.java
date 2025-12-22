package org.mozilla.javascript.regexp;

/* compiled from: NativeRegExp.java */
/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/regexp/REGlobalData.class */
class REGlobalData {
    boolean multiline;
    RECompiled regexp;
    int skipped;
    int cp;
    long[] parens;
    REProgState stateStackTop;
    REBackTrackData backTrackStackTop;

    REGlobalData() {
    }

    int parensIndex(int i) {
        return (int) this.parens[i];
    }

    int parensLength(int i) {
        return (int) (this.parens[i] >>> 32);
    }

    void setParens(int i, int index, int length) {
        if (this.backTrackStackTop != null && this.backTrackStackTop.parens == this.parens) {
            this.parens = (long[]) this.parens.clone();
        }
        this.parens[i] = (index & 4294967295L) | (length << 32);
    }
}
