package io.vertx.kotlin.micrometer;

import io.vertx.ext.web.handler.FormLoginHandler;
import io.vertx.micrometer.VertxInfluxDbOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: VertxInfluxDbOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\u001a\u009d\u0001\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\bH\u0007¢\u0006\u0002\u0010\u0011\u001a\u009b\u0001\u0010\u0012\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0011¨\u0006\u0013"}, d2 = {"VertxInfluxDbOptions", "Lio/vertx/micrometer/VertxInfluxDbOptions;", "batchSize", "", "compressed", "", "connectTimeout", "db", "", "enabled", "numThreads", FormLoginHandler.DEFAULT_PASSWORD_PARAM, "readTimeout", "retentionPolicy", "step", "uri", "userName", "(Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lio/vertx/micrometer/VertxInfluxDbOptions;", "vertxInfluxDbOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/micrometer/VertxInfluxDbOptionsKt.class */
public final class VertxInfluxDbOptionsKt {
    @NotNull
    public static /* synthetic */ VertxInfluxDbOptions vertxInfluxDbOptionsOf$default(Integer num, Boolean bool, Integer num2, String str, Boolean bool2, Integer num3, String str2, Integer num4, String str3, Integer num5, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        if ((i & 16) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 32) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 64) != 0) {
            str2 = (String) null;
        }
        if ((i & 128) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 256) != 0) {
            str3 = (String) null;
        }
        if ((i & 512) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 1024) != 0) {
            str4 = (String) null;
        }
        if ((i & 2048) != 0) {
            str5 = (String) null;
        }
        return vertxInfluxDbOptionsOf(num, bool, num2, str, bool2, num3, str2, num4, str3, num5, str4, str5);
    }

    @NotNull
    public static final VertxInfluxDbOptions vertxInfluxDbOptionsOf(@Nullable Integer batchSize, @Nullable Boolean compressed, @Nullable Integer connectTimeout, @Nullable String db, @Nullable Boolean enabled, @Nullable Integer numThreads, @Nullable String password, @Nullable Integer readTimeout, @Nullable String retentionPolicy, @Nullable Integer step, @Nullable String uri, @Nullable String userName) {
        VertxInfluxDbOptions $this$apply = new VertxInfluxDbOptions();
        if (batchSize != null) {
            $this$apply.setBatchSize(batchSize.intValue());
        }
        if (compressed != null) {
            $this$apply.setCompressed(compressed.booleanValue());
        }
        if (connectTimeout != null) {
            $this$apply.setConnectTimeout(connectTimeout.intValue());
        }
        if (db != null) {
            $this$apply.setDb(db);
        }
        if (enabled != null) {
            $this$apply.setEnabled(enabled.booleanValue());
        }
        if (numThreads != null) {
            $this$apply.setNumThreads(numThreads.intValue());
        }
        if (password != null) {
            $this$apply.setPassword(password);
        }
        if (readTimeout != null) {
            $this$apply.setReadTimeout(readTimeout.intValue());
        }
        if (retentionPolicy != null) {
            $this$apply.setRetentionPolicy(retentionPolicy);
        }
        if (step != null) {
            $this$apply.setStep(step.intValue());
        }
        if (uri != null) {
            $this$apply.setUri(uri);
        }
        if (userName != null) {
            $this$apply.setUserName(userName);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "vertxInfluxDbOptionsOf(batchSize, compressed, connectTimeout, db, enabled, numThreads, password, readTimeout, retentionPolicy, step, uri, userName)"))
    @NotNull
    public static /* synthetic */ VertxInfluxDbOptions VertxInfluxDbOptions$default(Integer num, Boolean bool, Integer num2, String str, Boolean bool2, Integer num3, String str2, Integer num4, String str3, Integer num5, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        if ((i & 16) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 32) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 64) != 0) {
            str2 = (String) null;
        }
        if ((i & 128) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 256) != 0) {
            str3 = (String) null;
        }
        if ((i & 512) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 1024) != 0) {
            str4 = (String) null;
        }
        if ((i & 2048) != 0) {
            str5 = (String) null;
        }
        return VertxInfluxDbOptions(num, bool, num2, str, bool2, num3, str2, num4, str3, num5, str4, str5);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "vertxInfluxDbOptionsOf(batchSize, compressed, connectTimeout, db, enabled, numThreads, password, readTimeout, retentionPolicy, step, uri, userName)"))
    @NotNull
    public static final VertxInfluxDbOptions VertxInfluxDbOptions(@Nullable Integer batchSize, @Nullable Boolean compressed, @Nullable Integer connectTimeout, @Nullable String db, @Nullable Boolean enabled, @Nullable Integer numThreads, @Nullable String password, @Nullable Integer readTimeout, @Nullable String retentionPolicy, @Nullable Integer step, @Nullable String uri, @Nullable String userName) {
        VertxInfluxDbOptions $this$apply = new VertxInfluxDbOptions();
        if (batchSize != null) {
            $this$apply.setBatchSize(batchSize.intValue());
        }
        if (compressed != null) {
            $this$apply.setCompressed(compressed.booleanValue());
        }
        if (connectTimeout != null) {
            $this$apply.setConnectTimeout(connectTimeout.intValue());
        }
        if (db != null) {
            $this$apply.setDb(db);
        }
        if (enabled != null) {
            $this$apply.setEnabled(enabled.booleanValue());
        }
        if (numThreads != null) {
            $this$apply.setNumThreads(numThreads.intValue());
        }
        if (password != null) {
            $this$apply.setPassword(password);
        }
        if (readTimeout != null) {
            $this$apply.setReadTimeout(readTimeout.intValue());
        }
        if (retentionPolicy != null) {
            $this$apply.setRetentionPolicy(retentionPolicy);
        }
        if (step != null) {
            $this$apply.setStep(step.intValue());
        }
        if (uri != null) {
            $this$apply.setUri(uri);
        }
        if (userName != null) {
            $this$apply.setUserName(userName);
        }
        return $this$apply;
    }
}
