/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
package jb;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class GraphController implements Initializable {
    
    @FXML
    SplitPane drawPane;
    @FXML
    AnchorPane anchorP;
    @FXML
    TextArea textArea;
    @FXML
    Button setStartEndBtn;
    @FXML
    Button generateBtn;
    @FXML
    Button dijkstraSearchBtn;
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
    Separator separatorStart;
    @FXML
    Separator separatorEnd;
    @FXML
    Separator separatorStartEnd;
    @FXML
    TextField textGraphNodes;
    @FXML
    Button goToHome;
    @FXML
    TextField textStartIndex;
    @FXML
    TextField textEndIndex;
    @FXML
    Label labelStart;
    @FXML
    Label labelEnd;
    @FXML
    private MenuItem userMenu;

    private Graph grp = null;
    private int startIndex = -1; // node startowy, a dokladnie jego index zapisany w kolku
    private int endIndex = -1; // node koncowy, a dokladnie jego index zapisany w kolku

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anchorP.setVisible(true);
        separator1.setVisible(false);
        separator2.setVisible(false);
        separator3.setVisible(false);
        separator4.setVisible(false);
        separator5.setVisible(false);
        separator6.setVisible(false);
        separatorStart.setVisible(false);
        separatorEnd.setVisible(false);
        separatorStartEnd.setVisible(false);

        graphControlsSetVisible(false);
        userMenu.setText(LoginUser.getLoggedUser().getUserName());
    }

    private void graphControlsSetVisible(boolean visible) {
        setStartEndBtn.setVisible(visible);
        dijkstraSearchBtn.setVisible(visible);
        textEndIndex.setVisible(visible);
        textStartIndex.setVisible(visible);
        labelEnd.setVisible(visible);
        labelStart.setVisible(visible);
    }

    @FXML
    private void handleLogout() {
        LoginUser.userLogout();
    }

    @FXML
    public void goToHome(ActionEvent event) {
        if (event.getSource() == goToHome) {
            WelcomeApp.getInstance().gotoMenu();
        }
    }

    @FXML
    private void handleButtonGenerate(ActionEvent event) {
        try {
            anchorP.setVisible(true);
            StackPane stp = new StackPane();
            anchorP.getChildren().clear();
            anchorP.getChildren().add(stp);
            stp.prefWidthProperty().bind(anchorP.widthProperty());
            stp.prefHeightProperty().bind(anchorP.heightProperty());
            stp.setStyle("-fx-background-color: #336699;");
            anchorP.getChildren().clear();

            createGraph();
            textArea.setText("");

            Color color = Color.GREEN;
            int indexStart = -1;
            int indexEnd = -1;

            DrawGraph(color, indexStart, indexEnd, null);
            graphControlsSetVisible(true);
        } catch (Exception e) {
            WelcomeApp.getInstance().showMessage("Error", "", e.getMessage());
        }
    }

    @FXML
    public void setStartEndBtnClick(ActionEvent event) {

        if (grp == null) {
            WelcomeApp.getInstance().showMessage("VisualDataJB", "No graph generated", "Please click first graph generate");
            return;
        }
        try {
            startIndex = Integer.parseInt(textStartIndex.getText());
            endIndex = Integer.parseInt(textEndIndex.getText());
        } catch (Exception ex) {
            WelcomeApp.getInstance().showMessage("VisualDataJB", "Incorrect start/end node", "Please enter correct index for start node  and for end node and click Set button");
            startIndex = -1;
            endIndex = -1;
            textStartIndex.setText("");
            textEndIndex.setText("");
            return;
        }
        if (!grp.correctIndex(startIndex) || !grp.correctIndex(endIndex)) {
            WelcomeApp.getInstance().showMessage("VisualDataJB", "Incorrect start/end node", "Please enter correct index for start node  and for end node and click Set button");
            startIndex = -1;
            endIndex = -1;
            textStartIndex.setText("");
            textEndIndex.setText("");
            return;
        }
        Color color = Color.FUCHSIA;
        DrawGraph(color, startIndex, endIndex, null);
        textArea.setText("");
        grp.cleanNodesForNewSerach();
    }

    @FXML
    public void dijkstraSearchBtnClick(ActionEvent event) {

        if (grp == null) {
            WelcomeApp.getInstance().showMessage("VisualDataJB", "No graph generated", "please click first graph generate");
            return;
        }
        if (!grp.correctIndex(startIndex) || !grp.correctIndex(endIndex)) {
            WelcomeApp.getInstance().showMessage("VisualDataJB", "Incorrect start/end node", "Please enter correct index for start node  and for end node and click Set button");
            return;
        }

        grp.computeShortestPaths(startIndex);
        textArea.setText(grp.getResult());

        // dla wierzcholka end rysuje sciezke najkrotszej drogi:
        GraphNode gp = grp.getByNodeValue(endIndex);
        if (gp != null) {
            textArea.appendText("\nThe shortest path form start to the end: ");
            for (GraphEdge ge : gp.getPathEdges()) {
                textArea.appendText(Integer.toString(ge.weight) + ", ");
            }
            textArea.appendText("\n" + getPathByNode(gp));
            DrawGraph(Color.FUCHSIA, startIndex, endIndex, gp.getPathEdges());
        }
    }

    private void createGraph() {
        // walidacja liczby nodow podanych przez usera:
        int nodeNumbers;
        try {
            nodeNumbers = userGraphNodesNumber();
        } catch (Exception ex) {
            WelcomeApp.getInstance().showMessage("VisualDataJB", "Incorrect graph size", "If you want generate graph please enter correct graph size");
            return;
        }
        if (nodeNumbers < 5 || nodeNumbers > 85) {
            WelcomeApp.getInstance().showMessage("VisualDataJB", "Incorrect graph size", "If you want generate graph please enter correct graph size");
            return;
        }
        int matrixSize = randomMatrixSize(nodeNumbers);
        if (grp == null) {
            grp = new Graph();
        }
        cleanGraph();
        grp.createRandomGraphNodes(nodeNumbers, matrixSize);
        grp.createEdgesGraphNodes();
    }
	
	/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */

    // zwraca wylosowany rozmiar macierzy pasujacy do liczy nodow w grafie
    private int randomMatrixSize(int numberGraphNodes) {
        int n = (int) (1 + Math.random() * 10); // losuje od 1 do 10 wlacznie
        while (numberGraphNodes > n * n) {
            n = (int) (1 + Math.random() * 10);
        }
        return n;
    }

    private void centerText2(Text text, int xgr, int ygr) {
        double W = text.getBoundsInLocal().getWidth();
        double H = text.getBoundsInLocal().getHeight();
        text.relocate(xgr - W / 2, ygr - H / 2);
    }

    public void cleanGraph() {
        grp.cleanGraphObjects();

        startIndex = -1;
        endIndex = -1;

        textStartIndex.setText("");
        textEndIndex.setText("");
    }

    // zwraca liczbe nodow w grafie, podana przez usera w polu tekstowym
    private int userGraphNodesNumber() {
        String s = textGraphNodes.getText();
        return Integer.parseInt(s);
    }

    private String getPathByNode(GraphNode gp) {
        int myIndex = startIndex;
        String pathByNodes = "\nThe shortes path by Nodes: \n";
        pathByNodes += Integer.toString(startIndex);
        for (GraphEdge ge : gp.getPathEdges()) {
            if (ge.nodePairBegin.index == myIndex) {
                pathByNodes += " -> " + Integer.toString(ge.nodePairEnd.index);
                myIndex = ge.nodePairEnd.index;
            } else {
                pathByNodes += " -> " + Integer.toString(ge.nodePairBegin.index);
                myIndex = ge.nodePairBegin.index;
            }
        }
        pathByNodes += "\n";
        return pathByNodes;
    }

    public void DrawGraph(Color clr, int indexStart, int indexEnd, ArrayList<GraphEdge> path) {
        anchorP.setVisible(true);
        StackPane stp = new StackPane();
        anchorP.getChildren().clear();
        anchorP.getChildren().add(stp);
        stp.prefWidthProperty().bind(anchorP.widthProperty());
        stp.prefHeightProperty().bind(anchorP.heightProperty());
        stp.setStyle("-fx-background-color: #336699;");

        double anchorWidth = anchorP.widthProperty().doubleValue() - 20;
        double anchorHeight = anchorP.heightProperty().doubleValue() - 20;

        //grp.Gsize to jest rozmiar macierzy, w ktorej wybieramy losowo wierzcholki grafu
        int distanceX = (int) anchorWidth / (grp.Gsize + 2);
        int distanceY = (int) anchorHeight / (grp.Gsize + 2);

        for (int e = 0; e < grp.edges.size(); e++) {
            DrawEdge(Color.BLACK, grp.edges.get(e), distanceX, distanceY);
        }

        for (int z = 0; z < grp.vhAxis.size(); z++) {
            DrawNode(Color.CORAL, grp.vhAxis.get(z).index, distanceX, distanceY);
        }

        if (indexStart >= 0) {
            DrawNode(clr, indexStart, distanceX, distanceY);
        }
        if (indexEnd >= 0) {
            DrawNode(clr, indexEnd, distanceX, distanceY);
        }
        if (path != null) {
            for (GraphEdge gre : path) {
                DrawEdge(clr, gre, distanceX, distanceY);
            }
        }
    }

    private void DrawEdge(Color color, GraphEdge grEd, int distanceX, int distanceY) {
        int x2s = (int) (grEd.getNodePairBegin().getLocationX()) * distanceX + distanceX;
        int y2s = (int) (grEd.getNodePairBegin().getLocationY()) * distanceY + distanceY;
        int x2e = (int) (grEd.getnodePairEnd().getLocationX()) * distanceX + distanceX;
        int y2e = (int) (grEd.getnodePairEnd().getLocationY()) * distanceY + distanceY;
        Line line = new Line(x2s, y2s, x2e, y2e);
        line.setStroke(color);

        Text text = new Text(Integer.toString(grEd.weight));
        centerText2(text, x2s + (x2e - x2s) / 2, y2s + (y2e - y2s) / 2);
        //text.setFill(color);
        text.toFront();
        text.fillProperty().set(Color.YELLOW);


        anchorP.getChildren().addAll(line, text);
    }

    private void DrawNode(Color color, int graphNodeIndex, int distanceX, int distanceY) {
        GraphNode gn = null;
        for (int i = 0; i < grp.vhAxis.size(); i++) {
            if (grp.vhAxis.get(i).index == graphNodeIndex) {
                gn = grp.vhAxis.get(i);
                break;
            }
        }
        if (gn == null) {
            return;
        }

        int xgr = gn.getLocationX() * distanceX + distanceX;
        int ygr = gn.getLocationY() * distanceY + distanceY;

        String str = gn.getDataToString();

        Circle circle = new Circle();
        circle.setCenterX(xgr);
        circle.setCenterY(ygr);
        circle.setRadius(10.0f);
        circle.fillProperty().set(color);

        Text text = new Text(str);
        centerText2(text, xgr, ygr);
        text.toFront();

        anchorP.getChildren().addAll(circle, text);
    }
}
