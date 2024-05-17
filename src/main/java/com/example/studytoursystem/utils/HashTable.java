package com.example.studytoursystem.utils;

import java.util.LinkedList;

public class HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private final LinkedList<Entry<K, V>>[] buckets;
    private int size;

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    public HashTable(int capacity) {
        this.buckets = new LinkedList[capacity];
        this.size = 0;
    }

    // 计算键的哈希值并确定其在数组中的索引
    private int hash(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    // 插入操作
    public void put(K key, V value) {
        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value); // 更新已存在的键的值
                return;
            }
        }
        buckets[index].add(new Entry<>(key, value));
        size++;
    }

    // 查找操作
    public V get(K key) {
        int index = hash(key);
        if (buckets[index] != null) {
            for (Entry<K, V> entry : buckets[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null; // 如果找不到对应的键，则返回null
    }

    // 删除操作
    public V remove(K key) {
        int index = hash(key);
        if (buckets[index] != null) {
            for (Entry<K, V> entry : buckets[index]) {
                if (entry.getKey().equals(key)) {
                    buckets[index].remove(entry);
                    size--;
                    return entry.getValue();
                }
            }
        }
        return null; // 如果找不到对应的键，则返回null
    }

    // 内部类，用于存储键值对
    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    // 获取当前哈希表大小
    public int getSize() {
        return size;
    }

    // 打印哈希表（仅用于演示）
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                sb.append("Bucket ").append(i).append(": ");
                for (Entry<K, V> entry : buckets[i]) {
                    sb.append('(').append(entry.getKey()).append(", ").append(entry.getValue()).append(") ");
                }
                sb.append(" \n");
            }
        }
        return sb.toString();
    }
}