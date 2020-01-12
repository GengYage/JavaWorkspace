package henu.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static final int MAX_SIZE = 20;
    /*
     *斐波那契数列相邻的两个数相处随着数值增加越来越接近黄金分割比0.618
     */
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        int res = FibonacciSearch.fibonacciSearch(arr, 89);
        System.out.println(res);
    }
    //斐波那契数列,非递归方式
    public static int[] getFibonacci() {
        int[] fibonacci = new int[MAX_SIZE];
        fibonacci[0] = fibonacci[1] = 1;
        for (int i = 2; i < MAX_SIZE; i++) {
            fibonacci[i] = fibonacci[i-1]+fibonacci[i-2];
        }
        return fibonacci;
    }
    /*
     * @param arr 数组
     * @param key 目标值
     * 用非递归方式
     */
    public static int fibonacciSearch(int[] arr,int key) {
        int low = 0;
        int high = arr.length-1;
        int k = 0;//斐波那契数列分割数值的下标
        int mid = 0;
        int[] fibonacci = getFibonacci();
        //获取分割下标
        /*
         * f[k]-1 k就是分割点
         * f[k]-1 = f[k-1]-1 + f[k-2]+1
         * f[k-1]-1 就是mid值
         */
        while(high > fibonacci[k]-1) {
            k++;
        }
        //f[k]可能大于arr的长度,需要把arr的长度填充到f[k],下面这个方法,不足的地方用0填充
        int[] temp = Arrays.copyOf(arr,fibonacci[k]);
        for (int i = high+1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        //把temp新添加的数值都改成原数组的最后一个值
        while (low <= high) {
            mid = low+fibonacci[k-1]-1;
            if(key < temp[mid]) {
                //左遍历
                high = mid-1;
                k--;
            } else if(key > temp[mid]) {
                //右遍历
                low = mid+1;
                k -= 2;
            } else {
                //说明找到
                return Math.min(mid, high);
            }
        }
        return -1;
    }
}
