package OJ.problems;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-23
 */
public class HaloDanChe20210823_2 {
    public static void fun() {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        sc.nextLine();
        boolean[] dp = new boolean[n + 1];
        dp[0] = false;
        dp[1] = true;
        dp[2] = false;
        for(int i = 3;i <= n;i++){
            int num = 1;
            int value = 1;
            while(value <= i) {
                dp[i] = !dp[i - value];
                if (dp[i]) {
                    break;
                }
                value = (int) Math.pow(num++, 2);
            }
        }
        System.out.println(dp[n]);
        Class c = String.class;
    }
}
