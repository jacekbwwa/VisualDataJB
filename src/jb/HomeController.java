/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
package jb;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
public class HomeController implements Initializable {

    @FXML
    SplitPane drawPane;
    @FXML
    BarChart barChart;
    @FXML
    TextArea textArea;
    @FXML
    Button bubbleSortBtn;
    @FXML
    Button generateBtn;
    @FXML
    Button insertionSortBtn;
    @FXML
    Button selectionSortBtn;
    @FXML
    Button quickSortBtn;
    @FXML
    Button mergeSortBtn;
    @FXML
    Button notVisibleBtn;
    @FXML
    Separator separator1;
    @FXML
    Separator separator2;
    @FXML
    Separator separator3;
    @FXML
    Separator separator4;
    @FXML
    Separator separator5;
    @FXML
    Separator separator6;
    @FXML
    Separator separator7;
    @FXML
    Separator separator8;
    @FXML
    Button goToHome;
    @FXML
    private MenuItem userMenu;
    @FXML
    private TextField textFieldSortInt;
    @FXML
    private Label labelTimer;

    private int userNum;
    private int[] dataToSort;
    DoubleProperty timeSeconds = new SimpleDoubleProperty();
    DoubleProperty milisecLabel = new SimpleDoubleProperty();
    public long startT = 0;
    public long currentT = 0;
    public long timeDifference = 0;
    Timeline timeline = new Timeline();
    BooleanProperty timerFlag;
    BooleanProperty timerFlagStart;
    BooleanProperty timerFlagStop;
    StringBinding labelText;
    Boolean labelBindFlag = false;
    Boolean timeLineCheck = false;
    boolean startBinding = false;

    @FXML
    public void goToHome(ActionEvent event) {
        if (event.getSource() == goToHome) {
            WelcomeApp.getInstance().gotoMenu();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        barChart.setVisible(false);
        bubbleSortBtn.setVisible(false);
        insertionSortBtn.setVisible(false);
        selectionSortBtn.setVisible(false);
        quickSortBtn.setVisible(false);
        mergeSortBtn.setVisible(false);
        notVisibleBtn.setVisible(false);
        separator1.setVisible(false);
        separator2.setVisible(false);
        separator3.setVisible(false);
        separator4.setVisible(false);
        separator5.setVisible(false);
        separator6.setVisible(false);
        separator7.setVisible(false);
        separator8.setVisible(false);

        userMenu.setText(LoginUser.getLoggedUser().getUserName());
    }

    @FXML
    private void handleLogout() {
        LoginUser.userLogout();
    }

    @FXML
    private void handleButtonGenerate(ActionEvent event) {
        if (validateUserNumber() == false) {
            return;
        } else {
            try {
                unbindLabelTimer();
                stopTimer();
                generateData();
            } catch (Exception e) {
                WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
            }
        }
    }

    @FXML
    private void bubbleSorting(ActionEvent event) {
        try {
            unbindLabelTimer();
            stopTimer();
            startTimer();
            bubbleSort(dataToSort);
        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
        }
    }

    @FXML
    private void insertionSorting(ActionEvent event) {
        try {
            unbindLabelTimer();
            stopTimer();
            startTimer();
            insertionSort(dataToSort);
        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
        }
    }

    @FXML
    private void selectionSorting(ActionEvent event) {
        try {
            unbindLabelTimer();
            stopTimer();
            startTimer();
            selectionSort(dataToSort);
        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
        }
    }

    @FXML
    private void quickSortingData(ActionEvent event) {
        try {
            unbindLabelTimer();
            stopTimer();
            startTimer();
            quickSort(dataToSort);

        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
        }
    }

    @FXML
    private void mergeSortingData(ActionEvent event) {
        try {
            unbindLabelTimer();
            stopTimer();
            startTimer();
            mergeSort(dataToSort);
        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
        }
    }

    private int userSortNodeNumber() {
        String s = textFieldSortInt.getText();
        return Integer.parseInt(s);
    }

    private boolean validateUserNumber() {
        //this.userNum = userNum;
        if ((textFieldSortInt.getText() == null)) {
            WelcomeApp.getInstance().showMessage("VisualDataJB", "No number entered", "Please provide integer number");
            return false;
        }
        try {
            userNum = userSortNodeNumber();
            dataToSort = new int[userNum];
        } catch (Exception ex) {
            WelcomeApp.getInstance().showMessage("VisualDataJB", "Incorrect sort number", "If you want generate sort number  please enter correct value");
            return false;
        }

        if (userNum <= 0 || userNum > 1000) {
            WelcomeApp.getInstance().showMessage("VisualDataJB", "Incorrect sort number", "If you want generate sort number  please enter correct value");
            return false;
        }
        return true;
    }

    private void generateData() throws Exception {
        //this.userNum = userNum;
        int i = 0;
        int r;
        while (i < userNum) {
            r = (int) (1 + Math.random() * 10 * userNum); // random number             
            boolean b = true;
            for (int j = 0; j < userNum; j++) { // check if rarndomed number is unique
                if (dataToSort[j] == r) {
                    b = false;
                    break;
                }
            }
            if (b) {
                dataToSort[i] = r;
                i++;
            }
        }

        barChart.setAnimated(true);
        drawDataChart(dataToSort);
        barChart.setVisible(true);
        bubbleSortBtn.setVisible(true);
        insertionSortBtn.setVisible(true);
        selectionSortBtn.setVisible(true);
        quickSortBtn.setVisible(true);
        mergeSortBtn.setVisible(true);

    }

    AnimationTimer anTimer = new AnimationTimer() {
        public void start() {
            timerFlag = new SimpleBooleanProperty();
            timerFlag.set(true);
            timerFlagStart = new SimpleBooleanProperty();
            timerFlagStart = timerFlag;
            startT = System.nanoTime();
            timeSeconds = new SimpleDoubleProperty();
            timeSeconds.set((System.nanoTime() / 1000000.0));
            startBinding = true;

        }

        public void stop() {
            timerFlag.set(false);
            timerFlagStop = new SimpleBooleanProperty();
            timerFlagStop = timerFlag;
            currentT = System.nanoTime();

        }

        @Override
        public void handle(long l) {
            timeSeconds = new SimpleDoubleProperty();
            timeSeconds.set((System.nanoTime() - startT) / 1000000.0);

        }
    };

    public void startTimer() {
        if (timeLineCheck == false) {

            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.totalDurationProperty().getValue().add(Duration.millis(10000));
            anTimer.start();
            timeline.play();
            timeLineCheck = true;

        }

    }

    public void stopTimer() {
        if (timeLineCheck == true) {
            timeline.stop();
            timeLineCheck = false;

        }
    }

    public void stopTimerAndDisplay() {
        if (timeLineCheck == true) {
            timeline.stop();
            timeLineCheck = false;
            anTimer.stop();
            timeDifference = currentT - startT;
            anTimer.handle(timeDifference);
            labelText = timeSeconds.asString("%f miliseconds");
            labelTimer.textProperty().bind(labelText);
            labelTimer.textProperty().unbind();
        }
    }

    public void unbindLabelTimer() {
        if (labelBindFlag == true) {
            labelTimer.textProperty().unbind();
            labelBindFlag = false;
        }
        labelTimer.setText("generated");
    }

    public void unbindLabelTimerEnd() {
        if (labelBindFlag == true) {
            labelTimer.textProperty().unbind();
            labelBindFlag = false;
        }
    }

    private void drawDataChart(int[] tab) throws Exception {
        int c = tab.length;

        barChart.getData().clear();
        barChart.layout();
        final XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        for (int i = 0; i < c; i++) {
            series1.getData().add(new XYChart.Data<String, Number>(String.valueOf(tab[i]), tab[i]));
        }
        barChart.getData().add(series1);

    }

    private int[] bubbleSort(int[] a) throws Exception {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp;
                    temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }

        stopTimerAndDisplay();
        drawDataChart(a);
        unbindLabelTimerEnd();
        return a;
    }
/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
    private int[] insertionSort(int[] a) throws Exception {
        int k = 0, j = 0, temp;
        for (k = 1; k < a.length; k++) {
            temp = a[k];
            j = k;
            while (j > 0 && a[j - 1] > temp) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = temp;
        }

        stopTimerAndDisplay();
        drawDataChart(a);
        unbindLabelTimerEnd();
        return a;
    }

