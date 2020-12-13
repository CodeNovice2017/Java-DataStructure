package sort;

public class InsertionSort {
    
    public static void insertionSort(int arr[]) {

        if (arr == null || arr.length < 2) {
            return;
        }
        for(int i = 1;i<arr.length;i++){

            for(int j = i - 1;j >= 0;j--){

                if(arr[j] > arr[j+1]){

                    swap(arr, j, j+1);

                }
            }
        }
    }

    private static void swap(int arr[], int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

}
