import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MenuHelper {
    private final BufferedReader bufferedReader;

    public MenuHelper() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public MenuHelper(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public ArrayList<String> getMenuItems(String header) {
        ArrayList<String> menuElements = new ArrayList<>();
        menuElements.add(header);
        menuElements.add("1. Change resolver address");
        menuElements.add("2. Resolve hostname");
        menuElements.add("3. Find hosts by hostname");
        menuElements.add("4. Quit");
        return menuElements;
    }

    public int readCommandNr() {
        try {
            System.out.flush();
            System.out.println("Write command nr!");
            String command = this.bufferedReader.readLine();
            return Integer.parseInt(command);
        } catch (NumberFormatException | IOException e) {
            return -1;
        }
    }
}
