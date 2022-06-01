import java.util.Scanner;

public class UI {
    private Scanner scan = new Scanner(System.in);
    private Route route = new Route(GraphData.createGraph());

    public void start() {

        System.out.println(route.listNodesAndLinks());
        System.out.print("Enter start and destination!\n");
        Node start = inputTest("Start: ");
        Node end = inputTest("Destination: ");

        int i = 0;
        for (Node node : Route.getRoute(start, end)) {
            System.out.printf(" %d. [%s] %s%n", ++i, node.getKey(), node.getName());
        }
    }

    public Node inputTest(String textDefault) {
        Node input;
        while (true) {
            System.out.print(textDefault);
            input = route.getNode(scan.nextLine());
            if (input != null) {
                break;
            }
        }
        return input;
    }
}





