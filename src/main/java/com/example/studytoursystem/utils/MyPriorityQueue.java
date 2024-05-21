package com.example.studytoursystem.utils;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MyPriorityQueue<T> {
    private Object[] queue;
    private int size;
    private final Comparator<? super T> comparator;

    public int size(){
        return size;
    }

    public MyPriorityQueue(Comparator<? super T> comparator) {
        this.comparator = comparator;
        queue = new Object[11]; // 初始容量，可根据需要调整
    }

    private void resize(int capacity) {
        assert capacity > size;
        Object[] temp = new Object[capacity];
        System.arraycopy(queue, 0, temp, 0, size);
        queue = temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T item = (T) queue[0];
        queue[0] = queue[--size];
        siftDown(0);
        return item;
    }

    public T peek() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return (T) queue[0];
    }

    public void offer(T item) {
        if (size == queue.length) {
            resize(size * 2);
        }
        queue[size++] = item;
        siftUp(size - 1);
    }

    private void siftUp(int k) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            if (comparator.compare((T) queue[k], (T) queue[parent]) >= 0) {
                break;
            }
            swap(k, parent);
            k = parent;
        }
    }

    private void siftDown(int k) {
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            int right = child + 1;
            if (right < size && comparator.compare((T) queue[right], (T) queue[child]) < 0) {
                child = right;
            }
            if (comparator.compare((T) queue[k], (T) queue[child]) <= 0) {
                break;
            }
            swap(k, child);
            k = child;
        }
    }

    private void swap(int i, int j) {
        Object tmp = queue[i];
        queue[i] = queue[j];
        queue[j] = tmp;
    }
}