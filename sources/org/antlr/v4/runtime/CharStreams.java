package org.antlr.v4.runtime;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.antlr.v4.runtime.CodePointBuffer;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/CharStreams.class */
public final class CharStreams {
    private static final int DEFAULT_BUFFER_SIZE = 4096;

    private CharStreams() {
    }

    public static CharStream fromPath(Path path) throws IOException {
        return fromPath(path, StandardCharsets.UTF_8);
    }

    public static CharStream fromPath(Path path, Charset charset) throws IOException {
        long size = Files.size(path);
        ReadableByteChannel channel = Files.newByteChannel(path, new OpenOption[0]);
        Throwable th = null;
        try {
            try {
                CodePointCharStream codePointCharStreamFromChannel = fromChannel(channel, charset, 4096, CodingErrorAction.REPLACE, path.toString(), size);
                if (channel != null) {
                    if (0 != 0) {
                        try {
                            channel.close();
                        } catch (Throwable x2) {
                            th.addSuppressed(x2);
                        }
                    } else {
                        channel.close();
                    }
                }
                return codePointCharStreamFromChannel;
            } catch (Throwable th2) {
                if (channel != null) {
                    if (th != null) {
                        try {
                            channel.close();
                        } catch (Throwable x22) {
                            th.addSuppressed(x22);
                        }
                    } else {
                        channel.close();
                    }
                }
                throw th2;
            }
        } finally {
        }
    }

    public static CharStream fromFileName(String fileName) throws IOException {
        return fromPath(Paths.get(fileName, new String[0]), StandardCharsets.UTF_8);
    }

    public static CharStream fromFileName(String fileName, Charset charset) throws IOException {
        return fromPath(Paths.get(fileName, new String[0]), charset);
    }

    public static CharStream fromStream(InputStream is) throws IOException {
        return fromStream(is, StandardCharsets.UTF_8);
    }

    public static CharStream fromStream(InputStream is, Charset charset) throws IOException {
        return fromStream(is, charset, -1L);
    }

    public static CharStream fromStream(InputStream is, Charset charset, long inputSize) throws IOException {
        ReadableByteChannel channel = Channels.newChannel(is);
        Throwable th = null;
        try {
            try {
                CodePointCharStream codePointCharStreamFromChannel = fromChannel(channel, charset, 4096, CodingErrorAction.REPLACE, IntStream.UNKNOWN_SOURCE_NAME, inputSize);
                if (channel != null) {
                    if (0 != 0) {
                        try {
                            channel.close();
                        } catch (Throwable x2) {
                            th.addSuppressed(x2);
                        }
                    } else {
                        channel.close();
                    }
                }
                return codePointCharStreamFromChannel;
            } finally {
            }
        } catch (Throwable th2) {
            if (channel != null) {
                if (th != null) {
                    try {
                        channel.close();
                    } catch (Throwable x22) {
                        th.addSuppressed(x22);
                    }
                } else {
                    channel.close();
                }
            }
            throw th2;
        }
    }

    public static CharStream fromChannel(ReadableByteChannel channel) throws IOException {
        return fromChannel(channel, StandardCharsets.UTF_8);
    }

    public static CharStream fromChannel(ReadableByteChannel channel, Charset charset) throws IOException {
        return fromChannel(channel, 4096, CodingErrorAction.REPLACE, IntStream.UNKNOWN_SOURCE_NAME);
    }

    public static CodePointCharStream fromReader(Reader r) throws IOException {
        return fromReader(r, IntStream.UNKNOWN_SOURCE_NAME);
    }

    public static CodePointCharStream fromReader(Reader r, String sourceName) throws IOException {
        try {
            CodePointBuffer.Builder codePointBufferBuilder = CodePointBuffer.builder(4096);
            CharBuffer charBuffer = CharBuffer.allocate(4096);
            while (r.read(charBuffer) != -1) {
                charBuffer.flip();
                codePointBufferBuilder.append(charBuffer);
                charBuffer.compact();
            }
            CodePointCharStream codePointCharStreamFromBuffer = CodePointCharStream.fromBuffer(codePointBufferBuilder.build(), sourceName);
            r.close();
            return codePointCharStreamFromBuffer;
        } catch (Throwable th) {
            r.close();
            throw th;
        }
    }

    public static CodePointCharStream fromString(String s) {
        return fromString(s, IntStream.UNKNOWN_SOURCE_NAME);
    }

    public static CodePointCharStream fromString(String s, String sourceName) {
        CodePointBuffer.Builder codePointBufferBuilder = CodePointBuffer.builder(s.length());
        CharBuffer cb = CharBuffer.allocate(s.length());
        cb.put(s);
        cb.flip();
        codePointBufferBuilder.append(cb);
        return CodePointCharStream.fromBuffer(codePointBufferBuilder.build(), sourceName);
    }

    public static CodePointCharStream fromChannel(ReadableByteChannel channel, int bufferSize, CodingErrorAction decodingErrorAction, String sourceName) throws IOException {
        return fromChannel(channel, StandardCharsets.UTF_8, bufferSize, decodingErrorAction, sourceName, -1L);
    }

    public static CodePointCharStream fromChannel(ReadableByteChannel channel, Charset charset, int bufferSize, CodingErrorAction decodingErrorAction, String sourceName, long inputSize) throws IOException {
        try {
            ByteBuffer utf8BytesIn = ByteBuffer.allocate(bufferSize);
            CharBuffer utf16CodeUnitsOut = CharBuffer.allocate(bufferSize);
            if (inputSize == -1) {
                inputSize = bufferSize;
            } else if (inputSize > 2147483647L) {
                throw new IOException(String.format("inputSize %d larger than max %d", Long.valueOf(inputSize), Integer.MAX_VALUE));
            }
            CodePointBuffer.Builder codePointBufferBuilder = CodePointBuffer.builder((int) inputSize);
            CharsetDecoder decoder = charset.newDecoder().onMalformedInput(decodingErrorAction).onUnmappableCharacter(decodingErrorAction);
            boolean endOfInput = false;
            while (!endOfInput) {
                int bytesRead = channel.read(utf8BytesIn);
                endOfInput = bytesRead == -1;
                utf8BytesIn.flip();
                CoderResult result = decoder.decode(utf8BytesIn, utf16CodeUnitsOut, endOfInput);
                if (result.isError() && decodingErrorAction.equals(CodingErrorAction.REPORT)) {
                    result.throwException();
                }
                utf16CodeUnitsOut.flip();
                codePointBufferBuilder.append(utf16CodeUnitsOut);
                utf8BytesIn.compact();
                utf16CodeUnitsOut.compact();
            }
            CoderResult flushResult = decoder.flush(utf16CodeUnitsOut);
            if (flushResult.isError() && decodingErrorAction.equals(CodingErrorAction.REPORT)) {
                flushResult.throwException();
            }
            utf16CodeUnitsOut.flip();
            codePointBufferBuilder.append(utf16CodeUnitsOut);
            CodePointBuffer codePointBuffer = codePointBufferBuilder.build();
            CodePointCharStream codePointCharStreamFromBuffer = CodePointCharStream.fromBuffer(codePointBuffer, sourceName);
            channel.close();
            return codePointCharStreamFromBuffer;
        } catch (Throwable th) {
            channel.close();
            throw th;
        }
    }
}
