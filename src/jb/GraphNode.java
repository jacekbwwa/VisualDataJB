/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
package jb;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
public class GraphNode {

    // attributes for create and draw node
    public int locationX = 0; // x axis
    public int locationY = 0; // y axis
    public int index; // node id = node data
    public Color clr; // node color

    // attributes for search the shortes path in graph
    private int distanceFromStart = Integer.MAX_VALUE; // suma wag najkrotszej sciezki od startu do tego wierzcholka
    private boolean visited;
    private ArrayList<GraphEdge> edges = new ArrayList<GraphEdge>();// zawiera tylko krawedzie zwiazane z tym wierzcholkiem
    private ArrayList<GraphEdge> pathEdges = new ArrayList<GraphEdge>(); // zawiera liste krawedzi zapisaną w ściezce przeszukiwania od startu do tego wierzcholka

 
    public GraphNode() {

    }

    public GraphNode(int newLocationX, int newLocationY, int indX, Color colr) {
        locationX = newLocationX;
        locationY = newLocationY;
        index = indX;
        clr = colr;
    }
 
    public int getLocationX() {
        return locationX;
    }
    
    public int getLocationY() {
        return locationY;
    }
   
    public int getNodeIndex() {
        return index;
    }

    public String getDataToString() {
        return Integer.toString(index);
    }

    public void addEdgeToPath(GraphEdge ge) {
        pathEdges.add(ge);
    }

    public ArrayList<GraphEdge> getPathEdges() {
        return pathEdges;
    }

    public void setPathEdges(ArrayList<GraphEdge> edges) {
        pathEdges = edges;
    }

    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    public void setDistanceFromStart(int distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }

    public boolean getVisited() {
        return visited;
    }
	/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public ArrayList<GraphEdge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<GraphEdge> edges) {
        this.edges = edges;
    }

}
