package sort;

import java.util.Arrays;

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
    public static void main(String[] args) {
        System.out.println("Hello World!");

        selectTest();
        
    }

    static void bubbleTest(){
        int arr[] = {7,6,5,4,3,2,1};

        BubbleSort.bubbleSort(arr);

        Arrays.stream(arr).forEach(System.out::println);
    }

    static void selectTest() {
        int arr[] = { 7, 6, 5, 4, 3, 2, 1 };

        SelectionSort.selectionSort(arr);

        Arrays.stream(arr).forEach(System.out::println);
    }
}
