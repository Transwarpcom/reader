package org.antlr.v4.runtime.misc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/LogManager.class */
public class LogManager {
    protected List<Record> records;

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/LogManager$Record.class */
    protected static class Record {
        long timestamp = System.currentTimeMillis();
        StackTraceElement location = new Throwable().getStackTrace()[0];
        String component;
        String msg;

        public String toString() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date(this.timestamp)) + " " + this.component + " " + this.location.getFileName() + ":" + this.location.getLineNumber() + " " + this.msg;
        }
    }

    public void log(String component, String msg) {
        Record r = new Record();
        r.component = component;
        r.msg = msg;
        if (this.records == null) {
            this.records = new ArrayList();
        }
        this.records.add(r);
    }

    public void log(String msg) {
        log(null, msg);
    }

    public void save(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            bw.write(toString());
            bw.close();
        } catch (Throwable th) {
            bw.close();
            throw th;
        }
    }

    public String save() throws IOException {
        String defaultFilename = "./antlr-" + new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(new Date()) + ".log";
        save(defaultFilename);
        return defaultFilename;
    }

    public String toString() {
        if (this.records == null) {
            return "";
        }
        String nl = System.getProperty("line.separator");
        StringBuilder buf = new StringBuilder();
        for (Record r : this.records) {
            buf.append(r);
            buf.append(nl);
        }
        return buf.toString();
    }

    public static void main(String[] args) throws IOException {
        LogManager mgr = new LogManager();
        mgr.log("atn", "test msg");
        mgr.log("dfa", "test msg 2");
        System.out.println(mgr);
        mgr.save();
    }
}
