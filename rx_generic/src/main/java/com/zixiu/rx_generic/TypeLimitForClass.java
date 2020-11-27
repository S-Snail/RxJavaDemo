package com.zixiu.rx_generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Author: Snail
 * Time:  2020/11/27 4:36 PM
 * FileName:  TypeLimitForClass
 * 简介：
 * 类型变量限定 - 类
 */
public class TypeLimitForClass<T extends List & Serializable> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T t) {
        this.data = t;
    }

    private static <T extends Comparable<T>> T getMin(T a,T b){
        return a.compareTo(b)< 0? a : b;
    }

    public static void main(String[] args) {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("a");
        strList.add("b");
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(1);
        integerArrayList.add(2);
        integerArrayList.add(3);
        TypeLimitForClass<ArrayList> typeLimitForClass01 = new TypeLimitForClass<>();
        typeLimitForClass01.setData(strList);
        TypeLimitForClass<ArrayList> typeLimitForClass02 = new TypeLimitForClass<>();
        typeLimitForClass02.setData(integerArrayList);

        System.out.println("最短集合" + getMin(typeLimitForClass01.getData().size(),typeLimitForClass02.getData().size()));


        //测试非List类型
//        TypeLimitForClass<String> stringTypeLimitForClass = new TypeLimitForClass<>();
        //测试list类型，未Serializable
//        TypeLimitForClass<Test> testTypeLimitForClass = new TypeLimitForClass<>();
    }

    final static class Test implements List{

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(@Nullable Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public boolean add(Object o) {
            return false;
        }

        @Override
        public boolean remove(@Nullable Object o) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection collection) {
            return false;
        }

        @Override
        public boolean addAll(int i, @NonNull Collection collection) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Object get(int i) {
            return null;
        }

        @Override
        public Object set(int i, Object o) {
            return null;
        }

        @Override
        public void add(int i, Object o) {

        }

        @Override
        public Object remove(int i) {
            return null;
        }

        @Override
        public int indexOf(@Nullable Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(@Nullable Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator listIterator(int i) {
            return null;
        }

        @NonNull
        @Override
        public List subList(int i, int i1) {
            return null;
        }

        @Override
        public boolean retainAll(@NonNull Collection collection) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection collection) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection collection) {
            return false;
        }

        @NonNull
        @Override
        public Object[] toArray(@NonNull Object[] objects) {
            return new Object[0];
        }
    }

}
