/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
package jb;

import javafx.scene.control.TextArea;


public class AlgorithmsDescription {

    public AlgorithmsDescription() {

    }

    final String BUBBLE_SORT_CODE = "procedure bubbleSort( A : the number of elements to sort )\n"
            + "  n = the number of elements(A)\n"
            + "   do\n"
            + "    for (i = 0; i < n-1; i++) do:\n"
            + "      if A[i] > A[i+1] then\n"
            + "        swap(A[i], A[i+1])\n"
            + "      end if\n"
            + "    end for\n"
            + "    n = n-1\n"
            + "  while n > 1\n"
            + "end procedure";

    final String BUBBLE_SORT_DESCRIPTION = "Bubble Sort Algorithm (BSA) is based on comparison \n"
            + "of two subsequent elements and swap of them \n"
            + "each other if they are not in queue order.\n"
            + "The time complexity of the algorithm is average O(n2),\n"
            + "The memory complexity of BSA is O(1).\n"
            + "The algorithm performs n-1 steps, \n"
            + "and in each step it does n-k comparison,\n"
            + "where k is equal number of the step.";

    

    final String INSERTION_SORT_CODE = "Insert_sort(A, n)  \n"
            + "where n is number of elements in array A  \n"
            + "    for i=2 to n :                                     \n"
            + "      key = A[i]\n"
            + "       # Insert A[i] to sorted row A[1 ... i-1]  \n"
            + "      j = i - 1                                       \n"
            + "      while j>0 and A[j]>key:  \n"
            + "        A[j + 1] = A[j]              \n"
            + "         j = j - 1                  \n"
            + "      A[j + 1] = key";
    
    final String INSERTION_SORT_DESCRIPTION = "Insertion sort is one of the simplest sorting algorithms,\n"
            +"the principle of which reflects the way in which people set the card - \n"
            +"another input elements are set to the appropriate destinations.\n"
            +"It is effective for a small number of items, complexity is O (n2). \n"
            +"Although it is much less efficient than algorithms such as quicksort or heapsort,\n"
            +"has certain advantages:\n" 
            +"- the number of comparisons is performed depending on \n"
            +"the number of inversions in the permutation,\n"
            +"so the algorithm is effective for pre-sorted data\n" 
            +"- it is efficient for the collection of a small number of;\n"
            +"- stable.";
    
    final String INSERTION_SORT_STEPS= "Steps for sorting an array: 3, 7, 4, 9, 5, 2, 6, 1\n\n"
            + "[3]   7   4   9   5   2   6   1\n"
            + "3   [7]   4   9   5   2   6   1\n"
            + "3   7   [4]   9   5   2   6   1\n"
            + "3   4   7   [9]   5   2   6   1\n"
            + "3   4   7   9   [5]   2   6   1\n"
            + "3   4   5   7   9   [2]   6   1\n"
            + "2   3   4   5   7   9   [6]   1\n"
            + "2   3   4   5   6   7   9   [1]\n"
            + "1   2   3   4   5   6   7   9\n";
    
    final String SELECTION_SORT_CODE = "selection Sort array a : "
            + "int m = 0, n = 0, tempS, indexMin = 0;\n" +
"        for (m = 0; m < a.length - 1; m++) {\n" +
"            indexMin = m;\n" +
"            for (n = m + 1; n < a.length; n++) {\n" +
"                if (a[n] < a[indexMin]) {\n" +
"                    indexMin = n;\n" +
"                }\n" +
"            }\n" +
"            if (indexMin != m) {\n" +
"                tempS = a[m];\n" +
"                a[m] = a[indexMin];\n" +
"                a[indexMin] = tempS;\n" +
"            }\n" +
"        }\n" +
"";
    
    final String SELECTION_SORT_DESCRIPTION = "Selection sort - one of the easiest methods of sorting,"
            + " the complexity O (n2). It consists in searching an element having to refer "
            +"to the set position and exchange places with the one that is there now."
            +" The operation is performed for all indexes sorted array.\n" 
            +"The algorithm is as follows:\n" +
            "- search for the minimum value from an array of the elements from 'i' to the end of the array\n" +
            "- replace the minimum value and the element in position 'i'\n"
            +"The algorithm is unstable.";
    
