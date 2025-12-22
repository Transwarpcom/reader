package org.mozilla.javascript.v8dtoa;

import java.util.Arrays;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/v8dtoa/FastDtoaBuilder.class */
public class FastDtoaBuilder {
    int point;
    static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    final char[] chars = new char[25];
    int end = 0;
    boolean formatted = false;

    void append(char c) {
        char[] cArr = this.chars;
        int i = this.end;
        this.end = i + 1;
        cArr[i] = c;
    }

    void decreaseLast() {
        char[] cArr = this.chars;
        int i = this.end - 1;
        cArr[i] = (char) (cArr[i] - 1);
    }

    public void reset() {
        this.end = 0;
        this.formatted = false;
    }

    public String toString() {
        return "[chars:" + new String(this.chars, 0, this.end) + ", point:" + this.point + "]";
    }

    public String format() {
        if (!this.formatted) {
            int firstDigit = this.chars[0] == '-' ? 1 : 0;
            int decPoint = this.point - firstDigit;
            if (decPoint < -5 || decPoint > 21) {
                toExponentialFormat(firstDigit, decPoint);
            } else {
                toFixedFormat(firstDigit, decPoint);
            }
            this.formatted = true;
        }
        return new String(this.chars, 0, this.end);
    }

    private void toFixedFormat(int firstDigit, int decPoint) {
        if (this.point < this.end) {
            if (decPoint > 0) {
                System.arraycopy(this.chars, this.point, this.chars, this.point + 1, this.end - this.point);
                this.chars[this.point] = '.';
                this.end++;
                return;
            }
            int target = (firstDigit + 2) - decPoint;
            System.arraycopy(this.chars, firstDigit, this.chars, target, this.end - firstDigit);
            this.chars[firstDigit] = '0';
            this.chars[firstDigit + 1] = '.';
            if (decPoint < 0) {
                Arrays.fill(this.chars, firstDigit + 2, target, '0');
            }
            this.end += 2 - decPoint;
            return;
        }
        if (this.point > this.end) {
            Arrays.fill(this.chars, this.end, this.point, '0');
            this.end += this.point - this.end;
        }
    }

    private void toExponentialFormat(int firstDigit, int decPoint) {
        if (this.end - firstDigit > 1) {
            int dot = firstDigit + 1;
            System.arraycopy(this.chars, dot, this.chars, dot + 1, this.end - dot);
            this.chars[dot] = '.';
            this.end++;
        }
        char[] cArr = this.chars;
        int i = this.end;
        this.end = i + 1;
        cArr[i] = 'e';
        char sign = '+';
        int exp = decPoint - 1;
        if (exp < 0) {
            sign = '-';
            exp = -exp;
        }
        char[] cArr2 = this.chars;
        int i2 = this.end;
        this.end = i2 + 1;
        cArr2[i2] = sign;
        int charPos = exp > 99 ? this.end + 2 : exp > 9 ? this.end + 1 : this.end;
        this.end = charPos + 1;
        do {
            int r = exp % 10;
            int i3 = charPos;
            charPos--;
            this.chars[i3] = digits[r];
            exp /= 10;
        } while (exp != 0);
    }
}
