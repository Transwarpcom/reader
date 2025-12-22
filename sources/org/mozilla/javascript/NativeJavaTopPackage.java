package org.mozilla.javascript;

import me.ag2s.epublib.epub.NCXDocumentV2;
import me.ag2s.epublib.epub.NCXDocumentV3;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/NativeJavaTopPackage.class */
public class NativeJavaTopPackage extends NativeJavaPackage implements Function, IdFunctionCall {
    private static final long serialVersionUID = -1455787259477709999L;
    private static final String[][] commonPackages = {new String[]{"java", NCXDocumentV3.XHTMLAttributes.lang, "reflect"}, new String[]{"java", "io"}, new String[]{"java", "math"}, new String[]{"java", "net"}, new String[]{"java", "util", "zip"}, new String[]{"java", NCXDocumentV2.NCXTags.text, "resources"}, new String[]{"java", "applet"}, new String[]{"javax", "swing"}};
    private static final Object FTAG = "JavaTopPackage";
    private static final int Id_getClass = 1;

    NativeJavaTopPackage(ClassLoader loader) {
        super(true, "", loader);
    }

    @Override // org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        return construct(cx, scope, args);
    }

    @Override // org.mozilla.javascript.Function
    public Scriptable construct(Context cx, Scriptable scope, Object[] args) {
        ClassLoader loader = null;
        if (args.length != 0) {
            Object arg = args[0];
            if (arg instanceof Wrapper) {
                arg = ((Wrapper) arg).unwrap();
            }
            if (arg instanceof ClassLoader) {
                loader = (ClassLoader) arg;
            }
        }
        if (loader == null) {
            Context.reportRuntimeError0("msg.not.classloader");
            return null;
        }
        NativeJavaPackage pkg = new NativeJavaPackage(true, "", loader);
        ScriptRuntime.setObjectProtoAndParent(pkg, scope);
        return pkg;
    }

    public static void init(Context cx, Scriptable scope, boolean sealed) {
        ClassLoader loader = cx.getApplicationClassLoader();
        NativeJavaTopPackage nativeJavaTopPackage = new NativeJavaTopPackage(loader);
        nativeJavaTopPackage.setPrototype(getObjectPrototype(scope));
        nativeJavaTopPackage.setParentScope(scope);
        for (int i = 0; i != commonPackages.length; i++) {
            NativeJavaPackage parent = nativeJavaTopPackage;
            for (int j = 0; j != commonPackages[i].length; j++) {
                parent = parent.forcePackage(commonPackages[i][j], scope);
            }
        }
        IdFunctionObject getClass = new IdFunctionObject(nativeJavaTopPackage, FTAG, 1, "getClass", 1, scope);
        String[] topNames = ScriptRuntime.getTopPackageNames();
        Object[] topPackages = new NativeJavaPackage[topNames.length];
        for (int i2 = 0; i2 < topNames.length; i2++) {
            topPackages[i2] = (NativeJavaPackage) nativeJavaTopPackage.get(topNames[i2], nativeJavaTopPackage);
        }
        ScriptableObject scriptableObject = (ScriptableObject) scope;
        if (sealed) {
            getClass.sealObject();
        }
        getClass.exportAsScopeProperty();
        scriptableObject.defineProperty("Packages", nativeJavaTopPackage, 2);
        for (int i3 = 0; i3 < topNames.length; i3++) {
            scriptableObject.defineProperty(topNames[i3], topPackages[i3], 2);
        }
    }

    @Override // org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (f.hasTag(FTAG) && f.methodId() == 1) {
            return js_getClass(cx, scope, args);
        }
        throw f.unknown();
    }

    private Scriptable js_getClass(Context cx, Scriptable scope, Object[] args) {
        String strSubstring;
        if (args.length > 0 && (args[0] instanceof Wrapper)) {
            Scriptable result = this;
            Class<?> cl = ((Wrapper) args[0]).unwrap().getClass();
            String name = cl.getName();
            int i = 0;
            while (true) {
                int offset = i;
                int index = name.indexOf(46, offset);
                if (index == -1) {
                    strSubstring = name.substring(offset);
                } else {
                    strSubstring = name.substring(offset, index);
                }
                String propName = strSubstring;
                Object prop = result.get(propName, result);
                if (!(prop instanceof Scriptable)) {
                    break;
                }
                result = (Scriptable) prop;
                if (index == -1) {
                    return result;
                }
                i = index + 1;
            }
        }
        throw Context.reportRuntimeError0("msg.not.java.obj");
    }
}
