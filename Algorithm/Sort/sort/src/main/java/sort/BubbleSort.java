package sort;

public class BubbleSort {

    public static void bubbleSort(int arr[]){

        if(arr == null || arr.length < 2){
            return;
        }
        for(int end = arr.length-1;end > 0;end--){
            // 不会越界,因为i到不了N-1的位置,i只能到end-1即N-2的位置
            for (int i = 0; i < end; i++) {
                if(arr[i] > arr[i+1]){
                    swap(arr,i,i+1);
                }
            }
        }
    }
    private static void swap(int arr[],int index1,int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    } 
    
}