    private int[] selectionSort(int[] a) throws Exception {
        int m = 0, n = 0, tempS, indexMin = 0;
        for (m = 0; m < a.length - 1; m++) {
            indexMin = m;
            for (n = m + 1; n < a.length; n++) {
                if (a[n] < a[indexMin]) {
                    indexMin = n;
                }
            }
            if (indexMin != m) {
                tempS = a[m];
                a[m] = a[indexMin];
                a[indexMin] = tempS;
            }
        }

        stopTimerAndDisplay();
        drawDataChart(a);
        unbindLabelTimerEnd();
        return a;
    }

    private int[] quickSort(int tab[]) throws Exception {
        if (tab.length > 0) {
            quickSort(tab, 0, tab.length - 1);
        }

        stopTimerAndDisplay();
        drawDataChart(tab);
        unbindLabelTimerEnd();
        return tab;
    }

    private void quickSort(int tab[], int beginPoint, int endPoint) throws Exception {

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
                }
            }
            replace(tab, beginPoint, pointRightToLeft);            
            quickSort(tab, beginPoint, pointRightToLeft - 1); // sorts left part
            quickSort(tab, pointRightToLeft + 1, endPoint);   // sorts right part
        } 
    }

    private void replace(int tab[], int start, int end) {
        if (tab.length > 0 && start < tab.length && end < tab.length) {
            int tmp = tab[start];   // replace
            tab[start] = tab[end];  
            tab[end] = tmp;       
        }
    }

    private int[] mergeSort(int[] tab) throws Exception {
        if (tab.length > 1) {
            // split
            int[] left = leftPart(tab);  //left half
            int[] right = rightPart(tab); //right half

            mergeSort(left); //left part array is sorted recursively 
            mergeSort(right); //right part is sorted recursively 

            merge(tab, left, right); // left and right parts are merged
        }
        drawDataChart(tab);
        return tab;
    }

    private int[] leftPart(int[] tab) throws Exception {
        int sizeLeft = tab.length / 2;
        int[] left = new int[sizeLeft];
        for (int t = 0; t < sizeLeft; t++) {
            left[t] = tab[t];
        }
        return left; // return left part from array

    }

    private int[] rightPart(int[] tab) throws Exception {
        int sizeLeft = tab.length / 2;
        int sizeRight = tab.length - sizeLeft;
        int[] right = new int[sizeRight];
        for (int u = 0; u < sizeRight; u++) {
            right[u] = tab[u + sizeLeft];
        }
        return right;//return right part from array 

    }

    private int[] merge(int[] result, int[] left, int[] right) throws Exception {  
        // merge parts: left and right into one part
        int indexL = 0;   // index to start to left part array
        int indexR = 0;   // index to start to right part array

        for (int v = 0; v < result.length; v++) {
            if (indexR >= right.length || (indexL < left.length
                    && left[indexL] <= right[indexR])) {
                result[v] = left[indexL];    // merge values from left
                indexL++;
            } else {
                result[v] = right[indexR];   // merge values from right
                indexR++;
            }
        }
        stopTimerAndDisplay();
        unbindLabelTimerEnd();
        return result;
    }
}
