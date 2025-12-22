package org.springframework.boot.convert;

import java.util.EnumSet;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.Assert;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/convert/StringToEnumIgnoringCaseConverterFactory.class */
final class StringToEnumIgnoringCaseConverterFactory implements ConverterFactory<String, Enum> {
    StringToEnumIgnoringCaseConverterFactory() {
    }

    @Override // org.springframework.core.convert.converter.ConverterFactory
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        Class<T> cls;
        Class<T> superclass = targetType;
        while (true) {
            cls = superclass;
            if (cls == null || cls.isEnum()) {
                break;
            }
            superclass = cls.getSuperclass();
        }
        Assert.notNull(cls, (Supplier<String>) () -> {
            return "The target type " + targetType.getName() + " does not refer to an enum";
        });
        return new StringToEnum(cls);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/convert/StringToEnumIgnoringCaseConverterFactory$StringToEnum.class */
    private class StringToEnum<T extends Enum> implements Converter<String, T> {
        private final Class<T> enumType;

        StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override // org.springframework.core.convert.converter.Converter
        public T convert(String str) {
            if (str.isEmpty()) {
                return null;
            }
            String strTrim = str.trim();
            try {
                return (T) Enum.valueOf(this.enumType, strTrim);
            } catch (Exception e) {
                return (T) findEnum(strTrim);
            }
        }

        private T findEnum(String source) {
            String name = getLettersAndDigits(source);
            for (T t : EnumSet.allOf(this.enumType)) {
                if (getLettersAndDigits(t.name()).equals(name)) {
                    return t;
                }
            }
            throw new IllegalArgumentException("No enum constant " + this.enumType.getCanonicalName() + "." + source);
        }

        private String getLettersAndDigits(String name) {
            StringBuilder canonicalName = new StringBuilder(name.length());
            IntStream map = name.chars().map(c -> {
                return (char) c;
            }).filter(Character::isLetterOrDigit).map(Character::toLowerCase);
            canonicalName.getClass();
            map.forEach(canonicalName::append);
            return canonicalName.toString();
        }
    }
}
