package org.bson.codecs.pojo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/Conventions.class */
public final class Conventions {
    public static final Convention CLASS_AND_PROPERTY_CONVENTION = new ConventionDefaultsImpl();
    public static final Convention ANNOTATION_CONVENTION = new ConventionAnnotationImpl();
    public static final Convention SET_PRIVATE_FIELDS_CONVENTION = new ConventionSetPrivateFieldImpl();
    public static final Convention USE_GETTERS_FOR_SETTERS = new ConventionUseGettersAsSettersImpl();
    public static final List<Convention> DEFAULT_CONVENTIONS = Collections.unmodifiableList(Arrays.asList(CLASS_AND_PROPERTY_CONVENTION, ANNOTATION_CONVENTION));
    public static final List<Convention> NO_CONVENTIONS = Collections.emptyList();

    private Conventions() {
    }
}
