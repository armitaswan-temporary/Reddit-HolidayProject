import java.util.UUID;
import java.util.ArrayList;

public class Subreddit {
    private String name;
    private UUID uniqueName;
    private Account creator;
    private ArrayList<Pair<Account, Boolean>> admins;
    private ArrayList<Account> membersList;
    private ArrayList<Post> posts;
    private int members;

    public Subreddit(Account creator, String name) {
        this.name = name;
        uniqueName = UUID.randomUUID();
        this.creator = creator;
        admins = new ArrayList<>();
        Pair<Account, Boolean> theCreator = new Pair<>(creator, true);
        admins.add(theCreator);
        membersList = new ArrayList<>();
        membersList.add(creator);
        posts = new ArrayList<>();
        members = 1;
    }

    public String getName() {
        return name;
    }
    public UUID getUniqueName() {
        return uniqueName;
    }
    public Account getCreator() {
        return creator;
    }
    public int getMembers() {
        return members;
    }

    public ArrayList<Pair<Account, Boolean>> getAdmins() {
        return admins;
    }
    public void addPost(Post post) {
        posts.add(post);
    }
    public ArrayList<Post> getPosts() {
        return posts;
    }
    public void addMembers(Account account) {
        members++;
        membersList.add(account);
    }
    public boolean checkCreator(Account account) {
        return account.getUsername().equals(creator.getUsername());
    }
    public boolean checkAdmin(Account account) {
        for (int i = 0; i < admins.size(); i++) {
            if (account.getUsername().equals(admins.get(i).getFirst().getUsername())) {
                return true;
            }
        }
        return false;
    }
    public boolean checkMember(Account account) {
        for (int i = 0; i < membersList.size(); i++) {
            if (membersList.get(i).getUsername().equals(account.getUsername())) {
                return true;
            }
        }
        return false;
    }
    public void addAdmins(Account account) {
        Pair<Account, Boolean> newAdmin = new Pair<>(account, false);
        admins.add(newAdmin);
    }
    public void deletePost(Post post) {
        posts.remove(post);
    }
    public void disAdmin(Account account) {
        admins.remove(account);
    }
    public void deleteMember(Account account) {
        members--;
        membersList.remove(account);
        if (checkAdmin(account)) {
            Pair<Account, Boolean> newAdmin = new Pair<>(account, false);
            admins.remove(newAdmin);
        }
    }
}
