package ViolenceRecursionAndDynamicProgramming;

import java.util.HashMap;

public class ShortestPath {

    public int getShortestPath(int[][] matrix){

        HashMap<String,Integer> cache = new HashMap<>();
        return processByCacheOptimization(matrix,0,0,cache);

    }

    // 暴力递归
    private int process(int[][] matrix,int rowIndex,int colIndex){
        // Base Case
        if(rowIndex == matrix.length - 1 && colIndex == matrix[0].length - 1){
            return matrix[rowIndex][colIndex];
        }
        if(rowIndex == matrix.length - 1){
            return matrix[rowIndex][colIndex] + process(matrix, rowIndex, colIndex + 1);
        }else if(colIndex == matrix[0].length - 1) {
            return matrix[rowIndex][colIndex] + process(matrix, rowIndex + 1, colIndex);
        }else{
            return matrix[rowIndex][colIndex] + Math.min(process(matrix, rowIndex+1, colIndex), process(matrix, rowIndex, colIndex+1));
        }
    }


    // 傻缓存优化
    private int processByCacheOptimization(int[][] matrix, int rowIndex, int colIndex,HashMap<String,Integer> cache) {

        // 为了添加傻缓存
        int result = 0;

        // Base Case
        if (rowIndex == matrix.length - 1 && colIndex == matrix[0].length - 1) {
            result = matrix[rowIndex][colIndex];
        }
        if (rowIndex == matrix.length - 1) { 
            result = matrix[rowIndex][colIndex] + (cache.containsKey(String.valueOf(rowIndex) + '_' + String.valueOf(colIndex + 1)) ? cache.get(String.valueOf(rowIndex) + '_' + String.valueOf(colIndex + 1)) : process(matrix, rowIndex, colIndex + 1));
        } else if (colIndex == matrix[0].length - 1) {
            result = matrix[rowIndex][colIndex] + (cache
                    .containsKey(String.valueOf(rowIndex + 1) + '_' + String.valueOf(colIndex))
                            ? cache.get(String.valueOf(rowIndex + 1) + '_' + String.valueOf(colIndex))
                            : process(matrix, rowIndex + 1, colIndex));
        } else {
            result = matrix[rowIndex][colIndex]
                    + Math.min((cache.containsKey(String.valueOf(rowIndex) + '_' + String.valueOf(colIndex + 1))
                            ? cache.get(String.valueOf(rowIndex) + '_' + String.valueOf(colIndex + 1))
                            : process(matrix, rowIndex, colIndex + 1)), 
                            (cache.containsKey(String.valueOf(rowIndex + 1) + '_' + String.valueOf(colIndex))
                                    ? cache.get(String.valueOf(rowIndex + 1) + '_' + String.valueOf(colIndex))
                                    : process(matrix, rowIndex + 1, colIndex)));
        }
        cache.put(String.valueOf(rowIndex) + '_' + String.valueOf(colIndex), result);
        return result;
    }

    // 动态规划
    public int getShortestPathByDynamicProgramming(int[][] matrix){

        int[][] shortestPathRecord = new int[matrix.length][matrix[0].length]; 
        shortestPathRecord[matrix.length-1][matrix[0].length-1] = matrix[matrix.length - 1][matrix[0].length - 1];

        for(int i = matrix.length - 1;i >= 0;i--){
            for(int j = matrix[0].length - 1;j >= 0;j--){
                if(i == matrix.length - 1 && j == matrix[0].length - 1){
                    continue;
                }else if(i == matrix.length - 1){
                    shortestPathRecord[i][j] = shortestPathRecord[i][j + 1] + matrix[i][j];
                }else if(j == matrix[0].length - 1){
                    shortestPathRecord[i][j] = shortestPathRecord[i + 1][j] + matrix[i][j];
                }else{
                    shortestPathRecord[i][j] = Math.min(shortestPathRecord[i][j + 1] + matrix[i][j], 
                            shortestPathRecord[i + 1][j] + matrix[i][j]);
                }
            }
        }
        return shortestPathRecord[0][0];
    }
}
