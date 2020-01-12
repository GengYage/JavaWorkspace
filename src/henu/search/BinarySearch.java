package henu.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1000,1000,1000,1234}; //二分查找,必须有序,可以先排序再查找
        //System.out.println(BinarySearch.binarySearchI(arr,0,arr.length-1,50));;
        List<Integer> resIndexList = BinarySearch.binarySearchII(arr,0,arr.length-1,1000);
        System.out.println(resIndexList);
    }
    /*
     *查找到findVal 立马返回
     *一次仅能查找一个
     */
    public static int binarySearchI(int[] arr, int left, int right, int findVal) {
        System.out.println("我被调用了,我是二分");
        if(left > right) {
            return -1;
        }

        int mid = (right+left)/2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            //右递归
            return binarySearchI(arr,mid+1,right,findVal);
        } else if(findVal < midVal) {
            //左递归
            return binarySearchI(arr,left,mid-1,findVal);
        } else {
            return mid;
        }
    }
    /*
     *查找到findVal不立马返回,
     */

    public static ArrayList<Integer> binarySearchII(int[] arr, int left, int right, int findVal) {
        if(left > right) {
            return new ArrayList<Integer>();
        }

        int mid = (right+left)/2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            //右递归
            return binarySearchII(arr,mid+1,right,findVal);
        } else if(findVal < midVal) {
            //左递归
            return binarySearchII(arr,left,mid-1,findVal);
        } else {
            //最后进入这个的都是找到了要查找的数,但是并不确定他周围的数是不是目标值
            ArrayList<Integer> resIndexList = new ArrayList<Integer>();
            int temp = mid -1;
            //mid左扫描
            while (true) {
                //退出条件,已经查找到最左边(当前的),或者这个数不是要查找的数
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp--;//temp左移
            }
            resIndexList.add(mid);//找到的mid
            //mid右扫描
            temp = mid+1;
            while (true) {
                if(temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp++;//temp左移
            }
            return resIndexList;
        }
    }
}
