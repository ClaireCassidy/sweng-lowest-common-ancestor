import java.util.ArrayList;

public class MostRecentAncestor {

    public static void main(String[] args) {

        BinaryTree tree = generateTestTree();
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

        tree.printInOrderVerbose();

        int valueToFind = 12;
        ArrayList<Node> path = tree.getPathTo(8);

        System.out.println("\n\nPATH:");
        for (Node node : path) {
            System.out.print(node.getVal() + " ");
        }

        int val1 = 15;
        int val2 = 17;
        Node lowestCommonAncestor = tree.getLowestCommonAncestor(val1, val2);
        System.out.println("LCA: " + lowestCommonAncestor.getVal());


    }

    private static BinaryTree generateTestTree() {
        BinaryTree tree = new BinaryTree(1);

        tree.insert(2);
        tree.insert(9);
        tree.insert(3);
        tree.insert(8);

        tree.root().getrChild().setRChild(10);
        tree.root().getlChild().getlChild().setLChild(4);
        tree.root().getlChild().getlChild().setRChild(6);
        tree.root().getlChild().getlChild().getlChild().setRChild(5);
        tree.root().getlChild().getlChild().getrChild().setRChild(7);

        tree.root().getrChild().getrChild().setLChild(11);
        tree.root().getrChild().getrChild().setRChild(12);
        tree.root().getrChild().getrChild().setRChild(12);
        tree.root().getrChild().getrChild().getlChild().setLChild(13);
        tree.root().getrChild().getrChild().getlChild().setRChild(14);
        tree.root().getrChild().getrChild().getlChild().getrChild().setLChild(15);

        tree.root().getrChild().getrChild().getrChild().setRChild(16);
        tree.root().getrChild().getrChild().getrChild().getrChild().setLChild(17);

        return tree;
    }
}
