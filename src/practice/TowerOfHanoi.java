package practice;

public class TowerOfHanoi {


    /**
     * 分治算法
     * @param num 盘的数量
     * @param a A塔
     * @param b B塔
     * @param c C塔
     */
    public static void towerOfHanoi(int num, char a, char b, char c) {
        //如果只有一个盘
        if(num==1) {
            System.out.println("第1个盘从"+a+"->"+c);
        } else {
            //如果n>=2，可以看作是两个盘，1.最下边一个盘 2.上面所有盘
            //1.先把上面所有盘从A->B,移动过程会使用到C
            towerOfHanoi(num-1,a,c,b);
            //2.最下边的盘A->C
            System.out.println("第"+num+"个盘从"+a+"->"+c);
            //3.把B塔的所有盘从B->C
            towerOfHanoi(num-1,b,a,c);
        }
    }

    public static void main(String[] args) {
        towerOfHanoi(5,'A','B', 'C');
    }
}
