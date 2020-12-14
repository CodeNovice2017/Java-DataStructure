package sort;

public class QuickSort {


    // 荷兰国旗问题
    public static void partition(int arr[],int leftIndex,int rightIndex){

        int lessPart = leftIndex - 1;// 小于区
        int morePart = rightIndex; // 大于区 包含了划分值

        int partitionValue = arr[rightIndex];

        for (int i = leftIndex; i <= rightIndex;) {

            if(arr[i] < partitionValue){
                swap(arr,++lessPart,i++);
            }else if(arr[i] == partitionValue){
                i++;
            }else{
                swap(arr,--morePart,i);
            }
            if(i == morePart){
                swap(arr,morePart,rightIndex);
                break;
            }
        }
    }
    private static void swap(int arr[], int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    } 
}
