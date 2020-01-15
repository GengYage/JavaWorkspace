package henu.graph;

import com.sun.java.swing.plaf.windows.WindowsDesktopIconUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList; //存储定点集合
    private int[][] edges; //邻接矩阵
    private int numOfEdges; //边的数目
    private boolean[] isVisited; //定义数组，记录某个节点是否被访问


    //构造器
    public Graph(int n) {
        this.edges = new int[n][n];
        this.vertexList = new ArrayList<String>(n);
        this.numOfEdges = 0;
        this.isVisited = new boolean[n];
    }
    //插入节点
    public void insertVertex(String vertex) {
        this.vertexList.add(vertex);
    }
    /**
     * 添加边
     * @param v1 表示第一个点的下标
     * @param v2 表示第二个点的下标
     * @param weight 表示v1 v2间的路径的权值 0表示不连通，1表示连通
     */
    public void insertEdge(int v1, int v2, int weight) {
        this.edges[v1][v2] = weight;
        this.edges[v2][v1] = weight;
        numOfEdges++;
    }
    //得到点的个数
    public int getNumOfVertex() {
        return this.vertexList.size();
    }
    //得到边的个数
    public int getNumOfEdges() {
        return this.numOfEdges;
    }
    //返回节点对应的数据
    public String getValue(int index) {
        return vertexList.get(index);
    }
    //返回v1 v2 的权值
    public int getWeight(int v1, int v2) {
        return this.edges[v1][v2];
    }
    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 得到第一个邻接定点的下标
     * @param index 需要获取邻接节点的节点下标
     * @return 如果存在就返回下标，否则返回-1
     */
    public int getFirstAdjacency(int index) {
        for (int j=0;j<vertexList.size();j++) {
            if(edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻接节点得到下一个邻接节点
    public int getNextAdjacency(int v1, int v2) {
        for (int j=v2+1;j<vertexList.size();j++) {
            if(edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }
    //深度优先
    private void dfs(boolean[] isVisited, int index) {
        System.out.print("-> " + getValue(index));
        isVisited[index] = true;
        //查找节点index的邻接节点
        int w = getFirstAdjacency(index);
        while (w!=-1) { //有邻接节点
            if(!isVisited[w]) {
                dfs(isVisited,w);
            }
            w = getNextAdjacency(index, w);
        }

    }
    //对dfs重载,遍历所有的节点
    public void dfs() {
        isVisited = new boolean[isVisited.length];
        for (int i=0;i<getNumOfVertex();i++) {
            if(!isVisited[i]) {
                dfs(isVisited,i);
            }
        }
    }
    //广度优先遍历
    private void bfs(boolean[] isVisited,int index) {
        int u; //队列头节点下标
        int w; //邻接节点下标
        LinkedList<Integer> queue = new LinkedList<Integer> ();
        //访问节点
        System.out.print("-> "+getValue(index));
        isVisited[index] = true;
        //入队
        queue.addLast(index);
        while (!queue.isEmpty()) {
            //取出队列头节点下表
            u = queue.removeFirst();
            w = getFirstAdjacency(u);
            while (w!=-1) {
                if(!isVisited[w]) { //没有访问过
                    System.out.print("-> "+getValue(w));
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextAdjacency(u,w); //体现出广度优先
            }
        }
    }

    //遍历所有的节点进行广度优先搜索
    public void bfs() {
        isVisited = new boolean[isVisited.length];
        for (int i=0;i<getNumOfVertex();i++) {
            if(!isVisited[i]) {
                bfs(isVisited,i);
            }
        }
    }

    public static void main(String[] args) {
        int n = 8;//节点个数
        String[] Vertex = {"1","2","3","4","5","6","7","8"};
        Graph graph = new Graph(n);
        for (String vertex : Vertex) {
            graph.insertVertex(vertex);
        }
        //A-B A-C B-C B-D B-E
//        graph.insertEdge(0,1,1);
//        graph.insertEdge(0,2,1);
//        graph.insertEdge(1,2,1);
//        graph.insertEdge(1,3,1);
//        graph.insertEdge(1,4,1);
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(2,6,1);
        graph.insertEdge(5,6,1);
        graph.showGraph();
        System.out.println("深度优先");
        graph.dfs();
        System.out.println();
        System.out.println("广度优先");
        graph.bfs();
    }
}
