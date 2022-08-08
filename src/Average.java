import java.util.Queue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

class Average implements Runnable {
    private Thread t;
    private int threadNo;
    private Queue<CsvRow> queue;
    private Queue<CsvRow> results;
    private CyclicBarrier x;

    Average(int threadNo, Queue<CsvRow> queue, Queue<CsvRow> results, CyclicBarrier x) {
        this.threadNo = threadNo;
        this.queue = queue;
        this.results = results;
        this.x = x;
    }

    public void run() {
        try {
            System.out.println("Running " + threadNo);
            while (!queue.isEmpty()) {
                CsvRow data = this.queue.poll();
                float average = (data.getBalance() + data.getPrevBalance()) / 2;
                data.setAverage(average);
                data.setno1threadid(String.valueOf(this.threadNo));
                results.add(data);
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
