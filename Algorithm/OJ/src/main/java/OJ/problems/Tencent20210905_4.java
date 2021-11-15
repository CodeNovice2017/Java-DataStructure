package OJ.problems;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-09-05
 */
public class Tencent20210905_4 {

    public static void func(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();
        int[] cost = new int[n];
        for(int i = 0;i < cost.length;i++){
            cost[i] = sc.nextInt();
        }
        int[] costSum = new int[n];
        costSum[0] = cost[0];
        for(int i = 1;i < costSum.length;i++){
            costSum[i] = costSum[i - 1] + cost[i];
        }
        int[][] dp = new int[n + 1][2];
        dp[1][0] = m;
        dp[1][1] = m - cost[0];
        for(int i = 2;i < dp.length;i++){
            if(i >= k){
                dp[i][0] = Math.max(dp[i - 1][0] - cost[i],dp[i - 1][1]);
                dp[i][0] = Math.max(dp[i][0],(dp[i - k][0] - (costSum[i] - costSum[i - k])));
            }
            if(i >= k){
                dp[i][1] = Math.max(dp[i - 1][1] - cost[i],(dp[i - k][1] - (costSum[i] - costSum[i - k])));
            }else{
                dp[i][1] = dp[i - 1][1] - cost[i];
            }
        }
        System.out.println(Math.max(dp[cost.length][0],dp[cost.length][1]));
    }

}
