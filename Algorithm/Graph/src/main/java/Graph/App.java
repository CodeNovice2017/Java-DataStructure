package Graph;

import java.util.Set;

import Graph.GraphAlgorithm.Prim;

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

        graphAlgorithmTest();
    }

    static void graphAlgorithmTest(){
        Integer[][] matrix = {{1,2,1},{2,1,1},{2,3,1},{3,2,1},{2,4,3},{4,2,3},{3,4,4},{4,3,4},{1,3,2},{3,1,2},{1,4,2},{4,1,2}};
        Graph graph = GraphGenerator.createGraph(matrix);
        Set<Edge> set1 = Prim.primMST(graph);
        Set<Edge> set2 = Prim.primByMySelf(graph);
        System.out.println(set1);
        System.out.println(set2);
    }
}
