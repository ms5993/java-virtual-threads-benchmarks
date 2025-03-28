import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortingTask implements Callable<Integer> {
    private static final Logger logger = Logger.getLogger(SortingTask.class.getName());
    private final int number;

    public SortingTask(int number) {
        this.number = number;
    }

    @Override
    public Integer call() {
        logger.log(Level.INFO, "Thread {0} - Task {1} started.",
                new Object[] { Thread.currentThread().getName(), number });

        int[] array = ThreadLocalRandom.current().ints(1_000_000, 1, 100).toArray();

        MergeSort.mergeSort(array);

        double result = 0;
        for (int i = 0; i < 1_000_000; i++) {
            result += Math.sqrt(i);
        }

        int sum = 0;
        for (int num : array) {
            sum += num;
        }

        logger.log(Level.INFO, "Thread {0} - Task {1} finished. Sum: {2}",
                new Object[] { Thread.currentThread().getName(), number, sum });

        return sum;
    }
}
