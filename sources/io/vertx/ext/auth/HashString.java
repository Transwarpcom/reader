package io.vertx.ext.auth;

import java.util.HashMap;
import java.util.Map;
import org.springframework.util.ClassUtils;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/HashString.class */
public final class HashString {
    private String id;
    private Map<String, String> params;
    private String salt;
    private String hash;

    public HashString(String id, Map<String, String> params, String salt) {
        this.id = id;
        this.params = params;
        this.salt = salt;
    }

    public HashString(String encoded) {
        if (encoded.length() > 1 && encoded.charAt(0) != '$') {
            encoded = encoded.replaceAll("\\{", "\\$\\{").replaceAll("\\}", "\\}\\$");
            if (encoded.length() > 1 && encoded.charAt(0) != '$') {
                encoded = ClassUtils.CGLIB_CLASS_SEPARATOR + encoded;
            }
        }
        String[] parts = encoded.split("\\$");
        if (parts.length < 2) {
            throw new IllegalStateException("Not enough segments: " + encoded);
        }
        switch (parts.length) {
            case 2:
                this.id = parts[1];
                break;
            case 3:
                break;
            case 4:
                this.id = parts[1];
                this.salt = parts[2];
                this.hash = parts[3];
                return;
            case 5:
            default:
                this.id = parts[1];
                this.params = new HashMap();
                for (String kv : parts[2].split(",")) {
                    int eq = kv.indexOf(61);
                    if (eq > 0) {
                        this.params.put(kv.substring(0, eq), kv.substring(eq + 1));
                    }
                }
                this.salt = parts[3];
                this.hash = parts[4];
                return;
        }
        this.id = parts[1];
        this.hash = parts[2];
    }

    public String id() {
        return this.id;
    }

    public String param(String param) {
        if (this.params == null) {
            return null;
        }
        return this.params.get(param);
    }

    public Map<String, String> params() {
        return this.params;
    }

    public String salt() {
        return this.salt;
    }

    public String hash() {
        return this.hash;
    }

    public static String encode(HashingAlgorithm algorithm, Map<String, String> params, String salt, String hash) {
        StringBuilder sb = new StringBuilder();
        if (algorithm.needsSeparator()) {
            sb.append('$');
        }
        sb.append(algorithm.id());
        if (params != null) {
            if (algorithm.needsSeparator()) {
                sb.append('$');
            }
            boolean notEmpty = false;
            for (String key : algorithm.params()) {
                String value = params.get(key);
                if (value != null) {
                    if (notEmpty) {
                        sb.append(',');
                    }
                    sb.append(key);
                    sb.append('=');
                    sb.append(params.get(key));
                    notEmpty = true;
                }
            }
        }
        if (salt != null) {
            if (algorithm.needsSeparator()) {
                sb.append('$');
            }
            sb.append(salt);
        }
        if (hash != null) {
            if (algorithm.needsSeparator()) {
                sb.append('$');
            }
            sb.append(hash);
        }
        return sb.toString();
    }

    public String toString() {
        return "id=" + id() + ",params=" + params() + ",salt=" + salt() + ",hash=" + hash();
    }
}
