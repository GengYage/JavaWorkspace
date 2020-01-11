package henu;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        MergeSort mergeSort = new MergeSort();
        int[] temp = new int[arr.length];
        mergeSort.mergeSort(arr,0,arr.length-1, temp);
        System.out.println(Arrays.toString(arr));
    }
    //分解
    public void mergeSort(int[] arr,int left,int right, int[] temp) {
        if(left<right) {
            int mid = (left+right)/2;
            mergeSort(arr,left,mid,temp);
            mergeSort(arr,mid+1,right,temp);
            merge(arr,left,mid,right,temp);
        }
    }
    //合并
    public void merge(int[] arr, int left, int mid,int right, int[] temp) {
        int i = left; //左边有序序列的初始索引
        int j = mid+1; //右边有序序列的初始索引
        int t = 0; //临时数组的索引

        while (i<=mid && j<=right) {
            if(arr[i]<=arr[j]) {
                temp[t] = arr[i];
                i++;
            } else {
                temp[t] = arr[j];
                j++;
            }
            t++;
        }
        //有一边有剩余
        while(j<=right) {
            temp[t] = arr[j];
            t++;
            j++;
        }
        while(i<=mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }

        //将数据拷贝回原数组
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
