/**
 * @Classname Main
 * @Date 2020/3/22 20:17
 * @Created by Veigar
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new linkedList<Integer>();
        list.add(20);
        list.add(0,10);
        list.add(30);
        list.add(list.size(),40);
        list.remove(1);
        System.out.println(list);
    }
}
