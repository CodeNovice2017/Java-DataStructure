package BasicDataStructure;

public class SortedRowAndColMatrixFindValue {
    
    public static boolean hasValue(int[][] matrix,int value){

        int rowIndex = 0;
        int colIndex = matrix[0].length - 1;

        while(rowIndex <= matrix.length-1 && colIndex >= 0){
            if(matrix[rowIndex][colIndex] > value){
                colIndex--;
            }else if(matrix[rowIndex][colIndex] < value){
                rowIndex++;
            }else{
                return true;
            }
        }
        return false;
    }
}
