package OJ.problems;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-09-05
 */
public class Tencent20210905_3 {

    public static void func(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        //for()
    }

    private static int[] getValue(int value){
        int[] result = new int[3];
        int res1 = value/2;
        int res2 = (value & 1) == 1 ? 1 : 0;
        result[0] = res1;
        result[2] = res1;
        result[1] = res2;

        return result;
    }

}
