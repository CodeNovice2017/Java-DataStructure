package sort.Example;

public class SmallSum {

    public static int smallSumByMergeSort(int arr[]){

        if (arr == null || arr.length < 2) {
            return 0;
        }
        return sortProcess(arr, 0, arr.length - 1);

    }

    private static int sortProcess(int arr[],int leftIndex,int rightIndex){
        if (leftIndex == rightIndex) {
            return 0;
        }
        int mid = leftIndex + ((rightIndex - leftIndex) >> 1); // L与R中点的位置

        return sortProcess(arr, leftIndex, mid) + sortProcess(arr, mid + 1, rightIndex) + merge(arr, leftIndex, mid, rightIndex);
    } 

    private static int merge(int arr[],int leftIndex,int mid,int rightIndex){

        int[] tempArray = new int[rightIndex - leftIndex + 1];

        int leftPointer = leftIndex;
        int rightPointer = mid + 1;
        int index = 0;
        int smallSum = 0;

        while (leftPointer <= mid && rightPointer <= rightIndex) {
            smallSum = smallSum + (arr[leftPointer] < arr[rightPointer] ? (rightIndex-rightPointer+1)*arr[leftPointer] : 0);
            tempArray[index++] = arr[leftPointer] < arr[rightPointer] ? arr[leftPointer++] : arr[rightPointer++];
        }
        while (leftPointer <= mid) {
            tempArray[index++] = arr[leftPointer++];
        }
        while (rightPointer <= rightIndex) {
            tempArray[index++] = arr[rightPointer++];
        }
        for (int i = 0; i < tempArray.length; i++) {
            arr[leftIndex + i] = tempArray[i];
        }
        return smallSum;
    }
}
