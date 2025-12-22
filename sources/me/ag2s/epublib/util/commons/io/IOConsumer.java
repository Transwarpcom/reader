package me.ag2s.epublib.util.commons.io;

import java.io.IOException;
import java.util.Objects;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/classes/me/ag2s/epublib/util/commons/io/IOConsumer.class */
public interface IOConsumer<T> {
    void accept(T t) throws IOException;

    default IOConsumer<T> andThen(final IOConsumer<? super T> after) {
        Objects.requireNonNull(after);
        return t -> {
            accept(t);
            after.accept(t);
        };
    }
}
