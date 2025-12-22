package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Logger;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException: Cannot invoke "java.util.List.forEach(java.util.function.Consumer)" because "blocks" is null
    	at jadx.core.utils.BlockUtils.collectAllInsns(BlockUtils.java:1029)
    	at jadx.core.dex.visitors.ClassModifier.removeBridgeMethod(ClassModifier.java:245)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticMethods(ClassModifier.java:160)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:65)
    */
/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/SequentialExecutor.class */
final class SequentialExecutor implements Executor {
    private static final Logger log = Logger.getLogger(SequentialExecutor.class.getName());
    private final Executor executor;

    @GuardedBy("queue")
    private final Deque<Runnable> queue = new ArrayDeque();

    @GuardedBy("queue")
    private WorkerRunningState workerRunningState = WorkerRunningState.IDLE;

    @GuardedBy("queue")
    private long workerRunCount = 0;
    private final QueueWorker worker = new QueueWorker();

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/SequentialExecutor$WorkerRunningState.class */
    enum WorkerRunningState {
        IDLE,
        QUEUING,
        QUEUED,
        RUNNING
    }

    /*  JADX ERROR: Failed to decode insn: 0x0005: MOVE_MULTI
        java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[8]
        	at java.base/java.lang.System.arraycopy(Native Method)
        	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:52)
        	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:137)
        	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
        	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:50)
        	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    static /* synthetic */ long access$308(com.google.common.util.concurrent.SequentialExecutor r8) {
        /*
            r0 = r8
            r1 = r0
            long r1 = r1.workerRunCount
            // decode failed: arraycopy: source index -1 out of bounds for object array[8]
            r2 = 1
            long r1 = r1 + r2
            r0.workerRunCount = r1
            return r-1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SequentialExecutor.access$308(com.google.common.util.concurrent.SequentialExecutor):long");
    }

    static /* synthetic */ Deque access$100(SequentialExecutor x0) {
        return x0.queue;
    }

    static /* synthetic */ WorkerRunningState access$202(SequentialExecutor x0, WorkerRunningState x1) {
        x0.workerRunningState = x1;
        return x1;
    }

    static /* synthetic */ WorkerRunningState access$200(SequentialExecutor x0) {
        return x0.workerRunningState;
    }

    static /* synthetic */ Logger access$400() {
        return log;
    }

    static {
    }

    SequentialExecutor(Executor executor) {
        this.executor = (Executor) Preconditions.checkNotNull(executor);
    }

    @Override // java.util.concurrent.Executor
    public void execute(final Runnable task) {
        Preconditions.checkNotNull(task);
        synchronized (this.queue) {
            if (this.workerRunningState == WorkerRunningState.RUNNING || this.workerRunningState == WorkerRunningState.QUEUED) {
                this.queue.add(task);
                return;
            }
            long oldRunCount = this.workerRunCount;
            Runnable submittedTask = new Runnable() { // from class: com.google.common.util.concurrent.SequentialExecutor.1
                @Override // java.lang.Runnable
                public void run() {
                    task.run();
                }
            };
            this.queue.add(submittedTask);
            this.workerRunningState = WorkerRunningState.QUEUING;
            try {
                this.executor.execute(this.worker);
                boolean alreadyMarkedQueued = this.workerRunningState != WorkerRunningState.QUEUING;
                if (alreadyMarkedQueued) {
                    return;
                }
                synchronized (this.queue) {
                    if (this.workerRunCount == oldRunCount && this.workerRunningState == WorkerRunningState.QUEUING) {
                        this.workerRunningState = WorkerRunningState.QUEUED;
                    }
                }
            } catch (Error | RuntimeException t) {
                synchronized (this.queue) {
                    boolean removed = (this.workerRunningState == WorkerRunningState.IDLE || this.workerRunningState == WorkerRunningState.QUEUING) && this.queue.removeLastOccurrence(submittedTask);
                    if (!(t instanceof RejectedExecutionException) || removed) {
                        throw t;
                    }
                }
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/SequentialExecutor$QueueWorker.class */
    private final class QueueWorker implements Runnable {
        private QueueWorker() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                workOnQueue();
            } catch (Error e) {
                synchronized (SequentialExecutor.this.queue) {
                    SequentialExecutor.this.workerRunningState = WorkerRunningState.IDLE;
                    throw e;
                }
            }
        }

        /*  JADX ERROR: NullPointerException in pass: AttachTryCatchVisitor
            java.lang.NullPointerException: Cannot invoke "String.charAt(int)" because "obj" is null
            	at jadx.core.utils.Utils.cleanObjectName(Utils.java:41)
            	at jadx.core.dex.instructions.args.ArgType.object(ArgType.java:88)
            	at jadx.core.dex.info.ClassInfo.fromName(ClassInfo.java:42)
            	at jadx.core.dex.visitors.AttachTryCatchVisitor.convertToHandlers(AttachTryCatchVisitor.java:113)
            	at jadx.core.dex.visitors.AttachTryCatchVisitor.initTryCatches(AttachTryCatchVisitor.java:54)
            	at jadx.core.dex.visitors.AttachTryCatchVisitor.visit(AttachTryCatchVisitor.java:42)
            */
        private void workOnQueue() {
            /*
                r5 = this;
                r0 = 0
                r6 = r0
                r0 = 0
                r7 = r0
                r0 = r5     // Catch: java.lang.Throwable -> Lb0
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> Lb0
                java.util.Deque r0 = com.google.common.util.concurrent.SequentialExecutor.access$100(r0)     // Catch: java.lang.Throwable -> Lb0
                r1 = r0     // Catch: java.lang.Throwable -> Lb0
                r9 = r1     // Catch: java.lang.Throwable -> Lb0
                monitor-enter(r0)     // Catch: java.lang.Throwable -> Lb0
                r0 = r7     // Catch: java.lang.Throwable -> Lb0
                if (r0 != 0) goto L43     // Catch: java.lang.Throwable -> Lb0
                r0 = r5     // Catch: java.lang.Throwable -> Lb0
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> Lb0
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r0 = com.google.common.util.concurrent.SequentialExecutor.access$200(r0)     // Catch: java.lang.Throwable -> Lb0
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r1 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch: java.lang.Throwable -> Lb0
                if (r0 != r1) goto L2e     // Catch: java.lang.Throwable -> Lb0
                r0 = r9     // Catch: java.lang.Throwable -> Lb0
                monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb0
                r0 = r6     // Catch: java.lang.Throwable -> Lb0
                if (r0 == 0) goto L2d
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
                return
                r0 = r5
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this
                long r0 = com.google.common.util.concurrent.SequentialExecutor.access$308(r0)
                r0 = r5
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r1 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.RUNNING
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r0 = com.google.common.util.concurrent.SequentialExecutor.access$202(r0, r1)
                r0 = 1
                r7 = r0
                r0 = r5
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this
                java.util.Deque r0 = com.google.common.util.concurrent.SequentialExecutor.access$100(r0)
                java.lang.Object r0 = r0.poll()
                java.lang.Runnable r0 = (java.lang.Runnable) r0
                r8 = r0
                r0 = r8
                if (r0 != 0) goto L70
                r0 = r5
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r1 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.IDLE
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r0 = com.google.common.util.concurrent.SequentialExecutor.access$202(r0, r1)
                r0 = r9
                monitor-exit(r0)
                r0 = r6
                if (r0 == 0) goto L6f
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
                return
                r0 = r9
                monitor-exit(r0)
                goto L7e
                r10 = move-exception
                r0 = r9
                monitor-exit(r0)
                r0 = r10
                throw r0
                r0 = r6
                boolean r1 = java.lang.Thread.interrupted()
                r0 = r0 | r1
                r6 = r0
                r0 = r8
                r0.run()
                goto Lad
                r9 = move-exception
                java.util.logging.Logger r0 = com.google.common.util.concurrent.SequentialExecutor.access$400()
                java.util.logging.Level r1 = java.util.logging.Level.SEVERE
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r3 = r2
                r3.<init>()
                java.lang.String r3 = "Exception while executing runnable "
                java.lang.StringBuilder r2 = r2.append(r3)
                r3 = r8
                java.lang.StringBuilder r2 = r2.append(r3)
                java.lang.String r2 = r2.toString()
                r3 = r9
                r0.log(r1, r2, r3)
                goto L4
            Lb0:
                r11 = move-exception
                r0 = r6
                if (r0 == 0) goto Lbc
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
                r0 = r11
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SequentialExecutor.QueueWorker.workOnQueue():void");
        }
    }
}
