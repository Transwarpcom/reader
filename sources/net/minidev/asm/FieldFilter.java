package net.minidev.asm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: reader.jar:BOOT-INF/lib/accessors-smart-2.4.7.jar:net/minidev/asm/FieldFilter.class */
public interface FieldFilter {
    boolean canUse(Field field);

    boolean canUse(Field field, Method method);

    boolean canRead(Field field);

    boolean canWrite(Field field);
}
