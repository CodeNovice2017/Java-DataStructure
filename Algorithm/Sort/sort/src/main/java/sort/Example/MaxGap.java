package sort.Example;

import java.util.Arrays;

public class MaxGap {
    
    public static int getMaxGap(int arr[]){

        if (arr == null || arr.length < 2) {
            return 0;
        }

        int length = arr.length;

        int maxValue = Arrays.stream(arr).max().getAsInt();
        int minValue = Arrays.stream(arr).min().getAsInt();

        if(maxValue == minValue){
            return 0;
        }

        // 我原本想用一个二维数组来存储每个桶的信息
        // int[][] bucket = new int[3][length];

        // 左神的写法是用三个数组来存储每个桶的信息
        boolean[] hasNum = new boolean[length + 1];

        int[] max = new int[length + 1];

        int[] min = new int[length + 1];

        for (int i = 0; i < arr.length; i++) {
            
            int bucketIndex = bucket(arr[i],length,minValue,maxValue);

            // 不能直接判断最大值,即使原本max[bucketIndex]有初始值0,但是我的数组万一都比0小,那么就会出现这个桶有值,但是最大值是0,0又不是我数组的值
            max[bucketIndex] = hasNum[bucketIndex] ? Math.max(arr[i], max[bucketIndex]) : arr[i];

            min[bucketIndex] = hasNum[bucketIndex] ? Math.min(arr[i], min[bucketIndex]) : arr[i];

            hasNum[bucketIndex] = true;

        }
        int bucketIndex = 1;

        int lastMax = max[bucketIndex]; // 含义是上一个最大值 因为第一个桶肯定至少有一个值,那么它就有最大值和最小值,我们只需要第一个桶提供最大值

        int result = 0;

        while(bucketIndex < length){

            if(hasNum[bucketIndex]){

                result = (min[bucketIndex] - lastMax) > result ? (min[bucketIndex] - lastMax) : result;

                lastMax = max[bucketIndex];

            }
            bucketIndex++;

        }
        return result;

    }

    // (卡壳的地方)bucket方法用于计算数组中的一个数据num,通过数组长度和数组的最小值和最大值计算出应该放入哪个桶
    private static int bucket(long num,long len,long min,long max){
        return (int)((num - min) * len/(max - min));
    }

}
