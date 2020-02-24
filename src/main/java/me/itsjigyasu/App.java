package me.itsjigyasu;

import java.util.Scanner;

public final class App {

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        RBTree rbt = new RBTree();
        Boolean loop = true;
        while(loop){
            System.out.println("\n------Menu------");
            System.out.println("1. Insert\t2. Delete\t3. Print Tree\t0. Exit");
            int opt = sc.nextInt();
            switch(opt){
                case 1: 
                    System.out.print("Enter key to insert: ");
                    int m = sc.nextInt();
                    rbt.insert(m);
                    break;
                case 2: 
                    System.out.print("Enter key to delete: ");
                    int n = sc.nextInt();
                    rbt.delete(n);
                    break;
                case 3:
                    System.out.println("Inorder Traversal: ");
                    rbt.printInorder(rbt.getRootNode());
                    break;
                case 0:
                    loop = false;
                    break;
                default: System.out.println("Invalid input");
            }
        }
         //sample input
         //41, 38, 31, 12, 19, 8
    }
}