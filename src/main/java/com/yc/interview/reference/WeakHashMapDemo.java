package com.yc.interview.reference;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 注意，用Integer作为key时，-127~127作为key不会被GC回收，可以用new Integer（1）裹一层
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        MyHashMap();

        System.out.println();
        System.out.println("=======以下为weakhashmap=========");
        System.out.println();

        MyWeakHashMap();
    }

    private static void MyWeakHashMap() {
        Map<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "weakhashmap";
        map.put(key,value);
        System.out.println(map);

        System.out.println("===========================");

        key = null;
        System.gc();
        System.out.println(map);
    }

    private static void MyHashMap() {
        Map<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "hashmap";
        map.put(key,value);
        System.out.println(map);

        System.out.println("===========================");

        key = null;
        System.gc();
        System.out.println(map);
    }
}
