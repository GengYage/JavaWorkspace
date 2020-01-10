package henu;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {5,6,9,8,2,4,6,5,2};
        SelectSort selectSort = new SelectSort();
        selectSort.selectSort(arr);
    }

    public void selectSort(int[] arrays) {

        for (int i = 0; i < arrays.length-1; i++) {
            int minIndex = i;
            int min = arrays[i];
            for (int j = minIndex+1; j < arrays.length; j++) {
                if(min>arrays[j]) {
                    min = arrays[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arrays[minIndex] = arrays[i];
                arrays[i] = min;
            }
            System.out.printf("第%d轮后~~~\n",i);
            System.out.println(Arrays.toString(arrays));
        }
    }
}
