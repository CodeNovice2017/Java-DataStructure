package OJ.problems;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-07-04
 */
public class SlideWindowMaxValues20210704 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0){
            return new int[0];
        }
        int[] result = new int[nums.length - k + 1];
        int index = 0;
        // 创建一个单调递减队列
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        for(int i = 1;i < nums.length;i++){
            if(i < k){
                while(!queue.isEmpty() && nums[queue.peekLast()] < nums[i]){
                    queue.removeLast();
                }
                queue.offer(i);
                if(i == k - 1){
                    result[index++] = nums[queue.peekFirst()];
                }
                continue;
            }
            while(!queue.isEmpty() && nums[queue.peekLast()] < nums[i]){
                queue.removeLast();
            }
            queue.offer(i);
            if(i - queue.peekFirst() == k){
                queue.removeLast();
            }
            result[index++] = nums[queue.peekFirst()];

        }
        return result;
    }
}
