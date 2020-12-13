package sort;

public class GetArrayMax {

    public static int getArrayMax(int arr[],int L,int R){

        // Base Case
        if(L == R){
            return arr[L];
        }
        int mid = (L+R)/2; // 实际应该使用L+(R-L)/2,因为L+R如果在特别大的时候,可能发生int类型的溢出

        int leftMax = getArrayMax(arr, L, mid);
        int rightMax = getArrayMax(arr, mid+1, R);

        return leftMax > rightMax ? leftMax : rightMax;

    }
    
}
