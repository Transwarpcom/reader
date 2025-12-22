package io.vertx.core.impl.launcher.commands;

import io.vertx.core.Handler;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import java.io.File;
import java.lang.ProcessBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/commands/Watcher.class */
public class Watcher implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger((Class<?>) Watcher.class);
    private final long gracePeriod;
    private final long scanPeriod;
    private final List<File> roots;
    private final File cwd;
    private final List<String> includes;
    private final Handler<Handler<Void>> deploy;
    private final Handler<Handler<Void>> undeploy;
    private final String cmd;
    private volatile boolean closed;
    private final Map<File, Map<File, FileInfo>> fileMap = new LinkedHashMap();
    private final Set<File> filesToWatch = new HashSet();
    private long lastChange = -1;

    public Watcher(File root, List<String> includes, Handler<Handler<Void>> deploy, Handler<Handler<Void>> undeploy, String onRedeployCommand, long gracePeriod, long scanPeriod) {
        this.gracePeriod = gracePeriod;
        this.includes = sanitizeIncludePatterns(includes);
        this.roots = extractRoots(root, this.includes);
        this.cwd = root;
        LOGGER.info("Watched paths: " + this.roots);
        this.deploy = deploy;
        this.undeploy = undeploy;
        this.cmd = onRedeployCommand;
        this.scanPeriod = scanPeriod;
        addFilesToWatchedList(this.roots);
    }

    static List<File> extractRoots(File root, List<String> includes) {
        return (List) ((Set) includes.stream().map(s -> {
            if (s.startsWith("*")) {
                return root.getAbsolutePath();
            }
            if (s.contains("*")) {
                s = s.substring(0, s.indexOf("*"));
            }
            File file = new File(s);
            if (file.isAbsolute()) {
                return file.getAbsolutePath();
            }
            return new File(root, s).getAbsolutePath();
        }).collect(Collectors.toSet())).stream().map(File::new).collect(Collectors.toList());
    }

    private List<String> sanitizeIncludePatterns(List<String> includes) {
        return (List) includes.stream().map(p -> {
            if (ExecUtils.isWindows()) {
                return p.replace('/', File.separatorChar);
            }
            return p.replace('\\', File.separatorChar);
        }).collect(Collectors.toList());
    }

    private void addFilesToWatchedList(List<File> roots) {
        roots.forEach(this::addFileToWatchedList);
    }

    private void addFileToWatchedList(File file) {
        this.filesToWatch.add(file);
        Map<File, FileInfo> map = new HashMap<>();
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    map.put(child, new FileInfo(child.lastModified(), child.length()));
                    if (child.isDirectory()) {
                        addFileToWatchedList(child);
                    }
                }
            }
        } else {
            map.put(file, new FileInfo(file.lastModified(), file.length()));
        }
        this.fileMap.put(file, map);
    }

    private boolean changesHaveOccurred() {
        boolean changed = false;
        Iterator it = new HashSet(this.filesToWatch).iterator();
        while (it.hasNext()) {
            File toWatch = (File) it.next();
            Map<File, File> newFiles = new LinkedHashMap<>();
            if (toWatch.isDirectory()) {
                File[] files = toWatch.exists() ? toWatch.listFiles() : new File[0];
                if (files == null) {
                    throw new IllegalStateException("Cannot scan the file system to detect file changes");
                }
                for (File file : files) {
                    newFiles.put(file, file);
                }
            } else {
                newFiles.put(toWatch, toWatch);
            }
            Map<File, FileInfo> currentFileMap = this.fileMap.get(toWatch);
            for (Map.Entry<File, FileInfo> currentEntry : new HashMap(currentFileMap).entrySet()) {
                File currFile = currentEntry.getKey();
                FileInfo currInfo = currentEntry.getValue();
                File newFile = newFiles.get(currFile);
                if (newFile == null) {
                    currentFileMap.remove(currFile);
                    if (currentFileMap.isEmpty()) {
                        this.fileMap.remove(toWatch);
                        this.filesToWatch.remove(toWatch);
                    }
                    LOGGER.trace("File: " + currFile + " has been deleted");
                    if (match(currFile)) {
                        changed = true;
                    }
                } else if (newFile.lastModified() != currInfo.lastModified || newFile.length() != currInfo.length) {
                    currentFileMap.put(newFile, new FileInfo(newFile.lastModified(), newFile.length()));
                    LOGGER.trace("File: " + currFile + " has been modified");
                    if (match(currFile)) {
                        changed = true;
                    }
                }
            }
            for (File newFile2 : newFiles.keySet()) {
                if (!currentFileMap.containsKey(newFile2)) {
                    currentFileMap.put(newFile2, new FileInfo(newFile2.lastModified(), newFile2.length()));
                    if (newFile2.isDirectory()) {
                        addFileToWatchedList(newFile2);
                    }
                    LOGGER.trace("File was added: " + newFile2);
                    if (match(newFile2)) {
                        changed = true;
                    }
                }
            }
        }
        long now = System.currentTimeMillis();
        if (changed) {
            this.lastChange = now;
        }
        if (this.lastChange != -1 && now - this.lastChange >= this.gracePeriod) {
            this.lastChange = -1L;
            return true;
        }
        return false;
    }

    protected boolean match(File file) {
        String rel = null;
        String relFromCwd = null;
        for (File root : this.roots) {
            if (file.getAbsolutePath().startsWith(root.getAbsolutePath())) {
                if (file.getAbsolutePath().equals(root.getAbsolutePath())) {
                    rel = file.getAbsolutePath();
                } else {
                    rel = file.getAbsolutePath().substring(root.getAbsolutePath().length() + 1);
                }
            }
        }
        if (rel == null) {
            LOGGER.warn("A change in " + file.getAbsolutePath() + " has been detected, but the file does not belong to a watched roots: " + this.roots);
            return false;
        }
        if (file.getAbsolutePath().startsWith(this.cwd.getAbsolutePath())) {
            relFromCwd = file.getAbsolutePath().substring(this.cwd.getAbsolutePath().length() + 1);
        }
        for (String include : this.includes) {
            if (relFromCwd != null) {
                if (FileSelector.matchPath(include, relFromCwd, !ExecUtils.isWindows())) {
                    return true;
                }
            }
            if (FileSelector.matchPath(include, file.getAbsolutePath(), !ExecUtils.isWindows())) {
                return true;
            }
        }
        return false;
    }

    public Watcher watch() {
        new Thread(this).start();
        LOGGER.info("Starting the vert.x application in redeploy mode");
        this.deploy.handle(null);
        return this;
    }

    public void close() {
        LOGGER.info("Stopping redeployment");
        this.closed = true;
        this.undeploy.handle(null);
    }

    @Override // java.lang.Runnable
    public void run() {
        while (!this.closed) {
            try {
                if (changesHaveOccurred()) {
                    trigger();
                }
                Thread.sleep(this.scanPeriod);
            } catch (Throwable e) {
                LOGGER.error("An error have been encountered while watching resources - leaving the redeploy mode", e);
                close();
                return;
            }
        }
    }

    private void trigger() {
        long begin = System.currentTimeMillis();
        LOGGER.info("Redeploying!");
        this.undeploy.handle(v1 -> {
            executeUserCommand(v2 -> {
                this.deploy.handle(v3 -> {
                    long end = System.currentTimeMillis();
                    LOGGER.info("Redeployment done in " + (end - begin) + " ms.");
                });
            });
        });
    }

    private void executeUserCommand(Handler<Void> onCompletion) {
        if (this.cmd != null) {
            try {
                List<String> command = new ArrayList<>();
                if (ExecUtils.isWindows()) {
                    ExecUtils.addArgument(command, "cmd");
                    ExecUtils.addArgument(command, "/c");
                } else {
                    ExecUtils.addArgument(command, OperatorName.SHADING_FILL);
                    ExecUtils.addArgument(command, "-c");
                }
                command.add(this.cmd);
                Process process = new ProcessBuilder(command).redirectError(ProcessBuilder.Redirect.INHERIT).redirectOutput(ProcessBuilder.Redirect.INHERIT).start();
                int status = process.waitFor();
                LOGGER.info("User command terminated with status " + status);
            } catch (Throwable e) {
                LOGGER.error("Error while executing the on-redeploy command : '" + this.cmd + OperatorName.SHOW_TEXT_LINE, e);
            }
        }
        onCompletion.handle(null);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/commands/Watcher$FileInfo.class */
    private static final class FileInfo {
        long lastModified;
        long length;

        private FileInfo(long lastModified, long length) {
            this.lastModified = lastModified;
            this.length = length;
        }
    }
}
