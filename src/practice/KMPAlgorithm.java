package practice;

import java.util.Arrays;

public class KMPAlgorithm {
    //获取部分匹配值
    public static int[] kempNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i=1,j=0;i<dest.length();i++) {
            //KMP算法核心
            while (j>0&&dest.charAt(i)!=dest.charAt(j)) {
                j=next[j-1];
            }
            //部分匹配值加一
            if(dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    /**
     * KMP搜索算法
     * @param source 源字符串
     * @param target 目标字符串
     * @param kmpNext kmpNext
     * @return 返回第一次匹配到的索引或者-1
     */
    public static int kmpSearch(String source, String target, int[] kmpNext) {

        //遍历
        for (int i=0,j=0;i<source.length();i++) {
            //KMP核心
            while (j>0 && source.charAt(i)!=target.charAt(j)) {
                j=kmpNext[j-1];
            }

            if(source.charAt(i) == target.charAt(j)) {
                j++;
            }
            if(j==target.length()) { //表示找到
                return i-j+1;
            }
        }
        return -1;
    }
    //封装
    public static int kmpSearch(String source, String target) {
        if(source.length()<target.length()) {
            return -1;
        } else {
            int[] kmpNext = KMPAlgorithm.kempNext(target);
            return kmpSearch(source, target, kmpNext);
        }
    }

    public static void main(String[] args) {
        System.out.println(kmpSearch("ABC","BC"));
    }
}
