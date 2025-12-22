package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonTokenType.class */
enum JsonTokenType {
    INVALID,
    BEGIN_ARRAY,
    BEGIN_OBJECT,
    END_ARRAY,
    LEFT_PAREN,
    RIGHT_PAREN,
    END_OBJECT,
    COLON,
    COMMA,
    DOUBLE,
    INT32,
    INT64,
    REGULAR_EXPRESSION,
    STRING,
    UNQUOTED_STRING,
    END_OF_FILE
}
