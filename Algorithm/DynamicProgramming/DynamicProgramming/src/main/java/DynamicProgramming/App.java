package DynamicProgramming;

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

        testNdicesProblem();
    }

    static void testNdicesProblem(){
        NdicesProblem.Solution solution = new NdicesProblem.Solution();
        solution.dicesProbability(3);
    }
}
