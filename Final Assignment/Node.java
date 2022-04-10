/*
    Nodes of the ternary search tree
 */

public class Node {

    char data;
    Node left;
    Node right;
    Node middle;
    String stopInfo = new String();

    public Node(char data, String[]stopInfo){
        this.data = data;
        this.left = null;
        this.right = null;
        this.middle = null;
        this.stopInfo = null;
    }
}
