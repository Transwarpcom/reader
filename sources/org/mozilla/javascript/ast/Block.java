package org.mozilla.javascript.ast;

import java.util.Iterator;
import org.mozilla.javascript.Node;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ast/Block.class */
public class Block extends AstNode {
    public Block() {
        this.type = 130;
    }

    public Block(int pos) {
        super(pos);
        this.type = 130;
    }

    public Block(int pos, int len) {
        super(pos, len);
        this.type = 130;
    }

    public void addStatement(AstNode statement) {
        addChild(statement);
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public String toSource(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
        sb.append("{\n");
        Iterator<Node> it = iterator();
        while (it.hasNext()) {
            Node kid = it.next();
            AstNode astNodeKid = (AstNode) kid;
            sb.append(astNodeKid.toSource(depth + 1));
            if (astNodeKid.getType() == 162) {
                sb.append("\n");
            }
        }
        sb.append(makeIndent(depth));
        sb.append("}");
        if (getInlineComment() != null) {
            sb.append(getInlineComment().toSource(depth));
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override // org.mozilla.javascript.ast.AstNode
    public void visit(NodeVisitor v) {
        if (v.visit(this)) {
            Iterator<Node> it = iterator();
            while (it.hasNext()) {
                Node kid = it.next();
                ((AstNode) kid).visit(v);
            }
        }
    }
}
