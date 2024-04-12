import java.util.UUID;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;
import java.util.Comparator;


public class Account {
    private final String email;
    private int password;
    private String name;
    private String gender;
    private final UUID username;
    private int totalKarma;
    private int postkarma;
    private int commentKarma;
    private ArrayList<Chat> chats;
    private ArrayList<Account> followers;
    private ArrayList<Account> followings;
    private ArrayList<Post> saves;
    private ArrayList<Subreddit> joinedSubreddits;
    private ArrayList<Post> usersPosts;
    private ArrayList<Post> upvotedPosts;
    private ArrayList<Comment> upvotedComments;
    private ArrayList<Pair<String, Integer>> usersTags;

    public Account(String email, String password) {
        this.email = email;
        this.password = password.hashCode();
        username = UUID.randomUUID();
        totalKarma = 0;
        postkarma = 0;
        commentKarma = 0;
        chats = new ArrayList<>();
        followers = new ArrayList<>();
        followings = new ArrayList<>();
        saves = new ArrayList<>();
        joinedSubreddits = new ArrayList<>();
        usersPosts = new ArrayList<>();
        upvotedPosts = new ArrayList<>();
        upvotedComments = new ArrayList<>();
        usersTags = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }
    public void setPassword(String password) {
        this.password = password.hashCode();
    }
    public UUID getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public void addPostKarma() {
        postkarma++;
        totalKarma++;
    }
    public void minPostKarma() {
        postkarma--;
        totalKarma--;
    }
    public void addCommentKarma() {
        commentKarma++;
        totalKarma++;
    }
    public void minCommentKarma() {
        commentKarma--;
        totalKarma--;
    }
    public int getPostkarma() {
        return postkarma;
    }
    public int getCommentKarma() {
        return commentKarma;
    }
    public int getTotalKarma() {
        return totalKarma;
    }
    public void addFollower(Account follower) {
        followers.add(follower);
    }
    public void addFollowing(Account following) {
        followings.add(following);
    }
    public ArrayList<Account> getFollowers() {
        return followers;
    }
    public ArrayList<Account> getFollowings() {
        return followings;
    }
    public void savePost(Post post) {
        saves.add(post);
    }
    public void unsavePost(Post post) {
        saves.remove(post);
    }
    public ArrayList<Post> getSaves() {
        return saves;
    }
    public void addSubreddit(Subreddit subreddit) {
        joinedSubreddits.add(subreddit);
    }
    public void deleteSubreddit(Subreddit subreddit) {
        joinedSubreddits.remove(subreddit);

    }
    public ArrayList<Subreddit> getJoinedSubreddits() {
        return joinedSubreddits;
    }
    public void addUsersPosts(Post post) {
        usersPosts.add(post);
    }
    public void deleteUsersPosts(Post post) {
        usersPosts.remove(post);
    }
    public ArrayList<Post> getUsersPosts() {
        return usersPosts;
    }
    public void setUpvotedPosts(boolean added, Post post) {
        if (added) {
            upvotedPosts.add(post);
        }
        else {
            upvotedPosts.remove(post);
        }
    }
    public ArrayList<Post> getUpvotedPosts() {
        return upvotedPosts;
    }
    public void setUpvotedComments(boolean added, Comment comment) {
        if (added) {
            upvotedComments.add(comment);
        }
        else {
            upvotedComments.remove(comment);
        }
    }
    public ArrayList<Comment> getUpvotedComments() {
        return upvotedComments;
    }
    public void addUsersTag(ArrayList<String> tags) {
        for (int i = 0; i < tags.size(); i++) {
            boolean flag = false;
            for (int j = 0; j < usersTags.size(); j++) {
                if (flag) {
                    break;
                }
                if (tags.get(i).equals(usersTags.get(j).getFirst())) {
                    Integer second = usersTags.get(j).getSecond() + 1;
                    usersTags.get(j).editSecond(second);
                    flag = true;
                }
            }
            if (!flag) {
                Pair<String, Integer> newTag = new Pair<>(tags.get(i), 1);
                usersTags.add(newTag);
            }
        }
        // Sort the list based on the second element (integer)
        Collections.sort(usersTags, Comparator.comparing(Pair::getSecond));
    }
    public ArrayList<Pair<String, Integer>> getUsersTags() {
        return usersTags;
    }
    public void addChat(Chat chat) {
        chats.add(chat);
    }
    public void deleteChat(Chat chat) {
        chats.remove(chat);
    }
    public ArrayList<Chat> getChats() {
        return chats;
    }



    public boolean validatePassword(String enteredPassword) {
        return (enteredPassword.hashCode() == this.password);
    }
    public static boolean validateEmail(String email) {
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
    public boolean checkChat(Chat chat) {
        for (int i = 0; i < chats.size(); i++) {
            if (chat.getUniqueName().equals(chats.get(i).getUniqueName())) {
                return true;
            }
        }
        return false;
    }
    public boolean checkFollower(Account account) {
        for (int i = 0; i < followers.size(); i++) {
            if (account.getUsername().equals(followers.get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }
    public boolean checkFollowing(Account account) {
        for (int i = 0; i < followings.size(); i++) {
            if (account.getUsername().equals(followings.get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }
    public boolean checkSaves(Post post) {
        for (int i = 0; i < saves.size(); i++) {
            if (post.getUniqueName().equals(saves.get(i).getUniqueName())) {
                return true;
            }
        }
        return false;
    }
    public boolean checkJoinedSubreddits(Subreddit subreddit) {
        for (int i = 0; i < joinedSubreddits.size(); i++) {
            if (subreddit.getUniqueName().equals(joinedSubreddits.get(i).getUniqueName())) {
                return true;
            }
        }
        return false;
    }
    public boolean checkUsersPosts(Post post) {
        for (int i = 0; i < usersPosts.size(); i++) {
            if (post.getUniqueName().equals(usersPosts.get(i).getUniqueName())) {
                return true;
            }
        }
        return false;
    }
    public boolean checkUpvotedPosts(Post post) {
        for (int i = 0; i < upvotedPosts.size(); i++) {
            if (post.getUniqueName().equals(upvotedPosts.get(i).getUniqueName())) {
                return true;
            }
        }
        return false;
    }
    public boolean checkUpvotedComments(Comment comment) {
        for (int i = 0; i < upvotedComments.size(); i++) {
            if (comment.getUniqueName().equals(upvotedComments.get(i).getUniqueName())) {
                return true;
            }
        }
        return false;
    }

    public String notifUpvotedPost(Post post) {
        String message = post.getTitle() + " got upvoted!";
        return message;
    }
    public String notifUpvotedComment(Comment comment) {
        String message = "Your comment on " + comment.getPost().getTitle()+ "  got upvoted!";
        return message;
    }
    public String notifNewFollower(Account account) {
        String message = account.getUsername() + " has followed you!";
        return message;
    }
    public String notifNewChat(Chat chat) {
        String message = chat.getThem() + " sent you a message!";
        return message;
    }
}
