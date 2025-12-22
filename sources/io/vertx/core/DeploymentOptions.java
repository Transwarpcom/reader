package io.vertx.core;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/DeploymentOptions.class */
public class DeploymentOptions {
    public static final boolean DEFAULT_WORKER = false;
    public static final boolean DEFAULT_MULTI_THREADED = false;
    public static final String DEFAULT_ISOLATION_GROUP = null;
    public static final boolean DEFAULT_HA = false;
    public static final int DEFAULT_INSTANCES = 1;
    private JsonObject config;
    private boolean worker;
    private boolean multiThreaded;
    private String isolationGroup;
    private String workerPoolName;
    private int workerPoolSize;
    private long maxWorkerExecuteTime;
    private boolean ha;
    private List<String> extraClasspath;
    private int instances;
    private List<String> isolatedClasses;
    private TimeUnit maxWorkerExecuteTimeUnit;

    public DeploymentOptions() {
        this.worker = false;
        this.config = null;
        this.multiThreaded = false;
        this.isolationGroup = DEFAULT_ISOLATION_GROUP;
        this.ha = false;
        this.instances = 1;
        this.workerPoolName = null;
        this.workerPoolSize = 20;
        this.maxWorkerExecuteTime = VertxOptions.DEFAULT_MAX_WORKER_EXECUTE_TIME;
        this.maxWorkerExecuteTimeUnit = VertxOptions.DEFAULT_MAX_WORKER_EXECUTE_TIME_UNIT;
    }

    public DeploymentOptions(DeploymentOptions other) {
        this.config = other.getConfig() == null ? null : other.getConfig().copy();
        this.worker = other.isWorker();
        this.multiThreaded = other.isMultiThreaded();
        this.isolationGroup = other.getIsolationGroup();
        this.ha = other.isHa();
        this.extraClasspath = other.getExtraClasspath() == null ? null : new ArrayList(other.getExtraClasspath());
        this.instances = other.instances;
        this.isolatedClasses = other.getIsolatedClasses() == null ? null : new ArrayList(other.getIsolatedClasses());
        this.workerPoolName = other.workerPoolName;
        setWorkerPoolSize(other.workerPoolSize);
        setMaxWorkerExecuteTime(other.maxWorkerExecuteTime);
        this.maxWorkerExecuteTimeUnit = other.maxWorkerExecuteTimeUnit;
    }

    public DeploymentOptions(JsonObject json) {
        this();
        DeploymentOptionsConverter.fromJson(json, this);
    }

    public void fromJson(JsonObject json) {
        this.config = json.getJsonObject("config");
        this.worker = json.getBoolean("worker", false).booleanValue();
        this.multiThreaded = json.getBoolean("multiThreaded", false).booleanValue();
        this.isolationGroup = json.getString("isolationGroup", DEFAULT_ISOLATION_GROUP);
        this.ha = json.getBoolean("ha", false).booleanValue();
        JsonArray arr = json.getJsonArray("extraClasspath", null);
        if (arr != null) {
            this.extraClasspath = arr.getList();
        }
        this.instances = json.getInteger("instances", 1).intValue();
        JsonArray arrIsolated = json.getJsonArray("isolatedClasses", null);
        if (arrIsolated != null) {
            this.isolatedClasses = arrIsolated.getList();
        }
    }

    public JsonObject getConfig() {
        return this.config;
    }

    public DeploymentOptions setConfig(JsonObject config) {
        this.config = config;
        return this;
    }

    public boolean isWorker() {
        return this.worker;
    }

    public DeploymentOptions setWorker(boolean worker) {
        this.worker = worker;
        return this;
    }

    public boolean isMultiThreaded() {
        return this.multiThreaded;
    }

    @Deprecated
    public DeploymentOptions setMultiThreaded(boolean multiThreaded) {
        this.multiThreaded = multiThreaded;
        return this;
    }

    public String getIsolationGroup() {
        return this.isolationGroup;
    }

