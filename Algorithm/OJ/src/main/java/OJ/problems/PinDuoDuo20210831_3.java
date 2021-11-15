package OJ.problems;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-31
 */
public class PinDuoDuo20210831_3 {
    public static void func() {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int T = sc.nextInt();

        while(T-- > 0){
            long n = sc.nextLong();
            if(n < 4){
                System.out.println(0);
                continue;
            }
            long sqrt = (long)Math.sqrt(n / 2);
            if(sqrt * (sqrt + 1) * 2 > n){
                sqrt--;
            }
            long res = sqrt * sqrt;
            long left = n - (sqrt * (sqrt + 1) * 2);
            if(2 * sqrt + 1 > left){
                res += sqrt;
                left -= 2 * sqrt + 1;
            }
            left--;
            res += left/2;
            System.out.println(res);
        }
    }
}
