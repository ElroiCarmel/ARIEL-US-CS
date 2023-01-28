import java.util.ArrayList;
import java.util.Arrays;

public class Q1 {
    public static void main(String[] args) {
        int[] trial = d_range(1);
        System.out.println(isPrime(9));
        System.out.println(Arrays.toString(trial));
    }
    public static boolean isPrime(int num) {
        if (num==2) return true;
        if (num<=1 || num%2==0) return false;
        for (int i=3; i<=Math.sqrt(num); i+=2) {
            if (num%i==0) return false;
        }
        return true;
    }

    public static int gcd(int num1, int num2) {
        int max = Math.max(num1, num2);
        int min = Math.min(num1, num2);
        int remainder = max%min;
        if (remainder==0) return min;
        while (remainder!=0) {
            max = min;
            min = remainder;
            remainder = max%min;
        }
        return min;
    }
    public static int[] d_range(int num) {
        if (isPrime(num) || num<=2) return new int[0];
        ArrayList<Integer> parts = new ArrayList<>();
        parts.add(num);
        int numUp = num+1;
        int numDown = num-1;
        while (!isPrime(numDown)) {
            parts.add(numDown);
            numDown--;
        }
        while (!isPrime(numUp)) {
            parts.add(numUp);
            numUp++;
        }
        int[] ans = new int[parts.size()];
        for (int i=0; i<ans.length; i++) {
            ans[i] = parts.get(i);
        }
        Arrays.sort(ans);
        return ans;
    }
}
