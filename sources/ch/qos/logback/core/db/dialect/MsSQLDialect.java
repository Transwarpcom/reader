package ch.qos.logback.core.db.dialect;

/* loaded from: reader.jar:BOOT-INF/lib/logback-core-1.2.3.jar:ch/qos/logback/core/db/dialect/MsSQLDialect.class */
public class MsSQLDialect implements SQLDialect {
    public static final String SELECT_CURRVAL = "SELECT @@identity id";

    @Override // ch.qos.logback.core.db.dialect.SQLDialect
    public String getSelectInsertId() {
        return "SELECT @@identity id";
    }
}
