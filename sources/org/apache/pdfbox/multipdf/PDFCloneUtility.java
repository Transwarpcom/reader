package org.apache.pdfbox.multipdf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/multipdf/PDFCloneUtility.class */
public class PDFCloneUtility {
    private static final Log LOG = LogFactory.getLog((Class<?>) PDFCloneUtility.class);
    private final PDDocument destination;
    private final Map<Object, COSBase> clonedVersion = new HashMap();
    private final Set<COSBase> clonedValues = new HashSet();

    public PDFCloneUtility(PDDocument dest) {
        this.destination = dest;
    }

    public PDDocument getDestination() {
        return this.destination;
    }

    public COSBase cloneForNewDocument(Object base) throws IOException {
        COSBase retval;
        if (base == null) {
            return null;
        }
        COSBase retval2 = this.clonedVersion.get(base);
        if (retval2 != null) {
            return retval2;
        }
        if ((base instanceof COSBase) && this.clonedValues.contains(base)) {
            return (COSBase) base;
        }
        if (base instanceof List) {
            COSArray array = new COSArray();
            List<?> list = (List) base;
            for (Object obj : list) {
                array.add(cloneForNewDocument(obj));
            }
            retval = array;
        } else if ((base instanceof COSObjectable) && !(base instanceof COSBase)) {
            retval = cloneForNewDocument(((COSObjectable) base).getCOSObject());
        } else if (base instanceof COSObject) {
            COSObject object = (COSObject) base;
            retval = cloneForNewDocument(object.getObject());
        } else if (base instanceof COSArray) {
            COSArray cOSArray = new COSArray();
            COSArray array2 = (COSArray) base;
            for (int i = 0; i < array2.size(); i++) {
                COSBase value = array2.get(i);
                if (hasSelfReference(base, value)) {
                    cOSArray.add((COSBase) cOSArray);
                } else {
                    cOSArray.add(cloneForNewDocument(value));
                }
            }
            retval = cOSArray;
        } else if (base instanceof COSStream) {
            COSStream originalStream = (COSStream) base;
            COSStream cOSStreamCreateCOSStream = this.destination.getDocument().createCOSStream();
            OutputStream output = cOSStreamCreateCOSStream.createRawOutputStream();
            InputStream input = originalStream.createRawInputStream();
            IOUtils.copy(input, output);
            input.close();
            output.close();
            this.clonedVersion.put(base, cOSStreamCreateCOSStream);
            for (Map.Entry<COSName, COSBase> entry : originalStream.entrySet()) {
                COSBase value2 = entry.getValue();
                if (hasSelfReference(base, value2)) {
                    cOSStreamCreateCOSStream.setItem(entry.getKey(), (COSBase) cOSStreamCreateCOSStream);
                } else {
                    cOSStreamCreateCOSStream.setItem(entry.getKey(), cloneForNewDocument(value2));
                }
            }
            retval = cOSStreamCreateCOSStream;
        } else if (base instanceof COSDictionary) {
            COSDictionary dic = (COSDictionary) base;
            retval = new COSDictionary();
            this.clonedVersion.put(base, retval);
            for (Map.Entry<COSName, COSBase> entry2 : dic.entrySet()) {
                COSBase value3 = entry2.getValue();
                if (hasSelfReference(base, value3)) {
                    ((COSDictionary) retval).setItem(entry2.getKey(), retval);
                } else {
                    ((COSDictionary) retval).setItem(entry2.getKey(), cloneForNewDocument(value3));
                }
            }
        } else {
            retval = (COSBase) base;
        }
        this.clonedVersion.put(base, retval);
        this.clonedValues.add(retval);
        return retval;
    }

    public void cloneMerge(COSObjectable base, COSObjectable target) throws IOException {
        if (base == null || base == target) {
            return;
        }
        COSBase retval = this.clonedVersion.get(base);
        if (retval != null) {
            return;
        }
        if (!(base instanceof COSBase)) {
            cloneMerge(base.getCOSObject(), target.getCOSObject());
        } else if (base instanceof COSObject) {
            if (target instanceof COSObject) {
                cloneMerge(((COSObject) base).getObject(), ((COSObject) target).getObject());
            } else if ((target instanceof COSDictionary) || (target instanceof COSArray)) {
                cloneMerge(((COSObject) base).getObject(), target);
            }
        } else if (base instanceof COSArray) {
            if (target instanceof COSObject) {
                cloneMerge(base, ((COSObject) target).getObject());
            } else {
                COSArray array = (COSArray) base;
                for (int i = 0; i < array.size(); i++) {
                    ((COSArray) target).add(cloneForNewDocument(array.get(i)));
                }
            }
        } else if (base instanceof COSStream) {
            COSStream originalStream = (COSStream) base;
            COSStream stream = this.destination.getDocument().createCOSStream();
            OutputStream output = stream.createOutputStream(originalStream.getFilters());
            IOUtils.copy(originalStream.createInputStream(), output);
            output.close();
            this.clonedVersion.put(base, stream);
            for (Map.Entry<COSName, COSBase> entry : originalStream.entrySet()) {
                stream.setItem(entry.getKey(), cloneForNewDocument(entry.getValue()));
            }
            retval = stream;
        } else if (base instanceof COSDictionary) {
            if (target instanceof COSObject) {
                cloneMerge(base, ((COSObject) target).getObject());
            } else {
                COSDictionary dic = (COSDictionary) base;
                this.clonedVersion.put(base, retval);
                for (Map.Entry<COSName, COSBase> entry2 : dic.entrySet()) {
                    COSName key = entry2.getKey();
                    COSBase value = entry2.getValue();
                    if (((COSDictionary) target).getItem(key) != null) {
                        cloneMerge(value, ((COSDictionary) target).getItem(key));
                    } else {
                        ((COSDictionary) target).setItem(key, cloneForNewDocument(value));
                    }
                }
            }
        } else {
            retval = (COSBase) base;
        }
        this.clonedVersion.put(base, retval);
        this.clonedValues.add(retval);
    }

    private boolean hasSelfReference(Object parent, COSBase value) {
        if (value instanceof COSObject) {
            COSBase actual = ((COSObject) value).getObject();
            if (actual == parent) {
                COSObject cosObj = (COSObject) value;
                LOG.warn(parent.getClass().getSimpleName() + " object has a reference to itself: " + cosObj.getObjectNumber() + " " + cosObj.getGenerationNumber() + " R");
                return true;
            }
            return false;
        }
        return false;
    }
}
