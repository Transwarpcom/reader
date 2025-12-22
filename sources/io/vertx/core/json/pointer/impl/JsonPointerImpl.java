package io.vertx.core.json.pointer.impl;

import io.vertx.core.json.pointer.JsonPointer;
import io.vertx.core.json.pointer.JsonPointerIterator;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/json/pointer/impl/JsonPointerImpl.class */
public class JsonPointerImpl implements JsonPointer {
    public static final Pattern VALID_POINTER_PATTERN = Pattern.compile("^(/(([^/~])|(~[01]))*)*$");
    URI startingUri;
    List<String> decodedTokens;

    public JsonPointerImpl(URI uri) {
        this.startingUri = removeFragment(uri);
        this.decodedTokens = parse(uri.getFragment());
    }

    public JsonPointerImpl(String pointer) {
        this.startingUri = URI.create("#");
        this.decodedTokens = parse(pointer);
    }

    public JsonPointerImpl() {
        this.startingUri = URI.create("#");
        this.decodedTokens = parse(null);
    }

    protected JsonPointerImpl(URI startingUri, List<String> decodedTokens) {
        this.startingUri = startingUri;
        this.decodedTokens = new ArrayList(decodedTokens);
    }

    private ArrayList<String> parse(String pointer) {
        if (pointer == null || "".equals(pointer)) {
            return new ArrayList<>();
        }
        if (VALID_POINTER_PATTERN.matcher(pointer).matches()) {
            return (ArrayList) Arrays.stream(pointer.split("\\/", -1)).skip(1L).map(this::unescape).collect(Collectors.toCollection(ArrayList::new));
        }
        throw new IllegalArgumentException("The provided pointer is not a valid JSON Pointer");
    }

    private String escape(String path) {
        return path.replace("~", "~0").replace("/", "~1");
    }

    private String unescape(String path) {
        return path.replace("~1", "/").replace("~0", "~");
    }

    @Override // io.vertx.core.json.pointer.JsonPointer
    public boolean isRootPointer() {
        return this.decodedTokens.size() == 0;
    }

    @Override // io.vertx.core.json.pointer.JsonPointer
    public boolean isLocalPointer() {
        return this.startingUri == null || this.startingUri.getSchemeSpecificPart() == null || this.startingUri.getSchemeSpecificPart().isEmpty();
    }

    @Override // io.vertx.core.json.pointer.JsonPointer
    public boolean isParent(JsonPointer c) {
        JsonPointerImpl child = (JsonPointerImpl) c;
        return child != null && ((child.getURIWithoutFragment() == null && getURIWithoutFragment() == null) || child.getURIWithoutFragment().equals(getURIWithoutFragment())) && this.decodedTokens.size() < child.decodedTokens.size() && ((Boolean) IntStream.range(0, this.decodedTokens.size()).mapToObj(i -> {
            return Boolean.valueOf(this.decodedTokens.get(i).equals(child.decodedTokens.get(i)));
        }).reduce((v0, v1) -> {
            return Boolean.logicalAnd(v0, v1);
        }).orElse(true)).booleanValue();
    }

    @Override // io.vertx.core.json.pointer.JsonPointer
    public String toString() {
        if (isRootPointer()) {
            return "";
        }
        return "/" + String.join("/", (Iterable<? extends CharSequence>) this.decodedTokens.stream().map(this::escape).collect(Collectors.toList()));
    }

    @Override // io.vertx.core.json.pointer.JsonPointer
    public URI toURI() {
        if (isRootPointer()) {
            return replaceFragment(this.startingUri, "");
        }
        return replaceFragment(this.startingUri, "/" + String.join("/", (Iterable<? extends CharSequence>) this.decodedTokens.stream().map(this::escape).collect(Collectors.toList())));
    }

    @Override // io.vertx.core.json.pointer.JsonPointer
    public URI getURIWithoutFragment() {
        return this.startingUri;
    }

    @Override // io.vertx.core.json.pointer.JsonPointer
    public JsonPointer append(String path) {
        this.decodedTokens.add(path);
        return this;
    }

    @Override // io.vertx.core.json.pointer.JsonPointer
    public JsonPointer append(int i) {
        return append(Integer.toString(i));
    }

    @Override // io.vertx.core.json.pointer.JsonPointer
    public JsonPointer append(List<String> paths) {
        this.decodedTokens.addAll(paths);
        return this;
    }

    @Override // io.vertx.core.json.pointer.JsonPointer
    public JsonPointer append(JsonPointer pointer) {
        this.decodedTokens.addAll(((JsonPointerImpl) pointer).decodedTokens);
        return this;
    }

    @Override // io.vertx.core.json.pointer.JsonPointer
    public JsonPointer parent() {
        if (!isRootPointer()) {
            this.decodedTokens.remove(this.decodedTokens.size() - 1);
        }
        return this;
    }

