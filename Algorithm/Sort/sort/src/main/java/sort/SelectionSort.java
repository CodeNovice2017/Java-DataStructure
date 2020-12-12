package sort;

public class SelectionSort {
    
    public static void selectionSort(int arr[]){

        if (arr == null || arr.length < 2) {
            return;
        }

        for(int i = 0;i<arr.length - 1;i++){
            int minIndex = i;
            for(int j = i+1;j<arr.length;j++){
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            swap(arr,i,minIndex);
        }
    }
    
    private static void swap(int arr[], int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
