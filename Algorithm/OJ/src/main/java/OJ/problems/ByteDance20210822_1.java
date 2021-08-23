package OJ.problems;

import java.io.BufferedInputStream;
import java.util.*;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-22
 */
public class ByteDance20210822_1 {
    public static void func(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int M = sc.nextInt();
        int N = sc.nextInt();
        sc.nextLine();
        String best = sc.nextLine();
        char[] bestArr = best.toCharArray();
        HashSet<String> set = new HashSet<>();
        set.add("AE");
        set.add("BD");
        set.add("CE");
        set.add("BE");
        HashMap<Integer, List<String>> resultMap = new HashMap<>();
        int minResult = Integer.MAX_VALUE;
        while(N-- > 0){
            String curS = sc.nextLine();
            char[] curSArr = curS.toCharArray();
            int value = 0;
            boolean flag = false;
            for(int i = 0;i < bestArr.length;i++){
                if(curSArr[i] != bestArr[i]){
                    StringBuilder sb = new StringBuilder();
                    if(curSArr[i] < bestArr[i]){
                        sb.append(curSArr[i]).append(bestArr[i]);
                        if(!set.contains(sb.toString())){
                            value += bestArr[i] - curSArr[i];
                        }else{
                            flag = true;
                            break;
                        }
                    }else{
                        sb.append(bestArr[i]).append(curSArr[i]);
                        if(!set.contains(sb.toString())){
                            value += curSArr[i] - bestArr[i];
                        }else{
                            flag = true;
                            break;
                        }
                    }
                }
            }
            if(!flag){
                minResult = Math.min(minResult,value);
                List<String> list = resultMap.getOrDefault(value,new ArrayList<>());
                list.add(curS);
                resultMap.put(value,list);
            }
        }
        if(minResult != Integer.MAX_VALUE){
            for(String temp : resultMap.get(minResult)){
                System.out.println(temp);
            }
        }else{
            System.out.println("None");
        }
    }
}
