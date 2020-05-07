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
        String[] strArray = new String[]{"2", "1", "+", "3", "*"};
        逆波兰表达式求值_150_中等_重点.Solution s = new 逆波兰表达式求值_150_中等_重点().new Solution();
        s.evalRPN(strArray);
    }
}
