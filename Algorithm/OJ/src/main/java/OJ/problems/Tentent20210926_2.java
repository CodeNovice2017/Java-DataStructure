package OJ.problems;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-09-26
 */
public class Tentent20210926_2 {

    public static void func() {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int t = sc.nextInt();
        sc.nextLine();
        while(t-- > 0){
            int result = 0;
            int n = sc.nextInt();
            sc.nextLine();
            int[] a = new int[n];
            HashMap<Integer,Integer> map = new HashMap<>();
            for(int i = 0;i < a.length;i++){
                a[i] = sc.nextInt();
                map.put(i,0);
            }
            for(int i = 0; i < a.length; i++){
                int resultPart = a[i];
                int nextIndex = i + a[i];
                while(nextIndex < a.length){
                    if(map.get(nextIndex) >= resultPart){
                        break;
                    }else{
                        map.put(nextIndex,resultPart);
                        resultPart += a[nextIndex];
                        nextIndex = nextIndex + a[nextIndex];
                    }
                }
                result = Math.max(result,resultPart);
            }
            System.out.println(result);
        }
    }
}
