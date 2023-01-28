import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Q1Test {
    @Test
    public void range() {
        int[] t = Q1.d_range(7);
        assertEquals(0, t.length);
        int[] t2 = Q1.d_range(8);
        assertEquals(3, t2.length);
        int[] t2Num = {8, 9 ,10};
        assertArrayEquals(t2Num, t2);

    }

}