package io.vertx.core.cli.converters;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/converters/CharacterConverter.class */
public final class CharacterConverter implements Converter<Character> {
    public static final CharacterConverter INSTANCE = new CharacterConverter();

    private CharacterConverter() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.cli.converters.Converter
    public Character fromString(String input) throws IllegalArgumentException {
        if (input == null) {
            throw new NullPointerException("input must not be null");
        }
        if (input.length() != 1) {
            throw new IllegalArgumentException("The input string \"" + input + "\" cannot be converted to a character. The input's length must be 1");
        }
        return Character.valueOf(input.toCharArray()[0]);
    }
}
