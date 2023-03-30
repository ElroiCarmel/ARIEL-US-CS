import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class EX1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {1,4,4,4,4,4,6,8,9,10};
		System.out.println(ones(test, in));
	}
	public static int ones(int[] a, int k) {
		// find last index
		int s = 0;
		int e = a.length-1;
		int in_last=-1;
		while (s<=e) {
			int mid = (s+e)/2;
			if (a[mid]==k) {
				in_last = mid;
				s=mid+1;
			} else if (a[mid]<k) {
				s=mid+1;
			} else {
				e=mid-1;
			}
		}
		if (in_last==-1) return 0; // meaning k is not in the array
		// find first index
		s=0;
		e=in_last;
		int in_first = -1;
		while (s<=e) {
			int mid = (s+e)/2;
			if (a[mid]==k) {
				in_first = mid;
				e=mid-1;
			} else if (a[mid]<k) {
				s=mid+1;
			} else {
				e=mid-1;
			}
		}
		return (in_last-in_first+1);
	}
	
	// Classic binary search
	public static int bs(int[] arr, int q) {
		int s = 0;
		int e = arr.length-1;
		while (s<=e) {
			int mid = (e+s)/2;
			if (arr[mid]==q) return mid;
			if (arr[mid]<q) {
				s=mid+1;
			} else {
				e=mid-1;
			}
		}
		return -1;
	}
}
