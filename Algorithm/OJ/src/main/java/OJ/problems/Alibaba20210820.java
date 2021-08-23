package OJ.problems;

import java.io.BufferedInputStream;
import java.util.*;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-20
 */
public class Alibaba20210820 {
    static void problem1(){
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        int K = sc.nextInt();
        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer,Integer> floorMap = new HashMap<>();
        sc.nextLine();
        while(n-- > 0){
            String s = sc.nextLine();
            String[] arr = s.split(" ");
            int a = Integer.parseInt(arr[0]);
            int b = Integer.parseInt(arr[1]);
            int c = Integer.parseInt(arr[2]);
            // 进楼
            if(b == 1) {
                int currentFloorCount = floorMap.getOrDefault(c, 0) + 1;
                if (currentFloorCount < K) {
                    if(!set.contains(a)){
                        set.add(a);
                        floorMap.put(c,currentFloorCount);
                    }
                }
            }else{
                floorMap.put(c, floorMap.get(c) - 1);
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 1;i <= 5;i++){
                sb.append(floorMap.get(i) == null ? "0 " : floorMap.get(i) + " ");
            }
            System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
        }
    }

    static void problem2(){
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        int[] count = new int[n + 1];
        Arrays.fill(count,1);
        HashMap<Integer,Integer> personMap = new HashMap<>();
        for(int i = 1;i <= n;i++){
            personMap.put(i,i);
        }
        int maxNumber = 0;
        while(m-- > 0){
            int a = sc.nextInt();
            int b = sc.nextInt();
            sc.nextLine();
            if(personMap.get(a) == personMap.get(b)){
                System.out.println(maxNumber - count[personMap.get(a)]);
            }else{
                int merge = Math.max(a,b);
                int merged = Math.min(a,b);
                int mergedRow = personMap.get(merged);
                personMap.put(merged,personMap.get(merge));
                for(Map.Entry entry : personMap.entrySet()){
                    if((int)entry.getValue() == mergedRow){
                        personMap.put((int)entry.getKey(),personMap.get(merge));
                    }
                }
                count[merge] = count[merge] + count[merged];
                count[merged] = 0;
                maxNumber = Math.max(count[merge],maxNumber);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1;i <= n;i++){
            sb.append(personMap.get(i) + " ");
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
    }
}
