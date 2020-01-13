package henu.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);
        System.out.println("~~~~~~~");
        arrBinaryTree.infixOrder(0);
        System.out.println("~~~~~~");
        arrBinaryTree.postOrder(0);
    }
}

class ArrBinaryTree {
    private int[] arr = null;//存储数据节点的数组
    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }
    //index 数组索引
    public void preOrder(int index) {
        if(this.arr.length == 0) {
            System.out.println("数组空");
            return;
        }
        //输出当前的数组的元素
        System.out.println(arr[index]);
        //向左递归遍历
        if((2*index+1)<this.arr.length) {
            this.preOrder(2*index+1);
        }
        //向右递归遍历
        if((index*2+2)<this.arr.length) {
            this.preOrder(2*index+2);
        }
    }
    //中序遍历
    public void infixOrder(int index){
        if(this.arr.length == 0) {
            System.out.println("数组空");
            return;
        }
        //左遍历
        if((2*index+1)<this.arr.length) {
            this.infixOrder(2*index+1);
        }
        //输出
        System.out.println(arr[index]);
        //右遍历
        if((index*2+2)<this.arr.length) {
            this.preOrder(2*index+2);
        }
    }
    //后序遍历
    public void postOrder(int index){
        if(this.arr.length == 0) {
            System.out.println("数组空");
            return;
        }
        //左遍历
        if((2*index+1)<this.arr.length) {
            this.infixOrder(2*index+1);
        }
        //右遍历
        if((index*2+2)<this.arr.length) {
            this.preOrder(2*index+2);
        }
        //输出
        System.out.println(arr[index]);
    }
}
