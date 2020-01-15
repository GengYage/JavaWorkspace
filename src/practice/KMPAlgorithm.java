package practice;

import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = KMPAlgorithm.kempNext(str2);
        int i = KMPAlgorithm.kmpSearch(str1, str2, next);
        System.out.println(i);
    }
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
}
