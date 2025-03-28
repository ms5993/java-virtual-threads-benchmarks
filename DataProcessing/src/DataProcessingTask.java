import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataProcessingTask implements Callable<Void> {
    private static final Logger logger = Logger.getLogger(DataProcessingTask.class.getName());
    private final int taskId;
    private final AggregatedResult aggregatedResult;

    public DataProcessingTask(int taskId, AggregatedResult aggregatedResult) {
        this.taskId = taskId;
        this.aggregatedResult = aggregatedResult;
    }

    @Override
    public Void call() {
        logger.log(Level.INFO, "Thread {0} - Task {1} started.",
                new Object[] { Thread.currentThread().getName(), taskId });

        for (int i = 0; i < 100_000; i++) {

            DataPacket packet = new DataPacket(i, "Data from task " + taskId + ", packet " + i);

            String processedData = packet.getData().replace("Data", "ProcessedData");

            aggregatedResult.addResult(packet.getId(), processedData);
        }

        logger.log(Level.INFO, "Thread {0} - Task {1} finished.",
                new Object[] { Thread.currentThread().getName(), taskId });

        return null;
    }
}