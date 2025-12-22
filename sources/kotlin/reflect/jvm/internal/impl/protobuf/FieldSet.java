package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.FieldDescriptorLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Internal;
import kotlin.reflect.jvm.internal.impl.protobuf.LazyField;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.WireFormat;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/FieldSet.class */
final class FieldSet<FieldDescriptorType extends FieldDescriptorLite<FieldDescriptorType>> {
    private boolean isImmutable;
    private static final FieldSet DEFAULT_INSTANCE = new FieldSet(true);
    private boolean hasLazyField = false;
    private final SmallSortedMap<FieldDescriptorType, Object> fields = SmallSortedMap.newFieldMap(16);

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/protobuf/FieldSet$FieldDescriptorLite.class */
    public interface FieldDescriptorLite<T extends FieldDescriptorLite<T>> extends Comparable<T> {
        int getNumber();

        WireFormat.FieldType getLiteType();

        WireFormat.JavaType getLiteJavaType();

        boolean isRepeated();

        boolean isPacked();

        MessageLite.Builder internalMergeFrom(MessageLite.Builder builder, MessageLite messageLite);
    }

    private FieldSet() {
    }

    private FieldSet(boolean dummy) {
        makeImmutable();
    }

    public static <T extends FieldDescriptorLite<T>> FieldSet<T> newFieldSet() {
        return new FieldSet<>();
    }

    public static <T extends FieldDescriptorLite<T>> FieldSet<T> emptySet() {
        return DEFAULT_INSTANCE;
    }

    public void makeImmutable() {
        if (this.isImmutable) {
            return;
        }
        this.fields.makeImmutable();
        this.isImmutable = true;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public FieldSet<FieldDescriptorType> m3775clone() {
        FieldSet<FieldDescriptorType> clone = newFieldSet();
        for (int i = 0; i < this.fields.getNumArrayEntries(); i++) {
            Map.Entry<K, Object> arrayEntryAt = this.fields.getArrayEntryAt(i);
            clone.setField((FieldDescriptorLite) arrayEntryAt.getKey(), arrayEntryAt.getValue());
        }
        Iterator i$ = this.fields.getOverflowEntries().iterator();
        while (i$.hasNext()) {
            Map.Entry<FieldDescriptorType, Object> entry = (Map.Entry) i$.next();
            FieldDescriptorType descriptor = entry.getKey();
            clone.setField(descriptor, entry.getValue());
        }
        clone.hasLazyField = this.hasLazyField;
        return clone;
    }

    public Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        if (this.hasLazyField) {
            return new LazyField.LazyIterator(this.fields.entrySet().iterator());
        }
        return this.fields.entrySet().iterator();
    }

    public boolean hasField(FieldDescriptorType descriptor) {
        if (descriptor.isRepeated()) {
            throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
        }
        return this.fields.get(descriptor) != null;
    }

    public Object getField(FieldDescriptorType descriptor) {
        Object o = this.fields.get(descriptor);
        if (o instanceof LazyField) {
            return ((LazyField) o).getValue();
        }
        return o;
    }

