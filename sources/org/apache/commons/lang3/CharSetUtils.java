package org.apache.commons.lang3;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/CharSetUtils.class */
public class CharSetUtils {
    /* JADX WARN: Removed duplicated region for block: B:26:0x0096 A[PHI: r13
  0x0096: PHI (r13v2 'notInChars' java.lang.Character) = 
  (r13v1 'notInChars' java.lang.Character)
  (r13v4 'notInChars' java.lang.Character)
  (r13v1 'notInChars' java.lang.Character)
 binds: [B:12:0x0058, B:25:0x008f, B:21:0x0079] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String squeeze(java.lang.String r4, java.lang.String... r5) {
        /*
            r0 = r4
            boolean r0 = org.apache.commons.lang3.StringUtils.isEmpty(r0)
            if (r0 != 0) goto Le
            r0 = r5
            boolean r0 = deepEmpty(r0)
            if (r0 == 0) goto L10
        Le:
            r0 = r4
            return r0
        L10:
            r0 = r5
            org.apache.commons.lang3.CharSet r0 = org.apache.commons.lang3.CharSet.getInstance(r0)
            r6 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r2 = r4
            int r2 = r2.length()
            r1.<init>(r2)
            r7 = r0
            r0 = r4
            char[] r0 = r0.toCharArray()
            r8 = r0
            r0 = r8
            int r0 = r0.length
            r9 = r0
            r0 = r8
            r1 = 0
            char r0 = r0[r1]
            r10 = r0
            r0 = 32
            r11 = r0
            r0 = 0
            r12 = r0
            r0 = 0
            r13 = r0
            r0 = r7
            r1 = r10
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = 1
            r14 = r0
        L46:
            r0 = r14
            r1 = r9
            if (r0 >= r1) goto La7
            r0 = r8
            r1 = r14
            char r0 = r0[r1]
            r11 = r0
            r0 = r11
            r1 = r10
            if (r0 != r1) goto L96
            r0 = r12
            if (r0 == 0) goto L6d
            r0 = r11
            r1 = r12
            char r1 = r1.charValue()
            if (r0 != r1) goto L6d
            goto La1
        L6d:
            r0 = r13
            if (r0 == 0) goto L7c
            r0 = r11
            r1 = r13
            char r1 = r1.charValue()
            if (r0 == r1) goto L96
        L7c:
            r0 = r6
            r1 = r11
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L8f
            r0 = r11
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            r12 = r0
            goto La1
        L8f:
            r0 = r11
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            r13 = r0
        L96:
            r0 = r7
            r1 = r11
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r11
            r10 = r0
        La1:
            int r14 = r14 + 1
            goto L46
        La7:
            r0 = r7
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.CharSetUtils.squeeze(java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static boolean containsAny(String str, String... set) {
        if (StringUtils.isEmpty(str) || deepEmpty(set)) {
            return false;
        }
        CharSet chars = CharSet.getInstance(set);
        for (char c : str.toCharArray()) {
            if (chars.contains(c)) {
                return true;
            }
        }
        return false;
    }

    public static int count(String str, String... set) {
        if (StringUtils.isEmpty(str) || deepEmpty(set)) {
            return 0;
        }
        CharSet chars = CharSet.getInstance(set);
        int count = 0;
        for (char c : str.toCharArray()) {
            if (chars.contains(c)) {
                count++;
            }
        }
        return count;
    }

    public static String keep(String str, String... set) {
        if (str == null) {
            return null;
        }
        if (str.isEmpty() || deepEmpty(set)) {
            return "";
        }
        return modify(str, set, true);
    }

    public static String delete(String str, String... set) {
        if (StringUtils.isEmpty(str) || deepEmpty(set)) {
            return str;
        }
        return modify(str, set, false);
    }

    private static String modify(String str, String[] set, boolean expect) {
        CharSet chars = CharSet.getInstance(set);
        StringBuilder buffer = new StringBuilder(str.length());
        char[] chrs = str.toCharArray();
        for (char chr : chrs) {
            if (chars.contains(chr) == expect) {
                buffer.append(chr);
            }
        }
        return buffer.toString();
    }

    private static boolean deepEmpty(String[] strings) {
        if (strings != null) {
            for (String s : strings) {
                if (StringUtils.isNotEmpty(s)) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }
}
