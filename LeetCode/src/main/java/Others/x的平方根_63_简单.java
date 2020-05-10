package Others;

public class x的平方根_63_简单 {
    // 袖珍计算器算法
    // 袖珍计算器算法是一种用指数函数exp和对数函数ln代替平方根函数的方法。我们通过有限的可以使用的数学函数，得到我们想要计算的结果
    // 同时对于java而言,当出现result用int保存时(result + 1) * (result + 1)会在x = 2147483647时 计算为-2147479015,这是超出了int范围越界了,所以算出的(result + 1) * (result + 1)为负数
    // 而如果像Solution2的写法,会发现result=46341,比原本的结果大1,这是因为由于计算机无法存储浮点数的精确值（浮点数的存储方法可以参考IEEE 754，这里不再赘述），而指数函数和对数函数的参数和返回值均为浮点数，因此运算过程中会存在误差
    // 因此在得到结果的整数部分result后，我们应当找出result与result+1中哪个是真正的答案
    public class Solution1 {
        public int mySqrt(int x) {
            if (x == 0 || x == 1) {
                return x;
            } else {
                long result = (long) Math.exp(0.5 * Math.log(x));
                long temp = (result + 1) * (result + 1) ;
                if (temp <= x) {
                    return (int)result + 1;
                } else {
                    return (int)result;
                }
            }
        }
    }
    //错误实例:与Solution1相比较
    public class Solution2 {
        public int mySqrt(int x) {
            if (x == 0 || x == 1) {
                return x;
            } else {
                int result = (int) Math.exp(0.5 * Math.log(x));
                return result;
            }
        }
    }

    //暴力破解法(不要忘了退出循环)
    public class Solution3 {
        public int mySqrt(int x) {
            long result = 0;
            if (x == 1||x == 0) {
                return x;
            }
            for (long i = 1; i <= x / 2; i++) {
                if ((i * i) <= x && ((i + 1) * (i + 1)) > x) {
                    result = i;
                    break;
                }
            }
            return (int)result;
        }
    }

    //
}
