import java.util.Queue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class Benefit implements Runnable {
    private Thread t;
    private int threadNo;
    private Queue<CsvRow> results;
    private CyclicBarrier x;

    Benefit(int threadNo, Queue<CsvRow> results, CyclicBarrier x) {
        this.threadNo = threadNo;
        this.results = results;
        this.x = x;
    }

    public void run() {
        try {
            System.out.println("Running " + threadNo);
            for (CsvRow data : results) {
                if (data.getBalance() > 150) {
                    float balance = data.getBalance() + 25;
                    data.setbalance(String.valueOf(balance));
                    data.setno2bthreadid(String.valueOf(this.threadNo));
                }

                if (data.getBalance() > 100 && data.getBalance() < 150) {
                    data.setfreetf("5");
                    data.setno2athreadid(String.valueOf(this.threadNo));
                }
            }

            this.x.await();
        } catch (InterruptedException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.println("Starting " + threadNo);
        if (t == null) {
            t = new Thread(this, "thread" + threadNo);
            t.start();
        }
    }
}
