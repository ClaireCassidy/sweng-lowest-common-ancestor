import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DirectedAcyclicGraph {

    ArrayList<Node> nodes;
    Queue<Node> blueNodes;


    public DirectedAcyclicGraph() {
        nodes = new ArrayList<>();

    }

    public DirectedAcyclicGraph(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void printGraph() {
        for (Node node:nodes) {
            System.out.printf("\n"+node+"{ \n\tParents: [");
            for (Node parent: node.getParents()) {
                System.out.print(parent+", ");
            }
            System.out.print("], \n\tChildren: [");
            for (Node child: node.getChildren()) {
                System.out.print(child+", ");
            }
            System.out.println("], \n\tColor: "+node.getColor()+", \n\tCount: "+node.getCount()+"\n}");
        }
    }

    // testing a generic BFS algorithm
    public void bfs(Node startNode) {
        LinkedList<Node> q = new LinkedList<>();

        q.add(startNode);
        System.out.println("\nPerforming BFS w starting node "+startNode+"\n");

        while(!q.isEmpty()) {
            System.out.println("Queue: "+Arrays.toString(q.toArray()));
            Node curNode = q.remove();

            System.out.println("Cur Node: " + curNode);

            for (Node n: curNode.getChildren()) {
                q.add(n);
                System.out.println("Adding "+n+" to queue");
            }
            System.out.println();
        }

    }

}
