package OJ;

import OJ.problems.Shopee20210705;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class App {

    public static void main(String[] args) {
        int[][] rooms = new int[3][3];
        rooms[0] = new int[]{-2, -3, 3};
        rooms[1] = new int[]{-5, -10, 1};
        rooms[2] = new int[]{10, 30, -5};
        int[] startPoint = new int[]{0, 0};
        int[] endPoint = new int[]{2, 2};
        Shopee20210705 shopee20210705 = new Shopee20210705();
        System.out.println(shopee20210705.minimumInitHealth(rooms, startPoint, endPoint));
    }

    public void A() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        HashSet<String> visited = new HashSet<>();
        int[][] gra = new int[200][200];
        PriorityQueue<String> priorityQueue = new PriorityQueue<String>((a, b) -> {
            return hashMap.get(a) - hashMap.get(b);
        });
        hashMap.put(0 + "@" + 0, -2);
        priorityQueue.add(0 + "@" + 0);
        while (priorityQueue.size() > 0) {
            String[] tem = priorityQueue.poll().split("@");
            int x = Integer.valueOf(tem[0]);
            int y = Integer.valueOf(tem[1]);
            int val  = hashMap.get()
        }
    }
}
