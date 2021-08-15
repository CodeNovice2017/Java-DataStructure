package OJ.problems;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-15
 */
public class GetTwoSingleNumbers {
    // 剑指offer 56
    public int[] singleNumbers(int[] nums) {
        int xorResult = 0;
        for(int i = 0;i < nums.length;i++){
            xorResult = xorResult ^ nums[i];
        }
        int partitionValue = 1;
        while((xorResult & partitionValue) == 0){
            partitionValue = partitionValue << 1;
        }
        int partitionIndex = partition(nums,partitionValue);
        int[] result = new int[2];
        for(int i = 0;i < partitionIndex;i++){
            result[0] = result[0] ^ nums[i];
        }
        for(int i = partitionIndex;i < nums.length;i++){
            result[1] = result[1] ^ nums[i];
        }
        return result;
    }
    private int partition(int[] nums,int partitionValue){
        int lessIndex = -1;
        int moreIndex = nums.length;
        int pointer = 0;
        while(pointer < moreIndex){
            if((nums[pointer] & partitionValue) == 0){
                swap(nums,++lessIndex,pointer++);
            }else{
                swap(nums,--moreIndex,pointer);
            }
        }
        return pointer;
    }
    private void swap(int[] nums,int leftIndex,int rightIndex){
        if(leftIndex != rightIndex){
            nums[leftIndex] = nums[leftIndex] ^ nums[rightIndex];
            nums[rightIndex] = nums[leftIndex] ^ nums[rightIndex];
            nums[leftIndex] = nums[leftIndex] ^ nums[rightIndex];
        }
    }
}
