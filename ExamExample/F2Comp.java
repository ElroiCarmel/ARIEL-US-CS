import java.util.Comparator;

public class F2Comp implements Comparator<F2>{
    public F2Comp() {}

    @Override
    public int compare(F2 p1, F2 p2) {
        int ans=0;
        double p1y = p1.f(p1.extreme(p1));
        double p2y = p2.f(p2.extreme(p2));
        if (p1y<p2y) ans = -1;
        if (p1y>p2y) ans = 1;
        if (p1y==p2y) ans = 0;
        return ans;
    }
}
