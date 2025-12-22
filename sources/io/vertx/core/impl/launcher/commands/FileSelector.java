package io.vertx.core.impl.launcher.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/commands/FileSelector.class */
public final class FileSelector {
    private static boolean separatorPatternStartSlashMismatch(String pattern, String str, String separator) {
        return str.startsWith(separator) != pattern.startsWith(separator);
    }

    public static boolean matchPath(String pattern, String str) {
        return matchPath(pattern, str, true);
    }

    public static boolean matchPath(String pattern, String str, boolean isCaseSensitive) {
        return matchPath(pattern, str, File.separator, isCaseSensitive);
    }

    protected static boolean matchPath(String pattern, String str, String separator, boolean isCaseSensitive) {
        return matchPathPattern(pattern, str, separator, isCaseSensitive);
    }

    private static boolean matchPathPattern(String pattern, String str, String separator, boolean isCaseSensitive) {
        if (separatorPatternStartSlashMismatch(pattern, str, separator)) {
            return false;
        }
        String[] patDirs = tokenizePathToString(pattern, separator);
        String[] strDirs = tokenizePathToString(str, separator);
        return matchPathPattern(patDirs, strDirs, isCaseSensitive);
    }

    private static boolean matchPathPattern(String[] patDirs, String[] strDirs, boolean isCaseSensitive) {
        int patIdxStart = 0;
        int patIdxEnd = patDirs.length - 1;
        int strIdxStart = 0;
        int strIdxEnd = strDirs.length - 1;
        while (patIdxStart <= patIdxEnd && strIdxStart <= strIdxEnd) {
            String patDir = patDirs[patIdxStart];
            if (patDir.equals("**")) {
                break;
            }
            if (!match(patDir, strDirs[strIdxStart], isCaseSensitive)) {
                return false;
            }
            patIdxStart++;
            strIdxStart++;
        }
        if (strIdxStart > strIdxEnd) {
            for (int i = patIdxStart; i <= patIdxEnd; i++) {
                if (!patDirs[i].equals("**")) {
                    return false;
                }
            }
            return true;
        }
        if (patIdxStart > patIdxEnd) {
            return false;
        }
        while (patIdxStart <= patIdxEnd && strIdxStart <= strIdxEnd) {
            String patDir2 = patDirs[patIdxEnd];
            if (patDir2.equals("**")) {
                break;
            }
            if (!match(patDir2, strDirs[strIdxEnd], isCaseSensitive)) {
                return false;
            }
            patIdxEnd--;
            strIdxEnd--;
        }
        if (strIdxStart > strIdxEnd) {
            for (int i2 = patIdxStart; i2 <= patIdxEnd; i2++) {
                if (!patDirs[i2].equals("**")) {
                    return false;
                }
            }
            return true;
        }
        while (patIdxStart != patIdxEnd && strIdxStart <= strIdxEnd) {
            int patIdxTmp = -1;
            int i3 = patIdxStart + 1;
            while (true) {
                if (i3 > patIdxEnd) {
                    break;
                }
                if (!patDirs[i3].equals("**")) {
                    i3++;
                } else {
                    patIdxTmp = i3;
                    break;
                }
            }
            if (patIdxTmp == patIdxStart + 1) {
                patIdxStart++;
            } else {
                int patLength = (patIdxTmp - patIdxStart) - 1;
                int strLength = (strIdxEnd - strIdxStart) + 1;
                int foundIdx = -1;
                int i4 = 0;
                while (true) {
                    if (i4 > strLength - patLength) {
                        break;
                    }
                    for (int j = 0; j < patLength; j++) {
                        String subPat = patDirs[patIdxStart + j + 1];
                        String subStr = strDirs[strIdxStart + i4 + j];
                        if (!match(subPat, subStr, isCaseSensitive)) {
                            break;
                        }
                    }
                    foundIdx = strIdxStart + i4;
                    break;
                    i4++;
                }
                if (foundIdx == -1) {
                    return false;
                }
                patIdxStart = patIdxTmp;
                strIdxStart = foundIdx + patLength;
            }
        }
        for (int i5 = patIdxStart; i5 <= patIdxEnd; i5++) {
            if (!patDirs[i5].equals("**")) {
                return false;
            }
        }
        return true;
    }

