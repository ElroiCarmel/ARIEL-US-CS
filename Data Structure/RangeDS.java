import java.util.Arrays;
import java.util.LinkedList;

public class RangeDS {
	private Object[] arr = new Object[10];
	private int num_ranges;
	
	public RangeDS() {
		for (int i=0; i<arr.length; i++) {
			arr[i] = new LinkedList<Double>();
		}
		num_ranges=0;
	}
	
	public void add(double num) {
		if (((LinkedList<Double>) arr[(int) num]).isEmpty()) num_ranges++;
		((LinkedList<Double>) arr[(int) num]).add(num);
	}
	public LinkedList<Double> get(int i) {
		return (LinkedList<Double>) arr[i];
	}
	public void del(int i) {
		if (!((LinkedList<Double>) arr[i]).isEmpty()) num_ranges--;
		((LinkedList<Double>) arr[i]).clear();
//		arr[i] = null;
	}
	public int getRanges() {
		return num_ranges;
	}
	@Override
	public String toString() {
		return Arrays.toString(arr);
	}
}
