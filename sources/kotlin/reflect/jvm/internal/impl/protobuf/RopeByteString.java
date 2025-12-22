package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/RopeByteString.class */
class RopeByteString extends ByteString {
    private static final int[] minLengthByDepth;
    private final int totalLength;
    private final ByteString left;
    private final ByteString right;
    private final int leftLength;
    private final int treeDepth;
    private int hash;

    static {
        List<Integer> numbers = new ArrayList<>();
        int f1 = 1;
        int i = 1;
        while (true) {
            int f2 = i;
            if (f2 <= 0) {
                break;
            }
            numbers.add(Integer.valueOf(f2));
            int temp = f1 + f2;
            f1 = f2;
            i = temp;
        }
        numbers.add(Integer.MAX_VALUE);
        minLengthByDepth = new int[numbers.size()];
        for (int i2 = 0; i2 < minLengthByDepth.length; i2++) {
            minLengthByDepth[i2] = numbers.get(i2).intValue();
        }
    }

    private RopeByteString(ByteString left, ByteString right) {
        this.hash = 0;
        this.left = left;
        this.right = right;
        this.leftLength = left.size();
        this.totalLength = this.leftLength + right.size();
        this.treeDepth = Math.max(left.getTreeDepth(), right.getTreeDepth()) + 1;
    }

    static ByteString concatenate(ByteString left, ByteString right) {
        ByteString result;
        RopeByteString leftRope = left instanceof RopeByteString ? (RopeByteString) left : null;
        if (right.size() == 0) {
            result = left;
        } else if (left.size() == 0) {
            result = right;
        } else {
            int newLength = left.size() + right.size();
            if (newLength < 128) {
                result = concatenateBytes(left, right);
            } else if (leftRope != null && leftRope.right.size() + right.size() < 128) {
                ByteString newRight = concatenateBytes(leftRope.right, right);
                result = new RopeByteString(leftRope.left, newRight);
            } else if (leftRope != null && leftRope.left.getTreeDepth() > leftRope.right.getTreeDepth() && leftRope.getTreeDepth() > right.getTreeDepth()) {
                ByteString newRight2 = new RopeByteString(leftRope.right, right);
                result = new RopeByteString(leftRope.left, newRight2);
            } else {
                int newDepth = Math.max(left.getTreeDepth(), right.getTreeDepth()) + 1;
                if (newLength >= minLengthByDepth[newDepth]) {
                    result = new RopeByteString(left, right);
                } else {
                    result = new Balancer().balance(left, right);
                }
            }
        }
        return result;
    }

