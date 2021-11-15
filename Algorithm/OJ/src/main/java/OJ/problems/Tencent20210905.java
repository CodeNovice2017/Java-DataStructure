package OJ.problems;

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-09-05
 */
public class Tencent20210905 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < a.length; i++) {
            int temp = sc.nextInt();
            a[i] = sum(temp);
        }
        for (int i = 0; i < b.length; i++) {
            int temp = sc.nextInt();
            b[i] = sum(temp);
        }
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0;
        int j = 0;
        int res = 0;
        while(i < a.length){
            if(a[i] > b[j]){
                res++;
                i++;
                j++;
            }else{
                i++;
            }
        }
        System.out.println(res);
    }

    private static int sum(int value){
        int res = 0;
        int max = (int)Math.sqrt(value);
        for (int i = 1; i <= max; i++) {
            if(value % i == 0){
                res++;
                if(res / i != i){
                    res++;
                }
            }
        }
        return res;
    }

}
