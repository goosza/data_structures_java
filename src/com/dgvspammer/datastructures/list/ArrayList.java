package com.dgvspammer.datastructures.list;

import java.util.Arrays;

public class ArrayList<E> implements List<E>{
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(E e) {
        if (this.size == this.elements.length) {
            ensureCapacity();
        }
        this.elements[this.size++] = e;
    }

    private void ensureCapacity() {
        int newSize = elements.length * 2;
        this.elements = Arrays.copyOf(this.elements, newSize);
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= this.size){
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // Clear to let GC do its work
    }

    private void arraycopy(Object src, int srcPos, Object dest, int destPos, int length){
        if (src == null || dest == null) {
            throw new NullPointerException("elements or dest is null");
        }
        if (!src.getClass().isArray() || !dest.getClass().isArray()) {
            throw new ClassCastException("elements or dest is not an array");
        }
        if (srcPos < 0 || destPos < 0 || length < 0) {
            throw new IllegalArgumentException("srcPos or destPos or length is negative");
        }
        if (srcPos + length > java.lang.reflect.Array.getLength(src) || destPos + length > java.lang.reflect.Array.getLength(dest)) {
            throw new ArrayIndexOutOfBoundsException("Array index out of bounds");
        }
        for (int i = 0; i < length; i++) {
            java.lang.reflect.Array.set(dest, destPos + i, java.lang.reflect.Array.get(src, srcPos + i));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        return (E)this.elements[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(E e) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(e))
                return true;
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.elements[i] = null;
        }
        size = 0;
    }
}
