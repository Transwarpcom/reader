package io.vertx.kotlin.core;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeploymentOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��4\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a©\u0001\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0002\u0010\u0015\u001a§\u0001\u0010\u0016\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u0015¨\u0006\u0017"}, d2 = {"DeploymentOptions", "Lio/vertx/core/DeploymentOptions;", "config", "Lio/vertx/core/json/JsonObject;", "extraClasspath", "", "", "ha", "", "instances", "", "isolatedClasses", "isolationGroup", "maxWorkerExecuteTime", "", "maxWorkerExecuteTimeUnit", "Ljava/util/concurrent/TimeUnit;", "multiThreaded", "worker", "workerPoolName", "workerPoolSize", "(Lio/vertx/core/json/JsonObject;Ljava/lang/Iterable;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Iterable;Ljava/lang/String;Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;)Lio/vertx/core/DeploymentOptions;", "deploymentOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/DeploymentOptionsKt.class */
public final class DeploymentOptionsKt {
    @NotNull
    public static /* synthetic */ DeploymentOptions deploymentOptionsOf$default(JsonObject jsonObject, Iterable iterable, Boolean bool, Integer num, Iterable iterable2, String str, Long l, TimeUnit timeUnit, Boolean bool2, Boolean bool3, String str2, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            num = (Integer) null;
        }
        if ((i & 16) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 32) != 0) {
            str = (String) null;
        }
        if ((i & 64) != 0) {
            l = (Long) null;
        }
        if ((i & 128) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 256) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 512) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 1024) != 0) {
            str2 = (String) null;
        }
        if ((i & 2048) != 0) {
            num2 = (Integer) null;
        }
        return deploymentOptionsOf(jsonObject, iterable, bool, num, iterable2, str, l, timeUnit, bool2, bool3, str2, num2);
    }

    @NotNull
    public static final DeploymentOptions deploymentOptionsOf(@Nullable JsonObject config, @Nullable Iterable<String> iterable, @Nullable Boolean ha, @Nullable Integer instances, @Nullable Iterable<String> iterable2, @Nullable String isolationGroup, @Nullable Long maxWorkerExecuteTime, @Nullable TimeUnit maxWorkerExecuteTimeUnit, @Nullable Boolean multiThreaded, @Nullable Boolean worker, @Nullable String workerPoolName, @Nullable Integer workerPoolSize) {
        DeploymentOptions $this$apply = new DeploymentOptions();
        if (config != null) {
            $this$apply.setConfig(config);
        }
        if (iterable != null) {
            $this$apply.setExtraClasspath(CollectionsKt.toList(iterable));
        }
        if (ha != null) {
            $this$apply.setHa(ha.booleanValue());
        }
        if (instances != null) {
            $this$apply.setInstances(instances.intValue());
        }
        if (iterable2 != null) {
            $this$apply.setIsolatedClasses(CollectionsKt.toList(iterable2));
        }
        if (isolationGroup != null) {
            $this$apply.setIsolationGroup(isolationGroup);
        }
        if (maxWorkerExecuteTime != null) {
            $this$apply.setMaxWorkerExecuteTime(maxWorkerExecuteTime.longValue());
        }
        if (maxWorkerExecuteTimeUnit != null) {
            $this$apply.setMaxWorkerExecuteTimeUnit(maxWorkerExecuteTimeUnit);
        }
        if (multiThreaded != null) {
            $this$apply.setMultiThreaded(multiThreaded.booleanValue());
        }
        if (worker != null) {
            $this$apply.setWorker(worker.booleanValue());
        }
        if (workerPoolName != null) {
            $this$apply.setWorkerPoolName(workerPoolName);
        }
        if (workerPoolSize != null) {
            $this$apply.setWorkerPoolSize(workerPoolSize.intValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "deploymentOptionsOf(config, extraClasspath, ha, instances, isolatedClasses, isolationGroup, maxWorkerExecuteTime, maxWorkerExecuteTimeUnit, multiThreaded, worker, workerPoolName, workerPoolSize)"))
    @NotNull
    public static /* synthetic */ DeploymentOptions DeploymentOptions$default(JsonObject jsonObject, Iterable iterable, Boolean bool, Integer num, Iterable iterable2, String str, Long l, TimeUnit timeUnit, Boolean bool2, Boolean bool3, String str2, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            jsonObject = (JsonObject) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            num = (Integer) null;
        }
        if ((i & 16) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 32) != 0) {
            str = (String) null;
        }
        if ((i & 64) != 0) {
            l = (Long) null;
        }
        if ((i & 128) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 256) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 512) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 1024) != 0) {
            str2 = (String) null;
        }
        if ((i & 2048) != 0) {
            num2 = (Integer) null;
        }
        return DeploymentOptions(jsonObject, iterable, bool, num, iterable2, str, l, timeUnit, bool2, bool3, str2, num2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "deploymentOptionsOf(config, extraClasspath, ha, instances, isolatedClasses, isolationGroup, maxWorkerExecuteTime, maxWorkerExecuteTimeUnit, multiThreaded, worker, workerPoolName, workerPoolSize)"))
    @NotNull
    public static final DeploymentOptions DeploymentOptions(@Nullable JsonObject config, @Nullable Iterable<String> iterable, @Nullable Boolean ha, @Nullable Integer instances, @Nullable Iterable<String> iterable2, @Nullable String isolationGroup, @Nullable Long maxWorkerExecuteTime, @Nullable TimeUnit maxWorkerExecuteTimeUnit, @Nullable Boolean multiThreaded, @Nullable Boolean worker, @Nullable String workerPoolName, @Nullable Integer workerPoolSize) {
        DeploymentOptions $this$apply = new DeploymentOptions();
        if (config != null) {
            $this$apply.setConfig(config);
        }
        if (iterable != null) {
            $this$apply.setExtraClasspath(CollectionsKt.toList(iterable));
        }
        if (ha != null) {
            $this$apply.setHa(ha.booleanValue());
        }
        if (instances != null) {
            $this$apply.setInstances(instances.intValue());
        }
        if (iterable2 != null) {
            $this$apply.setIsolatedClasses(CollectionsKt.toList(iterable2));
        }
        if (isolationGroup != null) {
            $this$apply.setIsolationGroup(isolationGroup);
        }
        if (maxWorkerExecuteTime != null) {
            $this$apply.setMaxWorkerExecuteTime(maxWorkerExecuteTime.longValue());
        }
        if (maxWorkerExecuteTimeUnit != null) {
            $this$apply.setMaxWorkerExecuteTimeUnit(maxWorkerExecuteTimeUnit);
        }
        if (multiThreaded != null) {
            $this$apply.setMultiThreaded(multiThreaded.booleanValue());
        }
        if (worker != null) {
            $this$apply.setWorker(worker.booleanValue());
        }
        if (workerPoolName != null) {
            $this$apply.setWorkerPoolName(workerPoolName);
        }
        if (workerPoolSize != null) {
            $this$apply.setWorkerPoolSize(workerPoolSize.intValue());
        }
        return $this$apply;
    }
}
