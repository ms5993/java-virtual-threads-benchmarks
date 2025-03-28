import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainVT {
    public static void main(String[] args) {
        int numTasks = 1_000; // Number of tasks to simulate
        long totalStartTime = System.currentTimeMillis();

        for (int j = 0; j < 10; j++) { // Run for 5 seconds
            try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
                List<WebScrapingTask> tasks = new ArrayList<>();
                for (int i = 0; i < numTasks; i++) {
                    tasks.add(new WebScrapingTask(i));
                }

                long time = System.currentTimeMillis();

                List<Future<String>> futures = executor.invokeAll(tasks);

                for (Future<String> future : futures) {
                    future.get();
                }
                time = System.currentTimeMillis() - time;

                System.out.println("Total time with virtual threads: " + time + " ms");

            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        long totalEndTime = System.currentTimeMillis();

        System.out.println("Total benchmark time: " + (totalEndTime - totalStartTime) + " ms");

    }
}