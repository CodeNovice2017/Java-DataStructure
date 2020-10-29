package RBTree;

import RBTree.printer.BinaryTrees;
import RBTree.tree.RBTree;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    static void test1() {
        Integer data[] = new Integer[] { 55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50 };

        RBTree<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
            System.out.println("【" + data[i] + "】");
            BinaryTrees.println(rb);
            System.out.println("---------------------------------------");
        }
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        test1();
    }
}
