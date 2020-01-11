package henu;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {3,1,2,7,9,3,4,5,10,8};
        QuickSort quickSort = new QuickSort();
        System.out.println(Arrays.toString(arr));
        quickSort.quickSort(arr,0,arr.length-1);
    }
    public void quickSort(int[] arr, int left, int right) {
        //递归结束条件
        if (left>right) {
            return;
        }
        int start = left; //左下标
        int end = right; //右下标
        int pivot = arr[(start+end)/2];
        int temp = 0;
        //比pivot小的放到其右边
        while(start < end) {
            while(arr[start] < pivot) {
                start++;
            }
            while(arr[end] > pivot) {
                end--;
            }
            //pivot 左边的已经都比pivot小,右边的都比pivot大,不需要交换
            if(start >= end) {
                break;
            }
            //交换
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            /*
            * 处理遇到跟基准值一样的情况
            * {2,3,2,5,0}
            * 令中间的2为基准值
            * 第一次
            * 2 = 2 start不会自加
            *2和0交换位置
            * {0,3,2,5,2}
            * start(0) < end(4)仍然成立
            * 0<2; start++ = 1,2!>2,end不会自减
            *这时又会把3和2交换
            * {0,2,2,5,3}
            * start=1,end=4
            * start不会变(2!<2)
            * 3>2 end-- =3
            * 5>2 end--=2
            * 2!>2
            * end不会自减 start也不会自加,然而start<end 陷入死循环
            * 下面代码就是解决死循环
            * */
            if(arr[start] == pivot) {
                end--;
            }
            if(arr[end] == pivot) {
                start++;
            }
            /*
            * {2,3,2,5,0}
            * {0,3,2,5,2}
            * 第一次交换后发现 arr[end] == pivot
            * start++ = 1
            * {0,2,2,5,3}
            * 此时 start = 1,end = 4
            * arr[start] == 2,end--=3
            * 下一轮,在交换的前一步
            * end = 2
            * arr[start] == arr[end] == 2
            * end-- =1  start++ = 2
            *此时就能退出循环
            * */
        }
        if(start == end) {
            start++;
            end--;
        }
        System.out.println(Arrays.toString(arr));
        //左递归
        if(left < end) {
            quickSort(arr,left,end);
        }
        //右递归
        if(right > start) {
            quickSort(arr,start,right);
        }

    }
}
