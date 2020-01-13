package henu.tree.threadedBinarytree;


public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        Node root = new Node(1, "blue1");
        Node node2 = new Node(3, "blue2");
        Node node3 = new Node(6, "blue3");
        Node node4 = new Node(8, "blue4");
        Node node5 = new Node(10, "blue5");
        Node node6 = new Node(14, "blue6");

        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);
        //线索化前
        System.out.println(node5.getLeft());
        System.out.println(node5.getRight());
        System.out.println();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println();
        binaryTree.threadedNode();
        //线索化后测试10号节点
        System.out.println(node5.getLeft());
        System.out.println(node5.getRight());
        System.out.println("线索化遍历");
        binaryTree.threadedList();
    }
}


class BinaryTree {
    private Node root;
    //指向前驱节点的指针
    private Node pre = null;
    public void setRoot(Node root) {
        this.root = root;
    }
    public void threadedNode() {
        this.threadedNode(root);
    }
    //线索化
    public void threadedNode(Node node) {
        //node为null，不能线索化
        if(node==null) {
            return;
        }
        //先线索化左子树
        threadedNode(node.getLeft());
        //处理前驱节点
        if(node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后驱节点,其实是下一次才会处理，下一次的时候pre已经指向了该节点
        if(pre!=null && pre.getRight()==null) {
            //前驱节点右指针指向该节点
            pre.setRight(node);
            //修改前驱节点右指针的类型
            pre.setRightType(1);
        }
        //确保pre一直是当前节点的前驱节点
        pre = node;
        //线索化右子树
        threadedNode(node.getRight());
    }

    //线索树的遍历,线性遍历
    public void threadedList() {
        Node node = root;
        while (node!=null) {
            //线索化遍历
            while(node.getLeftType()==0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType()==1) {
                //当前节点的后继节点为其右节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换遍历节点
            node = node.getRight();
        }
    }

    public void perOrder() {
        if(this.root != null) {
            this.root.perOrder();
        } else {
            System.out.println("当前而叉树为空");
        }
    }
    //中序遍历
    public void infixOrder() {
        if(this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("当前而叉树为空");
        }
    }
    //后序遍历
    public void postOrder() {
        if(this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("当前而叉树为空");
        }
    }
    //前序查找
    public void preOrderSearch(int id) {
      Node resNode = null;
        if(this.root != null) {
            resNode=this.root.preOrderSearch(id);
        } else {
            System.out.println("当前树为空");
        }
        System.out.println(resNode);
    }
    //中序查找
    public void infixOrderSearch(int id) {
       Node resNode = null;
        if(this.root != null) {
            resNode=this.root.infixOrderSearch(id);
        } else {
            System.out.println("当前树为空");
        }
        System.out.println(resNode);
    }
    //后序查找
    public void postOrderSearch(int id) {
        Node resNode = null;
        if(this.root != null) {
            resNode=this.root.postOrderSearch(id);
        } else {
            System.out.println("当前树为空");
        }
        System.out.println(resNode);
    }
    //删除
    public void delete(int id) {
        if(this.root!=null) {
            if(root.getId()==id) {
                this.root = null;
            } else {
                this.root.delete(id);
            }
        } else {
            System.out.println("树空，无法删除");
        }
    }
}



class Node {
    private int id;
    private String name;
    private Node left;
    private Node right;
    //leftType 为0 表示指向左子树，为1表示指向前驱节点
    private int leftType;
    private int rightType;
    public Node(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void perOrder() {
        //先输出父节点
        System.out.println(this.toString());
        //递归向左便利
        if(this.left != null) {
            this.left.perOrder();
        }
        if(this.right != null) {
            this.right.perOrder();
        }
    }
    //中序遍历
    public void infixOrder() {
        if(this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this.toString());
        if(this.right != null) {
            this.right.infixOrder();
        }
    }
    public void postOrder() {
        if(this.left!=null){
            this.left.postOrder();
        }
        if(this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this.toString());
    }
    //前序查找
    public Node preOrderSearch(int id) {
        System.out.println("前序查找..");
        if(this.id == id) {
            return this;
        }
       Node resNode = null;
        if(this.left != null) {
            resNode = this.left.preOrderSearch(id);
        }
        if(resNode != null) {
            //坐子树找到了
            return resNode;
        }
        if(this.right != null) {
            resNode = this.right.preOrderSearch(id);
        }
        return resNode;
    }
    //中序遍历查找
    public Node infixOrderSearch(int id) {
        Node resNode = null;
        if(this.left != null) {
            resNode = this.left.infixOrderSearch(id);
        }
        if(resNode != null) {
            return resNode;
        }
        System.out.println("中序查找..");
        if(this.id == id) {
            return this;
        }
        if(this.right != null) {
            resNode = this.right.infixOrderSearch(id);
        }
        return resNode;
    }
    //后序查找
    public Node postOrderSearch(int id) {
       Node resNode = null;
        if(this.left!=null) {
            resNode = this.left.postOrderSearch(id);
        }
        if(resNode != null) {
            return resNode;
        }
        if(this.right != null) {
            resNode = this.right.postOrderSearch(id);
        }
        if(resNode != null) {
            return resNode;
        }
        System.out.println("后序查找..");
        if(this.id == id) {
            return this;
        }
        return null;
    }
    /*递归删除
     *是叶子节点删除节点
     * 不是叶子节点，就删除该子树
     */
    public void delete(int id) {
        //左子节点
        if(this.left!=null && this.left.id==id) {
            this.left = null;
            return;
        }
        //右子节点
        if(this.right!=null && this.right.id==id) {
            this.right = null;
            return;
        }
        //向左子树递归删除
        if(this.left != null) {
            this.left.delete(id);
        }
        //右子树递归删除
        if(this.right!=null) {
            this.right.delete(id);
        }
    }
}