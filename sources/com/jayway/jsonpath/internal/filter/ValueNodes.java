package com.jayway.jsonpath.internal.filter;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPathException;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.internal.Path;
import com.jayway.jsonpath.internal.Utils;
import com.jayway.jsonpath.internal.path.PathCompiler;
import com.jayway.jsonpath.internal.path.PredicateContextImpl;
import com.jayway.jsonpath.spi.json.JsonProvider;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/ValueNodes.class */
public interface ValueNodes {
    public static final NullNode NULL_NODE = new NullNode();
    public static final BooleanNode TRUE = new BooleanNode("true");
    public static final BooleanNode FALSE = new BooleanNode("false");
    public static final UndefinedNode UNDEFINED = new UndefinedNode();

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/ValueNodes$PatternNode.class */
    public static class PatternNode extends ValueNode {
        private final String pattern;
        private final Pattern compiledPattern;
        private final String flags;

        PatternNode(CharSequence charSequence) {
            String tmp = charSequence.toString();
            int begin = tmp.indexOf(47);
            int end = tmp.lastIndexOf(47);
            this.pattern = tmp.substring(begin + 1, end);
            int flagsIndex = end + 1;
            this.flags = tmp.length() > flagsIndex ? tmp.substring(flagsIndex) : "";
            this.compiledPattern = Pattern.compile(this.pattern, PatternFlag.parseFlags(this.flags.toCharArray()));
        }

        PatternNode(Pattern pattern) {
            this.pattern = pattern.pattern();
            this.compiledPattern = pattern;
            this.flags = PatternFlag.parseFlags(pattern.flags());
        }

