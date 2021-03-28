package DynamicProgramming;

// 剑指Offer 60 n个骰子的点数问题

public class NdicesProblem{

    public static class Solution {

        public int[][] cache;

        public double[] dicesProbability(int n) {
            // n个骰子扔地上,最少n点,最多6n点
            // n个骰子的所有点数的全排列是6^n
            // 我们需要计算的是n个骰子掷出n点~6n点的所有不同的排列占全排列的比例
            // 就比如n个骰子掷出n点那肯定只有一种情况,就是n个骰子都是1点,那么对应的概率就是1/6^n
            // 而再比如n个骰子掷出n+1点,那么肯定是n-1个骰子掷出1点,一个骰子掷出2点,这个掷出两点的骰子可以是1号骰子~n号骰子,所以n+1点数的出现次数就是n
            // 概率就是n/6^n

            // 对应掷出n~6n点数出现的次数统计
            int[] countValue = new int[5 * n + 1];
            // 0代表n点 5n代表6n点
            double[] resultProbability = new double[5 * n + 1];

            // 建立傻缓存
            // 初始化时最多剩n个骰子,6*n剩余值
            cache = new int[n + 1][6 * n + 1];

            // i从n开始一直计算到等于6n时刻
            for (int i = n; i <= 6 * n; i++) {
                processByRecursion(n, i, countValue, i - n);
            }
            // 全排列个数
            double fullPermutation = 0;
            for (int i = 0; i < countValue.length; i++) {
                fullPermutation += countValue[i];
            }
            for (int i = 0; i < resultProbability.length; i++) {
                resultProbability[i] = (double) countValue[i] / fullPermutation;
            }
            return resultProbability;
        }
        // remain代表当前还剩下几个骰子没掷出,value代表还要掷出的点数和,countValue数组用于统计成功掷出规定点数的次数,n为本次递归需要掷出的点数对应的数组下标

        // 为了添加傻缓存,需要将递归函数改为返回int,返回的是当有remain个骰子,value的剩余点数,有多少组排列匹配
        private int processByRecursion(int remain, int value, int[] countValue, int n) {
            // Base Case
            // 包括:
            // 当下还有骰子没有掷出,但是还要掷出的点数和value已经为负或者为0
            // 当下骰子已经都掷出,但是还要掷出的点数和value仍大于0
            if (remain < 0 || value < 0) {
                return 0;
            }

            if (remain == 0) {
                if (value == 0) {
                    countValue[n]++;
                    return 1;
                } else {
                    return 0;
                }
            }

            // 如果剩余骰子个数比剩余点数还大
            if (remain > value) {
                return 0;
            }

            // remain > 0 && value > 0 情况分析,并且排除上述Base Case
            for (int i = 1; i <= 6; i++) {
                // 剪枝操作
                if (value - i < 0) {
                    break;
                } else {
                    if (cache[remain - 1][value - i] != 0) {
                        cache[remain][value] += cache[remain - 1][value - i];
                    } else {
                        int cacheValue = processByRecursion(remain - 1, value - i, countValue, n);
                        cache[remain][value] += cacheValue;
                    }
                }
            }
            return cache[remain][value];
        }
    }
}
