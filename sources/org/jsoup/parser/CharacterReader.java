package org.jsoup.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Locale;
import org.jsoup.UncheckedIOException;
import org.jsoup.helper.Validate;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/CharacterReader.class */
public final class CharacterReader {
    static final char EOF = 65535;
    private static final int maxStringCacheLen = 12;
    static final int maxBufferLen = 32768;
    static final int readAheadLimit = 24576;
    private static final int minReadAheadLen = 1024;
    private char[] charBuf;
    private Reader reader;
    private int bufLength;
    private int bufSplitPoint;
    private int bufPos;
    private int readerPos;
    private int bufMark;
    private static final int stringCacheSize = 512;
    private String[] stringCache;
    private boolean readFully;

    public CharacterReader(Reader input, int sz) throws IOException {
        this.bufMark = -1;
        this.stringCache = new String[512];
        Validate.notNull(input);
        Validate.isTrue(input.markSupported());
        this.reader = input;
        this.charBuf = new char[Math.min(sz, 32768)];
        bufferUp();
    }

    public CharacterReader(Reader input) {
        this(input, 32768);
    }

    public CharacterReader(String input) {
        this(new StringReader(input), input.length());
    }

    public void close() {
        if (this.reader == null) {
            return;
        }
        try {
            this.reader.close();
        } catch (IOException e) {
        } finally {
            this.reader = null;
            this.charBuf = null;
            this.stringCache = null;
        }
    }

