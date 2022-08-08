
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.opencsv.CSVWriter;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            int threadNum = 5;
            int threadNumNo3 = 9;
            int budget = 1000;
            Csvconverter converter = new Csvconverter();
            Queue<CsvRow> queue = converter.convert();
            Queue<CsvRow> results = new ConcurrentLinkedQueue<>();
            Queue<CsvRow> results2 = new ConcurrentLinkedQueue<>();
            CyclicBarrier x = new CyclicBarrier(threadNum);
            CyclicBarrier z = new CyclicBarrier(threadNumNo3);

            for (int i = 0; i < threadNum; i++) {
                Average runAvg = new Average(i, queue, results, x);
                runAvg.start();
                Benefit benefit = new Benefit(i, results, x);
                benefit.start();
            }
            x.await();

            for (int i = 0; i < threadNumNo3; i++) {
                Bonus bonus = new Bonus(i, results, results2, z, budget);
                bonus.start();
            }
            z.await();

            List<String[]> dataList = new ArrayList<String[]>();
            dataList.add(new String[] { "id", "nama", "age", "balance", "No 2b Thread-No", "No 3 Thread-No",
                    "Previous Balanced", "Average Balanced", "No 1 Thread-No", "Free Transfer",
                    "No 2a Thread-No" });
            for (CsvRow c : results2) {
                if (c == null) {
                    continue;
                }
                System.out.println("write " + c.getName());
                dataList.add(
                        new String[] { c.getId(), c.getName(), c.getAge(), String.valueOf(c.getBalance()),
                                c.getno2bthreadid(),
                                c.getno3threadid(),
                                String.valueOf(c.getPrevBalance()), String.valueOf(c.getAverage()), c.getno1threadid(),
                                c.getFreetf(), c.getno2athreadid()
                        });
            }

            writeDataAtOnce("after_eod.csv", dataList);

            System.out.println("finish");
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeDataAtOnce(String filePath, List<String[]> data) {
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeAll(data);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
