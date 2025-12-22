package io.vertx.kotlin.ext.sql;

import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ResultSet.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aD\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0003H\u0007\u001aB\u0010\t\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0003¨\u0006\n"}, d2 = {"ResultSet", "Lio/vertx/ext/sql/ResultSet;", "columnNames", "", "", "next", "output", "Lio/vertx/core/json/JsonArray;", "results", "resultSetOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/sql/ResultSetKt.class */
public final class ResultSetKt {
    @NotNull
    public static /* synthetic */ ResultSet resultSetOf$default(Iterable iterable, ResultSet resultSet, JsonArray jsonArray, Iterable iterable2, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            resultSet = (ResultSet) null;
        }
        if ((i & 4) != 0) {
            jsonArray = (JsonArray) null;
        }
        if ((i & 8) != 0) {
            iterable2 = (Iterable) null;
        }
        return resultSetOf(iterable, resultSet, jsonArray, iterable2);
    }

    @NotNull
    public static final ResultSet resultSetOf(@Nullable Iterable<String> iterable, @Nullable ResultSet next, @Nullable JsonArray output, @Nullable Iterable<? extends JsonArray> iterable2) {
        ResultSet $this$apply = new ResultSet();
        if (iterable != null) {
            $this$apply.setColumnNames(CollectionsKt.toList(iterable));
        }
        if (next != null) {
            $this$apply.setNext(next);
        }
        if (output != null) {
            $this$apply.setOutput(output);
        }
        if (iterable2 != null) {
            $this$apply.setResults(CollectionsKt.toList(iterable2));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "resultSetOf(columnNames, next, output, results)"))
    @NotNull
    public static /* synthetic */ ResultSet ResultSet$default(Iterable iterable, ResultSet resultSet, JsonArray jsonArray, Iterable iterable2, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            resultSet = (ResultSet) null;
        }
        if ((i & 4) != 0) {
            jsonArray = (JsonArray) null;
        }
        if ((i & 8) != 0) {
            iterable2 = (Iterable) null;
        }
        return ResultSet(iterable, resultSet, jsonArray, iterable2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "resultSetOf(columnNames, next, output, results)"))
    @NotNull
    public static final ResultSet ResultSet(@Nullable Iterable<String> iterable, @Nullable ResultSet next, @Nullable JsonArray output, @Nullable Iterable<? extends JsonArray> iterable2) {
        ResultSet $this$apply = new ResultSet();
        if (iterable != null) {
            $this$apply.setColumnNames(CollectionsKt.toList(iterable));
        }
        if (next != null) {
            $this$apply.setNext(next);
        }
        if (output != null) {
            $this$apply.setOutput(output);
        }
        if (iterable2 != null) {
            $this$apply.setResults(CollectionsKt.toList(iterable2));
        }
        return $this$apply;
    }
}
