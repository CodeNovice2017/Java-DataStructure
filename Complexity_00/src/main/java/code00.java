import com.sun.media.sound.SoftTuning;

/**
 * @Classname code00
 * @Date 2020/3/14 18:49
 * @Created by Veigar
 */
//斐波那契数
public class code00 {

    //递归实现(性能问题)
    public static int fibonacci_Recursion(int n){
         if(n<=1){
             return n;
         }
         return fibonacci_Recursion(n-1) + fibonacci_Recursion(n-2);
    }
    public static int fibonacci_NoRecursion(int n){
        if(n==0||n==1){
            return n;
        }
        int first = 0;
        int second = 1;
        int temp;
        int c = 2;
        while(true){
            if(n==c){
                return first+second;
            }
            else{
                temp = first;
                first = second;
                second = second + temp;
                c++;
            }
        }

    }

    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        System.out.println(fibonacci_Recursion(40));
        long time2 = System.currentTimeMillis();
        System.out.println(fibonacci_NoRecursion(40));
        long time3 = System.currentTimeMillis();
        System.out.println(time2-time1);
        System.out.println(time3-time2);
    }
}
