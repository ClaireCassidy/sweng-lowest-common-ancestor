import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LowestCommonAncestorTest {

    @Test
    public void testRoot() {
        assertEquals("Binary tree constructor passed null should have a null root",
                new BinaryTree().root(),
                null);
        assertEquals("Binary tree constructor passed a BinaryTreeNode should have the root of that node",
                new BinaryTree(new BinaryTreeNode(3)).root().getVal(),
                3);
        assertEquals("Binary tree constructor passed an arbitrary integer should have that value as its root's value",
                new BinaryTree(6).root().getVal(),
                6);

        BinaryTree testTree = new BinaryTree(5);
        testTree.insert(1);
        testTree.insert(2);
        testTree.insert(3);
        testTree.insert(4);
        assertEquals("Root should not change after an arbitrary number of insertions",
                testTree.root().getVal(),
                5);
    }

    @Test
    public void testGetLChild() {
        BinaryTree testTree = new BinaryTree(5);

        assertEquals("Test that the lchild of node is initially null",
                testTree.root().getlChild(),
                null);

        testTree.insert(4);

        assertEquals("Test that an existing lchild is correctly read",
                testTree.root().getlChild().getVal(),
                4);
    }

    @Test
    public void testGetRChild() {
        BinaryTree testTree = new BinaryTree(5);

        assertEquals("Test that the rChild of node is initially null",
                testTree.root().getrChild(),
                null);

        testTree.insert(5);
        testTree.insert(4);

        assertEquals("Test that an existing rchild is correctly read",
                testTree.root().getrChild().getVal(),
                4);
    }

    @Test
    public void testGetVal() {
        BinaryTree testTree = new BinaryTree(1);

        assertEquals("Test that the correct value for a node is read", testTree.root().getVal(), 1);
    }

    @Test
    public void testSetLChild() {
        BinaryTree testTree = new BinaryTree(5);

        testTree.root().setLChild(6);

        assertEquals("Test the lchild of a node is correctly set", testTree.root().getlChild().getVal(), 6);

        assertEquals("Test trying to set the lchild where it already exists", testTree.root().setLChild(7), false);
    }

    @Test
    public void testSetRChild(){
        BinaryTree testTree = new BinaryTree(5);

        testTree.root().setRChild(6);

        assertEquals("Test the rchild of a node is correctly set", testTree.root().getrChild().getVal(), 6);

    }

    @Test
    public void testInsertInBinaryTree() {
        BinaryTree testTree = new BinaryTree(null);

        testTree.insert(5);
        assertEquals("Test that inserting into an empty binary tree inserts a root",
                testTree.root().getVal(),
                5);

        testTree.insert(6);
        assertEquals("Test that insertion at a node with no children prioritises setting the lchild",
                testTree.root().getlChild().getVal(),
                6);

        testTree.insert(7);
        assertEquals("Test that insertion at a node with one child (will always be an lchild cos no deletion) inserts the new node at the right child",
                testTree.root().getrChild().getVal(),
                7);

        testTree.insert(8);
        assertEquals("Test insertion on a new row inserts in the leftmost position first",
                testTree.root().getlChild().getlChild().getVal(),
                8);

        //                     __[1]__
        //                    /       \
        //                   /         \
        //                [2]           [9]
        //               /   \             \
        //            [3]     [8]           [10]
        //           /   \                 /    \
        //        [4]     [6]          [11]      [12]
        //           \       \        /    \         \
        //            [5]     [7] [13]      [14]      [16]
        //                                 /         /
        //                             [15]      [17]
        testTree = LowestCommonAncestor.generateTestTree();
        testTree.insert(18);

        assertEquals("Test insertion into an arbitrary-shaped binary tree puts the node in the correct spot",
                testTree.root().getrChild().getlChild().getVal(),
                18);
    }

    @Test
    public void testGetLowestCommonAncestor() {
        BinaryTree testTree = LowestCommonAncestor.generateTestTree();

        //                     __[1]__
        //                    /       \
        //                   /         \
        //                [2]           [9]
        //               /   \             \
        //            [3]     [8]           [10]
        //           /   \                 /    \
        //        [4]     [6]          [11]      [12]
        //           \       \        /    \         \
        //            [5]     [7] [13]      [14]      [16]
        //                                 /         /
        //                             [15]      [17]

        assertEquals("Test ancestor of same node",
                testTree.getLowestCommonAncestor(1,1).getVal(),
                1);
        assertEquals("Test where one of the nodes itself is the lowest common ancestor",
                testTree.getLowestCommonAncestor(3,7).getVal(), 3);
        assertEquals("Test arbitrary common ancestor",
                testTree.getLowestCommonAncestor(13,14).getVal(), 11);
        assertEquals("Test case where root is lca",
                testTree.getLowestCommonAncestor(6,17).getVal(), 1);
    }


}