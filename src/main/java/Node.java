import java.util.ArrayList;

public class Node {
    // instansvariabel
    private String name, key;
    private double latitude, longitude;
    private ArrayList neighbors = new ArrayList<Node>();
    private Node previous;

    public Node(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public double calculateH(Node endNode) {

        return Utils.getDistance(latitude, longitude, endNode.latitude, endNode.longitude);
    }

    public double calculateG(Node startNode) {
        double G = 0;
        Node current = this;
        while (current != startNode) {
            G += current.calculateH(current.previous);
            current = current.previous;
        }
        return G;
    }

    public double getF(Node startNode, Node endNode) {
        return calculateG(startNode) + calculateH(endNode);
    }


}
