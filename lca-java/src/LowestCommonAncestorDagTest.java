import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@RunWith(JUnit4.class)
public class LowestCommonAncestorDagTest {

    @Test
    public void testDagBfsGeneric() {
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

        // This prototype BFS is used to implement other specialised BFS for the LCA algorithm
        // The traversal in the specialised BFS is in the same order, just different side effects occur
        // So if this traversal is correct, then the traversal in the specialised BFS's are correct
        DirectedAcyclicGraph testDag = generateTestGraph1();

        // extract a starting node; in this case 1:
        Node startNode = testDag.getNodes().get(0);

        ArrayList<Integer> expectedOrder = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,10,9,13,11,12));
        ArrayList<Integer> actualOrder = testDag.bfs(startNode);

        assertTrue("Testing BFS traverses the graph in the expected order given start node 1",
                expectedOrder.equals(actualOrder));

        // try again with a different node (n5)
        startNode = testDag.getNodes().get(4);

        expectedOrder = new ArrayList<>(Arrays.asList(5,7,8,10,9,13,11,12));
        actualOrder = testDag.bfs(startNode);

        assertTrue("Testing BFS traverses the graph in the expected order given start node 5",
                expectedOrder.equals(actualOrder));

        // and with one more node (n8)
        startNode = testDag.getNodes().get(7);

        expectedOrder = new ArrayList<>(Arrays.asList(8));
        actualOrder = testDag.bfs(startNode);

        assertTrue("Testing BFS traverses the graph in the expected order given start node 8",
                expectedOrder.equals(actualOrder));
    }

    @Test
    public void testDagBfsForTarget() {
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

        // Slightly altered version of the generic BFS algorithm that tests if a 'target' node
        // is a descendent of a 'start node' (i.e. 'startNode' is an ancestor of 'target'

        DirectedAcyclicGraph testDag = generateTestGraph1();

        // Test if n1 is an ancestor of n6
        Node startNode = testDag.getNodeWithValue(1);
        Node target = testDag.getNodeWithValue(6);

        assertTrue("Confirm 1 is an ancestor of 6", testDag.bfsForTarget(startNode, target));
        assertTrue("Confirm 6 is not an ancestor of 1", !testDag.bfsForTarget(target, startNode));

        startNode = testDag.getNodeWithValue(12);
        target = testDag.getNodeWithValue(10);

        assertTrue("Confirm 12 is not an ancestor of 10", !testDag.bfsForTarget(startNode, target));
        assertTrue("Confirm 10 is an ancestor of 12", testDag.bfsForTarget(target, startNode));

        startNode = testDag.getNodeWithValue(8);
        target = testDag.getNodeWithValue(10);

        assertTrue("Confirm 10 is not an ancestor of 8", !testDag.bfsForTarget(startNode, target));
        assertTrue("Confirm 8 is not an ancestor of 10", !testDag.bfsForTarget(target, startNode));
    }

    @Test
    public void testColorAncestorsBlue() {
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

        DirectedAcyclicGraph testDag = generateTestGraph1();

        Node target = testDag.getNodeWithValue(6);

        testDag.colourAncestorsBlue(target);

        ArrayList<Node> actualBlueNodes = testDag.getBlueNodes();
        ArrayList<Node> expectedBlueNodes = new ArrayList<>(Arrays.asList(testDag.getNodeWithValue(1),
                testDag.getNodeWithValue(2), testDag.getNodeWithValue(4)));

        assertTrue("Blue Nodes for target n6 => [n1,n2,n4]",
                expectedBlueNodes.equals(actualBlueNodes));

        testDag.resetColors();

        target = testDag.getNodeWithValue(11);

        testDag.colourAncestorsBlue(target);

        actualBlueNodes = testDag.getBlueNodes();
        expectedBlueNodes = new ArrayList<>(Arrays.asList(testDag.getNodeWithValue(1), testDag.getNodeWithValue(3),
                testDag.getNodeWithValue(5), testDag.getNodeWithValue(7), testDag.getNodeWithValue(10)));

        assertTrue("Blue nodes for target n11 => [n5, n7, n10]",
                expectedBlueNodes.equals(actualBlueNodes));

        testDag.resetColors();
    }


    @Test
    public void testColourAncestorsRed() {
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

        DirectedAcyclicGraph testDag = generateTestGraph1();

        // suppose we had target nodes of n6 and n5
        // Then we call colorAncestorsBlue on n6 and colorAncestorsRed on n5:

        Node target1 = testDag.getNodeWithValue(6);
        Node target2 = testDag.getNodeWithValue(5);

        testDag.colourAncestorsBlue(target1);
        testDag.colourAncestorsRed(target2);

        ArrayList<Node> actualRedNodes = testDag.getRedNodes();
        ArrayList<Node> expectedRedNodes = new ArrayList<>(Arrays.asList(testDag.getNodeWithValue(1)));

        assertTrue("Test that the red nodes for the graph using target1 = n6, target2 = n5 = [n1]",
                actualRedNodes.equals(expectedRedNodes));

        testDag.resetColors();

        target1 = testDag.getNodeWithValue(8);
        target2 = testDag.getNodeWithValue(9);

        testDag.colourAncestorsBlue(target1);
        testDag.colourAncestorsRed(target2);

        actualRedNodes = testDag.getRedNodes();
        expectedRedNodes = new ArrayList<>(Arrays.asList(testDag.getNodeWithValue(1), testDag.getNodeWithValue(3),
                testDag.getNodeWithValue(5)));

        assertTrue("Test that the red nodes for the graph using target1 = n8, target2 = n9 => [n1, n3, n5]",
                actualRedNodes.equals(expectedRedNodes));

        testDag.resetColors();
    }

    @Test
    public void testAdjustCount() {
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

        DirectedAcyclicGraph testDag = generateTestGraph1();

        // Get the counts of red ancestors for target1 = n5, target2 = n6
        Node target1 = testDag.getNodeWithValue(5);
        Node target2 = testDag.getNodeWithValue(6);

        testDag.colourAncestorsBlue(target1);         // color ancestors of n5 blue
        testDag.colourAncestorsRed(target2);        // color shared ancestors of n5 and n6 red
        testDag.adjustCount();                      // increment each red node's parents' counts by 1

        ArrayList<Node> redNodes = testDag.getRedNodes();

        HashMap<Node, Integer> actualRedNodeCount = new HashMap<>();
        for (Node n: redNodes) {
            actualRedNodeCount.put(n, n.getCount());
        }

        HashMap<Node, Integer> expectedRedNodeCount = new HashMap<>();
        expectedRedNodeCount.put(testDag.getNodeWithValue(1), 0);

        assertTrue("Check the red nodes have expected count for t1 = n5, t2 = n6 => [N1:0]",
                actualRedNodeCount.equals(expectedRedNodeCount));

        // reset the graph
        testDag.resetColors();
        testDag.resetCounts();

        // Test again with target1 = n4, target2 = n6
        target1 = testDag.getNodeWithValue(4);
        target2 = testDag.getNodeWithValue(6);

        testDag.colourAncestorsBlue(target1);
        testDag.colourAncestorsRed(target2);
        testDag.adjustCount();

        redNodes = testDag.getRedNodes();

        actualRedNodeCount = new HashMap<>();
        for (Node n: redNodes) {
            actualRedNodeCount.put(n, n.getCount());
        }

        expectedRedNodeCount = new HashMap<>();
        expectedRedNodeCount.put(testDag.getNodeWithValue(2), 0);
        expectedRedNodeCount.put(testDag.getNodeWithValue(1), 1);

        assertTrue("Check the red nodes have expected count for t1 = n4, t2 = n6 => [N2:0, N1:1]",
                actualRedNodeCount.equals(expectedRedNodeCount));
    }

    public static DirectedAcyclicGraph generateTestGraph1() {
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

        // create the nodes
        Node n1     = new Node(1, null, null);
        Node n2     = new Node(2, null, null);
        Node n3     = new Node(3, null, null);
        Node n4     = new Node(4, null, null);
        Node n5     = new Node(5, null, null);
        Node n6     = new Node(6, null, null);
        Node n7     = new Node(7, null, null);
        Node n8     = new Node(8, null, null);
        Node n9     = new Node(9, null, null);
        Node n10    = new Node(10, null, null);
        Node n11    = new Node(11, null, null);
        Node n12    = new Node(12, null, null);
        Node n13    = new Node(13, null, null);

        // create their relationships
        n1.addChild(n2);
        n1.addChild(n3);

        n2.addParent(n1);
        n2.addChild(n4);

        n3.addParent(n1);
        n3.addChild(n5);

        n4.addParent(n2);
        n4.addChild(n6);

        n5.addParent(n3);
        n5.addChild(n7);
        n5.addChild(n8);

        n6.addParent(n4);

        n7.addParent(n5);
        n7.addChild(n10);

        n8.addParent(n5);

        n9.addParent(n10);

        n10.addParent(n7);
        n10.addChild(n9);
        n10.addChild(n13);
        n10.addChild(n11);

        n11.addParent(n10);
        n11.addChild(n12);

        n12.addParent(n11);

        n13.addParent(n10);

        ArrayList<Node> dagNodes = new ArrayList<>(Arrays.asList(n1, n2, n3, n4, n5, n6, n7,
                                                                    n8, n9, n10, n11, n12, n13));

        return new DirectedAcyclicGraph(dagNodes);
    }

//    public static void main(String[] args) {
//        DirectedAcyclicGraph dag = generateTestGraph1();
//
//        dag.printGraph();
//        bfs(dag.getNodes().get(0));
//    }
}
