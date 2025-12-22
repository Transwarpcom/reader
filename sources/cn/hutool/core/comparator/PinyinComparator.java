package cn.hutool.core.comparator;

import java.io.Serializable;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/comparator/PinyinComparator.class */
public class PinyinComparator implements Comparator<String>, Serializable {
    private static final long serialVersionUID = 1;
    final Collator collator = Collator.getInstance(Locale.CHINESE);

    @Override // java.util.Comparator
    public int compare(String o1, String o2) {
        return this.collator.compare(o1, o2);
    }
}
