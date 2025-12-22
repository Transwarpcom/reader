package com.fasterxml.jackson.core;

import java.io.IOException;

/* loaded from: reader.jar:BOOT-INF/lib/jackson-core-2.9.9.jar:com/fasterxml/jackson/core/TreeCodec.class */
public abstract class TreeCodec {
    public abstract <T extends TreeNode> T readTree(JsonParser jsonParser) throws IOException;

    public abstract void writeTree(JsonGenerator jsonGenerator, TreeNode treeNode) throws IOException;

    public abstract TreeNode createArrayNode();

    public abstract TreeNode createObjectNode();

    public abstract JsonParser treeAsTokens(TreeNode treeNode);
}
