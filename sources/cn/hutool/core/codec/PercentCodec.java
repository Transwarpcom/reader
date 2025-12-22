package cn.hutool.core.codec;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.BitSet;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/codec/PercentCodec.class */
public class PercentCodec implements Serializable {
    private static final long serialVersionUID = 1;
    private final BitSet safeCharacters;
    private boolean encodeSpaceAsPlus;

    public static PercentCodec of(PercentCodec codec) {
        return new PercentCodec((BitSet) codec.safeCharacters.clone());
    }

    public static PercentCodec of(CharSequence chars) {
        PercentCodec codec = new PercentCodec();
        int length = chars.length();
        for (int i = 0; i < length; i++) {
            codec.addSafe(chars.charAt(i));
        }
        return codec;
    }

    public PercentCodec() {
        this(new BitSet(256));
    }

    public PercentCodec(BitSet safeCharacters) {
        this.encodeSpaceAsPlus = false;
        this.safeCharacters = safeCharacters;
    }

    public PercentCodec addSafe(char c) {
        this.safeCharacters.set(c);
        return this;
    }

    public PercentCodec removeSafe(char c) {
        this.safeCharacters.clear(c);
        return this;
    }

    public PercentCodec or(PercentCodec codec) {
        this.safeCharacters.or(codec.safeCharacters);
        return this;
    }

    public PercentCodec orNew(PercentCodec codec) {
        return of(this).or(codec);
    }

    public PercentCodec setEncodeSpaceAsPlus(boolean encodeSpaceAsPlus) {
        this.encodeSpaceAsPlus = encodeSpaceAsPlus;
        return this;
    }

    public String encode(CharSequence path, Charset charset) throws IOException {
        if (null == charset || StrUtil.isEmpty(path)) {
            return StrUtil.str(path);
        }
        StringBuilder rewrittenPath = new StringBuilder(path.length());
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(buf, charset);
        for (int i = 0; i < path.length(); i++) {
            int c = path.charAt(i);
            if (this.safeCharacters.get(c)) {
                rewrittenPath.append((char) c);
            } else if (this.encodeSpaceAsPlus && c == 32) {
                rewrittenPath.append('+');
            } else {
                try {
                    writer.write((char) c);
                    writer.flush();
                    byte[] ba = buf.toByteArray();
                    for (byte toEncode : ba) {
                        rewrittenPath.append('%');
                        HexUtil.appendHex(rewrittenPath, toEncode, false);
                    }
                    buf.reset();
                } catch (IOException e) {
                    buf.reset();
                }
            }
        }
        return rewrittenPath.toString();
    }
}
