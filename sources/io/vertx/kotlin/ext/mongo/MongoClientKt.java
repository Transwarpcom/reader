package io.vertx.kotlin.ext.mongo;

import ch.qos.logback.core.pattern.parser.Parser;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.BulkOperation;
import io.vertx.ext.mongo.BulkWriteOptions;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.mongo.IndexOptions;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.mongo.MongoClientBulkWriteResult;
import io.vertx.ext.mongo.MongoClientDeleteResult;
import io.vertx.ext.mongo.MongoClientUpdateResult;
import io.vertx.ext.mongo.UpdateOptions;
import io.vertx.ext.mongo.WriteOption;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MongoClient.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��t\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u001a-\u0010��\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a5\u0010\t\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a%\u0010\r\u001a\u00020\u000e*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011\u001a\u001d\u0010\u0012\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0015\u001a%\u0010\u0016\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011\u001a-\u0010\u0018\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@ø\u0001��¢\u0006\u0002\u0010\u001b\u001a-\u0010\u001c\u001a\u00020\u001d*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010 \u001a5\u0010!\u001a\u00020\u001d*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010\"\u001a\u001d\u0010#\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0015\u001a%\u0010$\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010&\u001a+\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00100\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011\u001a'\u0010(\u001a\u0004\u0018\u00010\u0010*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011\u001a/\u0010)\u001a\u0004\u0018\u00010\u0010*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010*\u001a\u00020+H\u0086@ø\u0001��¢\u0006\u0002\u0010,\u001a/\u0010-\u001a\u0004\u0018\u00010\u0010*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010/\u001a?\u00100\u001a\u0004\u0018\u00010\u0010*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u00102\u0006\u0010*\u001a\u00020+2\u0006\u00101\u001a\u000202H\u0086@ø\u0001��¢\u0006\u0002\u00103\u001a/\u00104\u001a\u0004\u0018\u00010\u0010*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010/\u001a?\u00106\u001a\u0004\u0018\u00010\u0010*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u00102\u0006\u0010*\u001a\u00020+2\u0006\u00101\u001a\u000202H\u0086@ø\u0001��¢\u0006\u0002\u00103\u001a1\u00107\u001a\u0004\u0018\u00010\u0010*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\b\u00108\u001a\u0004\u0018\u00010\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010/\u001a3\u00109\u001a\b\u0012\u0004\u0012\u00020\u00100\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020+H\u0086@ø\u0001��¢\u0006\u0002\u0010,\u001a\u001b\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010;\u001a'\u0010<\u001a\u0004\u0018\u00010\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011\u001a1\u0010>\u001a\u0004\u0018\u00010\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u00102\b\u0010?\u001a\u0004\u0018\u00010@H\u0086@ø\u0001��¢\u0006\u0002\u0010A\u001a\u001d\u0010B\u001a\u00020\u001d*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0015\u001a'\u0010C\u001a\u0004\u0018\u00010D*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011\u001a1\u0010E\u001a\u0004\u0018\u00010D*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010?\u001a\u0004\u0018\u00010@H\u0086@ø\u0001��¢\u0006\u0002\u0010A\u001a'\u0010F\u001a\u0004\u0018\u00010D*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011\u001a1\u0010G\u001a\u0004\u0018\u00010D*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010?\u001a\u0004\u0018\u00010@H\u0086@ø\u0001��¢\u0006\u0002\u0010A\u001a/\u0010H\u001a\u0004\u0018\u00010I*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010/\u001a7\u0010J\u001a\u0004\u0018\u00010I*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u000202H\u0086@ø\u0001��¢\u0006\u0002\u0010K\u001a'\u0010L\u001a\u0004\u0018\u00010\u0010*\u00020\u00022\u0006\u0010M\u001a\u00020\u00042\u0006\u0010N\u001a\u00020\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011\u001a'\u0010O\u001a\u0004\u0018\u00010\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011\u001a1\u0010P\u001a\u0004\u0018\u00010\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u00102\b\u0010?\u001a\u0004\u0018\u00010@H\u0086@ø\u0001��¢\u0006\u0002\u0010A\u001a/\u0010Q\u001a\u0004\u0018\u00010I*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u0010H\u0086@ø\u0001��¢\u0006\u0002\u0010/\u001a7\u0010R\u001a\u0004\u0018\u00010I*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u000202H\u0086@ø\u0001��¢\u0006\u0002\u0010K\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006S"}, d2 = {"bulkWriteAwait", "Lio/vertx/ext/mongo/MongoClientBulkWriteResult;", "Lio/vertx/ext/mongo/MongoClient;", "collection", "", "operations", "", "Lio/vertx/ext/mongo/BulkOperation;", "(Lio/vertx/ext/mongo/MongoClient;Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bulkWriteWithOptionsAwait", "bulkWriteOptions", "Lio/vertx/ext/mongo/BulkWriteOptions;", "(Lio/vertx/ext/mongo/MongoClient;Ljava/lang/String;Ljava/util/List;Lio/vertx/ext/mongo/BulkWriteOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "countAwait", "", "query", "Lio/vertx/core/json/JsonObject;", "(Lio/vertx/ext/mongo/MongoClient;Ljava/lang/String;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createCollectionAwait", "", "collectionName", "(Lio/vertx/ext/mongo/MongoClient;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createIndexAwait", "key", "createIndexWithOptionsAwait", "options", "Lio/vertx/ext/mongo/IndexOptions;", "(Lio/vertx/ext/mongo/MongoClient;Ljava/lang/String;Lio/vertx/core/json/JsonObject;Lio/vertx/ext/mongo/IndexOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "distinctAwait", "Lio/vertx/core/json/JsonArray;", "fieldName", "resultClassname", "(Lio/vertx/ext/mongo/MongoClient;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "distinctWithQueryAwait", "(Lio/vertx/ext/mongo/MongoClient;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dropCollectionAwait", "dropIndexAwait", "indexName", "(Lio/vertx/ext/mongo/MongoClient;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findAwait", "findOneAndDeleteAwait", "findOneAndDeleteWithOptionsAwait", "findOptions", "Lio/vertx/ext/mongo/FindOptions;", "(Lio/vertx/ext/mongo/MongoClient;Ljava/lang/String;Lio/vertx/core/json/JsonObject;Lio/vertx/ext/mongo/FindOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findOneAndReplaceAwait", Parser.REPLACE_CONVERTER_WORD, "(Lio/vertx/ext/mongo/MongoClient;Ljava/lang/String;Lio/vertx/core/json/JsonObject;Lio/vertx/core/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findOneAndReplaceWithOptionsAwait", "updateOptions", "Lio/vertx/ext/mongo/UpdateOptions;", "(Lio/vertx/ext/mongo/MongoClient;Ljava/lang/String;Lio/vertx/core/json/JsonObject;Lio/vertx/core/json/JsonObject;Lio/vertx/ext/mongo/FindOptions;Lio/vertx/ext/mongo/UpdateOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findOneAndUpdateAwait", "update", "findOneAndUpdateWithOptionsAwait", "findOneAwait", "fields", "findWithOptionsAwait", "getCollectionsAwait", "(Lio/vertx/ext/mongo/MongoClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAwait", "document", "insertWithOptionsAwait", "writeOption", "Lio/vertx/ext/mongo/WriteOption;", "(Lio/vertx/ext/mongo/MongoClient;Ljava/lang/String;Lio/vertx/core/json/JsonObject;Lio/vertx/ext/mongo/WriteOption;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listIndexesAwait", "removeDocumentAwait", "Lio/vertx/ext/mongo/MongoClientDeleteResult;", "removeDocumentWithOptionsAwait", "removeDocumentsAwait", "removeDocumentsWithOptionsAwait", "replaceDocumentsAwait", "Lio/vertx/ext/mongo/MongoClientUpdateResult;", "replaceDocumentsWithOptionsAwait", "(Lio/vertx/ext/mongo/MongoClient;Ljava/lang/String;Lio/vertx/core/json/JsonObject;Lio/vertx/core/json/JsonObject;Lio/vertx/ext/mongo/UpdateOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runCommandAwait", "commandName", "command", "saveAwait", "saveWithOptionsAwait", "updateCollectionAwait", "updateCollectionWithOptionsAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/mongo/MongoClientKt.class */
public final class MongoClientKt {
    @Nullable
    public static final Object saveAwait(@NotNull final MongoClient $this$saveAwait, @NotNull final String collection, @NotNull final JsonObject document, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.saveAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$saveAwait.save(collection, document, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object saveWithOptionsAwait(@NotNull final MongoClient $this$saveWithOptionsAwait, @NotNull final String collection, @NotNull final JsonObject document, @Nullable final WriteOption writeOption, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.saveWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$saveWithOptionsAwait.saveWithOptions(collection, document, writeOption, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object insertAwait(@NotNull final MongoClient $this$insertAwait, @NotNull final String collection, @NotNull final JsonObject document, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.insertAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$insertAwait.insert(collection, document, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object insertWithOptionsAwait(@NotNull final MongoClient $this$insertWithOptionsAwait, @NotNull final String collection, @NotNull final JsonObject document, @Nullable final WriteOption writeOption, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.insertWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$insertWithOptionsAwait.insertWithOptions(collection, document, writeOption, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object updateCollectionAwait(@NotNull final MongoClient $this$updateCollectionAwait, @NotNull final String collection, @NotNull final JsonObject query, @NotNull final JsonObject update, @NotNull Continuation<? super MongoClientUpdateResult> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MongoClientUpdateResult>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.updateCollectionAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<MongoClientUpdateResult>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<MongoClientUpdateResult>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$updateCollectionAwait.updateCollection(collection, query, update, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object updateCollectionWithOptionsAwait(@NotNull final MongoClient $this$updateCollectionWithOptionsAwait, @NotNull final String collection, @NotNull final JsonObject query, @NotNull final JsonObject update, @NotNull final UpdateOptions options, @NotNull Continuation<? super MongoClientUpdateResult> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MongoClientUpdateResult>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.updateCollectionWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<MongoClientUpdateResult>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<MongoClientUpdateResult>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$updateCollectionWithOptionsAwait.updateCollectionWithOptions(collection, query, update, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object replaceDocumentsAwait(@NotNull final MongoClient $this$replaceDocumentsAwait, @NotNull final String collection, @NotNull final JsonObject query, @NotNull final JsonObject replace, @NotNull Continuation<? super MongoClientUpdateResult> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MongoClientUpdateResult>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.replaceDocumentsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<MongoClientUpdateResult>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<MongoClientUpdateResult>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$replaceDocumentsAwait.replaceDocuments(collection, query, replace, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object replaceDocumentsWithOptionsAwait(@NotNull final MongoClient $this$replaceDocumentsWithOptionsAwait, @NotNull final String collection, @NotNull final JsonObject query, @NotNull final JsonObject replace, @NotNull final UpdateOptions options, @NotNull Continuation<? super MongoClientUpdateResult> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MongoClientUpdateResult>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.replaceDocumentsWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<MongoClientUpdateResult>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<MongoClientUpdateResult>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$replaceDocumentsWithOptionsAwait.replaceDocumentsWithOptions(collection, query, replace, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object bulkWriteAwait(@NotNull final MongoClient $this$bulkWriteAwait, @NotNull final String collection, @NotNull final List<? extends BulkOperation> list, @NotNull Continuation<? super MongoClientBulkWriteResult> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MongoClientBulkWriteResult>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.bulkWriteAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<MongoClientBulkWriteResult>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<MongoClientBulkWriteResult>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$bulkWriteAwait.bulkWrite(collection, list, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object bulkWriteWithOptionsAwait(@NotNull final MongoClient $this$bulkWriteWithOptionsAwait, @NotNull final String collection, @NotNull final List<? extends BulkOperation> list, @NotNull final BulkWriteOptions bulkWriteOptions, @NotNull Continuation<? super MongoClientBulkWriteResult> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MongoClientBulkWriteResult>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.bulkWriteWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<MongoClientBulkWriteResult>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<MongoClientBulkWriteResult>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$bulkWriteWithOptionsAwait.bulkWriteWithOptions(collection, list, bulkWriteOptions, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object findAwait(@NotNull final MongoClient $this$findAwait, @NotNull final String collection, @NotNull final JsonObject query, @NotNull Continuation<? super List<? extends JsonObject>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends JsonObject>>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.findAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends JsonObject>>> handler) {
                invoke2((Handler<AsyncResult<List<JsonObject>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<JsonObject>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$findAwait.find(collection, query, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object findWithOptionsAwait(@NotNull final MongoClient $this$findWithOptionsAwait, @NotNull final String collection, @NotNull final JsonObject query, @NotNull final FindOptions options, @NotNull Continuation<? super List<? extends JsonObject>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends JsonObject>>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.findWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends JsonObject>>> handler) {
                invoke2((Handler<AsyncResult<List<JsonObject>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<JsonObject>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$findWithOptionsAwait.findWithOptions(collection, query, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object findOneAwait(@NotNull final MongoClient $this$findOneAwait, @NotNull final String collection, @NotNull final JsonObject query, @Nullable final JsonObject fields, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.findOneAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonObject>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonObject>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$findOneAwait.findOne(collection, query, fields, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object findOneAndUpdateAwait(@NotNull final MongoClient $this$findOneAndUpdateAwait, @NotNull final String collection, @NotNull final JsonObject query, @NotNull final JsonObject update, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.findOneAndUpdateAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonObject>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonObject>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$findOneAndUpdateAwait.findOneAndUpdate(collection, query, update, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object findOneAndUpdateWithOptionsAwait(@NotNull final MongoClient $this$findOneAndUpdateWithOptionsAwait, @NotNull final String collection, @NotNull final JsonObject query, @NotNull final JsonObject update, @NotNull final FindOptions findOptions, @NotNull final UpdateOptions updateOptions, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.findOneAndUpdateWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonObject>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonObject>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$findOneAndUpdateWithOptionsAwait.findOneAndUpdateWithOptions(collection, query, update, findOptions, updateOptions, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object findOneAndReplaceAwait(@NotNull final MongoClient $this$findOneAndReplaceAwait, @NotNull final String collection, @NotNull final JsonObject query, @NotNull final JsonObject replace, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.findOneAndReplaceAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonObject>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonObject>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$findOneAndReplaceAwait.findOneAndReplace(collection, query, replace, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object findOneAndReplaceWithOptionsAwait(@NotNull final MongoClient $this$findOneAndReplaceWithOptionsAwait, @NotNull final String collection, @NotNull final JsonObject query, @NotNull final JsonObject replace, @NotNull final FindOptions findOptions, @NotNull final UpdateOptions updateOptions, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.findOneAndReplaceWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonObject>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonObject>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$findOneAndReplaceWithOptionsAwait.findOneAndReplaceWithOptions(collection, query, replace, findOptions, updateOptions, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object findOneAndDeleteAwait(@NotNull final MongoClient $this$findOneAndDeleteAwait, @NotNull final String collection, @NotNull final JsonObject query, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.findOneAndDeleteAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonObject>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonObject>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$findOneAndDeleteAwait.findOneAndDelete(collection, query, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object findOneAndDeleteWithOptionsAwait(@NotNull final MongoClient $this$findOneAndDeleteWithOptionsAwait, @NotNull final String collection, @NotNull final JsonObject query, @NotNull final FindOptions findOptions, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.findOneAndDeleteWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonObject>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonObject>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$findOneAndDeleteWithOptionsAwait.findOneAndDeleteWithOptions(collection, query, findOptions, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object countAwait(@NotNull final MongoClient $this$countAwait, @NotNull final String collection, @NotNull final JsonObject query, @NotNull Continuation<? super Long> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Long>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.countAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Long>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Long>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$countAwait.count(collection, query, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object removeDocumentsAwait(@NotNull final MongoClient $this$removeDocumentsAwait, @NotNull final String collection, @NotNull final JsonObject query, @NotNull Continuation<? super MongoClientDeleteResult> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MongoClientDeleteResult>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.removeDocumentsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<MongoClientDeleteResult>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<MongoClientDeleteResult>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$removeDocumentsAwait.removeDocuments(collection, query, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object removeDocumentsWithOptionsAwait(@NotNull final MongoClient $this$removeDocumentsWithOptionsAwait, @NotNull final String collection, @NotNull final JsonObject query, @Nullable final WriteOption writeOption, @NotNull Continuation<? super MongoClientDeleteResult> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MongoClientDeleteResult>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.removeDocumentsWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<MongoClientDeleteResult>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<MongoClientDeleteResult>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$removeDocumentsWithOptionsAwait.removeDocumentsWithOptions(collection, query, writeOption, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object removeDocumentAwait(@NotNull final MongoClient $this$removeDocumentAwait, @NotNull final String collection, @NotNull final JsonObject query, @NotNull Continuation<? super MongoClientDeleteResult> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MongoClientDeleteResult>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.removeDocumentAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<MongoClientDeleteResult>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<MongoClientDeleteResult>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$removeDocumentAwait.removeDocument(collection, query, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object removeDocumentWithOptionsAwait(@NotNull final MongoClient $this$removeDocumentWithOptionsAwait, @NotNull final String collection, @NotNull final JsonObject query, @Nullable final WriteOption writeOption, @NotNull Continuation<? super MongoClientDeleteResult> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<MongoClientDeleteResult>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.removeDocumentWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<MongoClientDeleteResult>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<MongoClientDeleteResult>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$removeDocumentWithOptionsAwait.removeDocumentWithOptions(collection, query, writeOption, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createCollectionAwait(@NotNull final MongoClient $this$createCollectionAwait, @NotNull final String collectionName, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.createCollectionAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createCollectionAwait.createCollection(collectionName, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.createCollectionAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object getCollectionsAwait(@NotNull final MongoClient $this$getCollectionsAwait, @NotNull Continuation<? super List<String>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends String>>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.getCollectionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends String>>> handler) {
                invoke2((Handler<AsyncResult<List<String>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<String>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getCollectionsAwait.getCollections(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object dropCollectionAwait(@NotNull final MongoClient $this$dropCollectionAwait, @NotNull final String collection, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.dropCollectionAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$dropCollectionAwait.dropCollection(collection, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.dropCollectionAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object createIndexAwait(@NotNull final MongoClient $this$createIndexAwait, @NotNull final String collection, @NotNull final JsonObject key, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.createIndexAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createIndexAwait.createIndex(collection, key, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.createIndexAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object createIndexWithOptionsAwait(@NotNull final MongoClient $this$createIndexWithOptionsAwait, @NotNull final String collection, @NotNull final JsonObject key, @NotNull final IndexOptions options, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.createIndexWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createIndexWithOptionsAwait.createIndexWithOptions(collection, key, options, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.createIndexWithOptionsAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object listIndexesAwait(@NotNull final MongoClient $this$listIndexesAwait, @NotNull final String collection, @NotNull Continuation<? super JsonArray> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonArray>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.listIndexesAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonArray>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonArray>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$listIndexesAwait.listIndexes(collection, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object dropIndexAwait(@NotNull final MongoClient $this$dropIndexAwait, @NotNull final String collection, @NotNull final String indexName, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.dropIndexAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$dropIndexAwait.dropIndex(collection, indexName, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.dropIndexAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object runCommandAwait(@NotNull final MongoClient $this$runCommandAwait, @NotNull final String commandName, @NotNull final JsonObject command, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.runCommandAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonObject>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonObject>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$runCommandAwait.runCommand(commandName, command, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object distinctAwait(@NotNull final MongoClient $this$distinctAwait, @NotNull final String collection, @NotNull final String fieldName, @NotNull final String resultClassname, @NotNull Continuation<? super JsonArray> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonArray>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.distinctAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonArray>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonArray>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$distinctAwait.distinct(collection, fieldName, resultClassname, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object distinctWithQueryAwait(@NotNull final MongoClient $this$distinctWithQueryAwait, @NotNull final String collection, @NotNull final String fieldName, @NotNull final String resultClassname, @NotNull final JsonObject query, @NotNull Continuation<? super JsonArray> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonArray>>, Unit>() { // from class: io.vertx.kotlin.ext.mongo.MongoClientKt.distinctWithQueryAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonArray>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonArray>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$distinctWithQueryAwait.distinctWithQuery(collection, fieldName, resultClassname, query, it);
            }
        }, continuation);
    }
}
