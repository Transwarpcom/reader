package org.springframework.boot.autoconfigure.data.couchbase;

import com.couchbase.client.java.Bucket;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import reactor.core.publisher.Flux;

@Configuration
@ConditionalOnClass({Bucket.class, ReactiveCouchbaseRepository.class, Flux.class})
@AutoConfigureAfter({CouchbaseDataAutoConfiguration.class})
@Import({SpringBootCouchbaseReactiveDataConfiguration.class})
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/data/couchbase/CouchbaseReactiveDataAutoConfiguration.class */
public class CouchbaseReactiveDataAutoConfiguration {
}
