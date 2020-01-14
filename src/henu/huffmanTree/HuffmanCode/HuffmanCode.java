package henu.huffmanTree.HuffmanCode;

import java.io.*;
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
    /**
     * 将一个byte转换成二进制
     * @param flag 标识是否需要补齐高位，true 需要，false不需要
     * @param b 传入的byte
     * @return 该byte的二进制补码
     */
    private static String byteToBit(boolean flag, byte b) {
        int temp = b; //byte 转int
        //如果是正数需要补高位
        if(flag) {
            temp |= 256; //按位或[256 => 1 0000 0000] | [1 => 0000 0001] = [1 0000 0000 0000]
        }
        String string = Integer.toBinaryString(temp);
        if (flag||temp<0) {
            return string.substring(string.length() - 8);
        } else {
            return string;
        }
    }
    //解码

    /**
     * huffman解码
     * @param huffmanCodes huffman编码表
     * @param huffmanBytes 经过哈夫曼编码的字节数组
     * @return 解码后的字节
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i=0;i<huffmanBytes.length;i++) {
            boolean flag = (i==huffmanBytes.length-1);
            stringBuffer.append(byteToBit(!flag,huffmanBytes[i]));
        }
        //把字符串安装huffmanCode进行解码
        Map<String ,Byte> map = new HashMap<String ,Byte>();
        //解码表
        for (Map.Entry<Byte,String > entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(),entry.getKey());
        }
        //创建集合存byte
        List<Byte> list = new ArrayList<Byte>();
        for (int i=0;i<stringBuffer.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key =  stringBuffer.substring(i,i+count);
                b = map.get(key);
                if(b==null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        //list --> byte[]
        byte[] bt = new byte[list.size()];
        for (int i=0;i<bt.length;i++) {
            bt[i] = list.get(i);
        }
        return bt;
    }

    /**
     * 文件压缩
     * @param srcFile 源文件路径
     * @param dstFile 压缩后路径
     */
    public static void zipFile(String srcFile, String dstFile) {
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try{
            is = new FileInputStream(srcFile);
            byte[] b = new byte[is.available()];
            is.read(b);
            //压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件输出流
            os = new FileOutputStream(dstFile);
            //创建和文件关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //将huffman编码后的字节写入文件
            oos.writeObject(huffmanBytes);
            //对象流写入huffman编码，解压时方便
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert oos != null;
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //文件解压
    public static void unzipFile(String srcFile,String dstFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream(srcFile);
            ois = new ObjectInputStream(is);
            //读取byte,和编码表
            byte[] huffmanByte = (byte[])ois.readObject();
            Map<Byte,String> map = (Map<Byte,String>)ois.readObject();
            byte[] decode = decode(map, huffmanByte);
            os = new FileOutputStream(dstFile);
            os.write(decode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert os != null;
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentByte = content.getBytes();
        byte[] huffmanCodeBytes = huffmanZip(contentByte);
        System.out.println("编码后"+Arrays.toString(huffmanCodeBytes));

        byte[] decode = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("解码后"+Arrays.toString(decode));
        System.out.println("解码后字符串"+new String(decode));
        //测试压缩文件
//        String srcFile = "";
//        String dstFile = "";
//        zipFile(srcFile,dstFile);
//        System.out.println("压缩成功");
//        unzipFile(dstFile,"");
//        System.out.println("解压成功");
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