import java.util.ArrayList;

public class DirectedAcyclicGraph {

    ArrayList<Node> nodes;

    public DirectedAcyclicGraph() {
        nodes = null;
    }

    public DirectedAcyclicGraph(ArrayList<Node> nodes) {
        this.nodes = nodes;
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


}
