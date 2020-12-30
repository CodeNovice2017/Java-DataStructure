package BinaryTree;

public class FindLocalMinimumIndex {
    
    public static int findLocalMinimumIndexFunction(int[] arr){
        if (arr == null || arr.length == 0) {
            return -1; // no exist
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        // while(left < right){
        //     mid = (left + right)/2;
        //     if(arr[mid] > arr[mid-1]){
        //         right = mid - 1;
        //     }else if(arr[mid] > arr[mid + 1]){
        //         left = mid + 1;
        //     }else {
        //         return mid;
        //     }
        // }
        return findLocalMinimumIndexProcess(arr,left,right);
    }
    private static int findLocalMinimumIndexProcess(int[] arr,int left,int right){
        if(left > right){
            return -1;
        }
        int mid = (left + right) / 2;
        int index = 0;
        if(arr[mid] < arr[mid-1] && arr[mid] < arr[mid + 1]){
            index = mid;
        }else if(arr[mid] > arr[mid - 1]){
            index = findLocalMinimumIndexProcess(arr, left, mid-1);
        }else if(arr[mid] > arr[mid + 1]){
            index = findLocalMinimumIndexProcess(arr, mid+1, right);
        }else{
            index = -1;
        }
        return index;
    }
}
