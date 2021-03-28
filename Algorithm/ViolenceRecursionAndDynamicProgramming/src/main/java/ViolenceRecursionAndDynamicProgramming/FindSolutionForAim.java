package ViolenceRecursionAndDynamicProgramming;

import java.util.Arrays;

/**
 * 题目描述:给你一个数组arr,(为了让问题简化,规定数组内全是正数)和一个整数(也规定为正数)aim。
 * 如果可以任意选择arr中的数字，能不能累加得到aim，返回true或者false
 */

public class FindSolutionForAim {
    
    public static boolean findSolutionForAim(int[] arr,int aim){

        // boolean[] visited = new boolean[arr.length];
        // return process(arr, aim, 0, visited);

        return process1(arr, 0, 0, aim);


    }
    private static boolean process(int[] arr,int aim,int value,boolean[] visited){
        boolean result = false;
        if(value > aim){
            result = false;
        }else if(value == aim){
            result = true;
        }else{
            for(int i = 0;i<arr.length;i++){
                if(visited[i] == false){
                    visited[i] = true;
                    result = process(arr,aim,value + arr[i],visited);
                    if(result == false){
                        visited[i] = false;
                    }else{
                        break;
                    }
                }
            }
        }
        return result;
    }
    
    private static boolean process1(int[] arr, int i, int sum, int aim) {
        // 最初的Base Case
        // if (sum == aim) {
        //     return true;
        // }
        // if (i == arr.length) {
        //     return false;
        // }

        // 优化后的Base Case
        // 就是当i到了最终位置时,看当前形成的sum是否等于aim
        if (i == arr.length) {
            return sum == aim;
        }
        return process1(arr, i + 1, sum, aim) || process1(arr, i + 1, sum + arr[i], aim);
    }

    private static boolean processByDynamicProgramming(int[] arr,int aim){
        int sumMax = Arrays.stream(arr).sum();
        boolean[][] dp = new boolean[arr.length+1][sumMax];
        for(int i = 0;i<sumMax;i++){
            if(i == aim){
                dp[arr.length][i] = true;
            }
        }
        for(int i = arr.length - 1;i>=0;i--){
            for(int j = 0;j<sumMax;j++){
                dp[i][j] = dp[i + 1][j] || dp[i + 1][j + arr[i]];
            }
        }
        return dp[0][0];
    }
}

