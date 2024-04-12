import java.util.UUID;
import java.util.ArrayList;

public class Comment {
    private UUID uniqueName;
    private Account creator;
    private Post post;
    private int karma;
    private ArrayList<Pair<Comment, Integer>> replies;
    private ArrayList<Pair<Account, Integer>> votedAccounts;

    public Comment(Account creator, Post post) {
        uniqueName = UUID.randomUUID();
        this.creator = creator;
        this.post = post;
        karma = 0;
        replies = new ArrayList<>();
        votedAccounts = new ArrayList<>();
    }

    public UUID getUniqueName() {
        return uniqueName;
    }
    public Account getCreator() {
        return creator;
    }
    public Post getPost() {
        return post;
    }
    public int getKarma() {
        return karma;
    }
    public ArrayList<Pair<Comment, Integer>> getReplies() {
        return replies;
    }
    public ArrayList<Pair<Account, Integer>> getVotedAccounts() {
        return votedAccounts;
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
    public void addReply(Comment reply) {
        Pair<Comment, Integer> newReply = new Pair<>(reply, 0);
        replies.add(newReply);
    }
    public void deletereply(Comment reply) {
        Pair<Comment, Integer> newReply = new Pair<>(reply, 0);
        replies.remove(newReply);
    }
    public void updateReplyKarma(Comment reply, Integer exKarma, Integer newKarma) {
        Pair<Comment, Integer> newReply = new Pair<>(reply, exKarma);
        replies.get(replies.indexOf(newReply)).editSecond(newKarma);
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
    public boolean checkReply(Comment reply) {
        for (int i = 0; i < replies.size(); i++) {
            if (replies.get(i).getFirst().getUniqueName().equals(reply.getUniqueName())) {
                return true;
            }
        }
        return false;
    }
}