    public void setField(FieldDescriptorType descriptor, Object value) {
        if (descriptor.isRepeated()) {
            if (!(value instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            List newList = new ArrayList();
            newList.addAll((List) value);
            for (Object element : newList) {
                verifyType(descriptor.getLiteType(), element);
            }
            value = newList;
        } else {
            verifyType(descriptor.getLiteType(), value);
        }
        if (value instanceof LazyField) {
            this.hasLazyField = true;
        }
        this.fields.put((SmallSortedMap<FieldDescriptorType, Object>) descriptor, (FieldDescriptorType) value);
    }

    public int getRepeatedFieldCount(FieldDescriptorType descriptor) {
        if (!descriptor.isRepeated()) {
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
        }
        Object value = getField(descriptor);
        if (value == null) {
            return 0;
        }
        return ((List) value).size();
    }

    public Object getRepeatedField(FieldDescriptorType descriptor, int index) {
        if (!descriptor.isRepeated()) {
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
        }
        Object value = getField(descriptor);
        if (value == null) {
            throw new IndexOutOfBoundsException();
        }
        return ((List) value).get(index);
    }

    public void addRepeatedField(FieldDescriptorType descriptor, Object value) {
        List<Object> list;
        if (!descriptor.isRepeated()) {
            throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
        }
        verifyType(descriptor.getLiteType(), value);
        Object existingValue = getField(descriptor);
        if (existingValue == null) {
            list = new ArrayList<>();
            this.fields.put((SmallSortedMap<FieldDescriptorType, Object>) descriptor, (FieldDescriptorType) list);
        } else {
            list = (List) existingValue;
        }
        list.add(value);
    }

    private static void verifyType(WireFormat.FieldType type, Object value) {
        if (value == null) {
            throw new NullPointerException();
        }
        boolean isValid = false;
        switch (type.getJavaType()) {
            case INT:
                isValid = value instanceof Integer;
                break;
            case LONG:
                isValid = value instanceof Long;
                break;
            case FLOAT:
                isValid = value instanceof Float;
                break;
            case DOUBLE:
                isValid = value instanceof Double;
                break;
            case BOOLEAN:
                isValid = value instanceof Boolean;
                break;
            case STRING:
                isValid = value instanceof String;
                break;
            case BYTE_STRING:
                isValid = (value instanceof ByteString) || (value instanceof byte[]);
                break;
            case ENUM:
                isValid = (value instanceof Integer) || (value instanceof Internal.EnumLite);
                break;
            case MESSAGE:
                isValid = (value instanceof MessageLite) || (value instanceof LazyField);
                break;
        }
        if (!isValid) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public boolean isInitialized() {
        for (int i = 0; i < this.fields.getNumArrayEntries(); i++) {
            if (!isInitialized(this.fields.getArrayEntryAt(i))) {
                return false;
            }
        }
        Iterator i$ = this.fields.getOverflowEntries().iterator();
        while (i$.hasNext()) {
            Map.Entry<FieldDescriptorType, Object> entry = (Map.Entry) i$.next();
            if (!isInitialized(entry)) {
                return false;
            }
        }
        return true;
    }

    private boolean isInitialized(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType descriptor = entry.getKey();
        if (descriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE) {
            if (descriptor.isRepeated()) {
                for (MessageLite element : (List) entry.getValue()) {
                    if (!element.isInitialized()) {
                        return false;
                    }
                }
                return true;
            }
            Object value = entry.getValue();
            if (value instanceof MessageLite) {
                if (!((MessageLite) value).isInitialized()) {
                    return false;
                }
                return true;
            }
            if (value instanceof LazyField) {
                return true;
            }
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        return true;
    }

    static int getWireFormatForFieldType(WireFormat.FieldType type, boolean isPacked) {
        if (isPacked) {
            return 2;
        }
        return type.getWireType();
    }

    public void mergeFrom(FieldSet<FieldDescriptorType> other) {
        for (int i = 0; i < other.fields.getNumArrayEntries(); i++) {
            mergeFromField(other.fields.getArrayEntryAt(i));
        }
        Iterator i$ = other.fields.getOverflowEntries().iterator();
        while (i$.hasNext()) {
            Map.Entry<FieldDescriptorType, Object> entry = (Map.Entry) i$.next();
            mergeFromField(entry);
        }
    }

    private Object cloneIfMutable(Object value) {
        if (value instanceof byte[]) {
            byte[] bytes = (byte[]) value;
            byte[] copy = new byte[bytes.length];
            System.arraycopy(bytes, 0, copy, 0, bytes.length);
            return copy;
        }
        return value;
    }

    private void mergeFromField(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType descriptor = entry.getKey();
        Object otherValue = entry.getValue();
        if (otherValue instanceof LazyField) {
            otherValue = ((LazyField) otherValue).getValue();
        }
        if (descriptor.isRepeated()) {
            Object value = getField(descriptor);
            if (value == null) {
                value = new ArrayList();
            }
            for (Object element : (List) otherValue) {
                ((List) value).add(cloneIfMutable(element));
            }
            this.fields.put((SmallSortedMap<FieldDescriptorType, Object>) descriptor, (FieldDescriptorType) value);
            return;
        }
        if (descriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE) {
            Object value2 = getField(descriptor);
            if (value2 == null) {
                this.fields.put((SmallSortedMap<FieldDescriptorType, Object>) descriptor, (FieldDescriptorType) cloneIfMutable(otherValue));
                return;
            } else {
                this.fields.put((SmallSortedMap<FieldDescriptorType, Object>) descriptor, (FieldDescriptorType) descriptor.internalMergeFrom(((MessageLite) value2).toBuilder(), (MessageLite) otherValue).build());
                return;
            }
        }
        this.fields.put((SmallSortedMap<FieldDescriptorType, Object>) descriptor, (FieldDescriptorType) cloneIfMutable(otherValue));
    }

    public static Object readPrimitiveField(CodedInputStream input, WireFormat.FieldType type, boolean checkUtf8) throws IOException {
        switch (type) {
            case DOUBLE:
                return Double.valueOf(input.readDouble());
            case FLOAT:
                return Float.valueOf(input.readFloat());
            case INT64:
                return Long.valueOf(input.readInt64());
            case UINT64:
                return Long.valueOf(input.readUInt64());
            case INT32:
                return Integer.valueOf(input.readInt32());
            case FIXED64:
                return Long.valueOf(input.readFixed64());
            case FIXED32:
                return Integer.valueOf(input.readFixed32());
            case BOOL:
                return Boolean.valueOf(input.readBool());
            case STRING:
                if (checkUtf8) {
                    return input.readStringRequireUtf8();
                }
                return input.readString();
            case BYTES:
                return input.readBytes();
            case UINT32:
                return Integer.valueOf(input.readUInt32());
            case SFIXED32:
                return Integer.valueOf(input.readSFixed32());
            case SFIXED64:
                return Long.valueOf(input.readSFixed64());
            case SINT32:
                return Integer.valueOf(input.readSInt32());
            case SINT64:
                return Long.valueOf(input.readSInt64());
            case GROUP:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
            case MESSAGE:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
            case ENUM:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static void writeElement(CodedOutputStream output, WireFormat.FieldType type, int number, Object value) throws IOException {
        if (type == WireFormat.FieldType.GROUP) {
            output.writeGroup(number, (MessageLite) value);
        } else {
            output.writeTag(number, getWireFormatForFieldType(type, false));
            writeElementNoTag(output, type, value);
        }
    }

    private static void writeElementNoTag(CodedOutputStream output, WireFormat.FieldType type, Object value) throws IOException {
        switch (type) {
            case DOUBLE:
                output.writeDoubleNoTag(((Double) value).doubleValue());
                break;
            case FLOAT:
                output.writeFloatNoTag(((Float) value).floatValue());
                break;
            case INT64:
                output.writeInt64NoTag(((Long) value).longValue());
                break;
            case UINT64:
                output.writeUInt64NoTag(((Long) value).longValue());
                break;
            case INT32:
                output.writeInt32NoTag(((Integer) value).intValue());
                break;
            case FIXED64:
                output.writeFixed64NoTag(((Long) value).longValue());
                break;
            case FIXED32:
                output.writeFixed32NoTag(((Integer) value).intValue());
                break;
            case BOOL:
                output.writeBoolNoTag(((Boolean) value).booleanValue());
                break;
            case STRING:
                output.writeStringNoTag((String) value);
                break;
            case BYTES:
                if (value instanceof ByteString) {
                    output.writeBytesNoTag((ByteString) value);
                    break;
                } else {
                    output.writeByteArrayNoTag((byte[]) value);
                    break;
                }
            case UINT32:
                output.writeUInt32NoTag(((Integer) value).intValue());
                break;
            case SFIXED32:
                output.writeSFixed32NoTag(((Integer) value).intValue());
                break;
            case SFIXED64:
                output.writeSFixed64NoTag(((Long) value).longValue());
                break;
            case SINT32:
                output.writeSInt32NoTag(((Integer) value).intValue());
                break;
            case SINT64:
                output.writeSInt64NoTag(((Long) value).longValue());
                break;
            case GROUP:
                output.writeGroupNoTag((MessageLite) value);
                break;
            case MESSAGE:
                output.writeMessageNoTag((MessageLite) value);
                break;
            case ENUM:
                if (value instanceof Internal.EnumLite) {
                    output.writeEnumNoTag(((Internal.EnumLite) value).getNumber());
                    break;
                } else {
                    output.writeEnumNoTag(((Integer) value).intValue());
                    break;
                }
        }
    }

    public static void writeField(FieldDescriptorLite<?> descriptor, Object value, CodedOutputStream output) throws IOException {
        WireFormat.FieldType type = descriptor.getLiteType();
        int number = descriptor.getNumber();
        if (descriptor.isRepeated()) {
            List<?> valueList = (List) value;
            if (descriptor.isPacked()) {
                output.writeTag(number, 2);
                int dataSize = 0;
                for (Object element : valueList) {
                    dataSize += computeElementSizeNoTag(type, element);
                }
                output.writeRawVarint32(dataSize);
                for (Object element2 : valueList) {
                    writeElementNoTag(output, type, element2);
                }
                return;
            }
            for (Object element3 : valueList) {
                writeElement(output, type, number, element3);
            }
            return;
        }
        if (value instanceof LazyField) {
            writeElement(output, type, number, ((LazyField) value).getValue());
        } else {
            writeElement(output, type, number, value);
        }
    }

    public int getSerializedSize() {
        int size = 0;
        for (int i = 0; i < this.fields.getNumArrayEntries(); i++) {
            Map.Entry<K, Object> arrayEntryAt = this.fields.getArrayEntryAt(i);
            size += computeFieldSize((FieldDescriptorLite) arrayEntryAt.getKey(), arrayEntryAt.getValue());
        }
        Iterator i$ = this.fields.getOverflowEntries().iterator();
        while (i$.hasNext()) {
            Map.Entry<FieldDescriptorType, Object> entry = (Map.Entry) i$.next();
            size += computeFieldSize(entry.getKey(), entry.getValue());
        }
        return size;
    }

    private static int computeElementSize(WireFormat.FieldType type, int number, Object value) {
        int tagSize = CodedOutputStream.computeTagSize(number);
        if (type == WireFormat.FieldType.GROUP) {
            tagSize *= 2;
        }
        return tagSize + computeElementSizeNoTag(type, value);
    }

    private static int computeElementSizeNoTag(WireFormat.FieldType type, Object value) {
        switch (type) {
            case DOUBLE:
                return CodedOutputStream.computeDoubleSizeNoTag(((Double) value).doubleValue());
            case FLOAT:
                return CodedOutputStream.computeFloatSizeNoTag(((Float) value).floatValue());
            case INT64:
                return CodedOutputStream.computeInt64SizeNoTag(((Long) value).longValue());
            case UINT64:
                return CodedOutputStream.computeUInt64SizeNoTag(((Long) value).longValue());
            case INT32:
                return CodedOutputStream.computeInt32SizeNoTag(((Integer) value).intValue());
            case FIXED64:
                return CodedOutputStream.computeFixed64SizeNoTag(((Long) value).longValue());
            case FIXED32:
                return CodedOutputStream.computeFixed32SizeNoTag(((Integer) value).intValue());
            case BOOL:
                return CodedOutputStream.computeBoolSizeNoTag(((Boolean) value).booleanValue());
            case STRING:
                return CodedOutputStream.computeStringSizeNoTag((String) value);
            case BYTES:
                if (value instanceof ByteString) {
                    return CodedOutputStream.computeBytesSizeNoTag((ByteString) value);
                }
                return CodedOutputStream.computeByteArraySizeNoTag((byte[]) value);
            case UINT32:
                return CodedOutputStream.computeUInt32SizeNoTag(((Integer) value).intValue());
            case SFIXED32:
                return CodedOutputStream.computeSFixed32SizeNoTag(((Integer) value).intValue());
            case SFIXED64:
                return CodedOutputStream.computeSFixed64SizeNoTag(((Long) value).longValue());
            case SINT32:
                return CodedOutputStream.computeSInt32SizeNoTag(((Integer) value).intValue());
            case SINT64:
                return CodedOutputStream.computeSInt64SizeNoTag(((Long) value).longValue());
            case GROUP:
                return CodedOutputStream.computeGroupSizeNoTag((MessageLite) value);
            case MESSAGE:
                if (value instanceof LazyField) {
                    return CodedOutputStream.computeLazyFieldSizeNoTag((LazyField) value);
                }
                return CodedOutputStream.computeMessageSizeNoTag((MessageLite) value);
            case ENUM:
                if (value instanceof Internal.EnumLite) {
                    return CodedOutputStream.computeEnumSizeNoTag(((Internal.EnumLite) value).getNumber());
                }
                return CodedOutputStream.computeEnumSizeNoTag(((Integer) value).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int computeFieldSize(FieldDescriptorLite<?> descriptor, Object value) {
        WireFormat.FieldType type = descriptor.getLiteType();
        int number = descriptor.getNumber();
        if (descriptor.isRepeated()) {
            if (descriptor.isPacked()) {
                int dataSize = 0;
                for (Object element : (List) value) {
                    dataSize += computeElementSizeNoTag(type, element);
                }
                return dataSize + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeRawVarint32Size(dataSize);
            }
            int size = 0;
            for (Object element2 : (List) value) {
                size += computeElementSize(type, number, element2);
            }
            return size;
        }
        return computeElementSize(type, number, value);
    }
}
