package com.example.studytoursystem.utils;

import java.util.Arrays;

public class MyPriorityQueue<T extends Comparable<T>> {
    private T[] heap;
    private int size;

    public MyPriorityQueue() {
        heap = (T[]) new Comparable[10];
        size = 0;
    }

    public void add(T value) {
        // 如果数组已满，则扩容
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, size * 2);
        }

        // 将新元素插入到堆的末尾
        heap[size++] = value;

        // 对堆进行上浮操作，保证新元素按照优先级排列
        siftUp(size - 1);
    }

    public T peek() {
        if (size == 0) {
            return null;
        }

        return heap[0];
    }

    public T remove() {
        if (size == 0) {
            return null;
        }

        // 取出堆顶元素
        T result = heap[0];

        // 将堆顶元素替换为堆底元素
        heap[0] = heap[--size];
        heap[size] = null;

        // 对堆进行下沉操作，保证堆仍然满足堆的性质
        siftDown(0);

        return result;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap[index].compareTo(heap[parent]) > 0) {
                // 如果当前节点比父节点优先级高，则交换它们的位置
                swap(index, parent);
                index = parent;
            } else {
                // 如果当前节点的优先级比父节点低，则不需要继续上浮
                break;
            }
        }
    }

    private void siftDown(int index) {
        while (index < size) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int max = index;

            // 找出当前节点、左节点和右节点中优先级最高的节点
            if (left < size && heap[left].compareTo(heap[max]) > 0) {
                max = left;
            }

            if (right < size && heap[right].compareTo(heap[max]) > 0) {
                max = right;
            }

            if (max != index) {
                // 如果当前节点不是最高优先级，则将它与最高优先级的节点交换
                swap(max, index);
                index = max;
            } else {
                // 如果当前节点已经是最高优先级了，则不需要继续下沉
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
