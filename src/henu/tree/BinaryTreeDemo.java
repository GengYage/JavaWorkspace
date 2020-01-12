package henu.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        Node root = new Node(1, "blue");
        Node node2 = new Node(2, "blue1");
        Node node3 = new Node(3, "blue2");
        Node node4 = new Node(4, "blue3");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        binaryTree.setRoot(root);
        System.out.println("前序遍历");
        binaryTree.perOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();
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

}