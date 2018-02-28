/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
package jb;

/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
public class GraphEdge {

    public int weight;
    public GraphNode nodePairBegin;
    public GraphNode nodePairEnd;

   
    public GraphEdge() {

    }

    public GraphEdge(GraphNode newNodePairBegin, GraphNode newNodePairEnd, int newWeight) {
        nodePairBegin = newNodePairBegin;
        nodePairEnd = newNodePairEnd;

        weight = newWeight;
    }

    public GraphEdge(GraphNode newNodePairBegin, GraphNode newNodePairEnd) {
        nodePairBegin = newNodePairBegin;
        nodePairEnd = newNodePairEnd;
    }

    public int getWeight() {
        return weight;
    }

    public GraphNode getNodePairBegin() {
        return nodePairBegin;
    }

    public GraphNode getnodePairEnd() {
        return nodePairEnd;
    }

    public void setGraphNode(GraphNode newNodePairBegin, GraphNode newNodePairEnd, int newWeight) {
        nodePairBegin = newNodePairBegin;
        nodePairEnd = newNodePairEnd;
        weight = newWeight;
    }

    // index oznacza numer zapisany w wierzcholku
    // metoda zwraca numer zapisany nadrugim koncu krawedzi:
    public int getIndexOfNeighbour(int nodeIndex) {

        if (nodePairBegin.index == nodeIndex) {
            return nodePairEnd.index;
        } else {
            return nodePairBegin.index;
        }
    }
}
/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */