package me.ag2s.epublib.domain;

/* loaded from: reader.jar:BOOT-INF/classes/me/ag2s/epublib/domain/ManifestItemRefProperties.class */
public enum ManifestItemRefProperties implements ManifestProperties {
    PAGE_SPREAD_LEFT("page-spread-left"),
    PAGE_SPREAD_RIGHT("page-spread-right");

    private final String name;

    ManifestItemRefProperties(String name) {
        this.name = name;
    }

    @Override // me.ag2s.epublib.domain.ManifestProperties
    public String getName() {
        return this.name;
    }
}
