/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
package jb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
public class Sort {

    private int szer = 0;
    private int space = 0;
    public int qtElements;

    public Sort() {
    }

    public int getSpaceEl() {
        return space;
    }

    // Generate elements to sort; hight=hight of panel
    public ArrayList<StackPane> generateDataList(int qtElements, double hight) {
        this.qtElements = qtElements;
        ArrayList<StackPane> dataList = new ArrayList<>();

        Random random = new Random(5);
        int width = 600;// dataPane.widthProperty().intValue();
        szer = width / qtElements;

        if (szer > 1) {
            szer = (int) width / qtElements;
            space = (int) (1 + (width / (10 * qtElements)));
        }
        if (szer < 1) {
            szer = 1;
            space = 0;
        }
        int rectHeigth;
        arl.clear();
        generateArl();

        for (int i = 0; i < arl.size(); i++) {
            rectHeigth = (int) arl.get(i);
            int d = (int) hight - 120 + (rectHeigth * 4);
            Rectangle rectangle = new Rectangle(szer, d/*(rectHeigth * 10) + 50*/);
            rectangle.setFill(Color.valueOf("#ADD8E6"));

            Text text = new Text(String.valueOf(rectHeigth));

            StackPane stackPane = new StackPane();
            stackPane.setPrefSize(rectangle.getWidth(), rectangle.getHeight());
            stackPane.setId(String.valueOf(rectHeigth));
            stackPane.getChildren().addAll(rectangle, text);

            dataList.add(stackPane);
        }
        return dataList;
    }

    public ArrayList arl = new ArrayList();
    int chk;

    public boolean checkArl(int chk) {
        for (int z = 0; z < arl.size(); z++) {
            if (arl.get(z).equals(chk)) {
                return true;
            }
        }
        return false;
    }

    public void generateArl() {
        Random random = new Random();
        int check1;
        int s = qtElements;
        while (s > 0) {
            check1 = random.nextInt(qtElements);
            if (checkArl(check1) == true) {
                check1 = random.nextInt(qtElements);
            } else {
                arl.add(check1);
                s--;
            }
        }
    }
/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
    public SequentialTransition bubbleSort(ArrayList<StackPane> dataList, double speed) {
        int tab[] = dataListToArray(dataList);
        SequentialTransition sqt = new SequentialTransition();
        int tmp;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length - 1; j++) {
                if (tab[j] > tab[j + 1]) {
                    tmp = tab[j + 1];
                    tab[j + 1] = tab[j];
                    tab[j] = tmp;
                    sqt.getChildren().add(swapElements(dataList.get(j), dataList.get(j + 1), dataList, speed));
                }
            }
        }
        return sqt;
    }

    // create int array from list of StackPane
    public int[] dataListToArray(List<StackPane> dataList) {
        int tab[] = new int[dataList.size()];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = Integer.parseInt(dataList.get(i).getId());
        }
        return tab;
    }

    // swap elements of visualisation
    private ParallelTransition swapElements(StackPane sp1, StackPane sp2, ArrayList<StackPane> list, double speed) {
        TranslateTransition t1 = new TranslateTransition();
        TranslateTransition t2 = new TranslateTransition();
        t1.setDuration(Duration.millis(speed));
        t2.setDuration(Duration.millis(speed));
        ParallelTransition pt = new ParallelTransition();
        t1.setNode(sp1);
        t2.setNode(sp2);
        t1.setByX(szer + space);
        t2.setByX(-1 * (szer + space));
        pt.getChildren().addAll(t1, t2);
        Collections.swap(list, list.indexOf(sp1), list.indexOf(sp2));
        return pt;
    }

    public SequentialTransition insertionSort(ArrayList<StackPane> dataList, double speed) {
        int tab[] = dataListToArray(dataList);
        SequentialTransition sqt = new SequentialTransition();

        int j = 0;
        int tmp;
        for (int k = 1; k < tab.length; k++) {
            tmp = tab[k];
            j = k - 1;
            while (j >= 0 && tab[j] > tmp) {
                tab[j + 1] = tab[j];
                sqt.getChildren().add(swapElements(dataList.get(j), dataList.get(j + 1), dataList, speed));
                j--;
            }
            tab[j + 1] = tmp;
        }
        return sqt;
    }

    public SequentialTransition selectionSort(ArrayList<StackPane> dataList, double speed) {
        int tab[] = dataListToArray(dataList);
        SequentialTransition sqt = new SequentialTransition();

        int tmp, indexMin = 0;
        for (int m = 0; m < tab.length - 1; m++) {
            indexMin = m;
            for (int n = m + 1; n < tab.length; n++) {
                if (tab[n] < tab[indexMin]) {
                    indexMin = n;
                }
            }
            if (indexMin != m) {
                tmp = tab[m];
                tab[m] = tab[indexMin];
                tab[indexMin] = tmp;

                int orygSpace = space;
                int orygSzer = szer;
                space = space * (indexMin - m);
                szer = szer * (indexMin - m);
                sqt.getChildren().add(swapElements(dataList.get(m), dataList.get(indexMin), dataList, speed));
                space = orygSpace;
                szer = orygSzer;
            }
        }
        return sqt;
    }

    public SequentialTransition quickSort(ArrayList<StackPane> dataList, double speed) {
        int tab[] = dataListToArray(dataList);
        SequentialTransition sqt = new SequentialTransition();

        if (tab.length > 0) {
            quickSort(tab, 0, tab.length - 1, dataList, sqt, speed);
        }
        return sqt;
    }

    private void quickSort(int tab[], int beginPoint, int endPoint,
            ArrayList<StackPane> dataList, SequentialTransition sqt, double speed) {
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
                        pointRightToLeft >= pointLeftToRight) { 
                    pointRightToLeft--;  // iterate from right to left
                }
                if (pointRightToLeft > pointLeftToRight) {  
                    replace(tab, pointLeftToRight, pointRightToLeft, dataList, sqt, speed); 
                }
            }
            replace(tab, beginPoint, pointRightToLeft, dataList, sqt, speed); 
            quickSort(tab, beginPoint, pointRightToLeft - 1, dataList, sqt, speed); // sorts left part
            quickSort(tab, pointRightToLeft + 1, endPoint, dataList, sqt, speed);   // orts right part
        }
    }
