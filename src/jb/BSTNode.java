package jb;

/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */
class BSTNode {
    // class represents single node in binary search tree
    
    public BSTNode left, right;    
    public int data;
        
    private double xAxis;
    private double yAxis;

    public BSTNode() {
        left = null;
        right = null;
        data = 0;
    }

    public BSTNode(int n) {
        left = null;
        right = null;
        data = n;
    }
    
    public double getX() {
        return xAxis;
    }

    public void setX(double xAxis) {
        this.xAxis = xAxis;
    }

    public double getY() {
        return yAxis;
    }

    public void setY(double yAxis) {
        this.yAxis = yAxis;
    }    

    //Method sets left node
    public void setLeft(BSTNode n) {        
        left = n;
    }

    //Method  sets right node
    public void setRight(BSTNode n) {        
        right = n;
    }

    public BSTNode getLeft() {
        return left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setData(int d) {
        data = d;
    }

    public int getData() {
        return data;
    }

    public String getDataToString() {
        return Integer.toString(data);
    }
}

/**
 *
 * @author Jacek Byzdra
 * email: jacekbwwa@gmail.com
 */