    @Override // io.vertx.core.json.pointer.JsonPointer
    public JsonPointer copy() {
        return new JsonPointerImpl(this.startingUri, this.decodedTokens);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JsonPointerImpl that = (JsonPointerImpl) o;
        return Objects.equals(this.startingUri, that.startingUri) && Objects.equals(this.decodedTokens, that.decodedTokens);
    }

    public int hashCode() {
        return Objects.hash(this.startingUri, this.decodedTokens);
    }

    @Override // io.vertx.core.json.pointer.JsonPointer
    public Object queryOrDefault(Object value, JsonPointerIterator iterator, Object defaultValue) {
        if (isRootPointer()) {
            return iterator.isNull(value) ? defaultValue : value;
        }
        Object value2 = walkTillLastElement(value, iterator, false, null);
        String lastKey = this.decodedTokens.get(this.decodedTokens.size() - 1);
        if (iterator.isObject(value2)) {
            Object finalValue = iterator.getObjectParameter(value2, lastKey, false);
            return !iterator.isNull(finalValue) ? finalValue : defaultValue;
        }
        if (iterator.isArray(value2) && !"-".equals(lastKey)) {
            try {
                Object finalValue2 = iterator.getArrayElement(value2, Integer.parseInt(lastKey));
                return !iterator.isNull(finalValue2) ? finalValue2 : defaultValue;
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    @Override // io.vertx.core.json.pointer.JsonPointer
    public List<Object> tracedQuery(Object objectToQuery, JsonPointerIterator iterator) {
        List<Object> list = new ArrayList<>();
        if (isRootPointer() && !iterator.isNull(objectToQuery)) {
            list.add(objectToQuery);
        } else {
            list.getClass();
            Object lastValue = walkTillLastElement(objectToQuery, iterator, false, list::add);
            if (!iterator.isNull(lastValue)) {
                list.add(lastValue);
            }
            String lastKey = this.decodedTokens.get(this.decodedTokens.size() - 1);
            if (iterator.isObject(lastValue)) {
                lastValue = iterator.getObjectParameter(lastValue, lastKey, false);
            } else if (iterator.isArray(lastValue) && !"-".equals(lastKey)) {
                try {
                    lastValue = iterator.getArrayElement(lastValue, Integer.parseInt(lastKey));
                } catch (NumberFormatException e) {
                }
            }
            if (!iterator.isNull(lastValue)) {
                list.add(lastValue);
            }
        }
        return list;
    }

    @Override // io.vertx.core.json.pointer.JsonPointer
    public Object write(Object valueToWrite, JsonPointerIterator iterator, Object newElement, boolean createOnMissing) {
        if (isRootPointer()) {
            if (iterator.isNull(valueToWrite)) {
                return null;
            }
            return newElement;
        }
        Object walkedValue = walkTillLastElement(valueToWrite, iterator, createOnMissing, null);
        if (writeLastElement(walkedValue, iterator, newElement)) {
            return valueToWrite;
        }
        return null;
    }

    private Object walkTillLastElement(Object value, JsonPointerIterator iterator, boolean createOnMissing, Consumer<Object> onNewValue) {
        for (int i = 0; i < this.decodedTokens.size() - 1; i++) {
            String k = this.decodedTokens.get(i);
            if (i != 0 || !"".equals(k)) {
                if (iterator.isObject(value)) {
                    if (onNewValue != null) {
                        onNewValue.accept(value);
                    }
                    value = iterator.getObjectParameter(value, k, createOnMissing);
                } else if (iterator.isArray(value)) {
                    if (onNewValue != null) {
                        onNewValue.accept(value);
                    }
                    try {
                        value = iterator.getArrayElement(value, Integer.parseInt(k));
                        if (iterator.isNull(value) && createOnMissing) {
                            value = iterator.getObjectParameter(value, k, true);
                        }
                    } catch (NumberFormatException e) {
                        value = null;
                    }
                } else {
                    return null;
                }
            }
        }
        return value;
    }

    private boolean writeLastElement(Object valueToWrite, JsonPointerIterator iterator, Object newElement) {
        String lastKey = this.decodedTokens.get(this.decodedTokens.size() - 1);
        if (iterator.isObject(valueToWrite)) {
            return iterator.writeObjectParameter(valueToWrite, lastKey, newElement);
        }
        if (iterator.isArray(valueToWrite)) {
            if ("-".equals(lastKey)) {
                return iterator.appendArrayElement(valueToWrite, newElement);
            }
            try {
                return iterator.writeArrayElement(valueToWrite, Integer.parseInt(lastKey), newElement);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    private URI removeFragment(URI oldURI) {
        return replaceFragment(oldURI, null);
    }

    private URI replaceFragment(URI oldURI, String fragment) {
        try {
            if (oldURI != null) {
                return new URI(oldURI.getScheme(), oldURI.getSchemeSpecificPart(), fragment);
            }
            return new URI(null, null, fragment);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
