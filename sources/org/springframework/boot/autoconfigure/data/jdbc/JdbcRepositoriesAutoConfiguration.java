package org.springframework.boot.autoconfigure.data.jdbc;

import java.util.Optional;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.JdbcConfiguration;
import org.springframework.data.jdbc.repository.config.JdbcRepositoryConfigExtension;
import org.springframework.data.relational.core.conversion.RelationalConverter;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@Configuration
@ConditionalOnClass({NamedParameterJdbcOperations.class, JdbcConfiguration.class})
@AutoConfigureAfter({JdbcTemplateAutoConfiguration.class})
@ConditionalOnBean({NamedParameterJdbcOperations.class})
@ConditionalOnProperty(prefix = "spring.data.jdbc.repositories", name = {"enabled"}, havingValue = "true", matchIfMissing = true)
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/data/jdbc/JdbcRepositoriesAutoConfiguration.class */
public class JdbcRepositoriesAutoConfiguration {

    @ConditionalOnMissingBean({JdbcRepositoryConfigExtension.class})
    @Configuration
    @Import({JdbcRepositoriesAutoConfigureRegistrar.class})
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/data/jdbc/JdbcRepositoriesAutoConfiguration$JdbcRepositoriesConfiguration.class */
    static class JdbcRepositoriesConfiguration {
        JdbcRepositoriesConfiguration() {
        }
    }

    @ConditionalOnMissingBean({JdbcConfiguration.class})
    @Configuration
    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/data/jdbc/JdbcRepositoriesAutoConfiguration$SpringBootJdbcConfiguration.class */
    static class SpringBootJdbcConfiguration extends JdbcConfiguration {
        SpringBootJdbcConfiguration() {
        }

        public JdbcCustomConversions jdbcCustomConversions() {
            return super.jdbcCustomConversions();
        }

        public RelationalMappingContext jdbcMappingContext(Optional<NamingStrategy> namingStrategy) {
            return super.jdbcMappingContext(namingStrategy);
        }

        public RelationalConverter relationalConverter(RelationalMappingContext mappingContext) {
            return super.relationalConverter(mappingContext);
        }
    }
}
