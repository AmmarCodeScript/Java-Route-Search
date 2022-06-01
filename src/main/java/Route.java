import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Route {

    private LinkedHashMap<String, Node> nodes;

    public Route(LinkedHashMap<String, Node> nodes) {
        this.nodes = nodes;
    }

    public String listNodesAndLinks() {
        String result = "";
        for (Node node : nodes.values()) {
            result += String.format("[%s]\t %s ->\t", node.getKey(), node.getName());
            for (Node neighbor : node.getNeighbors()) {
                result += "[" + neighbor.getKey() + "] ";
            }
            result += "\n";
        }
        return result;
    }

    public Node getNode(String key) {
        return nodes.get(key);
    }

    public static ArrayList<Node> getRoute(Node startNode, Node endNode) {
        ArrayList<Node> candidates = new ArrayList<>();
        ArrayList<Node> visited = new ArrayList<>();
        Node current = startNode;
        boolean done = false;

        while (!done) {
            double minF = 0;
            Node next = null;

            for (Node neighbor : current.getNeighbors()) {
                if (!visited.contains(neighbor) && !candidates.contains(neighbor)) {
                    candidates.add(neighbor);
                    neighbor.setPrevious(current);
                }
            }

            for (Node cun : candidates) {
                if (cun == endNode) {
                    done = true;
                    break;
                } else {
                    double F = cun.getF(startNode, endNode);
                    if (minF == 0 || minF > F) {
                        minF = F;
                        next = cun;
                        if (cun.getNeighbors().contains(current)) {
                            cun.setPrevious(current);
                        }
                    }
                }
            }
            if (!done) {
                current = next;
                visited.add(current);
                candidates.remove(current);
            }
        }
        ArrayList<Node> route = new ArrayList<>();
        current = endNode;
        while (current != startNode) {
            route.add(0, current);
            current = current.getPrevious();
        }
        route.add(0, startNode);
        return route;
    }
}