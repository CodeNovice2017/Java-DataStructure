package OJ.problems;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-07-06
 */
public class SmallNNumbers20210706 {
    public int[] getLeastNumbers(int[] arr, int k) {
        if(arr.length == 0 || arr.length == 1){
            return arr;
        }
        if(k == 0){
            return new int[0];
        }
        // 一想就不能用排序O(nlogn)的,肯定是想要O(logn)的复杂度的
        // 利用logn次的快排Partition过程即可
        int leftIndex = 0;
        int rightIndex = arr.length - 1;
        int partitionResult = partition(arr,leftIndex,rightIndex);
        while(partitionResult != k - 1){
            if(partitionResult < k - 1){
                partitionResult = partition(arr,partitionResult + 1,rightIndex);
            }else{
                partitionResult = partition(arr,leftIndex,partitionResult - 1);
            }
        }
        int[] resultArray = new int[k];
        for(int i = 0;i < resultArray.length;i++){
            resultArray[i] = arr[i];
        }
        return resultArray;
    }
    private int partition(int[] arr,int leftIndex,int rightIndex){
        if(leftIndex == rightIndex){
            return leftIndex;
        }
        // rightIndex作为本次Partition的基准值
        int morePointer = rightIndex - 1;
        int lessPointer = leftIndex - 1;
        int pointer = 0;
        while(pointer < morePointer){
            if(arr[pointer] < arr[rightIndex]){
                swap(arr,++lessPointer,pointer);
                pointer++;
            }else if(arr[pointer] > arr[rightIndex]){
                swap(arr,pointer,morePointer--);
            }else{
                pointer++;
            }
        }
        // 如果是这种先交换后移动morePointer的写法的话,那么最后跳出上面循环之后的morePointer肯定是和pointer相遇了
        // 此时morePointer位置的值还是尚未确认与基准值之间的大小关系的,所以还需要最后一次判断,这也是为什么将morePointer开始直接设置为endIndex位置,然后每次交换后,morePointer位置都是确保大于基准值的写法是更好的
        if(arr[morePointer] > arr[rightIndex]){
            swap(arr,morePointer,rightIndex);
        }else if(arr[morePointer] < arr[rightIndex]){
            swap(arr,morePointer++,++lessPointer);
            swap(arr,morePointer,rightIndex);
        }else{
            swap(arr,++morePointer,rightIndex);
        }
        return morePointer;
    }
    private void swap(int[] arr,int index1,int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