    private static LiteralByteString concatenateBytes(ByteString left, ByteString right) {
        int leftSize = left.size();
        int rightSize = right.size();
        byte[] bytes = new byte[leftSize + rightSize];
        left.copyTo(bytes, 0, 0, leftSize);
        right.copyTo(bytes, 0, leftSize, rightSize);
        return new LiteralByteString(bytes);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public int size() {
        return this.totalLength;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected int getTreeDepth() {
        return this.treeDepth;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected boolean isBalanced() {
        return this.totalLength >= minLengthByDepth[this.treeDepth];
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected void copyToInternal(byte[] target, int sourceOffset, int targetOffset, int numberToCopy) {
        if (sourceOffset + numberToCopy <= this.leftLength) {
            this.left.copyToInternal(target, sourceOffset, targetOffset, numberToCopy);
        } else {
            if (sourceOffset >= this.leftLength) {
                this.right.copyToInternal(target, sourceOffset - this.leftLength, targetOffset, numberToCopy);
                return;
            }
            int leftLength = this.leftLength - sourceOffset;
            this.left.copyToInternal(target, sourceOffset, targetOffset, leftLength);
            this.right.copyToInternal(target, 0, targetOffset + leftLength, numberToCopy - leftLength);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    void writeToInternal(OutputStream out, int sourceOffset, int numberToWrite) throws IOException {
        if (sourceOffset + numberToWrite <= this.leftLength) {
            this.left.writeToInternal(out, sourceOffset, numberToWrite);
        } else {
            if (sourceOffset >= this.leftLength) {
                this.right.writeToInternal(out, sourceOffset - this.leftLength, numberToWrite);
                return;
            }
            int numberToWriteInLeft = this.leftLength - sourceOffset;
            this.left.writeToInternal(out, sourceOffset, numberToWriteInLeft);
            this.right.writeToInternal(out, 0, numberToWrite - numberToWriteInLeft);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public String toString(String charsetName) throws UnsupportedEncodingException {
        return new String(toByteArray(), charsetName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public boolean isValidUtf8() {
        int leftPartial = this.left.partialIsValidUtf8(0, 0, this.leftLength);
        int state = this.right.partialIsValidUtf8(leftPartial, 0, this.right.size());
        return state == 0;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected int partialIsValidUtf8(int state, int offset, int length) {
        int toIndex = offset + length;
        if (toIndex <= this.leftLength) {
            return this.left.partialIsValidUtf8(state, offset, length);
        }
        if (offset >= this.leftLength) {
            return this.right.partialIsValidUtf8(state, offset - this.leftLength, length);
        }
        int leftLength = this.leftLength - offset;
        int leftPartial = this.left.partialIsValidUtf8(state, offset, leftLength);
        return this.right.partialIsValidUtf8(leftPartial, 0, length - leftLength);
    }

    public boolean equals(Object other) {
        int cachedOtherHash;
        if (other == this) {
            return true;
        }
        if (!(other instanceof ByteString)) {
            return false;
        }
        ByteString otherByteString = (ByteString) other;
        if (this.totalLength != otherByteString.size()) {
            return false;
        }
        if (this.totalLength == 0) {
            return true;
        }
        if (this.hash != 0 && (cachedOtherHash = otherByteString.peekCachedHashCode()) != 0 && this.hash != cachedOtherHash) {
            return false;
        }
        return equalsFragments(otherByteString);
    }

    private boolean equalsFragments(ByteString other) {
        int thisOffset = 0;
        Iterator<LiteralByteString> thisIter = new PieceIterator(this);
        LiteralByteString thisString = thisIter.next();
        int thatOffset = 0;
        Iterator<LiteralByteString> thatIter = new PieceIterator(other);
        LiteralByteString thatString = thatIter.next();
        int pos = 0;
        while (true) {
            int thisRemaining = thisString.size() - thisOffset;
            int thatRemaining = thatString.size() - thatOffset;
            int bytesToCompare = Math.min(thisRemaining, thatRemaining);
            boolean stillEqual = thisOffset == 0 ? thisString.equalsRange(thatString, thatOffset, bytesToCompare) : thatString.equalsRange(thisString, thisOffset, bytesToCompare);
            if (!stillEqual) {
                return false;
            }
            pos += bytesToCompare;
            if (pos >= this.totalLength) {
                if (pos == this.totalLength) {
                    return true;
                }
                throw new IllegalStateException();
            }
            if (bytesToCompare == thisRemaining) {
                thisOffset = 0;
                thisString = thisIter.next();
            } else {
                thisOffset += bytesToCompare;
            }
            if (bytesToCompare == thatRemaining) {
                thatOffset = 0;
                thatString = thatIter.next();
            } else {
                thatOffset += bytesToCompare;
            }
        }
    }

    public int hashCode() {
        int h = this.hash;
        if (h == 0) {
            h = partialHash(this.totalLength, 0, this.totalLength);
            if (h == 0) {
                h = 1;
            }
            this.hash = h;
        }
        return h;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected int peekCachedHashCode() {
        return this.hash;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected int partialHash(int h, int offset, int length) {
        int toIndex = offset + length;
        if (toIndex <= this.leftLength) {
            return this.left.partialHash(h, offset, length);
        }
        if (offset >= this.leftLength) {
            return this.right.partialHash(h, offset - this.leftLength, length);
        }
        int leftLength = this.leftLength - offset;
        int leftPartial = this.left.partialHash(h, offset, leftLength);
        return this.right.partialHash(leftPartial, 0, length - leftLength);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public CodedInputStream newCodedInput() {
        return CodedInputStream.newInstance(new RopeInputStream());
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/RopeByteString$Balancer.class */
    private static class Balancer {
        private final Stack<ByteString> prefixesStack;

        private Balancer() {
            this.prefixesStack = new Stack<>();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ByteString balance(ByteString left, ByteString right) {
            doBalance(left);
            doBalance(right);
            ByteString byteStringPop = this.prefixesStack.pop();
            while (true) {
                ByteString partialString = byteStringPop;
                if (!this.prefixesStack.isEmpty()) {
                    ByteString newLeft = this.prefixesStack.pop();
                    byteStringPop = new RopeByteString(newLeft, partialString);
                } else {
                    return partialString;
                }
            }
        }

        private void doBalance(ByteString root) {
            if (root.isBalanced()) {
                insert(root);
            } else {
                if (root instanceof RopeByteString) {
                    RopeByteString rbs = (RopeByteString) root;
                    doBalance(rbs.left);
                    doBalance(rbs.right);
                    return;
                }
                String strValueOf = String.valueOf(String.valueOf(root.getClass()));
                throw new IllegalArgumentException(new StringBuilder(49 + strValueOf.length()).append("Has a new type of ByteString been created? Found ").append(strValueOf).toString());
            }
        }

        private void insert(ByteString byteString) {
            ByteString newTree;
            ByteString newTree2;
            int depthBin = getDepthBinForLength(byteString.size());
            int binEnd = RopeByteString.minLengthByDepth[depthBin + 1];
            if (!this.prefixesStack.isEmpty() && this.prefixesStack.peek().size() < binEnd) {
                int binStart = RopeByteString.minLengthByDepth[depthBin];
                ByteString byteStringPop = this.prefixesStack.pop();
                while (true) {
                    newTree = byteStringPop;
                    if (this.prefixesStack.isEmpty() || this.prefixesStack.peek().size() >= binStart) {
                        break;
                    }
                    ByteString left = this.prefixesStack.pop();
                    byteStringPop = new RopeByteString(left, newTree);
                }
                ByteString ropeByteString = new RopeByteString(newTree, byteString);
                while (true) {
                    newTree2 = ropeByteString;
                    if (!this.prefixesStack.isEmpty()) {
                        int binEnd2 = RopeByteString.minLengthByDepth[getDepthBinForLength(newTree2.size()) + 1];
                        if (this.prefixesStack.peek().size() >= binEnd2) {
                            break;
                        }
                        ByteString left2 = this.prefixesStack.pop();
                        ropeByteString = new RopeByteString(left2, newTree2);
                    } else {
                        break;
                    }
                }
                this.prefixesStack.push(newTree2);
                return;
            }
            this.prefixesStack.push(byteString);
        }

        private int getDepthBinForLength(int length) {
            int depth = Arrays.binarySearch(RopeByteString.minLengthByDepth, length);
            if (depth < 0) {
                int insertionPoint = -(depth + 1);
                depth = insertionPoint - 1;
            }
            return depth;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/RopeByteString$PieceIterator.class */
    private static class PieceIterator implements Iterator<LiteralByteString> {
        private final Stack<RopeByteString> breadCrumbs;
        private LiteralByteString next;

        private PieceIterator(ByteString root) {
            this.breadCrumbs = new Stack<>();
            this.next = getLeafByLeft(root);
        }

        private LiteralByteString getLeafByLeft(ByteString root) {
            ByteString byteString = root;
            while (true) {
                ByteString pos = byteString;
                if (pos instanceof RopeByteString) {
                    RopeByteString rbs = (RopeByteString) pos;
                    this.breadCrumbs.push(rbs);
                    byteString = rbs.left;
                } else {
                    return (LiteralByteString) pos;
                }
            }
        }

        private LiteralByteString getNextNonEmptyLeaf() {
            while (!this.breadCrumbs.isEmpty()) {
                LiteralByteString result = getLeafByLeft(this.breadCrumbs.pop().right);
                if (!result.isEmpty()) {
                    return result;
                }
            }
            return null;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.next != null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public LiteralByteString next() {
            if (this.next == null) {
                throw new NoSuchElementException();
            }
            LiteralByteString result = this.next;
            this.next = getNextNonEmptyLeaf();
            return result;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString, java.lang.Iterable
    /* renamed from: iterator, reason: merged with bridge method [inline-methods] */
    public Iterator<Byte> iterator2() {
        return new RopeByteIterator();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/RopeByteString$RopeByteIterator.class */
    private class RopeByteIterator implements ByteString.ByteIterator {
        private final PieceIterator pieces;
        private ByteString.ByteIterator bytes;
        int bytesRemaining;

        /* JADX WARN: Type inference failed for: r1v5, types: [kotlin.reflect.jvm.internal.impl.protobuf.ByteString$ByteIterator] */
        private RopeByteIterator() {
            this.pieces = new PieceIterator(RopeByteString.this);
            this.bytes = this.pieces.next().iterator2();
            this.bytesRemaining = RopeByteString.this.size();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.bytesRemaining > 0;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Byte next() {
            return Byte.valueOf(nextByte());
        }

        /* JADX WARN: Type inference failed for: r1v6, types: [kotlin.reflect.jvm.internal.impl.protobuf.ByteString$ByteIterator] */
        @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString.ByteIterator
        public byte nextByte() {
            if (!this.bytes.hasNext()) {
                this.bytes = this.pieces.next().iterator2();
            }
            this.bytesRemaining--;
            return this.bytes.nextByte();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/RopeByteString$RopeInputStream.class */
    private class RopeInputStream extends InputStream {
        private PieceIterator pieceIterator;
        private LiteralByteString currentPiece;
        private int currentPieceSize;
        private int currentPieceIndex;
        private int currentPieceOffsetInRope;
        private int mark;

        public RopeInputStream() {
            initialize();
        }

        @Override // java.io.InputStream
        public int read(byte[] b, int offset, int length) {
            if (b == null) {
                throw new NullPointerException();
            }
            if (offset < 0 || length < 0 || length > b.length - offset) {
                throw new IndexOutOfBoundsException();
            }
            return readSkipInternal(b, offset, length);
        }

        @Override // java.io.InputStream
        public long skip(long length) {
            if (length < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (length > 2147483647L) {
                length = 2147483647L;
            }
            return readSkipInternal(null, 0, (int) length);
        }

        private int readSkipInternal(byte[] b, int offset, int length) {
            int bytesRemaining;
            int i = length;
            while (true) {
                bytesRemaining = i;
                if (bytesRemaining <= 0) {
                    break;
                }
                advanceIfCurrentPieceFullyRead();
                if (this.currentPiece == null) {
                    if (bytesRemaining == length) {
                        return -1;
                    }
                } else {
                    int currentPieceRemaining = this.currentPieceSize - this.currentPieceIndex;
                    int count = Math.min(currentPieceRemaining, bytesRemaining);
                    if (b != null) {
                        this.currentPiece.copyTo(b, this.currentPieceIndex, offset, count);
                        offset += count;
                    }
                    this.currentPieceIndex += count;
                    i = bytesRemaining - count;
                }
            }
            return length - bytesRemaining;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            advanceIfCurrentPieceFullyRead();
            if (this.currentPiece == null) {
                return -1;
            }
            LiteralByteString literalByteString = this.currentPiece;
            int i = this.currentPieceIndex;
            this.currentPieceIndex = i + 1;
            return literalByteString.byteAt(i) & 255;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            int bytesRead = this.currentPieceOffsetInRope + this.currentPieceIndex;
            return RopeByteString.this.size() - bytesRead;
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        @Override // java.io.InputStream
        public void mark(int readAheadLimit) {
            this.mark = this.currentPieceOffsetInRope + this.currentPieceIndex;
        }

        @Override // java.io.InputStream
        public synchronized void reset() {
            initialize();
            readSkipInternal(null, 0, this.mark);
        }

        private void initialize() {
            this.pieceIterator = new PieceIterator(RopeByteString.this);
            this.currentPiece = this.pieceIterator.next();
            this.currentPieceSize = this.currentPiece.size();
            this.currentPieceIndex = 0;
            this.currentPieceOffsetInRope = 0;
        }

        private void advanceIfCurrentPieceFullyRead() {
            if (this.currentPiece != null && this.currentPieceIndex == this.currentPieceSize) {
                this.currentPieceOffsetInRope += this.currentPieceSize;
                this.currentPieceIndex = 0;
                if (this.pieceIterator.hasNext()) {
                    this.currentPiece = this.pieceIterator.next();
                    this.currentPieceSize = this.currentPiece.size();
                } else {
                    this.currentPiece = null;
                    this.currentPieceSize = 0;
                }
            }
        }
    }
}
