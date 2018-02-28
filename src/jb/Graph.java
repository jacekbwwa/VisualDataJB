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
public class Graph {

    public int Gsize;   // rozmiar macierzy grafu    
    private int startIndex; // index startowy do przeszukiwania grafu
    int vhAxisLength; // liczba nodow w grafie

    ArrayList<GraphNode> vhAxis; // lista wierzcholkow
    ArrayList<GraphEdge> edges; // lista krawedzi grafu

    public Graph() {
        vhAxis = new ArrayList<GraphNode>();
        edges = new ArrayList<GraphEdge>();
        Gsize = 0;
        startIndex = 0;
        vhAxisLength = 0;
    }

    public void cleanGraphObjects() {
        vhAxis.clear();
        edges.clear();
        Gsize = 0;
        vhAxisLength = 0;
        startIndex = 0;
    }

    // metoda tworzy nody grafu;
    // nodeNumbers - liczba nodów w grafie, 
    // num - rozmiar macierzy, w której umiescimy nody
    public ArrayList<GraphNode> createRandomGraphNodes(int nodeNumbers, int num) {
        Gsize = num;
        int hAxis;
        int vAxis;
        int index = 0;
        //najpierw tworzymy nody dla calej macierzy
        for (hAxis = 0; hAxis < Gsize; hAxis++) {
            for (vAxis = 0; vAxis < Gsize; vAxis++) {
                GraphNode vHX = new GraphNode(vAxis, hAxis, index, Color.BLUE);
                vhAxis.add(vHX);
                index++;
            }
        }
        vhAxisLength = vhAxis.size();
        // nastepnie wyrzucamy czesc nodów (losowo) i zostawiamy tylko tyle ile wynosi nodeNumbers
        limitGraphMatrixToNodeNumbers(nodeNumbers);
        return vhAxis;
    }

    // metoda wyrzuca czesc wierzcholkow z macierzy tak aby pozostawic tylko wymaganą ilość
    private void limitGraphMatrixToNodeNumbers(int nodeNumbers) {
        ArrayList<Integer> lista = new ArrayList<Integer>(); // lista wylosowanych wierzcholkow w macierzy grafu, 
        //zapamietujemy wylosowane, zeby zapewnic unikalnosc
        int r; // zmienna do losowania
        while (lista.size() < nodeNumbers) {
            r = (int) (Math.random() * vhAxisLength); // losuje od zera do vhAxisLength-1
            if (!lista.contains(r)) {
                lista.add(r);
            }
        }
        // w grafie zostawiam tylko wierzcholki z listy
        int len = vhAxisLength - 1;
        for (int i = len; i >= 0; i--) {
            if (!lista.contains(vhAxis.get(i).index)) {
                vhAxis.remove(i);
            }
        }
    }

    public int getvhAxisSize() {
        vhAxisLength = vhAxis.size();
        return vhAxisLength;
    }

    // metoda tworzy losowo krawedzie grafu i przydziela im losowe wagi
    public void createEdgesGraphNodes() {
        if (getvhAxisSize() > 0) {
            for (int k = 0; k < vhAxis.size(); k++) {
                GraphNode firstN = vhAxis.get(k);
                int counter = 0;
                int len = vhAxis.size();
                while (counter < 2) {
                    int r = (int) (Math.random() * len); // losuje od zera do vhAxis.size()-1
                    if (r != k) {
                        counter++;
                        // generuj krawedz:
                        int weight = (int) ((Math.random() + 0.03) * 100 + 1);

                        GraphNode secondN = vhAxis.get(r);
                        //jesli nie istnieje krawedz f-s
                        if (!EdgeExist(firstN, secondN)) {
                            GraphEdge ge = new GraphEdge();
                            ge.setGraphNode(firstN, secondN, weight);
                            edges.add(ge);
                        }
                    }
                }
            }
        }
    }

    // metoda wykorzystywana do losowego tworzenia krawedzi,
    // sprawdza czy istnieje juz krawedz pomiedzy wierzcholkami fn i sn
    private boolean EdgeExist(GraphNode fn, GraphNode sn) {
        for (int i = 0; i < edges.size(); i++) {
            GraphNode gnf = edges.get(i).getNodePairBegin();
            GraphNode gns = edges.get(i).getnodePairEnd();
            if ((gnf.index == fn.index && gns.index == sn.index)
                    || (gnf.index == sn.index && gns.index == fn.index)) {
                return true;
            }
        }
        return false;
    }

