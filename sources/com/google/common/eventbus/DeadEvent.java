package com.google.common.eventbus;

import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import me.ag2s.epublib.epub.PackageDocumentBase;

@Beta
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/eventbus/DeadEvent.class */
public class DeadEvent {
    private final Object source;
    private final Object event;

    public DeadEvent(Object source, Object event) {
        this.source = Preconditions.checkNotNull(source);
        this.event = Preconditions.checkNotNull(event);
    }

    public Object getSource() {
        return this.source;
    }

    public Object getEvent() {
        return this.event;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(PackageDocumentBase.DCTags.source, this.source).add(PackageDocumentBase.OPFAttributes.event, this.event).toString();
    }
}
