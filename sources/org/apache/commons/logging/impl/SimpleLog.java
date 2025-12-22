package org.apache.commons.logging.impl;

import java.security.PrivilegedAction;

/* JADX WARN: Classes with same name are omitted:
  reader.jar:BOOT-INF/lib/commons-logging-1.2.jar:org/apache/commons/logging/impl/SimpleLog.class
 */
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/spring-jcl-5.1.8.RELEASE.jar:org/apache/commons/logging/impl/SimpleLog.class */
public class SimpleLog extends NoOpLog {
    public SimpleLog(String name) {
        super(name);
        System.out.println(SimpleLog.class.getName() + " is deprecated and equivalent to NoOpLog in spring-jcl. Use a standard LogFactory.getLog(Class/String) call instead.");
    }

    /* renamed from: org.apache.commons.logging.impl.SimpleLog$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/commons-logging-1.2.jar:org/apache/commons/logging/impl/SimpleLog$1.class */
    static class AnonymousClass1 implements PrivilegedAction {
        private final String val$name;

        AnonymousClass1(String str) {
            this.val$name = str;
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            ClassLoader threadCL = SimpleLog.access$000();
            if (threadCL != null) {
                return threadCL.getResourceAsStream(this.val$name);
            }
            return ClassLoader.getSystemResourceAsStream(this.val$name);
        }
    }
}