    //metoda sprawdza czy w nodach grafu znajduje sie podany indeks:
    public boolean correctIndex(int index) {
        for (int i = 0; i < vhAxis.size(); i++) {
            if (vhAxis.get(i).index == index) {
                return true;
            }
        }
        return false;
    }

    // metoda zwraca nod grafu o numerze równym index
    public GraphNode getByNodeValue(int index) {
        for (int i = 0; i < vhAxis.size(); i++) {
            if (vhAxis.get(i).index == index) {
                return vhAxis.get(i);
            }
        }
        return null;
    }

    // na potrzeby przeszukiwania grafu wrzuca do kazdego noda liste krawedzi zwiazanych z tym nodem 
    // kazda krawedz bedzie wrzucona do dwoch nodow (pocz i koniec)
    private void addEgdesToNodes() {
        int len = edges.size();
        for (int i = 0; i < len; i++) {
            GraphEdge ge = edges.get(i);
            GraphNode nodeBegin = ge.getNodePairBegin();
            GraphNode nodeEnd = ge.getnodePairEnd();
            nodeBegin.getEdges().add(ge);
            nodeEnd.getEdges().add(ge);
        }
    }

    public void computeShortestPaths(int startIndex) {

        this.startIndex = startIndex;
        addEgdesToNodes(); // na potrzeby przeszukiwania dodaję no kazdego noda liste jego krawedzi

        // zaczynamy od wierzcholka startIndex
        getByNodeValue(startIndex).setDistanceFromStart(0);
        int nextNode = startIndex;

        // odwiedzamy kazdy wierzcholek:
        for (int i = 0; i < vhAxis.size(); i++) {
            ArrayList<GraphEdge> nodeEdges = getByNodeValue(nextNode).getEdges();
            for (int j = 0; j < nodeEdges.size(); j++) {
                int nextNodeNeighbourIndex = nodeEdges.get(j).getIndexOfNeighbour(nextNode);
                if (!getByNodeValue(nextNodeNeighbourIndex).getVisited()) { // jesli nie byl odwiedzony
                    int tmpValue = getByNodeValue(nextNode).getDistanceFromStart() + nodeEdges.get(j).weight;

                    if (tmpValue < getByNodeValue(nextNodeNeighbourIndex).getDistanceFromStart()) {
                        getByNodeValue(nextNodeNeighbourIndex).setDistanceFromStart(tmpValue);

                        getByNodeValue(nextNodeNeighbourIndex).getPathEdges().clear();
                        for (GraphEdge eg : getByNodeValue(nextNode).getPathEdges()) {
                            getByNodeValue(nextNodeNeighbourIndex).addEdgeToPath(eg);
                        }
                        getByNodeValue(nextNodeNeighbourIndex).addEdgeToPath(nodeEdges.get(j));
                    }
                }
            }
            getByNodeValue(nextNode).setVisited(true);
            // nastepny wierzcholek to wierzcholek o najmniejszej odleglosci          
            nextNode = getNodeWithShortestDistance().index;//vhAxis.get(getNodeShortestDistanced()).index;
        }
    }

    // znajduje wierzcholek o najmniejszym zapisanym dystansie:
    private GraphNode getNodeWithShortestDistance() {
        int idx = 0;
        int dist = Integer.MAX_VALUE;

        for (int i = 0; i < vhAxis.size(); i++) {
            int nodeDist = vhAxis.get(i).getDistanceFromStart();

            if (!vhAxis.get(i).getVisited() && nodeDist < dist) {
                dist = nodeDist;
                idx = i;
            }
        }
        return vhAxis.get(idx);
    }
	
	/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */

    // display result
    public String getResult() {
        String message = "Dijkstra's algorithm";
        message += "\nfor finding the shortest way between nodes in a graph";
        message += "\n\nNumber of nodes = " + vhAxis.size();
        message += "\nNumber of edges = " + edges.size();

        for (int i = 0; i < vhAxis.size(); i++) {
            message += ("\nThe shortest distance from node "
                    + Integer.toString(startIndex)
                    + " to node " + vhAxis.get(i).index + " is "
                    + vhAxis.get(i).getDistanceFromStart());
        }

        //System.out.println(message);
        return message;
    }

    public void cleanNodesForNewSerach() {
        for (GraphNode gn : vhAxis) {
            gn.setDistanceFromStart(Integer.MAX_VALUE);
            gn.setVisited(false);
            gn.getEdges().clear();
            gn.getPathEdges().clear();
        }
    }
}
