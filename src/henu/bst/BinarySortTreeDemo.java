package henu.bst;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,0};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int value : arr) {
            binarySortTree.add(new Node(value));
        }
        binarySortTree.infixOrder();
        binarySortTree.delNode(10);
        System.out.println("~~~~~~~~~~");
        binarySortTree.infixOrder();
    }
}

class BinarySortTree {
    private Node root;
    public void add(Node node) {
        if(this.root == null) {
            root = node;
        } else {
            this.root.add(node);
        }
    }
    public void infixOrder() {
        if(this.root!=null) {
            this.root.infixOrder();
        } else {
            System.out.println("次bst为空，不能遍历");
        }
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
                    if(parent.left.value==value) { //是其父节点的左子节点
                        parent.left = target.left;
                    } else { //是其父树的右子节点
                        parent.right=target.left;
                    }
                } else { //target右右节点
                    if(parent.left.value==value) { //是其父节点的左子节点
                        parent.left = target.right;
                    } else { //是其父树的右子节点
                        parent.right=target.right;
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