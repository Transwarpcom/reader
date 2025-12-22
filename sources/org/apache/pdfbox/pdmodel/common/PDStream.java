package org.apache.pdfbox.pdmodel.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSInputStream;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNull;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.filter.DecodeOptions;
import org.apache.pdfbox.filter.Filter;
import org.apache.pdfbox.filter.FilterFactory;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.filespecification.PDFileSpecification;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/common/PDStream.class */
public class PDStream implements COSObjectable {
    private static final Log LOG = LogFactory.getLog((Class<?>) PDStream.class);
    private final COSStream stream;

    public PDStream(PDDocument document) {
        this.stream = document.getDocument().createCOSStream();
    }

    public PDStream(COSDocument document) {
        this.stream = document.createCOSStream();
    }

    public PDStream(COSStream str) {
        this.stream = str;
    }

    public PDStream(PDDocument doc, InputStream input) throws IOException {
        this(doc, input, (COSBase) null);
    }

    public PDStream(PDDocument doc, InputStream input, COSName filter) throws IOException {
        this(doc, input, (COSBase) filter);
    }

    public PDStream(PDDocument doc, InputStream input, COSArray filters) throws IOException {
        this(doc, input, (COSBase) filters);
    }

    private PDStream(PDDocument doc, InputStream input, COSBase filters) throws IOException {
        OutputStream output = null;
        try {
            this.stream = doc.getDocument().createCOSStream();
            output = this.stream.createOutputStream(filters);
            IOUtils.copy(input, output);
            if (output != null) {
                output.close();
            }
            input.close();
        } catch (Throwable th) {
            if (output != null) {
                output.close();
            }
            input.close();
            throw th;
        }
    }

