package OJ.problems;

import java.io.BufferedInputStream;
import java.util.*;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-08-31
 */
public class PinDuoDuo20210831_2 {

    public static void func() {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        sc.nextLine();
        String[] arr = new String[n];
        HashMap<String,Integer> map = new HashMap<>();
        for(int i = 0;i < arr.length;i++){
            arr[i] = sc.nextLine();
            map.put(arr[i],i);
        }
        Arrays.sort(arr,(item1,item2) -> {
            return charge(item1,item2,map);
        });
        for(int i = 0;i < arr.length;i++){
            System.out.println(arr[i]);
        }

    }

    private static int charge(String item1,String item2,HashMap<String,Integer> map){
        char[] item1Arr = item1.toCharArray();
        char[] item2Arr = item2.toCharArray();

        StringBuilder item1Small = new StringBuilder();
        StringBuilder item1Big = new StringBuilder();
        StringBuilder item2Small = new StringBuilder();
        StringBuilder item2Big = new StringBuilder();
        swap(item1Small,item1Big,item1Arr);
        swap(item2Small,item2Big,item2Arr);
        if(item1Small.toString().compareTo(item2Small.toString()) < 0){
            return -1;
        }else if(item2Small.toString().compareTo(item1Small.toString()) < 0){
            return 1;
        }else{
            return 0;
        }
    }
    
    private static void swap(StringBuilder small,StringBuilder big,char[] arr){
        for(int i = 0;i < arr.length/2;i++){
            int swapIndex = arr.length - i - 1;
            if(arr[i] > arr[swapIndex]){
                char temp = arr[i];
                arr[i] = arr[swapIndex];
                arr[swapIndex] = temp;
            }
            small = new StringBuilder(String.valueOf(arr));
            big = small.reverse();
        }
    }
}
