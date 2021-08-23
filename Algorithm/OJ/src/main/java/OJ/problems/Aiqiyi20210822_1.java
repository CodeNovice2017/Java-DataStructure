package OJ.problems;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-22
 */
public class Aiqiyi20210822_1 {
    public static void func(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        String s = sc.nextLine();
        String[] sArr = s.split(",");
        char[] arr = sArr[0].toCharArray();
        int leftIndex = Integer.parseInt(sArr[1]);
        int rightIndex = Integer.parseInt(sArr[2]);
        for(int i = leftIndex,j = rightIndex;i < j;i++,j--){
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        System.out.println(new String(arr));
    }
}
