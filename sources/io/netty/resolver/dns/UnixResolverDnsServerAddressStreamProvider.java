package io.netty.resolver.dns;

import io.netty.util.NetUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.SocketUtils;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-dns-4.1.42.Final.jar:io/netty/resolver/dns/UnixResolverDnsServerAddressStreamProvider.class */
public final class UnixResolverDnsServerAddressStreamProvider implements DnsServerAddressStreamProvider {
    private static final String ETC_RESOLV_CONF_FILE = "/etc/resolv.conf";
    private static final String ETC_RESOLVER_DIR = "/etc/resolver";
    private static final String NAMESERVER_ROW_LABEL = "nameserver";
    private static final String SORTLIST_ROW_LABEL = "sortlist";
    private static final String OPTIONS_ROW_LABEL = "options";
    private static final String DOMAIN_ROW_LABEL = "domain";
    private static final String SEARCH_ROW_LABEL = "search";
    private static final String PORT_ROW_LABEL = "port";
    private static final String NDOTS_LABEL = "ndots:";
    static final int DEFAULT_NDOTS = 1;
    private final DnsServerAddresses defaultNameServerAddresses;
    private final Map<String, DnsServerAddresses> domainToNameServerStreamMap;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) UnixResolverDnsServerAddressStreamProvider.class);
    private static final Pattern SEARCH_DOMAIN_PATTERN = Pattern.compile("\\s+");

    static DnsServerAddressStreamProvider parseSilently() {
        try {
            UnixResolverDnsServerAddressStreamProvider nameServerCache = new UnixResolverDnsServerAddressStreamProvider(ETC_RESOLV_CONF_FILE, ETC_RESOLVER_DIR);
            return nameServerCache.mayOverrideNameServers() ? nameServerCache : DefaultDnsServerAddressStreamProvider.INSTANCE;
        } catch (Exception e) {
            logger.debug("failed to parse {} and/or {}", ETC_RESOLV_CONF_FILE, ETC_RESOLVER_DIR, e);
            return DefaultDnsServerAddressStreamProvider.INSTANCE;
        }
    }

    public UnixResolverDnsServerAddressStreamProvider(File etcResolvConf, File... etcResolverFiles) throws IOException {
        Map<String, DnsServerAddresses> etcResolvConfMap = parse((File) ObjectUtil.checkNotNull(etcResolvConf, "etcResolvConf"));
        boolean useEtcResolverFiles = (etcResolverFiles == null || etcResolverFiles.length == 0) ? false : true;
        this.domainToNameServerStreamMap = useEtcResolverFiles ? parse(etcResolverFiles) : etcResolvConfMap;
        DnsServerAddresses defaultNameServerAddresses = etcResolvConfMap.get(etcResolvConf.getName());
        if (defaultNameServerAddresses == null) {
            Collection<DnsServerAddresses> values = etcResolvConfMap.values();
            if (values.isEmpty()) {
                throw new IllegalArgumentException(etcResolvConf + " didn't provide any name servers");
            }
            this.defaultNameServerAddresses = values.iterator().next();
        } else {
            this.defaultNameServerAddresses = defaultNameServerAddresses;
        }
        if (useEtcResolverFiles) {
            this.domainToNameServerStreamMap.putAll(etcResolvConfMap);
        }
    }

    public UnixResolverDnsServerAddressStreamProvider(String etcResolvConf, String etcResolverDir) throws IOException {
        this(etcResolvConf == null ? null : new File(etcResolvConf), etcResolverDir == null ? null : new File(etcResolverDir).listFiles());
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001d, code lost:
    
        return r4.defaultNameServerAddresses.stream();
     */
    @Override // io.netty.resolver.dns.DnsServerAddressStreamProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public io.netty.resolver.dns.DnsServerAddressStream nameServerAddressStream(java.lang.String r5) {
        /*
            r4 = this;
        L0:
            r0 = r5
            r1 = 46
            r2 = 1
            int r0 = r0.indexOf(r1, r2)
            r6 = r0
            r0 = r6
            if (r0 < 0) goto L16
            r0 = r6
            r1 = r5
            int r1 = r1.length()
            r2 = 1
            int r1 = r1 - r2
            if (r0 != r1) goto L1e
        L16:
            r0 = r4
            io.netty.resolver.dns.DnsServerAddresses r0 = r0.defaultNameServerAddresses
            io.netty.resolver.dns.DnsServerAddressStream r0 = r0.stream()
            return r0
        L1e:
            r0 = r4
            java.util.Map<java.lang.String, io.netty.resolver.dns.DnsServerAddresses> r0 = r0.domainToNameServerStreamMap
            r1 = r5
            java.lang.Object r0 = r0.get(r1)
            io.netty.resolver.dns.DnsServerAddresses r0 = (io.netty.resolver.dns.DnsServerAddresses) r0
            r7 = r0
            r0 = r7
            if (r0 == 0) goto L35
            r0 = r7
            io.netty.resolver.dns.DnsServerAddressStream r0 = r0.stream()
            return r0
        L35:
            r0 = r5
            r1 = r6
            r2 = 1
            int r1 = r1 + r2
            java.lang.String r0 = r0.substring(r1)
            r5 = r0
            goto L0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.resolver.dns.UnixResolverDnsServerAddressStreamProvider.nameServerAddressStream(java.lang.String):io.netty.resolver.dns.DnsServerAddressStream");
    }

    private boolean mayOverrideNameServers() {
        return (this.domainToNameServerStreamMap.isEmpty() && this.defaultNameServerAddresses.stream().next() == null) ? false : true;
    }

    private static Map<String, DnsServerAddresses> parse(File... etcResolverFiles) throws IOException {
        char c;
        Map<String, DnsServerAddresses> domainToNameServerStreamMap = new HashMap<>(etcResolverFiles.length << 1);
        for (File etcResolverFile : etcResolverFiles) {
            if (etcResolverFile.isFile()) {
                FileReader fr = new FileReader(etcResolverFile);
                BufferedReader br = null;
                try {
                    br = new BufferedReader(fr);
                    List<InetSocketAddress> addresses = new ArrayList<>(2);
                    String domainName = etcResolverFile.getName();
                    int port = 53;
                    while (true) {
                        String line = br.readLine();
                        if (line != null) {
                            String line2 = line.trim();
                            if (!line2.isEmpty() && (c = line2.charAt(0)) != '#' && c != ';') {
                                if (line2.startsWith(NAMESERVER_ROW_LABEL)) {
                                    int i = StringUtil.indexOfNonWhiteSpace(line2, NAMESERVER_ROW_LABEL.length());
                                    if (i < 0) {
                                        throw new IllegalArgumentException("error parsing label nameserver in file " + etcResolverFile + ". value: " + line2);
                                    }
                                    String maybeIP = line2.substring(i);
                                    if (!NetUtil.isValidIpV4Address(maybeIP) && !NetUtil.isValidIpV6Address(maybeIP)) {
                                        int i2 = maybeIP.lastIndexOf(46);
                                        if (i2 + 1 >= maybeIP.length()) {
                                            throw new IllegalArgumentException("error parsing label nameserver in file " + etcResolverFile + ". invalid IP value: " + line2);
                                        }
                                        port = Integer.parseInt(maybeIP.substring(i2 + 1));
                                        maybeIP = maybeIP.substring(0, i2);
                                    }
                                    addresses.add(SocketUtils.socketAddress(maybeIP, port));
                                } else if (line2.startsWith(DOMAIN_ROW_LABEL)) {
                                    int i3 = StringUtil.indexOfNonWhiteSpace(line2, DOMAIN_ROW_LABEL.length());
                                    if (i3 < 0) {
                                        throw new IllegalArgumentException("error parsing label domain in file " + etcResolverFile + " value: " + line2);
                                    }
                                    domainName = line2.substring(i3);
                                    if (!addresses.isEmpty()) {
                                        putIfAbsent(domainToNameServerStreamMap, domainName, addresses);
                                    }
                                    addresses = new ArrayList<>(2);
                                } else if (line2.startsWith("port")) {
                                    int i4 = StringUtil.indexOfNonWhiteSpace(line2, "port".length());
                                    if (i4 < 0) {
                                        throw new IllegalArgumentException("error parsing label port in file " + etcResolverFile + " value: " + line2);
                                    }
                                    port = Integer.parseInt(line2.substring(i4));
                                } else if (line2.startsWith(SORTLIST_ROW_LABEL)) {
                                    logger.info("row type {} not supported. ignoring line: {}", SORTLIST_ROW_LABEL, line2);
                                }
                            }
                        } else if (!addresses.isEmpty()) {
                            putIfAbsent(domainToNameServerStreamMap, domainName, addresses);
                        }
                    }
                } finally {
                    if (br == null) {
                        fr.close();
                    } else {
                        br.close();
                    }
                }
            }
        }
        return domainToNameServerStreamMap;
    }

    private static void putIfAbsent(Map<String, DnsServerAddresses> domainToNameServerStreamMap, String domainName, List<InetSocketAddress> addresses) {
        putIfAbsent(domainToNameServerStreamMap, domainName, DnsServerAddresses.sequential(addresses));
    }

    private static void putIfAbsent(Map<String, DnsServerAddresses> domainToNameServerStreamMap, String domainName, DnsServerAddresses addresses) {
        DnsServerAddresses existingAddresses = domainToNameServerStreamMap.put(domainName, addresses);
        if (existingAddresses != null) {
            domainToNameServerStreamMap.put(domainName, existingAddresses);
            logger.debug("Domain name {} already maps to addresses {} so new addresses {} will be discarded", domainName, existingAddresses, addresses);
        }
    }

    static int parseEtcResolverFirstNdots() throws IOException {
        return parseEtcResolverFirstNdots(new File(ETC_RESOLV_CONF_FILE));
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0033, code lost:
    
        r0 = r0 + io.netty.resolver.dns.UnixResolverDnsServerAddressStreamProvider.NDOTS_LABEL.length();
        r0 = r0.indexOf(32, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x004c, code lost:
    
        if (r0 >= 0) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x004f, code lost:
    
        r2 = r0.length();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0056, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0058, code lost:
    
        r0 = java.lang.Integer.parseInt(r0.substring(r0, r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0061, code lost:
    
        if (r6 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0064, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x006b, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0071, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0026, code lost:
    
        r0 = r0.indexOf(io.netty.resolver.dns.UnixResolverDnsServerAddressStreamProvider.NDOTS_LABEL);
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0030, code lost:
    
        if (r0 < 0) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static int parseEtcResolverFirstNdots(java.io.File r4) throws java.io.IOException {
        /*
            java.io.FileReader r0 = new java.io.FileReader
            r1 = r0
            r2 = r4
            r1.<init>(r2)
            r5 = r0
            r0 = 0
            r6 = r0
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L84
            r1 = r0
            r2 = r5
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L84
            r6 = r0
        L14:
            r0 = r6
            java.lang.String r0 = r0.readLine()     // Catch: java.lang.Throwable -> L84
            r1 = r0
            r7 = r1
            if (r0 == 0) goto L72
            r0 = r7
            java.lang.String r1 = "options"
            boolean r0 = r0.startsWith(r1)     // Catch: java.lang.Throwable -> L84
            if (r0 == 0) goto L14
            r0 = r7
            java.lang.String r1 = "ndots:"
            int r0 = r0.indexOf(r1)     // Catch: java.lang.Throwable -> L84
            r8 = r0
            r0 = r8
            if (r0 < 0) goto L72
            r0 = r8
            java.lang.String r1 = "ndots:"
            int r1 = r1.length()     // Catch: java.lang.Throwable -> L84
            int r0 = r0 + r1
            r8 = r0
            r0 = r7
            r1 = 32
            r2 = r8
            int r0 = r0.indexOf(r1, r2)     // Catch: java.lang.Throwable -> L84
            r9 = r0
            r0 = r7
            r1 = r8
            r2 = r9
            if (r2 >= 0) goto L56
            r2 = r7
            int r2 = r2.length()     // Catch: java.lang.Throwable -> L84
            goto L58
        L56:
            r2 = r9
        L58:
            java.lang.String r0 = r0.substring(r1, r2)     // Catch: java.lang.Throwable -> L84
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Throwable -> L84
            r10 = r0
            r0 = r6
            if (r0 != 0) goto L6b
            r0 = r5
            r0.close()
            goto L6f
        L6b:
            r0 = r6
            r0.close()
        L6f:
            r0 = r10
            return r0
        L72:
            r0 = r6
            if (r0 != 0) goto L7d
            r0 = r5
            r0.close()
            goto L98
        L7d:
            r0 = r6
            r0.close()
            goto L98
        L84:
            r11 = move-exception
            r0 = r6
            if (r0 != 0) goto L91
            r0 = r5
            r0.close()
            goto L95
        L91:
            r0 = r6
            r0.close()
        L95:
            r0 = r11
            throw r0
        L98:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.resolver.dns.UnixResolverDnsServerAddressStreamProvider.parseEtcResolverFirstNdots(java.io.File):int");
    }

    static List<String> parseEtcResolverSearchDomains() throws IOException {
        return parseEtcResolverSearchDomains(new File(ETC_RESOLV_CONF_FILE));
    }

    static List<String> parseEtcResolverSearchDomains(File etcResolvConf) throws IOException {
        String localDomain = null;
        List<String> searchDomains = new ArrayList<>();
        FileReader fr = new FileReader(etcResolvConf);
        BufferedReader br = null;
        try {
            br = new BufferedReader(fr);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                if (localDomain == null && line.startsWith(DOMAIN_ROW_LABEL)) {
                    int i = StringUtil.indexOfNonWhiteSpace(line, DOMAIN_ROW_LABEL.length());
                    if (i >= 0) {
                        localDomain = line.substring(i);
                    }
                } else if (line.startsWith(SEARCH_ROW_LABEL)) {
                    int i2 = StringUtil.indexOfNonWhiteSpace(line, SEARCH_ROW_LABEL.length());
                    if (i2 >= 0) {
                        String[] domains = SEARCH_DOMAIN_PATTERN.split(line.substring(i2));
                        Collections.addAll(searchDomains, domains);
                    }
                }
            }
            if (br == null) {
                fr.close();
            } else {
                br.close();
            }
            return (localDomain == null || !searchDomains.isEmpty()) ? searchDomains : Collections.singletonList(localDomain);
        } catch (Throwable th) {
            if (br == null) {
                fr.close();
            } else {
                br.close();
            }
            throw th;
        }
    }
}
