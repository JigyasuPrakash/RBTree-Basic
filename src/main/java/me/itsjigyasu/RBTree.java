package me.itsjigyasu;

public class RBTree {
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
        int comparisonCounter = 0;
        Node z = getNode(value);
        if (z.data != -1) {
            System.out.println("Cannot insert duplicate keys!!");
            return;
        }
        z = new Node(value);
        Node y = NIL;
        Node x = root;

        while (x != NIL) {
            y = x;
            comparisonCounter++;
            if (z.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        comparisonCounter++;
        if (y == NIL) {
            this.root = z;
        } else if (z.data < y.data) {
            comparisonCounter++;
            y.left = z;
        } else {
            y.right = z;
        }
        z.right = NIL;
        z.left = NIL;
        z.color = 'R';

        insertFixup(z, comparisonCounter);
        System.out.println("Inserted Successfully");
    }

    // Red Black Tree Inser Fixup
    void insertFixup(Node z, int comparisonCounter) {
        int rotationCounter = 0;
        int recoloringCounter = 0;

        Node y = NIL;
        while (z.parent.color == 'R') {
            comparisonCounter++;
            if (z.parent == z.parent.parent.left) {
                y = z.parent.parent.right;
                comparisonCounter++;
                if (y.color == 'R') {
                    z.parent.color = 'B';
                    y.color = 'B';
                    z.parent.parent.color = 'R';
                    recoloringCounter += 3;
                    z = z.parent.parent;
                } else {
                    comparisonCounter++;
                    if (z == z.parent.right) {
                        z = z.parent;
                        rotationCounter++;
                        leftRotate(z);
                    }
                    z.parent.color = 'B';
                    z.parent.parent.color = 'R';
                    recoloringCounter += 2;
                    rotationCounter++;
                    rightRotate(z.parent.parent);
                }
            } else {
                y = z.parent.parent.left;
                comparisonCounter++;
                if (y.color == 'R') {
                    z.parent.color = 'B';
                    y.color = 'B';
                    z.parent.parent.color = 'R';
                    recoloringCounter += 3;
                    z = z.parent.parent;
                } else {
                    comparisonCounter++;
                    if (z == z.parent.left) {
                        z = z.parent;
                        rotationCounter++;
                        rightRotate(z);
                    }
                    z.parent.color = 'B';
                    z.parent.parent.color = 'B';
                    recoloringCounter += 2;
                    rotationCounter++;
                    leftRotate(z.parent.parent);
                }
            }
        }
        this.root.color = 'B';
        System.out.println("No of rotations: " + rotationCounter);
        System.out.println("No of recoloring: " + recoloringCounter);
        System.out.println("No of comparisons: " + comparisonCounter);
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

    // basic BST deletion
    public void delete(int key) {
        int comparisonCounter = 0;
        Node z = getNode(key);
        comparisonCounter++;
        if (z == NIL) {
            System.out.println("Cannot delete, Element Not Found!!");
            return;
        }
        Node x;
        Node y = z;
        char YOriginalColor = y.color;
        comparisonCounter++;
        if (z.left == NIL) {
            x = z.right;
            RBTransplant(z, z.right);
        } else if (z.right == NIL) {
            comparisonCounter++;
            x = z.left;
            RBTransplant(z, z.left);
        } else {
            y = treeMinimum(z.right);
            YOriginalColor = y.color;
            x = y.right;
            comparisonCounter++;
            if (y.parent == z) {
                x.parent = y;
            } else {
                RBTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            RBTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        comparisonCounter++;
        if (YOriginalColor == 'B') {
            // x.color = 'B';
            deleteFixup(x, comparisonCounter);
        }
        System.out.println("Deleted Successfully!");
    }

    // Red Black Tree Delete Fixup
    void deleteFixup(Node x, int comparisonCounter) {
        int rotationCounter = 0;
        int recoloringCounter = 1;
        Node w = NIL;
        while (x != this.root && x.color == 'B') {
            comparisonCounter++;
            if (x == x.parent.left) {
                comparisonCounter++;
                w = w.parent.right;
                if (w.color == 'R') {
                    w.color = 'B';
                    x.parent.color = 'R';
                    recoloringCounter += 2;
                    rotationCounter++;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                comparisonCounter++;
                if (w.left.color == 'B' && w.right.color == 'B') {
                    w.color = 'R';
                    recoloringCounter++;
                    x = x.parent;
                } else {
                    comparisonCounter++;
                    if (w.right.color == 'B') {
                        w.left.color = 'R';
                        w.color = 'R';
                        recoloringCounter += 2;
                        rotationCounter++;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = 'B';
                    w.right.color = 'B';
                    recoloringCounter += 3;
                    rotationCounter++;
                    leftRotate(x.parent);
                    x = this.root;
                }
            } else {
                w = x.parent.left;
                comparisonCounter++;
                if (w.color == 'R') {
                    w.color = 'B';
                    x.parent.color = 'R';
                    recoloringCounter += 2;
                    rotationCounter++;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                comparisonCounter++;
                if (w.right.color == 'B' && w.left.color == 'B') {
                    w.color = 'R';
                    recoloringCounter++;
                    x = x.parent;
                } else {
                    comparisonCounter++;
                    if (w.left.color == 'B') {
                        w.right.color = 'R';
                        w.color = 'R';
                        recoloringCounter += 2;
                        rotationCounter++;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = 'B';
                    w.left.color = 'B';
                    recoloringCounter += 2;
                    rotationCounter++;
                    rightRotate(x.parent);
                    x = this.root;
                }
            }
        }
        this.root.color = 'B';
        recoloringCounter++;
        System.out.println("No of rotations: " + rotationCounter);
        System.out.println("No of recoloring: " + recoloringCounter);
        System.out.println("No of comparisons: " + comparisonCounter);
    }

    // Other Tree functions
    Node minValue(Node n) {
        Node current = n;
        while (current.left != NIL) {
            current = current.left;
        }
        return current;
    }

    void RBTransplant(Node u, Node v) {
        if (u.parent == NIL) {
            this.root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    Node treeMinimum(Node n) {
        if (n.right != NIL) {
            return minValue(n.right);
        }
        Node p = n.parent;
        while (p != NIL && n == p.right) {
            n = p;
            p = p.parent;
        }
        return p;
    }

}