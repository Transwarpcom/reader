package io.vertx.kotlin.core.json;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: json.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��R\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010$\n��\u001a%\u0010��\u001a\u00020\u00012\u0016\u0010\u0002\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00040\u0003\"\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0002\u0010\u0005\u001a=\u0010\u0006\u001a\u00020\u00072.\u0010\b\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\t0\u0003\"\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\tH\u0007¢\u0006\u0002\u0010\u000b\u001a-\u0010\f\u001a\u0002H\r\"\u0004\b��\u0010\r2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u0002H\r0\u000f¢\u0006\u0002\b\u0011H\u0086\b¢\u0006\u0002\u0010\u0012\u001a#\u0010\u0013\u001a\u00020\u00012\u0016\u0010\u0002\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00040\u0003\"\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005\u001a;\u0010\u0014\u001a\u00020\u00072.\u0010\b\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\t0\u0003\"\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\t¢\u0006\u0002\u0010\u000b\u001a\u0012\u0010\u0015\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0001\u001a#\u0010\u0015\u001a\u00020\u0001*\u00020\u00102\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00170\u000f¢\u0006\u0002\b\u0011\u001a\u0012\u0010\u0015\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0007\u001a'\u0010\u0015\u001a\u00020\u0001*\u00020\u00102\u0016\u0010\u0002\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00040\u0003\"\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0018\u001a\u001a\u0010\u0015\u001a\u00020\u0001*\u00020\u00102\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0019\u001a \u0010\u001a\u001a\u0002H\r\"\u0004\b��\u0010\r*\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u001cH\u0086\u0002¢\u0006\u0002\u0010\u001d\u001a \u0010\u001a\u001a\u0002H\r\"\u0004\b��\u0010\r*\u00020\u00072\u0006\u0010\u001e\u001a\u00020\nH\u0086\u0002¢\u0006\u0002\u0010\u001f\u001a#\u0010 \u001a\u00020\u0007*\u00020\u00102\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00170\u000f¢\u0006\u0002\b\u0011\u001a?\u0010 \u001a\u00020\u0007*\u00020\u00102.\u0010\b\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\t0\u0003\"\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\t¢\u0006\u0002\u0010!\u001a&\u0010 \u001a\u00020\u0007*\u00020\u00102\u001a\u0010\b\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\t0\u0019\u001a \u0010 \u001a\u00020\u0007*\u00020\u00102\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\"¨\u0006#"}, d2 = {"JsonArray", "Lio/vertx/core/json/JsonArray;", "values", "", "", "([Ljava/lang/Object;)Lio/vertx/core/json/JsonArray;", "JsonObject", "Lio/vertx/core/json/JsonObject;", "fields", "Lkotlin/Pair;", "", "([Lkotlin/Pair;)Lio/vertx/core/json/JsonObject;", "json", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "block", "Lkotlin/Function1;", "Lio/vertx/kotlin/core/json/Json;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "jsonArrayOf", "jsonObjectOf", BeanDefinitionParserDelegate.ARRAY_ELEMENT, "value", "", "(Lio/vertx/kotlin/core/json/Json;[Ljava/lang/Object;)Lio/vertx/core/json/JsonArray;", "", BeanUtil.PREFIX_GETTER_GET, "index", "", "(Lio/vertx/core/json/JsonArray;I)Ljava/lang/Object;", "key", "(Lio/vertx/core/json/JsonObject;Ljava/lang/String;)Ljava/lang/Object;", "obj", "(Lio/vertx/kotlin/core/json/Json;[Lkotlin/Pair;)Lio/vertx/core/json/JsonObject;", "", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/json/JsonKt.class */
public final class JsonKt {
    @NotNull
    public static final JsonObject jsonObjectOf(@NotNull Pair<String, ? extends Object>... fields) {
        Pair<String, ? extends Object> pair;
        Intrinsics.checkParameterIsNotNull(fields, "fields");
        Collection destination$iv$iv = new ArrayList(fields.length);
        for (Pair<String, ? extends Object> pair2 : fields) {
            if (pair2.getSecond() instanceof Instant) {
                String first = pair2.getFirst();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_INSTANT;
                Object second = pair2.getSecond();
                if (second == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.time.Instant");
                }
                pair = new Pair<>(first, dateTimeFormatter.format((Instant) second));
            } else if (pair2.getSecond() instanceof byte[]) {
                String first2 = pair2.getFirst();
                Base64.Encoder encoder = Base64.getEncoder();
                Object second2 = pair2.getSecond();
                if (second2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.ByteArray");
                }
                pair = new Pair<>(first2, encoder.encodeToString((byte[]) second2));
            } else {
                pair = pair2;
            }
            destination$iv$iv.add(pair);
        }
        Collection $this$toTypedArray$iv = (List) destination$iv$iv;
        Object[] array = $this$toTypedArray$iv.toArray(new Pair[0]);
        if (array == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        Pair[] processedCases = (Pair[]) array;
        return new JsonObject(MapsKt.linkedMapOf((Pair[]) Arrays.copyOf(processedCases, processedCases.length)));
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "jsonObjectOf(*fields)"))
    @NotNull
    public static final JsonObject JsonObject(@NotNull Pair<String, ? extends Object>... fields) {
        Intrinsics.checkParameterIsNotNull(fields, "fields");
        return jsonObjectOf((Pair[]) Arrays.copyOf(fields, fields.length));
    }

    @NotNull
    public static final JsonArray jsonArrayOf(@NotNull Object... values) {
        Intrinsics.checkParameterIsNotNull(values, "values");
        return new JsonArray(CollectionsKt.arrayListOf(Arrays.copyOf(values, values.length)));
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "jsonArrayOf(*values)"))
    @NotNull
    public static final JsonArray JsonArray(@NotNull Object... values) {
        Intrinsics.checkParameterIsNotNull(values, "values");
        return jsonArrayOf(Arrays.copyOf(values, values.length));
    }

    public static final <T> T json(@NotNull Function1<? super Json, ? extends T> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        return block.invoke(Json.INSTANCE);
    }

    @NotNull
    public static final JsonObject obj(@NotNull Json obj, @NotNull Pair<String, ? extends Object>... fields) {
        Intrinsics.checkParameterIsNotNull(obj, "$this$obj");
        Intrinsics.checkParameterIsNotNull(fields, "fields");
        return jsonObjectOf((Pair[]) Arrays.copyOf(fields, fields.length));
    }

    @NotNull
    public static final JsonArray array(@NotNull Json array, @NotNull Object... values) {
        Intrinsics.checkParameterIsNotNull(array, "$this$array");
        Intrinsics.checkParameterIsNotNull(values, "values");
        return jsonArrayOf(Arrays.copyOf(values, values.length));
    }

    @NotNull
    public static final JsonObject obj(@NotNull Json obj, @NotNull Iterable<? extends Pair<String, ? extends Object>> fields) {
        Intrinsics.checkParameterIsNotNull(obj, "$this$obj");
        Intrinsics.checkParameterIsNotNull(fields, "fields");
        Collection $this$toTypedArray$iv = CollectionsKt.toList(fields);
        if ($this$toTypedArray$iv == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
        }
        Object[] array = $this$toTypedArray$iv.toArray(new Pair[0]);
        if (array == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        Pair[] pairArr = (Pair[]) array;
        return jsonObjectOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
    }

    @NotNull
    public static final JsonObject obj(@NotNull Json obj, @NotNull Map<String, ? extends Object> fields) {
        Intrinsics.checkParameterIsNotNull(obj, "$this$obj");
        Intrinsics.checkParameterIsNotNull(fields, "fields");
        return new JsonObject(fields);
    }

    @NotNull
    public static final JsonArray array(@NotNull Json array, @NotNull Iterable<? extends Object> values) {
        Intrinsics.checkParameterIsNotNull(array, "$this$array");
        Intrinsics.checkParameterIsNotNull(values, "values");
        Collection $this$toTypedArray$iv = CollectionsKt.toList(values);
        if ($this$toTypedArray$iv == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
        }
        Object[] array2 = $this$toTypedArray$iv.toArray(new Object[0]);
        if (array2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return jsonArrayOf(Arrays.copyOf(array2, array2.length));
    }

    @NotNull
    public static final JsonObject obj(@NotNull Json obj, @NotNull Function1<? super JsonObject, Unit> block) {
        Intrinsics.checkParameterIsNotNull(obj, "$this$obj");
        Intrinsics.checkParameterIsNotNull(block, "block");
        JsonObject jsonObjectJsonObjectOf = jsonObjectOf(new Pair[0]);
        block.invoke(jsonObjectJsonObjectOf);
        return jsonObjectJsonObjectOf;
    }

    @NotNull
    public static final JsonArray array(@NotNull Json array, @NotNull Function1<? super JsonArray, Unit> block) {
        Intrinsics.checkParameterIsNotNull(array, "$this$array");
        Intrinsics.checkParameterIsNotNull(block, "block");
        JsonArray jsonArrayJsonArrayOf = jsonArrayOf(new Object[0]);
        block.invoke(jsonArrayJsonArrayOf);
        return jsonArrayJsonArrayOf;
    }

    @NotNull
    public static final JsonArray array(@NotNull Json array, @NotNull JsonObject value) {
        Intrinsics.checkParameterIsNotNull(array, "$this$array");
        Intrinsics.checkParameterIsNotNull(value, "value");
        return jsonArrayOf(value);
    }

    @NotNull
    public static final JsonArray array(@NotNull Json array, @NotNull JsonArray value) {
        Intrinsics.checkParameterIsNotNull(array, "$this$array");
        Intrinsics.checkParameterIsNotNull(value, "value");
        return jsonArrayOf(value);
    }

    public static final <T> T get(@NotNull JsonObject get, @NotNull String key) {
        Intrinsics.checkParameterIsNotNull(get, "$this$get");
        Intrinsics.checkParameterIsNotNull(key, "key");
        return (T) get.getValue(key);
    }

    public static final <T> T get(@NotNull JsonArray get, int i) {
        Intrinsics.checkParameterIsNotNull(get, "$this$get");
        return (T) get.getValue(i);
    }
}
