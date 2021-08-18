package OJ.problems;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-18
 */
public class Alibaba20210816 {
    // 部队同盟问题
    public static void armyUnion(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        // n是部队的数量
        int n = sc.nextInt();
        // m是输入指令的数量
        int m = sc.nextInt();
        int[] numbers = new int[n + 1];
        int[] count = new int[n + 1];
        for (int i = 1; i < numbers.length; i++) {
            // 最开始所有部队各成一个集合,集合的编号就是其本身
            numbers[i] = i;
            // 各部队的初始人数
            count[i] = sc.nextInt();
        }

        for (int i = 0; i < m; i++) {
            int p = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            // 找到x部队的联盟
            int a = find(numbers,x);
            // 找到y部队的联盟
            int b = find(numbers,y);
            // 如果是联盟指令
            if(p == 1){
                if(a != b){
                    // 如果x部队所在的a联盟和y部队所在的b联盟不是相同的联盟,才可以同盟,输出联盟金
                    System.out.println((a + b) ^ (Math.abs(count[a] - count[b])));
                }
                merge(numbers,x,y,count);
            }else{
                // 如果是询问指令
                if(a != b){
                    System.out.println("NO");
                }else{
                    System.out.println("YES");
                }
            }
        }
    }

    private static int find(int[] numbers,int index){
        if(numbers[index] != index){
            numbers[index] = find(numbers,numbers[index]);
        }
        return numbers[index];
    }

    private static void merge(int[] numbers,int a,int b,int[] count){
        int x = find(numbers,a);
        int y = find(numbers,b);
        if(x < y){
            count[x] += count[y];
            numbers[y] = x;
        }else if(y < x){
            count[y] += count[x];
            numbers[x] = y;
        }
    }
}
