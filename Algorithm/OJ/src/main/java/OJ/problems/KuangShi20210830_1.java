package OJ.problems;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-30
 */
public class KuangShi20210830_1 {
    public class Solution {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         *
         * 可以到达时返回True，否则返回False
         * @param arr int整型一维数组 非负整数数组
         * @param start int整型 起始下标
         * @return bool布尔型
         */
        public boolean jump_game (int[] arr, int start) {
            // write code here
            dfs(arr,start,new boolean[arr.length]);
            return result;
        }

        private boolean result = false;

        private void dfs(int[] arr,int currentIndex,boolean[] visited){
            if(arr[currentIndex] == 0){
                result = true;
                return;
            }
            if(!result){
                if(!visited[currentIndex]){
                    visited[currentIndex] = true;
                    int leftIndex = currentIndex - arr[currentIndex];
                    if(leftIndex >= 0){
                        dfs(arr,leftIndex,visited);
                    }
                    int rightIndex = currentIndex + arr[currentIndex];
                    if(rightIndex < arr.length){
                        dfs(arr,rightIndex,visited);
                    }
                }
            }
        }
    }
}
