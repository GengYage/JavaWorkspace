package practice;

import java.util.Arrays;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3};//物品重量
        int[] val = {1500,2000,3000}; //物品价值
        int m = 4;//背包容量
        int n = val.length; //物品个数
        int[][] v = new int[n+1][m+1]; //创建二位数组，表
        int[][] path = new int[n+1][m+1];
        for (int i=0;i<v.length;i++) {
            v[i][0] = 0; //第一列置为0
        }
        for (int i=0;i<v[0].length;i++) {
            v[0][i] = 0; //第一行置0
        }



        for (int i=1;i<v.length;i++) {
            for (int j=1;j<v[0].length;j++) {
                if(w[i-1]>j) {
                    v[i][j] = v[i-1][j];
                } else {
                    //v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    if(v[i-1][j] < val[i-1] + v[i-1][j-w[i-1]]) {
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }
        for (int[] value : v) {
            System.out.println(Arrays.toString(value));
        }
        for (int[] value : path) {
            System.out.println(Arrays.toString(value));
        }
    }
}
