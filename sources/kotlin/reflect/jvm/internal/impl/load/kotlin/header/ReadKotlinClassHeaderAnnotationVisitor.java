package kotlin.reflect.jvm.internal.impl.load.kotlin.header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ClassLiteralValue;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor.class */
public class ReadKotlinClassHeaderAnnotationVisitor implements KotlinJvmBinaryClass.AnnotationVisitor {
    private static final boolean IGNORE_OLD_METADATA = "true".equals(System.getProperty("kotlin.ignore.old.metadata"));
    private static final Map<ClassId, KotlinClassHeader.Kind> HEADER_KINDS = new HashMap();
    private int[] metadataVersionArray = null;
    private String extraString = null;
    private int extraInt = 0;
    private String packageName = null;
    private String[] data = null;
    private String[] strings = null;
    private String[] incompatibleData = null;
    private KotlinClassHeader.Kind headerKind = null;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        switch (i) {
            case 0:
            default:
                objArr[0] = "classId";
                break;
            case 1:
                objArr[0] = PackageDocumentBase.DCTags.source;
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor";
        objArr[2] = "visitAnnotation";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    static {
        HEADER_KINDS.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinClass")), KotlinClassHeader.Kind.CLASS);
        HEADER_KINDS.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinFileFacade")), KotlinClassHeader.Kind.FILE_FACADE);
        HEADER_KINDS.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinMultifileClass")), KotlinClassHeader.Kind.MULTIFILE_CLASS);
        HEADER_KINDS.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinMultifileClassPart")), KotlinClassHeader.Kind.MULTIFILE_CLASS_PART);
        HEADER_KINDS.put(ClassId.topLevel(new FqName("kotlin.jvm.internal.KotlinSyntheticClass")), KotlinClassHeader.Kind.SYNTHETIC_CLASS);
    }

    @Nullable
    public KotlinClassHeader createHeader() {
        if (this.headerKind == null || this.metadataVersionArray == null) {
            return null;
        }
        JvmMetadataVersion metadataVersion = new JvmMetadataVersion(this.metadataVersionArray, (this.extraInt & 8) != 0);
        if (!metadataVersion.isCompatible()) {
            this.incompatibleData = this.data;
            this.data = null;
        } else if (shouldHaveData() && this.data == null) {
            return null;
        }
        return new KotlinClassHeader(this.headerKind, metadataVersion, this.data, this.incompatibleData, this.strings, this.extraString, this.extraInt, this.packageName);
    }

    private boolean shouldHaveData() {
        return this.headerKind == KotlinClassHeader.Kind.CLASS || this.headerKind == KotlinClassHeader.Kind.FILE_FACADE || this.headerKind == KotlinClassHeader.Kind.MULTIFILE_CLASS_PART;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor
    @Nullable
    public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(@NotNull ClassId classId, @NotNull SourceElement source) {
        KotlinClassHeader.Kind newKind;
        if (classId == null) {
            $$$reportNull$$$0(0);
        }
        if (source == null) {
            $$$reportNull$$$0(1);
        }
        FqName fqName = classId.asSingleFqName();
        if (fqName.equals(JvmAnnotationNames.METADATA_FQ_NAME)) {
            return new KotlinMetadataArgumentVisitor();
        }
        if (!IGNORE_OLD_METADATA && this.headerKind == null && (newKind = HEADER_KINDS.get(classId)) != null) {
            this.headerKind = newKind;
            return new OldDeprecatedAnnotationArgumentVisitor();
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor
    public void visitEnd() {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinMetadataArgumentVisitor.class */
    private class KotlinMetadataArgumentVisitor implements KotlinJvmBinaryClass.AnnotationArgumentVisitor {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 0:
                case 2:
                case 3:
                case 6:
                default:
                    objArr[0] = "name";
                    break;
                case 1:
                    objArr[0] = "classLiteralValue";
                    break;
                case 4:
                    objArr[0] = "enumClassId";
                    break;
                case 5:
                    objArr[0] = "enumEntryName";
                    break;
                case 7:
                    objArr[0] = "classId";
                    break;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinMetadataArgumentVisitor";
            switch (i) {
                case 0:
                case 1:
                default:
                    objArr[2] = "visitClassLiteral";
                    break;
                case 2:
                    objArr[2] = "visitArray";
                    break;
                case 3:
                case 4:
                case 5:
                    objArr[2] = "visitEnum";
                    break;
                case 6:
                case 7:
                    objArr[2] = "visitAnnotation";
                    break;
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        private KotlinMetadataArgumentVisitor() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public void visit(@Nullable Name name, @Nullable Object value) {
            if (name == null) {
                return;
            }
            String string = name.asString();
            if (OperatorName.NON_STROKING_CMYK.equals(string)) {
                if (value instanceof Integer) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.headerKind = KotlinClassHeader.Kind.getById(((Integer) value).intValue());
                    return;
                }
                return;
            }
            if ("mv".equals(string)) {
                if (value instanceof int[]) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.metadataVersionArray = (int[]) value;
                    return;
                }
                return;
            }
            if ("xs".equals(string)) {
                if (value instanceof String) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.extraString = (String) value;
                    return;
                }
                return;
            }
            if ("xi".equals(string)) {
                if (value instanceof Integer) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.extraInt = ((Integer) value).intValue();
                    return;
                }
                return;
            }
            if ("pn".equals(string) && (value instanceof String)) {
                ReadKotlinClassHeaderAnnotationVisitor.this.packageName = (String) value;
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public void visitClassLiteral(@NotNull Name name, @NotNull ClassLiteralValue classLiteralValue) {
            if (name == null) {
                $$$reportNull$$$0(0);
            }
            if (classLiteralValue == null) {
                $$$reportNull$$$0(1);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        @Nullable
        public KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor visitArray(@NotNull Name name) {
            if (name == null) {
                $$$reportNull$$$0(2);
            }
            String string = name.asString();
            if (OperatorName.TYPE3_D1.equals(string)) {
                return dataArrayVisitor();
            }
            if ("d2".equals(string)) {
                return stringsArrayVisitor();
            }
            return null;
        }

        @NotNull
        private KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor dataArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.KotlinMetadataArgumentVisitor.1
                private static /* synthetic */ void $$$reportNull$$$0(int i) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", CacheOperationExpressionEvaluator.RESULT_VARIABLE, "kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinMetadataArgumentVisitor$1", "visitEnd"));
                }

                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.CollectStringArrayAnnotationVisitor
                protected void visitEnd(@NotNull String[] result) {
                    if (result == null) {
                        $$$reportNull$$$0(0);
                    }
                    ReadKotlinClassHeaderAnnotationVisitor.this.data = result;
                }
            };
        }

        @NotNull
        private KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor stringsArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.KotlinMetadataArgumentVisitor.2
                private static /* synthetic */ void $$$reportNull$$$0(int i) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", CacheOperationExpressionEvaluator.RESULT_VARIABLE, "kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$KotlinMetadataArgumentVisitor$2", "visitEnd"));
                }

                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.CollectStringArrayAnnotationVisitor
                protected void visitEnd(@NotNull String[] result) {
                    if (result == null) {
                        $$$reportNull$$$0(0);
                    }
                    ReadKotlinClassHeaderAnnotationVisitor.this.strings = result;
                }
            };
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public void visitEnum(@NotNull Name name, @NotNull ClassId enumClassId, @NotNull Name enumEntryName) {
            if (name == null) {
                $$$reportNull$$$0(3);
            }
            if (enumClassId == null) {
                $$$reportNull$$$0(4);
            }
            if (enumEntryName == null) {
                $$$reportNull$$$0(5);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        @Nullable
        public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(@NotNull Name name, @NotNull ClassId classId) {
            if (name == null) {
                $$$reportNull$$$0(6);
            }
            if (classId == null) {
                $$$reportNull$$$0(7);
                return null;
            }
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public void visitEnd() {
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$OldDeprecatedAnnotationArgumentVisitor.class */
    private class OldDeprecatedAnnotationArgumentVisitor implements KotlinJvmBinaryClass.AnnotationArgumentVisitor {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 0:
                case 2:
                case 3:
                case 6:
                default:
                    objArr[0] = "name";
                    break;
                case 1:
                    objArr[0] = "classLiteralValue";
                    break;
                case 4:
                    objArr[0] = "enumClassId";
                    break;
                case 5:
                    objArr[0] = "enumEntryName";
                    break;
                case 7:
                    objArr[0] = "classId";
                    break;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$OldDeprecatedAnnotationArgumentVisitor";
            switch (i) {
                case 0:
                case 1:
                default:
                    objArr[2] = "visitClassLiteral";
                    break;
                case 2:
                    objArr[2] = "visitArray";
                    break;
                case 3:
                case 4:
                case 5:
                    objArr[2] = "visitEnum";
                    break;
                case 6:
                case 7:
                    objArr[2] = "visitAnnotation";
                    break;
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        private OldDeprecatedAnnotationArgumentVisitor() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public void visit(@Nullable Name name, @Nullable Object value) {
            if (name == null) {
                return;
            }
            String string = name.asString();
            if ("version".equals(string)) {
                if (value instanceof int[]) {
                    ReadKotlinClassHeaderAnnotationVisitor.this.metadataVersionArray = (int[]) value;
                }
            } else if ("multifileClassName".equals(string)) {
                ReadKotlinClassHeaderAnnotationVisitor.this.extraString = value instanceof String ? (String) value : null;
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public void visitClassLiteral(@NotNull Name name, @NotNull ClassLiteralValue classLiteralValue) {
            if (name == null) {
                $$$reportNull$$$0(0);
            }
            if (classLiteralValue == null) {
                $$$reportNull$$$0(1);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        @Nullable
        public KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor visitArray(@NotNull Name name) {
            if (name == null) {
                $$$reportNull$$$0(2);
            }
            String string = name.asString();
            if ("data".equals(string) || "filePartClassNames".equals(string)) {
                return dataArrayVisitor();
            }
            if ("strings".equals(string)) {
                return stringsArrayVisitor();
            }
            return null;
        }

        @NotNull
        private KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor dataArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.OldDeprecatedAnnotationArgumentVisitor.1
                private static /* synthetic */ void $$$reportNull$$$0(int i) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "data", "kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$OldDeprecatedAnnotationArgumentVisitor$1", "visitEnd"));
                }

                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.CollectStringArrayAnnotationVisitor
                protected void visitEnd(@NotNull String[] data) {
                    if (data == null) {
                        $$$reportNull$$$0(0);
                    }
                    ReadKotlinClassHeaderAnnotationVisitor.this.data = data;
                }
            };
        }

        @NotNull
        private KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor stringsArrayVisitor() {
            return new CollectStringArrayAnnotationVisitor() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.OldDeprecatedAnnotationArgumentVisitor.2
                private static /* synthetic */ void $$$reportNull$$$0(int i) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "data", "kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$OldDeprecatedAnnotationArgumentVisitor$2", "visitEnd"));
                }

                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor.CollectStringArrayAnnotationVisitor
                protected void visitEnd(@NotNull String[] data) {
                    if (data == null) {
                        $$$reportNull$$$0(0);
                    }
                    ReadKotlinClassHeaderAnnotationVisitor.this.strings = data;
                }
            };
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public void visitEnum(@NotNull Name name, @NotNull ClassId enumClassId, @NotNull Name enumEntryName) {
            if (name == null) {
                $$$reportNull$$$0(3);
            }
            if (enumClassId == null) {
                $$$reportNull$$$0(4);
            }
            if (enumEntryName == null) {
                $$$reportNull$$$0(5);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        @Nullable
        public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(@NotNull Name name, @NotNull ClassId classId) {
            if (name == null) {
                $$$reportNull$$$0(6);
            }
            if (classId == null) {
                $$$reportNull$$$0(7);
                return null;
            }
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
        public void visitEnd() {
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$CollectStringArrayAnnotationVisitor.class */
    private static abstract class CollectStringArrayAnnotationVisitor implements KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor {
        private final List<String> strings = new ArrayList();

        protected abstract void visitEnd(@NotNull String[] strArr);

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "enumClassId";
                    break;
                case 1:
                    objArr[0] = "enumEntryName";
                    break;
                case 2:
                    objArr[0] = "classLiteralValue";
                    break;
                case 3:
                    objArr[0] = "classId";
                    break;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/kotlin/header/ReadKotlinClassHeaderAnnotationVisitor$CollectStringArrayAnnotationVisitor";
            switch (i) {
                case 0:
                case 1:
                default:
                    objArr[2] = "visitEnum";
                    break;
                case 2:
                    objArr[2] = "visitClassLiteral";
                    break;
                case 3:
                    objArr[2] = "visitAnnotation";
                    break;
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
        public void visit(@Nullable Object value) {
            if (value instanceof String) {
                this.strings.add((String) value);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
        public void visitEnum(@NotNull ClassId enumClassId, @NotNull Name enumEntryName) {
            if (enumClassId == null) {
                $$$reportNull$$$0(0);
            }
            if (enumEntryName == null) {
                $$$reportNull$$$0(1);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
        public void visitClassLiteral(@NotNull ClassLiteralValue classLiteralValue) {
            if (classLiteralValue == null) {
                $$$reportNull$$$0(2);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
        @Nullable
        public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(@NotNull ClassId classId) {
            if (classId == null) {
                $$$reportNull$$$0(3);
                return null;
            }
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
        public void visitEnd() {
            visitEnd((String[]) this.strings.toArray(new String[0]));
        }
    }
}
