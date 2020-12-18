package com.zixiu.proxy.static_proxy;

import com.zixiu.proxy.SubjectCustom;

/**
 * Author: Snail
 * Time:  2020/12/18 5:36 PM
 * FileName:  RealSubjectCustom
 * 简介：
 */
public class RealSubjectCustom implements SubjectCustom {
    @Override
    public void doSomething() {
        System.out.println("测试通用代理工厂类");
    }
}
