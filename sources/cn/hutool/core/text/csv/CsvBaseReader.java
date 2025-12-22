package cn.hutool.core.text.csv;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/text/csv/CsvBaseReader.class */
public class CsvBaseReader implements Serializable {
    private static final long serialVersionUID = 1;
    protected static final Charset DEFAULT_CHARSET = CharsetUtil.CHARSET_UTF_8;
    private final CsvReadConfig config;

    public CsvBaseReader() {
        this(null);
    }

    public CsvBaseReader(CsvReadConfig config) {
        this.config = (CsvReadConfig) ObjectUtil.defaultIfNull(config, (Supplier<? extends CsvReadConfig>) CsvReadConfig::defaultConfig);
    }

    public void setFieldSeparator(char fieldSeparator) {
        this.config.setFieldSeparator(fieldSeparator);
    }

    public void setTextDelimiter(char textDelimiter) {
        this.config.setTextDelimiter(textDelimiter);
    }

    public void setContainsHeader(boolean containsHeader) {
        this.config.setContainsHeader(containsHeader);
    }

    public void setSkipEmptyRows(boolean skipEmptyRows) {
        this.config.setSkipEmptyRows(skipEmptyRows);
    }

    public void setErrorOnDifferentFieldCount(boolean errorOnDifferentFieldCount) {
        this.config.setErrorOnDifferentFieldCount(errorOnDifferentFieldCount);
    }

    public CsvData read(File file) throws IORuntimeException {
        return read(file, DEFAULT_CHARSET);
    }

    public CsvData readFromStr(String csvStr) {
        return read(new StringReader(csvStr));
    }

    public void readFromStr(String csvStr, CsvRowHandler rowHandler) throws IOException, IORuntimeException {
        read(parse(new StringReader(csvStr)), rowHandler);
    }

    public CsvData read(File file, Charset charset) throws IORuntimeException {
        return read((Path) Objects.requireNonNull(file.toPath(), "file must not be null"), charset);
    }

    public CsvData read(Path path) throws IORuntimeException {
        return read(path, DEFAULT_CHARSET);
    }

    public CsvData read(Path path, Charset charset) throws IllegalArgumentException, IORuntimeException {
        Assert.notNull(path, "path must not be null", new Object[0]);
        return read(FileUtil.getReader(path, charset));
    }

    public CsvData read(Reader reader) throws IOException, IORuntimeException {
        CsvParser csvParser = parse(reader);
        List<CsvRow> rows = new ArrayList<>();
        rows.getClass();
        read(csvParser, (v1) -> {
            r2.add(v1);
        });
        List<String> header = this.config.headerLineNo > -1 ? csvParser.getHeader() : null;
        return new CsvData(header, rows);
    }

    public List<Map<String, String>> readMapList(Reader reader) throws IOException, IORuntimeException {
        this.config.setContainsHeader(true);
        List<Map<String, String>> result = new ArrayList<>();
        read(reader, row -> {
            result.add(row.getFieldMap());
        });
        return result;
    }

    public <T> List<T> read(Reader reader, Class<T> clazz) throws IOException, IORuntimeException {
        this.config.setContainsHeader(true);
        List<T> result = new ArrayList<>();
        read(reader, row -> {
            result.add(row.toBean(clazz));
        });
        return result;
    }

    public <T> List<T> read(String csvStr, Class<T> clazz) throws IOException, IORuntimeException {
        this.config.setContainsHeader(true);
        List<T> result = new ArrayList<>();
        read(new StringReader(csvStr), row -> {
            result.add(row.toBean(clazz));
        });
        return result;
    }

    public void read(Reader reader, CsvRowHandler rowHandler) throws IOException, IORuntimeException {
        read(parse(reader), rowHandler);
    }

    private void read(CsvParser csvParser, CsvRowHandler rowHandler) throws IOException, IORuntimeException {
        while (csvParser.hasNext()) {
            try {
                rowHandler.handle(csvParser.next());
            } finally {
                IoUtil.close((Closeable) csvParser);
            }
        }
    }

    protected CsvParser parse(Reader reader) throws IORuntimeException {
        return new CsvParser(reader, this.config);
    }
}
