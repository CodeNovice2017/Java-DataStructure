package OJ.problems;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-16
 */
public class MiHaYou20210815 {

    private static int minTime = Integer.MAX_VALUE;

    public static void fun3(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int t = sc.nextInt();
        sc.nextLine();
        while(t-- > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            boolean[][] area = new boolean[n][m];
            sc.nextLine();
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            sc.nextLine();
            for(int i = 0;i < n;i++){
                String path = sc.nextLine();
                char[] pathStatus = path.toCharArray();
                for(int j = 0;j < m;j++){
                    if(pathStatus[j] == '#'){
                        area[i][j] = true;
                    }
                }
            }
            if(x2 - 2 >= 0){
                if(y2 - 2 >= 0){
                    getMinCostTime(n,m,x1 - 1,y1 - 1,x2 - 2,y2 - 2,area,0);
                }
                getMinCostTime(n,m,x1 - 1,y1 - 1,x2 - 2,y2 - 1,area,0);
                if(y2 <= m - 1){
                    getMinCostTime(n,m,x1 - 1,y1 - 1,x2 - 2,y2,area,0);
                }
            }
            if(y2 - 2 >= 0){
                getMinCostTime(n,m,x1 - 1,y1 - 1,x2 - 1,y2 - 2,area,0);
            }
            if(y2 <= m - 1){
                getMinCostTime(n,m,x1 - 1,y1 - 1,x2 - 1,y2,area,0);
            }
            if(x2 <= n - 1){
                if(y2 - 2 >= 0){
                    getMinCostTime(n,m,x1 - 1,y1 - 1,x2,y2 - 2,area,0);
                }
                getMinCostTime(n,m,x1 - 1,y1 - 1,x2,y2 - 1,area,0);
                if(y2 <= m - 1){
                    getMinCostTime(n,m,x1 - 1,y1 - 1,x2,y2,area,0);
                }
            }
            if(minTime == Integer.MAX_VALUE){
                System.out.println(-1);
            }else{
                int result = (x1 * x2) ^ t;
                result = result ^ (y1 * y2);
                System.out.println(result);
            }
        }
    }
    static int pre = 0;
    private static void getMinCostTime(int n,int m,int currentRowIndex,int currentColIndex,int targetRowIndex,int targetColIndex,boolean[][] visited,int currentTime){
        if(currentRowIndex < 0 || currentRowIndex >= n || currentColIndex < 0 || currentColIndex >= m || visited[currentRowIndex][currentColIndex]){
            return;
        }
        if(visited[targetRowIndex][targetColIndex]){
            return;
        }
        if(currentTime>pre){
            System.out.println(pre);
            pre = currentTime;
        }
        if(currentRowIndex == targetRowIndex && currentColIndex == targetColIndex){
            minTime = Math.min(minTime,currentTime);
            return;
        }
        visited[currentRowIndex][currentColIndex] = true;
        getMinCostTime(n,m,currentRowIndex + 1,currentColIndex,targetRowIndex,targetColIndex,visited,currentTime + 1);
        getMinCostTime(n,m,currentRowIndex - 1,currentColIndex,targetRowIndex,targetColIndex,visited,currentTime + 1);
        getMinCostTime(n,m,currentRowIndex,currentColIndex + 1,targetRowIndex,targetColIndex,visited,currentTime + 1);
        getMinCostTime(n,m,currentRowIndex,currentColIndex - 1,targetRowIndex,targetColIndex,visited,currentTime + 1);
        visited[currentRowIndex][currentColIndex] = false;
    }

    public static void func2(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int t = sc.nextInt();
        sc.nextLine();
        a : while(t-- > 0){
            String s = sc.nextLine();
            char[] arr = s.toCharArray();
            int stackMockCount = 0;
            for(int i = 0;i < arr.length;i++){
                if(arr[i] == 'a'){
                    stackMockCount++;
                }else{
                    if(stackMockCount == 0){
                        System.out.println("NO");
                        continue a;
                    }else{
                        stackMockCount--;
                    }
                }
            }
            if(stackMockCount == 0){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }

    public static void func1(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        sc.nextLine();
        int[] arr = new int[n];
        for(int i = 0;i < arr.length;i++){
            arr[i] = sc.nextInt();
        }
        sc.nextLine();
        HashMap<Integer,Integer> mapSingle = new HashMap<>();
        HashMap<Integer,Integer> mapDouble = new HashMap<>();
        int singleNeedChange = 0;
        int doubleNeedChange = 0;
        for(int i = 0;i < arr.length;i = i + 2){
            mapSingle.put(arr[i],mapSingle.getOrDefault(arr[i],0) + 1);
            singleNeedChange = Math.max(mapSingle.get(arr[i]),singleNeedChange);
            mapDouble.put(arr[i + 1], mapDouble.getOrDefault(arr[i + 1],0) + 1);
            doubleNeedChange = Math.max(mapDouble.get(arr[i + 1]),doubleNeedChange);
        }
        int result = n/2 - singleNeedChange + n/2 - doubleNeedChange;
        System.out.println(result);
    }
}
