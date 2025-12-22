package com.mongodb;

import org.bson.types.BasicBSONList;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/BasicDBList.class */
public class BasicDBList extends BasicBSONList implements DBObject {
    private static final long serialVersionUID = -4415279469780082174L;
    private boolean _isPartialObject;

    @Override // com.mongodb.DBObject
    public boolean isPartialObject() {
        return this._isPartialObject;
    }

    @Override // com.mongodb.DBObject
    public void markAsPartialObject() {
        this._isPartialObject = true;
    }

    public Object copy() {
        BasicDBList newobj = new BasicDBList();
        for (int i = 0; i < size(); i++) {
            Object val = get(i);
            if (val instanceof BasicDBObject) {
                val = ((BasicDBObject) val).copy();
            } else if (val instanceof BasicDBList) {
                val = ((BasicDBList) val).copy();
            }
            newobj.add(val);
        }
        return newobj;
    }
}
