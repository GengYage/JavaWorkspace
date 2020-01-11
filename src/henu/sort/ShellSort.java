package henu.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        ShellSort shellSort = new ShellSort();
        shellSort.shellSortI(arr);
        arr = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort.shellSortII(arr);
    }

    //交换法
    public void shellSortI(int[] arr) {

        int temp = 0;
        int step = 0;
        for (int gap = arr.length;gap>0;gap/=2) {
            for (int i=gap;i<arr.length;i++) {
                //遍历各组中所有元素
                for (int j=i-gap;j>=0;j-=gap) {
                    if(arr[j] > arr[j+gap]) {
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
            System.out.printf("交换法,第%d轮后~~~~\n"+Arrays.toString(arr)+"\n",step++);
        }

    }
    //移动法
    public void shellSortII(int[] arr) {
        int temp = 0;
        int step = 0;
        for (int gap=arr.length;gap>0;gap/=2) {
            for (int i=gap;i<arr.length;i++) {
                int j=i;
                temp = arr[j];
                if (arr[j] > arr[j-gap]) {
                    while (j-gap>=0 && temp>arr[j-gap]) {
                        //移动,插入排序
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }
                    arr[j] = temp;
                }
            }
            System.out.printf("移位法,第%d轮后~~~~\n"+Arrays.toString(arr)+"\n",step++);
        }
    }
}
