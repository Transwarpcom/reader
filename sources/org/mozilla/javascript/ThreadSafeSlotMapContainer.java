package org.mozilla.javascript;

import java.util.Iterator;
import java.util.concurrent.locks.StampedLock;
import org.mozilla.javascript.ScriptableObject;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ThreadSafeSlotMapContainer.class */
class ThreadSafeSlotMapContainer extends SlotMapContainer {
    private final StampedLock lock;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !ThreadSafeSlotMapContainer.class.desiredAssertionStatus();
    }

    ThreadSafeSlotMapContainer(int initialSize) {
        super(initialSize);
        this.lock = new StampedLock();
    }

    @Override // org.mozilla.javascript.SlotMapContainer, org.mozilla.javascript.SlotMap
    public int size() {
        long stamp = this.lock.tryOptimisticRead();
        int s = this.map.size();
        if (this.lock.validate(stamp)) {
            return s;
        }
        long stamp2 = this.lock.readLock();
        try {
            int size = this.map.size();
            this.lock.unlockRead(stamp2);
            return size;
        } catch (Throwable th) {
            this.lock.unlockRead(stamp2);
            throw th;
        }
    }

    @Override // org.mozilla.javascript.SlotMapContainer
    public int dirtySize() {
        if ($assertionsDisabled || this.lock.isReadLocked()) {
            return this.map.size();
        }
        throw new AssertionError();
    }

    @Override // org.mozilla.javascript.SlotMapContainer, org.mozilla.javascript.SlotMap
    public boolean isEmpty() {
        long stamp = this.lock.tryOptimisticRead();
        boolean e = this.map.isEmpty();
        if (this.lock.validate(stamp)) {
            return e;
        }
        long stamp2 = this.lock.readLock();
        try {
            boolean zIsEmpty = this.map.isEmpty();
            this.lock.unlockRead(stamp2);
            return zIsEmpty;
        } catch (Throwable th) {
            this.lock.unlockRead(stamp2);
            throw th;
        }
    }

    @Override // org.mozilla.javascript.SlotMapContainer, org.mozilla.javascript.SlotMap
    public ScriptableObject.Slot get(Object key, int index, ScriptableObject.SlotAccess accessType) {
        long stamp = this.lock.writeLock();
        try {
            if (accessType != ScriptableObject.SlotAccess.QUERY) {
                checkMapSize();
            }
            ScriptableObject.Slot slot = this.map.get(key, index, accessType);
            this.lock.unlockWrite(stamp);
            return slot;
        } catch (Throwable th) {
            this.lock.unlockWrite(stamp);
            throw th;
        }
    }

    @Override // org.mozilla.javascript.SlotMapContainer, org.mozilla.javascript.SlotMap
    public ScriptableObject.Slot query(Object key, int index) {
        long stamp = this.lock.tryOptimisticRead();
        ScriptableObject.Slot s = this.map.query(key, index);
        if (this.lock.validate(stamp)) {
            return s;
        }
        long stamp2 = this.lock.readLock();
        try {
            ScriptableObject.Slot slotQuery = this.map.query(key, index);
            this.lock.unlockRead(stamp2);
            return slotQuery;
        } catch (Throwable th) {
            this.lock.unlockRead(stamp2);
            throw th;
        }
    }

    @Override // org.mozilla.javascript.SlotMapContainer, org.mozilla.javascript.SlotMap
    public void addSlot(ScriptableObject.Slot newSlot) {
        long stamp = this.lock.writeLock();
        try {
            checkMapSize();
            this.map.addSlot(newSlot);
            this.lock.unlockWrite(stamp);
        } catch (Throwable th) {
            this.lock.unlockWrite(stamp);
            throw th;
        }
    }

    @Override // org.mozilla.javascript.SlotMapContainer, org.mozilla.javascript.SlotMap
    public void remove(Object key, int index) {
        long stamp = this.lock.writeLock();
        try {
            this.map.remove(key, index);
            this.lock.unlockWrite(stamp);
        } catch (Throwable th) {
            this.lock.unlockWrite(stamp);
            throw th;
        }
    }

    @Override // org.mozilla.javascript.SlotMapContainer
    public long readLock() {
        return this.lock.readLock();
    }

    @Override // org.mozilla.javascript.SlotMapContainer
    public void unlockRead(long stamp) {
        this.lock.unlockRead(stamp);
    }

    @Override // org.mozilla.javascript.SlotMapContainer, java.lang.Iterable
    public Iterator<ScriptableObject.Slot> iterator() {
        if ($assertionsDisabled || this.lock.isReadLocked()) {
            return this.map.iterator();
        }
        throw new AssertionError();
    }

    @Override // org.mozilla.javascript.SlotMapContainer
    protected void checkMapSize() {
        if (!$assertionsDisabled && !this.lock.isWriteLocked()) {
            throw new AssertionError();
        }
        super.checkMapSize();
    }
}
