package io.vertx.kotlin.servicediscovery;

import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.Status;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Record.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"�� \n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aP\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006H\u0007\u001aN\u0010\u000b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006¨\u0006\f"}, d2 = {"Record", "Lio/vertx/servicediscovery/Record;", "location", "Lio/vertx/core/json/JsonObject;", PackageDocumentBase.OPFTags.metadata, "name", "", "registration", "status", "Lio/vertx/servicediscovery/Status;", "type", "recordOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/RecordKt.class */
public final class RecordKt {
    @NotNull
    public static /* synthetic */ Record recordOf$default(JsonObject jsonObject, JsonObject jsonObject2, String str, String str2, Status status, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 2) != 0) {
            jsonObject2 = (JsonObject) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        if ((i & 16) != 0) {
            status = (Status) null;
        }
        if ((i & 32) != 0) {
            str3 = (String) null;
        }
        return recordOf(jsonObject, jsonObject2, str, str2, status, str3);
    }

    @NotNull
    public static final Record recordOf(@Nullable JsonObject location, @Nullable JsonObject metadata, @Nullable String name, @Nullable String registration, @Nullable Status status, @Nullable String type) {
        Record $this$apply = new Record();
        if (location != null) {
            $this$apply.setLocation(location);
        }
        if (metadata != null) {
            $this$apply.setMetadata(metadata);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (registration != null) {
            $this$apply.setRegistration(registration);
        }
        if (status != null) {
            $this$apply.setStatus(status);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "recordOf(location, metadata, name, registration, status, type)"))
    @NotNull
    public static /* synthetic */ Record Record$default(JsonObject jsonObject, JsonObject jsonObject2, String str, String str2, Status status, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 2) != 0) {
            jsonObject2 = (JsonObject) null;
        }
        if ((i & 4) != 0) {
            str = (String) null;
        }
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        if ((i & 16) != 0) {
            status = (Status) null;
        }
        if ((i & 32) != 0) {
            str3 = (String) null;
        }
        return Record(jsonObject, jsonObject2, str, str2, status, str3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "recordOf(location, metadata, name, registration, status, type)"))
    @NotNull
    public static final Record Record(@Nullable JsonObject location, @Nullable JsonObject metadata, @Nullable String name, @Nullable String registration, @Nullable Status status, @Nullable String type) {
        Record $this$apply = new Record();
        if (location != null) {
            $this$apply.setLocation(location);
        }
        if (metadata != null) {
            $this$apply.setMetadata(metadata);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (registration != null) {
            $this$apply.setRegistration(registration);
        }
        if (status != null) {
            $this$apply.setStatus(status);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        return $this$apply;
    }
}
