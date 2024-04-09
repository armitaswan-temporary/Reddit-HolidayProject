import java.util.UUID;
import java.util.ArrayList;


public class Account {
    private String email;
    private String password;
    private String name;
    private String gender;
    private UUID username;
    private int totalKarma;
    private int Postkarma;
    private int commentKarma;
    private ArrayList<String> chats;
    private ArrayList<Account> followers;
    private ArrayList<Account> followings;
    private ArrayList<Posts> saves;
    private ArrayList<Subreddits> joinedSubreddits;
    private ArrayList<Posts> usersPosts;
    private ArrayList<Posts> upvotedPosts;
    private ArrayList<Comments> upvotedComments;


}
