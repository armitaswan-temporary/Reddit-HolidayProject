import java.util.UUID;
import java.util.ArrayList;

public class Post {
    public String title;
    private UUID uniqueName;
    private String bodyText;
    private ArrayList<String> tags;
    private Subreddit originSubreddit;
    private Account creator;
    private ArrayList<Pair<Comment, Integer>> postsComments;
    private ArrayList<Pair<Account, Integer>> votedAccounts;
    private int karma;

    public Post(Account creator, String title, String bodyText, Subreddit originSubreddit) {
        this.title = title;
        uniqueName = UUID.randomUUID();
        this.bodyText = bodyText;
        tags = new ArrayList<>();
        this.originSubreddit = originSubreddit;
        this.creator = creator;
        postsComments = new ArrayList<>();
        votedAccounts = new ArrayList<>();
        karma = 0;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
    public String getTitle() {
        return title;
    }
    public UUID getUniqueName() {
        return uniqueName;
    }
    public String getBodyText() {
        return bodyText;
    }
    public ArrayList<String> getTags() {
        return tags;
    }
    public Subreddit getOriginSubreddit() {
        return originSubreddit;
    }
    public Account getCreator() {
        return creator;
    }
    public ArrayList<Pair<Comment, Integer>> getPostsComments() {
        return postsComments;
    }
    public ArrayList<Pair<Account, Integer>> getVotedAccounts() {
        return votedAccounts;
    }
    public int getKarma() {
        return karma;
    }

    public void setKarma(Account account, int status, int goal) {
        if (status == 0) {
            Pair<Account, Integer> newAcc = new Pair<>(account, goal);
            votedAccounts.add(newAcc);
        }
        else if (status == goal) {
            Pair<Account, Integer> newAcc = new Pair<>(account, status);
            votedAccounts.remove(newAcc);
        }
        else {
            Pair<Account, Integer> newAcc = new Pair<>(account, status);
            votedAccounts.get(votedAccounts.indexOf(newAcc)).editSecond(goal);
        }
    }
    public void addKarma() {
        karma++;
    }
    public void minKarma() {
        karma--;
    }
    public void addComment(Comment comment) {
        Pair<Comment, Integer> newComment = new Pair<>(comment, 0);
        postsComments.add(newComment);
    }
    public void deleteComment(Comment comment) {
        Pair<Comment, Integer> newComment = new Pair<>(comment, 0);
        postsComments.remove(newComment);
    }
    public void updateCommentKarma(Comment comment, Integer exKarma, Integer newKarma) {
        Pair<Comment, Integer> newComment = new Pair<>(comment, exKarma);
        postsComments.get(postsComments.indexOf(newComment)).editSecond(newKarma);
    }
    public boolean checkAccount(Account account) {
        for (int i = 0; i < votedAccounts.size(); i++) {
            if (votedAccounts.get(i).getFirst().getUsername().equals(account.getUsername())) {
                return true;
            }
        }
        return false;
    }
    public boolean checkUpvoted(Account account) {
        for (int i = 0; i < votedAccounts.size(); i++) {
            if (votedAccounts.get(i).getFirst().getUsername().equals(account.getUsername())) {
                if (votedAccounts.get(i).getSecond() == 1) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }
    public boolean checkDownvoted(Account account) {
        for (int i = 0; i < votedAccounts.size(); i++) {
            if (votedAccounts.get(i).getFirst().getUsername().equals(account.getUsername())) {
                if (votedAccounts.get(i).getSecond() == 2) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }
    public boolean checkComment(Comment comment) {
        for (int i = 0; i < postsComments.size(); i++) {
            if (postsComments.get(i).getFirst().getUniqueName().equals(comment.getUniqueName())) {
                return true;
            }
        }
        return false;
    }
}
