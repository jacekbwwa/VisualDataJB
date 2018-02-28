package jb;

import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */

public class BST {
    // class represents binary search tree

    private BSTNode root;    
    public int sumNodes;
    
    public ArrayList<BSTNode> BSTList = new ArrayList();

    // Constructor bst
    public BST() {
        // creates an empty binary tree
        root = null;
    }

    public BSTNode getRoot() {
        return root;
    }

    // Method checks if bst is empty 
    public boolean isEmpty() {
        if (root == null) {
            return true;
        } else {
            return false;
        }        
    }

    //Method inserts data into bst
    public void insert(int data) {
        root = insert(root, data);
    }

    //Method inserts data recursively into bst node
    private BSTNode insert(BSTNode node, int data) {
        if (node == null) {
            node = new BSTNode(data);
        } else if (data <= node.getData()) {
            node.left = insert(node.left, data);
        } else {
            node.right = insert(node.right, data);
        }

        return node;
    }    

    // Method deletes data k from bst
    public void delete(int data) {

        if (isEmpty()) {
            // if empty => do nothng
            return;
        } else if (search(data) == false) {
            // if is not present, do nothing
            return;
        } else {
            // data is deleted from the tree
            root = delete(root, data);            
        }
    }

    private BSTNode delete(BSTNode root, int data) {
        BSTNode p, p2;
        BSTNode n;

        if (root.getData() == data) {
            BSTNode left;
            BSTNode right;

            left = root.getLeft();
            right = root.getRight();

            if (left == null && right == null) {
                return null;
            } else if (left == null) {
                return right;                
            } else if (right == null) {
                return left;                
            } else {
                p2 = right;
                p = right;
                while (p.getLeft() != null) {
                    p = p.getLeft();
                }
                p.setLeft(left);
                return p2;
            }
        }

        if (data < root.getData()) {
            n = delete(root.getLeft(), data);
            root.setLeft(n);
        } else {
            n = delete(root.getRight(), data);
            root.setRight(n);
        }
        return root;
    }

    // Method search data in bst
    public boolean search(int data) {
        return search(root, data);
    }

    //Method search data in bst recursively 
    private boolean search(BSTNode node, int data) {
        boolean found = false;
        while ((node != null) && !found) {
            int rval = node.getData();

            if (data < rval) {
                node = node.getLeft();
            } else if (data > rval) {
                node = node.getRight();
            } else {
                found = true;
                break;
            }
            found = search(node, data);
        }
        return found;
    }    

    //Method inorder traversal
    public void inorder() {
        inorder(root);
    }

    private void inorder(BSTNode r) {
        if (r != null) {
            inorder(r.getLeft());
            System.out.print(r.getData() + " ");
            inorder(r.getRight());
        }
    }

    private void inorder(BSTNode r, int dN) {        
        if (r != null) {
            inorder(r.getLeft(), dN + 1); //depth is increased 
            r.setX(sumNodes++ + 1);
            r.setY(dN - 1);
            inorder(r.getRight(), dN + 1);
        }
    }

    private void NodePosition() {
        int depthN = 1;
        inorder(root, depthN);
    }

    //Method generate k- random elements and insert them in bst
    public void generateNodesData(int k) {

        Random random = new Random();        
        UsedNumbersList usedN = new UsedNumbersList();
        for (int idx = 1; idx <= k; ++idx) {
            int randomInt = random.nextInt(100);
            while (usedN.checkIfUsedNumber(randomInt) == true) {
                randomInt = random.nextInt(100);
            }            
            usedN.insertNumber(randomInt);
            insert(randomInt);            
        }
    }

    public int TreeHeight() {
        return TreeHeight(root);
    }

    public int TreeHeight(BSTNode node) {
        if (node == null) {
            return -1;
        } else {
            return Math.max(TreeHeight(node.left),
                    TreeHeight(node.right)) + 1;
        }
    }

    // Method create bst with k- random elements
    public void createTree(BSTNode node, int k) {
        generateNodesData(k);
        sumNodes = 0;
        NodePosition();
        //int maxTreeHight = TreeHeight(root);
    }

    //Method preorder traversal 
    public void preorder() {
        preorder(root);
    }

