package OJ.problems;

import java.io.BufferedInputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-22
 */
public class MeiTuan20210822_2 {
    public static void fun(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        String s = sc.nextLine();
        StringBuilder chargeS = new StringBuilder();
        chargeS = chargeS.append("(").append(s).append(")");
        char[] arr = chargeS.toString().toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        HashMap<String,Integer> map = new HashMap<>();
        HashMap<Integer,Integer> match = new HashMap<>();
        for(int i = 0;i < arr.length;i++){
            if(arr[i] == '('){
                stack.push(i);
            }else{
                int index = stack.pop();
                StringBuilder sb = new StringBuilder();
                int value = 0;
                if(i - index == 1){
                    value = 2;
                }else{
                    sb.append(index + 1).append("@").append(i - 1);
                    if(!map.containsKey(sb.toString())){
                        int searchIndex = index + 1;
                        int tempValue = 1;
                        while(match.containsKey(searchIndex) && match.get(searchIndex) < i){
                            StringBuilder temp = new StringBuilder();
                            temp.append(searchIndex).append("@").append(match.get(searchIndex));
                            tempValue *= map.get(temp.toString());
                            searchIndex = match.get(searchIndex) + 1;
                        }
                        map.put(sb.toString(),tempValue);
                    }
                    value = map.get(sb.toString()) + 1;

                }
                StringBuilder curSb = new StringBuilder();
                curSb.append(index).append("@").append(i);
                match.put(index,i);
                map.put(curSb.toString(),value);
            }
        }
        StringBuilder resultSb = new StringBuilder();
        resultSb.append(0).append("@").append(arr.length - 1);
        System.out.println(map.get(resultSb.toString()) - 1);
    }
}
