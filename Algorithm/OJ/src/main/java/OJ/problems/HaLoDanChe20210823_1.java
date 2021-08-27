package OJ.problems;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-23
 */
public class HaLoDanChe20210823_1 {
    public static void fun() {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int row = sc.nextInt();
        int col = sc.nextInt();
        sc.nextLine();
        int[][] table = new int[row][col];
        for(int i = 0;i < row;i++){
            for(int j = 0;j < col;j++){
                table[i][j] = sc.nextInt();
            }
            sc.nextLine();
        }
        int[][] dp = new int[row][col];
        dp[0][0] = table[0][0];
        for(int i = 1;i < col;i++){
            dp[0][i] = dp[0][i - 1] + table[0][i];
        }
        for(int i = 1;i < row;i++){
            dp[i][0] = dp[i - 1][0] + table[i][0];
        }
        for(int i = 1;i < row;i++){
            for(int j = 1;j < col;j++){
                dp[i][j] = Math.min(dp[i - 1][j],dp[i][j - 1]) + table[i][j];
            }
        }
        System.out.println(dp[row - 1][col - 1]);
    }
}
