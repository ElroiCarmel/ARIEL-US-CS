import java.util.Arrays;

public class Q11 {
    public static void main(String[] args) {
        String sen = "abrakadabra";
        System.out.println(sub3(sen));
        System.out.println(sub4(sen));
    }
    public static String sub1(String s) {
        String ans = "";
        int[] counter = new int["z".compareTo("a")+1];
        System.out.println(counter.length);
        for (int i =0; i<s.length(); i++) {
            String s1 = "" + s.charAt(i);
            counter[s1.compareTo("a")]++;
        }
        char[] abc = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (int i=0; i<counter.length; i++) {
            if (counter[i]==1) {
                ans+= "" + abc[i];
            }
        }
        return ans;
    }
    public static String sub2(String s) {
        int[] counter = new int["z".compareTo("a")+1];
        System.out.println(counter.length);
        for (int i =0; i<s.length(); i++) {
            String s1 = "" + s.charAt(i);
            counter[s1.compareTo("a")]++;
        }
        char[] abc = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        String ans = s;
        for (int i=0; i<counter.length; i++) {
            if (counter[i]!=1) {
                ans = ans.replace(""+abc[i], "");
            }
        }
        return ans;
    }
    public static String sub3(String s) {
        int[] counter = new int["z".compareTo("a")+1];
        System.out.println(counter.length);
        for (int i =0; i<s.length(); i++) {
            String s1 = "" + s.charAt(i);
            counter[s1.compareTo("a")]++;
        }
//        char[] abc = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        String abc = "abcdefghijklmnopqrstuvwxyz";
        String ans = s;
        for (int i=0; i<counter.length; i++) {
            if (counter[i]>1) {
                ans = ans.replace(""+abc.charAt(i), "");
            }
        }
        return ans;
    }
    public static String sub4(String s) {
        // Each cell in the array represents how
        // many times the letter is shown in the word
        int[] counter = new int["z".compareTo("a")+1];
        for (int i =0; i<s.length(); i++) {
            String s1 = "" + s.charAt(i);
            counter[s1.compareTo("a")]++;
        }
        String ans = "";
        for (int i=0; i<s.length(); i++) {
            String s1 = "" + s.charAt(i);
            if (counter[s1.compareTo("a")]==1) {
                ans = ans + s1;
            }
        }
        return ans;
    }
}
