import java.io.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Csvconverter {
    public Queue<CsvRow> convert() {
        Queue<CsvRow> orders = new ConcurrentLinkedQueue<>();
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("before_eod.csv"));
            br.readLine(); // this will read the first line
            String line1 = null;
            while ((line1 = br.readLine()) != null) {
                String[] res = line1.split(";");
                CsvRow data = new CsvRow(res[0], res[1], res[2], res[3], res[4], res[6]);
                orders.add(data);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }
}
