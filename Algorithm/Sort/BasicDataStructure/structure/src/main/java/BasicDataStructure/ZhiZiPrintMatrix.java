package BasicDataStructure;

public class ZhiZiPrintMatrix {

    public static final int UP_TO_DOWN = 1;
    public static final int DOWN_TO_UP = -1;

    public static void zhiZiPrintMatrixFunction(int[][] matrix){

        int direction = DOWN_TO_UP;
        int row1 = 0;
        int col1 = 0;
        int row2 = 0;
        int col2 = 0;

        int endCol1Index = matrix[0].length - 1;
        int endRow2Index = matrix.length - 1;

        while(row1 <= endRow2Index){
            printOneLine(matrix, row1, col1, row2, col2, direction);
            col1 = col1 != endCol1Index ? col1 + 1 : col1;
            row1 = col1 != endCol1Index ? 0 : row1 + 1;
            row2 = row2 != endRow2Index ? row2 + 1 : row2;
            col2 = row2 != endRow2Index ? 0 : col2 + 1;
            if (direction == UP_TO_DOWN) {
                direction = DOWN_TO_UP;
            } else {
                direction = UP_TO_DOWN;
            }
        }
    }

    public static void printOneLine(int[][] matrix,int row1,int col1,int row2,int col2,int direction){
        if(row1 == row2){
            System.out.println(matrix[row1][col1] + " ");
        }else if(direction == UP_TO_DOWN){
            while(row1 <= row2){
                System.out.println(matrix[row1++][col1--] + " ");
            }
        }else if(direction == DOWN_TO_UP){
            while(row1 <= row2){
                System.out.println(matrix[row2--][col2++] + " ");
            }
        }
    }
}
