import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        DNSResolver dnsResolver;
        BufferedReader bufferedReader;
        MenuHelper menuHelper;

        try {
            dnsResolver = new DNSResolver();
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            menuHelper = new MenuHelper(bufferedReader);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        boolean stopMenu = false;

        while (!stopMenu) {
            System.out.flush();
            System.out.println(String.join("\r\n", menuHelper.getMenuItems("Address of resolver: " + dnsResolver.getResolverAddress())));

            try {
                switch (menuHelper.readCommandNr()) {
                    case 1 -> {
                        System.out.println("Write ip");
                        String ipInput = bufferedReader.readLine();
                        dnsResolver.setResolverAddress(ipInput);
                    }
                    case 2 -> {
                        System.out.println("Write hostname");
                        String hostName = bufferedReader.readLine();
                        System.out.println(dnsResolver.resolveHostName(hostName));
                    }
                    case 3 -> {
                        System.out.println("Write ip");
                        String ipInput = bufferedReader.readLine();
                        System.out.println(String.join("\r\n", dnsResolver.findHostsByIp(ipInput)));
                    }
                    case 4 -> stopMenu = true;
                    default -> System.out.println("Unknown command!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
