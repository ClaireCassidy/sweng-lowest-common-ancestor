import java.util.ArrayList;

public class Node {

    public static enum Color {
        WHITE, RED, BLUE;
    }

    private int val;
    private Color color;
    private int count;
    private ArrayList<Node> children;
    private ArrayList<Node> parents;


    public Node(int val, ArrayList<Node> children, ArrayList<Node> parents) {
        this.val = val;
        this.count = 0;
        this.color = Color.WHITE;
        this.children = children;
        this.parents = parents;
    }

    
}
