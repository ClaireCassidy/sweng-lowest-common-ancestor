import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LowestCommonAncestorDagTest {

    @Test
    public static void testDAG() {

        // Create graph shown in slides:
        //                   [1]
        //                  /   \
        //               [2]     [3]
        //              /           \
        //           [4]             [5]
        //          /               /   \
        //       [6]             [7]     [8]
        //                        |
        //                       [10]
        //                      / |  \
        //                    [9] |   [11]
        //                       [13]     \
        //                                 [12]

        // At this stage, I know my implementation for LCA will not work with DAGs since
        //  1. My node class is designed for binary trees and only permits up to two children
        //  2. My LCA algorithm is embedded into the BinaryTree class, and also is expressed in terms of left
        //      child and right child
        // Therefore there's no point in writing tests at this point since there's no way to express the test
        //  graph. I will have to rewrite my BinaryTreeNode class and add a DAG class.
    }
}
