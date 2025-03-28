public class DataPacket {
    private final int id;
    private final String data;

    public DataPacket(int id, String data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }
}