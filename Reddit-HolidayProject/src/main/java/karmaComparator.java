import java.util.Collections;
import java.util.Comparator;

public class karmaComparator implements Comparator<Post> {
    @Override
    public int compare(Post p1, Post p2) {
        return p1.getKarma() - p2.getKarma();
    }
}
