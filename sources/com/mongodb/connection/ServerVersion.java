package com.mongodb.connection;

import com.mongodb.assertions.Assertions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ServerVersion.class */
public class ServerVersion implements Comparable<ServerVersion> {
    private final List<Integer> versionList;

    public ServerVersion() {
        this.versionList = Collections.unmodifiableList(Arrays.asList(0, 0, 0));
    }

    public ServerVersion(List<Integer> versionList) {
        Assertions.notNull("versionList", versionList);
        Assertions.isTrue("version array has three elements", versionList.size() == 3);
        this.versionList = Collections.unmodifiableList(new ArrayList(versionList));
    }

    public ServerVersion(int majorVersion, int minorVersion) {
        this(Arrays.asList(Integer.valueOf(majorVersion), Integer.valueOf(minorVersion), 0));
    }

    public List<Integer> getVersionList() {
        return this.versionList;
    }

    @Override // java.lang.Comparable
    public int compareTo(ServerVersion o) {
        int retVal = 0;
        for (int i = 0; i < this.versionList.size(); i++) {
            retVal = this.versionList.get(i).compareTo(o.versionList.get(i));
            if (retVal != 0) {
                break;
            }
        }
        return retVal;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServerVersion that = (ServerVersion) o;
        if (!this.versionList.equals(that.versionList)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.versionList.hashCode();
    }

    public String toString() {
        return "ServerVersion{versionList=" + this.versionList + '}';
    }
}
