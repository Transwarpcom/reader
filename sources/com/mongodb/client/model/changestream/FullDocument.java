package com.mongodb.client.model.changestream;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/changestream/FullDocument.class */
public enum FullDocument {
    DEFAULT("default"),
    UPDATE_LOOKUP("updateLookup");

    private final String value;

    FullDocument(String caseFirst) {
        this.value = caseFirst;
    }

    public String getValue() {
        return this.value;
    }

    public static FullDocument fromString(String changeStreamFullDocument) {
        if (changeStreamFullDocument != null) {
            for (FullDocument fullDocument : values()) {
                if (changeStreamFullDocument.equals(fullDocument.value)) {
                    return fullDocument;
                }
            }
        }
        throw new IllegalArgumentException(String.format("'%s' is not a valid ChangeStreamFullDocument", changeStreamFullDocument));
    }
}
