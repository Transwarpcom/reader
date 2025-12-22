package cn.hutool.core.swing;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.URLUtil;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/swing/DesktopUtil.class */
public class DesktopUtil {
    public static Desktop getDsktop() {
        return Desktop.getDesktop();
    }

    public static void browse(String url) {
        browse(URLUtil.toURI(url));
    }

    public static void browse(URI uri) {
        Desktop dsktop = getDsktop();
        try {
            dsktop.browse(uri);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    public static void open(File file) {
        Desktop dsktop = getDsktop();
        try {
            dsktop.open(file);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    public static void edit(File file) {
        Desktop dsktop = getDsktop();
        try {
            dsktop.edit(file);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    public static void print(File file) {
        Desktop dsktop = getDsktop();
        try {
            dsktop.print(file);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    public static void mail(String mailAddress) {
        Desktop dsktop = getDsktop();
        try {
            dsktop.mail(URLUtil.toURI(mailAddress));
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }
}
