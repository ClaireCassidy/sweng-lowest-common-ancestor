//public class BinaryTreeNode<T> {
//
//    private T val;
//
//    public BinaryTreeNode(T val) {
//        this.val = val;
//    }
//
//    public T getVal() {
//        return this.val;
//    }
//
//}

public class BinaryTreeNode {

    private int val;
    private BinaryTreeNode lChild, rChild;

    public BinaryTreeNode(int val) {
        this.val = val;
        this.lChild = null;
        this.rChild = null;
    }

    public int getVal() {
        return this.val;
    }

    public BinaryTreeNode getlChild() {
        return this.lChild;
    }

    public BinaryTreeNode getrChild() {
        return this.rChild;
    }

    public boolean setLChild(int val) {
        if (this.lChild != null) {
            return false;
        }

        this.lChild = new BinaryTreeNode(val);
        return true;
    }

    public boolean setRChild(int val) {
        if (this.rChild != null) {
            return false;
        }

        this.rChild = new BinaryTreeNode(val);
        return true;
    }

}

