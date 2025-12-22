package org.springframework.boot.context.properties.bind;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/bind/BeanPropertyName.class */
abstract class BeanPropertyName {
    private BeanPropertyName() {
    }

    public static String toDashedForm(String name) {
        return toDashedForm(name, 0);
    }

    public static String toDashedForm(String name, int start) {
        StringBuilder result = new StringBuilder();
        char[] chars = name.replace("_", "-").toCharArray();
        for (int i = start; i < chars.length; i++) {
            char ch2 = chars[i];
            if (Character.isUpperCase(ch2) && result.length() > 0 && result.charAt(result.length() - 1) != '-') {
                result.append("-");
            }
            result.append(Character.toLowerCase(ch2));
        }
        return result.toString();
    }
}
