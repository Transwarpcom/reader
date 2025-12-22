package org.apache.logging.log4j.message;

/* loaded from: reader.jar:BOOT-INF/lib/log4j-api-2.11.2.jar:org/apache/logging/log4j/message/MultiformatMessage.class */
public interface MultiformatMessage extends Message {
    String getFormattedMessage(String[] strArr);

    String[] getFormats();
}
