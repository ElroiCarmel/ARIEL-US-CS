import java.util.ArrayList;
import java.util.Arrays;

public class Q7 {
    public static void main(String[] args) {
        double[] array = {4, 2, 11,77,33,56,89,19,3, 5, 0, 1,100};
        System.out.println(Arrays.toString(subMono(array)));
    }

    public static double[] subMono(double [] arr) {
        ArrayList<ArrayList<Double>> subArr = new ArrayList<>();
        int start = 0;
        while (start<arr.length) {
            ArrayList<Double> tempList = new ArrayList<>();
            tempList.add(arr[start]);
            for (int i = start+1; i<arr.length; i++) {
                if (arr[i]>tempList.get(tempList.size()-1)) {
                    tempList.add(arr[i]);
                }
            }
            subArr.add(tempList);
            start++;
        }
        int max=0;
        for (int i=0; i<subArr.size(); i++) {
            if (subArr.get(i).size()>subArr.get(max).size()) max = i;
        }
        double[] ans = new double[subArr.get(max).size()];
        for (int i=0; i< ans.length; i++) {
            ans[i] = subArr.get(max).get(i);
        }
        return ans;
    }
}
