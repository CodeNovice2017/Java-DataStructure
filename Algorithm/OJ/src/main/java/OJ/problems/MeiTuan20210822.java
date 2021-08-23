package OJ.problems;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-22
 */

public class MeiTuan20210822 {

    public static void func(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        String s = sc.nextLine();
        int n = sc.nextInt();
        sc.nextLine();
        char[] arr = s.toCharArray();
        HashMap<Character,int[]> map = new HashMap<>();

        HashMap<Character,Integer> distance = new HashMap<>();

        HashMap<Integer,Integer> resultMap = new HashMap<>();

        for(int i = 0; i < arr.length;i++){
            if(map.containsKey(arr[i])){
                int[] cur = new int[]{map.get(arr[i])[1],i};
                distance.put(arr[i],Math.min(distance.get(arr[i]),i - map.get(arr[i])[1]));
                map.put(arr[i],cur);

            }else{
                map.put(arr[i],new int[]{-1,i});

                distance.put(arr[i],Integer.MAX_VALUE);
            }
        }
        StringBuilder sb = new StringBuilder(s);
        while(n-- > 0){
            String next = sc.nextLine();
            String[] nextArr = next.split(" ");
            int command = Integer.parseInt(nextArr[0]);
            if(command == 1){
                sb.append(nextArr[1]);
                char c = nextArr[1].charAt(0);
                if(map.containsKey(c)){
                    int[] cur = new int[]{map.get(c)[1],sb.length() - 1};

                    distance.put(c,Math.min(distance.get(c),sb.length() - 1 - map.get(c)[1]));

                    map.put(c,cur);
                }else{
                    map.put(c,new int[]{-1,sb.length() - 1});

                    distance.put(c,Integer.MAX_VALUE);

                }
            }else{
                int[] cur = map.get(sb.charAt(Integer.parseInt(nextArr[1]) - 1));
                if(cur[0] == -1){
                    System.out.println(-1);
                }else{

                    System.out.println(distance.get(sb.charAt(Integer.parseInt(nextArr[1]) - 1)));

                }
            }
        }
    }

}
