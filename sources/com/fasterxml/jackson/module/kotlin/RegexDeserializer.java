package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinDeserializers.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\bÆ\u0002\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/RegexDeserializer;", "Lcom/fasterxml/jackson/databind/deser/std/StdDeserializer;", "Lkotlin/text/Regex;", "()V", "deserialize", "p", "Lcom/fasterxml/jackson/core/JsonParser;", "ctxt", "Lcom/fasterxml/jackson/databind/DeserializationContext;", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/RegexDeserializer.class */
public final class RegexDeserializer extends StdDeserializer<Regex> {

    @NotNull
    public static final RegexDeserializer INSTANCE = new RegexDeserializer();

    private RegexDeserializer() {
        super((Class<?>) Regex.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @NotNull
    public Regex deserialize(@NotNull JsonParser p, @NotNull DeserializationContext ctxt) {
        Set setEmptySet;
        Intrinsics.checkNotNullParameter(p, "p");
        Intrinsics.checkNotNullParameter(ctxt, "ctxt");
        JsonNode node = ctxt.readTree(p);
        if (node.isTextual()) {
            String strAsText = node.asText();
            Intrinsics.checkNotNullExpressionValue(strAsText, "node.asText()");
            return new Regex(strAsText);
        }
        if (node.isObject()) {
            String pattern = node.get("pattern").asText();
            if (node.has("options")) {
                JsonNode optionsNode = node.get("options");
                if (!optionsNode.isArray()) {
                    throw new IllegalStateException(Intrinsics.stringPlus("Expected an array of strings for RegexOptions, but type was ", node.getNodeType()));
                }
                Iterator<JsonNode> itElements = optionsNode.elements();
                Intrinsics.checkNotNullExpressionValue(itElements, "optionsNode.elements()");
                setEmptySet = SequencesKt.toSet(SequencesKt.map(SequencesKt.asSequence(itElements), new Function1<JsonNode, RegexOption>() { // from class: com.fasterxml.jackson.module.kotlin.RegexDeserializer$deserialize$options$1
                    @Override // kotlin.jvm.functions.Function1
                    @NotNull
                    public final RegexOption invoke(JsonNode it) {
                        String strAsText2 = it.asText();
                        Intrinsics.checkNotNullExpressionValue(strAsText2, "it.asText()");
                        return RegexOption.valueOf(strAsText2);
                    }
                }));
            } else {
                setEmptySet = SetsKt.emptySet();
            }
            Set options = setEmptySet;
            Intrinsics.checkNotNullExpressionValue(pattern, "pattern");
            return new Regex(pattern, (Set<? extends RegexOption>) options);
        }
        throw new IllegalStateException(Intrinsics.stringPlus("Expected a string or an object to deserialize a Regex, but type was ", node.getNodeType()));
    }
}
