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

        // bubbleTest();

        // selectTest();

        // insertTest();

        // getMaxTest();
        
        mergeSortTest();
        
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

    static void insertTest() {
        int arr[] = { 7, 6, 5, 4, 3, 2, 1 };

        InsertionSort.insertionSort(arr);

        Arrays.stream(arr).forEach(System.out::println);
    }

    static void getMaxTest(){

        int arr[] = { 8,9,7, 6, 5, 4, 3, 2, 1 };
        int result = GetArrayMax.getArrayMax(arr, 0, arr.length-1);
        System.out.println(result);
    }

    static void mergeSortTest() {
        int arr[] = { 8, 9, 7, 6, 5, 4, 3, 2, 1 };

        MergeSort.mergeSort(arr);

        Arrays.stream(arr).forEach(System.out::println);
    }
}
