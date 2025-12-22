package kotlin.reflect.jvm.internal.impl.metadata;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractParser;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Internal;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.LazyStringArrayList;
import kotlin.reflect.jvm.internal.impl.protobuf.LazyStringList;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder;
import kotlin.reflect.jvm.internal.impl.protobuf.Parser;
import kotlin.reflect.jvm.internal.impl.protobuf.ProtocolStringList;
import org.apache.fontbox.ttf.WGL4Names;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException: Cannot invoke "java.util.List.forEach(java.util.function.Consumer)" because "blocks" is null
    	at jadx.core.utils.BlockUtils.collectAllInsns(BlockUtils.java:1029)
    	at jadx.core.dex.visitors.ClassModifier.removeBridgeMethod(ClassModifier.java:245)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticMethods(ClassModifier.java:160)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:65)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:58)
    */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf.class */
public final class ProtoBuf {

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$AnnotationOrBuilder.class */
    public interface AnnotationOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$ClassOrBuilder.class */
    public interface ClassOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$ConstructorOrBuilder.class */
    public interface ConstructorOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$ContractOrBuilder.class */
    public interface ContractOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$EffectOrBuilder.class */
    public interface EffectOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$EnumEntryOrBuilder.class */
    public interface EnumEntryOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$ExpressionOrBuilder.class */
    public interface ExpressionOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$FunctionOrBuilder.class */
    public interface FunctionOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$PackageFragmentOrBuilder.class */
    public interface PackageFragmentOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$PackageOrBuilder.class */
    public interface PackageOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$PropertyOrBuilder.class */
    public interface PropertyOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$QualifiedNameTableOrBuilder.class */
    public interface QualifiedNameTableOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$StringTableOrBuilder.class */
    public interface StringTableOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$TypeAliasOrBuilder.class */
    public interface TypeAliasOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$TypeOrBuilder.class */
    public interface TypeOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$TypeParameterOrBuilder.class */
    public interface TypeParameterOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$TypeTableOrBuilder.class */
    public interface TypeTableOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$ValueParameterOrBuilder.class */
    public interface ValueParameterOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$VersionRequirementOrBuilder.class */
    public interface VersionRequirementOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$VersionRequirementTableOrBuilder.class */
    public interface VersionRequirementTableOrBuilder extends MessageLiteOrBuilder {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Modality.class */
    public enum Modality implements Internal.EnumLite {
        FINAL(0, 0),
        OPEN(1, 1),
        ABSTRACT(2, 2),
        SEALED(3, 3);

