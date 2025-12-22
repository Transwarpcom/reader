package cn.hutool.core.lang;

import cn.hutool.core.lang.EnumItem;
import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/EnumItem.class */
public interface EnumItem<E extends EnumItem<E>> extends Serializable {
    String name();

    int intVal();

    default String text() {
        return name();
    }

    default E[] items() {
        return (E[]) ((EnumItem[]) getClass().getEnumConstants());
    }

    default E fromInt(Integer num) {
        if (num == null) {
            return null;
        }
        for (EnumItem enumItem : items()) {
            E e = (E) enumItem;
            if (e.intVal() == num.intValue()) {
                return e;
            }
        }
        return null;
    }

    default E fromStr(String str) {
        if (str == null) {
            return null;
        }
        for (EnumItem enumItem : items()) {
            E e = (E) enumItem;
            if (str.equalsIgnoreCase(e.name())) {
                return e;
            }
        }
        return null;
    }
}
