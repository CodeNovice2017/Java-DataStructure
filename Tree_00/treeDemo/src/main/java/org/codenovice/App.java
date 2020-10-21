package org.codenovice;

import java.util.ArrayList;
import java.util.List;

import org.codenovice.BinarySearchTree.Node;
import org.codenovice.printer.BinaryTrees;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */

     private static class PersonComparator implements Comparator<Person> {
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }
    
    private static class PersonComparator2 implements Comparator<Person> {
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }

    //Integer传入bst测试
    static void testInteger(){
        // BST测试
        Integer[] data = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3 };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
    }
    //Person对象传入bst测试
    static void testPerson(){
        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new PersonComparator());
        Integer[] data = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3 };
        for (int i = 0; i < 8; i++) {
            bst2.add(new Person(data[i]));
        }

        //这样直接打印是打印的org.codenovice.Person@7c53a9eb形式个很多Person对象
        BinaryTrees.println(bst2);
    }
    //随机数创建二叉搜索树bst测试
    static void testRandomInteger() {
        // BST测试

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < 30; i++) {
            //random方法一定要*100整个加括号然后转换为int,否则相当于random先转int,random本身是0-1,取不到1的小数,那么自然转为int后就一直是0,*100依然还是0
            bst.add((int)(Math.random()*100));
        }
        BinaryTrees.println(bst);

        bst.preorderTraversal();
        System.out.println("==========================");
        bst.preorderTraversalNonRecursive();
        System.out.println("==========================");
        // bst.inorderTraversalNonRecursive();
        
        System.out.println("==========================");
        bst.inorderTraversalNonRecursive();
        System.out.println("==========================");

        System.out.println("==========================");
        bst.postorderTraversal();
        System.out.println("==========================");

        System.out.println("==========================");
        bst.levelOrderTraversal();
        System.out.println("==========================");
    }

    static void testPredecessor(){

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < 30; i++) {
            // random方法一定要*100整个加括号然后转换为int,否则相当于random先转int,random本身是0-1,取不到1的小数,那么自然转为int后就一直是0,*100依然还是0
            bst.add((int) (Math.random() * 100));
        }
        bst.inorderTraversalNonRecursive();
        List<Node<Integer>> list = new ArrayList<>();
        bst.getInorderTraversalList(bst.getRNode(), list);
        System.out.println(bst.predecessor(list.get(5)).element);
    }

    public static void main(String[] args) {

        System.out.println("Hello World!");

        // testInteger();

        // testPerson();

        // testRandomInteger();

        testPredecessor();
    }
        
}
