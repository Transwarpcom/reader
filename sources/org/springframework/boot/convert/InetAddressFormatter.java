package org.springframework.boot.convert;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/convert/InetAddressFormatter.class */
final class InetAddressFormatter implements Formatter<InetAddress> {
    InetAddressFormatter() {
    }

    @Override // org.springframework.format.Printer
    public String print(InetAddress object, Locale locale) {
        return object.getHostAddress();
    }

    @Override // org.springframework.format.Parser
    public InetAddress parse(String text, Locale locale) throws ParseException {
        try {
            return InetAddress.getByName(text);
        } catch (UnknownHostException ex) {
            throw new IllegalStateException("Unknown host " + text, ex);
        }
    }
}
