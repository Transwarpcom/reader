package com.mongodb.client.model;

import ch.qos.logback.core.pattern.parser.Parser;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/MapReduceAction.class */
public enum MapReduceAction {
    REPLACE(Parser.REPLACE_CONVERTER_WORD),
    MERGE(BeanDefinitionParserDelegate.MERGE_ATTRIBUTE),
    REDUCE("reduce");

    private final String value;

    MapReduceAction(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
