import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Q11Test {

    @Test
    void sub1Test() {
        String check = "abracadabra";
        long start = System.currentTimeMillis();
        String ans = Q11.sub1(check);
        long end = System.currentTimeMillis();
        double dt_sec = (end-start)/1000.0;
        System.out.println("time of process = "+dt_sec);
        assertEquals("cd", ans);
    }

    @Test
    void sub2Test() {
        String check = "abracadabra";
        long start = System.currentTimeMillis();
        String ans = Q11.sub2(check);
        long end = System.currentTimeMillis();
        double dt_sec = (end-start)/1000.0;
        System.out.println("time of process = "+dt_sec);
        assertEquals("cd", ans);
    }
    @Test
    void sub3Test() {
        String check = "abracadabra";
        long start = System.currentTimeMillis();
        String ans = Q11.sub3(check);
        long end = System.currentTimeMillis();
        double dt_sec = (end-start)/1000.0;
        System.out.println("time of process = "+dt_sec);
        assertEquals("cd", ans);
    }
}