        private static Internal.EnumLiteMap<Modality> internalValueMap = new Internal.EnumLiteMap<Modality>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
            public /* bridge */ /* synthetic */ Internal.EnumLite findValueByNumber(int x0) {
                return findValueByNumber(x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
            public Modality findValueByNumber(int number) {
                return Modality.valueOf(number);
            }
        };
        private final int value;

        static {
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static Modality valueOf(int value) {
            switch (value) {
                case 0:
                    return FINAL;
                case 1:
                    return OPEN;
                case 2:
                    return ABSTRACT;
                case 3:
                    return SEALED;
                default:
                    return null;
            }
        }

        Modality(int index, int value) {
            this.value = value;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Visibility.class */
    public enum Visibility implements Internal.EnumLite {
        INTERNAL(0, 0),
        PRIVATE(1, 1),
        PROTECTED(2, 2),
        PUBLIC(3, 3),
        PRIVATE_TO_THIS(4, 4),
        LOCAL(5, 5);

        private static Internal.EnumLiteMap<Visibility> internalValueMap = new Internal.EnumLiteMap<Visibility>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
            public /* bridge */ /* synthetic */ Internal.EnumLite findValueByNumber(int x0) {
                return findValueByNumber(x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
            public Visibility findValueByNumber(int number) {
                return Visibility.valueOf(number);
            }
        };
        private final int value;

        static {
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static Visibility valueOf(int value) {
            switch (value) {
                case 0:
                    return INTERNAL;
                case 1:
                    return PRIVATE;
                case 2:
                    return PROTECTED;
                case 3:
                    return PUBLIC;
                case 4:
                    return PRIVATE_TO_THIS;
                case 5:
                    return LOCAL;
                default:
                    return null;
            }
        }

        Visibility(int index, int value) {
            this.value = value;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$MemberKind.class */
    public enum MemberKind implements Internal.EnumLite {
        DECLARATION(0, 0),
        FAKE_OVERRIDE(1, 1),
        DELEGATION(2, 2),
        SYNTHESIZED(3, 3);

        private static Internal.EnumLiteMap<MemberKind> internalValueMap = new Internal.EnumLiteMap<MemberKind>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.MemberKind.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
            public /* bridge */ /* synthetic */ Internal.EnumLite findValueByNumber(int x0) {
                return findValueByNumber(x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
            public MemberKind findValueByNumber(int number) {
                return MemberKind.valueOf(number);
            }
        };
        private final int value;

        static {
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static MemberKind valueOf(int value) {
            switch (value) {
                case 0:
                    return DECLARATION;
                case 1:
                    return FAKE_OVERRIDE;
                case 2:
                    return DELEGATION;
                case 3:
                    return SYNTHESIZED;
                default:
                    return null;
            }
        }

        MemberKind(int index, int value) {
            this.value = value;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$StringTable.class */
    public static final class StringTable extends GeneratedMessageLite implements StringTableOrBuilder {
        private final ByteString unknownFields;
        private LazyStringList string_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<StringTable> PARSER = new AbstractParser<StringTable>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public StringTable parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new StringTable(input, extensionRegistry, null);
            }
        };
        private static final StringTable defaultInstance = new StringTable(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        /* synthetic */ StringTable(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ StringTable(GeneratedMessageLite.Builder x0, AnonymousClass1 x1) {
            this(x0);
        }

        private StringTable(GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private StringTable(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static StringTable getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public StringTable getDefaultInstanceForType() {
            return defaultInstance;
        }

        private StringTable(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                case 10:
                                    ByteString bs = input.readBytes();
                                    if ((mutable_bitField0_ & 1) != 1) {
                                        this.string_ = new LazyStringArrayList();
                                        mutable_bitField0_ |= 1;
                                    }
                                    this.string_.add(bs);
                                default:
                                    if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                        done = true;
                                    }
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        }
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 1) == 1) {
                    this.string_ = this.string_.getUnmodifiableView();
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 1) == 1) {
                    this.string_ = this.string_.getUnmodifiableView();
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<StringTable> getParserForType() {
            return PARSER;
        }

        public ProtocolStringList getStringList() {
            return this.string_;
        }

        public String getString(int index) {
            return (String) this.string_.get(index);
        }

        private void initFields() {
            this.string_ = LazyStringArrayList.EMPTY;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            for (int i = 0; i < this.string_.size(); i++) {
                output.writeBytes(1, this.string_.getByteString(i));
            }
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int dataSize = 0;
            for (int i = 0; i < this.string_.size(); i++) {
                dataSize += CodedOutputStream.computeBytesSizeNoTag(this.string_.getByteString(i));
            }
            int size2 = 0 + dataSize + (1 * getStringList().size()) + this.unknownFields.size();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(StringTable prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$StringTable$Builder.class */
        public static final class Builder extends GeneratedMessageLite.Builder<StringTable, Builder> implements StringTableOrBuilder {
            private int bitField0_;
            private LazyStringList string_ = LazyStringArrayList.EMPTY;

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((StringTable) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public StringTable getDefaultInstanceForType() {
                return StringTable.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public StringTable build() {
                StringTable result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public StringTable buildPartial() {
                StringTable result = new StringTable(this, (AnonymousClass1) null);
                int i = this.bitField0_;
                if ((this.bitField0_ & 1) == 1) {
                    this.string_ = this.string_.getUnmodifiableView();
                    this.bitField0_ &= -2;
                }
                result.string_ = this.string_;
                return result;
            }

            public Builder mergeFrom(StringTable other) {
                if (other == StringTable.getDefaultInstance()) {
                    return this;
                }
                if (!other.string_.isEmpty()) {
                    if (this.string_.isEmpty()) {
                        this.string_ = other.string_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureStringIsMutable();
                        this.string_.addAll(other.string_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                StringTable parsedMessage = null;
                try {
                    try {
                        parsedMessage = StringTable.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (StringTable) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            private void ensureStringIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.string_ = new LazyStringArrayList(this.string_);
                    this.bitField0_ |= 1;
                }
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$QualifiedNameTable.class */
    public static final class QualifiedNameTable extends GeneratedMessageLite implements QualifiedNameTableOrBuilder {
        private final ByteString unknownFields;
        private List<QualifiedName> qualifiedName_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<QualifiedNameTable> PARSER = new AbstractParser<QualifiedNameTable>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public QualifiedNameTable parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new QualifiedNameTable(input, extensionRegistry, null);
            }
        };
        private static final QualifiedNameTable defaultInstance = new QualifiedNameTable(true);

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$QualifiedNameTable$QualifiedNameOrBuilder.class */
        public interface QualifiedNameOrBuilder extends MessageLiteOrBuilder {
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        /* synthetic */ QualifiedNameTable(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ QualifiedNameTable(GeneratedMessageLite.Builder x0, AnonymousClass1 x1) {
            this(x0);
        }

        private QualifiedNameTable(GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private QualifiedNameTable(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static QualifiedNameTable getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public QualifiedNameTable getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private QualifiedNameTable(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                case 10:
                                    if ((mutable_bitField0_ & 1) != 1) {
                                        this.qualifiedName_ = new ArrayList();
                                        mutable_bitField0_ |= 1;
                                    }
                                    this.qualifiedName_.add(input.readMessage(QualifiedName.PARSER, extensionRegistry));
                                default:
                                    if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                        done = true;
                                    }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 1) == 1) {
                    this.qualifiedName_ = Collections.unmodifiableList(this.qualifiedName_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 1) == 1) {
                    this.qualifiedName_ = Collections.unmodifiableList(this.qualifiedName_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<QualifiedNameTable> getParserForType() {
            return PARSER;
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$QualifiedNameTable$QualifiedName.class */
        public static final class QualifiedName extends GeneratedMessageLite implements QualifiedNameOrBuilder {
            private final ByteString unknownFields;
            private int bitField0_;
            private int parentQualifiedName_;
            private int shortName_;
            private Kind kind_;
            private byte memoizedIsInitialized;
            private int memoizedSerializedSize;
            public static Parser<QualifiedName> PARSER = new AbstractParser<QualifiedName>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.1
                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                    return parsePartialFrom(x0, x1);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public QualifiedName parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new QualifiedName(input, extensionRegistry, null);
                }
            };
            private static final QualifiedName defaultInstance = new QualifiedName(true);

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
                return toBuilder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
                return newBuilderForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            /* synthetic */ QualifiedName(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
                this(x0, x1);
            }

            /* synthetic */ QualifiedName(GeneratedMessageLite.Builder x0, AnonymousClass1 x1) {
                this(x0);
            }

            private QualifiedName(GeneratedMessageLite.Builder builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = builder.getUnknownFields();
            }

            private QualifiedName(boolean noInit) {
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = ByteString.EMPTY;
            }

            public static QualifiedName getDefaultInstance() {
                return defaultInstance;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public QualifiedName getDefaultInstanceForType() {
                return defaultInstance;
            }

            private QualifiedName(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                initFields();
                ByteString.Output unknownFieldsOutput = ByteString.newOutput();
                CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
                try {
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                case 8:
                                    this.bitField0_ |= 1;
                                    this.parentQualifiedName_ = input.readInt32();
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.shortName_ = input.readInt32();
                                case 24:
                                    int rawValue = input.readEnum();
                                    Kind value = Kind.valueOf(rawValue);
                                    if (value == null) {
                                        unknownFieldsCodedOutput.writeRawVarint32(tag);
                                        unknownFieldsCodedOutput.writeRawVarint32(rawValue);
                                    } else {
                                        this.bitField0_ |= 4;
                                        this.kind_ = value;
                                    }
                                default:
                                    if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                        done = true;
                                    }
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                        }
                    }
                    try {
                        unknownFieldsCodedOutput.flush();
                        this.unknownFields = unknownFieldsOutput.toByteString();
                    } catch (IOException e3) {
                        this.unknownFields = unknownFieldsOutput.toByteString();
                    } catch (Throwable th) {
                        this.unknownFields = unknownFieldsOutput.toByteString();
                        throw th;
                    }
                    makeExtensionsImmutable();
                } catch (Throwable th2) {
                    try {
                        unknownFieldsCodedOutput.flush();
                        this.unknownFields = unknownFieldsOutput.toByteString();
                    } catch (IOException e4) {
                        this.unknownFields = unknownFieldsOutput.toByteString();
                    } catch (Throwable th3) {
                        this.unknownFields = unknownFieldsOutput.toByteString();
                        throw th3;
                    }
                    makeExtensionsImmutable();
                    throw th2;
                }
            }

            static {
                defaultInstance.initFields();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public Parser<QualifiedName> getParserForType() {
                return PARSER;
            }

            /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$QualifiedNameTable$QualifiedName$Kind.class */
            public enum Kind implements Internal.EnumLite {
                CLASS(0, 0),
                PACKAGE(1, 1),
                LOCAL(2, 2);

                private static Internal.EnumLiteMap<Kind> internalValueMap = new Internal.EnumLiteMap<Kind>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind.1
                    @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                    public /* bridge */ /* synthetic */ Internal.EnumLite findValueByNumber(int x0) {
                        return findValueByNumber(x0);
                    }

                    @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                    public Kind findValueByNumber(int number) {
                        return Kind.valueOf(number);
                    }
                };
                private final int value;

                static {
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite
                public final int getNumber() {
                    return this.value;
                }

                public static Kind valueOf(int value) {
                    switch (value) {
                        case 0:
                            return CLASS;
                        case 1:
                            return PACKAGE;
                        case 2:
                            return LOCAL;
                        default:
                            return null;
                    }
                }

                Kind(int index, int value) {
                    this.value = value;
                }
            }

            public boolean hasParentQualifiedName() {
                return (this.bitField0_ & 1) == 1;
            }

            public int getParentQualifiedName() {
                return this.parentQualifiedName_;
            }

            public boolean hasShortName() {
                return (this.bitField0_ & 2) == 2;
            }

            public int getShortName() {
                return this.shortName_;
            }

            public boolean hasKind() {
                return (this.bitField0_ & 4) == 4;
            }

            public Kind getKind() {
                return this.kind_;
            }

            private void initFields() {
                this.parentQualifiedName_ = -1;
                this.shortName_ = 0;
                this.kind_ = Kind.PACKAGE;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte isInitialized = this.memoizedIsInitialized;
                if (isInitialized == 1) {
                    return true;
                }
                if (isInitialized == 0) {
                    return false;
                }
                if (!hasShortName()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                getSerializedSize();
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt32(1, this.parentQualifiedName_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeInt32(2, this.shortName_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeEnum(3, this.kind_.getNumber());
                }
                output.writeRawBytes(this.unknownFields);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public int getSerializedSize() {
                int size = this.memoizedSerializedSize;
                if (size != -1) {
                    return size;
                }
                int size2 = 0;
                if ((this.bitField0_ & 1) == 1) {
                    size2 = 0 + CodedOutputStream.computeInt32Size(1, this.parentQualifiedName_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeInt32Size(2, this.shortName_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeEnumSize(3, this.kind_.getNumber());
                }
                int size3 = size2 + this.unknownFields.size();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder(QualifiedName prototype) {
                return newBuilder().mergeFrom(prototype);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$QualifiedNameTable$QualifiedName$Builder.class */
            public static final class Builder extends GeneratedMessageLite.Builder<QualifiedName, Builder> implements QualifiedNameOrBuilder {
                private int bitField0_;
                private int shortName_;
                private int parentQualifiedName_ = -1;
                private Kind kind_ = Kind.PACKAGE;

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                    return getDefaultInstanceForType();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
                public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                    return mergeFrom((QualifiedName) x0);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                    return mo3702clone();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
                public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                    return mergeFrom(x0, x1);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                    return mo3702clone();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
                public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                    return mergeFrom(x0, x1);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
                public /* bridge */ /* synthetic */ MessageLite build() {
                    return build();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                    return getDefaultInstanceForType();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                    return mo3702clone();
                }

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static Builder create() {
                    return new Builder();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public Builder mo3702clone() {
                    return create().mergeFrom(buildPartial());
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public QualifiedName getDefaultInstanceForType() {
                    return QualifiedName.getDefaultInstance();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
                public QualifiedName build() {
                    QualifiedName result = buildPartial();
                    if (!result.isInitialized()) {
                        throw newUninitializedMessageException(result);
                    }
                    return result;
                }

                public QualifiedName buildPartial() {
                    QualifiedName result = new QualifiedName(this, (AnonymousClass1) null);
                    int from_bitField0_ = this.bitField0_;
                    int to_bitField0_ = 0;
                    if ((from_bitField0_ & 1) == 1) {
                        to_bitField0_ = 0 | 1;
                    }
                    result.parentQualifiedName_ = this.parentQualifiedName_;
                    if ((from_bitField0_ & 2) == 2) {
                        to_bitField0_ |= 2;
                    }
                    result.shortName_ = this.shortName_;
                    if ((from_bitField0_ & 4) == 4) {
                        to_bitField0_ |= 4;
                    }
                    result.kind_ = this.kind_;
                    result.bitField0_ = to_bitField0_;
                    return result;
                }

                public Builder mergeFrom(QualifiedName other) {
                    if (other == QualifiedName.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasParentQualifiedName()) {
                        setParentQualifiedName(other.getParentQualifiedName());
                    }
                    if (other.hasShortName()) {
                        setShortName(other.getShortName());
                    }
                    if (other.hasKind()) {
                        setKind(other.getKind());
                    }
                    setUnknownFields(getUnknownFields().concat(other.unknownFields));
                    return this;
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    if (!hasShortName()) {
                        return false;
                    }
                    return true;
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    QualifiedName parsedMessage = null;
                    try {
                        try {
                            parsedMessage = QualifiedName.PARSER.parsePartialFrom(input, extensionRegistry);
                            if (parsedMessage != null) {
                                mergeFrom(parsedMessage);
                            }
                            return this;
                        } catch (InvalidProtocolBufferException e) {
                            parsedMessage = (QualifiedName) e.getUnfinishedMessage();
                            throw e;
                        }
                    } catch (Throwable th) {
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        throw th;
                    }
                }

                public Builder setParentQualifiedName(int value) {
                    this.bitField0_ |= 1;
                    this.parentQualifiedName_ = value;
                    return this;
                }

                public boolean hasShortName() {
                    return (this.bitField0_ & 2) == 2;
                }

                public Builder setShortName(int value) {
                    this.bitField0_ |= 2;
                    this.shortName_ = value;
                    return this;
                }

                public Builder setKind(Kind value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 4;
                    this.kind_ = value;
                    return this;
                }
            }
        }

        public int getQualifiedNameCount() {
            return this.qualifiedName_.size();
        }

        public QualifiedName getQualifiedName(int index) {
            return this.qualifiedName_.get(index);
        }

        private void initFields() {
            this.qualifiedName_ = Collections.emptyList();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < getQualifiedNameCount(); i++) {
                if (!getQualifiedName(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            for (int i = 0; i < this.qualifiedName_.size(); i++) {
                output.writeMessage(1, this.qualifiedName_.get(i));
            }
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            for (int i = 0; i < this.qualifiedName_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.qualifiedName_.get(i));
            }
            int size3 = size2 + this.unknownFields.size();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(QualifiedNameTable prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$QualifiedNameTable$Builder.class */
        public static final class Builder extends GeneratedMessageLite.Builder<QualifiedNameTable, Builder> implements QualifiedNameTableOrBuilder {
            private int bitField0_;
            private List<QualifiedName> qualifiedName_ = Collections.emptyList();

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((QualifiedNameTable) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public QualifiedNameTable getDefaultInstanceForType() {
                return QualifiedNameTable.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public QualifiedNameTable build() {
                QualifiedNameTable result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public QualifiedNameTable buildPartial() {
                QualifiedNameTable result = new QualifiedNameTable(this, (AnonymousClass1) null);
                int i = this.bitField0_;
                if ((this.bitField0_ & 1) == 1) {
                    this.qualifiedName_ = Collections.unmodifiableList(this.qualifiedName_);
                    this.bitField0_ &= -2;
                }
                result.qualifiedName_ = this.qualifiedName_;
                return result;
            }

            public Builder mergeFrom(QualifiedNameTable other) {
                if (other == QualifiedNameTable.getDefaultInstance()) {
                    return this;
                }
                if (!other.qualifiedName_.isEmpty()) {
                    if (this.qualifiedName_.isEmpty()) {
                        this.qualifiedName_ = other.qualifiedName_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureQualifiedNameIsMutable();
                        this.qualifiedName_.addAll(other.qualifiedName_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getQualifiedNameCount(); i++) {
                    if (!getQualifiedName(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                QualifiedNameTable parsedMessage = null;
                try {
                    try {
                        parsedMessage = QualifiedNameTable.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (QualifiedNameTable) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            private void ensureQualifiedNameIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.qualifiedName_ = new ArrayList(this.qualifiedName_);
                    this.bitField0_ |= 1;
                }
            }

            public int getQualifiedNameCount() {
                return this.qualifiedName_.size();
            }

            public QualifiedName getQualifiedName(int index) {
                return this.qualifiedName_.get(index);
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Annotation.class */
    public static final class Annotation extends GeneratedMessageLite implements AnnotationOrBuilder {
        private final ByteString unknownFields;
        private int bitField0_;
        private int id_;
        private List<Argument> argument_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<Annotation> PARSER = new AbstractParser<Annotation>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public Annotation parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Annotation(input, extensionRegistry, null);
            }
        };
        private static final Annotation defaultInstance = new Annotation(true);

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Annotation$ArgumentOrBuilder.class */
        public interface ArgumentOrBuilder extends MessageLiteOrBuilder {
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        /* synthetic */ Annotation(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ Annotation(GeneratedMessageLite.Builder x0, AnonymousClass1 x1) {
            this(x0);
        }

        private Annotation(GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Annotation(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Annotation getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public Annotation getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Annotation(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 8:
                                this.bitField0_ |= 1;
                                this.id_ = input.readInt32();
                            case 18:
                                if ((mutable_bitField0_ & 2) != 2) {
                                    this.argument_ = new ArrayList();
                                    mutable_bitField0_ |= 2;
                                }
                                this.argument_.add(input.readMessage(Argument.PARSER, extensionRegistry));
                            default:
                                if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 2) == 2) {
                    this.argument_ = Collections.unmodifiableList(this.argument_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 2) == 2) {
                    this.argument_ = Collections.unmodifiableList(this.argument_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<Annotation> getParserForType() {
            return PARSER;
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Annotation$Argument.class */
        public static final class Argument extends GeneratedMessageLite implements ArgumentOrBuilder {
            private final ByteString unknownFields;
            private int bitField0_;
            private int nameId_;
            private Value value_;
            private byte memoizedIsInitialized;
            private int memoizedSerializedSize;
            public static Parser<Argument> PARSER = new AbstractParser<Argument>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.1
                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Argument parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Argument(input, extensionRegistry, null);
                }
            };
            private static final Argument defaultInstance = new Argument(true);

            /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Annotation$Argument$ValueOrBuilder.class */
            public interface ValueOrBuilder extends MessageLiteOrBuilder {
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
                return toBuilder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
                return newBuilderForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            /* synthetic */ Argument(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
                this(x0, x1);
            }

            /* synthetic */ Argument(GeneratedMessageLite.Builder x0, AnonymousClass1 x1) {
                this(x0);
            }

            private Argument(GeneratedMessageLite.Builder builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = builder.getUnknownFields();
            }

            private Argument(boolean noInit) {
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = ByteString.EMPTY;
            }

            public static Argument getDefaultInstance() {
                return defaultInstance;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public Argument getDefaultInstanceForType() {
                return defaultInstance;
            }

            private Argument(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                initFields();
                ByteString.Output unknownFieldsOutput = ByteString.newOutput();
                CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
                try {
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                case 8:
                                    this.bitField0_ |= 1;
                                    this.nameId_ = input.readInt32();
                                case 18:
                                    Value.Builder subBuilder = null;
                                    subBuilder = (this.bitField0_ & 2) == 2 ? this.value_.toBuilder() : subBuilder;
                                    this.value_ = (Value) input.readMessage(Value.PARSER, extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom(this.value_);
                                        this.value_ = subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 2;
                                default:
                                    if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                        done = true;
                                    }
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                        }
                    }
                    try {
                        unknownFieldsCodedOutput.flush();
                        this.unknownFields = unknownFieldsOutput.toByteString();
                    } catch (IOException e3) {
                        this.unknownFields = unknownFieldsOutput.toByteString();
                    } catch (Throwable th) {
                        this.unknownFields = unknownFieldsOutput.toByteString();
                        throw th;
                    }
                    makeExtensionsImmutable();
                } catch (Throwable th2) {
                    try {
                        unknownFieldsCodedOutput.flush();
                        this.unknownFields = unknownFieldsOutput.toByteString();
                    } catch (IOException e4) {
                        this.unknownFields = unknownFieldsOutput.toByteString();
                    } catch (Throwable th3) {
                        this.unknownFields = unknownFieldsOutput.toByteString();
                        throw th3;
                    }
                    makeExtensionsImmutable();
                    throw th2;
                }
            }

            static {
                defaultInstance.initFields();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public Parser<Argument> getParserForType() {
                return PARSER;
            }

            /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
                java.lang.NullPointerException
                */
            /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Annotation$Argument$Value.class */
            public static final class Value extends GeneratedMessageLite implements ValueOrBuilder {
                private final ByteString unknownFields;
                private int bitField0_;
                private Type type_;
                private long intValue_;
                private float floatValue_;
                private double doubleValue_;
                private int stringValue_;
                private int classId_;
                private int enumValueId_;
                private Annotation annotation_;
                private List<Value> arrayElement_;
                private int arrayDimensionCount_;
                private int flags_;
                private byte memoizedIsInitialized;
                private int memoizedSerializedSize;
                public static Parser<Value> PARSER = new AbstractParser<Value>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.1
                    @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                    public Value parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                        return new Value(input, extensionRegistry);
                    }
                };
                private static final Value defaultInstance = new Value(true);

                /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI
                    java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
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
                    */
                static /* synthetic */ long access$2402(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value r6, long r7) {
                    /*
                        r0 = r6
                        r1 = r7
                        // decode failed: arraycopy: source index -1 out of bounds for object array[6]
                        r0.intValue_ = r1
                        return r-1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.access$2402(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation$Argument$Value, long):long");
                }

                /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI
                    java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
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
                    */
                static /* synthetic */ double access$2602(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value r6, double r7) {
                    /*
                        r0 = r6
                        r1 = r7
                        // decode failed: arraycopy: source index -1 out of bounds for object array[6]
                        r0.doubleValue_ = r1
                        return r-1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.access$2602(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation$Argument$Value, double):double");
                }

                private Value(GeneratedMessageLite.Builder builder) {
                    super(builder);
                    this.memoizedIsInitialized = (byte) -1;
                    this.memoizedSerializedSize = -1;
                    this.unknownFields = builder.getUnknownFields();
                }

                private Value(boolean noInit) {
                    this.memoizedIsInitialized = (byte) -1;
                    this.memoizedSerializedSize = -1;
                    this.unknownFields = ByteString.EMPTY;
                }

                public static Value getDefaultInstance() {
                    return defaultInstance;
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public Value getDefaultInstanceForType() {
                    return defaultInstance;
                }

                /* JADX WARN: Multi-variable type inference failed */
                private Value(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    this.memoizedIsInitialized = (byte) -1;
                    this.memoizedSerializedSize = -1;
                    initFields();
                    int mutable_bitField0_ = 0;
                    ByteString.Output unknownFieldsOutput = ByteString.newOutput();
                    CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
                    try {
                        boolean done = false;
                        while (!done) {
                            try {
                                try {
                                    int tag = input.readTag();
                                    switch (tag) {
                                        case 0:
                                            done = true;
                                        case 8:
                                            int rawValue = input.readEnum();
                                            Type value = Type.valueOf(rawValue);
                                            if (value == null) {
                                                unknownFieldsCodedOutput.writeRawVarint32(tag);
                                                unknownFieldsCodedOutput.writeRawVarint32(rawValue);
                                            } else {
                                                this.bitField0_ |= 1;
                                                this.type_ = value;
                                            }
                                        case 16:
                                            this.bitField0_ |= 2;
                                            this.intValue_ = input.readSInt64();
                                        case 29:
                                            this.bitField0_ |= 4;
                                            this.floatValue_ = input.readFloat();
                                        case 33:
                                            this.bitField0_ |= 8;
                                            this.doubleValue_ = input.readDouble();
                                        case 40:
                                            this.bitField0_ |= 16;
                                            this.stringValue_ = input.readInt32();
                                        case 48:
                                            this.bitField0_ |= 32;
                                            this.classId_ = input.readInt32();
                                        case 56:
                                            this.bitField0_ |= 64;
                                            this.enumValueId_ = input.readInt32();
                                        case 66:
                                            Builder subBuilder = null;
                                            subBuilder = (this.bitField0_ & 128) == 128 ? this.annotation_.toBuilder() : subBuilder;
                                            this.annotation_ = (Annotation) input.readMessage(Annotation.PARSER, extensionRegistry);
                                            if (subBuilder != null) {
                                                subBuilder.mergeFrom(this.annotation_);
                                                this.annotation_ = subBuilder.buildPartial();
                                            }
                                            this.bitField0_ |= 128;
                                        case 74:
                                            if ((mutable_bitField0_ & 256) != 256) {
                                                this.arrayElement_ = new ArrayList();
                                                mutable_bitField0_ |= 256;
                                            }
                                            this.arrayElement_.add(input.readMessage(PARSER, extensionRegistry));
                                        case 80:
                                            this.bitField0_ |= 512;
                                            this.flags_ = input.readInt32();
                                        case 88:
                                            this.bitField0_ |= 256;
                                            this.arrayDimensionCount_ = input.readInt32();
                                        default:
                                            if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                                done = true;
                                            }
                                    }
                                } catch (IOException e) {
                                    throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                                }
                            } catch (InvalidProtocolBufferException e2) {
                                throw e2.setUnfinishedMessage(this);
                            }
                        }
                        if ((mutable_bitField0_ & 256) == 256) {
                            this.arrayElement_ = Collections.unmodifiableList(this.arrayElement_);
                        }
                        try {
                            unknownFieldsCodedOutput.flush();
                            this.unknownFields = unknownFieldsOutput.toByteString();
                        } catch (IOException e3) {
                            this.unknownFields = unknownFieldsOutput.toByteString();
                        } catch (Throwable th) {
                            this.unknownFields = unknownFieldsOutput.toByteString();
                            throw th;
                        }
                        makeExtensionsImmutable();
                    } catch (Throwable th2) {
                        if ((mutable_bitField0_ & 256) == 256) {
                            this.arrayElement_ = Collections.unmodifiableList(this.arrayElement_);
                        }
                        try {
                            unknownFieldsCodedOutput.flush();
                            this.unknownFields = unknownFieldsOutput.toByteString();
                        } catch (IOException e4) {
                            this.unknownFields = unknownFieldsOutput.toByteString();
                        } catch (Throwable th3) {
                            this.unknownFields = unknownFieldsOutput.toByteString();
                            throw th3;
                        }
                        makeExtensionsImmutable();
                        throw th2;
                    }
                }

                static {
                    defaultInstance.initFields();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
                public Parser<Value> getParserForType() {
                    return PARSER;
                }

                /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Annotation$Argument$Value$Type.class */
                public enum Type implements Internal.EnumLite {
                    BYTE(0, 0),
                    CHAR(1, 1),
                    SHORT(2, 2),
                    INT(3, 3),
                    LONG(4, 4),
                    FLOAT(5, 5),
                    DOUBLE(6, 6),
                    BOOLEAN(7, 7),
                    STRING(8, 8),
                    CLASS(9, 9),
                    ENUM(10, 10),
                    ANNOTATION(11, 11),
                    ARRAY(12, 12);

                    private static Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap<Type>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Type.1
                        @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                        public Type findValueByNumber(int number) {
                            return Type.valueOf(number);
                        }
                    };
                    private final int value;

                    @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite
                    public final int getNumber() {
                        return this.value;
                    }

                    public static Type valueOf(int value) {
                        switch (value) {
                            case 0:
                                return BYTE;
                            case 1:
                                return CHAR;
                            case 2:
                                return SHORT;
                            case 3:
                                return INT;
                            case 4:
                                return LONG;
                            case 5:
                                return FLOAT;
                            case 6:
                                return DOUBLE;
                            case 7:
                                return BOOLEAN;
                            case 8:
                                return STRING;
                            case 9:
                                return CLASS;
                            case 10:
                                return ENUM;
                            case 11:
                                return ANNOTATION;
                            case 12:
                                return ARRAY;
                            default:
                                return null;
                        }
                    }

                    Type(int index, int value) {
                        this.value = value;
                    }
                }

                public boolean hasType() {
                    return (this.bitField0_ & 1) == 1;
                }

                public Type getType() {
                    return this.type_;
                }

                public boolean hasIntValue() {
                    return (this.bitField0_ & 2) == 2;
                }

                public long getIntValue() {
                    return this.intValue_;
                }

                public boolean hasFloatValue() {
                    return (this.bitField0_ & 4) == 4;
                }

                public float getFloatValue() {
                    return this.floatValue_;
                }

                public boolean hasDoubleValue() {
                    return (this.bitField0_ & 8) == 8;
                }

                public double getDoubleValue() {
                    return this.doubleValue_;
                }

                public boolean hasStringValue() {
                    return (this.bitField0_ & 16) == 16;
                }

                public int getStringValue() {
                    return this.stringValue_;
                }

                public boolean hasClassId() {
                    return (this.bitField0_ & 32) == 32;
                }

                public int getClassId() {
                    return this.classId_;
                }

                public boolean hasEnumValueId() {
                    return (this.bitField0_ & 64) == 64;
                }

                public int getEnumValueId() {
                    return this.enumValueId_;
                }

                public boolean hasAnnotation() {
                    return (this.bitField0_ & 128) == 128;
                }

                public Annotation getAnnotation() {
                    return this.annotation_;
                }

                public List<Value> getArrayElementList() {
                    return this.arrayElement_;
                }

                public int getArrayElementCount() {
                    return this.arrayElement_.size();
                }

                public Value getArrayElement(int index) {
                    return this.arrayElement_.get(index);
                }

                public boolean hasArrayDimensionCount() {
                    return (this.bitField0_ & 256) == 256;
                }

                public int getArrayDimensionCount() {
                    return this.arrayDimensionCount_;
                }

                public boolean hasFlags() {
                    return (this.bitField0_ & 512) == 512;
                }

                public int getFlags() {
                    return this.flags_;
                }

                private void initFields() {
                    this.type_ = Type.BYTE;
                    this.intValue_ = 0L;
                    this.floatValue_ = 0.0f;
                    this.doubleValue_ = 0.0d;
                    this.stringValue_ = 0;
                    this.classId_ = 0;
                    this.enumValueId_ = 0;
                    this.annotation_ = Annotation.getDefaultInstance();
                    this.arrayElement_ = Collections.emptyList();
                    this.arrayDimensionCount_ = 0;
                    this.flags_ = 0;
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    byte isInitialized = this.memoizedIsInitialized;
                    if (isInitialized == 1) {
                        return true;
                    }
                    if (isInitialized == 0) {
                        return false;
                    }
                    if (hasAnnotation() && !getAnnotation().isInitialized()) {
                        this.memoizedIsInitialized = (byte) 0;
                        return false;
                    }
                    for (int i = 0; i < getArrayElementCount(); i++) {
                        if (!getArrayElement(i).isInitialized()) {
                            this.memoizedIsInitialized = (byte) 0;
                            return false;
                        }
                    }
                    this.memoizedIsInitialized = (byte) 1;
                    return true;
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
                public void writeTo(CodedOutputStream output) throws IOException {
                    getSerializedSize();
                    if ((this.bitField0_ & 1) == 1) {
                        output.writeEnum(1, this.type_.getNumber());
                    }
                    if ((this.bitField0_ & 2) == 2) {
                        output.writeSInt64(2, this.intValue_);
                    }
                    if ((this.bitField0_ & 4) == 4) {
                        output.writeFloat(3, this.floatValue_);
                    }
                    if ((this.bitField0_ & 8) == 8) {
                        output.writeDouble(4, this.doubleValue_);
                    }
                    if ((this.bitField0_ & 16) == 16) {
                        output.writeInt32(5, this.stringValue_);
                    }
                    if ((this.bitField0_ & 32) == 32) {
                        output.writeInt32(6, this.classId_);
                    }
                    if ((this.bitField0_ & 64) == 64) {
                        output.writeInt32(7, this.enumValueId_);
                    }
                    if ((this.bitField0_ & 128) == 128) {
                        output.writeMessage(8, this.annotation_);
                    }
                    for (int i = 0; i < this.arrayElement_.size(); i++) {
                        output.writeMessage(9, this.arrayElement_.get(i));
                    }
                    if ((this.bitField0_ & 512) == 512) {
                        output.writeInt32(10, this.flags_);
                    }
                    if ((this.bitField0_ & 256) == 256) {
                        output.writeInt32(11, this.arrayDimensionCount_);
                    }
                    output.writeRawBytes(this.unknownFields);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
                public int getSerializedSize() {
                    int size = this.memoizedSerializedSize;
                    if (size != -1) {
                        return size;
                    }
                    int size2 = 0;
                    if ((this.bitField0_ & 1) == 1) {
                        size2 = 0 + CodedOutputStream.computeEnumSize(1, this.type_.getNumber());
                    }
                    if ((this.bitField0_ & 2) == 2) {
                        size2 += CodedOutputStream.computeSInt64Size(2, this.intValue_);
                    }
                    if ((this.bitField0_ & 4) == 4) {
                        size2 += CodedOutputStream.computeFloatSize(3, this.floatValue_);
                    }
                    if ((this.bitField0_ & 8) == 8) {
                        size2 += CodedOutputStream.computeDoubleSize(4, this.doubleValue_);
                    }
                    if ((this.bitField0_ & 16) == 16) {
                        size2 += CodedOutputStream.computeInt32Size(5, this.stringValue_);
                    }
                    if ((this.bitField0_ & 32) == 32) {
                        size2 += CodedOutputStream.computeInt32Size(6, this.classId_);
                    }
                    if ((this.bitField0_ & 64) == 64) {
                        size2 += CodedOutputStream.computeInt32Size(7, this.enumValueId_);
                    }
                    if ((this.bitField0_ & 128) == 128) {
                        size2 += CodedOutputStream.computeMessageSize(8, this.annotation_);
                    }
                    for (int i = 0; i < this.arrayElement_.size(); i++) {
                        size2 += CodedOutputStream.computeMessageSize(9, this.arrayElement_.get(i));
                    }
                    if ((this.bitField0_ & 512) == 512) {
                        size2 += CodedOutputStream.computeInt32Size(10, this.flags_);
                    }
                    if ((this.bitField0_ & 256) == 256) {
                        size2 += CodedOutputStream.computeInt32Size(11, this.arrayDimensionCount_);
                    }
                    int size3 = size2 + this.unknownFields.size();
                    this.memoizedSerializedSize = size3;
                    return size3;
                }

                public static Builder newBuilder() {
                    return Builder.create();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
                public Builder newBuilderForType() {
                    return newBuilder();
                }

                public static Builder newBuilder(Value prototype) {
                    return newBuilder().mergeFrom(prototype);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
                public Builder toBuilder() {
                    return newBuilder(this);
                }

                /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Annotation$Argument$Value$Builder.class */
                public static final class Builder extends GeneratedMessageLite.Builder<Value, Builder> implements ValueOrBuilder {
                    private int bitField0_;
                    private long intValue_;
                    private float floatValue_;
                    private double doubleValue_;
                    private int stringValue_;
                    private int classId_;
                    private int enumValueId_;
                    private int arrayDimensionCount_;
                    private int flags_;
                    private Type type_ = Type.BYTE;
                    private Annotation annotation_ = Annotation.getDefaultInstance();
                    private List<Value> arrayElement_ = Collections.emptyList();

                    private Builder() {
                        maybeForceBuilderInitialization();
                    }

                    private void maybeForceBuilderInitialization() {
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public static Builder create() {
                        return new Builder();
                    }

                    @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
                    /* renamed from: clone */
                    public Builder mo3702clone() {
                        return create().mergeFrom(buildPartial());
                    }

                    @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                    public Value getDefaultInstanceForType() {
                        return Value.getDefaultInstance();
                    }

                    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
                    public Value build() {
                        Value result = buildPartial();
                        if (!result.isInitialized()) {
                            throw newUninitializedMessageException(result);
                        }
                        return result;
                    }

                    /* JADX WARN: Failed to check method for inline after forced processkotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.access$2402(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation$Argument$Value, long):long */
                    /* JADX WARN: Failed to check method for inline after forced processkotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.access$2602(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation$Argument$Value, double):double */
                    public Value buildPartial() {
                        Value result = new Value(this);
                        int from_bitField0_ = this.bitField0_;
                        int to_bitField0_ = 0;
                        if ((from_bitField0_ & 1) == 1) {
                            to_bitField0_ = 0 | 1;
                        }
                        result.type_ = this.type_;
                        if ((from_bitField0_ & 2) == 2) {
                            to_bitField0_ |= 2;
                        }
                        Value.access$2402(result, this.intValue_);
                        if ((from_bitField0_ & 4) == 4) {
                            to_bitField0_ |= 4;
                        }
                        result.floatValue_ = this.floatValue_;
                        if ((from_bitField0_ & 8) == 8) {
                            to_bitField0_ |= 8;
                        }
                        Value.access$2602(result, this.doubleValue_);
                        if ((from_bitField0_ & 16) == 16) {
                            to_bitField0_ |= 16;
                        }
                        result.stringValue_ = this.stringValue_;
                        if ((from_bitField0_ & 32) == 32) {
                            to_bitField0_ |= 32;
                        }
                        result.classId_ = this.classId_;
                        if ((from_bitField0_ & 64) == 64) {
                            to_bitField0_ |= 64;
                        }
                        result.enumValueId_ = this.enumValueId_;
                        if ((from_bitField0_ & 128) == 128) {
                            to_bitField0_ |= 128;
                        }
                        result.annotation_ = this.annotation_;
                        if ((this.bitField0_ & 256) == 256) {
                            this.arrayElement_ = Collections.unmodifiableList(this.arrayElement_);
                            this.bitField0_ &= -257;
                        }
                        result.arrayElement_ = this.arrayElement_;
                        if ((from_bitField0_ & 512) == 512) {
                            to_bitField0_ |= 256;
                        }
                        result.arrayDimensionCount_ = this.arrayDimensionCount_;
                        if ((from_bitField0_ & 1024) == 1024) {
                            to_bitField0_ |= 512;
                        }
                        result.flags_ = this.flags_;
                        result.bitField0_ = to_bitField0_;
                        return result;
                    }

                    @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
                    public Builder mergeFrom(Value other) {
                        if (other == Value.getDefaultInstance()) {
                            return this;
                        }
                        if (other.hasType()) {
                            setType(other.getType());
                        }
                        if (other.hasIntValue()) {
                            setIntValue(other.getIntValue());
                        }
                        if (other.hasFloatValue()) {
                            setFloatValue(other.getFloatValue());
                        }
                        if (other.hasDoubleValue()) {
                            setDoubleValue(other.getDoubleValue());
                        }
                        if (other.hasStringValue()) {
                            setStringValue(other.getStringValue());
                        }
                        if (other.hasClassId()) {
                            setClassId(other.getClassId());
                        }
                        if (other.hasEnumValueId()) {
                            setEnumValueId(other.getEnumValueId());
                        }
                        if (other.hasAnnotation()) {
                            mergeAnnotation(other.getAnnotation());
                        }
                        if (!other.arrayElement_.isEmpty()) {
                            if (this.arrayElement_.isEmpty()) {
                                this.arrayElement_ = other.arrayElement_;
                                this.bitField0_ &= -257;
                            } else {
                                ensureArrayElementIsMutable();
                                this.arrayElement_.addAll(other.arrayElement_);
                            }
                        }
                        if (other.hasArrayDimensionCount()) {
                            setArrayDimensionCount(other.getArrayDimensionCount());
                        }
                        if (other.hasFlags()) {
                            setFlags(other.getFlags());
                        }
                        setUnknownFields(getUnknownFields().concat(other.unknownFields));
                        return this;
                    }

                    @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                    public final boolean isInitialized() {
                        if (hasAnnotation() && !getAnnotation().isInitialized()) {
                            return false;
                        }
                        for (int i = 0; i < getArrayElementCount(); i++) {
                            if (!getArrayElement(i).isInitialized()) {
                                return false;
                            }
                        }
                        return true;
                    }

                    @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
                    public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                        Value parsedMessage = null;
                        try {
                            try {
                                parsedMessage = Value.PARSER.parsePartialFrom(input, extensionRegistry);
                                if (parsedMessage != null) {
                                    mergeFrom(parsedMessage);
                                }
                                return this;
                            } catch (InvalidProtocolBufferException e) {
                                parsedMessage = (Value) e.getUnfinishedMessage();
                                throw e;
                            }
                        } catch (Throwable th) {
                            if (parsedMessage != null) {
                                mergeFrom(parsedMessage);
                            }
                            throw th;
                        }
                    }

                    public Builder setType(Type value) {
                        if (value == null) {
                            throw new NullPointerException();
                        }
                        this.bitField0_ |= 1;
                        this.type_ = value;
                        return this;
                    }

                    public Builder setIntValue(long value) {
                        this.bitField0_ |= 2;
                        this.intValue_ = value;
                        return this;
                    }

                    public Builder setFloatValue(float value) {
                        this.bitField0_ |= 4;
                        this.floatValue_ = value;
                        return this;
                    }

                    public Builder setDoubleValue(double value) {
                        this.bitField0_ |= 8;
                        this.doubleValue_ = value;
                        return this;
                    }

                    public Builder setStringValue(int value) {
                        this.bitField0_ |= 16;
                        this.stringValue_ = value;
                        return this;
                    }

                    public Builder setClassId(int value) {
                        this.bitField0_ |= 32;
                        this.classId_ = value;
                        return this;
                    }

                    public Builder setEnumValueId(int value) {
                        this.bitField0_ |= 64;
                        this.enumValueId_ = value;
                        return this;
                    }

                    public boolean hasAnnotation() {
                        return (this.bitField0_ & 128) == 128;
                    }

                    public Annotation getAnnotation() {
                        return this.annotation_;
                    }

                    public Builder mergeAnnotation(Annotation value) {
                        if ((this.bitField0_ & 128) == 128 && this.annotation_ != Annotation.getDefaultInstance()) {
                            this.annotation_ = Annotation.newBuilder(this.annotation_).mergeFrom(value).buildPartial();
                        } else {
                            this.annotation_ = value;
                        }
                        this.bitField0_ |= 128;
                        return this;
                    }

                    private void ensureArrayElementIsMutable() {
                        if ((this.bitField0_ & 256) != 256) {
                            this.arrayElement_ = new ArrayList(this.arrayElement_);
                            this.bitField0_ |= 256;
                        }
                    }

                    public int getArrayElementCount() {
                        return this.arrayElement_.size();
                    }

                    public Value getArrayElement(int index) {
                        return this.arrayElement_.get(index);
                    }

                    public Builder setArrayDimensionCount(int value) {
                        this.bitField0_ |= 512;
                        this.arrayDimensionCount_ = value;
                        return this;
                    }

                    public Builder setFlags(int value) {
                        this.bitField0_ |= 1024;
                        this.flags_ = value;
                        return this;
                    }
                }
            }

            public boolean hasNameId() {
                return (this.bitField0_ & 1) == 1;
            }

            public int getNameId() {
                return this.nameId_;
            }

            public boolean hasValue() {
                return (this.bitField0_ & 2) == 2;
            }

            public Value getValue() {
                return this.value_;
            }

            private void initFields() {
                this.nameId_ = 0;
                this.value_ = Value.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte isInitialized = this.memoizedIsInitialized;
                if (isInitialized == 1) {
                    return true;
                }
                if (isInitialized == 0) {
                    return false;
                }
                if (!hasNameId()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!hasValue()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                if (!getValue().isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                getSerializedSize();
                if ((this.bitField0_ & 1) == 1) {
                    output.writeInt32(1, this.nameId_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeMessage(2, this.value_);
                }
                output.writeRawBytes(this.unknownFields);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public int getSerializedSize() {
                int size = this.memoizedSerializedSize;
                if (size != -1) {
                    return size;
                }
                int size2 = 0;
                if ((this.bitField0_ & 1) == 1) {
                    size2 = 0 + CodedOutputStream.computeInt32Size(1, this.nameId_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeMessageSize(2, this.value_);
                }
                int size3 = size2 + this.unknownFields.size();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder(Argument prototype) {
                return newBuilder().mergeFrom(prototype);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Annotation$Argument$Builder.class */
            public static final class Builder extends GeneratedMessageLite.Builder<Argument, Builder> implements ArgumentOrBuilder {
                private int bitField0_;
                private int nameId_;
                private Value value_ = Value.getDefaultInstance();

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static Builder create() {
                    return new Builder();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone, reason: merged with bridge method [inline-methods] */
                public Builder mo3702clone() {
                    return create().mergeFrom(buildPartial());
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public Argument getDefaultInstanceForType() {
                    return Argument.getDefaultInstance();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
                public Argument build() {
                    Argument result = buildPartial();
                    if (!result.isInitialized()) {
                        throw newUninitializedMessageException(result);
                    }
                    return result;
                }

                public Argument buildPartial() {
                    Argument result = new Argument(this, (AnonymousClass1) null);
                    int from_bitField0_ = this.bitField0_;
                    int to_bitField0_ = 0;
                    if ((from_bitField0_ & 1) == 1) {
                        to_bitField0_ = 0 | 1;
                    }
                    result.nameId_ = this.nameId_;
                    if ((from_bitField0_ & 2) == 2) {
                        to_bitField0_ |= 2;
                    }
                    result.value_ = this.value_;
                    result.bitField0_ = to_bitField0_;
                    return result;
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
                public Builder mergeFrom(Argument other) {
                    if (other == Argument.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasNameId()) {
                        setNameId(other.getNameId());
                    }
                    if (other.hasValue()) {
                        mergeValue(other.getValue());
                    }
                    setUnknownFields(getUnknownFields().concat(other.unknownFields));
                    return this;
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    if (!hasNameId() || !hasValue() || !getValue().isInitialized()) {
                        return false;
                    }
                    return true;
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    Argument parsedMessage = null;
                    try {
                        try {
                            parsedMessage = Argument.PARSER.parsePartialFrom(input, extensionRegistry);
                            if (parsedMessage != null) {
                                mergeFrom(parsedMessage);
                            }
                            return this;
                        } catch (InvalidProtocolBufferException e) {
                            parsedMessage = (Argument) e.getUnfinishedMessage();
                            throw e;
                        }
                    } catch (Throwable th) {
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        throw th;
                    }
                }

                public boolean hasNameId() {
                    return (this.bitField0_ & 1) == 1;
                }

                public Builder setNameId(int value) {
                    this.bitField0_ |= 1;
                    this.nameId_ = value;
                    return this;
                }

                public boolean hasValue() {
                    return (this.bitField0_ & 2) == 2;
                }

                public Value getValue() {
                    return this.value_;
                }

                public Builder mergeValue(Value value) {
                    if ((this.bitField0_ & 2) == 2 && this.value_ != Value.getDefaultInstance()) {
                        this.value_ = Value.newBuilder(this.value_).mergeFrom(value).buildPartial();
                    } else {
                        this.value_ = value;
                    }
                    this.bitField0_ |= 2;
                    return this;
                }
            }
        }

        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getId() {
            return this.id_;
        }

        public List<Argument> getArgumentList() {
            return this.argument_;
        }

        public int getArgumentCount() {
            return this.argument_.size();
        }

        public Argument getArgument(int index) {
            return this.argument_.get(index);
        }

        private void initFields() {
            this.id_ = 0;
            this.argument_ = Collections.emptyList();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasId()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            for (int i = 0; i < getArgumentCount(); i++) {
                if (!getArgument(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.id_);
            }
            for (int i = 0; i < this.argument_.size(); i++) {
                output.writeMessage(2, this.argument_.get(i));
            }
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.id_);
            }
            for (int i = 0; i < this.argument_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.argument_.get(i));
            }
            int size3 = size2 + this.unknownFields.size();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Annotation prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Annotation$Builder.class */
        public static final class Builder extends GeneratedMessageLite.Builder<Annotation, Builder> implements AnnotationOrBuilder {
            private int bitField0_;
            private int id_;
            private List<Argument> argument_ = Collections.emptyList();

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((Annotation) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public Annotation getDefaultInstanceForType() {
                return Annotation.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Annotation build() {
                Annotation result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public Annotation buildPartial() {
                Annotation result = new Annotation(this, (AnonymousClass1) null);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.id_ = this.id_;
                if ((this.bitField0_ & 2) == 2) {
                    this.argument_ = Collections.unmodifiableList(this.argument_);
                    this.bitField0_ &= -3;
                }
                result.argument_ = this.argument_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(Annotation other) {
                if (other == Annotation.getDefaultInstance()) {
                    return this;
                }
                if (other.hasId()) {
                    setId(other.getId());
                }
                if (!other.argument_.isEmpty()) {
                    if (this.argument_.isEmpty()) {
                        this.argument_ = other.argument_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureArgumentIsMutable();
                        this.argument_.addAll(other.argument_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasId()) {
                    return false;
                }
                for (int i = 0; i < getArgumentCount(); i++) {
                    if (!getArgument(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Annotation parsedMessage = null;
                try {
                    try {
                        parsedMessage = Annotation.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (Annotation) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            public boolean hasId() {
                return (this.bitField0_ & 1) == 1;
            }

            public Builder setId(int value) {
                this.bitField0_ |= 1;
                this.id_ = value;
                return this;
            }

            private void ensureArgumentIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.argument_ = new ArrayList(this.argument_);
                    this.bitField0_ |= 2;
                }
            }

            public int getArgumentCount() {
                return this.argument_.size();
            }

            public Argument getArgument(int index) {
                return this.argument_.get(index);
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Type.class */
    public static final class Type extends GeneratedMessageLite.ExtendableMessage<Type> implements TypeOrBuilder {
        private final ByteString unknownFields;
        private int bitField0_;
        private List<Argument> argument_;
        private boolean nullable_;
        private int flexibleTypeCapabilitiesId_;
        private Type flexibleUpperBound_;
        private int flexibleUpperBoundId_;
        private int className_;
        private int typeParameter_;
        private int typeParameterName_;
        private int typeAliasName_;
        private Type outerType_;
        private int outerTypeId_;
        private Type abbreviatedType_;
        private int abbreviatedTypeId_;
        private int flags_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<Type> PARSER = new AbstractParser<Type>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public Type parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Type(input, extensionRegistry, null);
            }
        };
        private static final Type defaultInstance = new Type(true);

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Type$ArgumentOrBuilder.class */
        public interface ArgumentOrBuilder extends MessageLiteOrBuilder {
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        /* synthetic */ Type(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ Type(GeneratedMessageLite.ExtendableBuilder x0, AnonymousClass1 x1) {
            this((GeneratedMessageLite.ExtendableBuilder<Type, ?>) x0);
        }

        private Type(GeneratedMessageLite.ExtendableBuilder<Type, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Type(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Type getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public Type getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Type(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                case 8:
                                    this.bitField0_ |= 4096;
                                    this.flags_ = input.readInt32();
                                case 18:
                                    if ((mutable_bitField0_ & 1) != 1) {
                                        this.argument_ = new ArrayList();
                                        mutable_bitField0_ |= 1;
                                    }
                                    this.argument_.add(input.readMessage(Argument.PARSER, extensionRegistry));
                                case 24:
                                    this.bitField0_ |= 1;
                                    this.nullable_ = input.readBool();
                                case 32:
                                    this.bitField0_ |= 2;
                                    this.flexibleTypeCapabilitiesId_ = input.readInt32();
                                case 42:
                                    Builder subBuilder = null;
                                    subBuilder = (this.bitField0_ & 4) == 4 ? this.flexibleUpperBound_.toBuilder() : subBuilder;
                                    this.flexibleUpperBound_ = (Type) input.readMessage(PARSER, extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom(this.flexibleUpperBound_);
                                        this.flexibleUpperBound_ = subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 4;
                                case 48:
                                    this.bitField0_ |= 16;
                                    this.className_ = input.readInt32();
                                case 56:
                                    this.bitField0_ |= 32;
                                    this.typeParameter_ = input.readInt32();
                                case 64:
                                    this.bitField0_ |= 8;
                                    this.flexibleUpperBoundId_ = input.readInt32();
                                case 72:
                                    this.bitField0_ |= 64;
                                    this.typeParameterName_ = input.readInt32();
                                case 82:
                                    Builder subBuilder2 = null;
                                    subBuilder2 = (this.bitField0_ & 256) == 256 ? this.outerType_.toBuilder() : subBuilder2;
                                    this.outerType_ = (Type) input.readMessage(PARSER, extensionRegistry);
                                    if (subBuilder2 != null) {
                                        subBuilder2.mergeFrom(this.outerType_);
                                        this.outerType_ = subBuilder2.buildPartial();
                                    }
                                    this.bitField0_ |= 256;
                                case 88:
                                    this.bitField0_ |= 512;
                                    this.outerTypeId_ = input.readInt32();
                                case 96:
                                    this.bitField0_ |= 128;
                                    this.typeAliasName_ = input.readInt32();
                                case 106:
                                    Builder subBuilder3 = null;
                                    subBuilder3 = (this.bitField0_ & 1024) == 1024 ? this.abbreviatedType_.toBuilder() : subBuilder3;
                                    this.abbreviatedType_ = (Type) input.readMessage(PARSER, extensionRegistry);
                                    if (subBuilder3 != null) {
                                        subBuilder3.mergeFrom(this.abbreviatedType_);
                                        this.abbreviatedType_ = subBuilder3.buildPartial();
                                    }
                                    this.bitField0_ |= 1024;
                                case 112:
                                    this.bitField0_ |= 2048;
                                    this.abbreviatedTypeId_ = input.readInt32();
                                default:
                                    if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                        done = true;
                                    }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 1) == 1) {
                    this.argument_ = Collections.unmodifiableList(this.argument_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 1) == 1) {
                    this.argument_ = Collections.unmodifiableList(this.argument_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<Type> getParserForType() {
            return PARSER;
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Type$Argument.class */
        public static final class Argument extends GeneratedMessageLite implements ArgumentOrBuilder {
            private final ByteString unknownFields;
            private int bitField0_;
            private Projection projection_;
            private Type type_;
            private int typeId_;
            private byte memoizedIsInitialized;
            private int memoizedSerializedSize;
            public static Parser<Argument> PARSER = new AbstractParser<Argument>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.1
                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                    return parsePartialFrom(x0, x1);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Argument parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                    return new Argument(input, extensionRegistry, null);
                }
            };
            private static final Argument defaultInstance = new Argument(true);

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
                return toBuilder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
                return newBuilderForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            /* synthetic */ Argument(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
                this(x0, x1);
            }

            /* synthetic */ Argument(GeneratedMessageLite.Builder x0, AnonymousClass1 x1) {
                this(x0);
            }

            private Argument(GeneratedMessageLite.Builder builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = builder.getUnknownFields();
            }

            private Argument(boolean noInit) {
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = ByteString.EMPTY;
            }

            public static Argument getDefaultInstance() {
                return defaultInstance;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public Argument getDefaultInstanceForType() {
                return defaultInstance;
            }

            private Argument(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                initFields();
                ByteString.Output unknownFieldsOutput = ByteString.newOutput();
                CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
                try {
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                case 8:
                                    int rawValue = input.readEnum();
                                    Projection value = Projection.valueOf(rawValue);
                                    if (value == null) {
                                        unknownFieldsCodedOutput.writeRawVarint32(tag);
                                        unknownFieldsCodedOutput.writeRawVarint32(rawValue);
                                    } else {
                                        this.bitField0_ |= 1;
                                        this.projection_ = value;
                                    }
                                case 18:
                                    Builder subBuilder = null;
                                    subBuilder = (this.bitField0_ & 2) == 2 ? this.type_.toBuilder() : subBuilder;
                                    this.type_ = (Type) input.readMessage(Type.PARSER, extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom(this.type_);
                                        this.type_ = subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 2;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.typeId_ = input.readInt32();
                                default:
                                    if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                        done = true;
                                    }
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                        }
                    }
                    try {
                        unknownFieldsCodedOutput.flush();
                        this.unknownFields = unknownFieldsOutput.toByteString();
                    } catch (IOException e3) {
                        this.unknownFields = unknownFieldsOutput.toByteString();
                    } catch (Throwable th) {
                        this.unknownFields = unknownFieldsOutput.toByteString();
                        throw th;
                    }
                    makeExtensionsImmutable();
                } catch (Throwable th2) {
                    try {
                        unknownFieldsCodedOutput.flush();
                        this.unknownFields = unknownFieldsOutput.toByteString();
                    } catch (IOException e4) {
                        this.unknownFields = unknownFieldsOutput.toByteString();
                    } catch (Throwable th3) {
                        this.unknownFields = unknownFieldsOutput.toByteString();
                        throw th3;
                    }
                    makeExtensionsImmutable();
                    throw th2;
                }
            }

            static {
                defaultInstance.initFields();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public Parser<Argument> getParserForType() {
                return PARSER;
            }

            /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Type$Argument$Projection.class */
            public enum Projection implements Internal.EnumLite {
                IN(0, 0),
                OUT(1, 1),
                INV(2, 2),
                STAR(3, 3);

                private static Internal.EnumLiteMap<Projection> internalValueMap = new Internal.EnumLiteMap<Projection>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Projection.1
                    @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                    public /* bridge */ /* synthetic */ Internal.EnumLite findValueByNumber(int x0) {
                        return findValueByNumber(x0);
                    }

                    @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                    public Projection findValueByNumber(int number) {
                        return Projection.valueOf(number);
                    }
                };
                private final int value;

                static {
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite
                public final int getNumber() {
                    return this.value;
                }

                public static Projection valueOf(int value) {
                    switch (value) {
                        case 0:
                            return IN;
                        case 1:
                            return OUT;
                        case 2:
                            return INV;
                        case 3:
                            return STAR;
                        default:
                            return null;
                    }
                }

                Projection(int index, int value) {
                    this.value = value;
                }
            }

            public boolean hasProjection() {
                return (this.bitField0_ & 1) == 1;
            }

            public Projection getProjection() {
                return this.projection_;
            }

            public boolean hasType() {
                return (this.bitField0_ & 2) == 2;
            }

            public Type getType() {
                return this.type_;
            }

            public boolean hasTypeId() {
                return (this.bitField0_ & 4) == 4;
            }

            public int getTypeId() {
                return this.typeId_;
            }

            private void initFields() {
                this.projection_ = Projection.INV;
                this.type_ = Type.getDefaultInstance();
                this.typeId_ = 0;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                byte isInitialized = this.memoizedIsInitialized;
                if (isInitialized == 1) {
                    return true;
                }
                if (isInitialized == 0) {
                    return false;
                }
                if (hasType() && !getType().isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public void writeTo(CodedOutputStream output) throws IOException {
                getSerializedSize();
                if ((this.bitField0_ & 1) == 1) {
                    output.writeEnum(1, this.projection_.getNumber());
                }
                if ((this.bitField0_ & 2) == 2) {
                    output.writeMessage(2, this.type_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    output.writeInt32(3, this.typeId_);
                }
                output.writeRawBytes(this.unknownFields);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public int getSerializedSize() {
                int size = this.memoizedSerializedSize;
                if (size != -1) {
                    return size;
                }
                int size2 = 0;
                if ((this.bitField0_ & 1) == 1) {
                    size2 = 0 + CodedOutputStream.computeEnumSize(1, this.projection_.getNumber());
                }
                if ((this.bitField0_ & 2) == 2) {
                    size2 += CodedOutputStream.computeMessageSize(2, this.type_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    size2 += CodedOutputStream.computeInt32Size(3, this.typeId_);
                }
                int size3 = size2 + this.unknownFields.size();
                this.memoizedSerializedSize = size3;
                return size3;
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public Builder newBuilderForType() {
                return newBuilder();
            }

            public static Builder newBuilder(Argument prototype) {
                return newBuilder().mergeFrom(prototype);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Type$Argument$Builder.class */
            public static final class Builder extends GeneratedMessageLite.Builder<Argument, Builder> implements ArgumentOrBuilder {
                private int bitField0_;
                private Projection projection_ = Projection.INV;
                private Type type_ = Type.getDefaultInstance();
                private int typeId_;

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                    return getDefaultInstanceForType();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
                public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                    return mergeFrom((Argument) x0);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                    return mo3702clone();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
                public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                    return mergeFrom(x0, x1);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                    return mo3702clone();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
                public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                    return mergeFrom(x0, x1);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
                public /* bridge */ /* synthetic */ MessageLite build() {
                    return build();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                    return getDefaultInstanceForType();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                    return mo3702clone();
                }

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private void maybeForceBuilderInitialization() {
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static Builder create() {
                    return new Builder();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
                /* renamed from: clone */
                public Builder mo3702clone() {
                    return create().mergeFrom(buildPartial());
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public Argument getDefaultInstanceForType() {
                    return Argument.getDefaultInstance();
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
                public Argument build() {
                    Argument result = buildPartial();
                    if (!result.isInitialized()) {
                        throw newUninitializedMessageException(result);
                    }
                    return result;
                }

                public Argument buildPartial() {
                    Argument result = new Argument(this, (AnonymousClass1) null);
                    int from_bitField0_ = this.bitField0_;
                    int to_bitField0_ = 0;
                    if ((from_bitField0_ & 1) == 1) {
                        to_bitField0_ = 0 | 1;
                    }
                    result.projection_ = this.projection_;
                    if ((from_bitField0_ & 2) == 2) {
                        to_bitField0_ |= 2;
                    }
                    result.type_ = this.type_;
                    if ((from_bitField0_ & 4) == 4) {
                        to_bitField0_ |= 4;
                    }
                    result.typeId_ = this.typeId_;
                    result.bitField0_ = to_bitField0_;
                    return result;
                }

                public Builder mergeFrom(Argument other) {
                    if (other == Argument.getDefaultInstance()) {
                        return this;
                    }
                    if (other.hasProjection()) {
                        setProjection(other.getProjection());
                    }
                    if (other.hasType()) {
                        mergeType(other.getType());
                    }
                    if (other.hasTypeId()) {
                        setTypeId(other.getTypeId());
                    }
                    setUnknownFields(getUnknownFields().concat(other.unknownFields));
                    return this;
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    if (hasType() && !getType().isInitialized()) {
                        return false;
                    }
                    return true;
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
                public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                    Argument parsedMessage = null;
                    try {
                        try {
                            parsedMessage = Argument.PARSER.parsePartialFrom(input, extensionRegistry);
                            if (parsedMessage != null) {
                                mergeFrom(parsedMessage);
                            }
                            return this;
                        } catch (InvalidProtocolBufferException e) {
                            parsedMessage = (Argument) e.getUnfinishedMessage();
                            throw e;
                        }
                    } catch (Throwable th) {
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        throw th;
                    }
                }

                public Builder setProjection(Projection value) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 1;
                    this.projection_ = value;
                    return this;
                }

                public boolean hasType() {
                    return (this.bitField0_ & 2) == 2;
                }

                public Type getType() {
                    return this.type_;
                }

                public Builder mergeType(Type value) {
                    if ((this.bitField0_ & 2) == 2 && this.type_ != Type.getDefaultInstance()) {
                        this.type_ = Type.newBuilder(this.type_).mergeFrom(value).buildPartial();
                    } else {
                        this.type_ = value;
                    }
                    this.bitField0_ |= 2;
                    return this;
                }

                public Builder setTypeId(int value) {
                    this.bitField0_ |= 4;
                    this.typeId_ = value;
                    return this;
                }
            }
        }

        public List<Argument> getArgumentList() {
            return this.argument_;
        }

        public int getArgumentCount() {
            return this.argument_.size();
        }

        public Argument getArgument(int index) {
            return this.argument_.get(index);
        }

        public boolean hasNullable() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean getNullable() {
            return this.nullable_;
        }

        public boolean hasFlexibleTypeCapabilitiesId() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getFlexibleTypeCapabilitiesId() {
            return this.flexibleTypeCapabilitiesId_;
        }

        public boolean hasFlexibleUpperBound() {
            return (this.bitField0_ & 4) == 4;
        }

        public Type getFlexibleUpperBound() {
            return this.flexibleUpperBound_;
        }

        public boolean hasFlexibleUpperBoundId() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getFlexibleUpperBoundId() {
            return this.flexibleUpperBoundId_;
        }

        public boolean hasClassName() {
            return (this.bitField0_ & 16) == 16;
        }

        public int getClassName() {
            return this.className_;
        }

        public boolean hasTypeParameter() {
            return (this.bitField0_ & 32) == 32;
        }

        public int getTypeParameter() {
            return this.typeParameter_;
        }

        public boolean hasTypeParameterName() {
            return (this.bitField0_ & 64) == 64;
        }

        public int getTypeParameterName() {
            return this.typeParameterName_;
        }

        public boolean hasTypeAliasName() {
            return (this.bitField0_ & 128) == 128;
        }

        public int getTypeAliasName() {
            return this.typeAliasName_;
        }

        public boolean hasOuterType() {
            return (this.bitField0_ & 256) == 256;
        }

        public Type getOuterType() {
            return this.outerType_;
        }

        public boolean hasOuterTypeId() {
            return (this.bitField0_ & 512) == 512;
        }

        public int getOuterTypeId() {
            return this.outerTypeId_;
        }

        public boolean hasAbbreviatedType() {
            return (this.bitField0_ & 1024) == 1024;
        }

        public Type getAbbreviatedType() {
            return this.abbreviatedType_;
        }

        public boolean hasAbbreviatedTypeId() {
            return (this.bitField0_ & 2048) == 2048;
        }

        public int getAbbreviatedTypeId() {
            return this.abbreviatedTypeId_;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 4096) == 4096;
        }

        public int getFlags() {
            return this.flags_;
        }

        private void initFields() {
            this.argument_ = Collections.emptyList();
            this.nullable_ = false;
            this.flexibleTypeCapabilitiesId_ = 0;
            this.flexibleUpperBound_ = getDefaultInstance();
            this.flexibleUpperBoundId_ = 0;
            this.className_ = 0;
            this.typeParameter_ = 0;
            this.typeParameterName_ = 0;
            this.typeAliasName_ = 0;
            this.outerType_ = getDefaultInstance();
            this.outerTypeId_ = 0;
            this.abbreviatedType_ = getDefaultInstance();
            this.abbreviatedTypeId_ = 0;
            this.flags_ = 0;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < getArgumentCount(); i++) {
                if (!getArgument(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (hasFlexibleUpperBound() && !getFlexibleUpperBound().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (hasOuterType() && !getOuterType().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (hasAbbreviatedType() && !getAbbreviatedType().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            GeneratedMessageLite.ExtendableMessage<MessageType>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeInt32(1, this.flags_);
            }
            for (int i = 0; i < this.argument_.size(); i++) {
                output.writeMessage(2, this.argument_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(3, this.nullable_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(4, this.flexibleTypeCapabilitiesId_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(5, this.flexibleUpperBound_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(6, this.className_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(7, this.typeParameter_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(8, this.flexibleUpperBoundId_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(9, this.typeParameterName_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeMessage(10, this.outerType_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeInt32(11, this.outerTypeId_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeInt32(12, this.typeAliasName_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeMessage(13, this.abbreviatedType_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeInt32(14, this.abbreviatedTypeId_);
            }
            extensionWriterNewExtensionWriter.writeUntil(200, output);
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 4096) == 4096) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.flags_);
            }
            for (int i = 0; i < this.argument_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.argument_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                size2 += CodedOutputStream.computeBoolSize(3, this.nullable_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(4, this.flexibleTypeCapabilitiesId_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(5, this.flexibleUpperBound_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(6, this.className_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(7, this.typeParameter_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(8, this.flexibleUpperBoundId_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt32Size(9, this.typeParameterName_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeMessageSize(10, this.outerType_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeInt32Size(11, this.outerTypeId_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeInt32Size(12, this.typeAliasName_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeMessageSize(13, this.abbreviatedType_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeInt32Size(14, this.abbreviatedTypeId_);
            }
            int size3 = size2 + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Type prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Type$Builder.class */
        public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<Type, Builder> implements TypeOrBuilder {
            private int bitField0_;
            private boolean nullable_;
            private int flexibleTypeCapabilitiesId_;
            private int flexibleUpperBoundId_;
            private int className_;
            private int typeParameter_;
            private int typeParameterName_;
            private int typeAliasName_;
            private int outerTypeId_;
            private int abbreviatedTypeId_;
            private int flags_;
            private List<Argument> argument_ = Collections.emptyList();
            private Type flexibleUpperBound_ = Type.getDefaultInstance();
            private Type outerType_ = Type.getDefaultInstance();
            private Type abbreviatedType_ = Type.getDefaultInstance();

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.ExtendableBuilder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((Type) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public Type getDefaultInstanceForType() {
                return Type.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Type build() {
                Type result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public Type buildPartial() {
                Type result = new Type(this, (AnonymousClass1) null);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((this.bitField0_ & 1) == 1) {
                    this.argument_ = Collections.unmodifiableList(this.argument_);
                    this.bitField0_ &= -2;
                }
                result.argument_ = this.argument_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ = 0 | 1;
                }
                result.nullable_ = this.nullable_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 2;
                }
                result.flexibleTypeCapabilitiesId_ = this.flexibleTypeCapabilitiesId_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 4;
                }
                result.flexibleUpperBound_ = this.flexibleUpperBound_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 8;
                }
                result.flexibleUpperBoundId_ = this.flexibleUpperBoundId_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 16;
                }
                result.className_ = this.className_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 32;
                }
                result.typeParameter_ = this.typeParameter_;
                if ((from_bitField0_ & 128) == 128) {
                    to_bitField0_ |= 64;
                }
                result.typeParameterName_ = this.typeParameterName_;
                if ((from_bitField0_ & 256) == 256) {
                    to_bitField0_ |= 128;
                }
                result.typeAliasName_ = this.typeAliasName_;
                if ((from_bitField0_ & 512) == 512) {
                    to_bitField0_ |= 256;
                }
                result.outerType_ = this.outerType_;
                if ((from_bitField0_ & 1024) == 1024) {
                    to_bitField0_ |= 512;
                }
                result.outerTypeId_ = this.outerTypeId_;
                if ((from_bitField0_ & 2048) == 2048) {
                    to_bitField0_ |= 1024;
                }
                result.abbreviatedType_ = this.abbreviatedType_;
                if ((from_bitField0_ & 4096) == 4096) {
                    to_bitField0_ |= 2048;
                }
                result.abbreviatedTypeId_ = this.abbreviatedTypeId_;
                if ((from_bitField0_ & 8192) == 8192) {
                    to_bitField0_ |= 4096;
                }
                result.flags_ = this.flags_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(Type other) {
                if (other == Type.getDefaultInstance()) {
                    return this;
                }
                if (!other.argument_.isEmpty()) {
                    if (this.argument_.isEmpty()) {
                        this.argument_ = other.argument_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureArgumentIsMutable();
                        this.argument_.addAll(other.argument_);
                    }
                }
                if (other.hasNullable()) {
                    setNullable(other.getNullable());
                }
                if (other.hasFlexibleTypeCapabilitiesId()) {
                    setFlexibleTypeCapabilitiesId(other.getFlexibleTypeCapabilitiesId());
                }
                if (other.hasFlexibleUpperBound()) {
                    mergeFlexibleUpperBound(other.getFlexibleUpperBound());
                }
                if (other.hasFlexibleUpperBoundId()) {
                    setFlexibleUpperBoundId(other.getFlexibleUpperBoundId());
                }
                if (other.hasClassName()) {
                    setClassName(other.getClassName());
                }
                if (other.hasTypeParameter()) {
                    setTypeParameter(other.getTypeParameter());
                }
                if (other.hasTypeParameterName()) {
                    setTypeParameterName(other.getTypeParameterName());
                }
                if (other.hasTypeAliasName()) {
                    setTypeAliasName(other.getTypeAliasName());
                }
                if (other.hasOuterType()) {
                    mergeOuterType(other.getOuterType());
                }
                if (other.hasOuterTypeId()) {
                    setOuterTypeId(other.getOuterTypeId());
                }
                if (other.hasAbbreviatedType()) {
                    mergeAbbreviatedType(other.getAbbreviatedType());
                }
                if (other.hasAbbreviatedTypeId()) {
                    setAbbreviatedTypeId(other.getAbbreviatedTypeId());
                }
                if (other.hasFlags()) {
                    setFlags(other.getFlags());
                }
                mergeExtensionFields(other);
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getArgumentCount(); i++) {
                    if (!getArgument(i).isInitialized()) {
                        return false;
                    }
                }
                if (hasFlexibleUpperBound() && !getFlexibleUpperBound().isInitialized()) {
                    return false;
                }
                if (hasOuterType() && !getOuterType().isInitialized()) {
                    return false;
                }
                if ((hasAbbreviatedType() && !getAbbreviatedType().isInitialized()) || !extensionsAreInitialized()) {
                    return false;
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Type parsedMessage = null;
                try {
                    try {
                        parsedMessage = Type.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (Type) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            private void ensureArgumentIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.argument_ = new ArrayList(this.argument_);
                    this.bitField0_ |= 1;
                }
            }

            public int getArgumentCount() {
                return this.argument_.size();
            }

            public Argument getArgument(int index) {
                return this.argument_.get(index);
            }

            public Builder setNullable(boolean value) {
                this.bitField0_ |= 2;
                this.nullable_ = value;
                return this;
            }

            public Builder setFlexibleTypeCapabilitiesId(int value) {
                this.bitField0_ |= 4;
                this.flexibleTypeCapabilitiesId_ = value;
                return this;
            }

            public boolean hasFlexibleUpperBound() {
                return (this.bitField0_ & 8) == 8;
            }

            public Type getFlexibleUpperBound() {
                return this.flexibleUpperBound_;
            }

            public Builder mergeFlexibleUpperBound(Type value) {
                if ((this.bitField0_ & 8) == 8 && this.flexibleUpperBound_ != Type.getDefaultInstance()) {
                    this.flexibleUpperBound_ = Type.newBuilder(this.flexibleUpperBound_).mergeFrom(value).buildPartial();
                } else {
                    this.flexibleUpperBound_ = value;
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setFlexibleUpperBoundId(int value) {
                this.bitField0_ |= 16;
                this.flexibleUpperBoundId_ = value;
                return this;
            }

            public Builder setClassName(int value) {
                this.bitField0_ |= 32;
                this.className_ = value;
                return this;
            }

            public Builder setTypeParameter(int value) {
                this.bitField0_ |= 64;
                this.typeParameter_ = value;
                return this;
            }

            public Builder setTypeParameterName(int value) {
                this.bitField0_ |= 128;
                this.typeParameterName_ = value;
                return this;
            }

            public Builder setTypeAliasName(int value) {
                this.bitField0_ |= 256;
                this.typeAliasName_ = value;
                return this;
            }

            public boolean hasOuterType() {
                return (this.bitField0_ & 512) == 512;
            }

            public Type getOuterType() {
                return this.outerType_;
            }

            public Builder mergeOuterType(Type value) {
                if ((this.bitField0_ & 512) == 512 && this.outerType_ != Type.getDefaultInstance()) {
                    this.outerType_ = Type.newBuilder(this.outerType_).mergeFrom(value).buildPartial();
                } else {
                    this.outerType_ = value;
                }
                this.bitField0_ |= 512;
                return this;
            }

            public Builder setOuterTypeId(int value) {
                this.bitField0_ |= 1024;
                this.outerTypeId_ = value;
                return this;
            }

            public boolean hasAbbreviatedType() {
                return (this.bitField0_ & 2048) == 2048;
            }

            public Type getAbbreviatedType() {
                return this.abbreviatedType_;
            }

            public Builder mergeAbbreviatedType(Type value) {
                if ((this.bitField0_ & 2048) == 2048 && this.abbreviatedType_ != Type.getDefaultInstance()) {
                    this.abbreviatedType_ = Type.newBuilder(this.abbreviatedType_).mergeFrom(value).buildPartial();
                } else {
                    this.abbreviatedType_ = value;
                }
                this.bitField0_ |= 2048;
                return this;
            }

            public Builder setAbbreviatedTypeId(int value) {
                this.bitField0_ |= 4096;
                this.abbreviatedTypeId_ = value;
                return this;
            }

            public Builder setFlags(int value) {
                this.bitField0_ |= 8192;
                this.flags_ = value;
                return this;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$TypeParameter.class */
    public static final class TypeParameter extends GeneratedMessageLite.ExtendableMessage<TypeParameter> implements TypeParameterOrBuilder {
        private final ByteString unknownFields;
        private int bitField0_;
        private int id_;
        private int name_;
        private boolean reified_;
        private Variance variance_;
        private List<Type> upperBound_;
        private List<Integer> upperBoundId_;
        private int upperBoundIdMemoizedSerializedSize;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<TypeParameter> PARSER = new AbstractParser<TypeParameter>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public TypeParameter parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new TypeParameter(input, extensionRegistry, null);
            }
        };
        private static final TypeParameter defaultInstance = new TypeParameter(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        /* synthetic */ TypeParameter(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ TypeParameter(GeneratedMessageLite.ExtendableBuilder x0, AnonymousClass1 x1) {
            this((GeneratedMessageLite.ExtendableBuilder<TypeParameter, ?>) x0);
        }

        private TypeParameter(GeneratedMessageLite.ExtendableBuilder<TypeParameter, ?> builder) {
            super(builder);
            this.upperBoundIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private TypeParameter(boolean noInit) {
            this.upperBoundIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static TypeParameter getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public TypeParameter getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private TypeParameter(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.upperBoundIdMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                case 8:
                                    this.bitField0_ |= 1;
                                    this.id_ = input.readInt32();
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.name_ = input.readInt32();
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.reified_ = input.readBool();
                                case 32:
                                    int rawValue = input.readEnum();
                                    Variance value = Variance.valueOf(rawValue);
                                    if (value == null) {
                                        unknownFieldsCodedOutput.writeRawVarint32(tag);
                                        unknownFieldsCodedOutput.writeRawVarint32(rawValue);
                                    } else {
                                        this.bitField0_ |= 8;
                                        this.variance_ = value;
                                    }
                                case 42:
                                    if ((mutable_bitField0_ & 16) != 16) {
                                        this.upperBound_ = new ArrayList();
                                        mutable_bitField0_ |= 16;
                                    }
                                    this.upperBound_.add(input.readMessage(Type.PARSER, extensionRegistry));
                                case 48:
                                    if ((mutable_bitField0_ & 32) != 32) {
                                        this.upperBoundId_ = new ArrayList();
                                        mutable_bitField0_ |= 32;
                                    }
                                    this.upperBoundId_.add(Integer.valueOf(input.readInt32()));
                                case 50:
                                    int length = input.readRawVarint32();
                                    int limit = input.pushLimit(length);
                                    if ((mutable_bitField0_ & 32) != 32 && input.getBytesUntilLimit() > 0) {
                                        this.upperBoundId_ = new ArrayList();
                                        mutable_bitField0_ |= 32;
                                    }
                                    while (input.getBytesUntilLimit() > 0) {
                                        this.upperBoundId_.add(Integer.valueOf(input.readInt32()));
                                    }
                                    input.popLimit(limit);
                                    break;
                                default:
                                    if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                        done = true;
                                    }
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        }
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 16) == 16) {
                    this.upperBound_ = Collections.unmodifiableList(this.upperBound_);
                }
                if ((mutable_bitField0_ & 32) == 32) {
                    this.upperBoundId_ = Collections.unmodifiableList(this.upperBoundId_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 16) == 16) {
                    this.upperBound_ = Collections.unmodifiableList(this.upperBound_);
                }
                if ((mutable_bitField0_ & 32) == 32) {
                    this.upperBoundId_ = Collections.unmodifiableList(this.upperBoundId_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<TypeParameter> getParserForType() {
            return PARSER;
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$TypeParameter$Variance.class */
        public enum Variance implements Internal.EnumLite {
            IN(0, 0),
            OUT(1, 1),
            INV(2, 2);

            private static Internal.EnumLiteMap<Variance> internalValueMap = new Internal.EnumLiteMap<Variance>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Variance.1
                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                public /* bridge */ /* synthetic */ Internal.EnumLite findValueByNumber(int x0) {
                    return findValueByNumber(x0);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                public Variance findValueByNumber(int number) {
                    return Variance.valueOf(number);
                }
            };
            private final int value;

            static {
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            public static Variance valueOf(int value) {
                switch (value) {
                    case 0:
                        return IN;
                    case 1:
                        return OUT;
                    case 2:
                        return INV;
                    default:
                        return null;
                }
            }

            Variance(int index, int value) {
                this.value = value;
            }
        }

        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getId() {
            return this.id_;
        }

        public boolean hasName() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getName() {
            return this.name_;
        }

        public boolean hasReified() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean getReified() {
            return this.reified_;
        }

        public boolean hasVariance() {
            return (this.bitField0_ & 8) == 8;
        }

        public Variance getVariance() {
            return this.variance_;
        }

        public List<Type> getUpperBoundList() {
            return this.upperBound_;
        }

        public int getUpperBoundCount() {
            return this.upperBound_.size();
        }

        public Type getUpperBound(int index) {
            return this.upperBound_.get(index);
        }

        public List<Integer> getUpperBoundIdList() {
            return this.upperBoundId_;
        }

        private void initFields() {
            this.id_ = 0;
            this.name_ = 0;
            this.reified_ = false;
            this.variance_ = Variance.INV;
            this.upperBound_ = Collections.emptyList();
            this.upperBoundId_ = Collections.emptyList();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasId()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasName()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            for (int i = 0; i < getUpperBoundCount(); i++) {
                if (!getUpperBound(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            GeneratedMessageLite.ExtendableMessage<MessageType>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.name_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(3, this.reified_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeEnum(4, this.variance_.getNumber());
            }
            for (int i = 0; i < this.upperBound_.size(); i++) {
                output.writeMessage(5, this.upperBound_.get(i));
            }
            if (getUpperBoundIdList().size() > 0) {
                output.writeRawVarint32(50);
                output.writeRawVarint32(this.upperBoundIdMemoizedSerializedSize);
            }
            for (int i2 = 0; i2 < this.upperBoundId_.size(); i2++) {
                output.writeInt32NoTag(this.upperBoundId_.get(i2).intValue());
            }
            extensionWriterNewExtensionWriter.writeUntil(1000, output);
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.name_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBoolSize(3, this.reified_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeEnumSize(4, this.variance_.getNumber());
            }
            for (int i = 0; i < this.upperBound_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(5, this.upperBound_.get(i));
            }
            int dataSize = 0;
            for (int i2 = 0; i2 < this.upperBoundId_.size(); i2++) {
                dataSize += CodedOutputStream.computeInt32SizeNoTag(this.upperBoundId_.get(i2).intValue());
            }
            int size3 = size2 + dataSize;
            if (!getUpperBoundIdList().isEmpty()) {
                size3 = size3 + 1 + CodedOutputStream.computeInt32SizeNoTag(dataSize);
            }
            this.upperBoundIdMemoizedSerializedSize = dataSize;
            int size4 = size3 + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = size4;
            return size4;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(TypeParameter prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$TypeParameter$Builder.class */
        public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<TypeParameter, Builder> implements TypeParameterOrBuilder {
            private int bitField0_;
            private int id_;
            private int name_;
            private boolean reified_;
            private Variance variance_ = Variance.INV;
            private List<Type> upperBound_ = Collections.emptyList();
            private List<Integer> upperBoundId_ = Collections.emptyList();

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.ExtendableBuilder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((TypeParameter) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public TypeParameter getDefaultInstanceForType() {
                return TypeParameter.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public TypeParameter build() {
                TypeParameter result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public TypeParameter buildPartial() {
                TypeParameter result = new TypeParameter(this, (AnonymousClass1) null);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.id_ = this.id_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.reified_ = this.reified_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.variance_ = this.variance_;
                if ((this.bitField0_ & 16) == 16) {
                    this.upperBound_ = Collections.unmodifiableList(this.upperBound_);
                    this.bitField0_ &= -17;
                }
                result.upperBound_ = this.upperBound_;
                if ((this.bitField0_ & 32) == 32) {
                    this.upperBoundId_ = Collections.unmodifiableList(this.upperBoundId_);
                    this.bitField0_ &= -33;
                }
                result.upperBoundId_ = this.upperBoundId_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(TypeParameter other) {
                if (other == TypeParameter.getDefaultInstance()) {
                    return this;
                }
                if (other.hasId()) {
                    setId(other.getId());
                }
                if (other.hasName()) {
                    setName(other.getName());
                }
                if (other.hasReified()) {
                    setReified(other.getReified());
                }
                if (other.hasVariance()) {
                    setVariance(other.getVariance());
                }
                if (!other.upperBound_.isEmpty()) {
                    if (this.upperBound_.isEmpty()) {
                        this.upperBound_ = other.upperBound_;
                        this.bitField0_ &= -17;
                    } else {
                        ensureUpperBoundIsMutable();
                        this.upperBound_.addAll(other.upperBound_);
                    }
                }
                if (!other.upperBoundId_.isEmpty()) {
                    if (this.upperBoundId_.isEmpty()) {
                        this.upperBoundId_ = other.upperBoundId_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureUpperBoundIdIsMutable();
                        this.upperBoundId_.addAll(other.upperBoundId_);
                    }
                }
                mergeExtensionFields(other);
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasId() || !hasName()) {
                    return false;
                }
                for (int i = 0; i < getUpperBoundCount(); i++) {
                    if (!getUpperBound(i).isInitialized()) {
                        return false;
                    }
                }
                if (!extensionsAreInitialized()) {
                    return false;
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                TypeParameter parsedMessage = null;
                try {
                    try {
                        parsedMessage = TypeParameter.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (TypeParameter) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            public boolean hasId() {
                return (this.bitField0_ & 1) == 1;
            }

            public Builder setId(int value) {
                this.bitField0_ |= 1;
                this.id_ = value;
                return this;
            }

            public boolean hasName() {
                return (this.bitField0_ & 2) == 2;
            }

            public Builder setName(int value) {
                this.bitField0_ |= 2;
                this.name_ = value;
                return this;
            }

            public Builder setReified(boolean value) {
                this.bitField0_ |= 4;
                this.reified_ = value;
                return this;
            }

            public Builder setVariance(Variance value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.variance_ = value;
                return this;
            }

            private void ensureUpperBoundIsMutable() {
                if ((this.bitField0_ & 16) != 16) {
                    this.upperBound_ = new ArrayList(this.upperBound_);
                    this.bitField0_ |= 16;
                }
            }

            public int getUpperBoundCount() {
                return this.upperBound_.size();
            }

            public Type getUpperBound(int index) {
                return this.upperBound_.get(index);
            }

            private void ensureUpperBoundIdIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.upperBoundId_ = new ArrayList(this.upperBoundId_);
                    this.bitField0_ |= 32;
                }
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Class.class */
    public static final class Class extends GeneratedMessageLite.ExtendableMessage<Class> implements ClassOrBuilder {
        private final ByteString unknownFields;
        private int bitField0_;
        private int flags_;
        private int fqName_;
        private int companionObjectName_;
        private List<TypeParameter> typeParameter_;
        private List<Type> supertype_;
        private List<Integer> supertypeId_;
        private int supertypeIdMemoizedSerializedSize;
        private List<Integer> nestedClassName_;
        private int nestedClassNameMemoizedSerializedSize;
        private List<Constructor> constructor_;
        private List<Function> function_;
        private List<Property> property_;
        private List<TypeAlias> typeAlias_;
        private List<EnumEntry> enumEntry_;
        private List<Integer> sealedSubclassFqName_;
        private int sealedSubclassFqNameMemoizedSerializedSize;
        private int inlineClassUnderlyingPropertyName_;
        private Type inlineClassUnderlyingType_;
        private int inlineClassUnderlyingTypeId_;
        private TypeTable typeTable_;
        private List<Integer> versionRequirement_;
        private VersionRequirementTable versionRequirementTable_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<Class> PARSER = new AbstractParser<Class>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public Class parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Class(input, extensionRegistry, null);
            }
        };
        private static final Class defaultInstance = new Class(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        /* synthetic */ Class(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ Class(GeneratedMessageLite.ExtendableBuilder x0, AnonymousClass1 x1) {
            this((GeneratedMessageLite.ExtendableBuilder<Class, ?>) x0);
        }

        private Class(GeneratedMessageLite.ExtendableBuilder<Class, ?> builder) {
            super(builder);
            this.supertypeIdMemoizedSerializedSize = -1;
            this.nestedClassNameMemoizedSerializedSize = -1;
            this.sealedSubclassFqNameMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Class(boolean noInit) {
            this.supertypeIdMemoizedSerializedSize = -1;
            this.nestedClassNameMemoizedSerializedSize = -1;
            this.sealedSubclassFqNameMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Class getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public Class getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Class(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.supertypeIdMemoizedSerializedSize = -1;
            this.nestedClassNameMemoizedSerializedSize = -1;
            this.sealedSubclassFqNameMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 8:
                                this.bitField0_ |= 1;
                                this.flags_ = input.readInt32();
                            case 16:
                                if ((mutable_bitField0_ & 32) != 32) {
                                    this.supertypeId_ = new ArrayList();
                                    mutable_bitField0_ |= 32;
                                }
                                this.supertypeId_.add(Integer.valueOf(input.readInt32()));
                            case 18:
                                int length = input.readRawVarint32();
                                int limit = input.pushLimit(length);
                                if ((mutable_bitField0_ & 32) != 32 && input.getBytesUntilLimit() > 0) {
                                    this.supertypeId_ = new ArrayList();
                                    mutable_bitField0_ |= 32;
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.supertypeId_.add(Integer.valueOf(input.readInt32()));
                                }
                                input.popLimit(limit);
                                break;
                            case 24:
                                this.bitField0_ |= 2;
                                this.fqName_ = input.readInt32();
                            case 32:
                                this.bitField0_ |= 4;
                                this.companionObjectName_ = input.readInt32();
                            case 42:
                                if ((mutable_bitField0_ & 8) != 8) {
                                    this.typeParameter_ = new ArrayList();
                                    mutable_bitField0_ |= 8;
                                }
                                this.typeParameter_.add(input.readMessage(TypeParameter.PARSER, extensionRegistry));
                            case 50:
                                if ((mutable_bitField0_ & 16) != 16) {
                                    this.supertype_ = new ArrayList();
                                    mutable_bitField0_ |= 16;
                                }
                                this.supertype_.add(input.readMessage(Type.PARSER, extensionRegistry));
                            case 56:
                                if ((mutable_bitField0_ & 64) != 64) {
                                    this.nestedClassName_ = new ArrayList();
                                    mutable_bitField0_ |= 64;
                                }
                                this.nestedClassName_.add(Integer.valueOf(input.readInt32()));
                            case 58:
                                int length2 = input.readRawVarint32();
                                int limit2 = input.pushLimit(length2);
                                if ((mutable_bitField0_ & 64) != 64 && input.getBytesUntilLimit() > 0) {
                                    this.nestedClassName_ = new ArrayList();
                                    mutable_bitField0_ |= 64;
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.nestedClassName_.add(Integer.valueOf(input.readInt32()));
                                }
                                input.popLimit(limit2);
                                break;
                            case 66:
                                if ((mutable_bitField0_ & 128) != 128) {
                                    this.constructor_ = new ArrayList();
                                    mutable_bitField0_ |= 128;
                                }
                                this.constructor_.add(input.readMessage(Constructor.PARSER, extensionRegistry));
                            case 74:
                                if ((mutable_bitField0_ & 256) != 256) {
                                    this.function_ = new ArrayList();
                                    mutable_bitField0_ |= 256;
                                }
                                this.function_.add(input.readMessage(Function.PARSER, extensionRegistry));
                            case 82:
                                if ((mutable_bitField0_ & 512) != 512) {
                                    this.property_ = new ArrayList();
                                    mutable_bitField0_ |= 512;
                                }
                                this.property_.add(input.readMessage(Property.PARSER, extensionRegistry));
                            case 90:
                                if ((mutable_bitField0_ & 1024) != 1024) {
                                    this.typeAlias_ = new ArrayList();
                                    mutable_bitField0_ |= 1024;
                                }
                                this.typeAlias_.add(input.readMessage(TypeAlias.PARSER, extensionRegistry));
                            case 106:
                                if ((mutable_bitField0_ & 2048) != 2048) {
                                    this.enumEntry_ = new ArrayList();
                                    mutable_bitField0_ |= 2048;
                                }
                                this.enumEntry_.add(input.readMessage(EnumEntry.PARSER, extensionRegistry));
                            case 128:
                                if ((mutable_bitField0_ & 4096) != 4096) {
                                    this.sealedSubclassFqName_ = new ArrayList();
                                    mutable_bitField0_ |= 4096;
                                }
                                this.sealedSubclassFqName_.add(Integer.valueOf(input.readInt32()));
                            case 130:
                                int length3 = input.readRawVarint32();
                                int limit3 = input.pushLimit(length3);
                                if ((mutable_bitField0_ & 4096) != 4096 && input.getBytesUntilLimit() > 0) {
                                    this.sealedSubclassFqName_ = new ArrayList();
                                    mutable_bitField0_ |= 4096;
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.sealedSubclassFqName_.add(Integer.valueOf(input.readInt32()));
                                }
                                input.popLimit(limit3);
                                break;
                            case 136:
                                this.bitField0_ |= 8;
                                this.inlineClassUnderlyingPropertyName_ = input.readInt32();
                            case 146:
                                Type.Builder subBuilder = null;
                                subBuilder = (this.bitField0_ & 16) == 16 ? this.inlineClassUnderlyingType_.toBuilder() : subBuilder;
                                this.inlineClassUnderlyingType_ = (Type) input.readMessage(Type.PARSER, extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(this.inlineClassUnderlyingType_);
                                    this.inlineClassUnderlyingType_ = subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 16;
                            case 152:
                                this.bitField0_ |= 32;
                                this.inlineClassUnderlyingTypeId_ = input.readInt32();
                            case 242:
                                TypeTable.Builder subBuilder2 = null;
                                subBuilder2 = (this.bitField0_ & 64) == 64 ? this.typeTable_.toBuilder() : subBuilder2;
                                this.typeTable_ = (TypeTable) input.readMessage(TypeTable.PARSER, extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom(this.typeTable_);
                                    this.typeTable_ = subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 64;
                            case 248:
                                if ((mutable_bitField0_ & 131072) != 131072) {
                                    this.versionRequirement_ = new ArrayList();
                                    mutable_bitField0_ |= 131072;
                                }
                                this.versionRequirement_.add(Integer.valueOf(input.readInt32()));
                            case 250:
                                int length4 = input.readRawVarint32();
                                int limit4 = input.pushLimit(length4);
                                if ((mutable_bitField0_ & 131072) != 131072 && input.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_ = new ArrayList();
                                    mutable_bitField0_ |= 131072;
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_.add(Integer.valueOf(input.readInt32()));
                                }
                                input.popLimit(limit4);
                                break;
                            case WGL4Names.NUMBER_OF_MAC_GLYPHS /* 258 */:
                                VersionRequirementTable.Builder subBuilder3 = null;
                                subBuilder3 = (this.bitField0_ & 128) == 128 ? this.versionRequirementTable_.toBuilder() : subBuilder3;
                                this.versionRequirementTable_ = (VersionRequirementTable) input.readMessage(VersionRequirementTable.PARSER, extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom(this.versionRequirementTable_);
                                    this.versionRequirementTable_ = subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 128;
                            default:
                                if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 32) == 32) {
                    this.supertypeId_ = Collections.unmodifiableList(this.supertypeId_);
                }
                if ((mutable_bitField0_ & 8) == 8) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                }
                if ((mutable_bitField0_ & 16) == 16) {
                    this.supertype_ = Collections.unmodifiableList(this.supertype_);
                }
                if ((mutable_bitField0_ & 64) == 64) {
                    this.nestedClassName_ = Collections.unmodifiableList(this.nestedClassName_);
                }
                if ((mutable_bitField0_ & 128) == 128) {
                    this.constructor_ = Collections.unmodifiableList(this.constructor_);
                }
                if ((mutable_bitField0_ & 256) == 256) {
                    this.function_ = Collections.unmodifiableList(this.function_);
                }
                if ((mutable_bitField0_ & 512) == 512) {
                    this.property_ = Collections.unmodifiableList(this.property_);
                }
                if ((mutable_bitField0_ & 1024) == 1024) {
                    this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
                }
                if ((mutable_bitField0_ & 2048) == 2048) {
                    this.enumEntry_ = Collections.unmodifiableList(this.enumEntry_);
                }
                if ((mutable_bitField0_ & 4096) == 4096) {
                    this.sealedSubclassFqName_ = Collections.unmodifiableList(this.sealedSubclassFqName_);
                }
                if ((mutable_bitField0_ & 131072) == 131072) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 32) == 32) {
                    this.supertypeId_ = Collections.unmodifiableList(this.supertypeId_);
                }
                if ((mutable_bitField0_ & 8) == 8) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                }
                if ((mutable_bitField0_ & 16) == 16) {
                    this.supertype_ = Collections.unmodifiableList(this.supertype_);
                }
                if ((mutable_bitField0_ & 64) == 64) {
                    this.nestedClassName_ = Collections.unmodifiableList(this.nestedClassName_);
                }
                if ((mutable_bitField0_ & 128) == 128) {
                    this.constructor_ = Collections.unmodifiableList(this.constructor_);
                }
                if ((mutable_bitField0_ & 256) == 256) {
                    this.function_ = Collections.unmodifiableList(this.function_);
                }
                if ((mutable_bitField0_ & 512) == 512) {
                    this.property_ = Collections.unmodifiableList(this.property_);
                }
                if ((mutable_bitField0_ & 1024) == 1024) {
                    this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
                }
                if ((mutable_bitField0_ & 2048) == 2048) {
                    this.enumEntry_ = Collections.unmodifiableList(this.enumEntry_);
                }
                if ((mutable_bitField0_ & 4096) == 4096) {
                    this.sealedSubclassFqName_ = Collections.unmodifiableList(this.sealedSubclassFqName_);
                }
                if ((mutable_bitField0_ & 131072) == 131072) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<Class> getParserForType() {
            return PARSER;
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Class$Kind.class */
        public enum Kind implements Internal.EnumLite {
            CLASS(0, 0),
            INTERFACE(1, 1),
            ENUM_CLASS(2, 2),
            ENUM_ENTRY(3, 3),
            ANNOTATION_CLASS(4, 4),
            OBJECT(5, 5),
            COMPANION_OBJECT(6, 6);

            private static Internal.EnumLiteMap<Kind> internalValueMap = new Internal.EnumLiteMap<Kind>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Kind.1
                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                public /* bridge */ /* synthetic */ Internal.EnumLite findValueByNumber(int x0) {
                    return findValueByNumber(x0);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                public Kind findValueByNumber(int number) {
                    return Kind.valueOf(number);
                }
            };
            private final int value;

            static {
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            public static Kind valueOf(int value) {
                switch (value) {
                    case 0:
                        return CLASS;
                    case 1:
                        return INTERFACE;
                    case 2:
                        return ENUM_CLASS;
                    case 3:
                        return ENUM_ENTRY;
                    case 4:
                        return ANNOTATION_CLASS;
                    case 5:
                        return OBJECT;
                    case 6:
                        return COMPANION_OBJECT;
                    default:
                        return null;
                }
            }

            Kind(int index, int value) {
                this.value = value;
            }
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getFlags() {
            return this.flags_;
        }

        public boolean hasFqName() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getFqName() {
            return this.fqName_;
        }

        public boolean hasCompanionObjectName() {
            return (this.bitField0_ & 4) == 4;
        }

        public int getCompanionObjectName() {
            return this.companionObjectName_;
        }

        public List<TypeParameter> getTypeParameterList() {
            return this.typeParameter_;
        }

        public int getTypeParameterCount() {
            return this.typeParameter_.size();
        }

        public TypeParameter getTypeParameter(int index) {
            return this.typeParameter_.get(index);
        }

        public List<Type> getSupertypeList() {
            return this.supertype_;
        }

        public int getSupertypeCount() {
            return this.supertype_.size();
        }

        public Type getSupertype(int index) {
            return this.supertype_.get(index);
        }

        public List<Integer> getSupertypeIdList() {
            return this.supertypeId_;
        }

        public List<Integer> getNestedClassNameList() {
            return this.nestedClassName_;
        }

        public List<Constructor> getConstructorList() {
            return this.constructor_;
        }

        public int getConstructorCount() {
            return this.constructor_.size();
        }

        public Constructor getConstructor(int index) {
            return this.constructor_.get(index);
        }

        public List<Function> getFunctionList() {
            return this.function_;
        }

        public int getFunctionCount() {
            return this.function_.size();
        }

        public Function getFunction(int index) {
            return this.function_.get(index);
        }

        public List<Property> getPropertyList() {
            return this.property_;
        }

        public int getPropertyCount() {
            return this.property_.size();
        }

        public Property getProperty(int index) {
            return this.property_.get(index);
        }

        public List<TypeAlias> getTypeAliasList() {
            return this.typeAlias_;
        }

        public int getTypeAliasCount() {
            return this.typeAlias_.size();
        }

        public TypeAlias getTypeAlias(int index) {
            return this.typeAlias_.get(index);
        }

        public List<EnumEntry> getEnumEntryList() {
            return this.enumEntry_;
        }

        public int getEnumEntryCount() {
            return this.enumEntry_.size();
        }

        public EnumEntry getEnumEntry(int index) {
            return this.enumEntry_.get(index);
        }

        public List<Integer> getSealedSubclassFqNameList() {
            return this.sealedSubclassFqName_;
        }

        public boolean hasInlineClassUnderlyingPropertyName() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getInlineClassUnderlyingPropertyName() {
            return this.inlineClassUnderlyingPropertyName_;
        }

        public boolean hasInlineClassUnderlyingType() {
            return (this.bitField0_ & 16) == 16;
        }

        public Type getInlineClassUnderlyingType() {
            return this.inlineClassUnderlyingType_;
        }

        public boolean hasInlineClassUnderlyingTypeId() {
            return (this.bitField0_ & 32) == 32;
        }

        public int getInlineClassUnderlyingTypeId() {
            return this.inlineClassUnderlyingTypeId_;
        }

        public boolean hasTypeTable() {
            return (this.bitField0_ & 64) == 64;
        }

        public TypeTable getTypeTable() {
            return this.typeTable_;
        }

        public List<Integer> getVersionRequirementList() {
            return this.versionRequirement_;
        }

        public boolean hasVersionRequirementTable() {
            return (this.bitField0_ & 128) == 128;
        }

        public VersionRequirementTable getVersionRequirementTable() {
            return this.versionRequirementTable_;
        }

        private void initFields() {
            this.flags_ = 6;
            this.fqName_ = 0;
            this.companionObjectName_ = 0;
            this.typeParameter_ = Collections.emptyList();
            this.supertype_ = Collections.emptyList();
            this.supertypeId_ = Collections.emptyList();
            this.nestedClassName_ = Collections.emptyList();
            this.constructor_ = Collections.emptyList();
            this.function_ = Collections.emptyList();
            this.property_ = Collections.emptyList();
            this.typeAlias_ = Collections.emptyList();
            this.enumEntry_ = Collections.emptyList();
            this.sealedSubclassFqName_ = Collections.emptyList();
            this.inlineClassUnderlyingPropertyName_ = 0;
            this.inlineClassUnderlyingType_ = Type.getDefaultInstance();
            this.inlineClassUnderlyingTypeId_ = 0;
            this.typeTable_ = TypeTable.getDefaultInstance();
            this.versionRequirement_ = Collections.emptyList();
            this.versionRequirementTable_ = VersionRequirementTable.getDefaultInstance();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasFqName()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            for (int i = 0; i < getTypeParameterCount(); i++) {
                if (!getTypeParameter(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i2 = 0; i2 < getSupertypeCount(); i2++) {
                if (!getSupertype(i2).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i3 = 0; i3 < getConstructorCount(); i3++) {
                if (!getConstructor(i3).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i4 = 0; i4 < getFunctionCount(); i4++) {
                if (!getFunction(i4).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i5 = 0; i5 < getPropertyCount(); i5++) {
                if (!getProperty(i5).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i6 = 0; i6 < getTypeAliasCount(); i6++) {
                if (!getTypeAlias(i6).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i7 = 0; i7 < getEnumEntryCount(); i7++) {
                if (!getEnumEntry(i7).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (hasInlineClassUnderlyingType() && !getInlineClassUnderlyingType().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (hasTypeTable() && !getTypeTable().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            GeneratedMessageLite.ExtendableMessage<MessageType>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.flags_);
            }
            if (getSupertypeIdList().size() > 0) {
                output.writeRawVarint32(18);
                output.writeRawVarint32(this.supertypeIdMemoizedSerializedSize);
            }
            for (int i = 0; i < this.supertypeId_.size(); i++) {
                output.writeInt32NoTag(this.supertypeId_.get(i).intValue());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(3, this.fqName_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(4, this.companionObjectName_);
            }
            for (int i2 = 0; i2 < this.typeParameter_.size(); i2++) {
                output.writeMessage(5, this.typeParameter_.get(i2));
            }
            for (int i3 = 0; i3 < this.supertype_.size(); i3++) {
                output.writeMessage(6, this.supertype_.get(i3));
            }
            if (getNestedClassNameList().size() > 0) {
                output.writeRawVarint32(58);
                output.writeRawVarint32(this.nestedClassNameMemoizedSerializedSize);
            }
            for (int i4 = 0; i4 < this.nestedClassName_.size(); i4++) {
                output.writeInt32NoTag(this.nestedClassName_.get(i4).intValue());
            }
            for (int i5 = 0; i5 < this.constructor_.size(); i5++) {
                output.writeMessage(8, this.constructor_.get(i5));
            }
            for (int i6 = 0; i6 < this.function_.size(); i6++) {
                output.writeMessage(9, this.function_.get(i6));
            }
            for (int i7 = 0; i7 < this.property_.size(); i7++) {
                output.writeMessage(10, this.property_.get(i7));
            }
            for (int i8 = 0; i8 < this.typeAlias_.size(); i8++) {
                output.writeMessage(11, this.typeAlias_.get(i8));
            }
            for (int i9 = 0; i9 < this.enumEntry_.size(); i9++) {
                output.writeMessage(13, this.enumEntry_.get(i9));
            }
            if (getSealedSubclassFqNameList().size() > 0) {
                output.writeRawVarint32(130);
                output.writeRawVarint32(this.sealedSubclassFqNameMemoizedSerializedSize);
            }
            for (int i10 = 0; i10 < this.sealedSubclassFqName_.size(); i10++) {
                output.writeInt32NoTag(this.sealedSubclassFqName_.get(i10).intValue());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(17, this.inlineClassUnderlyingPropertyName_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeMessage(18, this.inlineClassUnderlyingType_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(19, this.inlineClassUnderlyingTypeId_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeMessage(30, this.typeTable_);
            }
            for (int i11 = 0; i11 < this.versionRequirement_.size(); i11++) {
                output.writeInt32(31, this.versionRequirement_.get(i11).intValue());
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeMessage(32, this.versionRequirementTable_);
            }
            extensionWriterNewExtensionWriter.writeUntil(19000, output);
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.flags_);
            }
            int dataSize = 0;
            for (int i = 0; i < this.supertypeId_.size(); i++) {
                dataSize += CodedOutputStream.computeInt32SizeNoTag(this.supertypeId_.get(i).intValue());
            }
            int size3 = size2 + dataSize;
            if (!getSupertypeIdList().isEmpty()) {
                size3 = size3 + 1 + CodedOutputStream.computeInt32SizeNoTag(dataSize);
            }
            this.supertypeIdMemoizedSerializedSize = dataSize;
            if ((this.bitField0_ & 2) == 2) {
                size3 += CodedOutputStream.computeInt32Size(3, this.fqName_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size3 += CodedOutputStream.computeInt32Size(4, this.companionObjectName_);
            }
            for (int i2 = 0; i2 < this.typeParameter_.size(); i2++) {
                size3 += CodedOutputStream.computeMessageSize(5, this.typeParameter_.get(i2));
            }
            for (int i3 = 0; i3 < this.supertype_.size(); i3++) {
                size3 += CodedOutputStream.computeMessageSize(6, this.supertype_.get(i3));
            }
            int dataSize2 = 0;
            for (int i4 = 0; i4 < this.nestedClassName_.size(); i4++) {
                dataSize2 += CodedOutputStream.computeInt32SizeNoTag(this.nestedClassName_.get(i4).intValue());
            }
            int size4 = size3 + dataSize2;
            if (!getNestedClassNameList().isEmpty()) {
                size4 = size4 + 1 + CodedOutputStream.computeInt32SizeNoTag(dataSize2);
            }
            this.nestedClassNameMemoizedSerializedSize = dataSize2;
            for (int i5 = 0; i5 < this.constructor_.size(); i5++) {
                size4 += CodedOutputStream.computeMessageSize(8, this.constructor_.get(i5));
            }
            for (int i6 = 0; i6 < this.function_.size(); i6++) {
                size4 += CodedOutputStream.computeMessageSize(9, this.function_.get(i6));
            }
            for (int i7 = 0; i7 < this.property_.size(); i7++) {
                size4 += CodedOutputStream.computeMessageSize(10, this.property_.get(i7));
            }
            for (int i8 = 0; i8 < this.typeAlias_.size(); i8++) {
                size4 += CodedOutputStream.computeMessageSize(11, this.typeAlias_.get(i8));
            }
            for (int i9 = 0; i9 < this.enumEntry_.size(); i9++) {
                size4 += CodedOutputStream.computeMessageSize(13, this.enumEntry_.get(i9));
            }
            int dataSize3 = 0;
            for (int i10 = 0; i10 < this.sealedSubclassFqName_.size(); i10++) {
                dataSize3 += CodedOutputStream.computeInt32SizeNoTag(this.sealedSubclassFqName_.get(i10).intValue());
            }
            int size5 = size4 + dataSize3;
            if (!getSealedSubclassFqNameList().isEmpty()) {
                size5 = size5 + 2 + CodedOutputStream.computeInt32SizeNoTag(dataSize3);
            }
            this.sealedSubclassFqNameMemoizedSerializedSize = dataSize3;
            if ((this.bitField0_ & 8) == 8) {
                size5 += CodedOutputStream.computeInt32Size(17, this.inlineClassUnderlyingPropertyName_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size5 += CodedOutputStream.computeMessageSize(18, this.inlineClassUnderlyingType_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size5 += CodedOutputStream.computeInt32Size(19, this.inlineClassUnderlyingTypeId_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size5 += CodedOutputStream.computeMessageSize(30, this.typeTable_);
            }
            int dataSize4 = 0;
            for (int i11 = 0; i11 < this.versionRequirement_.size(); i11++) {
                dataSize4 += CodedOutputStream.computeInt32SizeNoTag(this.versionRequirement_.get(i11).intValue());
            }
            int size6 = size5 + dataSize4 + (2 * getVersionRequirementList().size());
            if ((this.bitField0_ & 128) == 128) {
                size6 += CodedOutputStream.computeMessageSize(32, this.versionRequirementTable_);
            }
            int size7 = size6 + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = size7;
            return size7;
        }

        public static Class parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Class prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Class$Builder.class */
        public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<Class, Builder> implements ClassOrBuilder {
            private int bitField0_;
            private int fqName_;
            private int companionObjectName_;
            private int inlineClassUnderlyingPropertyName_;
            private int inlineClassUnderlyingTypeId_;
            private int flags_ = 6;
            private List<TypeParameter> typeParameter_ = Collections.emptyList();
            private List<Type> supertype_ = Collections.emptyList();
            private List<Integer> supertypeId_ = Collections.emptyList();
            private List<Integer> nestedClassName_ = Collections.emptyList();
            private List<Constructor> constructor_ = Collections.emptyList();
            private List<Function> function_ = Collections.emptyList();
            private List<Property> property_ = Collections.emptyList();
            private List<TypeAlias> typeAlias_ = Collections.emptyList();
            private List<EnumEntry> enumEntry_ = Collections.emptyList();
            private List<Integer> sealedSubclassFqName_ = Collections.emptyList();
            private Type inlineClassUnderlyingType_ = Type.getDefaultInstance();
            private TypeTable typeTable_ = TypeTable.getDefaultInstance();
            private List<Integer> versionRequirement_ = Collections.emptyList();
            private VersionRequirementTable versionRequirementTable_ = VersionRequirementTable.getDefaultInstance();

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.ExtendableBuilder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((Class) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public Class getDefaultInstanceForType() {
                return Class.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Class build() {
                Class result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public Class buildPartial() {
                Class result = new Class(this, (AnonymousClass1) null);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.flags_ = this.flags_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.fqName_ = this.fqName_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.companionObjectName_ = this.companionObjectName_;
                if ((this.bitField0_ & 8) == 8) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    this.bitField0_ &= -9;
                }
                result.typeParameter_ = this.typeParameter_;
                if ((this.bitField0_ & 16) == 16) {
                    this.supertype_ = Collections.unmodifiableList(this.supertype_);
                    this.bitField0_ &= -17;
                }
                result.supertype_ = this.supertype_;
                if ((this.bitField0_ & 32) == 32) {
                    this.supertypeId_ = Collections.unmodifiableList(this.supertypeId_);
                    this.bitField0_ &= -33;
                }
                result.supertypeId_ = this.supertypeId_;
                if ((this.bitField0_ & 64) == 64) {
                    this.nestedClassName_ = Collections.unmodifiableList(this.nestedClassName_);
                    this.bitField0_ &= -65;
                }
                result.nestedClassName_ = this.nestedClassName_;
                if ((this.bitField0_ & 128) == 128) {
                    this.constructor_ = Collections.unmodifiableList(this.constructor_);
                    this.bitField0_ &= -129;
                }
                result.constructor_ = this.constructor_;
                if ((this.bitField0_ & 256) == 256) {
                    this.function_ = Collections.unmodifiableList(this.function_);
                    this.bitField0_ &= -257;
                }
                result.function_ = this.function_;
                if ((this.bitField0_ & 512) == 512) {
                    this.property_ = Collections.unmodifiableList(this.property_);
                    this.bitField0_ &= -513;
                }
                result.property_ = this.property_;
                if ((this.bitField0_ & 1024) == 1024) {
                    this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
                    this.bitField0_ &= -1025;
                }
                result.typeAlias_ = this.typeAlias_;
                if ((this.bitField0_ & 2048) == 2048) {
                    this.enumEntry_ = Collections.unmodifiableList(this.enumEntry_);
                    this.bitField0_ &= -2049;
                }
                result.enumEntry_ = this.enumEntry_;
                if ((this.bitField0_ & 4096) == 4096) {
                    this.sealedSubclassFqName_ = Collections.unmodifiableList(this.sealedSubclassFqName_);
                    this.bitField0_ &= -4097;
                }
                result.sealedSubclassFqName_ = this.sealedSubclassFqName_;
                if ((from_bitField0_ & 8192) == 8192) {
                    to_bitField0_ |= 8;
                }
                result.inlineClassUnderlyingPropertyName_ = this.inlineClassUnderlyingPropertyName_;
                if ((from_bitField0_ & 16384) == 16384) {
                    to_bitField0_ |= 16;
                }
                result.inlineClassUnderlyingType_ = this.inlineClassUnderlyingType_;
                if ((from_bitField0_ & 32768) == 32768) {
                    to_bitField0_ |= 32;
                }
                result.inlineClassUnderlyingTypeId_ = this.inlineClassUnderlyingTypeId_;
                if ((from_bitField0_ & 65536) == 65536) {
                    to_bitField0_ |= 64;
                }
                result.typeTable_ = this.typeTable_;
                if ((this.bitField0_ & 131072) == 131072) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    this.bitField0_ &= -131073;
                }
                result.versionRequirement_ = this.versionRequirement_;
                if ((from_bitField0_ & 262144) == 262144) {
                    to_bitField0_ |= 128;
                }
                result.versionRequirementTable_ = this.versionRequirementTable_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(Class other) {
                if (other == Class.getDefaultInstance()) {
                    return this;
                }
                if (other.hasFlags()) {
                    setFlags(other.getFlags());
                }
                if (other.hasFqName()) {
                    setFqName(other.getFqName());
                }
                if (other.hasCompanionObjectName()) {
                    setCompanionObjectName(other.getCompanionObjectName());
                }
                if (!other.typeParameter_.isEmpty()) {
                    if (this.typeParameter_.isEmpty()) {
                        this.typeParameter_ = other.typeParameter_;
                        this.bitField0_ &= -9;
                    } else {
                        ensureTypeParameterIsMutable();
                        this.typeParameter_.addAll(other.typeParameter_);
                    }
                }
                if (!other.supertype_.isEmpty()) {
                    if (this.supertype_.isEmpty()) {
                        this.supertype_ = other.supertype_;
                        this.bitField0_ &= -17;
                    } else {
                        ensureSupertypeIsMutable();
                        this.supertype_.addAll(other.supertype_);
                    }
                }
                if (!other.supertypeId_.isEmpty()) {
                    if (this.supertypeId_.isEmpty()) {
                        this.supertypeId_ = other.supertypeId_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureSupertypeIdIsMutable();
                        this.supertypeId_.addAll(other.supertypeId_);
                    }
                }
                if (!other.nestedClassName_.isEmpty()) {
                    if (this.nestedClassName_.isEmpty()) {
                        this.nestedClassName_ = other.nestedClassName_;
                        this.bitField0_ &= -65;
                    } else {
                        ensureNestedClassNameIsMutable();
                        this.nestedClassName_.addAll(other.nestedClassName_);
                    }
                }
                if (!other.constructor_.isEmpty()) {
                    if (this.constructor_.isEmpty()) {
                        this.constructor_ = other.constructor_;
                        this.bitField0_ &= -129;
                    } else {
                        ensureConstructorIsMutable();
                        this.constructor_.addAll(other.constructor_);
                    }
                }
                if (!other.function_.isEmpty()) {
                    if (this.function_.isEmpty()) {
                        this.function_ = other.function_;
                        this.bitField0_ &= -257;
                    } else {
                        ensureFunctionIsMutable();
                        this.function_.addAll(other.function_);
                    }
                }
                if (!other.property_.isEmpty()) {
                    if (this.property_.isEmpty()) {
                        this.property_ = other.property_;
                        this.bitField0_ &= -513;
                    } else {
                        ensurePropertyIsMutable();
                        this.property_.addAll(other.property_);
                    }
                }
                if (!other.typeAlias_.isEmpty()) {
                    if (this.typeAlias_.isEmpty()) {
                        this.typeAlias_ = other.typeAlias_;
                        this.bitField0_ &= -1025;
                    } else {
                        ensureTypeAliasIsMutable();
                        this.typeAlias_.addAll(other.typeAlias_);
                    }
                }
                if (!other.enumEntry_.isEmpty()) {
                    if (this.enumEntry_.isEmpty()) {
                        this.enumEntry_ = other.enumEntry_;
                        this.bitField0_ &= -2049;
                    } else {
                        ensureEnumEntryIsMutable();
                        this.enumEntry_.addAll(other.enumEntry_);
                    }
                }
                if (!other.sealedSubclassFqName_.isEmpty()) {
                    if (this.sealedSubclassFqName_.isEmpty()) {
                        this.sealedSubclassFqName_ = other.sealedSubclassFqName_;
                        this.bitField0_ &= -4097;
                    } else {
                        ensureSealedSubclassFqNameIsMutable();
                        this.sealedSubclassFqName_.addAll(other.sealedSubclassFqName_);
                    }
                }
                if (other.hasInlineClassUnderlyingPropertyName()) {
                    setInlineClassUnderlyingPropertyName(other.getInlineClassUnderlyingPropertyName());
                }
                if (other.hasInlineClassUnderlyingType()) {
                    mergeInlineClassUnderlyingType(other.getInlineClassUnderlyingType());
                }
                if (other.hasInlineClassUnderlyingTypeId()) {
                    setInlineClassUnderlyingTypeId(other.getInlineClassUnderlyingTypeId());
                }
                if (other.hasTypeTable()) {
                    mergeTypeTable(other.getTypeTable());
                }
                if (!other.versionRequirement_.isEmpty()) {
                    if (this.versionRequirement_.isEmpty()) {
                        this.versionRequirement_ = other.versionRequirement_;
                        this.bitField0_ &= -131073;
                    } else {
                        ensureVersionRequirementIsMutable();
                        this.versionRequirement_.addAll(other.versionRequirement_);
                    }
                }
                if (other.hasVersionRequirementTable()) {
                    mergeVersionRequirementTable(other.getVersionRequirementTable());
                }
                mergeExtensionFields(other);
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasFqName()) {
                    return false;
                }
                for (int i = 0; i < getTypeParameterCount(); i++) {
                    if (!getTypeParameter(i).isInitialized()) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getSupertypeCount(); i2++) {
                    if (!getSupertype(i2).isInitialized()) {
                        return false;
                    }
                }
                for (int i3 = 0; i3 < getConstructorCount(); i3++) {
                    if (!getConstructor(i3).isInitialized()) {
                        return false;
                    }
                }
                for (int i4 = 0; i4 < getFunctionCount(); i4++) {
                    if (!getFunction(i4).isInitialized()) {
                        return false;
                    }
                }
                for (int i5 = 0; i5 < getPropertyCount(); i5++) {
                    if (!getProperty(i5).isInitialized()) {
                        return false;
                    }
                }
                for (int i6 = 0; i6 < getTypeAliasCount(); i6++) {
                    if (!getTypeAlias(i6).isInitialized()) {
                        return false;
                    }
                }
                for (int i7 = 0; i7 < getEnumEntryCount(); i7++) {
                    if (!getEnumEntry(i7).isInitialized()) {
                        return false;
                    }
                }
                if (hasInlineClassUnderlyingType() && !getInlineClassUnderlyingType().isInitialized()) {
                    return false;
                }
                if ((hasTypeTable() && !getTypeTable().isInitialized()) || !extensionsAreInitialized()) {
                    return false;
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Class parsedMessage = null;
                try {
                    try {
                        parsedMessage = Class.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (Class) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            public Builder setFlags(int value) {
                this.bitField0_ |= 1;
                this.flags_ = value;
                return this;
            }

            public boolean hasFqName() {
                return (this.bitField0_ & 2) == 2;
            }

            public Builder setFqName(int value) {
                this.bitField0_ |= 2;
                this.fqName_ = value;
                return this;
            }

            public Builder setCompanionObjectName(int value) {
                this.bitField0_ |= 4;
                this.companionObjectName_ = value;
                return this;
            }

            private void ensureTypeParameterIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.typeParameter_ = new ArrayList(this.typeParameter_);
                    this.bitField0_ |= 8;
                }
            }

            public int getTypeParameterCount() {
                return this.typeParameter_.size();
            }

            public TypeParameter getTypeParameter(int index) {
                return this.typeParameter_.get(index);
            }

            private void ensureSupertypeIsMutable() {
                if ((this.bitField0_ & 16) != 16) {
                    this.supertype_ = new ArrayList(this.supertype_);
                    this.bitField0_ |= 16;
                }
            }

            public int getSupertypeCount() {
                return this.supertype_.size();
            }

            public Type getSupertype(int index) {
                return this.supertype_.get(index);
            }

            private void ensureSupertypeIdIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.supertypeId_ = new ArrayList(this.supertypeId_);
                    this.bitField0_ |= 32;
                }
            }

            private void ensureNestedClassNameIsMutable() {
                if ((this.bitField0_ & 64) != 64) {
                    this.nestedClassName_ = new ArrayList(this.nestedClassName_);
                    this.bitField0_ |= 64;
                }
            }

            private void ensureConstructorIsMutable() {
                if ((this.bitField0_ & 128) != 128) {
                    this.constructor_ = new ArrayList(this.constructor_);
                    this.bitField0_ |= 128;
                }
            }

            public int getConstructorCount() {
                return this.constructor_.size();
            }

            public Constructor getConstructor(int index) {
                return this.constructor_.get(index);
            }

            private void ensureFunctionIsMutable() {
                if ((this.bitField0_ & 256) != 256) {
                    this.function_ = new ArrayList(this.function_);
                    this.bitField0_ |= 256;
                }
            }

            public int getFunctionCount() {
                return this.function_.size();
            }

            public Function getFunction(int index) {
                return this.function_.get(index);
            }

            private void ensurePropertyIsMutable() {
                if ((this.bitField0_ & 512) != 512) {
                    this.property_ = new ArrayList(this.property_);
                    this.bitField0_ |= 512;
                }
            }

            public int getPropertyCount() {
                return this.property_.size();
            }

            public Property getProperty(int index) {
                return this.property_.get(index);
            }

            private void ensureTypeAliasIsMutable() {
                if ((this.bitField0_ & 1024) != 1024) {
                    this.typeAlias_ = new ArrayList(this.typeAlias_);
                    this.bitField0_ |= 1024;
                }
            }

            public int getTypeAliasCount() {
                return this.typeAlias_.size();
            }

            public TypeAlias getTypeAlias(int index) {
                return this.typeAlias_.get(index);
            }

            private void ensureEnumEntryIsMutable() {
                if ((this.bitField0_ & 2048) != 2048) {
                    this.enumEntry_ = new ArrayList(this.enumEntry_);
                    this.bitField0_ |= 2048;
                }
            }

            public int getEnumEntryCount() {
                return this.enumEntry_.size();
            }

            public EnumEntry getEnumEntry(int index) {
                return this.enumEntry_.get(index);
            }

            private void ensureSealedSubclassFqNameIsMutable() {
                if ((this.bitField0_ & 4096) != 4096) {
                    this.sealedSubclassFqName_ = new ArrayList(this.sealedSubclassFqName_);
                    this.bitField0_ |= 4096;
                }
            }

            public Builder setInlineClassUnderlyingPropertyName(int value) {
                this.bitField0_ |= 8192;
                this.inlineClassUnderlyingPropertyName_ = value;
                return this;
            }

            public boolean hasInlineClassUnderlyingType() {
                return (this.bitField0_ & 16384) == 16384;
            }

            public Type getInlineClassUnderlyingType() {
                return this.inlineClassUnderlyingType_;
            }

            public Builder mergeInlineClassUnderlyingType(Type value) {
                if ((this.bitField0_ & 16384) == 16384 && this.inlineClassUnderlyingType_ != Type.getDefaultInstance()) {
                    this.inlineClassUnderlyingType_ = Type.newBuilder(this.inlineClassUnderlyingType_).mergeFrom(value).buildPartial();
                } else {
                    this.inlineClassUnderlyingType_ = value;
                }
                this.bitField0_ |= 16384;
                return this;
            }

            public Builder setInlineClassUnderlyingTypeId(int value) {
                this.bitField0_ |= 32768;
                this.inlineClassUnderlyingTypeId_ = value;
                return this;
            }

            public boolean hasTypeTable() {
                return (this.bitField0_ & 65536) == 65536;
            }

            public TypeTable getTypeTable() {
                return this.typeTable_;
            }

            public Builder mergeTypeTable(TypeTable value) {
                if ((this.bitField0_ & 65536) == 65536 && this.typeTable_ != TypeTable.getDefaultInstance()) {
                    this.typeTable_ = TypeTable.newBuilder(this.typeTable_).mergeFrom(value).buildPartial();
                } else {
                    this.typeTable_ = value;
                }
                this.bitField0_ |= 65536;
                return this;
            }

            private void ensureVersionRequirementIsMutable() {
                if ((this.bitField0_ & 131072) != 131072) {
                    this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                    this.bitField0_ |= 131072;
                }
            }

            public Builder mergeVersionRequirementTable(VersionRequirementTable value) {
                if ((this.bitField0_ & 262144) == 262144 && this.versionRequirementTable_ != VersionRequirementTable.getDefaultInstance()) {
                    this.versionRequirementTable_ = VersionRequirementTable.newBuilder(this.versionRequirementTable_).mergeFrom(value).buildPartial();
                } else {
                    this.versionRequirementTable_ = value;
                }
                this.bitField0_ |= 262144;
                return this;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Package.class */
    public static final class Package extends GeneratedMessageLite.ExtendableMessage<Package> implements PackageOrBuilder {
        private final ByteString unknownFields;
        private int bitField0_;
        private List<Function> function_;
        private List<Property> property_;
        private List<TypeAlias> typeAlias_;
        private TypeTable typeTable_;
        private VersionRequirementTable versionRequirementTable_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<Package> PARSER = new AbstractParser<Package>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public Package parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Package(input, extensionRegistry, null);
            }
        };
        private static final Package defaultInstance = new Package(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        /* synthetic */ Package(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ Package(GeneratedMessageLite.ExtendableBuilder x0, AnonymousClass1 x1) {
            this((GeneratedMessageLite.ExtendableBuilder<Package, ?>) x0);
        }

        private Package(GeneratedMessageLite.ExtendableBuilder<Package, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Package(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Package getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public Package getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Package(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 26:
                                if ((mutable_bitField0_ & 1) != 1) {
                                    this.function_ = new ArrayList();
                                    mutable_bitField0_ |= 1;
                                }
                                this.function_.add(input.readMessage(Function.PARSER, extensionRegistry));
                            case 34:
                                if ((mutable_bitField0_ & 2) != 2) {
                                    this.property_ = new ArrayList();
                                    mutable_bitField0_ |= 2;
                                }
                                this.property_.add(input.readMessage(Property.PARSER, extensionRegistry));
                            case 42:
                                if ((mutable_bitField0_ & 4) != 4) {
                                    this.typeAlias_ = new ArrayList();
                                    mutable_bitField0_ |= 4;
                                }
                                this.typeAlias_.add(input.readMessage(TypeAlias.PARSER, extensionRegistry));
                            case 242:
                                TypeTable.Builder subBuilder = null;
                                subBuilder = (this.bitField0_ & 1) == 1 ? this.typeTable_.toBuilder() : subBuilder;
                                this.typeTable_ = (TypeTable) input.readMessage(TypeTable.PARSER, extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(this.typeTable_);
                                    this.typeTable_ = subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            case WGL4Names.NUMBER_OF_MAC_GLYPHS /* 258 */:
                                VersionRequirementTable.Builder subBuilder2 = null;
                                subBuilder2 = (this.bitField0_ & 2) == 2 ? this.versionRequirementTable_.toBuilder() : subBuilder2;
                                this.versionRequirementTable_ = (VersionRequirementTable) input.readMessage(VersionRequirementTable.PARSER, extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom(this.versionRequirementTable_);
                                    this.versionRequirementTable_ = subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            default:
                                if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 1) == 1) {
                    this.function_ = Collections.unmodifiableList(this.function_);
                }
                if ((mutable_bitField0_ & 2) == 2) {
                    this.property_ = Collections.unmodifiableList(this.property_);
                }
                if ((mutable_bitField0_ & 4) == 4) {
                    this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 1) == 1) {
                    this.function_ = Collections.unmodifiableList(this.function_);
                }
                if ((mutable_bitField0_ & 2) == 2) {
                    this.property_ = Collections.unmodifiableList(this.property_);
                }
                if ((mutable_bitField0_ & 4) == 4) {
                    this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<Package> getParserForType() {
            return PARSER;
        }

        public List<Function> getFunctionList() {
            return this.function_;
        }

        public int getFunctionCount() {
            return this.function_.size();
        }

        public Function getFunction(int index) {
            return this.function_.get(index);
        }

        public List<Property> getPropertyList() {
            return this.property_;
        }

        public int getPropertyCount() {
            return this.property_.size();
        }

        public Property getProperty(int index) {
            return this.property_.get(index);
        }

        public List<TypeAlias> getTypeAliasList() {
            return this.typeAlias_;
        }

        public int getTypeAliasCount() {
            return this.typeAlias_.size();
        }

        public TypeAlias getTypeAlias(int index) {
            return this.typeAlias_.get(index);
        }

        public boolean hasTypeTable() {
            return (this.bitField0_ & 1) == 1;
        }

        public TypeTable getTypeTable() {
            return this.typeTable_;
        }

        public boolean hasVersionRequirementTable() {
            return (this.bitField0_ & 2) == 2;
        }

        public VersionRequirementTable getVersionRequirementTable() {
            return this.versionRequirementTable_;
        }

        private void initFields() {
            this.function_ = Collections.emptyList();
            this.property_ = Collections.emptyList();
            this.typeAlias_ = Collections.emptyList();
            this.typeTable_ = TypeTable.getDefaultInstance();
            this.versionRequirementTable_ = VersionRequirementTable.getDefaultInstance();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < getFunctionCount(); i++) {
                if (!getFunction(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i2 = 0; i2 < getPropertyCount(); i2++) {
                if (!getProperty(i2).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i3 = 0; i3 < getTypeAliasCount(); i3++) {
                if (!getTypeAlias(i3).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (hasTypeTable() && !getTypeTable().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            GeneratedMessageLite.ExtendableMessage<MessageType>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            for (int i = 0; i < this.function_.size(); i++) {
                output.writeMessage(3, this.function_.get(i));
            }
            for (int i2 = 0; i2 < this.property_.size(); i2++) {
                output.writeMessage(4, this.property_.get(i2));
            }
            for (int i3 = 0; i3 < this.typeAlias_.size(); i3++) {
                output.writeMessage(5, this.typeAlias_.get(i3));
            }
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(30, this.typeTable_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(32, this.versionRequirementTable_);
            }
            extensionWriterNewExtensionWriter.writeUntil(200, output);
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            for (int i = 0; i < this.function_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(3, this.function_.get(i));
            }
            for (int i2 = 0; i2 < this.property_.size(); i2++) {
                size2 += CodedOutputStream.computeMessageSize(4, this.property_.get(i2));
            }
            for (int i3 = 0; i3 < this.typeAlias_.size(); i3++) {
                size2 += CodedOutputStream.computeMessageSize(5, this.typeAlias_.get(i3));
            }
            if ((this.bitField0_ & 1) == 1) {
                size2 += CodedOutputStream.computeMessageSize(30, this.typeTable_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(32, this.versionRequirementTable_);
            }
            int size3 = size2 + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Package parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Package prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Package$Builder.class */
        public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<Package, Builder> implements PackageOrBuilder {
            private int bitField0_;
            private List<Function> function_ = Collections.emptyList();
            private List<Property> property_ = Collections.emptyList();
            private List<TypeAlias> typeAlias_ = Collections.emptyList();
            private TypeTable typeTable_ = TypeTable.getDefaultInstance();
            private VersionRequirementTable versionRequirementTable_ = VersionRequirementTable.getDefaultInstance();

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.ExtendableBuilder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((Package) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public Package getDefaultInstanceForType() {
                return Package.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Package build() {
                Package result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public Package buildPartial() {
                Package result = new Package(this, (AnonymousClass1) null);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((this.bitField0_ & 1) == 1) {
                    this.function_ = Collections.unmodifiableList(this.function_);
                    this.bitField0_ &= -2;
                }
                result.function_ = this.function_;
                if ((this.bitField0_ & 2) == 2) {
                    this.property_ = Collections.unmodifiableList(this.property_);
                    this.bitField0_ &= -3;
                }
                result.property_ = this.property_;
                if ((this.bitField0_ & 4) == 4) {
                    this.typeAlias_ = Collections.unmodifiableList(this.typeAlias_);
                    this.bitField0_ &= -5;
                }
                result.typeAlias_ = this.typeAlias_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ = 0 | 1;
                }
                result.typeTable_ = this.typeTable_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 2;
                }
                result.versionRequirementTable_ = this.versionRequirementTable_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(Package other) {
                if (other == Package.getDefaultInstance()) {
                    return this;
                }
                if (!other.function_.isEmpty()) {
                    if (this.function_.isEmpty()) {
                        this.function_ = other.function_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureFunctionIsMutable();
                        this.function_.addAll(other.function_);
                    }
                }
                if (!other.property_.isEmpty()) {
                    if (this.property_.isEmpty()) {
                        this.property_ = other.property_;
                        this.bitField0_ &= -3;
                    } else {
                        ensurePropertyIsMutable();
                        this.property_.addAll(other.property_);
                    }
                }
                if (!other.typeAlias_.isEmpty()) {
                    if (this.typeAlias_.isEmpty()) {
                        this.typeAlias_ = other.typeAlias_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureTypeAliasIsMutable();
                        this.typeAlias_.addAll(other.typeAlias_);
                    }
                }
                if (other.hasTypeTable()) {
                    mergeTypeTable(other.getTypeTable());
                }
                if (other.hasVersionRequirementTable()) {
                    mergeVersionRequirementTable(other.getVersionRequirementTable());
                }
                mergeExtensionFields(other);
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getFunctionCount(); i++) {
                    if (!getFunction(i).isInitialized()) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getPropertyCount(); i2++) {
                    if (!getProperty(i2).isInitialized()) {
                        return false;
                    }
                }
                for (int i3 = 0; i3 < getTypeAliasCount(); i3++) {
                    if (!getTypeAlias(i3).isInitialized()) {
                        return false;
                    }
                }
                if ((hasTypeTable() && !getTypeTable().isInitialized()) || !extensionsAreInitialized()) {
                    return false;
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Package parsedMessage = null;
                try {
                    try {
                        parsedMessage = Package.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (Package) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            private void ensureFunctionIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.function_ = new ArrayList(this.function_);
                    this.bitField0_ |= 1;
                }
            }

            public int getFunctionCount() {
                return this.function_.size();
            }

            public Function getFunction(int index) {
                return this.function_.get(index);
            }

            private void ensurePropertyIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.property_ = new ArrayList(this.property_);
                    this.bitField0_ |= 2;
                }
            }

            public int getPropertyCount() {
                return this.property_.size();
            }

            public Property getProperty(int index) {
                return this.property_.get(index);
            }

            private void ensureTypeAliasIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.typeAlias_ = new ArrayList(this.typeAlias_);
                    this.bitField0_ |= 4;
                }
            }

            public int getTypeAliasCount() {
                return this.typeAlias_.size();
            }

            public TypeAlias getTypeAlias(int index) {
                return this.typeAlias_.get(index);
            }

            public boolean hasTypeTable() {
                return (this.bitField0_ & 8) == 8;
            }

            public TypeTable getTypeTable() {
                return this.typeTable_;
            }

            public Builder mergeTypeTable(TypeTable value) {
                if ((this.bitField0_ & 8) == 8 && this.typeTable_ != TypeTable.getDefaultInstance()) {
                    this.typeTable_ = TypeTable.newBuilder(this.typeTable_).mergeFrom(value).buildPartial();
                } else {
                    this.typeTable_ = value;
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder mergeVersionRequirementTable(VersionRequirementTable value) {
                if ((this.bitField0_ & 16) == 16 && this.versionRequirementTable_ != VersionRequirementTable.getDefaultInstance()) {
                    this.versionRequirementTable_ = VersionRequirementTable.newBuilder(this.versionRequirementTable_).mergeFrom(value).buildPartial();
                } else {
                    this.versionRequirementTable_ = value;
                }
                this.bitField0_ |= 16;
                return this;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$TypeTable.class */
    public static final class TypeTable extends GeneratedMessageLite implements TypeTableOrBuilder {
        private final ByteString unknownFields;
        private int bitField0_;
        private List<Type> type_;
        private int firstNullable_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<TypeTable> PARSER = new AbstractParser<TypeTable>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public TypeTable parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new TypeTable(input, extensionRegistry, null);
            }
        };
        private static final TypeTable defaultInstance = new TypeTable(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        /* synthetic */ TypeTable(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ TypeTable(GeneratedMessageLite.Builder x0, AnonymousClass1 x1) {
            this(x0);
        }

        private TypeTable(GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private TypeTable(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static TypeTable getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public TypeTable getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private TypeTable(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 10:
                                if ((mutable_bitField0_ & 1) != 1) {
                                    this.type_ = new ArrayList();
                                    mutable_bitField0_ |= 1;
                                }
                                this.type_.add(input.readMessage(Type.PARSER, extensionRegistry));
                            case 16:
                                this.bitField0_ |= 1;
                                this.firstNullable_ = input.readInt32();
                            default:
                                if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 1) == 1) {
                    this.type_ = Collections.unmodifiableList(this.type_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 1) == 1) {
                    this.type_ = Collections.unmodifiableList(this.type_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<TypeTable> getParserForType() {
            return PARSER;
        }

        public List<Type> getTypeList() {
            return this.type_;
        }

        public int getTypeCount() {
            return this.type_.size();
        }

        public Type getType(int index) {
            return this.type_.get(index);
        }

        public boolean hasFirstNullable() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getFirstNullable() {
            return this.firstNullable_;
        }

        private void initFields() {
            this.type_ = Collections.emptyList();
            this.firstNullable_ = -1;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < getTypeCount(); i++) {
                if (!getType(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            for (int i = 0; i < this.type_.size(); i++) {
                output.writeMessage(1, this.type_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(2, this.firstNullable_);
            }
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            for (int i = 0; i < this.type_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.type_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                size2 += CodedOutputStream.computeInt32Size(2, this.firstNullable_);
            }
            int size3 = size2 + this.unknownFields.size();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(TypeTable prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$TypeTable$Builder.class */
        public static final class Builder extends GeneratedMessageLite.Builder<TypeTable, Builder> implements TypeTableOrBuilder {
            private int bitField0_;
            private List<Type> type_ = Collections.emptyList();
            private int firstNullable_ = -1;

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((TypeTable) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public TypeTable getDefaultInstanceForType() {
                return TypeTable.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public TypeTable build() {
                TypeTable result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public TypeTable buildPartial() {
                TypeTable result = new TypeTable(this, (AnonymousClass1) null);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((this.bitField0_ & 1) == 1) {
                    this.type_ = Collections.unmodifiableList(this.type_);
                    this.bitField0_ &= -2;
                }
                result.type_ = this.type_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ = 0 | 1;
                }
                result.firstNullable_ = this.firstNullable_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(TypeTable other) {
                if (other == TypeTable.getDefaultInstance()) {
                    return this;
                }
                if (!other.type_.isEmpty()) {
                    if (this.type_.isEmpty()) {
                        this.type_ = other.type_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureTypeIsMutable();
                        this.type_.addAll(other.type_);
                    }
                }
                if (other.hasFirstNullable()) {
                    setFirstNullable(other.getFirstNullable());
                }
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getTypeCount(); i++) {
                    if (!getType(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                TypeTable parsedMessage = null;
                try {
                    try {
                        parsedMessage = TypeTable.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (TypeTable) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            private void ensureTypeIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.type_ = new ArrayList(this.type_);
                    this.bitField0_ |= 1;
                }
            }

            public int getTypeCount() {
                return this.type_.size();
            }

            public Type getType(int index) {
                return this.type_.get(index);
            }

            public Builder setFirstNullable(int value) {
                this.bitField0_ |= 2;
                this.firstNullable_ = value;
                return this;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Constructor.class */
    public static final class Constructor extends GeneratedMessageLite.ExtendableMessage<Constructor> implements ConstructorOrBuilder {
        private final ByteString unknownFields;
        private int bitField0_;
        private int flags_;
        private List<ValueParameter> valueParameter_;
        private List<Integer> versionRequirement_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<Constructor> PARSER = new AbstractParser<Constructor>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public Constructor parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Constructor(input, extensionRegistry, null);
            }
        };
        private static final Constructor defaultInstance = new Constructor(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        /* synthetic */ Constructor(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ Constructor(GeneratedMessageLite.ExtendableBuilder x0, AnonymousClass1 x1) {
            this((GeneratedMessageLite.ExtendableBuilder<Constructor, ?>) x0);
        }

        private Constructor(GeneratedMessageLite.ExtendableBuilder<Constructor, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Constructor(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Constructor getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public Constructor getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Constructor(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 8:
                                this.bitField0_ |= 1;
                                this.flags_ = input.readInt32();
                            case 18:
                                if ((mutable_bitField0_ & 2) != 2) {
                                    this.valueParameter_ = new ArrayList();
                                    mutable_bitField0_ |= 2;
                                }
                                this.valueParameter_.add(input.readMessage(ValueParameter.PARSER, extensionRegistry));
                            case 248:
                                if ((mutable_bitField0_ & 4) != 4) {
                                    this.versionRequirement_ = new ArrayList();
                                    mutable_bitField0_ |= 4;
                                }
                                this.versionRequirement_.add(Integer.valueOf(input.readInt32()));
                            case 250:
                                int length = input.readRawVarint32();
                                int limit = input.pushLimit(length);
                                if ((mutable_bitField0_ & 4) != 4 && input.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_ = new ArrayList();
                                    mutable_bitField0_ |= 4;
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_.add(Integer.valueOf(input.readInt32()));
                                }
                                input.popLimit(limit);
                                break;
                            default:
                                if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 2) == 2) {
                    this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                }
                if ((mutable_bitField0_ & 4) == 4) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 2) == 2) {
                    this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                }
                if ((mutable_bitField0_ & 4) == 4) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<Constructor> getParserForType() {
            return PARSER;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getFlags() {
            return this.flags_;
        }

        public List<ValueParameter> getValueParameterList() {
            return this.valueParameter_;
        }

        public int getValueParameterCount() {
            return this.valueParameter_.size();
        }

        public ValueParameter getValueParameter(int index) {
            return this.valueParameter_.get(index);
        }

        public List<Integer> getVersionRequirementList() {
            return this.versionRequirement_;
        }

        private void initFields() {
            this.flags_ = 6;
            this.valueParameter_ = Collections.emptyList();
            this.versionRequirement_ = Collections.emptyList();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < getValueParameterCount(); i++) {
                if (!getValueParameter(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            GeneratedMessageLite.ExtendableMessage<MessageType>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.flags_);
            }
            for (int i = 0; i < this.valueParameter_.size(); i++) {
                output.writeMessage(2, this.valueParameter_.get(i));
            }
            for (int i2 = 0; i2 < this.versionRequirement_.size(); i2++) {
                output.writeInt32(31, this.versionRequirement_.get(i2).intValue());
            }
            extensionWriterNewExtensionWriter.writeUntil(19000, output);
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.flags_);
            }
            for (int i = 0; i < this.valueParameter_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.valueParameter_.get(i));
            }
            int dataSize = 0;
            for (int i2 = 0; i2 < this.versionRequirement_.size(); i2++) {
                dataSize += CodedOutputStream.computeInt32SizeNoTag(this.versionRequirement_.get(i2).intValue());
            }
            int size3 = size2 + dataSize + (2 * getVersionRequirementList().size()) + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Constructor prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Constructor$Builder.class */
        public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<Constructor, Builder> implements ConstructorOrBuilder {
            private int bitField0_;
            private int flags_ = 6;
            private List<ValueParameter> valueParameter_ = Collections.emptyList();
            private List<Integer> versionRequirement_ = Collections.emptyList();

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.ExtendableBuilder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((Constructor) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public Constructor getDefaultInstanceForType() {
                return Constructor.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Constructor build() {
                Constructor result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public Constructor buildPartial() {
                Constructor result = new Constructor(this, (AnonymousClass1) null);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.flags_ = this.flags_;
                if ((this.bitField0_ & 2) == 2) {
                    this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                    this.bitField0_ &= -3;
                }
                result.valueParameter_ = this.valueParameter_;
                if ((this.bitField0_ & 4) == 4) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    this.bitField0_ &= -5;
                }
                result.versionRequirement_ = this.versionRequirement_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(Constructor other) {
                if (other == Constructor.getDefaultInstance()) {
                    return this;
                }
                if (other.hasFlags()) {
                    setFlags(other.getFlags());
                }
                if (!other.valueParameter_.isEmpty()) {
                    if (this.valueParameter_.isEmpty()) {
                        this.valueParameter_ = other.valueParameter_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureValueParameterIsMutable();
                        this.valueParameter_.addAll(other.valueParameter_);
                    }
                }
                if (!other.versionRequirement_.isEmpty()) {
                    if (this.versionRequirement_.isEmpty()) {
                        this.versionRequirement_ = other.versionRequirement_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureVersionRequirementIsMutable();
                        this.versionRequirement_.addAll(other.versionRequirement_);
                    }
                }
                mergeExtensionFields(other);
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getValueParameterCount(); i++) {
                    if (!getValueParameter(i).isInitialized()) {
                        return false;
                    }
                }
                if (!extensionsAreInitialized()) {
                    return false;
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Constructor parsedMessage = null;
                try {
                    try {
                        parsedMessage = Constructor.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (Constructor) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            public Builder setFlags(int value) {
                this.bitField0_ |= 1;
                this.flags_ = value;
                return this;
            }

            private void ensureValueParameterIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.valueParameter_ = new ArrayList(this.valueParameter_);
                    this.bitField0_ |= 2;
                }
            }

            public int getValueParameterCount() {
                return this.valueParameter_.size();
            }

            public ValueParameter getValueParameter(int index) {
                return this.valueParameter_.get(index);
            }

            private void ensureVersionRequirementIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                    this.bitField0_ |= 4;
                }
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Function.class */
    public static final class Function extends GeneratedMessageLite.ExtendableMessage<Function> implements FunctionOrBuilder {
        private final ByteString unknownFields;
        private int bitField0_;
        private int flags_;
        private int oldFlags_;
        private int name_;
        private Type returnType_;
        private int returnTypeId_;
        private List<TypeParameter> typeParameter_;
        private Type receiverType_;
        private int receiverTypeId_;
        private List<ValueParameter> valueParameter_;
        private TypeTable typeTable_;
        private List<Integer> versionRequirement_;
        private Contract contract_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<Function> PARSER = new AbstractParser<Function>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public Function parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Function(input, extensionRegistry, null);
            }
        };
        private static final Function defaultInstance = new Function(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        /* synthetic */ Function(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ Function(GeneratedMessageLite.ExtendableBuilder x0, AnonymousClass1 x1) {
            this((GeneratedMessageLite.ExtendableBuilder<Function, ?>) x0);
        }

        private Function(GeneratedMessageLite.ExtendableBuilder<Function, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Function(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Function getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public Function getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Function(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                case 8:
                                    this.bitField0_ |= 2;
                                    this.oldFlags_ = input.readInt32();
                                case 16:
                                    this.bitField0_ |= 4;
                                    this.name_ = input.readInt32();
                                case 26:
                                    Type.Builder subBuilder = null;
                                    subBuilder = (this.bitField0_ & 8) == 8 ? this.returnType_.toBuilder() : subBuilder;
                                    this.returnType_ = (Type) input.readMessage(Type.PARSER, extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom(this.returnType_);
                                        this.returnType_ = subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 8;
                                case 34:
                                    if ((mutable_bitField0_ & 32) != 32) {
                                        this.typeParameter_ = new ArrayList();
                                        mutable_bitField0_ |= 32;
                                    }
                                    this.typeParameter_.add(input.readMessage(TypeParameter.PARSER, extensionRegistry));
                                case 42:
                                    Type.Builder subBuilder2 = null;
                                    subBuilder2 = (this.bitField0_ & 32) == 32 ? this.receiverType_.toBuilder() : subBuilder2;
                                    this.receiverType_ = (Type) input.readMessage(Type.PARSER, extensionRegistry);
                                    if (subBuilder2 != null) {
                                        subBuilder2.mergeFrom(this.receiverType_);
                                        this.receiverType_ = subBuilder2.buildPartial();
                                    }
                                    this.bitField0_ |= 32;
                                case 50:
                                    if ((mutable_bitField0_ & 256) != 256) {
                                        this.valueParameter_ = new ArrayList();
                                        mutable_bitField0_ |= 256;
                                    }
                                    this.valueParameter_.add(input.readMessage(ValueParameter.PARSER, extensionRegistry));
                                case 56:
                                    this.bitField0_ |= 16;
                                    this.returnTypeId_ = input.readInt32();
                                case 64:
                                    this.bitField0_ |= 64;
                                    this.receiverTypeId_ = input.readInt32();
                                case 72:
                                    this.bitField0_ |= 1;
                                    this.flags_ = input.readInt32();
                                case 242:
                                    TypeTable.Builder subBuilder3 = null;
                                    subBuilder3 = (this.bitField0_ & 128) == 128 ? this.typeTable_.toBuilder() : subBuilder3;
                                    this.typeTable_ = (TypeTable) input.readMessage(TypeTable.PARSER, extensionRegistry);
                                    if (subBuilder3 != null) {
                                        subBuilder3.mergeFrom(this.typeTable_);
                                        this.typeTable_ = subBuilder3.buildPartial();
                                    }
                                    this.bitField0_ |= 128;
                                case 248:
                                    if ((mutable_bitField0_ & 1024) != 1024) {
                                        this.versionRequirement_ = new ArrayList();
                                        mutable_bitField0_ |= 1024;
                                    }
                                    this.versionRequirement_.add(Integer.valueOf(input.readInt32()));
                                case 250:
                                    int length = input.readRawVarint32();
                                    int limit = input.pushLimit(length);
                                    if ((mutable_bitField0_ & 1024) != 1024 && input.getBytesUntilLimit() > 0) {
                                        this.versionRequirement_ = new ArrayList();
                                        mutable_bitField0_ |= 1024;
                                    }
                                    while (input.getBytesUntilLimit() > 0) {
                                        this.versionRequirement_.add(Integer.valueOf(input.readInt32()));
                                    }
                                    input.popLimit(limit);
                                    break;
                                case WGL4Names.NUMBER_OF_MAC_GLYPHS /* 258 */:
                                    Contract.Builder subBuilder4 = null;
                                    subBuilder4 = (this.bitField0_ & 256) == 256 ? this.contract_.toBuilder() : subBuilder4;
                                    this.contract_ = (Contract) input.readMessage(Contract.PARSER, extensionRegistry);
                                    if (subBuilder4 != null) {
                                        subBuilder4.mergeFrom(this.contract_);
                                        this.contract_ = subBuilder4.buildPartial();
                                    }
                                    this.bitField0_ |= 256;
                                default:
                                    if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                        done = true;
                                    }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 32) == 32) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                }
                if ((mutable_bitField0_ & 256) == 256) {
                    this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                }
                if ((mutable_bitField0_ & 1024) == 1024) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 32) == 32) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                }
                if ((mutable_bitField0_ & 256) == 256) {
                    this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                }
                if ((mutable_bitField0_ & 1024) == 1024) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<Function> getParserForType() {
            return PARSER;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getFlags() {
            return this.flags_;
        }

        public boolean hasOldFlags() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getOldFlags() {
            return this.oldFlags_;
        }

        public boolean hasName() {
            return (this.bitField0_ & 4) == 4;
        }

        public int getName() {
            return this.name_;
        }

        public boolean hasReturnType() {
            return (this.bitField0_ & 8) == 8;
        }

        public Type getReturnType() {
            return this.returnType_;
        }

        public boolean hasReturnTypeId() {
            return (this.bitField0_ & 16) == 16;
        }

        public int getReturnTypeId() {
            return this.returnTypeId_;
        }

        public List<TypeParameter> getTypeParameterList() {
            return this.typeParameter_;
        }

        public int getTypeParameterCount() {
            return this.typeParameter_.size();
        }

        public TypeParameter getTypeParameter(int index) {
            return this.typeParameter_.get(index);
        }

        public boolean hasReceiverType() {
            return (this.bitField0_ & 32) == 32;
        }

        public Type getReceiverType() {
            return this.receiverType_;
        }

        public boolean hasReceiverTypeId() {
            return (this.bitField0_ & 64) == 64;
        }

        public int getReceiverTypeId() {
            return this.receiverTypeId_;
        }

        public List<ValueParameter> getValueParameterList() {
            return this.valueParameter_;
        }

        public int getValueParameterCount() {
            return this.valueParameter_.size();
        }

        public ValueParameter getValueParameter(int index) {
            return this.valueParameter_.get(index);
        }

        public boolean hasTypeTable() {
            return (this.bitField0_ & 128) == 128;
        }

        public TypeTable getTypeTable() {
            return this.typeTable_;
        }

        public List<Integer> getVersionRequirementList() {
            return this.versionRequirement_;
        }

        public boolean hasContract() {
            return (this.bitField0_ & 256) == 256;
        }

        public Contract getContract() {
            return this.contract_;
        }

        private void initFields() {
            this.flags_ = 6;
            this.oldFlags_ = 6;
            this.name_ = 0;
            this.returnType_ = Type.getDefaultInstance();
            this.returnTypeId_ = 0;
            this.typeParameter_ = Collections.emptyList();
            this.receiverType_ = Type.getDefaultInstance();
            this.receiverTypeId_ = 0;
            this.valueParameter_ = Collections.emptyList();
            this.typeTable_ = TypeTable.getDefaultInstance();
            this.versionRequirement_ = Collections.emptyList();
            this.contract_ = Contract.getDefaultInstance();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasName()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (hasReturnType() && !getReturnType().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            for (int i = 0; i < getTypeParameterCount(); i++) {
                if (!getTypeParameter(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (hasReceiverType() && !getReceiverType().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            for (int i2 = 0; i2 < getValueParameterCount(); i2++) {
                if (!getValueParameter(i2).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (hasTypeTable() && !getTypeTable().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (hasContract() && !getContract().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            GeneratedMessageLite.ExtendableMessage<MessageType>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(1, this.oldFlags_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(2, this.name_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(3, this.returnType_);
            }
            for (int i = 0; i < this.typeParameter_.size(); i++) {
                output.writeMessage(4, this.typeParameter_.get(i));
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeMessage(5, this.receiverType_);
            }
            for (int i2 = 0; i2 < this.valueParameter_.size(); i2++) {
                output.writeMessage(6, this.valueParameter_.get(i2));
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(7, this.returnTypeId_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(8, this.receiverTypeId_);
            }
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(9, this.flags_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeMessage(30, this.typeTable_);
            }
            for (int i3 = 0; i3 < this.versionRequirement_.size(); i3++) {
                output.writeInt32(31, this.versionRequirement_.get(i3).intValue());
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeMessage(32, this.contract_);
            }
            extensionWriterNewExtensionWriter.writeUntil(19000, output);
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.oldFlags_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(2, this.name_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(3, this.returnType_);
            }
            for (int i = 0; i < this.typeParameter_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(4, this.typeParameter_.get(i));
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeMessageSize(5, this.receiverType_);
            }
            for (int i2 = 0; i2 < this.valueParameter_.size(); i2++) {
                size2 += CodedOutputStream.computeMessageSize(6, this.valueParameter_.get(i2));
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(7, this.returnTypeId_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt32Size(8, this.receiverTypeId_);
            }
            if ((this.bitField0_ & 1) == 1) {
                size2 += CodedOutputStream.computeInt32Size(9, this.flags_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeMessageSize(30, this.typeTable_);
            }
            int dataSize = 0;
            for (int i3 = 0; i3 < this.versionRequirement_.size(); i3++) {
                dataSize += CodedOutputStream.computeInt32SizeNoTag(this.versionRequirement_.get(i3).intValue());
            }
            int size3 = size2 + dataSize + (2 * getVersionRequirementList().size());
            if ((this.bitField0_ & 256) == 256) {
                size3 += CodedOutputStream.computeMessageSize(32, this.contract_);
            }
            int size4 = size3 + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = size4;
            return size4;
        }

        public static Function parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Function prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Function$Builder.class */
        public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<Function, Builder> implements FunctionOrBuilder {
            private int bitField0_;
            private int name_;
            private int returnTypeId_;
            private int receiverTypeId_;
            private int flags_ = 6;
            private int oldFlags_ = 6;
            private Type returnType_ = Type.getDefaultInstance();
            private List<TypeParameter> typeParameter_ = Collections.emptyList();
            private Type receiverType_ = Type.getDefaultInstance();
            private List<ValueParameter> valueParameter_ = Collections.emptyList();
            private TypeTable typeTable_ = TypeTable.getDefaultInstance();
            private List<Integer> versionRequirement_ = Collections.emptyList();
            private Contract contract_ = Contract.getDefaultInstance();

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.ExtendableBuilder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((Function) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public Function getDefaultInstanceForType() {
                return Function.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Function build() {
                Function result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public Function buildPartial() {
                Function result = new Function(this, (AnonymousClass1) null);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.flags_ = this.flags_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.oldFlags_ = this.oldFlags_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.returnType_ = this.returnType_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.returnTypeId_ = this.returnTypeId_;
                if ((this.bitField0_ & 32) == 32) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    this.bitField0_ &= -33;
                }
                result.typeParameter_ = this.typeParameter_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 32;
                }
                result.receiverType_ = this.receiverType_;
                if ((from_bitField0_ & 128) == 128) {
                    to_bitField0_ |= 64;
                }
                result.receiverTypeId_ = this.receiverTypeId_;
                if ((this.bitField0_ & 256) == 256) {
                    this.valueParameter_ = Collections.unmodifiableList(this.valueParameter_);
                    this.bitField0_ &= -257;
                }
                result.valueParameter_ = this.valueParameter_;
                if ((from_bitField0_ & 512) == 512) {
                    to_bitField0_ |= 128;
                }
                result.typeTable_ = this.typeTable_;
                if ((this.bitField0_ & 1024) == 1024) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    this.bitField0_ &= -1025;
                }
                result.versionRequirement_ = this.versionRequirement_;
                if ((from_bitField0_ & 2048) == 2048) {
                    to_bitField0_ |= 256;
                }
                result.contract_ = this.contract_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(Function other) {
                if (other == Function.getDefaultInstance()) {
                    return this;
                }
                if (other.hasFlags()) {
                    setFlags(other.getFlags());
                }
                if (other.hasOldFlags()) {
                    setOldFlags(other.getOldFlags());
                }
                if (other.hasName()) {
                    setName(other.getName());
                }
                if (other.hasReturnType()) {
                    mergeReturnType(other.getReturnType());
                }
                if (other.hasReturnTypeId()) {
                    setReturnTypeId(other.getReturnTypeId());
                }
                if (!other.typeParameter_.isEmpty()) {
                    if (this.typeParameter_.isEmpty()) {
                        this.typeParameter_ = other.typeParameter_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureTypeParameterIsMutable();
                        this.typeParameter_.addAll(other.typeParameter_);
                    }
                }
                if (other.hasReceiverType()) {
                    mergeReceiverType(other.getReceiverType());
                }
                if (other.hasReceiverTypeId()) {
                    setReceiverTypeId(other.getReceiverTypeId());
                }
                if (!other.valueParameter_.isEmpty()) {
                    if (this.valueParameter_.isEmpty()) {
                        this.valueParameter_ = other.valueParameter_;
                        this.bitField0_ &= -257;
                    } else {
                        ensureValueParameterIsMutable();
                        this.valueParameter_.addAll(other.valueParameter_);
                    }
                }
                if (other.hasTypeTable()) {
                    mergeTypeTable(other.getTypeTable());
                }
                if (!other.versionRequirement_.isEmpty()) {
                    if (this.versionRequirement_.isEmpty()) {
                        this.versionRequirement_ = other.versionRequirement_;
                        this.bitField0_ &= -1025;
                    } else {
                        ensureVersionRequirementIsMutable();
                        this.versionRequirement_.addAll(other.versionRequirement_);
                    }
                }
                if (other.hasContract()) {
                    mergeContract(other.getContract());
                }
                mergeExtensionFields(other);
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasName()) {
                    return false;
                }
                if (hasReturnType() && !getReturnType().isInitialized()) {
                    return false;
                }
                for (int i = 0; i < getTypeParameterCount(); i++) {
                    if (!getTypeParameter(i).isInitialized()) {
                        return false;
                    }
                }
                if (hasReceiverType() && !getReceiverType().isInitialized()) {
                    return false;
                }
                for (int i2 = 0; i2 < getValueParameterCount(); i2++) {
                    if (!getValueParameter(i2).isInitialized()) {
                        return false;
                    }
                }
                if (hasTypeTable() && !getTypeTable().isInitialized()) {
                    return false;
                }
                if ((hasContract() && !getContract().isInitialized()) || !extensionsAreInitialized()) {
                    return false;
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Function parsedMessage = null;
                try {
                    try {
                        parsedMessage = Function.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (Function) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            public Builder setFlags(int value) {
                this.bitField0_ |= 1;
                this.flags_ = value;
                return this;
            }

            public Builder setOldFlags(int value) {
                this.bitField0_ |= 2;
                this.oldFlags_ = value;
                return this;
            }

            public boolean hasName() {
                return (this.bitField0_ & 4) == 4;
            }

            public Builder setName(int value) {
                this.bitField0_ |= 4;
                this.name_ = value;
                return this;
            }

            public boolean hasReturnType() {
                return (this.bitField0_ & 8) == 8;
            }

            public Type getReturnType() {
                return this.returnType_;
            }

            public Builder mergeReturnType(Type value) {
                if ((this.bitField0_ & 8) == 8 && this.returnType_ != Type.getDefaultInstance()) {
                    this.returnType_ = Type.newBuilder(this.returnType_).mergeFrom(value).buildPartial();
                } else {
                    this.returnType_ = value;
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setReturnTypeId(int value) {
                this.bitField0_ |= 16;
                this.returnTypeId_ = value;
                return this;
            }

            private void ensureTypeParameterIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.typeParameter_ = new ArrayList(this.typeParameter_);
                    this.bitField0_ |= 32;
                }
            }

            public int getTypeParameterCount() {
                return this.typeParameter_.size();
            }

            public TypeParameter getTypeParameter(int index) {
                return this.typeParameter_.get(index);
            }

            public boolean hasReceiverType() {
                return (this.bitField0_ & 64) == 64;
            }

            public Type getReceiverType() {
                return this.receiverType_;
            }

            public Builder mergeReceiverType(Type value) {
                if ((this.bitField0_ & 64) == 64 && this.receiverType_ != Type.getDefaultInstance()) {
                    this.receiverType_ = Type.newBuilder(this.receiverType_).mergeFrom(value).buildPartial();
                } else {
                    this.receiverType_ = value;
                }
                this.bitField0_ |= 64;
                return this;
            }

            public Builder setReceiverTypeId(int value) {
                this.bitField0_ |= 128;
                this.receiverTypeId_ = value;
                return this;
            }

            private void ensureValueParameterIsMutable() {
                if ((this.bitField0_ & 256) != 256) {
                    this.valueParameter_ = new ArrayList(this.valueParameter_);
                    this.bitField0_ |= 256;
                }
            }

            public int getValueParameterCount() {
                return this.valueParameter_.size();
            }

            public ValueParameter getValueParameter(int index) {
                return this.valueParameter_.get(index);
            }

            public boolean hasTypeTable() {
                return (this.bitField0_ & 512) == 512;
            }

            public TypeTable getTypeTable() {
                return this.typeTable_;
            }

            public Builder mergeTypeTable(TypeTable value) {
                if ((this.bitField0_ & 512) == 512 && this.typeTable_ != TypeTable.getDefaultInstance()) {
                    this.typeTable_ = TypeTable.newBuilder(this.typeTable_).mergeFrom(value).buildPartial();
                } else {
                    this.typeTable_ = value;
                }
                this.bitField0_ |= 512;
                return this;
            }

            private void ensureVersionRequirementIsMutable() {
                if ((this.bitField0_ & 1024) != 1024) {
                    this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                    this.bitField0_ |= 1024;
                }
            }

            public boolean hasContract() {
                return (this.bitField0_ & 2048) == 2048;
            }

            public Contract getContract() {
                return this.contract_;
            }

            public Builder mergeContract(Contract value) {
                if ((this.bitField0_ & 2048) == 2048 && this.contract_ != Contract.getDefaultInstance()) {
                    this.contract_ = Contract.newBuilder(this.contract_).mergeFrom(value).buildPartial();
                } else {
                    this.contract_ = value;
                }
                this.bitField0_ |= 2048;
                return this;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Property.class */
    public static final class Property extends GeneratedMessageLite.ExtendableMessage<Property> implements PropertyOrBuilder {
        private final ByteString unknownFields;
        private int bitField0_;
        private int flags_;
        private int oldFlags_;
        private int name_;
        private Type returnType_;
        private int returnTypeId_;
        private List<TypeParameter> typeParameter_;
        private Type receiverType_;
        private int receiverTypeId_;
        private ValueParameter setterValueParameter_;
        private int getterFlags_;
        private int setterFlags_;
        private List<Integer> versionRequirement_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<Property> PARSER = new AbstractParser<Property>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public Property parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Property(input, extensionRegistry, null);
            }
        };
        private static final Property defaultInstance = new Property(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        /* synthetic */ Property(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ Property(GeneratedMessageLite.ExtendableBuilder x0, AnonymousClass1 x1) {
            this((GeneratedMessageLite.ExtendableBuilder<Property, ?>) x0);
        }

        private Property(GeneratedMessageLite.ExtendableBuilder<Property, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Property(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Property getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public Property getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Property(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 8:
                                this.bitField0_ |= 2;
                                this.oldFlags_ = input.readInt32();
                            case 16:
                                this.bitField0_ |= 4;
                                this.name_ = input.readInt32();
                            case 26:
                                Type.Builder subBuilder = null;
                                subBuilder = (this.bitField0_ & 8) == 8 ? this.returnType_.toBuilder() : subBuilder;
                                this.returnType_ = (Type) input.readMessage(Type.PARSER, extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(this.returnType_);
                                    this.returnType_ = subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 8;
                            case 34:
                                if ((mutable_bitField0_ & 32) != 32) {
                                    this.typeParameter_ = new ArrayList();
                                    mutable_bitField0_ |= 32;
                                }
                                this.typeParameter_.add(input.readMessage(TypeParameter.PARSER, extensionRegistry));
                            case 42:
                                Type.Builder subBuilder2 = null;
                                subBuilder2 = (this.bitField0_ & 32) == 32 ? this.receiverType_.toBuilder() : subBuilder2;
                                this.receiverType_ = (Type) input.readMessage(Type.PARSER, extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom(this.receiverType_);
                                    this.receiverType_ = subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 32;
                            case 50:
                                ValueParameter.Builder subBuilder3 = null;
                                subBuilder3 = (this.bitField0_ & 128) == 128 ? this.setterValueParameter_.toBuilder() : subBuilder3;
                                this.setterValueParameter_ = (ValueParameter) input.readMessage(ValueParameter.PARSER, extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom(this.setterValueParameter_);
                                    this.setterValueParameter_ = subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 128;
                            case 56:
                                this.bitField0_ |= 256;
                                this.getterFlags_ = input.readInt32();
                            case 64:
                                this.bitField0_ |= 512;
                                this.setterFlags_ = input.readInt32();
                            case 72:
                                this.bitField0_ |= 16;
                                this.returnTypeId_ = input.readInt32();
                            case 80:
                                this.bitField0_ |= 64;
                                this.receiverTypeId_ = input.readInt32();
                            case 88:
                                this.bitField0_ |= 1;
                                this.flags_ = input.readInt32();
                            case 248:
                                if ((mutable_bitField0_ & 2048) != 2048) {
                                    this.versionRequirement_ = new ArrayList();
                                    mutable_bitField0_ |= 2048;
                                }
                                this.versionRequirement_.add(Integer.valueOf(input.readInt32()));
                            case 250:
                                int length = input.readRawVarint32();
                                int limit = input.pushLimit(length);
                                if ((mutable_bitField0_ & 2048) != 2048 && input.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_ = new ArrayList();
                                    mutable_bitField0_ |= 2048;
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_.add(Integer.valueOf(input.readInt32()));
                                }
                                input.popLimit(limit);
                                break;
                            default:
                                if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 32) == 32) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                }
                if ((mutable_bitField0_ & 2048) == 2048) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 32) == 32) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                }
                if ((mutable_bitField0_ & 2048) == 2048) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<Property> getParserForType() {
            return PARSER;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getFlags() {
            return this.flags_;
        }

        public boolean hasOldFlags() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getOldFlags() {
            return this.oldFlags_;
        }

        public boolean hasName() {
            return (this.bitField0_ & 4) == 4;
        }

        public int getName() {
            return this.name_;
        }

        public boolean hasReturnType() {
            return (this.bitField0_ & 8) == 8;
        }

        public Type getReturnType() {
            return this.returnType_;
        }

        public boolean hasReturnTypeId() {
            return (this.bitField0_ & 16) == 16;
        }

        public int getReturnTypeId() {
            return this.returnTypeId_;
        }

        public List<TypeParameter> getTypeParameterList() {
            return this.typeParameter_;
        }

        public int getTypeParameterCount() {
            return this.typeParameter_.size();
        }

        public TypeParameter getTypeParameter(int index) {
            return this.typeParameter_.get(index);
        }

        public boolean hasReceiverType() {
            return (this.bitField0_ & 32) == 32;
        }

        public Type getReceiverType() {
            return this.receiverType_;
        }

        public boolean hasReceiverTypeId() {
            return (this.bitField0_ & 64) == 64;
        }

        public int getReceiverTypeId() {
            return this.receiverTypeId_;
        }

        public boolean hasSetterValueParameter() {
            return (this.bitField0_ & 128) == 128;
        }

        public ValueParameter getSetterValueParameter() {
            return this.setterValueParameter_;
        }

        public boolean hasGetterFlags() {
            return (this.bitField0_ & 256) == 256;
        }

        public int getGetterFlags() {
            return this.getterFlags_;
        }

        public boolean hasSetterFlags() {
            return (this.bitField0_ & 512) == 512;
        }

        public int getSetterFlags() {
            return this.setterFlags_;
        }

        public List<Integer> getVersionRequirementList() {
            return this.versionRequirement_;
        }

        private void initFields() {
            this.flags_ = 518;
            this.oldFlags_ = 2054;
            this.name_ = 0;
            this.returnType_ = Type.getDefaultInstance();
            this.returnTypeId_ = 0;
            this.typeParameter_ = Collections.emptyList();
            this.receiverType_ = Type.getDefaultInstance();
            this.receiverTypeId_ = 0;
            this.setterValueParameter_ = ValueParameter.getDefaultInstance();
            this.getterFlags_ = 0;
            this.setterFlags_ = 0;
            this.versionRequirement_ = Collections.emptyList();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasName()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (hasReturnType() && !getReturnType().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            for (int i = 0; i < getTypeParameterCount(); i++) {
                if (!getTypeParameter(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (hasReceiverType() && !getReceiverType().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (hasSetterValueParameter() && !getSetterValueParameter().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            GeneratedMessageLite.ExtendableMessage<MessageType>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(1, this.oldFlags_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(2, this.name_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(3, this.returnType_);
            }
            for (int i = 0; i < this.typeParameter_.size(); i++) {
                output.writeMessage(4, this.typeParameter_.get(i));
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeMessage(5, this.receiverType_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeMessage(6, this.setterValueParameter_);
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeInt32(7, this.getterFlags_);
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeInt32(8, this.setterFlags_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(9, this.returnTypeId_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(10, this.receiverTypeId_);
            }
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(11, this.flags_);
            }
            for (int i2 = 0; i2 < this.versionRequirement_.size(); i2++) {
                output.writeInt32(31, this.versionRequirement_.get(i2).intValue());
            }
            extensionWriterNewExtensionWriter.writeUntil(19000, output);
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.oldFlags_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(2, this.name_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(3, this.returnType_);
            }
            for (int i = 0; i < this.typeParameter_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(4, this.typeParameter_.get(i));
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeMessageSize(5, this.receiverType_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeMessageSize(6, this.setterValueParameter_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeInt32Size(7, this.getterFlags_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeInt32Size(8, this.setterFlags_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(9, this.returnTypeId_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt32Size(10, this.receiverTypeId_);
            }
            if ((this.bitField0_ & 1) == 1) {
                size2 += CodedOutputStream.computeInt32Size(11, this.flags_);
            }
            int dataSize = 0;
            for (int i2 = 0; i2 < this.versionRequirement_.size(); i2++) {
                dataSize += CodedOutputStream.computeInt32SizeNoTag(this.versionRequirement_.get(i2).intValue());
            }
            int size3 = size2 + dataSize + (2 * getVersionRequirementList().size()) + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Property prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Property$Builder.class */
        public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<Property, Builder> implements PropertyOrBuilder {
            private int bitField0_;
            private int name_;
            private int returnTypeId_;
            private int receiverTypeId_;
            private int getterFlags_;
            private int setterFlags_;
            private int flags_ = 518;
            private int oldFlags_ = 2054;
            private Type returnType_ = Type.getDefaultInstance();
            private List<TypeParameter> typeParameter_ = Collections.emptyList();
            private Type receiverType_ = Type.getDefaultInstance();
            private ValueParameter setterValueParameter_ = ValueParameter.getDefaultInstance();
            private List<Integer> versionRequirement_ = Collections.emptyList();

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.ExtendableBuilder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((Property) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public Property getDefaultInstanceForType() {
                return Property.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Property build() {
                Property result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public Property buildPartial() {
                Property result = new Property(this, (AnonymousClass1) null);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.flags_ = this.flags_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.oldFlags_ = this.oldFlags_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.returnType_ = this.returnType_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.returnTypeId_ = this.returnTypeId_;
                if ((this.bitField0_ & 32) == 32) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    this.bitField0_ &= -33;
                }
                result.typeParameter_ = this.typeParameter_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 32;
                }
                result.receiverType_ = this.receiverType_;
                if ((from_bitField0_ & 128) == 128) {
                    to_bitField0_ |= 64;
                }
                result.receiverTypeId_ = this.receiverTypeId_;
                if ((from_bitField0_ & 256) == 256) {
                    to_bitField0_ |= 128;
                }
                result.setterValueParameter_ = this.setterValueParameter_;
                if ((from_bitField0_ & 512) == 512) {
                    to_bitField0_ |= 256;
                }
                result.getterFlags_ = this.getterFlags_;
                if ((from_bitField0_ & 1024) == 1024) {
                    to_bitField0_ |= 512;
                }
                result.setterFlags_ = this.setterFlags_;
                if ((this.bitField0_ & 2048) == 2048) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    this.bitField0_ &= -2049;
                }
                result.versionRequirement_ = this.versionRequirement_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(Property other) {
                if (other == Property.getDefaultInstance()) {
                    return this;
                }
                if (other.hasFlags()) {
                    setFlags(other.getFlags());
                }
                if (other.hasOldFlags()) {
                    setOldFlags(other.getOldFlags());
                }
                if (other.hasName()) {
                    setName(other.getName());
                }
                if (other.hasReturnType()) {
                    mergeReturnType(other.getReturnType());
                }
                if (other.hasReturnTypeId()) {
                    setReturnTypeId(other.getReturnTypeId());
                }
                if (!other.typeParameter_.isEmpty()) {
                    if (this.typeParameter_.isEmpty()) {
                        this.typeParameter_ = other.typeParameter_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureTypeParameterIsMutable();
                        this.typeParameter_.addAll(other.typeParameter_);
                    }
                }
                if (other.hasReceiverType()) {
                    mergeReceiverType(other.getReceiverType());
                }
                if (other.hasReceiverTypeId()) {
                    setReceiverTypeId(other.getReceiverTypeId());
                }
                if (other.hasSetterValueParameter()) {
                    mergeSetterValueParameter(other.getSetterValueParameter());
                }
                if (other.hasGetterFlags()) {
                    setGetterFlags(other.getGetterFlags());
                }
                if (other.hasSetterFlags()) {
                    setSetterFlags(other.getSetterFlags());
                }
                if (!other.versionRequirement_.isEmpty()) {
                    if (this.versionRequirement_.isEmpty()) {
                        this.versionRequirement_ = other.versionRequirement_;
                        this.bitField0_ &= -2049;
                    } else {
                        ensureVersionRequirementIsMutable();
                        this.versionRequirement_.addAll(other.versionRequirement_);
                    }
                }
                mergeExtensionFields(other);
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasName()) {
                    return false;
                }
                if (hasReturnType() && !getReturnType().isInitialized()) {
                    return false;
                }
                for (int i = 0; i < getTypeParameterCount(); i++) {
                    if (!getTypeParameter(i).isInitialized()) {
                        return false;
                    }
                }
                if (hasReceiverType() && !getReceiverType().isInitialized()) {
                    return false;
                }
                if ((hasSetterValueParameter() && !getSetterValueParameter().isInitialized()) || !extensionsAreInitialized()) {
                    return false;
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Property parsedMessage = null;
                try {
                    try {
                        parsedMessage = Property.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (Property) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            public Builder setFlags(int value) {
                this.bitField0_ |= 1;
                this.flags_ = value;
                return this;
            }

            public Builder setOldFlags(int value) {
                this.bitField0_ |= 2;
                this.oldFlags_ = value;
                return this;
            }

            public boolean hasName() {
                return (this.bitField0_ & 4) == 4;
            }

            public Builder setName(int value) {
                this.bitField0_ |= 4;
                this.name_ = value;
                return this;
            }

            public boolean hasReturnType() {
                return (this.bitField0_ & 8) == 8;
            }

            public Type getReturnType() {
                return this.returnType_;
            }

            public Builder mergeReturnType(Type value) {
                if ((this.bitField0_ & 8) == 8 && this.returnType_ != Type.getDefaultInstance()) {
                    this.returnType_ = Type.newBuilder(this.returnType_).mergeFrom(value).buildPartial();
                } else {
                    this.returnType_ = value;
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setReturnTypeId(int value) {
                this.bitField0_ |= 16;
                this.returnTypeId_ = value;
                return this;
            }

            private void ensureTypeParameterIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.typeParameter_ = new ArrayList(this.typeParameter_);
                    this.bitField0_ |= 32;
                }
            }

            public int getTypeParameterCount() {
                return this.typeParameter_.size();
            }

            public TypeParameter getTypeParameter(int index) {
                return this.typeParameter_.get(index);
            }

            public boolean hasReceiverType() {
                return (this.bitField0_ & 64) == 64;
            }

            public Type getReceiverType() {
                return this.receiverType_;
            }

            public Builder mergeReceiverType(Type value) {
                if ((this.bitField0_ & 64) == 64 && this.receiverType_ != Type.getDefaultInstance()) {
                    this.receiverType_ = Type.newBuilder(this.receiverType_).mergeFrom(value).buildPartial();
                } else {
                    this.receiverType_ = value;
                }
                this.bitField0_ |= 64;
                return this;
            }

            public Builder setReceiverTypeId(int value) {
                this.bitField0_ |= 128;
                this.receiverTypeId_ = value;
                return this;
            }

            public boolean hasSetterValueParameter() {
                return (this.bitField0_ & 256) == 256;
            }

            public ValueParameter getSetterValueParameter() {
                return this.setterValueParameter_;
            }

            public Builder mergeSetterValueParameter(ValueParameter value) {
                if ((this.bitField0_ & 256) == 256 && this.setterValueParameter_ != ValueParameter.getDefaultInstance()) {
                    this.setterValueParameter_ = ValueParameter.newBuilder(this.setterValueParameter_).mergeFrom(value).buildPartial();
                } else {
                    this.setterValueParameter_ = value;
                }
                this.bitField0_ |= 256;
                return this;
            }

            public Builder setGetterFlags(int value) {
                this.bitField0_ |= 512;
                this.getterFlags_ = value;
                return this;
            }

            public Builder setSetterFlags(int value) {
                this.bitField0_ |= 1024;
                this.setterFlags_ = value;
                return this;
            }

            private void ensureVersionRequirementIsMutable() {
                if ((this.bitField0_ & 2048) != 2048) {
                    this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                    this.bitField0_ |= 2048;
                }
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$ValueParameter.class */
    public static final class ValueParameter extends GeneratedMessageLite.ExtendableMessage<ValueParameter> implements ValueParameterOrBuilder {
        private final ByteString unknownFields;
        private int bitField0_;
        private int flags_;
        private int name_;
        private Type type_;
        private int typeId_;
        private Type varargElementType_;
        private int varargElementTypeId_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<ValueParameter> PARSER = new AbstractParser<ValueParameter>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public ValueParameter parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new ValueParameter(input, extensionRegistry, null);
            }
        };
        private static final ValueParameter defaultInstance = new ValueParameter(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        /* synthetic */ ValueParameter(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ ValueParameter(GeneratedMessageLite.ExtendableBuilder x0, AnonymousClass1 x1) {
            this((GeneratedMessageLite.ExtendableBuilder<ValueParameter, ?>) x0);
        }

        private ValueParameter(GeneratedMessageLite.ExtendableBuilder<ValueParameter, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ValueParameter(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static ValueParameter getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public ValueParameter getDefaultInstanceForType() {
            return defaultInstance;
        }

        private ValueParameter(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 8:
                                this.bitField0_ |= 1;
                                this.flags_ = input.readInt32();
                            case 16:
                                this.bitField0_ |= 2;
                                this.name_ = input.readInt32();
                            case 26:
                                Type.Builder subBuilder = null;
                                subBuilder = (this.bitField0_ & 4) == 4 ? this.type_.toBuilder() : subBuilder;
                                this.type_ = (Type) input.readMessage(Type.PARSER, extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(this.type_);
                                    this.type_ = subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            case 34:
                                Type.Builder subBuilder2 = null;
                                subBuilder2 = (this.bitField0_ & 16) == 16 ? this.varargElementType_.toBuilder() : subBuilder2;
                                this.varargElementType_ = (Type) input.readMessage(Type.PARSER, extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom(this.varargElementType_);
                                    this.varargElementType_ = subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 16;
                            case 40:
                                this.bitField0_ |= 8;
                                this.typeId_ = input.readInt32();
                            case 48:
                                this.bitField0_ |= 32;
                                this.varargElementTypeId_ = input.readInt32();
                            default:
                                if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<ValueParameter> getParserForType() {
            return PARSER;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getFlags() {
            return this.flags_;
        }

        public boolean hasName() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getName() {
            return this.name_;
        }

        public boolean hasType() {
            return (this.bitField0_ & 4) == 4;
        }

        public Type getType() {
            return this.type_;
        }

        public boolean hasTypeId() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getTypeId() {
            return this.typeId_;
        }

        public boolean hasVarargElementType() {
            return (this.bitField0_ & 16) == 16;
        }

        public Type getVarargElementType() {
            return this.varargElementType_;
        }

        public boolean hasVarargElementTypeId() {
            return (this.bitField0_ & 32) == 32;
        }

        public int getVarargElementTypeId() {
            return this.varargElementTypeId_;
        }

        private void initFields() {
            this.flags_ = 0;
            this.name_ = 0;
            this.type_ = Type.getDefaultInstance();
            this.typeId_ = 0;
            this.varargElementType_ = Type.getDefaultInstance();
            this.varargElementTypeId_ = 0;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasName()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (hasType() && !getType().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (hasVarargElementType() && !getVarargElementType().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            GeneratedMessageLite.ExtendableMessage<MessageType>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.flags_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.name_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, this.type_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeMessage(4, this.varargElementType_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(5, this.typeId_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(6, this.varargElementTypeId_);
            }
            extensionWriterNewExtensionWriter.writeUntil(200, output);
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.flags_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.name_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, this.type_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeMessageSize(4, this.varargElementType_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(5, this.typeId_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(6, this.varargElementTypeId_);
            }
            int size3 = size2 + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ValueParameter prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$ValueParameter$Builder.class */
        public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<ValueParameter, Builder> implements ValueParameterOrBuilder {
            private int bitField0_;
            private int flags_;
            private int name_;
            private int typeId_;
            private int varargElementTypeId_;
            private Type type_ = Type.getDefaultInstance();
            private Type varargElementType_ = Type.getDefaultInstance();

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.ExtendableBuilder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((ValueParameter) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public ValueParameter getDefaultInstanceForType() {
                return ValueParameter.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public ValueParameter build() {
                ValueParameter result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public ValueParameter buildPartial() {
                ValueParameter result = new ValueParameter(this, (AnonymousClass1) null);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.flags_ = this.flags_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.type_ = this.type_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.typeId_ = this.typeId_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.varargElementType_ = this.varargElementType_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.varargElementTypeId_ = this.varargElementTypeId_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(ValueParameter other) {
                if (other == ValueParameter.getDefaultInstance()) {
                    return this;
                }
                if (other.hasFlags()) {
                    setFlags(other.getFlags());
                }
                if (other.hasName()) {
                    setName(other.getName());
                }
                if (other.hasType()) {
                    mergeType(other.getType());
                }
                if (other.hasTypeId()) {
                    setTypeId(other.getTypeId());
                }
                if (other.hasVarargElementType()) {
                    mergeVarargElementType(other.getVarargElementType());
                }
                if (other.hasVarargElementTypeId()) {
                    setVarargElementTypeId(other.getVarargElementTypeId());
                }
                mergeExtensionFields(other);
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasName()) {
                    return false;
                }
                if (hasType() && !getType().isInitialized()) {
                    return false;
                }
                if ((hasVarargElementType() && !getVarargElementType().isInitialized()) || !extensionsAreInitialized()) {
                    return false;
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                ValueParameter parsedMessage = null;
                try {
                    try {
                        parsedMessage = ValueParameter.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (ValueParameter) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            public Builder setFlags(int value) {
                this.bitField0_ |= 1;
                this.flags_ = value;
                return this;
            }

            public boolean hasName() {
                return (this.bitField0_ & 2) == 2;
            }

            public Builder setName(int value) {
                this.bitField0_ |= 2;
                this.name_ = value;
                return this;
            }

            public boolean hasType() {
                return (this.bitField0_ & 4) == 4;
            }

            public Type getType() {
                return this.type_;
            }

            public Builder mergeType(Type value) {
                if ((this.bitField0_ & 4) == 4 && this.type_ != Type.getDefaultInstance()) {
                    this.type_ = Type.newBuilder(this.type_).mergeFrom(value).buildPartial();
                } else {
                    this.type_ = value;
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setTypeId(int value) {
                this.bitField0_ |= 8;
                this.typeId_ = value;
                return this;
            }

            public boolean hasVarargElementType() {
                return (this.bitField0_ & 16) == 16;
            }

            public Type getVarargElementType() {
                return this.varargElementType_;
            }

            public Builder mergeVarargElementType(Type value) {
                if ((this.bitField0_ & 16) == 16 && this.varargElementType_ != Type.getDefaultInstance()) {
                    this.varargElementType_ = Type.newBuilder(this.varargElementType_).mergeFrom(value).buildPartial();
                } else {
                    this.varargElementType_ = value;
                }
                this.bitField0_ |= 16;
                return this;
            }

            public Builder setVarargElementTypeId(int value) {
                this.bitField0_ |= 32;
                this.varargElementTypeId_ = value;
                return this;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$TypeAlias.class */
    public static final class TypeAlias extends GeneratedMessageLite.ExtendableMessage<TypeAlias> implements TypeAliasOrBuilder {
        private final ByteString unknownFields;
        private int bitField0_;
        private int flags_;
        private int name_;
        private List<TypeParameter> typeParameter_;
        private Type underlyingType_;
        private int underlyingTypeId_;
        private Type expandedType_;
        private int expandedTypeId_;
        private List<Annotation> annotation_;
        private List<Integer> versionRequirement_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<TypeAlias> PARSER = new AbstractParser<TypeAlias>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public TypeAlias parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new TypeAlias(input, extensionRegistry, null);
            }
        };
        private static final TypeAlias defaultInstance = new TypeAlias(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        /* synthetic */ TypeAlias(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ TypeAlias(GeneratedMessageLite.ExtendableBuilder x0, AnonymousClass1 x1) {
            this((GeneratedMessageLite.ExtendableBuilder<TypeAlias, ?>) x0);
        }

        private TypeAlias(GeneratedMessageLite.ExtendableBuilder<TypeAlias, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private TypeAlias(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static TypeAlias getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public TypeAlias getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private TypeAlias(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 8:
                                this.bitField0_ |= 1;
                                this.flags_ = input.readInt32();
                            case 16:
                                this.bitField0_ |= 2;
                                this.name_ = input.readInt32();
                            case 26:
                                if ((mutable_bitField0_ & 4) != 4) {
                                    this.typeParameter_ = new ArrayList();
                                    mutable_bitField0_ |= 4;
                                }
                                this.typeParameter_.add(input.readMessage(TypeParameter.PARSER, extensionRegistry));
                            case 34:
                                Type.Builder subBuilder = null;
                                subBuilder = (this.bitField0_ & 4) == 4 ? this.underlyingType_.toBuilder() : subBuilder;
                                this.underlyingType_ = (Type) input.readMessage(Type.PARSER, extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(this.underlyingType_);
                                    this.underlyingType_ = subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            case 40:
                                this.bitField0_ |= 8;
                                this.underlyingTypeId_ = input.readInt32();
                            case 50:
                                Type.Builder subBuilder2 = null;
                                subBuilder2 = (this.bitField0_ & 16) == 16 ? this.expandedType_.toBuilder() : subBuilder2;
                                this.expandedType_ = (Type) input.readMessage(Type.PARSER, extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom(this.expandedType_);
                                    this.expandedType_ = subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 16;
                            case 56:
                                this.bitField0_ |= 32;
                                this.expandedTypeId_ = input.readInt32();
                            case 66:
                                if ((mutable_bitField0_ & 128) != 128) {
                                    this.annotation_ = new ArrayList();
                                    mutable_bitField0_ |= 128;
                                }
                                this.annotation_.add(input.readMessage(Annotation.PARSER, extensionRegistry));
                            case 248:
                                if ((mutable_bitField0_ & 256) != 256) {
                                    this.versionRequirement_ = new ArrayList();
                                    mutable_bitField0_ |= 256;
                                }
                                this.versionRequirement_.add(Integer.valueOf(input.readInt32()));
                            case 250:
                                int length = input.readRawVarint32();
                                int limit = input.pushLimit(length);
                                if ((mutable_bitField0_ & 256) != 256 && input.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_ = new ArrayList();
                                    mutable_bitField0_ |= 256;
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.versionRequirement_.add(Integer.valueOf(input.readInt32()));
                                }
                                input.popLimit(limit);
                                break;
                            default:
                                if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 4) == 4) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                }
                if ((mutable_bitField0_ & 128) == 128) {
                    this.annotation_ = Collections.unmodifiableList(this.annotation_);
                }
                if ((mutable_bitField0_ & 256) == 256) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 4) == 4) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                }
                if ((mutable_bitField0_ & 128) == 128) {
                    this.annotation_ = Collections.unmodifiableList(this.annotation_);
                }
                if ((mutable_bitField0_ & 256) == 256) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<TypeAlias> getParserForType() {
            return PARSER;
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getFlags() {
            return this.flags_;
        }

        public boolean hasName() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getName() {
            return this.name_;
        }

        public List<TypeParameter> getTypeParameterList() {
            return this.typeParameter_;
        }

        public int getTypeParameterCount() {
            return this.typeParameter_.size();
        }

        public TypeParameter getTypeParameter(int index) {
            return this.typeParameter_.get(index);
        }

        public boolean hasUnderlyingType() {
            return (this.bitField0_ & 4) == 4;
        }

        public Type getUnderlyingType() {
            return this.underlyingType_;
        }

        public boolean hasUnderlyingTypeId() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getUnderlyingTypeId() {
            return this.underlyingTypeId_;
        }

        public boolean hasExpandedType() {
            return (this.bitField0_ & 16) == 16;
        }

        public Type getExpandedType() {
            return this.expandedType_;
        }

        public boolean hasExpandedTypeId() {
            return (this.bitField0_ & 32) == 32;
        }

        public int getExpandedTypeId() {
            return this.expandedTypeId_;
        }

        public List<Annotation> getAnnotationList() {
            return this.annotation_;
        }

        public int getAnnotationCount() {
            return this.annotation_.size();
        }

        public Annotation getAnnotation(int index) {
            return this.annotation_.get(index);
        }

        public List<Integer> getVersionRequirementList() {
            return this.versionRequirement_;
        }

        private void initFields() {
            this.flags_ = 6;
            this.name_ = 0;
            this.typeParameter_ = Collections.emptyList();
            this.underlyingType_ = Type.getDefaultInstance();
            this.underlyingTypeId_ = 0;
            this.expandedType_ = Type.getDefaultInstance();
            this.expandedTypeId_ = 0;
            this.annotation_ = Collections.emptyList();
            this.versionRequirement_ = Collections.emptyList();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasName()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            for (int i = 0; i < getTypeParameterCount(); i++) {
                if (!getTypeParameter(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (hasUnderlyingType() && !getUnderlyingType().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (hasExpandedType() && !getExpandedType().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            for (int i2 = 0; i2 < getAnnotationCount(); i2++) {
                if (!getAnnotation(i2).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            GeneratedMessageLite.ExtendableMessage<MessageType>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.flags_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.name_);
            }
            for (int i = 0; i < this.typeParameter_.size(); i++) {
                output.writeMessage(3, this.typeParameter_.get(i));
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(4, this.underlyingType_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(5, this.underlyingTypeId_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeMessage(6, this.expandedType_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(7, this.expandedTypeId_);
            }
            for (int i2 = 0; i2 < this.annotation_.size(); i2++) {
                output.writeMessage(8, this.annotation_.get(i2));
            }
            for (int i3 = 0; i3 < this.versionRequirement_.size(); i3++) {
                output.writeInt32(31, this.versionRequirement_.get(i3).intValue());
            }
            extensionWriterNewExtensionWriter.writeUntil(200, output);
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.flags_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.name_);
            }
            for (int i = 0; i < this.typeParameter_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(3, this.typeParameter_.get(i));
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(4, this.underlyingType_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(5, this.underlyingTypeId_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeMessageSize(6, this.expandedType_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(7, this.expandedTypeId_);
            }
            for (int i2 = 0; i2 < this.annotation_.size(); i2++) {
                size2 += CodedOutputStream.computeMessageSize(8, this.annotation_.get(i2));
            }
            int dataSize = 0;
            for (int i3 = 0; i3 < this.versionRequirement_.size(); i3++) {
                dataSize += CodedOutputStream.computeInt32SizeNoTag(this.versionRequirement_.get(i3).intValue());
            }
            int size3 = size2 + dataSize + (2 * getVersionRequirementList().size()) + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static TypeAlias parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(TypeAlias prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$TypeAlias$Builder.class */
        public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<TypeAlias, Builder> implements TypeAliasOrBuilder {
            private int bitField0_;
            private int name_;
            private int underlyingTypeId_;
            private int expandedTypeId_;
            private int flags_ = 6;
            private List<TypeParameter> typeParameter_ = Collections.emptyList();
            private Type underlyingType_ = Type.getDefaultInstance();
            private Type expandedType_ = Type.getDefaultInstance();
            private List<Annotation> annotation_ = Collections.emptyList();
            private List<Integer> versionRequirement_ = Collections.emptyList();

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.ExtendableBuilder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((TypeAlias) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public TypeAlias getDefaultInstanceForType() {
                return TypeAlias.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public TypeAlias build() {
                TypeAlias result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public TypeAlias buildPartial() {
                TypeAlias result = new TypeAlias(this, (AnonymousClass1) null);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.flags_ = this.flags_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.name_ = this.name_;
                if ((this.bitField0_ & 4) == 4) {
                    this.typeParameter_ = Collections.unmodifiableList(this.typeParameter_);
                    this.bitField0_ &= -5;
                }
                result.typeParameter_ = this.typeParameter_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 4;
                }
                result.underlyingType_ = this.underlyingType_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 8;
                }
                result.underlyingTypeId_ = this.underlyingTypeId_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 16;
                }
                result.expandedType_ = this.expandedType_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 32;
                }
                result.expandedTypeId_ = this.expandedTypeId_;
                if ((this.bitField0_ & 128) == 128) {
                    this.annotation_ = Collections.unmodifiableList(this.annotation_);
                    this.bitField0_ &= -129;
                }
                result.annotation_ = this.annotation_;
                if ((this.bitField0_ & 256) == 256) {
                    this.versionRequirement_ = Collections.unmodifiableList(this.versionRequirement_);
                    this.bitField0_ &= -257;
                }
                result.versionRequirement_ = this.versionRequirement_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(TypeAlias other) {
                if (other == TypeAlias.getDefaultInstance()) {
                    return this;
                }
                if (other.hasFlags()) {
                    setFlags(other.getFlags());
                }
                if (other.hasName()) {
                    setName(other.getName());
                }
                if (!other.typeParameter_.isEmpty()) {
                    if (this.typeParameter_.isEmpty()) {
                        this.typeParameter_ = other.typeParameter_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureTypeParameterIsMutable();
                        this.typeParameter_.addAll(other.typeParameter_);
                    }
                }
                if (other.hasUnderlyingType()) {
                    mergeUnderlyingType(other.getUnderlyingType());
                }
                if (other.hasUnderlyingTypeId()) {
                    setUnderlyingTypeId(other.getUnderlyingTypeId());
                }
                if (other.hasExpandedType()) {
                    mergeExpandedType(other.getExpandedType());
                }
                if (other.hasExpandedTypeId()) {
                    setExpandedTypeId(other.getExpandedTypeId());
                }
                if (!other.annotation_.isEmpty()) {
                    if (this.annotation_.isEmpty()) {
                        this.annotation_ = other.annotation_;
                        this.bitField0_ &= -129;
                    } else {
                        ensureAnnotationIsMutable();
                        this.annotation_.addAll(other.annotation_);
                    }
                }
                if (!other.versionRequirement_.isEmpty()) {
                    if (this.versionRequirement_.isEmpty()) {
                        this.versionRequirement_ = other.versionRequirement_;
                        this.bitField0_ &= -257;
                    } else {
                        ensureVersionRequirementIsMutable();
                        this.versionRequirement_.addAll(other.versionRequirement_);
                    }
                }
                mergeExtensionFields(other);
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasName()) {
                    return false;
                }
                for (int i = 0; i < getTypeParameterCount(); i++) {
                    if (!getTypeParameter(i).isInitialized()) {
                        return false;
                    }
                }
                if (hasUnderlyingType() && !getUnderlyingType().isInitialized()) {
                    return false;
                }
                if (hasExpandedType() && !getExpandedType().isInitialized()) {
                    return false;
                }
                for (int i2 = 0; i2 < getAnnotationCount(); i2++) {
                    if (!getAnnotation(i2).isInitialized()) {
                        return false;
                    }
                }
                if (!extensionsAreInitialized()) {
                    return false;
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                TypeAlias parsedMessage = null;
                try {
                    try {
                        parsedMessage = TypeAlias.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (TypeAlias) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            public Builder setFlags(int value) {
                this.bitField0_ |= 1;
                this.flags_ = value;
                return this;
            }

            public boolean hasName() {
                return (this.bitField0_ & 2) == 2;
            }

            public Builder setName(int value) {
                this.bitField0_ |= 2;
                this.name_ = value;
                return this;
            }

            private void ensureTypeParameterIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.typeParameter_ = new ArrayList(this.typeParameter_);
                    this.bitField0_ |= 4;
                }
            }

            public int getTypeParameterCount() {
                return this.typeParameter_.size();
            }

            public TypeParameter getTypeParameter(int index) {
                return this.typeParameter_.get(index);
            }

            public boolean hasUnderlyingType() {
                return (this.bitField0_ & 8) == 8;
            }

            public Type getUnderlyingType() {
                return this.underlyingType_;
            }

            public Builder mergeUnderlyingType(Type value) {
                if ((this.bitField0_ & 8) == 8 && this.underlyingType_ != Type.getDefaultInstance()) {
                    this.underlyingType_ = Type.newBuilder(this.underlyingType_).mergeFrom(value).buildPartial();
                } else {
                    this.underlyingType_ = value;
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setUnderlyingTypeId(int value) {
                this.bitField0_ |= 16;
                this.underlyingTypeId_ = value;
                return this;
            }

            public boolean hasExpandedType() {
                return (this.bitField0_ & 32) == 32;
            }

            public Type getExpandedType() {
                return this.expandedType_;
            }

            public Builder mergeExpandedType(Type value) {
                if ((this.bitField0_ & 32) == 32 && this.expandedType_ != Type.getDefaultInstance()) {
                    this.expandedType_ = Type.newBuilder(this.expandedType_).mergeFrom(value).buildPartial();
                } else {
                    this.expandedType_ = value;
                }
                this.bitField0_ |= 32;
                return this;
            }

            public Builder setExpandedTypeId(int value) {
                this.bitField0_ |= 64;
                this.expandedTypeId_ = value;
                return this;
            }

            private void ensureAnnotationIsMutable() {
                if ((this.bitField0_ & 128) != 128) {
                    this.annotation_ = new ArrayList(this.annotation_);
                    this.bitField0_ |= 128;
                }
            }

            public int getAnnotationCount() {
                return this.annotation_.size();
            }

            public Annotation getAnnotation(int index) {
                return this.annotation_.get(index);
            }

            private void ensureVersionRequirementIsMutable() {
                if ((this.bitField0_ & 256) != 256) {
                    this.versionRequirement_ = new ArrayList(this.versionRequirement_);
                    this.bitField0_ |= 256;
                }
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$EnumEntry.class */
    public static final class EnumEntry extends GeneratedMessageLite.ExtendableMessage<EnumEntry> implements EnumEntryOrBuilder {
        private final ByteString unknownFields;
        private int bitField0_;
        private int name_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<EnumEntry> PARSER = new AbstractParser<EnumEntry>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public EnumEntry parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new EnumEntry(input, extensionRegistry, null);
            }
        };
        private static final EnumEntry defaultInstance = new EnumEntry(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        /* synthetic */ EnumEntry(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ EnumEntry(GeneratedMessageLite.ExtendableBuilder x0, AnonymousClass1 x1) {
            this((GeneratedMessageLite.ExtendableBuilder<EnumEntry, ?>) x0);
        }

        private EnumEntry(GeneratedMessageLite.ExtendableBuilder<EnumEntry, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private EnumEntry(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static EnumEntry getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public EnumEntry getDefaultInstanceForType() {
            return defaultInstance;
        }

        private EnumEntry(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 8:
                                this.bitField0_ |= 1;
                                this.name_ = input.readInt32();
                            default:
                                if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<EnumEntry> getParserForType() {
            return PARSER;
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getName() {
            return this.name_;
        }

        private void initFields() {
            this.name_ = 0;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            GeneratedMessageLite.ExtendableMessage<MessageType>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.name_);
            }
            extensionWriterNewExtensionWriter.writeUntil(200, output);
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.name_);
            }
            int size3 = size2 + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(EnumEntry prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$EnumEntry$Builder.class */
        public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<EnumEntry, Builder> implements EnumEntryOrBuilder {
            private int bitField0_;
            private int name_;

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.ExtendableBuilder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((EnumEntry) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public EnumEntry getDefaultInstanceForType() {
                return EnumEntry.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public EnumEntry build() {
                EnumEntry result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public EnumEntry buildPartial() {
                EnumEntry result = new EnumEntry(this, (AnonymousClass1) null);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.name_ = this.name_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(EnumEntry other) {
                if (other == EnumEntry.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    setName(other.getName());
                }
                mergeExtensionFields(other);
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!extensionsAreInitialized()) {
                    return false;
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                EnumEntry parsedMessage = null;
                try {
                    try {
                        parsedMessage = EnumEntry.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (EnumEntry) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            public Builder setName(int value) {
                this.bitField0_ |= 1;
                this.name_ = value;
                return this;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$VersionRequirement.class */
    public static final class VersionRequirement extends GeneratedMessageLite implements VersionRequirementOrBuilder {
        private final ByteString unknownFields;
        private int bitField0_;
        private int version_;
        private int versionFull_;
        private Level level_;
        private int errorCode_;
        private int message_;
        private VersionKind versionKind_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<VersionRequirement> PARSER = new AbstractParser<VersionRequirement>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public VersionRequirement parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new VersionRequirement(input, extensionRegistry, null);
            }
        };
        private static final VersionRequirement defaultInstance = new VersionRequirement(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        /* synthetic */ VersionRequirement(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ VersionRequirement(GeneratedMessageLite.Builder x0, AnonymousClass1 x1) {
            this(x0);
        }

        private VersionRequirement(GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private VersionRequirement(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static VersionRequirement getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public VersionRequirement getDefaultInstanceForType() {
            return defaultInstance;
        }

        private VersionRequirement(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 8:
                                this.bitField0_ |= 1;
                                this.version_ = input.readInt32();
                            case 16:
                                this.bitField0_ |= 2;
                                this.versionFull_ = input.readInt32();
                            case 24:
                                int rawValue = input.readEnum();
                                Level value = Level.valueOf(rawValue);
                                if (value == null) {
                                    unknownFieldsCodedOutput.writeRawVarint32(tag);
                                    unknownFieldsCodedOutput.writeRawVarint32(rawValue);
                                } else {
                                    this.bitField0_ |= 4;
                                    this.level_ = value;
                                }
                            case 32:
                                this.bitField0_ |= 8;
                                this.errorCode_ = input.readInt32();
                            case 40:
                                this.bitField0_ |= 16;
                                this.message_ = input.readInt32();
                            case 48:
                                int rawValue2 = input.readEnum();
                                VersionKind value2 = VersionKind.valueOf(rawValue2);
                                if (value2 == null) {
                                    unknownFieldsCodedOutput.writeRawVarint32(tag);
                                    unknownFieldsCodedOutput.writeRawVarint32(rawValue2);
                                } else {
                                    this.bitField0_ |= 32;
                                    this.versionKind_ = value2;
                                }
                            default:
                                if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<VersionRequirement> getParserForType() {
            return PARSER;
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$VersionRequirement$Level.class */
        public enum Level implements Internal.EnumLite {
            WARNING(0, 0),
            ERROR(1, 1),
            HIDDEN(2, 2);

            private static Internal.EnumLiteMap<Level> internalValueMap = new Internal.EnumLiteMap<Level>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Level.1
                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                public /* bridge */ /* synthetic */ Internal.EnumLite findValueByNumber(int x0) {
                    return findValueByNumber(x0);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                public Level findValueByNumber(int number) {
                    return Level.valueOf(number);
                }
            };
            private final int value;

            static {
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            public static Level valueOf(int value) {
                switch (value) {
                    case 0:
                        return WARNING;
                    case 1:
                        return ERROR;
                    case 2:
                        return HIDDEN;
                    default:
                        return null;
                }
            }

            Level(int index, int value) {
                this.value = value;
            }
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$VersionRequirement$VersionKind.class */
        public enum VersionKind implements Internal.EnumLite {
            LANGUAGE_VERSION(0, 0),
            COMPILER_VERSION(1, 1),
            API_VERSION(2, 2);

            private static Internal.EnumLiteMap<VersionKind> internalValueMap = new Internal.EnumLiteMap<VersionKind>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.VersionKind.1
                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                public /* bridge */ /* synthetic */ Internal.EnumLite findValueByNumber(int x0) {
                    return findValueByNumber(x0);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                public VersionKind findValueByNumber(int number) {
                    return VersionKind.valueOf(number);
                }
            };
            private final int value;

            static {
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            public static VersionKind valueOf(int value) {
                switch (value) {
                    case 0:
                        return LANGUAGE_VERSION;
                    case 1:
                        return COMPILER_VERSION;
                    case 2:
                        return API_VERSION;
                    default:
                        return null;
                }
            }

            VersionKind(int index, int value) {
                this.value = value;
            }
        }

        public boolean hasVersion() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getVersion() {
            return this.version_;
        }

        public boolean hasVersionFull() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getVersionFull() {
            return this.versionFull_;
        }

        public boolean hasLevel() {
            return (this.bitField0_ & 4) == 4;
        }

        public Level getLevel() {
            return this.level_;
        }

        public boolean hasErrorCode() {
            return (this.bitField0_ & 8) == 8;
        }

        public int getErrorCode() {
            return this.errorCode_;
        }

        public boolean hasMessage() {
            return (this.bitField0_ & 16) == 16;
        }

        public int getMessage() {
            return this.message_;
        }

        public boolean hasVersionKind() {
            return (this.bitField0_ & 32) == 32;
        }

        public VersionKind getVersionKind() {
            return this.versionKind_;
        }

        private void initFields() {
            this.version_ = 0;
            this.versionFull_ = 0;
            this.level_ = Level.ERROR;
            this.errorCode_ = 0;
            this.message_ = 0;
            this.versionKind_ = VersionKind.LANGUAGE_VERSION;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.version_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.versionFull_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeEnum(3, this.level_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.errorCode_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.message_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeEnum(6, this.versionKind_.getNumber());
            }
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.version_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.versionFull_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeEnumSize(3, this.level_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.errorCode_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.message_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeEnumSize(6, this.versionKind_.getNumber());
            }
            int size3 = size2 + this.unknownFields.size();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(VersionRequirement prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$VersionRequirement$Builder.class */
        public static final class Builder extends GeneratedMessageLite.Builder<VersionRequirement, Builder> implements VersionRequirementOrBuilder {
            private int bitField0_;
            private int version_;
            private int versionFull_;
            private int errorCode_;
            private int message_;
            private Level level_ = Level.ERROR;
            private VersionKind versionKind_ = VersionKind.LANGUAGE_VERSION;

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((VersionRequirement) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public VersionRequirement getDefaultInstanceForType() {
                return VersionRequirement.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public VersionRequirement build() {
                VersionRequirement result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public VersionRequirement buildPartial() {
                VersionRequirement result = new VersionRequirement(this, (AnonymousClass1) null);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.version_ = this.version_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.versionFull_ = this.versionFull_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.level_ = this.level_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.errorCode_ = this.errorCode_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.message_ = this.message_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.versionKind_ = this.versionKind_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(VersionRequirement other) {
                if (other == VersionRequirement.getDefaultInstance()) {
                    return this;
                }
                if (other.hasVersion()) {
                    setVersion(other.getVersion());
                }
                if (other.hasVersionFull()) {
                    setVersionFull(other.getVersionFull());
                }
                if (other.hasLevel()) {
                    setLevel(other.getLevel());
                }
                if (other.hasErrorCode()) {
                    setErrorCode(other.getErrorCode());
                }
                if (other.hasMessage()) {
                    setMessage(other.getMessage());
                }
                if (other.hasVersionKind()) {
                    setVersionKind(other.getVersionKind());
                }
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                VersionRequirement parsedMessage = null;
                try {
                    try {
                        parsedMessage = VersionRequirement.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (VersionRequirement) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            public Builder setVersion(int value) {
                this.bitField0_ |= 1;
                this.version_ = value;
                return this;
            }

            public Builder setVersionFull(int value) {
                this.bitField0_ |= 2;
                this.versionFull_ = value;
                return this;
            }

            public Builder setLevel(Level value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.level_ = value;
                return this;
            }

            public Builder setErrorCode(int value) {
                this.bitField0_ |= 8;
                this.errorCode_ = value;
                return this;
            }

            public Builder setMessage(int value) {
                this.bitField0_ |= 16;
                this.message_ = value;
                return this;
            }

            public Builder setVersionKind(VersionKind value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 32;
                this.versionKind_ = value;
                return this;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$VersionRequirementTable.class */
    public static final class VersionRequirementTable extends GeneratedMessageLite implements VersionRequirementTableOrBuilder {
        private final ByteString unknownFields;
        private List<VersionRequirement> requirement_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<VersionRequirementTable> PARSER = new AbstractParser<VersionRequirementTable>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public VersionRequirementTable parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new VersionRequirementTable(input, extensionRegistry, null);
            }
        };
        private static final VersionRequirementTable defaultInstance = new VersionRequirementTable(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        /* synthetic */ VersionRequirementTable(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ VersionRequirementTable(GeneratedMessageLite.Builder x0, AnonymousClass1 x1) {
            this(x0);
        }

        private VersionRequirementTable(GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private VersionRequirementTable(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static VersionRequirementTable getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public VersionRequirementTable getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private VersionRequirementTable(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                case 10:
                                    if ((mutable_bitField0_ & 1) != 1) {
                                        this.requirement_ = new ArrayList();
                                        mutable_bitField0_ |= 1;
                                    }
                                    this.requirement_.add(input.readMessage(VersionRequirement.PARSER, extensionRegistry));
                                default:
                                    if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                        done = true;
                                    }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 1) == 1) {
                    this.requirement_ = Collections.unmodifiableList(this.requirement_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 1) == 1) {
                    this.requirement_ = Collections.unmodifiableList(this.requirement_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<VersionRequirementTable> getParserForType() {
            return PARSER;
        }

        public List<VersionRequirement> getRequirementList() {
            return this.requirement_;
        }

        public int getRequirementCount() {
            return this.requirement_.size();
        }

        private void initFields() {
            this.requirement_ = Collections.emptyList();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            for (int i = 0; i < this.requirement_.size(); i++) {
                output.writeMessage(1, this.requirement_.get(i));
            }
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            for (int i = 0; i < this.requirement_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.requirement_.get(i));
            }
            int size3 = size2 + this.unknownFields.size();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(VersionRequirementTable prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$VersionRequirementTable$Builder.class */
        public static final class Builder extends GeneratedMessageLite.Builder<VersionRequirementTable, Builder> implements VersionRequirementTableOrBuilder {
            private int bitField0_;
            private List<VersionRequirement> requirement_ = Collections.emptyList();

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((VersionRequirementTable) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public VersionRequirementTable getDefaultInstanceForType() {
                return VersionRequirementTable.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public VersionRequirementTable build() {
                VersionRequirementTable result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public VersionRequirementTable buildPartial() {
                VersionRequirementTable result = new VersionRequirementTable(this, (AnonymousClass1) null);
                int i = this.bitField0_;
                if ((this.bitField0_ & 1) == 1) {
                    this.requirement_ = Collections.unmodifiableList(this.requirement_);
                    this.bitField0_ &= -2;
                }
                result.requirement_ = this.requirement_;
                return result;
            }

            public Builder mergeFrom(VersionRequirementTable other) {
                if (other == VersionRequirementTable.getDefaultInstance()) {
                    return this;
                }
                if (!other.requirement_.isEmpty()) {
                    if (this.requirement_.isEmpty()) {
                        this.requirement_ = other.requirement_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureRequirementIsMutable();
                        this.requirement_.addAll(other.requirement_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                VersionRequirementTable parsedMessage = null;
                try {
                    try {
                        parsedMessage = VersionRequirementTable.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (VersionRequirementTable) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            private void ensureRequirementIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.requirement_ = new ArrayList(this.requirement_);
                    this.bitField0_ |= 1;
                }
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$PackageFragment.class */
    public static final class PackageFragment extends GeneratedMessageLite.ExtendableMessage<PackageFragment> implements PackageFragmentOrBuilder {
        private final ByteString unknownFields;
        private int bitField0_;
        private StringTable strings_;
        private QualifiedNameTable qualifiedNames_;
        private Package package_;
        private List<Class> class__;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<PackageFragment> PARSER = new AbstractParser<PackageFragment>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public PackageFragment parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new PackageFragment(input, extensionRegistry, null);
            }
        };
        private static final PackageFragment defaultInstance = new PackageFragment(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        /* synthetic */ PackageFragment(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ PackageFragment(GeneratedMessageLite.ExtendableBuilder x0, AnonymousClass1 x1) {
            this((GeneratedMessageLite.ExtendableBuilder<PackageFragment, ?>) x0);
        }

        private PackageFragment(GeneratedMessageLite.ExtendableBuilder<PackageFragment, ?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private PackageFragment(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static PackageFragment getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public PackageFragment getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private PackageFragment(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 10:
                                StringTable.Builder subBuilder = null;
                                subBuilder = (this.bitField0_ & 1) == 1 ? this.strings_.toBuilder() : subBuilder;
                                this.strings_ = (StringTable) input.readMessage(StringTable.PARSER, extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(this.strings_);
                                    this.strings_ = subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            case 18:
                                QualifiedNameTable.Builder subBuilder2 = null;
                                subBuilder2 = (this.bitField0_ & 2) == 2 ? this.qualifiedNames_.toBuilder() : subBuilder2;
                                this.qualifiedNames_ = (QualifiedNameTable) input.readMessage(QualifiedNameTable.PARSER, extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom(this.qualifiedNames_);
                                    this.qualifiedNames_ = subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            case 26:
                                Package.Builder subBuilder3 = null;
                                subBuilder3 = (this.bitField0_ & 4) == 4 ? this.package_.toBuilder() : subBuilder3;
                                this.package_ = (Package) input.readMessage(Package.PARSER, extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom(this.package_);
                                    this.package_ = subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            case 34:
                                if ((mutable_bitField0_ & 8) != 8) {
                                    this.class__ = new ArrayList();
                                    mutable_bitField0_ |= 8;
                                }
                                this.class__.add(input.readMessage(Class.PARSER, extensionRegistry));
                            default:
                                if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 8) == 8) {
                    this.class__ = Collections.unmodifiableList(this.class__);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 8) == 8) {
                    this.class__ = Collections.unmodifiableList(this.class__);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<PackageFragment> getParserForType() {
            return PARSER;
        }

        public boolean hasStrings() {
            return (this.bitField0_ & 1) == 1;
        }

        public StringTable getStrings() {
            return this.strings_;
        }

        public boolean hasQualifiedNames() {
            return (this.bitField0_ & 2) == 2;
        }

        public QualifiedNameTable getQualifiedNames() {
            return this.qualifiedNames_;
        }

        public boolean hasPackage() {
            return (this.bitField0_ & 4) == 4;
        }

        public Package getPackage() {
            return this.package_;
        }

        public List<Class> getClass_List() {
            return this.class__;
        }

        public int getClass_Count() {
            return this.class__.size();
        }

        public Class getClass_(int index) {
            return this.class__.get(index);
        }

        private void initFields() {
            this.strings_ = StringTable.getDefaultInstance();
            this.qualifiedNames_ = QualifiedNameTable.getDefaultInstance();
            this.package_ = Package.getDefaultInstance();
            this.class__ = Collections.emptyList();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (hasQualifiedNames() && !getQualifiedNames().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (hasPackage() && !getPackage().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            for (int i = 0; i < getClass_Count(); i++) {
                if (!getClass_(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (!extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            GeneratedMessageLite.ExtendableMessage<MessageType>.ExtensionWriter extensionWriterNewExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, this.strings_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, this.qualifiedNames_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, this.package_);
            }
            for (int i = 0; i < this.class__.size(); i++) {
                output.writeMessage(4, this.class__.get(i));
            }
            extensionWriterNewExtensionWriter.writeUntil(200, output);
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeMessageSize(1, this.strings_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, this.qualifiedNames_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, this.package_);
            }
            for (int i = 0; i < this.class__.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(4, this.class__.get(i));
            }
            int size3 = size2 + extensionsSerializedSize() + this.unknownFields.size();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static PackageFragment parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(PackageFragment prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$PackageFragment$Builder.class */
        public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<PackageFragment, Builder> implements PackageFragmentOrBuilder {
            private int bitField0_;
            private StringTable strings_ = StringTable.getDefaultInstance();
            private QualifiedNameTable qualifiedNames_ = QualifiedNameTable.getDefaultInstance();
            private Package package_ = Package.getDefaultInstance();
            private List<Class> class__ = Collections.emptyList();

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.ExtendableBuilder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((PackageFragment) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableBuilder, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public PackageFragment getDefaultInstanceForType() {
                return PackageFragment.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public PackageFragment build() {
                PackageFragment result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public PackageFragment buildPartial() {
                PackageFragment result = new PackageFragment(this, (AnonymousClass1) null);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.strings_ = this.strings_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.qualifiedNames_ = this.qualifiedNames_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.package_ = this.package_;
                if ((this.bitField0_ & 8) == 8) {
                    this.class__ = Collections.unmodifiableList(this.class__);
                    this.bitField0_ &= -9;
                }
                result.class__ = this.class__;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(PackageFragment other) {
                if (other == PackageFragment.getDefaultInstance()) {
                    return this;
                }
                if (other.hasStrings()) {
                    mergeStrings(other.getStrings());
                }
                if (other.hasQualifiedNames()) {
                    mergeQualifiedNames(other.getQualifiedNames());
                }
                if (other.hasPackage()) {
                    mergePackage(other.getPackage());
                }
                if (!other.class__.isEmpty()) {
                    if (this.class__.isEmpty()) {
                        this.class__ = other.class__;
                        this.bitField0_ &= -9;
                    } else {
                        ensureClass_IsMutable();
                        this.class__.addAll(other.class__);
                    }
                }
                mergeExtensionFields(other);
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (hasQualifiedNames() && !getQualifiedNames().isInitialized()) {
                    return false;
                }
                if (hasPackage() && !getPackage().isInitialized()) {
                    return false;
                }
                for (int i = 0; i < getClass_Count(); i++) {
                    if (!getClass_(i).isInitialized()) {
                        return false;
                    }
                }
                if (!extensionsAreInitialized()) {
                    return false;
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                PackageFragment parsedMessage = null;
                try {
                    try {
                        parsedMessage = PackageFragment.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (PackageFragment) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            public Builder mergeStrings(StringTable value) {
                if ((this.bitField0_ & 1) == 1 && this.strings_ != StringTable.getDefaultInstance()) {
                    this.strings_ = StringTable.newBuilder(this.strings_).mergeFrom(value).buildPartial();
                } else {
                    this.strings_ = value;
                }
                this.bitField0_ |= 1;
                return this;
            }

            public boolean hasQualifiedNames() {
                return (this.bitField0_ & 2) == 2;
            }

            public QualifiedNameTable getQualifiedNames() {
                return this.qualifiedNames_;
            }

            public Builder mergeQualifiedNames(QualifiedNameTable value) {
                if ((this.bitField0_ & 2) == 2 && this.qualifiedNames_ != QualifiedNameTable.getDefaultInstance()) {
                    this.qualifiedNames_ = QualifiedNameTable.newBuilder(this.qualifiedNames_).mergeFrom(value).buildPartial();
                } else {
                    this.qualifiedNames_ = value;
                }
                this.bitField0_ |= 2;
                return this;
            }

            public boolean hasPackage() {
                return (this.bitField0_ & 4) == 4;
            }

            public Package getPackage() {
                return this.package_;
            }

            public Builder mergePackage(Package value) {
                if ((this.bitField0_ & 4) == 4 && this.package_ != Package.getDefaultInstance()) {
                    this.package_ = Package.newBuilder(this.package_).mergeFrom(value).buildPartial();
                } else {
                    this.package_ = value;
                }
                this.bitField0_ |= 4;
                return this;
            }

            private void ensureClass_IsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.class__ = new ArrayList(this.class__);
                    this.bitField0_ |= 8;
                }
            }

            public int getClass_Count() {
                return this.class__.size();
            }

            public Class getClass_(int index) {
                return this.class__.get(index);
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Contract.class */
    public static final class Contract extends GeneratedMessageLite implements ContractOrBuilder {
        private final ByteString unknownFields;
        private List<Effect> effect_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<Contract> PARSER = new AbstractParser<Contract>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Contract.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public Contract parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Contract(input, extensionRegistry, null);
            }
        };
        private static final Contract defaultInstance = new Contract(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        /* synthetic */ Contract(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ Contract(GeneratedMessageLite.Builder x0, AnonymousClass1 x1) {
            this(x0);
        }

        private Contract(GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Contract(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Contract getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public Contract getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Contract(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                case 10:
                                    if ((mutable_bitField0_ & 1) != 1) {
                                        this.effect_ = new ArrayList();
                                        mutable_bitField0_ |= 1;
                                    }
                                    this.effect_.add(input.readMessage(Effect.PARSER, extensionRegistry));
                                default:
                                    if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                        done = true;
                                    }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 1) == 1) {
                    this.effect_ = Collections.unmodifiableList(this.effect_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 1) == 1) {
                    this.effect_ = Collections.unmodifiableList(this.effect_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<Contract> getParserForType() {
            return PARSER;
        }

        public int getEffectCount() {
            return this.effect_.size();
        }

        public Effect getEffect(int index) {
            return this.effect_.get(index);
        }

        private void initFields() {
            this.effect_ = Collections.emptyList();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < getEffectCount(); i++) {
                if (!getEffect(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            for (int i = 0; i < this.effect_.size(); i++) {
                output.writeMessage(1, this.effect_.get(i));
            }
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            for (int i = 0; i < this.effect_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.effect_.get(i));
            }
            int size3 = size2 + this.unknownFields.size();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Contract prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Contract$Builder.class */
        public static final class Builder extends GeneratedMessageLite.Builder<Contract, Builder> implements ContractOrBuilder {
            private int bitField0_;
            private List<Effect> effect_ = Collections.emptyList();

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((Contract) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public Contract getDefaultInstanceForType() {
                return Contract.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Contract build() {
                Contract result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public Contract buildPartial() {
                Contract result = new Contract(this, (AnonymousClass1) null);
                int i = this.bitField0_;
                if ((this.bitField0_ & 1) == 1) {
                    this.effect_ = Collections.unmodifiableList(this.effect_);
                    this.bitField0_ &= -2;
                }
                result.effect_ = this.effect_;
                return result;
            }

            public Builder mergeFrom(Contract other) {
                if (other == Contract.getDefaultInstance()) {
                    return this;
                }
                if (!other.effect_.isEmpty()) {
                    if (this.effect_.isEmpty()) {
                        this.effect_ = other.effect_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureEffectIsMutable();
                        this.effect_.addAll(other.effect_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getEffectCount(); i++) {
                    if (!getEffect(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Contract parsedMessage = null;
                try {
                    try {
                        parsedMessage = Contract.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (Contract) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            private void ensureEffectIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.effect_ = new ArrayList(this.effect_);
                    this.bitField0_ |= 1;
                }
            }

            public int getEffectCount() {
                return this.effect_.size();
            }

            public Effect getEffect(int index) {
                return this.effect_.get(index);
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Effect.class */
    public static final class Effect extends GeneratedMessageLite implements EffectOrBuilder {
        private final ByteString unknownFields;
        private int bitField0_;
        private EffectType effectType_;
        private List<Expression> effectConstructorArgument_;
        private Expression conclusionOfConditionalEffect_;
        private InvocationKind kind_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<Effect> PARSER = new AbstractParser<Effect>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public Effect parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Effect(input, extensionRegistry, null);
            }
        };
        private static final Effect defaultInstance = new Effect(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        /* synthetic */ Effect(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ Effect(GeneratedMessageLite.Builder x0, AnonymousClass1 x1) {
            this(x0);
        }

        private Effect(GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Effect(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Effect getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public Effect getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Effect(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 8:
                                int rawValue = input.readEnum();
                                EffectType value = EffectType.valueOf(rawValue);
                                if (value == null) {
                                    unknownFieldsCodedOutput.writeRawVarint32(tag);
                                    unknownFieldsCodedOutput.writeRawVarint32(rawValue);
                                } else {
                                    this.bitField0_ |= 1;
                                    this.effectType_ = value;
                                }
                            case 18:
                                if ((mutable_bitField0_ & 2) != 2) {
                                    this.effectConstructorArgument_ = new ArrayList();
                                    mutable_bitField0_ |= 2;
                                }
                                this.effectConstructorArgument_.add(input.readMessage(Expression.PARSER, extensionRegistry));
                            case 26:
                                Expression.Builder subBuilder = null;
                                subBuilder = (this.bitField0_ & 2) == 2 ? this.conclusionOfConditionalEffect_.toBuilder() : subBuilder;
                                this.conclusionOfConditionalEffect_ = (Expression) input.readMessage(Expression.PARSER, extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(this.conclusionOfConditionalEffect_);
                                    this.conclusionOfConditionalEffect_ = subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            case 32:
                                int rawValue2 = input.readEnum();
                                InvocationKind value2 = InvocationKind.valueOf(rawValue2);
                                if (value2 == null) {
                                    unknownFieldsCodedOutput.writeRawVarint32(tag);
                                    unknownFieldsCodedOutput.writeRawVarint32(rawValue2);
                                } else {
                                    this.bitField0_ |= 4;
                                    this.kind_ = value2;
                                }
                            default:
                                if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 2) == 2) {
                    this.effectConstructorArgument_ = Collections.unmodifiableList(this.effectConstructorArgument_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 2) == 2) {
                    this.effectConstructorArgument_ = Collections.unmodifiableList(this.effectConstructorArgument_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<Effect> getParserForType() {
            return PARSER;
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Effect$EffectType.class */
        public enum EffectType implements Internal.EnumLite {
            RETURNS_CONSTANT(0, 0),
            CALLS(1, 1),
            RETURNS_NOT_NULL(2, 2);

            private static Internal.EnumLiteMap<EffectType> internalValueMap = new Internal.EnumLiteMap<EffectType>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.EffectType.1
                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                public /* bridge */ /* synthetic */ Internal.EnumLite findValueByNumber(int x0) {
                    return findValueByNumber(x0);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                public EffectType findValueByNumber(int number) {
                    return EffectType.valueOf(number);
                }
            };
            private final int value;

            static {
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            public static EffectType valueOf(int value) {
                switch (value) {
                    case 0:
                        return RETURNS_CONSTANT;
                    case 1:
                        return CALLS;
                    case 2:
                        return RETURNS_NOT_NULL;
                    default:
                        return null;
                }
            }

            EffectType(int index, int value) {
                this.value = value;
            }
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Effect$InvocationKind.class */
        public enum InvocationKind implements Internal.EnumLite {
            AT_MOST_ONCE(0, 0),
            EXACTLY_ONCE(1, 1),
            AT_LEAST_ONCE(2, 2);

            private static Internal.EnumLiteMap<InvocationKind> internalValueMap = new Internal.EnumLiteMap<InvocationKind>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Effect.InvocationKind.1
                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                public /* bridge */ /* synthetic */ Internal.EnumLite findValueByNumber(int x0) {
                    return findValueByNumber(x0);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                public InvocationKind findValueByNumber(int number) {
                    return InvocationKind.valueOf(number);
                }
            };
            private final int value;

            static {
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            public static InvocationKind valueOf(int value) {
                switch (value) {
                    case 0:
                        return AT_MOST_ONCE;
                    case 1:
                        return EXACTLY_ONCE;
                    case 2:
                        return AT_LEAST_ONCE;
                    default:
                        return null;
                }
            }

            InvocationKind(int index, int value) {
                this.value = value;
            }
        }

        public boolean hasEffectType() {
            return (this.bitField0_ & 1) == 1;
        }

        public EffectType getEffectType() {
            return this.effectType_;
        }

        public int getEffectConstructorArgumentCount() {
            return this.effectConstructorArgument_.size();
        }

        public Expression getEffectConstructorArgument(int index) {
            return this.effectConstructorArgument_.get(index);
        }

        public boolean hasConclusionOfConditionalEffect() {
            return (this.bitField0_ & 2) == 2;
        }

        public Expression getConclusionOfConditionalEffect() {
            return this.conclusionOfConditionalEffect_;
        }

        public boolean hasKind() {
            return (this.bitField0_ & 4) == 4;
        }

        public InvocationKind getKind() {
            return this.kind_;
        }

        private void initFields() {
            this.effectType_ = EffectType.RETURNS_CONSTANT;
            this.effectConstructorArgument_ = Collections.emptyList();
            this.conclusionOfConditionalEffect_ = Expression.getDefaultInstance();
            this.kind_ = InvocationKind.AT_MOST_ONCE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < getEffectConstructorArgumentCount(); i++) {
                if (!getEffectConstructorArgument(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (hasConclusionOfConditionalEffect() && !getConclusionOfConditionalEffect().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.effectType_.getNumber());
            }
            for (int i = 0; i < this.effectConstructorArgument_.size(); i++) {
                output.writeMessage(2, this.effectConstructorArgument_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(3, this.conclusionOfConditionalEffect_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeEnum(4, this.kind_.getNumber());
            }
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeEnumSize(1, this.effectType_.getNumber());
            }
            for (int i = 0; i < this.effectConstructorArgument_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(2, this.effectConstructorArgument_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(3, this.conclusionOfConditionalEffect_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeEnumSize(4, this.kind_.getNumber());
            }
            int size3 = size2 + this.unknownFields.size();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Effect prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Effect$Builder.class */
        public static final class Builder extends GeneratedMessageLite.Builder<Effect, Builder> implements EffectOrBuilder {
            private int bitField0_;
            private EffectType effectType_ = EffectType.RETURNS_CONSTANT;
            private List<Expression> effectConstructorArgument_ = Collections.emptyList();
            private Expression conclusionOfConditionalEffect_ = Expression.getDefaultInstance();
            private InvocationKind kind_ = InvocationKind.AT_MOST_ONCE;

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((Effect) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public Effect getDefaultInstanceForType() {
                return Effect.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Effect build() {
                Effect result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public Effect buildPartial() {
                Effect result = new Effect(this, (AnonymousClass1) null);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.effectType_ = this.effectType_;
                if ((this.bitField0_ & 2) == 2) {
                    this.effectConstructorArgument_ = Collections.unmodifiableList(this.effectConstructorArgument_);
                    this.bitField0_ &= -3;
                }
                result.effectConstructorArgument_ = this.effectConstructorArgument_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 2;
                }
                result.conclusionOfConditionalEffect_ = this.conclusionOfConditionalEffect_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 4;
                }
                result.kind_ = this.kind_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(Effect other) {
                if (other == Effect.getDefaultInstance()) {
                    return this;
                }
                if (other.hasEffectType()) {
                    setEffectType(other.getEffectType());
                }
                if (!other.effectConstructorArgument_.isEmpty()) {
                    if (this.effectConstructorArgument_.isEmpty()) {
                        this.effectConstructorArgument_ = other.effectConstructorArgument_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureEffectConstructorArgumentIsMutable();
                        this.effectConstructorArgument_.addAll(other.effectConstructorArgument_);
                    }
                }
                if (other.hasConclusionOfConditionalEffect()) {
                    mergeConclusionOfConditionalEffect(other.getConclusionOfConditionalEffect());
                }
                if (other.hasKind()) {
                    setKind(other.getKind());
                }
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getEffectConstructorArgumentCount(); i++) {
                    if (!getEffectConstructorArgument(i).isInitialized()) {
                        return false;
                    }
                }
                if (hasConclusionOfConditionalEffect() && !getConclusionOfConditionalEffect().isInitialized()) {
                    return false;
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Effect parsedMessage = null;
                try {
                    try {
                        parsedMessage = Effect.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (Effect) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            public Builder setEffectType(EffectType value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.effectType_ = value;
                return this;
            }

            private void ensureEffectConstructorArgumentIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.effectConstructorArgument_ = new ArrayList(this.effectConstructorArgument_);
                    this.bitField0_ |= 2;
                }
            }

            public int getEffectConstructorArgumentCount() {
                return this.effectConstructorArgument_.size();
            }

            public Expression getEffectConstructorArgument(int index) {
                return this.effectConstructorArgument_.get(index);
            }

            public boolean hasConclusionOfConditionalEffect() {
                return (this.bitField0_ & 4) == 4;
            }

            public Expression getConclusionOfConditionalEffect() {
                return this.conclusionOfConditionalEffect_;
            }

            public Builder mergeConclusionOfConditionalEffect(Expression value) {
                if ((this.bitField0_ & 4) == 4 && this.conclusionOfConditionalEffect_ != Expression.getDefaultInstance()) {
                    this.conclusionOfConditionalEffect_ = Expression.newBuilder(this.conclusionOfConditionalEffect_).mergeFrom(value).buildPartial();
                } else {
                    this.conclusionOfConditionalEffect_ = value;
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setKind(InvocationKind value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.kind_ = value;
                return this;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Expression.class */
    public static final class Expression extends GeneratedMessageLite implements ExpressionOrBuilder {
        private final ByteString unknownFields;
        private int bitField0_;
        private int flags_;
        private int valueParameterReference_;
        private ConstantValue constantValue_;
        private Type isInstanceType_;
        private int isInstanceTypeId_;
        private List<Expression> andArgument_;
        private List<Expression> orArgument_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        public static Parser<Expression> PARSER = new AbstractParser<Expression>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.1
            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public /* bridge */ /* synthetic */ Object parsePartialFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws InvalidProtocolBufferException {
                return parsePartialFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Parser
            public Expression parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Expression(input, extensionRegistry, null);
            }
        };
        private static final Expression defaultInstance = new Expression(true);

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
            return toBuilder();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
            return newBuilderForType();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return getDefaultInstanceForType();
        }

        /* synthetic */ Expression(CodedInputStream x0, ExtensionRegistryLite x1, AnonymousClass1 x2) throws InvalidProtocolBufferException {
            this(x0, x1);
        }

        /* synthetic */ Expression(GeneratedMessageLite.Builder x0, AnonymousClass1 x1) {
            this(x0);
        }

        private Expression(GeneratedMessageLite.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Expression(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static Expression getDefaultInstance() {
            return defaultInstance;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public Expression getDefaultInstanceForType() {
            return defaultInstance;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Expression(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            ByteString.Output unknownFieldsOutput = ByteString.newOutput();
            CodedOutputStream unknownFieldsCodedOutput = CodedOutputStream.newInstance(unknownFieldsOutput, 1);
            try {
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 8:
                                this.bitField0_ |= 1;
                                this.flags_ = input.readInt32();
                            case 16:
                                this.bitField0_ |= 2;
                                this.valueParameterReference_ = input.readInt32();
                            case 24:
                                int rawValue = input.readEnum();
                                ConstantValue value = ConstantValue.valueOf(rawValue);
                                if (value == null) {
                                    unknownFieldsCodedOutput.writeRawVarint32(tag);
                                    unknownFieldsCodedOutput.writeRawVarint32(rawValue);
                                } else {
                                    this.bitField0_ |= 4;
                                    this.constantValue_ = value;
                                }
                            case 34:
                                Type.Builder subBuilder = null;
                                subBuilder = (this.bitField0_ & 8) == 8 ? this.isInstanceType_.toBuilder() : subBuilder;
                                this.isInstanceType_ = (Type) input.readMessage(Type.PARSER, extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(this.isInstanceType_);
                                    this.isInstanceType_ = subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 8;
                            case 40:
                                this.bitField0_ |= 16;
                                this.isInstanceTypeId_ = input.readInt32();
                            case 50:
                                if ((mutable_bitField0_ & 32) != 32) {
                                    this.andArgument_ = new ArrayList();
                                    mutable_bitField0_ |= 32;
                                }
                                this.andArgument_.add(input.readMessage(PARSER, extensionRegistry));
                            case 58:
                                if ((mutable_bitField0_ & 64) != 64) {
                                    this.orArgument_ = new ArrayList();
                                    mutable_bitField0_ |= 64;
                                }
                                this.orArgument_.add(input.readMessage(PARSER, extensionRegistry));
                            default:
                                if (!parseUnknownField(input, unknownFieldsCodedOutput, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                }
                if ((mutable_bitField0_ & 32) == 32) {
                    this.andArgument_ = Collections.unmodifiableList(this.andArgument_);
                }
                if ((mutable_bitField0_ & 64) == 64) {
                    this.orArgument_ = Collections.unmodifiableList(this.orArgument_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th;
                }
                makeExtensionsImmutable();
            } catch (Throwable th2) {
                if ((mutable_bitField0_ & 32) == 32) {
                    this.andArgument_ = Collections.unmodifiableList(this.andArgument_);
                }
                if ((mutable_bitField0_ & 64) == 64) {
                    this.orArgument_ = Collections.unmodifiableList(this.orArgument_);
                }
                try {
                    unknownFieldsCodedOutput.flush();
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (IOException e4) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                } catch (Throwable th3) {
                    this.unknownFields = unknownFieldsOutput.toByteString();
                    throw th3;
                }
                makeExtensionsImmutable();
                throw th2;
            }
        }

        static {
            defaultInstance.initFields();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Parser<Expression> getParserForType() {
            return PARSER;
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Expression$ConstantValue.class */
        public enum ConstantValue implements Internal.EnumLite {
            TRUE(0, 0),
            FALSE(1, 1),
            NULL(2, 2);

            private static Internal.EnumLiteMap<ConstantValue> internalValueMap = new Internal.EnumLiteMap<ConstantValue>() { // from class: kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Expression.ConstantValue.1
                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                public /* bridge */ /* synthetic */ Internal.EnumLite findValueByNumber(int x0) {
                    return findValueByNumber(x0);
                }

                @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap
                public ConstantValue findValueByNumber(int number) {
                    return ConstantValue.valueOf(number);
                }
            };
            private final int value;

            static {
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            public static ConstantValue valueOf(int value) {
                switch (value) {
                    case 0:
                        return TRUE;
                    case 1:
                        return FALSE;
                    case 2:
                        return NULL;
                    default:
                        return null;
                }
            }

            ConstantValue(int index, int value) {
                this.value = value;
            }
        }

        public boolean hasFlags() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getFlags() {
            return this.flags_;
        }

        public boolean hasValueParameterReference() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getValueParameterReference() {
            return this.valueParameterReference_;
        }

        public boolean hasConstantValue() {
            return (this.bitField0_ & 4) == 4;
        }

        public ConstantValue getConstantValue() {
            return this.constantValue_;
        }

        public boolean hasIsInstanceType() {
            return (this.bitField0_ & 8) == 8;
        }

        public Type getIsInstanceType() {
            return this.isInstanceType_;
        }

        public boolean hasIsInstanceTypeId() {
            return (this.bitField0_ & 16) == 16;
        }

        public int getIsInstanceTypeId() {
            return this.isInstanceTypeId_;
        }

        public int getAndArgumentCount() {
            return this.andArgument_.size();
        }

        public Expression getAndArgument(int index) {
            return this.andArgument_.get(index);
        }

        public int getOrArgumentCount() {
            return this.orArgument_.size();
        }

        public Expression getOrArgument(int index) {
            return this.orArgument_.get(index);
        }

        private void initFields() {
            this.flags_ = 0;
            this.valueParameterReference_ = 0;
            this.constantValue_ = ConstantValue.TRUE;
            this.isInstanceType_ = Type.getDefaultInstance();
            this.isInstanceTypeId_ = 0;
            this.andArgument_ = Collections.emptyList();
            this.orArgument_ = Collections.emptyList();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (hasIsInstanceType() && !getIsInstanceType().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            for (int i = 0; i < getAndArgumentCount(); i++) {
                if (!getAndArgument(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i2 = 0; i2 < getOrArgumentCount(); i2++) {
                if (!getOrArgument(i2).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.flags_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.valueParameterReference_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeEnum(3, this.constantValue_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, this.isInstanceType_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.isInstanceTypeId_);
            }
            for (int i = 0; i < this.andArgument_.size(); i++) {
                output.writeMessage(6, this.andArgument_.get(i));
            }
            for (int i2 = 0; i2 < this.orArgument_.size(); i2++) {
                output.writeMessage(7, this.orArgument_.get(i2));
            }
            output.writeRawBytes(this.unknownFields);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.flags_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.valueParameterReference_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeEnumSize(3, this.constantValue_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(4, this.isInstanceType_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.isInstanceTypeId_);
            }
            for (int i = 0; i < this.andArgument_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(6, this.andArgument_.get(i));
            }
            for (int i2 = 0; i2 < this.orArgument_.size(); i2++) {
                size2 += CodedOutputStream.computeMessageSize(7, this.orArgument_.get(i2));
            }
            int size3 = size2 + this.unknownFields.size();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Expression prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Expression$Builder.class */
        public static final class Builder extends GeneratedMessageLite.Builder<Expression, Builder> implements ExpressionOrBuilder {
            private int bitField0_;
            private int flags_;
            private int valueParameterReference_;
            private int isInstanceTypeId_;
            private ConstantValue constantValue_ = ConstantValue.TRUE;
            private Type isInstanceType_ = Type.getDefaultInstance();
            private List<Expression> andArgument_ = Collections.emptyList();
            private List<Expression> orArgument_ = Collections.emptyList();

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ GeneratedMessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite x0) {
                return mergeFrom((Expression) x0);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ GeneratedMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo3702clone() {
                return mo3702clone();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream x0, ExtensionRegistryLite x1) throws IOException {
                return mergeFrom(x0, x1);
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public /* bridge */ /* synthetic */ MessageLite build() {
                return build();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
                return getDefaultInstanceForType();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public /* bridge */ /* synthetic */ Object mo3702clone() throws CloneNotSupportedException {
                return mo3702clone();
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo3702clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public Expression getDefaultInstanceForType() {
                return Expression.getDefaultInstance();
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Expression build() {
                Expression result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public Expression buildPartial() {
                Expression result = new Expression(this, (AnonymousClass1) null);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.flags_ = this.flags_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.valueParameterReference_ = this.valueParameterReference_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.constantValue_ = this.constantValue_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.isInstanceType_ = this.isInstanceType_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.isInstanceTypeId_ = this.isInstanceTypeId_;
                if ((this.bitField0_ & 32) == 32) {
                    this.andArgument_ = Collections.unmodifiableList(this.andArgument_);
                    this.bitField0_ &= -33;
                }
                result.andArgument_ = this.andArgument_;
                if ((this.bitField0_ & 64) == 64) {
                    this.orArgument_ = Collections.unmodifiableList(this.orArgument_);
                    this.bitField0_ &= -65;
                }
                result.orArgument_ = this.orArgument_;
                result.bitField0_ = to_bitField0_;
                return result;
            }

            public Builder mergeFrom(Expression other) {
                if (other == Expression.getDefaultInstance()) {
                    return this;
                }
                if (other.hasFlags()) {
                    setFlags(other.getFlags());
                }
                if (other.hasValueParameterReference()) {
                    setValueParameterReference(other.getValueParameterReference());
                }
                if (other.hasConstantValue()) {
                    setConstantValue(other.getConstantValue());
                }
                if (other.hasIsInstanceType()) {
                    mergeIsInstanceType(other.getIsInstanceType());
                }
                if (other.hasIsInstanceTypeId()) {
                    setIsInstanceTypeId(other.getIsInstanceTypeId());
                }
                if (!other.andArgument_.isEmpty()) {
                    if (this.andArgument_.isEmpty()) {
                        this.andArgument_ = other.andArgument_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureAndArgumentIsMutable();
                        this.andArgument_.addAll(other.andArgument_);
                    }
                }
                if (!other.orArgument_.isEmpty()) {
                    if (this.orArgument_.isEmpty()) {
                        this.orArgument_ = other.orArgument_;
                        this.bitField0_ &= -65;
                    } else {
                        ensureOrArgumentIsMutable();
                        this.orArgument_.addAll(other.orArgument_);
                    }
                }
                setUnknownFields(getUnknownFields().concat(other.unknownFields));
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (hasIsInstanceType() && !getIsInstanceType().isInitialized()) {
                    return false;
                }
                for (int i = 0; i < getAndArgumentCount(); i++) {
                    if (!getAndArgument(i).isInitialized()) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getOrArgumentCount(); i2++) {
                    if (!getOrArgument(i2).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Expression parsedMessage = null;
                try {
                    try {
                        parsedMessage = Expression.PARSER.parsePartialFrom(input, extensionRegistry);
                        if (parsedMessage != null) {
                            mergeFrom(parsedMessage);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        parsedMessage = (Expression) e.getUnfinishedMessage();
                        throw e;
                    }
                } catch (Throwable th) {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                    throw th;
                }
            }

            public Builder setFlags(int value) {
                this.bitField0_ |= 1;
                this.flags_ = value;
                return this;
            }

            public Builder setValueParameterReference(int value) {
                this.bitField0_ |= 2;
                this.valueParameterReference_ = value;
                return this;
            }

            public Builder setConstantValue(ConstantValue value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.constantValue_ = value;
                return this;
            }

            public boolean hasIsInstanceType() {
                return (this.bitField0_ & 8) == 8;
            }

            public Type getIsInstanceType() {
                return this.isInstanceType_;
            }

            public Builder mergeIsInstanceType(Type value) {
                if ((this.bitField0_ & 8) == 8 && this.isInstanceType_ != Type.getDefaultInstance()) {
                    this.isInstanceType_ = Type.newBuilder(this.isInstanceType_).mergeFrom(value).buildPartial();
                } else {
                    this.isInstanceType_ = value;
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setIsInstanceTypeId(int value) {
                this.bitField0_ |= 16;
                this.isInstanceTypeId_ = value;
                return this;
            }

            private void ensureAndArgumentIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.andArgument_ = new ArrayList(this.andArgument_);
                    this.bitField0_ |= 32;
                }
            }

            public int getAndArgumentCount() {
                return this.andArgument_.size();
            }

            public Expression getAndArgument(int index) {
                return this.andArgument_.get(index);
            }

            private void ensureOrArgumentIsMutable() {
                if ((this.bitField0_ & 64) != 64) {
                    this.orArgument_ = new ArrayList(this.orArgument_);
                    this.bitField0_ |= 64;
                }
            }

            public int getOrArgumentCount() {
                return this.orArgument_.size();
            }

            public Expression getOrArgument(int index) {
                return this.orArgument_.get(index);
            }
        }
    }
}
