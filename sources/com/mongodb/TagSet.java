package com.mongodb;

import com.mongodb.annotations.Immutable;
import com.mongodb.assertions.Assertions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/TagSet.class */
public final class TagSet implements Iterable<Tag> {
    private final List<Tag> wrapped;

    public TagSet() {
        this.wrapped = Collections.emptyList();
    }

    public TagSet(Tag tag) {
        Assertions.notNull("tag", tag);
        this.wrapped = Collections.singletonList(tag);
    }

    public TagSet(List<Tag> tagList) {
        Assertions.notNull("tagList", tagList);
        Set<String> tagNames = new HashSet<>();
        for (Tag tag : tagList) {
            if (tag == null) {
                throw new IllegalArgumentException("Null tags are not allowed");
            }
            if (!tagNames.add(tag.getName())) {
                throw new IllegalArgumentException("Duplicate tag names not allowed in a tag set: " + tag.getName());
            }
        }
        this.wrapped = Collections.unmodifiableList(new ArrayList(tagList));
    }

    @Override // java.lang.Iterable
    public Iterator<Tag> iterator() {
        return this.wrapped.iterator();
    }

    public boolean containsAll(TagSet tagSet) {
        return this.wrapped.containsAll(tagSet.wrapped);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TagSet tags = (TagSet) o;
        if (!this.wrapped.equals(tags.wrapped)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.wrapped.hashCode();
    }

    public String toString() {
        return "TagSet{" + this.wrapped + '}';
    }
}
