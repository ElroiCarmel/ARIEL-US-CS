import java.util.Arrays;
import java.util.Random;

public class bubble {
    public static void main(String[] args) {
        int[] test = new int[30];
        Random random = new Random();
        for (int i=0; i<test.length; i++) {
            int r = random.nextInt(102);
            test[i] = r;
        }
        System.out.println(Arrays.toString(test));
        bubbleSort(test);
        System.out.println(Arrays.toString(test));

    }
    public static void bubbleSort(int[] arr) {
        for (int i=0; i<arr.length-1; i++) {
            for (int j=0; j<arr.length-1-i; j++) {
                if (arr[j]>arr[j+1]) {
                    int temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }
}
