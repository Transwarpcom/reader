package org.apache.pdfbox.util.filetypedetector;

import java.io.BufferedInputStream;
import java.io.IOException;
import org.apache.pdfbox.util.Charsets;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/util/filetypedetector/FileTypeDetector.class */
public final class FileTypeDetector {
    private static final ByteTrie<FileType> root = new ByteTrie<>();

    /* JADX WARN: Type inference failed for: r2v1, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v11, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v13, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v15, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v17, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v19, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v21, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v23, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v25, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v27, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v29, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v3, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v31, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v33, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v35, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v37, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v39, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v41, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v5, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v7, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r2v9, types: [byte[], byte[][]] */
    static {
        root.setDefaultValue(FileType.UNKNOWN);
        root.addPath(FileType.JPEG, new byte[]{new byte[]{-1, -40}});
        root.addPath(FileType.TIFF, new byte[]{"II".getBytes(Charsets.ISO_8859_1), new byte[]{42, 0}});
        root.addPath(FileType.TIFF, new byte[]{"MM".getBytes(Charsets.ISO_8859_1), new byte[]{0, 42}});
        root.addPath(FileType.PSD, new byte[]{"8BPS".getBytes(Charsets.ISO_8859_1)});
        root.addPath(FileType.PNG, new byte[]{new byte[]{-119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82}});
        root.addPath(FileType.BMP, new byte[]{"BM".getBytes(Charsets.ISO_8859_1)});
        root.addPath(FileType.GIF, new byte[]{"GIF87a".getBytes(Charsets.ISO_8859_1)});
        root.addPath(FileType.GIF, new byte[]{"GIF89a".getBytes(Charsets.ISO_8859_1)});
        root.addPath(FileType.ICO, new byte[]{new byte[]{0, 0, 1, 0}});
        root.addPath(FileType.PCX, new byte[]{new byte[]{10, 0, 1}});
        root.addPath(FileType.PCX, new byte[]{new byte[]{10, 2, 1}});
        root.addPath(FileType.PCX, new byte[]{new byte[]{10, 3, 1}});
        root.addPath(FileType.PCX, new byte[]{new byte[]{10, 5, 1}});
        root.addPath(FileType.RIFF, new byte[]{"RIFF".getBytes(Charsets.ISO_8859_1)});
        root.addPath(FileType.CRW, new byte[]{"II".getBytes(Charsets.ISO_8859_1), new byte[]{26, 0, 0, 0}, "HEAPCCDR".getBytes(Charsets.ISO_8859_1)});
        root.addPath(FileType.CR2, new byte[]{"II".getBytes(Charsets.ISO_8859_1), new byte[]{42, 0, 16, 0, 0, 0, 67, 82}});
        root.addPath(FileType.NEF, new byte[]{"MM".getBytes(Charsets.ISO_8859_1), new byte[]{0, 42, 0, 0, 0, -128, 0}});
        root.addPath(FileType.ORF, new byte[]{"IIRO".getBytes(Charsets.ISO_8859_1), new byte[]{8, 0}});
        root.addPath(FileType.ORF, new byte[]{"IIRS".getBytes(Charsets.ISO_8859_1), new byte[]{8, 0}});
        root.addPath(FileType.RAF, new byte[]{"FUJIFILMCCD-RAW".getBytes(Charsets.ISO_8859_1)});
        root.addPath(FileType.RW2, new byte[]{"II".getBytes(Charsets.ISO_8859_1), new byte[]{85, 0}});
    }

    private FileTypeDetector() {
    }

    public static FileType detectFileType(BufferedInputStream inputStream) throws IOException {
        if (!inputStream.markSupported()) {
            throw new IOException("Stream must support mark/reset");
        }
        int maxByteCount = root.getMaxDepth();
        inputStream.mark(maxByteCount);
        byte[] bytes = new byte[maxByteCount];
        int bytesRead = inputStream.read(bytes);
        if (bytesRead == -1) {
            throw new IOException("Stream ended before file's magic number could be determined.");
        }
        inputStream.reset();
        return root.find(bytes);
    }

    public static FileType detectFileType(byte[] fileBytes) throws IOException {
        return root.find(fileBytes);
    }
}
