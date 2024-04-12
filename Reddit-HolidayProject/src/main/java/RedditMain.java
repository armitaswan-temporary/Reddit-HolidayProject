import java.util.UUID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class RedditMain {
    public static void main(String[] args) {
        boolean stayinApp = true;
        while (stayinApp) {
            System.out.println("      [Reddit]");
            System.out.println("1- signin\n2- login\n3- exit");
            Account user = null;
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                user = signin();
            } else if (choice == 2) {
                user = login();
            } else if (choice == 3) {
                stayinApp = false;
                break;
            } else {
                System.out.println("choose correctly");
                continue;
            }
            boolean isOnline = true;
            while (isOnline) {
                System.out.println("1- timline\n2- search\n3- new\n4- chats\n5- notification\n6- profile\n7- logout");
                choice = scanner.nextInt();
                if (choice == 1) {
                    timline(user);
                } else if (choice == 2) {
                    search(user);
                } else if (choice == 3) {
                    makeStuff(user);
                } else if (choice == 4) {
                    chats(user);
                } else if (choice == 5) {
                    notifications(user);
                } else if (choice == 6) {
                    profile(user);
                } else if (choice == 7) {
                    isOnline = false;
                    user = null;
                } else {
                    System.out.println("choose correctly");
                    continue;
                }
            }
        }
    }

    // functions to identify the online user
    public static Account signin() {
        System.out.println("      [Signin]");
        Scanner scanner = new Scanner(System.in);
        boolean emailGot = false;
        String email = "";
        while(!emailGot) {
            System.out.print("enter email: ");
            email = scanner.nextLine();
            if (Account.validateEmail(email)) {
                emailGot = true;
            }
            else {
                System.out.println("Wrong!");
            }
        }
        System.out.print("enter password: ");
        String password = scanner.nextLine();
        Account user = new Account(email, password);
        Data.setAllAccounts(user);
        System.out.println("your id: " + user.getUsername());

        System.out.println("choose your gender");
        System.out.println("1- woman   2- man   3- non-binary   4- prefer not to say");
        Scanner scanner6 = new Scanner(System.in);
        int choice62 = scanner6.nextInt();
        if (choice62 == 1) {
            user.setGender("woman");
        }
        else if (choice62 == 2) {
            user.setGender("man");
        }
        else if (choice62 == 3) {
            user.setGender("non-binary");
        }
        else {
            user.setGender("prefer not to say");
        }
        return user;
    }

    public static Account login() {
        System.out.println("      [Login]");
        Scanner scanner = new Scanner(System.in);
        boolean userFound = false;
        Account user = null;
        while (!userFound) {
            System.out.print("enter username: ");
            String username = scanner.nextLine();
            for (int i = 0; i < Data.allAccountsUUID.size(); i++) {
                if (Data.allAccountsUUID.get(i).equals(username)) {
                    userFound = true;
                    user = Data.gettheAccount(UUID.fromString(username));
                }
            }
            if (!userFound) {
                System.out.println("user not found!");
            }
        }
        boolean passwordGot = false;
        while (!passwordGot) {
            System.out.print("enter password: ");
            String password = scanner.nextLine();
            if (user.validatePassword(password)) {
                passwordGot = true;
            }
            if (!passwordGot) {
                System.out.println("wrong!");
            }
        }
        System.out.println("your id: " + user.getUsername() + "\n      hello");
        return user;
    }

    // main menu functions
    public static void timline(Account user) {
        boolean stayin = true;
        while (stayin) {
            System.out.println("1- for you   2- trending   3- back");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                boolean stayin1 = true;
                while(stayin1) {
                    System.out.println("1- subreddits' new posts   2- followings' new posts   3- recommended posts   4- back");
                    Scanner scanner1 = new Scanner(System.in);
                    int choice1 = scanner1.nextInt();
                    if (choice1 == 1) {
                        boolean stayin11 = true;
                        while(stayin11) {
                            System.out.println("   [subreddits' new posts]");
                            for (int i = user.getJoinedSubreddits().size() - 1; i > user.getJoinedSubreddits().size() - 11; i--) {
                                for (int j = user.getJoinedSubreddits().get(i).getPosts().size() - 1; j > user.getJoinedSubreddits().get(i).getPosts().size() - 11; j--) {
                                    System.out.println((user.getJoinedSubreddits().size() - i) + " & "
                                            + (user.getJoinedSubreddits().get(i).getPosts().size())
                                            + ": " + user.getJoinedSubreddits().get(i).getPosts().get(j).getTitle());
                                }
                            }
                            System.out.print("choose what subreddit to view and what post of it(0 to go back): ");
                            Scanner scanner11 = new Scanner(System.in);
                            int choice11 = scanner11.nextInt();
                            int choice12 = scanner11.nextInt();
                            if (choice11 == 0 || choice12 == 0) {
                                stayin11 = false;
                                break;
                            }
                            showPost(user, user.getJoinedSubreddits().get(choice11).getPosts().get(choice12));
                        }
                    }
                    else if (choice1 == 2) {
                        boolean stayin11 = true;
                        while(stayin11) {
                            System.out.println("   [followings' new posts]");
                            for (int i = user.getFollowings().size() - 1; i > user.getFollowings().size() - 11; i--) {
                                for (int j = user.getFollowings().get(i).getUsersPosts().size() - 1; j > user.getFollowings().get(i).getUsersPosts().size() - 11; j--) {
                                    System.out.println((user.getFollowings().size() - i) + " & "
                                            + (user.getFollowings().get(i).getUsersPosts().size())
                                            + ": " + user.getFollowings().get(i).getUsersPosts().get(j).getTitle());
                                }
                            }
                            System.out.print("choose what subreddit to view and what post of it(0 to go back): ");
                            Scanner scanner11 = new Scanner(System.in);
                            int choice11 = scanner11.nextInt();
                            int choice12 = scanner11.nextInt();
                            if (choice11 == 0 || choice12 == 0) {
                                stayin11 = false;
                                break;
                            }
                            showPost(user, user.getFollowings().get(choice11).getUsersPosts().get(choice12));
                        }
                    }
                    else if (choice1 == 3) {
                        boolean stayin11 = true;
                        while(stayin11) {
                            System.out.println("   [recommended posts]");
                            for (int i = Data.getAllPosts().size() - 1; i > Data.getAllPosts().size() - 51; i--) {
                                for (int j = 0; j < user.getUsersTags().size(); j++) {
                                    if (Data.getAllPosts().get(i).getTags().contains(user.getUsersTags().get(j))) {
                                        System.out.println("user: " + (i + 1) + " &post: " + (j + 1) + " " + user.getUsersPosts().get(i).getTitle() + "   subreddit: " + user.getUsersPosts().get(i).getOriginSubreddit());
                                    }
                                }
                            }
                            System.out.print("choose what user to view and what post of it(0 to go back): ");
                            Scanner scanner11 = new Scanner(System.in);
                            int choice11 = scanner11.nextInt();
                            int choice12 = scanner11.nextInt();
                            if (choice11 == 0 || choice12 == 0) {
                                stayin11 = false;
                                break;
                            }
                            showPost(user, user.getFollowings().get(choice11).getUsersPosts().get(choice12));
                        }
                    }
                    else if (choice1 == 4) {
                        stayin1 = false;
                        break;
                    }
                    else {
                        continue;
                    }
                }
            }
            else if (choice == 2) {
                boolean stayin2 = true;
                while(stayin2) {
                    System.out.println("1- latest posts   2- top posts   3- back");
                    Scanner scanner2 = new Scanner(System.in);
                    int choice2 = scanner2.nextInt();
                    if (choice2 == 1) {
                        boolean stayin21 = true;
                        while(stayin21) {
                            System.out.println("      [latest posts]");
                            for (int i = Data.getAllPosts().size() - 1; i > Data.getAllPosts().size() - 11; i--) {
                                System.out.println((Data.getAllPosts().size() - i) + ": " + Data.getAllPosts().get(i).getTitle() + "   subreddit: " + Data.getAllPosts().get(i).getOriginSubreddit().getName());
                            }
                            System.out.print("choose what post to view(0 to go  back): ");
                            Scanner scanner21 = new Scanner(System.in);
                            int choice21 = scanner21.nextInt();
                            if (choice21 == 0) {
                                stayin21 = false;
                                break;
                            }
                            showPost(user, Data.getAllPosts().get(Data.getAllPosts().size() - choice21));
                        }
                    }
                    else if (choice2 == 2) {
                        boolean stayin22 = true;
                        while(stayin22) {
                            System.out.println("      [top posts]");
                            ArrayList<Post> sortedPosts = new ArrayList<>();
                            for (int i = 0; i < Data.getAllPosts().size(); i++) {
                                sortedPosts.add(Data.getAllPosts().get(i));
                            }
                            // Sort the list based on their karma
                            Collections.sort(sortedPosts, new karmaComparator());
                            for (int i = sortedPosts.size() - 1; i > sortedPosts.size() - 11; i--) {
                                System.out.println((sortedPosts.size() - i) + ": " + sortedPosts.get(i).getTitle() + "   subreddit: " + sortedPosts.get(i).getOriginSubreddit().getName());

                            }
                            System.out.print("choose what post to view(0 to go  back): ");
                            Scanner scanner22 = new Scanner(System.in);
                            int choice22 = scanner22.nextInt();
                            if (choice22 == 0) {
                                stayin22 = false;
                                break;
                            }
                            showPost(user, sortedPosts.get(Data.getAllPosts().size() - choice22));
                        }
                    }
                    else if (choice2 == 3) {
                        stayin2 = false;
                        break;
                    }
                    else {
                        continue;
                    }
                }
            }
            else if (choice == 3) {
                stayin = false;
                break;
            }
            else {
                continue;
            }
        }
    }
    public static void search(Account user) {
        boolean stayin = true;
        while(stayin) {
            System.out.println("what are you going to search?\n1- all\n2- usernames\n3- subreddits\n4- posts\n5- back");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.print("search here: ");
                Scanner scanner1 = new Scanner(System.in);
                String choice1 = scanner1.nextLine();
                boolean flag = false;
                for (int i = 0; i < Data.getAllAccounts().size(); i++) {
                    if (Data.getAllAccounts().get(i).getUsername().equals(choice1)) {
                        showAccount(user, Data.getAllAccounts().get(i));
                        flag = true;
                        break;
                    }
                }
                for (int i = 0; i < Data.getAllSubreddits().size(); i++) {
                    if (Data.getAllSubreddits().get(i).getUniqueName().equals(choice1)) {
                        showSubreddit(user, Data.gettheSubreddit(Data.getAllSubreddits().get(i).getUniqueName()));
                        flag = true;
                        break;
                    }
                }
                for (int i = 0; i < Data.getAllPosts().size(); i++) {
                    for (int j = 0; j < Data.getAllPosts().get(i).getTags().size(); j++) {
                        if (Data.getAllPosts().get(i).getTags().get(j).equals(choice1)) {
                            showPost(user, Data.getAllPosts().get(i));
                            flag = true;
                            break;
                        }
                    }
                }
                if (!flag) {
                    System.out.print("not found!");
                }
            }
            else if (choice == 2) {
                System.out.print("search here: ");
                Scanner scanner2 = new Scanner(System.in);
                String choice2 = scanner2.nextLine();
                boolean flag = false;
                for (int i = 0; i < Data.getAllAccounts().size(); i++) {
                    if (Data.getAllAccounts().get(i).getUsername().equals(choice2)) {
                        showAccount(user, Data.getAllAccounts().get(i));
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.print("user not found!");
                }
            }
            else if (choice == 3) {
                System.out.print("search here: ");
                Scanner scanner3 = new Scanner(System.in);
                String choice3 = scanner3.nextLine();
                boolean flag = false;
                for (int i = 0; i < Data.getAllSubreddits().size(); i++) {
                    if (Data.getAllSubreddits().get(i).getUniqueName().equals(choice3)) {
                        showSubreddit(user, Data.gettheSubreddit(Data.getAllSubreddits().get(i).getUniqueName()));
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.print("subreddit not found!");
                }
            }
            else if (choice == 4) {
                System.out.print("search here: ");
                Scanner scanner4 = new Scanner(System.in);
                String choice4 = scanner4.nextLine();
                boolean flag = false;
                for (int i = 0; i < Data.getAllPosts().size(); i++) {
                    for (int j = 0; j < Data.getAllPosts().get(i).getTags().size(); j++) {
                        if (Data.getAllPosts().get(i).getTags().get(j).equals(choice4)) {
                            showPost(user, Data.getAllPosts().get(i));
                            flag = true;
                            break;
                        }
                    }
                }
                if (!flag) {
                    System.out.print("not found!");
                }
            }
            else if (choice == 5) {
                stayin = false;
                break;
            }
            else {
                continue;
            }
        }
    }
    public static void makeStuff(Account user) {
        boolean stayin = true;
        while (stayin) {
            System.out.println("what are you going to make?\n1- new subreddit   2- new post   3- back");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                makeSubreddit(user);
            }
            else if (choice == 2) {
                makePost(user);
            }
            else if (choice == 3) {
                stayin = false;
                break;
            }
            else {
                continue;
            }
        }
    }
    public static void chats(Account user) {
        boolean stayin = true;
        while (stayin) {
            System.out.println("      [Chats]");
            System.out.println("1- view chats   2- new chat   3- back");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                boolean stayin1 = true;
                while (stayin1) {
                    System.out.println("      [chats]");
                    for (int i = 0; i < user.getChats().size(); i++) {
                        System.out.println((i + 1) + " " + user.getChats().get(i).getThem().getName());
                    }
                    System.out.print("choose what chat to view(0 to go back): ");
                    Scanner scanner1 = new Scanner(System.in);
                    int choice1 = scanner1.nextInt();
                    if (choice1 == 0) {
                        stayin1 = false;
                        break;
                    }
                    showChat(user, user.getChats().get(choice1));
                }
            }
            else if (choice == 2) {
                makeChat(user);
            }
            else if (choice == 3) {
                stayin = false;
                break;
            }
            else {
                continue;
            }
        }
    }
    public static void notifications(Account user) {
        boolean stayin = true;
        while (stayin) {
            System.out.println("      [Notifications]");
            System.out.println("1- view upvoted posts   2- view upvoted comments   3- back");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                boolean stayin1 = true;
                while(stayin1) {
                    System.out.println("      [upvoted posts]");
                    for (int i = 0; i < user.getUpvotedPosts().size(); i++) {
                        System.out.println((i + 1) + " " + user.getUpvotedPosts().get(i).getTitle());
                    }
                    System.out.print("choose what post to view(0 to go back): ");
                    Scanner scanner1 = new Scanner(System.in);
                    int choice1 = scanner1.nextInt();
                    if (choice1 == 0) {
                        stayin1 = false;
                        break;
                    }
                    showPost(user, user.getUsersPosts().get(choice1));
                }
            }
            else if (choice == 2) {
                System.out.println("      [upvoted comments]");
                for (int i = 0; i < user.getUpvotedComments().size(); i++) {
                    showComment(user, user.getUpvotedComments().get(i));
                }
            }
            else if (choice == 3) {
                stayin = false;
                break;
            }
            else {
                continue;
            }
        }
    }
    public static void profile(Account user) {
        boolean stayin = true;
        while (stayin) {
            System.out.println("      [Profile]");
            System.out.println(user.getName() + " " + user.getGender());
            System.out.println(user.getFollowers().size() + " followers");
            System.out.println("u/" + user.getUsername() + " ~ " + user.getTotalKarma() + " karma");
            System.out.println("1- posts\n2- comments\n3- joined subreddit\n4- about\n5- saved posts\n6- edit\n7- back");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                boolean stayin1 = true;
                while(stayin1) {
                    System.out.println("      [Posts]");
                    for (int i = 0; i < user.getUsersPosts().size(); i++) {
                        System.out.println((i + 1) + ": " + user.getUsersPosts().get(i).getTitle() + "   subreddit: " + user.getUsersPosts().get(i).getOriginSubreddit());
                    }
                    System.out.print("choose what post to view(0 to go back): ");
                    Scanner scanner1 = new Scanner(System.in);
                    int choice1 = scanner1.nextInt();
                    if (choice1 == 0) {
                        stayin1 = false;
                        break;
                    }
                    showPost(user, user.getUsersPosts().get(choice1));
                }
            }
            else if (choice == 2) {
                boolean stayin2 = true;
                while(stayin2) {
                    System.out.println("      [Comments]");
                    for (int i = 0; i < user.getUsersComment().size(); i++) {
                        System.out.println((i + 1) + ": " + user.getUsersComment().get(i).getPost() + "'s comment");
                    }
                    System.out.print("choose what post to view(0 to go back): ");
                    Scanner scanner2 = new Scanner(System.in);
                    int choice2 = scanner2.nextInt();
                    if (choice2 == 0) {
                        stayin2 = false;
                        break;
                    }
                    showComment(user, user.getUsersComment().get(choice2));
                }
            }
            else if (choice == 3) {
                boolean stayin3 = true;
                while(stayin3) {
                    System.out.println("      [Joined subreddits]");
                    for (int i = 0; i < user.getJoinedSubreddits().size(); i++) {
                        System.out.println((i + 1) + ": " + user.getJoinedSubreddits().get(i).getName());
                    }
                    System.out.print("choose what subreddit to view(0 to go back): ");
                    Scanner scanner3 = new Scanner(System.in);
                    int choice3 = scanner3.nextInt();
                    if (choice3 == 0) {
                        stayin3 = false;
                        break;
                    }
                    showSubreddit(user, user.getJoinedSubreddits().get(choice3));
                }
            }
            else if (choice == 4) {
                System.out.println("     [About]");
                System.out.println("post karma: " + user.getPostkarma());
                System.out.println("comment karma: " + user.getCommentKarma());
            }
            else if (choice == 5) {
                boolean stayin5 = true;
                while(stayin5) {
                    System.out.println("      [Saved posts]");
                    for (int i = 0; i < user.getSaves().size(); i++) {
                        System.out.println((i + 1) + ": " + user.getSaves().get(i).getTitle());
                    }
                    System.out.print("choose what saved post to view(0 to go  back): ");
                    Scanner scanner5 = new Scanner(System.in);
                    int choice5 = scanner5.nextInt();
                    if (choice5 == 0) {
                        stayin5 = false;
                        break;
                    }
                    showPost(user, user.getSaves().get(choice5));
                }
            }
            else if (choice == 6) {
                boolean stayin2 = true;
                while (stayin2) {
                    System.out.println("choose what you want to change!");
                    System.out.println("1- name   2- gender   3- username   4- password   5- delete account   6- back");
                    int choice6 = scanner.nextInt();
                    if (choice6 == 1) {
                        System.out.print("enter new name: ");
                        Scanner scanner6 = new Scanner(System.in);
                        String newName = scanner6.nextLine();
                        user.setName(newName);
                    }
                    else if (choice6 == 2) {
                        System.out.println("choose your gender");
                        System.out.println("1- woman   2- man   3- non-binary   4- prefer not to say");
                        Scanner scanner6 = new Scanner(System.in);
                        int choice62 = scanner6.nextInt();
                        if (choice62 == 1) {
                            user.setGender("woman");
                        } else if (choice62 == 2) {
                            user.setGender("man");
                        } else if (choice62 == 3) {
                            user.setGender("non-binary");
                        } else {
                            user.setGender("prefer not to say");
                        }
                    }
                    else if (choice6 == 3) {
                        System.out.print("enter new username: ");
                        Scanner scanner63 = new Scanner(System.in);
                        String newUsername = scanner63.nextLine();
                        user.setUsername(newUsername);
                    }
                    else if (choice6 == 4) {
                        Scanner scanner6 = new Scanner(System.in);
                        boolean passwordGot = false;
                        String newPassword = null;
                        while (!passwordGot) {
                            System.out.print("enter old password: ");
                            String oldPassword = scanner6.nextLine();
                            if (user.validatePassword(oldPassword)) {
                                System.out.print("enter new password: ");
                                newPassword = scanner6.nextLine();
                                passwordGot = true;
                            } else {
                                System.out.println("wrong!");
                            }
                        }
                        if (passwordGot) {
                            user.setPassword(newPassword);
                        }
                    }
                    else if (choice6 == 5) {
                        Data.deleteAcount(user);
                        stayin = false;
                        stayin2 = false;
                        break;
                    }
                    else if (choice6 == 6) {
                        stayin2 = false;
                        break;
                    }
                    else {
                        continue;
                    }
                }
                if (!stayin) {
                    break;
                }
            }
            else if (choice == 7) {
                stayin = false;
                break;
            }
            else {
                continue;
            }
        }
    }


    // viewing options
    public static void showAccount(Account user, Account account) {
        boolean stayin = true;
        while (stayin) {
            System.out.println("   [Profile]");
            System.out.println(account.getName() + " " + account.getGender());
            System.out.println(account.getFollowers().size() + " followers");
            System.out.println("u/" + account.getUsername() + " ~ " + account.getTotalKarma() + " karma");
            System.out.println("post karma: " + user.getPostkarma() + "  comment karma: " + user.getCommentKarma());
            System.out.println("1- posts\n2- joined subreddit\n3- follow\n4- back");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                boolean stayin1 = true;
                while(stayin1) {
                    System.out.println("   [Posts]");
                    for (int i = 0; i < account.getUsersPosts().size(); i++) {
                        System.out.println((i + 1) + ": " + account.getUsersPosts().get(i).getTitle() + "   subreddit: " + account.getUsersPosts().get(i).getOriginSubreddit().getName());
                    }
                    System.out.print("choose what post to view(0 to go  back): ");
                    Scanner scanner1 = new Scanner(System.in);
                    int choice1 = scanner1.nextInt();
                    if (choice1 == 0) {
                        stayin1 = false;
                        break;
                    }
                    showPost(user, account.getUsersPosts().get(choice1));
                }
            }
            else if (choice == 2) {
                boolean stayin2 = true;
                while(stayin2) {
                    System.out.println("   [Joined subreddits]");
                    for (int i = 0; i < account.getJoinedSubreddits().size(); i++) {
                        System.out.println((i + 1) + ": " + account.getJoinedSubreddits().get(i).getName());
                    }
                    System.out.print("choose what subreddit to view(0 to go back): ");
                    Scanner scanner3 = new Scanner(System.in);
                    int choice3 = scanner3.nextInt();
                    if (choice3 == 0) {
                        stayin2 = false;
                        break;
                    }
                    showSubreddit(user, account.getJoinedSubreddits().get(choice3));
                }
            }
            else if (choice == 3) {
                user.addFollowing(account);
                account.addFollower(user);
            }
            else if (choice == 4) {
                stayin = false;
                break;
            }
            else {
                continue;
            }
        }
    }
    public static void showSubreddit(Account user, Subreddit subreddit) {
        boolean stayin = true;
        while (true) {
            System.out.println(subreddit.getName());
            System.out.println("creator: " + subreddit.getCreator() + "   memebers: " + subreddit.getMembers());
            System.out.println("1- posts   2- admins");
            if (subreddit.checkAdmin(user)) {
                System.out.println("3- leave subreddit   4- add new admins   5- disadmin   6- delete member   7- back");
            } else if (!subreddit.checkMember(user)) {
                System.out.println("3- join subreddit   4- back");
            } else if (subreddit.checkMember(user)) {
                System.out.println("3- leave subreddit   4- back");
            } else if (subreddit.checkCreator(user)) {
                System.out.println("option 0- delete subreddit");
            }
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                boolean stayin1 = true;
                while(stayin1) {
                    System.out.println("Posts");
                    for (int i = 0; i < subreddit.getPosts().size(); i++) {
                        System.out.println((i + 1) + ": " + subreddit.getPosts().get(i).getTitle() + " subreddit: " + subreddit.getPosts().get(i).getOriginSubreddit());
                    }
                    System.out.print("choose what post to view(0 to go bak): ");
                    int choice1 = scanner.nextInt();
                    if (choice1 == 0) {
                        stayin1 = false;
                        break;
                    }
                    showPost(user, subreddit.getPosts().get(choice1));
                }
            }
            else if (choice == 2) {
                boolean stayin2 = true;
                while(stayin2) {
                    System.out.println("Admins");
                    for (int i = 0; i < subreddit.getAdmins().size(); i++) {
                        System.out.print((i + 1) + ": " + subreddit.getAdmins().get(i).getFirst().getUsername());
                        if (subreddit.getAdmins().get(i).getSecond()) {
                            System.out.println(" creator");
                        } else {
                            System.out.println(" admin");
                        }
                    }
                    System.out.print("choose what admin to view(0 to go back): ");
                    int choice2 = scanner.nextInt();
                    if (choice2 == 0) {
                        stayin2 = false;
                        break;
                    }
                    showAccount(user, subreddit.getAdmins().get(choice2).getFirst());
                }
            }
            else if (choice == 3 && !subreddit.checkMember(user)) {
                System.out.println("You joined!");
                subreddit.addMembers(user);
            }
            else if (choice == 3 && subreddit.checkMember(user)) {
                System.out.println("You left!");
                subreddit.deleteMember(user);
            }
            else if (choice == 4 && subreddit.checkAdmin(user)) {
                System.out.println("Add new admins");
                boolean userFound = false;
                while (!userFound) {
                    System.out.print("write the username of the new admin(or back to quit): ");
                    String newAdminUsername = scanner.nextLine();
                    if (newAdminUsername.equals("back")) {
                        userFound = true;
                        break;
                    }
                    Account newAdmin = Data.gettheAccount(UUID.fromString(newAdminUsername));
                    if (subreddit.checkMember(newAdmin)) {
                        subreddit.addAdmins(newAdmin);
                        userFound = true;
                    }
                    else {
                        continue;
                    }
                }
            }
            else if (choice == 5 && subreddit.checkAdmin(user)) {
                System.out.println("Disadmin");
                boolean userFound = false;
                while (!userFound) {
                    System.out.print("write the username of the admin you wanna kick out(or back to quit): ");
                    String newAdminUsername = scanner.nextLine();
                    if (newAdminUsername.equals("back")) {
                        userFound = true;
                        break;
                    }
                    Account newAdmin = Data.gettheAccount(UUID.fromString(newAdminUsername));
                    if (subreddit.checkAdmin(newAdmin)) {
                        subreddit.disAdmin(newAdmin);
                        userFound = true;
                    }
                    else {
                        continue;
                    }
                }
            }
            else if (choice == 6 && subreddit.checkAdmin(user)) {
                System.out.println("Delete member");
                boolean userFound = false;
                while (!userFound) {
                    System.out.print("write the username of the member you wanna kick out(or back to quit): ");
                    String newAdminUsername = scanner.nextLine();
                    if (newAdminUsername.equals("back")) {
                        userFound = true;
                        break;
                    }
                    Account newAdmin = Data.gettheAccount(UUID.fromString(newAdminUsername));
                    if (subreddit.checkMember(newAdmin)) {
                        subreddit.deleteMember(newAdmin);
                        userFound = true;
                    }
                    else {
                        continue;
                    }
                }
            }
            else if ((subreddit.checkAdmin(user) && choice == 7) || (!subreddit.checkAdmin(user) && choice == 4)) {
                stayin = false;
                break;
            }
            else if (choice == 0) {
                Data.deleteSubreddit(subreddit);
                break;
            }
            else {
                continue;
            }
        }
    }
    public static void showPost(Account user, Post post) {
        boolean stayin = true;
        while (stayin) {
            System.out.println(post.getTitle());
            System.out.println("r/" + post.getOriginSubreddit());
            System.out.println("u/" + post.getCreator());
            System.out.println(post.getBodyText());
            System.out.println("karma: " + post.getKarma());
            Scanner scanner = new Scanner(System.in);
            System.out.println("1- upvote   2- downvote   3- view tags   4- view comments   5- share   6- back");
            int choice = scanner.nextInt();
            if (choice == 1) {
                if (post.checkUpvoted(user)) {
                    post.setKarma(user, 1, 1);
                    post.minKarma();
                }
                else if (post.checkDownvoted(user)) {
                    post.setKarma(user, 2, 1);
                    post.addKarma();
                    post.addKarma();
                }
                else {
                    post.setKarma(user, 0, 1);
                    post.addKarma();
                }
            }
            else if (choice == 2) {
                if (post.checkUpvoted(user)) {
                    post.setKarma(user, 1, 2);
                    post.minKarma();
                    post.minKarma();
                }
                else if (post.checkDownvoted(user)) {
                    post.setKarma(user, 2, 2);
                    post.addKarma();
                }
                else {
                    post.setKarma(user, 0, 2);
                    post.minKarma();
                }
            }
            else if (choice == 3) {
                System.out.println("Tags");
                for (int i = 0; i < post.getTags().size(); i++) {
                    System.out.println(post.getTags().get(i));
                }
            }
            else if (choice == 4) {
                System.out.println("Comments");
                System.out.println("1- post a comment   2- view randomly   3- view sortedly by karma");
                Scanner scanner1 = new Scanner(System.in);
                int choice1 = scanner1.nextInt();
                if (choice1 == 1) {
                    makeComment(user, post);
                }
                else if (choice1 == 3) {
                    ArrayList<Pair<Comment, Integer>> sortedComments = new ArrayList<>();
                    for (int i = 0; i < post.getPostsComments().size(); i++) {
                        Pair<Comment, Integer> newpair = new Pair<>(post.getPostsComments().get(i).getFirst(), post.getPostsComments().get(i).getSecond());
                        sortedComments.add(newpair);
                    }
                    // Sort the list based on the second element (integer)
                    Collections.sort(sortedComments, Comparator.comparing(Pair::getSecond));
                    for (int i = sortedComments.size() - 1; i >= 0; i--) {
                        showComment(user, sortedComments.get(i).getFirst());
                    }
                }
                else {
                    for (int i = 0; i < post.getPostsComments().size(); i++) {
                        showComment(user, post.getPostsComments().get(i).getFirst());
                    }
                }
            }
            else if (choice == 5) {
                System.out.print("write the id of the person you want to send this post to: ");
                Scanner scanner5 = new Scanner(System.in);
                String thePerson = scanner5.nextLine();
                Account them = Data.gettheAccount(UUID.fromString(thePerson));
                share(user, them, post);
            }
            else if (choice == 6) {
                stayin = false;
                break;
            }
            else {
                continue;
            }

        }
    }
    public static void showComment(Account user, Comment comment) {
        boolean stayin = true;
        while (stayin) {
            System.out.println("u/" + comment.getCreator().getUsername() + ":");
            System.out.println(comment.getBodyText());
            System.out.println("karma: " + comment.getKarma());
            Scanner scanner = new Scanner(System.in);
            System.out.println("1- upvote   2- downvote   3- view replies   4- back");
            int choice = scanner.nextInt();
            if (choice == 1) {
                if (comment.checkUpvoted(user)) {
                    comment.setKarma(user, 1, 1);
                    comment.minKarma();
                }
                else if (comment.checkDownvoted(user)) {
                    comment.setKarma(user, 2, 1);
                    comment.addKarma();
                    comment.addKarma();
                }
                else {
                    comment.setKarma(user, 0, 1);
                    comment.addKarma();
                }
            }
            else if (choice == 2) {
                if (comment.checkUpvoted(user)) {
                    comment.setKarma(user, 1, 2);
                    comment.minKarma();
                    comment.minKarma();
                }
                else if (comment.checkDownvoted(user)) {
                    comment.setKarma(user, 2, 2);
                    comment.addKarma();
                }
                else {
                    comment.setKarma(user, 0, 2);
                    comment.minKarma();
                }
            }
            else if (choice == 3) {
                System.out.println("Replies");
                System.out.println("1- post a reply   2- view randomly   3- view sortedly by karma");
                Scanner scanner1 = new Scanner(System.in);
                int choice1 = scanner1.nextInt();
                if (choice1 == 1) {
                    makeReply(user, comment);
                }
                else if (choice1 == 2) {
                    ArrayList<Pair<Comment, Integer>> sortedComments = new ArrayList<>();
                    for (int i = 0; i < comment.getReplies().size(); i++) {
                        Pair<Comment, Integer> newpair = new Pair<>(comment.getReplies().get(i).getFirst(), comment.getReplies().get(i).getSecond());
                        sortedComments.add(newpair);
                    }
                    // Sort the list based on the second element (integer)
                    Collections.sort(sortedComments, Comparator.comparing(Pair::getSecond));
                    for (int i = sortedComments.size() - 1; i >= 0; i--) {
                        showComment(user, sortedComments.get(i).getFirst());
                    }
                }
                else {
                    for (int i = 0; i < comment.getReplies().size(); i++) {
                        showComment(user, comment.getReplies().get(i).getFirst());
                    }
                }
            }
            else if (choice == 4) {
                stayin = false;
                break;
            }
            else {
                continue;
            }

        }
    }
    public static void showChat(Account user, Chat chat) {
        System.out.println(chat.getThem().getName());
        for (int i = 0; i < chat.getMessages().size(); i++) {
            String theMessage = chat.getMessages().get(i).getSecond();
            if (chat.getMessages().get(i).getFirst()) {
                System.out.print(chat.getThem().getName() + ": ");
                if (theMessage.charAt(0) == '/') {
                    String thePost = theMessage.substring(1);
                    Post foundedPost = Data.getthePost(UUID.fromString(thePost));
                    showPost(user, foundedPost);
                    System.out.print(" ");
                }
                else {
                    System.out.println(theMessage);
                }
            }
            else {
                if (theMessage.charAt(0) == '/') {
                    String thePost = theMessage.substring(1);
                    Post foundedPost = Data.getthePost(UUID.fromString(thePost));
                    showPost(user, foundedPost);
                    System.out.print(" ");
                }
                else {
                    System.out.print(theMessage);
                }
                System.out.println(" :" + user.getName());
            }
        }
        System.out.println("write the new message here(in one line): ");
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        addtoChat(user, chat, message);
    }

    // making stuff functions
    public static void makeSubreddit(Account user) {
        System.out.print("name your subreddit: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Subreddit newSubreddit = new Subreddit(user, name);
        user.addSubreddit(newSubreddit);
        Data.setAllSubreddits(newSubreddit);
    }
    public static void makePost(Account user) {
        System.out.println("choose the subreddit of the post:");
        for (int i = 0; i < user.getJoinedSubreddits().size(); i++) {
            System.out.println((i + 1) + " " + user.getJoinedSubreddits().get(i).getName());
        }
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        Subreddit originSubreddit = user.getJoinedSubreddits().get(choice);
        System.out.print("choose the post title: ");
        Scanner scanner1 = new Scanner(System.in);
        String title = scanner1.nextLine();
        System.out.println("write the body text in  one line: ");
        Scanner scanner2 = new Scanner(System.in);
        String bodeText = scanner2.nextLine();
        Post newPost = new Post(user, title, bodeText, originSubreddit);
        boolean cont = true;
        while(cont) {
            System.out.println("do you want to add tags?\n1-yes   2-no");
            Scanner scanner3 = new Scanner(System.in);
            int choice3 = scanner3.nextInt();
            if (choice3 == 1) {
                System.out.print("enter tag: ");
                Scanner scanner4 = new Scanner(System.in);
                String tag = scanner4.nextLine();
                newPost.addTags(tag);
            }
            else {
                cont = false;
                break;
            }
        }
        System.out.println("posted!");
        user.addUsersPosts(newPost);
        originSubreddit.addPost(newPost);
        Data.setAllPosts(newPost);
        showPost(user, newPost);
    }
    public static void makeComment(Account user, Post post) {
        System.out.println("write the body text in one line: ");
        Scanner scanner = new Scanner(System.in);
        String bodyText = scanner.nextLine();
        Comment newComment = new Comment(user, post, bodyText);
        System.out.println("posted!");
        user.addUsersComment(newComment);
        post.addComment(newComment);
        showComment(user, newComment);
    }
    public static void makeReply(Account user, Comment comment) {
        System.out.println("write the body text in one line: ");
        Scanner scanner = new Scanner(System.in);
        String bodyText = scanner.nextLine();
        Comment newComment = new Comment(user, comment.getPost(), bodyText);
        System.out.println("posted!");
        user.addUsersComment(newComment);
        comment.addReply(newComment);
        showComment(user, newComment);
    }

    public static void makeChat(Account user) {
        System.out.println("choose who you wanna chat with: ");
        for (int i = 0; i < user.getFollowings().size(); i++) {
            System.out.println((i + 1) + " " + user.getFollowings().get(i).getName());
        }
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        Chat newChat = new Chat(user, user.getFollowings().get(choice));
        user.addChat(newChat);
        newChat.getThem().addChat(newChat);
        Data.setAllChats(newChat);
        showChat(user, newChat);
    }
    public static void addtoChat(Account user, Chat chat, String message) {
        chat.newMessage(user, message);
    }
    public static void share(Account user, Account them, Post post) {
        Chat theChat = null;
        for (int i = 0; i < Data.getAllChats().size(); i++) {
            if (Data.getAllChats().get(i).getThem().getUsername().equals(them.getUsername())) {
                theChat = Data.getAllChats().get(i);
            }
        }
        String message = "/" + post.getUniqueName();
        theChat.newMessage(user, message);
    }


}