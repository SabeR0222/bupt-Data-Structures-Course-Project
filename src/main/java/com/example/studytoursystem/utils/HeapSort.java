package com.example.studytoursystem.utils;

import java.util.Comparator;


import java.util.List;

public class HeapSort<T> {
    public void sort(List<T> list, Comparator<? super T> comparator) {
        T[] array = list.toArray((T[]) new Object[0]);
        sort(array, comparator);
        list.clear();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
    }

    public void sort(T[] array, Comparator<? super T> comparator) {
        int n = Math.min(array.length, 10); // 只排序前10个元素

        // 构建大顶堆
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i, comparator);

        // 一个个取出堆顶元素并放到数组末尾，每次移除堆顶元素后调整堆
        for (int i = n - 1; i > 0; i--) {
            // 移动当前根到数组末尾
            T temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // 调整剩余元素为大顶堆
            heapify(array, i, 0, comparator);
        }
    }

    private void heapify(T[] array, int n, int i, Comparator<? super T> comparator) {
        int largest = i; // 初始化最大元素为根节点
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // 如果左子节点大于根节点
        if (left < n && comparator.compare(array[left], array[largest]) > 0)
            largest = left;

        // 如果右子节点大于当前最大节点
        if (right < n && comparator.compare(array[right], array[largest]) > 0)
            largest = right;

        // 如果最大元素不是根节点
        if (largest != i) {
            // 交换
            T temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;

            // 对根节点重新调整
            heapify(array, n, largest, comparator);
        }
    }
}
