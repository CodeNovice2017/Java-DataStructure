package BinaryTree;

// 折纸问题
public class OrigamiProblem {

    public static void printAllFolds(int N){
        printProcess(1,N,true);
    }
    
    // 如果down是true的话,表示来到的是下这个左子节点
    // i表示当前在第几层
    // N表示一共有几层
    public static void printProcess(int i,int N,boolean down){
        // 如果i来到最后一层就返回
        if(i > N){
            return;
        }
        // 否则每次都是先跑下一层的左子节点(下折痕)打印递归,然后打印自己,然后右子节点的打印递归
        // 因为当前层产生的下上折痕都在上一层的下/上折痕的左右
        // 只有先打印当前层的下折痕(及其因为当前层下折痕产生的下上折痕)才能打印上一层的折痕,
        // 然后打印当前层的右折痕(及其因为当前层上折痕产生的下上折痕)
        printProcess(i + 1, N, true);
        System.out.println(down ? "down " : "up ");
        printProcess(i + 1, N, false);
    }
}
