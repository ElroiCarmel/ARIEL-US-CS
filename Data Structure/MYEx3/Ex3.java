package Ex3;

public class Ex3 {
	static final boolean RED = false;
    static final boolean BLACK = true;
	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		
	}
	
	public static boolean isValidBST(BinaryTree bt) {
		return isValidBST(bt.getRoot(), Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	

	private static boolean isValidBST(Node current, int min, int max) {
		if(current.getKey()<min || current.getKey()>max) return false;
		if(current.getLeft()!=null && !isValidBST(current.getLeft(), min, current.getKey()))
			return false;
		if (current.getRight()!=null && !isValidBST(current.getRight(), current.getKey(), max))
			return false;
		return true;
	}
	
	public static boolean hasValidHeight(BinaryTree bt) {
		int numBlacks = blacksInPath(bt.getRoot());
		return hasValidHeight(bt.getRoot(), 0, numBlacks);
		
	}
	
	public static boolean hasValidHeight(Node current, int blacksCount, int check) {
		if (current==null) return blacksCount==check;
		if (current.getColor()==BLACK) blacksCount++;
		if (blacksCount>check) return false;
		boolean right = hasValidHeight(current.getRight(), blacksCount, check);
		boolean left = hasValidHeight(current.getLeft(), blacksCount, check);
		return right && left;
	}
	
	private static int blacksInPath(Node root) {
		if (root==null) return 0;
		if (root.getColor()==BLACK) return 1+blacksInPath(root.getRight());
		return blacksInPath(root.getRight());
	}
	
}
