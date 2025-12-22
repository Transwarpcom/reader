package cn.hutool.core.net;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/net/UserPassAuthenticator.class */
public class UserPassAuthenticator extends Authenticator {
    private final String user;
    private final char[] pass;

    public UserPassAuthenticator(String user, char[] pass) {
        this.user = user;
        this.pass = pass;
    }

    @Override // java.net.Authenticator
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.user, this.pass);
    }
}
