import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class BinaryTree {

    private BinaryTreeNode root;

    /* Constructors ------------ */

    public BinaryTree(BinaryTreeNode root) {
        this.root = root;
    }

    public BinaryTree(int rootVal) {
        this.root = new BinaryTreeNode(rootVal);
    }

    public BinaryTree() {
        this.root = null;
    }

    /* ------------------------- */

    public BinaryTreeNode root() {
        return this.root;
    }

    public void insert(int val) {

        if (root == null) { // if root of subtree is null
            root = new BinaryTreeNode(val);
            return;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(this.root);
        BinaryTreeNode curBinaryTreeNode;

        while (!queue.isEmpty()) {
            curBinaryTreeNode = queue.peek();
            queue.remove();

            if (curBinaryTreeNode.getlChild() == null) {
//                System.out.println(String.format("setting lChild of %d: %d", curBinaryTreeNode.getVal(), val));
                curBinaryTreeNode.setLChild(val);
//                System.out.println(curBinaryTreeNode.getlChild().getVal());
                break;
            } else queue.add(curBinaryTreeNode.getlChild());

            if (curBinaryTreeNode.getrChild() == null) {
//                System.out.println(String.format("setting rChild of %d: %d", curBinaryTreeNode.getVal(), val));
                curBinaryTreeNode.setRChild(val);
//                System.out.println(curBinaryTreeNode.getrChild().getVal());
                break;
            } else queue.add(curBinaryTreeNode.getrChild());
        }
    }
    public BinaryTreeNode getLowestCommonAncestor(int val1, int val2) {

        ArrayList<BinaryTreeNode> pathToVal1 = new ArrayList<>();
        ArrayList<BinaryTreeNode> pathToVal2 = new ArrayList<>();

        getPathTo(root, val1, pathToVal1);
        getPathTo(root, val2, pathToVal2);

        System.out.println("\n\nPATH1:");
        for (BinaryTreeNode binaryTreeNode : pathToVal1) {
            System.out.print(binaryTreeNode.getVal() + " ");
        }
        System.out.println("\nPATH2:");
        for (BinaryTreeNode binaryTreeNode : pathToVal2) {
            System.out.print(binaryTreeNode.getVal() + " ");
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

    private static boolean getPathTo(BinaryTreeNode root, int val, ArrayList<BinaryTreeNode> curPath) {
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
