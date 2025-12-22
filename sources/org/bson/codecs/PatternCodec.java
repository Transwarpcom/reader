package org.bson.codecs;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.bson.BsonReader;
import org.bson.BsonRegularExpression;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/PatternCodec.class */
public class PatternCodec implements Codec<Pattern> {
    private static final int GLOBAL_FLAG = 256;

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, Pattern value, EncoderContext encoderContext) {
        writer.writeRegularExpression(new BsonRegularExpression(value.pattern(), getOptionsAsString(value)));
    }

    @Override // org.bson.codecs.Decoder
    public Pattern decode(BsonReader reader, DecoderContext decoderContext) {
        BsonRegularExpression regularExpression = reader.readRegularExpression();
        return Pattern.compile(regularExpression.getPattern(), getOptionsAsInt(regularExpression));
    }

    @Override // org.bson.codecs.Encoder
    public Class<Pattern> getEncoderClass() {
        return Pattern.class;
    }

    private static String getOptionsAsString(Pattern pattern) {
        int flags = pattern.flags();
        StringBuilder buf = new StringBuilder();
        for (RegexFlag flag : RegexFlag.values()) {
            if ((pattern.flags() & flag.javaFlag) > 0) {
                buf.append(flag.flagChar);
                flags -= flag.javaFlag;
            }
        }
        if (flags > 0) {
            throw new IllegalArgumentException("some flags could not be recognized.");
        }
        return buf.toString();
    }

    private static int getOptionsAsInt(BsonRegularExpression regularExpression) {
        int optionsInt = 0;
        String optionsString = regularExpression.getOptions();
        if (optionsString == null || optionsString.length() == 0) {
            return 0;
        }
        String optionsString2 = optionsString.toLowerCase();
        for (int i = 0; i < optionsString2.length(); i++) {
            RegexFlag flag = RegexFlag.getByCharacter(optionsString2.charAt(i));
            if (flag == null) {
                throw new IllegalArgumentException("unrecognized flag [" + optionsString2.charAt(i) + "] " + ((int) optionsString2.charAt(i)));
            }
            optionsInt |= flag.javaFlag;
            if (flag.unsupported != null) {
            }
        }
        return optionsInt;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/PatternCodec$RegexFlag.class */
    private enum RegexFlag {
        CANON_EQ(128, 'c', "Pattern.CANON_EQ"),
        UNIX_LINES(1, 'd', "Pattern.UNIX_LINES"),
        GLOBAL(256, 'g', null),
        CASE_INSENSITIVE(2, 'i', null),
        MULTILINE(8, 'm', null),
        DOTALL(32, 's', "Pattern.DOTALL"),
        LITERAL(16, 't', "Pattern.LITERAL"),
        UNICODE_CASE(64, 'u', "Pattern.UNICODE_CASE"),
        COMMENTS(4, 'x', null);

        private static final Map<Character, RegexFlag> BY_CHARACTER = new HashMap();
        private final int javaFlag;
        private final char flagChar;
        private final String unsupported;

        static {
            for (RegexFlag flag : values()) {
                BY_CHARACTER.put(Character.valueOf(flag.flagChar), flag);
            }
        }

        public static RegexFlag getByCharacter(char ch2) {
            return BY_CHARACTER.get(Character.valueOf(ch2));
        }

        RegexFlag(int f, char ch2, String u) {
            this.javaFlag = f;
            this.flagChar = ch2;
            this.unsupported = u;
        }
    }
}
