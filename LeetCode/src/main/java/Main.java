import javax.security.auth.x500.X500Principal;

import Others.x的平方根_63_简单;
import Queue.用栈实现队列_232;
import Stack.有效的括号_20_简单_重点;
import Stack.逆波兰表达式求值_150_中等_重点;

/**
 * package_name: PACKAGE_NAME
 * project_name: *
 * creat_user: Veigar
 * creat_date: 2020/4/21
 * creat_time: 10:57
 **/
public class Main {
    public static void main(String[] args) {
//        String str = "(}";
//        有效的括号_20_简单_重点.Solution s = new 有效的括号_20_简单_重点().new Solution();
//        System.out.println(s.isValid2(str));

        // String[] strArray = new String[]{"2", "1", "+", "3", "*"};
        // 逆波兰表达式求值_150_中等_重点.Solution s = new 逆波兰表达式求值_150_中等_重点().new Solution();
        // s.evalRPN(strArray);

        // 用栈实现队列_232.MyQueue queue = new 用栈实现队列_232().new MyQueue();
        // queue.push(10);
// System.out.println(queue.pop());
        
        //x的平方根
        x的平方根_63_简单.Solution1 solution1 = new x的平方根_63_简单().new Solution1();
        x的平方根_63_简单.Solution2 solution2 = new x的平方根_63_简单().new Solution2();
        x的平方根_63_简单.Solution3 solution3 = new x的平方根_63_简单().new Solution3();

        System.out.println(solution3.mySqrt(2147483647));
    }
}
