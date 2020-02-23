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

    // Simple BST Insertion
    public void insert(int value) {
        Node z = new Node(value);
        Node y = NIL;
        Node x = root;

        while (x != NIL) {
            y = x;
            if (z.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == NIL) {
            this.root = z;
        } else if (z.data < y.data) {
            y.left = z;
        } else {
            y.right = z;
        }
        z.right = NIL;
        z.left = NIL;
        z.color = 'R';

        insertFixup(z);
    }

    // Red Black Tree Inser Fixup
    void insertFixup(Node z) {
        Node y = NIL;
        while (z.parent.color == 'R') {
            if (z.parent == z.parent.parent.left) {
                y = z.parent.parent.right;
                if (y.color == 'R') {
                    z.parent.color = 'B';
                    y.color = 'B';
                    z.parent.parent.color = 'R';
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color = 'B';
                    z.parent.parent.color = 'R';
                    rightRotate(z.parent.parent);
                }
            } else {
                y = z.parent.parent.left;
                if (y.color == 'R') {
                    z.parent.color = 'B';
                    y.color = 'B';
                    z.parent.parent.color = 'R';
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = 'B';
                    z.parent.parent.color = 'B';
                    leftRotate(z.parent.parent);
                }
            }
        }
        this.root.color = 'B';
    }

    // Left Rotate
    void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == NIL) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    // Right Rotate
    void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != NIL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == NIL) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

}