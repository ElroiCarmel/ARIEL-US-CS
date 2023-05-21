package RBT;

public class TestRedBlackTree {
	
	static boolean REACHED = false;
	static int blacksInPath = -1;
	public static void main(String[] args) {
		RedBlackTree bt = new RedBlackTree();
//		bt.insert(1);
//		bt.insert(2);
//		bt.insert(3);
		for (int i=1; i<21;i++) {
			bt.insert(i);
		}
		bt.printPreorderPlus();
		System.out.println(findMinBlack(bt));
		System.out.println(isValidV2(bt));
		bt.printPreorderPlus();
//		bt.root.right.right.color=false;
		System.out.println(isValidV2(bt));
//		System.out.println(isValid(bt));

		

	}
	
	public static boolean isValid(RedBlackTree bt) {
		int blackCounts = 0;
//		int blacksInPath = -1;
//		boolean reachedToFirst = false;
		return isValidHelper(bt.root, blackCounts);
	}
	
	private static boolean isValidHelper(Node current, int blackCounts) {
		if (current == null) {
			blackCounts+=1;
			if (!REACHED) {
				blacksInPath = blackCounts;
				REACHED = true;
				return true;
			} else {
				return blackCounts==blacksInPath;
			}
		}
		if (current.color==true) blackCounts+=1;
		if (REACHED && blackCounts>blacksInPath) return false;
		boolean left = isValidHelper(current.left, blackCounts);
		boolean right = isValidHelper(current.right, blackCounts);
		return left && right;
	}
	public static boolean isValidV2(RedBlackTree bt) {
		int blacksAllowed = findMinBlack(bt);
		return isValidHelperV2(bt.root, -1, blacksAllowed);
	}
	
	private static boolean isValidHelperV2(Node current, int blackCounts, int check) {
		if (current==null) return 1+blackCounts==check;
		if (current.color==true) blackCounts+=1;
		if (blackCounts>check) return false;
		boolean left = isValidHelperV2(current.left, blackCounts, check);
		boolean right = isValidHelperV2(current.right, blackCounts, check);
		return left&&right;
	}
	public static int findMinBlack(RedBlackTree bt) {
		return -1+findMinBlack(bt.root);
	}
	
	public static int findMinBlack(Node current) {
		if (current==null) return 1;
		if (current.color==true) 
			return 1+Math.min(findMinBlack(current.left), findMinBlack(current.right));
		return Math.min(findMinBlack(current.left), findMinBlack(current.right));
	}
	
}
