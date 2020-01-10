package henu;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {5,6,9,8,2,4,6,5,2};
        InsertSort insertSort = new InsertSort();
        insertSort.insertSort(arr);
    }

    public void insertSort(int[] arrays) {
        for (int i = 1; i < arrays.length; i++) {
            int insertVal = arrays[i];
            int insertIndex = i-1;

            while(insertIndex >= 0 && insertVal < arrays[insertIndex]) {
                arrays[insertIndex + 1] = arrays[insertIndex];
                insertIndex--;
            }
            arrays[insertIndex+1] = insertVal;
            System.out.printf("第%d排序后~~~\n",i+1);
            System.out.println(Arrays.toString(arrays));
        }
    }
}
