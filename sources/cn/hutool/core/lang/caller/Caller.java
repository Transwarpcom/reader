package cn.hutool.core.lang.caller;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/caller/Caller.class */
public interface Caller {
    Class<?> getCaller();

    Class<?> getCallerCaller();

    Class<?> getCaller(int i);

    boolean isCalledBy(Class<?> cls);
}
