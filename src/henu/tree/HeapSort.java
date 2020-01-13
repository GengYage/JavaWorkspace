package henu.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4,6,8,5,9,-1,90,58,-56,0};
        HeapSort.heapSort(arr);
    }
    public static void heapSort(int[] arr) {
        System.out.println("堆排序");
        int temp;
        //调整为大顶堆
        for (int i=(arr.length/2)-1;i>=0;i--) {
            adjust(arr,i,arr.length);
        }
        //将堆顶元素与为元素交换
        //重新调整结构，然后重复上述步骤
        for (int j=arr.length-1;j>0;j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjust(arr,0,j);
        }

        System.out.println(Arrays.toString(arr));
    }
    //将一个数组（二叉树），调整为大顶堆
    /*
     * arr 需要调整的数组
     * index 表示非叶子节点的索引
     * length 表示堆多少个元素进行调整，length会逐渐减少
     */
    public static void adjust(int[] arr,int index, int length) {
        //先保存非叶子节点
        int temp = arr[index];
        //开始调整
        //i指向的是index的左子节点
        for(int i=(index*2+1);i<length;i=i*2+1) {
            if((i+1<length) && arr[i]<arr[i+1]) { //左子节点小于右子节点
                i++;//i指向右子节点
            }
            if(arr[i] > temp) { //如果子节点大于父节点
                arr[index] = arr[i]; //把较大的值个当前的非叶子节点
                index = i; //!!! index指向i,循环比较
            } else {
                break; //所有的子树都是最大顶堆
            }
        }
        //次for结束后，已经将此子树调整为大顶堆
        arr[index] = temp; //将temp放到其应该在的位置
    }
}