    final String SELECTION_SORT_STEPS= "Steps for sorting an array: 9, 1, 6, 8, 4, 3, 2, 0\n\n"
            + "9   1   6   8   4   3   2   [0]\n"
            + "0  [1]  6   8   4   3   2   9\n"
            + "0   1   6   8   4   3  [2]  9\n"
            + "0   1   2   8   4  [3]  6   9\n"
            + "0   1   2   3  [4]  8   6   9\n"
            + "0   1   2   3   4   8  [6]  9\n"
            + "0   1   2   3   4   6   8   9\n";
            
    
    final String MERGE_SORT_CODE = "SORT-MERGE(T, p, r):\n" +
"    IF p < r:\n" +
"        q â†’ (p+r)/2\n" +
"        SORT-MERGE(T, p, q)\n" +
"        SORT-MERGE(T, q+1, r)\n" +
"        MERGE(T, p, q, r)";
    
    final String MERGE_SORT_DESCRIPTION = "Mergesort is a divide and conquer algorithm that was invented by John von Neumann in 1945."
            +"There are three basic steps:\n" +
"     Share data set into two equal parts\n" +
"     Apply merge sort for each of them separately unless they remained only one element;\n" +
"     Connect sorted substrings in a string of sorted.";
    
    final String MERGE_SORT_STEPS= "Steps for sorting an array: 6, 5, 3, 1, 8, 7, 2, 4\n\n"
            + "      6   5   3   1   8   7   2   4\n"
            + "   [ 6   5   3   1 ]     [ 8   7   2   4 ]\n"
            + "[6   5]    [3   1]         [8   7]    [2   4]\n"
            + "[5   6]    [1   3]         [7   8]    [2   4]\n"
            + "   [ 1   3   5   6 ]     [ 2   4   7   8 ]\n"
            + "      1   2   3   4   5   6   7   8\n";
            
    
    final String QUICK_SORT_DESCRIPTION = "Quick sort - one of the popular sorting algorithms operating on the principle of \"divide and conquer\".\n" +
"Sorting QuickSort was invented in 1962, by C.A.R. Hoare [2].\n" +
"Fast sorting algorithm is efficient: its average computational complexity is of the order O(n log n). Because of the speed and simplicity of implementation is commonly used.";
    
    final String QUICK_SORT_CODE = "Algorithm quicksort(A, lo, hi) is\n" +
"    if lo < hi then\n" +
"        p := partition(A, lo, hi)\n" +
"        quicksort(A, lo, p - 1)\n" +
"        quicksort(A, p + 1, hi)" +

"  algorithm partition(A, lo, hi) is\n" +
"    pivot := A[hi]\n" +
"    i := lo     // place for swapping\n" +
"    for j := lo to hi -1 do\n" +
"        if A[j] <= pivot then\n" +
"            swap A[i] with A[j]           \n" +
"            i := i + 1\n" +
"    swap A[i] with A[hi]\n" +
"    return i";
    
    final String QUICK_SORT_STEPS= "Steps for sorting an array: 6, 5, 3, 1, 8, 7, 2, 4\n\n"
            + "      6   5   3   1   8   7   2   4\n"
            + "   [ 6   5   3   1 ]     [ 8   7   2   4 ]\n"
            + "[6   5]    [3   1]         [8   7]    [2   4]\n"
            + "[5   6]    [1   3]         [7   8]    [2   4]\n"
            + "   [ 1   3   5   6 ]     [ 2   4   7   8 ]\n"
            + "      1   2   3   4   5   6   7   8\n";
    
    final String BST_CODE = "define BST_TREE_SEARCH (Node, Key):\n" +
"    if (Node == NULL) or (Node->Key == Key)\n" +
"        return Node\n" +
"    if Key < Node->Key\n" +
"        return BST_TREE_SEARCH (Node->Left, Key)\n" +
"    return BST_TREE_SEARCH (Node->Right, Key)\n\n"+
            "define ITERATIVE_BST_TREE_SEARCH (Node, Key):\n" +
"    while ((Node != NULL) and (Node->Key != Key))\n" +
"        if (Key < Node->Key)\n" +
"            Node = Node->left\n" +
"        else\n" +
"            Node = Node->right\n" +
"    return Node";
    
