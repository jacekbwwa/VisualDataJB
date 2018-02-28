/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jb;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author XNOTE
 */
public class BSTTest {
    
    public BSTTest() {
    }
    
    BST instance = new BST();
    
    @BeforeClass
    public static void setUpClass() {   
        System.out.println("* UtilsJUnit4_BST_Test: setUpClass() method");        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println("* UtilsJUnit4_BST_Test: setUp() method");
        // create tree with below numbers to validate BST methods:       
        int[] arrayTree = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        for(int i = 0; i< arrayTree.length; i++){
            instance.insert(arrayTree[i]);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRoot method, of class BST.
     */
    @Test
    public void testGetRoot() {
        System.out.println("getRoot");             
        int data = 8;
        BSTNode result = instance.getRoot();
        assertEquals(data, result.getData());        
    }

    /**
     * Test of isEmpty method, of class BST.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");       
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);        
    }

    /**
     * Test of search method, of class BST.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        int data = 6; // exist in tree
        boolean expResult = true;
        boolean result = instance.search(data);
        assertEquals(expResult, result);
        data = 5; // not exist in tree
        expResult = false;
        result = instance.search(data);
        assertEquals(expResult, result);        
    }   
    
    /**
     * Test of TreeHeight method, of class BST.
     */
    @Test
    public void testTreeHeight_0args() {
        System.out.println("TreeHeight_0args");        
        int expResult = 3;
        int result = instance.TreeHeight();
        assertEquals(expResult, result);        
    }

    /**
     * Test of TreeHeight method, of class BST.
     */
    @Test
    public void testTreeHeight_BSTNode() {
        System.out.println("TreeHeight_BSTNode");
        BSTNode node = instance.getRoot();        
        int expResult = 3;
        int result = instance.TreeHeight(node);
        assertEquals(expResult, result);        
    }        
    
    /**
     * Test of size method, of class BST.
     */
    @Test
    public void testSize() {
        System.out.println("size");        
        int expResult = 9;
        int result = instance.size();
        assertEquals(expResult, result);        
    }

    /**
     * Test of maxDepth method, of class BST.
     */
    @Test
    public void testMaxDepth() {
        System.out.println("maxDepth");        
        int expResult = 4;
        int result = instance.maxDepth();
        assertEquals(expResult, result);        
    }

    /**
     * Test of minValue method, of class BST.
     */
    @Test
    public void testMinValue() {
        System.out.println("minValue");        
        int expResult = 1;
        int result = instance.minValue();
        assertEquals(expResult, result);        
    }
       
    /**
     * Test of maxValue method, of class BST.
     */
    @Test
    public void testMaxValue() {
        System.out.println("maxValue");              
        int expResult = 14;
        int result = instance.maxValue();
        assertEquals(expResult, result);        
    }

    /**
     * Test of printTreeInOrder method, of class BST.
     */
    @Test
    public void testPrintTreeInOrder() {
        System.out.println("printTreeInOrder");
        int[] tab = {1,3,4,6,7,8,10,13,14};
        instance.printTreeInOrder();
        for(int i = 0; i < tab.length; i++){
            assertEquals(tab[i], instance.BSTList.get(i).getData()); 
        } 
    }

    /**
     * Test of printTreePostOrder method, of class BST.
     */
    @Test
    public void testPrintTreePostOrder() {
        System.out.println("printTreePostOrder");
        int[] tab = {1,4,7,6,3,13,14,10,8};
        instance.printTreePostOrder();
        for(int i = 0; i < tab.length; i++){
            assertEquals(tab[i], instance.BSTList.get(i).getData()); 
        } 
    }

    /**
     * Test of printTreePreOrder method, of class BST.
     */
    @Test
    public void testPrintTreePreOrder() {
        System.out.println("printTreePreOrder");
        int[] tab = {8, 3, 1, 6, 4, 7, 10, 14, 13};
        instance.printTreePreOrder();
        for(int i = 0; i < tab.length; i++){
            assertEquals(tab[i], instance.BSTList.get(i).getData()); 
        }        
    }
            
    /**
     * Test of isBST method, of class BST.
     * Method tests if created tree is the Binary Search Tree
     */
    @Test
    public void testIsBST() {
        System.out.println("isBST");        
        boolean expResult = true;
        boolean result = instance.isBST();
        assertEquals(expResult, result);       
    }    
}
