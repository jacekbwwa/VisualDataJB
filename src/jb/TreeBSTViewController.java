/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
package jb;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
public class TreeBSTViewController implements Initializable {

    @FXML
    SplitPane drawPane;
    @FXML
    AnchorPane anchorP;
    @FXML
    TextArea textArea;
    ;
    @FXML
    Button generateBtn;
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
    Separator separator9;
    @FXML
    Separator separator10;
    @FXML
    TextField textTreeNodes;
    @FXML
    Button goToHome;
    @FXML
    Button searchInorderBtn;
    @FXML
    Button searchPostOrderBtn;
    @FXML
    Button searchPreOrderBtn;
    @FXML
    Button searchRootToLeafBtn;
    @FXML
    Button searchMaxValueBtn;
    @FXML
    Button searchMinValueBtn;
    @FXML
    Button searchTreeHeightBtn;
    @FXML
    Button searchTreeSizeBtn;
    @FXML
    Button searchTreeDepthBtn;
    @FXML
    private MenuItem userMenu;

    BST bst1;
    BSTNode bstn;
    BSTNode nod;
    BSTNode nod1;
    Color g = Color.BLUE;
    int scaleX;
    int scaleY;
    int dif;
    int d1 = 100;
    int d2 = 100;
    StackPane stp;
    private double speed = 400;

    @FXML
    public void handlesortsBtn(ActionEvent event) {
        if (event.getSource() == goToHome) {
            WelcomeApp.getInstance().gotoHome();
        }
    }

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
        anchorP.setVisible(false);
        //bubbleSortBtn.setVisible(true);
        separator1.setVisible(false);
        separator2.setVisible(false);
        separator3.setVisible(false);
        separator4.setVisible(false);
        separator5.setVisible(false);
        separator6.setVisible(false);
        separator7.setVisible(false);
        separator8.setVisible(false);
        separator9.setVisible(false);
        separator10.setVisible(false);

