package com.zixiu.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

/**
 * Author: Snail
 * Time:  2020/12/14 3:19 PM
 * FileName:  Main
 * 简介：
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
//        System.out.println("-----每一个类都有一个Class对象，每当编译一个新类就会产生一个Class对象：基本类型，引用类型，数组以及关键字，都有Class对象--------");
//        System.out.println("-----Java获取一个类的Class的四种方式----");
//        Class<?> class1 = null;
//        Class<?> class2 = null;
//        Class<?> class3 = null;
//        Class<?> class4 = null;
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
//        Class<?> personClass = Class.forName(Person.CLASS_NAME);
//        Class<?> childClass = Class.forName(Child.CLASS_NAME);
//        System.out.println("------获取Class中的所有方法，不包括私有方法，且获取从父类继承来的所有方法-------");
//        Method[] childClassMethods = childClass.getMethods();
//        for (Method method : childClassMethods) {
//            System.out.println(" " + method.getName() + "()");
//        }
//
//        System.out.println("-------获取所有方法，包括private方法，且只获取当前类的方法-------");
//        Method[] childClassDeclaredMethods = childClass.getDeclaredMethods();
//        for (Method method : childClassDeclaredMethods) {
//            System.out.println(" " + method.getName() + "()");
//        }
//
//        System.out.println("-------获取指定的方法，需要参数名和参数列表，无参则不需要写");
//        Method setNameMethod = personClass.getDeclaredMethod("setName",String.class);
//        System.out.println(setNameMethod);
//        Method setIntAge = personClass.getDeclaredMethod("setAge", int.class);
//        System.out.println(setIntAge);
//        Method setIntegerAge = personClass.getDeclaredMethod("setAge", Integer.class);
//        System.out.println(setIntegerAge);
//
//        System.out.println("-------执行方法，第一个参数是指执行哪一个对象的方法，第二个参数是执行方法时具体的实参-------");
//        Object personInstance = personClass.newInstance();
//        setIntAge.invoke(personInstance,18);
//
//        Method getNameMethod = personClass.getDeclaredMethod("getName");
//        Person person = (Person)personClass.newInstance();
//        person.setName("Snail");
//        getNameMethod.invoke(person);
//
//        System.out.println("-------执行私有方法，必须加上setAccessable(true)-------");
//        Method privateChildMethod = childClass.getDeclaredMethod("privateChildMethod");
//        Object childInstance = childClass.newInstance();
//        privateChildMethod.setAccessible(true);
//        privateChildMethod.invoke(childInstance);
//        privateChildMethod.setAccessible(false);

        /**
         * 通过Class获取类加载器
         */
//        Class<?> childClass = Class.forName(Child.CLASS_NAME);
//        String classLoaderName = childClass.getClassLoader().getClass().getName();
//        System.out.println("类加载器名 -> " + classLoaderName);
//
//        System.out.println("------获取一个系统的类加载器（系统的类加载器，可以获取，当前的类就是它加载的）-------");
//        //1、获取一个系统类的加载器（系统的类加载器，可以获取，当前的类就是它加载的）
//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        System.out.println(classLoader);
//        System.out.println("-------获取系统类的加载器的父加载器（扩展类加载器，可以获取）-------");
//        //2、获取系统类的加载器的父加载器（扩展类加载器，可以获取）
//        classLoader = classLoader.getParent();
//        System.out.println(classLoader);
//        System.out.println("-------获取扩展类的加载器的父加载器（引导类加载器，不可获取）-------");
//        //3、获取扩展类加载器的父加载器（引导类加载器，不可获取）
//        classLoader = classLoader.getParent();
//        System.out.println(classLoader);
//        System.out.println("-------测试当前类是由哪个类加载器加载的（系统类加载器）");
//        //4、测试当前类是由哪个类加载器加载的（系统类加载器）
//        classLoader = Class.forName(Child.CLASS_NAME).getClassLoader();
//        System.out.println(classLoader);
//        System.out.println("-------测试JDK提供的Object类是由哪个类加载器加载的（引导类加载器，不可获取）-------");
//        //5、测试JDK提供的Object类是由哪个类加载器加载的（引导类加载器，不可获取）
//        classLoader = Class.forName("java.lang.Object").getClassLoader();
//        System.out.println(classLoader);

        /**
         * 通过Class获取类的其他信息（接口、注解、修饰符等）
         */
        Class<?> personClass = Class.forName(Person.CLASS_NAME);
        Class<?> childClass = Class.forName(Child.CLASS_NAME);
//
//        System.out.println("-------获取类的包名和全类名-------");
//        System.out.println("类的包名：" + personClass.getPackage().getName());
//        System.out.println("类的完整类名：" + personClass.getName());
//
//        System.out.println("-------取得父类名-------");
//        System.out.println("当前类的父类名：" + childClass.getSuperclass());
//
//        System.out.println("-------取得类中的属性-------");
//        Field[] personClassDeclaredFields = personClass.getDeclaredFields();
//        for (Field field : personClassDeclaredFields) {
//            System.out.println("类中的成员：" + field);
//        }
//
//        System.out.println("-------取得类方法-------");
//        Method[] personClassDeclaredMethods = personClass.getDeclaredMethods();//获取当前类的所有方法，包括私有方法
//        for (Method method : personClassDeclaredMethods) {
//            System.out.println("函数名：" + method.getName());
//            System.out.println("函数返回值类型：" + method.getReturnType());
//            System.out.println("函数访问修饰符：" + Modifier.toString(method.getModifiers()));
//            System.out.println("函数代码写法：" + method);
//            System.out.println("**************************************");
//        }

//        System.out.println("-------取得构造方法-------");
//        Constructor<?>[] personClassConstructors = personClass.getConstructors();
//        for (Constructor constructor : personClassConstructors) {
//            System.out.println(constructor);
//        }

//        System.out.println("-------测试当前类是由哪个类加载器加载的（系统加载器）-------");
//        ClassLoader personClassClassLoader = personClass.getClassLoader();
//        System.out.println(personClassClassLoader);

//        System.out.println("-------取得类实现的接口-------");
//        Class<?>[] personClassInterfaces = personClass.getInterfaces();
//        for (Class<?> interfaceAddress : personClassInterfaces) {
//            System.out.println(interfaceAddress.getName());
//            for (Method method : interfaceAddress.getDeclaredMethods()) {
//                System.out.println("函数名：" + method.getName());
//                System.out.println("函数代码写法：" + method);
//            }
//        }

        System.out.println("-------取得类注解-------");
        Annotation[] personClassAnnotations = personClass.getAnnotations();
        for (Annotation annotation : personClassAnnotations) {
            System.out.println(annotation);
        }
        ClassAnnotation annotation = personClass.getAnnotation(ClassAnnotation.class);
        System.out.println("path = " + annotation.path() + "\tdesc = " + annotation.desc());

    }
}
