package ViolenceRecursionAndDynamicProgramming;

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

        // testgetShortestPath();

        testfindSolutionForAim();
    }

    static void testgetShortestPath(){

        int[][] matrix = {{1,3,2},{0,3,4},{5,7,0}};

        ShortestPath shortestPath = new ShortestPath();
        System.out.println(shortestPath.getShortestPath(matrix));

        System.out.println(shortestPath.getShortestPathByDynamicProgramming(matrix));
    }

    static void testfindSolutionForAim(){
        int[] arr = new int[]{1,2,3,4,5,6};
        System.out.println(FindSolutionForAim.findSolutionForAim(arr, 16)); 
    }
}
