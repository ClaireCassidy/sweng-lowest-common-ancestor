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
    public ArrayList<Integer> bfs(Node startNode) {
        LinkedList<Node> q = new LinkedList<>();
        ArrayList<Integer> order = new ArrayList<>();

        q.add(startNode);
        System.out.println("\nPerforming BFS w starting node "+startNode+"\n");

        while(!q.isEmpty()) {
            System.out.println("Queue: "+Arrays.toString(q.toArray()));
            Node curNode = q.remove();
            order.add(curNode.getVal());

            System.out.println("Cur Node: " + curNode);

            for (Node n: curNode.getChildren()) {
                q.add(n);
                System.out.println("Adding "+n+" to queue");
            }
            System.out.println();
        }
        return order;
    }

    public boolean bfsForTarget(Node startNode, Node target) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(startNode);
        boolean targetFound = false;

        System.out.println("\nPerforming BFS w starting node "+startNode+" seeking target " + target + "\n");

        while(!q.isEmpty()) {
            System.out.println("Queue: "+Arrays.toString(q.toArray()));
            Node curNode = q.remove();

            System.out.println("Cur Node: " + curNode);

            if (curNode.getChildren().contains(target)) {
                targetFound = true;
                break;
            }
            for (Node n: curNode.getChildren()) {
                q.add(n);
                System.out.println("Adding "+n+" to queue");
            }
            System.out.println();
        }

        return targetFound;
    }

    public void colorAncestorsBlue(Node target) {
        // performs a BFS from each node 'n' to determine if n is an ancestor of 'target'
        // If so, colors 'n' blue.

        // Create a queue containing all the nodes in the graph
        LinkedList<Node> nodesInGraph = new LinkedList<>();
        nodesInGraph.addAll(nodes);

        while (!nodesInGraph.isEmpty()) {
            Node curStartNode = nodesInGraph.remove();

            boolean ancestorOfTarget = bfsForTarget(curStartNode, target);
        }
    }

    // returns a reference to the node in the graph with a given value val
    public Node getNodeWithValue(int val) {
        Node theNode = null;

        for (Node n: nodes) {
            if (n.getVal() == val) {
                return n;
            }
        }

        return theNode;
    }

}
