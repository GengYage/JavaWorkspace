package henu.avl;

import com.sun.crypto.provider.PBEWithMD5AndDESCipher;

public class AvlTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};
        //int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();
        for (int value : arr) {
            avlTree.add(new Node(value));
        }
        avlTree.infixOrder();
        System.out.printf("当前AVL的高度为%d\n",avlTree.getRoot().height());
        System.out.printf("当前AVL的左子树高度为%d\n",avlTree.getRoot().left.height());
        System.out.printf("当前AVL的右子树高度为%d\n",avlTree.getRoot().right.height());
        //System.out.println(avlTree.getRoot().right.right);
    }

}



class AVLTree {
    private Node root;
    public void add(Node node) {
        if(this.root == null) {
            root = node;
        } else {
            this.root.add(node);
        }
    }
    //中序遍历
    public void infixOrder() {
        if(this.root!=null) {
            this.root.infixOrder();
        } else {
            System.out.println("次bst为空，不能遍历");
        }
    }
    public Node getRoot() {
        return root;
    }
    //查找要删除的节点
    public Node search(int value) {
        if(root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }
    //查找其父节点
    public Node searchParent(int value) {
        if(root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 删除最小右节点
     * @param node 当作二叉排序树的根节点
     * @return 以node为根节点的最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //找最小值
        while (target.left!=null) {
            target = target.left;
        }
        //删除当前子树的最小节点
        delNode(target.value);
        //返回当前子树最小值（已经被删除）
        return target.value;
    }
    //删除节点
    public void delNode(int value) {
        if(root == null) {
            return;
        } else {
            Node target = search(value);
            if(target==null) {
                return;
            }
            //如果当前二叉排序树只有一个节点
            if(root.left==null && root.right==null) {
                root = null;
                return;
            }
            //查找其父节点
            Node parent = searchParent(value);

            //该节点为叶子节点
            if(target.left==null && target.right==null){
                //target是其父节点的左节点
                if(parent.left!=null && parent.left==target) {
                    parent.left = null;
                } else if(parent.right!=null && parent.right==target) { //是右子节点
                    parent.right = null;
                }
            } else if(target.left!= null && target.right!=null) { //两个子树
                target.value = delRightTreeMin(target.right);
            } else { //只有一个子树
                if(target.left!=null) { //有左子节点
                    if(parent!=null) {
                        if(parent.left.value==value) { //是其父节点的左子节点
                            parent.left = target.left;
                        } else { //是其父树的右子节点
                            parent.right=target.left;
                        }
                    } else {
                        root = target.left;
                    }
                } else { //target右右节点
                    if(parent!=null) {
                        if(parent.left.value==value) { //是其父节点的左子节点
                            parent.left = target.right;
                        } else { //是其父树的右子节点
                            parent.right=target.right;
                        }
                    } else {
                        root = target.right;
                    }
                }
            }

        }
    }
}

class Node {
    int value;
    Node left;
    Node right;
    public Node(int value) {
        this.value = value;
    }

    //左旋转
    //去掉this.right
    public void leftRotate() {
        Node newNode = new Node(value);
        //新节点的左子树设置为当前树的左子树
        newNode.left = this.left;
        //新节点的右子树设置为当前子树的右子树的左子树
        newNode.right = this.right.left;
        //把当前节点的值替换成右子节点的值
        this.value = this.right.value;
        //把当前节点的右子树设置为其右子树的右子树
        this.right = this.right.right;
        //把当前节点的左子树，设置为新的节点
        this.left = newNode;
    }
    //右旋转
    //去掉this.left
    public void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;
    }
    //返回左子树高度
    public int leftHeigh() {
        if(left==null) {
            return 0;
        }
        return left.height();
    }
    //返回右子树高度
    public int rightHeigh() {
        if(right==null) {
            return 0;
        }
        return right.height();
    }
    //返回当前节点的高度
    public int height() {
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }

    //添加节点
    public void add(Node node) {
        if(node == null) {
            return;
        }
        if(node.value<this.value) {
            if(this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if(this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        //当添加节点时，判断高度差,左旋转
        if(rightHeigh()-leftHeigh() > 1) {
            //如果其右子树的左子树的高度大于其右子树的右子树的高度
            if(this.right!=null && this.right.leftHeigh()>this.right.leftHeigh()) {
                //先对其右子树右旋转
                this.right.rightRotate();
            }
            leftRotate();
            return;
        }
        if(leftHeigh()-rightHeigh() > 1) { //右旋转
            //如果其左子树的右子树的高度大于其左子树的高度
            if(this.left!=null && this.left.rightHeigh()>this.left.leftHeigh()) {
                //先对其左子树进行左旋转
                this.left.leftRotate();
            }
            rightRotate();
        }
    }

    /**
     * 查找要删除的节点
     * @param value 希望删除的节点的值
     * @return 如果找到就返回，否则返回null
     */
    public Node search(int value) {
        if(value == this.value) {
            return this;
        } else if(value < this.value) {
            if(this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if(this.right==null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     * @param value 要删除节点的值
     * @return 返回其父节点或者null
     */
    public Node searchParent(int value) {
        if((this.left!=null && this.left.value==value)
                || (this.right!=null&&this.right.value==value)){
            return this;
        } else if(value<this.value && this.left!=null) {
            return this.left.searchParent(value);
        } else if(value>=this.value && this.right!=null) {
            return this.right.searchParent(value);
        } else {
            return null;
        }
    }
    //中序遍历
    public void infixOrder(){
        if(this.left!=null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}