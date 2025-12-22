package org.mozilla.javascript.regexp;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.RegExpProxy;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/regexp/RegExpImpl.class */
public class RegExpImpl implements RegExpProxy {
    protected String input;
    protected boolean multiline;
    protected SubString[] parens;
    protected SubString lastMatch;
    protected SubString lastParen;
    protected SubString leftContext;
    protected SubString rightContext;

    @Override // org.mozilla.javascript.RegExpProxy
    public boolean isRegExp(Scriptable obj) {
        return obj instanceof NativeRegExp;
    }

    @Override // org.mozilla.javascript.RegExpProxy
    public Object compileRegExp(Context cx, String source, String flags) {
        return NativeRegExp.compileRE(cx, source, flags, false);
    }

    @Override // org.mozilla.javascript.RegExpProxy
    public Scriptable wrapRegExp(Context cx, Scriptable scope, Object compiled) {
        return NativeRegExpInstantiator.withLanguageVersionScopeCompiled(cx.getLanguageVersion(), scope, (RECompiled) compiled);
    }

    @Override // org.mozilla.javascript.RegExpProxy
    public Object action(Context cx, Scriptable scope, Scriptable thisObj, Object[] args, int actionType) throws RuntimeException {
        Object val;
        GlobData data = new GlobData();
        data.mode = actionType;
        data.str = ScriptRuntime.toString(thisObj);
        switch (actionType) {
            case 1:
                int optarg = Integer.MAX_VALUE;
                if (cx.getLanguageVersion() < 160) {
                    optarg = 1;
                }
                NativeRegExp re = createRegExp(cx, scope, args, optarg, false);
                Object rval = matchOrReplace(cx, scope, thisObj, args, this, data, re);
                return data.arrayobj == null ? rval : data.arrayobj;
            case 2:
                boolean useRE = args.length > 0 && (args[0] instanceof NativeRegExp);
                if (cx.getLanguageVersion() < 160) {
                    useRE |= args.length > 2;
                }
                NativeRegExp re2 = null;
                String search = null;
                if (useRE) {
                    re2 = createRegExp(cx, scope, args, 2, true);
                } else {
                    Object arg0 = args.length < 1 ? Undefined.instance : args[0];
                    search = ScriptRuntime.toString(arg0);
                }
                Object arg1 = args.length < 2 ? Undefined.instance : args[1];
                String repstr = null;
                Function lambda = null;
                if ((arg1 instanceof Function) && (cx.getLanguageVersion() < 200 || !(arg1 instanceof NativeRegExp))) {
                    lambda = (Function) arg1;
                } else {
                    repstr = ScriptRuntime.toString(arg1);
                }
                data.lambda = lambda;
                data.repstr = repstr;
                data.dollar = repstr == null ? -1 : repstr.indexOf(36);
                data.charBuf = null;
                data.leftIndex = 0;
                if (useRE) {
                    val = matchOrReplace(cx, scope, thisObj, args, this, data, re2);
                } else {
                    String str = data.str;
                    int index = str.indexOf(search);
                    if (index >= 0) {
                        int slen = search.length();
                        this.parens = null;
                        this.lastParen = null;
                        this.leftContext = new SubString(str, 0, index);
                        this.lastMatch = new SubString(str, index, slen);
                        this.rightContext = new SubString(str, index + slen, (str.length() - index) - slen);
                        val = Boolean.TRUE;
                    } else {
                        val = Boolean.FALSE;
                    }
                }
                if (data.charBuf == null) {
                    if (data.global || val == null || !val.equals(Boolean.TRUE)) {
                        return data.str;
                    }
                    SubString lc = this.leftContext;
                    replace_glob(data, cx, scope, this, lc.index, lc.length);
                }
                SubString rc = this.rightContext;
                data.charBuf.append((CharSequence) rc.str, rc.index, rc.index + rc.length);
                return data.charBuf.toString();
            case 3:
                int optarg2 = Integer.MAX_VALUE;
                if (cx.getLanguageVersion() < 160) {
                    optarg2 = 1;
                }
                NativeRegExp re3 = createRegExp(cx, scope, args, optarg2, false);
                return matchOrReplace(cx, scope, thisObj, args, this, data, re3);
            default:
                throw Kit.codeBug();
        }
    }

