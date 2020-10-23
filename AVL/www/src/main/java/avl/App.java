package avl;

import avl.printer.BinaryTrees;
import avl.tree.AVLTree;

/**
 * Hello world!
 *
 */
public class App 
{
    static void test1() {
        Integer data[] = new Integer[] { 4 ,7, 9, 2, 5, 8, 11, 3, 12, 1 };

        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
        }

        BinaryTrees.println(avl);
    }
    public static void main(String[] args) {
        test1();
    }
}
