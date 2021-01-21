package ViolenceRecursionAndDynamicProgramming;

public class GetMin {
    
    public static int getMin(int[] arr,int L,int R){
        if(L == R){
            return arr[L];
        }
        int midIndex = (L+R)/2;
        int leftMin = getMin(arr, L, midIndex);
        int rightMin = getMin(arr, midIndex + 1,R);
        return Math.min(leftMin, rightMin);
    }
}
