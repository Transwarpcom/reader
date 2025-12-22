package org.mozilla.javascript.regexp;

import org.mozilla.javascript.Scriptable;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/regexp/NativeRegExpInstantiator.class */
public class NativeRegExpInstantiator {
    private NativeRegExpInstantiator() {
    }

    static NativeRegExp withLanguageVersion(int languageVersion) {
        if (languageVersion < 200) {
            return new NativeRegExpCallable();
        }
        return new NativeRegExp();
    }

    static NativeRegExp withLanguageVersionScopeCompiled(int languageVersion, Scriptable scope, RECompiled compiled) {
        if (languageVersion < 200) {
            return new NativeRegExpCallable(scope, compiled);
        }
        return new NativeRegExp(scope, compiled);
    }
}
