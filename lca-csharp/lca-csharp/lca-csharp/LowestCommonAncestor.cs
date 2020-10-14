using System;
using System.Collections.Generic;
using System.Text;

namespace lca_csharp
{
    class LowestCommonAncestor
    {
        static void Main(string[] args)
        {
            //Console.WriteLine("Hello World!!!!!!!!!!!!!");

            //BinaryTree myTree = new BinaryTree();
            //myTree.SayHello();

            //Node myNode = new Node(3);
            //Console.WriteLine("Node val: " + myNode.GetVal());

            //myNode.SetLChild(5);
            //myNode.SetRChild(6);

            //Console.WriteLine("Node lchild: " + myNode.GetLChild().GetVal());
            //Console.WriteLine("Node rchild: " + myNode.GetRChild().GetVal());

            //myTree = new BinaryTree(40);
            //Console.WriteLine("root: " + myTree.GetRoot().GetVal());

            BinaryTree tree = GenerateTestTree();
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
            tree.PrintInOrderVerbose();

            int valueToFind = 12;
            List<Node> path = tree.GetPathTo(valueToFind);

            Console.WriteLine("\n\nPATH:");
            foreach (Node node in path)
            {
                Console.WriteLine(node.GetVal() + " ");
            }

            int val1 = 6;
            int val2 = 3;
            Node lowestCommonAncestor = tree.GetLowestCommonAncestor(val1, val2);
            Console.WriteLine("LCA: " + lowestCommonAncestor.GetVal());
        }


        private static BinaryTree GenerateTestTree()
        {
            BinaryTree tree = new BinaryTree(1);

            tree.Insert(2);
            tree.Insert(9);
            tree.Insert(3);
            tree.Insert(8);

            tree.GetRoot().GetRChild().SetRChild(10);
            tree.GetRoot().GetLChild().GetLChild().SetLChild(4);
            tree.GetRoot().GetLChild().GetLChild().SetRChild(6);
            tree.GetRoot().GetLChild().GetLChild().GetLChild().SetRChild(5);
            tree.GetRoot().GetLChild().GetLChild().GetRChild().SetRChild(7);

            tree.GetRoot().GetRChild().GetRChild().SetLChild(11);
            tree.GetRoot().GetRChild().GetRChild().SetRChild(12);
            tree.GetRoot().GetRChild().GetRChild().SetRChild(12);
            tree.GetRoot().GetRChild().GetRChild().GetLChild().SetLChild(13);
            tree.GetRoot().GetRChild().GetRChild().GetLChild().SetRChild(14);
            tree.GetRoot().GetRChild().GetRChild().GetLChild().GetRChild().SetLChild(15);

            tree.GetRoot().GetRChild().GetRChild().GetRChild().SetRChild(16);
            tree.GetRoot().GetRChild().GetRChild().GetRChild().GetRChild().SetLChild(17);

            return tree;
        }
    }
}
