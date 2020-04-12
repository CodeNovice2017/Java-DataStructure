package Stack;

import java.util.Stack;

/**
 * package_name: Stack
 * project_name: LeetCode
 * creat_user: Veigar
 * creat_date: 2020/4/12
 * creat_time: 16:31
 **/
public class 有效的括号_20_简单_重点 {
    class Solution {
        public boolean isValid(String s) {
            Stack<Character> stack1 = new Stack<Character>();
            char[] c = s.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if(c[i] == '(' || c[i] == '{' || c[i] == '['){
                    stack1.push(c[i]);
                }else if(c[i] == ')' || c[i] == '}' || c[i] == ']'){
                    if(stack1.empty()){
                        return false;
                    }
                    char temp = stack1.pop();
                    if(c[i] == ')' && temp != '('){
                        return false;
                    }else if(c[i] == '}' && temp != '{'){
                        return false;
                    }else if(c[i] == ']' && temp != '['){
                        return false;
                    }
                }
            }
            return stack1.empty();
        }
    }
}
