package org.mozilla.javascript;

import org.mozilla.javascript.ScriptableObject;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/SlotMap.class */
public interface SlotMap extends Iterable<ScriptableObject.Slot> {
    int size();

    boolean isEmpty();

    ScriptableObject.Slot get(Object obj, int i, ScriptableObject.SlotAccess slotAccess);

    ScriptableObject.Slot query(Object obj, int i);

    void addSlot(ScriptableObject.Slot slot);

    void remove(Object obj, int i);
}
