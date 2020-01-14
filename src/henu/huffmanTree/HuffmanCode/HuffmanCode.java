package henu.huffmanTree.HuffmanCode;

import java.util.*;

public class HuffmanCode {

    static Map<Byte,String > huffmanCodes = new HashMap<Byte, String>();
    static StringBuffer stringBuffer = new StringBuffer();

    /**
     *  封装方法
     * @param bytes 需要处理压缩的字节数组
     * @return 压缩后的数组
     */
    private static byte[] huffmanZip(byte[] bytes) {
        //第一步，拿到对应的节点
        List<Node> nodes = getNodes(bytes);
        //第二步，创建huffmanTree
        Node node = creatHuffmanTree(nodes);
        //第三步，生成对应的huffmanCodes生成编码
        Map<Byte, String> huffmanCodes = getCodes(node);
        //根据生成的huffmanCodes压缩
        return encoded(bytes, huffmanCodes);
    }
    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        //遍历存储每一个byte出现的次数
        Map<Byte,Integer> counts = new HashMap<>();
        //统计权重
        for (byte b : bytes) {
            counts.merge(b, 1, Integer::sum);
        }
        //创建node对象
        for(Map.Entry<Byte,Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }
    //通过List创建HuffmanTree
    private static Node creatHuffmanTree(List<Node> nodes) {
        while (nodes.size()>1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, (leftNode.weight + rightNode.weight));
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
    //前序遍历
    public static void preOrder(Node root) {
        if(root == null) {
            System.out.println("树空，不能遍历");
        } else {
            root.preOrder();
        }
    }
    /*
     *生成huffmanCode
     * StringBuilder 存储路径
     */

    //为了调用方便重载此方法
    private static Map<Byte,String> getCodes(Node root) {
        if(root == null) {
            return null;
        }
        getCode(root.left,"0",stringBuffer);
        getCode(root.right,"1",stringBuffer);
        return huffmanCodes;
    }
    /**
     * 将传入的以node为根节点的huffmanTree的huffmanCodes得到
     * @param node 根节点
     * @param code 路径左 0 右1
     * @param stringBuffer 构建路径
     */
    public static void getCode(Node node, String code, StringBuffer stringBuffer) {
        StringBuffer strBuffer = new StringBuffer(stringBuffer);
        strBuffer.append(code);
        if(node != null) {
            //判断当前node是否为叶子节点
            if(node.data==null) { //非叶子节点,
                getCode(node.left,"0",strBuffer);
                getCode(node.right,"1",strBuffer);
            } else { //叶子节点
                huffmanCodes.put(node.data,strBuffer.toString());
            }
        }
    }
    //用huffmanCodes编码
    //8位一个byte，类似于10101000(补码)[推导 10101000 -1 => 10100111(反码) => 11011000(原码) => -88]
    private static byte[] encoded(byte[] bytes,Map<Byte,String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b :
                bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        int len; //字节数组长度
        if(stringBuilder.length()%8 == 0) {
            len = stringBuilder.length()/8;
        } else {
            len = stringBuilder.length()/8 + 1;
        }
        //len = (stringBuilder.length()+7)/8

        //创建临时存储
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        String  strbyte;
        for (int i=0;i<stringBuilder.length();i+=8) {
            if(i+8>stringBuilder.length()) {
                strbyte = stringBuilder.substring(i);
            } else {
                strbyte = stringBuilder.substring(i,i+8);
            }
            //将8位一组的编码转为byte
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strbyte,2);
            index++;
        }
        return huffmanCodeBytes;
    }


    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentByte = content.getBytes();
        byte[] huffmanCodeBytes = huffmanZip(contentByte);
        System.out.println(Arrays.toString(huffmanCodeBytes));
    }
}

class Node implements Comparable<Node> {
    Byte data; //存放数据，字符本身
    int weight; //权值，出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node node) {
        return this.weight-node.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if(this.left!=null) {
            this.left.preOrder();
        }
        if(this.right!=null) {
            this.right.preOrder();
        }
    }
}