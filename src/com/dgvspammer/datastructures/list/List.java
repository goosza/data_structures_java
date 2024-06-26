package com.dgvspammer.datastructures.list;

public interface List<E> {
    void add(E e);
    void remove(int index);
    E get(int index);
    int size();
    boolean isEmpty();
    boolean contains(E e);
    void clear();
}
