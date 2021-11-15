package OJ.problems;

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-09-05
 */
public class Tencent20210905_2 {

    public static void func(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        char[] arr = s.toCharArray();
        int[] dp = new int[arr.length + 1];
        Arrays.fill(dp,1);
        for(int i = 1;i < dp.length;i++){
            int res = 1;
            for(int j = i - 1;j >= 0;j--){
                if(arr[i] == arr[j]){
                    res++;
                    if(j == 0){
                        dp[i] = Math.max(dp[i],(i + res) * res / 2);
                    }else{
                        dp[i] = Math.max(dp[i],(i + res) * res / 2 + dp[j - 1]);
                    }
                }
            }
        }
        System.out.println(dp[arr.length - 1]);
    }
}
