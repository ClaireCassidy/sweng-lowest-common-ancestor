
import java.util.*;

public class DirectedAcyclicGraph {

    ArrayList<Node> nodes;

    public DirectedAcyclicGraph(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Node> getLCAs(Node target1, Node target2) {

        ArrayList<Node> lCAs = new ArrayList<>();

        colourAncestorsBlue(target1);
        colourAncestorsRed(target2);

        HashMap<Node, Integer> maxDistToTarget1Target2 = new HashMap<>();

        ArrayList<Node> redNodes = getRedNodes();

        for (Node redNode: redNodes) {
            ArrayList<Node> pathToTarget1 = shortestPath(redNode, target1);
            ArrayList<Node> pathToTarget2 = shortestPath(redNode, target2);

            maxDistToTarget1Target2.put(redNode, Math.max(pathToTarget1.size(), pathToTarget2.size()));
        }

        // find the smallest distance
        int minPathLength = Integer.MAX_VALUE;

        for (Map.Entry<Node, Integer> entry : maxDistToTarget1Target2.entrySet()) {
            Node redNode = entry.getKey();
            Integer distance = entry.getValue();

            System.out.println("NODE: "+redNode+"\tDISTANCE: "+distance);

            if (distance < minPathLength) {
                minPathLength = distance;
            }
        }

        for (Map.Entry<Node, Integer> entry : maxDistToTarget1Target2.entrySet()) {
            Node redNode = entry.getKey();
            Integer distance = entry.getValue();

            if (distance == minPathLength) lCAs.add(redNode);
        }

        return lCAs;
    }

    // Returns the length of the shortest path from startNode to endNode via BFS
    public ArrayList<Node> shortestPath(Node startNode, Node endNode) {

        LinkedList<ArrayList<Node>> paths = new LinkedList<>();
        paths.add(new ArrayList<>(Arrays.asList(startNode)));

        while (!paths.isEmpty()) {
            ArrayList<Node> curPath = paths.remove();

            Node lastElem = curPath.get(curPath.size()-1);

            if (lastElem == endNode) {
                return curPath;
            }

            for (Node child: lastElem.getChildren()) {
                ArrayList<Node> newPath = new ArrayList<>(curPath);
                newPath.add(child);

                paths.add(newPath);
            }
        }

        return null;
    }

    // testing a generic BFS algorithm
    public ArrayList<Integer> bfs(Node startNode) {
        LinkedList<Node> q = new LinkedList<>();
        ArrayList<Integer> order = new ArrayList<>();

        q.add(startNode);
        //System.out.println("\nPerforming BFS w starting node "+startNode+"\n");

        while(!q.isEmpty()) {
            //System.out.println("Queue: "+Arrays.toString(q.toArray()));
            Node curNode = q.remove();
            order.add(curNode.getVal());

            //System.out.println("Cur Node: " + curNode);

            for (Node n: curNode.getChildren()) {
                q.add(n);
                //System.out.println("Adding "+n+" to queue");
            }
            //System.out.println();
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

    public void colourAncestorsBlue(Node target) {
        // performs a BFS from each node 'n' to determine if n is an ancestor of 'target'
        // If so, colours 'n' blue.

        // Create a queue containing all the nodes in the graph
        LinkedList<Node> nodesInGraph = new LinkedList<>();
        nodesInGraph.addAll(nodes);

        while (!nodesInGraph.isEmpty()) {
            Node curStartNode = nodesInGraph.remove();

            boolean ancestorOfTarget = bfsForTarget(curStartNode, target);

            if (ancestorOfTarget) {
                curStartNode.setColor(Node.Color.BLUE);
            }
        }
    }

    // colours any BLUE ancestors of the other target node RED if they are ancestors of this 'target'
    public void colourAncestorsRed(Node target) {

        // get the blue nodes
        LinkedList<Node> blueNodes = new LinkedList<>(getBlueNodes());

        // bfs from each blue node and if it can reach 'target' colour it red
        while (!blueNodes.isEmpty()) {

            Node curNode = blueNodes.remove();

            boolean isAncestor = bfsForTarget(curNode, target);

            if (isAncestor) {
                curNode.setColor(Node.Color.RED);
            }

        }

    }

    public ArrayList<Node> getBlueNodes() {

        ArrayList<Node> blueNodes = new ArrayList<>();

        System.out.println("Blue nodes: ");
        for (Node n:nodes) {
            if (n.getColor().toLowerCase() == "blue") {
                System.out.println("\t"+n);
                blueNodes.add(n);
            }
        }

        return blueNodes;

    }

    public ArrayList<Node> getRedNodes() {

        ArrayList<Node> redNodes = new ArrayList<>();

        System.out.println("\nRed nodes: ");
        for (Node n:nodes) {
            if (n.getColor().toLowerCase() == "red") {
                System.out.println("\t"+n);
                redNodes.add(n);
            }
        }

        return redNodes;

    }


    // Sets all nodes' colours back to white
    public void resetColors() {
        for (Node n: nodes) {
            n.setColor(Node.Color.WHITE);
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
