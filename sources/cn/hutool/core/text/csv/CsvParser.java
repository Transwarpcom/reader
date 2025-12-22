package cn.hutool.core.text.csv;

import cn.hutool.core.collection.ComputeIter;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/text/csv/CsvParser.class */
public final class CsvParser extends ComputeIter<CsvRow> implements Closeable, Serializable {
    private static final long serialVersionUID = 1;
    private static final int DEFAULT_ROW_CAPACITY = 10;
    private final Reader reader;
    private final CsvReadConfig config;
    private boolean inQuotes;
    private CsvRow header;
    private long inQuotesLineCount;
    private int maxFieldCount;
    private boolean finished;
    private final Buffer buf = new Buffer(32768);
    private int preChar = -1;
    private final StrBuilder currentField = new StrBuilder(512);
    private long lineNo = -1;
    private int firstLineFieldCount = -1;

    public CsvParser(Reader reader, CsvReadConfig config) {
        this.reader = (Reader) Objects.requireNonNull(reader, "reader must not be null");
        this.config = (CsvReadConfig) ObjectUtil.defaultIfNull(config, (Supplier<? extends CsvReadConfig>) CsvReadConfig::defaultConfig);
    }

    public List<String> getHeader() {
        if (this.config.headerLineNo < 0) {
            throw new IllegalStateException("No header available - header parsing is disabled");
        }
        if (this.lineNo < this.config.beginLineNo) {
            throw new IllegalStateException("No header available - call nextRow() first");
        }
        return this.header.fields;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // cn.hutool.core.collection.ComputeIter
    public CsvRow computeNext() {
        return nextRow();
    }

    public CsvRow nextRow() throws IORuntimeException {
        List<String> currentFields;
        int fieldCount;
        while (false == this.finished && (fieldCount = (currentFields = readLine()).size()) >= 1) {
            if (this.lineNo >= this.config.beginLineNo) {
                if (this.lineNo <= this.config.endLineNo) {
                    if (!this.config.skipEmptyRows || fieldCount != 1 || !currentFields.get(0).isEmpty()) {
                        if (this.config.errorOnDifferentFieldCount) {
                            if (this.firstLineFieldCount < 0) {
                                this.firstLineFieldCount = fieldCount;
                            } else if (fieldCount != this.firstLineFieldCount) {
                                throw new IORuntimeException(String.format("Line %d has %d fields, but first line has %d fields", Long.valueOf(this.lineNo), Integer.valueOf(fieldCount), Integer.valueOf(this.firstLineFieldCount)));
                            }
                        }
                        if (fieldCount > this.maxFieldCount) {
                            this.maxFieldCount = fieldCount;
                        }
                        if (this.lineNo == this.config.headerLineNo && null == this.header) {
                            initHeader(currentFields);
                        } else {
                            return new CsvRow(this.lineNo, null == this.header ? null : this.header.headerMap, currentFields);
                        }
                    }
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    private void initHeader(List<String> currentFields) {
        Map<String, Integer> localHeaderMap = new LinkedHashMap<>(currentFields.size());
        for (int i = 0; i < currentFields.size(); i++) {
            String field = currentFields.get(i);
            if (MapUtil.isNotEmpty(this.config.headerAlias)) {
                field = (String) ObjectUtil.defaultIfNull(this.config.headerAlias.get(field), field);
            }
            if (StrUtil.isNotEmpty(field) && false == localHeaderMap.containsKey(field)) {
                localHeaderMap.put(field, Integer.valueOf(i));
            }
        }
        this.header = new CsvRow(this.lineNo, Collections.unmodifiableMap(localHeaderMap), Collections.unmodifiableList(currentFields));
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x00f0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00ca A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<java.lang.String> readLine() throws cn.hutool.core.io.IORuntimeException {
        /*
            Method dump skipped, instructions count: 480
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.hutool.core.text.csv.CsvParser.readLine():java.util.List");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.reader.close();
    }

    private void addField(List<String> currentFields, String field) {
        char textDelimiter = this.config.textDelimiter;
        String field2 = StrUtil.replace(StrUtil.unWrap(StrUtil.trim(field, 1, c -> {
            return c.charValue() == '\n' || c.charValue() == '\r';
        }), textDelimiter), "" + textDelimiter + textDelimiter, textDelimiter + "");
        if (this.config.trimField) {
            field2 = StrUtil.trim(field2);
        }
        currentFields.add(field2);
    }

    private boolean isLineEnd(char c, int preChar) {
        return (c == '\r' || c == '\n') && preChar != 13;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/text/csv/CsvParser$Buffer.class */
    private static class Buffer implements Serializable {
        private static final long serialVersionUID = 1;
        final char[] buf;
        private int mark;
        private int position;
        private int limit;

        Buffer(int capacity) {
            this.buf = new char[capacity];
        }

        public final boolean hasRemaining() {
            return this.position < this.limit;
        }

        int read(Reader reader) throws IOException {
            try {
                int length = reader.read(this.buf);
                this.mark = 0;
                this.position = 0;
                this.limit = length;
                return length;
            } catch (IOException e) {
                throw new IORuntimeException(e);
            }
        }

        char get() {
            char[] cArr = this.buf;
            int i = this.position;
            this.position = i + 1;
            return cArr[i];
        }

        void mark() {
            this.mark = this.position;
        }

        void appendTo(StrBuilder builder, int length) {
            builder.append(this.buf, this.mark, length);
        }
    }
}
