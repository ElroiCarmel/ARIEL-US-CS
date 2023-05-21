package RBT;

// https://www.happycoders.eu/algorithms/red-black-tree-java/
public class Node {
    int data;
    Node left, right, parent;
    boolean color;
    public Node(int data) {
        this.data = data;
    }
    public String toString(){
        String c = color ? "black" : "red";
        return data + ", " + c;
    }
}
