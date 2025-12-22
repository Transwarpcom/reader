package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/text/translate/LookupTranslator.class */
public class LookupTranslator extends CharSequenceTranslator {
    private final HashMap<String, String> lookupMap = new HashMap<>();
    private final HashSet<Character> prefixSet = new HashSet<>();
    private final int shortest;
    private final int longest;

    public LookupTranslator(CharSequence[]... lookup) {
        int _shortest = Integer.MAX_VALUE;
        int _longest = 0;
        if (lookup != null) {
            for (CharSequence[] seq : lookup) {
                this.lookupMap.put(seq[0].toString(), seq[1].toString());
                this.prefixSet.add(Character.valueOf(seq[0].charAt(0)));
                int sz = seq[0].length();
                _shortest = sz < _shortest ? sz : _shortest;
                if (sz > _longest) {
                    _longest = sz;
                }
            }
        }
        this.shortest = _shortest;
        this.longest = _longest;
    }

    @Override // org.apache.commons.lang3.text.translate.CharSequenceTranslator
    public int translate(CharSequence input, int index, Writer out) throws IOException {
        if (this.prefixSet.contains(Character.valueOf(input.charAt(index)))) {
            int max = this.longest;
            if (index + this.longest > input.length()) {
                max = input.length() - index;
            }
            for (int i = max; i >= this.shortest; i--) {
                CharSequence subSeq = input.subSequence(index, index + i);
                String result = this.lookupMap.get(subSeq.toString());
                if (result != null) {
                    out.write(result);
                    return i;
                }
            }
            return 0;
        }
        return 0;
    }
}
