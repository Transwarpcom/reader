package com.jayway.jsonpath;

import com.jayway.jsonpath.internal.DefaultsImpl;
import com.jayway.jsonpath.internal.Utils;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/Configuration.class */
public class Configuration {
    private static Defaults DEFAULTS = null;
    private final JsonProvider jsonProvider;
    private final MappingProvider mappingProvider;
    private final Set<Option> options;
    private final Collection<EvaluationListener> evaluationListeners;

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/Configuration$Defaults.class */
    public interface Defaults {
        JsonProvider jsonProvider();

        Set<Option> options();

        MappingProvider mappingProvider();
    }

    public static synchronized void setDefaults(Defaults defaults) {
        DEFAULTS = defaults;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Defaults getEffectiveDefaults() {
        if (DEFAULTS == null) {
            return DefaultsImpl.INSTANCE;
        }
        return DEFAULTS;
    }

    private Configuration(JsonProvider jsonProvider, MappingProvider mappingProvider, EnumSet<Option> options, Collection<EvaluationListener> evaluationListeners) {
        Utils.notNull(jsonProvider, "jsonProvider can not be null", new Object[0]);
        Utils.notNull(mappingProvider, "mappingProvider can not be null", new Object[0]);
        Utils.notNull(options, "setOptions can not be null", new Object[0]);
        Utils.notNull(evaluationListeners, "evaluationListeners can not be null", new Object[0]);
        this.jsonProvider = jsonProvider;
        this.mappingProvider = mappingProvider;
        this.options = Collections.unmodifiableSet(options);
        this.evaluationListeners = Collections.unmodifiableCollection(evaluationListeners);
    }

    public Configuration addEvaluationListeners(EvaluationListener... evaluationListener) {
        return builder().jsonProvider(this.jsonProvider).mappingProvider(this.mappingProvider).options(this.options).evaluationListener(evaluationListener).build();
    }

    public Configuration setEvaluationListeners(EvaluationListener... evaluationListener) {
        return builder().jsonProvider(this.jsonProvider).mappingProvider(this.mappingProvider).options(this.options).evaluationListener(evaluationListener).build();
    }

    public Collection<EvaluationListener> getEvaluationListeners() {
        return this.evaluationListeners;
    }

    public Configuration jsonProvider(JsonProvider newJsonProvider) {
        return builder().jsonProvider(newJsonProvider).mappingProvider(this.mappingProvider).options(this.options).evaluationListener(this.evaluationListeners).build();
    }

    public JsonProvider jsonProvider() {
        return this.jsonProvider;
    }

    public Configuration mappingProvider(MappingProvider newMappingProvider) {
        return builder().jsonProvider(this.jsonProvider).mappingProvider(newMappingProvider).options(this.options).evaluationListener(this.evaluationListeners).build();
    }

    public MappingProvider mappingProvider() {
        return this.mappingProvider;
    }

    public Configuration addOptions(Option... options) {
        EnumSet<Option> opts = EnumSet.noneOf(Option.class);
        opts.addAll(this.options);
        opts.addAll(Arrays.asList(options));
        return builder().jsonProvider(this.jsonProvider).mappingProvider(this.mappingProvider).options(opts).evaluationListener(this.evaluationListeners).build();
    }

    public Configuration setOptions(Option... options) {
        return builder().jsonProvider(this.jsonProvider).mappingProvider(this.mappingProvider).options(options).evaluationListener(this.evaluationListeners).build();
    }

    public Set<Option> getOptions() {
        return this.options;
    }

    public boolean containsOption(Option option) {
        return this.options.contains(option);
    }

    public static Configuration defaultConfiguration() {
        Defaults defaults = getEffectiveDefaults();
        return builder().jsonProvider(defaults.jsonProvider()).options(defaults.options()).build();
    }

    public static ConfigurationBuilder builder() {
        return new ConfigurationBuilder();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/Configuration$ConfigurationBuilder.class */
    public static class ConfigurationBuilder {
        private JsonProvider jsonProvider;
        private MappingProvider mappingProvider;
        private EnumSet<Option> options = EnumSet.noneOf(Option.class);
        private Collection<EvaluationListener> evaluationListener = new ArrayList();

        public ConfigurationBuilder jsonProvider(JsonProvider provider) {
            this.jsonProvider = provider;
            return this;
        }

        public ConfigurationBuilder mappingProvider(MappingProvider provider) {
            this.mappingProvider = provider;
            return this;
        }

        public ConfigurationBuilder options(Option... flags) {
            if (flags.length > 0) {
                this.options.addAll(Arrays.asList(flags));
            }
            return this;
        }

        public ConfigurationBuilder options(Set<Option> options) {
            this.options.addAll(options);
            return this;
        }

        public ConfigurationBuilder evaluationListener(EvaluationListener... listener) {
            this.evaluationListener = Arrays.asList(listener);
            return this;
        }

        public ConfigurationBuilder evaluationListener(Collection<EvaluationListener> listeners) {
            this.evaluationListener = listeners == null ? Collections.emptyList() : listeners;
            return this;
        }

        public Configuration build() {
            if (this.jsonProvider == null || this.mappingProvider == null) {
                Defaults defaults = Configuration.getEffectiveDefaults();
                if (this.jsonProvider == null) {
                    this.jsonProvider = defaults.jsonProvider();
                }
                if (this.mappingProvider == null) {
                    this.mappingProvider = defaults.mappingProvider();
                }
            }
            return new Configuration(this.jsonProvider, this.mappingProvider, this.options, this.evaluationListener);
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Configuration that = (Configuration) o;
        return this.jsonProvider.getClass() == that.jsonProvider.getClass() && this.mappingProvider.getClass() == that.mappingProvider.getClass() && Objects.equals(this.options, that.options);
    }
}
