package org.codenovice;

public class Person{
    private int age;

    public Person(int age) {
        this.age = age;
    }

    
    //实现compareTo方法,告诉我如果传入的是另一个Person对象,那么如何进行比较
    // @Override
    // public int comparaTo(Person e) {
    //     // TODO Auto-generated method stub
    //     // 一种不聪明的判断方法
    //     // if(age > e.age){
    //     //     return 1;
    //     // }else if(age < e.age){
    //     //     return -1;
    //     // }
    //     // return 0;
    //     // 一句话解决
    //     return age - e.age;
    // }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [age=" + age + "]";
    }

}
