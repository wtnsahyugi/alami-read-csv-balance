import java.util.Queue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class Bonus extends Thread {
    private Thread t;
    private int threadNo;
    private Queue<CsvRow> results;
    private Queue<CsvRow> results2;
    private CyclicBarrier x;
    private int budget;

    Bonus(int threadNo, Queue<CsvRow> results, Queue<CsvRow> results2, CyclicBarrier x, int budget) {
        this.threadNo = threadNo;
        this.results = results;
        this.results2 = results2;
        this.x = x;
        this.budget = budget;
    }

    public void run() {
        try {
            System.out.println("Running " + threadNo);
            while (!this.results.isEmpty()) {
                CsvRow data = this.results.poll();
                if (budget != 0) {
                    data.setbalance(String.valueOf(data.getBalance() + 10));
                    budget -= 10;
                    data.setno3threadid(String.valueOf(this.threadNo));
                }
                this.results2.add(data);
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
}
