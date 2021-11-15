package OJ;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        String s = sc.nextLine();
        int a = countSubstrings(s);
        int b = longestPalindrome(s).length();
        System.out.println(a + "," + b);
    }
    public static int countSubstrings(String s) {
        int resultCount = 0;
        char[] arr = s.toCharArray();
        boolean[][] dp = new boolean[arr.length][arr.length];
        for(int i = 0;i < arr.length;i++){
            dp[i][i] = true;
        }
        for(int j = 1;j < arr.length;j++){
            for(int i = 0;i < j;i++){
                if(arr[i] == arr[j]){
                    if(j - i < 3){
                        resultCount++;
                        dp[i][j] = true;
                    }else{
                        if(dp[i + 1][j - 1]){
                            dp[i][j] = true;
                            resultCount++;
                        }
                    }
                }
            }
        }
        return resultCount;
    }

    public static String longestPalindrome(String s) {
        if(s.length() < 2){
            return s;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int i = 0;i < s.length();i++){
            dp[i][i] = true;
        }
        int beginIndex = 0;
        int result = 1;
        char[] arr = s.toCharArray();
        for(int col = 1;col < arr.length;col++){
            for(int row = 0;row < col;row++){
                if(arr[col] == arr[row]){
                    if(col - row < 3){
                        dp[row][col] = true;
                        if(col - row + 1 > result){
                            result = col - row + 1;
                            beginIndex = row;
                        }
                    }else{
                        if(dp[row + 1][col - 1]){
                            dp[row][col] = true;
                            if(col - row + 1 > result){
                                result = col - row + 1;
                                beginIndex = row;
                            }
                        }
                    }
                }
            }
        }
        return s.substring(beginIndex,beginIndex + result);
    }
}
