package BasicDataStructure;

public class CirclePrintMatrix {
    
    public static void spiralOrder(int matrix[][]){
        int row1 = 0;
        int col1 = 0;
        int row2 = matrix.length-1;
        int col2 = matrix[0].length-1;
        while(row1 <= row2 && col1 <= col2){
            printEdge(matrix, row1++, col1++, row2--, col2--);
        }
    }
    // 只打印一圈的函数
    // row1,col1是最左上角的横纵坐标,row2,col2是最右下角的横纵坐标
    // 然后我其实可以依次想剥洋葱一样,依次向里面的圈剥,这就是一个宏观控制
    public static void printEdge(int[][] matrix,int row1,int col1,int row2,int col2){
        // 原矩阵只有一行的情况 int[1][N]这种形式的数组
        if(row1 == row2){
            for(int i = col1;i<=col2;i++){
                System.out.println(matrix[row1][i] + " ");
            }
        }else if(col1 == col2){
            for(int i = row1;i<=row2;i++){
                System.out.println(matrix[i][col1] + " ");
            }
        }else{
            int currentColPointer = col1;
            int currentRowPointer = row1;
            while(currentColPointer != col2){
                System.out.println(matrix[currentRowPointer][currentColPointer++] + " ");
            }
            while(currentRowPointer != row2){
                System.out.println(matrix[currentRowPointer++][currentColPointer] + " ");
            }
            while(currentColPointer != col1){
                System.out.println(matrix[currentRowPointer][currentColPointer--] + " ");
            }
            while (currentRowPointer != row1) {
                System.out.println(matrix[currentRowPointer--][currentColPointer] + " ");
            }
        }
    }
}
