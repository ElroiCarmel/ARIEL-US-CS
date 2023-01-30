import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Q5 {
    public static <List> void main(String[] args) {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(7);
        F2 pa = new F2(5,7,8);
        list.add(pa);
        list.add("hello");
        System.out.println(list);
    }
//    public static int Q11(LinkedList<Object>) {
//
//    }
}
