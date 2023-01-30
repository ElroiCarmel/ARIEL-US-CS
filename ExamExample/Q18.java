import java.util.Arrays;

public class Q18 {
    public static void main(String[] args) {
        String[] test = allCode();
        for (int i=0; i< test.length; i++) {
            System.out.println(test[i]);
        }
    }
    public static String[] allCode() {
        String[] ans = new String[120];
        int index = 0;
        for (int a=1; a<=5; a++) {
            for (int b=1;b<=5;b++) {
                for (int c = 1; c<=5; c++) {
                    for (int d=1; d<=5; d++) {
                        if (notEq(a,b,c,d)) {
                            ans[index] = ""+a+b+c+d+"#";
                            index++;
                        }
                    }
                }
            }
        }
        return ans;
    }
    public static boolean notEq(int a,int b,int c,int d) {
        return (a!=b&&a!=c&&a!=d&&b!=c&&b!=d&&c!=d);
    }
}
