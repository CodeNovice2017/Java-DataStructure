package OJ.problems;

import java.io.BufferedInputStream;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-31
 */
public class PinDuoDuo20210831_1 {
    public static void func() {

        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int T = sc.nextInt();
        while (T-- > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            PriorityQueue<Integer> red = new PriorityQueue<>();
            PriorityQueue<Integer> blue = new PriorityQueue<>();

            for(int i = 0;i < n;i++){
                red.add(sc.nextInt());
            }

            for(int i = 0;i < m;i++){
                blue.add(sc.nextInt());
            }

            int res = 0;
            while(!red.isEmpty() && !blue.isEmpty()){
                int r = red.peek();
                int b = blue.peek();
                if((r < b && b - r <= k) || (r > b && r - b <= k)){
                    res++;
                    red.poll();
                    blue.poll();
                }else if(r < b){
                    red.poll();
                }else{
                    blue.poll();
                }
            }
            System.out.println(res);
        }
    }
}
