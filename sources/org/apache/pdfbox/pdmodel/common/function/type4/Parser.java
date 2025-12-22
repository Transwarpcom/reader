package org.apache.pdfbox.pdmodel.common.function.type4;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/common/function/type4/Parser.class */
public final class Parser {

    /* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/common/function/type4/Parser$State.class */
    private enum State {
        NEWLINE,
        WHITESPACE,
        COMMENT,
        TOKEN
    }

    /* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/common/function/type4/Parser$SyntaxHandler.class */
    public interface SyntaxHandler {
        void newLine(CharSequence charSequence);

        void whitespace(CharSequence charSequence);

        void token(CharSequence charSequence);

        void comment(CharSequence charSequence);
    }

    private Parser() {
    }

    public static void parse(CharSequence input, SyntaxHandler handler) {
        Tokenizer tokenizer = new Tokenizer(input, handler);
        tokenizer.tokenize();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/common/function/type4/Parser$AbstractSyntaxHandler.class */
    public static abstract class AbstractSyntaxHandler implements SyntaxHandler {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Parser.SyntaxHandler
        public void comment(CharSequence text) {
        }

        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Parser.SyntaxHandler
        public void newLine(CharSequence text) {
        }

        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Parser.SyntaxHandler
        public void whitespace(CharSequence text) {
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/common/function/type4/Parser$Tokenizer.class */
    private static final class Tokenizer {
        private static final char NUL = 0;
        private static final char EOT = 4;
        private static final char TAB = '\t';
        private static final char FF = '\f';
        private static final char CR = '\r';
        private static final char LF = '\n';
        private static final char SPACE = ' ';
        private final CharSequence input;
        private int index;
        private final SyntaxHandler handler;
        private State state;
        private final StringBuilder buffer;
        static final /* synthetic */ boolean $assertionsDisabled;

        static {
            $assertionsDisabled = !Parser.class.desiredAssertionStatus();
        }

        private Tokenizer(CharSequence text, SyntaxHandler syntaxHandler) {
            this.state = State.WHITESPACE;
            this.buffer = new StringBuilder();
            this.input = text;
            this.handler = syntaxHandler;
        }

        private boolean hasMore() {
            return this.index < this.input.length();
        }

        private char currentChar() {
            return this.input.charAt(this.index);
        }

        private char nextChar() {
            this.index++;
            if (!hasMore()) {
                return (char) 4;
            }
            return currentChar();
        }

        private char peek() {
            if (this.index < this.input.length() - 1) {
                return this.input.charAt(this.index + 1);
            }
            return (char) 4;
        }

        private State nextState() {
            char ch2 = currentChar();
            switch (ch2) {
                case 0:
                case '\t':
                case ' ':
                    this.state = State.WHITESPACE;
                    break;
                case '\n':
                case '\f':
                case '\r':
                    this.state = State.NEWLINE;
                    break;
                case '%':
                    this.state = State.COMMENT;
                    break;
                default:
                    this.state = State.TOKEN;
                    break;
            }
            return this.state;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void tokenize() {
            while (hasMore()) {
                this.buffer.setLength(0);
                nextState();
                switch (this.state) {
                    case NEWLINE:
                        scanNewLine();
                        break;
                    case WHITESPACE:
                        scanWhitespace();
                        break;
                    case COMMENT:
                        scanComment();
                        break;
                    default:
                        scanToken();
                        break;
                }
            }
        }

        private void scanNewLine() {
            if (!$assertionsDisabled && this.state != State.NEWLINE) {
                throw new AssertionError();
            }
            char ch2 = currentChar();
            this.buffer.append(ch2);
            if (ch2 == '\r' && peek() == '\n') {
                this.buffer.append(nextChar());
            }
            this.handler.newLine(this.buffer);
            nextChar();
        }

        private void scanWhitespace() {
            if (!$assertionsDisabled && this.state != State.WHITESPACE) {
                throw new AssertionError();
            }
            this.buffer.append(currentChar());
            while (hasMore()) {
                char ch2 = nextChar();
                switch (ch2) {
                    case 0:
                    case '\t':
                    case ' ':
                        this.buffer.append(ch2);
                }
                this.handler.whitespace(this.buffer);
            }
            this.handler.whitespace(this.buffer);
        }

        private void scanComment() {
            if (!$assertionsDisabled && this.state != State.COMMENT) {
                throw new AssertionError();
            }
            this.buffer.append(currentChar());
            while (hasMore()) {
                char ch2 = nextChar();
                switch (ch2) {
                    case '\n':
                    case '\f':
                    case '\r':
                        break;
                    case 11:
                    default:
                        this.buffer.append(ch2);
                }
                this.handler.comment(this.buffer);
            }
            this.handler.comment(this.buffer);
        }

        private void scanToken() {
            if (!$assertionsDisabled && this.state != State.TOKEN) {
                throw new AssertionError();
            }
            char ch2 = currentChar();
            this.buffer.append(ch2);
            switch (ch2) {
                case '{':
                case '}':
                    this.handler.token(this.buffer);
                    nextChar();
                    return;
            }
            while (hasMore()) {
                char ch3 = nextChar();
                switch (ch3) {
                    case 0:
                    case 4:
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case '{':
                    case '}':
                        break;
                    default:
                        this.buffer.append(ch3);
                }
                this.handler.token(this.buffer);
            }
            this.handler.token(this.buffer);
        }
    }
}
