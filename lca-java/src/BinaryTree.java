import java.util.ArrayList;
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
    public Node getLowestCommonAncestor(int val1, int val2) {

        ArrayList<Node> pathToVal1 = new ArrayList<>();
        ArrayList<Node> pathToVal2 = new ArrayList<>();

        getPathTo(root, val1, pathToVal1);
        getPathTo(root, val2, pathToVal2);

        System.out.println("\n\nPATH1:");
        for (Node node : pathToVal1) {
            System.out.print(node.getVal() + " ");
        }
        System.out.println("\nPATH2:");
        for (Node node : pathToVal2) {
            System.out.print(node.getVal() + " ");
        }
        System.out.println("\n");

        int i;
        for (i = 0; i < pathToVal1.size() && i < pathToVal2.size(); i++) {

            // System.out.println(path1.get(i) + " " + path2.get(i));
            if (!pathToVal1.get(i).equals(pathToVal2.get(i)))
                break;
        }

        return pathToVal1.get(i-1);

    }

    private static boolean getPathTo(Node root, int val, ArrayList<Node> curPath) {
        if (root == null) return false;

        curPath.add(root);
        if (root.getVal() == val) {
            return true;
        }

        if (root.getlChild() != null && getPathTo(root.getlChild(), val, curPath)) {
            return true;
        }

        if (root.getrChild() != null && getPathTo(root.getrChild(), val, curPath)) {
            return true;
        }

        curPath.remove(curPath.size() - 1);
        return false;
    }

}