    private void bufferUp() throws IOException {
        int pos;
        int offset;
        if (this.readFully || this.bufPos < this.bufSplitPoint) {
            return;
        }
        if (this.bufMark != -1) {
            pos = this.bufMark;
            offset = this.bufPos - this.bufMark;
        } else {
            pos = this.bufPos;
            offset = 0;
        }
        try {
            long skipped = this.reader.skip(pos);
            this.reader.mark(32768);
            int read = 0;
            while (read <= 1024) {
                int thisRead = this.reader.read(this.charBuf, read, this.charBuf.length - read);
                if (thisRead == -1) {
                    this.readFully = true;
                }
                if (thisRead <= 0) {
                    break;
                } else {
                    read += thisRead;
                }
            }
            this.reader.reset();
            if (read > 0) {
                Validate.isTrue(skipped == ((long) pos));
                this.bufLength = read;
                this.readerPos += pos;
                this.bufPos = offset;
                if (this.bufMark != -1) {
                    this.bufMark = 0;
                }
                this.bufSplitPoint = Math.min(this.bufLength, readAheadLimit);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public int pos() {
        return this.readerPos + this.bufPos;
    }

    public boolean isEmpty() throws IOException {
        bufferUp();
        return this.bufPos >= this.bufLength;
    }

    private boolean isEmptyNoBufferUp() {
        return this.bufPos >= this.bufLength;
    }

    public char current() throws IOException {
        bufferUp();
        if (isEmptyNoBufferUp()) {
            return (char) 65535;
        }
        return this.charBuf[this.bufPos];
    }

    char consume() throws IOException {
        bufferUp();
        char val = isEmptyNoBufferUp() ? (char) 65535 : this.charBuf[this.bufPos];
        this.bufPos++;
        return val;
    }

    void unconsume() {
        if (this.bufPos < 1) {
            throw new UncheckedIOException(new IOException("WTF: No buffer left to unconsume."));
        }
        this.bufPos--;
    }

    public void advance() {
        this.bufPos++;
    }

    void mark() throws IOException {
        if (this.bufLength - this.bufPos < 1024) {
            this.bufSplitPoint = 0;
        }
        bufferUp();
        this.bufMark = this.bufPos;
    }

    void unmark() {
        this.bufMark = -1;
    }

    void rewindToMark() {
        if (this.bufMark == -1) {
            throw new UncheckedIOException(new IOException("Mark invalid"));
        }
        this.bufPos = this.bufMark;
        unmark();
    }

    int nextIndexOf(char c) throws IOException {
        bufferUp();
        for (int i = this.bufPos; i < this.bufLength; i++) {
            if (c == this.charBuf[i]) {
                return i - this.bufPos;
            }
        }
        return -1;
    }

    int nextIndexOf(CharSequence seq) throws IOException {
        bufferUp();
        char startChar = seq.charAt(0);
        int offset = this.bufPos;
        while (offset < this.bufLength) {
            if (startChar != this.charBuf[offset]) {
                do {
                    offset++;
                    if (offset >= this.bufLength) {
                        break;
                    }
                } while (startChar != this.charBuf[offset]);
            }
            int i = offset + 1;
            int last = (i + seq.length()) - 1;
            if (offset < this.bufLength && last <= this.bufLength) {
                for (int j = 1; i < last && seq.charAt(j) == this.charBuf[i]; j++) {
                    i++;
                }
                if (i == last) {
                    return offset - this.bufPos;
                }
            }
            offset++;
        }
        return -1;
    }

    public String consumeTo(char c) throws IOException {
        int offset = nextIndexOf(c);
        if (offset != -1) {
            String consumed = cacheString(this.charBuf, this.stringCache, this.bufPos, offset);
            this.bufPos += offset;
            return consumed;
        }
        return consumeToEnd();
    }

    String consumeTo(String seq) throws IOException {
        int offset = nextIndexOf(seq);
        if (offset != -1) {
            String consumed = cacheString(this.charBuf, this.stringCache, this.bufPos, offset);
            this.bufPos += offset;
            return consumed;
        }
        if (this.bufLength - this.bufPos < seq.length()) {
            return consumeToEnd();
        }
        int endPos = (this.bufLength - seq.length()) + 1;
        String consumed2 = cacheString(this.charBuf, this.stringCache, this.bufPos, endPos - this.bufPos);
        this.bufPos = endPos;
        return consumed2;
    }

    public String consumeToAny(char... chars) throws IOException {
        bufferUp();
        int pos = this.bufPos;
        int remaining = this.bufLength;
        char[] val = this.charBuf;
        loop0: while (pos < remaining) {
            for (char c : chars) {
                if (val[pos] == c) {
                    break loop0;
                }
            }
            pos++;
        }
        this.bufPos = pos;
        return pos > pos ? cacheString(this.charBuf, this.stringCache, pos, pos - pos) : "";
    }

    String consumeToAnySorted(char... chars) throws IOException {
        bufferUp();
        int pos = this.bufPos;
        int remaining = this.bufLength;
        char[] val = this.charBuf;
        while (pos < remaining && Arrays.binarySearch(chars, val[pos]) < 0) {
            pos++;
        }
        this.bufPos = pos;
        return this.bufPos > pos ? cacheString(this.charBuf, this.stringCache, pos, pos - pos) : "";
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0061 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    java.lang.String consumeData() {
        /*
            r6 = this;
            r0 = r6
            int r0 = r0.bufPos
            r7 = r0
            r0 = r7
            r8 = r0
            r0 = r6
            int r0 = r0.bufLength
            r9 = r0
            r0 = r6
            char[] r0 = r0.charBuf
            r10 = r0
        L12:
            r0 = r7
            r1 = r9
            if (r0 >= r1) goto L45
            r0 = r10
            r1 = r7
            char r0 = r0[r1]
            switch(r0) {
                case 0: goto L3c;
                case 38: goto L3c;
                case 60: goto L3c;
                default: goto L3f;
            }
        L3c:
            goto L45
        L3f:
            int r7 = r7 + 1
            goto L12
        L45:
            r0 = r6
            r1 = r7
            r0.bufPos = r1
            r0 = r7
            r1 = r8
            if (r0 <= r1) goto L61
            r0 = r6
            char[] r0 = r0.charBuf
            r1 = r6
            java.lang.String[] r1 = r1.stringCache
            r2 = r8
            r3 = r7
            r4 = r8
            int r3 = r3 - r4
            java.lang.String r0 = cacheString(r0, r1, r2, r3)
            goto L63
        L61:
            java.lang.String r0 = ""
        L63:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.CharacterReader.consumeData():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007b A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    java.lang.String consumeAttributeQuoted(boolean r7) {
        /*
            r6 = this;
            r0 = r6
            int r0 = r0.bufPos
            r8 = r0
            r0 = r8
            r9 = r0
            r0 = r6
            int r0 = r0.bufLength
            r10 = r0
            r0 = r6
            char[] r0 = r0.charBuf
            r11 = r0
        L13:
            r0 = r8
            r1 = r10
            if (r0 >= r1) goto L5f
            r0 = r11
            r1 = r8
            char r0 = r0[r1]
            switch(r0) {
                case 0: goto L48;
                case 34: goto L52;
                case 38: goto L48;
                case 39: goto L4b;
                default: goto L59;
            }
        L48:
            goto L5f
        L4b:
            r0 = r7
            if (r0 == 0) goto L52
            goto L5f
        L52:
            r0 = r7
            if (r0 != 0) goto L59
            goto L5f
        L59:
            int r8 = r8 + 1
            goto L13
        L5f:
            r0 = r6
            r1 = r8
            r0.bufPos = r1
            r0 = r8
            r1 = r9
            if (r0 <= r1) goto L7b
            r0 = r6
            char[] r0 = r0.charBuf
            r1 = r6
            java.lang.String[] r1 = r1.stringCache
            r2 = r9
            r3 = r8
            r4 = r9
            int r3 = r3 - r4
            java.lang.String r0 = cacheString(r0, r1, r2, r3)
            goto L7d
        L7b:
            java.lang.String r0 = ""
        L7d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.CharacterReader.consumeAttributeQuoted(boolean):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0059 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    java.lang.String consumeRawData() {
        /*
            r6 = this;
            r0 = r6
            int r0 = r0.bufPos
            r7 = r0
            r0 = r7
            r8 = r0
            r0 = r6
            int r0 = r0.bufLength
            r9 = r0
            r0 = r6
            char[] r0 = r0.charBuf
            r10 = r0
        L12:
            r0 = r7
            r1 = r9
            if (r0 >= r1) goto L3d
            r0 = r10
            r1 = r7
            char r0 = r0[r1]
            switch(r0) {
                case 0: goto L34;
                case 60: goto L34;
                default: goto L37;
            }
        L34:
            goto L3d
        L37:
            int r7 = r7 + 1
            goto L12
        L3d:
            r0 = r6
            r1 = r7
            r0.bufPos = r1
            r0 = r7
            r1 = r8
            if (r0 <= r1) goto L59
            r0 = r6
            char[] r0 = r0.charBuf
            r1 = r6
            java.lang.String[] r1 = r1.stringCache
            r2 = r8
            r3 = r7
            r4 = r8
            int r3 = r3 - r4
            java.lang.String r0 = cacheString(r0, r1, r2, r3)
            goto L5b
        L59:
            java.lang.String r0 = ""
        L5b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.CharacterReader.consumeRawData():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0095 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    java.lang.String consumeTagName() throws java.io.IOException {
        /*
            r6 = this;
            r0 = r6
            r0.bufferUp()
            r0 = r6
            int r0 = r0.bufPos
            r7 = r0
            r0 = r7
            r8 = r0
            r0 = r6
            int r0 = r0.bufLength
            r9 = r0
            r0 = r6
            char[] r0 = r0.charBuf
            r10 = r0
        L16:
            r0 = r7
            r1 = r9
            if (r0 >= r1) goto L79
            r0 = r10
            r1 = r7
            char r0 = r0[r1]
            switch(r0) {
                case 0: goto L70;
                case 9: goto L70;
                case 10: goto L70;
                case 12: goto L70;
                case 13: goto L70;
                case 32: goto L70;
                case 47: goto L70;
                case 60: goto L70;
                case 62: goto L70;
                default: goto L73;
            }
        L70:
            goto L79
        L73:
            int r7 = r7 + 1
            goto L16
        L79:
            r0 = r6
            r1 = r7
            r0.bufPos = r1
            r0 = r7
            r1 = r8
            if (r0 <= r1) goto L95
            r0 = r6
            char[] r0 = r0.charBuf
            r1 = r6
            java.lang.String[] r1 = r1.stringCache
            r2 = r8
            r3 = r7
            r4 = r8
            int r3 = r3 - r4
            java.lang.String r0 = cacheString(r0, r1, r2, r3)
            goto L97
        L95:
            java.lang.String r0 = ""
        L97:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.CharacterReader.consumeTagName():java.lang.String");
    }

    String consumeToEnd() throws IOException {
        bufferUp();
        String data = cacheString(this.charBuf, this.stringCache, this.bufPos, this.bufLength - this.bufPos);
        this.bufPos = this.bufLength;
        return data;
    }

    String consumeLetterSequence() throws IOException {
        char c;
        bufferUp();
        int start = this.bufPos;
        while (this.bufPos < this.bufLength && (((c = this.charBuf[this.bufPos]) >= 'A' && c <= 'Z') || ((c >= 'a' && c <= 'z') || Character.isLetter(c)))) {
            this.bufPos++;
        }
        return cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
    }

    String consumeLetterThenDigitSequence() throws IOException {
        char c;
        char c2;
        bufferUp();
        int start = this.bufPos;
        while (this.bufPos < this.bufLength && (((c2 = this.charBuf[this.bufPos]) >= 'A' && c2 <= 'Z') || ((c2 >= 'a' && c2 <= 'z') || Character.isLetter(c2)))) {
            this.bufPos++;
        }
        while (!isEmptyNoBufferUp() && (c = this.charBuf[this.bufPos]) >= '0' && c <= '9') {
            this.bufPos++;
        }
        return cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
    }

    String consumeHexSequence() throws IOException {
        char c;
        bufferUp();
        int start = this.bufPos;
        while (this.bufPos < this.bufLength && (((c = this.charBuf[this.bufPos]) >= '0' && c <= '9') || ((c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f')))) {
            this.bufPos++;
        }
        return cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
    }

    String consumeDigitSequence() throws IOException {
        char c;
        bufferUp();
        int start = this.bufPos;
        while (this.bufPos < this.bufLength && (c = this.charBuf[this.bufPos]) >= '0' && c <= '9') {
            this.bufPos++;
        }
        return cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
    }

    boolean matches(char c) {
        return !isEmpty() && this.charBuf[this.bufPos] == c;
    }

    boolean matches(String seq) throws IOException {
        bufferUp();
        int scanLength = seq.length();
        if (scanLength > this.bufLength - this.bufPos) {
            return false;
        }
        for (int offset = 0; offset < scanLength; offset++) {
            if (seq.charAt(offset) != this.charBuf[this.bufPos + offset]) {
                return false;
            }
        }
        return true;
    }

    boolean matchesIgnoreCase(String seq) throws IOException {
        bufferUp();
        int scanLength = seq.length();
        if (scanLength > this.bufLength - this.bufPos) {
            return false;
        }
        for (int offset = 0; offset < scanLength; offset++) {
            char upScan = Character.toUpperCase(seq.charAt(offset));
            char upTarget = Character.toUpperCase(this.charBuf[this.bufPos + offset]);
            if (upScan != upTarget) {
                return false;
            }
        }
        return true;
    }

    boolean matchesAny(char... seq) throws IOException {
        if (isEmpty()) {
            return false;
        }
        bufferUp();
        char c = this.charBuf[this.bufPos];
        for (char seek : seq) {
            if (seek == c) {
                return true;
            }
        }
        return false;
    }

    boolean matchesAnySorted(char[] seq) throws IOException {
        bufferUp();
        return !isEmpty() && Arrays.binarySearch(seq, this.charBuf[this.bufPos]) >= 0;
    }

    boolean matchesLetter() {
        if (isEmpty()) {
            return false;
        }
        char c = this.charBuf[this.bufPos];
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || Character.isLetter(c);
    }

    boolean matchesDigit() {
        char c;
        return !isEmpty() && (c = this.charBuf[this.bufPos]) >= '0' && c <= '9';
    }

    boolean matchConsume(String seq) throws IOException {
        bufferUp();
        if (matches(seq)) {
            this.bufPos += seq.length();
            return true;
        }
        return false;
    }

    boolean matchConsumeIgnoreCase(String seq) {
        if (matchesIgnoreCase(seq)) {
            this.bufPos += seq.length();
            return true;
        }
        return false;
    }

    boolean containsIgnoreCase(String seq) {
        String loScan = seq.toLowerCase(Locale.ENGLISH);
        String hiScan = seq.toUpperCase(Locale.ENGLISH);
        return nextIndexOf(loScan) > -1 || nextIndexOf(hiScan) > -1;
    }

    public String toString() {
        if (this.bufLength - this.bufPos < 0) {
            return "";
        }
        return new String(this.charBuf, this.bufPos, this.bufLength - this.bufPos);
    }

    private static String cacheString(char[] charBuf, String[] stringCache, int start, int count) {
        String cached;
        if (count > 12) {
            return new String(charBuf, start, count);
        }
        if (count < 1) {
            return "";
        }
        int hash = 31 * count;
        int offset = start;
        for (int i = 0; i < count; i++) {
            int i2 = offset;
            offset++;
            hash = (31 * hash) + charBuf[i2];
        }
        int index = hash & 511;
        String cached2 = stringCache[index];
        if (cached2 == null) {
            cached = new String(charBuf, start, count);
            stringCache[index] = cached;
        } else {
            if (rangeEquals(charBuf, start, count, cached2)) {
                return cached2;
            }
            cached = new String(charBuf, start, count);
            stringCache[index] = cached;
        }
        return cached;
    }

    static boolean rangeEquals(char[] charBuf, int start, int count, String cached) {
        int i;
        int i2;
        if (count == cached.length()) {
            int i3 = start;
            int j = 0;
            do {
                int i4 = count;
                count--;
                if (i4 == 0) {
                    return true;
                }
                i = i3;
                i3++;
                i2 = j;
                j++;
            } while (charBuf[i] == cached.charAt(i2));
            return false;
        }
        return false;
    }

    boolean rangeEquals(int start, int count, String cached) {
        return rangeEquals(this.charBuf, start, count, cached);
    }
}
