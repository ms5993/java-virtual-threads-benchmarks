import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainVT {
    public static void main(String[] args) {
        int numTasks = 10_000;
        AggregatedResult aggregatedResult = new AggregatedResult();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<DataProcessingTask> tasks = new ArrayList<>();
            for (int i = 0; i < numTasks; i++) {
                tasks.add(new DataProcessingTask(i, aggregatedResult));
            }

            long time = System.currentTimeMillis();

            List<Future<Void>> futures = executor.invokeAll(tasks);

            for (Future<Void> future : futures) {
                future.get();
            }
            time = System.currentTimeMillis() - time;

            System.out.println("Total time: " + time + " ms");
            System.out.println("Aggregated results size: " + aggregatedResult.getResults().size());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}