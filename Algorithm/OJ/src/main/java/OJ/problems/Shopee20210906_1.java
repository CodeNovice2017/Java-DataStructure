package OJ.problems;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-09-06
 */
public class Shopee20210906_1 {

    public int maxValue(int[] arrs){
        // 利用String进行比较,解决隐含的大数问题
        String[] numsToStringArray = new String[arrs.length];
        for(int i = 0;i < arrs.length; i++){
            numsToStringArray[i] = String.valueOf(arrs[i]);
        }
        quickSort(numsToStringArray,0,numsToStringArray.length-1);
        StringBuilder resultSb = new StringBuilder();
        for(int i = numsToStringArray.length - 1;i >= 0;i--){
            resultSb.append(numsToStringArray[i]);
        }
        return Integer.parseInt(resultSb.toString());
    }

    private void quickSort(String[] arr,int leftIndex,int rightIndex){
        if(leftIndex >= rightIndex){
            return;
        }
        int partitionPointer = leftIndex;
        int lessPointer = leftIndex - 1;
        int morePointer = rightIndex;
        while(partitionPointer < morePointer){
            // 最关键的地方,每次都和划分值字符串拼接,判断arr[partitionPointer]和划分值字符串arr[rightIndex]谁应该放在前面,谁应该放在后面
            if((arr[partitionPointer]+arr[rightIndex]).compareTo((arr[rightIndex]+arr[partitionPointer])) > 0){
                String temp = arr[partitionPointer];
                arr[partitionPointer] = arr[morePointer - 1];
                arr[morePointer - 1] = temp;
                morePointer--;
            }else if((arr[partitionPointer]+arr[rightIndex]).compareTo((arr[rightIndex]+arr[partitionPointer])) < 0){
                String temp = arr[partitionPointer];
                arr[partitionPointer] = arr[lessPointer + 1];
                arr[lessPointer + 1] = temp;
                partitionPointer++;
                lessPointer++;
            }else{
                partitionPointer++;
            }
        }
        String temp = arr[morePointer];
        arr[morePointer] = arr[rightIndex];
        arr[rightIndex] = temp;
        quickSort(arr,leftIndex,lessPointer);
        quickSort(arr,morePointer,rightIndex);
        return;
    }
}
