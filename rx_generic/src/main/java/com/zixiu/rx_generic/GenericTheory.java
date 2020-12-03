package com.zixiu.rx_generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Snail
 * Time:  2020/12/2 4:11 PM
 * FileName:  GenericTheory
 * 简介：泛型原理测试类
 */
public class GenericTheory {

    public static class GenericClass<K, V> {
        private K key;
        private V value;

        public void put(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V get(K k) {
            return value;
        }
    }


    /**
     * 类型擦除后GenericClass2<Object>
     *
     * @param <T>
     */
    private class GenericClass2<T> {

    }

    /**
     * 类型擦除后GenericClass3<ArrayList>
     * 当使用到Serializable时会将相应代码强制转换为Serializable
     * @param <T>
     */
    private class GenericClass3<T extends ArrayList & Serializable> {

    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("Key","Value");
        System.out.println(map.get("Key"));

        GenericClass<String,String> genericClass = new GenericClass<>();
        genericClass.put("Key","Value");
        System.out.println(genericClass.get("Key"));
    }

}
