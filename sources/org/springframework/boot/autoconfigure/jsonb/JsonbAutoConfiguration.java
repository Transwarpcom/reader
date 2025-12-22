package org.springframework.boot.autoconfigure.jsonb;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({Jsonb.class})
@ConditionalOnResource(resources = {"classpath:META-INF/services/javax.json.bind.spi.JsonbProvider", "classpath:META-INF/services/javax.json.spi.JsonProvider"})
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/jsonb/JsonbAutoConfiguration.class */
public class JsonbAutoConfiguration {
    @ConditionalOnMissingBean
    @Bean
    public Jsonb jsonb() {
        return JsonbBuilder.create();
    }
}
