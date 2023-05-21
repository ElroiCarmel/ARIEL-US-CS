package Ex3;

public class Node {
	private final Integer key; //associated data
	private final boolean color; // true means "black" and false means "red"
	private Node left, right;
	
	//constructor
	public Node(Integer data, boolean color) {
		this.key = data;
		this.color = color;
	}
	public Integer getKey() {
		return this.key;
	}
	public boolean getColor() {
		return this.color;
	}
	public Node getLeft() {
		return this.left;
	}
	public Node getRight() {
		return this.right;
	}
	
	@Override
	public String toString() {
		if (this.color==true) return "data: "+ key + ", color: black";
		return "data: "+ key + ", color: red";
	}
}


