import java.util.ArrayList;

public class Q2 {
    public static void main(String[] args) {
        ArrayList<String> test = new ArrayList<>();
        test.add("aa");
        test.add(" b b ");
        test.add("c");
        String testLine = "";
        System.out.println(isPer(test, testLine));
    }
    public static boolean isPer(ArrayList<String> words, String line) {
        for (int i=0; i<words.size(); i++) {
            if (!line.contains(words.get(i))) return false;
            line = line.replace(words.get(i), "");
        }
        if (line.length()==0) {
            return true;
        }
        return false;
    }
}
