package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import org.jetbrains.annotations.NotNull;

/* compiled from: predefinedEnhancementInfo.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/SignatureEnhancementBuilder.class */
final class SignatureEnhancementBuilder {

    @NotNull
    private final Map<String, PredefinedFunctionEnhancementInfo> signatures = new LinkedHashMap();

    /* compiled from: predefinedEnhancementInfo.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/SignatureEnhancementBuilder$ClassEnhancementBuilder.class */
    public final class ClassEnhancementBuilder {

        @NotNull
        private final String className;
        final /* synthetic */ SignatureEnhancementBuilder this$0;

        public ClassEnhancementBuilder(@NotNull SignatureEnhancementBuilder this$0, String className) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(className, "className");
            this.this$0 = this$0;
            this.className = className;
        }

        @NotNull
        public final String getClassName() {
            return this.className;
        }

        public final void function(@NotNull String name, @NotNull Function1<? super FunctionEnhancementBuilder, Unit> block) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(block, "block");
            Map map = this.this$0.signatures;
            FunctionEnhancementBuilder functionEnhancementBuilder = new FunctionEnhancementBuilder(this, name);
            block.invoke(functionEnhancementBuilder);
            Pair<String, PredefinedFunctionEnhancementInfo> pairBuild = functionEnhancementBuilder.build();
            map.put(pairBuild.getFirst(), pairBuild.getSecond());
        }

        /* compiled from: predefinedEnhancementInfo.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/SignatureEnhancementBuilder$ClassEnhancementBuilder$FunctionEnhancementBuilder.class */
        public final class FunctionEnhancementBuilder {

            @NotNull
            private final String functionName;

            @NotNull
            private final List<Pair<String, TypeEnhancementInfo>> parameters;

            @NotNull
            private Pair<String, TypeEnhancementInfo> returnType;
            final /* synthetic */ ClassEnhancementBuilder this$0;

            public FunctionEnhancementBuilder(@NotNull ClassEnhancementBuilder this$0, String functionName) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Intrinsics.checkNotNullParameter(functionName, "functionName");
                this.this$0 = this$0;
                this.functionName = functionName;
                this.parameters = new ArrayList();
                this.returnType = TuplesKt.to("V", null);
            }

            @NotNull
            public final String getFunctionName() {
                return this.functionName;
            }

            public final void parameter(@NotNull String type, @NotNull JavaTypeQualifiers... qualifiers) {
                TypeEnhancementInfo typeEnhancementInfo;
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(qualifiers, "qualifiers");
                List<Pair<String, TypeEnhancementInfo>> list = this.parameters;
                String str = type;
                if (qualifiers.length == 0) {
                    typeEnhancementInfo = null;
                } else {
                    Iterable $this$associateBy$iv = ArraysKt.withIndex(qualifiers);
                    int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
                    Map destination$iv$iv = new LinkedHashMap(capacity$iv);
                    for (Object element$iv$iv : $this$associateBy$iv) {
                        IndexedValue it = (IndexedValue) element$iv$iv;
                        IndexedValue it2 = (IndexedValue) element$iv$iv;
                        destination$iv$iv.put(Integer.valueOf(it.getIndex()), (JavaTypeQualifiers) it2.getValue());
                    }
                    str = str;
                    typeEnhancementInfo = new TypeEnhancementInfo(destination$iv$iv);
                }
                list.add(TuplesKt.to(str, typeEnhancementInfo));
            }

            public final void returns(@NotNull String type, @NotNull JavaTypeQualifiers... qualifiers) {
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(qualifiers, "qualifiers");
                Iterable $this$associateBy$iv = ArraysKt.withIndex(qualifiers);
                int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
                Map destination$iv$iv = new LinkedHashMap(capacity$iv);
                for (Object element$iv$iv : $this$associateBy$iv) {
                    IndexedValue it = (IndexedValue) element$iv$iv;
                    IndexedValue it2 = (IndexedValue) element$iv$iv;
                    destination$iv$iv.put(Integer.valueOf(it.getIndex()), (JavaTypeQualifiers) it2.getValue());
                }
                this.returnType = TuplesKt.to(type, new TypeEnhancementInfo(destination$iv$iv));
            }

            public final void returns(@NotNull JvmPrimitiveType type) {
                Intrinsics.checkNotNullParameter(type, "type");
                String desc = type.getDesc();
                Intrinsics.checkNotNullExpressionValue(desc, "type.desc");
                this.returnType = TuplesKt.to(desc, null);
            }

            @NotNull
            public final Pair<String, PredefinedFunctionEnhancementInfo> build() {
                SignatureBuildingComponents $this$build_u24lambda_u2d6 = SignatureBuildingComponents.INSTANCE;
                String className = this.this$0.getClassName();
                String functionName = getFunctionName();
                Iterable $this$map$iv = this.parameters;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    Pair it = (Pair) item$iv$iv;
                    destination$iv$iv.add((String) it.getFirst());
                }
                String strSignature = $this$build_u24lambda_u2d6.signature(className, $this$build_u24lambda_u2d6.jvmDescriptor(functionName, (List) destination$iv$iv, this.returnType.getFirst()));
                TypeEnhancementInfo second = this.returnType.getSecond();
                Iterable $this$map$iv2 = this.parameters;
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                for (Object item$iv$iv2 : $this$map$iv2) {
                    Pair it2 = (Pair) item$iv$iv2;
                    destination$iv$iv2.add((TypeEnhancementInfo) it2.getSecond());
                }
                return TuplesKt.to(strSignature, new PredefinedFunctionEnhancementInfo(second, (List) destination$iv$iv2));
            }
        }
    }

    @NotNull
    public final Map<String, PredefinedFunctionEnhancementInfo> build() {
        return this.signatures;
    }
}
