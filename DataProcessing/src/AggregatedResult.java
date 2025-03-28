import java.util.HashMap;
import java.util.Map;

public class AggregatedResult {
    private final Map<Integer, String> results = new HashMap<>();

    public void addResult(int id, String data) {
        results.put(id, data);
    }

    public Map<Integer, String> getResults() {
        return results;
    }
}