package cn.hutool.core.text.escape;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/text/escape/InternalEscapeUtil.class */
class InternalEscapeUtil {
    InternalEscapeUtil() {
    }

    public static String[][] invert(String[][] array) {
        String[][] newarray = new String[array.length][2];
        for (int i = 0; i < array.length; i++) {
            newarray[i][0] = array[i][1];
            newarray[i][1] = array[i][0];
        }
        return newarray;
    }
}
