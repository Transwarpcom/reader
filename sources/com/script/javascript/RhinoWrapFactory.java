package com.script.javascript;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import org.mozilla.javascript.ClassShutter;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.WrapFactory;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/javascript/RhinoWrapFactory.class */
final class RhinoWrapFactory extends WrapFactory {
    private static RhinoWrapFactory theInstance;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !RhinoWrapFactory.class.desiredAssertionStatus();
    }

    private RhinoWrapFactory() {
    }

    static synchronized WrapFactory getInstance() {
        if (theInstance == null) {
            theInstance = new RhinoWrapFactory();
        }
        return theInstance;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/javascript/RhinoWrapFactory$RhinoJavaObject.class */
    private static class RhinoJavaObject extends NativeJavaObject {
        RhinoJavaObject(Scriptable scope, Object obj, Class type) {
            super(scope, null, type);
            this.javaObject = obj;
        }

        @Override // org.mozilla.javascript.NativeJavaObject, org.mozilla.javascript.Scriptable
        public Object get(String name, Scriptable start) {
            if (name.equals("getClass") || name.equals("exec")) {
                return NOT_FOUND;
            }
            return super.get(name, start);
        }
    }

    @Override // org.mozilla.javascript.WrapFactory
    public Scriptable wrapAsJavaObject(Context cx, Scriptable scope, Object javaObject, Class staticType) {
        if (scope != null) {
            scope.delete("Packages");
        }
        SecurityManager sm = System.getSecurityManager();
        ClassShutter classShutter = RhinoClassShutter.getInstance();
        if (javaObject instanceof ClassLoader) {
            if (sm != null) {
                sm.checkPermission(new RuntimePermission("getClassLoader"));
            }
            return super.wrapAsJavaObject(cx, scope, javaObject, staticType);
        }
        String name = null;
        if (javaObject instanceof Class) {
            name = ((Class) javaObject).getName();
        } else if (javaObject instanceof Member) {
            Member member = (Member) javaObject;
            if (sm != null && !Modifier.isPublic(member.getModifiers())) {
                return null;
            }
            name = member.getDeclaringClass().getName();
        }
        if (name != null) {
            if (!classShutter.visibleToScripts(name)) {
                return null;
            }
            return super.wrapAsJavaObject(cx, scope, javaObject, staticType);
        }
        Class dynamicType = javaObject.getClass();
        String name2 = dynamicType.getName();
        if (!classShutter.visibleToScripts(name2)) {
            Class type = null;
            if (staticType != null && staticType.isInterface()) {
                type = staticType;
            } else {
                while (true) {
                    if (dynamicType == null) {
                        break;
                    }
                    dynamicType = dynamicType.getSuperclass();
                    String name3 = dynamicType.getName();
                    if (classShutter.visibleToScripts(name3)) {
                        type = dynamicType;
                        break;
                    }
                }
                if (!$assertionsDisabled && type == null) {
                    throw new AssertionError("java.lang.Object 不可访问");
                }
            }
            return new RhinoJavaObject(scope, javaObject, type);
        }
        return super.wrapAsJavaObject(cx, scope, javaObject, staticType);
    }
}
