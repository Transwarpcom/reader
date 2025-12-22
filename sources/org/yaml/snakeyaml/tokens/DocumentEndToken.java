package org.yaml.snakeyaml.tokens;

import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.tokens.Token;

/* loaded from: reader.jar:BOOT-INF/lib/snakeyaml-1.23.jar:org/yaml/snakeyaml/tokens/DocumentEndToken.class */
public final class DocumentEndToken extends Token {
    public DocumentEndToken(Mark startMark, Mark endMark) {
        super(startMark, endMark);
    }

    @Override // org.yaml.snakeyaml.tokens.Token
    public Token.ID getTokenId() {
        return Token.ID.DocumentEnd;
    }
}