    @Deprecated
    public void addCompression() throws IOException {
        if (getFilters() == null) {
            if (this.stream.getLength() > 0) {
                OutputStream out = null;
                try {
                    try {
                        byte[] bytes = IOUtils.toByteArray(this.stream.createInputStream());
                        out = this.stream.createOutputStream(COSName.FLATE_DECODE);
                        out.write(bytes);
                        IOUtils.closeQuietly(out);
                        return;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch (Throwable th) {
                    IOUtils.closeQuietly(out);
                    throw th;
                }
            }
            List<COSName> filters = new ArrayList<>(1);
            filters.add(COSName.FLATE_DECODE);
            setFilters(filters);
        }
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSStream getCOSObject() {
        return this.stream;
    }

    public OutputStream createOutputStream() throws IOException {
        return this.stream.createOutputStream();
    }

    public OutputStream createOutputStream(COSName filter) throws IOException {
        return this.stream.createOutputStream(filter);
    }

    public COSInputStream createInputStream() throws IOException {
        return this.stream.createInputStream();
    }

    public COSInputStream createInputStream(DecodeOptions options) throws IOException {
        return this.stream.createInputStream(options);
    }

    public InputStream createInputStream(List<String> stopFilters) throws IOException {
        InputStream is = this.stream.createRawInputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        List<COSName> filters = getFilters();
        if (filters != null) {
            for (int i = 0; i < filters.size(); i++) {
                COSName nextFilter = filters.get(i);
                if (stopFilters != null && stopFilters.contains(nextFilter.getName())) {
                    break;
                }
                Filter filter = FilterFactory.INSTANCE.getFilter(nextFilter);
                try {
                    filter.decode(is, os, this.stream, i);
                    IOUtils.closeQuietly(is);
                    is = new ByteArrayInputStream(os.toByteArray());
                    os.reset();
                } catch (Throwable th) {
                    IOUtils.closeQuietly(is);
                    throw th;
                }
            }
        }
        return is;
    }

    @Deprecated
    public COSStream getStream() {
        return this.stream;
    }

    public int getLength() {
        return this.stream.getInt(COSName.LENGTH, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<COSName> getFilters() {
        List list = null;
        COSBase filters = this.stream.getFilters();
        if (filters instanceof COSName) {
            COSName name = (COSName) filters;
            list = new COSArrayList(name, name, this.stream, COSName.FILTER);
        } else if (filters instanceof COSArray) {
            list = ((COSArray) filters).toList();
        }
        return list;
    }

    public void setFilters(List<COSName> filters) {
        COSBase obj = COSArrayList.converterToCOSArray(filters);
        this.stream.setItem(COSName.FILTER, obj);
    }

    public List<Object> getDecodeParms() throws IOException {
        return internalGetDecodeParams(COSName.DECODE_PARMS, COSName.DP);
    }

    public List<Object> getFileDecodeParams() throws IOException {
        return internalGetDecodeParams(COSName.F_DECODE_PARMS, null);
    }

    private List<Object> internalGetDecodeParams(COSName name1, COSName name2) throws IOException {
        COSBase dp = this.stream.getDictionaryObject(name1, name2);
        if (dp instanceof COSDictionary) {
            Map<?, ?> map = COSDictionaryMap.convertBasicTypesToMap((COSDictionary) dp);
            return new COSArrayList(map, dp, this.stream, name1);
        }
        if (dp instanceof COSArray) {
            COSArray array = (COSArray) dp;
            List<Object> actuals = new ArrayList<>(array.size());
            for (int i = 0; i < array.size(); i++) {
                COSBase base = array.getObject(i);
                if (base instanceof COSDictionary) {
                    actuals.add(COSDictionaryMap.convertBasicTypesToMap((COSDictionary) base));
                } else {
                    LOG.warn("Expected COSDictionary, got " + base + ", ignored");
                }
            }
            return new COSArrayList(actuals, array);
        }
        return null;
    }

    public void setDecodeParms(List<?> decodeParams) {
        this.stream.setItem(COSName.DECODE_PARMS, (COSBase) COSArrayList.converterToCOSArray(decodeParams));
    }

    public PDFileSpecification getFile() throws IOException {
        COSBase f = this.stream.getDictionaryObject(COSName.F);
        return PDFileSpecification.createFS(f);
    }

    public void setFile(PDFileSpecification f) {
        this.stream.setItem(COSName.F, f);
    }

    public List<String> getFileFilters() {
        List<String> retval = null;
        COSBase filters = this.stream.getDictionaryObject(COSName.F_FILTER);
        if (filters instanceof COSName) {
            COSName name = (COSName) filters;
            retval = new COSArrayList(name.getName(), name, this.stream, COSName.F_FILTER);
        } else if (filters instanceof COSArray) {
            retval = COSArrayList.convertCOSNameCOSArrayToList((COSArray) filters);
        }
        return retval;
    }

    public void setFileFilters(List<String> filters) {
        COSBase obj = COSArrayList.convertStringListToCOSNameCOSArray(filters);
        this.stream.setItem(COSName.F_FILTER, obj);
    }

    public void setFileDecodeParams(List<?> decodeParams) {
        this.stream.setItem(COSName.F_DECODE_PARMS, (COSBase) COSArrayList.converterToCOSArray(decodeParams));
    }

    public byte[] toByteArray() throws IOException {
        InputStream is = null;
        try {
            is = createInputStream();
            byte[] byteArray = IOUtils.toByteArray(is);
            if (is != null) {
                is.close();
            }
            return byteArray;
        } catch (Throwable th) {
            if (is != null) {
                is.close();
            }
            throw th;
        }
    }

    public PDMetadata getMetadata() {
        PDMetadata retval = null;
        COSBase mdStream = this.stream.getDictionaryObject(COSName.METADATA);
        if (mdStream instanceof COSStream) {
            retval = new PDMetadata((COSStream) mdStream);
        } else if (!(mdStream instanceof COSNull) && mdStream != null) {
            throw new IllegalStateException("Expected a COSStream but was a " + mdStream.getClass().getSimpleName());
        }
        return retval;
    }

    public void setMetadata(PDMetadata meta) {
        this.stream.setItem(COSName.METADATA, meta);
    }

    public int getDecodedStreamLength() {
        return this.stream.getInt(COSName.DL);
    }

    public void setDecodedStreamLength(int decodedStreamLength) {
        this.stream.setInt(COSName.DL, decodedStreamLength);
    }
}
