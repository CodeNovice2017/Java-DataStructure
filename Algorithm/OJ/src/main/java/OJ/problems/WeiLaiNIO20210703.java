package OJ.problems;

import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-07-04
 */
public class WeiLaiNIO20210703 {
    public static String skipsame (String old) {
        // write code here
        char[] array = old.toCharArray();
        LinkedHashSet<Character> linkedHashSet = new LinkedHashSet<>();
        for(int i = 0;i < array.length;i++){
            linkedHashSet.add(array[i]);
        }
        Iterator<Character> iterator = linkedHashSet.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while(iterator.hasNext()){
            stringBuilder.append(iterator.next());
        }
        return stringBuilder.toString();
    }
    public static int minNumberInRotateArray(int [] array) {
        if(array == null || array.length == 0){
            return 0;
        }
        int result = process(array,0,array.length - 1);
        if(result == -1){
            return array[0];
        }
        return result;
    }
    private static int process(int[] array,int leftIndex,int rightIndex){
        if(leftIndex >= rightIndex){
            return -1;
        }
        int middleIndex = leftIndex + ((rightIndex - leftIndex) >> 1);
        if(array[middleIndex] > array[middleIndex + 1]){
            return array[middleIndex + 1];
        }
        int leftPartResult = process(array,leftIndex,middleIndex);
        if(leftPartResult != -1){
            return leftPartResult;
        }
        int rightPartResult = process(array,middleIndex + 1,rightIndex);
        if(rightPartResult != -1){
            return rightPartResult;
        }
        return -1;
    }

    public static int theNumberofWays (int[] arr, int aim) {
        // write code here
        int specialValue = (int)Math.pow(10,9) + 7;
        int[] dp = new int[aim + 1];
        dp[0] = 1;
        for(int i = 0;i < arr.length;i++){
            for(int j = arr[i];j <= aim;j++){
                dp[j] = (dp[j] + dp[j - arr[i]]) % specialValue;
            }
        }
        return dp[aim];
    }
}
