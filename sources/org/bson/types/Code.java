package org.bson.types;

import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/types/Code.class */
public class Code implements Serializable {
    private static final long serialVersionUID = 475535263314046697L;
    private final String code;

    public Code(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Code code1 = (Code) o;
        if (!this.code.equals(code1.code)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.code.hashCode();
    }

    public String toString() {
        return "Code{code='" + this.code + "'}";
    }
}
