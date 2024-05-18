package com.example.studytoursystem.utils;

import java.util.*;

public class InsertSort {

    /**
     * 插入排序，根据对象的某个属性对List进行排序。
     * @param list 要排序的对象列表
     * @param comparator 比较器，用于比较对象的指定属性
     * @param <T> 对象类型
     */
    public static <T> void insertSort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() <= 1) {
            return;
        }

        for (int i = 1; i < list.size(); i++) {
            T current = list.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(current, list.get(j)) < 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, current);
        }
    }
}