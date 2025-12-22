package org.jsoup.nodes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.helper.Validate;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/FormElement.class */
public class FormElement extends Element {
    private final Elements elements;

    public FormElement(Tag tag, String baseUri, Attributes attributes) {
        super(tag, baseUri, attributes);
        this.elements = new Elements();
    }

    public Elements elements() {
        return this.elements;
    }

    public FormElement addElement(Element element) {
        this.elements.add(element);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    protected void removeChild(Node out) {
        super.removeChild(out);
        this.elements.remove(out);
    }

    public Connection submit() {
        String action = hasAttr("action") ? absUrl("action") : baseUri();
        Validate.notEmpty(action, "Could not determine a form action URL for submit. Ensure you set a base URI when parsing.");
        Connection.Method method = attr("method").equalsIgnoreCase("POST") ? Connection.Method.POST : Connection.Method.GET;
        Document owner = ownerDocument();
        Connection connection = owner != null ? owner.connection().newRequest() : Jsoup.newSession();
        return connection.url(action).data(formData()).method(method);
    }

    public List<Connection.KeyVal> formData() {
        Element option;
        ArrayList<Connection.KeyVal> data = new ArrayList<>();
        Iterator<Element> it = this.elements.iterator();
        while (it.hasNext()) {
            Element el = it.next();
            if (el.tag().isFormSubmittable() && !el.hasAttr("disabled")) {
                String name = el.attr("name");
                if (name.length() != 0) {
                    String type = el.attr("type");
                    if (!type.equalsIgnoreCase("button")) {
                        if ("select".equals(el.normalName())) {
                            Elements options = el.select("option[selected]");
                            boolean set = false;
                            Iterator<Element> it2 = options.iterator();
                            while (it2.hasNext()) {
                                data.add(HttpConnection.KeyVal.create(name, it2.next().val()));
                                set = true;
                            }
                            if (!set && (option = el.selectFirst("option")) != null) {
                                data.add(HttpConnection.KeyVal.create(name, option.val()));
                            }
                        } else if ("checkbox".equalsIgnoreCase(type) || "radio".equalsIgnoreCase(type)) {
                            if (el.hasAttr("checked")) {
                                String val = el.val().length() > 0 ? el.val() : "on";
                                data.add(HttpConnection.KeyVal.create(name, val));
                            }
                        } else {
                            data.add(HttpConnection.KeyVal.create(name, el.val()));
                        }
                    }
                }
            }
        }
        return data;
    }

    @Override // org.jsoup.nodes.Element, org.jsoup.nodes.Node
    /* renamed from: clone */
    public FormElement mo5203clone() {
        return (FormElement) super.mo5203clone();
    }
}
