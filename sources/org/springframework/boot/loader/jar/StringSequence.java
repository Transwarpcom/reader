package org.springframework.boot.loader.jar;

import java.util.Objects;

/* loaded from: reader.jar:org/springframework/boot/loader/jar/StringSequence.class */
final class StringSequence implements CharSequence {
    private final String source;
    private final int start;
    private final int end;
    private int hash;

    StringSequence(String source) {
        this(source, 0, source != null ? source.length() : -1);
    }

    StringSequence(String source, int start, int end) {
        Objects.requireNonNull(source, "Source must not be null");
        if (start < 0) {
            throw new StringIndexOutOfBoundsException(start);
        }
        if (end > source.length()) {
            throw new StringIndexOutOfBoundsException(end);
        }
        this.source = source;
        this.start = start;
        this.end = end;
    }

    public StringSequence subSequence(int start) {
        return subSequence(start, length());
    }

    @Override // java.lang.CharSequence
    public StringSequence subSequence(int start, int end) {
        int subSequenceStart = this.start + start;
        int subSequenceEnd = this.start + end;
        if (subSequenceStart > this.end) {
            throw new StringIndexOutOfBoundsException(start);
        }
        if (subSequenceEnd > this.end) {
            throw new StringIndexOutOfBoundsException(end);
        }
        if (start == 0 && subSequenceEnd == this.end) {
            return this;
        }
        return new StringSequence(this.source, subSequenceStart, subSequenceEnd);
    }

    public boolean isEmpty() {
        return length() == 0;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.end - this.start;
    }

    @Override // java.lang.CharSequence
    public char charAt(int index) {
        return this.source.charAt(this.start + index);
    }

    public int indexOf(char ch2) {
        return this.source.indexOf(ch2, this.start) - this.start;
    }

    public int indexOf(String str) {
        return this.source.indexOf(str, this.start) - this.start;
    }

    public int indexOf(String str, int fromIndex) {
        return this.source.indexOf(str, this.start + fromIndex) - this.start;
    }

    public boolean startsWith(CharSequence prefix) {
        return startsWith(prefix, 0);
    }

    public boolean startsWith(CharSequence prefix, int offset) {
        int i;
        int i2;
        int prefixLength = prefix.length();
        if ((length() - prefixLength) - offset < 0) {
            return false;
        }
        int prefixOffset = 0;
        int sourceOffset = offset;
        do {
            int i3 = prefixLength;
            prefixLength--;
            if (i3 == 0) {
                return true;
            }
            i = sourceOffset;
            sourceOffset++;
            i2 = prefixOffset;
            prefixOffset++;
        } while (charAt(i) == prefix.charAt(i2));
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CharSequence)) {
            return false;
        }
        CharSequence other = (CharSequence) obj;
        int n = length();
        if (n != other.length()) {
            return false;
        }
        int i = 0;
        while (true) {
            int i2 = n;
            n--;
            if (i2 != 0) {
                if (charAt(i) != other.charAt(i)) {
                    return false;
                }
                i++;
            } else {
                return true;
            }
        }
    }

    public int hashCode() {
        int hash = this.hash;
        if (hash == 0 && length() > 0) {
            for (int i = this.start; i < this.end; i++) {
                hash = (31 * hash) + this.source.charAt(i);
            }
            this.hash = hash;
        }
        return hash;
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.source.substring(this.start, this.end);
    }
}
