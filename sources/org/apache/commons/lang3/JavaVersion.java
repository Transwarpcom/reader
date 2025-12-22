package org.apache.commons.lang3;

import org.apache.commons.lang3.math.NumberUtils;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/JavaVersion.class */
public enum JavaVersion {
    JAVA_0_9(1.5f, "0.9"),
    JAVA_1_1(1.1f, "1.1"),
    JAVA_1_2(1.2f, "1.2"),
    JAVA_1_3(1.3f, "1.3"),
    JAVA_1_4(1.4f, "1.4"),
    JAVA_1_5(1.5f, "1.5"),
    JAVA_1_6(1.6f, "1.6"),
    JAVA_1_7(1.7f, "1.7"),
    JAVA_1_8(1.8f, "1.8"),
    JAVA_1_9(9.0f, "9"),
    JAVA_9(9.0f, "9"),
    JAVA_10(10.0f, "10"),
    JAVA_11(11.0f, "11"),
    JAVA_RECENT(maxVersion(), Float.toString(maxVersion()));

    private final float value;
    private final String name;

    JavaVersion(float value, String name) {
        this.value = value;
        this.name = name;
    }

    public boolean atLeast(JavaVersion requiredVersion) {
        return this.value >= requiredVersion.value;
    }

    static JavaVersion getJavaVersion(String nom) {
        return get(nom);
    }

    static JavaVersion get(String nom) {
        if ("0.9".equals(nom)) {
            return JAVA_0_9;
        }
        if ("1.1".equals(nom)) {
            return JAVA_1_1;
        }
        if ("1.2".equals(nom)) {
            return JAVA_1_2;
        }
        if ("1.3".equals(nom)) {
            return JAVA_1_3;
        }
        if ("1.4".equals(nom)) {
            return JAVA_1_4;
        }
        if ("1.5".equals(nom)) {
            return JAVA_1_5;
        }
        if ("1.6".equals(nom)) {
            return JAVA_1_6;
        }
        if ("1.7".equals(nom)) {
            return JAVA_1_7;
        }
        if ("1.8".equals(nom)) {
            return JAVA_1_8;
        }
        if ("9".equals(nom)) {
            return JAVA_9;
        }
        if ("10".equals(nom)) {
            return JAVA_10;
        }
        if ("11".equals(nom)) {
            return JAVA_11;
        }
        if (nom == null) {
            return null;
        }
        float v = toFloatVersion(nom);
        if (v - 1.0d >= 1.0d) {
            if (v > 10.0f) {
                return JAVA_RECENT;
            }
            return null;
        }
        int firstComma = Math.max(nom.indexOf(46), nom.indexOf(44));
        int end = Math.max(nom.length(), nom.indexOf(44, firstComma));
        if (Float.parseFloat(nom.substring(firstComma + 1, end)) > 0.9f) {
            return JAVA_RECENT;
        }
        return null;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }

    private static float maxVersion() {
        float v = toFloatVersion(System.getProperty("java.specification.version", "99.0"));
        if (v > 0.0f) {
            return v;
        }
        return 99.0f;
    }

    private static float toFloatVersion(String value) {
        if (value.contains(".")) {
            String[] toParse = value.split("\\.");
            if (toParse.length >= 2) {
                return NumberUtils.toFloat(toParse[0] + '.' + toParse[1], -1.0f);
            }
            return -1.0f;
        }
        return NumberUtils.toFloat(value, -1.0f);
    }
}
