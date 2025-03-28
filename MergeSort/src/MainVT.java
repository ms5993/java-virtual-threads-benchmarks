import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainVT {
    public static void main(String[] args) {
        // Bellow is the only changed line
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<SortingTask> tasks = new ArrayList<>();
            for (int i = 0; i < 1_000; i++) {
                tasks.add(new SortingTask(i));
            }

            long time = System.currentTimeMillis();

            List<Future<Integer>> futures = executor.invokeAll(tasks); // mora ostat

            long sum = 0;
            for (Future<Integer> future : futures) {
                sum += future.get();
            }
            Thread.sleep(1000);
            time = System.currentTimeMillis() - time;

            System.out.println("sum = " + sum + "; time = " + time + " ms");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}