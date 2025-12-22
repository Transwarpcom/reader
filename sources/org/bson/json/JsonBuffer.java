package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/JsonBuffer.class */
class JsonBuffer {
    private final String buffer;
    private int position;
    private boolean eof;

    JsonBuffer(String buffer) {
        this.buffer = buffer;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int read() {
        if (this.eof) {
            throw new JsonParseException("Trying to read past EOF.");
        }
        if (this.position >= this.buffer.length()) {
            this.eof = true;
            return -1;
        }
        String str = this.buffer;
        int i = this.position;
        this.position = i + 1;
        return str.charAt(i);
    }

    public void unread(int c) {
        this.eof = false;
        if (c != -1 && this.buffer.charAt(this.position - 1) == c) {
            this.position--;
        }
    }

    public String substring(int beginIndex) {
        return this.buffer.substring(beginIndex);
    }

    public String substring(int beginIndex, int endIndex) {
        return this.buffer.substring(beginIndex, endIndex);
    }
}
