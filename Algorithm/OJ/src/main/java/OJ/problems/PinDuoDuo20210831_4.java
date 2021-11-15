package OJ.problems;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-31
 */
public class PinDuoDuo20210831_4 {
    public static void func() {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int T = sc.nextInt();
        sc.nextLine();
        while(T-- > 0){
            int N = sc.nextInt();
            int c = N;
            sc.nextLine();
            List<Integer> table = new ArrayList<>();
            int row = 0;
            while(N-- > 0){
                int num = sc.nextInt();
                table.add(num);
                row = Math.max(row,num);
            }
            int[][] arr = new int[c][row];
            for(int temp : table){

            }
        }
    }
}
