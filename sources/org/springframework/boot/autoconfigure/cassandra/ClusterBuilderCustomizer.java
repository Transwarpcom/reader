package org.springframework.boot.autoconfigure.cassandra;

import com.datastax.driver.core.Cluster;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/cassandra/ClusterBuilderCustomizer.class */
public interface ClusterBuilderCustomizer {
    void customize(Cluster.Builder clusterBuilder);
}
