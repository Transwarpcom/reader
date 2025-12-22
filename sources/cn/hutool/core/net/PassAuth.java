package cn.hutool.core.net;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/net/PassAuth.class */
public class PassAuth extends Authenticator {
    private final PasswordAuthentication auth;

    public static PassAuth of(String user, char[] pass) {
        return new PassAuth(user, pass);
    }

    public PassAuth(String user, char[] pass) {
        this.auth = new PasswordAuthentication(user, pass);
    }

    @Override // java.net.Authenticator
    protected PasswordAuthentication getPasswordAuthentication() {
        return this.auth;
    }
}
