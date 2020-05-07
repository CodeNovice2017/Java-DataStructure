package Stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * package_name: Stack
 * project_name: LeetCode
 * creat_user: Veigar
 * creat_date: 2020/4/12
 * creat_time: 16:31
 **/
public class 有效的括号_20_简单_重点 {
    private HashMap<Character,Character> map = new HashMap<>();
    public 有效的括号_20_简单_重点(){
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');
    }
    public class Solution {
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
        //只考虑仅含有括号的情况,没有字符串,所以只用了一个else没有像上面设置对c[i]只有反括号的else if判断
        public boolean isValidStackUseHashMap(String s) {
            Stack<Character> stack1 = new Stack<Character>();
            char[] c = s.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if(map.containsKey(c[i])){
                    stack1.push(c[i]);
                }else{
                    if(stack1.empty()){
                        return false;
                    }
                    if(c[i] != map.get(stack1.pop())){
                        return false;
                    }
                }
            }
            return stack1.empty();
        }
        public boolean isValid2(String s){
            char[] c = s.toCharArray();
            String str = "";
            StringBuffer temp = new StringBuffer(str);
            String result;
            for(char item : c){
                if(item == '(' || item == '{' || item == '[' || item == ')' || item == '}' || item == ']'){
                    temp.append(item);
                }
            }
            result = temp.toString();
            while(result.contains("()") || result.contains("{}") || result.contains("[]")){
                result = result.replace("()", "");
                result = result.replace("{}", "");
                result = result.replace("[]", "");
            }
            return result.isEmpty();
        }
//        效率会非常低,涉及不可变字符串的判断和修改,要不断的重新申请空间等操作
        //而且这个只适用于只含有括号的情况,对于还含有字母数字的情况还需要进行其他处理
        public boolean isValid3(String s){
            while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
                s = s.replace("()", "");
                s = s.replace("{}", "");
                s = s.replace("[]", "");
            }
            if(s.isEmpty()){
                return true;
            }
            return false;
        }
    }
}