    final String BST_DESCRIPTION = "A binary search tree is a rooted binary tree, "+
            "whose internal nodes each store a key (and optionally, an associated value) "+
            "and each have two distinguished sub-trees, commonly denoted left and right. "+
            "The tree additionally satisfies the binary search tree property, "+
            "which states that the key in each node must be greater than all "+
            "keys stored in the left sub-tree, and not greater than all keys in the right sub-tree."+
            "(The leaves (final nodes) of the tree contain no key and have no structure to "+
            "distinguish them from one another. Leaves are commonly represented by a special leaf or nil symbol, "+
            "a NULL pointer, etc.)\n" +
            "Generally, the information represented by each node is a record "+
            "rather than a single data element. However, for sequencing purposes, "+
            "nodes are compared according to their keys rather than any part of their associated records.\n" +
            "The major advantage of binary search trees over other data structures "+
            "is that the related sorting algorithms and search algorithms such "+
            "as in-order traversal can be very efficient; they are also easy to code.";
    
    final String BST_STEPS = "There are three ways which we use to traverse a tree \n" +
"\n" +
"    In-order Traversal\n" +
"    Pre-order Traversal\n" +
"    Post-order Traversal\n\n"+
            "In-order algorithm:\nUntil all nodes are traversed \n" +
"Step 1 Recursively traverse left subtree.\n" +
"Step 2 Visit root node.\n" +
"Step 3 Recursively traverse right subtree.\n\n"+
            "Pre-order algorithm:\nUntil all nodes are traversed \n" +
"Step 1 Visit root node.\n" +
"Step 2 Recursively traverse left subtree.\n" +
"Step 3 Recursively traverse right subtree.\n\n"+
            "Post-order algorithm:\nUntil all nodes are traversed \n" +
"Step 1 Recursively traverse left subtree.\n" +
"Step 2 Recursively traverse right subtree.\n" +
"Step 3 Visit root node.";
    
    final String GRAPH_CODE = "Dijkstra(G,w,s):\n" +
"   for each vertex v in Graph V[G] do:\n" +
"      d[v] := infinity\n" +
"      previous[v] := undefined\n" +
"   d[s] := 0\n" +
"   Q := V\n" +
"   while  Q is not empty:\n" +
"      u := node in Q with smallest d[]\n" +
            " remove u from Q \n"+
"      for each neighbour v of u\n" +
"         if d[v] > d[u] + w(u, v) then\n" +
"            d[v] := d[u] + w(u, v)\n" +
"            previous[v] := u\n" +
"            Add(Q, v)\n" +
"   return  d[v]";
    
    final String GRAPH_DESCRIPTION = "Dijkstra's algorithm is an algorithm for "+
            "finding the shortest paths between nodes in a graph, which may represent, "+
            "for example, road networks. It was conceived by computer scientist Edsger W. Dijkstra in 1956 and published three years later.\n" +
            "The algorithm exists in many variants; Dijkstra's original variant found the shortest path between two nodes,"+
            "but a more common variant fixes a single node as the \"source\" node and finds shortest paths from the source to "+
            "all other nodes in the graph, producing a shortest-path tree.";
    
    final String GRAPH_STEPS ="\nDijkstra's original algorithm does not use a min-priority "+
            "queue and runs in time O(|V|^2) "+
            "(where | V | is the number of nodes). "+
            "The idea of this algorithm is also given in Leyzorek et al. 1957. "+
            "The implementation based on a min-priority queue implemented by a Fibonacci "+
            "heap and running in O ( | E | + | V | log | V | )  (where |E| is the number of edges) "+
            "is due to Fredman & Tarjan 1984. This is asymptotically the fastest known "+
            "single-source shortest-path algorithm for arbitrary directed graphs with "+
            "unbounded non-negative weights. However, specialized cases "+
            "(such as bounded/integer weights, directed acyclic graphs etc.) "+
            "can indeed be improved further as detailed in Specialized variants.";
    
    public String getBubbleSortDescription() {
        return BUBBLE_SORT_DESCRIPTION;
    }

    public String getBubbleSortCode() {
        return BUBBLE_SORT_CODE;
    }
    
    public String getInsertionSortCode() {
        return INSERTION_SORT_CODE;
    }
    public String getInsertionSortDescription() {
        return INSERTION_SORT_DESCRIPTION;
    }
    public String getInsertionSortSteps() {
        return INSERTION_SORT_STEPS;
    }
    
