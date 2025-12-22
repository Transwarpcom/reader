package org.yaml.snakeyaml.events;

import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.events.Event;

/* loaded from: reader.jar:BOOT-INF/lib/snakeyaml-1.23.jar:org/yaml/snakeyaml/events/StreamEndEvent.class */
public final class StreamEndEvent extends Event {
    public StreamEndEvent(Mark startMark, Mark endMark) {
        super(startMark, endMark);
    }

    @Override // org.yaml.snakeyaml.events.Event
    public boolean is(Event.ID id) {
        return Event.ID.StreamEnd == id;
    }
}
