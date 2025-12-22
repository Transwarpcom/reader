package org.bson.codecs.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.codecs.Codec;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/configuration/CodecRegistries.class */
public final class CodecRegistries {
    public static CodecRegistry fromCodecs(Codec<?>... codecs) {
        return fromCodecs((List<? extends Codec<?>>) Arrays.asList(codecs));
    }

    public static CodecRegistry fromCodecs(List<? extends Codec<?>> codecs) {
        return fromProviders(new MapOfCodecsProvider(codecs));
    }

    public static CodecRegistry fromProviders(CodecProvider... providers) {
        return fromProviders((List<? extends CodecProvider>) Arrays.asList(providers));
    }

    public static CodecRegistry fromProviders(List<? extends CodecProvider> providers) {
        return new ProvidersCodecRegistry(providers);
    }

    public static CodecRegistry fromRegistries(CodecRegistry... registries) {
        return fromRegistries((List<? extends CodecRegistry>) Arrays.asList(registries));
    }

    public static CodecRegistry fromRegistries(List<? extends CodecRegistry> registries) {
        List<CodecProvider> providers = new ArrayList<>();
        for (CodecRegistry registry : registries) {
            providers.add(providerFromRegistry(registry));
        }
        return new ProvidersCodecRegistry(providers);
    }

    private static CodecProvider providerFromRegistry(final CodecRegistry innerRegistry) {
        if (innerRegistry instanceof CodecProvider) {
            return (CodecProvider) innerRegistry;
        }
        return new CodecProvider() { // from class: org.bson.codecs.configuration.CodecRegistries.1
            @Override // org.bson.codecs.configuration.CodecProvider
            public <T> Codec<T> get(Class<T> clazz, CodecRegistry outerRregistry) {
                try {
                    return innerRegistry.get(clazz);
                } catch (CodecConfigurationException e) {
                    return null;
                }
            }
        };
    }

    private CodecRegistries() {
    }
}