        Pattern getCompiledPattern() {
            return this.compiledPattern;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public Class<?> type(Predicate.PredicateContext ctx) {
            return Void.TYPE;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public boolean isPatternNode() {
            return true;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public PatternNode asPatternNode() {
            return this;
        }

        public String toString() {
            if (!this.pattern.startsWith("/")) {
                return "/" + this.pattern + "/" + this.flags;
            }
            return this.pattern;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof PatternNode)) {
                return false;
            }
            PatternNode that = (PatternNode) o;
            return this.compiledPattern == null ? that.compiledPattern == null : this.compiledPattern.equals(that.compiledPattern);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/ValueNodes$JsonNode.class */
    public static class JsonNode extends ValueNode {
        private final Object json;
        private final boolean parsed = false;

        JsonNode(CharSequence charSequence) {
            this.json = charSequence.toString();
        }

        JsonNode(Object parsedJson) {
            this.json = parsedJson;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public Class<?> type(Predicate.PredicateContext ctx) {
            return isArray(ctx) ? List.class : isMap(ctx) ? Map.class : parse(ctx) instanceof Number ? Number.class : parse(ctx) instanceof String ? String.class : parse(ctx) instanceof Boolean ? Boolean.class : Void.class;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public boolean isJsonNode() {
            return true;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public JsonNode asJsonNode() {
            return this;
        }

        public ValueNode asValueListNode(Predicate.PredicateContext ctx) {
            if (!isArray(ctx)) {
                return ValueNodes.UNDEFINED;
            }
            return new ValueListNode(Collections.unmodifiableList((List) parse(ctx)));
        }

        public Object parse(Predicate.PredicateContext ctx) {
            try {
                return this.parsed ? this.json : new JSONParser(-1).parse(this.json.toString());
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }

        public boolean isParsed() {
            return this.parsed;
        }

        public Object getJson() {
            return this.json;
        }

        public boolean isArray(Predicate.PredicateContext ctx) {
            return parse(ctx) instanceof List;
        }

        public boolean isMap(Predicate.PredicateContext ctx) {
            return parse(ctx) instanceof Map;
        }

        public int length(Predicate.PredicateContext ctx) {
            if (isArray(ctx)) {
                return ((List) parse(ctx)).size();
            }
            return -1;
        }

        public boolean isEmpty(Predicate.PredicateContext ctx) {
            return (isArray(ctx) || isMap(ctx)) ? ((Collection) parse(ctx)).size() == 0 : !(parse(ctx) instanceof String) || ((String) parse(ctx)).length() == 0;
        }

        public String toString() {
            return this.json.toString();
        }

        public boolean equals(JsonNode jsonNode, Predicate.PredicateContext ctx) {
            if (this == jsonNode) {
                return true;
            }
            return this.json == null ? jsonNode.json == null : this.json.equals(jsonNode.parse(ctx));
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof JsonNode)) {
                return false;
            }
            JsonNode jsonNode = (JsonNode) o;
            return this.json == null ? jsonNode.json == null : this.json.equals(jsonNode.json);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/ValueNodes$StringNode.class */
    public static class StringNode extends ValueNode {
        private final String string;
        private boolean useSingleQuote;

        StringNode(CharSequence charSequence, boolean escape) {
            this.useSingleQuote = true;
            if (escape && charSequence.length() > 1) {
                char open = charSequence.charAt(0);
                char close = charSequence.charAt(charSequence.length() - 1);
                if (open == '\'' && close == '\'') {
                    charSequence = charSequence.subSequence(1, charSequence.length() - 1);
                } else if (open == '\"' && close == '\"') {
                    charSequence = charSequence.subSequence(1, charSequence.length() - 1);
                    this.useSingleQuote = false;
                }
                this.string = Utils.unescape(charSequence.toString());
                return;
            }
            this.string = charSequence.toString();
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public NumberNode asNumberNode() {
            try {
                BigDecimal number = new BigDecimal(this.string);
                return new NumberNode(number);
            } catch (NumberFormatException e) {
                return NumberNode.NAN;
            }
        }

        public String getString() {
            return this.string;
        }

        public int length() {
            return getString().length();
        }

        public boolean isEmpty() {
            return getString().isEmpty();
        }

        public boolean contains(String str) {
            return getString().contains(str);
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public Class<?> type(Predicate.PredicateContext ctx) {
            return String.class;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public boolean isStringNode() {
            return true;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public StringNode asStringNode() {
            return this;
        }

        public String toString() {
            String quote = this.useSingleQuote ? OperatorName.SHOW_TEXT_LINE : OperatorName.SHOW_TEXT_LINE_AND_SPACE;
            return quote + Utils.escape(this.string, true) + quote;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof StringNode) && !(o instanceof NumberNode)) {
                return false;
            }
            StringNode that = ((ValueNode) o).asStringNode();
            return this.string == null ? that.getString() == null : this.string.equals(that.getString());
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/ValueNodes$NumberNode.class */
    public static class NumberNode extends ValueNode {
        public static NumberNode NAN = new NumberNode((BigDecimal) null);
        private final BigDecimal number;

        NumberNode(BigDecimal number) {
            this.number = number;
        }

        NumberNode(CharSequence num) {
            this.number = new BigDecimal(num.toString());
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public StringNode asStringNode() {
            return new StringNode(this.number.toString(), false);
        }

        public BigDecimal getNumber() {
            return this.number;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public Class<?> type(Predicate.PredicateContext ctx) {
            return Number.class;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public boolean isNumberNode() {
            return true;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public NumberNode asNumberNode() {
            return this;
        }

        public String toString() {
            return this.number.toString();
        }

        public boolean equals(Object o) {
            NumberNode that;
            if (this == o) {
                return true;
            }
            return ((o instanceof NumberNode) || (o instanceof StringNode)) && (that = ((ValueNode) o).asNumberNode()) != NAN && this.number.compareTo(that.number) == 0;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/ValueNodes$OffsetDateTimeNode.class */
    public static class OffsetDateTimeNode extends ValueNode {
        private final OffsetDateTime dateTime;

        OffsetDateTimeNode(OffsetDateTime dateTime) {
            this.dateTime = dateTime;
        }

        OffsetDateTimeNode(CharSequence date) {
            this.dateTime = OffsetDateTime.parse(date);
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public StringNode asStringNode() {
            return new StringNode(this.dateTime.toString(), false);
        }

        public OffsetDateTime getDate() {
            return this.dateTime;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public Class<?> type(Predicate.PredicateContext ctx) {
            return OffsetDateTimeNode.class;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public boolean isOffsetDateTimeNode() {
            return true;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public OffsetDateTimeNode asOffsetDateTimeNode() {
            return this;
        }

        public String toString() {
            return this.dateTime.toString();
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof OffsetDateTimeNode) && !(o instanceof StringNode)) {
                return false;
            }
            OffsetDateTimeNode that = ((ValueNode) o).asOffsetDateTimeNode();
            return this.dateTime.compareTo(that.dateTime) == 0;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/ValueNodes$BooleanNode.class */
    public static class BooleanNode extends ValueNode {
        private final Boolean value;

        private BooleanNode(CharSequence boolValue) {
            this.value = Boolean.valueOf(Boolean.parseBoolean(boolValue.toString()));
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public Class<?> type(Predicate.PredicateContext ctx) {
            return Boolean.class;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public boolean isBooleanNode() {
            return true;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public BooleanNode asBooleanNode() {
            return this;
        }

        public boolean getBoolean() {
            return this.value.booleanValue();
        }

        public String toString() {
            return this.value.toString();
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof BooleanNode)) {
                return false;
            }
            BooleanNode that = (BooleanNode) o;
            return this.value == null ? that.value == null : this.value.equals(that.value);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/ValueNodes$ClassNode.class */
    public static class ClassNode extends ValueNode {
        private final Class clazz;

        ClassNode(Class clazz) {
            this.clazz = clazz;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public Class<?> type(Predicate.PredicateContext ctx) {
            return Class.class;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public boolean isClassNode() {
            return true;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public ClassNode asClassNode() {
            return this;
        }

        public Class getClazz() {
            return this.clazz;
        }

        public String toString() {
            return this.clazz.getName();
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ClassNode)) {
                return false;
            }
            ClassNode that = (ClassNode) o;
            return this.clazz == null ? that.clazz == null : this.clazz.equals(that.clazz);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/ValueNodes$NullNode.class */
    public static class NullNode extends ValueNode {
        private NullNode() {
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public Class<?> type(Predicate.PredicateContext ctx) {
            return Void.class;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public boolean isNullNode() {
            return true;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public NullNode asNullNode() {
            return this;
        }

        public String toString() {
            return "null";
        }

        public boolean equals(Object o) {
            return this == o || (o instanceof NullNode);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/ValueNodes$UndefinedNode.class */
    public static class UndefinedNode extends ValueNode {
        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public Class<?> type(Predicate.PredicateContext ctx) {
            return Void.class;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public UndefinedNode asUndefinedNode() {
            return this;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public boolean isUndefinedNode() {
            return true;
        }

        public boolean equals(Object o) {
            return false;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/ValueNodes$PredicateNode.class */
    public static class PredicateNode extends ValueNode {
        private final Predicate predicate;

        public PredicateNode(Predicate predicate) {
            this.predicate = predicate;
        }

        public Predicate getPredicate() {
            return this.predicate;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public PredicateNode asPredicateNode() {
            return this;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public Class<?> type(Predicate.PredicateContext ctx) {
            return Void.class;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public boolean isPredicateNode() {
            return true;
        }

        public boolean equals(Object o) {
            return false;
        }

        public String toString() {
            return this.predicate.toString();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/ValueNodes$ValueListNode.class */
    public static class ValueListNode extends ValueNode implements Iterable<ValueNode> {
        private List<ValueNode> nodes = new ArrayList();

        public ValueListNode(Collection<?> values) {
            for (Object value : values) {
                this.nodes.add(toValueNode(value));
            }
        }

        public boolean contains(ValueNode node) {
            return this.nodes.contains(node);
        }

        public boolean subsetof(ValueListNode right) {
            for (ValueNode leftNode : this.nodes) {
                if (!right.nodes.contains(leftNode)) {
                    return false;
                }
            }
            return true;
        }

        public List<ValueNode> getNodes() {
            return Collections.unmodifiableList(this.nodes);
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public Class<?> type(Predicate.PredicateContext ctx) {
            return List.class;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public boolean isValueListNode() {
            return true;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public ValueListNode asValueListNode() {
            return this;
        }

        public String toString() {
            return "[" + Utils.join(",", this.nodes) + "]";
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ValueListNode)) {
                return false;
            }
            ValueListNode that = (ValueListNode) o;
            return this.nodes.equals(that.nodes);
        }

        @Override // java.lang.Iterable
        public Iterator<ValueNode> iterator() {
            return this.nodes.iterator();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/ValueNodes$PathNode.class */
    public static class PathNode extends ValueNode {
        private static final Logger logger = LoggerFactory.getLogger((Class<?>) PathNode.class);
        private final Path path;
        private final boolean existsCheck;
        private final boolean shouldExist;

        PathNode(Path path) {
            this(path, false, false);
        }

        PathNode(CharSequence charSequence, boolean existsCheck, boolean shouldExist) {
            this(PathCompiler.compile(charSequence.toString(), new Predicate[0]), existsCheck, shouldExist);
        }

        PathNode(Path path, boolean existsCheck, boolean shouldExist) {
            this.path = path;
            this.existsCheck = existsCheck;
            this.shouldExist = shouldExist;
            logger.trace("PathNode {} existsCheck: {}", path, Boolean.valueOf(existsCheck));
        }

        public Path getPath() {
            return this.path;
        }

        public boolean isExistsCheck() {
            return this.existsCheck;
        }

        public boolean shouldExists() {
            return this.shouldExist;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public Class<?> type(Predicate.PredicateContext ctx) {
            return Void.class;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public boolean isPathNode() {
            return true;
        }

        @Override // com.jayway.jsonpath.internal.filter.ValueNode
        public PathNode asPathNode() {
            return this;
        }

        public PathNode asExistsCheck(boolean shouldExist) {
            return new PathNode(this.path, true, shouldExist);
        }

        public String toString() {
            return (!this.existsCheck || this.shouldExist) ? this.path.toString() : Utils.concat("!", this.path.toString());
        }

        public ValueNode evaluate(Predicate.PredicateContext ctx) {
            Object res;
            if (isExistsCheck()) {
                try {
                    Configuration c = Configuration.builder().jsonProvider(ctx.configuration().jsonProvider()).options(Option.REQUIRE_PROPERTIES).build();
                    Object result = this.path.evaluate(ctx.item(), ctx.root(), c).getValue(false);
                    return result == JsonProvider.UNDEFINED ? ValueNodes.FALSE : ValueNodes.TRUE;
                } catch (PathNotFoundException e) {
                    return ValueNodes.FALSE;
                }
            }
            try {
                if (ctx instanceof PredicateContextImpl) {
                    PredicateContextImpl ctxi = (PredicateContextImpl) ctx;
                    res = ctxi.evaluate(this.path);
                } else {
                    Object doc = this.path.isRootPath() ? ctx.root() : ctx.item();
                    res = this.path.evaluate(doc, ctx.root(), ctx.configuration()).getValue();
                }
                Object res2 = ctx.configuration().jsonProvider().unwrap(res);
                if (res2 instanceof Number) {
                    return ValueNode.createNumberNode(res2.toString());
                }
                if (res2 instanceof String) {
                    return ValueNode.createStringNode(res2.toString(), false);
                }
                if (res2 instanceof Boolean) {
                    return ValueNode.createBooleanNode(res2.toString());
                }
                if (res2 instanceof OffsetDateTime) {
                    return ValueNode.createOffsetDateTimeNode(res2.toString());
                }
                if (res2 == null) {
                    return ValueNodes.NULL_NODE;
                }
                if (ctx.configuration().jsonProvider().isArray(res2)) {
                    return ValueNode.createJsonNode(ctx.configuration().mappingProvider().map(res2, List.class, ctx.configuration()));
                }
                if (ctx.configuration().jsonProvider().isMap(res2)) {
                    return ValueNode.createJsonNode(ctx.configuration().mappingProvider().map(res2, Map.class, ctx.configuration()));
                }
                throw new JsonPathException("Could not convert " + res2.getClass().toString() + ":" + res2.toString() + " to a ValueNode");
            } catch (PathNotFoundException e2) {
                return ValueNodes.UNDEFINED;
            }
        }
    }
}
