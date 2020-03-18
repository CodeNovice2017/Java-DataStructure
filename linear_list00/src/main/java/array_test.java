
import java.util.ArrayList;

/**
 * @Classname array_test
 * @Date 2020/3/15 9:06
 * @Created by Veigar
 */
public class array_test {
    public static void main(String[] args) {
        int[] array1 = new int[]{11,22,33};

        //自己编写arrayList动态数组
        arrayList<Person> persons = new arrayList<>();
        persons.add(new Person(10,"Jack"));
        persons.add(new Person(12,"Jack"));
        persons.add(new Person(13,"Jack"));
        persons.add(new Person(14,"Jack"));
        persons.add(new Person(15,"Jack"));
        System.out.println(persons);
        persons.clear();
        //提醒JVM进行垃圾回收
        System.gc();



        arrayList<Integer> ints = new arrayList<>();
        ints.add(10);
        ints.add(10);
        ints.add(10);
        ints.add(10);
        ints.add(10);
        System.out.println(ints);
        //相当于左移1位
//        int a = 4;
//        a = a << -63;
//        System.out.println(a);
    }
}


