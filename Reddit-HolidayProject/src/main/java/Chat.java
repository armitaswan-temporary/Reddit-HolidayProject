import java.util.UUID;
import java.util.ArrayList;

public class Chat {
    private UUID uniqueName;
    private Account you;
    private Account them;
    private ArrayList<Pair<Boolean, String>> messages;

    public Chat(Account you, Account them) {
        uniqueName = UUID.randomUUID();
        this.you = you;
        this.them = them;
        messages = new ArrayList<>();
    }

    public UUID getUniqueName() {
        return uniqueName;
    }
    public ArrayList<Pair<Boolean, String>> getMessages() {
        return messages;
    }
    public Account getThem() {
        return them;
    }
}
