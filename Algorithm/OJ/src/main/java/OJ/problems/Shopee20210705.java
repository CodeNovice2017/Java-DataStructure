package OJ.problems;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-07-05
 */
public class Shopee20210705 {
    public static String compressString(String param) {
        // write code here
        if(param.length() == 0 || param.length() == 1){
            return param;
        }
        StringBuilder sb = new StringBuilder();
        char[] array = param.toCharArray();
        char pre = array[0];
        for(int i = 1;i < array.length;){
            int count = 1;
            while(i < array.length && array[i] == pre){
                count++;
                i++;
            }
            sb.append(pre);
            if(count != 1) {
                sb.append(String.valueOf(count));
            }
            if(i == array.length){
                break;
            }
            pre = array[i++];
        }
        return sb.toString();
    }

    public static int petalsBreak(int[] petals) {
        // write code here
        if(petals == null || petals.length < 1){
            return 0;
        }
        int result = 0;
        for(int i = 0;i < petals.length;i++){
            result += (petals[i] + 1) / 2;
        }
        return result;
    }

    private static int resultNeedHealth = Integer.MIN_VALUE;
    public static int minimumInitHealth(int[][] rooms, int[] startPoint, int[] endPoint) {
        // write code here
        // DFS
        dfs(rooms,startPoint[0],startPoint[1],endPoint[0],endPoint[1],0,0,new boolean[rooms.length][rooms[0].length]);
        return resultNeedHealth < 0 ? -1 * resultNeedHealth + 1 : 1;
    }
    private static void dfs(int[][] rooms,int rowIndex,int colIndex,int endRowIndex,int endColIndex,int minNeedHealth,int currentHealth,boolean[][] visited){
        if(rowIndex < 0 || rowIndex >= rooms.length || colIndex < 0 || colIndex >= rooms[0].length){
            return;
        }
        if(visited[rowIndex][colIndex]){
            return;
        }
        currentHealth = currentHealth + rooms[rowIndex][colIndex];
        if(currentHealth < 0){
            if(currentHealth < minNeedHealth){
                minNeedHealth = currentHealth;
            }
        }
        if(rowIndex == endRowIndex && colIndex == endColIndex){
            resultNeedHealth = Math.max(resultNeedHealth,minNeedHealth);
        }
        visited[rowIndex][colIndex] = true;
        dfs(rooms, rowIndex + 1, colIndex, endRowIndex, endColIndex, minNeedHealth,currentHealth, visited);
        dfs(rooms, rowIndex - 1, colIndex, endRowIndex, endColIndex, minNeedHealth,currentHealth, visited);
        dfs(rooms, rowIndex, colIndex + 1, endRowIndex, endColIndex, minNeedHealth,currentHealth, visited);
        dfs(rooms, rowIndex, colIndex - 1, endRowIndex, endColIndex, minNeedHealth,currentHealth, visited);
        visited[rowIndex][colIndex] = false;
    }
}
