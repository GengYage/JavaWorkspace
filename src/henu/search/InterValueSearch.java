package henu.search;

/**
 * 插值查找(只)关键字分布均匀,分布连续的数组查找
 */
public class InterValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i+1;
        }
        int resI = InterValueSearch.interValueSearch(arr, 0, arr.length - 1, 56);
        System.out.println(resI);
        int resII = BinarySearch.binarySearchI(arr, 0, arr.length - 1, 56);
        System.out.println(resII);
    }
    public static int interValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("我被调用了,我是插值");
        if(left > right) {
            return -1;
        }
        /*
        *插值算法的核心,剩下的和二分一样
        *就是原长度*(所查找的值与最左侧的值的距离/最左侧与最右侧的距离)
        */
        int mid = left + (right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
            //右递归
            return interValueSearch(arr,mid+1,right,findVal);
        } else if(findVal < midVal) {
            //左递归
            return interValueSearch(arr,left,mid-1,findVal);
        } else {
            return mid;
        }
    }
}
