package org.bson.codecs.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bson.assertions.Assertions;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.diagnostics.Logger;
import org.bson.diagnostics.Loggers;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/PojoCodecProvider.class */
public final class PojoCodecProvider implements CodecProvider {
    static final Logger LOGGER = Loggers.getLogger("codecs.pojo");
    private final boolean automatic;
    private final Map<Class<?>, ClassModel<?>> classModels;
    private final Set<String> packages;
    private final List<Convention> conventions;
    private final DiscriminatorLookup discriminatorLookup;
    private final List<PropertyCodecProvider> propertyCodecProviders;

    private PojoCodecProvider(boolean automatic, Map<Class<?>, ClassModel<?>> classModels, Set<String> packages, List<Convention> conventions, List<PropertyCodecProvider> propertyCodecProviders) {
        this.automatic = automatic;
        this.classModels = classModels;
        this.packages = packages;
        this.conventions = conventions;
        this.discriminatorLookup = new DiscriminatorLookup(classModels, packages);
        this.propertyCodecProviders = propertyCodecProviders;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // org.bson.codecs.configuration.CodecProvider
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        return getPojoCodec(clazz, registry);
    }

    private <T> PojoCodec<T> getPojoCodec(Class<T> clazz, CodecRegistry registry) {
        ClassModel<?> classModel = this.classModels.get(clazz);
        if (classModel != null) {
            return new PojoCodecImpl(classModel, registry, this.propertyCodecProviders, this.discriminatorLookup);
        }
        if (this.automatic || (clazz.getPackage() != null && this.packages.contains(clazz.getPackage().getName()))) {
            try {
                ClassModel<?> classModelCreateClassModel = createClassModel(clazz, this.conventions);
                if (!clazz.isInterface() && classModelCreateClassModel.getPropertyModels().isEmpty()) {
                    return null;
                }
                this.discriminatorLookup.addClassModel(classModelCreateClassModel);
                return new AutomaticPojoCodec(new PojoCodecImpl(classModelCreateClassModel, registry, this.propertyCodecProviders, this.discriminatorLookup));
            } catch (Exception e) {
                LOGGER.warn(String.format("Cannot use '%s' with the PojoCodec.", clazz.getSimpleName()), e);
                return null;
            }
        }
        return null;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/PojoCodecProvider$Builder.class */
    public static final class Builder {
        private final Set<String> packages;
        private final Map<Class<?>, ClassModel<?>> classModels;
        private final List<Class<?>> clazzes;
        private List<Convention> conventions;
        private final List<PropertyCodecProvider> propertyCodecProviders;
        private boolean automatic;

        public PojoCodecProvider build() {
            List<Convention> listUnmodifiableList;
            if (this.conventions != null) {
                listUnmodifiableList = Collections.unmodifiableList(new ArrayList(this.conventions));
            } else {
                listUnmodifiableList = null;
            }
            List<Convention> immutableConventions = listUnmodifiableList;
            for (Class<?> clazz : this.clazzes) {
                if (!this.classModels.containsKey(clazz)) {
                    register(PojoCodecProvider.createClassModel(clazz, immutableConventions));
                }
            }
            return new PojoCodecProvider(this.automatic, this.classModels, this.packages, immutableConventions, this.propertyCodecProviders);
        }

        public Builder automatic(boolean automatic) {
            this.automatic = automatic;
            return this;
        }

        public Builder conventions(List<Convention> conventions) {
            this.conventions = (List) Assertions.notNull("conventions", conventions);
            return this;
        }

        public Builder register(Class<?>... classes) {
            this.clazzes.addAll(Arrays.asList(classes));
            return this;
        }

        public Builder register(ClassModel<?>... classModels) {
            Assertions.notNull("classModels", classModels);
            for (ClassModel<?> classModel : classModels) {
                this.classModels.put(classModel.getType(), classModel);
            }
            return this;
        }

        public Builder register(String... packageNames) {
            this.packages.addAll(Arrays.asList((Object[]) Assertions.notNull("packageNames", packageNames)));
            return this;
        }

        public Builder register(PropertyCodecProvider... providers) {
            this.propertyCodecProviders.addAll(Arrays.asList((Object[]) Assertions.notNull("providers", providers)));
            return this;
        }

        private Builder() {
            this.packages = new HashSet();
            this.classModels = new HashMap();
            this.clazzes = new ArrayList();
            this.conventions = null;
            this.propertyCodecProviders = new ArrayList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> ClassModel<T> createClassModel(Class<T> clazz, List<Convention> conventions) {
        ClassModelBuilder<T> builder = ClassModel.builder(clazz);
        if (conventions != null) {
            builder.conventions(conventions);
        }
        return builder.build();
    }
}
