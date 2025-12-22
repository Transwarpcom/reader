package org.springframework.boot.autoconfigure.jooq;

import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jooq.SQLDialect;
import org.jooq.tools.jdbc.JDBCUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/jooq/SqlDialectLookup.class */
final class SqlDialectLookup {
    private static final Log logger = LogFactory.getLog((Class<?>) SqlDialectLookup.class);

    private SqlDialectLookup() {
    }

    public static SQLDialect getDialect(DataSource dataSource) {
        if (dataSource == null) {
            return SQLDialect.DEFAULT;
        }
        try {
            String url = (String) JdbcUtils.extractDatabaseMetaData(dataSource, "getURL");
            SQLDialect sqlDialect = JDBCUtils.dialect(url);
            if (sqlDialect != null) {
                return sqlDialect;
            }
        } catch (MetaDataAccessException e) {
            logger.warn("Unable to determine jdbc url from datasource", e);
        }
        return SQLDialect.DEFAULT;
    }
}
