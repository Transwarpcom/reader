package io.vertx.kotlin.cassandra;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.cassandra.CassandraClientOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CassandraClientOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u001a7\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\b\u001a5\u0010\t\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"CassandraClientOptions", "Lio/vertx/cassandra/CassandraClientOptions;", "contactPoints", "", "", "keyspace", RtspHeaders.Values.PORT, "", "(Ljava/lang/Iterable;Ljava/lang/String;Ljava/lang/Integer;)Lio/vertx/cassandra/CassandraClientOptions;", "cassandraClientOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/cassandra/CassandraClientOptionsKt.class */
public final class CassandraClientOptionsKt {
    @NotNull
    public static /* synthetic */ CassandraClientOptions cassandraClientOptionsOf$default(Iterable iterable, String str, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        return cassandraClientOptionsOf(iterable, str, num);
    }

    @NotNull
    public static final CassandraClientOptions cassandraClientOptionsOf(@Nullable Iterable<String> iterable, @Nullable String keyspace, @Nullable Integer port) {
        CassandraClientOptions $this$apply = new CassandraClientOptions();
        if (iterable != null) {
            $this$apply.setContactPoints(CollectionsKt.toList(iterable));
        }
        if (keyspace != null) {
            $this$apply.setKeyspace(keyspace);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "cassandraClientOptionsOf(contactPoints, keyspace, port)"))
    @NotNull
    public static /* synthetic */ CassandraClientOptions CassandraClientOptions$default(Iterable iterable, String str, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        return CassandraClientOptions(iterable, str, num);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "cassandraClientOptionsOf(contactPoints, keyspace, port)"))
    @NotNull
    public static final CassandraClientOptions CassandraClientOptions(@Nullable Iterable<String> iterable, @Nullable String keyspace, @Nullable Integer port) {
        CassandraClientOptions $this$apply = new CassandraClientOptions();
        if (iterable != null) {
            $this$apply.setContactPoints(CollectionsKt.toList(iterable));
        }
        if (keyspace != null) {
            $this$apply.setKeyspace(keyspace);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        return $this$apply;
    }
}
