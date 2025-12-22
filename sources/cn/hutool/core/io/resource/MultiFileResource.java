package cn.hutool.core.io.resource;

import java.io.File;
import java.util.Collection;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/io/resource/MultiFileResource.class */
public class MultiFileResource extends MultiResource {
    private static final long serialVersionUID = 1;

    public MultiFileResource(Collection<File> files) {
        super(new Resource[0]);
        add(files);
    }

    public MultiFileResource(File... files) {
        super(new Resource[0]);
        add(files);
    }

    public MultiFileResource add(File... files) {
        for (File file : files) {
            add((Resource) new FileResource(file));
        }
        return this;
    }

    public MultiFileResource add(Collection<File> files) {
        for (File file : files) {
            add((Resource) new FileResource(file));
        }
        return this;
    }

    @Override // cn.hutool.core.io.resource.MultiResource
    public MultiFileResource add(Resource resource) {
        return (MultiFileResource) super.add(resource);
    }
}
