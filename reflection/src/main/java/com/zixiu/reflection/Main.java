package com.zixiu.reflection;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Author: Snail
 * Time:  2020/12/14 3:19 PM
 * FileName:  Main
 * 简介：
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        System.out.println("-----每一个类都有一个Class对象，每当编译一个新类就会产生一个Class对象：基本类型，引用类型，数组以及关键字，都有Class对象--------");
        System.out.println("-----Java获取一个类的Class的四种方式----");

        Class<?> class1 = null;
        Class<?> class2 = null;
        Class<?> class3 = null;
        Class<?> class4 = null;

//        //方式一：Class.forName()
//        System.out.println("-------方式一：Class.forName()-------");
//        class1 = Class.forName(Person.CLASS_NAME);
//        System.out.println("Class1的包名：" + class1.getPackage().getName());
//        System.out.println("Class1的完整类名：" + class1.getClass().getName());
//
//        System.out.println("-------方式二：Person.class-------");
//        class2 = Person.class;
//        System.out.println("Class2的包名：" + class2.getPackage().getName());
//        System.out.println("Class2的完整类名：" + class2.getClass().getName());
//
//        System.out.println("-------方式三：personInstance.getClass()-------");
//        Person person = new Person();
//        class3 = person.getClass();
//        System.out.println("Class3的包名：" + class3.getPackage().getName());
//        System.out.println("Class3的完整类名：" + class3.getClass().getName());
//
//        System.out.println("-------ClassLoader.getSystemClassLoader.loadClass(CLASS_NAME)-------");
//        class4 = ClassLoader.getSystemClassLoader().loadClass(Person.CLASS_NAME);
//        System.out.println("Class4的包名：" + class4.getPackage().getName());
//        System.out.println("Class4的完整类名：" + class4.getClass().getName());

//        /**
//         * 通过Class获取实例
//         */
//        //写法1
//        class1 = Class.forName(Person.CLASS_NAME);
//        Person person = (Person) class1.newInstance();
//        person.setAge(20);
//        person.setName("王武");
//        System.out.println("Person1 -> " + person.getName() + ":" + person.getAge());
//
//        //写法2
//        class2 = Person.class;
//        Person person2 = (Person) class2.newInstance();
//        person2.setAge(30);
//        person2.setName("李六");
//        System.out.println("Person2 -> " + person2.getName() + ":" + person2.getAge());
//
//
//        //写法3
//        class3 = ClassLoader.getSystemClassLoader().loadClass(Person.CLASS_NAME);
//        Person person3 = (Person)class3.newInstance();
//        person3.setAge(18);
//        person3.setName("小七");
//        System.out.println("Person3 -> "+ person3.getName() + ":" + person3.getAge());

//        /**
//         * 通过Class获取Constructor
//         */
//        Class<?> personClass = Class.forName(Person.CLASS_NAME);
//        Person person1 = null;
//        Person person2 = null;
//        System.out.println("-------获取全部的Constructor-------");
//        Constructor<?>[] constructors = personClass.getConstructors();
//        for (Constructor constructor : constructors) {
//            System.out.println(constructor);
//        }
//        System.out.println("从构造器集合中获取一个Constructor对象");
//        Constructor<?> constructorWithNoParams = constructors[1];
//        System.out.println(constructorWithNoParams);
//
//        System.out.println("-------调用构造器的newInstance方法创建对象-------");
//        person1 = (Person) constructorWithNoParams.newInstance();
//        person1.setAge(30);
//        person1.setName("Jay");
//        System.out.println("person1 -> " + person1.getName() + ":" + person1.getAge());
//
//        System.out.println("-------获取指定的Constructor对象-------");
//        Constructor<?> constructorWithTwoParams = personClass.getConstructor(int.class, String.class);
//        System.out.println(constructorWithTwoParams);
//
//        System.out.println("-------调用构造器的newInstance()方法创建对象");
//        person2 = (Person) constructorWithTwoParams.newInstance(100, "Snail");
//        System.out.println("person2 -> " + person2.getName() + ":" + person2.getAge());

//        /**
//         * 通过Class获取Field
//         */
//        Class<?> personClass = Class.forName(Person.CLASS_NAME);
//        Class<?> childClass = Class.forName(Child.CLASS_NAME);
//        Person person = (Person) personClass.newInstance();
//
//        System.out.println("-------获取公用和私有的所有字段，但不能获取父类的字段");
//        Field[] declaredFields = personClass.getDeclaredFields();
//        for (Field field : declaredFields) {
//            System.out.println(" " + field.getName());
//        }
//        System.out.println();
//
//        System.out.println("-------获取指定字段-------");
//        Field field = personClass.getDeclaredField("name");
//        System.out.println("字段名：" + field.getName());
//
//        System.out.println("-------获取指定字段的值-------");
//        person.setAge(20);
//        person.setName("李小六");
//        field.setAccessible(true);
//        Object val = field.get(person);
////        field.setAccessible(false);
//        //获取name的值
//        System.out.println(field.getName() + ":" + val);
//
//        System.out.println("-------设置指定对象指定字段的值-------");
//        field.set(person,"Jay");
//        System.out.println(field.getName() + ":" + person.getName());
//
//        System.out.println("-------字段是私有的，无论是读还是写，都必须调用setAccessAble(true)方法-------");
//        person.setName("张三");
//        person.setAge(30);
//        Field ageField = personClass.getDeclaredField("age");
//        ageField.setAccessible(true);
//        //读
//        Object ageVal = ageField.get(person);
//        System.out.println(ageField.getName() + ":" + ageVal);
//        //写
//        ageField.set(person,100);
//        System.out.println(ageField.getName() + ":" + person.getAge());

        /**
         * 通过Class获取Method
         */


    }
}
