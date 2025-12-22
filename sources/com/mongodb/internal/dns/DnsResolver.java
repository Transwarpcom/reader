package com.mongodb.internal.dns;

import com.mongodb.MongoClientException;
import com.mongodb.MongoConfigurationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/dns/DnsResolver.class */
public final class DnsResolver {
    public static List<String> resolveHostFromSrvRecords(String srvHost) {
        if (srvHost.split("\\.").length < 3) {
            throw new IllegalArgumentException(String.format("An SRV host name '%s' was provided that does not contain at least three parts. It must contain a hostname, domain name and a top level domain.", srvHost));
        }
        String srvHostDomain = srvHost.substring(srvHost.indexOf(46) + 1);
        List<String> srvHostDomainParts = Arrays.asList(srvHostDomain.split("\\."));
        List<String> hosts = new ArrayList<>();
        InitialDirContext dirContext = createDnsDirContext();
        try {
            try {
                Attributes attributes = dirContext.getAttributes("_mongodb._tcp." + srvHost, new String[]{"SRV"});
                Attribute attribute = attributes.get("SRV");
                if (attribute == null) {
                    throw new MongoClientException("No SRV record available for host " + srvHost);
                }
                NamingEnumeration<?> srvRecordEnumeration = attribute.getAll();
                while (srvRecordEnumeration.hasMore()) {
                    String srvRecord = (String) srvRecordEnumeration.next();
                    String[] split = srvRecord.split(" ");
                    String resolvedHost = split[3].endsWith(".") ? split[3].substring(0, split[3].length() - 1) : split[3];
                    String resolvedHostDomain = resolvedHost.substring(resolvedHost.indexOf(46) + 1);
                    if (!sameParentDomain(srvHostDomainParts, resolvedHostDomain)) {
                        throw new MongoConfigurationException(String.format("The SRV host name '%s'resolved to a host '%s 'that is not in a sub-domain of the SRV host.", srvHost, resolvedHost));
                    }
                    hosts.add(resolvedHost + ":" + split[2]);
                }
                return hosts;
            } catch (NamingException e) {
                throw new MongoConfigurationException("Unable to look up SRV record for host " + srvHost, e);
            }
        } finally {
            try {
                dirContext.close();
            } catch (NamingException e2) {
            }
        }
    }

    private static boolean sameParentDomain(List<String> srvHostDomainParts, String resolvedHostDomain) {
        List<String> resolvedHostDomainParts = Arrays.asList(resolvedHostDomain.split("\\."));
        if (srvHostDomainParts.size() > resolvedHostDomainParts.size()) {
            return false;
        }
        return resolvedHostDomainParts.subList(resolvedHostDomainParts.size() - srvHostDomainParts.size(), resolvedHostDomainParts.size()).equals(srvHostDomainParts);
    }

    public static String resolveAdditionalQueryParametersFromTxtRecords(String host) {
        String additionalQueryParameters = "";
        InitialDirContext dirContext = createDnsDirContext();
        try {
            try {
                Attributes attributes = dirContext.getAttributes(host, new String[]{"TXT"});
                Attribute attribute = attributes.get("TXT");
                if (attribute != null) {
                    NamingEnumeration<?> txtRecordEnumeration = attribute.getAll();
                    if (txtRecordEnumeration.hasMore()) {
                        additionalQueryParameters = ((String) txtRecordEnumeration.next()).replaceAll("\\s", "");
                        if (txtRecordEnumeration.hasMore()) {
                            throw new MongoConfigurationException(String.format("Multiple TXT records found for host '%s'.  Only one is permitted", host));
                        }
                    }
                }
                return additionalQueryParameters;
            } catch (NamingException e) {
                throw new MongoConfigurationException("Unable to look up TXT record for host " + host, e);
            }
        } finally {
            try {
                dirContext.close();
            } catch (NamingException e2) {
            }
        }
    }

    private static InitialDirContext createDnsDirContext() {
        Hashtable<String, String> envProps = new Hashtable<>();
        envProps.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
        try {
            return new InitialDirContext(envProps);
        } catch (NamingException e) {
            throw new MongoClientException("Unable to create JNDI context for resolving SRV records. The 'com.sun.jndi.dns.DnsContextFactory' class is not available in this JRE", e);
        }
    }

    private DnsResolver() {
    }
}
