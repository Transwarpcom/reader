package org.apache.pdfbox.pdfparser;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObjectKey;
import org.apache.pdfbox.cos.COSStream;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdfparser/PDFXrefStreamParser.class */
public class PDFXrefStreamParser extends BaseParser {
    private final XrefTrailerResolver xrefTrailerResolver;
    private final int[] w;
    private ObjectNumbers objectNumbers;

    public PDFXrefStreamParser(COSStream stream, COSDocument document, XrefTrailerResolver resolver) throws IOException {
        super(new InputStreamSource(stream.createInputStream()));
        this.w = new int[3];
        this.objectNumbers = null;
        this.document = document;
        this.xrefTrailerResolver = resolver;
        try {
            initParserValues(stream);
        } catch (IOException exception) {
            close();
            throw exception;
        }
    }

    private void initParserValues(COSStream stream) throws IOException {
        COSArray wArray = stream.getCOSArray(COSName.W);
        if (wArray == null) {
            throw new IOException("/W array is missing in Xref stream");
        }
        if (wArray.size() != 3) {
            throw new IOException("Wrong number of values for /W array in XRef: " + Arrays.toString(this.w));
        }
        for (int i = 0; i < 3; i++) {
            this.w[i] = wArray.getInt(i, 0);
        }
        if (this.w[0] < 0 || this.w[1] < 0 || this.w[2] < 0) {
            throw new IOException("Incorrect /W array in XRef: " + Arrays.toString(this.w));
        }
        COSArray indexArray = stream.getCOSArray(COSName.INDEX);
        if (indexArray == null) {
            indexArray = new COSArray();
            indexArray.add((COSBase) COSInteger.ZERO);
            indexArray.add((COSBase) COSInteger.get(stream.getInt(COSName.SIZE, 0)));
        }
        if (indexArray.size() == 0 || indexArray.size() % 2 == 1) {
            throw new IOException("Wrong number of values for /Index array in XRef: " + Arrays.toString(this.w));
        }
        this.objectNumbers = new ObjectNumbers(indexArray);
    }

    private void close() throws IOException {
        if (this.seqSource != null) {
            this.seqSource.close();
        }
        this.document = null;
    }

    public void parse() throws IOException {
        byte[] currLine = new byte[this.w[0] + this.w[1] + this.w[2]];
        while (!this.seqSource.isEOF() && this.objectNumbers.hasNext()) {
            this.seqSource.read(currLine);
            long objID = this.objectNumbers.next().longValue();
            int type = this.w[0] == 0 ? 1 : (int) parseValue(currLine, 0, this.w[0]);
            if (type != 0) {
                long offset = parseValue(currLine, this.w[0], this.w[1]);
                int genNum = type == 1 ? (int) parseValue(currLine, this.w[0] + this.w[1], this.w[2]) : 0;
                COSObjectKey objKey = new COSObjectKey(objID, genNum);
                if (type == 1) {
                    this.xrefTrailerResolver.setXRef(objKey, offset);
                } else {
                    this.xrefTrailerResolver.setXRef(objKey, -offset);
                }
            }
        }
        close();
    }

    private long parseValue(byte[] data, int start, int length) {
        long value = 0;
        for (int i = 0; i < length; i++) {
            value += (data[i + start] & 255) << (((length - i) - 1) * 8);
        }
        return value;
    }

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    /* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdfparser/PDFXrefStreamParser$ObjectNumbers.class */
    private static class ObjectNumbers implements Iterator<Long> {
        private final long[] start;
        private final long[] end;
        private int currentRange;
        private long currentEnd;
        private long currentNumber;
        private long maxValue;

        /*  JADX ERROR: Failed to decode insn: 0x0025: MOVE_MULTI
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[8]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:52)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:137)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:50)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0055: MOVE_MULTI
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[8]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:52)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:137)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:50)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:466)
            	at jadx.core.ProcessClass.process(ProcessClass.java:69)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:109)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
            */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public java.lang.Long next() {
            /*
                r8 = this;
                r0 = r8
                long r0 = r0.currentNumber
                r1 = r8
                long r1 = r1.maxValue
                int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                if (r0 < 0) goto L14
                java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
                r1 = r0
                r1.<init>()
                throw r0
                r0 = r8
                long r0 = r0.currentNumber
                r1 = r8
                long r1 = r1.currentEnd
                int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                if (r0 >= 0) goto L2f
                r0 = r8
                r1 = r0
                long r1 = r1.currentNumber
                // decode failed: arraycopy: source index -1 out of bounds for object array[8]
                r2 = 1
                long r1 = r1 + r2
                r0.currentNumber = r1
                java.lang.Long.valueOf(r-1)
                return r-1
                r0 = r8
                r1 = r8
                long[] r1 = r1.start
                r2 = r8
                r3 = r2
                int r3 = r3.currentRange
                r4 = 1
                int r3 = r3 + r4
                r4 = r3; r3 = r2; r2 = r4; 
                r3.currentRange = r4
                r1 = r1[r2]
                r0.currentNumber = r1
                r0 = r8
                r1 = r8
                long[] r1 = r1.end
                r2 = r8
                int r2 = r2.currentRange
                r1 = r1[r2]
                r0.currentEnd = r1
                r0 = r8
                r1 = r0
                long r1 = r1.currentNumber
                // decode failed: arraycopy: source index -1 out of bounds for object array[8]
                r2 = 1
                long r1 = r1 + r2
                r0.currentNumber = r1
                java.lang.Long.valueOf(r-1)
                return r-1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.pdfbox.pdfparser.PDFXrefStreamParser.ObjectNumbers.next():java.lang.Long");
        }

        private ObjectNumbers(COSArray indexArray) throws IOException {
            this.currentRange = 0;
            this.currentEnd = 0L;
            this.currentNumber = 0L;
            this.maxValue = 0L;
            this.start = new long[indexArray.size() / 2];
            this.end = new long[this.start.length];
            int counter = 0;
            Iterator<COSBase> indexIter = indexArray.iterator();
            while (indexIter.hasNext()) {
                COSBase base = indexIter.next();
                if (!(base instanceof COSInteger)) {
                    throw new IOException("Xref stream must have integer in /Index array");
                }
                long startValue = ((COSInteger) base).longValue();
                if (!indexIter.hasNext()) {
                    break;
                }
                COSBase base2 = indexIter.next();
                if (!(base2 instanceof COSInteger)) {
                    throw new IOException("Xref stream must have integer in /Index array");
                }
                long sizeValue = ((COSInteger) base2).longValue();
                this.start[counter] = startValue;
                int i = counter;
                counter++;
                this.end[i] = startValue + sizeValue;
            }
            this.currentNumber = this.start[0];
            this.currentEnd = this.end[0];
            this.maxValue = this.end[counter - 1];
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.currentNumber < this.maxValue;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
