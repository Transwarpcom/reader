package me.ag2s.epublib.epub;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: reader.jar:BOOT-INF/classes/me/ag2s/epublib/epub/EpubProcessorSupport.class */
public class EpubProcessorSupport {
    private static final String TAG = EpubProcessorSupport.class.getName();
    protected static DocumentBuilderFactory documentBuilderFactory;

    static {
        init();
    }

    /* loaded from: reader.jar:BOOT-INF/classes/me/ag2s/epublib/epub/EpubProcessorSupport$EntityResolverImpl.class */
    static class EntityResolverImpl implements EntityResolver {
        private String previousLocation;

        EntityResolverImpl() {
        }

        @Override // org.xml.sax.EntityResolver
        public InputSource resolveEntity(String publicId, String systemId) throws IOException {
            String resourcePath;
            if (systemId.startsWith("http:")) {
                URL url = new URL(systemId);
                resourcePath = "dtd/" + url.getHost() + url.getPath();
                this.previousLocation = resourcePath.substring(0, resourcePath.lastIndexOf(47));
            } else {
                resourcePath = this.previousLocation + systemId.substring(systemId.lastIndexOf(47));
            }
            if (((ClassLoader) Objects.requireNonNull(getClass().getClassLoader())).getResource(resourcePath) == null) {
                throw new RuntimeException("remote resource is not cached : [" + systemId + "] cannot continue");
            }
            InputStream in = ((ClassLoader) Objects.requireNonNull(EpubProcessorSupport.class.getClassLoader())).getResourceAsStream(resourcePath);
            return new InputSource(in);
        }
    }

    private static void init() {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setValidating(false);
    }

    public static XmlSerializer createXmlSerializer(OutputStream out) throws UnsupportedEncodingException {
        return createXmlSerializer(new OutputStreamWriter(out, "UTF-8"));
    }

    public static XmlSerializer createXmlSerializer(Writer out) {
        XmlSerializer result = null;
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setValidating(true);
            result = factory.newSerializer();
            result.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            result.setOutput(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static EntityResolver getEntityResolver() {
        return new EntityResolverImpl();
    }

    public DocumentBuilderFactory getDocumentBuilderFactory() {
        return documentBuilderFactory;
    }

    public static DocumentBuilder createDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilder result = null;
        try {
            result = documentBuilderFactory.newDocumentBuilder();
            result.setEntityResolver(getEntityResolver());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return result;
    }
}
