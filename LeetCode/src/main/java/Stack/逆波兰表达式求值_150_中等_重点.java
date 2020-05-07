package Stack;

import java.nio.file.StandardWatchEventKinds;
import java.util.HashMap;
import java.util.Stack;

/**
 * package_name: Stack
 * project_name: LeetCode
 * creat_user: Veigar
 * creat_date: 2020/4/24
 * creat_time: 13:08
 **/
public class 逆波兰表达式求值_150_中等_重点 {
    public class Solution {
        public int evalRPN(String[] tokens) {
            int result = 0;
            int a,b = 0;
            Stack<Integer> stack = new Stack<>();
            for(String s : tokens){
                if(s.equals("+")){
                    a = stack.pop();
                    b = stack.pop();
                    result = a+b;
                    stack.push(result);
                }else if(s.equals("*")){
                    a = stack.pop();
                    b = stack.pop();
                    result = a*b;
                    stack.push(result);
                }else if(s.equals("/")){
                    a = stack.pop();
                    b = stack.pop();
                    result = b/a;
                    stack.push(result);
                }else if(s.equals("-")){
                    a = stack.pop();
                    b = stack.pop();
                    result = b-a;
                    stack.push(result);
                }else{
                    int c = Integer.parseInt(s);
                    stack.push(c);
                }
            }
            return stack.pop();
        }
    }
}
