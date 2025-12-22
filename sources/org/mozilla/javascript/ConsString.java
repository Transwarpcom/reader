package org.mozilla.javascript;

import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ConsString.class */
public class ConsString implements CharSequence, Serializable {
    private static final long serialVersionUID = -8432806714471372570L;
    private CharSequence left;
    private CharSequence right;
    private final int length;
    private boolean isFlat = false;

    public ConsString(CharSequence str1, CharSequence str2) {
        this.left = str1;
        this.right = str2;
        this.length = this.left.length() + this.right.length();
    }

    private Object writeReplace() {
        return toString();
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.isFlat ? (String) this.left : flatten();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized java.lang.String flatten() {
        /*
            r6 = this;
            r0 = r6
            boolean r0 = r0.isFlat
            if (r0 != 0) goto La9
            r0 = r6
            int r0 = r0.length
            char[] r0 = new char[r0]
            r7 = r0
            r0 = r6
            int r0 = r0.length
            r8 = r0
            java.util.ArrayDeque r0 = new java.util.ArrayDeque
            r1 = r0
            r1.<init>()
            r9 = r0
            r0 = r9
            r1 = r6
            java.lang.CharSequence r1 = r1.left
            r0.addFirst(r1)
            r0 = r6
            java.lang.CharSequence r0 = r0.right
            r10 = r0
        L29:
            r0 = r10
            boolean r0 = r0 instanceof org.mozilla.javascript.ConsString
            if (r0 == 0) goto L5d
            r0 = r10
            org.mozilla.javascript.ConsString r0 = (org.mozilla.javascript.ConsString) r0
            r11 = r0
            r0 = r11
            boolean r0 = r0.isFlat
            if (r0 == 0) goto L4a
            r0 = r11
            java.lang.CharSequence r0 = r0.left
            r10 = r0
            goto L5d
        L4a:
            r0 = r9
            r1 = r11
            java.lang.CharSequence r1 = r1.left
            r0.addFirst(r1)
            r0 = r11
            java.lang.CharSequence r0 = r0.right
            r10 = r0
            goto L8d
        L5d:
            r0 = r10
            java.lang.String r0 = (java.lang.String) r0
            r11 = r0
            r0 = r8
            r1 = r11
            int r1 = r1.length()
            int r0 = r0 - r1
            r8 = r0
            r0 = r11
            r1 = 0
            r2 = r11
            int r2 = r2.length()
            r3 = r7
            r4 = r8
            r0.getChars(r1, r2, r3, r4)
            r0 = r9
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L84
            r0 = 0
            goto L8b
        L84:
            r0 = r9
            java.lang.Object r0 = r0.removeFirst()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
        L8b:
            r10 = r0
        L8d:
            r0 = r10
            if (r0 != 0) goto L29
            r0 = r6
            java.lang.String r1 = new java.lang.String
            r2 = r1
            r3 = r7
            r2.<init>(r3)
            r0.left = r1
            r0 = r6
            java.lang.String r1 = ""
            r0.right = r1
            r0 = r6
            r1 = 1
            r0.isFlat = r1
        La9:
            r0 = r6
            java.lang.CharSequence r0 = r0.left
            java.lang.String r0 = (java.lang.String) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ConsString.flatten():java.lang.String");
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.length;
    }

    @Override // java.lang.CharSequence
    public char charAt(int index) {
        String str = this.isFlat ? (String) this.left : flatten();
        return str.charAt(index);
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int start, int end) {
        String str = this.isFlat ? (String) this.left : flatten();
        return str.substring(start, end);
    }
}
