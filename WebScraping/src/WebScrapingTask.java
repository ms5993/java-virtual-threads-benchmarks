import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebScrapingTask implements Callable<String> {
    private static final Logger logger = Logger.getLogger(WebScrapingTask.class.getName());
    private final int taskId;

    public WebScrapingTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String call() {

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String data = "Data from task " + taskId;
        for (int i = 0; i < 10_000; i++) {
            data = data.replace("task", "processedTask");
        }

        return data;
    }
}