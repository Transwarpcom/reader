package org.antlr.v4.runtime.tree.pattern;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/pattern/TagChunk.class */
class TagChunk extends Chunk {
    private final String tag;
    private final String label;

    public TagChunk(String tag) {
        this(null, tag);
    }

    public TagChunk(String label, String tag) {
        if (tag == null || tag.isEmpty()) {
            throw new IllegalArgumentException("tag cannot be null or empty");
        }
        this.label = label;
        this.tag = tag;
    }

    public final String getTag() {
        return this.tag;
    }

    public final String getLabel() {
        return this.label;
    }

    public String toString() {
        if (this.label != null) {
            return this.label + ":" + this.tag;
        }
        return this.tag;
    }
}