    public String getSelectionSortCode() {
        return SELECTION_SORT_CODE;
    }
    public String getSelectionSortDescription() {
        return SELECTION_SORT_DESCRIPTION;
    }
    public String getSelectionSortSteps() {
        return SELECTION_SORT_STEPS;
    }

    public String getMergeSortCode() {
        return MERGE_SORT_CODE;
    }
    public String getMergeSortDescription() {
        return MERGE_SORT_DESCRIPTION;
    }
    public String getMergeSortSteps() {
        return MERGE_SORT_STEPS;
    }
    
    public String getQuickSortCode() {
        return QUICK_SORT_CODE;
    }
    public String getQuickSortDescription() {
        return QUICK_SORT_DESCRIPTION;
    }
    
     public void showQuickSortSteps(TextArea ta) {
        int[] we = {8, 9, 3, 5, 2, 7, 1, 4};        
        try {
            quickSort(we, ta);
        } catch (Exception e) {;
        }
    }
    
    public String getBSTCode() {
        return BST_CODE;
    }
    public String getBSTDescription() {
        return BST_DESCRIPTION;
    }
    public String getBSTAddDescription() {
        return BST_STEPS;
    }
    
    public String getGraphCode() {
        return GRAPH_CODE;
    }
    public String getGraphDescription() {
        return GRAPH_DESCRIPTION;
    }
    public String getGraphAddDescription() {
        return GRAPH_STEPS;
    }
    
    public void BubbleSortArea(TextArea ta) {

        int[] we = new int[5];
        we[0] = 4;
        we[1] = 2;
        we[2] = 3;
        we[3] = 1;
        we[4] = 0;
        int[] wy = new int[5];
        try {
            wy = bubbleSort(we, ta);
        } catch (Exception e) {;
        }
    }

    private int[] bubbleSort(int[] a, TextArea ta) throws Exception {
        ta.appendText("\n");
        ta.appendText(printTab(a));
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp;
                    temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                    ta.appendText(printTab(a));
                }
            }
        }
        return a;
    }

    private String printTab(int[] a) {
        StringBuffer buf = new StringBuffer();
        buf.append("          ");
        for (int i = 0; i < a.length; i++) {
            buf.append(a[i]);
            buf.append("          ");
        }
        buf.append("\n");
        return buf.toString();
    }

    private int[] quickSort(int tab[], TextArea ta) throws Exception {
        ta.appendText("\n");
        ta.appendText(printTab(tab));
        if (tab.length > 0) {
            quickSort(tab, 0, tab.length - 1, ta);
        }
        return tab;
    }

    private void quickSort(int tab[], int beginPoint, int endPoint, TextArea ta) throws Exception {

        int pointLeftToRight = beginPoint;
        int pointRightToLeft = endPoint;

        if (endPoint - beginPoint >= 1) { 
            //one element is not sorted 
            int start = tab[beginPoint];     

            while (pointRightToLeft > pointLeftToRight) {
                while (tab[pointLeftToRight] <= start && 
                       pointLeftToRight <= endPoint && 
                       pointRightToLeft > pointLeftToRight) { 
                    pointLeftToRight++;  // iterate from left to right
                }
                while (tab[pointRightToLeft] > start && 
                       pointRightToLeft >= beginPoint && 
                       pointRightToLeft >= pointLeftToRight) { // first element from right
                    pointRightToLeft--; // iterate from right to left
                }
                if (pointRightToLeft > pointLeftToRight) { 
                    replace(tab, pointLeftToRight, pointRightToLeft); 
                    ta.appendText(printTab(tab));
                }
            }
            replace(tab, beginPoint, pointRightToLeft);   
            ta.appendText(printTab(tab));
            quickSort(tab, beginPoint, pointRightToLeft - 1, ta); // sorts left part
            quickSort(tab, pointRightToLeft + 1, endPoint, ta);   // sorts right part
        } 
    }
/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
    private void replace(int tab[], int start, int end) {
        if (tab.length > 0 && start < tab.length && end < tab.length) {
            int tmp = tab[start];   // replace
            tab[start] = tab[end];  
            tab[end] = tmp;       
        }
    }
}
