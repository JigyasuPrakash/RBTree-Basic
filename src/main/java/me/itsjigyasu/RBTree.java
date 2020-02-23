package me.itsjigyasu;

public class RBTree{
    // Global tree variables
    Node NIL, root;

    // Node class
    class Node {
        char color;
        int data;
        Node left = null;
        Node right = null;
        Node parent = null;

        public Node() {
            this.data = -1;
            this.color = 'B';
            this.left = null;
            this.right = null;
        }

        public Node(int v) {
            this.data = v;
            this.color = 'R';
            this.left = NIL;
            this.right = NIL;
        }
    }

    // Tree Initialization
    public RBTree() {
        this.NIL = new Node();
        this.NIL.left = NIL;
        this.NIL.right = NIL;
        root = NIL;
        root.parent = NIL;
    }

    // Returns Root Node of Tree
    public Node getRootNode() {
        return this.root;
    }

    // Return Node for searched key from tree
    Node getNode(int key) {
        Node curr = root;
        while (curr != NIL) {
            if (key == curr.data) {
                return curr;
            }
            if (key < curr.data) {
                curr = curr.left;
            } else if (key > curr.data) {
                curr = curr.right;
            }
        }
        return NIL;
    }

    // In order traversal
    public void printInorder(Node node) {
        if (node == NIL) {
            return;
        }
        printInorder(node.left);
        System.out.print(node.data + "(" + node.color + "), ");
        printInorder(node.right);
    }

}