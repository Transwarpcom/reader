package org.jsoup.helper;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/helper/W3CDom.class */
public class W3CDom {
    protected DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    public W3CDom() {
        this.factory.setNamespaceAware(true);
    }

    public static Document convert(org.jsoup.nodes.Document in) {
        return new W3CDom().fromJsoup(in);
    }

    public static String asString(Document doc, @Nullable Map<String, String> properties) throws TransformerException, TransformerFactoryConfigurationError, IllegalArgumentException {
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            if (properties != null) {
                transformer.setOutputProperties(propertiesFromMap(properties));
            }
            if (doc.getDoctype() != null) {
                DocumentType doctype = doc.getDoctype();
                if (!StringUtil.isBlank(doctype.getPublicId())) {
                    transformer.setOutputProperty("doctype-public", doctype.getPublicId());
                }
                if (!StringUtil.isBlank(doctype.getSystemId())) {
                    transformer.setOutputProperty("doctype-system", doctype.getSystemId());
                } else if (doctype.getName().equalsIgnoreCase("html") && StringUtil.isBlank(doctype.getPublicId()) && StringUtil.isBlank(doctype.getSystemId())) {
                    transformer.setOutputProperty("doctype-system", "about:legacy-compat");
                }
            }
            transformer.transform(domSource, result);
            return writer.toString();
        } catch (TransformerException e) {
            throw new IllegalStateException(e);
        }
    }

    static Properties propertiesFromMap(Map<String, String> map) {
        Properties props = new Properties();
        props.putAll(map);
        return props;
    }

    public static HashMap<String, String> OutputHtml() {
        return methodMap("html");
    }

    public static HashMap<String, String> OutputXml() {
        return methodMap("xml");
    }

    private static HashMap<String, String> methodMap(String method) {
        HashMap<String, String> map = new HashMap<>();
        map.put("method", method);
        return map;
    }

    public Document fromJsoup(org.jsoup.nodes.Document in) throws ParserConfigurationException, DOMException {
        Validate.notNull(in);
        try {
            DocumentBuilder builder = this.factory.newDocumentBuilder();
            DOMImplementation impl = builder.getDOMImplementation();
            Document out = builder.newDocument();
            org.jsoup.nodes.DocumentType doctype = in.documentType();
            if (doctype != null) {
                DocumentType documentType = impl.createDocumentType(doctype.name(), doctype.publicId(), doctype.systemId());
                out.appendChild(documentType);
            }
            out.setXmlStandalone(true);
            convert(in, out);
            return out;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    public void convert(org.jsoup.nodes.Document in, Document out) {
        if (!StringUtil.isBlank(in.location())) {
            out.setDocumentURI(in.location());
        }
        Element rootEl = in.child(0);
        NodeTraversor.traverse(new W3CBuilder(out), rootEl);
    }

    public String asString(Document doc) {
        return asString(doc, null);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/helper/W3CDom$W3CBuilder.class */
    protected static class W3CBuilder implements NodeVisitor {
        private static final String xmlnsKey = "xmlns";
        private static final String xmlnsPrefix = "xmlns:";
        private final Document doc;
        private final Stack<HashMap<String, String>> namespacesStack = new Stack<>();
        private Node dest;
        private static final Pattern attrKeyReplace = Pattern.compile("[^-a-zA-Z0-9_:.]");
        private static final Pattern attrKeyValid = Pattern.compile("[a-zA-Z_:][-a-zA-Z0-9_:.]*");

        public W3CBuilder(Document doc) {
            this.doc = doc;
            this.namespacesStack.push(new HashMap<>());
            this.dest = doc;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0065 A[Catch: DOMException -> 0x0090, TryCatch #0 {DOMException -> 0x0090, blocks: (B:7:0x004b, B:9:0x0055, B:11:0x0072, B:10:0x0065), top: B:25:0x004b }] */
        @Override // org.jsoup.select.NodeVisitor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void head(org.jsoup.nodes.Node r6, int r7) throws org.w3c.dom.DOMException {
            /*
                Method dump skipped, instructions count: 317
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.helper.W3CDom.W3CBuilder.head(org.jsoup.nodes.Node, int):void");
        }

        @Override // org.jsoup.select.NodeVisitor
        public void tail(org.jsoup.nodes.Node source, int depth) {
            if ((source instanceof Element) && (this.dest.getParentNode() instanceof org.w3c.dom.Element)) {
                this.dest = this.dest.getParentNode();
            }
            this.namespacesStack.pop();
        }

        private void copyAttributes(org.jsoup.nodes.Node source, org.w3c.dom.Element el) throws DOMException {
            Iterator<Attribute> it = source.attributes().iterator();
            while (it.hasNext()) {
                Attribute attribute = it.next();
                String key = attrKeyReplace.matcher(attribute.getKey()).replaceAll("");
                if (attrKeyValid.matcher(key).matches()) {
                    el.setAttribute(key, attribute.getValue());
                }
            }
        }

        private String updateNamespaces(Element el) {
            String strSubstring;
            Attributes attributes = el.attributes();
            Iterator<Attribute> it = attributes.iterator();
            while (it.hasNext()) {
                Attribute attr = it.next();
                String key = attr.getKey();
                if (key.equals("xmlns")) {
                    strSubstring = "";
                } else if (key.startsWith(xmlnsPrefix)) {
                    strSubstring = key.substring(xmlnsPrefix.length());
                }
                String prefix = strSubstring;
                this.namespacesStack.peek().put(prefix, attr.getValue());
            }
            int pos = el.tagName().indexOf(":");
            return pos > 0 ? el.tagName().substring(0, pos) : "";
        }
    }
}
