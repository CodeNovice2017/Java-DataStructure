package OJ.problems;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-30
 */
public class KuangShi20210830 {
    public class Solution {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         *
         *
         * @param nums int整型一维数组 每个跑步机最大步数
         * @return int整型
         */
        public int max_steps (int[] nums) {
            // write code here
            dfs(nums,0,new boolean[nums.length],0);
            return result;
        }

        private int result = 0;

        private void dfs(int[] nums,int currentValue,boolean[] runnable,int count){
            if(count == nums.length){
                result = Math.max(result,currentValue);
                return;
            }
            for(int i = 0;i < nums.length;i++){
                if(!runnable[i]){
                    int countAdd = 1;
                    int leftIndex = -1;
                    int rightIndex = -1;
                    for(int j = i - 1;j >= 0;j--){
                        if(!runnable[j]){
                            countAdd++;
                            runnable[j] = true;
                            leftIndex = j;
                            break;
                        }
                    }
                    for(int j = i + 1;j < nums.length;j++){
                        if(!runnable[j]){
                            countAdd++;
                            runnable[j] = true;
                            rightIndex = j;
                            break;
                        }
                    }
                    runnable[i] = true;
                    dfs(nums,currentValue + nums[i],runnable,count + countAdd);
                    if(leftIndex != -1){
                        runnable[leftIndex] = false;
                    }
                    if(rightIndex != -1){
                        runnable[rightIndex] = false;
                    }
                    runnable[i] = false;
                }
            }
        }
    }
}
