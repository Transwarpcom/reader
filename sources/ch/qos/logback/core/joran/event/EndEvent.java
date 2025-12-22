package ch.qos.logback.core.joran.event;

import org.xml.sax.Locator;

/* loaded from: reader.jar:BOOT-INF/lib/logback-core-1.2.3.jar:ch/qos/logback/core/joran/event/EndEvent.class */
public class EndEvent extends SaxEvent {
    EndEvent(String namespaceURI, String localName, String qName, Locator locator) {
        super(namespaceURI, localName, qName, locator);
    }

    public String toString() {
        return "  EndEvent(" + getQName() + ")  [" + this.locator.getLineNumber() + "," + this.locator.getColumnNumber() + "]";
    }
}
