package OJ;

import OJ.problems.SmallNNumbers20210706;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {

        // ShopeeTest
        //int[][] rooms = new int[3][3];
        //rooms[0] = new int[]{-2, -3, 3};
        //rooms[1] = new int[]{-5, -10, 1};
        //rooms[2] = new int[]{10, 30, -5};
        //int[] startPoint = new int[]{0, 0};
        //int[] endPoint = new int[]{2, 2};
        //Shopee20210705 shopee20210705 = new Shopee20210705();
        //System.out.println(shopee20210705.minimumInitHealth(rooms, startPoint, endPoint));

        SmallNNumbers20210706 smallNNumbers20210706 = new SmallNNumbers20210706();
        Arrays.stream(smallNNumbers20210706.getLeastNumbers(new int[]{3,2,1},2)).forEach(System.out::println);
    }
}
