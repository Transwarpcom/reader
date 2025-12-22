package org.springframework.boot.loader.jar;

import org.springframework.boot.loader.data.RandomAccessData;

/* loaded from: reader.jar:org/springframework/boot/loader/jar/CentralDirectoryVisitor.class */
interface CentralDirectoryVisitor {
    void visitStart(CentralDirectoryEndRecord endRecord, RandomAccessData centralDirectoryData);

    void visitFileHeader(CentralDirectoryFileHeader fileHeader, int dataOffset);

    void visitEnd();
}
