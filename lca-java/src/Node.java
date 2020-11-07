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

    public ArrayList<Node> getParents() {
        return parents;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public int getVal() {
        return val;
    }

    public int getCount() {
        return count;
    }

    public int incrCount() {
        count++;

        return count;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
