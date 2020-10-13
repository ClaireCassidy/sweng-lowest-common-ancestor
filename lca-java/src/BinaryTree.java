import java.util.Queue;
import java.util.LinkedList;

public class BinaryTree {

    private Node root;

    /* Constructors ------------ */

    public BinaryTree(Node root) {
        this.root = root;
    }

    public BinaryTree(int rootVal) {
        this.root = new Node(rootVal);
    }

    public BinaryTree() {
        this.root = null;
    }

    /* ------------------------- */

    public Node root() {
        return this.root;
    }

    public void insert(int val) {

        if (root == null) { // if root of subtree is null
            root = new Node(val);
            return;
        }

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(this.root);
        Node curNode;

        while (!queue.isEmpty()) {
            curNode = queue.peek();
            queue.remove();

            if (curNode.getlChild() == null) {
//                System.out.println(String.format("setting lChild of %d: %d", curNode.getVal(), val));
                curNode.setLChild(val);
//                System.out.println(curNode.getlChild().getVal());
                break;
            } else queue.add(curNode.getlChild());

            if (curNode.getrChild() == null) {
//                System.out.println(String.format("setting rChild of %d: %d", curNode.getVal(), val));
                curNode.setRChild(val);
//                System.out.println(curNode.getrChild().getVal());
                break;
            } else queue.add(curNode.getrChild());
        }
    }

    public void printInOrder() {
        System.out.println("\n\nPRINTING:\n\n");
        printInOrder(this.root);
    }

    private static void printInOrder(Node curNode) {
        if (curNode == null) {
            return;
        }

        printInOrder(curNode.getlChild());
        System.out.print(curNode.getVal() + " ");
        printInOrder(curNode.getrChild());
    }

    public void printInOrderVerbose() {
        System.out.println("\n\nPRINTING:\n\n");
        printInOrderVerbose(this.root);
    }

    private static void printInOrderVerbose(Node curNode) {
        if (curNode == null) {
            System.out.println("It's null");
            return;
        }

        System.out.println(String.format("It's %d", curNode.getVal()));
        System.out.println(String.format("Going left of %d ... ", curNode.getVal()));
        printInOrderVerbose(curNode.getlChild());
        System.out.print(curNode.getVal() + " ");
        System.out.println(String.format("Going right of %d ... ", curNode.getVal()));
        printInOrderVerbose(curNode.getrChild());
    }

    public Node getMostRecentAncestor(int val1, int val2) {
        return null;
    }

}