    private void preorder(BSTNode n) {
        if (n != null) {
            System.out.print(n.getData() + " ");
            preorder(n.getLeft());
            preorder(n.getRight());
        }
    }

    //Method postorder traversal 
    public void postorder() {
        postorder(root);
    }

    private void postorder(BSTNode n) {
        if (n != null) {
            postorder(n.getLeft());
            postorder(n.getRight());
            System.out.print(n.getData() + " ");
        }
    }    

    //Method returns numbers of nodes in bst
    public int size() {
        return (size(root));
    }

    private int size(BSTNode node) {
        if (node == null) {
            return (0);
        } else {
            return (size(node.left) + 1 + size(node.right));
        }
    }
    
    //Methods returns a depth of the tree, dist. from root to leaf
    public int maxDepth() {
        return maxDepth(root);
    }

    private int maxDepth(BSTNode node) {
        if (node == null) {
            return (0);
        } else {
            int leftDepth = maxDepth(node.left);
            int rightDepth = maxDepth(node.right);           
            return (Math.max(leftDepth, rightDepth) + 1);
        }
    }
	
	/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
 

    //Method returns min value in bst
    public int minValue() {
        return minValue(root);
    }

    private int minValue(BSTNode node) {
        BSTNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        BSTList.add(current);
        return current.data;
    }

    //Method returns max value in bst
    public int maxValue() {
        return maxValue(root);
    }

    private int maxValue(BSTNode node) {
        BSTNode current = node;
        while (current.right != null) {
            current = current.right;

        }
        BSTList.add(current);
        return current.data;
    }
    
    //Method prints the node values in the 'inorder' order
    public void printTreeInOrder() {
        printTreeInOrder(root);
        System.out.println();
    }

    private void printTreeInOrder(BSTNode node) {
        if (node == null) {
            return;
        } 
        //left, node itself, right
        printTreeInOrder(node.left);
        System.out.print(node.data + " ");
        BSTList.add(node);
        printTreeInOrder(node.right);            
    }
   
    //Method prints the node values in the 'postorder' order
    public void printTreePostOrder() {
        printTreePostOrder(root);
        System.out.println();
    }

    private void printTreePostOrder(BSTNode node) {
        if (node == null) {
            return;
        } 
        //recur on both subtrees
        printTreePostOrder(node.left);
        printTreePostOrder(node.right);

        //next deal with the node
        System.out.print(node.data + " ");
        BSTList.add(node);
    }
    
    //Method prints the node values in the 'preorder' order
    public void printTreePreOrder() {
        printTreePreOrder(root);
    }

    private void printTreePreOrder(BSTNode node) {

        if (node != null) {
            System.out.print(node.getData() + " ");
            BSTList.add(node);
            printTreePreOrder(node.getLeft());
            printTreePreOrder(node.getRight());
        }
    }    
    
    //Method prints out all of pahts in bst: root to leaf, one per line
    public void printAllRootToLeafPaths() {
        int[] path = new int[1000];
        printAllRootToLeafPaths(root, path, 0);
    }
   
    private void printAllRootToLeafPaths(BSTNode node, int[] path, int pathLen) {
        if (node == null) {
            return;
        } 
        // add node to the path
        path[pathLen] = node.data;
        pathLen++;
        // if leaf, then print the path that led to here
        if (node.left == null && node.right == null) {
            printArray(path, pathLen);
            BSTList.add(node);
        } else {
            // if not leaf - try left and right subtree
            printAllRootToLeafPaths(node.left, path, pathLen);
            printAllRootToLeafPaths(node.right, path, pathLen);
        }        
    }

    //Mehod helps print out bst path
    private void printArray(int[] ints, int len) {
        int i;
        for (i = 0; i < len; i++) {
            System.out.print(ints[i] + " ");
        }
        System.out.println();
    }
    
    //Method tests if  tree meets the conditions to be a binary search tree
    public boolean isBST() {
        return (isBST(root));
    }
    
    private boolean isBST(BSTNode node) {
        if (node == null) {
            return true;
        }
        //check if subtrees contain values that do not:
        if (node.left != null && maxValue(node.left) > node.data) {
            return false;
        }
        if (node.right != null && minValue(node.right) <= node.data) {
            return false;
        }
        //validate subtrees
        return (isBST(node.left) && isBST(node.right));
    }
}
