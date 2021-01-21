package ViolenceRecursionAndDynamicProgramming;

public class Factorial {
    
    public static long getFactorial(int n){
        if(n == 1){
            return 1L;
        }
        return (long)n*getFactorial(n-1);
    }

    public static long getFactorialNonRecursion(int n){
        long result = 1L;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

}
