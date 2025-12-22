package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import io.vertx.core.cli.converters.FromBasedConverter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import me.ag2s.epublib.epub.NCXDocumentV2;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Extensions.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��ò\u0001\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0006\u0010��\u001a\u00020\u0001\u001a\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0006\b��\u0010\u0006\u0018\u0001H\u0086\b\u001a!\u0010\u0007\u001a\u00020\b2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\f\u001a!\u0010\r\u001a\u00020\u000e2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\f\u001a5\u0010\u0010\u001a\u00020\u0011\"\n\b��\u0010\u0006\u0018\u0001*\u00020\u0012*\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0016H\u0086\b\u001a5\u0010\u0017\u001a\u00020\u0011\"\n\b��\u0010\u0006\u0018\u0001*\u00020\u0012*\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00142\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0019H\u0086\b\u001a\u0015\u0010\u001a\u001a\u00020\u001b*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0086\u0002\u001a\u0015\u0010\u001a\u001a\u00020\u001b*\u00020\u001c2\u0006\u0010\u001f\u001a\u00020 H\u0086\u0002\u001a\"\u0010!\u001a\u0002H\u0006\"\u0006\b��\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010\"\u001a\u00020\u0012H\u0086\b¢\u0006\u0002\u0010#\u001a\u0010\u0010$\u001a\u00020\u001b*\u0006\u0012\u0002\b\u00030%H��\u001a\u0015\u0010&\u001a\u00020\u000b*\u00020'2\u0006\u0010\u001d\u001a\u00020\u001eH\u0086\u0002\u001a\u0015\u0010&\u001a\u00020\u000b*\u00020(2\u0006\u0010\u001f\u001a\u00020 H\u0086\u0002\u001a\u001b\u0010&\u001a\u00020\u000b*\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020 0*H\u0086\u0002\u001a\u0015\u0010+\u001a\u00020\u000b*\u00020'2\u0006\u0010\u001d\u001a\u00020\u001eH\u0086\u0002\u001a\u0015\u0010+\u001a\u00020\u000b*\u00020(2\u0006\u0010\u001f\u001a\u00020 H\u0086\u0002\u001a\u001b\u0010+\u001a\u00020\u000b*\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020 0*H\u0086\u0002\u001a\u0015\u0010,\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u00020\u001cH\u0086\u0002\u001a\u0015\u0010,\u001a\u00020\u000b*\u00020'2\u0006\u0010.\u001a\u00020'H\u0086\u0002\u001a\u0015\u0010,\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u00020/H\u0086\u0002\u001a\u0015\u0010,\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u000200H\u0086\u0002\u001a\u0015\u0010,\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u00020\u001bH\u0086\u0002\u001a\u0015\u0010,\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u000201H\u0086\u0002\u001a\u0015\u0010,\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u000202H\u0086\u0002\u001a\u0015\u0010,\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u000203H\u0086\u0002\u001a\u0015\u0010,\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u00020\u001eH\u0086\u0002\u001a\u0015\u0010,\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u000204H\u0086\u0002\u001a\u0015\u0010,\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u000205H\u0086\u0002\u001a\u0015\u0010,\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u00020 H\u0086\u0002\u001a\u0015\u00106\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u00020\u001cH\u0086\u0002\u001a\u0015\u00106\u001a\u00020\u000b*\u00020'2\u0006\u0010.\u001a\u00020'H\u0086\u0002\u001a\u0015\u00106\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u00020/H\u0086\u0002\u001a\u0015\u00106\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u000200H\u0086\u0002\u001a\u0015\u00106\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u00020\u001bH\u0086\u0002\u001a\u0015\u00106\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u000201H\u0086\u0002\u001a\u0015\u00106\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u000202H\u0086\u0002\u001a\u0015\u00106\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u000203H\u0086\u0002\u001a\u0015\u00106\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u00020\u001eH\u0086\u0002\u001a\u0015\u00106\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u000204H\u0086\u0002\u001a\u0015\u00106\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u000205H\u0086\u0002\u001a\u0015\u00106\u001a\u00020\u000b*\u00020'2\u0006\u0010-\u001a\u00020 H\u0086\u0002\u001a\"\u00107\u001a\u0002H\u0006\"\u0006\b��\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u00108\u001a\u000209H\u0086\b¢\u0006\u0002\u0010:\u001a\"\u00107\u001a\u0002H\u0006\"\u0006\b��\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010;\u001a\u00020<H\u0086\b¢\u0006\u0002\u0010=\u001a\"\u00107\u001a\u0002H\u0006\"\u0006\b��\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010;\u001a\u00020>H\u0086\b¢\u0006\u0002\u0010?\u001a\"\u00107\u001a\u0002H\u0006\"\u0006\b��\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010;\u001a\u00020@H\u0086\b¢\u0006\u0002\u0010A\u001a\"\u00107\u001a\u0002H\u0006\"\u0006\b��\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010;\u001a\u00020BH\u0086\b¢\u0006\u0002\u0010C\u001a\"\u00107\u001a\u0002H\u0006\"\u0006\b��\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010;\u001a\u000201H\u0086\b¢\u0006\u0002\u0010D\u001a\"\u00107\u001a\u0002H\u0006\"\u0006\b��\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010E\u001a\u00020 H\u0086\b¢\u0006\u0002\u0010F\u001a\"\u0010G\u001a\u0002H\u0006\"\u0006\b��\u0010\u0006\u0018\u0001*\u00020H2\u0006\u00108\u001a\u000209H\u0086\b¢\u0006\u0002\u0010I\u001a#\u0010J\u001a\b\u0012\u0004\u0012\u0002H\u00060K\"\u0006\b��\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u00108\u001a\u000209H\u0086\b\u001a#\u0010L\u001a\b\u0012\u0004\u0012\u0002H\u00060M\"\u0006\b��\u0010\u0006\u0018\u0001*\u00020H2\u0006\u00108\u001a\u000209H\u0086\b\u001a\n\u0010N\u001a\u00020\u0003*\u00020\u0003\u001a\f\u0010O\u001a\u00020P*\u00020\u001eH��\u001a\"\u0010Q\u001a\u0002H\u0006\"\u0006\b��\u0010\u0006\u0018\u0001*\u00020\u00032\u0006\u0010R\u001a\u00020SH\u0086\b¢\u0006\u0002\u0010T\u001a$\u0010Q\u001a\u0004\u0018\u0001H\u0006\"\u0006\b��\u0010\u0006\u0018\u0001*\u00020H2\u0006\u0010R\u001a\u00020SH\u0086\b¢\u0006\u0002\u0010U\u001a&\u0010V\u001a\n X*\u0004\u0018\u00010W0W*\u00020W2\b\u0010Y\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001d\u001a\u00020\u001eH��\u001a&\u0010V\u001a\n X*\u0004\u0018\u00010W0W*\u00020W2\b\u0010Y\u001a\u0004\u0018\u00010\u00122\u0006\u0010Z\u001a\u00020 H��¨\u0006["}, d2 = {"jacksonMapperBuilder", "Lcom/fasterxml/jackson/databind/json/JsonMapper$Builder;", "jacksonObjectMapper", "Lcom/fasterxml/jackson/databind/ObjectMapper;", "jacksonTypeRef", "Lcom/fasterxml/jackson/core/type/TypeReference;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "jsonMapper", "Lcom/fasterxml/jackson/databind/json/JsonMapper;", "initializer", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "kotlinModule", "Lcom/fasterxml/jackson/module/kotlin/KotlinModule;", "Lcom/fasterxml/jackson/module/kotlin/KotlinModule$Builder;", "addDeserializer", "Lcom/fasterxml/jackson/databind/module/SimpleModule;", "", "kClass", "Lkotlin/reflect/KClass;", "deserializer", "Lcom/fasterxml/jackson/databind/JsonDeserializer;", "addSerializer", "serializer", "Lcom/fasterxml/jackson/databind/JsonSerializer;", "contains", "", "Lcom/fasterxml/jackson/databind/JsonNode;", "index", "", "field", "", "convertValue", FromBasedConverter.FROM, "(Lcom/fasterxml/jackson/databind/ObjectMapper;Ljava/lang/Object;)Ljava/lang/Object;", "isUnboxableValueClass", "Ljava/lang/Class;", "minus", "Lcom/fasterxml/jackson/databind/node/ArrayNode;", "Lcom/fasterxml/jackson/databind/node/ObjectNode;", "fields", "", "minusAssign", "plus", "element", "elements", "Ljava/math/BigDecimal;", "Ljava/math/BigInteger;", "", "", "", "", "", "plusAssign", "readValue", "jp", "Lcom/fasterxml/jackson/core/JsonParser;", "(Lcom/fasterxml/jackson/databind/ObjectMapper;Lcom/fasterxml/jackson/core/JsonParser;)Ljava/lang/Object;", NCXDocumentV2.NCXAttributes.src, "Ljava/io/File;", "(Lcom/fasterxml/jackson/databind/ObjectMapper;Ljava/io/File;)Ljava/lang/Object;", "Ljava/io/InputStream;", "(Lcom/fasterxml/jackson/databind/ObjectMapper;Ljava/io/InputStream;)Ljava/lang/Object;", "Ljava/io/Reader;", "(Lcom/fasterxml/jackson/databind/ObjectMapper;Ljava/io/Reader;)Ljava/lang/Object;", "Ljava/net/URL;", "(Lcom/fasterxml/jackson/databind/ObjectMapper;Ljava/net/URL;)Ljava/lang/Object;", "(Lcom/fasterxml/jackson/databind/ObjectMapper;[B)Ljava/lang/Object;", "content", "(Lcom/fasterxml/jackson/databind/ObjectMapper;Ljava/lang/String;)Ljava/lang/Object;", "readValueTyped", "Lcom/fasterxml/jackson/databind/ObjectReader;", "(Lcom/fasterxml/jackson/databind/ObjectReader;Lcom/fasterxml/jackson/core/JsonParser;)Ljava/lang/Object;", "readValues", "Lcom/fasterxml/jackson/databind/MappingIterator;", "readValuesTyped", "", "registerKotlinModule", "toBitSet", "Ljava/util/BitSet;", "treeToValue", OperatorName.ENDPATH, "Lcom/fasterxml/jackson/core/TreeNode;", "(Lcom/fasterxml/jackson/databind/ObjectMapper;Lcom/fasterxml/jackson/core/TreeNode;)Ljava/lang/Object;", "(Lcom/fasterxml/jackson/databind/ObjectReader;Lcom/fasterxml/jackson/core/TreeNode;)Ljava/lang/Object;", "wrapWithPath", "Lcom/fasterxml/jackson/databind/JsonMappingException;", "kotlin.jvm.PlatformType", "refFrom", "refFieldName", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/ExtensionsKt.class */
public final class ExtensionsKt {
    public static /* synthetic */ KotlinModule kotlinModule$default(Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<KotlinModule.Builder, Unit>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt.kotlinModule.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(@NotNull KotlinModule.Builder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KotlinModule.Builder builder) {
                    invoke2(builder);
                    return Unit.INSTANCE;
                }
            };
        }
        return kotlinModule(function1);
    }

    @NotNull
    public static final KotlinModule kotlinModule(@NotNull Function1<? super KotlinModule.Builder, Unit> initializer) {
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        KotlinModule.Builder builder = new KotlinModule.Builder();
        initializer.invoke(builder);
        return builder.build();
    }

    public static /* synthetic */ JsonMapper jsonMapper$default(Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<JsonMapper.Builder, Unit>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt.jsonMapper.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(@NotNull JsonMapper.Builder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(JsonMapper.Builder builder) {
                    invoke2(builder);
                    return Unit.INSTANCE;
                }
            };
        }
        return jsonMapper(function1);
    }

    @NotNull
    public static final JsonMapper jsonMapper(@NotNull Function1<? super JsonMapper.Builder, Unit> initializer) {
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        JsonMapper.Builder builder = JsonMapper.builder();
        Intrinsics.checkNotNullExpressionValue(builder, "builder");
        initializer.invoke(builder);
        JsonMapper jsonMapperBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(jsonMapperBuild, "builder.build()");
        return jsonMapperBuild;
    }

    @NotNull
    public static final ObjectMapper jacksonObjectMapper() {
        return jsonMapper(new Function1<JsonMapper.Builder, Unit>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt.jacksonObjectMapper.1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull JsonMapper.Builder jsonMapper) {
                Intrinsics.checkNotNullParameter(jsonMapper, "$this$jsonMapper");
                jsonMapper.addModule(ExtensionsKt.kotlinModule$default(null, 1, null));
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(JsonMapper.Builder builder) {
                invoke2(builder);
                return Unit.INSTANCE;
            }
        });
    }

    @NotNull
    public static final JsonMapper.Builder jacksonMapperBuilder() {
        JsonMapper.Builder builderAddModule = JsonMapper.builder().addModule(kotlinModule$default(null, 1, null));
        Intrinsics.checkNotNullExpressionValue(builderAddModule, "builder().addModule(kotlinModule())");
        return builderAddModule;
    }

    @NotNull
    public static final ObjectMapper registerKotlinModule(@NotNull ObjectMapper $this$registerKotlinModule) {
        Intrinsics.checkNotNullParameter($this$registerKotlinModule, "<this>");
        ObjectMapper objectMapperRegisterModule = $this$registerKotlinModule.registerModule(kotlinModule$default(null, 1, null));
        Intrinsics.checkNotNullExpressionValue(objectMapperRegisterModule, "this.registerModule(kotlinModule())");
        return objectMapperRegisterModule;
    }

    public static final /* synthetic */ <T> TypeReference<T> jacksonTypeRef() {
        Intrinsics.needClassReification();
        return new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt.jacksonTypeRef.1
        };
    }

    public static final /* synthetic */ <T> T readValue(ObjectMapper objectMapper, JsonParser jp) {
        Intrinsics.checkNotNullParameter(objectMapper, "<this>");
        Intrinsics.checkNotNullParameter(jp, "jp");
        Intrinsics.needClassReification();
        return (T) objectMapper.readValue(jp, (TypeReference<?>) new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValue$$inlined$jacksonTypeRef$1
        });
    }

    public static final /* synthetic */ <T> MappingIterator<T> readValues(ObjectMapper objectMapper, JsonParser jp) throws IOException {
        Intrinsics.checkNotNullParameter(objectMapper, "<this>");
        Intrinsics.checkNotNullParameter(jp, "jp");
        Intrinsics.needClassReification();
        MappingIterator<T> values = objectMapper.readValues(jp, (TypeReference<?>) new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValues$$inlined$jacksonTypeRef$1
        });
        Intrinsics.checkNotNullExpressionValue(values, "readValues(jp, jacksonTypeRef<T>())");
        return values;
    }

    public static final /* synthetic */ <T> T readValue(ObjectMapper objectMapper, File src) {
        Intrinsics.checkNotNullParameter(objectMapper, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.needClassReification();
        return (T) objectMapper.readValue(src, new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValue$$inlined$jacksonTypeRef$2
        });
    }

    public static final /* synthetic */ <T> T readValue(ObjectMapper objectMapper, URL src) {
        Intrinsics.checkNotNullParameter(objectMapper, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.needClassReification();
        return (T) objectMapper.readValue(src, new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValue$$inlined$jacksonTypeRef$3
        });
    }

    public static final /* synthetic */ <T> T readValue(ObjectMapper objectMapper, String content) {
        Intrinsics.checkNotNullParameter(objectMapper, "<this>");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.needClassReification();
        return (T) objectMapper.readValue(content, new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValue$$inlined$jacksonTypeRef$4
        });
    }

    public static final /* synthetic */ <T> T readValue(ObjectMapper objectMapper, Reader src) {
        Intrinsics.checkNotNullParameter(objectMapper, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.needClassReification();
        return (T) objectMapper.readValue(src, new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValue$$inlined$jacksonTypeRef$5
        });
    }

    public static final /* synthetic */ <T> T readValue(ObjectMapper objectMapper, InputStream src) {
        Intrinsics.checkNotNullParameter(objectMapper, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.needClassReification();
        return (T) objectMapper.readValue(src, new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValue$$inlined$jacksonTypeRef$6
        });
    }

    public static final /* synthetic */ <T> T readValue(ObjectMapper objectMapper, byte[] src) {
        Intrinsics.checkNotNullParameter(objectMapper, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.needClassReification();
        return (T) objectMapper.readValue(src, new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValue$$inlined$jacksonTypeRef$7
        });
    }

    public static final /* synthetic */ <T> T treeToValue(ObjectMapper objectMapper, TreeNode n) {
        Intrinsics.checkNotNullParameter(objectMapper, "<this>");
        Intrinsics.checkNotNullParameter(n, "n");
        JsonParser jsonParserTreeAsTokens = objectMapper.treeAsTokens(n);
        Intrinsics.needClassReification();
        return (T) objectMapper.readValue(jsonParserTreeAsTokens, (TypeReference<?>) new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$treeToValue$$inlined$jacksonTypeRef$1
        });
    }

    public static final /* synthetic */ <T> T convertValue(ObjectMapper objectMapper, Object from) {
        Intrinsics.checkNotNullParameter(objectMapper, "<this>");
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.needClassReification();
        return (T) objectMapper.convertValue(from, (TypeReference<?>) new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$convertValue$$inlined$jacksonTypeRef$1
        });
    }

    public static final /* synthetic */ <T> T readValueTyped(ObjectReader objectReader, JsonParser jp) {
        Intrinsics.checkNotNullParameter(objectReader, "<this>");
        Intrinsics.checkNotNullParameter(jp, "jp");
        Intrinsics.needClassReification();
        return (T) objectReader.readValue(jp, (TypeReference<?>) new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValueTyped$$inlined$jacksonTypeRef$1
        });
    }

    public static final /* synthetic */ <T> Iterator<T> readValuesTyped(ObjectReader objectReader, JsonParser jp) throws IOException {
        Intrinsics.checkNotNullParameter(objectReader, "<this>");
        Intrinsics.checkNotNullParameter(jp, "jp");
        Intrinsics.needClassReification();
        Iterator<T> values = objectReader.readValues(jp, (TypeReference<?>) new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$readValuesTyped$$inlined$jacksonTypeRef$1
        });
        Intrinsics.checkNotNullExpressionValue(values, "readValues(jp, jacksonTypeRef<T>())");
        return values;
    }

    public static final /* synthetic */ <T> T treeToValue(ObjectReader objectReader, TreeNode n) {
        Intrinsics.checkNotNullParameter(objectReader, "<this>");
        Intrinsics.checkNotNullParameter(n, "n");
        JsonParser jsonParserTreeAsTokens = objectReader.treeAsTokens(n);
        Intrinsics.needClassReification();
        return (T) objectReader.readValue(jsonParserTreeAsTokens, (TypeReference<?>) new TypeReference<T>() { // from class: com.fasterxml.jackson.module.kotlin.ExtensionsKt$treeToValue$$inlined$jacksonTypeRef$2
        });
    }

    public static final void plus(@NotNull ArrayNode $this$plus, boolean element) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        Unit unit = Unit.INSTANCE;
        $this$plus.add(element);
    }

    public static final void plus(@NotNull ArrayNode $this$plus, short element) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        Unit unit = Unit.INSTANCE;
        $this$plus.add(element);
    }

    public static final void plus(@NotNull ArrayNode $this$plus, int element) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        Unit unit = Unit.INSTANCE;
        $this$plus.add(element);
    }

    public static final void plus(@NotNull ArrayNode $this$plus, long element) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        Unit unit = Unit.INSTANCE;
        $this$plus.add(element);
    }

    public static final void plus(@NotNull ArrayNode $this$plus, float element) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        Unit unit = Unit.INSTANCE;
        $this$plus.add(element);
    }

    public static final void plus(@NotNull ArrayNode $this$plus, double element) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        Unit unit = Unit.INSTANCE;
        $this$plus.add(element);
    }

    public static final void plus(@NotNull ArrayNode $this$plus, @NotNull BigDecimal element) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        Intrinsics.checkNotNullParameter(element, "element");
        Unit unit = Unit.INSTANCE;
        $this$plus.add(element);
    }

    public static final void plus(@NotNull ArrayNode $this$plus, @NotNull BigInteger element) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        Intrinsics.checkNotNullParameter(element, "element");
        Unit unit = Unit.INSTANCE;
        $this$plus.add(element);
    }

    public static final void plus(@NotNull ArrayNode $this$plus, @NotNull String element) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        Intrinsics.checkNotNullParameter(element, "element");
        Unit unit = Unit.INSTANCE;
        $this$plus.add(element);
    }

    public static final void plus(@NotNull ArrayNode $this$plus, @NotNull byte[] element) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        Intrinsics.checkNotNullParameter(element, "element");
        Unit unit = Unit.INSTANCE;
        $this$plus.add(element);
    }

    public static final void plus(@NotNull ArrayNode $this$plus, @NotNull JsonNode element) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        Intrinsics.checkNotNullParameter(element, "element");
        Unit unit = Unit.INSTANCE;
        $this$plus.add(element);
    }

    public static final void plus(@NotNull ArrayNode $this$plus, @NotNull ArrayNode elements) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        Unit unit = Unit.INSTANCE;
        $this$plus.addAll(elements);
    }

    public static final void plusAssign(@NotNull ArrayNode $this$plusAssign, boolean element) {
        Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
        Unit unit = Unit.INSTANCE;
        $this$plusAssign.add(element);
    }

    public static final void plusAssign(@NotNull ArrayNode $this$plusAssign, short element) {
        Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
        Unit unit = Unit.INSTANCE;
        $this$plusAssign.add(element);
    }

    public static final void plusAssign(@NotNull ArrayNode $this$plusAssign, int element) {
        Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
        Unit unit = Unit.INSTANCE;
        $this$plusAssign.add(element);
    }

    public static final void plusAssign(@NotNull ArrayNode $this$plusAssign, long element) {
        Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
        Unit unit = Unit.INSTANCE;
        $this$plusAssign.add(element);
    }

    public static final void plusAssign(@NotNull ArrayNode $this$plusAssign, float element) {
        Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
        Unit unit = Unit.INSTANCE;
        $this$plusAssign.add(element);
    }

    public static final void plusAssign(@NotNull ArrayNode $this$plusAssign, double element) {
        Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
        Unit unit = Unit.INSTANCE;
        $this$plusAssign.add(element);
    }

    public static final void plusAssign(@NotNull ArrayNode $this$plusAssign, @NotNull BigDecimal element) {
        Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
        Intrinsics.checkNotNullParameter(element, "element");
        Unit unit = Unit.INSTANCE;
        $this$plusAssign.add(element);
    }

    public static final void plusAssign(@NotNull ArrayNode $this$plusAssign, @NotNull BigInteger element) {
        Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
        Intrinsics.checkNotNullParameter(element, "element");
        Unit unit = Unit.INSTANCE;
        $this$plusAssign.add(element);
    }

    public static final void plusAssign(@NotNull ArrayNode $this$plusAssign, @NotNull String element) {
        Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
        Intrinsics.checkNotNullParameter(element, "element");
        Unit unit = Unit.INSTANCE;
        $this$plusAssign.add(element);
    }

    public static final void plusAssign(@NotNull ArrayNode $this$plusAssign, @NotNull byte[] element) {
        Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
        Intrinsics.checkNotNullParameter(element, "element");
        Unit unit = Unit.INSTANCE;
        $this$plusAssign.add(element);
    }

    public static final void plusAssign(@NotNull ArrayNode $this$plusAssign, @NotNull JsonNode element) {
        Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
        Intrinsics.checkNotNullParameter(element, "element");
        Unit unit = Unit.INSTANCE;
        $this$plusAssign.add(element);
    }

    public static final void plusAssign(@NotNull ArrayNode $this$plusAssign, @NotNull ArrayNode elements) {
        Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        Unit unit = Unit.INSTANCE;
        $this$plusAssign.addAll(elements);
    }

    public static final void minus(@NotNull ArrayNode $this$minus, int index) {
        Intrinsics.checkNotNullParameter($this$minus, "<this>");
        Unit unit = Unit.INSTANCE;
        $this$minus.remove(index);
    }

    public static final void minusAssign(@NotNull ArrayNode $this$minusAssign, int index) {
        Intrinsics.checkNotNullParameter($this$minusAssign, "<this>");
        Unit unit = Unit.INSTANCE;
        $this$minusAssign.remove(index);
    }

    public static final void minus(@NotNull ObjectNode $this$minus, @NotNull String field) {
        Intrinsics.checkNotNullParameter($this$minus, "<this>");
        Intrinsics.checkNotNullParameter(field, "field");
        Unit unit = Unit.INSTANCE;
        $this$minus.remove(field);
    }

    public static final void minus(@NotNull ObjectNode $this$minus, @NotNull Collection<String> fields) {
        Intrinsics.checkNotNullParameter($this$minus, "<this>");
        Intrinsics.checkNotNullParameter(fields, "fields");
        Unit unit = Unit.INSTANCE;
        $this$minus.remove(fields);
    }

    public static final void minusAssign(@NotNull ObjectNode $this$minusAssign, @NotNull String field) {
        Intrinsics.checkNotNullParameter($this$minusAssign, "<this>");
        Intrinsics.checkNotNullParameter(field, "field");
        Unit unit = Unit.INSTANCE;
        $this$minusAssign.remove(field);
    }

    public static final void minusAssign(@NotNull ObjectNode $this$minusAssign, @NotNull Collection<String> fields) {
        Intrinsics.checkNotNullParameter($this$minusAssign, "<this>");
        Intrinsics.checkNotNullParameter(fields, "fields");
        Unit unit = Unit.INSTANCE;
        $this$minusAssign.remove(fields);
    }

    public static final boolean contains(@NotNull JsonNode $this$contains, @NotNull String field) {
        Intrinsics.checkNotNullParameter($this$contains, "<this>");
        Intrinsics.checkNotNullParameter(field, "field");
        return $this$contains.has(field);
    }

    public static final boolean contains(@NotNull JsonNode $this$contains, int index) {
        Intrinsics.checkNotNullParameter($this$contains, "<this>");
        return $this$contains.has(index);
    }

    public static final JsonMappingException wrapWithPath(@NotNull JsonMappingException $this$wrapWithPath, @Nullable Object refFrom, @NotNull String refFieldName) {
        Intrinsics.checkNotNullParameter($this$wrapWithPath, "<this>");
        Intrinsics.checkNotNullParameter(refFieldName, "refFieldName");
        return JsonMappingException.wrapWithPath($this$wrapWithPath, refFrom, refFieldName);
    }

    public static final JsonMappingException wrapWithPath(@NotNull JsonMappingException $this$wrapWithPath, @Nullable Object refFrom, int index) {
        Intrinsics.checkNotNullParameter($this$wrapWithPath, "<this>");
        return JsonMappingException.wrapWithPath($this$wrapWithPath, refFrom, index);
    }

    public static final /* synthetic */ <T> SimpleModule addSerializer(SimpleModule $this$addSerializer, KClass<T> kClass, JsonSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter($this$addSerializer, "<this>");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        $this$addSerializer.addSerializer(JvmClassMappingKt.getJavaClass((KClass) kClass), serializer);
        $this$addSerializer.addSerializer(JvmClassMappingKt.getJavaObjectType(kClass), serializer);
        return $this$addSerializer;
    }

    public static final /* synthetic */ <T> SimpleModule addDeserializer(SimpleModule $this$addDeserializer, KClass<T> kClass, JsonDeserializer<T> deserializer) {
        Intrinsics.checkNotNullParameter($this$addDeserializer, "<this>");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        $this$addDeserializer.addDeserializer(JvmClassMappingKt.getJavaClass((KClass) kClass), deserializer);
        $this$addDeserializer.addDeserializer(JvmClassMappingKt.getJavaObjectType(kClass), deserializer);
        return $this$addDeserializer;
    }

    @NotNull
    public static final BitSet toBitSet(int $this$toBitSet) {
        int index = 0;
        BitSet bits = new BitSet(32);
        for (int i = $this$toBitSet; i != 0; i >>= 1) {
            if (i % 2 != 0) {
                bits.set(index);
            }
            index++;
        }
        return bits;
    }

    public static final boolean isUnboxableValueClass(@NotNull Class<?> cls) {
        boolean z;
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Object[] annotations = cls.getAnnotations();
        Intrinsics.checkNotNullExpressionValue(annotations, "annotations");
        Object[] $this$any$iv = annotations;
        int length = $this$any$iv.length;
        int i = 0;
        while (true) {
            if (i < length) {
                Object element$iv = $this$any$iv[i];
                Annotation it = (Annotation) element$iv;
                if (it instanceof JvmInline) {
                    z = true;
                    break;
                }
                i++;
            } else {
                z = false;
                break;
            }
        }
        return z && KotlinModuleKt.isKotlinClass(cls);
    }
}
