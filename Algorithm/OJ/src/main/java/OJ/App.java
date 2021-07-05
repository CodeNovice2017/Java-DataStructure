package OJ;

import static OJ.problems.Shopee20210705.minimumInitHealth;

public class App {

    public static void main(String[] args) {

        //System.out.println(compressString("aabcccccaaa"));
        //System.out.println(petalsBreak(new int[]{4,2,1}));

        int[][] rooms = new int[3][3];
        rooms[0] = new int[]{-2,-3,3};
        rooms[1] = new int[]{-5,-10,1};
        rooms[2] = new int[]{10,30,-5};
        int[] startPoint = new int[]{0,0};
        int[] endPoint = new int[]{2,2};
        System.out.println(minimumInitHealth(rooms,startPoint,endPoint));
    }
}
