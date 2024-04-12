import java.util.ArrayList;
import java.util.UUID;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;


public class Data {
    static ArrayList<Account> allAccounts = new ArrayList<>();
    static ArrayList<UUID> allAccountsUUID = new ArrayList<>();
    static ArrayList<Post> allPosts = new ArrayList<>();
    static ArrayList<UUID> allPostsUUID = new ArrayList<>();
    static ArrayList<Subreddit> allSubreddits = new ArrayList<>();
    static ArrayList<UUID> allSubredditsUUID = new ArrayList<>();
    static ArrayList<String> allTags = new ArrayList<>();
    static ArrayList<Chat> allChats = new ArrayList<>();
    static ArrayList<UUID> allChatsUUID = new ArrayList<>();

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
    public static void deleteAcount(Account account) {
        allAccounts.remove(account);
        allAccountsUUID.remove(account.getUsername());
    }
    public static void deletePost(Post post) {
        allPosts.remove(post);
        allPostsUUID.remove(post.getUniqueName());
    }
    public static void deleteSubreddit(Subreddit subreddit) {
        allSubreddits.remove(subreddit);
        allSubredditsUUID.remove(subreddit.getUniqueName());
    }

    public static Account gettheAccount(UUID username) {
        for (int i = 0; i < getAllAccounts().size(); i++) {
            if (getAllAccounts().get(i).getUsername() == username) {
                return getAllAccounts().get(i);
            }
        }
        return getAllAccounts().get(0);
    }
    public static Post getthePost(UUID uniqueName) {
        for (int i = 0; i < getAllPosts().size(); i++) {
            if (getAllPosts().get(i).getUniqueName() == uniqueName) {
                return getAllPosts().get(i);
            }
        }
        return getAllPosts().get(0);
    }
    public static Subreddit gettheSubreddit(UUID uniqueName) {
        for (int i = 0; i < getAllSubreddits().size(); i++) {
            if (getAllSubreddits().get(i).getUniqueName() == uniqueName) {
                return getAllSubreddits().get(i);
            }
        }
        return getAllSubreddits().get(0);
    }
    public static Chat gettheChat(UUID uniqueName) {
        for (int i = 0; i < getAllChats().size(); i++) {
            if (getAllChats().get(i).getUniqueName() == uniqueName) {
                return getAllChats().get(i);
            }
        }
        return getAllChats().get(0);
    }
}