    public static boolean match(String pattern, String str) {
        return match(pattern, str, true);
    }

    public static boolean match(String pattern, String str, boolean isCaseSensitive) {
        char[] patArr = pattern.toCharArray();
        char[] strArr = str.toCharArray();
        return match(patArr, strArr, isCaseSensitive);
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b8, code lost:
    
        if (r10 <= r11) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00c2, code lost:
    
        return checkOnlyStartsLeft(r5, r8, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00c3, code lost:
    
        r0 = r5[r9];
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00cc, code lost:
    
        if (r0 == '*') goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00d3, code lost:
    
        if (r10 > r11) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00da, code lost:
    
        if (r0 == '?') goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00e7, code lost:
    
        if (equals(r0, r6[r11], r7) != false) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00ea, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00ec, code lost:
    
        r9 = r9 - 1;
        r11 = r11 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00f9, code lost:
    
        if (r10 <= r11) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0103, code lost:
    
        return checkOnlyStartsLeft(r5, r8, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0107, code lost:
    
        if (r8 == r9) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x010e, code lost:
    
        if (r10 > r11) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0111, code lost:
    
        r14 = -1;
        r15 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x011d, code lost:
    
        if (r15 > r9) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0126, code lost:
    
        if (r5[r15] != '*') goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0129, code lost:
    
        r14 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0130, code lost:
    
        r15 = r15 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x013b, code lost:
    
        if (r14 != (r8 + 1)) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x013e, code lost:
    
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0144, code lost:
    
        r0 = (r14 - r8) - 1;
        r0 = (r11 - r10) + 1;
        r17 = -1;
        r18 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0162, code lost:
    
        if (r18 > (r0 - r0)) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0165, code lost:
    
        r19 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x016c, code lost:
    
        if (r19 >= r0) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x016f, code lost:
    
        r0 = r5[(r8 + r19) + 1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x017d, code lost:
    
        if (r0 == '?') goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0190, code lost:
    
        if (equals(r0, r6[(r10 + r18) + r19], r7) != false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0196, code lost:
    
        r19 = r19 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x019c, code lost:
    
        r17 = r10 + r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01a6, code lost:
    
        r18 = r18 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01af, code lost:
    
        if (r17 != (-1)) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01b2, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01b4, code lost:
    
        r8 = r14;
        r10 = r17 + r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01c8, code lost:
    
        return checkOnlyStartsLeft(r5, r8, r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean match(char[] r5, char[] r6, boolean r7) {
        /*
            Method dump skipped, instructions count: 457
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.vertx.core.impl.launcher.commands.FileSelector.match(char[], char[], boolean):boolean");
    }

    private static boolean checkOnlyStartsLeft(char[] patArr, int patIdxStart, int patIdxEnd) {
        for (int i = patIdxStart; i <= patIdxEnd; i++) {
            if (patArr[i] != '*') {
                return false;
            }
        }
        return true;
    }

    private static boolean equals(char c1, char c2, boolean isCaseSensitive) {
        if (c1 == c2) {
            return true;
        }
        if (!isCaseSensitive) {
            if (Character.toUpperCase(c1) == Character.toUpperCase(c2) || Character.toLowerCase(c1) == Character.toLowerCase(c2)) {
                return true;
            }
            return false;
        }
        return false;
    }

    private static String[] tokenizePathToString(String path, String separator) {
        List<String> ret = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(path, separator);
        while (st.hasMoreTokens()) {
            ret.add(st.nextToken());
        }
        return (String[]) ret.toArray(new String[ret.size()]);
    }
}
