package com.zixiu.rx_generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Author: Snail
 * Time:  2020/12/2 6:00 PM
 * FileName:  GenericUtils
 * 简介：泛型相关工具类
 */
class GenericUtils {

    private static <T> void sortAnyList(List<T> targetList, final String sortFiled, final boolean sortMode) {
        if (targetList == null || targetList.size() < 2 || sortFiled == null || sortFiled.length() == 0) {
            return;
        }

        Collections.sort(targetList, new Comparator<Object>() {
            @Override
            public int compare(Object obj1, Object obj2) {
                int retVal = 0;
                try {
                    String methodStr = "get" + sortFiled.toUpperCase().subSequence(0, 1) + sortFiled.substring(1);
                    Method method1 = ((T) obj1).getClass().getMethod(methodStr, null);
                    Method method2 = ((T) obj2).getClass().getMethod(methodStr, null);
                    if (sortMode) {
                        retVal = method1.invoke((T) obj1, null).toString().compareTo(method2.invoke((T) obj2, null).toString());
                    } else {
                        retVal = method2.invoke(((T) obj2), null).toString().compareTo(method1.invoke((T) obj1, null).toString());
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    System.out.println("List<" + (T) obj1.getClass().getName() + ">排序异常");
                    e.printStackTrace();
                }

                return retVal;
            }
        });

    }

    public static void main(String[] args) {
        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            movieList.add(new Movie("movie" + i, new Date()));
        }

        System.out.println("排序前:" + movieList.toString());

        GenericUtils.sortAnyList(movieList, "name", true);
        System.out.println("按正序排序后：" + movieList.toString());

        GenericUtils.sortAnyList(movieList, "name", false);
        System.out.println("按逆序排序后" + movieList.toString());

    }

    private static class Movie {
        private String name;
        private Date date;

        public Movie(String name, Date date) {
            this.name = name;
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "Movie{" +
                    "name='" + name + '\'' +
                    ", date=" + date +
                    '}';
        }
    }


}
