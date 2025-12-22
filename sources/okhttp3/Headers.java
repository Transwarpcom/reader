package okhttp3;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: Headers.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��f\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\"\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0006\u0018�� '2\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0002&'B\u0015\b\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0012\u001a\u00020\u0003H\u0086\u0002J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0012\u001a\u00020\u0003J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0012\u001a\u00020\u0003H\u0007J\b\u0010\u0017\u001a\u00020\tH\u0016J\u001b\u0010\u0018\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0019H\u0096\u0002J\u000e\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\tJ\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u001cJ\u0006\u0010\u001d\u001a\u00020\u001eJ\r\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b\u001fJ\u0018\u0010 \u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\"0!J\b\u0010#\u001a\u00020\u0003H\u0016J\u000e\u0010$\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\tJ\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00030\"2\u0006\u0010\u0012\u001a\u00020\u0003R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8G¢\u0006\u0006\u001a\u0004\b\b\u0010\n¨\u0006("}, d2 = {"Lokhttp3/Headers;", "", "Lkotlin/Pair;", "", "namesAndValues", "", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "size", "", "()I", "byteCount", "", "equals", "", "other", "", BeanUtil.PREFIX_GETTER_GET, "name", "getDate", "Ljava/util/Date;", "getInstant", "Ljava/time/Instant;", IdentityNamingStrategy.HASH_CODE_KEY, "iterator", "", "index", "names", "", "newBuilder", "Lokhttp3/Headers$Builder;", "-deprecated_size", "toMultimap", "", "", "toString", "value", "values", "Builder", "Companion", "okhttp"})
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/Headers.class */
public final class Headers implements Iterable<Pair<? extends String, ? extends String>>, KMappedMarker {
    private final String[] namesAndValues;
    public static final Companion Companion = new Companion(null);

    @JvmStatic
    @JvmName(name = "of")
    @NotNull
    public static final Headers of(@NotNull String... namesAndValues) {
        return Companion.of(namesAndValues);
    }

    @JvmStatic
    @JvmName(name = "of")
    @NotNull
    public static final Headers of(@NotNull Map<String, String> map) {
        return Companion.of(map);
    }

    private Headers(String[] namesAndValues) {
        this.namesAndValues = namesAndValues;
    }

    public /* synthetic */ Headers(String[] namesAndValues, DefaultConstructorMarker $constructor_marker) {
        this(namesAndValues);
    }

    @Nullable
    public final String get(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return Companion.get(this.namesAndValues, name);
    }

    @Nullable
    public final Date getDate(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        String str = get(name);
        if (str != null) {
            return DatesKt.toHttpDateOrNull(str);
        }
        return null;
    }

    @IgnoreJRERequirement
    @Nullable
    public final Instant getInstant(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Date value = getDate(name);
        if (value != null) {
            return value.toInstant();
        }
        return null;
    }

