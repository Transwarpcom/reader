package com.jayway.jsonpath.internal.function;

import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.internal.function.json.Append;
import com.jayway.jsonpath.internal.function.json.KeySetFunction;
import com.jayway.jsonpath.internal.function.numeric.Average;
import com.jayway.jsonpath.internal.function.numeric.Max;
import com.jayway.jsonpath.internal.function.numeric.Min;
import com.jayway.jsonpath.internal.function.numeric.StandardDeviation;
import com.jayway.jsonpath.internal.function.numeric.Sum;
import com.jayway.jsonpath.internal.function.text.Concatenate;
import com.jayway.jsonpath.internal.function.text.Length;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/function/PathFunctionFactory.class */
public class PathFunctionFactory {
    public static final Map<String, Class> FUNCTIONS;

    static {
        Map<String, Class> map = new HashMap<>();
        map.put("avg", Average.class);
        map.put("stddev", StandardDeviation.class);
        map.put("sum", Sum.class);
        map.put("min", Min.class);
        map.put("max", Max.class);
        map.put("concat", Concatenate.class);
        map.put(Length.TOKEN_NAME, Length.class);
        map.put("size", Length.class);
        map.put(RtspHeaders.Values.APPEND, Append.class);
        map.put("keys", KeySetFunction.class);
        FUNCTIONS = Collections.unmodifiableMap(map);
    }

    public static PathFunction newFunction(String name) throws InvalidPathException {
        Class functionClazz = FUNCTIONS.get(name);
        if (functionClazz == null) {
            throw new InvalidPathException("Function with name: " + name + " does not exist.");
        }
        try {
            return (PathFunction) functionClazz.newInstance();
        } catch (Exception e) {
            throw new InvalidPathException("Function of name: " + name + " cannot be created", e);
        }
    }
}
