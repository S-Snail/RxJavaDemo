package com.zixiu.rx_generic;

/**
 * Author: Snail
 * Time:  2020/11/30 4:31 PM
 * FileName:  GenericByWildCard
 * 简介：通配符的类型
 */
public class GenericByWildCard {

    private static class Food {

    }

    private static class Fruit extends Food {
        private String color;

        public void setColor(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }

    private static class Apple extends Fruit {

    }

    private static class Orange extends Fruit {

    }

    private static class HongFuSHi extends Apple {

    }

    private static void print(GenericClass<Fruit> genericClass){
        System.out.println(genericClass.getData().getColor());
    }

    private static void use(){
        GenericClass<Fruit> fruitGenericClass = new GenericClass<>();
        print(fruitGenericClass);
        GenericClass<Orange> orangeGenericClass = new GenericClass<>();
//        print(orangeGenericClass);
    }

    /**
     * <? extends Parent> 指定了泛型类型的上届
     */
    private static void printExtends(GenericClass<? extends Fruit> genericClass){
        System.out.println(genericClass.getData().getColor());
    }

    public static void main(String[] args) {
        useExtends();
    }

    private static void useExtends(){
        GenericClass<Apple> appleGenericClass = new GenericClass<>();
        Apple appleTest = new Apple();
        appleTest.setColor("apple -> red");
        appleGenericClass.setData(appleTest);
        printExtends(appleGenericClass);
        GenericClass<Orange> orangeGenericClass = new GenericClass<>();
        printExtends(orangeGenericClass);

        GenericClass<Food> foodGenericClass = new GenericClass<>();
        //Food是Fruit的父类，超过了泛型上届范围，类型不匹配
//        printExtends(foodGenericClass);

        //表示GenericClass的类型参数的上届是Fruit
        GenericClass<? extends Fruit> extendFruitGenericClass = new GenericClass<>();
        Apple apple = new Apple();
        Fruit fruit = new Fruit();
        /**
         * <? extends X> 指定了泛型参数的上届为X,类型参数为X的子类，get()方法返回的是X(或者X的子类)，编译器是可以确定知道的
         * 但是set(X)方法传入的是个X,具体是X的哪个子类，不知道
         * 总结：可以安全的访问数据，返回的是X或者X的子类，并且不能写入一个非null的数据
         */
//        extendFruitGenericClass.setData(apple);
//        extendFruitGenericClass.setData(fruit);

        fruit = extendFruitGenericClass.getData();
    }

    /**
     * <? super X>指定泛型类型的下届
     */
    private static void printSuper(GenericClass<? super Apple> genericClass){
        System.out.println(genericClass.getData());
    }

    private static void useSuper(){
        GenericClass<Apple> appleGenericClass = new GenericClass<>();
        printSuper(appleGenericClass);
        GenericClass<Fruit> fruitGenericClass = new GenericClass<>();
        printSuper(fruitGenericClass);
        GenericClass<HongFuSHi> hongFuSHiGenericClass = new GenericClass<>();
        //HongFuShi是Apple的子类，达不到泛型下届
//        printSuper(hongFuSHiGenericClass);

        GenericClass<Orange> orangeGenericClass = new GenericClass<>();
        //Orange和Apple是兄弟关系，没有继承关系，类型不匹配
//        printSuper(orangeGenericClass);

        //表示 GenericClass泛型参数的下届是Apple
        GenericClass<? super Apple> supperAppleGenericClass = new GenericClass<>();
        supperAppleGenericClass.setData(new Apple());
        supperAppleGenericClass.setData(new HongFuSHi());
        /**
         * ? super X 表示类型的下届，类型参数是X的下届（包括X本身）
         * 那么可以肯定的说，get()方法返回的一定是X的超类，那么到底是哪个超类，不知道
         * 但是可以肯定的说，Object一定是它的超类，所以get方法返回Object.
         * 编译器是可以确定知道的。对于set方法来说，编译器不知道它需要的确切类型，但是X和X的子类是可以安全的转型为X的
         * 总结：主要用于可以安全的写入数据，可以写入X及其子类型
         */
//        supperAppleGenericClass.setData(new Fruit());

        //get方法只会返回一个Object类型的值
        Object data = supperAppleGenericClass.getData();
    }

    /**
     * <?>指定了没有限定的通配符
     */
    private static void printNoLimit(GenericClass<?> genericClass){
        System.out.println(genericClass.getData());
    }

    private static void useNoLimit(){
        GenericClass<Food> foodGenericClass = new GenericClass<>();
        printNoLimit(foodGenericClass);
        GenericClass<Fruit> fruitGenericClass = new GenericClass<>();
        printNoLimit(fruitGenericClass);
        GenericClass<Apple> appleGenericClass = new GenericClass<>();
        printNoLimit(appleGenericClass);

        GenericClass<?> genericClass = new GenericClass<>();
        //set方法不能被调用
//        genericClass.setData(foodGenericClass);
//        genericClass.setData(new Object());

        //返回值只能赋值给Object
        Object data = genericClass.getData();
    }

}
