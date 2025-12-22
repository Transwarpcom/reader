package org.jsoup.parser;

import io.vertx.core.cli.UsageMessageFormatter;
import java.io.IOException;
import org.jsoup.nodes.DocumentType;
import org.jsoup.parser.Token;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/parser/TokeniserState.class */
enum TokeniserState {
    Data { // from class: org.jsoup.parser.TokeniserState.1
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            switch (r.current()) {
                case 0:
                    t.error(this);
                    t.emit(r.consume());
                    break;
                case '&':
                    t.advanceTransition(CharacterReferenceInData);
                    break;
                case '<':
                    t.advanceTransition(TagOpen);
                    break;
                case 65535:
                    t.emit(new Token.EOF());
                    break;
                default:
                    String data = r.consumeData();
                    t.emit(data);
                    break;
            }
        }
    },
    CharacterReferenceInData { // from class: org.jsoup.parser.TokeniserState.2
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            TokeniserState.readCharRef(t, Data);
        }
    },
    Rcdata { // from class: org.jsoup.parser.TokeniserState.3
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            switch (r.current()) {
                case 0:
                    t.error(this);
                    r.advance();
                    t.emit((char) 65533);
                    break;
                case '&':
                    t.advanceTransition(CharacterReferenceInRcdata);
                    break;
                case '<':
                    t.advanceTransition(RcdataLessthanSign);
                    break;
                case 65535:
                    t.emit(new Token.EOF());
                    break;
                default:
                    String data = r.consumeData();
                    t.emit(data);
                    break;
            }
        }
    },
    CharacterReferenceInRcdata { // from class: org.jsoup.parser.TokeniserState.4
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            TokeniserState.readCharRef(t, Rcdata);
        }
    },
    Rawtext { // from class: org.jsoup.parser.TokeniserState.5
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            TokeniserState.readRawData(t, r, this, RawtextLessthanSign);
        }
    },
    ScriptData { // from class: org.jsoup.parser.TokeniserState.6
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            TokeniserState.readRawData(t, r, this, ScriptDataLessthanSign);
        }
    },
    PLAINTEXT { // from class: org.jsoup.parser.TokeniserState.7
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            switch (r.current()) {
                case 0:
                    t.error(this);
                    r.advance();
                    t.emit((char) 65533);
                    break;
                case 65535:
                    t.emit(new Token.EOF());
                    break;
                default:
                    String data = r.consumeTo((char) 0);
                    t.emit(data);
                    break;
            }
        }
    },
    TagOpen { // from class: org.jsoup.parser.TokeniserState.8
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            switch (r.current()) {
                case '!':
                    t.advanceTransition(MarkupDeclarationOpen);
                    break;
                case '/':
                    t.advanceTransition(EndTagOpen);
                    break;
                case '?':
                    t.createBogusCommentPending();
                    t.advanceTransition(BogusComment);
                    break;
                default:
                    if (r.matchesLetter()) {
                        t.createTagPending(true);
                        t.transition(TagName);
                        break;
                    } else {
                        t.error(this);
                        t.emit('<');
                        t.transition(Data);
                        break;
                    }
            }
        }
    },
    EndTagOpen { // from class: org.jsoup.parser.TokeniserState.9
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            if (r.isEmpty()) {
                t.eofError(this);
                t.emit("</");
                t.transition(Data);
            } else if (r.matchesLetter()) {
                t.createTagPending(false);
                t.transition(TagName);
            } else if (r.matches('>')) {
                t.error(this);
                t.advanceTransition(Data);
            } else {
                t.error(this);
                t.createBogusCommentPending();
                t.advanceTransition(BogusComment);
            }
        }
    },
    TagName { // from class: org.jsoup.parser.TokeniserState.10
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            String tagName = r.consumeTagName();
            t.tagPending.appendTagName(tagName);
            char c = r.consume();
            switch (c) {
                case 0:
                    t.tagPending.appendTagName(TokeniserState.replacementStr);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    t.transition(BeforeAttributeName);
                    return;
                case '/':
                    t.transition(SelfClosingStartTag);
                    return;
                case '<':
                    r.unconsume();
                    t.error(this);
                    break;
                case '>':
                    break;
                case 65535:
                    t.eofError(this);
                    t.transition(Data);
                    return;
                default:
                    t.tagPending.appendTagName(c);
                    return;
            }
            t.emitTagPending();
            t.transition(Data);
        }
    },
    RcdataLessthanSign { // from class: org.jsoup.parser.TokeniserState.11
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            if (r.matches('/')) {
                t.createTempBuffer();
                t.advanceTransition(RCDATAEndTagOpen);
            } else if (r.matchesLetter() && t.appropriateEndTagName() != null && !r.containsIgnoreCase("</" + t.appropriateEndTagName())) {
                t.tagPending = t.createTagPending(false).name(t.appropriateEndTagName());
                t.emitTagPending();
                t.transition(TagOpen);
            } else {
                t.emit("<");
                t.transition(Rcdata);
            }
        }
    },
    RCDATAEndTagOpen { // from class: org.jsoup.parser.TokeniserState.12
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            if (r.matchesLetter()) {
                t.createTagPending(false);
                t.tagPending.appendTagName(r.current());
                t.dataBuffer.append(r.current());
                t.advanceTransition(RCDATAEndTagName);
                return;
            }
            t.emit("</");
            t.transition(Rcdata);
        }
    },
    RCDATAEndTagName { // from class: org.jsoup.parser.TokeniserState.13
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            if (r.matchesLetter()) {
                String name = r.consumeLetterSequence();
                t.tagPending.appendTagName(name);
                t.dataBuffer.append(name);
            }
            char c = r.consume();
            switch (c) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    if (t.isAppropriateEndTagToken()) {
                        t.transition(BeforeAttributeName);
                        break;
                    } else {
                        anythingElse(t, r);
                        break;
                    }
                case '/':
                    if (t.isAppropriateEndTagToken()) {
                        t.transition(SelfClosingStartTag);
                        break;
                    } else {
                        anythingElse(t, r);
                        break;
                    }
                case '>':
                    if (t.isAppropriateEndTagToken()) {
                        t.emitTagPending();
                        t.transition(Data);
                        break;
                    } else {
                        anythingElse(t, r);
                        break;
                    }
                default:
                    anythingElse(t, r);
                    break;
            }
        }

        private void anythingElse(Tokeniser t, CharacterReader r) {
            t.emit("</");
            t.emit(t.dataBuffer);
            r.unconsume();
            t.transition(Rcdata);
        }
    },
    RawtextLessthanSign { // from class: org.jsoup.parser.TokeniserState.14
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            if (r.matches('/')) {
                t.createTempBuffer();
                t.advanceTransition(RawtextEndTagOpen);
            } else {
                t.emit('<');
                t.transition(Rawtext);
            }
        }
    },
    RawtextEndTagOpen { // from class: org.jsoup.parser.TokeniserState.15
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            TokeniserState.readEndTag(t, r, RawtextEndTagName, Rawtext);
        }
    },
    RawtextEndTagName { // from class: org.jsoup.parser.TokeniserState.16
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            TokeniserState.handleDataEndTag(t, r, Rawtext);
        }
    },
    ScriptDataLessthanSign { // from class: org.jsoup.parser.TokeniserState.17
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            switch (r.consume()) {
                case '!':
                    t.emit("<!");
                    t.transition(ScriptDataEscapeStart);
                    break;
                case '/':
                    t.createTempBuffer();
                    t.transition(ScriptDataEndTagOpen);
                    break;
                case 65535:
                    t.emit("<");
                    t.eofError(this);
                    t.transition(Data);
                    break;
                default:
                    t.emit("<");
                    r.unconsume();
                    t.transition(ScriptData);
                    break;
            }
        }
    },
    ScriptDataEndTagOpen { // from class: org.jsoup.parser.TokeniserState.18
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            TokeniserState.readEndTag(t, r, ScriptDataEndTagName, ScriptData);
        }
    },
    ScriptDataEndTagName { // from class: org.jsoup.parser.TokeniserState.19
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            TokeniserState.handleDataEndTag(t, r, ScriptData);
        }
    },
    ScriptDataEscapeStart { // from class: org.jsoup.parser.TokeniserState.20
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            if (r.matches('-')) {
                t.emit('-');
                t.advanceTransition(ScriptDataEscapeStartDash);
            } else {
                t.transition(ScriptData);
            }
        }
    },
    ScriptDataEscapeStartDash { // from class: org.jsoup.parser.TokeniserState.21
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            if (r.matches('-')) {
                t.emit('-');
                t.advanceTransition(ScriptDataEscapedDashDash);
            } else {
                t.transition(ScriptData);
            }
        }
    },
    ScriptDataEscaped { // from class: org.jsoup.parser.TokeniserState.22
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            if (r.isEmpty()) {
                t.eofError(this);
                t.transition(Data);
            }
            switch (r.current()) {
                case 0:
                    t.error(this);
                    r.advance();
                    t.emit((char) 65533);
                    break;
                case '-':
                    t.emit('-');
                    t.advanceTransition(ScriptDataEscapedDash);
                    break;
                case '<':
                    t.advanceTransition(ScriptDataEscapedLessthanSign);
                    break;
                default:
                    String data = r.consumeToAny('-', '<', 0);
                    t.emit(data);
                    break;
            }
        }
    },
    ScriptDataEscapedDash { // from class: org.jsoup.parser.TokeniserState.23
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            if (r.isEmpty()) {
                t.eofError(this);
                t.transition(Data);
            }
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.emit((char) 65533);
                    t.transition(ScriptDataEscaped);
                    break;
                case '-':
                    t.emit(c);
                    t.transition(ScriptDataEscapedDashDash);
                    break;
                case '<':
                    t.transition(ScriptDataEscapedLessthanSign);
                    break;
                default:
                    t.emit(c);
                    t.transition(ScriptDataEscaped);
                    break;
            }
        }
    },
    ScriptDataEscapedDashDash { // from class: org.jsoup.parser.TokeniserState.24
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            if (r.isEmpty()) {
                t.eofError(this);
                t.transition(Data);
            }
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.emit((char) 65533);
                    t.transition(ScriptDataEscaped);
                    break;
                case '-':
                    t.emit(c);
                    break;
                case '<':
                    t.transition(ScriptDataEscapedLessthanSign);
                    break;
                case '>':
                    t.emit(c);
                    t.transition(ScriptData);
                    break;
                default:
                    t.emit(c);
                    t.transition(ScriptDataEscaped);
                    break;
            }
        }
    },
    ScriptDataEscapedLessthanSign { // from class: org.jsoup.parser.TokeniserState.25
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            if (r.matchesLetter()) {
                t.createTempBuffer();
                t.dataBuffer.append(r.current());
                t.emit("<");
                t.emit(r.current());
                t.advanceTransition(ScriptDataDoubleEscapeStart);
                return;
            }
            if (r.matches('/')) {
                t.createTempBuffer();
                t.advanceTransition(ScriptDataEscapedEndTagOpen);
            } else {
                t.emit('<');
                t.transition(ScriptDataEscaped);
            }
        }
    },
    ScriptDataEscapedEndTagOpen { // from class: org.jsoup.parser.TokeniserState.26
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            if (r.matchesLetter()) {
                t.createTagPending(false);
                t.tagPending.appendTagName(r.current());
                t.dataBuffer.append(r.current());
                t.advanceTransition(ScriptDataEscapedEndTagName);
                return;
            }
            t.emit("</");
            t.transition(ScriptDataEscaped);
        }
    },
    ScriptDataEscapedEndTagName { // from class: org.jsoup.parser.TokeniserState.27
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            TokeniserState.handleDataEndTag(t, r, ScriptDataEscaped);
        }
    },
    ScriptDataDoubleEscapeStart { // from class: org.jsoup.parser.TokeniserState.28
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            TokeniserState.handleDataDoubleEscapeTag(t, r, ScriptDataDoubleEscaped, ScriptDataEscaped);
        }
    },
    ScriptDataDoubleEscaped { // from class: org.jsoup.parser.TokeniserState.29
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.current();
            switch (c) {
                case 0:
                    t.error(this);
                    r.advance();
                    t.emit((char) 65533);
                    break;
                case '-':
                    t.emit(c);
                    t.advanceTransition(ScriptDataDoubleEscapedDash);
                    break;
                case '<':
                    t.emit(c);
                    t.advanceTransition(ScriptDataDoubleEscapedLessthanSign);
                    break;
                case 65535:
                    t.eofError(this);
                    t.transition(Data);
                    break;
                default:
                    String data = r.consumeToAny('-', '<', 0);
                    t.emit(data);
                    break;
            }
        }
    },
    ScriptDataDoubleEscapedDash { // from class: org.jsoup.parser.TokeniserState.30
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.emit((char) 65533);
                    t.transition(ScriptDataDoubleEscaped);
                    break;
                case '-':
                    t.emit(c);
                    t.transition(ScriptDataDoubleEscapedDashDash);
                    break;
                case '<':
                    t.emit(c);
                    t.transition(ScriptDataDoubleEscapedLessthanSign);
                    break;
                case 65535:
                    t.eofError(this);
                    t.transition(Data);
                    break;
                default:
                    t.emit(c);
                    t.transition(ScriptDataDoubleEscaped);
                    break;
            }
        }
    },
    ScriptDataDoubleEscapedDashDash { // from class: org.jsoup.parser.TokeniserState.31
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.emit((char) 65533);
                    t.transition(ScriptDataDoubleEscaped);
                    break;
                case '-':
                    t.emit(c);
                    break;
                case '<':
                    t.emit(c);
                    t.transition(ScriptDataDoubleEscapedLessthanSign);
                    break;
                case '>':
                    t.emit(c);
                    t.transition(ScriptData);
                    break;
                case 65535:
                    t.eofError(this);
                    t.transition(Data);
                    break;
                default:
                    t.emit(c);
                    t.transition(ScriptDataDoubleEscaped);
                    break;
            }
        }
    },
    ScriptDataDoubleEscapedLessthanSign { // from class: org.jsoup.parser.TokeniserState.32
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            if (r.matches('/')) {
                t.emit('/');
                t.createTempBuffer();
                t.advanceTransition(ScriptDataDoubleEscapeEnd);
                return;
            }
            t.transition(ScriptDataDoubleEscaped);
        }
    },
    ScriptDataDoubleEscapeEnd { // from class: org.jsoup.parser.TokeniserState.33
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            TokeniserState.handleDataDoubleEscapeTag(t, r, ScriptDataEscaped, ScriptDataDoubleEscaped);
        }
    },
    BeforeAttributeName { // from class: org.jsoup.parser.TokeniserState.34
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case 0:
                    r.unconsume();
                    t.error(this);
                    t.tagPending.newAttribute();
                    t.transition(AttributeName);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                case '\'':
                case '=':
                    t.error(this);
                    t.tagPending.newAttribute();
                    t.tagPending.appendAttributeName(c);
                    t.transition(AttributeName);
                    return;
                case '/':
                    t.transition(SelfClosingStartTag);
                    return;
                case '<':
                    r.unconsume();
                    t.error(this);
                    break;
                case '>':
                    break;
                case 65535:
                    t.eofError(this);
                    t.transition(Data);
                    return;
                default:
                    t.tagPending.newAttribute();
                    r.unconsume();
                    t.transition(AttributeName);
                    return;
            }
            t.emitTagPending();
            t.transition(Data);
        }
    },
    AttributeName { // from class: org.jsoup.parser.TokeniserState.35
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            String name = r.consumeToAnySorted(attributeNameCharsSorted);
            t.tagPending.appendAttributeName(name);
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.tagPending.appendAttributeName((char) 65533);
                    break;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    t.transition(AfterAttributeName);
                    break;
                case '\"':
                case '\'':
                case '<':
                    t.error(this);
                    t.tagPending.appendAttributeName(c);
                    break;
                case '/':
                    t.transition(SelfClosingStartTag);
                    break;
                case '=':
                    t.transition(BeforeAttributeValue);
                    break;
                case '>':
                    t.emitTagPending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.transition(Data);
                    break;
                default:
                    t.tagPending.appendAttributeName(c);
                    break;
            }
        }
    },
    AfterAttributeName { // from class: org.jsoup.parser.TokeniserState.36
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.tagPending.appendAttributeName((char) 65533);
                    t.transition(AttributeName);
                    break;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    break;
                case '\"':
                case '\'':
                case '<':
                    t.error(this);
                    t.tagPending.newAttribute();
                    t.tagPending.appendAttributeName(c);
                    t.transition(AttributeName);
                    break;
                case '/':
                    t.transition(SelfClosingStartTag);
                    break;
                case '=':
                    t.transition(BeforeAttributeValue);
                    break;
                case '>':
                    t.emitTagPending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.transition(Data);
                    break;
                default:
                    t.tagPending.newAttribute();
                    r.unconsume();
                    t.transition(AttributeName);
                    break;
            }
        }
    },
    BeforeAttributeValue { // from class: org.jsoup.parser.TokeniserState.37
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.tagPending.appendAttributeValue((char) 65533);
                    t.transition(AttributeValue_unquoted);
                    break;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    break;
                case '\"':
                    t.transition(AttributeValue_doubleQuoted);
                    break;
                case '&':
                    r.unconsume();
                    t.transition(AttributeValue_unquoted);
                    break;
                case '\'':
                    t.transition(AttributeValue_singleQuoted);
                    break;
                case '<':
                case '=':
                case '`':
                    t.error(this);
                    t.tagPending.appendAttributeValue(c);
                    t.transition(AttributeValue_unquoted);
                    break;
                case '>':
                    t.error(this);
                    t.emitTagPending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.emitTagPending();
                    t.transition(Data);
                    break;
                default:
                    r.unconsume();
                    t.transition(AttributeValue_unquoted);
                    break;
            }
        }
    },
    AttributeValue_doubleQuoted { // from class: org.jsoup.parser.TokeniserState.38
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            String value = r.consumeAttributeQuoted(false);
            if (value.length() > 0) {
                t.tagPending.appendAttributeValue(value);
            } else {
                t.tagPending.setEmptyAttributeValue();
            }
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.tagPending.appendAttributeValue((char) 65533);
                    break;
                case '\"':
                    t.transition(AfterAttributeValue_quoted);
                    break;
                case '&':
                    int[] ref = t.consumeCharacterReference('\"', true);
                    if (ref != null) {
                        t.tagPending.appendAttributeValue(ref);
                        break;
                    } else {
                        t.tagPending.appendAttributeValue('&');
                        break;
                    }
                case 65535:
                    t.eofError(this);
                    t.transition(Data);
                    break;
                default:
                    t.tagPending.appendAttributeValue(c);
                    break;
            }
        }
    },
    AttributeValue_singleQuoted { // from class: org.jsoup.parser.TokeniserState.39
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            String value = r.consumeAttributeQuoted(true);
            if (value.length() > 0) {
                t.tagPending.appendAttributeValue(value);
            } else {
                t.tagPending.setEmptyAttributeValue();
            }
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.tagPending.appendAttributeValue((char) 65533);
                    break;
                case '&':
                    int[] ref = t.consumeCharacterReference('\'', true);
                    if (ref != null) {
                        t.tagPending.appendAttributeValue(ref);
                        break;
                    } else {
                        t.tagPending.appendAttributeValue('&');
                        break;
                    }
                case '\'':
                    t.transition(AfterAttributeValue_quoted);
                    break;
                case 65535:
                    t.eofError(this);
                    t.transition(Data);
                    break;
                default:
                    t.tagPending.appendAttributeValue(c);
                    break;
            }
        }
    },
    AttributeValue_unquoted { // from class: org.jsoup.parser.TokeniserState.40
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            String value = r.consumeToAnySorted(attributeValueUnquoted);
            if (value.length() > 0) {
                t.tagPending.appendAttributeValue(value);
            }
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.tagPending.appendAttributeValue((char) 65533);
                    break;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    t.transition(BeforeAttributeName);
                    break;
                case '\"':
                case '\'':
                case '<':
                case '=':
                case '`':
                    t.error(this);
                    t.tagPending.appendAttributeValue(c);
                    break;
                case '&':
                    int[] ref = t.consumeCharacterReference('>', true);
                    if (ref != null) {
                        t.tagPending.appendAttributeValue(ref);
                        break;
                    } else {
                        t.tagPending.appendAttributeValue('&');
                        break;
                    }
                case '>':
                    t.emitTagPending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.transition(Data);
                    break;
                default:
                    t.tagPending.appendAttributeValue(c);
                    break;
            }
        }
    },
    AfterAttributeValue_quoted { // from class: org.jsoup.parser.TokeniserState.41
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    t.transition(BeforeAttributeName);
                    break;
                case '/':
                    t.transition(SelfClosingStartTag);
                    break;
                case '>':
                    t.emitTagPending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.transition(Data);
                    break;
                default:
                    r.unconsume();
                    t.error(this);
                    t.transition(BeforeAttributeName);
                    break;
            }
        }
    },
    SelfClosingStartTag { // from class: org.jsoup.parser.TokeniserState.42
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case '>':
                    t.tagPending.selfClosing = true;
                    t.emitTagPending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.transition(Data);
                    break;
                default:
                    r.unconsume();
                    t.error(this);
                    t.transition(BeforeAttributeName);
                    break;
            }
        }
    },
    BogusComment { // from class: org.jsoup.parser.TokeniserState.43
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            r.unconsume();
            t.commentPending.append(r.consumeTo('>'));
            char next = r.consume();
            if (next == '>' || next == 65535) {
                t.emitCommentPending();
                t.transition(Data);
            }
        }
    },
    MarkupDeclarationOpen { // from class: org.jsoup.parser.TokeniserState.44
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            if (r.matchConsume(UsageMessageFormatter.DEFAULT_LONG_OPT_PREFIX)) {
                t.createCommentPending();
                t.transition(CommentStart);
            } else {
                if (r.matchConsumeIgnoreCase("DOCTYPE")) {
                    t.transition(Doctype);
                    return;
                }
                if (r.matchConsume("[CDATA[")) {
                    t.createTempBuffer();
                    t.transition(CdataSection);
                } else {
                    t.error(this);
                    t.createBogusCommentPending();
                    t.advanceTransition(BogusComment);
                }
            }
        }
    },
    CommentStart { // from class: org.jsoup.parser.TokeniserState.45
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.commentPending.append((char) 65533);
                    t.transition(Comment);
                    break;
                case '-':
                    t.transition(CommentStartDash);
                    break;
                case '>':
                    t.error(this);
                    t.emitCommentPending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.emitCommentPending();
                    t.transition(Data);
                    break;
                default:
                    r.unconsume();
                    t.transition(Comment);
                    break;
            }
        }
    },
    CommentStartDash { // from class: org.jsoup.parser.TokeniserState.46
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.commentPending.append((char) 65533);
                    t.transition(Comment);
                    break;
                case '-':
                    t.transition(CommentStartDash);
                    break;
                case '>':
                    t.error(this);
                    t.emitCommentPending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.emitCommentPending();
                    t.transition(Data);
                    break;
                default:
                    t.commentPending.append(c);
                    t.transition(Comment);
                    break;
            }
        }
    },
    Comment { // from class: org.jsoup.parser.TokeniserState.47
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.current();
            switch (c) {
                case 0:
                    t.error(this);
                    r.advance();
                    t.commentPending.append((char) 65533);
                    break;
                case '-':
                    t.advanceTransition(CommentEndDash);
                    break;
                case 65535:
                    t.eofError(this);
                    t.emitCommentPending();
                    t.transition(Data);
                    break;
                default:
                    t.commentPending.append(r.consumeToAny('-', 0));
                    break;
            }
        }
    },
    CommentEndDash { // from class: org.jsoup.parser.TokeniserState.48
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.commentPending.append('-').append((char) 65533);
                    t.transition(Comment);
                    break;
                case '-':
                    t.transition(CommentEnd);
                    break;
                case 65535:
                    t.eofError(this);
                    t.emitCommentPending();
                    t.transition(Data);
                    break;
                default:
                    t.commentPending.append('-').append(c);
                    t.transition(Comment);
                    break;
            }
        }
    },
    CommentEnd { // from class: org.jsoup.parser.TokeniserState.49
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.commentPending.append(UsageMessageFormatter.DEFAULT_LONG_OPT_PREFIX).append((char) 65533);
                    t.transition(Comment);
                    break;
                case '!':
                    t.error(this);
                    t.transition(CommentEndBang);
                    break;
                case '-':
                    t.error(this);
                    t.commentPending.append('-');
                    break;
                case '>':
                    t.emitCommentPending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.emitCommentPending();
                    t.transition(Data);
                    break;
                default:
                    t.error(this);
                    t.commentPending.append(UsageMessageFormatter.DEFAULT_LONG_OPT_PREFIX).append(c);
                    t.transition(Comment);
                    break;
            }
        }
    },
    CommentEndBang { // from class: org.jsoup.parser.TokeniserState.50
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.commentPending.append("--!").append((char) 65533);
                    t.transition(Comment);
                    break;
                case '-':
                    t.commentPending.append("--!");
                    t.transition(CommentEndDash);
                    break;
                case '>':
                    t.emitCommentPending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.emitCommentPending();
                    t.transition(Data);
                    break;
                default:
                    t.commentPending.append("--!").append(c);
                    t.transition(Comment);
                    break;
            }
        }
    },
    Doctype { // from class: org.jsoup.parser.TokeniserState.51
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    t.transition(BeforeDoctypeName);
                    return;
                case '>':
                    break;
                case 65535:
                    t.eofError(this);
                    break;
                default:
                    t.error(this);
                    t.transition(BeforeDoctypeName);
                    return;
            }
            t.error(this);
            t.createDoctypePending();
            t.doctypePending.forceQuirks = true;
            t.emitDoctypePending();
            t.transition(Data);
        }
    },
    BeforeDoctypeName { // from class: org.jsoup.parser.TokeniserState.52
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            if (r.matchesLetter()) {
                t.createDoctypePending();
                t.transition(DoctypeName);
            }
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.createDoctypePending();
                    t.doctypePending.name.append((char) 65533);
                    t.transition(DoctypeName);
                    break;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    break;
                case 65535:
                    t.eofError(this);
                    t.createDoctypePending();
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                default:
                    t.createDoctypePending();
                    t.doctypePending.name.append(c);
                    t.transition(DoctypeName);
                    break;
            }
        }
    },
    DoctypeName { // from class: org.jsoup.parser.TokeniserState.53
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            if (r.matchesLetter()) {
                String name = r.consumeLetterSequence();
                t.doctypePending.name.append(name);
            }
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.doctypePending.name.append((char) 65533);
                    break;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    t.transition(AfterDoctypeName);
                    break;
                case '>':
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                default:
                    t.doctypePending.name.append(c);
                    break;
            }
        }
    },
    AfterDoctypeName { // from class: org.jsoup.parser.TokeniserState.54
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) {
            if (r.isEmpty()) {
                t.eofError(this);
                t.doctypePending.forceQuirks = true;
                t.emitDoctypePending();
                t.transition(Data);
                return;
            }
            if (r.matchesAny('\t', '\n', '\r', '\f', ' ')) {
                r.advance();
                return;
            }
            if (r.matches('>')) {
                t.emitDoctypePending();
                t.advanceTransition(Data);
                return;
            }
            if (r.matchConsumeIgnoreCase(DocumentType.PUBLIC_KEY)) {
                t.doctypePending.pubSysKey = DocumentType.PUBLIC_KEY;
                t.transition(AfterDoctypePublicKeyword);
            } else if (r.matchConsumeIgnoreCase(DocumentType.SYSTEM_KEY)) {
                t.doctypePending.pubSysKey = DocumentType.SYSTEM_KEY;
                t.transition(AfterDoctypeSystemKeyword);
            } else {
                t.error(this);
                t.doctypePending.forceQuirks = true;
                t.advanceTransition(BogusDoctype);
            }
        }
    },
    AfterDoctypePublicKeyword { // from class: org.jsoup.parser.TokeniserState.55
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    t.transition(BeforeDoctypePublicIdentifier);
                    break;
                case '\"':
                    t.error(this);
                    t.transition(DoctypePublicIdentifier_doubleQuoted);
                    break;
                case '\'':
                    t.error(this);
                    t.transition(DoctypePublicIdentifier_singleQuoted);
                    break;
                case '>':
                    t.error(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                default:
                    t.error(this);
                    t.doctypePending.forceQuirks = true;
                    t.transition(BogusDoctype);
                    break;
            }
        }
    },
    BeforeDoctypePublicIdentifier { // from class: org.jsoup.parser.TokeniserState.56
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    break;
                case '\"':
                    t.transition(DoctypePublicIdentifier_doubleQuoted);
                    break;
                case '\'':
                    t.transition(DoctypePublicIdentifier_singleQuoted);
                    break;
                case '>':
                    t.error(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                default:
                    t.error(this);
                    t.doctypePending.forceQuirks = true;
                    t.transition(BogusDoctype);
                    break;
            }
        }
    },
    DoctypePublicIdentifier_doubleQuoted { // from class: org.jsoup.parser.TokeniserState.57
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.doctypePending.publicIdentifier.append((char) 65533);
                    break;
                case '\"':
                    t.transition(AfterDoctypePublicIdentifier);
                    break;
                case '>':
                    t.error(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                default:
                    t.doctypePending.publicIdentifier.append(c);
                    break;
            }
        }
    },
    DoctypePublicIdentifier_singleQuoted { // from class: org.jsoup.parser.TokeniserState.58
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.doctypePending.publicIdentifier.append((char) 65533);
                    break;
                case '\'':
                    t.transition(AfterDoctypePublicIdentifier);
                    break;
                case '>':
                    t.error(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                default:
                    t.doctypePending.publicIdentifier.append(c);
                    break;
            }
        }
    },
    AfterDoctypePublicIdentifier { // from class: org.jsoup.parser.TokeniserState.59
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    t.transition(BetweenDoctypePublicAndSystemIdentifiers);
                    break;
                case '\"':
                    t.error(this);
                    t.transition(DoctypeSystemIdentifier_doubleQuoted);
                    break;
                case '\'':
                    t.error(this);
                    t.transition(DoctypeSystemIdentifier_singleQuoted);
                    break;
                case '>':
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                default:
                    t.error(this);
                    t.doctypePending.forceQuirks = true;
                    t.transition(BogusDoctype);
                    break;
            }
        }
    },
    BetweenDoctypePublicAndSystemIdentifiers { // from class: org.jsoup.parser.TokeniserState.60
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    break;
                case '\"':
                    t.error(this);
                    t.transition(DoctypeSystemIdentifier_doubleQuoted);
                    break;
                case '\'':
                    t.error(this);
                    t.transition(DoctypeSystemIdentifier_singleQuoted);
                    break;
                case '>':
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                default:
                    t.error(this);
                    t.doctypePending.forceQuirks = true;
                    t.transition(BogusDoctype);
                    break;
            }
        }
    },
    AfterDoctypeSystemKeyword { // from class: org.jsoup.parser.TokeniserState.61
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    t.transition(BeforeDoctypeSystemIdentifier);
                    break;
                case '\"':
                    t.error(this);
                    t.transition(DoctypeSystemIdentifier_doubleQuoted);
                    break;
                case '\'':
                    t.error(this);
                    t.transition(DoctypeSystemIdentifier_singleQuoted);
                    break;
                case '>':
                    t.error(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                default:
                    t.error(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    break;
            }
        }
    },
    BeforeDoctypeSystemIdentifier { // from class: org.jsoup.parser.TokeniserState.62
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    break;
                case '\"':
                    t.transition(DoctypeSystemIdentifier_doubleQuoted);
                    break;
                case '\'':
                    t.transition(DoctypeSystemIdentifier_singleQuoted);
                    break;
                case '>':
                    t.error(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                default:
                    t.error(this);
                    t.doctypePending.forceQuirks = true;
                    t.transition(BogusDoctype);
                    break;
            }
        }
    },
    DoctypeSystemIdentifier_doubleQuoted { // from class: org.jsoup.parser.TokeniserState.63
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.doctypePending.systemIdentifier.append((char) 65533);
                    break;
                case '\"':
                    t.transition(AfterDoctypeSystemIdentifier);
                    break;
                case '>':
                    t.error(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                default:
                    t.doctypePending.systemIdentifier.append(c);
                    break;
            }
        }
    },
    DoctypeSystemIdentifier_singleQuoted { // from class: org.jsoup.parser.TokeniserState.64
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case 0:
                    t.error(this);
                    t.doctypePending.systemIdentifier.append((char) 65533);
                    break;
                case '\'':
                    t.transition(AfterDoctypeSystemIdentifier);
                    break;
                case '>':
                    t.error(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                default:
                    t.doctypePending.systemIdentifier.append(c);
                    break;
            }
        }
    },
    AfterDoctypeSystemIdentifier { // from class: org.jsoup.parser.TokeniserState.65
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    break;
                case '>':
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.eofError(this);
                    t.doctypePending.forceQuirks = true;
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                default:
                    t.error(this);
                    t.transition(BogusDoctype);
                    break;
            }
        }
    },
    BogusDoctype { // from class: org.jsoup.parser.TokeniserState.66
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            char c = r.consume();
            switch (c) {
                case '>':
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
                case 65535:
                    t.emitDoctypePending();
                    t.transition(Data);
                    break;
            }
        }
    },
    CdataSection { // from class: org.jsoup.parser.TokeniserState.67
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser t, CharacterReader r) throws IOException {
            String data = r.consumeTo("]]>");
            t.dataBuffer.append(data);
            if (r.matchConsume("]]>") || r.isEmpty()) {
                t.emit(new Token.CData(t.dataBuffer.toString()));
                t.transition(Data);
            }
        }
    };

    static final char nullChar = 0;
    private static final char replacementChar = 65533;
    private static final char eof = 65535;
    static final char[] attributeNameCharsSorted = {0, '\t', '\n', '\f', '\r', ' ', '\"', '\'', '/', '<', '=', '>'};
    static final char[] attributeValueUnquoted = {0, '\t', '\n', '\f', '\r', ' ', '\"', '&', '\'', '<', '=', '>', '`'};
    private static final String replacementStr = String.valueOf((char) 65533);

    abstract void read(Tokeniser tokeniser, CharacterReader characterReader);

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleDataEndTag(Tokeniser t, CharacterReader r, TokeniserState elseTransition) throws IOException {
        if (r.matchesLetter()) {
            String name = r.consumeLetterSequence();
            t.tagPending.appendTagName(name);
            t.dataBuffer.append(name);
            return;
        }
        boolean needsExitTransition = false;
        if (t.isAppropriateEndTagToken() && !r.isEmpty()) {
            char c = r.consume();
            switch (c) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    t.transition(BeforeAttributeName);
                    break;
                case '/':
                    t.transition(SelfClosingStartTag);
                    break;
                case '>':
                    t.emitTagPending();
                    t.transition(Data);
                    break;
                default:
                    t.dataBuffer.append(c);
                    needsExitTransition = true;
                    break;
            }
        } else {
            needsExitTransition = true;
        }
        if (needsExitTransition) {
            t.emit("</");
            t.emit(t.dataBuffer);
            t.transition(elseTransition);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void readRawData(Tokeniser t, CharacterReader r, TokeniserState current, TokeniserState advance) {
        switch (r.current()) {
            case 0:
                t.error(current);
                r.advance();
                t.emit((char) 65533);
                break;
            case '<':
                t.advanceTransition(advance);
                break;
            case 65535:
                t.emit(new Token.EOF());
                break;
            default:
                String data = r.consumeRawData();
                t.emit(data);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void readCharRef(Tokeniser t, TokeniserState advance) {
        int[] c = t.consumeCharacterReference(null, false);
        if (c == null) {
            t.emit('&');
        } else {
            t.emit(c);
        }
        t.transition(advance);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void readEndTag(Tokeniser t, CharacterReader r, TokeniserState a, TokeniserState b) {
        if (r.matchesLetter()) {
            t.createTagPending(false);
            t.transition(a);
        } else {
            t.emit("</");
            t.transition(b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleDataDoubleEscapeTag(Tokeniser t, CharacterReader r, TokeniserState primary, TokeniserState fallback) throws IOException {
        if (r.matchesLetter()) {
            String name = r.consumeLetterSequence();
            t.dataBuffer.append(name);
            t.emit(name);
        }
        char c = r.consume();
        switch (c) {
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case '/':
            case '>':
                if (t.dataBuffer.toString().equals("script")) {
                    t.transition(primary);
                } else {
                    t.transition(fallback);
                }
                t.emit(c);
                break;
            default:
                r.unconsume();
                t.transition(fallback);
                break;
        }
    }
}