/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
    private void replace(int tab[], int start, int end,
            ArrayList<StackPane> dataList, SequentialTransition sqt, double speed) {
        if (tab.length > 0 && start < tab.length && end < tab.length) {
            int tmp = tab[start];  //  replace
            tab[start] = tab[end]; 
            tab[end] = tmp;  

            int orygSpace = space;
            int orygSzer = szer;
            space = space * (end - start);
            szer = szer * (end - start);
            sqt.getChildren().add(swapElements(dataList.get(start), dataList.get(end), dataList, speed));
            space = orygSpace;
            szer = orygSzer;
        }
    }

    public SequentialTransition heapSort(ArrayList<StackPane> dataList, double speed) {
        int tab[] = dataListToArray(dataList);
        SequentialTransition sqt = new SequentialTransition();

        createHeap(tab, sqt, dataList, speed);

        for (int i = idx; i > 0; i--) {

            //swap tab[0], tab[i]:
            replace(tab, 0, i, dataList, sqt, speed);

            idx = idx - 1;
            maxHeap(tab, 0, sqt, dataList, speed);
        }
        return sqt;
    }

    private void createHeap(int[] data, SequentialTransition sqt,
            ArrayList<StackPane> dataList, double speed) {
        idx = data.length - 1;
        for (int i = idx / 2; i >= 0; i--) {
            maxHeap(data, i, sqt, dataList, speed);
        }
    }

    // class variables for heap sort:
    private int idx;
    private int left;
    private int right;
    private int largest;

    private void maxHeap(int[] data, int i, SequentialTransition sqt,
            ArrayList<StackPane> dataList, double speed) {
        left = 2 * i;
        right = 2 * i + 1;
        if (left <= idx && data[left] > data[i]) {
            largest = left;
        } else {
            largest = i;
        }

        if (right <= idx && data[right] > data[largest]) {
            largest = right;
        }
        if (largest != i) {
            //swap tab[i], tab[largest]:
            replace(data, i, largest, dataList, sqt, speed);
            maxHeap(data, largest, sqt, dataList, speed);
        }
    }
}
