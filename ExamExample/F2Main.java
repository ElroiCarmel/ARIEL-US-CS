import java.util.ArrayList;

public class F2Main {
    public static void main(String[] args) {
        F2 p1 = new F2(4,6,2);
        F2 p2 = new F2(1,3,9);
        F2 p3 = new F2(22,34,11);
        F2 p4 = new F2(1.8,6.34,7.54);
        ArrayList<F2> ll = new ArrayList<>();
        ll.add(p1);
        ll.add(p2);
        ll.add(p3);
        ll.add(p4);
        System.out.println(ll);
        F2.sort(ll);
        System.out.println(ll);


    }
}
