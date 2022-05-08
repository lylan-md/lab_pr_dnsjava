import org.xbill.DNS.*;
import org.xbill.DNS.Record;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DNSResolver {
    private SimpleResolver simpleResolver;
    public DNSResolver() throws UnknownHostException {
        this.simpleResolver = new SimpleResolver("8.8.8.8");
    }
    public String resolveHostName(String hostName) throws Exception {
        Lookup lookup = new Lookup(hostName, Type.A);
        lookup.setResolver(this.simpleResolver);
        Record[] records = lookup.run();

        if (records == null) {
            throw new Exception("Host name not found!");
        }

        ARecord aRecord = (ARecord) records[0];
        return aRecord.getAddress().getHostAddress();
    }

    public String[] findHostsByIp(String ip) throws Exception {
        Lookup lookup = new Lookup(ReverseMap.fromAddress(InetAddress.getByName(ip)), Type.PTR);
        lookup.setResolver(this.simpleResolver);
        Record[] records = lookup.run();

        if (records == null) {
            throw new Exception("Hosts not found!");
        }

        String[] result = new String[records.length];

        for (int i = 0; i < records.length; i++) {
            PTRRecord ptrRecord = (PTRRecord) records[i];
            result[i] = ptrRecord.getTarget().toString();
        }

        return result;
    }

    public String getResolverAddress() {
        return this.simpleResolver.getAddress().toString();
    }

    public void setResolverAddress(String address) throws Exception {
        InetAddress inetAddress = InetAddress.getByName(address);

        if (!inetAddress.isReachable(5)) {
            throw new Exception("Host not reachable");
        }

        this.simpleResolver.setAddress(inetAddress);
    }
}
