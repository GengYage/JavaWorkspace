package henu.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        Node root = new Node(1, "blue");
        Node node2 = new Node(2, "blue1");
        Node node3 = new Node(3, "blue2");
        Node node4 = new Node(4, "blue3");
        Node node5 = new Node(5, "blue4");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
//        System.out.println("前序遍历");
//        binaryTree.perOrder();
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//        System.out.println("后序遍历");
//        binaryTree.postOrder();
        System.out.println("前序查找 ");
        binaryTree.preOrderSearch(5);
        System.out.println("中序查找 ");
        binaryTree.infixOrderSearch(5);
        System.out.println("后序查找 ");
        binaryTree.postOrderSearch(5);


    }
}


class BinaryTree {
    private Node root;
    public void setRoot(Node root) {
        this.root = root;
    }
    //前序遍历
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
}


class Node {
    private int id;
    private String name;
    private Node left;
    private Node right;
    public Node(int id,String name) {
        this.id = id;
        this.name = name;
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
}