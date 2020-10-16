import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MostRecentAncestorTest {

    @Test
    public void testRoot() {
        assertEquals("Binary tree constructor passed null should have a null root",
                new BinaryTree().root(),
                null);
        assertEquals("Binary tree constructor passed a Node should have the root of that node",
                new BinaryTree(new Node(3)).root().getVal(),
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

}