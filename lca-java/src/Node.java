//public class Node<T> {
//
//    private T val;
//
//    public Node(T val) {
//        this.val = val;
//    }
//
//    public T getVal() {
//        return this.val;
//    }
//
//}

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node {

    private int val;
    private Node lChild, rChild;

    public Node(int val) {
        this.val = val;
        this.lChild = null;
        this.rChild = null;
    }

    public int getVal() {
        return this.val;
    }

    public Node getlChild() {
        return this.lChild;
    }

    public Node getrChild() {
        return this.rChild;
    }

    public boolean setLChild(int val) {
        if (this.lChild != null) {
            return false;
        }

        this.lChild = new Node(val);
        return true;
    }

    public boolean setRChild(int val) {
        if (this.rChild != null) {
            return false;
        }

        this.rChild = new Node(val);
        return true;
    }

}

