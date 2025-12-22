package org.jsoup.helper;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import javax.annotation.Nullable;
import me.ag2s.epublib.epub.NCXDocumentV3;
import org.apache.commons.lang3.CharEncoding;
import org.jsoup.UncheckedIOException;
import org.jsoup.internal.ConstrainableInputStream;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/helper/DataUtil.class */
public final class DataUtil {
    private static final int firstReadBufferSize = 5120;
    static final int bufferSize = 32768;
    static final int boundaryLength = 32;
    private static final Pattern charsetPattern = Pattern.compile("(?i)\\bcharset=\\s*(?:[\"'])?([^\\s,;\"']*)");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    static final String defaultCharsetName = UTF_8.name();
    private static final char[] mimeBoundaryChars = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private DataUtil() {
    }

    public static Document load(File in, @Nullable String charsetName, String baseUri) throws IOException {
        InputStream stream = new FileInputStream(in);
        String name = Normalizer.lowerCase(in.getName());
        if (name.endsWith(".gz") || name.endsWith(".z")) {
            boolean zipped = stream.read() == 31 && stream.read() == 139;
            stream.close();
            stream = zipped ? new GZIPInputStream(new FileInputStream(in)) : new FileInputStream(in);
        }
        return parseInputStream(stream, charsetName, baseUri, Parser.htmlParser());
    }

    public static Document load(InputStream in, String charsetName, String baseUri) throws IOException {
        return parseInputStream(in, charsetName, baseUri, Parser.htmlParser());
    }

    public static Document load(InputStream in, String charsetName, String baseUri, Parser parser) throws IOException {
        return parseInputStream(in, charsetName, baseUri, parser);
    }

    static void crossStreams(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[32768];
        while (true) {
            int len = in.read(buffer);
            if (len != -1) {
                out.write(buffer, 0, len);
            } else {
                return;
            }
        }
    }

    static Document parseInputStream(@Nullable InputStream input, @Nullable String charsetName, String baseUri, Parser parser) throws IOException {
        if (input == null) {
            return new Document(baseUri);
        }
        InputStream input2 = ConstrainableInputStream.wrap(input, 32768, 0);
        Document doc = null;
        input2.mark(32768);
        ByteBuffer firstBytes = readToByteBuffer(input2, 5119);
        boolean fullyRead = input2.read() == -1;
        input2.reset();
        BomCharset bomCharset = detectCharsetFromBom(firstBytes);
        if (bomCharset != null) {
            charsetName = bomCharset.charset;
        }
        if (charsetName == null) {
            try {
                CharBuffer defaultDecoded = UTF_8.decode(firstBytes);
                if (defaultDecoded.hasArray()) {
                    doc = parser.parseInput(new CharArrayReader(defaultDecoded.array(), defaultDecoded.arrayOffset(), defaultDecoded.limit()), baseUri);
                } else {
                    doc = parser.parseInput(defaultDecoded.toString(), baseUri);
                }
                Elements metaElements = doc.select("meta[http-equiv=content-type], meta[charset]");
                String foundCharset = null;
                Iterator<Element> it = metaElements.iterator();
                while (it.hasNext()) {
                    Element meta = it.next();
                    if (meta.hasAttr(NCXDocumentV3.XHTMLAttributes.http_equiv)) {
                        foundCharset = getCharsetFromContentType(meta.attr("content"));
                    }
                    if (foundCharset == null && meta.hasAttr("charset")) {
                        foundCharset = meta.attr("charset");
                    }
                    if (foundCharset != null) {
                        break;
                    }
                }
                if (foundCharset == null && doc.childNodeSize() > 0) {
                    Node first = doc.childNode(0);
                    XmlDeclaration decl = null;
                    if (first instanceof XmlDeclaration) {
                        decl = (XmlDeclaration) first;
                    } else if (first instanceof Comment) {
                        Comment comment = (Comment) first;
                        if (comment.isXmlDeclaration()) {
                            decl = comment.asXmlDeclaration();
                        }
                    }
                    if (decl != null && decl.name().equalsIgnoreCase("xml")) {
                        foundCharset = decl.attr("encoding");
                    }
                }
                String foundCharset2 = validateCharset(foundCharset);
                if (foundCharset2 != null && !foundCharset2.equalsIgnoreCase(defaultCharsetName)) {
                    charsetName = foundCharset2.trim().replaceAll("[\"']", "");
                    doc = null;
                } else if (!fullyRead) {
                    doc = null;
                }
            } catch (UncheckedIOException e) {
                throw e.ioException();
            }
        } else {
            Validate.notEmpty(charsetName, "Must set charset arg to character set of file to parse. Set to null to attempt to detect from HTML");
        }
        if (doc == null) {
            if (charsetName == null) {
                charsetName = defaultCharsetName;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(input2, charsetName), 32768);
            if (bomCharset != null && bomCharset.offset) {
                long skipped = reader.skip(1L);
                Validate.isTrue(skipped == 1);
            }
            try {
                doc = parser.parseInput(reader, baseUri);
                Charset charset = charsetName.equals(defaultCharsetName) ? UTF_8 : Charset.forName(charsetName);
                doc.outputSettings().charset(charset);
                if (!charset.canEncode()) {
                    doc.charset(UTF_8);
                }
            } catch (UncheckedIOException e2) {
                throw e2.ioException();
            }
        }
        input2.close();
        return doc;
    }

