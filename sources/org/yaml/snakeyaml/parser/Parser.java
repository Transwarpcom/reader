package org.yaml.snakeyaml.parser;

import org.yaml.snakeyaml.events.Event;

/* loaded from: reader.jar:BOOT-INF/lib/snakeyaml-1.23.jar:org/yaml/snakeyaml/parser/Parser.class */
public interface Parser {
    boolean checkEvent(Event.ID id);

    Event peekEvent();

    Event getEvent();
}
