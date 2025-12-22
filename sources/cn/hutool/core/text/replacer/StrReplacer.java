package cn.hutool.core.text.replacer;

import cn.hutool.core.lang.Replacer;
import cn.hutool.core.text.StrBuilder;
import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/text/replacer/StrReplacer.class */
public abstract class StrReplacer implements Replacer<CharSequence>, Serializable {
    private static final long serialVersionUID = 1;

    protected abstract int replace(CharSequence charSequence, int i, StrBuilder strBuilder);

    @Override // cn.hutool.core.lang.Replacer
    public CharSequence replace(CharSequence t) {
        int len = t.length();
        StrBuilder builder = StrBuilder.create(len);
        int i = 0;
        while (true) {
            int pos = i;
            if (pos < len) {
                int consumed = replace(t, pos, builder);
                if (0 == consumed) {
                    builder.append(t.charAt(pos));
                    pos++;
                }
                i = pos + consumed;
            } else {
                return builder;
            }
        }
    }
}