    public static ByteBuffer readToByteBuffer(InputStream inStream, int maxSize) throws IOException {
        Validate.isTrue(maxSize >= 0, "maxSize must be 0 (unlimited) or larger");
        ConstrainableInputStream input = ConstrainableInputStream.wrap(inStream, 32768, maxSize);
        return input.readToByteBuffer(maxSize);
    }

    static ByteBuffer emptyByteBuffer() {
        return ByteBuffer.allocate(0);
    }

    @Nullable
    static String getCharsetFromContentType(@Nullable String contentType) {
        if (contentType == null) {
            return null;
        }
        Matcher m = charsetPattern.matcher(contentType);
        if (m.find()) {
            String charset = m.group(1).trim();
            return validateCharset(charset.replace("charset=", ""));
        }
        return null;
    }

    @Nullable
    private static String validateCharset(@Nullable String cs) {
        if (cs == null || cs.length() == 0) {
            return null;
        }
        String cs2 = cs.trim().replaceAll("[\"']", "");
        try {
            if (Charset.isSupported(cs2)) {
                return cs2;
            }
            String cs3 = cs2.toUpperCase(Locale.ENGLISH);
            if (Charset.isSupported(cs3)) {
                return cs3;
            }
            return null;
        } catch (IllegalCharsetNameException e) {
            return null;
        }
    }

    static String mimeBoundary() {
        StringBuilder mime = StringUtil.borrowBuilder();
        Random rand = new Random();
        for (int i = 0; i < 32; i++) {
            mime.append(mimeBoundaryChars[rand.nextInt(mimeBoundaryChars.length)]);
        }
        return StringUtil.releaseBuilder(mime);
    }

    @Nullable
    private static BomCharset detectCharsetFromBom(ByteBuffer byteData) {
        byteData.mark();
        byte[] bom = new byte[4];
        if (byteData.remaining() >= bom.length) {
            byteData.get(bom);
            byteData.rewind();
        }
        if ((bom[0] == 0 && bom[1] == 0 && bom[2] == -2 && bom[3] == -1) || (bom[0] == -1 && bom[1] == -2 && bom[2] == 0 && bom[3] == 0)) {
            return new BomCharset("UTF-32", false);
        }
        if ((bom[0] == -2 && bom[1] == -1) || (bom[0] == -1 && bom[1] == -2)) {
            return new BomCharset(CharEncoding.UTF_16, false);
        }
        if (bom[0] == -17 && bom[1] == -69 && bom[2] == -65) {
            return new BomCharset("UTF-8", true);
        }
        return null;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/helper/DataUtil$BomCharset.class */
    private static class BomCharset {
        private final String charset;
        private final boolean offset;

        public BomCharset(String charset, boolean offset) {
            this.charset = charset;
            this.offset = offset;
        }
    }
}
