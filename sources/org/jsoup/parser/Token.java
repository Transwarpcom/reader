package org.jsoup.parser;

import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attributes;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/Token.class */
abstract class Token {
    TokenType type;

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/Token$TokenType.class */
    public enum TokenType {
        Doctype,
        StartTag,
        EndTag,
        Comment,
        Character,
        EOF
    }

    abstract Token reset();

    private Token() {
    }

    String tokenType() {
        return getClass().getSimpleName();
    }

    static void reset(StringBuilder sb) {
        if (sb != null) {
            sb.delete(0, sb.length());
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/Token$Doctype.class */
    static final class Doctype extends Token {
        final StringBuilder name;
        String pubSysKey;
        final StringBuilder publicIdentifier;
        final StringBuilder systemIdentifier;
        boolean forceQuirks;

        Doctype() {
            super();
            this.name = new StringBuilder();
            this.pubSysKey = null;
            this.publicIdentifier = new StringBuilder();
            this.systemIdentifier = new StringBuilder();
            this.forceQuirks = false;
            this.type = TokenType.Doctype;
        }

        @Override // org.jsoup.parser.Token
        Token reset() {
            reset(this.name);
            this.pubSysKey = null;
            reset(this.publicIdentifier);
            reset(this.systemIdentifier);
            this.forceQuirks = false;
            return this;
        }

        String getName() {
            return this.name.toString();
        }

        String getPubSysKey() {
            return this.pubSysKey;
        }

        String getPublicIdentifier() {
            return this.publicIdentifier.toString();
        }

        public String getSystemIdentifier() {
            return this.systemIdentifier.toString();
        }

        public boolean isForceQuirks() {
            return this.forceQuirks;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/Token$Tag.class */
    static abstract class Tag extends Token {
        protected String tagName;
        protected String normalName;
        private String pendingAttributeName;
        private StringBuilder pendingAttributeValue;
        private String pendingAttributeValueS;
        private boolean hasEmptyAttributeValue;
        private boolean hasPendingAttributeValue;
        boolean selfClosing;
        Attributes attributes;

        public abstract String toString();

        Tag() {
            super();
            this.pendingAttributeValue = new StringBuilder();
            this.hasEmptyAttributeValue = false;
            this.hasPendingAttributeValue = false;
            this.selfClosing = false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.jsoup.parser.Token
        public Tag reset() {
            this.tagName = null;
            this.normalName = null;
            this.pendingAttributeName = null;
            reset(this.pendingAttributeValue);
            this.pendingAttributeValueS = null;
            this.hasEmptyAttributeValue = false;
            this.hasPendingAttributeValue = false;
            this.selfClosing = false;
            this.attributes = null;
            return this;
        }

        final void newAttribute() {
            String value;
            if (this.attributes == null) {
                this.attributes = new Attributes();
            }
            if (this.pendingAttributeName != null) {
                this.pendingAttributeName = this.pendingAttributeName.trim();
                if (this.pendingAttributeName.length() > 0) {
                    if (this.hasPendingAttributeValue) {
                        value = this.pendingAttributeValue.length() > 0 ? this.pendingAttributeValue.toString() : this.pendingAttributeValueS;
                    } else if (this.hasEmptyAttributeValue) {
                        value = "";
                    } else {
                        value = null;
                    }
                    this.attributes.add(this.pendingAttributeName, value);
                }
            }
            this.pendingAttributeName = null;
            this.hasEmptyAttributeValue = false;
            this.hasPendingAttributeValue = false;
            reset(this.pendingAttributeValue);
            this.pendingAttributeValueS = null;
        }

        final boolean hasAttributes() {
            return this.attributes != null;
        }

        final boolean hasAttribute(String key) {
            return this.attributes != null && this.attributes.hasKey(key);
        }

        final void finaliseTag() {
            if (this.pendingAttributeName != null) {
                newAttribute();
            }
        }

        final String name() {
            Validate.isFalse(this.tagName == null || this.tagName.length() == 0);
            return this.tagName;
        }

        final String normalName() {
            return this.normalName;
        }

        final String toStringName() {
            return this.tagName != null ? this.tagName : "[unset]";
        }

        final Tag name(String name) {
            this.tagName = name;
            this.normalName = Normalizer.lowerCase(name);
            return this;
        }

        final boolean isSelfClosing() {
            return this.selfClosing;
        }

        final void appendTagName(String append) {
            this.tagName = this.tagName == null ? append : this.tagName.concat(append);
            this.normalName = Normalizer.lowerCase(this.tagName);
        }

        final void appendTagName(char append) {
            appendTagName(String.valueOf(append));
        }

        final void appendAttributeName(String append) {
            this.pendingAttributeName = this.pendingAttributeName == null ? append : this.pendingAttributeName.concat(append);
        }

        final void appendAttributeName(char append) {
            appendAttributeName(String.valueOf(append));
        }

        final void appendAttributeValue(String append) {
            ensureAttributeValue();
            if (this.pendingAttributeValue.length() == 0) {
                this.pendingAttributeValueS = append;
            } else {
                this.pendingAttributeValue.append(append);
            }
        }

        final void appendAttributeValue(char append) {
            ensureAttributeValue();
            this.pendingAttributeValue.append(append);
        }

        final void appendAttributeValue(char[] append) {
            ensureAttributeValue();
            this.pendingAttributeValue.append(append);
        }

        final void appendAttributeValue(int[] appendCodepoints) {
            ensureAttributeValue();
            for (int codepoint : appendCodepoints) {
                this.pendingAttributeValue.appendCodePoint(codepoint);
            }
        }

        final void setEmptyAttributeValue() {
            this.hasEmptyAttributeValue = true;
        }

        private void ensureAttributeValue() {
            this.hasPendingAttributeValue = true;
            if (this.pendingAttributeValueS != null) {
                this.pendingAttributeValue.append(this.pendingAttributeValueS);
                this.pendingAttributeValueS = null;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/Token$StartTag.class */
    static final class StartTag extends Tag {
        StartTag() {
            this.type = TokenType.StartTag;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.jsoup.parser.Token.Tag, org.jsoup.parser.Token
        public Tag reset() {
            super.reset();
            this.attributes = null;
            return this;
        }

        StartTag nameAttr(String name, Attributes attributes) {
            this.tagName = name;
            this.attributes = attributes;
            this.normalName = Normalizer.lowerCase(this.tagName);
            return this;
        }

        @Override // org.jsoup.parser.Token.Tag
        public String toString() {
            if (hasAttributes() && this.attributes.size() > 0) {
                return "<" + toStringName() + " " + this.attributes.toString() + ">";
            }
            return "<" + toStringName() + ">";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/Token$EndTag.class */
    static final class EndTag extends Tag {
        EndTag() {
            this.type = TokenType.EndTag;
        }

        @Override // org.jsoup.parser.Token.Tag
        public String toString() {
            return "</" + toStringName() + ">";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/Token$Comment.class */
    static final class Comment extends Token {
        private final StringBuilder data;
        private String dataS;
        boolean bogus;

        @Override // org.jsoup.parser.Token
        Token reset() {
            reset(this.data);
            this.dataS = null;
            this.bogus = false;
            return this;
        }

        Comment() {
            super();
            this.data = new StringBuilder();
            this.bogus = false;
            this.type = TokenType.Comment;
        }

        String getData() {
            return this.dataS != null ? this.dataS : this.data.toString();
        }

        final Comment append(String append) {
            ensureData();
            if (this.data.length() == 0) {
                this.dataS = append;
            } else {
                this.data.append(append);
            }
            return this;
        }

        final Comment append(char append) {
            ensureData();
            this.data.append(append);
            return this;
        }

        private void ensureData() {
            if (this.dataS != null) {
                this.data.append(this.dataS);
                this.dataS = null;
            }
        }

        public String toString() {
            return "<!--" + getData() + "-->";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/Token$Character.class */
    static class Character extends Token {
        private String data;

        Character() {
            super();
            this.type = TokenType.Character;
        }

        @Override // org.jsoup.parser.Token
        Token reset() {
            this.data = null;
            return this;
        }

        Character data(String data) {
            this.data = data;
            return this;
        }

        String getData() {
            return this.data;
        }

        public String toString() {
            return getData();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/Token$CData.class */
    static final class CData extends Character {
        CData(String data) {
            data(data);
        }

        @Override // org.jsoup.parser.Token.Character
        public String toString() {
            return "<![CDATA[" + getData() + "]]>";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/Token$EOF.class */
    static final class EOF extends Token {
        EOF() {
            super();
            this.type = TokenType.EOF;
        }

        @Override // org.jsoup.parser.Token
        Token reset() {
            return this;
        }

        public String toString() {
            return "";
        }
    }

    final boolean isDoctype() {
        return this.type == TokenType.Doctype;
    }

    final Doctype asDoctype() {
        return (Doctype) this;
    }

    final boolean isStartTag() {
        return this.type == TokenType.StartTag;
    }

    final StartTag asStartTag() {
        return (StartTag) this;
    }

    final boolean isEndTag() {
        return this.type == TokenType.EndTag;
    }

    final EndTag asEndTag() {
        return (EndTag) this;
    }

    final boolean isComment() {
        return this.type == TokenType.Comment;
    }

    final Comment asComment() {
        return (Comment) this;
    }

    final boolean isCharacter() {
        return this.type == TokenType.Character;
    }

    final boolean isCData() {
        return this instanceof CData;
    }

    final Character asCharacter() {
        return (Character) this;
    }

    final boolean isEOF() {
        return this.type == TokenType.EOF;
    }
}
