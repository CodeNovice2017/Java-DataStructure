package org.codenovice;

//一个接口,用于让每一个需要处理的E元素都要实现compareTo这个方法
public interface Comparable<E> {
    int comparaTo(E e);
}
