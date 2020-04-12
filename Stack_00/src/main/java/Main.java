/**
 * package_name: PACKAGE_NAME
 * project_name: Stack_00
 * creat_user: Veigar
 * creat_date: 2020/4/12
 * creat_time: 15:05
 **/
public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);

        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
