/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jb;

import java.util.ArrayList;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.StackPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class SortTest {
    
    Sort instance = new Sort();
    ArrayList<StackPane> dataList;
    double speed;
    int[] initArray; // initial array of numbers to sort
    int[] sortedArray; // array after sort 
    
    public SortTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println("* UtilsJUnit4_Sort_Test: setUp() method");
        
        // create array of 12 numbers to validate sort methods:       
        int[] tab = {8, 3, 10, 1, 6, 14, 4, 7, 13, 2, 19, 5};
        initArray = tab;
        int[] tabSorted = {1, 2, 3, 4, 5, 6, 7, 8, 10, 13, 14, 19};
        sortedArray = tabSorted;
        
        // create stck pane list, size 12
        dataList = new ArrayList<>();
        for(int i = 0; i< tab.length; i++){
            StackPane stackPane = new StackPane();
            stackPane.setId(String.valueOf(tab[i]));
            dataList.add(stackPane);
        }

        // initialize speed value:
        speed = 400;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of bubbleSort method, of class Sort.
     */
    @Test
    public void testBubbleSort() {
        System.out.println("bubbleSort");
        
        SequentialTransition result = instance.bubbleSort(dataList, speed);
        
        for(int i = 0; i < dataList.size(); i++){
            assertEquals(sortedArray[i], Integer.parseInt(dataList.get(i).getId())); 
        }        
    }

    /**
     * Test of insertionSort method, of class Sort.
     */
    @Test
    public void testInsertionSort() {
        System.out.println("insertionSort");
        
        SequentialTransition result = instance.insertionSort(dataList, speed);
        
        for(int i = 0; i < dataList.size(); i++){
            assertEquals(sortedArray[i], Integer.parseInt(dataList.get(i).getId())); 
        } 
    }

    /**
     * Test of selectionSort method, of class Sort.
     */
    @Test
    public void testSelectionSort() {
        System.out.println("selectionSort");
        
        SequentialTransition result = instance.selectionSort(dataList, speed);
        
        for(int i = 0; i < dataList.size(); i++){
            assertEquals(sortedArray[i], Integer.parseInt(dataList.get(i).getId())); 
        } 
    }

    /**
     * Test of quickSort method, of class Sort.
     */
    @Test
    public void testQuickSort() {
        System.out.println("quickSort");
        
        SequentialTransition result = instance.quickSort(dataList, speed);
        
        for(int i = 0; i < dataList.size(); i++){
            assertEquals(sortedArray[i], Integer.parseInt(dataList.get(i).getId())); 
        } 
    }

    /**
     * Test of heapSort method, of class Sort.
     */
    @Test
    public void testHeapSort() {
        System.out.println("heapSort");
        
        SequentialTransition result = instance.heapSort(dataList, speed);
        
        for(int i = 0; i < dataList.size(); i++){
            assertEquals(sortedArray[i], Integer.parseInt(dataList.get(i).getId())); 
        } 
    }    
}
