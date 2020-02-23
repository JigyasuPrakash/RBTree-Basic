package me.itsjigyasu;


public final class App {

    public static void main(String[] args) {
        RBTree rbt = new RBTree();
        
         //inserting below keys to RBTree
         rbt.insert(41);
         rbt.insert(38);
         rbt.insert(31);
         rbt.insert(12);
         rbt.insert(19);
         rbt.insert(8);
 
         System.out.println("Inorder: ");
         rbt.printInorder(rbt.getRootNode());
    }
}