    @JvmName(name = "size")
    public final int size() {
        return this.namesAndValues.length / 2;
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "size"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_size")
    /* renamed from: -deprecated_size, reason: not valid java name */
    public final int m4474deprecated_size() {
        return size();
    }

    @NotNull
    public final String name(int index) {
        return this.namesAndValues[index * 2];
    }

    @NotNull
    public final String value(int index) {
        return this.namesAndValues[(index * 2) + 1];
    }

    @NotNull
    public final Set<String> names() {
        TreeSet result = new TreeSet(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
        int size = size();
        for (int i = 0; i < size; i++) {
            result.add(name(i));
        }
        Set<String> setUnmodifiableSet = Collections.unmodifiableSet(result);
        Intrinsics.checkNotNullExpressionValue(setUnmodifiableSet, "Collections.unmodifiableSet(result)");
        return setUnmodifiableSet;
    }

    @NotNull
    public final List<String> values(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        List result = (List) null;
        int size = size();
        for (int i = 0; i < size; i++) {
            if (StringsKt.equals(name, name(i), true)) {
                if (result == null) {
                    result = new ArrayList(2);
                }
                result.add(value(i));
            }
        }
        if (result != null) {
            List<String> listUnmodifiableList = Collections.unmodifiableList(result);
            Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "Collections.unmodifiableList(result)");
            return listUnmodifiableList;
        }
        return CollectionsKt.emptyList();
    }

    public final long byteCount() {
        long result = this.namesAndValues.length * 2;
        for (int i = 0; i < this.namesAndValues.length; i++) {
            result += this.namesAndValues[i].length();
        }
        return result;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Pair<? extends String, ? extends String>> iterator() {
        int size = size();
        Pair[] pairArr = new Pair[size];
        for (int i = 0; i < size; i++) {
            int it = i;
            pairArr[i] = TuplesKt.to(name(it), value(it));
        }
        return ArrayIteratorKt.iterator(pairArr);
    }

    @NotNull
    public final Builder newBuilder() {
        Builder result = new Builder();
        CollectionsKt.addAll(result.getNamesAndValues$okhttp(), this.namesAndValues);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof Headers) && Arrays.equals(this.namesAndValues, ((Headers) other).namesAndValues);
    }

    public int hashCode() {
        return Arrays.hashCode(this.namesAndValues);
    }

    @NotNull
    public String toString() {
        StringBuilder $this$buildString = new StringBuilder();
        int size = size();
        for (int i = 0; i < size; i++) {
            $this$buildString.append(name(i));
            $this$buildString.append(": ");
            $this$buildString.append(value(i));
            $this$buildString.append("\n");
        }
        String string = $this$buildString.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @NotNull
    public final Map<String, List<String>> toMultimap() {
        TreeMap result = new TreeMap(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
        int size = size();
        for (int i = 0; i < size; i++) {
            String strName = name(i);
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "Locale.US");
            if (strName == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String name = strName.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(name, "(this as java.lang.String).toLowerCase(locale)");
            List values = (List) result.get(name);
            if (values == null) {
                values = new ArrayList(2);
                result.put(name, values);
            }
            values.add(value(i));
        }
        return result;
    }

    /* compiled from: Headers.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020��2\u0006\u0010\t\u001a\u00020\u0005J\u0018\u0010\b\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0016\u0010\b\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\rJ\u0016\u0010\b\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020��2\u0006\u0010\u000f\u001a\u00020\u0010J\u0015\u0010\u0011\u001a\u00020��2\u0006\u0010\t\u001a\u00020\u0005H��¢\u0006\u0002\b\u0012J\u001d\u0010\u0011\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H��¢\u0006\u0002\b\u0012J\u0016\u0010\u0013\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005J\u0006\u0010\u0014\u001a\u00020\u0010J\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u0005H\u0086\u0002J\u000e\u0010\u0016\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u0005J\u0019\u0010\u0017\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0087\u0002J\u0019\u0010\u0017\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\rH\u0086\u0002J\u0019\u0010\u0017\u001a\u00020��2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0086\u0002R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lokhttp3/Headers$Builder;", "", "()V", "namesAndValues", "", "", "getNamesAndValues$okhttp", "()Ljava/util/List;", BeanUtil.PREFIX_ADDER, "line", "name", "value", "Ljava/time/Instant;", "Ljava/util/Date;", "addAll", "headers", "Lokhttp3/Headers;", "addLenient", "addLenient$okhttp", "addUnsafeNonAscii", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, BeanUtil.PREFIX_GETTER_GET, "removeAll", "set", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/Headers$Builder.class */
    public static final class Builder {

        @NotNull
        private final List<String> namesAndValues = new ArrayList(20);

        @NotNull
        public final List<String> getNamesAndValues$okhttp() {
            return this.namesAndValues;
        }

        @NotNull
        public final Builder addLenient$okhttp(@NotNull String line) {
            Intrinsics.checkNotNullParameter(line, "line");
            Builder $this$apply = this;
            int index = StringsKt.indexOf$default((CharSequence) line, ':', 1, false, 4, (Object) null);
            if (index != -1) {
                String strSubstring = line.substring(0, index);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                String strSubstring2 = line.substring(index + 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.String).substring(startIndex)");
                $this$apply.addLenient$okhttp(strSubstring, strSubstring2);
            } else if (line.charAt(0) == ':') {
                String strSubstring3 = line.substring(1);
                Intrinsics.checkNotNullExpressionValue(strSubstring3, "(this as java.lang.String).substring(startIndex)");
                $this$apply.addLenient$okhttp("", strSubstring3);
            } else {
                $this$apply.addLenient$okhttp("", line);
            }
            return this;
        }

        @NotNull
        public final Builder add(@NotNull String line) {
            Intrinsics.checkNotNullParameter(line, "line");
            Builder $this$apply = this;
            int index = StringsKt.indexOf$default((CharSequence) line, ':', 0, false, 6, (Object) null);
            if (!(index != -1)) {
                throw new IllegalArgumentException(("Unexpected header: " + line).toString());
            }
            String strSubstring = line.substring(0, index);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            if (strSubstring == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            String string = StringsKt.trim((CharSequence) strSubstring).toString();
            String strSubstring2 = line.substring(index + 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.String).substring(startIndex)");
            $this$apply.add(string, strSubstring2);
            return this;
        }

        @NotNull
        public final Builder add(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder $this$apply = this;
            Headers.Companion.checkName(name);
            Headers.Companion.checkValue(value, name);
            $this$apply.addLenient$okhttp(name, value);
            return this;
        }

        @NotNull
        public final Builder addUnsafeNonAscii(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder $this$apply = this;
            Headers.Companion.checkName(name);
            $this$apply.addLenient$okhttp(name, value);
            return this;
        }

        @NotNull
        public final Builder addAll(@NotNull Headers headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            Builder $this$apply = this;
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                $this$apply.addLenient$okhttp(headers.name(i), headers.value(i));
            }
            return this;
        }

        @NotNull
        public final Builder add(@NotNull String name, @NotNull Date value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder $this$apply = this;
            $this$apply.add(name, DatesKt.toHttpDateString(value));
            return this;
        }

        @IgnoreJRERequirement
        @NotNull
        public final Builder add(@NotNull String name, @NotNull Instant value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder $this$apply = this;
            $this$apply.add(name, new Date(value.toEpochMilli()));
            return this;
        }

        @NotNull
        public final Builder set(@NotNull String name, @NotNull Date value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder $this$apply = this;
            $this$apply.set(name, DatesKt.toHttpDateString(value));
            return this;
        }

        @IgnoreJRERequirement
        @NotNull
        public final Builder set(@NotNull String name, @NotNull Instant value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder $this$apply = this;
            return $this$apply.set(name, new Date(value.toEpochMilli()));
        }

        @NotNull
        public final Builder addLenient$okhttp(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder $this$apply = this;
            $this$apply.namesAndValues.add(name);
            $this$apply.namesAndValues.add(StringsKt.trim((CharSequence) value).toString());
            return this;
        }

        @NotNull
        public final Builder removeAll(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            Builder $this$apply = this;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= $this$apply.namesAndValues.size()) {
                    return this;
                }
                if (StringsKt.equals(name, $this$apply.namesAndValues.get(i2), true)) {
                    $this$apply.namesAndValues.remove(i2);
                    $this$apply.namesAndValues.remove(i2);
                    i2 -= 2;
                }
                i = i2 + 2;
            }
        }

        @NotNull
        public final Builder set(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Builder $this$apply = this;
            Headers.Companion.checkName(name);
            Headers.Companion.checkValue(value, name);
            $this$apply.removeAll(name);
            $this$apply.addLenient$okhttp(name, value);
            return this;
        }

        @Nullable
        public final String get(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            IntProgression intProgressionStep = RangesKt.step(RangesKt.downTo(this.namesAndValues.size() - 2, 0), 2);
            int i = intProgressionStep.getFirst();
            int last = intProgressionStep.getLast();
            int step = intProgressionStep.getStep();
            if (step >= 0) {
                if (i > last) {
                    return null;
                }
            } else if (i < last) {
                return null;
            }
            while (!StringsKt.equals(name, this.namesAndValues.get(i), true)) {
                if (i == last) {
                    return null;
                }
                i += step;
            }
            return this.namesAndValues.get(i + 1);
        }

        @NotNull
        public final Headers build() {
            Collection $this$toTypedArray$iv = this.namesAndValues;
            Object[] array = $this$toTypedArray$iv.toArray(new String[0]);
            if (array == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            return new Headers((String[]) array, null);
        }
    }

    /* compiled from: Headers.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J%\u0010\t\u001a\u0004\u0018\u00010\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010\fJ#\u0010\r\u001a\u00020\u000e2\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u000b\"\u00020\u0006H\u0007¢\u0006\u0004\b\u000f\u0010\u0010J#\u0010\u000f\u001a\u00020\u000e2\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u000b\"\u00020\u0006H\u0007¢\u0006\u0004\b\u0011\u0010\u0010J!\u0010\u000f\u001a\u00020\u000e2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0013H\u0007¢\u0006\u0002\b\u0011J\u001d\u0010\u0014\u001a\u00020\u000e*\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0013H\u0007¢\u0006\u0002\b\u000f¨\u0006\u0015"}, d2 = {"Lokhttp3/Headers$Companion;", "", "()V", "checkName", "", "name", "", "checkValue", "value", BeanUtil.PREFIX_GETTER_GET, "namesAndValues", "", "([Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "headersOf", "Lokhttp3/Headers;", "of", "([Ljava/lang/String;)Lokhttp3/Headers;", "-deprecated_of", "headers", "", "toHeaders", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/Headers$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String get(String[] namesAndValues, String name) {
            IntProgression intProgressionStep = RangesKt.step(RangesKt.downTo(namesAndValues.length - 2, 0), 2);
            int i = intProgressionStep.getFirst();
            int last = intProgressionStep.getLast();
            int step = intProgressionStep.getStep();
            if (step >= 0) {
                if (i > last) {
                    return null;
                }
            } else if (i < last) {
                return null;
            }
            while (!StringsKt.equals(name, namesAndValues[i], true)) {
                if (i == last) {
                    return null;
                }
                i += step;
            }
            return namesAndValues[i + 1];
        }

        @JvmStatic
        @JvmName(name = "of")
        @NotNull
        public final Headers of(@NotNull String... namesAndValues) throws CloneNotSupportedException {
            Intrinsics.checkNotNullParameter(namesAndValues, "namesAndValues");
            if (!(namesAndValues.length % 2 == 0)) {
                throw new IllegalArgumentException("Expected alternating header names and values".toString());
            }
            Object objClone = namesAndValues.clone();
            if (objClone == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
            }
            String[] namesAndValues2 = (String[]) objClone;
            int length = namesAndValues2.length;
            for (int i = 0; i < length; i++) {
                if (!(namesAndValues2[i] != null)) {
                    throw new IllegalArgumentException("Headers cannot be null".toString());
                }
                int i2 = i;
                String str = namesAndValues2[i];
                if (str == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                namesAndValues2[i2] = StringsKt.trim((CharSequence) str).toString();
            }
            IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, namesAndValues2.length), 2);
            int i3 = intProgressionStep.getFirst();
            int last = intProgressionStep.getLast();
            int step = intProgressionStep.getStep();
            if (step < 0 ? i3 >= last : i3 <= last) {
                while (true) {
                    String name = namesAndValues2[i3];
                    String value = namesAndValues2[i3 + 1];
                    checkName(name);
                    checkValue(value, name);
                    if (i3 == last) {
                        break;
                    }
                    i3 += step;
                }
            }
            return new Headers(namesAndValues2, null);
        }

        @Deprecated(message = "function name changed", replaceWith = @ReplaceWith(imports = {}, expression = "headersOf(*namesAndValues)"), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_of")
        @NotNull
        /* renamed from: -deprecated_of, reason: not valid java name */
        public final Headers m4476deprecated_of(@NotNull String... namesAndValues) {
            Intrinsics.checkNotNullParameter(namesAndValues, "namesAndValues");
            return of((String[]) Arrays.copyOf(namesAndValues, namesAndValues.length));
        }

        @JvmStatic
        @JvmName(name = "of")
        @NotNull
        public final Headers of(@NotNull Map<String, String> toHeaders) {
            Intrinsics.checkNotNullParameter(toHeaders, "$this$toHeaders");
            String[] namesAndValues = new String[toHeaders.size() * 2];
            int i = 0;
            for (Map.Entry<String, String> entry : toHeaders.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                if (k == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                String name = StringsKt.trim((CharSequence) k).toString();
                if (v == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                String value = StringsKt.trim((CharSequence) v).toString();
                checkName(name);
                checkValue(value, name);
                namesAndValues[i] = name;
                namesAndValues[i + 1] = value;
                i += 2;
            }
            return new Headers(namesAndValues, null);
        }

        @Deprecated(message = "function moved to extension", replaceWith = @ReplaceWith(imports = {}, expression = "headers.toHeaders()"), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_of")
        @NotNull
        /* renamed from: -deprecated_of, reason: not valid java name */
        public final Headers m4477deprecated_of(@NotNull Map<String, String> headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            return of(headers);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void checkName(String name) {
            if (!(name.length() > 0)) {
                throw new IllegalArgumentException("name is empty".toString());
            }
            int length = name.length();
            for (int i = 0; i < length; i++) {
                char c = name.charAt(i);
                if (!('!' <= c && '~' >= c)) {
                    throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(c), Integer.valueOf(i), name).toString());
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void checkValue(String value, String name) {
            int length = value.length();
            for (int i = 0; i < length; i++) {
                char c = value.charAt(i);
                if (!(c == '\t' || (' ' <= c && '~' >= c))) {
                    throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(c), Integer.valueOf(i), name, value).toString());
                }
            }
        }
    }
}
