package org.springframework.boot.autoconfigure;

import java.util.Collections;
import java.util.EventObject;
import java.util.List;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/AutoConfigurationImportEvent.class */
public class AutoConfigurationImportEvent extends EventObject {
    private final List<String> candidateConfigurations;
    private final Set<String> exclusions;

    public AutoConfigurationImportEvent(Object source, List<String> candidateConfigurations, Set<String> exclusions) {
        super(source);
        this.candidateConfigurations = Collections.unmodifiableList(candidateConfigurations);
        this.exclusions = Collections.unmodifiableSet(exclusions);
    }

    public List<String> getCandidateConfigurations() {
        return this.candidateConfigurations;
    }

    public Set<String> getExclusions() {
        return this.exclusions;
    }
}
