package OJ.problems;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-22
 */
public class ByteDance20210822_4 {
    public static void func(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int t = sc.nextInt();
        sc.nextLine();
        while(t-- > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            sc.nextLine();
            int[] nums = new int[n];
            for(int i = 0;i < n;i++){
                nums[i] = sc.nextInt();
            }
            sc.nextLine();
            int index = 0;
            int before = 0;
            while(m-- > 0){
                for(int i = before;i < n;i++){
                    if(i != n - 1){
                        if(nums[i] + 1 <= nums[i + 1]){
                            nums[i]++;
                            index = i;
                            before = index == 0 ? 0 : index - 1;
                            break;
                        }
                    }else{
                        index = n + 1;
                    }
                }
            }
            System.out.println(index == n + 1 ? n + 1 : index + 1);
        }
    }
}
