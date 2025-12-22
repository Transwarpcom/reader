package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import ch.qos.logback.core.pattern.parser.Parser;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancementBuilder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDListAttributeObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: predefinedEnhancementInfo.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/typeEnhancement/PredefinedEnhancementInfoKt.class */
public final class PredefinedEnhancementInfoKt {

    @NotNull
    private static final JavaTypeQualifiers NULLABLE = new JavaTypeQualifiers(NullabilityQualifier.NULLABLE, null, false, false, 8, null);

    @NotNull
    private static final JavaTypeQualifiers NOT_PLATFORM = new JavaTypeQualifiers(NullabilityQualifier.NOT_NULL, null, false, false, 8, null);

    @NotNull
    private static final JavaTypeQualifiers NOT_NULLABLE = new JavaTypeQualifiers(NullabilityQualifier.NOT_NULL, null, true, false, 8, null);

    @NotNull
    private static final Map<String, PredefinedFunctionEnhancementInfo> PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE;

    static {
        final SignatureBuildingComponents $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15 = SignatureBuildingComponents.INSTANCE;
        final String JLObject = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaLang("Object");
        final String JFPredicate = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaFunction("Predicate");
        final String JFFunction = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaFunction("Function");
        final String JFConsumer = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaFunction("Consumer");
        final String JFBiFunction = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaFunction("BiFunction");
        final String JFBiConsumer = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaFunction("BiConsumer");
        final String JFUnaryOperator = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaFunction("UnaryOperator");
        final String JUStream = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaUtil("stream/Stream");
        final String JUOptional = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaUtil("Optional");
        SignatureEnhancementBuilder $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14 = new SignatureEnhancementBuilder();
        String internalName$iv = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaUtil("Iterator");
        SignatureEnhancementBuilder.ClassEnhancementBuilder $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d0 = new SignatureEnhancementBuilder.ClassEnhancementBuilder($this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14, internalName$iv);
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d0.function("forEachRemaining", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JFConsumer, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_PLATFORM);
            }
        });
        String internalName$iv2 = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaLang("Iterable");
        SignatureEnhancementBuilder.ClassEnhancementBuilder $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d1 = new SignatureEnhancementBuilder.ClassEnhancementBuilder($this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14, internalName$iv2);
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d1.function("spliterator", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$2$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.returns($this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaUtil("Spliterator"), PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_PLATFORM);
            }
        });
        String internalName$iv3 = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaUtil("Collection");
        SignatureEnhancementBuilder.ClassEnhancementBuilder $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d2 = new SignatureEnhancementBuilder.ClassEnhancementBuilder($this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14, internalName$iv3);
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d2.function("removeIf", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$3$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JFPredicate, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.returns(JvmPrimitiveType.BOOLEAN);
            }
        });
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d2.function("stream", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$3$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.returns(JUStream, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_PLATFORM);
            }
        });
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d2.function("parallelStream", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$3$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.returns(JUStream, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_PLATFORM);
            }
        });
        String internalName$iv4 = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaUtil(PDListAttributeObject.OWNER_LIST);
        SignatureEnhancementBuilder.ClassEnhancementBuilder $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d3 = new SignatureEnhancementBuilder.ClassEnhancementBuilder($this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14, internalName$iv4);
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d3.function("replaceAll", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$4$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JFUnaryOperator, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_PLATFORM);
            }
        });
        String internalName$iv5 = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaUtil("Map");
        SignatureEnhancementBuilder.ClassEnhancementBuilder $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d4 = new SignatureEnhancementBuilder.ClassEnhancementBuilder($this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14, internalName$iv5);
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d4.function("forEach", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JFBiConsumer, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_PLATFORM);
            }
        });
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d4.function("putIfAbsent", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.returns(JLObject, PredefinedEnhancementInfoKt.NULLABLE);
            }
        });
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d4.function(Parser.REPLACE_CONVERTER_WORD, new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.returns(JLObject, PredefinedEnhancementInfoKt.NULLABLE);
            }
        });
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d4.function(Parser.REPLACE_CONVERTER_WORD, new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.returns(JvmPrimitiveType.BOOLEAN);
            }
        });
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d4.function("replaceAll", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JFBiFunction, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_PLATFORM);
            }
        });
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d4.function("compute", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.parameter(JFBiFunction, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NULLABLE, PredefinedEnhancementInfoKt.NULLABLE);
                function.returns(JLObject, PredefinedEnhancementInfoKt.NULLABLE);
            }
        });
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d4.function("computeIfAbsent", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.parameter(JFFunction, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.returns(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
            }
        });
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d4.function("computeIfPresent", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.parameter(JFBiFunction, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_NULLABLE, PredefinedEnhancementInfoKt.NULLABLE);
                function.returns(JLObject, PredefinedEnhancementInfoKt.NULLABLE);
            }
        });
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d4.function(BeanDefinitionParserDelegate.MERGE_ATTRIBUTE, new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_NULLABLE);
                function.parameter(JFBiFunction, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_NULLABLE, PredefinedEnhancementInfoKt.NOT_NULLABLE, PredefinedEnhancementInfoKt.NULLABLE);
                function.returns(JLObject, PredefinedEnhancementInfoKt.NULLABLE);
            }
        });
        SignatureEnhancementBuilder.ClassEnhancementBuilder $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d5 = new SignatureEnhancementBuilder.ClassEnhancementBuilder($this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14, JUOptional);
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d5.function("empty", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$6$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.returns(JUOptional, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_NULLABLE);
            }
        });
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d5.function("of", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$6$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_NULLABLE);
                function.returns(JUOptional, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_NULLABLE);
            }
        });
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d5.function("ofNullable", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$6$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NULLABLE);
                function.returns(JUOptional, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_NULLABLE);
            }
        });
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d5.function(BeanUtil.PREFIX_GETTER_GET, new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$6$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.returns(JLObject, PredefinedEnhancementInfoKt.NOT_NULLABLE);
            }
        });
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d5.function("ifPresent", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$6$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JFConsumer, PredefinedEnhancementInfoKt.NOT_PLATFORM, PredefinedEnhancementInfoKt.NOT_NULLABLE);
            }
        });
        String internalName$iv6 = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaLang("ref/Reference");
        SignatureEnhancementBuilder.ClassEnhancementBuilder $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d6 = new SignatureEnhancementBuilder.ClassEnhancementBuilder($this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14, internalName$iv6);
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d6.function(BeanUtil.PREFIX_GETTER_GET, new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$7$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.returns(JLObject, PredefinedEnhancementInfoKt.NULLABLE);
            }
        });
        SignatureEnhancementBuilder.ClassEnhancementBuilder $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d7 = new SignatureEnhancementBuilder.ClassEnhancementBuilder($this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14, JFPredicate);
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d7.function("test", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$8$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.returns(JvmPrimitiveType.BOOLEAN);
            }
        });
        String internalName$iv7 = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaFunction("BiPredicate");
        SignatureEnhancementBuilder.ClassEnhancementBuilder $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d8 = new SignatureEnhancementBuilder.ClassEnhancementBuilder($this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14, internalName$iv7);
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d8.function("test", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$9$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.returns(JvmPrimitiveType.BOOLEAN);
            }
        });
        SignatureEnhancementBuilder.ClassEnhancementBuilder $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d9 = new SignatureEnhancementBuilder.ClassEnhancementBuilder($this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14, JFConsumer);
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d9.function("accept", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$10$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
            }
        });
        SignatureEnhancementBuilder.ClassEnhancementBuilder $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d10 = new SignatureEnhancementBuilder.ClassEnhancementBuilder($this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14, JFBiConsumer);
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d10.function("accept", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$11$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
            }
        });
        SignatureEnhancementBuilder.ClassEnhancementBuilder $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d11 = new SignatureEnhancementBuilder.ClassEnhancementBuilder($this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14, JFFunction);
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d11.function("apply", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$12$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.returns(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
            }
        });
        SignatureEnhancementBuilder.ClassEnhancementBuilder $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d12 = new SignatureEnhancementBuilder.ClassEnhancementBuilder($this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14, JFBiFunction);
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d12.function("apply", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$13$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.parameter(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
                function.returns(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
            }
        });
        String internalName$iv8 = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15.javaFunction("Supplier");
        SignatureEnhancementBuilder.ClassEnhancementBuilder $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d13 = new SignatureEnhancementBuilder.ClassEnhancementBuilder($this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14, internalName$iv8);
        $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14_u24lambda_u2d13.function(BeanUtil.PREFIX_GETTER_GET, new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$14$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                Intrinsics.checkNotNullParameter(function, "$this$function");
                function.returns(JLObject, PredefinedEnhancementInfoKt.NOT_PLATFORM);
            }
        });
        PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE = $this$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE_u24lambda_u2d15_u24lambda_u2d14.build();
    }

    @NotNull
    public static final Map<String, PredefinedFunctionEnhancementInfo> getPREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE() {
        return PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE;
    }
}
