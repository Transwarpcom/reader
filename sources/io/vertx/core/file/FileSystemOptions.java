package io.vertx.core.file;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.file.impl.FileResolver;
import io.vertx.core.json.JsonObject;
import java.io.File;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/file/FileSystemOptions.class */
public class FileSystemOptions {
    public static final boolean DEFAULT_FILE_CACHING_ENABLED;
    public static final boolean DEFAULT_CLASS_PATH_RESOLVING_ENABLED;
    private static final String TMPDIR;
    private static final String DEFAULT_CACHE_DIR_BASE = "vertx-cache";
    public static final String DEFAULT_FILE_CACHING_DIR;
    private boolean classPathResolvingEnabled;
    private boolean fileCachingEnabled;
    private String fileCacheDir;

    static {
        DEFAULT_FILE_CACHING_ENABLED = !Boolean.getBoolean(FileResolver.DISABLE_FILE_CACHING_PROP_NAME);
        DEFAULT_CLASS_PATH_RESOLVING_ENABLED = !Boolean.getBoolean(FileResolver.DISABLE_CP_RESOLVING_PROP_NAME);
        TMPDIR = System.getProperty("java.io.tmpdir", ".");
        DEFAULT_FILE_CACHING_DIR = System.getProperty(FileResolver.CACHE_DIR_BASE_PROP_NAME, TMPDIR + File.separator + DEFAULT_CACHE_DIR_BASE);
    }

    public FileSystemOptions() {
        this.classPathResolvingEnabled = DEFAULT_CLASS_PATH_RESOLVING_ENABLED;
        this.fileCachingEnabled = DEFAULT_FILE_CACHING_ENABLED;
        this.fileCacheDir = DEFAULT_FILE_CACHING_DIR;
    }

    public FileSystemOptions(FileSystemOptions other) {
        this.classPathResolvingEnabled = DEFAULT_CLASS_PATH_RESOLVING_ENABLED;
        this.fileCachingEnabled = DEFAULT_FILE_CACHING_ENABLED;
        this.fileCacheDir = DEFAULT_FILE_CACHING_DIR;
        this.classPathResolvingEnabled = other.isClassPathResolvingEnabled();
        this.fileCachingEnabled = other.isFileCachingEnabled();
        this.fileCacheDir = other.getFileCacheDir();
    }

    public FileSystemOptions(JsonObject json) {
        this();
        FileSystemOptionsConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        FileSystemOptionsConverter.toJson(this, json);
        return json;
    }

    public boolean isClassPathResolvingEnabled() {
        return this.classPathResolvingEnabled;
    }

    public FileSystemOptions setClassPathResolvingEnabled(boolean classPathResolvingEnabled) {
        this.classPathResolvingEnabled = classPathResolvingEnabled;
        return this;
    }

    public boolean isFileCachingEnabled() {
        return this.fileCachingEnabled;
    }

    public FileSystemOptions setFileCachingEnabled(boolean fileCachingEnabled) {
        this.fileCachingEnabled = fileCachingEnabled;
        return this;
    }

    public String getFileCacheDir() {
        return this.fileCacheDir;
    }

    public FileSystemOptions setFileCacheDir(String fileCacheDir) {
        this.fileCacheDir = fileCacheDir;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileSystemOptions that = (FileSystemOptions) o;
        return this.classPathResolvingEnabled == that.isClassPathResolvingEnabled() && this.fileCachingEnabled == that.isFileCachingEnabled();
    }

    public int hashCode() {
        int result = this.classPathResolvingEnabled ? 1 : 0;
        return 31 * (result + (this.fileCachingEnabled ? 1 : 0));
    }

    public String toString() {
        return "FileSystemOptions{classPathResolvingEnabled=" + this.classPathResolvingEnabled + ", fileCachingEnabled=" + this.fileCachingEnabled + ", fileCacheDir=" + this.fileCacheDir + '}';
    }
}
