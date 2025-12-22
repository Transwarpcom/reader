package io.vertx.core;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.http.CaseInsensitiveHeaders;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/MultiMap.class */
public interface MultiMap extends Iterable<Map.Entry<String, String>> {
    @GenIgnore({"permitted-type"})
    String get(CharSequence charSequence);

    String get(String str);

    List<String> getAll(String str);

    @GenIgnore({"permitted-type"})
    List<String> getAll(CharSequence charSequence);

    @GenIgnore({"permitted-type"})
    List<Map.Entry<String, String>> entries();

    boolean contains(String str);

    @GenIgnore({"permitted-type"})
    boolean contains(CharSequence charSequence);

    boolean isEmpty();

    Set<String> names();

    @Fluent
    MultiMap add(String str, String str2);

    @GenIgnore({"permitted-type"})
    @Fluent
    MultiMap add(CharSequence charSequence, CharSequence charSequence2);

    @GenIgnore({"permitted-type"})
    @Fluent
    /* renamed from: add */
    MultiMap mo1938add(String str, Iterable<String> iterable);

    @GenIgnore({"permitted-type"})
    @Fluent
    /* renamed from: add */
    MultiMap mo1937add(CharSequence charSequence, Iterable<CharSequence> iterable);

    @Fluent
    MultiMap addAll(MultiMap multiMap);

    @GenIgnore({"permitted-type"})
    @Fluent
    MultiMap addAll(Map<String, String> map);

    @Fluent
    MultiMap set(String str, String str2);

    @GenIgnore({"permitted-type"})
    @Fluent
    MultiMap set(CharSequence charSequence, CharSequence charSequence2);

    @GenIgnore({"permitted-type"})
    @Fluent
    /* renamed from: set */
    MultiMap mo1936set(String str, Iterable<String> iterable);

    @GenIgnore({"permitted-type"})
    @Fluent
    /* renamed from: set */
    MultiMap mo1935set(CharSequence charSequence, Iterable<CharSequence> iterable);

    @Fluent
    MultiMap setAll(MultiMap multiMap);

    @GenIgnore({"permitted-type"})
    @Fluent
    MultiMap setAll(Map<String, String> map);

    @Fluent
    /* renamed from: remove */
    MultiMap mo1934remove(String str);

    @GenIgnore({"permitted-type"})
    @Fluent
    /* renamed from: remove */
    MultiMap mo1933remove(CharSequence charSequence);

    @Fluent
    /* renamed from: clear */
    MultiMap mo1932clear();

    int size();

    static MultiMap caseInsensitiveMultiMap() {
        return new CaseInsensitiveHeaders();
    }

    default boolean contains(String name, String value, boolean caseInsensitive) {
        return getAll(name).stream().anyMatch(val -> {
            return caseInsensitive ? val.equalsIgnoreCase(value) : val.equals(value);
        });
    }

    @GenIgnore({"permitted-type"})
    default boolean contains(CharSequence name, CharSequence value, boolean caseInsensitive) {
        Predicate<String> predicate;
        if (caseInsensitive) {
            String valueAsString = value.toString();
            predicate = val -> {
                return val.equalsIgnoreCase(valueAsString);
            };
        } else {
            predicate = val2 -> {
                return val2.contentEquals(value);
            };
        }
        return getAll(name).stream().anyMatch(predicate);
    }
}
