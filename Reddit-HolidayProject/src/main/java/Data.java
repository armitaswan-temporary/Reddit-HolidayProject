import java.util.ArrayList;
import java.util.UUID;

public class Data {
    static ArrayList<Account> allAccounts;
    static ArrayList<UUID> allAccountsUUID;
    static ArrayList<Post> allPosts;
    static ArrayList<UUID> allPostsUUID;
    static ArrayList<Subreddit> allSubreddits;
    static ArrayList<UUID> allSubredditsUUID;
    static ArrayList<String> allTags;
    static ArrayList<Chat> allChats;
    static ArrayList<UUID> allChatsUUID;

    public Data() {
        allAccounts = new ArrayList<>();
        allAccountsUUID = new ArrayList<>();
        allPosts = new ArrayList<>();
        allPostsUUID = new ArrayList<>();
        allSubreddits = new ArrayList<>();
        allSubredditsUUID = new ArrayList<>();
        allTags = new ArrayList<>();
        allChats = new ArrayList<>();
        allChatsUUID = new ArrayList<>();
    }

    public static void setAllAccounts(Account account) {
        allAccounts.add(account);
        allAccountsUUID.add(account.getUsername());
    }
    public static void setAllPosts(Post post) {
        allPosts.add(post);
        allPostsUUID.add(post.getUniqueName());
    }
    public static void setAllSubreddits(Subreddit subreddit) {
        allSubreddits.add(subreddit);
        allSubredditsUUID.add(subreddit.getUniqueName());
    }
    public static void setAllTags(Post post) {
        ArrayList<String> tags = post.getTags();
        for (int i = 0; i < tags.size(); i++) {
            allTags.add(tags.get(i));
        }
    }
    public static void setAllChats(Chat chat) {
        allChats.add(chat);
        allChatsUUID.add(chat.getUniqueName());
    }
    public static ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }
    public static ArrayList<UUID> getAllAccountsUUID() {
        return allAccountsUUID;
    }
    public static ArrayList<Post> getAllPosts() {
        return allPosts;
    }
    public static ArrayList<UUID> getAllPostsUUID() {
        return allPostsUUID;
    }
    public static ArrayList<Subreddit> getAllSubreddits() {
        return allSubreddits;
    }
    public static ArrayList<UUID> getAllSubredditsUUID() {
        return allSubredditsUUID;
    }
    public static ArrayList<String> getAllTags() {
        return allTags;
    }
    public static ArrayList<Chat> getAllChats() {
        return allChats;
    }
    public static ArrayList<UUID> getAllChatsUUID() {
        return allChatsUUID;
    }
}