        userMenu.setText(LoginUser.getLoggedUser().getUserName());
    }

    @FXML
    private void handleLogout() {
        LoginUser.userLogout();
    }

    private int getNumberTreeNodes() {
        String s = textTreeNodes.getText();
        int nr = -1;
        try {
            nr = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            nr = -1;
        }
        return nr;
    }

    @FXML
    private void handleButtonGenerate(ActionEvent event) {
        generateTree();

        anchorP.setVisible(true);
        StackPane stp = new StackPane();
        anchorP.getChildren().clear();
        anchorP.getChildren().add(stp);
        stp.prefWidthProperty().bind(anchorP.widthProperty());
        stp.prefHeightProperty().bind(anchorP.heightProperty());
        stp.setStyle("-fx-background-color: #336699;");

        BSTNode node = bst1.getRoot();
        SequentialTransition st = new SequentialTransition();
        DrawTree(node, g, st, stp);
        st.play();
    }

    @FXML
    private void handleButtonInorder(ActionEvent event) {

        try {
            bst1.BSTList.clear();
            bst1.printTreeInOrder();
            textArea.setText("Searching Tree InOrder \n");
            for (int w = 0; w < bst1.BSTList.size(); w++) {
                if (w % 10 == 0) {
                    textArea.appendText("\n");
                }
                //System.out.print(bst1.BSTList.get(w).getDataToString()+" ");
                textArea.appendText(bst1.BSTList.get(w).getDataToString() + " ");
            }
            searchInorder();
        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }

    @FXML
    private void handleButtonPostOrder(ActionEvent event) {
        try {
            bst1.BSTList.clear();
            bst1.printTreePostOrder();
            textArea.setText("Searching Tree PostOrder \n");
            for (int w = 0; w < bst1.BSTList.size(); w++) {
                if (w % 10 == 0) {
                    textArea.appendText("\n");
                }
                //System.out.print(bst1.BSTList.get(w).getDataToString()+" ");
                textArea.appendText(bst1.BSTList.get(w).getDataToString() + " ");
            }
            searchPostOrder();
        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }

    @FXML
    private void handleButtonPreOrder(ActionEvent event) {
        try {
            bst1.BSTList.clear();
            bst1.printTreePreOrder();
            textArea.setText("Searching Tree PreOrder \n");
            for (int w = 0; w < bst1.BSTList.size(); w++) {
                if (w % 10 == 0) {
                    textArea.appendText("\n");
                }
                //System.out.print(bst1.BSTList.get(w).getDataToString()+" ");
                textArea.appendText(bst1.BSTList.get(w).getDataToString() + " ");
            }
            searchPreOrder();
        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }

    @FXML
    private void handleButtonLeaves(ActionEvent event) {
        try {
            bst1.BSTList.clear();
            bst1.printAllRootToLeafPaths();
            textArea.setText("Searching Tree Leaves \n");
            for (int w = 0; w < bst1.BSTList.size(); w++) {
                if (w % 10 == 0) {
                    textArea.appendText("\n");
                }
                //System.out.print(bst1.BSTList.get(w).getDataToString()+" ");
                textArea.appendText(bst1.BSTList.get(w).getDataToString() + " ");
            }
            searchRootToLeaf();
        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }

    @FXML
    private void handleButtonMaxValue(ActionEvent event) {
        try {
            bst1.BSTList.clear();
            bst1.maxValue();
            textArea.setText("Searching Tree Max Value \n");
            for (int w = 0; w < bst1.BSTList.size(); w++) {
                if (w % 10 == 0) {
                    textArea.appendText("\n");
                }
                //System.out.print(bst1.BSTList.get(w).getDataToString()+" ");
                textArea.appendText(bst1.BSTList.get(w).getDataToString() + " ");
            }
            searchMaxValueNode();
        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }

    @FXML
    private void handleButtonMinValue(ActionEvent event) {
        try {
            bst1.BSTList.clear();
            bst1.minValue();
            textArea.setText("Searching Tree Min Value \n");
            for (int w = 0; w < bst1.BSTList.size(); w++) {
                if (w % 10 == 0) {
                    textArea.appendText("\n");
                }
                //System.out.print(bst1.BSTList.get(w).getDataToString()+" ");
                textArea.appendText(bst1.BSTList.get(w).getDataToString() + " ");
            }
            searchMinValueNode();
        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }

    @FXML
    private void handleButtonTreeHeight(ActionEvent event) {
        try {
            textArea.setText("Searching Tree Height \n");
            //System.out.print(bst1.TreeHeight()+" ");
            textArea.appendText(bst1.TreeHeight() + " ");

        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }

    @FXML
    private void handleButtonTreeSize(ActionEvent event) {
        try {
            textArea.setText("Searching Tree Size \n");
            //System.out.print(bst1.size()+" ");
            textArea.appendText(bst1.size() + " ");
        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }

    @FXML
    private void handleButtonTreeDepth(ActionEvent event) {
        try {
            textArea.setText("Searching Tree Depth \n");
            //System.out.print(bst1.maxDepth()+" ");
            textArea.appendText(bst1.maxDepth() + " ");

        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }

    private void generateTree() {
        int numberTreeNodes = getNumberTreeNodes();
        if (numberTreeNodes < 5 || numberTreeNodes > 85) {
            textTreeNodes.requestFocus();
            Alert alert = new Alert(AlertType.WARNING, "Please enter correct number of nodes");
            alert.show();
            return;
        }
        bst1 = new BST();
        bst1.createTree(nod1, numberTreeNodes);
    }

    public void searchInorder() {
        try {
            int r = 0;
            drawInOrderGraph();

        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }

    public void searchPostOrder() {
        try {
            int r = 0;
            drawPostOrderGraph();

        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }

    public void searchPreOrder() {
        try {
            int r = 0;
            drawPreOrderGraph();

        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }

    public void searchRootToLeaf() {
        try {
            int r = 0;
            drawRootToLeaf();

        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }
/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
    public void searchMaxValueNode() {
        try {
            int r = 0;
            drawMaxValueNode();
        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }

    public void searchMinValueNode() {
        try {
            int r = 0;;
            drawMinValueNode();
        } catch (Exception e) {
            String m = e.getMessage();
            String n = m;
        }
    }

    public void DrawTree(BSTNode n, Color g, SequentialTransition st, StackPane stp) {
        //traver inorder to draw each node         
        double anchorWidth = anchorP.widthProperty().doubleValue() - 20;
        double anchorHeight = anchorP.heightProperty().doubleValue() - 20;
        int scaleX = (int) (anchorWidth / bst1.sumNodes);
        BSTNode root = bst1.getRoot();
        int hgh = bst1.TreeHeight(root);
        int scaleY = (int) (anchorHeight / hgh);
        int x = 0;
        int y = 0;
        int x2 = 0;
        int y2 = 0;
        int dif = 0;
        g = Color.BLUE;

        if (n != null) {  //traverse inorde , to draw nodes
            DrawTree(n.getLeft(), g, st, stp); // traverse inorder left part of tree
            x = (int) (n.getX() * scaleX);   // x position
            y = (int) (n.getY() * scaleY) + 10; // y position
            String str = n.getDataToString();
            //System.out.println(n.getDataToString() + ", " + Double.toString(n.xAxis)+ ", " + Double.toString(n.yAxis) +
            //        ", x: " + Integer.toString(x)+ ", y: " + Integer.toString(y));

            Circle circle = new Circle();
            circle.setCenterX(x - dif);
            circle.setCenterY(y);
            circle.setRadius(10.0f);
            circle.fillProperty().set(Color.CORAL);

            Text text = new Text(str);
            centerText(text, x - dif, y);
            text.toFront();

            if (n.getLeft() != null) {    //draws the line to left child if it exists     		
                x2 = (int) (n.getLeft().getX() * scaleX);
                y2 = (int) (n.getLeft().getY() * scaleY);
                Line line = new Line(x - dif, y, x2 - dif, y2);
                anchorP.getChildren().add(line);
            }

            if (n.getRight() != null) {
                x2 = (int) (n.getRight().getX() * scaleX);  //draw line to right child
                y2 = (int) (n.getRight().getY() * scaleY);
                Line line = new Line(x - dif, y, x2 - dif, y2);
                anchorP.getChildren().add(line);
            }

            anchorP.getChildren().addAll(circle, text);
            DrawTree(n.getRight(), g, st, stp);    //traverse inorder left part of tree
        }

        if (n != null) {
            int xr = (int) bst1.getRoot().getX() * scaleX;
            int yr = (int) bst1.getRoot().getY() * scaleY + 10;
            Circle circleR = new Circle();
            circleR.setCenterX(xr - dif);
            circleR.setCenterY(yr);
            circleR.setRadius(10.0f);
            circleR.fillProperty().set(Color.LIMEGREEN);

            String strR = bst1.getRoot().getDataToString();
            Text textR = new Text(/*x-dif, y+dif/2,*/strR);
            centerText(textR, xr - dif, yr);
            textR.toFront();

            anchorP.getChildren().add(circleR);
            anchorP.getChildren().add(textR);
        }
    }

    private void centerText(Text text, int x, int y) {
        double W = text.getBoundsInLocal().getWidth();
        double H = text.getBoundsInLocal().getHeight();
        text.relocate(x - W / 2, y - H / 2);
    }

    public void drawInOrderGraph() {
        double anchorWidth = anchorP.widthProperty().doubleValue() - 20;
        double anchorHeight = anchorP.heightProperty().doubleValue() - 20;
        int scaleX = (int) (anchorWidth / bst1.sumNodes);
        BSTNode root = bst1.getRoot();
        int hgh = bst1.TreeHeight(root);
        int scaleY = (int) (anchorHeight / hgh);

        if (bst1.getRoot() != null) {
            for (int r = 0; r < bst1.BSTList.size(); r++) {

                int xD = (int) bst1.BSTList.get(r).getX() * scaleX;
                int yD = (int) bst1.BSTList.get(r).getY() * scaleY + 10;

                Circle circleD = new Circle();
                circleD.setCenterX(xD - dif);
                circleD.setCenterY(yD);
                circleD.setRadius(10.0f);
                circleD.fillProperty().set(Color.LIMEGREEN);

                String strD = bst1.BSTList.get(r).getDataToString();
                Text textD = new Text(strD);
                centerText(textD, xD - dif, yD);
                textD.toFront();

                anchorP.getChildren().add(circleD);
                anchorP.getChildren().add(textD);
            }
        }
    }

    public void drawPostOrderGraph() {

        double anchorWidth = anchorP.widthProperty().doubleValue() - 20;
        double anchorHeight = anchorP.heightProperty().doubleValue() - 20;
        int scaleX = (int) (anchorWidth / bst1.sumNodes);
        BSTNode root = bst1.getRoot();
        int hgh = bst1.TreeHeight(root);
        int scaleY = (int) (anchorHeight / hgh);//.sumNodes);

        if (bst1.getRoot() != null) {
            for (int r = 0; r < bst1.BSTList.size(); r++) {

                int xD = (int) bst1.BSTList.get(r).getX() * scaleX;
                int yD = (int) bst1.BSTList.get(r).getY() * scaleY + 10;

                Circle circleD = new Circle();
                circleD.setCenterX(xD - dif);
                circleD.setCenterY(yD);
                circleD.setRadius(10.0f);
                circleD.fillProperty().set(Color.LIGHTBLUE);

                String strD = bst1.BSTList.get(r).getDataToString();
                Text textD = new Text(strD);
                centerText(textD, xD - dif, yD);
                textD.toFront();

                anchorP.getChildren().add(circleD);
                anchorP.getChildren().add(textD);
            }
        }
    }

    public void drawPreOrderGraph() {

        double anchorWidth = anchorP.widthProperty().doubleValue() - 20;
        double anchorHeight = anchorP.heightProperty().doubleValue() - 20;
        int scaleX = (int) (anchorWidth / bst1.sumNodes);
        BSTNode root = bst1.getRoot();
        int hgh = bst1.TreeHeight(root);
        int scaleY = (int) (anchorHeight / hgh);//.sumNodes);

        if (bst1.getRoot() != null) {
            for (int r = 0; r < bst1.BSTList.size(); r++) {

                int xD = (int) bst1.BSTList.get(r).getX() * scaleX;
                int yD = (int) bst1.BSTList.get(r).getY() * scaleY + 10;

                Circle circleD = new Circle();
                circleD.setCenterX(xD - dif);
                circleD.setCenterY(yD);
                circleD.setRadius(10.0f);
                circleD.fillProperty().set(Color.VIOLET);

                String strD = bst1.BSTList.get(r).getDataToString();
                Text textD = new Text(strD);
                centerText(textD, xD - dif, yD);
                textD.toFront();

                anchorP.getChildren().add(circleD);
                anchorP.getChildren().add(textD);
            }
        }
    }

    public void drawRootToLeaf() {

        double anchorWidth = anchorP.widthProperty().doubleValue() - 20;
        double anchorHeight = anchorP.heightProperty().doubleValue() - 20;
        int scaleX = (int) (anchorWidth / bst1.sumNodes);
        BSTNode root = bst1.getRoot();
        int hgh = bst1.TreeHeight(root);
        int scaleY = (int) (anchorHeight / hgh);//.sumNodes);

        if (bst1.getRoot() != null) {
            for (int r = 0; r < bst1.BSTList.size(); r++) {

                int xD = (int) bst1.BSTList.get(r).getX() * scaleX;
                int yD = (int) bst1.BSTList.get(r).getY() * scaleY + 10;

                Circle circleD = new Circle();
                circleD.setCenterX(xD - dif);
                circleD.setCenterY(yD);
                circleD.setRadius(10.0f);
                circleD.fillProperty().set(Color.BISQUE);

                String strD = bst1.BSTList.get(r).getDataToString();
                Text textD = new Text(strD);
                centerText(textD, xD - dif, yD);
                textD.toFront();

                anchorP.getChildren().add(circleD);
                anchorP.getChildren().add(textD);
            }
        }
    }

    public void drawMaxValueNode() {

        double anchorWidth = anchorP.widthProperty().doubleValue() - 20;
        double anchorHeight = anchorP.heightProperty().doubleValue() - 20;
        int scaleX = (int) (anchorWidth / bst1.sumNodes);
        BSTNode root = bst1.getRoot();
        int hgh = bst1.TreeHeight(root);
        int scaleY = (int) (anchorHeight / hgh);//.sumNodes);

        if (bst1.getRoot() != null) {
            for (int r = 0; r < bst1.BSTList.size(); r++) {

                int xD = (int) bst1.BSTList.get(r).getX() * scaleX;
                int yD = (int) bst1.BSTList.get(r).getY() * scaleY + 10;

                Circle circleD = new Circle();
                circleD.setCenterX(xD - dif);
                circleD.setCenterY(yD);
                circleD.setRadius(10.0f);
                circleD.fillProperty().set(Color.KHAKI);

                String strD = bst1.BSTList.get(r).getDataToString();
                Text textD = new Text(strD);
                centerText(textD, xD - dif, yD);
                textD.toFront();

                anchorP.getChildren().add(circleD);
                anchorP.getChildren().add(textD);

            }
        }
    }

    public void drawMinValueNode() {

        double anchorWidth = anchorP.widthProperty().doubleValue() - 20;
        double anchorHeight = anchorP.heightProperty().doubleValue() - 20;
        int scaleX = (int) (anchorWidth / bst1.sumNodes);
        BSTNode root = bst1.getRoot();
        int hgh = bst1.TreeHeight(root);
        int scaleY = (int) (anchorHeight / hgh);//.sumNodes);

        if (bst1.getRoot() != null) {
            for (int r = 0; r < bst1.BSTList.size(); r++) {

                int xD = (int) bst1.BSTList.get(r).getX() * scaleX;
                int yD = (int) bst1.BSTList.get(r).getY() * scaleY + 10;

                Circle circleD = new Circle();
                circleD.setCenterX(xD - dif);
                circleD.setCenterY(yD);
                circleD.setRadius(10.0f);
                circleD.fillProperty().set(Color.KHAKI);

                String strD = bst1.BSTList.get(r).getDataToString();
                Text textD = new Text(strD);
                centerText(textD, xD - dif, yD);
                textD.toFront();

                anchorP.getChildren().add(circleD);
                anchorP.getChildren().add(textD);
            }
        }
    }

}
