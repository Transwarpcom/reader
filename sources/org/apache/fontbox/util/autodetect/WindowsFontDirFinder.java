package org.apache.fontbox.util.autodetect;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.fontbox.util.Charsets;

/* loaded from: reader.jar:BOOT-INF/lib/fontbox-2.0.27.jar:org/apache/fontbox/util/autodetect/WindowsFontDirFinder.class */
public class WindowsFontDirFinder implements FontDirFinder {
    private String getWinDir(String osName) throws IOException {
        Process process;
        Runtime runtime = Runtime.getRuntime();
        if (osName.startsWith("Windows 9")) {
            process = runtime.exec("command.com /c echo %windir%");
        } else {
            process = runtime.exec("cmd.exe /c echo %windir%");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charsets.ISO_8859_1));
        String winDir = bufferedReader.readLine();
        bufferedReader.close();
        return winDir;
    }

    @Override // org.apache.fontbox.util.autodetect.FontDirFinder
    public List<File> find() {
        List<File> fontDirList = new ArrayList<>();
        String windir = null;
        try {
            windir = System.getProperty("env.windir");
        } catch (SecurityException e) {
        }
        String osName = System.getProperty("os.name");
        if (windir == null) {
            try {
                windir = getWinDir(osName);
            } catch (IOException e2) {
            } catch (SecurityException e3) {
            }
        }
        if (windir != null && windir.length() > 2) {
            if (windir.endsWith("/")) {
                windir = windir.substring(0, windir.length() - 1);
            }
            File osFontsDir = new File(windir + File.separator + "FONTS");
            if (osFontsDir.exists() && osFontsDir.canRead()) {
                fontDirList.add(osFontsDir);
            }
            File psFontsDir = new File(windir.substring(0, 2) + File.separator + "PSFONTS");
            if (psFontsDir.exists() && psFontsDir.canRead()) {
                fontDirList.add(psFontsDir);
            }
        } else {
            String windowsDirName = osName.endsWith("NT") ? "WINNT" : "WINDOWS";
            char c = 'C';
            while (true) {
                char driveLetter = c;
                if (driveLetter > 'E') {
                    break;
                }
                File osFontsDir2 = new File(driveLetter + ":" + File.separator + windowsDirName + File.separator + "FONTS");
                if (!osFontsDir2.exists() || !osFontsDir2.canRead()) {
                    c = (char) (driveLetter + 1);
                } else {
                    fontDirList.add(osFontsDir2);
                    break;
                }
            }
            char c2 = 'C';
            while (true) {
                char driveLetter2 = c2;
                if (driveLetter2 <= 'E') {
                    File psFontsDir2 = new File(driveLetter2 + ":" + File.separator + "PSFONTS");
                    if (!psFontsDir2.exists() || !psFontsDir2.canRead()) {
                        c2 = (char) (driveLetter2 + 1);
                    } else {
                        fontDirList.add(psFontsDir2);
                    }
                }
            }
        }
        try {
            String localAppData = System.getenv("LOCALAPPDATA");
            if (localAppData != null && !localAppData.isEmpty()) {
                File localFontDir = new File(localAppData + File.separator + "Microsoft" + File.separator + "Windows" + File.separator + "Fonts");
                if (localFontDir.exists() && localFontDir.canRead()) {
                    fontDirList.add(localFontDir);
                }
            }
        } catch (SecurityException e4) {
        }
        return fontDirList;
    }
}
