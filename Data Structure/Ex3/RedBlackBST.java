package RBT;
//http://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
// http://algs4.cs.princeton.edu/home/
import java.util.NoSuchElementException;

public class RedBlackBST {
	private static final boolean RED  = true, BLACK = false;
	private Node root;     // root of the BST

	// BST helper node data type
	private class Node {
		private String key;           // key
		private Integer val;         // associated data
		private Node left, right;  // links to left and right subtrees
		private boolean color;     // color of parent link

		public Node(String key, Integer val, boolean color) {
			this.key = key;
			this.val = val;
			this.color = color;
		}
		public String toString(){
			String c = "red";
			if (!color) c = "black";
			return "key: "+key+" val: "+val+" color: "+c;
		}
	}

	/*************************************************************************
	 *  Node helper methods
	 *************************************************************************/
	// is node x red; false if x is null ?
	private boolean isRed(Node n) {
		if (n == null) return false;
		return (n.color == RED);
	}
	/*************************************************************************
	 *  Standard BST search
	 *************************************************************************/

	// value associated with the given key; null if no such key
	public Integer get(String key) { return get(root, key); }

	// value associated with the given key in subtree rooted at x; null if no such key
	private Integer get(Node x, String key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if      (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else              return x.val;
		}
		return null;
	}
	// is there a key-value pair with the given key?
	public boolean contains(String key) {
		return (get(key) != null);
	}

	/*************************************************************************
	 *  Red-black insertion
	 *************************************************************************/

	// insert the key-value pair; overwrite the old value with the new value
	// if the key is already present
	public void insert(String key, Integer val) {
		root = insert(root, key, val);
		root.color = BLACK;
		// assert check();
	}

	// insert the key-value pair in the subtree rooted at h
	private Node insert(Node h, String key, Integer val) { 
		if (h == null) return new Node(key, val, RED);

		int cmp = key.compareTo(h.key);
		if      (cmp < 0) h.left  = insert(h.left,  key, val); 
		else if (cmp > 0) h.right = insert(h.right, key, val); 
		else              h.val   = val;

		// fix-up any right-leaning links
		if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
		if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);

		return h;
	}

	// rotate right
	private Node rotateRight(Node h) {
		assert (h != null) && isRed(h.left);
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}

	// rotate left
	private Node rotateLeft(Node h) {
		assert (h != null) && isRed(h.right);
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}

	// precondition: two children are red, node is black
	// postcondition: two children are black, node is red
	private void flipColors(Node h) {
		assert !isRed(h) && isRed(h.left) && isRed(h.right);
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	public void printPreorderPlus(){
		printPreorderPlus("", root);
	}
	private void printPreorderPlus(String Path, Node node){
		if (node != null){
			String color = "RED";
			if (!node.color) color = "BLACK";
			System.out.println(node.key + ": " + Path + "  ("+color+")");
			printPreorderPlus(Path+"L", node.left);
			printPreorderPlus(Path+"R", node.right);
		}
	}

	/*************************************************************************
	 *  Red-black deletion
	 *************************************************************************/
	// the smallest key; null if no such key
	public String min() {
		if (isEmpty()) return null;
		return min(root).key;
	} 
	// the smallest key in subtree rooted at x; null if no such key
	private Node min(Node x) { 
		// assert x != null;
		if (x.left == null) return x; 
		else                return min(x.left); 
	} 
	// is this symbol table empty?
	public boolean isEmpty() {
		return root == null;
	}
	// restore red-black tree invariant
	private Node balance(Node h) {
		assert (h != null);

		if (isRed(h.right))                      h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))     flipColors(h);
		return h;
	}
	// Assuming that h is red and both h.left and h.left.left
	// are black, make h.left or one of its children red.
	private Node moveRedLeft(Node h) {
		assert (h != null);
		assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

		flipColors(h);
		if (isRed(h.right.left)) { 
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
		}
		return h;
	}

	// Assuming that h is red and both h.right and h.right.left
	// are black, make h.right or one of its children red.
	private Node moveRedRight(Node h) {
		assert (h != null);
		assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
		flipColors(h);
		if (isRed(h.left.left)) { 
			h = rotateRight(h);
		}
		return h;
	}
	// delete the key-value pair with the minimum key
	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("BST underflow");

		// if both children of root are black, set root to red
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = deleteMin(root);
		if (!isEmpty()) root.color = BLACK;
		//assert check();
	}

	// delete the key-value pair with the minimum key rooted at h
	private Node deleteMin(Node h) { 
		if (h.left == null)
			return null;

		if (!isRed(h.left) && !isRed(h.left.left))
			h = moveRedLeft(h);

		h.left = deleteMin(h.left);
		return balance(h);
	}

	// delete the key-value pair with the given key
	public void delete(String key) { 
		if (!contains(key)) {
			System.err.println("symbol table does not contain " + key);
			return;
		}

		// if both children of root are black, set root to red
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = delete(root, key);
		if (!isEmpty()) root.color = BLACK;
		// assert check();
	}

	// delete the key-value pair with the given key rooted at h
	private Node delete(Node h, String key) { 

		if (key.compareTo(h.key) < 0)  {
			if (!isRed(h.left) && !isRed(h.left.left))
				h = moveRedLeft(h);
			h.left = delete(h.left, key);
		}
		else {
			if (isRed(h.left))
				h = rotateRight(h);
			if (key.compareTo(h.key) == 0 && (h.right == null))
				return null;
			if (!isRed(h.right) && !isRed(h.right.left))
				h = moveRedRight(h);
			if (key.compareTo(h.key) == 0) {
				Node x = min(h.right);
				h.key = x.key;
				h.val = x.val;
				h.right = deleteMin(h.right);
			}
			else h.right = delete(h.right, key);
		}
		return balance(h);
	}



	public static void main(String[] args) {
		String test = "a b c";// d e f"; 
		String[] keys = test.split(" "); 
		RedBlackBST st = new RedBlackBST();
		for (int i = 0; i < keys.length; i++){ 
			st.insert(keys[i], i); 
			st.printPreorderPlus();
			System.out.println();
		}
		System.out.println();
		st.printPreorderPlus();
		System.out.println("\n delete c");
		st.delete("c");
		st.printPreorderPlus();
	}
}
