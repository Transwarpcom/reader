package org.apache.logging.log4j.spi;

import java.util.EnumSet;
import java.util.Iterator;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;

/* loaded from: reader.jar:BOOT-INF/lib/log4j-api-2.11.2.jar:org/apache/logging/log4j/spi/StandardLevel.class */
public enum StandardLevel {
    OFF(0),
    FATAL(100),
    ERROR(200),
    WARN(OS2WindowsMetricsTable.WEIGHT_CLASS_LIGHT),
    INFO(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL),
    DEBUG(500),
    TRACE(OS2WindowsMetricsTable.WEIGHT_CLASS_SEMI_BOLD),
    ALL(Integer.MAX_VALUE);

    private static final EnumSet<StandardLevel> LEVELSET = EnumSet.allOf(StandardLevel.class);
    private final int intLevel;

    StandardLevel(int val) {
        this.intLevel = val;
    }

    public int intLevel() {
        return this.intLevel;
    }

    public static StandardLevel getStandardLevel(int intLevel) {
        StandardLevel level = OFF;
        Iterator i$ = LEVELSET.iterator();
        while (i$.hasNext()) {
            StandardLevel lvl = (StandardLevel) i$.next();
            if (lvl.intLevel() > intLevel) {
                break;
            }
            level = lvl;
        }
        return level;
    }
}
