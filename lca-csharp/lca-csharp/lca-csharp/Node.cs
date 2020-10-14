using System;
using System.Collections.Generic;
using System.Text;

namespace lca_csharp
{
    class Node
    {

        private int val;
        private Node lchild, rchild;
        
        public Node(int val)
        {
            this.val = val;
            this.lchild = null;
            this.rchild = null;
        }

        public int GetVal()
        {
            return this.val;
        }

        public Node GetLChild()
        {
            return this.lchild;
        }

        public Node GetRChild()
        {
            return this.rchild;
        }

        public bool SetLChild(int val)
        {
            if (this.lchild != null)
            {
                return false;
            }

            this.lchild = new Node(val);
            return true;
        }

        public bool SetRChild(int val)
        {
            if (this.rchild != null)
            {
                return false;
            }

            this.rchild = new Node(val);
            return true;
        }
    }
}
