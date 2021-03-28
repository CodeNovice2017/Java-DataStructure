package OJ;

import java.io.BufferedInputStream;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hello world!
 */

public final class App {

    public class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        public TreeNode(int val){
            this.val = val;
        }
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */

    public static final ReentrantLock lock = new ReentrantLock();
    public static final Condition condition = lock.newCondition();
    public static void main(String[] args) {
        
        // problemThread();

        // System.out.println(problemString("open.aliyun.com", "aliyun"));

        Scanner sc = new Scanner(new BufferedInputStream(System.in));

        int t = sc.nextInt();
        
        while(t-- > 0){
            int n = sc.nextInt();
            int[] value = new int[n];
            for (int i = 0; i < n; i++) {
                value[i] = sc.nextInt();
            }
            System.out.println(test2(n, value));
        }
    }

    static int test2(int n,int[] value){

        if(n == 0 || n == 1){
            return value[0];
        }
        int[] dp = new int[n];
        dp[0] = value[0];
        dp[1] = value[0] + (int)Math.floor(value[1] / 2);
        for(int i = 2;i < n;i++){
            dp[i] = Math.max(dp[i - 1] + (int)Math.floor(value[i]/2),dp[i - 2] + value[i]);
        }
        return dp[n - 1];
    }

    
    // static int dfs(int n,int[] value,int[] pre,int[] order){

    //     if(n == 0){
    //         return 0;
    //     }
    //     if(n == 1){
    //         return value[0];
    //     }
        
    // }

    static void problemThread(){
        // 两个线程交替打印1-100的整数，一个打印奇数，一个打印偶数，要求输出结果有序
        Thread t1 = new Thread(() -> {
            try {
                for (int i = 1; i <= 99; i += 2) {
                    lock.lock();
                    condition.signal();
                    System.out.println(i);
                    condition.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                for (int i = 2; i <= 100; i += 2) {
                    lock.lock();
                    condition.signal();
                    System.out.println(i);
                    condition.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.start();
        t2.start();
    }

    static int problemString(String str1,String str2){
        // 输入: 字符串str1， 字符串str2
        // 输出: 字符串str2在字符串str1中第一次出现的位置。如果没有返回-1.
        // 例如： str1=“open.aliyun.com” str2="aliyun"  ->  4
        // 其它要求：不能使用String类的indexOf方法
        if(str1.length() == 0 || str2.length() == 0){
            return -1;
        }
        char[] str1ToArray = str1.toCharArray();
        char[] str2ToArray = str2.toCharArray();
        for(int i = 0;i < str1ToArray.length;i++){
            if(str1ToArray[i] == str2ToArray[0]){
                int str1ArrayIndex = i + 1;
                boolean flag = true;
                for(int j = 1;j < str2ToArray.length; j++){
                    if(str1ArrayIndex < str1ToArray.length && str1ToArray[str1ArrayIndex] == str2ToArray[j]){
                        str1ArrayIndex++;
                    }else{
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    return i;
                }
            }
        }
        return -1;
    }

}

    // Scanner cin = new Scanner(new BufferedInputStream(System.in));
    // int t = cin.nextInt();
    // // 排除k > m * n
    // while(t>0)
    // {
    //     t = t - 1;
    //     int n = cin.nextInt();
    //     int m = cin.nextInt();
    //     int k = cin.nextInt();

    //     // 肯定要用动态规划,否则暴力法的时间复杂度为n的m次方
    //     // 定义一个动态规划数组,最大的牌号相加之后为 m * n
    //     // 数组长度最长为3001
    //     int[] sums = new int[m * n + 1];
    //     for (int i = 1; i <= m; i++) {
    //         sums[i] = 1;
    //     }

    //     // 最开始初始化时,当已抽的牌为n张时,最大的编号和为n * m,那么当n = 1时,最小点数为1,最大点数为m
    //     int min = 1;
    //     int max = m;
    //     for (int i = 2; i <= n; i++) {
    //         // 每一次最小值加1,因为多抽一张牌,最小编号和就至少为n张牌的数量
    //         min = min + 1;
    //         // 同理 最大值多追加m
    //         max = max + m;
    //         // j - j2为已经抽出n-1张牌的时候,编号和为j - j2
    //         for (int j = max; j >= min; j--) {
    //             // 过程中,sums[j]表示掷出j点数时的不同抽排方式所能得到的次数和
    //             // 倒序遍历sums数组,防止从前向后遍历时,sums[j] = 0;会破坏之前的条件
    //             sums[j] = 0;
    //             for (int j2 = 1; j2 <= m; j2++) {
    //                 if (j - j2 < i - 1) {
    //                     break;
    //                 }
    //                 sums[j] = (sums[j] + sums[j - j2]) % 100000007;
    //             }
    //         }
    //     }
    //     System.out.println(sums[k]);
    // }
