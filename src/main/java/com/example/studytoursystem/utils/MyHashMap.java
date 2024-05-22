package com.example.studytoursystem.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

public class MyHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private List<MyEntry<K, V>>[] buckets;
    private int size;

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashMap(int capacity) {
        this.buckets = new LinkedList[capacity];
    }

    private int hash(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % buckets.length);
    }

    public boolean containsKey(K key) {
        int index = hash(key);
        List<MyEntry<K, V>> bucket = buckets[index];
        if (bucket != null) {
            for (MyEntry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    public V put(K key, V value) {
        int index = hash(key);
        List<MyEntry<K, V>> bucket = buckets[index];
        if (bucket == null) {
            bucket = new LinkedList<>();
            buckets[index] = bucket;
        }

        for (MyEntry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }

        bucket.add(new MyEntry<>(key, value));
        size++;
        return null;
    }

    public V get(K key) {
        int index = hash(key);
        List<MyEntry<K, V>> bucket = buckets[index];
        if (bucket != null) {
            for (MyEntry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        return value != null ? value : defaultValue;
    }

    // Inner class to represent the entries in the map
    @Getter
    private static class MyEntry<K, V> {
        private final K key;
        @Setter
        private V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}