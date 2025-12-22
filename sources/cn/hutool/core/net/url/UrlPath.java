package cn.hutool.core.net.url;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.RFC3986;
import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.util.StrUtil;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/net/url/UrlPath.class */
public class UrlPath {
    private List<String> segments;
    private boolean withEngTag;

    public static UrlPath of(CharSequence pathStr, Charset charset) throws IllegalArgumentException {
        UrlPath urlPath = new UrlPath();
        urlPath.parse(pathStr, charset);
        return urlPath;
    }

    public UrlPath setWithEndTag(boolean withEngTag) {
        this.withEngTag = withEngTag;
        return this;
    }

    public List<String> getSegments() {
        return this.segments;
    }

    public String getSegment(int index) {
        if (null == this.segments || index >= this.segments.size()) {
            return null;
        }
        return this.segments.get(index);
    }

    public UrlPath add(CharSequence segment) {
        addInternal(fixPath(segment), false);
        return this;
    }

    public UrlPath addBefore(CharSequence segment) {
        addInternal(fixPath(segment), true);
        return this;
    }

    public UrlPath parse(CharSequence path, Charset charset) throws IllegalArgumentException {
        if (StrUtil.isNotEmpty(path)) {
            if (StrUtil.endWith(path, '/')) {
                this.withEngTag = true;
            }
            CharSequence path2 = fixPath(path);
            if (StrUtil.isNotEmpty(path2)) {
                List<String> split = StrUtil.split(path2, '/');
                for (String seg : split) {
                    addInternal(URLDecoder.decodeForPath(seg, charset), false);
                }
            }
        }
        return this;
    }

    public String build(Charset charset) {
        if (CollUtil.isEmpty((Collection<?>) this.segments)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (String segment : this.segments) {
            if (builder.length() == 0) {
                builder.append('/').append(RFC3986.SEGMENT_NZ_NC.encode(segment, charset));
            } else {
                builder.append('/').append(RFC3986.SEGMENT.encode(segment, charset));
            }
        }
        if (StrUtil.isEmpty(builder)) {
            builder.append('/');
        } else if (this.withEngTag && false == StrUtil.endWith((CharSequence) builder, '/')) {
            builder.append('/');
        }
        return builder.toString();
    }

    public String toString() {
        return build(null);
    }

    private void addInternal(CharSequence segment, boolean before) {
        if (this.segments == null) {
            this.segments = new LinkedList();
        }
        String seg = StrUtil.str(segment);
        if (before) {
            this.segments.add(0, seg);
        } else {
            this.segments.add(seg);
        }
    }

    private static String fixPath(CharSequence path) throws IllegalArgumentException {
        Assert.notNull(path, "Path segment must be not null!", new Object[0]);
        if ("/".contentEquals(path)) {
            return "";
        }
        String segmentStr = StrUtil.trim(path);
        return StrUtil.trim(StrUtil.removeSuffix(StrUtil.removePrefix(segmentStr, "/"), "/"));
    }
}
