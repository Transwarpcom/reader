package cn.hutool.core.io.resource;

import cn.hutool.core.io.FileUtil;
import java.io.File;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/io/resource/WebAppResource.class */
public class WebAppResource extends FileResource {
    private static final long serialVersionUID = 1;

    public WebAppResource(String path) {
        super(new File(FileUtil.getWebRoot(), path));
    }
}