    private static NativeRegExp createRegExp(Context cx, Scriptable scope, Object[] args, int optarg, boolean forceFlat) {
        NativeRegExp re;
        String opt;
        Scriptable topScope = ScriptableObject.getTopLevelScope(scope);
        if (args.length == 0 || args[0] == Undefined.instance) {
            RECompiled compiled = NativeRegExp.compileRE(cx, "", "", false);
            re = NativeRegExpInstantiator.withLanguageVersionScopeCompiled(cx.getLanguageVersion(), topScope, compiled);
        } else if (args[0] instanceof NativeRegExp) {
            re = (NativeRegExp) args[0];
        } else {
            String src = ScriptRuntime.toString(args[0]);
            if (optarg < args.length) {
                args[0] = src;
                opt = ScriptRuntime.toString(args[optarg]);
            } else {
                opt = null;
            }
            RECompiled compiled2 = NativeRegExp.compileRE(cx, src, opt, forceFlat);
            re = NativeRegExpInstantiator.withLanguageVersionScopeCompiled(cx.getLanguageVersion(), topScope, compiled2);
        }
        return re;
    }

    private static Object matchOrReplace(Context cx, Scriptable scope, Scriptable thisObj, Object[] args, RegExpImpl reImpl, GlobData data, NativeRegExp re) throws RuntimeException {
        String str = data.str;
        data.global = (re.getFlags() & 1) != 0;
        int[] indexp = {0};
        Object result = null;
        if (data.mode == 3) {
            Object result2 = re.executeRegExp(cx, scope, reImpl, str, indexp, 0);
            result = (result2 == null || !result2.equals(Boolean.TRUE)) ? -1 : Integer.valueOf(reImpl.leftContext.length);
        } else if (data.global) {
            re.lastIndex = ScriptRuntime.zeroObj;
            int count = 0;
            while (indexp[0] <= str.length()) {
                result = re.executeRegExp(cx, scope, reImpl, str, indexp, 0);
                if (result == null || !result.equals(Boolean.TRUE)) {
                    break;
                }
                if (data.mode == 1) {
                    match_glob(data, cx, scope, count, reImpl);
                } else {
                    if (data.mode != 2) {
                        Kit.codeBug();
                    }
                    SubString lastMatch = reImpl.lastMatch;
                    int leftIndex = data.leftIndex;
                    int leftlen = lastMatch.index - leftIndex;
                    data.leftIndex = lastMatch.index + lastMatch.length;
                    replace_glob(data, cx, scope, reImpl, leftIndex, leftlen);
                }
                if (reImpl.lastMatch.length == 0) {
                    if (indexp[0] == str.length()) {
                        break;
                    }
                    indexp[0] = indexp[0] + 1;
                }
                count++;
            }
        } else {
            result = re.executeRegExp(cx, scope, reImpl, str, indexp, data.mode == 2 ? 0 : 1);
        }
        return result;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x00a7, code lost:
    
        r20 = r0 - r15[0];
     */
    @Override // org.mozilla.javascript.RegExpProxy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int find_split(org.mozilla.javascript.Context r9, org.mozilla.javascript.Scriptable r10, java.lang.String r11, java.lang.String r12, org.mozilla.javascript.Scriptable r13, int[] r14, int[] r15, boolean[] r16, java.lang.String[][] r17) {
        /*
            Method dump skipped, instructions count: 245
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.RegExpImpl.find_split(org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, java.lang.String, java.lang.String, org.mozilla.javascript.Scriptable, int[], int[], boolean[], java.lang.String[][]):int");
    }

    SubString getParenSubString(int i) {
        SubString parsub;
        if (this.parens != null && i < this.parens.length && (parsub = this.parens[i]) != null) {
            return parsub;
        }
        return new SubString();
    }

    private static void match_glob(GlobData mdata, Context cx, Scriptable scope, int count, RegExpImpl reImpl) {
        if (mdata.arrayobj == null) {
            mdata.arrayobj = cx.newArray(scope, 0);
        }
        SubString matchsub = reImpl.lastMatch;
        String matchstr = matchsub.toString();
        mdata.arrayobj.put(count, mdata.arrayobj, matchstr);
    }

    private static void replace_glob(GlobData rdata, Context cx, Scriptable scope, RegExpImpl reImpl, int leftIndex, int leftlen) throws RuntimeException {
        String lambdaStr;
        int replen;
        int dp;
        if (rdata.lambda != null) {
            SubString[] parens = reImpl.parens;
            int parenCount = parens == null ? 0 : parens.length;
            Object[] args = new Object[parenCount + 3];
            args[0] = reImpl.lastMatch.toString();
            for (int i = 0; i < parenCount; i++) {
                SubString sub = parens[i];
                if (sub != null) {
                    args[i + 1] = sub.toString();
                } else {
                    args[i + 1] = Undefined.instance;
                }
            }
            args[parenCount + 1] = Integer.valueOf(reImpl.leftContext.length);
            args[parenCount + 2] = rdata.str;
            if (reImpl != ScriptRuntime.getRegExpProxy(cx)) {
                Kit.codeBug();
            }
            RegExpImpl re2 = new RegExpImpl();
            re2.multiline = reImpl.multiline;
            re2.input = reImpl.input;
            ScriptRuntime.setRegExpProxy(cx, re2);
            try {
                Scriptable parent = ScriptableObject.getTopLevelScope(scope);
                Object result = rdata.lambda.call(cx, parent, parent, args);
                lambdaStr = ScriptRuntime.toString(result);
                ScriptRuntime.setRegExpProxy(cx, reImpl);
                replen = lambdaStr.length();
            } catch (Throwable th) {
                ScriptRuntime.setRegExpProxy(cx, reImpl);
                throw th;
            }
        } else {
            lambdaStr = null;
            replen = rdata.repstr.length();
            if (rdata.dollar >= 0) {
                int[] skip = new int[1];
                int dp2 = rdata.dollar;
                do {
                    SubString sub2 = interpretDollar(cx, reImpl, rdata.repstr, dp2, skip);
                    if (sub2 != null) {
                        replen += sub2.length - skip[0];
                        dp = dp2 + skip[0];
                    } else {
                        dp = dp2 + 1;
                    }
                    dp2 = rdata.repstr.indexOf(36, dp);
                } while (dp2 >= 0);
            }
        }
        int growth = leftlen + replen + reImpl.rightContext.length;
        StringBuilder charBuf = rdata.charBuf;
        if (charBuf == null) {
            charBuf = new StringBuilder(growth);
            rdata.charBuf = charBuf;
        } else {
            charBuf.ensureCapacity(rdata.charBuf.length() + growth);
        }
        charBuf.append((CharSequence) reImpl.leftContext.str, leftIndex, leftIndex + leftlen);
        if (rdata.lambda != null) {
            charBuf.append(lambdaStr);
        } else {
            do_replace(rdata, cx, reImpl);
        }
    }

    private static SubString interpretDollar(Context cx, RegExpImpl res, String da, int dp, int[] skip) throws RuntimeException {
        int daL;
        int num;
        int cp;
        int tmp;
        int tmp2;
        if (da.charAt(dp) != '$') {
            Kit.codeBug();
        }
        int version = cx.getLanguageVersion();
        if ((version != 0 && version <= 140 && dp > 0 && da.charAt(dp - 1) == '\\') || dp + 1 >= (daL = da.length())) {
            return null;
        }
        char dc = da.charAt(dp + 1);
        if (NativeRegExp.isDigit(dc)) {
            if (version != 0 && version <= 140) {
                if (dc != '0') {
                    num = 0;
                    cp = dp;
                    while (true) {
                        cp++;
                        if (cp >= daL) {
                            break;
                        }
                        char dc2 = da.charAt(cp);
                        if (!NativeRegExp.isDigit(dc2) || (tmp2 = (10 * num) + (dc2 - '0')) < num) {
                            break;
                        }
                        num = tmp2;
                    }
                } else {
                    return null;
                }
            } else {
                int parenCount = res.parens == null ? 0 : res.parens.length;
                num = dc - '0';
                if (num > parenCount) {
                    return null;
                }
                cp = dp + 2;
                if (dp + 2 < daL) {
                    char dc3 = da.charAt(dp + 2);
                    if (NativeRegExp.isDigit(dc3) && (tmp = (10 * num) + (dc3 - '0')) <= parenCount) {
                        cp++;
                        num = tmp;
                    }
                }
                if (num == 0) {
                    return null;
                }
            }
            skip[0] = cp - dp;
            return res.getParenSubString(num - 1);
        }
        skip[0] = 2;
        switch (dc) {
            case '$':
                return new SubString(PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX);
            case '&':
                return res.lastMatch;
            case '\'':
                return res.rightContext;
            case '+':
                return res.lastParen;
            case '`':
                if (version == 120) {
                    res.leftContext.index = 0;
                    res.leftContext.length = res.lastMatch.index;
                }
                return res.leftContext;
            default:
                return null;
        }
    }

    private static void do_replace(GlobData rdata, Context cx, RegExpImpl regExpImpl) throws RuntimeException {
        int dp;
        StringBuilder charBuf = rdata.charBuf;
        int cp = 0;
        String da = rdata.repstr;
        int dp2 = rdata.dollar;
        if (dp2 != -1) {
            int[] skip = new int[1];
            do {
                int i = dp2 - cp;
                charBuf.append(da.substring(cp, dp2));
                cp = dp2;
                SubString sub = interpretDollar(cx, regExpImpl, da, dp2, skip);
                if (sub != null) {
                    int len = sub.length;
                    if (len > 0) {
                        charBuf.append((CharSequence) sub.str, sub.index, sub.index + len);
                    }
                    cp += skip[0];
                    dp = dp2 + skip[0];
                } else {
                    dp = dp2 + 1;
                }
                dp2 = da.indexOf(36, dp);
            } while (dp2 >= 0);
        }
        int daL = da.length();
        if (daL > cp) {
            charBuf.append(da.substring(cp, daL));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v29, types: [java.lang.String[], java.lang.String[][]] */
    @Override // org.mozilla.javascript.RegExpProxy
    public Object js_split(Context cx, Scriptable scope, String target, Object[] args) {
        Scriptable scriptableNewArray = cx.newArray(scope, 0);
        boolean limited = args.length > 1 && args[1] != Undefined.instance;
        long limit = 0;
        if (limited) {
            limit = ScriptRuntime.toUint32(args[1]);
            if (limit == 0) {
                return scriptableNewArray;
            }
            if (limit > target.length()) {
                limit = 1 + target.length();
            }
        }
        if (args.length < 1 || args[0] == Undefined.instance) {
            scriptableNewArray.put(0, scriptableNewArray, target);
            return scriptableNewArray;
        }
        String separator = null;
        int[] matchlen = new int[1];
        Scriptable re = null;
        RegExpProxy reProxy = null;
        if (args[0] instanceof Scriptable) {
            reProxy = ScriptRuntime.getRegExpProxy(cx);
            if (reProxy != null) {
                Scriptable test = (Scriptable) args[0];
                if (reProxy.isRegExp(test)) {
                    re = test;
                }
            }
        }
        if (re == null) {
            separator = ScriptRuntime.toString(args[0]);
            matchlen[0] = separator.length();
        }
        int[] ip = {0};
        int len = 0;
        boolean[] matched = {false};
        ?? r0 = {0};
        int version = cx.getLanguageVersion();
        while (true) {
            int match = find_split(cx, scope, target, separator, version, reProxy, re, ip, matchlen, matched, r0);
            if (match < 0 || ((limited && len >= limit) || match > target.length())) {
                break;
            }
            Object substr = target.length() == 0 ? target : target.substring(ip[0], match);
            scriptableNewArray.put(len, scriptableNewArray, substr);
            len++;
            if (re != null && matched[0]) {
                int size = r0[0].length;
                for (int num = 0; num < size && (!limited || len < limit); num++) {
                    scriptableNewArray.put(len, scriptableNewArray, r0[0][num]);
                    len++;
                }
                matched[0] = false;
            }
            ip[0] = match + matchlen[0];
            if (version < 130 && version != 0 && !limited && ip[0] == target.length()) {
                break;
            }
        }
        return scriptableNewArray;
    }

    private static int find_split(Context cx, Scriptable scope, String target, String separator, int version, RegExpProxy reProxy, Scriptable re, int[] ip, int[] matchlen, boolean[] matched, String[][] parensp) {
        int i;
        int i2 = ip[0];
        int length = target.length();
        if (version == 120 && re == null && separator.length() == 1 && separator.charAt(0) == ' ') {
            if (i2 == 0) {
                while (i2 < length && Character.isWhitespace(target.charAt(i2))) {
                    i2++;
                }
                ip[0] = i2;
            }
            if (i2 == length) {
                return -1;
            }
            while (i2 < length && !Character.isWhitespace(target.charAt(i2))) {
                i2++;
            }
            int j = i2;
            while (j < length && Character.isWhitespace(target.charAt(j))) {
                j++;
            }
            matchlen[0] = j - i2;
            return i2;
        }
        if (i2 > length) {
            return -1;
        }
        if (re != null) {
            return reProxy.find_split(cx, scope, target, separator, re, ip, matchlen, matched, parensp);
        }
        if (version != 0 && version < 130 && length == 0) {
            return -1;
        }
        if (separator.length() == 0) {
            if (version == 120) {
                if (i2 == length) {
                    matchlen[0] = 1;
                    return i2;
                }
                return i2 + 1;
            }
            if (i2 == length) {
                return -1;
            }
            return i2 + 1;
        }
        if (ip[0] < length && (i = target.indexOf(separator, ip[0])) != -1) {
            return i;
        }
        return length;
    }
}
