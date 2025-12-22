package cn.hutool.core.swing.clipboard;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/swing/clipboard/ClipboardListener.class */
public interface ClipboardListener {
    Transferable onChange(Clipboard clipboard, Transferable transferable);
}
