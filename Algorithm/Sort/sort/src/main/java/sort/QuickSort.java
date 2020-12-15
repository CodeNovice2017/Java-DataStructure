package sort;

public class QuickSort {

    public static void quickSort(int arr[],int leftIndex,int rightIndex){

        if(leftIndex < rightIndex){

            // 不要这次交换,每次都以传入范围的最后一个位置为划分值,那么就是经典快速排序,如果有这行交换就是随机快速排序
            swap(arr,leftIndex + (int)(Math.random()*(rightIndex - leftIndex + 1)),rightIndex);

            int[] result = partition(arr, leftIndex, rightIndex);

            quickSort(arr, leftIndex,result[0]-1);  // result[0]是小于区坐标+1的位置,所以需要减1

            quickSort(arr, result[1]+1, rightIndex); // result[1]是大于区坐标,但是在包含划分值的写法中,大于区最后经过交换结尾的划分值,实际morePart位置就是划分值,morePart+1就是大于区开始坐标

        }

    }
    // 荷兰国旗问题
    public static int[] partition(int arr[],int leftIndex,int rightIndex){

        int lessPart = leftIndex - 1;// 小于区
        int morePart = rightIndex; // 大于区 包含了划分值

        int partitionValue = arr[rightIndex]; // 实际如果大于区采用了包含划分值的方式,那么这个变量不需要创建的,只需要用arr[rightIndex]代替即可,这里添加上便于逻辑的理解

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
        return new int[]{lessPart+1,morePart};
    }
    private static void swap(int arr[], int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    } 
}
