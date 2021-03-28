package ViolenceRecursionAndDynamicProgramming;

public class Redex {
    public boolean isMatch(String s, String p) {

        return isMatchProcess(s, p, 0, 0);

    }

    private boolean isMatchProcess(String s, String p, int indexS, int indexP) {
        // Base Case
        // 只有indexS和indexP都遍历完了,一起到了尾部才返回true
        // 否则当indexP遍历完,但是indexS还未遍历完,就返回false
        if (indexP == p.length()) {
            return indexS == s.length();

            
        }

        // 当indexP是最后一个字符时或者indexP + 1不等于*时,这两种情况都无需考虑多个s与p匹配的情况
        if (indexP + 1 == p.length() || p.charAt(indexP + 1) != '*') {
            // if ((indexS < s.length()) && (s.charAt(indexS) == p.charAt(indexP) || p.charAt(indexP) == '.')) {
            //     return isMatchProcess(s, p, indexS + 1, indexP + 1);
            // }


        }
        // 否则当indexP既不是最后一位,且p.charAt(indexP+1) == '*' 
        else {
            int i = indexS;
            // while (i < s.length()) {
            //     if (s.charAt(i) == p.charAt(indexP) || p.charAt(indexP) == '.') {
            //         if (isMatchProcess(s, p, i + 1, indexP + 2)) {
            //             return true;
            //         }
            //     }
            //     i++;
            // }

            // 之前这个循环的写法一直有问题,因为只有当(s.charAt(i) == p.charAt(indexP) || p.charAt(indexP) == '.')匹配,
            // 并且i < s.length()的时候才应该下一步分析,而如果分开写就变成了不管是否连续的多个s和p匹配了,只要s还没遍历到s.length,
            // 就继续跨过这个不匹配的字符继续看下一个位置的indexS~s.length()-1和indexP~p.length()-1是否匹配
            // 这样肯定是有问题的
            while(i < s.length() && (s.charAt(i) == p.charAt(indexP) || p.charAt(indexP) == '.')){
                if (isMatchProcess(s, p, i + 1, indexP + 2)) {
                    return true;
                }
                i++;
            }
        }
        return isMatchProcess(s, p, indexS, indexP + 2);
    }
}