    public DeploymentOptions setIsolationGroup(String isolationGroup) {
        this.isolationGroup = isolationGroup;
        return this;
    }

    public boolean isHa() {
        return this.ha;
    }

    public DeploymentOptions setHa(boolean ha) {
        this.ha = ha;
        return this;
    }

    public List<String> getExtraClasspath() {
        return this.extraClasspath;
    }

    public DeploymentOptions setExtraClasspath(List<String> extraClasspath) {
        this.extraClasspath = extraClasspath;
        return this;
    }

    public int getInstances() {
        return this.instances;
    }

    public DeploymentOptions setInstances(int instances) {
        this.instances = instances;
        return this;
    }

    public List<String> getIsolatedClasses() {
        return this.isolatedClasses;
    }

    public DeploymentOptions setIsolatedClasses(List<String> isolatedClasses) {
        this.isolatedClasses = isolatedClasses;
        return this;
    }

    public String getWorkerPoolName() {
        return this.workerPoolName;
    }

    public DeploymentOptions setWorkerPoolName(String workerPoolName) {
        this.workerPoolName = workerPoolName;
        return this;
    }

    public int getWorkerPoolSize() {
        return this.workerPoolSize;
    }

    public DeploymentOptions setWorkerPoolSize(int workerPoolSize) {
        if (workerPoolSize < 1) {
            throw new IllegalArgumentException("workerPoolSize must be > 0");
        }
        this.workerPoolSize = workerPoolSize;
        return this;
    }

    public long getMaxWorkerExecuteTime() {
        return this.maxWorkerExecuteTime;
    }

    public DeploymentOptions setMaxWorkerExecuteTime(long maxWorkerExecuteTime) {
        if (maxWorkerExecuteTime < 1) {
            throw new IllegalArgumentException("maxWorkerExecuteTime must be > 0");
        }
        this.maxWorkerExecuteTime = maxWorkerExecuteTime;
        return this;
    }

    public TimeUnit getMaxWorkerExecuteTimeUnit() {
        return this.maxWorkerExecuteTimeUnit;
    }

    public DeploymentOptions setMaxWorkerExecuteTimeUnit(TimeUnit maxWorkerExecuteTimeUnit) {
        this.maxWorkerExecuteTimeUnit = maxWorkerExecuteTimeUnit;
        return this;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        DeploymentOptionsConverter.toJson(this, json);
        return json;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeploymentOptions that = (DeploymentOptions) o;
        if (this.worker != that.worker || this.multiThreaded != that.multiThreaded || this.ha != that.ha || this.instances != that.instances) {
            return false;
        }
        if (this.config != null) {
            if (!this.config.equals(that.config)) {
                return false;
            }
        } else if (that.config != null) {
            return false;
        }
        if (this.isolationGroup != null) {
            if (!this.isolationGroup.equals(that.isolationGroup)) {
                return false;
            }
        } else if (that.isolationGroup != null) {
            return false;
        }
        if (this.extraClasspath != null) {
            if (!this.extraClasspath.equals(that.extraClasspath)) {
                return false;
            }
        } else if (that.extraClasspath != null) {
            return false;
        }
        return this.isolatedClasses == null ? that.isolatedClasses == null : this.isolatedClasses.equals(that.isolatedClasses);
    }

    public int hashCode() {
        int result = this.config != null ? this.config.hashCode() : 0;
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + (this.worker ? 1 : 0))) + (this.multiThreaded ? 1 : 0))) + (this.isolationGroup != null ? this.isolationGroup.hashCode() : 0))) + (this.ha ? 1 : 0))) + (this.extraClasspath != null ? this.extraClasspath.hashCode() : 0))) + this.instances)) + (this.isolatedClasses != null ? this.isolatedClasses.hashCode() : 0))) + (this.workerPoolName != null ? this.workerPoolName.hashCode() : 0))) + this.workerPoolSize)) + Long.hashCode(this.maxWorkerExecuteTime);
    }
}
