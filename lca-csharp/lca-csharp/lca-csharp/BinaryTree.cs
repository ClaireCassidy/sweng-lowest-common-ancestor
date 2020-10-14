using System;
using System.Collections.Generic;
using System.Text;

namespace lca_csharp
{
    class BinaryTree
    {

        private Node root;

        /* Constructors ------------------*/

        public BinaryTree()
        {
            this.root = null;
        }

        public BinaryTree(int val)
        {
            this.root = new Node(val);
        }

        public BinaryTree(Node root)
        {
            this.root = root;
        }

        /* -------------------------------*/
        public void SayHello()
        {
            Console.WriteLine("Hi there");
        }

        public Node GetRoot()
        {
            return this.root;
        }

        public void Insert(int val)
        {
            if (this.root == null)
            {
                this.root = new Node(val);
                return;
            }

            Queue<Node> queue = new Queue<Node>();
            queue.Enqueue(this.root);
            Node curNode;

            while(!(queue.Count == 0))
            {
                curNode = queue.Peek();
                queue.Dequeue();

                if (curNode.GetLChild() == null)
                {
                    Console.WriteLine(String.Format("setting lChild of {0}: {1}", curNode.GetVal(), val));
                    curNode.SetLChild(val);
                    //Console.WriteLine("");
                    break;
                }
                else queue.Enqueue(curNode.GetLChild());

                if (curNode.GetRChild() == null)
                {
                    Console.WriteLine(String.Format("setting `rChild of {0}: {1}", curNode.GetVal(), val));
                    curNode.SetRChild(val);
                    break;
                }
                else queue.Enqueue(curNode.GetRChild());
            }
        }

        public void PrintInOrder()
        {
            Console.WriteLine("\n\nPRINTING\n\n");
            PrintInOrder(this.root);
        }


        private static void PrintInOrder(Node curNode)
        {
            if (curNode == null)
            {
                return;
            }

            PrintInOrder(curNode.GetLChild());
            Console.WriteLine(curNode.GetVal() + " ");
            PrintInOrder(curNode.GetRChild());
        }

        public void PrintInOrderVerbose()
        {
            Console.WriteLine("\n\nPRINTING\n\n");
            PrintInOrderVerbose(this.root);
        }

        private static void PrintInOrderVerbose(Node curNode)
        {
            if (curNode == null)
            {
                Console.WriteLine("It's null");
                return;
            }

            Console.WriteLine(String.Format("It's {0}", curNode.GetVal()));
            Console.WriteLine(String.Format("Going left of {0} ... ", curNode.GetVal()));
            PrintInOrderVerbose(curNode.GetLChild());
            Console.WriteLine(curNode.GetVal() + " ");
            Console.WriteLine(String.Format("Going right of {0} ... ", curNode.GetVal()));
            PrintInOrderVerbose(curNode.GetRChild());
        }

        public Node GetLowestCommonAncestor(int val1, int val2)
        {
            List<Node> pathToVal1 = new List<Node>();
            List<Node> pathToVal2 = new List<Node>();

            GetPathTo(root, val1, pathToVal1);
            GetPathTo(root, val2, pathToVal2);

            Console.WriteLine("\n\nPATH1:");
            foreach (Node node in pathToVal1)
            {
                Console.WriteLine(node.GetVal() + " ");
            }

            Console.WriteLine("\n\nPATH2:");
            foreach (Node node in pathToVal2)
            {
                Console.WriteLine(node.GetVal() + " ");
            }
            Console.WriteLine("\n");

            int i;
            for (i=0; i < pathToVal1.Count && i < pathToVal2.Count; i++)
            {
                if (!pathToVal1[i].Equals(pathToVal2[i])) break;
            }

            return pathToVal1[i - 1];
        }

        public List<Node> GetPathTo(int val)
        {
            List<Node> path = new List<Node>();

            GetPathTo(this.root, val, path);

            return path;
        }

        private static bool GetPathTo(Node root, int val, List<Node> curPath)
        {
            if (root == null) return false;

            curPath.Add(root);

            if (root.GetVal() == val)
            {
                return true;
            }

            if (root.GetLChild() != null && GetPathTo(root.GetLChild(), val, curPath))
            {
                return true;
            }

            if (root.GetRChild() != null && GetPathTo(root.GetRChild(), val, curPath))
            {
                return true;
            }

            curPath.RemoveAt(curPath.Count - 1);
            return false;
        }
    }
}
