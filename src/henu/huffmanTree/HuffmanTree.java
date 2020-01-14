package henu.huffmanTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        perOder(root);

    }
    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size()>1) {
            //Node已经实现了Comparable接口
            Collections.sort(nodes);
            //取出权值最小的二叉树节点
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.value+right.value);
            parent.left = left;
            parent.right = right;
            //删除已经用过的节点
            nodes.remove(left);
            nodes.remove(right);
            //将新树加入
            nodes.add(parent);
        }
        return nodes.get(0);
    }
    public static void perOder(Node root) {
        if(root!=null) {
            root.perOder();
        } else {
            System.out.println("树空无法遍历");
        }
    }

}

//创建节点类
class Node implements Comparable<Node>{
    int value;//权值
    Node left;//左子节点
    Node right;//右子节点
    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node node) {
        //从小到大排序
        return this.value-node.value;
    }
    //前序遍历
    public void perOder() {
        System.out.println(this);
        if(this.left!=null) {
            this.left.perOder();
        }
        if(this.right!=null) {
            this.right.perOder();
        }
    }
}
