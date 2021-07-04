package OJ.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-07-04
 */
public class NQueens20210704 {
    private List<List<String>> result = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        dfs(0,new int[n][n]);
        return result;
    }
    private void dfs(int row,int[][] selected){
        if(row == selected.length){
            List<String> part = new ArrayList<>();
            for(int i = 0;i < selected.length;i++){
                StringBuilder sb = new StringBuilder();
                for(int j = 0;j < selected[0].length;j++){
                    if(selected[i][j] == 2){
                        sb.append("Q");
                    }else{
                        sb.append(".");
                    }
                }
                part.add(sb.toString());
            }
            result.add(part);
            return;
        }
        for(int i = 0;i < selected[0].length;i++){
            if(selected[row][i] == 0){
                int[][] selectedCopy = Arrays.copyOf(selected,selected.length);
                // 同一行全设置为1
                for(int k = 0;k < selected[0].length;k++){
                    selected[row][k] = 1;
                }
                // 同一列全设置为1
                for(int k = 0;k < selected.length;k++){
                    selected[k][i] = 1;
                }
                int rowIndex = row;
                int colIndex = i;
                // 45度方向全设置为1
                while(rowIndex >= 0 && colIndex >= 0){
                    selected[rowIndex--][colIndex--] = 1;
                }
                rowIndex = row;
                colIndex = i;
                while(rowIndex >= 0 && colIndex < selected[0].length){
                    selected[rowIndex--][colIndex++] = 1;
                }
                rowIndex = row;
                colIndex = i;
                while(rowIndex < selected.length && colIndex >= 0){
                    selected[rowIndex++][colIndex--] = 1;
                }
                rowIndex = row;
                colIndex = i;
                while(rowIndex < selected.length && colIndex < selected[0].length){
                    selected[rowIndex++][colIndex++] = 1;
                }
                selected[row][i] = 2;
                dfs(row + 1,selected);
                selected = selectedCopy;
            }
        }
    }
}
