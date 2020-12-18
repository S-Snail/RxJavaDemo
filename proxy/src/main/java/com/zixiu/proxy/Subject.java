package com.zixiu.proxy;

/**
 * Author: Snail
 * Time:  2020/12/18 11:20 AM
 * FileName:  Subject
 * 简介：
 */

/**
 * 抽象角色：指代理角色和真实角色对外提供的公共方法，一般是接口
 */
public interface Subject {
    String request(String data);

    int response(int value);
}
