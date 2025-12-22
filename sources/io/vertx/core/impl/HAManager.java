package io.vertx.core.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.VertxException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.core.spi.cluster.NodeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/HAManager.class */
public class HAManager {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) HAManager.class);
    private static final long QUORUM_CHECK_PERIOD = 1000;
    private final VertxInternal vertx;
    private final DeploymentManager deploymentManager;
    private final ClusterManager clusterManager;
    private final int quorumSize;
    private final String group;
    private final JsonObject haInfo;
    private final Map<String, String> clusterMap;
    private final String nodeID;
    private final Queue<Runnable> toDeployOnQuorum = new ConcurrentLinkedQueue();
    private final boolean enabled;
    private long quorumTimerID;
    private volatile boolean attainedQuorum;
    private volatile FailoverCompleteHandler failoverCompleteHandler;
    private volatile boolean failDuringFailover;
    private volatile boolean stopped;
    private volatile boolean killed;
    private Consumer<Set<String>> clusterViewChangedHandler;

    public HAManager(VertxInternal vertx, DeploymentManager deploymentManager, ClusterManager clusterManager, Map<String, String> clusterMap, int quorumSize, String group, boolean enabled) {
        this.vertx = vertx;
        this.deploymentManager = deploymentManager;
        this.clusterManager = clusterManager;
        this.clusterMap = clusterMap;
        this.quorumSize = enabled ? quorumSize : 0;
        this.group = enabled ? group : "__DISABLED__";
        this.enabled = enabled;
        this.haInfo = new JsonObject().put("verticles", new JsonArray()).put("group", this.group);
        this.nodeID = clusterManager.getNodeID();
    }

    void init() {
        synchronized (this.haInfo) {
            this.clusterMap.put(this.nodeID, this.haInfo.encode());
        }
        this.clusterManager.nodeListener(new NodeListener() { // from class: io.vertx.core.impl.HAManager.1
            @Override // io.vertx.core.spi.cluster.NodeListener
            public void nodeAdded(String nodeID) throws InterruptedException {
                HAManager.this.nodeAdded(nodeID);
            }

            @Override // io.vertx.core.spi.cluster.NodeListener
            public void nodeLeft(String leftNodeID) throws InterruptedException {
                HAManager.this.nodeLeft(leftNodeID);
            }
        });
        this.quorumTimerID = this.vertx.setPeriodic(1000L, tid -> {
            checkHADeployments();
        });
        synchronized (this) {
            checkQuorum();
        }
    }

    public void removeFromHA(String depID) {
        Deployment dep = this.deploymentManager.getDeployment(depID);
        if (dep == null || !dep.deploymentOptions().isHa()) {
            return;
        }
        synchronized (this.haInfo) {
            JsonArray haMods = this.haInfo.getJsonArray("verticles");
            Iterator<Object> iter = haMods.iterator();
            while (iter.hasNext()) {
                Object obj = iter.next();
                JsonObject mod = (JsonObject) obj;
                if (mod.getString("dep_id").equals(depID)) {
                    iter.remove();
                }
            }
            this.clusterMap.put(this.nodeID, this.haInfo.encode());
        }
    }

    public void addDataToAHAInfo(String key, JsonObject value) {
        synchronized (this.haInfo) {
            this.haInfo.put(key, value);
            this.clusterMap.put(this.nodeID, this.haInfo.encode());
        }
    }

    public void deployVerticle(String verticleName, DeploymentOptions deploymentOptions, Handler<AsyncResult<String>> doneHandler) {
        if (this.attainedQuorum) {
            doDeployVerticle(verticleName, deploymentOptions, doneHandler);
        } else {
            log.info("Quorum not attained. Deployment of verticle will be delayed until there's a quorum.");
            addToHADeployList(verticleName, deploymentOptions, doneHandler);
        }
    }

    public void stop() {
        if (!this.stopped) {
            if (this.clusterManager.isActive()) {
                this.clusterMap.remove(this.nodeID);
            }
            this.vertx.cancelTimer(this.quorumTimerID);
            this.stopped = true;
        }
    }

    public void simulateKill() {
        if (!this.stopped) {
            this.killed = true;
            CountDownLatch latch = new CountDownLatch(1);
            this.clusterManager.leave(ar -> {
                if (ar.failed()) {
                    log.error("Failed to leave cluster", ar.cause());
                }
                latch.countDown();
            });
            this.vertx.cancelTimer(this.quorumTimerID);
            boolean interrupted = false;
            try {
                long remainingNanos = TimeUnit.MINUTES.toNanos(1L);
                long end = System.nanoTime() + remainingNanos;
                while (true) {
                    try {
                        latch.await(remainingNanos, TimeUnit.NANOSECONDS);
                        break;
                    } catch (InterruptedException e) {
                        interrupted = true;
                        remainingNanos = end - System.nanoTime();
                    }
                }
                this.stopped = true;
            } finally {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void setFailoverCompleteHandler(FailoverCompleteHandler failoverCompleteHandler) {
        this.failoverCompleteHandler = failoverCompleteHandler;
    }

    public void setClusterViewChangedHandler(Consumer<Set<String>> handler) {
        this.clusterViewChangedHandler = handler;
    }

    public boolean isKilled() {
        return this.killed;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void failDuringFailover(boolean fail) {
        this.failDuringFailover = fail;
    }

    private void doDeployVerticle(String verticleName, DeploymentOptions deploymentOptions, Handler<AsyncResult<String>> doneHandler) {
        Handler<AsyncResult<String>> wrappedHandler = ar1 -> {
            this.vertx.executeBlocking(fut -> {
                if (ar1.succeeded()) {
                    String deploymentID = (String) ar1.result();
                    addToHA(deploymentID, verticleName, deploymentOptions);
                    fut.complete(deploymentID);
                    return;
                }
                fut.fail(ar1.cause());
            }, false, ar2 -> {
                if (doneHandler != null) {
                    doneHandler.handle(ar2);
                } else if (ar2.failed()) {
                    log.error("Failed to deploy verticle", ar2.cause());
                }
            });
        };
        this.deploymentManager.deployVerticle(verticleName, deploymentOptions, wrappedHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void nodeAdded(String nodeID) throws InterruptedException {
        addHaInfoIfLost();
        checkQuorumWhenAdded(nodeID, System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void nodeLeft(String leftNodeID) throws InterruptedException {
        addHaInfoIfLost();
        checkQuorum();
        if (this.attainedQuorum) {
            checkSubs(leftNodeID);
            String sclusterInfo = this.clusterMap.get(leftNodeID);
            if (sclusterInfo != null) {
                JsonObject clusterInfo = new JsonObject(sclusterInfo);
                checkFailover(leftNodeID, clusterInfo);
            }
            List<String> nodes = this.clusterManager.getNodes();
            for (Map.Entry<String, String> entry : this.clusterMap.entrySet()) {
                if (!leftNodeID.equals(entry.getKey()) && !nodes.contains(entry.getKey())) {
                    JsonObject haInfo = new JsonObject(entry.getValue());
                    checkFailover(entry.getKey(), haInfo);
                }
            }
        }
    }

    private void addHaInfoIfLost() {
        if (this.clusterManager.getNodes().contains(this.nodeID) && !this.clusterMap.containsKey(this.nodeID)) {
            synchronized (this.haInfo) {
                this.clusterMap.put(this.nodeID, this.haInfo.encode());
            }
        }
    }

    private synchronized void checkQuorumWhenAdded(String nodeID, long start) throws InterruptedException {
        if (this.clusterMap.containsKey(nodeID)) {
            checkQuorum();
            if (this.attainedQuorum) {
                checkSubs(nodeID);
                return;
            }
            return;
        }
        this.vertx.setTimer(200L, tid -> {
            this.vertx.executeBlockingInternal(fut -> {
                if (System.currentTimeMillis() - start > 10000) {
                    log.warn("Timed out waiting for group information to appear");
                } else if (!this.stopped) {
                    ContextInternal context = this.vertx.getContext();
                    try {
                        ContextImpl.setContext(null);
                        checkQuorumWhenAdded(nodeID, start);
                        ContextImpl.setContext((ContextImpl) context);
                    } catch (Throwable th) {
                        ContextImpl.setContext((ContextImpl) context);
                        throw th;
                    }
                }
                fut.complete();
            }, null);
        });
    }

    private void checkQuorum() {
        if (this.quorumSize == 0) {
            this.attainedQuorum = true;
            return;
        }
        List<String> nodes = this.clusterManager.getNodes();
        int count = 0;
        for (String node : nodes) {
            String json = this.clusterMap.get(node);
            if (json != null) {
                JsonObject clusterInfo = new JsonObject(json);
                String group = clusterInfo.getString("group");
                if (group.equals(this.group)) {
                    count++;
                }
            }
        }
        boolean attained = count >= this.quorumSize;
        if (!this.attainedQuorum && attained) {
            log.info("A quorum has been obtained. Any deploymentIDs waiting on a quorum will now be deployed");
            this.attainedQuorum = true;
        } else if (this.attainedQuorum && !attained) {
            log.info("There is no longer a quorum. Any HA deploymentIDs will be undeployed until a quorum is re-attained");
            this.attainedQuorum = false;
        }
    }

    private void addToHA(String deploymentID, String verticleName, DeploymentOptions deploymentOptions) {
        synchronized (this.haInfo) {
            JsonObject verticleConf = new JsonObject().put("dep_id", deploymentID);
            verticleConf.put("verticle_name", verticleName);
            verticleConf.put("options", deploymentOptions.toJson());
            JsonArray haMods = this.haInfo.getJsonArray("verticles");
            haMods.add(verticleConf);
            String encoded = this.haInfo.encode();
            this.clusterMap.put(this.nodeID, encoded);
        }
    }

    private void addToHADeployList(String verticleName, DeploymentOptions deploymentOptions, Handler<AsyncResult<String>> doneHandler) {
        this.toDeployOnQuorum.add(() -> {
            ContextInternal ctx = this.vertx.getContext();
            try {
                ContextImpl.setContext(null);
                deployVerticle(verticleName, deploymentOptions, doneHandler);
                ContextImpl.setContext((ContextImpl) ctx);
            } catch (Throwable th) {
                ContextImpl.setContext((ContextImpl) ctx);
                throw th;
            }
        });
    }

    private void checkHADeployments() {
        try {
            if (this.attainedQuorum) {
                deployHADeployments();
            } else {
                undeployHADeployments();
            }
        } catch (Throwable t) {
            log.error("Failed when checking HA deploymentIDs", t);
        }
    }

    private void undeployHADeployments() {
        for (String deploymentID : this.deploymentManager.deployments()) {
            Deployment dep = this.deploymentManager.getDeployment(deploymentID);
            if (dep != null && dep.deploymentOptions().isHa()) {
                ContextInternal ctx = this.vertx.getContext();
                try {
                    ContextImpl.setContext(null);
                    this.deploymentManager.undeployVerticle(deploymentID, result -> {
                        if (result.succeeded()) {
                            log.info("Successfully undeployed HA deployment " + deploymentID + "-" + dep.verticleIdentifier() + " as there is no quorum");
                            addToHADeployList(dep.verticleIdentifier(), dep.deploymentOptions(), result1 -> {
                                if (result1.succeeded()) {
                                    log.info("Successfully redeployed verticle " + dep.verticleIdentifier() + " after quorum was re-attained");
                                } else {
                                    log.error("Failed to redeploy verticle " + dep.verticleIdentifier() + " after quorum was re-attained", result1.cause());
                                }
                            });
                        } else {
                            log.error("Failed to undeploy deployment on lost quorum", result.cause());
                        }
                    });
                    ContextImpl.setContext((ContextImpl) ctx);
                } catch (Throwable th) {
                    ContextImpl.setContext((ContextImpl) ctx);
                    throw th;
                }
            }
        }
    }

    private void deployHADeployments() {
        int size = this.toDeployOnQuorum.size();
        if (size != 0) {
            log.info("There are " + size + " HA deploymentIDs waiting on a quorum. These will now be deployed");
            while (true) {
                Runnable task = this.toDeployOnQuorum.poll();
                if (task != null) {
                    try {
                        task.run();
                    } catch (Throwable t) {
                        log.error("Failed to run redeployment task", t);
                    }
                } else {
                    return;
                }
            }
        }
    }

    private void checkFailover(String failedNodeID, JsonObject theHAInfo) throws InterruptedException {
        try {
            JsonArray deployments = theHAInfo.getJsonArray("verticles");
            String group = theHAInfo.getString("group");
            String chosen = chooseHashedNode(group, failedNodeID.hashCode());
            if (chosen != null && chosen.equals(this.nodeID)) {
                if (deployments != null && deployments.size() != 0) {
                    log.info("node" + this.nodeID + " says: Node " + failedNodeID + " has failed. This node will deploy " + deployments.size() + " deploymentIDs from that node.");
                    Iterator<Object> it = deployments.iterator();
                    while (it.hasNext()) {
                        Object obj = it.next();
                        JsonObject app = (JsonObject) obj;
                        processFailover(app);
                    }
                }
                this.clusterMap.remove(failedNodeID);
                runOnContextAndWait(() -> {
                    if (this.failoverCompleteHandler != null) {
                        this.failoverCompleteHandler.handle(failedNodeID, theHAInfo, true);
                    }
                });
            }
        } catch (Throwable t) {
            log.error("Failed to handle failover", t);
            runOnContextAndWait(() -> {
                if (this.failoverCompleteHandler != null) {
                    this.failoverCompleteHandler.handle(failedNodeID, theHAInfo, false);
                }
            });
        }
    }

    private void checkSubs(String failedNodeID) throws InterruptedException {
        String chosen;
        if (this.clusterViewChangedHandler != null && (chosen = chooseHashedNode(null, failedNodeID.hashCode())) != null && chosen.equals(this.nodeID)) {
            runOnContextAndWait(() -> {
                this.clusterViewChangedHandler.accept(new HashSet(this.clusterManager.getNodes()));
            });
        }
    }

    private void runOnContextAndWait(Runnable runnable) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        this.vertx.runOnContext(v -> {
            try {
                runnable.run();
            } finally {
                latch.countDown();
            }
        });
        try {
            latch.await(30L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
    }

    private void processFailover(JsonObject failedVerticle) {
        if (this.failDuringFailover) {
            throw new VertxException("Oops!");
        }
        String verticleName = failedVerticle.getString("verticle_name");
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<Throwable> err = new AtomicReference<>();
        ContextInternal ctx = this.vertx.getContext();
        if (ctx != null) {
            ContextImpl.setContext(null);
        }
        JsonObject options = failedVerticle.getJsonObject("options");
        try {
            doDeployVerticle(verticleName, new DeploymentOptions(options), result -> {
                if (result.succeeded()) {
                    log.info("Successfully redeployed verticle " + verticleName + " after failover");
                } else {
                    log.error("Failed to redeploy verticle after failover", result.cause());
                    err.set(result.cause());
                }
                latch.countDown();
                Throwable t = (Throwable) err.get();
                if (t != null) {
                    throw new VertxException(t);
                }
            });
            if (ctx != null) {
                ContextImpl.setContext((ContextImpl) ctx);
            }
            try {
                if (!latch.await(120L, TimeUnit.SECONDS)) {
                    throw new VertxException("Timed out waiting for redeploy on failover");
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        } catch (Throwable th) {
            if (ctx != null) {
                ContextImpl.setContext((ContextImpl) ctx);
            }
            throw th;
        }
    }

    private String chooseHashedNode(String group, int hashCode) {
        List<String> nodes = this.clusterManager.getNodes();
        ArrayList<String> matchingMembers = new ArrayList<>();
        for (String node : nodes) {
            String sclusterInfo = this.clusterMap.get(node);
            if (sclusterInfo != null) {
                JsonObject clusterInfo = new JsonObject(sclusterInfo);
                String memberGroup = clusterInfo.getString("group");
                if (group == null || group.equals(memberGroup)) {
                    matchingMembers.add(node);
                }
            }
        }
        if (!matchingMembers.isEmpty()) {
            long absHash = hashCode + 2147483647L;
            long lpos = absHash % matchingMembers.size();
            return matchingMembers.get((int) lpos);
        }
        return null;
    }
}
