package com.zixiu.rx_generic;

import androidx.annotation.NonNull;

/**
 * Author: Snail
 * Time:  2020/11/27 3:51 PM
 * FileName:  GenericMethod3
 * 简介：
 */
public class GenericMethod3 {

    static class Animal {
        @NonNull
        @Override
        public String toString() {
            return "Animal";
        }
    }

    static class Dog {
        @NonNull
        @Override
        public String toString() {
            return "Dog";
        }
    }

    static class Fruit {
        @NonNull
        @Override
        public String toString() {
            return "Fruit";
        }
    }

    static class GenericClass<T> {

        public void show01(T t) {
            System.out.println(t.toString());
        }

        public <T> void show02(T t) {
            System.out.println(t.toString());
        }

        public <K> void show03(K k) {
            System.out.println(k.toString());
        }
    }

    public static void main(String[] args) {
        Animal animal  = new Animal();
        Dog dog = new Dog();
        Fruit fruit = new Fruit();
        GenericClass<Animal> genericClass = new GenericClass<>();
        //泛型在初始化时，限定了参数类型
        genericClass.show01(animal);
//        genericClass.show01(fruit);

        //泛型方法的参数类型在使用时指定
        genericClass.show02(dog);
        genericClass.show02(fruit);

        genericClass.<Animal>show03(animal);
        genericClass.<Dog>show03(dog);
        genericClass.show03(fruit);


    }

}
