package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.Check;
import io.vertx.ext.consul.CheckStatus;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Check.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001ah\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007\u001af\u0010\f\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¨\u0006\r"}, d2 = {PDAnnotationText.NAME_CHECK, "Lio/vertx/ext/consul/Check;", "id", "", "name", "nodeName", "notes", "output", "serviceId", "serviceName", "status", "Lio/vertx/ext/consul/CheckStatus;", "checkOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/CheckKt.class */
public final class CheckKt {
    @NotNull
    public static /* synthetic */ Check checkOf$default(String str, String str2, String str3, String str4, String str5, String str6, String str7, CheckStatus checkStatus, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            str3 = (String) null;
        }
        if ((i & 8) != 0) {
            str4 = (String) null;
        }
        if ((i & 16) != 0) {
            str5 = (String) null;
        }
        if ((i & 32) != 0) {
            str6 = (String) null;
        }
        if ((i & 64) != 0) {
            str7 = (String) null;
        }
        if ((i & 128) != 0) {
            checkStatus = (CheckStatus) null;
        }
        return checkOf(str, str2, str3, str4, str5, str6, str7, checkStatus);
    }

    @NotNull
    public static final Check checkOf(@Nullable String id, @Nullable String name, @Nullable String nodeName, @Nullable String notes, @Nullable String output, @Nullable String serviceId, @Nullable String serviceName, @Nullable CheckStatus status) {
        Check $this$apply = new Check();
        if (id != null) {
            $this$apply.setId(id);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (nodeName != null) {
            $this$apply.setNodeName(nodeName);
        }
        if (notes != null) {
            $this$apply.setNotes(notes);
        }
        if (output != null) {
            $this$apply.setOutput(output);
        }
        if (serviceId != null) {
            $this$apply.setServiceId(serviceId);
        }
        if (serviceName != null) {
            $this$apply.setServiceName(serviceName);
        }
        if (status != null) {
            $this$apply.setStatus(status);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "checkOf(id, name, nodeName, notes, output, serviceId, serviceName, status)"))
    @NotNull
    public static /* synthetic */ Check Check$default(String str, String str2, String str3, String str4, String str5, String str6, String str7, CheckStatus checkStatus, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            str3 = (String) null;
        }
        if ((i & 8) != 0) {
            str4 = (String) null;
        }
        if ((i & 16) != 0) {
            str5 = (String) null;
        }
        if ((i & 32) != 0) {
            str6 = (String) null;
        }
        if ((i & 64) != 0) {
            str7 = (String) null;
        }
        if ((i & 128) != 0) {
            checkStatus = (CheckStatus) null;
        }
        return Check(str, str2, str3, str4, str5, str6, str7, checkStatus);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "checkOf(id, name, nodeName, notes, output, serviceId, serviceName, status)"))
    @NotNull
    public static final Check Check(@Nullable String id, @Nullable String name, @Nullable String nodeName, @Nullable String notes, @Nullable String output, @Nullable String serviceId, @Nullable String serviceName, @Nullable CheckStatus status) {
        Check $this$apply = new Check();
        if (id != null) {
            $this$apply.setId(id);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (nodeName != null) {
            $this$apply.setNodeName(nodeName);
        }
        if (notes != null) {
            $this$apply.setNotes(notes);
        }
        if (output != null) {
            $this$apply.setOutput(output);
        }
        if (serviceId != null) {
            $this$apply.setServiceId(serviceId);
        }
        if (serviceName != null) {
            $this$apply.setServiceName(serviceName);
        }
        if (status != null) {
            $this$apply.setStatus(status);
        }
        return $this$apply;
    }
}
