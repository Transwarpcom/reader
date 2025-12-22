package com.google.thirdparty.publicsuffix;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.List;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/thirdparty/publicsuffix/TrieParser.class */
final class TrieParser {
    private static final Joiner PREFIX_JOINER = Joiner.on("");

    TrieParser() {
    }

    static ImmutableMap<String, PublicSuffixType> parseTrie(CharSequence encoded) {
        ImmutableMap.Builder<String, PublicSuffixType> builder = ImmutableMap.builder();
        int encodedLen = encoded.length();
        int iDoParseTrieToBuilder = 0;
        while (true) {
            int idx = iDoParseTrieToBuilder;
            if (idx < encodedLen) {
                iDoParseTrieToBuilder = idx + doParseTrieToBuilder(Lists.newLinkedList(), encoded, idx, builder);
            } else {
                return builder.build();
            }
        }
    }

    private static int doParseTrieToBuilder(List<CharSequence> stack, CharSequence encoded, int start, ImmutableMap.Builder<String, PublicSuffixType> builder) {
        int encodedLen = encoded.length();
        int idx = start;
        char c = 0;
        while (idx < encodedLen) {
            c = encoded.charAt(idx);
            if (c == '&' || c == '?' || c == '!' || c == ':' || c == ',') {
                break;
            }
            idx++;
        }
        stack.add(0, reverse(encoded.subSequence(start, idx)));
        if (c == '!' || c == '?' || c == ':' || c == ',') {
            String domain = PREFIX_JOINER.join(stack);
            if (domain.length() > 0) {
                builder.put(domain, PublicSuffixType.fromCode(c));
            }
        }
        int idx2 = idx + 1;
        if (c != '?' && c != ',') {
            while (idx2 < encodedLen) {
                idx2 += doParseTrieToBuilder(stack, encoded, idx2, builder);
                if (encoded.charAt(idx2) == '?' || encoded.charAt(idx2) == ',') {
                    idx2++;
                    break;
                }
            }
        }
        stack.remove(0);
        return idx2 - start;
    }

    private static CharSequence reverse(CharSequence s) {
        return new StringBuilder(s).reverse();
    }
}
