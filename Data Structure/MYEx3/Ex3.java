package Ex3;

public class Ex3 {
	static final boolean RED = false;
    	static final boolean BLACK = true;
	public static void main(String[] args) {
		
		
	}
	
	public static boolean isValidBST(BinaryTree bt) {
		return isValidBST(bt.getRoot(), Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	/**
	 * The method goes through the tree, and for each node determines the right range
	 * of which in it it should be. e.g. the tree:
	 *    ****6*****
	 *    4***  ***8*
	 *    **5*  *7**
	 * so Node "5" range is (Parent's key, Parent's right limit) = (4 , 6) 
	 * 
	 * @param current. The current tree's node
	 * @param min. Left limit of the node's range
	 * @param max. Right limit of the node's range
	 * @return boolean whether the tree is a valid binary search tree
	 */
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
	/**
	 * The main idea is to travel through the RBT and each time we 
	 * get to a null root,it means we found a "distinct" path from root to a leaf
	 * since null nodes are considered as leaves in a RBT. Than we check whether
	 * the number of black nodes from the root to that null node is valid (with
	 * the help of "blacksInPath" function).
	 * @param current. The current node of the tree.
	 * @param blacksCount. The number of black nodes from the root to this node.
	 * @param check. The exact number of black nodes each path should have.
	 * @return true if all paths from root to a null/leaf contain same number of black nodes
	 *         else returns false.
	 */
	public static boolean hasValidHeight(Node current, int blacksCount, int check) {
		if (current==null) return blacksCount==check;
		if (current.getColor()==BLACK) blacksCount++;
		if (blacksCount>check) return false;
		boolean right = hasValidHeight(current.getRight(), blacksCount, check);
		boolean left = hasValidHeight(current.getLeft(), blacksCount, check);
		return right && left;
	}
	/**
	 * Simple function that counts how many black nodes are there
	 * in the path from the root to the most right leaf.
	 * Since in a RBT all routes must containsame number of black nodes
	 * it's enough to choose one path.
	 * @param root. The RBT root
	 * @return Number of black nodes from root to most-right leaf
	 */
	private static int blacksInPath(Node root) {
		if (root==null) return 0;
		if (root.getColor()==BLACK) return 1+blacksInPath(root.getRight());
		return blacksInPath(root.getRight());
	}
	
}
