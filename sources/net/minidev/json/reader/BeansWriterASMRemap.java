package net.minidev.json.reader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.minidev.asm.Accessor;
import net.minidev.asm.BeansAccess;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONStyle;
import net.minidev.json.JSONUtil;

/* loaded from: reader.jar:BOOT-INF/lib/json-smart-2.4.7.jar:net/minidev/json/reader/BeansWriterASMRemap.class */
public class BeansWriterASMRemap implements JsonWriterI<Object> {
    private Map<String, String> rename = new HashMap();

    public void renameField(String source, String dest) {
        this.rename.put(source, dest);
    }

    private String rename(String key) {
        String k2 = this.rename.get(key);
        if (k2 != null) {
            return k2;
        }
        return key;
    }

    @Override // net.minidev.json.reader.JsonWriterI
    public <E> void writeJSONString(E value, Appendable out, JSONStyle compression) throws IOException {
        try {
            Class<?> cls = value.getClass();
            boolean needSep = false;
            BeansAccess fields = BeansAccess.get(cls, JSONUtil.JSON_SMART_FIELD_FILTER);
            out.append('{');
            for (Accessor field : fields.getAccessors()) {
                Object v = fields.get((BeansAccess) value, field.getIndex());
                if (v != null || !compression.ignoreNull()) {
                    if (needSep) {
                        out.append(',');
                    } else {
                        needSep = true;
                    }
                    String key = field.getName();
                    JSONObject.writeJSONKV(rename(key), v, out, compression);
                }
            }
            out.append('}');
        } catch (IOException e) {
            throw e;
        }
    }
}
